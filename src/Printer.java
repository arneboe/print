package src;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by arne on 19.03.16.
 */
public class Printer {

  private FileOutputStream writer = null;
//  Charset charset = new IBM437();//is the default charset of the printer
  private final int imgWidth = 392;

   void open(final String device)
  {
    try {
      writer = new FileOutputStream(device);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**Clear the print buffer and reset offset to begin of line */
  public void reset()
  {
    if(null != writer)
    {
      try {
        writer.write(new byte[]{0x1B, 0x40});
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void lineFeed(final int num)
  {
    if(null != writer)
    {
      try {
        for(int i = 0; i < num; ++i)
          writer.write(new byte[]{0x0A});
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void close()
  {
    if(null != writer)
    {
      try {
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void text(final String value, Font font, boolean mirror)
  {
    AttributedString str = new AttributedString(value);
    str.addAttribute(TextAttribute.FONT, font);
    AttributedCharacterIterator paragraph = str.getIterator();
    final int paragraphStart = paragraph.getBeginIndex();
    final int paragraphEnd = paragraph.getEndIndex();
    final int initialHeight = 6000;//FIXME cant draw more text than 6000 pixels?
    BufferedImage img = new BufferedImage(imgWidth, initialHeight, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = (Graphics2D)img.getGraphics();
    g2d.fillRect(0, 0, imgWidth, initialHeight);
    FontRenderContext frc = g2d.getFontRenderContext();
    LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, frc);
    float breakWidth = (float)imgWidth - 8;
    float drawPosY = 0;
    // Set position to the index of the first character in the paragraph.
    lineMeasurer.setPosition(paragraphStart);
    g2d.setColor(Color.BLACK);
    // Get lines until the entire paragraph has been displayed.
    while (lineMeasurer.getPosition() < paragraphEnd)
    {
      TextLayout layout = lineMeasurer.nextLayout(breakWidth);
      final float drawPosX = 8;
      drawPosY += layout.getAscent();
      layout.draw(g2d, drawPosX, drawPosY);
      drawPosY += layout.getDescent() + layout.getLeading();
    }
    g2d.dispose();
    BufferedImage croppedImg = img.getSubimage(0, 0, imgWidth, (int)drawPosY);

    if(mirror)
    {
      AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
      tx = AffineTransform.getScaleInstance(-1, -1);
      tx.translate(-croppedImg.getWidth(null), -croppedImg.getHeight(null));
      AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      croppedImg = op.filter(croppedImg, null);
    }
    File outputfile = new File("/home/arne/test22.png");//only for testing
    try {
      ImageIO.write(croppedImg, "png", outputfile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    sendImg(croppedImg);
  }

  public void image(final String path)
  {
    try {
      FileInputStream in = new FileInputStream(path);
      BufferedImage img = ImageIO.read(in);
      image(img);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**converts image to black&white and prints it */
  public void image(BufferedImage input)
  {
    if(null == writer)
      return;

    //scale it
    final int targetWidth = imgWidth;
    final double scale = input.getWidth() / targetWidth;
    final int targetHeight = (int) Math.ceil(input.getHeight() / scale);

    BufferedImage img = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = img.createGraphics();

    g.drawImage(input, 0, 0, targetWidth, targetHeight, 0, 0, input.getWidth(),
                input.getHeight(), null);
    g.dispose();

    //convert to b&w using atkinson dithering with otsu thresholding
    atkinsonDither(img);

    File outputfile = new File("/home/arne/test.png");//only for testing
    try {
      ImageIO.write(img, "png", outputfile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    sendImg(img);
  }

  private boolean isWhite(int x, int y, BufferedImage img)
  {
    Color c = new Color(img.getRGB(x, y));
    int red = c.getRed();
    int green = c.getGreen();
    int blue = c.getBlue();
    int brightness = (red + green + blue) / 3;

    return brightness > 128;
  }

  private byte[] printDraw(BufferedImage img)
  {

    byte[] imgbuf = new byte[img.getWidth() / 8 * img.getHeight()];
    byte[] bitbuf = new byte[img.getWidth() / 8];
    int s = 0;

    for (int i = 0; i < img.getHeight(); ++i) {
      int t;
      for (t = 0; t < img.getWidth() / 8; ++t) {
        byte p0;
        if(isWhite(t * 8 + 0, i, img)) {
          p0 = 0;
        } else {
          p0 = 1;
        }

        byte p1;
        if(isWhite(t * 8 + 1, i, img)) {
          p1 = 0;
        } else {
          p1 = 1;
        }

        byte p2;
        if(isWhite(t * 8 + 2, i, img)) {
          p2 = 0;
        } else {
          p2 = 1;
        }

        byte p3;
        if(isWhite(t * 8 + 3, i, img)) {
          p3 = 0;
        } else {
          p3 = 1;
        }


        byte p4;
        if(isWhite(t * 8 + 4, i, img)) {
          p4 = 0;
        } else {
          p4 = 1;
        }

        byte p5;
        if(isWhite(t * 8 + 5, i, img)) {
          p5 = 0;
        } else {
          p5 = 1;
        }

        byte p6;
        if(isWhite(t * 8 + 6, i, img)) {
          p6 = 0;
        } else {
          p6 = 1;
        }

        byte p7;
        if(isWhite(t * 8 + 7, i, img)) {
          p7 = 0;
        } else {
          p7 = 1;
        }

        int value = p0 * 128 + p1 * 64 + p2 * 32 + p3 * 16 + p4 * 8 + p5 * 4 + p6 * 2 + p7;
        bitbuf[t] = (byte) value;
      }

      for (t = 0; t < img.getWidth() / 8; ++t) {
        imgbuf[s] = bitbuf[t];
        ++s;
      }
    }
    return imgbuf;
  }

  private void sendImg(BufferedImage img)
  {
    byte[] data = printDraw(img);

    byte[] temp = new byte[8 + img.getWidth() / 8];
    int index = 0;
    for(int i = 0 ; i < img.getHeight() ; i++ ){
      int s = 0;
      temp[s++] = 0x1D; //GS
      temp[s++] = 0x76; // v
      temp[s++] = 0x30; // 0
      temp[s++] = 0x00; //mode = normal
      temp[s++] = (byte)(img.getWidth() / 8); //width low byte
      temp[s++] = 0x00; //width high byte
      temp[s++] = 0x01; //height low byte
      temp[s++] = 0x00; //height high byte
      for(int j = 0 ; j < (img.getWidth() / 8) ; j++ ) {
        temp[s++] = data[index++];
      }
      try {
        writer.write(temp);
        writer.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void atkinsonDither(BufferedImage img) {
    final double w1 = 1.0/8.0;
    final int h = img.getHeight();
    final int w = img.getWidth();
    final int threshold = otsuTreshold(img);
    for (int y=0; y<h; y++){
      for (int x=0; x<w; x++){
        int oldpixel = getBrightness(x,y, img);
        int newpixel = oldpixel < threshold? 0 : 255;
        setPixel(x, y, newpixel, img);
        final double quant_error = oldpixel - newpixel;
        setPixel(x+1,y, (int)(getBrightness(x+1,y, img) + w1 * quant_error), img);
        setPixel(x+2,y, (int)(getBrightness(x+2,y, img) + w1 * quant_error), img);
        setPixel(x-1,y+1, (int)(getBrightness(x-1,y+1, img) + w1 * quant_error), img);
        setPixel(x,y+1, (int)(getBrightness(x,y+1, img) + w1 * quant_error), img);
        setPixel(x+1,y+1, (int)(getBrightness(x+1,y+1, img) + w1 * quant_error), img);
        setPixel(x,y+2, (int)(getBrightness(x,y+2, img) + w1 * quant_error), img);
      }
    }
  }

  private int otsuTreshold(BufferedImage original) {

    int[] histogram = imageHistogram(original);
    int total = original.getHeight() * original.getWidth();

    float sum = 0;
    for(int i=0; i<256; i++) sum += i * histogram[i];

    float sumB = 0;
    int wB = 0;
    int wF = 0;

    float varMax = 0;
    int threshold = 0;

    for(int i=0 ; i<256 ; i++) {
      wB += histogram[i];
      if(wB == 0) continue;
      wF = total - wB;

      if(wF == 0) break;

      sumB += (float) (i * histogram[i]);
      float mB = sumB / wB;
      float mF = (sum - sumB) / wF;

      float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

      if(varBetween > varMax) {
        varMax = varBetween;
        threshold = i;
      }
    }

    return threshold;

  }

  private int[] imageHistogram(BufferedImage input) {

    int[] histogram = new int[256];

    for(int i=0; i<histogram.length; i++) histogram[i] = 0;

    for(int i=0; i<input.getWidth(); i++) {
      for(int j=0; j<input.getHeight(); j++) {
        int brigh = getBrightness(i, j, input);
        histogram[brigh]++;
      }
    }

    return histogram;

  }

  private int getBrightness(int x, int y, BufferedImage img)
  {
    if(x >= img.getWidth() || y >= img.getHeight() || x < 0 || y < 0) return 0;
    Color c = new Color(img.getRGB(x, y));
    int red = c.getRed();
    int green = c.getGreen();
    int blue = c.getBlue();
    return (int)((0.21 * red) + (0.71 * green) + (0.07 * blue));
  }

  private void setPixel(int x, int y, int brightness, BufferedImage img)
  {
    if(x >= img.getWidth() || y >= img.getHeight() || x < 0 || y < 0) return;
    if(brightness < 0) brightness = 0;
    if(brightness > 255) brightness = 255;
    Color c = new Color(brightness, brightness, brightness);
    img.setRGB(x, y, c.getRGB());
  }


}

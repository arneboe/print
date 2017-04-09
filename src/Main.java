package src;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

/**
 * print images to thermal printer
 */
public class Main {

/*
  public static void writeIWTFY(ArrayList<IWroteThisForYouEntry> entries) {

    try {
      FileOutputStream out = new FileOutputStream("/home/arne/iwtfy_entries");
      ObjectOutputStream oos = new ObjectOutputStream(out);
      oos.writeObject(entries);
      oos.flush();
    } catch (Exception e) {
      System.out.println("Problem serializing: " + e);
    }
  }

  public static ArrayList<IWroteThisForYouEntry> readIWTFY() throws ClassNotFoundException, IOException {

    ArrayList<IWroteThisForYouEntry> entries = null;
    try {
      FileInputStream in = new FileInputStream("/home/arne/iwtfy_entries");
      ObjectInputStream ois = new ObjectInputStream(in);
      entries = (ArrayList<IWroteThisForYouEntry>) (ois.readObject());
    } catch (Exception e) {
      System.out.println("Problem serializing: " + e);
    }
    return entries;
  }

  public static void crawl() throws IOException, ClassNotFoundException {
    ArrayList<IWroteThisForYouEntry> entries = IWroteThisForYouCrawler.crawl();
    writeIWTFY(entries);
  }
*/

  public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException {

//    ArrayList<IWroteThisForYouEntry> entries = readIWTFY();
//    for(IWroteThisForYouEntry e : entries)
//    {
//      System.out.println("------------------------");
//      System.out.println(e.title);
//      System.out.println(e.text);
//    }

  //crawl();


    Printer printer = new Printer();
    printer.open("/dev/usb/lp0");
    Porn p = new Porn();
    for(int i = 0; i < 300; ++i)
    {
      final String s = p.getNext();
      printer.text(s, new Font("Serif", Font.PLAIN, 20), false);
      printer.lineFeed(1);
    }


/*

    for (int i = 0; i < 4; ++i) {
      FileInputStream in = new FileInputStream("/home/arne/1.jpg");
      BufferedImage screen = ImageIO.read(in);
      final int targetWidth = 390;
      final double scale = screen.getWidth() / targetWidth;
      final int targetHeight = (int) Math.ceil(screen.getHeight() / scale);
      //width must be a multiple of 8
      BufferedImage img = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = img.createGraphics();

      g.drawImage(screen, 0, 0, targetWidth, targetHeight, 0, 0, screen.getWidth(),
              screen.getHeight(), null);
      g.dispose();

      atkinsonDither(img);
      File outputfile = new File("/home/arne/test.png");
      ImageIO.write(img, "png", outputfile);


      try {
        FileOutputStream writer = new FileOutputStream("/dev/usb/lp0");
        writer.write(new byte[]{0x1B, 0x40});
        sendImg(img, writer);
        writer.close();

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
*/
  }

}

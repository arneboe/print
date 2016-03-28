import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by arne on 28.03.16.
 */
public class SerializeableImage implements Serializable {
    public transient BufferedImage image;

    public SerializeableImage(BufferedImage img) {
      image = img;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
      out.defaultWriteObject();
        ImageIO.write(image, "png", out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      image = ImageIO.read(in);
    }
}

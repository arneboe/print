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
    printer.open("/dev/usb/lp6");
    printer.imagesFromFolder("/home/arne/lenas");
    printer.close();


  }

}

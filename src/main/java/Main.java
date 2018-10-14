import java.awt.*;
import java.io.*;
import java.util.HashSet;
import java.io.File;

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

  public static String findPrinter() {

    File dir = new File("/dev/usb");
    File[] directoryListing = dir.listFiles();
    if (directoryListing != null) {
      for (File child : directoryListing) {
        if(child.getName().startsWith("lp"))
          return "/dev/usb/" + child.getName();
      }
    }
    throw new RuntimeException("could not find printer");
  }

  public static void main(String args[]) {

    Printer printer = new Printer();
    try {
      printer.open(findPrinter());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }

    try {
      Porn porn = new Porn();
      HashSet<String> porns = new HashSet<>();

      for(int i = 0; i < 50; ++i) {
        final String p = porn.getNext();
        if(porns.contains(p))
          continue;
        porns.add(p);
        printer.text(p, new Font("Serif", Font.PLAIN, 20), false);
        //printer.lineFeed(1);
      }
    } catch (Exception e) {
      printer.text("NO INTERNET CONNECTION", new Font("Serif", Font.BOLD, 20), false);
      printer.lineFeed(4);
    }

    printer.close();

  }

}

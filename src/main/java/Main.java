import java.awt.*;
import java.io.*;
import java.util.HashSet;

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

    Porn porn = new Porn();
    HashSet<String> porns = new HashSet<>();
    Printer printer = new Printer();
    printer.open("/dev/printer");

    for(int i = 0; i < 1000; ++i) {
      final String p = porn.getNext();
      if(porns.contains(p))
        continue;
      porns.add(p);
      printer.text(p, new Font("Serif", Font.PLAIN, 20), false);
      //printer.lineFeed(1);
    }


    /*
    for(int i = 0; i < 2; ++i) {
      printer.imagesFromFolder("/home/arne/lenas");
      printer.lineFeed(20);
    }
    */

    printer.close();


  }

}

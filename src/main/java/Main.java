import java.awt.*;
import java.io.*;
import java.io.File;


/**
 * print images to thermal printer
 */
public class Main {

  private static void sleep() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) { }
  }

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

  private static void print(Porn porn, Printer printer, int count) {
    for(int i = 0; i < count; ++i) {

      final String text;
      try {
        text = porn.getNext();
        printer.text(text, new Font("Serif", Font.PLAIN, 20), false);
      } catch (Exception e) {
        System.out.println("Error while printing (internet broken?)");
        return;
      }
    }
  }



//  public static void download() throws Exception {
//    Porn porn = new Porn();
//    TreeSet<String> words = new TreeSet<>();
//    final String path = "/home/arne/porn_search_terms.txt";
//
//    //load existing words
//    File f = new File(path);
//    f.createNewFile(); //if not exist
//    /*
//    Scanner s = new Scanner(f);
//    int i = 0;
//    while (s.hasNextLine()) {
//      words.add(s.nextLine());
//      ++i;
//    }
//    s.close();
//
//    System.out.println("loaded: " + i + " " + words.size());
//    */
//
//
//    try(FileWriter fw = new FileWriter(path, true);
//        BufferedWriter bw = new BufferedWriter(fw);
//        PrintWriter out = new PrintWriter(bw))
//    {
//        int count = 1; //start at one to avoid long sleep when lots of duplicates
//        int duplicates = 0;
//
//        while(count < 500000) {
//
//          if(count % 10000 == 0) {
//            Thread.sleep(10000);
//            System.out.println("got: " + count);
//            out.flush();
//          }
//
//          final String word = porn.getNext();
//          out.println(word);
//          ++count;
//        }
//      //more code
//    } catch (IOException e) {
//      //exception handling left as an exercise for the reader
//    }
//
//  }


  public static void main(String args[]) {
    TheList theList = new TheList("/home/pi/TheList.csv");




//    try {
//      download();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return;


    Printer printer = new Printer();

    while(true) {
      try {
        printer.open(findPrinter());
        break;
      } catch (RuntimeException e) {
        System.out.println("Printer not found, waiting");
        sleep();
      } catch (FileNotFoundException e) {
        System.out.println("Printer not found, waiting");
        sleep();

      }
    }
    System.out.println("Found printer");

//    Porn porn = null;
//    while(true) {
//      try {
//        porn = new Porn();
//        break;
//      } catch (Exception e) {
//        System.out.println("No internet, waiting...");
//        sleep();
//      }
//    }
//    System.out.println("Got internet");

 //   final GpioController gpio = GpioFactory.getInstance();
 //   GpioPinDigitalInput button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_23, "MyButton", PinPullResistance.PULL_UP); // PIN RESISTANCE (optional)

    for(TheList.ListEntry e : theList.records)
    {

      //if(e.regionOfOrigin.equals("unknown"))
      //  printer.text("Unknown Location", new Font(" Monospaced", Font.BOLD, 22), false);
      //else
      //  printer.text(e.regionOfOrigin, new Font(" Monospaced", Font.BOLD, 22), false);


      if(Integer.valueOf(e.number.trim()) > 1 && e.nameGenderAge.contains("Name(s)"))
        e.nameGenderAge = e.nameGenderAge.replace("Name(s)", "Names");

      if(e.number.trim().equals("1") && e.nameGenderAge.contains("Name(s)"))
        e.nameGenderAge = e.nameGenderAge.replace("Name(s)", "Name");


      printer.text(e.source, new Font(" Monospaced", Font.PLAIN, 20), true);
      printer.text("Reported by:", new Font(" Monospaced", Font.PLAIN, 20), true);
      printer.lineFeed(1);
      printer.text(e.causeOfDeath, new Font(" Monospaced", Font.PLAIN, 20), true);

      if(e.regionOfOrigin.trim().equals("unknown"))
        printer.text("Born in unknown location", new Font(" Monospaced", Font.PLAIN, 20), true);
      else
        printer.text("Born in " + e.regionOfOrigin, new Font(" Monospaced", Font.PLAIN, 20), true);

      printer.text(e.nameGenderAge, new Font(" Monospaced", Font.PLAIN, 20), true);
      printer.text(e.number + " dead", new Font(" Monospaced", Font.PLAIN, 20), true);
      printer.text(e.foundDead, new Font(" Monospaced", Font.BOLD, 20), true);
      
      printer.lineFeed(3);

      try {
        Thread.sleep(10000);
      } catch (InterruptedException ex) {
      }
    }

//    while(true)
//    {
//      try {
//        Thread.sleep(1);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//


//      if(button.isLow())
//      {
//        print(porn, printer,50);
//      }




  }

}

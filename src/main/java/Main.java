import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.awt.*;
import java.io.*;
import java.util.HashSet;
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

  public static void main(String args[]) {


    Printer printer = new Printer();

    while(true) {
      try {
        printer.open(findPrinter());
        break;
      } catch (RuntimeException e) {
        System.out.println("Printer not found, waiting");
        sleep();
      }
    }
    System.out.println("Found printer");

    Porn porn = null;
    while(true) {
      try {
        porn = new Porn();
        break;
      } catch (Exception e) {
        System.out.println("No internet, waiting...");
        sleep();
      }
    }
    System.out.println("Got internet");

    final GpioController gpio = GpioFactory.getInstance();
    GpioPinDigitalInput button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_23, "MyButton", PinPullResistance.PULL_UP); // PIN RESISTANCE (optional)
    Porn finalPorn = porn;
    button.addListener(new GpioPinListenerDigital() {
      @Override
      public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent ev) {
        if(ev.getState() == PinState.LOW) {
          //print(finalPorn, printer, 50);
          System.out.println("printing");
          sleep();
          System.out.println("printing22222");
          sleep();
          System.out.println("printing333333");
        }
      }
    });

    //now just wait for button presses
    while(true)
    {
      sleep();
    }
  }

}

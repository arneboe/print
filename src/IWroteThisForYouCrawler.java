package src;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Grabs all entires from iwrotethisforyou and stores them in a database
 */
public class IWroteThisForYouCrawler {



public static ArrayList<IWroteThisForYouEntry> crawl() throws IOException {

  ArrayList<String> urls = new ArrayList<>();
  ArrayList<IWroteThisForYouEntry> entries = new ArrayList<>();
//  urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2008-01-01T00:00:00-08:00&max-results=50");
//  urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-11-07T03:11:00-08:00&max-results=50&start=50&by-date=false");
//  urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-10-11T05:14:00-07:00&max-results=50&start=100&by-date=false");
//  urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-09-24T10:17:00-07:00&max-results=50&start=150&by-date=false");
//  urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-09-06T02:43:00-07:00&max-results=50&start=200&by-date=false");
//  urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-07-13T06:16:00-07:00&max-results=50&start=250&by-date=false");

 // urls.add("http://www.iwrotethisforyou.me/search?q=The+Way+Saturn+Turns");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2017-02-15T06:59:00-08:00&max-results=200");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2016-04-21T06:45:00-07:00&max-results=200&start=51&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2015-07-02T08:58:00-07:00&max-results=200&start=98&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2015-03-13T01:18:00-07:00&max-results=200&start=118&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2014-12-02T06:06:00-08:00&max-results=200&start=144&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2014-06-05T07:29:00-07:00&max-results=200&start=197&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2014-02-05T08:45:00-08:00&max-results=200&start=249&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2012-11-19T07:02:00-08:00&max-results=200&start=284&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2012-06-11T01:55:00-07:00&max-results=200&start=332&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2012-02-17T03:31:00-08:00&max-results=200&start=387&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2012-01-10T23:20:00-08:00&max-results=200&start=419&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2011-12-30T23:32:00-08:00&max-results=200&start=429&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2011-08-31T00:27:00-07:00&max-results=200&start=478&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2011-05-05T03:17:00-07:00&max-results=200&start=531&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2010-10-05T15:41:00-07:00&max-results=200&start=585&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2010-06-25T13:12:00-07:00&max-results=200&start=635&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2010-04-15T00:14:00-07:00&max-results=200&start=685&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2010-01-25T23:08:00-08:00&max-results=200&start=735&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2009-10-27T03:37:00-07:00&max-results=200&start=785&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2009-08-19T00:18:00-07:00&max-results=200&start=835&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2009-06-10T03:56:00-07:00&max-results=200&start=885&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2009-04-01T00:50:00-07:00&max-results=200&start=935&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2009-01-23T03:28:00-08:00&max-results=200&start=985&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2008-11-18T08:16:00-08:00&max-results=200&start=1035&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2008-09-24T09:29:00-07:00&max-results=200&start=1085&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2008-07-22T06:58:00-07:00&max-results=200&start=1135&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2008-05-21T00:53:00-07:00&max-results=200&start=1185&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2008-03-07T05:05:00-08:00&max-results=200&start=1235&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2007-12-14T01:45:00-08:00&max-results=200&start=1285&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2007-11-07T01:38:00-08:00&max-results=200&start=1335&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2007-10-10T12:21:00-07:00&max-results=200&start=1385&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2007-09-21T01:29:00-07:00&max-results=200&start=1435&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2007-09-05T02:58:00-07:00&max-results=200&start=1485&by-date=false");
  urls.add("http://www.iwrotethisforyou.me/search?updated-max=2007-07-11T05:09:00-07:00&max-results=200&start=1535&by-date=false");

  for(String url : urls) {
    try {
      Document doc = Jsoup.connect(url).get();

      String html = doc.body().html();
      doc = Jsoup.parse(html.replaceAll("(?i)<br[^>]*>", "br2n"));


      Elements posts = doc.getElementsByClass("post-outer");

      for (int i = 0; i < posts.size(); ++i) {
        System.out.println("---------------------------");

        //posts.get(i).html();
        Elements title = posts.get(i).getElementsByClass("post-title");
        if(title.size() != 1)
        {
          throw new RuntimeException("more than 1 title");
        }
        final String titleString = title.get(0).text();
        if(titleString.isEmpty()) {
          System.out.println("IGNORE EMPTY TITLE");
          continue;
        }
        System.out.println(titleString + "\n");

        Elements body = posts.get(i).getElementsByClass("post-body");
        if(body.size() != 1)
        {
          throw new RuntimeException("more than 1 body");
        }
        String bodyString = body.get(0).text().replaceAll("br2n", "\n");
        while(bodyString.startsWith("\n ")) {
          bodyString = bodyString.substring(2);
        }
        if(bodyString.startsWith("\n"))
          bodyString = bodyString.substring(1);

        if(bodyString.isEmpty()) {
          System.out.println("IGNORE EMPTY BODY!");
          continue;
        }
        System.out.println(bodyString);

        Elements image = body.get(0).getElementsByTag("img");
        if(image.size() != 1) {
          System.out.println("IGNORE: NO OR TOO MUCH IMAGES");
          continue;

        }

        final String imageString = image.get(0).attr("src");
        System.out.println(imageString);

        IWroteThisForYouEntry entry = new IWroteThisForYouEntry();
        entry.title = titleString;
        entry.text = bodyString;
        URL img = new URL(imageString);
        BufferedImage bufferedImage = ImageIO.read(img);
        if(bufferedImage == null)
        {
          for(int j = 0; j < 10; ++j) {
            System.out.println("retry " + j);
            Thread.sleep(100);
            bufferedImage = ImageIO.read(img);
            if(bufferedImage != null) {
              break;
            }
          }
        }

        if(bufferedImage == null) {
          System.out.println("IGNORE, image is null");
          continue;
        }

        entry.image = new SerializableImage(bufferedImage);
        entries.add(entry);
      }


/*

      Elements titles = doc.select("h3");
      Elements texts = doc.select("div.post-body");
      Elements images = doc.select("img[src~=.+flickr.*]");

      if (titles.size() != texts.size()) throw new AssertionError();
      if (images.size() != titles.size()) throw new AssertionError();
      {
        for (int i = 0; i < titles.size(); ++i) {
          final String title = titles.get(i).text();
          final String text  = texts.get(i).text().replaceAll("br2n", "\n");
          final String imgUrl = images.get(i).attr("src");
          System.out.println("-------------------------------");
          System.out.println("Title: " + title);
          System.out.println("Image: " + imgUrl);
          System.out.println(text);
          IWroteThisForYouEntry entry = new IWroteThisForYouEntry();
          entry.title = title;
          entry.text = text;
          URL img = new URL("http://www.digitalphotoartistry.com/rose1.jpg");
          entry.image = new SerializableImage(ImageIO.read(img));
          entries.add(entry);
        }
      }
      */

    }
    catch(AssertionError e)
    {
      System.out.println("ASSERT");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  return entries;
}




}

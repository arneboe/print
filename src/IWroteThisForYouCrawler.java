import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
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
  urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2008-01-01T00:00:00-08:00&max-results=50");
  //urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-11-07T03:11:00-08:00&max-results=50&start=50&by-date=false");
  //urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-10-11T05:14:00-07:00&max-results=50&start=100&by-date=false");
  //urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-09-24T10:17:00-07:00&max-results=50&start=150&by-date=false");
  //urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-09-06T02:43:00-07:00&max-results=50&start=200&by-date=false");
  //urls.add("http://www.iwrotethisforyou.me/search?updated-min=2007-01-01T00:00:00-08:00&updated-max=2007-07-13T06:16:00-07:00&max-results=50&start=250&by-date=false");

  for(String url : urls) {
    try {
      Document doc = Jsoup.connect(url).get();

      String html = doc.body().html();
      doc = Jsoup.parse(html.replaceAll("(?i)<br[^>]*>", "br2n"));


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
          entry.image = new SerializeableImage(ImageIO.read(img));
          entries.add(entry);
        }
      }
    }
    catch(AssertionError e)
    {
      System.out.println("ASSERT");
    }
  }
  return entries;
}




}

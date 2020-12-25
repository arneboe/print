import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Stack;


public class Porn {

  private Stack<String> keywords = new Stack<>();

  public Porn() throws Exception {
    loadNext();
  }

  public String getNext() throws Exception {
    if(keywords.size() <= 0) {
      loadNext();
    }
    if(keywords.size() <= 0) {
      return "";
    }
    return keywords.pop();
  }

  private void loadNext() throws Exception {

    final String[] urls = {"https://www.pornmd.com/api/v1/live-search?orientation=straight&country=",
                           "https://www.pornmd.com/api/v1/live-search?orientation=tranny&country=",
                           "https://www.pornmd.com/api/v1/live-search?orientation=gay&country="};

    for(String url : urls)
    {
      final String jsonStr = readUrl(url);
      final JSONObject obj = (JSONObject) new JSONParser().parse(jsonStr);
      final JSONArray arr = (JSONArray) obj.get("live_search");
//      System.out.println(arr);
      for(int i = 0; i < arr.size(); ++i) {
        final String keyword = arr.get(i).toString();
        keywords.push(keyword);
      }
    }




  }

  private  String readUrl(String urlString) throws Exception {
    BufferedReader reader = null;
    try {
      URL url = new URL(urlString);
      reader = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuffer buffer = new StringBuffer();
      int read;
      char[] chars = new char[1024];
      while ((read = reader.read(chars)) != -1)
        buffer.append(chars, 0, read);

      return buffer.toString();
    } finally {
      if (reader != null)
        reader.close();
    }
  }
}

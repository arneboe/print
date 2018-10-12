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
    final String jsonStr = readUrl("https://www.pornmd.com/getliveterms");
    JSONArray array = (JSONArray)new JSONParser().parse(jsonStr);
    for(int i = 0; i < array.size(); ++i) {
      JSONObject obj = (JSONObject) array.get(i);
      keywords.push(obj.get("keyword").toString());
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

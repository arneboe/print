import java.io.*;
import java.util.TreeSet;

public class PornDownloader
{

    public static void download() throws Exception {
        Porn porn = new Porn();
        TreeSet<String> words = new TreeSet<>();
        final String path = "/home/arne/porn_search_terms.txt";

        //load existing words
        File f = new File(path);
        f.createNewFile(); //if not exist
/*
Scanner s = new Scanner(f);
int i = 0;
while (s.hasNextLine()) {
  words.add(s.nextLine());
  ++i;
}
s.close();

System.out.println("loaded: " + i + " " + words.size());
*/


        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            int count = 1; //start at one to avoid long sleep when lots of duplicates
            int duplicates = 0;

            while(true) {

                if(count % 10000 == 0) {
                    Thread.sleep(10000);
                    System.out.println("got: " + count);
                    out.flush();
                }

                final String word = porn.getNext();
                out.println(word);
                ++count;
            }
            //more code
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }


    public static void main(String args[]) {
        try {
            download();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
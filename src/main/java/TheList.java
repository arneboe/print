import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheList {

    public class ListEntry
    {
        public String foundDead;
        public String number;
        public String nameGenderAge;
        public String regionOfDeath;
        public String causeOfDeath;
        public String source;
    }


    final String[] headings = {"found dead", "number", "name, gender, age", "region of death", "cause of death", "source"};
    public List<ListEntry> records = new ArrayList<>();

    public TheList(final String pathToCsv)
    {
        loadCsv(pathToCsv);
    }

    private void loadCsv(final String pathToCsv)
    {

        try (BufferedReader br = new BufferedReader(new FileReader(pathToCsv))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");

                ListEntry e = new ListEntry();
                e.foundDead = values[0];
                e.number = values[1];
                e.nameGenderAge = values[2];
                e.regionOfDeath = values[3];
                e.causeOfDeath = values[4];
                e.source = values[5];

                records.add(e);

                if(values.length != headings.length)
                {
                    System.out.println("MISSMATCH: " + values.length );
                    System.out.println(line);
                    System.out.println();
                    System.out.println();
                    throw new RuntimeException("load missmatch");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

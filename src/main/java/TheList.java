import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheList {

    final String[] headings = {"found dead", "number", "name, gender, age", "region of death", "cause of death", "source"};
    List<List<String>> records = new ArrayList<>();

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
                records.add(Arrays.asList(values));

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

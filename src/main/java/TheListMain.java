import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;

public class TheListMain {

    public static List<ListEntry> load(String path) {
        try {
            return new CsvToBeanBuilder(new FileReader(path)).withType(ListEntry.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {

        List<ListEntry> entries = load("/home/arne/Downloads/MissingMigrants-Global-2020-11-07T12-03-49.csv");
        System.out.println("loaded list:");
        System.out.println("Num entires: " + entries.size());






    }

}

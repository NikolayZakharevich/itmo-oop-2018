import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        final String FILE_NAME = "config.ini";
        IniParser parser = new IniParser();
        SectionContainer sections = new SectionContainer();

        try {
            InputReader fileReader;
            fileReader = new InputReader(new File(FILE_NAME), "\t\n\r\f");
            String line;
            while (fileReader.hasNext()) {
                try {
                    line = fileReader.next();
                    if (parser.isSectionName(line)) {
                        if (parser.hasSection()) {
                            sections.insert(parser.extractSection());
                        }
                        parser.createSection(line);
                    } else {
                        parser.insertParameter(line);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File with name " + FILE_NAME + " not found");
        }
        if (parser.hasSection()) {
            sections.insert(parser.extractSection());
        }

        try {
            int intValue = sections.<Integer>getParameterValue("LEGACY_XML",
                    "ListenTcpPort", Integer.class);
            double floatingPointValue = sections.getParameterValue("NCMD",
                    "SampleRate", Double.class);
            String stringValue = sections.getParameterValue("COMMON",
                    "DiskCachePath", String.class);
            System.out.println(intValue + " " + floatingPointValue + " " + stringValue);
        } catch (ClassCastException | NoSuchElementException e) {
            e.printStackTrace();
        }

    }


}

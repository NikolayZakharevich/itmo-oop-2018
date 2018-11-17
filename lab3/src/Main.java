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
                    System.err.println("Wrong file format");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File with name " + FILE_NAME + " not found");
        }
        if (parser.hasSection()) {
            sections.insert(parser.extractSection());
        }

        try {
            int intValue = sections.getParameterIntValue("LEGACY_XML", "ListenTcpPort");
            double floatingPointValue = sections.getParameterDoubleValue("NCMD", "SampleRate");
            String stringValue = sections.getParameterStringValue("COMMON", "DiskCachePath");
            System.out.println(intValue + " " + floatingPointValue + " " + stringValue);
        } catch (ClassCastException | NoSuchElementException e) {
            e.printStackTrace();
        }

    }
}

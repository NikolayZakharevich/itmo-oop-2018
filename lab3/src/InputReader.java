import java.io.*;
import java.util.StringTokenizer;

public class InputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private String delim = " \t\n\r\f";
    private String nextString;

    public InputReader(File file) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file), 32768);
    }

    public InputReader(File file, String delim) throws FileNotFoundException {
        this(file);
        this.delim = delim;
    }

    public String next() throws RuntimeException {
        if (nextString != null) {
            String ret = nextString;
            nextString = null;
            return ret;
        }
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                String line = reader.readLine();
                while (line.isEmpty()) {
                    line = reader.readLine();
                }
                tokenizer = new StringTokenizer(line, delim);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public boolean hasNext() {
        if (nextString == null) {
            try {
                nextString = next();
            } catch (RuntimeException e) {
                return false;
            }
        }
        return true;
    }

}

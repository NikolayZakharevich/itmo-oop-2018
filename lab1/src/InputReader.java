import java.io.*;
import java.util.StringTokenizer;

public class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(File file) {
        try {
            reader = new BufferedReader(new FileReader(file), 32768);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }


}

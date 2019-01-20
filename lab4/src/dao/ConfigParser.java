package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigParser {
    public static Dao getDao(String username, String password) {
        Dao dao = null;
        File config = new File(".property");
        try {
            Scanner scanner = new Scanner(config);
            String configString = scanner.nextLine();
            configString = configString.substring(configString.indexOf('=') + 1);
            if (configString.equals("database")) {
                Authorizator authorizator = new Authorizator(username, password);
                dao = authorizator.connect();
            } else if (configString.equals("csv")) {
                dao = new CsvDao();
            }
        } catch (FileNotFoundException e) {
            // do nothing
        }
        return dao;
    }
}

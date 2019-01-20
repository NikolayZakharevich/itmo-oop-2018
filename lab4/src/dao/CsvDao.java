package dao;

import javenue.csv.Csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvDao implements Dao {
    private String shopsFilename = "shops.csv";
    private String goodsFilename = "goods.csv";
    private Csv.Writer shopsWriter, goodsWriter;
    int currentShopId;

    public CsvDao() {
       // shopsWriter = new Csv.Writer(shopsFilename).delimiter(',');
       // goodsWriter = new Csv.Writer(goodsFilename).delimiter(',');
        Csv.Reader reader = getReader(shopsFilename);
        while (reader.readLine() != null) {
            currentShopId++;
        }
    }

    private int getShopId(String name) {
        Csv.Reader reader = getReader(shopsFilename);
        List<String> row = reader.readLine();
        while (row != null) {
            if (row.get(1).equals(name)) {
                return Integer.valueOf(row.get(0));
            }
            row = reader.readLine();
        }
        reader.close();
        return 0;
    }

    private Csv.Reader getReader(String fileName) {
        Csv.Reader reader = null;
        try {
            reader = new Csv.Reader(new FileReader(fileName)).delimiter(',');
        } catch (FileNotFoundException e) {
            // do nothing
        }
        return reader;
    }

    @Override
    public boolean containsShop(String name) {
        return getShopId(name) != 0;
    }

    @Override
    public void addShop(String name) {
        currentShopId++;
        shopsWriter.value(String.valueOf(currentShopId)).value(name).newLine();
    }

    @Override
    public List<String> getShops() {
        List<String> result = new ArrayList<>();
        Csv.Reader reader = getReader(shopsFilename);
        List<String> row = reader.readLine();
        while (row != null) {
            result.add(row.get(1));
            row = reader.readLine();
        }
        reader.close();
        return result;
    }

    @Override
    public boolean containsGood(String shopName, String name) {
        int shopId = getShopId(shopName);
        Csv.Reader reader = getReader(goodsFilename);
        List<String> row = reader.readLine();
        while (row != null) {
            if (Integer.valueOf(row.get(1)) == shopId) {
                return true;
            }
            row = reader.readLine();
        }
        reader.close();
        return false;
    }

    @Override
    public List<List<String>> getAllGoods(String shopName) {
        int shopId = getShopId(shopName);
        List<List<String>> result = new ArrayList<>();
        Csv.Reader reader = getReader(goodsFilename);
        List<String> row = reader.readLine();
        while (row != null) {
            if (Integer.valueOf(row.get(1)) == shopId) {
                result.add(row);
            }
            row = reader.readLine();
        }
        reader.close();
        return result;
    }

    @Override
    public void addGood(String shopName, String name, int cost, int amount) {
        int shopId = getShopId(shopName);
        goodsWriter.value(name).value(String.valueOf(shopId)).value(String.valueOf(cost))
                .value(String.valueOf(amount)).newLine();
    }

    @Override
    public void setCost(String shopName, String goodName, int newCost) {
        int shopId = getShopId(shopName);
        Csv.Reader reader = getReader(goodsFilename);
        List<String> row = reader.readLine();
        while (row != null) {
            if (Integer.valueOf(row.get(1)) == shopId) {
                // TBD
            }
            row = reader.readLine();
        }
        reader.close();
    }

    @Override
    public void setAmount(String shopName, String goodName, int newAmount) {
        int shopId = getShopId(shopName);
        Csv.Reader reader = getReader(goodsFilename);
        List<String> row = reader.readLine();
        while (row != null) {
            if (Integer.valueOf(row.get(1)) == shopId) {
                // TBD
            }
            row = reader.readLine();
        }
        reader.close();
    }

    @Override
    public int getCost(String shopName, String goodName) {
        int shopId = getShopId(shopName);
        Csv.Reader reader = getReader(goodsFilename);
        List<String> row = reader.readLine();
        while (row != null) {
            if (Integer.valueOf(row.get(1)) == shopId) {
                return Integer.valueOf(row.get(2));
            }
            row = reader.readLine();
        }
        reader.close();
        return 0;
    }

    @Override
    public int getAmount(String shopName, String goodName) {
        int shopId = getShopId(shopName);
        Csv.Reader reader = getReader(goodsFilename);
        List<String> row = reader.readLine();
        while (row != null) {
            if (Integer.valueOf(row.get(1)) == shopId) {
                return Integer.valueOf(row.get(3));
            }
            row = reader.readLine();
        }
        reader.close();
        return 0;
    }
}

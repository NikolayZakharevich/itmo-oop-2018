package service;

import dao.Authorizator;
import dao.ConfigParser;
import dao.CsvDao;
import dao.Dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ShopCatalogService implements Service {
    private Dao dao;

    @Override
    public void connect(String username, String password) {
        dao = ConfigParser.getDao(username, password);
    }

    @Override
    public Shop addShop(String name) {
        if (dao == null) {
            throw new NoConnectionException("Unable to add shop without connection");
        } else if (!dao.containsShop(name)) {
            dao.addShop(name);
            return new Shop(name);
        } else {
            throw new DuplicateObjectException("Shop with such name has been already created");
        }
    }

    @Override
    public Good addGood(String shopName, String name, int cost, int amount) {
        if (dao == null) {
            throw new NoConnectionException("Unable to add shop without connection");
        } else if (!dao.containsGood(shopName, name)) {
            dao.addGood(shopName, name, cost, amount);
            return new Good(name, cost, amount);
        } else {
            throw new DuplicateObjectException("Good with such name has been already created");
        }
    }

    @Override
    public boolean containsShop(String name) {
        if (dao == null) {
            throw new NoConnectionException("Unable to check shop without connection");
        } else {
            return dao.containsShop(name);
        }
    }

    @Override
    public boolean containsGood(String shopName, String name) {
        if (dao == null) {
            throw new NoConnectionException("Unable to check good without connection");
        } else {
            return dao.containsGood(shopName, name);
        }
    }

    @Override
    public List<Shop> getShops() {
        if (dao == null) {
            throw new NoConnectionException("Unable to check good without connection");
        } else {
            List<Shop> result = new ArrayList<>();
            for (String shopName: dao.getShops()) {
                result.add(new Shop(shopName));
            }
            return result;
        }
    }

    @Override
    public List<Good> getAllGoods(String shopName) {
        if (dao == null) {
            throw new NoConnectionException("Unable to check goods without connection");
        } else {
            List<Good> result = new ArrayList<>();
            for (List<String> fields: dao.getAllGoods(shopName)) {
                result.add(new Good(fields.get(0), Integer.parseInt(fields.get(1)),
                        Integer.parseInt(fields.get(1))));
            }
            return result;
        }
    }

    @Override
    public void setCost(String shopName, String goodName, int newCost) {
        if (dao == null) {
            throw new NoConnectionException("Unable to set cost without connection");
        } else {
            dao.setCost(shopName, goodName, newCost);
        }
    }

    @Override
    public void setAmount(String shopName, String goodName, int newAmount) {
        if (dao == null) {
            throw new NoConnectionException("Unable to set amount without connection");
        } else {
            dao.setCost(shopName, goodName, newAmount);
        }
    }

    @Override
    public int getCost(String shopName, String goodName) {
        if (dao == null) {
            throw new NoConnectionException("Unable to get cost without connection");
        } else {
            return dao.getCost(shopName, goodName);
        }
    }

    @Override
    public int getAmount(String shopName, String goodName) {
        if (dao == null) {
            throw new NoConnectionException("Unable to get cost without connection");
        } else {
            return dao.getAmount(shopName, goodName);
        }
    }

}

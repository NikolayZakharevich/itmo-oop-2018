package dao;

import java.util.List;

public interface Dao {

    boolean containsShop(String name);
    void addShop(String name);
    List<String> getShops();

    boolean containsGood(String shopName, String name);
    List<List<String>> getAllGoods(String shopName);
    void addGood(String shopName, String name, int cost, int amount);

    void setCost(String shopName, String goodName, int newCost);
    void setAmount(String shopName, String goodName, int newAmount);

    int getCost(String shopName, String goodName);
    int getAmount(String shopName, String goodName);

}
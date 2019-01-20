package service;

import java.util.List;

public interface Service {

    void connect(String username, String password);
    Shop addShop(String name);
    Good addGood(String shopName, String name, int cost, int amount);

    boolean containsShop(String name);
    boolean containsGood(String shopName, String name);
    List<Shop> getShops();
    List<Good> getAllGoods(String shopName);

    void setCost(String shopName, String goodName, int newCost);
    void setAmount(String shopName, String goodName, int newAmount);

    int getCost(String shopName, String goodName);
    int getAmount(String shopName, String goodName);
}

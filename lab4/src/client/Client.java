package client;

import java.util.Map;

public interface Client {

    void connect(String username, String password);

    void addShop(String name);

    void addGood(String shopName, String name, int cost, int amount);

    void addGoods(String shopName, Map<String, Integer> goods);

    void changeCost(String shopName, String goodName, int newCost);

    String getLowestPriceShop(String goodName);

    int getAffordableAmount(String shopName, String goodName, int moneyAmount);

    int buyGoods(String shopName, Map<String, Integer> goodsList) throws OutOfGoodsException;

    String getLowestPriceShop(Map<String, Integer> goodsList) throws OutOfGoodsException;
}

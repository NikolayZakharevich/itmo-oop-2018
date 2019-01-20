package service;

import java.util.HashMap;
import java.util.Map;


public class Shop {
    private String name;
    Map<String, Good> goods = new HashMap<>();

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void addGood(Good good) {
        goods.put(good.getName(), good);
    }
}

package client;

import java.util.Map;

import java.util.List;

import service.Service;
import service.Shop;
import service.ShopCatalogService;

public class ShopCatalog implements Client {
    private Service catalogService;

    public ShopCatalog() {
        catalogService = new ShopCatalogService();
    }

    @Override
    public void connect(String username, String password) {
        catalogService.connect(username, password);
    }

    @Override
    public void addShop(String name) {
        catalogService.addShop(name);
    }

    @Override
    public void addGood(String shopName, String name, int cost, int amount) {
       catalogService.addGood(shopName, name, cost, amount);
    }

    @Override
    public void addGoods(String shopName, Map<String, Integer> goods) {
        for (Map.Entry<String, Integer> good: goods.entrySet()) {
            if (!catalogService.containsGood(shopName, good.getKey())) {
                catalogService.addGood(shopName, good.getKey(), 0, good.getValue());
            }
            int oldAmount = catalogService.getAmount(shopName, good.getKey());
            catalogService.setAmount(shopName, good.getKey(), oldAmount + good.getValue());
        }
    }

    @Override
    public void changeCost(String shopName, String goodName, int newCost) {
        catalogService.setCost(shopName, goodName, newCost);
    }

    @Override
    public String getLowestPriceShop(String goodName) {
        List<Shop> shopList = catalogService.getShops();
        int minCost = Integer.MAX_VALUE;
        Shop result = null;
        for (Shop shop: shopList) {
            int currentCost = catalogService.getCost(shop.getName(), goodName);
            if (currentCost < minCost) {
                minCost = currentCost;
                result = shop;
            }
        }
        return (result == null ? null : result.getName());
    }

    @Override
    public int getAffordableAmount(String shopName, String goodName, int moneyAmount) {
        int cost = catalogService.getCost(shopName, goodName);
        return moneyAmount / cost;
    }

    @Override
    public int buyGoods(String shopName, Map<String, Integer> goodsList) throws OutOfGoodsException {
        int result = 0;
        for (Map.Entry<String, Integer> good: goodsList.entrySet()) {
            int amount = catalogService.getAmount(shopName, good.getKey());
            if (amount < good.getValue()) {
                throw new OutOfGoodsException("Not enough" + good.getKey() + "in storage");
            }
            result += catalogService.getCost(shopName, good.getKey()) * good.getValue();
        }
        return result;
    }

    @Override
    public String getLowestPriceShop(Map<String, Integer> goodsList) throws OutOfGoodsException {
        List<Shop> shopList = catalogService.getShops();
        int minCost = Integer.MAX_VALUE;
        Shop result = null;
        for (Shop shop: shopList) {
            int currentCost = 0;
            boolean hasEnoughAmount = true;
            for (Map.Entry<String, Integer> good: goodsList.entrySet()) {
                int amount = catalogService.getAmount(shop.getName(), good.getKey());
                if (amount < good.getValue()) {
                    hasEnoughAmount = false;
                    break;
                }
                currentCost += catalogService.getCost(shop.getName(), good.getKey()) * good.getValue();
            }
            if (hasEnoughAmount && currentCost < minCost) {
                minCost = currentCost;
                result = shop;
            }
        }
        if (result == null) {
            throw new OutOfGoodsException();
        } else {
            return result.getName();
        }
    }
}

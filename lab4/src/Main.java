import client.Client;
import client.ShopCatalog;

public class Main {

    public static void main(String[] args) {
        Client client = new ShopCatalog();
        client.connect("sa", "Ujvth_cbvgcjy2000");
        try {
            client.addShop("Real");
            client.addGood("Real", "Toilet paper", 30, 20);
            client.addGood("Lenta", "Toilet paper", 29, 15);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Lowest price shop for toilet paper is " +
                client.getLowestPriceShop("Toilet paper"));
        System.out.println("Affordable amount of toilet paper in Real with sum = 10000 is " +
                client.getAffordableAmount("Real", "Toilet paper", 10000));
    }
}

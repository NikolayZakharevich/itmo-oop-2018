package service;

public class Good {
    final private String name;
    private int cost;
    private int amount;

    public Good(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Good(String name, int cost, int amount) {
        this.name = name;
        this.cost = cost;
        this.amount = amount;
    }

    void setCost(int cost) { this.cost = cost; }

    void setAmount(int amount) {
        this.amount = amount;
    }

    String getName() {
        return name;
    }

    int getCost() {
        return cost;
    }

    int getAmount() {
        return amount;
    }
}

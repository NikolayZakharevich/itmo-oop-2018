import java.time.LocalDate;

public class CreditAccount extends Account {

    private double amountOfMoney;
    private double limitOfNegativeAmountOfMoney;

    public CreditAccount(double amountOfMoney, Client client, double commissionProcent, double limitOfNegativeAmountOfMoney) {
        this.amountOfMoney = amountOfMoney;
        this.client = client;
        this.commissionProcent = commissionProcent;
        this.limitOfNegativeAmountOfMoney = -limitOfNegativeAmountOfMoney;
    }

    @Override
    protected double getAmountOfMoney() {
        return amountOfMoney;
    }

    @Override
    public void withdraw(double amount) {
        changeBalance(-amount);
    }

    @Override
    protected void changeBalance(double amount) {
        if (amountOfMoney < limitOfNegativeAmountOfMoney) {
            throw new CreditLimitException("Client " + client.getName() + " has reached credit limit");
        }
        if (amount < 0) {
            amountOfMoney -= amount * commissionProcent / 100;
        }
        amountOfMoney += amount;
    }

    public double getCommissionProcent() {
        return commissionProcent;
    }
}

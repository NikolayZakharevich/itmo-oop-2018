import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CurrentAccount implements Account {
    private double amountOfMoney;
    private Client client;
    private final double percent;
    private LocalDate dateOfOpening;

    public CurrentAccount(double amountOfMoney, Client client, LocalDate dateOfOpening, double percent) {
        this.amountOfMoney = amountOfMoney;
        this.client = client;
        this.dateOfOpening = dateOfOpening;
        this.percent = percent;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= this.amountOfMoney) {
            this.amountOfMoney -= amount;
        }
    }

    @Override
    public void put(double amount) {
        this.amountOfMoney += amount;
    }

    @Override
    public void transfer(Account account, double amount) {
        this.withdraw(amount);
        account.put(amount);
    }

    public double getAmountOfMoney() {
        LocalDate currentDate = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(this.dateOfOpening, currentDate);
        this.amountOfMoney *= Math.pow(1 + percent / 100, months);
        return this.amountOfMoney;

    }

    public Client getClient() {
        return client;
    }

    public double getPercent() {
        return percent;
    }

    public LocalDate getDateOfOpening() {
        return dateOfOpening;
    }
}

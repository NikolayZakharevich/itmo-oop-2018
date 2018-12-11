import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CurrentAccount extends Account {


    private double amountOfMoney;
    private final double percent;
    private LocalDate dateOfOpening;

    public CurrentAccount(double amountOfMoney, Client client, LocalDate dateOfOpening, double percent) {
        this.amountOfMoney = amountOfMoney;
        this.client = client;
        this.dateOfOpening = dateOfOpening;
        this.percent = percent;
    }


    public double getAmountOfMoney() {
        LocalDate currentDate = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(this.dateOfOpening, currentDate);
        this.amountOfMoney *= Math.pow(1 + percent / 100, months);
        return this.amountOfMoney;

    }

    @Override
    protected void setAmountOfMoney(double amount) {
        this.amountOfMoney = amount;
    }

    public double getPercent() {
        return percent;
    }

    public LocalDate getDateOfOpening() {
        return dateOfOpening;
    }
}

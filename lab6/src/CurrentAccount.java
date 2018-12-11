import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CurrentAccount extends Account {

    private double amountOfMoney;
    private final double percent;
    private LocalDate dateOfLastUpdate;

    public CurrentAccount(double initialAmount, Client client, LocalDate dateOfOpening, double percent) {
        this.amountOfMoney = initialAmount;
        this.client = client;
        this.percent = percent;
        this.dateOfLastUpdate = dateOfOpening;
    }

    @Override
    protected double getAmountOfMoney() {
        LocalDate currentDate = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(this.dateOfLastUpdate, currentDate);
        amountOfMoney *= Math.pow(1 + percent / (12 * 100), months);
        dateOfLastUpdate = currentDate;
        return amountOfMoney;
    }

    @Override
    protected void changeBalance(double amount) {
        amountOfMoney += amount;
    }


    public double getPercent() {
        return percent;
    }

    public double getCommissionProcent() {
        return commissionProcent;
    }
}

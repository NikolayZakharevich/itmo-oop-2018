public class Decorator extends Account {
    private Account component;
    private double maxAmountOfMoney;

    public Decorator(Account component, double maxAmountOfMoney) {
        this.component = component;
        this.maxAmountOfMoney = maxAmountOfMoney;

    }

    @Override
    protected double getAmountOfMoney() {
        return component.getAmountOfMoney();
    }

    @Override
    protected void changeBalance(double amount) {
        component.changeBalance(amount);
    }

    @Override
    public void withdraw(double amount) throws IllegalAccountOperationException {
        if (component.getClient().getPassportNummber() == null || component.getClient().getAddress() == null) {
            if (amount <= this.maxAmountOfMoney) {
                component.withdraw(amount);
            } else {
                throw new IllegalAccountOperationException("Недопустимая операция снятия для сомнительного клиента");
            }
        }
    }

    @Override
    public void put(double amount) {
        component.put(amount);

    }

    @Override
    public void transfer(AccountInterface account, double amount) throws IllegalAccountOperationException, IllegalAccountAccessException {
        if (component.getClient().getPassportNummber() == null || component.getClient().getAddress() == null) {
            if (amount <= this.maxAmountOfMoney) {
                component.transfer(account, amount);
            } else {
                throw new IllegalAccountOperationException("Недопустимая операция снятия для сомнительного клиента");
            }
        }

    }
}

abstract class Account implements AccountInterface {

    abstract protected double getAmountOfMoney();
    abstract protected void setAmountOfMoney(double amount);

    @Override
    public void withdraw(double amount) {
        if (amount <= getAmountOfMoney()) {
            setAmountOfMoney(getAmountOfMoney() - amount);
        }
    }

    @Override
    public void put(double amount) {
        setAmountOfMoney(getAmountOfMoney() + amount);
    }

    @Override
    public void transfer(AccountInterface account, double amount) {
        withdraw(amount);
        account.put(amount);
    }
}

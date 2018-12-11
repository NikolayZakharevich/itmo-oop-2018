abstract class Account implements AccountInterface {

    abstract protected double getAmountOfMoney();
    abstract protected void changeBalance(double amount);
    protected Client client;

    @Override
    public void withdraw(double amount) {
        if (amount <= getAmountOfMoney()) {
            changeBalance(-amount);
        }
    }

    @Override
    public void put(double amount) {
        changeBalance(amount);
    }

    @Override
    public void transfer(AccountInterface account, double amount) {
        withdraw(amount);
        account.put(amount);
    }

    @Override
    public Client getClient() {
        return client;
    }
}

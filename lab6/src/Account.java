abstract class Account implements AccountInterface {

    abstract protected double getAmountOfMoney();
    abstract protected void changeBalance(double amount);
    protected Client client;
    protected double commissionProcent;
    protected Account nextAccount;

    public Account setNext(Account account) {
        nextAccount = account;
        return account;
    }

    @Override
    public void withdraw(double amount) throws IllegalAccountOperationException {
        if (amount <= getAmountOfMoney()) {
            changeBalance(-amount);
        }
    }

    @Override
    public void put(double amount) {
        changeBalance(amount);
    }

    @Override
    public void transfer(AccountInterface account, double amount) throws IllegalAccountAccessException, IllegalAccountOperationException {
        if (account.getClient().equals(this.getClient()) && !account.equals(this)) {
            withdraw(amount);
            account.put(amount);
        } else {
            throw new IllegalAccountAccessException("Перевод возможен только между разными счетами одного клиента");
        }
    }

    @Override
    public Client getClient() {
        return client;
    }

    public void printMoneyFromAllAccounts() {
        System.out.println(this.getAmountOfMoney());
        if (this.nextAccount != null) {
            this.nextAccount.printMoneyFromAllAccounts();
        }
    }


}

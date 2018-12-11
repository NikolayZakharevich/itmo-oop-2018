public interface AccountInterface {

    void withdraw(double amount);

    void put(double amount);

    void transfer(AccountInterface account, double amount);
}

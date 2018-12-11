public interface Account {
    void withdraw(double amount);

    void put(double amount);

    void transfer(Account account, double amount);
}

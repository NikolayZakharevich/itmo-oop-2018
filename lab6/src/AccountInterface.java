public interface AccountInterface {

    void withdraw(double amount) throws IllegalAccountOperationException;

    void put(double amount);

    void transfer(AccountInterface account, double amount) throws IllegalAccountAccessException, IllegalAccountOperationException;

    Client getClient();
}

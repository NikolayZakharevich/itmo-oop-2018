import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Client vadimKorepanov = Client.builder("Vadim", "Korepanov")
                .withAddress("Vyazemskiy 5/7")
                .withPassportNumber("14 88 228322")
                .build();
        System.out.println(vadimKorepanov.getAddress() + " " + vadimKorepanov.getPassportNummber());

        Account account = new CurrentAccount(100, vadimKorepanov,
                LocalDate.of(2014, 1, 1), 11);

        Account newAccount = new CurrentAccount(200, vadimKorepanov,
                LocalDate.of(2018, 12, 11), 12);

        System.out.println(account.getAmountOfMoney());
        account.transfer(newAccount, 100);
        System.out.println(account.getAmountOfMoney());
        account.put(10);
        System.out.println(account.getAmountOfMoney());
        account.withdraw(20);
        System.out.println(account.getAmountOfMoney());

        System.out.println("old account - " + ((CurrentAccount) account).getAmountOfMoney());
        System.out.println("new account - " + ((CurrentAccount) newAccount).getAmountOfMoney());

        Client nikolayZakharevich = Client.builder("Nikolay", "Zakharevich")
                .build();

        Account account1 = new Deposit(100000, nikolayZakharevich,
                LocalDate.of(2018, 10, 1), 12);

        Decorator decoratedAccount = new Decorator(account1, 10000);

        decoratedAccount.withdraw(11000);
        decoratedAccount.withdraw(5000);
        System.out.println(decoratedAccount.getAmountOfMoney());

        Account account2 = new CreditAccount(100, nikolayZakharevich,
                13, 1000);
        System.out.println(account2.getAmountOfMoney());
        account2.withdraw(1500);
        System.out.println(account2.getAmountOfMoney());
        account2.withdraw(1000);
        System.out.println(account2.getAmountOfMoney());
        account1.transfer(account1, 1000);


    }
}

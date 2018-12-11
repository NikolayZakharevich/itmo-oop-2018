import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Client vadimKorepanov = Client.builder("Vadim", "Korepanov")
                .withAddress("Vyazemskiy 5/7")
                .withPassportNumber("14 88 228322")
                .build();
        System.out.println(vadimKorepanov.getAddress() + " " + vadimKorepanov.getPassportNummber());

        Account account = new CurrentAccount(100, vadimKorepanov,
                LocalDate.of(2014, 1, 1), 1.1);
        System.out.println(((CurrentAccount) account).getAmountOfMoney());
        System.out.println(((CurrentAccount) account).getClient().getName());

        Account newAccount = new CurrentAccount(100, vadimKorepanov,
                LocalDate.of(2014, 1, 1), 1.1);

        Client nikolayZakharevich = Client.builder("Nikolay", "Zakharevich")
                .build();

        Account account1 = new CurrentAccount(100000, nikolayZakharevich,
                LocalDate.of(2018, 10, 1), 1.1);

        Decorator decoratedAccount = new Decorator(account1, 10000);

        decoratedAccount.withdraw(11000);
        decoratedAccount.withdraw(5000);
        System.out.println(decoratedAccount.getAmountOfMoney());


    }
}

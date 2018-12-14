import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Client vadimKorepanov = Client.builder("Vadim", "Korepanov")
                .withAddress("Vyazemskiy 5/7")
                .withPassportNumber("14 88 228322")
                .build();
        System.out.println(vadimKorepanov.getAddress() + " " + vadimKorepanov.getPassportNummber());

        Account currentAccount = new CurrentAccount(100, vadimKorepanov,
                LocalDate.of(2014, 1, 1), 11);

        Account newAccount = new CurrentAccount(200, vadimKorepanov,
                LocalDate.of(2018, 12, 11), 12);

//        System.out.println(currentAccount.getAmountOfMoney());
//        currentAccount.transfer(newAccount, 100);
//        System.out.println(currentAccount.getAmountOfMoney());
//        currentAccount.put(10);
//        System.out.println(currentAccount.getAmountOfMoney());
//        currentAccount.withdraw(20);
//        System.out.println(currentAccount.getAmountOfMoney());

        System.out.println("old currentAccount - " + ((CurrentAccount) currentAccount).getAmountOfMoney());
        System.out.println("new currentAccount - " + ((CurrentAccount) newAccount).getAmountOfMoney());

        Client nikolayZakharevich = Client.builder("Nikolay", "Zakharevich")
                .build();

        Account depositAccount = new Deposit(100000, nikolayZakharevich,
                LocalDate.of(2018, 10, 1), 12);

        Decorator decoratedAccount = new Decorator(depositAccount, 10000);

        //decoratedAccount.withdraw(11000);
        decoratedAccount.withdraw(5000);
        System.out.println(decoratedAccount.getAmountOfMoney());

        Account creditAccount = new CreditAccount(100, nikolayZakharevich,
                13, 1000);
        System.out.println(creditAccount.getAmountOfMoney());
        creditAccount.withdraw(1500);
        System.out.println(creditAccount.getAmountOfMoney());
        //creditAccount.withdraw(1000);
        System.out.println(creditAccount.getAmountOfMoney());
        //depositAccount.transfer(depositAccount, 1000);


        depositAccount = currentAccount.setNext(depositAccount);
        creditAccount = depositAccount.setNext(creditAccount);

        currentAccount.printMoneyFromAllAccounts();



    }
}

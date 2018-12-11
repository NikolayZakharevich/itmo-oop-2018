import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Client vadimKorepanov = Client.builder("Vadim", "Korepanov")
                .withAddress("Vyazemskiy 5/7")
                .withPassportNumber("14 88 228322").build();
        System.out.println(vadimKorepanov.getAddress() + " " + vadimKorepanov.getPassportNummber());

        Account account = new CurrentAccount(100, vadimKorepanov,
                LocalDate.of(2014, 1, 1), 1.1);
        System.out.println(((CurrentAccount) account).getAmountOfMoney());
        System.out.println(((CurrentAccount) account).getClient().getName());

        Account newAccount = new CurrentAccount(100, vadimKorepanov,
                LocalDate.of(2014, 1, 1), 1.1);

        Client nikolayZakharevich = Client.builder("Nikolay", "Zakharevich")
                .withAddress("Vyazemskiy 5/7")
                .withPassportNumber("BM 2202328").build();

    }
}

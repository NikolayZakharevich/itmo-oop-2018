public class Main {


    public static void main(String[] args) {
	    Client vadimKorepanov = Client.builder("Vadim", "Korepanov")
                .withAddress("Vyazemskiy 5/7")
                .withPassportNumber("14 88 228322").build();

	    System.out.println(vadimKorepanov.getAddress() + " " + vadimKorepanov.getPassportNummber());
    }
}

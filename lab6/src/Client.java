public class Client {

    public static class Builder {

        private String name;
        private String surname;

        private String address;
        private String passportNumber;

        Builder(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPassportNumber(String passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }

        public Client build() {
            return new Client(name, surname, address, passportNumber);
        }
    }

    private final String name;
    private final String surname;
    private final String address;
    private final String passportNummber;

    Client(String name, String surname, String address, String passportNumber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportNummber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPassportNummber() {
        return passportNummber;
    }

    public static Builder builder(String name, String surname) {
        return new Builder(name, surname);
    }

}

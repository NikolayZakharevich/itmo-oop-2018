public class Client {
<<<<<<< HEAD

    public static class Builder {

        private String name;
        private String surname;

        private String address;
        private String passportNummber;

        public Builder(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPassportNumber(String passportNumber) {
            this.passportNummber = passportNumber;
            return this;
        }

        public Client build() {
            return new Client(name, surname, address, passportNummber);
        }
    }

    private final String name;
    private final String surname;
    private final String address;
    private final String passportNummber;

    public Client(String name, String surname, String address, String passportNummber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportNummber = passportNummber;
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

=======
>>>>>>> origin/master
}

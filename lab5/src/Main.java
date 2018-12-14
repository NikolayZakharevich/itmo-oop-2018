import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Person person = new Person("Sergey", "RUS", 32);
        TestClass1 test = new TestClass1(person);
        XmlGenerator xmlGenerator = new XmlGenerator(test);

        try {
            xmlGenerator.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

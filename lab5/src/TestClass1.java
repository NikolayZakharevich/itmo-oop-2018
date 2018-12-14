@XmlObject
public class TestClass1 {

    @XmlTag(name="anotherTag")
    public final static String tag = "testString";

    @XmlTag(name="personTag")
    private Person person;

    public TestClass1(Person person) {
        this.person = person;
    }
}

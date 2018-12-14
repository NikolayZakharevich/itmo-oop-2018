import java.util.ArrayList;
import java.util.List;

abstract public class XmlNode<T> {
    protected String key;
    protected T value;
    protected List<XmlNodeAttribute> attributes = new ArrayList<>();

    public XmlNode(String key, T value) {
        this.key = key;
        this.value = value;
    }

    protected XmlNode() {
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return this.value;
    }

    public String toString() {
        return this.key + "=" + this.value.toString();
    }

    public void addAtribute(XmlNodeAttribute attr) {
        attributes.add(attr);
    }

    public List<XmlNodeAttribute> getAttributes() {
        return attributes;
    }

    public abstract void addXmlNode(XmlNode innerNode);
}

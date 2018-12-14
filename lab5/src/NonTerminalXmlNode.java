import java.util.HashSet;
import java.util.Set;

public class NonTerminalXmlNode extends XmlNode<Set<XmlNode>> {

    public NonTerminalXmlNode(String key) {
        this.key = key;
        this.value = new HashSet<>();
    }

    public NonTerminalXmlNode(String key, XmlNode value) {
        this(key);
        addXmlNode(value);
    }

    public void addXmlNode(XmlNode node) {
        value.add(node);
    }
}
public class FinalXmlNode extends XmlNode<String> {
    public FinalXmlNode(String key, String value) {
        super(key, value);
    }

    @Override
    public void addXmlNode(XmlNode innerNode) {
        return;
    }
}

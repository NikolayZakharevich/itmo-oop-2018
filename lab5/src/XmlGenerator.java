import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.util.HashSet;
import java.util.List;
import java.io.IOException;

public class XmlGenerator {
    private Document document;
    private Element root;
    private XmlNode rootNode;

    public XmlGenerator(Object object) {
        document = DocumentHelper.createDocument();
        TagSearcher tagSearcher = new TagSearcher(object);
        rootNode = tagSearcher.getRoot();
        root = nodeDfs(rootNode);
        document.add(root);
    }

    private Element nodeDfs(XmlNode xmlNode) {
        Element node = DocumentHelper.createElement(xmlNode.getKey());
        for (XmlNodeAttribute attribute: (List<XmlNodeAttribute>) xmlNode.getAttributes()) {
            node.addAttribute(attribute.getKey(), attribute.getValue());
        }
        if (!(xmlNode.getValue() instanceof String)) {
            for (XmlNode to: (HashSet<XmlNode>) xmlNode.getValue()) {
                node.add(nodeDfs(to));
            }
        } else {
            node.addText((String) xmlNode.getValue());
        }
        return node;
    }

    public void write() throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer;
        writer = new XMLWriter(System.out, format);
        writer.write(document);
    }

}

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class TagSearcher {
    private Map<String, XmlNode> allTags = new HashMap<>();
    private XmlNode root;
    private Object object;

    public TagSearcher(Object object) {
        this.object = object;
    }

    public class ClassProcessor {
        private Object object;
        private XmlNode node;

        public ClassProcessor(String name, Object object) {
            this.object = object;
            node = new NonTerminalXmlNode(name);
        }

        public XmlNode process() {
            Class clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Method[] methods = clazz.getDeclaredMethods();

            for (Field field : fields) {
                field.setAccessible(true);

                XmlTag tag = field.getAnnotation(XmlTag.class);
                XmlAttribute attribute = field.getAnnotation(XmlAttribute.class);
                if (tag != null && attribute != null) {
                    throw new IllegalMethodAnnotatedExeption("Object has two different xml annotations");
                } else if (tag == null && attribute == null) {
                    continue;
                }
                if (tag != null) {
                    try {
                        String tagName = (tag.name().isEmpty() ? field.getName() : tag.name());
                        if (field.getType().getAnnotation(XmlObject.class) != null) {
                            XmlNode newNode = new ClassProcessor(tagName, field.get(object)).process();
                            allTags.put(tagName, newNode);
                            node.addXmlNode(newNode);
                        } else {

                            XmlNode newNode = new FinalXmlNode(tagName, field.get(object).toString());
                            allTags.put(tagName, newNode);
                            node.addXmlNode(newNode);
                        }
                    } catch (IllegalAccessException e) {
                        // do nothing
                    }
                } else {
                    try {
                        addAttribute(attribute, field.getName(), field.get(object));
                    } catch (IllegalAccessException e) {
                        // do nothing
                    }
                }
            }

            for (Method method : methods) {
                method.setAccessible(true);

                XmlTag tag = method.getAnnotation(XmlTag.class);
                XmlAttribute attribute = method.getAnnotation(XmlAttribute.class);
                if (tag != null && attribute != null) {
                    throw new IllegalMethodAnnotatedExeption("Method annotation failed");
                } else if (tag == null && attribute == null) {
                    continue;
                }
                if (tag != null) {
                    try {
                        String tagName = (tag.name().isEmpty() ? method.getName() : tag.name());
                        if (tagName.startsWith("get")) {
                            tagName = tagName.substring(3).toLowerCase();
                        }
                        if (method.getReturnType().getAnnotation(XmlObject.class) != null) {
                            XmlNode newNode = new ClassProcessor(tagName, method.invoke(object)).process();
                            allTags.put(tagName, newNode);
                            node.addXmlNode(newNode);
                        } else {

                            XmlNode newNode = new FinalXmlNode(tagName, method.invoke(object).toString());
                            allTags.put(tagName, newNode);
                            node.addXmlNode(newNode);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        // do nothing
                    }
                } else {
                    try {
                        addAttribute(attribute, method.getName(), method.invoke(object));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        // do nothing
                    }
                }
            }

            return node;
        }

        private void addAttribute(XmlAttribute attribute, String name, Object value) {
            if (attribute.tag().isEmpty()) {
                root.addAtribute(new XmlNodeAttribute(name, value.toString()));
            } else {
                allTags.get(attribute.tag()).addAtribute(new XmlNodeAttribute(name,
                        value.toString()));
            }
        }
    }

    public XmlNode getRoot() {
        if (object.getClass().getAnnotation(XmlObject.class) == null) {
            throw new XmlObjectIsNotAnnotatedException("Unable to process class");
        }
        root = new ClassProcessor(object.getClass().getSimpleName().toLowerCase(), object).process();
        return root;
    }

}

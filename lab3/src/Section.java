import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Section {
    private String name;
    private Map<String, Parameter<?>> elements = new HashMap<>();

    Section(String name) {
        this.name = name;
    }

    void add(Parameter parameter) {
        elements.put(parameter.getName(), parameter);
    }

    public String getName() {
        return name;
    }

    public Parameter<?> getParameter(String parameterName) throws NoSuchElementException {
        if (!elements.containsKey(parameterName)) {
            throw new NoSuchElementException("Parameter not found");
        } else {
            return elements.get(parameterName);
        }
    }
}

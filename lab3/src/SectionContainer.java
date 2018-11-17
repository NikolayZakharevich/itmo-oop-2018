import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class SectionContainer {
    private Map<String, Section> elements = new HashMap<>();

    private Parameter<?> getSectionParameter(String sectionName, String parameterName) throws NoSuchElementException {
        if (!elements.containsKey(sectionName)) {
            throw new NoSuchElementException("Section not found");
        }
        Section section = elements.get(sectionName);
        return section.getParameter(parameterName);
    }

    public void insert(Section section) throws IllegalArgumentException {
        if (section == null) {
            throw new IllegalArgumentException("Can't insert empty section");
        }
        elements.put(section.getName(), section);
    }

    public int getParameterIntValue(String sectionName, String parameterName) throws NoSuchElementException, ClassCastException {
        Object value = getSectionParameter(sectionName, parameterName).getValue();
        try {
            return (int) value;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parameter value is not int");
        }
    }

    public double getParameterDoubleValue(String sectionName, String parameterName) throws ClassCastException {
        Object value = getSectionParameter(sectionName, parameterName).getValue();
        try {
            return (double) value;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parameter value is not double");
        }
    }

    public String getParameterStringValue(String sectionName, String parameterName) throws ClassCastException {
        Object value = getSectionParameter(sectionName, parameterName).getValue();
        try {
            return (String) value;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parameter value is not String");
        }
    }

}

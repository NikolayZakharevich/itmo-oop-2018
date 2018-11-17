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

    public <T> T getParameterValue(String sectionName, String parameterName, Class<T> tClass) throws ClassCastException {
        try {
            return tClass.cast(getSectionParameter(sectionName, parameterName).getValue());
        } catch (ClassCastException e) {
            throw new ClassCastException("Unable to cast parameter");
        }
    }

}

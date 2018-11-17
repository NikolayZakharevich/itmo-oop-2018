public class IniParser {
    private Section currentSection;

    public boolean isSectionName(String string) {
        return string.charAt(0) == '[' && string.charAt(string.length() - 1) == ']';
    }

    public void createSection(String sectionName) throws IllegalArgumentException{
        if (!isSectionName(sectionName)) {
            throw new IllegalArgumentException("Invalid name for section");
        }
        currentSection = new Section(sectionName.substring(1, sectionName.length() - 1));
    }

    public boolean hasSection() {
        return currentSection != null;
    }

    public Section extractSection() {
        Section ret = currentSection;
        currentSection = null;
        return ret;
    }


    public void insertParameter(String line) throws IllegalArgumentException {
        line = line.trim();
        int end = line.indexOf(";");
        if (end != 0) {
            String[] parts = line.substring(0, (end == -1 ? line.length() : end)).split("=");
            if (parts[0].isEmpty() || parts.length < 2) {
                throw new IllegalArgumentException("Wrong file format");
            }
            String parameterName = parts[0].trim();
            String parameterValue = parts[1].trim();
            int integerValue;
            double floatingPointValue;
            if (parameterValue.matches("-?\\d+")) {
                integerValue = Integer.parseInt(parameterValue);
                currentSection.add(new Parameter<>(parameterName, integerValue));
            } else if (parameterValue.matches("-?\\d+(\\.\\d+)?")) {
                floatingPointValue = Double.parseDouble(parameterValue);
                currentSection.add(new Parameter<>(parameterName, floatingPointValue));
            } else {
                currentSection.add(new Parameter<>(parameterName, parameterValue));
            }
        }
    }
}

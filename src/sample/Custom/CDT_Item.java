package sample.Custom;

public class CDT_Item {
    private final String name;
    private final String value;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public CDT_Item(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

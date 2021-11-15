package hw7;

import java.util.Objects;

public enum Size {
    XXS("XXS", "32"),
    XS("XS", "34"),
    S("S", "36"),
    M("M", "38"),
    L("L", "40");

    private final String description;
    private final String euroSize;

    Size (String description, String euroSize) {
        this.description = description;
        this.euroSize = euroSize;
    }

    public String getDescription() {
        if (Objects.equals(this.description, XXS.description)) {
            return "детский размер";
        }

        return "взрослый размер";
    }

    public String getEuroSize() {
        return this.euroSize;
    }
}
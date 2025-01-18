package fr.pantheonsorbonne.ufr27.miage.dto;

public enum AlertTypes {
    ADOPTED("adopted"),
    NEGLECT("dead"),
    CREATED("created");

    private final String value;

    AlertTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


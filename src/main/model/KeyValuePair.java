package model;

public class KeyValuePair {
    private String key;
    private int value;

    public KeyValuePair(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}

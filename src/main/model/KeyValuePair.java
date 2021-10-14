package model;

// represents an object with a key and a value
public class KeyValuePair {
    private String key;
    private int value;


    //Constructor
    // EFFECT: creates a KeyValuePair with key and value
    public KeyValuePair(String key, int value) {
        this.key = key;
        this.value = value;
    }

    // EFFECTS: returns value
    public int getValue() {
        return value;
    }


    // EFFECTS: return key
    public String getKey() {
        return key;
    }
}

package model;

/**
 * A constraint will receive a variable name and a value
 */
public class Constraint {
    private int value = -1;
    private char name;

    public Constraint(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }
}

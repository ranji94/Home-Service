package pl.itronics.home.domain;

public abstract class Meter {
    private String value;

    public Meter() {
    }

    public Meter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

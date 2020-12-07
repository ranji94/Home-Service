package pl.itronics.home.domain;

public class WaterMeter extends Meter {

    public WaterMeter() {
    }

    public WaterMeter(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return super.toString() + " m3";
    }
}

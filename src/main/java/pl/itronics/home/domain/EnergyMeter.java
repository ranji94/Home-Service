package pl.itronics.home.domain;

public class EnergyMeter extends Meter {

    public EnergyMeter() {
    }

    public EnergyMeter(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return super.toString() + " kWh";
    }
}

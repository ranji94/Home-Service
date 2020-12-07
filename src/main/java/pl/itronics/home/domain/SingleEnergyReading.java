package pl.itronics.home.domain;

public class SingleEnergyReading {
    private EnergyMeter energyReading;

    public SingleEnergyReading() {
    }

    public SingleEnergyReading(EnergyMeter energyReading) {
        this.energyReading = energyReading;
    }

    public EnergyMeter getEnergyReading() {
        return energyReading;
    }

    public void setEnergyReading(EnergyMeter energyReading) {
        this.energyReading = energyReading;
    }

    @Override
    public String toString() {
        return "{" +
                "energy: " + energyReading +
                '}';
    }
}

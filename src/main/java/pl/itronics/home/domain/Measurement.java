package pl.itronics.home.domain;

import pl.itronics.home.enums.MeasurementStatus;

public class Measurement {
    private Integer id;
    private SingleWaterReading kitchenReadings;
    private SingleWaterReading bathroomReadings;
    private SingleEnergyReading energyReading;
    private String date;
    private MeasurementStatus status;

    public Measurement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SingleWaterReading getKitchenReadings() {
        return kitchenReadings;
    }

    public void setKitchenReadings(SingleWaterReading kitchenReadings) {
        this.kitchenReadings = kitchenReadings;
    }

    public SingleWaterReading getBathroomReadings() {
        return bathroomReadings;
    }

    public void setBathroomReadings(SingleWaterReading bathroomReadings) {
        this.bathroomReadings = bathroomReadings;
    }

    public SingleEnergyReading getEnergyReading() {
        return energyReading;
    }

    public void setEnergyReading(SingleEnergyReading energyReading) {
        this.energyReading = energyReading;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MeasurementStatus getStatus() {
        return status;
    }

    public void setStatus(MeasurementStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id='" + id + '\'' + "\n" +
                ", kitchenReadings=" + kitchenReadings + "\n" +
                ", bathroomReadings=" + bathroomReadings + "\n" +
                ", energyReading=" + energyReading + "\n" +
                ", date=" + date + "\n" +
                '}';
    }
}

package pl.itronics.home.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.minidev.json.annotate.JsonIgnore;

public class Measurement {
    @JsonIgnore
    private Integer id;
    private SingleWaterReading kitchenReadings;
    private SingleWaterReading bathroomReadings;
    private SingleEnergyReading energyReading;
    @JsonIgnore
    private String date;

    public Measurement(SingleWaterReading kitchenReadings, SingleWaterReading bathroomReadings, SingleEnergyReading energyReading, String date) {
        this.kitchenReadings = kitchenReadings;
        this.bathroomReadings = bathroomReadings;
        this.energyReading = energyReading;
        this.date = date;
    }

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

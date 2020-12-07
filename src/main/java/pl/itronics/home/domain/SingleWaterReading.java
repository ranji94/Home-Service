package pl.itronics.home.domain;

public class SingleWaterReading {
    private WaterMeter coldWater;
    private WaterMeter hotWater;

    public SingleWaterReading() {
    }

    public SingleWaterReading(WaterMeter coldWater, WaterMeter hotWater) {
        this.coldWater = coldWater;
        this.hotWater = hotWater;
    }

    public WaterMeter getColdWater() {
        return coldWater;
    }

    public void setColdWater(WaterMeter coldWater) {
        this.coldWater = coldWater;
    }

    public WaterMeter getHotWater() {
        return hotWater;
    }

    public void setHotWater(WaterMeter hotWater) {
        this.hotWater = hotWater;
    }

    @Override
    public String toString() {
        return "{" +
                "coldWater: " + coldWater +
                ", hotWater: " + hotWater +
                '}';
    }
}

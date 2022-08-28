package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Star")
public class Star extends CelestialBody {
    private double effectiveTemperature;
    public static final double KG_IN_MSUN = 1.98892E30;
    public static final double KM_IN_RSUN = 695700;
    //tom konstruktør
    public Star(){

    }
    //konstruktør
    public Star(String name, double radius, double mass, double effectiveTemperature, String pictureUrl) {
        super(name, mass, radius, pictureUrl);
        this.effectiveTemperature = effectiveTemperature;
    }
    //ignore alle verdier som ikke har noe med hovedsktruktur av objektet å gjøre
    @JsonIgnore
    public double getMassInMsun() {
        return super.getMass() / KG_IN_MSUN;
    }

    @JsonIgnore
    public double getReadiusInRsun() {
        return getRadius() / KM_IN_RSUN;
    }

    public double getEffectiveTemperature() {
        return effectiveTemperature;
    }

    public void setEffectiveTemperature(double effectiveTemperature) {
        this.effectiveTemperature = effectiveTemperature;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return String.format("%s has a radius of %.2fkm, a mass of %.2fkg and a effective temperature of %.2fK", getName(), getRadius(), getMass(), effectiveTemperature);
    }
}

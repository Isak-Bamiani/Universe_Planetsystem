package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
//vi vil ikke definerer noe som kun en celestialbody men en spesifikk implementasjon
//derfor blir det abstract
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Star.class, name = "Star"),
        @JsonSubTypes.Type(value = NaturalSatellite.class, name = "NaturalSatellite"),
})

public abstract class CelestialBody implements Comparable<CelestialBody> {
    private String name, pictureUrl;
    private double mass, radius;
    //tom konstruktør
    public CelestialBody (){

    }
    //konstruktør

    public CelestialBody(String name, double mass, double radius, String pictureUrl) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.pictureUrl = pictureUrl;
    }

    @Override
    public int compareTo(CelestialBody annetRadius) {
        if(this.radius < annetRadius.radius){
            return 1;
        }else if(this.radius == annetRadius.radius){
            return 0;
        }else{
            return -1;
        }

    }

    //getter og setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    //toString

    @Override
    @JsonIgnore
    public String toString() {
        return String.format("%s has a mass of %.0fkg and a radius of %.0fkm", name, mass, radius);
    }
}

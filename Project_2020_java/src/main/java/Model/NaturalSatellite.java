package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo (
        use = JsonTypeInfo .Id. NAME ,
        include = JsonTypeInfo. As. PROPERTY ,
        property = "type" )
@JsonSubTypes ({
        @ JsonSubTypes.Type (value = Planet.class , name = "Planet" )
})

public abstract class NaturalSatellite extends CelestialBody {
    private double semiMajorAxis, eccentricity, orbitalPeriod;
    private CelestialBody centralCelestialBody;

    public static final double ASTRONOMICAL_UNITS_IN_KM = 149597871;

    public NaturalSatellite(){

    }

    public NaturalSatellite(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody centralCelestialBody, String pictureUrl) {
        super(name, mass, radius, pictureUrl);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.centralCelestialBody = centralCelestialBody;

    }

    @JsonIgnore
    public double orbitingVelocity(double distance) {
        // v = sqrt(G * M1 / r)
        // velocity = sqrt(Gconst * MassOfObjectOrbiting / distanceInMeters)
        double velocity = Math.sqrt(((Planet.GRAVITATIONAL_CONSTANT * centralCelestialBody.getMass()) / convertKmToMeter(distance)));
        return convertMeterToKilometer(velocity);
    }

    @JsonIgnore
    public double distanceToCentralBody(double degrees) {
        double currentDistanceInAU = ((semiMajorAxis * (1 - Math.pow(eccentricity,2))) / (1 + eccentricity * Math.cos(Math.toRadians(degrees))));
        return ASTRONOMICAL_UNITS_IN_KM * currentDistanceInAU;
    }

    @JsonIgnore
    private double degreesAtDayNumber(double day) {
        return (day / this.getOrbitalPeriod()) * 360;
    }

    @JsonIgnore
    private double convertKmToMeter(double distance) {
        return distance * 1000;
    }

    @JsonIgnore
    private double convertMeterToKilometer(double distance) {
        return distance / 1000;
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public CelestialBody getCentralCelestialBody() {
        return centralCelestialBody;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return super.toString() + String.format(" and does a full orbit around the %s in %.0f days", centralCelestialBody.getName(), orbitalPeriod);
    }
}

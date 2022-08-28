package repository;

import Model.Planet;
import Model.PlanetSystem;
import Model.Star;

import java.util.ArrayList;

public class UniverseRepository implements IUniverseRepository {
    public ArrayList<PlanetSystem> planetSystemer = new ArrayList<>();


    public UniverseRepository() {
        Star theSun = new Star("Sun", 695700, 1.98892E30, 5777, "https://upload.wikimedia.org/wikipedia/commons/c/c3/Solar_sys8.jpg");
        PlanetSystem solarSystem = new PlanetSystem("Solar System", theSun, "https://upload.wikimedia.org/wikipedia/commons/c/c3/Solar_sys8.jpg");


        Star keplerStar = new Star("keplerStar",695342, 1.9885E30, 5777, "https://upload.wikimedia.org/wikipedia/commons/6/64/Kepler11.png");
        PlanetSystem keplerSystem = new PlanetSystem("Kepler", keplerStar, "https://upload.wikimedia.org/wikipedia/commons/6/64/Kepler11.png" );

        solarSystem.addPlanet(new Planet("Mercury", 3.283E23, 2439.7, 0.387, 0.206, 88, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Transit_Of_Mercury%2C_May_9th%2C_2016.png/480px-Transit_Of_Mercury%2C_May_9th%2C_2016.png"));
        solarSystem.addPlanet(new Planet("Venus", 4.867E24, 6051.8, 0.723, 0.007, 225, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/SDO%27s_Ultra-high_Definition_View_of_2012_Venus_Transit_%28304_Angstrom_Full_Disc_02%29.jpg/480px-SDO%27s_Ultra-high_Definition_View_of_2012_Venus_Transit_%28304_Angstrom_Full_Disc_02%29.jpg"));
        solarSystem.addPlanet(new Planet("Earth", 5.972E24, 6371, 1, 0.017, 365, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/NASA_Earth_America_2002.jpg/480px-NASA_Earth_America_2002.jpg"));
        solarSystem.addPlanet(new Planet("Mars", 6.39E23, 3389.5, 1.524, 0.093, 687, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Mars_23_aug_2003_hubble.jpg/480px-Mars_23_aug_2003_hubble.jpg"));
        solarSystem.addPlanet(new Planet("Jupiter", 1.898E27, 69911, 5.20440, 0.049, 4380, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Jupiter_and_its_shrunken_Great_Red_Spot.jpg/480px-Jupiter_and_its_shrunken_Great_Red_Spot.jpg"));
        solarSystem.addPlanet(new Planet("Saturn", 5.683E26, 58232, 9.5826, 0.057, 10585, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Saturn_from_Cassini_Orbiter_-_Square_%282004-10-06%29.jpg/480px-Saturn_from_Cassini_Orbiter_-_Square_%282004-10-06%29.jpg"));
        solarSystem.addPlanet(new Planet("Uranus", 8.681E25, 25362, 19.2184, 0.046, 30660, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Uranus2.jpg/480px-Uranus2.jpg"));
        solarSystem.addPlanet(new Planet("Neptune", 1.024E26, 24622, 30.11, 0.010, 60225, theSun, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Neptune_Full_%28cropped%29.jpg/480px-Neptune_Full_%28cropped%29.jpg"));


        Planet mercury = solarSystem.getPlanet("Mercury");
        Planet venus = solarSystem.getPlanet("Venus");
        Planet earth = solarSystem.getPlanet("Earth");
        Planet mars = solarSystem.getPlanet("Mars");
        Planet jupiter = solarSystem.getPlanet("jupiter");
        Planet saturn = solarSystem.getPlanet("Saturn");
        Planet uranus = solarSystem.getPlanet("Uranus");
        Planet neptune = solarSystem.getPlanet("Neptune");

        keplerSystem.addPlanet(new Planet("11b", 2.56796E25, 12550, 1.36134E7, 0.045, 10, keplerStar, null));
        keplerSystem.addPlanet(new Planet("11c", 8.0622E25, 20068, 1.5857E7, 0.026, 13, keplerStar, null));
        keplerSystem.addPlanet(new Planet("11d", 3.64292E25, 21852, 2.3786E7, 0.004, 22, keplerStar, null));
        keplerSystem.addPlanet(new Planet("11e", 5.01648E25, 28796, 2.9021E7, 0.012, 31, keplerStar, null));
        keplerSystem.addPlanet(new Planet("11f", 1.37356E25, 16628, 3.7399E7, 0.013, 36, keplerStar, null));
        keplerSystem.addPlanet(new Planet("11g", 1.7916E27, 23317, 6.9114E7, 0.015, 118, keplerStar, null));



        planetSystemer.add(keplerSystem);
        planetSystemer.add(solarSystem);
    }

    // GetAllPlanetSystem
    public ArrayList<PlanetSystem> hentAllePlanetsystemer() {
       return planetSystemer;

    }

    // GetSpecificPlanetSYstem
    public PlanetSystem hentEtSpesifiktPlanetSystem(String name) {
        for (PlanetSystem enPlanetSystem : planetSystemer) {
            if (enPlanetSystem.getName().equals(name)) {
                return enPlanetSystem;
            }
        }
        return null;
    }

    // Get a planet
    public Planet hentEnPlanet(String psName, String pName){
        return hentEtSpesifiktPlanetSystem(psName).getPlanet(pName);
    }

    @Override
    public void readFromFile(String filename) {

    }

    @Override
    public void writeToFile(String filename) {

    }

    @Override
    public void deletePlanet(String planetSystemName, String planetName) {

    }

    @Override
    public void createPlanet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystem) {

    }

    @Override
    public PlanetSystem getPlanetSystemByStarName(String name) {
        return null;
    }


    public ArrayList<Planet> hentAllePlaneterIPlanetSystem(String systemNavn) {
        ArrayList<Planet> res = new ArrayList<>();

        PlanetSystem aktuellSystem = hentEtSpesifiktPlanetSystem(systemNavn);

        if(aktuellSystem != null)
            res = aktuellSystem.getPlanets();

        return res;
    }
}
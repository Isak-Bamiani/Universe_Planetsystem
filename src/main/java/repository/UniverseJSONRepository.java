package repository;

import Model.Planet;
import Model.PlanetSystem;
import Model.Star;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UniverseJSONRepository implements IUniverseRepository {
    private ArrayList<PlanetSystem> planetSystemer = new ArrayList<>();

    public UniverseJSONRepository() {

    }
    //kaller back readfromfile
    public UniverseJSONRepository(String filename){
        readFromFile(filename);
    }

    public void readFromFile(String name) {

        ArrayList<PlanetSystem> planetList = new ArrayList<>();

        try {
            File lese = new File(name);

            ObjectMapper objectMapper = new ObjectMapper();

            PlanetSystem[] planets = objectMapper.readValue(new File(name), PlanetSystem[].class);

            planetList = new ArrayList<PlanetSystem>(Arrays.asList(planets));
            planetSystemer = planetList;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void writeToFile(String fileName){
        try {
           // File file = new File(fileName);
            //ObjectMapper objectMapper = new ObjectMapper();

           // objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, planetSystemer);

            // opretter ny tråd

            JsonWriterThread writer = new JsonWriterThread(planetSystemer, fileName);
            Thread jsonWriterThread = new Thread(writer);
            //kjøring av koden vi skrev i run() metoden
            jsonWriterThread.start();
            //den sette tråd på vent til tråden som blir kalt er ferdig
            jsonWriterThread.join();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlanet(String planetSystemName, String planetName) {

        PlanetSystem targetSystem = null;

        for (PlanetSystem ps : planetSystemer){
            if (ps.getName().equals(planetSystemName)){
                targetSystem = ps;
                break;
            }
        }

        if (targetSystem != null){
            ArrayList<Planet> planets = targetSystem.getPlanets();

            Planet targetPlanet = null;

            for (Planet p : planets){
                if (p.getName().equals(planetName)){
                    targetPlanet = p;
                    break;
                }
            }

            int idx = planets.indexOf(targetPlanet);

            planets.remove(idx);

            int i =0 ;
        }
    }

    @Override
    public void createPlanet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystemName) {
        PlanetSystem targetSystem = null;

        for (PlanetSystem ps : planetSystemer){
            if (ps.getName().equals(planetSystemName)){
                targetSystem = ps;
                break;
            }
        }

        if (targetSystem != null) {
            ArrayList<Planet> planets = targetSystem.getPlanets();
            Planet targetPlanet = null;

            for (Planet p : planets){
                if (p.getName().equals(name)){
                    targetPlanet = p;
                    break;
                }
            }

            /// create if not existing CREATE
            if (targetPlanet == null) {
                Star s = targetSystem.getCenterStar();
                Planet p = new Planet(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, s, pictureUrl);
                targetSystem.addPlanet(p);
            }
            else{ ////// ELSE UPDATE
                targetPlanet.setMass(mass);
                targetPlanet.setRadius(radius);
                targetPlanet.setSemiMajorAxis(semiMajorAxis);
                targetPlanet.setEccentricity(eccentricity);
                targetPlanet.setOrbitalPeriod(orbitalPeriod);
                targetPlanet.setPictureUrl(pictureUrl);
            }
        }
    }

    @Override
    public PlanetSystem getPlanetSystemByStarName(String name) {
        PlanetSystem res = null;

        for (PlanetSystem s : planetSystemer) {
            if (s.getCenterStar().getName().equals(name)){
                res= s;
                break;
            }
        }

        return res;
    }

    public ArrayList<PlanetSystem> getPlanetSystem() { return planetSystemer;}

    public void setPlanetSystem(ArrayList<PlanetSystem> planetSystemer) {
        this.planetSystemer = planetSystemer;
    }

    // GetAllPlanetSystem
    public ArrayList<PlanetSystem> hentAllePlanetsystemer() {
        return planetSystemer;

    };

    // GetSpecificPlanetSYstem
    public PlanetSystem hentEtSpesifiktPlanetSystem(String name) {
        for (PlanetSystem enPlanetSystem : planetSystemer) {
            if (enPlanetSystem.getName().equals(name)) {
                return enPlanetSystem;
            }
        }
        return null;
    }

    // hente planet
    public Planet hentEnPlanet(String psName, String pName){
        return hentEtSpesifiktPlanetSystem(psName).getPlanet(pName);
    }

    // hente alle planeter i planetsystem med pplanetsystem navn
    public ArrayList<Planet> hentAllePlaneterIPlanetSystem(String systemNavn) {
        ArrayList<Planet> res = new ArrayList<>();

        PlanetSystem aktuellSystem = hentEtSpesifiktPlanetSystem(systemNavn);

        if(aktuellSystem != null)
            res = aktuellSystem.getPlanets();

        return res;
    }

}

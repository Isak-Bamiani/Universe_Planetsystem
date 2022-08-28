package repository;

import Model.Planet;
import Model.PlanetSystem;
import Model.Star;

import java.io.*;
import java.util.ArrayList;

public class  UniverseCSVRepository implements IUniverseRepository {

    public ArrayList<PlanetSystem> planetSystemer = new ArrayList<>();

    public UniverseCSVRepository(String filename){
        readFromFile(filename);
    }

    @Override
    public void readFromFile(String name) {

        ArrayList<PlanetSystem> systemList = new ArrayList<>();

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(name))) {

            String linje;
            PlanetSystem system = new PlanetSystem();

            while ((linje = bufretLeser.readLine()) != null) {

                String[] deler = linje.split(",");
                if (!(system.getName() == null) && !system.getName().equals(deler[0])){
                    systemList.add(system);
                    system = new PlanetSystem();
                }

                system.setName(deler[0]); // NAME
                system.setPictureUrl(deler[1]);

                Planet p = new Planet();
                p.setName(deler[7]);
                p.setMass(Double.parseDouble(deler[8]));
                p.setRadius(Double.parseDouble(deler[9]));
                p.setSemiMajorAxis(Double.parseDouble(deler[10]));
                p.setEccentricity(Double.parseDouble(deler[11]));
                p.setOrbitalPeriod(Double.parseDouble(deler[12]));
                p.setPictureUrl(deler[13]);

                system.addPlanet(p);

                Star s = new Star();
                s.setName(deler[2]);
                s.setMass(Double.parseDouble(deler[3]));
                s.setRadius(Double.parseDouble(deler[4]));
                s.setEffectiveTemperature(Double.parseDouble(deler[5]));

                system.setCenterStar(s);
            }

        } catch (FileNotFoundException fnfe) {
            // Skriver ut feilmelding om filen ikke finnes

            System.out.println(fnfe.getMessage());
        } catch (IOException ioexc) {
            // skriver ut feilmelding om det oppstår feil ved skriving til fil
            System.out.println(ioexc.getLocalizedMessage());
        }

        planetSystemer = systemList;
    }

    @Override
    public void writeToFile(String filename) {
        try (BufferedWriter bufretSkriver = new BufferedWriter(new FileWriter(filename))) {
            // Går igjennom alle planetsystemer
            for(PlanetSystem ps: planetSystemer) {

                Star st = ps.getCenterStar();

                for(Planet p : ps.getPlanets()){

                    bufretSkriver.write(ps.getName() + "," +
                            ps.getPictureUrl() + "," +
                            st.getName() + "," +
                            st.getMass() + "," +
                            st.getRadius() + "," +
                            st.getEffectiveTemperature() + "," +
                            st.getPictureUrl() + "," +
                            p.getName() + "," +
                            p.getMass() + "," +
                            p.getRadius() + "," +
                            p.getSemiMajorAxis() + "," +
                            p.getEccentricity() + "," +
                            p.getOrbitalPeriod() + "," +
                            p.getPictureUrl());
                    bufretSkriver.newLine();
                }


            }
        } catch (FileNotFoundException fnfe) {
            // Skriver ut feilmelding om filen ikke finnes
            System.out.println(fnfe.getMessage());
        } catch (IOException ioexc) {
            // skriver ut feilmelding om det oppstår feil ved skriving til fil
            System.out.println(ioexc.getLocalizedMessage());
        }

    }

    @Override
    public void deletePlanet(String planetSystemName, String planetName) {

    }

    @Override
    public void createPlanet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystem) {
        Planet p = new Planet(name, mass, radius, semiMajorAxis,eccentricity, orbitalPeriod, null, pictureUrl);
        //plane
    }

    @Override
    public PlanetSystem getPlanetSystemByStarName(String name) {

        return null;
    }

    // GetAllPlanetSystem
    @Override
    public ArrayList<PlanetSystem> hentAllePlanetsystemer() {
        return planetSystemer;

    }

    // GetSpecificPlanetSYstem
    @Override
    public PlanetSystem hentEtSpesifiktPlanetSystem(String name) {
        for (PlanetSystem enPlanetSystem : planetSystemer) {
            if (enPlanetSystem.getName().equals(name)) {
                return enPlanetSystem;
            }
        }
        return null;
    }

    // Get a planet
    @Override
    public Planet hentEnPlanet(String psName, String pName){
        return hentEtSpesifiktPlanetSystem(psName).getPlanet(pName);
    }

    // Get all planets from a planetsystem by planetsystem name
    @Override
    public ArrayList<Planet> hentAllePlaneterIPlanetSystem(String systemNavn) {
        ArrayList<Planet> res = new ArrayList<>();

        PlanetSystem aktuellSystem = hentEtSpesifiktPlanetSystem(systemNavn);

        if(aktuellSystem != null)
            res = aktuellSystem.getPlanets();

        return res;
    }
}

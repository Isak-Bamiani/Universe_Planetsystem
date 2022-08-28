package repository;

import Model.Planet;
import Model.PlanetSystem;

import java.util.ArrayList;

public interface IUniverseRepository {
    //her er metodene som er aktuelle for uthenting av data

    //henter ut alle planetsystemer i en liste,metoden kaller vi hentAllePlanetsystemer
    ArrayList<PlanetSystem> hentAllePlanetsystemer();

    //metoden henter en planetsystem ved bruk av navnet på det
    PlanetSystem hentEtSpesifiktPlanetSystem(String name);
//lese fra og skrive til JSON

    /*void leseFraFil(String name);

   void skriveFraFil(String name);*/
    ArrayList<Planet> hentAllePlaneterIPlanetSystem(String name);


    Planet hentEnPlanet(String planetId, String planetsystemId);
    //
    void readFromFile(String filename);
    //
    void writeToFile(String filename);

    //metoden for å slette planet som tar planetsysnavnet og planetnavnet som skal slettes
    void deletePlanet(String planetSystemName, String planetName);

    void createPlanet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystem);

    PlanetSystem getPlanetSystemByStarName(String name);
}

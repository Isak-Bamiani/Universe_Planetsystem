package controller;

import Model.Planet;
import Model.PlanetSystem;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import repository.IUniverseRepository;

import java.util.ArrayList;
import java.util.Comparator;
//controller inneholder metoder som skal svare på API forespørslene i Application.java. Forespørselen kommer fra front-end
public class PlanetController {
    //referance til universeRepository fordi det er den som gjør at vi kan hente data fra et eller annet sted,
    //men vi vet hvilke metoder og data som skal kalles
    //det er interfacet som vi definerer, dette gjør at vi bare har tilgang til de metodene som er i interfacet
    private IUniverseRepository universeRepository;

    //konstruktør
    public PlanetController (IUniverseRepository universeRepository) {
        this.universeRepository = universeRepository;
    }


    //metoden som tar context(web request) inn og bruker det til å returnere data til fornt-end
    public void hentAllePlaneterIPlanetSystem (Context context) {

        String planetSystemId = context.pathParam("planet-system-id");
        String sortBy = context.queryParam("sort_by");

        //henter alle planeter fra repository
        ArrayList<Planet> allePlaneter = universeRepository.hentAllePlaneterIPlanetSystem(planetSystemId);

        if (sortBy != null) {
            switch (sortBy) {
                case "name":
                    allePlaneter.sort(new Comparator<Planet>() {
                        @Override
                        public int compare(Planet o1, Planet o2) {
                            return (int) (o1.getName().compareTo(o2.getName()));

                        };
                    });
                    break;
                case "mass":
                    allePlaneter.sort(new Comparator<Planet>() {
                        @Override
                        public int compare(Planet o1, Planet o2) {

                            return (int) (o1.getMass()- o2.getMass());

                        };
                    });
                    break;
                case "radius":
                    allePlaneter.sort(new Comparator<Planet>() {
                        @Override
                        public int compare(Planet o1, Planet o2) {
                            return (int) (o1.getRadius()-o2.getRadius());
                        };

                    });
                    break;


            }
        }
        //. jason konverterer liste med planetsystemer til json format og sender det til front.end

        context.json(allePlaneter);
    }

    public void getSorted(Context context){
        String planetSystemId = context.pathParam("planet-system-id");
        PlanetSystem planetSystemer = universeRepository.hentEtSpesifiktPlanetSystem(planetSystemId);
        ArrayList<Planet> allePlaneter = planetSystemer.getPlanets();

        context.json(allePlaneter);
    }

    //metoden som tar context(web request) inn og bruker det til å returnere data til fornt-end som henter en planet
    public void hentEnPlanet (Context context) {

        String planetsystemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        Planet planet = universeRepository.hentEnPlanet(planetsystemName,planetName);
        context.json(planet);

    }
    //metoden som tar context(web request) inn og bruker det til å returnere data til fornt-end som sletter en planet
    public void deletePlanet (Context context){

        String startName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        PlanetSystem systemName = universeRepository.getPlanetSystemByStarName(startName);

        universeRepository.deletePlanet(systemName.getName(), planetName);
        context.redirect("/planet-systems/" + systemName.getName());

        //context.json(planet);
    }

    public void createPlanet (@NotNull Context context){
        String name = context.formParam("name");
        double mass = Double.parseDouble(context.formParam("mass"));
        double radius = Double.parseDouble(context.formParam("radius"));
        double semiMajorAxis = Double.parseDouble(context.formParam("semiMajorAxis"));
        double eccentricity = Double.parseDouble(context.formParam("eccentricity"));
        double orbitalPeriod = Double.parseDouble(context.formParam("orbitalPeriod"));
        String pictureUrl = context.formParam("pictureUrl");
        String planetSystemName = context.pathParam("planet-system-id");


        universeRepository.createPlanet(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl, planetSystemName);

        context.redirect("/planet-systems/" + planetSystemName);
    }

    public void updatePlanet(Context context){
        String name = context.formParam("name");
        double mass = Double.parseDouble(context.formParam("mass"));
        double radius = Double.parseDouble(context.formParam("radius"));
        double semiMajorAxis = Double.parseDouble(context.formParam("semiMajorAxis"));
        double eccentricity = Double.parseDouble(context.formParam("eccentricity"));
        double orbitalPeriod = Double.parseDouble(context.formParam("orbitalPeriod"));
        String pictureUrl = context.formParam("pictureUrl");
        String planetSystemName = context.pathParam("planet-system-id");


        universeRepository.createPlanet(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, pictureUrl, planetSystemName);

        context.redirect("/planet-systems/" + planetSystemName);
    }
}

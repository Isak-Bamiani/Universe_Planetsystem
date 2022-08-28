package controller;

import Model.PlanetSystem;
import io.javalin.http.Context;
import repository.IUniverseRepository;

import java.util.ArrayList;


public class PlanetSystemController {
    private IUniverseRepository universeRepository;

    public PlanetSystemController (IUniverseRepository universeRepository) {

        this.universeRepository = universeRepository;
    }

    public void hentAllePlanetSystemer(Context context) {
        ArrayList<PlanetSystem> planetSystemer = universeRepository.hentAllePlanetsystemer();
        context.json(planetSystemer);
    }

    public void hentEtSpesifiktPlanetSystem(Context context) {
        String planetSystemId = context.pathParam("planet-system-id");
        //trenger navnet på planetsystemet og det trenges også i universityrepository
        //key er navnet på path parameter

        //String planetSystemId = context.pathParam("planet-system-id");

        PlanetSystem planetSystemer = universeRepository.hentEtSpesifiktPlanetSystem(planetSystemId);
        context.json(planetSystemer);
    }

    public void getPlanetSystemByName(Context context) {
        String planetSystemId = context.pathParam("planet-system-name");

        PlanetSystem planetSystemer = universeRepository.getPlanetSystemByStarName(planetSystemId);
        context.json(planetSystemer);
    }
}

import controller.PlanetController;
import controller.PlanetSystemController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.vue.VueComponent;
import org.jetbrains.annotations.NotNull;
import repository.IUniverseRepository;
import repository.UniverseJSONRepository;

public class Application {
        public static void main(String[] args) {
            Javalin app = Javalin.create().start(7000);
            app.config.enableWebjars();

            //Views
            //app.get("/", new VueComponent("hello-world"));
            app.get("/", new VueComponent("planet-system-overview"));
            app.get("/planet-systems/:planet-system-id", new VueComponent("planet-system-detail"));

            //UniverseRepository universeRepository = new UniverseRepository();
            IUniverseRepository universeRepository = new UniverseJSONRepository("planets_100.json");

            // WE write the output to another file, because we don't want to lose data from the original file. OK ???
            universeRepository.writeToFile("outputJSON.json");
            //IUniverseRepository universeRepository = new UniverseCSVRepository("outputCSV.csv");
            //universeRepository.writeToFile("outputCSV.csv");
            PlanetSystemController planetSystemController = new PlanetSystemController(universeRepository);


            app.get("/api/planet-systems/:planet-system-id", new Handler() {
                @Override
                public void handle(@NotNull Context ctx) throws Exception {
                    planetSystemController.hentEtSpesifiktPlanetSystem(ctx);
                }
           });

            app.get("/api/planet-system-by-name/:planet-system-name", new Handler() {
                @Override
                public void handle(@NotNull Context ctx) throws Exception {
                    planetSystemController.getPlanetSystemByName(ctx);
                }
            });


            app.get("/api/planet-systems", new Handler() {
                @Override
                public void handle(@NotNull Context ctx) throws Exception {
                    planetSystemController.hentAllePlanetSystemer(ctx);
                }
            });

            PlanetController planetController = new PlanetController(universeRepository);


            app.get("/planet-systems/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));

            app.get("/api/planet-systems/:planet-system-id/planets", new Handler() {
                @Override
                public void handle(@NotNull Context ctx) throws Exception {
                    planetController.hentAllePlaneterIPlanetSystem(ctx);
                }
            });

            app.get("/api/planet-systems/:planet-system-id/planets/:planet-id", context-> planetController.hentEnPlanet(context));

            app.get("/api/planet-systems/:planet-system-id/planets?sort_by=:sort_by", new Handler() {
                @Override
                public void handle(@NotNull Context ctx) throws Exception {
                    planetController.hentAllePlaneterIPlanetSystem(ctx);
                }
            });

            app.get("/api/planet-system-by-name/:planet-system-id/planets/:planet-id/delete", context-> planetController.deletePlanet(context));
            app.get("/planet-systems/:planet-system-id/createplanet", new VueComponent("planet-create"));
            app.post("/api/planet-systems/:planet-system-id/createplanet", context -> planetController.createPlanet(context));
            app.get("/planet-systems/:planet-system-id/planets/:planet-id/update", new VueComponent("planet-update"));
            app.post("/api/planet-systems/:planet-system-id/planets/:planet-id/update", context -> planetController.createPlanet(context));
        }

}

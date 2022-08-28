package repository;

import Model.PlanetSystem;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
    //implementerer runnable interfcaet som er et funksjonelt interface
public class JsonWriterThread implements Runnable {
    //
    private final ArrayList<PlanetSystem> planetSystemer;
    private final String fileName;

    public JsonWriterThread (ArrayList<PlanetSystem> system, String fileName){
        this.planetSystemer = system;
        this.fileName = fileName;
    }
    // override metoden run () fra runnable interfacet. metoden inneholder logikken som skal kjøre i egen tråd.
    @Override
    public void run() {
        try {
            File file = new File(fileName);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, planetSystemer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

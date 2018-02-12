package som.umltonosql.core.middleware.datastore.store;

import com.google.gson.Gson;
import fr.inria.atlanmod.commons.log.Log;
import som.umltonosql.core.middleware.bean.Bean;

import java.io.*;
import java.util.*;

public class JsonDatastore extends Datastore {

    private static String DRILL_DRIVER = "dfs";

    // class -> set
    private Map<String, Set<Bean>> elements;

    public JsonDatastore(String path) {
        super(path);
        this.elements = new HashMap<>();
    }

    @Override
    public Bean getElement(int id, Class<? extends Bean> clazz) {
        try {
            this.importCollectionNoBrackets(path, clazz);
        } catch(IOException e) {
            throw new RuntimeException("Cannot import the Json document (" + path + ")");
        }
        Set<Bean> instances = elements.get(clazz.getName());
        for(Bean el : instances) {
            if(el.getId() == id) {
                return el;
            }
        }
        return null;
    }

    @Override
    public void commit() throws Exception {
        try {
            this.exportCollectionNoBrackets(path);
        } catch(IOException e) {
            throw new RuntimeException("Cannot commit the Json document (" + path + ")");
        }
    }

    @Override
    public String getDrillDriver() {
        return DRILL_DRIVER;
    }

    private void importCollectionNoBrackets(String path, Class<? extends Bean> type) throws IOException {
        if(elements.get(type.getName()) != null) {
            return; // already imported
        }
        Reader zipReader = new FileReader(path);
        BufferedReader reader = new BufferedReader(zipReader);
        Gson gson = new Gson();
        Set<Bean> instances = new HashSet<Bean>();
        String line;
        while((line = reader.readLine()) != null) {
            try {
                Bean element = gson.fromJson(line, type);
                instances.add(element);
            } catch(ClassCastException e) {
                Log.warn("Received a ClassCastException when searching for ");
                continue;
            }
        }
        elements.put(type.getName(), instances);
    }

    private void exportCollectionNoBrackets(String path) throws IOException {
        File newFile = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
        Gson gson = new Gson();
        for(String key : elements.keySet()) {
            for(Object o : elements.get(key)) {
                String json = gson.toJson(o);
                writer.write(json);
                writer.newLine();
            }
        }
        writer.close();
    }
}

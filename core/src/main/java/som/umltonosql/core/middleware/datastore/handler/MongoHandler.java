package som.umltonosql.core.middleware.datastore.handler;

import java.io.IOException;
import java.text.MessageFormat;

public class MongoHandler extends DatastoreHandler {

    private String mongoInstallationPath;

    private Process mongoProcess;

    private static String MONGO_COMMAND_TEMPLATE = "{0}\\mongod.exe\"";

    public MongoHandler(String mongoInstallationPath) {
        this.mongoInstallationPath = mongoInstallationPath;
    }

    /**
     * Starts the MongoDB server located in the {@link #mongoInstallationPath} folder.
     */
    @Override
    public void startDatastore() {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", MessageFormat.format
                    (MONGO_COMMAND_TEMPLATE, mongoInstallationPath));
            builder.redirectErrorStream(true);
            mongoProcess = builder.start();
        } catch (IOException e) {
            throw new RuntimeException("Cannot start the mongo server"); // should be an InitializationException
        }
    }

    /**
     * Stops the MongoDB server.
     */
    @Override
    public void stopDatastore() {
        if(mongoProcess.isAlive()) {
            mongoProcess.destroy();
        }
    }
}

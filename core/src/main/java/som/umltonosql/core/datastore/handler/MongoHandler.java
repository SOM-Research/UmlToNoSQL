package som.umltonosql.core.datastore.handler;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * A MongoDB {@link DatastoreHandler} that manages a local MongoDB database.
 * <p>
 * <b>Note:</b> this handler relies on the <i>cmd.exe</i> application, and thus only supports <b>Windows</b> operating
 * system. Support for Linux-based systems is planned in a future release.
 */
public class MongoHandler extends DatastoreHandler {

    /**
     * The MongoDB installation folder path.
     */
    private String mongoInstallationPath;

    /**
     * The {@link Process} used to start the database server.
     */
    private Process mongoProcess;

    /**
     * The start command template that is used to initialize the database server.
     */
    private static String MONGO_COMMAND_TEMPLATE = "{0}\\mongod.exe\"";

    /**
     * Constructs a new {@link MongoHandler} managing the MongoDB server at the provided {@code mongoInstallationPath}.
     *
     * @param mongoInstallationPath the path of the server's executable folder
     */
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
     * Stops the MongoDB server located in the {@link #mongoInstallationPath} folder.
     */
    @Override
    public void stopDatastore() {
        if (mongoProcess.isAlive()) {
            mongoProcess.destroy();
        }
    }
}

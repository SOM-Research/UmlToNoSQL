package som.umltonosql.core.datastore.handler;

import fr.inria.atlanmod.commons.log.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

/**
 * A Drill {@link DatastoreHandler} that manages a local Drill server.
 * <p>
 * <b>Note:</b> this handler relies on the <i>cmd.exe</i> application, and thus only supports <b>Windows</b>
 * operating system. Support for Linux-based systems is planned in a future release.
 */
public class DrillHandler extends DatastoreHandler {

    /**
     * The Drill installation folder path.
     */
    private String drillInstallationPath;

    /**
     * The {@link Process} used to start the Drill server.
     */
    private Process drillProcess;

    /**
     * The start command template that is used to initialize the database server.
     */
    private static String DRILL_COMMAND_TEMPLATE = "cd {0} && sqlline -u \"jdbc:drill:zk=local\"";

    /**
     * Constructs a new {@link DrillHandler} managing the Drill server at the provided {@code drillInstallationPath}.
     *
     * @param drillInstallationPath the path of the server's executable folder
     */
    public DrillHandler(String drillInstallationPath) {
        this.drillInstallationPath = drillInstallationPath;
    }

    /**
     * Starts the Drill server located in the {@link #drillInstallationPath} folder.
     * <p>
     * <b>Note:</b> This method relies on {@link ProcessBuilder} to start new {@code cmd} instances. However, ghost
     * conhost.exe processes are also created and not stopped when calling {{@link #stopDatastore()} ()}}, this may
     * result in error messages from Drill that are not blocking for the execution (the conhost.exe process
     * holds a running embedded instance of Drill that blocks the new connection).
     * <p>
     * TODO: This method waits for the complete Drill initialization by reading the output of the start command. A more
     * robust implementation is left for a future release.
     */
    @Override
    public void startDatastore() {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", MessageFormat.format
                    (DRILL_COMMAND_TEMPLATE, drillInstallationPath));
            builder.redirectErrorStream(true);
            drillProcess = builder.start();
            Log.info("Initializing Drill server");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(drillProcess.getInputStream()));
            String line;
            while (true) {
                line = stdInput.readLine();
                if (line != null && line.startsWith("\"") && line.endsWith("\"")) {
                    // Check the citation before the final print, that is never read in the input stream for
                    // whatever reason.
                    break;
                }
            }
            Log.info("Drill server started");
        } catch (IOException e) {
            throw new RuntimeException("Cannot start the embedded drillbit");
        }
    }

    /**
     * Stops the Drill server located in the {@link #drillInstallationPath} folder.
     */
    @Override
    public void stopDatastore() {
        if (drillProcess.isAlive()) {
            drillProcess.destroy();
        }
    }
}

package som.umltonosql.core.datastore.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

public class DrillHandler extends DatastoreHandler {

    private String drillInstallationPath;

    private Process drillProcess;

    private static String DRILL_COMMAND_TEMPLATE = "cd {0} && sqlline -u \"jdbc:drill:zk=local\"";

    // Called in generated code
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
     */
    @Override
    public void startDatastore() {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", MessageFormat.format
                    (DRILL_COMMAND_TEMPLATE, drillInstallationPath));
            builder.redirectErrorStream(true);
            drillProcess = builder.start();
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
        } catch (IOException e) {
            throw new RuntimeException("Cannot start the embedded drillbit");
        }
    }

    /**
     * Stops the Drill server.
     */
    @Override
    public void stopDatastore() {
        if(drillProcess.isAlive()) {
            drillProcess.destroy();
        }
    }
}

package eggventory.logic.commands.help;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import eggventory.logic.commands.Command;
import eggventory.ui.Ui;
import eggventory.storage.Storage;
import eggventory.model.StockList;
import eggventory.commons.enums.CommandType;

/**
 * Command object for all help command.
 */
public class HelpCommand extends Command {
    private String options;
    private static String OS = System.getProperty("os.name").toLowerCase();


    /**
     * Constructor for command: "help".
     * @param type The type of the command
     */
    public HelpCommand(CommandType type) {
        super(type);
        this.options = null;
    }

    /**
     * Constructor for command: "help xyz" where xyz are first parsed.
     * @param type The type of the command
     * @param options The command syntax for which detailed help is required
     */
    public HelpCommand(CommandType type, String options) {
        super(type);
        this.options = options;
    }

    /**
     * Execute the help prompt given to user when user request for help.
     * @param list The inventory currently managed by the system
     * @param ui Ui implementation to display help to
     * @param storage Storage object which handles saving and loading of data
     * @return The string passed to Ui for parsing.
     */
    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output = "";
        String filename;
        if (this.options == null) {
            if (OS.indexOf("win") >= 0) {
                filename = "\\src\\main\\java\\eggventory\\commands\\help\\Help.txt";
            } else {
                filename = "/src/main/java/eggventory/commands/help/Help.txt";
            }
            try {
                Path filePath = Paths.get(System.getProperty("user.dir"), filename);
                output = Files.readString(filePath); //default UTF-8 charset.
                ui.print(output);
            } catch (IOException e) {
                output = "Error in reading help.txt";
                ui.print(output);
            }
        } else {
            switch (this.options) {
            case "add":
                if (OS.indexOf("win") >= 0) {
                    filename = "\\src\\main\\java\\eggventory\\commands\\help\\Helpadd.txt";
                } else {
                    filename =   "src/main/java/eggventory/commands/help/Helpadd.txt";
                }
                try {
                    output = getStringFromFile("/help/HelpAdd.txt");
                } catch (IOException e) {
                    output = "Error in reading Helpadd.txt";
                    ui.print(output);
                }
                break;
            case "edit":
                if (OS.indexOf("win") >= 0) {
                    filename = "\\src\\main\\java\\eggventory\\commands\\help\\Helpedit.txt";
                } else {
                    filename = "src/main/java/eggventory/commands/help/Helpedit.txt";
                }
                try {
                    output = getStringFromFile("/help/HelpEdit.txt");
                } catch (IOException e) {
                    output = "Error in reading Helpedit.txt";
                    ui.print(output);
                }
                break;
            case "delete":

                if (OS.indexOf("win") >= 0) {
                    filename = "\\src\\main\\java\\eggventory\\commands\\help\\Helpdelete.txt";
                } else {
                    filename = "src/main/java/eggventory/commands/help/Helpdelete.txt";
                }
                try {
                    output = getStringFromFile("/help/HelpDelete.txt");
                } catch (IOException e) {
                    output = "Error in reading Helpdelete.txt";
                    ui.print(output);
                }
                break;
            default:
                output = "Your help command is not defined. Please enter 'help' for reference.";
                ui.print(output);
            }
            ui.print(output);
        }
        return output;
    }

    /**
     * Reads help files from the resource folder of the JAR and returns the files as
     * a String.
     * @param resourcePath Path to file in resources folder.
     * @return String format of entire file.
     * @throws IOException when file cannot be read for some unknown error.
     */
    private String getStringFromFile(String resourcePath) throws IOException {
        InputStream is = getClass().getResourceAsStream(resourcePath);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuffer sb = new StringBuffer();
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }

            sb.append(line).append("\n");
        }

        br.close();
        isr.close();
        is.close();

        return sb.toString();
    }
}
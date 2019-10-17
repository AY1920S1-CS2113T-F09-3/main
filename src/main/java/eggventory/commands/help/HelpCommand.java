package eggventory.commands;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import eggventory.Ui;
import eggventory.Storage;
import eggventory.StockList;
import eggventory.enums.CommandType;
import eggventory.commands.Command;

public class HelpCommand extends Command {
    private String options;

    public HelpCommand(CommandType type) {
        super(type);
        this.options = null;
    }

    public HelpCommand(CommandType type, String options) {
        super(type);
        this.options = options;
    }

    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output = "";
        if(this.options == null) {
            String filename = "\\src\\main\\java\\eggventory\\commands\\help\\Help.txt";
            try {
                Path filePath = Paths.get(System.getProperty("user.dir"), filename);
                output = Files.readString(filePath); //default UTF-8 charset.
            } catch (IOException e) {
                System.out.println("Error in reading help.txt");
            }
            ui.print(output);
        } else {
            switch(options) {
                case "add":
                    String filename = "\\src\\main\\java\\eggventory\\commands\\help\\HelpAdd.txt";
                    try {
                        Path filePath = Paths.get(System.getProperty("user.dir"), filename);
                        output = Files.readString(filePath); //default UTF-8 charset.
                    } catch (IOException e) {
                        System.out.println("Error in reading help.txt");
                    }
                    break;

            }
            //output = "HELP IS HERE FOR " + this.options;
            ui.print(output);
        }
        return output;
    }
}
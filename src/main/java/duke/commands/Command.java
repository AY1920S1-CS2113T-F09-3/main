package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.BadInputException;

/**
 * This is an abstract class.
 * Command objects are sent from the Parser and executed with TaskList or Ui.
 * Commands include: adding, deleting, marking as done etc.
 */

public class Command {

    public enum CommandType {
        TODO, DEADLINE, EVENT, BYE, LIST, REMINDER, SNOOZE, DONE, DELETE, FIND, BAD, VIEW
    }

    protected CommandType type;

    //Currently the default constructor is a bad command
    public Command() {
        this.type = CommandType.BAD;
    }

    public Command(CommandType type) {
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }

    /**
     * Executes the command.
     * Prints the list or saves the list and sends exit message.
     * Might need to separate into bye and list commands.
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws BadInputException {
        if (type == CommandType.LIST) {
            list.printList();
        } else if (type == CommandType.REMINDER) {
            list.printReminders();
        } else if (type == CommandType.BYE) {
            storage.save(list.getTaskList());
            ui.printExitMessage();
        }
    }
}

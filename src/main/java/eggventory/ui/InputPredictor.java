package eggventory.ui;

import eggventory.commands.CommandDictionary;
import java.util.ArrayList;

//@@author Raghav-B
public class InputPredictor {

    private CommandDictionary commandDictionary;
    // Stores all results that match the current prediction.
    private static ArrayList<String> curSearch;
    private int curSearchIndex = 0; // Used to iterate through curSearch
    public boolean isCommandFound = false;
    private String foundCommand = "";

    /**
     * Initialize current prediction for user's input String. Initializes the
     * curSearch ArrayList to store all matching results so that user can
     * iterate through the results.
     */
    public InputPredictor() {
        this.commandDictionary = new CommandDictionary();
        reset();
    }

    public void reset() {
        curSearch = new ArrayList<>();
        curSearchIndex = 0;
        isCommandFound = false;
        foundCommand = "";
    }

    /**
     * Returns rest of String that matches prediction.
     * @param query User's input so far that will be used for prediction.
     * @param direction -1 = previous result.
     *                  0 = current result.
     *                  1 = next result.
     * @return Returns requested String that matches prediction.
     */
    public String getPrediction(String query, int direction) {
        String remainString = "";

        if (isCommandFound) {
            if (query.length() > foundCommand.length()) {
                remainString = argumentPredictionHandler(query, direction);
            } else {
                System.out.println("Reset called");
                reset();
                remainString = commandPredictionHandler(query, direction);
            }
        } else {
            remainString = commandPredictionHandler(query, direction);
        }

        return remainString;
    }

    private String commandPredictionHandler(String query, int direction) {
        String returnString = "";
        curSearch = commandDictionary.searchDictCommands(query);

        // Checking if no result is found from search. If so, we return
        // a blank String, "".
        if (curSearch.isEmpty()) {
            return "";
        }

        curSearchIndex = moduloIndex(direction);
        /* Checks if the user has completed a Command entry by comparing
        the input length with the returned Command length. This works because it is already
        guaranteed that curSearch has a valid Command available because of the curSearch.empty()
        check above.
        */
        if (query.length() == curSearch.get(curSearchIndex).length()) {
            /* Once we're at this point, we will only be interested in the remaining Arguments
            corresponding to the entered Command.
            */

            // Storing query so that we can search for it to get the corresponding arguments.
            foundCommand = query;
            isCommandFound = true;
            curSearchIndex = 0;

            returnString = " " + getArgumentPrediction(query, direction);
        }
        else {
            returnString = getRemainString(query, curSearch.get(curSearchIndex));
        }

        return returnString;
    }

    private String argumentPredictionHandler(String command, int direction) {
        String returnString = "";
        String inputArgs = command.substring(foundCommand.length());

        // Check if user has not properly entered a space after typing command.
        if (inputArgs.charAt(0) != ' ') {
            reset();
            return commandPredictionHandler(command, direction);
            //return "";
        }
        inputArgs = inputArgs.substring(1); // Make String ""

        returnString = getArgumentPrediction(foundCommand, direction);
        String[] remainStringArr = returnString.split("(?<=>) (?=<)");
        String[] inputArgsArr = inputArgs.split(" ");
        StringBuilder sb = new StringBuilder();

        int argsToExclude = inputArgsArr.length;
        if (inputArgsArr[0].equals("")) { // When String is only "";
            argsToExclude = 0;
        } else {
            if (inputArgs.charAt(inputArgs.length() - 1) != ' ') {
                sb.append(" ");
            }
        }
        for (int i = argsToExclude; i < remainStringArr.length; i++) {
            sb.append(remainStringArr[i]);
            sb.append(" ");
        }
        returnString = sb.toString();

        return returnString;
    }

    private String getArgumentPrediction(String command, int direction) {
        String returnString = "";
        curSearch = commandDictionary.searchDictArguments(command);
        // curSearch shouldn't be empty right now.

        curSearchIndex = moduloIndex(direction);

        returnString = curSearch.get(curSearchIndex);
        if (returnString == null) {
            return "";
        }
        return curSearch.get(curSearchIndex);
    }

    /**
     *
     * @param direction
     * @return
     */
    private int moduloIndex(int direction) {
        if (direction == -1) {
            curSearchIndex -= 1;
            if (curSearchIndex < 0) {
                curSearchIndex = curSearch.size() - 1;
            }
        }
        else if (direction == 1) {
            curSearchIndex += 1;
            if (curSearchIndex >= curSearch.size()) {
                curSearchIndex = 0;
            }
        }

        return curSearchIndex;
    }

    private String getRemainString(String query, String match) {
        String remainString = null;
        remainString = match.substring(query.length());
        return remainString;
    }
}
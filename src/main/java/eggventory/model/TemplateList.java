package eggventory.model;

import eggventory.model.loans.Loan;

import java.util.HashMap;

//@@author Deculsion
public class TemplateList {
    private static HashMap<String, Loan[]> templates = new HashMap<>();

    /**
     * Adds a specified template.
     * @param name Name of the template.
     * @param loans Array of Loan objects to be associated with the template.
     * @return Boolean representing whether the operation was a success.
     */
    public static boolean addTemplate(String name, Loan[] loans) {
        if (templateExists(name)) {
            return false;
        }

        templates.put(name, loans);
        return true;
    }

    /**
     * Deletes a specified template.
     * @param name Name of the template.
     * @return Boolean representing whether the operation was a success.
     */
    public static boolean deleteTemplate(String name) {
        if (templateExists(name)) {
            return false;
        }

        templates.remove(name);

        return true;
    }

    /**
     * Returns all the loans of a template.
     * @param name Name of template.
     * @return An array of Loan objects of the template. Null if it doesn't exist.
     */
    public static Loan[] getTemplateLoans(String name) {
        if (!templateExists(name)) {
            return null;
        }
        return templates.get(name);
    }

    private static boolean templateExists(String name) {
        if (templates.containsKey(name)) {
            return true;
        }

        return false;
    }

    /**
     * Prints all loans of a template.
     * @param name Name of template to print.
     * @return A string of all the loans within the template.
     */
    public static String printTemplateLoans(String name) {
        if (!templateExists(name)) {
            return "The template does not exist!";
        }
        Loan[] loans = templates.get(name);
        StringBuilder sb = new StringBuilder();

        for (Loan loan: loans) {
            sb.append("StockCode: ").append(loan.getStockCode());
            sb.append(", Quantity: ").append(loan.getQuantity());
            sb.append("\n");
        }

        return sb.toString();
    }
}

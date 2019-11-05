package eggventory.model;

import eggventory.model.loans.Loan;

import java.util.HashMap;

//@@author Deculsion
public class TemplateList {
    private static HashMap<String, Loan[]> templates = new HashMap<>();

    public static boolean addTemplate(String name, Loan[] loans) {
        if (templateExists(name)) {
            return false;
        }

        templates.put(name, loans);
        return true;
    }

    public static boolean deleteTemplate(String name) {
        if (templateExists(name)) {
            return false;
        }

        templates.remove(name);

        return true;
    }

    public static Loan[] getTemplateLoans(String name) {
        return templates.get(name);
    }

    private static boolean templateExists(String name) {
        if (templates.containsKey(name)) {
            return true;
        }

        return false;
    }

    public static String printTemplateLoans(String name) {
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

package eggventory.storage;

import eggventory.commons.enums.CommandType;
import eggventory.logic.commands.add.*;
import eggventory.logic.parsers.ParseAdd;
import eggventory.model.LoanList;
import eggventory.model.PersonList;
import eggventory.model.StockList;
import eggventory.model.TemplateList;
import eggventory.model.items.StockType;
import eggventory.model.loans.Person;

public class StorageStub {

    public StockList loadStockList(String stockListSave, String stockTypeListSave) {
        StockList savedList = new StockList();
        try {
                String[] lines = stockListSave.split(System.getProperty("line.separator"));
                for(String line : lines) {
                    String[] item = line.split(",", 0);
                    AddStockCommand loadStocks = new AddStockCommand(CommandType.ADD, item[0], item[1],
                            Integer.parseInt(item[2]), item[3], Integer.parseInt(item[4]));
                    loadStocks.execute(savedList);
                }

                String[] lines2 = stockListSave.split(System.getProperty("line.seperator"));
                for(String line : lines2) {
                    AddStockTypeCommand loadStockTypes = new AddStockTypeCommand(CommandType.ADD, line);
                    loadStockTypes.execute(savedList);
                }
        } catch (Exception e) {
            System.out.println("Could not load historical savedList");
        }
        return savedList; //Returns a StockList.
    }

    public LoanList loadLoanList(String loanListSave) {
        LoanList savedLoanList = new LoanList();
        try {
            String[] lines = loanListSave.split(System.getProperty("line.separator"));
            for(String line : lines) {
                String[] item = line.split(",", 0);
                AddLoanCommand addLoans = new AddLoanCommand(CommandType.ADD, item[0], item[1],
                        Integer.parseInt(item[2]));
                addLoans.executeLoadLoanList(savedLoanList);
            }
        } catch (Exception e) {
            System.out.println("Could not load historical savedLoanList");
        }
        return savedLoanList; //Returns a LoanList.
    }


    public PersonList loadPersonList(String personListSave) {
        PersonList savedPersonList = new PersonList();
        try {
                String[] lines = personListSave.split(System.getProperty("line.separator"));
                for(String line : lines) {
                    String[] item = line.split(",", 0);
                    AddPersonCommand addPersons = new AddPersonCommand(CommandType.ADD, item[0], item[1]);
                    addPersons.executeLoadPersonList(savedPersonList);
                }
        } catch (Exception e) {
            System.out.println("Could not load historical savedPersonList");
        }
        return savedPersonList;
    }

    public TemplateList loadTemplateList(String templateListSave) {
        TemplateList savedTemplateList = new TemplateList();
        try {
            String[] lines = templateListSave.split(System.getProperty("lines.seperator"));
           for(String line : lines) {
                AddTemplateCommand addTemplate = ((AddTemplateCommand) new ParseAdd().processAddTemplate(line));
                addTemplate.executeSaveTemplateList(savedTemplateList);
            }
        } catch (Exception e) {
            System.out.println("Could not load historical savedTemplateList");
        }
        return savedTemplateList;
    }

}

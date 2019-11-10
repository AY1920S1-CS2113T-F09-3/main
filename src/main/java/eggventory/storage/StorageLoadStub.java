package eggventory.storage;

import eggventory.commons.enums.CommandType;

import eggventory.commons.exceptions.BadInputException;
import eggventory.logic.commands.add.AddLoanCommand;
import eggventory.logic.commands.add.AddPersonCommand;
import eggventory.logic.commands.add.AddStockCommand;
import eggventory.logic.commands.add.AddStockTypeCommand;
import eggventory.logic.commands.add.AddTemplateCommand;
import eggventory.logic.parsers.ParseAdd;
import eggventory.model.LoanList;
import eggventory.model.PersonList;
import eggventory.model.StockList;
import eggventory.model.TemplateList;

//@@author patwaririshab
public class StorageLoadStub {

    private void addStock(StockList savedList, String line) {
        AddStockTypeCommand loadStockTypes = new AddStockTypeCommand(CommandType.ADD, line);
        loadStockTypes.execute(savedList);
    }

    private void addStockType(StockList savedList, String line) throws BadInputException {
        String[] item = line.split(",", 0);
        AddStockCommand loadStocks = new AddStockCommand(CommandType.ADD, item[0], item[1],
                Integer.parseInt(item[2]), item[3], Integer.parseInt(item[4]));
        loadStocks.execute(savedList);
    }

    private void addLoan(LoanList savedLoanList, String line) {
        String[] item = line.split(",", 0);
        AddLoanCommand addLoans = new AddLoanCommand(CommandType.ADD, item[0], item[1],
                Integer.parseInt(item[2]));
        addLoans.executeLoadLoanList(savedLoanList);
    }

    private void addPerson(PersonList savedPersonList, String line) throws BadInputException {
        String[] item = line.split(",", 0);
        AddPersonCommand addPersons = new AddPersonCommand(CommandType.ADD, item[0], item[1]);
        addPersons.executeLoadPersonList(savedPersonList);
    }

    private void addTemplate(TemplateList savedTemplateList, String line) throws BadInputException {
        AddTemplateCommand addTemplate = ((AddTemplateCommand) new ParseAdd().processAddTemplate(line));
        addTemplate.executeSaveTemplateList(savedTemplateList);
    }

    /**
     * Loads stockList from state strings obtained from state stacks.
     *
     * @param stockListSave     string save state of stocks.
     * @param stockTypeListSave string save state of stocktypes.
     * @return StockList object.
     */
    public StockList loadStockList(String stockListSave, String stockTypeListSave) {
        StockList savedList = new StockList();
        try {

            String[] lines2 = stockTypeListSave.split(System.getProperty("line.separator"));
            for (String line : lines2) {
                addStock(savedList, line);
            }
            String[] lines = stockListSave.split(System.getProperty("line.separator"));
            for (String line : lines) {
                addStockType(savedList, line);
            }
        } catch (Exception e) {
            System.out.println("Could not load historical savedList");
        }
        return savedList; //Returns a StockList.
    }

    /**
     * Loads LoanList from state strings obtained from state stacks.
     *
     * @param loanListSave string save state of loanlist.
     * @return LoanList object.
     */
    public LoanList loadLoanList(String loanListSave) {
        LoanList savedLoanList = new LoanList();
        try {
            String[] lines = loanListSave.split(System.getProperty("line.separator"));
            for (String line : lines) {
                addLoan(savedLoanList, line);
            }
        } catch (Exception e) {
            System.out.println("Could not load historical savedLoanList");
        }
        return savedLoanList; //Returns a LoanList.
    }

    /**
     * Loads personlist from state strings obtained from state stacks.
     *
     * @param personListSave string save state of personlist.
     * @return PersonList object.
     */
    public PersonList loadPersonList(String personListSave) {
        PersonList savedPersonList = new PersonList();
        try {
            String[] lines = personListSave.split(System.getProperty("line.separator"));
            for (String line : lines) {
                addPerson(savedPersonList, line);
            }
        } catch (Exception e) {
            System.out.println("Could not load historical savedPersonList");
        }
        return savedPersonList;
    }

    /**
     * Loads templatelist from state strings obtained from state stacks.
     *
     * @param templateListSave string save state of templatelist.
     * @return TemplateList object.
     */
    public TemplateList loadTemplateList(String templateListSave) {
        TemplateList savedTemplateList = new TemplateList();
        try {
            String[] lines = templateListSave.split(System.getProperty("line.separator"));
            for (String line : lines) {
                addTemplate(savedTemplateList, line);
            }
        } catch (Exception e) {
            System.out.println("Could not load historical savedTemplateList");
        }
        return savedTemplateList;
    }

}
//@@ author

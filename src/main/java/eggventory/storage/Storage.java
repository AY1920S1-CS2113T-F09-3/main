package eggventory.storage;

import eggventory.logic.commands.add.AddLoanCommand;
import eggventory.logic.commands.add.AddStockCommand;
import eggventory.commons.enums.CommandType;
import eggventory.logic.commands.add.AddStockTypeCommand;
import eggventory.model.LoanList;
import eggventory.model.StockList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

//@@author yanprosobo
/**
 * Handles reading and writing the stockList to file.
 */
public class Storage {
    private String stockFilePath;
    private String stockTypesFilePath;
    private String loanListFilePath;

    public Storage(String stockFilePath, String stockTypesFilePath, String loanListFilePath) {
        this.stockFilePath = stockFilePath;
        this.stockTypesFilePath = stockTypesFilePath;
        this.loanListFilePath = loanListFilePath;
    }

    /**
     * Converts save file details into a StockList object.
     */
    public StockList load() {
        StockList savedList = new StockList();

        if (Files.notExists(Paths.get(stockFilePath)) || Files.notExists(Paths.get(stockTypesFilePath))
                || Files.notExists(Paths.get(loanListFilePath))) {
            try {
                Files.createDirectory(Paths.get("data/"));
            } catch (IOException e) {
                System.out.println("Unknown IO error when creating 'data/' folder.");
            }
        }
        File f = new File(stockTypesFilePath);
        File f2 = new File(stockFilePath);

        try {
            Scanner s = new Scanner(f); //Create a Scanner to read saved_stocktypes file
            Scanner s2 = new Scanner(f2); //Create a Scanner using the File as the source


            while (s.hasNext()) {
                String itemRaw = s.nextLine();
                AddStockTypeCommand loadStockTypes = new AddStockTypeCommand(CommandType.ADD, itemRaw);
                loadStockTypes.execute(savedList);
            }
            while (s2.hasNext()) {
                String itemRaw = s2.nextLine();
                String[] item = itemRaw.split(",", 0);

                AddStockCommand loadStocks = new AddStockCommand(CommandType.ADD, item[0], item[1],
                        Integer.parseInt(item[2]), item[3]);

                /*Todo: In the future, call setMinimum here to update the minimum value (item[4]) instead of defaulting.
                    Also applies for other optional params that we may add.
                */

                loadStocks.execute(savedList);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. New list will be created instead.");
        } catch (Exception e) {
            System.out.println("Save file cannot be read. Please fix it manually or use a new list.");
        }
        return savedList; //Returns a StockList.
    }

    public LoanList loadLoanList() {
        LoanList savedLoanList = new LoanList();
        File f3 = new File(loanListFilePath);
        try {
            Scanner s3 = new Scanner(f3); //Create a Scanner using LoanListFilePath as source
            while (s3.hasNext()) {
                String itemRaw = s3.nextLine();
                String[] item = itemRaw.split(",", 0);
                AddLoanCommand addLoans = new AddLoanCommand(CommandType.ADD, item[0], item[1],
                        Integer.parseInt(item[2]));
                addLoans.executeLoadLoanList(savedLoanList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. New list will be created instead.");
        } catch (Exception e) {
            System.out.println("Save file cannot be read. Please fix it manually or use a new list.");
        }
        return savedLoanList;
    }


    private void writeToFile(String textToAdd, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        pw.print(textToAdd);
        pw.flush();
        pw.close();
    }

    //@@author cyanoei
    /**
     * Saves existing StockType to a text file.
     */
    public void save(StockList stockList) {
        String stocksToSave = stockList.saveDetailsString();
        String stockTypesToSave = stockList.saveStockTypesString();

        try {
            writeToFile(stocksToSave, stockFilePath);
            writeToFile(stockTypesToSave, stockTypesFilePath);
        } catch (IOException e) {
            System.out.println("Something went wrong saving the file :(");
        }
    }

    public void save(StockList stockList, LoanList loanList) {
        String stocksToSave = stockList.saveDetailsString();
        String stockTypesToSave = stockList.saveStockTypesString();
        String loanListToSave = loanList.saveLoanListString();

        try {
            writeToFile(stocksToSave, stockFilePath);
            writeToFile(stockTypesToSave, stockTypesFilePath);
            writeToFile(loanListToSave, loanListFilePath);
        } catch (IOException e) {
            System.out.println("Something went wrong saving the file :(");
        }
    }
    //@@author
}

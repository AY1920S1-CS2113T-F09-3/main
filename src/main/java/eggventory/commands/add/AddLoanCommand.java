package eggventory.commands.add;

import eggventory.LoanList;
import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.ui.Ui;

//@@author cyanoei
public class AddLoanCommand extends Command {

    private String stockCode;
    private String matricNo;
    private int quantity;

    public AddLoanCommand(CommandType type, String stockCode, String matricNo, int quantity) {
        super(type);
        this.stockCode = stockCode;
        this.matricNo = matricNo;
        this.quantity = quantity;
    }

    public String execute(StockList list, Ui ui, Storage storage) {
        LoanList.addLoan(stockCode, matricNo, quantity);
        String output = (String.format("Nice, I have added this loan for you: \n"
                + "Stock: %s | Person: %s | Quantity: %d", stockCode, matricNo, quantity));

        ui.print(output);
        return output;
    }
}

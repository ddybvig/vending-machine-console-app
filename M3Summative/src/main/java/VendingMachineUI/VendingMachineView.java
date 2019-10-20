/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineUI;

import VendingMachineController.VendingMachineController;
import VendingMachineDTO.Change;
import VendingMachineDTO.Item;
import VendingMachineDao.VendingMachinePersistenceException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author daler
 */
public class VendingMachineView {

    private UserIO io;
    //private Change change;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void printItemList(List<Item>items) throws VendingMachinePersistenceException {
        io.print("Name | Price | Amount Left");
        for (Item i : items) {
            if (i.getQty()>0){
            io.print(i.getName() + "| $" + String.valueOf(i.getPrice()) + " | " + String.valueOf(i.getQty()));
            }
        }
    }
    
    public String getMoneyUserInput() {
        return io.readString("Type in your money (e.g. 1.25 or 0.75) to make your selection.");
    }
    
    public String getMenuSelection() {
        return io.readString("Type the name of the item you want.");
    }
    
    public void displayUserChange(Change change) {
        io.print("You receive " + change.getQuarters() + " quarters, " + change.getDimes() + " dimes, " 
                + change.getNickels() + " nickels, " + change.getPennies() + " pennies in change.");
    }
    
    public void displayExitBanner() {
        io.print("Goodbye!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("===ERROR===");
        io.print(errorMsg);
    }
    
    public String exitCommand() {
        return io.readString("||VENDING MACHINE|| Press enter to continue, or type 'exit' to exit the program.");
    }

}

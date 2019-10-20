/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineController;

import VendingMachineDTO.Change;
import VendingMachineDTO.Item;
import VendingMachineDao.VendingMachinePersistenceException;
import VendingMachineService.InsufficientFundsException;
import VendingMachineService.NoItemInventoryException;
import VendingMachineService.VendingMachineServiceLayer;
import VendingMachineUI.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author daler
 */
public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        try {
            List<Item> items = getAllItems(); //call the getAllItems method to load the items inventory list into memory
            while (keepGoing) {
                if (view.exitCommand().equalsIgnoreCase("exit")) {
                    keepGoing = false;
                } else {
                    view.printItemList(items); //display the list of items to the user
                    getMoneyAndCheckAmount(); // prompt the user to enter money and save their money into a big decimal variable
                }
            }
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage("Error loading inventory.");
        }

        exitMessage();
    }

    public void getMoneyAndCheckAmount() throws VendingMachinePersistenceException {
        boolean enoughMoney = true;

        do {
            try {
                BigDecimal userMoney = getMoney();
                String itemSelectionUserInput = getMenuSelection();
                Item itemToVend = service.getItem(itemSelectionUserInput);
                BigDecimal itemCost = itemToVend.getPrice();
                if (userMoney.compareTo(itemCost) < 0) {
                    service.checkMoney(userMoney, itemCost);
                } else {
                    enoughMoney = true;
                    Change change = new Change();
                    change.getChange(userMoney, itemToVend.getPrice());
                    view.displayUserChange(change);
                    service.updateInventory(itemSelectionUserInput);
                }
            } catch (NoItemInventoryException e) {
                view.displayErrorMessage("Item out of stock. Please select a different item.");
            } catch (InsufficientFundsException e) {
                view.displayErrorMessage("Insufficient funds. Please enter more money or choose a different item.");
                enoughMoney = false;
            } catch (NumberFormatException e) {
                view.displayErrorMessage("Error: enter a valid amount of money in 'X.XX' format. Or type 'exit' to exit.");
                enoughMoney = false;
            }
        } while (!enoughMoney);

    }

    public BigDecimal getMoney() {
        BigDecimal userMoney = new BigDecimal(view.getMoneyUserInput());
        return userMoney;
    }

    public String getMenuSelection() {
        return view.getMenuSelection();
    }

    public List getAllItems() throws VendingMachinePersistenceException {
        return service.getAllItems();
    }

    public void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}

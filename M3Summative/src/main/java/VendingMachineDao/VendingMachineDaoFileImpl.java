/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

import VendingMachineDTO.Item;
import VendingMachineService.NoItemInventoryException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author daler
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    public static final String INVENTORY = "inventory.txt";
    public static final String DELIMITER = "::";
    
    List<Item> items = new ArrayList<>();

    @Override
    public List<Item> getAllItems()  throws VendingMachinePersistenceException {
        loadInventory();
        return items;
    }

    @Override
    public Item getItem(String name) throws NoItemInventoryException {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null;
    }
    

    @Override
    public void updateInventory(String name) throws VendingMachinePersistenceException {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(name)) {
                i.setQty(i.getQty()-1);
            }
        }
        writeInventory();
    }
    
    private Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        String name = itemTokens[0];
        Item itemFromFile = new Item();
        itemFromFile.setName(itemTokens[0]);
        itemFromFile.setPrice(priceToBigDecimal(itemTokens[1]));
        itemFromFile.setQty(Integer.parseInt(itemTokens[2]));               
        return itemFromFile;
    }
    
    public BigDecimal priceToBigDecimal(String price) {
        BigDecimal priceBD = new BigDecimal(price);
        return priceBD;
    }
    
    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Could not load inventory data into memory.", e);
        }
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.add(currentItem);
        }
        scanner.close();
        
    }
    
    private String marshallItem(Item anItem) {
        String itemAsText = anItem.getName() + DELIMITER;
        itemAsText += anItem.getPrice() + DELIMITER;
        itemAsText += anItem.getQty();
        return itemAsText;
    }
    
    private void writeInventory() throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(INVENTORY));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save inventory data.", e);
        }
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
    
}

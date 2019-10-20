/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

import VendingMachineDTO.Item;
import VendingMachineService.NoItemInventoryException;
import java.util.List;

/**
 *
 * @author daler
 */
public interface VendingMachineDao {
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item getItem(String name) throws VendingMachinePersistenceException, NoItemInventoryException;
    
    void updateInventory(String name) throws VendingMachinePersistenceException;
    
}

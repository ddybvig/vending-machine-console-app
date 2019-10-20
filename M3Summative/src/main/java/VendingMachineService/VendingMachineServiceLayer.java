/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineDTO.Item;
import VendingMachineDao.VendingMachinePersistenceException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author daler
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item getItem(String name) throws 
            InsufficientFundsException,
            NoItemInventoryException,
            VendingMachinePersistenceException;
    
    void updateInventory(String name) throws VendingMachinePersistenceException;
    
    void checkMoney(BigDecimal userMoney, BigDecimal itemCost) throws InsufficientFundsException;
       
}

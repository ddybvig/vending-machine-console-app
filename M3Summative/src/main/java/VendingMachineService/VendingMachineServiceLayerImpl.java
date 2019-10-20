/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineService;

import VendingMachineDTO.Item;
import VendingMachineDao.VendingMachineAuditDao;
import VendingMachineDao.VendingMachineDao;
import VendingMachineDao.VendingMachinePersistenceException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author daler
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineAuditDao auditDao;

    VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String name) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {
        if (dao.getItem(name).getQty()<=0){
            throw new NoItemInventoryException("Item out of stock. Please choose another item.");
        }
        
        return dao.getItem(name);
    }

    @Override
    public void updateInventory(String name) throws VendingMachinePersistenceException {
        dao.updateInventory(name);
        auditDao.writeAuditEntry("1 " + name + " REMOVED.");
        
    }
    
    @Override
    public void checkMoney(BigDecimal userMoney, BigDecimal itemCost) throws InsufficientFundsException {
        if (userMoney.subtract(itemCost).doubleValue()<0){
            throw new InsufficientFundsException("Insufficient funds.");
        }
    }

}

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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author daler
 */
public class VendingMachineServiceLayerTest {

//    VendingMachineDao dao = new VendingMachineDaoFileImpl();
//    VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
    VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws VendingMachinePersistenceException {
        List<Item> items = service.getAllItems();
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase("test")) {
                i.setQty(5);
            }
        }
    }

    @AfterEach
    public void tearDown() throws VendingMachinePersistenceException {

    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        //pass through method
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {
        //List<Item>items = service.getAllItems();
        Item testItem = service.getItem("test");
        //good path:
        assertEquals("test", testItem.getName());
        //bad path:
        testItem.setQty(0);
        try {
            service.getItem("test");
        } catch (NoItemInventoryException e) {
            //expected result
        }
    }

    /**
     * Test of updateInventory method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testUpdateInventory() throws Exception {
        //pass through method?
    }

    /**
     * Test of checkMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testCheckMoney() throws Exception {
        BigDecimal testItemCost = new BigDecimal("0.55");
        //good path:
        BigDecimal testGoodUserMoney = new BigDecimal("1.00");
        try {
            service.checkMoney(testGoodUserMoney, testItemCost);
        } catch (InsufficientFundsException e) {
            fail("should not happen");
        }
        //bad path:
        BigDecimal testBadUserMoney = new BigDecimal("0.10");
        try {
            service.checkMoney(testBadUserMoney, testItemCost);
            fail("should not happen");
        } catch (InsufficientFundsException e) {
            //expected result
        }
    }

}

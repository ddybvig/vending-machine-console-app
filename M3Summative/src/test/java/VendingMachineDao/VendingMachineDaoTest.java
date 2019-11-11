/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDao;

import VendingMachineDTO.Item;
import VendingMachineService.NoItemInventoryException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author daler
 */
public class VendingMachineDaoTest {

    private VendingMachineDao dao = new VendingMachineDaoStubImpl();

    public VendingMachineDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of addItem method, of class VendingMachineDao.
     */

    @Test
    public void testGetAllItems() throws Exception {
        List<Item>items = dao.getAllItems();
        assertFalse(items.isEmpty());
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem() throws Exception {
        List<Item>items = dao.getAllItems();
        Item testItem1 = dao.getItem("test");
        Item testItem2 = dao.getItem("test");
        assertEquals(testItem1.getName(), testItem2.getName());
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testUpdateInventory() throws Exception {
        List<Item> items = dao.getAllItems();
        Item testItem = dao.getItem("test");
        int quantityCheck = testItem.getQty()-1;
        dao.updateInventory("test");
        assertEquals(quantityCheck, testItem.getQty());
    }

}

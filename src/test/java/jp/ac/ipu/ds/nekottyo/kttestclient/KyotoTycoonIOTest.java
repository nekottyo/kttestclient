/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.ipu.ds.nekottyo.kttestclient;



import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 *
 * @author nekottyo
 */
public class KyotoTycoonIOTest {

    public KyotoTycoonIOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of putData method, of class KyotoTycoonIO.
     */
    @Test
    public void testPutData() {
        System.out.println("putData");
        String key = "0422811d5c3e802d404d0a106b2ad834042000a0300200101b1084000000200400000000000000000000000000000000800502410a100102000000080000281000000000000000000000000000000000014120800005000000080009442204000000000000000000000000000000000040001882b60002012020010800080000";
        String value = "valaaaa";
        KyotoTycoonIO instance = new KyotoTycoonIO();
        instance.putData(key, value);
        key = "aaa";
        instance.putData(key, value);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class KyotoTycoonIO.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        String key = "http://dbpedia.org/resource/Ludovic_Lazarus_Zamenhof";
        KyotoTycoonIO instance = new KyotoTycoonIO();
        String expResult = "valaaaa";
        String result = instance.getData(key);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getKeylist method, of class KyotoTycoonIO.
     */
    @Test
    public void testGetKeylist() {
        System.out.println("getKeylist");
        KyotoTycoonIO instance = new KyotoTycoonIO();
        List<String> expResult = null;
        List<String> result = instance.getKeylist();
        System.out.println(Arrays.toString(result.toArray()));
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setExpiration method, of class KyotoTycoonIO.
     */
    @Test
    public void testSetExpiration() {
        System.out.println("setExpiration");
        int i = 0;
        KyotoTycoonIO instance = new KyotoTycoonIO();
        instance.setExpiration(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteData method, of class KyotoTycoonIO.
     */

    public void testDeleteData_String() {
        System.out.println("deleteData");
        String key = "valaaaa";
        KyotoTycoonIO instance = new KyotoTycoonIO();
        instance.deleteData(key);
        System.out.println(Arrays.toString(instance.getKeylist().toArray()));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteData method, of class KyotoTycoonIO.
     */

    public void testDeleteData_StringArr() {
        System.out.println("deleteData");
        String[] key = null;
        KyotoTycoonIO instance = new KyotoTycoonIO();
//        instance.deleteData(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAllData method, of class KyotoTycoonIO.
     */

    public void testDeleteAllData() {
        System.out.println("deleteAllData");
        KyotoTycoonIO instance = new KyotoTycoonIO();
        instance.deleteAllData();
        System.out.println(Arrays.toString(instance.getKeylist().toArray()));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteData method, of class KyotoTycoonIO.
     */

    public void testDeleteData_List() {
        System.out.println("deleteData");
        List<String> key = null;
        KyotoTycoonIO instance = new KyotoTycoonIO();
        instance.deleteData(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

package it.uniroma2.dicii.isw2.jcs.paramTests;

import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.engine.control.CompositeCacheManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.apache.jcs.JCS;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

/**
 * Simple methods to be run by active test suites that test removal.
 */
@RunWith(value = Parameterized.class)
public class RemovalTestUtil {
    private JCS jcs;
    private int start;
    private int end;
    private boolean check;

    public RemovalTestUtil(int start, int end, boolean check) {
        this.start = start;
        this.end = end;
        this.check = check;
    }

    @Parameters
    public static Collection<Object []> getTestParameters() {
        return Arrays.asList(new Object[][]{
                {10, 20, true},
                {178, 23, true},
                {0, 0, false},
                {1, 20, false}
        });
    }

    @Before
    public void configure() throws CacheException {
        jcs = JCS.getInstance("testCache1");
    }

    @Test
    public void runTestPutThenRemoveCategorical() throws Exception {
        for (int i = start; i <= end; i++) {
            jcs.put(i + ":key", "data" + i);
        }

        for (int i = end; i >= start; i--) {
            String res = (String) jcs.get(i + ":key");
            if (res == null) {
                Assert.assertNotNull("[" + i + ":key] should not be null", res);
            }
        }
        System.out.println("Confirmed that " + (end - start) + " items could be found");

        for (int i = start; i <= end; i++) {
            jcs.remove(i + ":");
            Assert.assertNull(jcs.get(i + ":key"));
        }
        System.out.println("Confirmed that " + (end - start) + " items were removed");

        System.out.println(jcs.getStats());

    }


    @Test
    public void runPutInRange() throws Exception {
        for (int i = start; i <= end; i++) {
            jcs.put(i + ":key", "data" + i);
        }

        for (int i = end; i >= start; i--) {
            String res = (String) jcs.get(i + ":key");
            if (res == null) {
                Assert.assertNotNull("[" + i + ":key] should not be null", res);
            }
        }

    }

    @Test
    public void runGetInRange() throws Exception {
        for (int i = end; i >= start; i--) {
            String res = (String) jcs.get(i + ":key");
            if (check && res == null) {
                Assert.assertNotNull("[" + i + ":key] should not be null", res);
            }

        }

    }
}
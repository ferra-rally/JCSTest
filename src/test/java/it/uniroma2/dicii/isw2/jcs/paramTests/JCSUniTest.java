package it.uniroma2.dicii.isw2.jcs.paramTests;/*import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;*/

import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.engine.control.CompositeCacheManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.apache.jcs.JCS;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

@RunWith(value = Parameterized.class)
public class JCSUniTest {
    private final Random random = new Random();
    private int number = 100;
    private String name;
    private JCS jcs;

    public JCSUniTest(String name) {
        this.name = name;
    }

    @Parameters
    public static Collection<String[]> getTestParameters() {
        return Arrays.asList(new String[][]{
                {"some:key"},
                {""},
                {"Another:key"}
        });
    }

    @Before
    public void configure() throws CacheException {
        jcs = JCS.getInstance("testCache1");
    }

    @Test
    public void testJCS() throws Exception {
        //JCS.setConfigFilename("./cache.ccf");

        LinkedList list = buildList(number);

        jcs.put(name, list);

        Assert.assertEquals(list, jcs.get(name));
    }

    private LinkedList buildList(int number) {
        LinkedList list = new LinkedList();

        for (int i = 0; i < number; i++) {
            list.add(buildMap());
        }

        return list;
    }

    private HashMap buildMap() {
        HashMap map = new HashMap();

        byte[] keyBytes = new byte[32];
        byte[] valBytes = new byte[128];

        for (int i = 0; i < 10; i++) {
            random.nextBytes(keyBytes);
            random.nextBytes(valBytes);

            map.put(new String(keyBytes), new String(valBytes));
        }

        return map;
    }

}

package it.uniroma2.dicii.isw2.jcs.paramTests;

import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class IntegrationIT {

    @Test
    public void mockitoTestIT() {
        List<String> mockList = Mockito.mock(List.class);
        when(mockList.add(anyString())).thenReturn(true);

        boolean added = mockList.add("any string");

        Assert.assertTrue(added);
    }
}

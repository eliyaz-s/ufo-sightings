package ufo.test.service;

import org.junit.Before;
import org.junit.Test;
import ufo.dto.UfoSighting;
import java.util.List;
import ufo.service.UfoSightingServiceImpl;
import static org.junit.Assert.*;

public class UfoSightingServiceImplTest {
    private UfoSightingServiceImpl ufoSightingService;

    @Before
    public void setUp() throws Exception {
        ufoSightingService = new UfoSightingServiceImpl();
    }

    /**
     * Test of getAllSightings method, of class UfoSightingServiceImpl.
     */
    @Test
    public void testGetAllSightings() {
        int expResult = 61391;
        List<UfoSighting> result = ufoSightingService.getAllSightings();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of search method, of class UfoSightingServiceImpl.
     */
    @Test
    public void testSearch() {
        int yearSeen = 1995;
        int monthSeen = 10;
        int expResult=107;
        List<UfoSighting> result = ufoSightingService.search(yearSeen, monthSeen);
        assertEquals(expResult, result.size());
    }
}
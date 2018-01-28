package ufo.test.util;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import ufo.dto.UfoSighting;
import ufo.util.UfoSightingUtil;

public class UfoSightingUtilTest {
    private UfoSighting ufoSighting;
    private String ufoSightingInputString;
    private UfoSightingUtil ufoSightingUtil;

    @Before
    public void setUp(){
        ufoSightingUtil = new UfoSightingUtil();
        ufoSightingInputString = "19951009\t19951009\t Iowa City, IA\t\t\tMan repts. witnessing &quot;flash, followed by a classic UFO, w/ a tailfin at back.&quot; Red color on top half of tailfin. Became triangular.";
        ufoSighting = new UfoSighting("19951009", "19951009", " Iowa City, IA", "", "", "Man repts. witnessing &quot;flash, followed by a classic UFO, w/ a tailfin at back.&quot; Red color on top half of tailfin. Became triangular.");
    }

    @Test
    public void getUfoSightingFileTest() {
        assertNotNull(ufoSightingUtil.getUfoSightingFile());
    }

    @Test
    public void getUfoSightingDATATest() {
        assertNotNull(ufoSightingUtil.getUfoSightingsData());
    }
    
    @Test
    public void processUfoSightingInfoTest() {
        UfoSighting ufoSightingNew = ufoSightingUtil.processUfoSightingInfo(ufoSightingInputString);
        assertNotNull(ufoSightingNew);
        assertEquals(ufoSighting.toString(), ufoSightingNew.toString());
    }
}

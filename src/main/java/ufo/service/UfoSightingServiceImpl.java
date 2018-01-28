package ufo.service;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ufo.util.UfoSightingUtil;
import ufo.dto.UfoSighting;



public class UfoSightingServiceImpl implements UfoSightingService {
    static final Logger logger = LogManager.getLogger(UfoSightingUtil.class.getName());
    
    /**
     * Returns all the sightings in the tsv file.
     * @return 
     */
    @Override
    public List<UfoSighting> getAllSightings() {
        logger.debug("Entering getAllSightings");
        UfoSightingUtil ufoSightingUtil = new UfoSightingUtil();
        List<String> ufoSightingList = ufoSightingUtil.getUfoSightingsData();
        List<UfoSighting> ufoSightings = ufoSightingUtil.processUfoSightingInfo(ufoSightingList);
        return ufoSightings;
    }

   /**
     * Search for sightings happened in the specified year and month;
     *
     * @param yearSeen  The year when the sighting happened
     * @param monthSeen The month when the sightings happened
     * @return the list of UfoSightings.
     */
    @Override
    public List<UfoSighting> search(int yearSeen, int monthSeen) {
        logger.debug("Entering search@UfoSightingServiceImpl");
        List<UfoSighting> ufoSightingList = getAllSightings().stream() 
                .filter(ufoSighting -> ufoSighting.getDateSeen().startsWith(String.valueOf(yearSeen).concat(String.valueOf(monthSeen)))) // filter stream on date seen
                .collect(Collectors.toList());
        return ufoSightingList;
    }
}

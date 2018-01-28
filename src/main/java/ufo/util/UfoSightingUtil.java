package ufo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import ufo.builder.UfoSightingBuilder;
import ufo.dto.UfoSighting;

public class UfoSightingUtil {

    static final Logger logger = LogManager.getLogger(UfoSightingUtil.class.getName());

    /**
     * get file object that represent to ufo_awesome.tsv
     *
     * @return File - file object that represent to ufo_awesome.tsv
     */
    public File getUfoSightingFile() {
        // Get file from resources folder
        ClassLoader classLoader = UfoSightingUtil.class.getClassLoader();
        File ufoSightingFile = new File(classLoader.getResource(loadProperties()).getFile());
        return ufoSightingFile;
    }

    /**
     * get list of Ufo Sightings from ufo_awesome.tsv file
     *
     * @return File - file object that represent to ufo_awesome.tsv
     */
    public List<String> getUfoSightingsData() {
        List<String> ufoSightingList = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(getUfoSightingFile());
            bufferedReader = new BufferedReader(fileReader);
            String sightingstr;
            while ((sightingstr = bufferedReader.readLine()) != null) {
                ufoSightingList.add(sightingstr);
            }
        } catch (IOException e) {
            logger.error("Exception while reading file. " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return ufoSightingList;
    }

    /**
     * process ufoSighting string and populate UfoSighting object
     *
     * @param ufoSightingStr
     * @return UfoSighting
     */
    public UfoSighting processUfoSightingInfo(String ufoSightingStr) {
        UfoSighting ufoSighting = null;
        UfoSightingBuilder builder = new UfoSightingBuilder();
        String[] strToken = ufoSightingStr.split("\t"); // split ufoSightingStr
        // with tab char
        if (strToken.length >= 6) {
            builder.setDateSeen(strToken[0]);
            builder.setDateReported(strToken[1]);
            builder.setPlaceSeen(strToken[2]);
            builder.setShape(strToken[3]);
            builder.setDuration(strToken[4]);
            String description = String.join("\t", Arrays.copyOfRange(strToken, 5, strToken.length));
            builder.setDescription(description);
            ufoSighting = builder.build();
        } else {
            logger.error("Error in ufoSighting record: " + ufoSightingStr); // print
        }

        return ufoSighting;
    }

    /**
     * Process list of ufoSighting string and populate UfoSighting objects
     *
     * @param ufoSightingStrList
     * @return List<UfoSighting>
     */
    public List<UfoSighting> processUfoSightingInfo(List<String> ufoSightingStrList) {
        UfoSighting ufoSighting = null;
        List<UfoSighting> ufoSightingList = new ArrayList<>();
        for (String ufoSightingStr : ufoSightingStrList) {
            String[] strToken = ufoSightingStr.split("\t"); // split
            // ufoSightingStr
            // with tab char
            if (strToken.length >= 6) {
                String dateSeen = strToken[0];
                String dateReported = strToken[1];
                String placeSeen = strToken[2];
                String shape = strToken[3];
                String duration = strToken[4];
                String description = String.join("\t", Arrays.copyOfRange(strToken, 5, strToken.length));

                ufoSighting = new UfoSighting(dateSeen, dateReported, placeSeen, shape, duration, description);
                ufoSightingList.add(ufoSighting);
            } else {
                logger.error("Error in ufoSighting record: " + ufoSightingStr);
            }
        }
        return ufoSightingList;
    }

    private String loadProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        String inputFile = "";
        try {
            ClassLoader classLoader = UfoSightingUtil.class.getClassLoader();
            input = new FileInputStream(classLoader.getResource("ufo-sighting/config.properties").getFile());
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            inputFile = prop.getProperty("input_file");
        } catch (IOException ex) {
            logger.error("Exception while reading file. " + ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("Exception while reading file. " + e.getMessage());
                }
            }
        }
        return inputFile;
    }
}

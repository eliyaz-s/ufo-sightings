package ufo.builder;

import ufo.dto.UfoSighting;

public class UfoSightingBuilder {
    private String dateSeen;
    private String dateReported;
    private String placeSeen;
    private String shape;
    private String duration;
    private String description;

    /**
     *
     * @param dateSeen - date when Ufo Sighting Seen
     */
    public void setDateSeen(String dateSeen) {
        this.dateSeen = dateSeen;
    }

    /**
     *
     * @param dateReported - date when Ufo Sighting Reported
     */
    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    /**
     *
     * @param placeSeen - place of seen Sighting
     */
    public void setPlaceSeen(String placeSeen) {
        this.placeSeen = placeSeen;
    }

    /**
     *
     * @param shape - shape of Sighting
     */
    public void setShape(String shape) {
        this.shape = shape;
    }

    /**
     *
     * @param duration - duration of Sighting
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     *
     * @param description - description of Sighting
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Its build a UfoSighting object.
     * @return UfoSighting
     */
    public UfoSighting build(){
        UfoSighting ufoSighting = new UfoSighting( dateSeen, dateReported, placeSeen, shape, duration, description);
        return ufoSighting;
    }
}

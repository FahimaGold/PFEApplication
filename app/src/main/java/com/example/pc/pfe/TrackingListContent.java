package com.example.pc.pfe;

/**
 * Created by pc on 30/04/2018.
 */

public class TrackingListContent {
    private String trackingType;

    public TrackingListContent(){

    }

    public TrackingListContent(String trackingType) {
        this.trackingType = trackingType;
    }

    public String getTrackingType() {
        return trackingType;
    }

    public void setTrackingType(String trackingType) {
        this.trackingType = trackingType;
    }
}

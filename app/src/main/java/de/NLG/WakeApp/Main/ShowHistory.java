package de.NLG.WakeApp.Main;

public class ShowHistory {

    private final String arrival;
    private final String timeRequired;
    private final String address;
    private final String destination;
    private final String transportOption;


    public ShowHistory(String arrival, String timeRequired, String address, String destination, String transportOption) {
        this.arrival = arrival;
        this.timeRequired = timeRequired;
        this.address = address;
        this.destination = destination;
        this.transportOption = transportOption;
    }

    public String getArrival() {
        return arrival;
    }

    public String getTimeRequired() {
        return timeRequired;
    }

    public String getAddress() {
        return address;
    }

    public String getDestination() {
        return destination;
    }

    public String getTransportOption() {
        return transportOption;
    }
}
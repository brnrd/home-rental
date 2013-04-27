package web.model;

import java.math.BigInteger;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public class SearchResult {

    // Property attributes.
    private Integer property_id;
    private String title;
    private String short_desc;
    private Integer price;
    private String city;
    private String country;
    private String type;
    private Integer note;
    private BigInteger places;
    private Float latitude;
    private Float longitude;
    
    // Geoloc attribute
    private Double distance = 0.0;
    
    // Property Options attributes.
    private Boolean parking;
    private Boolean swim;
    private Boolean wifi;
    private Boolean laundry;
    
    public SearchResult() {}
    
    public SearchResult(Integer p_id, String p_title, String p_sdesc, Integer p_price, String p_city, 
            String p_country, String p_type, Integer p_note, BigInteger p_places, Float lat, Float lng, Boolean po_park,
            Boolean po_swim, Boolean po_wifi, Boolean po_laundry, Double distance) {
        this.property_id = p_id;
        this.title = p_title;
        this.short_desc = p_sdesc;
        this.price = p_price;
        this.city = p_city;
        this.country = p_country;
        this.type = p_type;
        this.note = p_note;
        this.places = p_places;
        this.latitude = lat;
        this.longitude = lng;
        this.parking = po_park;
        this.swim = po_swim;
        this.wifi = po_wifi;
        this.laundry = po_laundry;
        this.distance = distance;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter/Setter">
    public Integer getPropertyId() {
        return this.property_id;
    }
    
    public void setPropertyId(Integer id) {
        this.property_id = id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getShortDesc() {
        return this.short_desc;
    }
    
    public void setShortDesc(String desc) {
        this.short_desc = desc;
    }
    
    public Integer getPrice() {
        return this.price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getNote() {
        return this.note;
    }
    
    public void setNote(Integer note) {
        this.note = note;
    }
    
    public BigInteger getPlaces() {
        return this.places;
    }
    
    public void setPlaces(BigInteger places) {
        this.places = places;
    }
    
    public Float getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(Float lat) {
        this.latitude = lat;
    }
    
    public Float getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(Float lng) {
        this.longitude = lng;
    }
    
    public void setDistance(Double dist) {
        this.distance = dist;
    }
    
    public Boolean getPoParking() {
        return this.parking;
    }
    
    public void setPoParking(Boolean park) {
        this.parking = park;
    }

    public Boolean getPoSwim() {
        return this.swim;
    }
    
    public void setPoSwim(Boolean swim) {
        this.swim = swim;
    }
    
    public Boolean getPoWifi() {
        return this.wifi;
    }
    
    public void setPoWifi(Boolean wifi) {
        this.wifi = wifi;
    }
    
    public Boolean getPoLaundry() {
        return this.laundry;
    }
    
    public void setPoLaundry(Boolean laundry) {
        this.laundry = laundry;
    }
    // </editor-fold>
}
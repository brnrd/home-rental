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
    
    // Geoloc attribute
    private Integer distance = 0;
    
    // Property Options attributes.
    private Boolean parking;
    private Boolean swimming_pool;
    private Boolean wifi;
    private Boolean laundry;
    
    public SearchResult() {}
    
    public SearchResult(Integer p_id, String p_title, String p_sdesc, Integer p_price, String p_city, 
            String p_country, String p_type, Integer p_note, BigInteger p_places, Boolean po_park,
            Boolean po_swim, Boolean po_wifi, Boolean po_laundry, Integer distance) {
        this.property_id = p_id;
        this.title = p_title;
        this.short_desc = p_sdesc;
        this.price = p_price;
        this.city = p_city;
        this.country = p_country;
        this.type = p_type;
        this.note = p_note;
        this.places = p_places;
        this.parking = po_park;
        this.swimming_pool = po_swim;
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
    
    public String getPropertyTitle() {
        return this.title;
    }
    
    public void setPropertyTitle(String title) {
        this.title = title;
    }
    
    public String getPropertyShortDesc() {
        return this.short_desc;
    }
    
    public void setPropertyShortDesc(String desc) {
        this.short_desc = desc;
    }
    
    public Integer getPropertyPrice() {
        return this.price;
    }
    
    public void setPropertyPrice(Integer price) {
        this.price = price;
    }
    
    public String getPropertyCity() {
        return this.city;
    }
    
    public void setPropertyCity(String city) {
        this.city = city;
    }
    
    public String getPropertyCountry() {
        return this.country;
    }
    
    public void setPropertyCountry(String country) {
        this.country = country;
    }
    
    public String getPropertyType() {
        return this.type;
    }
    
    public void setPropertyType(String type) {
        this.type = type;
    }
    
    public Integer getPropertyNote() {
        return this.note;
    }
    
    public void setPropertyNote(Integer note) {
        this.note = note;
    }
    
    public BigInteger getPropertyPlaces() {
        return this.places;
    }
    
    public void setPropertyPlaces(BigInteger places) {
        this.places = places;
    }
    
    public void setDistance(Integer dist) {
        this.distance = dist;
    }
    
    public Boolean getPoParking() {
        return this.parking;
    }
    
    public void setPoParking(Boolean park) {
        this.parking = park;
    }

    public Boolean getPoSwimmingPool() {
        return this.swimming_pool;
    }
    
    public void setPoSwimmingPool(Boolean swim) {
        this.swimming_pool = swim;
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
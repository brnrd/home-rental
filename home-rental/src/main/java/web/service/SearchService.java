package web.service;

import java.util.List;
import org.joda.time.LocalDateTime;
import web.model.SearchResult;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface SearchService {
    
    List<SearchResult> searchForSpecificCountry(String country, LocalDateTime checkin, LocalDateTime checkout, Integer guests);
    List<SearchResult> searchInRadius25(Double lat, Double lng, LocalDateTime checkin, LocalDateTime checkout, Integer guests, Integer min, Integer max, String[] property_types, String[] property_options);
}
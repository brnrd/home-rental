package web.service;

import java.util.List;
import org.joda.time.LocalDateTime;
import web.model.SearchResult;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface SearchService {
    
    List<SearchResult> searchForSpecificCountry(String country, LocalDateTime checkin, LocalDateTime checkout, Integer guests);
    List<SearchResult> searchInRadius25(Integer lat, Integer lng, LocalDateTime checkin, LocalDateTime checkout, Integer guests);
}
package web.dao;

import java.util.List;
import org.joda.time.LocalDateTime;
import web.model.SearchResult;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface SearchDao {
    
    List<SearchResult> searchForSpecificCountry(String country, LocalDateTime checkin, LocalDateTime checkout, Integer guests);
    List<SearchResult> searchInRadius25(Long lat, Long lng, LocalDateTime checkin, LocalDateTime checkout, Integer guests);
}
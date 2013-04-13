package web.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface AbstractDao<E, I extends Serializable> {

    E findById(I id);
    void saveOrUpdate(E e);
    void delete(E e);
    List<E> findByCriteria(Criterion criterion);
    List<E> selectAll(String table);
    List<String> listDistinctCriteria(String criterion, String table);
}
package web.dao;

import web.model.Evaluation;
import web.model.Property;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface EvaluationDao extends AbstractDao<Evaluation, Integer> {

    Boolean saveEvaluation(Evaluation eval);
    Evaluation findByProperty(Property property);
}
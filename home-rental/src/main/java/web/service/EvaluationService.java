package web.service;

import web.model.Evaluation;
import web.model.Property;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface EvaluationService {

    Evaluation findById(Integer eval_id);
    void saveEvaluation(Evaluation eval);
    void deleteEvaluation(Integer eval_id);
    Evaluation findByProperty(Property property);
}
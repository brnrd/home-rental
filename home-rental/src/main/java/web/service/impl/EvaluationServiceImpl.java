package web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.EvaluationDao;
import web.model.Evaluation;
import web.model.Property;
import web.service.EvaluationService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Service("evaluationService")
@Transactional(readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationDao evalDao;
    
    @Override
    public Evaluation findById(Integer eval_id) {
        return evalDao.findById(eval_id);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveEvaluation(Evaluation eval) {
        evalDao.saveEvaluation(eval);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteEvaluation(Integer eval_id) {
        Evaluation eval = evalDao.findById(eval_id);
        if (eval != null) {
            evalDao.delete(eval);
        }
    }
    
    @Override
    public Evaluation findByProperty(Property property) {
        return evalDao.findByProperty(property);
    }
}
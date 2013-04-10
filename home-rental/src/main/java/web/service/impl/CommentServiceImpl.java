package web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.CommentDao;
import web.model.Comment;
import web.model.Property;
import web.model.User;
import web.service.CommentService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Service("commentService")
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao comDao;
    
    @Override
    public Comment findById(Integer com_id) {
        return comDao.findById(com_id);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveComment(Comment com) {
        comDao.saveComment(com);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteComment(Integer com_id) {
        Comment com = comDao.findById(com_id);
        if (com != null) {
            comDao.delete(com);
        }
    }
    
    @Override
    public Comment findByProperty(Property property) {
        return comDao.findByProperty(property);
    }
    
    @Override
    public List<Comment> findByUser(User user) {
        return comDao.findByUser(user);
    }
}
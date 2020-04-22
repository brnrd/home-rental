package web.service;

import java.util.List;
import web.model.Comment;
import web.model.Property;
import web.model.User;

/**
 * 
 * comment added
 * @author Romain <ro.foncier@gmail.com>
 *Comment2
 */

public interface CommentService {

    Comment findById(Integer com_id);
    void saveComment(Comment com);
    void deleteComment(Integer com_id);
    List<Comment> findByProperty(Property property);
    List<Comment> findByUser(User user);
}

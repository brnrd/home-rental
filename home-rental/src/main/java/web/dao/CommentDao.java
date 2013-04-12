package web.dao;

import java.util.List;
import web.model.Comment;
import web.model.Property;
import web.model.User;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface CommentDao extends AbstractDao<Comment, Integer> {
    
    Boolean saveComment(Comment comment);
    List<Comment> findByProperty(Property property);
    List<Comment> findByUser(User user);
}
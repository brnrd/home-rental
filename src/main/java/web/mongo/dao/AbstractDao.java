package web.mongo.dao;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class AbstractDao {
    
    protected MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate template) {
        this.mongoTemplate = template;
    }

    public MongoTemplate getMongoTemplate() {
        return this.mongoTemplate;
    }


}

package web.mongo.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import web.mongo.MongoConfig;

/**
 *
 * @author monsieurblah
 */
public class TestApp {

    private static final Log log = LogFactory.getLog(TestApp.class);
    
    public static void main(String[] args) throws Exception {
        MongoOperations mongoOps = new MongoConfig().mongoTemplate();
        mongoOps.insert(new UserItem("Bernard", "Debecker", "test@test.com"));
        log.info(mongoOps.findOne(new Query(where("firstName").is("Bernard")), UserItem.class));
    }
}

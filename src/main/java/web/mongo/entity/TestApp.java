package web.mongo.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import web.model.Countries;
import web.model.Types;
import web.mongo.MongoConfig;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class TestApp {

    private static final Log log = LogFactory.getLog(TestApp.class);
    
    public static void main(String[] args) throws Exception {
        MongoOperations mongoOps = new MongoConfig().mongoTemplate();
        mongoOps.insert(new UserItem("Bernard", "Debecker", "test@test.com"));
        RentableItem rentable = new RentableItem();
        rentable.setType(Types.FLAT);
        rentable.setCountry(Countries.BELGIUM);
        rentable.setPostCode(1000);
        rentable.setAddress("Boulevard Anspach, 2");
        rentable.setOwner(mongoOps.findOne(new Query(where("firstName").is("Bernard")), UserItem.class).getId());
        rentable.setPrice(100);
        rentable.setCapacity(4);
        mongoOps.insert(rentable);
        log.info(mongoOps.findOne(new Query(where("firstName").is("Bernard")), UserItem.class));
        String id = mongoOps.findOne(new Query(where("firstName").is("Bernard")), UserItem.class).getId();
        log.info(mongoOps.findOne(new Query(where("owner").is(id)), RentableItem.class));
    }
}

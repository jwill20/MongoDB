import com.mongodb.DB;
import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

// Grab the random userId
String user = vars.get("userId");
String userId = "uid" + user + "@email.com";

DB db = MongoDBHolder.getDBFromSource("db", vars.get("mongoDatabase"));
DBCollection customerColl = db.getCollection("customer");

BasicDBObject searchDoc = new BasicDBObject("_id", userId);
BasicDBObject foundDoc = customerColl.findOne(searchDoc);

vars.putObject("SEARCH_CUSTOMER", searchDoc); 
vars.putObject("FOUND_CUSTOMER", foundDoc); 

//System.out.println("here is the status => " + foundDoc.get("status"));


import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteConcern;

// Get Database
com.mongodb.DB db = MongoDBHolder.getDBFromSource("db", vars.get("mongoDatabase"));

DBCollection bookingColl = db.getCollection("booking");
String id = "uid" + vars.get("userId") + "@email.com";

bookingDoc = new BasicDBObject("customerId", id).
	append("flightId", vars.get("returnflightId")).
     append("dateOfBooking", new Date().toString());

//bookingColl.insert(bookingDoc,WriteConcern.UNACKNOWLEDGED);
bookingColl.insert(bookingDoc,WriteConcern.majorityWriteConcern(10000,false,true));

//System.out.println("return  " + vars.get("userId"));




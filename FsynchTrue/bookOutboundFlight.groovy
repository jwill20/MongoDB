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
	append("flightId", vars.get("outboundflightId")).
     append("dateOfBooking", new Date().toString());

bookingColl.insert(bookingDoc,WriteConcern.majorityWriteConcern(10000,true,false));
//bookingColl.insert(bookingDoc,WriteConcern.REPLICA_ACKNOWLEDGED);
//bookingColl.insert(bookingDoc,WriteConcern.ACKNOWLEDGED);
//bookingColl.insert(bookingDoc,WriteConcern.UNACKNOWLEDGED);

//System.out.println(vars.get("userId"));

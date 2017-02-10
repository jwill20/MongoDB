import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import org.bson.types.*;

// Get Database
com.mongodb.DB db = MongoDBHolder.getDBFromSource("db", vars.get("mongoDatabase"));
DBCollection bookingColl = db.getCollection("booking");

//System.out.println("In cancel booking " + ${CANCEL_LOOP_COUNTER});

String[] bookingIDList = vars.getObject("BOOKINGID_LIST");
int idx = Integer.parseInt(vars.get("CANCEL_LOOP_COUNTER"));
String bookingIDtoCancel=bookingIDList[idx];

//System.out.println("Id is  " + bookingIDtoCancel);

ObjectId idToCan = new ObjectId(bookingIDtoCancel);
DBObject searchBookingDoc = new BasicDBObject("_id", idToCan);

bookingColl.remove(searchBookingDoc);


vars.put("BOOKINGID_TO_CANCEL",bookingIDtoCancel);



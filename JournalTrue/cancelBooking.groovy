import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;

// Get Database
com.mongodb.DB db = MongoDBHolder.getDBFromSource("db", vars.get("mongoDatabase"));
DBCollection bookingColl = db.getCollection("booking");

//System.out.println("In cancel booking " + ${CANCEL_LOOP_COUNTER});

String[] bookingIDList = vars.getObject("BOOKINGID_LIST");
String bookingIDtoCancel=bookingIDList[${CANCEL_LOOP_COUNTER}];

//System.out.println("Id is  " + bookingIDtoCancel);

org.bson.types.ObjectId idToCan = new ObjectId(bookingIDtoCancel);
DBObject searchBookingDoc = new BasicDBObject("_id", idToCan);

bookingColl.remove(searchBookingDoc);

vars.put("BOOKINGID_TO_CANCEL",bookingIDtoCancel);


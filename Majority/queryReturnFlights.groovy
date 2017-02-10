import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;

// Get Database
com.mongodb.DB db = MongoDBHolder.getDBFromSource("db", vars.get("mongoDatabase"));

DBCollection flightColl = db.getCollection("flight");
DBObject foundFlightDoc;

DBObject flightDoc = new BasicDBObject("flightSegmentId", vars.get("returnFlightSegmentId")).
	append("scheduledDepartureTime", vars.get("RETURN_DATE"));

foundFlightDoc = flightColl.findOne(flightDoc);
if (foundFlightDoc != null)
{
	vars.put("returnflightId",foundFlightDoc.get("_id").toString());
	//System.out.println("bingo");
}
else
{
	vars.put("returnflightId","none");
	//System.out.println("nope");
}

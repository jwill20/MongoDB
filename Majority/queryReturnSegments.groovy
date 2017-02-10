import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;

// Get Database
com.mongodb.DB db = MongoDBHolder.getDBFromSource("db", vars.get("mongoDatabase"));

DBCollection flightSegmentColl = db.getCollection("flightSegment");
	    
DBObject flightSegmentDoc = new BasicDBObject("originPort", vars.get("TO_AIRPORT_CODE")).
	    	append("destPort", vars.get("FROM_AIRPORT_CODE"));

DBObject foundSegmentDoc = flightSegmentColl.findOne(flightSegmentDoc);

vars.put("returnFlightSegmentId","none");

if (foundSegmentDoc != null)
{	
	vars.put("returnFlightSegmentId",foundSegmentDoc.get("_id"));
}	
else
{
	vars.put("returnFlightSegmentId","none"); 
}

import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;

// Get Database
com.mongodb.DB db = MongoDBHolder.getDBFromSource("db", vars.get("mongoDatabase"));

DBCollection flightSegmentColl = db.getCollection("flightSegment");
	    
DBObject flightSegmentDoc = new BasicDBObject("originPort", vars.get("FROM_AIRPORT_CODE")).
	    	append("destPort", vars.get("TO_AIRPORT_CODE"));

DBObject foundSegmentDoc = flightSegmentColl.findOne(flightSegmentDoc);
//System.out.println("out = " + vars.get("FROM_AIRPORT_CODE"));
//System.out.println("ret = " + vars.get("TO_AIRPORT_CODE"));

vars.put("outboundFlightSegmentId","none");
vars.put("returnFlightSegmentId","none");

if (foundSegmentDoc != null)
{	
	vars.put("outboundFlightSegmentId",foundSegmentDoc.get("_id"));
	//System.out.println("hit");
}	
else
{
	vars.put("outboundFlightSegmentId","none"); 
	//System.out.println("miss");
}

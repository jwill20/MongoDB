import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;

// Get Database
com.mongodb.DB db = MongoDBHolder.getDBFromSource("db", vars.get("mongoDatabase"));

// Get all bookings for a user
String emailUser = "uid" + vars.get("userId") + "@email.com";
DBCollection bookingColl = db.getCollection("booking");
DBObject searchBookingDoc = new BasicDBObject("customerId", emailUser);

foundBookingCursor = bookingColl.find(searchBookingDoc);
String[] bookingIdList = new String[200];

int numToCancel = 0;
int numBookings = foundBookingCursor.size();

//System.out.println("numbookings = " + numBookings); 
if (numBookings > 2)
{
	 numToCancel = numBookings - 2; 
}
//System.out.println("numtoCancel = " + numToCancel); 

// Grab all the booking id's to cancel
for (int a = 0; a < numToCancel; a++)
{
	bookingDoc = foundBookingCursor.next();
	bookingIdList[a] = bookingDoc.get("_id").toString();
	//System.out.println("id = " + bookingIdList[a]);
}

vars.putObject("BOOKINGID_LIST", bookingIdList); 
vars.put("NUMBER_TO_CANCEL", numToCancel.toString());




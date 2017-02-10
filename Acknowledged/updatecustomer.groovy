import com.mongodb.DB;
import org.apache.jmeter.protocol.mongodb.config.MongoDBHolder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.WriteConcern;

DB db = MongoDBHolder.getDBFromSource("db", vars.get("mongoDatabase"));
DBCollection customerColl = db.getCollection("customer");

//Grab the customer documents stored in the View Customer Profile Step
BasicDBObject searchDoc = vars.getObject("SEARCH_CUSTOMER");
BasicDBObject foundDoc  = vars.getObject("FOUND_CUSTOMER");

//Grab the address portion from the complete customer document
BasicDBObject addressDoc = (BasicDBObject)foundDoc.get("address");
			
/*************************************************************/
/* Grab the embedded street address                          */  
/*************************************************************/
String addressString = addressDoc.get("streetAddress");
			
/*************************************************************/
/* Grab the street part from that                            */
/*************************************************************/
String addStreet = addressString.substring(addressString.indexOf(" ") + 1);
			
/*************************************************************/
/* Grab the number part, convert to integer and increment    */
/*************************************************************/
String addNumString = addressString.substring(0, addressString.indexOf(" "));
int addNum = new Integer(addNumString);
addNum++;
			
/*************************************************************/
/* Grab the postal code, convert to integer and increment    */
/*************************************************************/
String postCodeString = addressDoc.get("postalCode");
Integer postNum = new Integer(postCodeString);

if (postNum == 99999)
{
	postNum = 10000;
}
postNum++;
			
/*************************************************************/
/* Update the embedded street address                        */
/*************************************************************/
addressDoc.put("streetAddress", addNum + " " + addStreet);
addressDoc.put("postalCode", postNum.toString());

/*************************************************************/
/* Put the embedded address document back into the main doc. */
/*************************************************************/
foundDoc.put("address", addressDoc);

customerColl.update(searchDoc, foundDoc, false, false, WriteConcern.ACKNOWLEDGED);
//customerColl.update(searchDoc, foundDoc, false, false, WriteConcern.majorityWriteConcern(10000,true,false));

//System.out.println("Just updated => " + foundDoc.get("_id"));
//System.out.println("here is the status => " + foundDoc.get("status"));




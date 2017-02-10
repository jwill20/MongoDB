/**********************************************************************************/
/* In this section we are simulating a customer picking an outbound flight date   */
/* and a return date.  Flights go out for 365 days from the date that they are    */
/* generated, so the outbound flight will be randomly generated from 0-358 to     */
/* allow time for a return flight.                                                */
/**********************************************************************************/
long msInSecond            = 1000l;
long secondsInMinute       = 60l;
long minutesInHour         = 60l;
long hoursInDay            = 24l;
long msForOutboundFlight   = 0l;
long msForReturnFlight     = 0l;
int daysOutForFlight       = 358;
int daysOutForReturnFlight = 8;
long msInDay               = 3600000l;
int easternStandardTimeZoneOffset = 300;
/*******************************************************************************/
/* The starting point date will be the current date.  We then set the time to  */   
/* midnight.                                                                   */ 
/*******************************************************************************/
Date baseDate = new Date();
baseDate.setHours(0); baseDate.setMinutes(0); baseDate.setSeconds(0);

/*******************************************************************************/
/* Get the random day for the outbound flight. Multiply to get the total       */
/* milliseconds to add onto the date.                                          */
/*******************************************************************************/
long firstRandomDay = new Random().nextInt(daysOutForFlight);
						
msForOutboundFlight = (firstRandomDay * hoursInDay * minutesInHour * secondsInMinute * msInSecond);
Date outboundFlightDate = new Date(baseDate.getTime() + msForOutboundFlight);
			
/*******************************************************************************/
/* Adjust for the time zone offset.                                            */
/*******************************************************************************/
//if (outboundFlightDate.getTimezoneOffset() == easternStandardTimeZoneOffset)
//{
	//outboundFlightDate.setTime(outboundFlightDate.getTime() + msInDay);
//}

/*******************************************************************************/
/* Get the random day for the return flight. Between 1-8 days past the         */
/* outbound flight.                                                            */ 
/*******************************************************************************/
long secondRandomDay = new Random().nextInt(daysOutForReturnFlight) + 1;
msForReturnFlight = secondRandomDay * hoursInDay * minutesInHour * secondsInMinute * msInSecond;
Date returnDate = new Date(outboundFlightDate.getTime() + msForReturnFlight);

vars.put("DEPARTURE_DATE", outboundFlightDate.toString());
vars.put("RETURN_DATE", returnDate.toString());

//System.out.println(outboundFlightDate.toString());





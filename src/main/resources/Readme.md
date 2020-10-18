A GPS is plugged into a vehicle which sends GPS and vehicle information per minutes to our server. Our application process this information so that  
at any particular time user can use the following data. This data should also be preserved in a datastore for future analytics purpose (For simplicity we can use file for 
the data storage)

1. Current State.

2. Trips : 

    1. A trip is a contiguous set of positive “ignitionstatus”.

    2. You can start a trip with a Threshold of 3 continuous positive “ignitionstatus” packets and close with a similar threshold of negative packets.

    3. Path for a trip. For simplicity we can return GPS Point for the trips.

    4. Trip History. 



Assumptions:

1. You can assume you have a file of GPS locations in chronological order.

2. You can create a file in  which you can have address for a specific lat-long.





Data Format : DeviceId, timestamp, latitude,longitude,ignitionstatus

   eg. 123,    1600435658000,   12.345,  32.234,   1



Processed Data Format:
{

“deviceId” : 123,

“timestamo” : 1600435658000,

“createdOn” : 1600435658000,

“latitude” : 12.345,

”longitude” : 32.234,

“ignitionstatus” : true

}

	

Note : 

 Device Id : Alphanumeric

 Timestamp: Long(epoch)

 Latitude: Double

 Longitude: Double

 IgnitionStatus: boolean

 Created On : Long(epoch)



	
	

	

	Output

	Number of Trips started: 2

	Number of Trips completed/closed: 1<DeviceId 123>

	

	

	Test case 2)

	DeviceId,timestamp,    latitude,  longitude,ignitionstatus

    123,    1600435658000,   12.345,  32.234,   0

	123,    1600435658000,   12.345,  32.234,   1

	124,    1600435658050,   14.345,  34.234,   1

	123,    1600435658100,   12.347,  32.237,   1

	124,    1600435658100,   14.347,  34.237,   1

	124,    1600435658200,   12.347,  32.239,   1

	123,    1600435658200,   12.145,  31.234,   1

	124,    1600435658200,   12.349,  32.234,   1

	123,    1600435658300,   12.175,  31.244,   1

	123,    1600435658320,   12.345,  32.234,   1

	123,    1600435658320,   12.345,  32.234,   1

	124,    1600435658200,   12.349,  32.234,   0

	124,    1600435658201,   12.349,  32.234,   0

	123,    1600435658320,   12.345,  32.234,   1

	123,    1600435658401,   12.345,  32.234,   0

	123,    1600435658420,   12.345,  32.234,   0

	124,    1600435658201,   12.349,  32.234,   0

	123,    1600435658240,   12.345,  32.234,   1

	123,    1600435658440,   12.345,  32.234,   0

	

	

	Output

	Number of Trips started: 2

	Number of Trips completed: 2<Hint check order of arrival>
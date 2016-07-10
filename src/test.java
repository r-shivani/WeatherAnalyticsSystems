import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class test {

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		
		Map<String, Object> weatherMap =getRemoteTemp("Provo");
		
		for (String name: weatherMap.keySet()){

            String key =name.toString();
            String value = weatherMap.get(name).toString();  
            System.out.println(key + " " + value);  


}
		System.out.println("");
		
		
	}
	public static String getId(String name) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(new MongoClientURI(
				"mongodb://localhost:27017"));

		DB database = mongoClient.getDB("cs570");
		DBCollection collection = database
				.getCollection("wholeData");
		
		
		DBObject searchByName = new BasicDBObject("name", name);
		DBObject found = collection.findOne(searchByName);
		
		String cityId = found.get("_id").toString();
		System.out.println("id" + cityId);
		return cityId;
	}
	
	
	public  static Map<String, Object> getRemoteTemp(String zipcode)
			throws RemoteException {
		
		String cityId = null;
		String data = null;
		try {
		 cityId=getId(zipcode);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//updateWeatherConditions(zipcode);
		Map<String, Object> weatherMap = new HashMap<String, Object>();
		List<String> tempList = new ArrayList<String>();
		try {
			@SuppressWarnings("deprecation")
			MongoClient mongoClient = new MongoClient(new MongoClientURI(
					"mongodb://localhost:27017"));

			DB database = mongoClient.getDB("cs570");
			DBCollection collection = database
					.getCollection("weatherByCityData");
			System.out.println("hi 0");
			
//			BasicDBObject whereQuery = new BasicDBObject();
//			whereQuery.put("mainId",cityId );
//			DBCursor cursor = collection.find(whereQuery);
//			System.out.println("size" +cursor.size());
//			while(cursor.hasNext()) {
//			    DBObject cityData = (DBObject) cursor.next().get("city");
//			    //DBObject cityData2 = (DBObject) cursor.next().get("list");
//			    String cityName = cityData.get("name").toString();
//			    System.out.println("city name ---> "+cityName);
//			}
//			
			
			DBObject searchByZip = new BasicDBObject("mainId", "4070245");
			DBObject found = collection.findOne(searchByZip);
			System.out.println("hi 1");
			//System.out.println("found-->"+found.toString());
			BasicDBList list = (BasicDBList) found.get("list");
			BasicDBObject[] listArr = list.toArray(new BasicDBObject[0]);
			for (BasicDBObject dbObj : listArr) {
				tempList.add(dbObj.get("temp").toString());
				// System.out.println(dbObj.get("temp").toString());
			}

			JSONObject jObject = new JSONObject(found.toString());
			
			String date = jObject.getString("dateFirst");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

		

			Date date1 = format.parse(date);

			Calendar cal = Calendar.getInstance(); 
			cal.setTime(date1); 
			System.out.println(date+"here is the zol");
			System.out.println("jobject"+jObject);
			JSONArray listData = jObject.getJSONArray("list");
			//for list array
			for(int i = 0; i < listData.length(); i++)
			{
				
				Date nextDate = cal.getTime();
                
                String DateToStr = format.format(nextDate);
                cal.add(Calendar.DATE, 1);
				System.out.println(DateToStr+"here");
				
			      JSONObject objects = listData.getJSONObject(i);
			      
			      JSONObject tempObj = new JSONObject(objects.toString());
			      JSONObject tempFinal = tempObj.getJSONObject("temp");
			      //for temp data 6 fields
			      Iterator iter = tempFinal.keys();
					while (iter.hasNext()) {
						String key = (String) iter.next();
						Object value = tempFinal.get(key);
						weatherMap.put(key+i, value);
					}
			      //for weather data
				JSONArray weatherData = objects.getJSONArray("weather");
				JSONObject wData = weatherData.getJSONObject(0);

				//weather inside 4 values
				Iterator iter1 = wData.keys();
				while (iter1.hasNext()) {
					String key = (String) iter1.next();
					Object value = wData.get(key);
					weatherMap.put(key, value);
				}
			}
			
			JSONObject city = jObject.getJSONObject("city");
			JSONObject coord = city.getJSONObject("coord");
			
			String name = city.getString("name");
			weatherMap.put("name", name);
			
			String country = city.getString("country");
			weatherMap.put("country", country);
			
			Integer id = city.getInt("id");
			weatherMap.put("id", id);
			
			Iterator iter = coord.keys();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				Object value = coord.get(key);
				weatherMap.put(key, value);
			}
		
			//System.out.println("weatherMap "+weatherMap.size());

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return weatherMap;
	}


}

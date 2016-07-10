import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
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
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Client {

    private Client() {}
    public void getRemoteTemp(String zipcode) {
		String data = null;
		
		Map<String, Object> weatherMap = new HashMap<String, Object>();
		List<String> tempList = new ArrayList<String>();
		try {
			@SuppressWarnings("deprecation")
			MongoClient mongoClient = new MongoClient(new MongoClientURI(
					"mongodb://localhost:27017"));

			DB database = mongoClient.getDB("cs570");
			DBCollection collection = database
					.getCollection("weatherByCityData");

			DBObject searchByZip = new BasicDBObject("zipcode", zipcode);
			DBObject found = collection.findOne(searchByZip);

			BasicDBList list = (BasicDBList) found.get("list");
			BasicDBObject[] listArr = list.toArray(new BasicDBObject[0]);
			for (BasicDBObject dbObj : listArr) {
				tempList.add(dbObj.get("temp").toString());
				// System.out.println(dbObj.get("temp").toString());
			}

			JSONObject jObject = new JSONObject(found.toString());
			System.out.println("jobject"+jObject);
			JSONArray listData = jObject.getJSONArray("list");
			//for list array
			for(int i = 0; i < listData.length(); i++)
			{
			      JSONObject objects = listData.getJSONObject(i);
			      
			      /*String[] elementNames = JSONObject.getNames(objects);
			      System.out.printf("%d ELEMENTS IN CURRENT OBJECT:\n", elementNames.length);
			      for (String elementName : elementNames)
			      {
			        double value = objects.getDouble(elementName);
			        System.out.println("element" + elementName + " value " + value);
			      }
			      System.out.println();*/
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
	}


    public static void main(String[] args) {

	String host = (args.length < 1) ? null : args[0];
	try {
	    Registry registry = LocateRegistry.getRegistry(host);
	    Hello stub = (Hello) registry.lookup("Hello");
	    String response = stub.sayHello();
	    System.out.println("response: " + response);
	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
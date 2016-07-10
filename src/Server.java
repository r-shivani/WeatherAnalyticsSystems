import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class Server implements Hello {
	private WeatherInfo weatherInformation[];

	public void TemperatureServerImpl() throws RemoteException {

	}

	public Server() throws RemoteException {
	}

	public static String getId(String name) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(new MongoClientURI(
				"mongodb://localhost:27017"));

		DB database = mongoClient.getDB("cs570");
		DBCollection collection = database
				.getCollection("allCityList");
		
		
		DBObject searchByName = new BasicDBObject("name", name);
		DBObject found = collection.findOne(searchByName);
		
		String cityId = found.get("_id").toString();
		System.out.println("id" + cityId);
		return cityId;
	}

	public   Map<String, Object> getRemoteTemp(String zipcode)
			throws RemoteException {
		
		String cityId = null;
		String data = null;
		try {
		 cityId=getId(zipcode);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<String, Object> weatherMap = new HashMap<String, Object>();
		List<String> tempList = new ArrayList<String>();
		try {
			@SuppressWarnings("deprecation")
			MongoClient mongoClient = new MongoClient(new MongoClientURI(
					"mongodb://localhost:27017"));

			DB database = mongoClient.getDB("cs570");
			DBCollection collection = database
					.getCollection("weatherByCityData");
			
			DBObject searchByZip = new BasicDBObject("mainId", cityId);
			DBObject found = collection.findOne(searchByZip);
			
			BasicDBList list = (BasicDBList) found.get("list");
			
			
			for (int j = 0; j < list.size(); j++) {
				Calendar c2 = Calendar.getInstance();
				c2.add(Calendar.DATE, j);

				int day = c2.get(Calendar.DAY_OF_WEEK);
				if (day == 1) {
					weatherMap.put("d" + j, "Saturday");
				} else if (day == 2) {
					weatherMap.put("d" + j, "Sunday");
				} else if (day == 3) {
					weatherMap.put("d" + j, "Monday");
				} else if (day == 4) {
					weatherMap.put("d" + j, "Tuesday");
				} else if (day == 5) {
					weatherMap.put("d" + j, "Wednesday");
				} else if (day == 6) {
					weatherMap.put("d" + j, "Thursday");
				} else if (day == 7) {
					weatherMap.put("d" + j, "Friday");
				}
			
			BasicDBObject[] listArr = list.toArray(new BasicDBObject[0]);
			for (BasicDBObject dbObj : listArr) {
				tempList.add(dbObj.get("temp").toString());
			}

			JSONObject jObject = new JSONObject(found.toString());
			
			String date = jObject.getString("dateFirst");
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

		
			weatherMap.put("date", date);
			Date date1 = format.parse(date);

			Calendar cal = Calendar.getInstance(); 
			cal.setTime(date1); 
			System.out.println(date+"here is the zol");
			System.out.println("jobject"+jObject);
			JSONArray listData = jObject.getJSONArray("list");
			
			for(int i = 0; i < listData.length(); i++)
			{
				
				Date nextDate = cal.getTime();
                
                String DateToStr = format.format(nextDate);
                cal.add(Calendar.DATE, 1);
				System.out.println(DateToStr+"here");
				
			      JSONObject objects = listData.getJSONObject(i);
			      
			      JSONObject tempObj = new JSONObject(objects.toString());
			      JSONObject tempFinal = tempObj.getJSONObject("temp");
			      Object speed = tempObj.getDouble("speed");

					double a1 = (double) speed;
					int y1 = (int) Math.round(a1);

					weatherMap.put("speed0", y1);
					Integer humidity = tempObj.getInt("humidity");
					weatherMap.put("humidity" + i, humidity);
					Integer clouds = tempObj.getInt("clouds");
					weatherMap.put("clouds" + i, clouds);
			      //for temp data 6 fields
			      Iterator iter = tempFinal.keys();
					while (iter.hasNext()) {
						String key = (String) iter.next();
						Object value = tempFinal.get(key);
						
						weatherMap.put(key + i, value);
					}
			      //for weather data
				JSONArray weatherData = objects.getJSONArray("weather");
				JSONObject wData = weatherData.getJSONObject(0);
				
				

				//weather inside 4 values
				Iterator iter1 = wData.keys();
				while (iter1.hasNext()) {
					String key = (String) iter1.next();
					Object value = wData.get(key);
					
					weatherMap.put(key + i, value);
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
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return weatherMap;
	}


	public String sayHello() {
		return "Hello, world!";
	}

	public static void main(String args[]) throws UnknownHostException {

		try {
			Server obj = new Server();
			Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Hello", stub);
			//updateMethod();
			
			
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
		
	}

	public static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"C:/Users/shivani/Desktop/test.json"));
		String name = null;
		String[] names = new String[21000];
		try {

			JSONParser parser = new JSONParser();
			try {

				JSONArray data = (JSONArray) parser.parse(new FileReader(
						"/Users/shivani/Desktop/test1.json"));

				@SuppressWarnings("deprecation")
				Mongo mongo1 = new Mongo("localhost", 27017);
				DB db1 = mongo1.getDB("cs570");
				DBCollection collection1 = db1.getCollection("wholeData");

				for (int i = 0; i < data.length(); i++) {
					names[i] = data.get(i).toString();

					DBObject dbObject1 = (DBObject) JSON.parse(names[i]);
					collection1.insert(dbObject1);
				}

				System.out.println("list" + names[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			br.close();
		}
		return name;
	}

	public static void getCityById() throws IOException {

		List<String> listId = new ArrayList<String>();
		@SuppressWarnings("deprecation")
		Mongo mongo1 = new Mongo("localhost", 27017);
		DB db1 = mongo1.getDB("cs570");
		DBCollection coll = db1.getCollection("wholeData");
		DBCursor cursor = coll.find();

		while (cursor.hasNext()) {
			String data = cursor.next().get("_id").toString();
			//System.out.println("count " + listId.size());
			listId.add(data);
		}
System.out.println("list size first "+listId.size());
		String responseString = "";
		@SuppressWarnings("deprecation")
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://api.openweathermap.org/data/2.5/forecast/daily?appid=587599c80dacc1803188ed836ac2420d");
		HttpResponse response = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Calendar calobj = Calendar.getInstance();
		String finalstore=df.format(calobj.getTime()).toString();
		for (int i = 0; i < listId.size(); i++) {

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("id", listId.get(i)));
			nameValuePairs.add(new BasicNameValuePair("mode", "json"));
			nameValuePairs.add(new BasicNameValuePair("units", "metric"));
			nameValuePairs.add(new BasicNameValuePair("cnt", "7"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity);

			//System.out.println("this is the response " + responseString);

			@SuppressWarnings("deprecation")
			Mongo mongo2 = new Mongo("localhost", 27017);
			DB db2 = mongo2.getDB("cs570");
			DBCollection collection1 = db2.getCollection("weatherByCityData");
			//System.out.println("line1-->" + responseString);
			// convert JSON to DBObject directly
			DBObject dbObject1 = (DBObject) JSON.parse(responseString);

			
			//System.out.println(df.format(calobj.getTime()));
System.out.println("list id --> "+listId.get(i));
			dbObject1.put("dateFirst", finalstore);
			dbObject1.put("mainId", listId.get(i));
			collection1.insert(dbObject1);
			
		}
		System.out.println("size of list" + listId.size());
	}
	
	public static void updateMethod() throws IOException {
		Date date = Calendar.getInstance().getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
	    String todayDate = sdf.format(date);
	    
	    @SuppressWarnings("deprecation")
		MongoClient mongoClient = new MongoClient(new MongoClientURI(
				"mongodb://localhost:27017"));

		DB database = mongoClient.getDB("cs570");
		DBCollection collection = database
				.getCollection("weatherByCityData");
		DBCursor cursor = collection.find();
		String dbDate = cursor.next().get("dateFirst").toString();
		
		System.out.println("current date"+dbDate);
		System.out.println("today's date"+todayDate);
		
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yy");
	    String todayDate1 = sdf1.format(date);
				cal.add(Calendar.DATE, -7);
				System.out.println("Date = "+ sdf1.format(cal.getTime()));
				if(dbDate.compareTo(sdf1.format(cal.getTime()))>0){
	        		System.out.println("databse is up to date");
	        	}else if(dbDate.compareTo(sdf1.format(cal.getTime()))<0){
	        		System.out.println("databse is outdated");
	        		
	        		MongoClient mongoClient1 = new MongoClient(new MongoClientURI(
	        				"mongodb://localhost:27017"));

	        		DB database1 = mongoClient.getDB("cs570");
	        		DBCollection collection1 = database
	        				.getCollection("weatherByCityData");

	        		
	        		// Delete All documents from collection using DBCursor
	        		DBCursor cursor1 = collection.find();
	        		while (cursor.hasNext()) {
	        		    collection.remove(cursor.next());
	        		}
	        		
	        		getCityById();
	        		
	        		
	        		
	        	}else if(dbDate.compareTo(sdf1.format(cal.getTime()))==0){
	        		System.out.println("databse is up to date");
	        	}else{
	        		System.out.println("");
	        	}
	    
	}

	@Override
	public WeatherInfo[] getWeatherInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
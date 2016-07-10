import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;


public class Read {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//getCityById();
		//readFile("data.json");
	}

	
	public static void getCityById() throws IOException {

		List<String> listId = new ArrayList<String>();
		
		@SuppressWarnings("deprecation")
		Mongo mongo1 = new Mongo("localhost", 27017);
		DB db1 = mongo1.getDB("cs570");
		DBCollection coll = db1.getCollection("allCityList");
		DBCursor cursor = coll.find();

		while (cursor.hasNext()) {
			String data = cursor.next().get("_id").toString();
			//System.out.println("count " + listId.size());
			listId.add(data);
			
		}
		int[] listInt = new int[listId.size()];
		for(int i=0;i<listId.size();i++)
		{
			listInt[i]=Integer.parseInt(listId.get(i));
		}
		Arrays.sort(listInt);
		
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
		for (int i = 0; i < listInt.length; i++) {

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("id", listInt[i]+""));
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
System.out.println(i+"list id --> "+listInt[i]);
			dbObject1.put("dateFirst", finalstore);
			dbObject1.put("mainId", listInt[i]+"");
			collection1.insert(dbObject1);
			
		}
		System.out.println("size of list" + listId.size());
	}
	
	public static String readFile(String fileName) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader(
			//	"C:/Users/shivani/Desktop/test.json"));
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
				DBCollection collection1 = db1.getCollection("allCityList");

				for (int i = 0; i < data.size(); i++) {
					names[i] = data.get(i).toString();

					DBObject dbObject1 = (DBObject) JSON.parse(names[i]);
					collection1.insert(dbObject1);
				}

				System.out.println("list" + names[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			
		}
		return name;
	}
}

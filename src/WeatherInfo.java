// WeatherInfo Class

import java.rmi.*;
import java.io.Serializable;

public class WeatherInfo implements Serializable {
   private String cityName;
   private String temperature;
   private String description;

   public WeatherInfo( String city, String desc, String temp )
   {
      cityName = city;
      temperature = temp;
      description = desc;
   }

   public String getCityName() { return cityName; }

   public String getTemperature() { return temperature; }

   public String getDescription() { return description; }
}
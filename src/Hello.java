

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface Hello extends Remote {
    String sayHello() throws RemoteException, Exception;
    
    public WeatherInfo[] getWeatherInfo() throws RemoteException;
    
    public Map<String, Object> getRemoteTemp(String zipcode) throws RemoteException;
}
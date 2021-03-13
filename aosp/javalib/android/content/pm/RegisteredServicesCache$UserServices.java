package android.content.pm;

import com.google.android.collect.Maps;
import java.util.Map;

class UserServices<V> {
  boolean mBindInstantServiceAllowed = false;
  
  boolean mPersistentServicesFileDidNotExist = true;
  
  final Map<V, Integer> persistentServices = Maps.newHashMap();
  
  Map<V, RegisteredServicesCache.ServiceInfo<V>> services = null;
  
  private UserServices() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/RegisteredServicesCache$UserServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
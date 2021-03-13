package android.app;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.pm.ApplicationInfo;

public class AppComponentFactory {
  public static final AppComponentFactory DEFAULT = new AppComponentFactory();
  
  public Activity instantiateActivity(ClassLoader paramClassLoader, String paramString, Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return (Activity)paramClassLoader.loadClass(paramString).newInstance();
  }
  
  public Application instantiateApplication(ClassLoader paramClassLoader, String paramString) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return (Application)paramClassLoader.loadClass(paramString).newInstance();
  }
  
  public ClassLoader instantiateClassLoader(ClassLoader paramClassLoader, ApplicationInfo paramApplicationInfo) {
    return paramClassLoader;
  }
  
  public ContentProvider instantiateProvider(ClassLoader paramClassLoader, String paramString) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return (ContentProvider)paramClassLoader.loadClass(paramString).newInstance();
  }
  
  public BroadcastReceiver instantiateReceiver(ClassLoader paramClassLoader, String paramString, Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return (BroadcastReceiver)paramClassLoader.loadClass(paramString).newInstance();
  }
  
  public Service instantiateService(ClassLoader paramClassLoader, String paramString, Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return (Service)paramClassLoader.loadClass(paramString).newInstance();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppComponentFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
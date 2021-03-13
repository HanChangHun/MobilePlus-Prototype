package android.content.pm;

import android.appwidget.AppWidgetProviderInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPinItemRequest {
  public boolean accept(Bundle paramBundle) throws RemoteException {
    return false;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public AppWidgetProviderInfo getAppWidgetProviderInfo() throws RemoteException {
    return null;
  }
  
  public Bundle getExtras() throws RemoteException {
    return null;
  }
  
  public ShortcutInfo getShortcutInfo() throws RemoteException {
    return null;
  }
  
  public boolean isValid() throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPinItemRequest$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content;

import android.os.IBinder;

public interface ServiceConnection {
  default void onBindingDied(ComponentName paramComponentName) {}
  
  default void onNullBinding(ComponentName paramComponentName) {}
  
  void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder);
  
  void onServiceDisconnected(ComponentName paramComponentName);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ServiceConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
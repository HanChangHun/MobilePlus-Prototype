package android.app;

import android.os.ServiceManager;
import android.util.Singleton;

class null extends Singleton<IActivityManager> {
  protected IActivityManager create() {
    return IActivityManager.Stub.asInterface(ServiceManager.getService("activity"));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.ServiceManager;
import android.util.Singleton;

class null extends Singleton<IActivityTaskManager> {
  protected IActivityTaskManager create() {
    return IActivityTaskManager.Stub.asInterface(ServiceManager.getService("activity_task"));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityTaskManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
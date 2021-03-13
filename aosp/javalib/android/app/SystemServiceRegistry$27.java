package android.app;

import android.hardware.input.InputManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.StaticServiceFetcher<InputManager> {
  public InputManager createService() {
    return InputManager.getInstance();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$27.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
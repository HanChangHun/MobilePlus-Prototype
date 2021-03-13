package android.app;

import android.os.ServiceManager;
import android.util.Singleton;

class null extends Singleton<IUriGrantsManager> {
  protected IUriGrantsManager create() {
    return IUriGrantsManager.Stub.asInterface(ServiceManager.getService("uri_grants"));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/UriGrantsManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
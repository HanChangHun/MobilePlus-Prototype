package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IOtaDexopt {
  public IBinder asBinder() {
    return null;
  }
  
  public void cleanup() throws RemoteException {}
  
  public void dexoptNextPackage() throws RemoteException {}
  
  public float getProgress() throws RemoteException {
    return 0.0F;
  }
  
  public boolean isDone() throws RemoteException {
    return false;
  }
  
  public String nextDexoptCommand() throws RemoteException {
    return null;
  }
  
  public void prepare() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IOtaDexopt$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
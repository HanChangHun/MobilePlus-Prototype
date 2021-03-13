package android.hardware.radio;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IAnnouncementListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onListUpdated(List<Announcement> paramList) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/IAnnouncementListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.hardware.radio;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IAnnouncementListener {
  public static IAnnouncementListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.radio.IAnnouncementListener";
  }
  
  public void onListUpdated(List<Announcement> paramList) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.radio.IAnnouncementListener");
      parcel.writeTypedList(paramList);
      if (!this.mRemote.transact(1, parcel, null, 1) && IAnnouncementListener.Stub.getDefaultImpl() != null) {
        IAnnouncementListener.Stub.getDefaultImpl().onListUpdated(paramList);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/IAnnouncementListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
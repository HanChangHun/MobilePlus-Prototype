package android.hardware.radio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IAnnouncementListener {
  private static final String DESCRIPTOR = "android.hardware.radio.IAnnouncementListener";
  
  static final int TRANSACTION_onListUpdated = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.radio.IAnnouncementListener");
  }
  
  public static IAnnouncementListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.radio.IAnnouncementListener");
    return (iInterface != null && iInterface instanceof IAnnouncementListener) ? (IAnnouncementListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAnnouncementListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onListUpdated";
  }
  
  public static boolean setDefaultImpl(IAnnouncementListener paramIAnnouncementListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAnnouncementListener != null) {
        Proxy.sDefaultImpl = paramIAnnouncementListener;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.radio.IAnnouncementListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.radio.IAnnouncementListener");
    onListUpdated(paramParcel1.createTypedArrayList(Announcement.CREATOR));
    return true;
  }
  
  private static class Proxy implements IAnnouncementListener {
    public static IAnnouncementListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.radio.IAnnouncementListener";
    }
    
    public void onListUpdated(List<Announcement> param2List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.radio.IAnnouncementListener");
        parcel.writeTypedList(param2List);
        if (!this.mRemote.transact(1, parcel, null, 1) && IAnnouncementListener.Stub.getDefaultImpl() != null) {
          IAnnouncementListener.Stub.getDefaultImpl().onListUpdated(param2List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/IAnnouncementListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
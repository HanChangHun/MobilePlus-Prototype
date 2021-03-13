package android.hardware.radio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IAnnouncementListener extends IInterface {
  void onListUpdated(List<Announcement> paramList) throws RemoteException;
  
  public static class Default implements IAnnouncementListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onListUpdated(List<Announcement> param1List) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAnnouncementListener {
    private static final String DESCRIPTOR = "android.hardware.radio.IAnnouncementListener";
    
    static final int TRANSACTION_onListUpdated = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.radio.IAnnouncementListener");
    }
    
    public static IAnnouncementListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.radio.IAnnouncementListener");
      return (iInterface != null && iInterface instanceof IAnnouncementListener) ? (IAnnouncementListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAnnouncementListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onListUpdated";
    }
    
    public static boolean setDefaultImpl(IAnnouncementListener param1IAnnouncementListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAnnouncementListener != null) {
          Proxy.sDefaultImpl = param1IAnnouncementListener;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.hardware.radio.IAnnouncementListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.radio.IAnnouncementListener");
      onListUpdated(param1Parcel1.createTypedArrayList(Announcement.CREATOR));
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
  
  private static class Proxy implements IAnnouncementListener {
    public static IAnnouncementListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.radio.IAnnouncementListener";
    }
    
    public void onListUpdated(List<Announcement> param1List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.radio.IAnnouncementListener");
        parcel.writeTypedList(param1List);
        if (!this.mRemote.transact(1, parcel, null, 1) && IAnnouncementListener.Stub.getDefaultImpl() != null) {
          IAnnouncementListener.Stub.getDefaultImpl().onListUpdated(param1List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/IAnnouncementListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
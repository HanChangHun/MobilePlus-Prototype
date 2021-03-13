package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICameraClient extends IInterface {
  public static class Default implements ICameraClient {
    public IBinder asBinder() {
      return null;
    }
  }
  
  public static abstract class Stub extends Binder implements ICameraClient {
    private static final String DESCRIPTOR = "android.hardware.ICameraClient";
    
    public Stub() {
      attachInterface(this, "android.hardware.ICameraClient");
    }
    
    public static ICameraClient asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.ICameraClient");
      return (iInterface != null && iInterface instanceof ICameraClient) ? (ICameraClient)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICameraClient getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return null;
    }
    
    public static boolean setDefaultImpl(ICameraClient param1ICameraClient) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICameraClient != null) {
          Proxy.sDefaultImpl = param1ICameraClient;
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
      if (param1Int1 != 1598968902)
        return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
      param1Parcel2.writeString("android.hardware.ICameraClient");
      return true;
    }
    
    private static class Proxy implements ICameraClient {
      public static ICameraClient sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.ICameraClient";
      }
    }
  }
  
  private static class Proxy implements ICameraClient {
    public static ICameraClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ICameraClient";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
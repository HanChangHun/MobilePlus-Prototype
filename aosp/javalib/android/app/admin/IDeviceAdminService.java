package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDeviceAdminService extends IInterface {
  public static class Default implements IDeviceAdminService {
    public IBinder asBinder() {
      return null;
    }
  }
  
  public static abstract class Stub extends Binder implements IDeviceAdminService {
    private static final String DESCRIPTOR = "android.app.admin.IDeviceAdminService";
    
    public Stub() {
      attachInterface(this, "android.app.admin.IDeviceAdminService");
    }
    
    public static IDeviceAdminService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.admin.IDeviceAdminService");
      return (iInterface != null && iInterface instanceof IDeviceAdminService) ? (IDeviceAdminService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IDeviceAdminService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return null;
    }
    
    public static boolean setDefaultImpl(IDeviceAdminService param1IDeviceAdminService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IDeviceAdminService != null) {
          Proxy.sDefaultImpl = param1IDeviceAdminService;
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
      param1Parcel2.writeString("android.app.admin.IDeviceAdminService");
      return true;
    }
    
    private static class Proxy implements IDeviceAdminService {
      public static IDeviceAdminService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.admin.IDeviceAdminService";
      }
    }
  }
  
  private static class Proxy implements IDeviceAdminService {
    public static IDeviceAdminService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.admin.IDeviceAdminService";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IDeviceAdminService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
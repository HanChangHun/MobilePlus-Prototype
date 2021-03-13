package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IDeviceAdminService {
  private static final String DESCRIPTOR = "android.app.admin.IDeviceAdminService";
  
  public Stub() {
    attachInterface(this, "android.app.admin.IDeviceAdminService");
  }
  
  public static IDeviceAdminService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.admin.IDeviceAdminService");
    return (iInterface != null && iInterface instanceof IDeviceAdminService) ? (IDeviceAdminService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDeviceAdminService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return null;
  }
  
  public static boolean setDefaultImpl(IDeviceAdminService paramIDeviceAdminService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDeviceAdminService != null) {
        Proxy.sDefaultImpl = paramIDeviceAdminService;
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
    if (paramInt1 != 1598968902)
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
    paramParcel2.writeString("android.app.admin.IDeviceAdminService");
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


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IDeviceAdminService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
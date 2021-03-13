package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IGeofenceHardwareMonitorCallback {
  private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardwareMonitorCallback";
  
  static final int TRANSACTION_onMonitoringSystemChange = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IGeofenceHardwareMonitorCallback");
  }
  
  public static IGeofenceHardwareMonitorCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IGeofenceHardwareMonitorCallback");
    return (iInterface != null && iInterface instanceof IGeofenceHardwareMonitorCallback) ? (IGeofenceHardwareMonitorCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IGeofenceHardwareMonitorCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onMonitoringSystemChange";
  }
  
  public static boolean setDefaultImpl(IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIGeofenceHardwareMonitorCallback != null) {
        Proxy.sDefaultImpl = paramIGeofenceHardwareMonitorCallback;
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
      paramParcel2.writeString("android.hardware.location.IGeofenceHardwareMonitorCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.location.IGeofenceHardwareMonitorCallback");
    if (paramParcel1.readInt() != 0) {
      GeofenceHardwareMonitorEvent geofenceHardwareMonitorEvent = (GeofenceHardwareMonitorEvent)GeofenceHardwareMonitorEvent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onMonitoringSystemChange((GeofenceHardwareMonitorEvent)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IGeofenceHardwareMonitorCallback {
    public static IGeofenceHardwareMonitorCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IGeofenceHardwareMonitorCallback";
    }
    
    public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent param2GeofenceHardwareMonitorEvent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareMonitorCallback");
        if (param2GeofenceHardwareMonitorEvent != null) {
          parcel.writeInt(1);
          param2GeofenceHardwareMonitorEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IGeofenceHardwareMonitorCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareMonitorCallback.Stub.getDefaultImpl().onMonitoringSystemChange(param2GeofenceHardwareMonitorEvent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardwareMonitorCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
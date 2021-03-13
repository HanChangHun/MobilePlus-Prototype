package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGeofenceHardwareMonitorCallback extends IInterface {
  void onMonitoringSystemChange(GeofenceHardwareMonitorEvent paramGeofenceHardwareMonitorEvent) throws RemoteException;
  
  public static class Default implements IGeofenceHardwareMonitorCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent param1GeofenceHardwareMonitorEvent) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IGeofenceHardwareMonitorCallback {
    private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardwareMonitorCallback";
    
    static final int TRANSACTION_onMonitoringSystemChange = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IGeofenceHardwareMonitorCallback");
    }
    
    public static IGeofenceHardwareMonitorCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IGeofenceHardwareMonitorCallback");
      return (iInterface != null && iInterface instanceof IGeofenceHardwareMonitorCallback) ? (IGeofenceHardwareMonitorCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IGeofenceHardwareMonitorCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onMonitoringSystemChange";
    }
    
    public static boolean setDefaultImpl(IGeofenceHardwareMonitorCallback param1IGeofenceHardwareMonitorCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IGeofenceHardwareMonitorCallback != null) {
          Proxy.sDefaultImpl = param1IGeofenceHardwareMonitorCallback;
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
        param1Parcel2.writeString("android.hardware.location.IGeofenceHardwareMonitorCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardwareMonitorCallback");
      if (param1Parcel1.readInt() != 0) {
        GeofenceHardwareMonitorEvent geofenceHardwareMonitorEvent = (GeofenceHardwareMonitorEvent)GeofenceHardwareMonitorEvent.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onMonitoringSystemChange((GeofenceHardwareMonitorEvent)param1Parcel1);
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
  
  private static class Proxy implements IGeofenceHardwareMonitorCallback {
    public static IGeofenceHardwareMonitorCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IGeofenceHardwareMonitorCallback";
    }
    
    public void onMonitoringSystemChange(GeofenceHardwareMonitorEvent param1GeofenceHardwareMonitorEvent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.location.IGeofenceHardwareMonitorCallback");
        if (param1GeofenceHardwareMonitorEvent != null) {
          parcel.writeInt(1);
          param1GeofenceHardwareMonitorEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IGeofenceHardwareMonitorCallback.Stub.getDefaultImpl() != null) {
          IGeofenceHardwareMonitorCallback.Stub.getDefaultImpl().onMonitoringSystemChange(param1GeofenceHardwareMonitorEvent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardwareMonitorCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
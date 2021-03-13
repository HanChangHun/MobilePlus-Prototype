package android.hardware.location;

import android.location.IFusedGeofenceHardware;
import android.location.IGpsGeofenceHardware;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGeofenceHardware extends IInterface {
  boolean addCircularFence(int paramInt, GeofenceHardwareRequestParcelable paramGeofenceHardwareRequestParcelable, IGeofenceHardwareCallback paramIGeofenceHardwareCallback) throws RemoteException;
  
  int[] getMonitoringTypes() throws RemoteException;
  
  int getStatusOfMonitoringType(int paramInt) throws RemoteException;
  
  boolean pauseGeofence(int paramInt1, int paramInt2) throws RemoteException;
  
  boolean registerForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) throws RemoteException;
  
  boolean removeGeofence(int paramInt1, int paramInt2) throws RemoteException;
  
  boolean resumeGeofence(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void setFusedGeofenceHardware(IFusedGeofenceHardware paramIFusedGeofenceHardware) throws RemoteException;
  
  void setGpsGeofenceHardware(IGpsGeofenceHardware paramIGpsGeofenceHardware) throws RemoteException;
  
  boolean unregisterForMonitorStateChangeCallback(int paramInt, IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback) throws RemoteException;
  
  public static class Default implements IGeofenceHardware {
    public boolean addCircularFence(int param1Int, GeofenceHardwareRequestParcelable param1GeofenceHardwareRequestParcelable, IGeofenceHardwareCallback param1IGeofenceHardwareCallback) throws RemoteException {
      return false;
    }
    
    public IBinder asBinder() {
      return null;
    }
    
    public int[] getMonitoringTypes() throws RemoteException {
      return null;
    }
    
    public int getStatusOfMonitoringType(int param1Int) throws RemoteException {
      return 0;
    }
    
    public boolean pauseGeofence(int param1Int1, int param1Int2) throws RemoteException {
      return false;
    }
    
    public boolean registerForMonitorStateChangeCallback(int param1Int, IGeofenceHardwareMonitorCallback param1IGeofenceHardwareMonitorCallback) throws RemoteException {
      return false;
    }
    
    public boolean removeGeofence(int param1Int1, int param1Int2) throws RemoteException {
      return false;
    }
    
    public boolean resumeGeofence(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      return false;
    }
    
    public void setFusedGeofenceHardware(IFusedGeofenceHardware param1IFusedGeofenceHardware) throws RemoteException {}
    
    public void setGpsGeofenceHardware(IGpsGeofenceHardware param1IGpsGeofenceHardware) throws RemoteException {}
    
    public boolean unregisterForMonitorStateChangeCallback(int param1Int, IGeofenceHardwareMonitorCallback param1IGeofenceHardwareMonitorCallback) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IGeofenceHardware {
    private static final String DESCRIPTOR = "android.hardware.location.IGeofenceHardware";
    
    static final int TRANSACTION_addCircularFence = 5;
    
    static final int TRANSACTION_getMonitoringTypes = 3;
    
    static final int TRANSACTION_getStatusOfMonitoringType = 4;
    
    static final int TRANSACTION_pauseGeofence = 7;
    
    static final int TRANSACTION_registerForMonitorStateChangeCallback = 9;
    
    static final int TRANSACTION_removeGeofence = 6;
    
    static final int TRANSACTION_resumeGeofence = 8;
    
    static final int TRANSACTION_setFusedGeofenceHardware = 2;
    
    static final int TRANSACTION_setGpsGeofenceHardware = 1;
    
    static final int TRANSACTION_unregisterForMonitorStateChangeCallback = 10;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IGeofenceHardware");
    }
    
    public static IGeofenceHardware asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IGeofenceHardware");
      return (iInterface != null && iInterface instanceof IGeofenceHardware) ? (IGeofenceHardware)iInterface : new Proxy(param1IBinder);
    }
    
    public static IGeofenceHardware getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 10:
          return "unregisterForMonitorStateChangeCallback";
        case 9:
          return "registerForMonitorStateChangeCallback";
        case 8:
          return "resumeGeofence";
        case 7:
          return "pauseGeofence";
        case 6:
          return "removeGeofence";
        case 5:
          return "addCircularFence";
        case 4:
          return "getStatusOfMonitoringType";
        case 3:
          return "getMonitoringTypes";
        case 2:
          return "setFusedGeofenceHardware";
        case 1:
          break;
      } 
      return "setGpsGeofenceHardware";
    }
    
    public static boolean setDefaultImpl(IGeofenceHardware param1IGeofenceHardware) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IGeofenceHardware != null) {
          Proxy.sDefaultImpl = param1IGeofenceHardware;
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
      if (param1Int1 != 1598968902) {
        boolean bool2;
        int j;
        boolean bool1;
        int i;
        int[] arrayOfInt;
        GeofenceHardwareRequestParcelable geofenceHardwareRequestParcelable;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 10:
            param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
            bool2 = unregisterForMonitorStateChangeCallback(param1Parcel1.readInt(), IGeofenceHardwareMonitorCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
            bool2 = registerForMonitorStateChangeCallback(param1Parcel1.readInt(), IGeofenceHardwareMonitorCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
            bool2 = resumeGeofence(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
            bool2 = pauseGeofence(param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
            bool2 = removeGeofence(param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
            j = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              geofenceHardwareRequestParcelable = (GeofenceHardwareRequestParcelable)GeofenceHardwareRequestParcelable.CREATOR.createFromParcel(param1Parcel1);
            } else {
              geofenceHardwareRequestParcelable = null;
            } 
            bool1 = addCircularFence(j, geofenceHardwareRequestParcelable, IGeofenceHardwareCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
            i = getStatusOfMonitoringType(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.location.IGeofenceHardware");
            arrayOfInt = getMonitoringTypes();
            param1Parcel2.writeNoException();
            param1Parcel2.writeIntArray(arrayOfInt);
            return true;
          case 2:
            arrayOfInt.enforceInterface("android.hardware.location.IGeofenceHardware");
            setFusedGeofenceHardware(IFusedGeofenceHardware.Stub.asInterface(arrayOfInt.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        arrayOfInt.enforceInterface("android.hardware.location.IGeofenceHardware");
        setGpsGeofenceHardware(IGpsGeofenceHardware.Stub.asInterface(arrayOfInt.readStrongBinder()));
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.hardware.location.IGeofenceHardware");
      return true;
    }
    
    private static class Proxy implements IGeofenceHardware {
      public static IGeofenceHardware sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public boolean addCircularFence(int param2Int, GeofenceHardwareRequestParcelable param2GeofenceHardwareRequestParcelable, IGeofenceHardwareCallback param2IGeofenceHardwareCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          parcel1.writeInt(param2Int);
          boolean bool = true;
          if (param2GeofenceHardwareRequestParcelable != null) {
            parcel1.writeInt(1);
            param2GeofenceHardwareRequestParcelable.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2IGeofenceHardwareCallback != null) {
            iBinder = param2IGeofenceHardwareCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
            bool = IGeofenceHardware.Stub.getDefaultImpl().addCircularFence(param2Int, param2GeofenceHardwareRequestParcelable, param2IGeofenceHardwareCallback);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.location.IGeofenceHardware";
      }
      
      public int[] getMonitoringTypes() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null)
            return IGeofenceHardware.Stub.getDefaultImpl().getMonitoringTypes(); 
          parcel2.readException();
          return parcel2.createIntArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getStatusOfMonitoringType(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
            param2Int = IGeofenceHardware.Stub.getDefaultImpl().getStatusOfMonitoringType(param2Int);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean pauseGeofence(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(7, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
            bool = IGeofenceHardware.Stub.getDefaultImpl().pauseGeofence(param2Int1, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean registerForMonitorStateChangeCallback(int param2Int, IGeofenceHardwareMonitorCallback param2IGeofenceHardwareMonitorCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          parcel1.writeInt(param2Int);
          if (param2IGeofenceHardwareMonitorCallback != null) {
            iBinder = param2IGeofenceHardwareMonitorCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(9, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
            bool = IGeofenceHardware.Stub.getDefaultImpl().registerForMonitorStateChangeCallback(param2Int, param2IGeofenceHardwareMonitorCallback);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean removeGeofence(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(6, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
            bool = IGeofenceHardware.Stub.getDefaultImpl().removeGeofence(param2Int1, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean resumeGeofence(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(8, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
            bool = IGeofenceHardware.Stub.getDefaultImpl().resumeGeofence(param2Int1, param2Int2, param2Int3);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setFusedGeofenceHardware(IFusedGeofenceHardware param2IFusedGeofenceHardware) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          if (param2IFusedGeofenceHardware != null) {
            iBinder = param2IFusedGeofenceHardware.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
            IGeofenceHardware.Stub.getDefaultImpl().setFusedGeofenceHardware(param2IFusedGeofenceHardware);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setGpsGeofenceHardware(IGpsGeofenceHardware param2IGpsGeofenceHardware) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          if (param2IGpsGeofenceHardware != null) {
            iBinder = param2IGpsGeofenceHardware.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
            IGeofenceHardware.Stub.getDefaultImpl().setGpsGeofenceHardware(param2IGpsGeofenceHardware);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean unregisterForMonitorStateChangeCallback(int param2Int, IGeofenceHardwareMonitorCallback param2IGeofenceHardwareMonitorCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
          parcel1.writeInt(param2Int);
          if (param2IGeofenceHardwareMonitorCallback != null) {
            iBinder = param2IGeofenceHardwareMonitorCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(10, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
            bool = IGeofenceHardware.Stub.getDefaultImpl().unregisterForMonitorStateChangeCallback(param2Int, param2IGeofenceHardwareMonitorCallback);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IGeofenceHardware {
    public static IGeofenceHardware sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public boolean addCircularFence(int param1Int, GeofenceHardwareRequestParcelable param1GeofenceHardwareRequestParcelable, IGeofenceHardwareCallback param1IGeofenceHardwareCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param1Int);
        boolean bool = true;
        if (param1GeofenceHardwareRequestParcelable != null) {
          parcel1.writeInt(1);
          param1GeofenceHardwareRequestParcelable.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IGeofenceHardwareCallback != null) {
          iBinder = param1IGeofenceHardwareCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().addCircularFence(param1Int, param1GeofenceHardwareRequestParcelable, param1IGeofenceHardwareCallback);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IGeofenceHardware";
    }
    
    public int[] getMonitoringTypes() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null)
          return IGeofenceHardware.Stub.getDefaultImpl().getMonitoringTypes(); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getStatusOfMonitoringType(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          param1Int = IGeofenceHardware.Stub.getDefaultImpl().getStatusOfMonitoringType(param1Int);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean pauseGeofence(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().pauseGeofence(param1Int1, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean registerForMonitorStateChangeCallback(int param1Int, IGeofenceHardwareMonitorCallback param1IGeofenceHardwareMonitorCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param1Int);
        if (param1IGeofenceHardwareMonitorCallback != null) {
          iBinder = param1IGeofenceHardwareMonitorCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().registerForMonitorStateChangeCallback(param1Int, param1IGeofenceHardwareMonitorCallback);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeGeofence(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().removeGeofence(param1Int1, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean resumeGeofence(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().resumeGeofence(param1Int1, param1Int2, param1Int3);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setFusedGeofenceHardware(IFusedGeofenceHardware param1IFusedGeofenceHardware) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        if (param1IFusedGeofenceHardware != null) {
          iBinder = param1IFusedGeofenceHardware.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          IGeofenceHardware.Stub.getDefaultImpl().setFusedGeofenceHardware(param1IFusedGeofenceHardware);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setGpsGeofenceHardware(IGpsGeofenceHardware param1IGpsGeofenceHardware) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        if (param1IGpsGeofenceHardware != null) {
          iBinder = param1IGpsGeofenceHardware.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          IGeofenceHardware.Stub.getDefaultImpl().setGpsGeofenceHardware(param1IGpsGeofenceHardware);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean unregisterForMonitorStateChangeCallback(int param1Int, IGeofenceHardwareMonitorCallback param1IGeofenceHardwareMonitorCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IGeofenceHardware");
        parcel1.writeInt(param1Int);
        if (param1IGeofenceHardwareMonitorCallback != null) {
          iBinder = param1IGeofenceHardwareMonitorCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(10, parcel1, parcel2, 0) && IGeofenceHardware.Stub.getDefaultImpl() != null) {
          bool = IGeofenceHardware.Stub.getDefaultImpl().unregisterForMonitorStateChangeCallback(param1Int, param1IGeofenceHardwareMonitorCallback);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IGeofenceHardware.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
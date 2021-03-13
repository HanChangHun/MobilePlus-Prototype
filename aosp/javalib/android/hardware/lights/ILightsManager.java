package android.hardware.lights;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public interface ILightsManager extends IInterface {
  void closeSession(IBinder paramIBinder) throws RemoteException;
  
  LightState getLightState(int paramInt) throws RemoteException;
  
  List<Light> getLights() throws RemoteException;
  
  void openSession(IBinder paramIBinder) throws RemoteException;
  
  void setLightStates(IBinder paramIBinder, int[] paramArrayOfint, LightState[] paramArrayOfLightState) throws RemoteException;
  
  public static class Default implements ILightsManager {
    public IBinder asBinder() {
      return null;
    }
    
    public void closeSession(IBinder param1IBinder) throws RemoteException {}
    
    public LightState getLightState(int param1Int) throws RemoteException {
      return null;
    }
    
    public List<Light> getLights() throws RemoteException {
      return null;
    }
    
    public void openSession(IBinder param1IBinder) throws RemoteException {}
    
    public void setLightStates(IBinder param1IBinder, int[] param1ArrayOfint, LightState[] param1ArrayOfLightState) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ILightsManager {
    private static final String DESCRIPTOR = "android.hardware.lights.ILightsManager";
    
    static final int TRANSACTION_closeSession = 4;
    
    static final int TRANSACTION_getLightState = 2;
    
    static final int TRANSACTION_getLights = 1;
    
    static final int TRANSACTION_openSession = 3;
    
    static final int TRANSACTION_setLightStates = 5;
    
    public Stub() {
      attachInterface(this, "android.hardware.lights.ILightsManager");
    }
    
    public static ILightsManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.lights.ILightsManager");
      return (iInterface != null && iInterface instanceof ILightsManager) ? (ILightsManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static ILightsManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "setLightStates") : "closeSession") : "openSession") : "getLightState") : "getLights";
    }
    
    public static boolean setDefaultImpl(ILightsManager param1ILightsManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ILightsManager != null) {
          Proxy.sDefaultImpl = param1ILightsManager;
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
      LightState lightState;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.hardware.lights.ILightsManager");
                return true;
              } 
              param1Parcel1.enforceInterface("android.hardware.lights.ILightsManager");
              setLightStates(param1Parcel1.readStrongBinder(), param1Parcel1.createIntArray(), (LightState[])param1Parcel1.createTypedArray(LightState.CREATOR));
              param1Parcel2.writeNoException();
              return true;
            } 
            param1Parcel1.enforceInterface("android.hardware.lights.ILightsManager");
            closeSession(param1Parcel1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          } 
          param1Parcel1.enforceInterface("android.hardware.lights.ILightsManager");
          openSession(param1Parcel1.readStrongBinder());
          param1Parcel2.writeNoException();
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.lights.ILightsManager");
        lightState = getLightState(param1Parcel1.readInt());
        param1Parcel2.writeNoException();
        if (lightState != null) {
          param1Parcel2.writeInt(1);
          lightState.writeToParcel(param1Parcel2, 1);
        } else {
          param1Parcel2.writeInt(0);
        } 
        return true;
      } 
      lightState.enforceInterface("android.hardware.lights.ILightsManager");
      List<Light> list = getLights();
      param1Parcel2.writeNoException();
      param1Parcel2.writeTypedList(list);
      return true;
    }
    
    private static class Proxy implements ILightsManager {
      public static ILightsManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void closeSession(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
            ILightsManager.Stub.getDefaultImpl().closeSession(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.lights.ILightsManager";
      }
      
      public LightState getLightState(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          LightState lightState;
          parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
            lightState = ILightsManager.Stub.getDefaultImpl().getLightState(param2Int);
            return lightState;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            lightState = (LightState)LightState.CREATOR.createFromParcel(parcel2);
          } else {
            lightState = null;
          } 
          return lightState;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<Light> getLights() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null)
            return ILightsManager.Stub.getDefaultImpl().getLights(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(Light.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void openSession(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
            ILightsManager.Stub.getDefaultImpl().openSession(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setLightStates(IBinder param2IBinder, int[] param2ArrayOfint, LightState[] param2ArrayOfLightState) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeIntArray(param2ArrayOfint);
          parcel1.writeTypedArray((Parcelable[])param2ArrayOfLightState, 0);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
            ILightsManager.Stub.getDefaultImpl().setLightStates(param2IBinder, param2ArrayOfint, param2ArrayOfLightState);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ILightsManager {
    public static ILightsManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void closeSession(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
          ILightsManager.Stub.getDefaultImpl().closeSession(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.lights.ILightsManager";
    }
    
    public LightState getLightState(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        LightState lightState;
        parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
          lightState = ILightsManager.Stub.getDefaultImpl().getLightState(param1Int);
          return lightState;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          lightState = (LightState)LightState.CREATOR.createFromParcel(parcel2);
        } else {
          lightState = null;
        } 
        return lightState;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<Light> getLights() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null)
          return ILightsManager.Stub.getDefaultImpl().getLights(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(Light.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void openSession(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
          ILightsManager.Stub.getDefaultImpl().openSession(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setLightStates(IBinder param1IBinder, int[] param1ArrayOfint, LightState[] param1ArrayOfLightState) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeIntArray(param1ArrayOfint);
        parcel1.writeTypedArray((Parcelable[])param1ArrayOfLightState, 0);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
          ILightsManager.Stub.getDefaultImpl().setLightStates(param1IBinder, param1ArrayOfint, param1ArrayOfLightState);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/ILightsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
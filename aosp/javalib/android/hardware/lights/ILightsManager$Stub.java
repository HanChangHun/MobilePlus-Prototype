package android.hardware.lights;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements ILightsManager {
  private static final String DESCRIPTOR = "android.hardware.lights.ILightsManager";
  
  static final int TRANSACTION_closeSession = 4;
  
  static final int TRANSACTION_getLightState = 2;
  
  static final int TRANSACTION_getLights = 1;
  
  static final int TRANSACTION_openSession = 3;
  
  static final int TRANSACTION_setLightStates = 5;
  
  public Stub() {
    attachInterface(this, "android.hardware.lights.ILightsManager");
  }
  
  public static ILightsManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.lights.ILightsManager");
    return (iInterface != null && iInterface instanceof ILightsManager) ? (ILightsManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static ILightsManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "setLightStates") : "closeSession") : "openSession") : "getLightState") : "getLights";
  }
  
  public static boolean setDefaultImpl(ILightsManager paramILightsManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramILightsManager != null) {
        Proxy.sDefaultImpl = paramILightsManager;
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
    LightState lightState;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.hardware.lights.ILightsManager");
              return true;
            } 
            paramParcel1.enforceInterface("android.hardware.lights.ILightsManager");
            setLightStates(paramParcel1.readStrongBinder(), paramParcel1.createIntArray(), (LightState[])paramParcel1.createTypedArray(LightState.CREATOR));
            paramParcel2.writeNoException();
            return true;
          } 
          paramParcel1.enforceInterface("android.hardware.lights.ILightsManager");
          closeSession(paramParcel1.readStrongBinder());
          paramParcel2.writeNoException();
          return true;
        } 
        paramParcel1.enforceInterface("android.hardware.lights.ILightsManager");
        openSession(paramParcel1.readStrongBinder());
        paramParcel2.writeNoException();
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.lights.ILightsManager");
      lightState = getLightState(paramParcel1.readInt());
      paramParcel2.writeNoException();
      if (lightState != null) {
        paramParcel2.writeInt(1);
        lightState.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      return true;
    } 
    lightState.enforceInterface("android.hardware.lights.ILightsManager");
    List<Light> list = getLights();
    paramParcel2.writeNoException();
    paramParcel2.writeTypedList(list);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/ILightsManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
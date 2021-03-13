package android.hardware.lights;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

class Proxy implements ILightsManager {
  public static ILightsManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void closeSession(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
        ILightsManager.Stub.getDefaultImpl().closeSession(paramIBinder);
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
  
  public LightState getLightState(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      LightState lightState;
      parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
        lightState = ILightsManager.Stub.getDefaultImpl().getLightState(paramInt);
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
  
  public void openSession(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
        ILightsManager.Stub.getDefaultImpl().openSession(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setLightStates(IBinder paramIBinder, int[] paramArrayOfint, LightState[] paramArrayOfLightState) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.lights.ILightsManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeIntArray(paramArrayOfint);
      parcel1.writeTypedArray((Parcelable[])paramArrayOfLightState, 0);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ILightsManager.Stub.getDefaultImpl() != null) {
        ILightsManager.Stub.getDefaultImpl().setLightStates(paramIBinder, paramArrayOfint, paramArrayOfLightState);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/ILightsManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
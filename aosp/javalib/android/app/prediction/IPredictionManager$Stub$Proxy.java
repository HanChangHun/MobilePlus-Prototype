package android.app.prediction;

import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPredictionManager {
  public static IPredictionManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void createPredictionSession(AppPredictionContext paramAppPredictionContext, AppPredictionSessionId paramAppPredictionSessionId, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
      if (paramAppPredictionContext != null) {
        parcel1.writeInt(1);
        paramAppPredictionContext.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramAppPredictionSessionId != null) {
        parcel1.writeInt(1);
        paramAppPredictionSessionId.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
        IPredictionManager.Stub.getDefaultImpl().createPredictionSession(paramAppPredictionContext, paramAppPredictionSessionId, paramIBinder);
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
    return "android.app.prediction.IPredictionManager";
  }
  
  public void notifyAppTargetEvent(AppPredictionSessionId paramAppPredictionSessionId, AppTargetEvent paramAppTargetEvent) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
      if (paramAppPredictionSessionId != null) {
        parcel1.writeInt(1);
        paramAppPredictionSessionId.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramAppTargetEvent != null) {
        parcel1.writeInt(1);
        paramAppTargetEvent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
        IPredictionManager.Stub.getDefaultImpl().notifyAppTargetEvent(paramAppPredictionSessionId, paramAppTargetEvent);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyLaunchLocationShown(AppPredictionSessionId paramAppPredictionSessionId, String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
      if (paramAppPredictionSessionId != null) {
        parcel1.writeInt(1);
        paramAppPredictionSessionId.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
        IPredictionManager.Stub.getDefaultImpl().notifyLaunchLocationShown(paramAppPredictionSessionId, paramString, paramParceledListSlice);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onDestroyPredictionSession(AppPredictionSessionId paramAppPredictionSessionId) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
      if (paramAppPredictionSessionId != null) {
        parcel1.writeInt(1);
        paramAppPredictionSessionId.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
        IPredictionManager.Stub.getDefaultImpl().onDestroyPredictionSession(paramAppPredictionSessionId);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerPredictionUpdates(AppPredictionSessionId paramAppPredictionSessionId, IPredictionCallback paramIPredictionCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
      if (paramAppPredictionSessionId != null) {
        parcel1.writeInt(1);
        paramAppPredictionSessionId.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIPredictionCallback != null) {
        iBinder = paramIPredictionCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
        IPredictionManager.Stub.getDefaultImpl().registerPredictionUpdates(paramAppPredictionSessionId, paramIPredictionCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestPredictionUpdate(AppPredictionSessionId paramAppPredictionSessionId) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
      if (paramAppPredictionSessionId != null) {
        parcel1.writeInt(1);
        paramAppPredictionSessionId.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
        IPredictionManager.Stub.getDefaultImpl().requestPredictionUpdate(paramAppPredictionSessionId);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sortAppTargets(AppPredictionSessionId paramAppPredictionSessionId, ParceledListSlice paramParceledListSlice, IPredictionCallback paramIPredictionCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
      if (paramAppPredictionSessionId != null) {
        parcel1.writeInt(1);
        paramAppPredictionSessionId.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIPredictionCallback != null) {
        iBinder = paramIPredictionCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
        IPredictionManager.Stub.getDefaultImpl().sortAppTargets(paramAppPredictionSessionId, paramParceledListSlice, paramIPredictionCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterPredictionUpdates(AppPredictionSessionId paramAppPredictionSessionId, IPredictionCallback paramIPredictionCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.prediction.IPredictionManager");
      if (paramAppPredictionSessionId != null) {
        parcel1.writeInt(1);
        paramAppPredictionSessionId.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIPredictionCallback != null) {
        iBinder = paramIPredictionCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPredictionManager.Stub.getDefaultImpl() != null) {
        IPredictionManager.Stub.getDefaultImpl().unregisterPredictionUpdates(paramAppPredictionSessionId, paramIPredictionCallback);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/IPredictionManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
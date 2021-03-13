package android.hardware.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IContextHubService {
  public static IContextHubService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public IContextHubClient createClient(int paramInt, IContextHubClientCallback paramIContextHubClientCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (paramIContextHubClientCallback != null) {
        iBinder = paramIContextHubClientCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
        return IContextHubService.Stub.getDefaultImpl().createClient(paramInt, paramIContextHubClientCallback); 
      parcel2.readException();
      return IContextHubClient.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IContextHubClient createPendingIntentClient(int paramInt, PendingIntent paramPendingIntent, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (paramPendingIntent != null) {
        parcel1.writeInt(1);
        paramPendingIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
        return IContextHubService.Stub.getDefaultImpl().createPendingIntentClient(paramInt, paramPendingIntent, paramLong); 
      parcel2.readException();
      return IContextHubClient.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void disableNanoApp(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (paramIContextHubTransactionCallback != null) {
        iBinder = paramIContextHubTransactionCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        IContextHubService.Stub.getDefaultImpl().disableNanoApp(paramInt, paramIContextHubTransactionCallback, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enableNanoApp(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (paramIContextHubTransactionCallback != null) {
        iBinder = paramIContextHubTransactionCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        IContextHubService.Stub.getDefaultImpl().enableNanoApp(paramInt, paramIContextHubTransactionCallback, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int[] findNanoAppOnHub(int paramInt, NanoAppFilter paramNanoAppFilter) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (paramNanoAppFilter != null) {
        parcel1.writeInt(1);
        paramNanoAppFilter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
        return IContextHubService.Stub.getDefaultImpl().findNanoAppOnHub(paramInt, paramNanoAppFilter); 
      parcel2.readException();
      return parcel2.createIntArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int[] getContextHubHandles() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
        return IContextHubService.Stub.getDefaultImpl().getContextHubHandles(); 
      parcel2.readException();
      return parcel2.createIntArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ContextHubInfo getContextHubInfo(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ContextHubInfo contextHubInfo;
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        contextHubInfo = IContextHubService.Stub.getDefaultImpl().getContextHubInfo(paramInt);
        return contextHubInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        contextHubInfo = (ContextHubInfo)ContextHubInfo.CREATOR.createFromParcel(parcel2);
      } else {
        contextHubInfo = null;
      } 
      return contextHubInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ContextHubInfo> getContextHubs() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
        return IContextHubService.Stub.getDefaultImpl().getContextHubs(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ContextHubInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.location.IContextHubService";
  }
  
  public NanoAppInstanceInfo getNanoAppInstanceInfo(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      NanoAppInstanceInfo nanoAppInstanceInfo;
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        nanoAppInstanceInfo = IContextHubService.Stub.getDefaultImpl().getNanoAppInstanceInfo(paramInt);
        return nanoAppInstanceInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        nanoAppInstanceInfo = (NanoAppInstanceInfo)NanoAppInstanceInfo.CREATOR.createFromParcel(parcel2);
      } else {
        nanoAppInstanceInfo = null;
      } 
      return nanoAppInstanceInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int loadNanoApp(int paramInt, NanoApp paramNanoApp) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (paramNanoApp != null) {
        parcel1.writeInt(1);
        paramNanoApp.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        paramInt = IContextHubService.Stub.getDefaultImpl().loadNanoApp(paramInt, paramNanoApp);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void loadNanoAppOnHub(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, NanoAppBinary paramNanoAppBinary) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (paramIContextHubTransactionCallback != null) {
        iBinder = paramIContextHubTransactionCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramNanoAppBinary != null) {
        parcel1.writeInt(1);
        paramNanoAppBinary.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        IContextHubService.Stub.getDefaultImpl().loadNanoAppOnHub(paramInt, paramIContextHubTransactionCallback, paramNanoAppBinary);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void queryNanoApps(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (paramIContextHubTransactionCallback != null) {
        iBinder = paramIContextHubTransactionCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        IContextHubService.Stub.getDefaultImpl().queryNanoApps(paramInt, paramIContextHubTransactionCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int registerCallback(IContextHubCallback paramIContextHubCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      if (paramIContextHubCallback != null) {
        iBinder = paramIContextHubCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
        return IContextHubService.Stub.getDefaultImpl().registerCallback(paramIContextHubCallback); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int sendMessage(int paramInt1, int paramInt2, ContextHubMessage paramContextHubMessage) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramContextHubMessage != null) {
        parcel1.writeInt(1);
        paramContextHubMessage.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        paramInt1 = IContextHubService.Stub.getDefaultImpl().sendMessage(paramInt1, paramInt2, paramContextHubMessage);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int unloadNanoApp(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        paramInt = IContextHubService.Stub.getDefaultImpl().unloadNanoApp(paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unloadNanoAppFromHub(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
      parcel1.writeInt(paramInt);
      if (paramIContextHubTransactionCallback != null) {
        iBinder = paramIContextHubTransactionCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
        IContextHubService.Stub.getDefaultImpl().unloadNanoAppFromHub(paramInt, paramIContextHubTransactionCallback, paramLong);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
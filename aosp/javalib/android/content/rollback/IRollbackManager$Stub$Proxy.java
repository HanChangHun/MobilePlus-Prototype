package android.content.rollback;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IRollbackManager {
  public static IRollbackManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void blockRollbackManager(long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
        IRollbackManager.Stub.getDefaultImpl().blockRollbackManager(paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void commitRollback(int paramInt, ParceledListSlice paramParceledListSlice, String paramString, IntentSender paramIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
      parcel1.writeInt(paramInt);
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (paramIntentSender != null) {
        parcel1.writeInt(1);
        paramIntentSender.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
        IRollbackManager.Stub.getDefaultImpl().commitRollback(paramInt, paramParceledListSlice, paramString, paramIntentSender);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void expireRollbackForPackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
        IRollbackManager.Stub.getDefaultImpl().expireRollbackForPackage(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getAvailableRollbacks() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IRollbackManager.Stub.getDefaultImpl().getAvailableRollbacks();
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.rollback.IRollbackManager";
  }
  
  public ParceledListSlice getRecentlyCommittedRollbacks() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IRollbackManager.Stub.getDefaultImpl().getRecentlyCommittedRollbacks();
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyStagedApkSession(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
        IRollbackManager.Stub.getDefaultImpl().notifyStagedApkSession(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int notifyStagedSession(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
        paramInt = IRollbackManager.Stub.getDefaultImpl().notifyStagedSession(paramInt);
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
  
  public void reloadPersistedData() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
        IRollbackManager.Stub.getDefaultImpl().reloadPersistedData();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void snapshotAndRestoreUserData(String paramString1, int[] paramArrayOfint, int paramInt1, long paramLong, String paramString2, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeIntArray(paramArrayOfint);
          try {
            parcel1.writeInt(paramInt1);
            try {
              parcel1.writeLong(paramLong);
              parcel1.writeString(paramString2);
              parcel1.writeInt(paramInt2);
              if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
                IRollbackManager.Stub.getDefaultImpl().snapshotAndRestoreUserData(paramString1, paramArrayOfint, paramInt1, paramLong, paramString2, paramInt2);
                parcel2.recycle();
                parcel1.recycle();
                return;
              } 
              parcel2.readException();
              parcel2.recycle();
              parcel1.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/rollback/IRollbackManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
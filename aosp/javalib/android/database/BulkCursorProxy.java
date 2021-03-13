package android.database;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class BulkCursorProxy implements IBulkCursor {
  private Bundle mExtras;
  
  private IBinder mRemote;
  
  public BulkCursorProxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
    this.mExtras = null;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void close() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IBulkCursor");
      this.mRemote.transact(7, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public void deactivate() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IBulkCursor");
      this.mRemote.transact(2, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public Bundle getExtras() throws RemoteException {
    if (this.mExtras == null) {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IBulkCursor");
        this.mRemote.transact(5, parcel1, parcel2, 0);
        DatabaseUtils.readExceptionFromParcel(parcel2);
        this.mExtras = parcel2.readBundle();
      } finally {
        parcel1.recycle();
        parcel2.recycle();
      } 
    } 
    return this.mExtras;
  }
  
  public CursorWindow getWindow(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IBulkCursor");
      parcel1.writeInt(paramInt);
      this.mRemote.transact(1, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      CursorWindow cursorWindow = null;
      if (parcel2.readInt() == 1)
        cursorWindow = CursorWindow.newFromParcel(parcel2); 
      return cursorWindow;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public void onMove(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IBulkCursor");
      parcel1.writeInt(paramInt);
      this.mRemote.transact(4, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public int requery(IContentObserver paramIContentObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      int i;
      parcel1.writeInterfaceToken("android.content.IBulkCursor");
      parcel1.writeStrongInterface(paramIContentObserver);
      boolean bool = this.mRemote.transact(3, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      if (!bool) {
        i = -1;
      } else {
        i = parcel2.readInt();
        this.mExtras = parcel2.readBundle();
      } 
      return i;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public Bundle respond(Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IBulkCursor");
      parcel1.writeBundle(paramBundle);
      this.mRemote.transact(6, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      paramBundle = parcel2.readBundle();
      return paramBundle;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/BulkCursorProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
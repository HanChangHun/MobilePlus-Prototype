package android.database;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class BulkCursorNative extends Binder implements IBulkCursor {
  public BulkCursorNative() {
    attachInterface(this, "android.content.IBulkCursor");
  }
  
  public static IBulkCursor asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IBulkCursor iBulkCursor = (IBulkCursor)paramIBinder.queryLocalInterface("android.content.IBulkCursor");
    return (iBulkCursor != null) ? iBulkCursor : new BulkCursorProxy(paramIBinder);
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    Bundle bundle;
    switch (paramInt1) {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 7:
        try {
          paramParcel1.enforceInterface("android.content.IBulkCursor");
          close();
          paramParcel2.writeNoException();
          return true;
        } catch (Exception exception) {
          DatabaseUtils.writeExceptionToParcel(paramParcel2, exception);
          return true;
        } 
      case 6:
        exception.enforceInterface("android.content.IBulkCursor");
        bundle = respond(exception.readBundle());
        paramParcel2.writeNoException();
        paramParcel2.writeBundle(bundle);
        return true;
      case 5:
        bundle.enforceInterface("android.content.IBulkCursor");
        bundle = getExtras();
        paramParcel2.writeNoException();
        paramParcel2.writeBundle(bundle);
        return true;
      case 4:
        bundle.enforceInterface("android.content.IBulkCursor");
        onMove(bundle.readInt());
        paramParcel2.writeNoException();
        return true;
      case 3:
        bundle.enforceInterface("android.content.IBulkCursor");
        paramInt1 = requery(IContentObserver.Stub.asInterface(bundle.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        paramParcel2.writeBundle(getExtras());
        return true;
      case 2:
        bundle.enforceInterface("android.content.IBulkCursor");
        deactivate();
        paramParcel2.writeNoException();
        return true;
      case 1:
        break;
    } 
    bundle.enforceInterface("android.content.IBulkCursor");
    CursorWindow cursorWindow = getWindow(bundle.readInt());
    paramParcel2.writeNoException();
    if (cursorWindow == null) {
      paramParcel2.writeInt(0);
    } else {
      paramParcel2.writeInt(1);
      cursorWindow.writeToParcel(paramParcel2, 1);
    } 
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/BulkCursorNative.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
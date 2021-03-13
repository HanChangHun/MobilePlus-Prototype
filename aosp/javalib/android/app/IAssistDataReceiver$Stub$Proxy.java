package android.app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IAssistDataReceiver {
  public static IAssistDataReceiver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IAssistDataReceiver";
  }
  
  public void onHandleAssistData(Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IAssistDataReceiver");
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IAssistDataReceiver.Stub.getDefaultImpl() != null) {
        IAssistDataReceiver.Stub.getDefaultImpl().onHandleAssistData(paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onHandleAssistScreenshot(Bitmap paramBitmap) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IAssistDataReceiver");
      if (paramBitmap != null) {
        parcel.writeInt(1);
        paramBitmap.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IAssistDataReceiver.Stub.getDefaultImpl() != null) {
        IAssistDataReceiver.Stub.getDefaultImpl().onHandleAssistScreenshot(paramBitmap);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAssistDataReceiver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IWallpaperManagerCallback {
  public static IWallpaperManagerCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IWallpaperManagerCallback";
  }
  
  public void onWallpaperChanged() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IWallpaperManagerCallback");
      if (!this.mRemote.transact(1, parcel, null, 1) && IWallpaperManagerCallback.Stub.getDefaultImpl() != null) {
        IWallpaperManagerCallback.Stub.getDefaultImpl().onWallpaperChanged();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onWallpaperColorsChanged(WallpaperColors paramWallpaperColors, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IWallpaperManagerCallback");
      if (paramWallpaperColors != null) {
        parcel.writeInt(1);
        paramWallpaperColors.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(2, parcel, null, 1) && IWallpaperManagerCallback.Stub.getDefaultImpl() != null) {
        IWallpaperManagerCallback.Stub.getDefaultImpl().onWallpaperColorsChanged(paramWallpaperColors, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IWallpaperManagerCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
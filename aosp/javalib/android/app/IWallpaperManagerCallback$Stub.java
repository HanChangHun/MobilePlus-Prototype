package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IWallpaperManagerCallback {
  private static final String DESCRIPTOR = "android.app.IWallpaperManagerCallback";
  
  static final int TRANSACTION_onWallpaperChanged = 1;
  
  static final int TRANSACTION_onWallpaperColorsChanged = 2;
  
  public Stub() {
    attachInterface(this, "android.app.IWallpaperManagerCallback");
  }
  
  public static IWallpaperManagerCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IWallpaperManagerCallback");
    return (iInterface != null && iInterface instanceof IWallpaperManagerCallback) ? (IWallpaperManagerCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IWallpaperManagerCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onWallpaperColorsChanged") : "onWallpaperChanged";
  }
  
  public static boolean setDefaultImpl(IWallpaperManagerCallback paramIWallpaperManagerCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIWallpaperManagerCallback != null) {
        Proxy.sDefaultImpl = paramIWallpaperManagerCallback;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.app.IWallpaperManagerCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IWallpaperManagerCallback");
      if (paramParcel1.readInt() != 0) {
        WallpaperColors wallpaperColors = (WallpaperColors)WallpaperColors.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel2 = null;
      } 
      onWallpaperColorsChanged((WallpaperColors)paramParcel2, paramParcel1.readInt(), paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IWallpaperManagerCallback");
    onWallpaperChanged();
    return true;
  }
  
  private static class Proxy implements IWallpaperManagerCallback {
    public static IWallpaperManagerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
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
    
    public void onWallpaperColorsChanged(WallpaperColors param2WallpaperColors, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IWallpaperManagerCallback");
        if (param2WallpaperColors != null) {
          parcel.writeInt(1);
          param2WallpaperColors.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IWallpaperManagerCallback.Stub.getDefaultImpl() != null) {
          IWallpaperManagerCallback.Stub.getDefaultImpl().onWallpaperColorsChanged(param2WallpaperColors, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IWallpaperManagerCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.gsi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IImageService {
  public static IImageService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean backingImageExists(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(5, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        bool = IImageService.Stub.getDefaultImpl().backingImageExists(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void createBackingImage(String paramString, long paramLong, int paramInt, IProgressCallback paramIProgressCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      parcel1.writeString(paramString);
      parcel1.writeLong(paramLong);
      parcel1.writeInt(paramInt);
      if (paramIProgressCallback != null) {
        iBinder = paramIProgressCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        IImageService.Stub.getDefaultImpl().createBackingImage(paramString, paramLong, paramInt, paramIProgressCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deleteBackingImage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        IImageService.Stub.getDefaultImpl().deleteBackingImage(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getAllBackingImages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null)
        return IImageService.Stub.getDefaultImpl().getAllBackingImages(); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getAvbPublicKey(String paramString, AvbPublicKey paramAvbPublicKey) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null)
        return IImageService.Stub.getDefaultImpl().getAvbPublicKey(paramString, paramAvbPublicKey); 
      parcel2.readException();
      int i = parcel2.readInt();
      if (parcel2.readInt() != 0)
        paramAvbPublicKey.readFromParcel(parcel2); 
      return i;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.gsi.IImageService";
  }
  
  public String getMappedImageDevice(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        paramString = IImageService.Stub.getDefaultImpl().getMappedImageDevice(paramString);
        return paramString;
      } 
      parcel2.readException();
      paramString = parcel2.readString();
      return paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isImageMapped(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(6, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        bool = IImageService.Stub.getDefaultImpl().isImageMapped(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void mapImageDevice(String paramString, int paramInt, MappedImage paramMappedImage) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        IImageService.Stub.getDefaultImpl().mapImageDevice(paramString, paramInt, paramMappedImage);
        return;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0)
        paramMappedImage.readFromParcel(parcel2); 
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeAllImages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        IImageService.Stub.getDefaultImpl().removeAllImages();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeDisabledImages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        IImageService.Stub.getDefaultImpl().removeDisabledImages();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unmapImageDevice(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        IImageService.Stub.getDefaultImpl().unmapImageDevice(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void zeroFillNewImage(String paramString, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IImageService");
      parcel1.writeString(paramString);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
        IImageService.Stub.getDefaultImpl().zeroFillNewImage(paramString, paramLong);
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


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IImageService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.gsi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IImageService {
  private static final String DESCRIPTOR = "android.gsi.IImageService";
  
  static final int TRANSACTION_backingImageExists = 5;
  
  static final int TRANSACTION_createBackingImage = 1;
  
  static final int TRANSACTION_deleteBackingImage = 2;
  
  static final int TRANSACTION_getAllBackingImages = 8;
  
  static final int TRANSACTION_getAvbPublicKey = 7;
  
  static final int TRANSACTION_getMappedImageDevice = 12;
  
  static final int TRANSACTION_isImageMapped = 6;
  
  static final int TRANSACTION_mapImageDevice = 3;
  
  static final int TRANSACTION_removeAllImages = 10;
  
  static final int TRANSACTION_removeDisabledImages = 11;
  
  static final int TRANSACTION_unmapImageDevice = 4;
  
  static final int TRANSACTION_zeroFillNewImage = 9;
  
  public Stub() {
    attachInterface(this, "android.gsi.IImageService");
  }
  
  public static IImageService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.gsi.IImageService");
    return (iInterface != null && iInterface instanceof IImageService) ? (IImageService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IImageService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 12:
        return "getMappedImageDevice";
      case 11:
        return "removeDisabledImages";
      case 10:
        return "removeAllImages";
      case 9:
        return "zeroFillNewImage";
      case 8:
        return "getAllBackingImages";
      case 7:
        return "getAvbPublicKey";
      case 6:
        return "isImageMapped";
      case 5:
        return "backingImageExists";
      case 4:
        return "unmapImageDevice";
      case 3:
        return "mapImageDevice";
      case 2:
        return "deleteBackingImage";
      case 1:
        break;
    } 
    return "createBackingImage";
  }
  
  public static boolean setDefaultImpl(IImageService paramIImageService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIImageService != null) {
        Proxy.sDefaultImpl = paramIImageService;
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
    if (paramInt1 != 1598968902) {
      boolean bool;
      int i;
      String str2;
      List<String> list;
      String str1;
      MappedImage mappedImage;
      AvbPublicKey avbPublicKey;
      String str3;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 12:
          paramParcel1.enforceInterface("android.gsi.IImageService");
          str2 = getMappedImageDevice(paramParcel1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 11:
          str2.enforceInterface("android.gsi.IImageService");
          removeDisabledImages();
          paramParcel2.writeNoException();
          return true;
        case 10:
          str2.enforceInterface("android.gsi.IImageService");
          removeAllImages();
          paramParcel2.writeNoException();
          return true;
        case 9:
          str2.enforceInterface("android.gsi.IImageService");
          zeroFillNewImage(str2.readString(), str2.readLong());
          paramParcel2.writeNoException();
          return true;
        case 8:
          str2.enforceInterface("android.gsi.IImageService");
          list = getAllBackingImages();
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list);
          return true;
        case 7:
          list.enforceInterface("android.gsi.IImageService");
          str1 = list.readString();
          avbPublicKey = new AvbPublicKey();
          paramInt1 = getAvbPublicKey(str1, avbPublicKey);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          paramParcel2.writeInt(1);
          avbPublicKey.writeToParcel(paramParcel2, 1);
          return true;
        case 6:
          str1.enforceInterface("android.gsi.IImageService");
          bool = isImageMapped(str1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 5:
          str1.enforceInterface("android.gsi.IImageService");
          bool = backingImageExists(str1.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 4:
          str1.enforceInterface("android.gsi.IImageService");
          unmapImageDevice(str1.readString());
          paramParcel2.writeNoException();
          return true;
        case 3:
          str1.enforceInterface("android.gsi.IImageService");
          str3 = str1.readString();
          i = str1.readInt();
          mappedImage = new MappedImage();
          mapImageDevice(str3, i, mappedImage);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(1);
          mappedImage.writeToParcel(paramParcel2, 1);
          return true;
        case 2:
          mappedImage.enforceInterface("android.gsi.IImageService");
          deleteBackingImage(mappedImage.readString());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      mappedImage.enforceInterface("android.gsi.IImageService");
      createBackingImage(mappedImage.readString(), mappedImage.readLong(), mappedImage.readInt(), IProgressCallback.Stub.asInterface(mappedImage.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.gsi.IImageService");
    return true;
  }
  
  private static class Proxy implements IImageService {
    public static IImageService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean backingImageExists(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          bool = IImageService.Stub.getDefaultImpl().backingImageExists(param2String);
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
    
    public void createBackingImage(String param2String, long param2Long, int param2Int, IProgressCallback param2IProgressCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param2String);
        parcel1.writeLong(param2Long);
        parcel1.writeInt(param2Int);
        if (param2IProgressCallback != null) {
          iBinder = param2IProgressCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().createBackingImage(param2String, param2Long, param2Int, param2IProgressCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteBackingImage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().deleteBackingImage(param2String);
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
    
    public int getAvbPublicKey(String param2String, AvbPublicKey param2AvbPublicKey) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null)
          return IImageService.Stub.getDefaultImpl().getAvbPublicKey(param2String, param2AvbPublicKey); 
        parcel2.readException();
        int i = parcel2.readInt();
        if (parcel2.readInt() != 0)
          param2AvbPublicKey.readFromParcel(parcel2); 
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.gsi.IImageService";
    }
    
    public String getMappedImageDevice(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          param2String = IImageService.Stub.getDefaultImpl().getMappedImageDevice(param2String);
          return param2String;
        } 
        parcel2.readException();
        param2String = parcel2.readString();
        return param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isImageMapped(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          bool = IImageService.Stub.getDefaultImpl().isImageMapped(param2String);
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
    
    public void mapImageDevice(String param2String, int param2Int, MappedImage param2MappedImage) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().mapImageDevice(param2String, param2Int, param2MappedImage);
          return;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0)
          param2MappedImage.readFromParcel(parcel2); 
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
    
    public void unmapImageDevice(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().unmapImageDevice(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void zeroFillNewImage(String param2String, long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param2String);
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().zeroFillNewImage(param2String, param2Long);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IImageService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
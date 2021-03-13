package android.gsi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IImageService extends IInterface {
  public static final int CREATE_IMAGE_DEFAULT = 0;
  
  public static final int CREATE_IMAGE_READONLY = 1;
  
  public static final int CREATE_IMAGE_ZERO_FILL = 2;
  
  public static final int IMAGE_ERROR = 1;
  
  public static final int IMAGE_OK = 0;
  
  boolean backingImageExists(String paramString) throws RemoteException;
  
  void createBackingImage(String paramString, long paramLong, int paramInt, IProgressCallback paramIProgressCallback) throws RemoteException;
  
  void deleteBackingImage(String paramString) throws RemoteException;
  
  List<String> getAllBackingImages() throws RemoteException;
  
  int getAvbPublicKey(String paramString, AvbPublicKey paramAvbPublicKey) throws RemoteException;
  
  String getMappedImageDevice(String paramString) throws RemoteException;
  
  boolean isImageMapped(String paramString) throws RemoteException;
  
  void mapImageDevice(String paramString, int paramInt, MappedImage paramMappedImage) throws RemoteException;
  
  void removeAllImages() throws RemoteException;
  
  void removeDisabledImages() throws RemoteException;
  
  void unmapImageDevice(String paramString) throws RemoteException;
  
  void zeroFillNewImage(String paramString, long paramLong) throws RemoteException;
  
  public static class Default implements IImageService {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean backingImageExists(String param1String) throws RemoteException {
      return false;
    }
    
    public void createBackingImage(String param1String, long param1Long, int param1Int, IProgressCallback param1IProgressCallback) throws RemoteException {}
    
    public void deleteBackingImage(String param1String) throws RemoteException {}
    
    public List<String> getAllBackingImages() throws RemoteException {
      return null;
    }
    
    public int getAvbPublicKey(String param1String, AvbPublicKey param1AvbPublicKey) throws RemoteException {
      return 0;
    }
    
    public String getMappedImageDevice(String param1String) throws RemoteException {
      return null;
    }
    
    public boolean isImageMapped(String param1String) throws RemoteException {
      return false;
    }
    
    public void mapImageDevice(String param1String, int param1Int, MappedImage param1MappedImage) throws RemoteException {}
    
    public void removeAllImages() throws RemoteException {}
    
    public void removeDisabledImages() throws RemoteException {}
    
    public void unmapImageDevice(String param1String) throws RemoteException {}
    
    public void zeroFillNewImage(String param1String, long param1Long) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IImageService {
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
    
    public static IImageService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.gsi.IImageService");
      return (iInterface != null && iInterface instanceof IImageService) ? (IImageService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IImageService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IImageService param1IImageService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IImageService != null) {
          Proxy.sDefaultImpl = param1IImageService;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        boolean bool;
        int i;
        String str2;
        List<String> list;
        String str1;
        MappedImage mappedImage;
        AvbPublicKey avbPublicKey;
        String str3;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 12:
            param1Parcel1.enforceInterface("android.gsi.IImageService");
            str2 = getMappedImageDevice(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str2);
            return true;
          case 11:
            str2.enforceInterface("android.gsi.IImageService");
            removeDisabledImages();
            param1Parcel2.writeNoException();
            return true;
          case 10:
            str2.enforceInterface("android.gsi.IImageService");
            removeAllImages();
            param1Parcel2.writeNoException();
            return true;
          case 9:
            str2.enforceInterface("android.gsi.IImageService");
            zeroFillNewImage(str2.readString(), str2.readLong());
            param1Parcel2.writeNoException();
            return true;
          case 8:
            str2.enforceInterface("android.gsi.IImageService");
            list = getAllBackingImages();
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringList(list);
            return true;
          case 7:
            list.enforceInterface("android.gsi.IImageService");
            str1 = list.readString();
            avbPublicKey = new AvbPublicKey();
            param1Int1 = getAvbPublicKey(str1, avbPublicKey);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            param1Parcel2.writeInt(1);
            avbPublicKey.writeToParcel(param1Parcel2, 1);
            return true;
          case 6:
            str1.enforceInterface("android.gsi.IImageService");
            bool = isImageMapped(str1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 5:
            str1.enforceInterface("android.gsi.IImageService");
            bool = backingImageExists(str1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 4:
            str1.enforceInterface("android.gsi.IImageService");
            unmapImageDevice(str1.readString());
            param1Parcel2.writeNoException();
            return true;
          case 3:
            str1.enforceInterface("android.gsi.IImageService");
            str3 = str1.readString();
            i = str1.readInt();
            mappedImage = new MappedImage();
            mapImageDevice(str3, i, mappedImage);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(1);
            mappedImage.writeToParcel(param1Parcel2, 1);
            return true;
          case 2:
            mappedImage.enforceInterface("android.gsi.IImageService");
            deleteBackingImage(mappedImage.readString());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        mappedImage.enforceInterface("android.gsi.IImageService");
        createBackingImage(mappedImage.readString(), mappedImage.readLong(), mappedImage.readInt(), IProgressCallback.Stub.asInterface(mappedImage.readStrongBinder()));
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.gsi.IImageService");
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
  
  private static class Proxy implements IImageService {
    public static IImageService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean backingImageExists(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          bool = IImageService.Stub.getDefaultImpl().backingImageExists(param1String);
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
    
    public void createBackingImage(String param1String, long param1Long, int param1Int, IProgressCallback param1IProgressCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param1String);
        parcel1.writeLong(param1Long);
        parcel1.writeInt(param1Int);
        if (param1IProgressCallback != null) {
          iBinder = param1IProgressCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().createBackingImage(param1String, param1Long, param1Int, param1IProgressCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deleteBackingImage(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().deleteBackingImage(param1String);
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
    
    public int getAvbPublicKey(String param1String, AvbPublicKey param1AvbPublicKey) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null)
          return IImageService.Stub.getDefaultImpl().getAvbPublicKey(param1String, param1AvbPublicKey); 
        parcel2.readException();
        int i = parcel2.readInt();
        if (parcel2.readInt() != 0)
          param1AvbPublicKey.readFromParcel(parcel2); 
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.gsi.IImageService";
    }
    
    public String getMappedImageDevice(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          param1String = IImageService.Stub.getDefaultImpl().getMappedImageDevice(param1String);
          return param1String;
        } 
        parcel2.readException();
        param1String = parcel2.readString();
        return param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isImageMapped(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          bool = IImageService.Stub.getDefaultImpl().isImageMapped(param1String);
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
    
    public void mapImageDevice(String param1String, int param1Int, MappedImage param1MappedImage) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().mapImageDevice(param1String, param1Int, param1MappedImage);
          return;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0)
          param1MappedImage.readFromParcel(parcel2); 
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
    
    public void unmapImageDevice(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().unmapImageDevice(param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void zeroFillNewImage(String param1String, long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IImageService");
        parcel1.writeString(param1String);
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IImageService.Stub.getDefaultImpl() != null) {
          IImageService.Stub.getDefaultImpl().zeroFillNewImage(param1String, param1Long);
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


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IImageService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
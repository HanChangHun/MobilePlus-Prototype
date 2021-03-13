package android.gsi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

public interface IGsiService extends IInterface {
  public static final int INSTALL_ERROR_FILE_SYSTEM_CLUTTERED = 3;
  
  public static final int INSTALL_ERROR_GENERIC = 1;
  
  public static final int INSTALL_ERROR_NO_SPACE = 2;
  
  public static final int INSTALL_OK = 0;
  
  public static final int STATUS_COMPLETE = 2;
  
  public static final int STATUS_NO_OPERATION = 0;
  
  public static final int STATUS_WORKING = 1;
  
  boolean cancelGsiInstall() throws RemoteException;
  
  int closeInstall() throws RemoteException;
  
  boolean commitGsiChunkFromAshmem(long paramLong) throws RemoteException;
  
  boolean commitGsiChunkFromStream(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong) throws RemoteException;
  
  int createPartition(String paramString, long paramLong, boolean paramBoolean) throws RemoteException;
  
  boolean disableGsi() throws RemoteException;
  
  String dumpDeviceMapperDevices() throws RemoteException;
  
  int enableGsi(boolean paramBoolean, String paramString) throws RemoteException;
  
  void enableGsiAsync(boolean paramBoolean, String paramString, IGsiServiceCallback paramIGsiServiceCallback) throws RemoteException;
  
  String getActiveDsuSlot() throws RemoteException;
  
  int getAvbPublicKey(AvbPublicKey paramAvbPublicKey) throws RemoteException;
  
  GsiProgress getInstallProgress() throws RemoteException;
  
  List<String> getInstalledDsuSlots() throws RemoteException;
  
  String getInstalledGsiImageDir() throws RemoteException;
  
  boolean isGsiEnabled() throws RemoteException;
  
  boolean isGsiInstallInProgress() throws RemoteException;
  
  boolean isGsiInstalled() throws RemoteException;
  
  boolean isGsiRunning() throws RemoteException;
  
  IImageService openImageService(String paramString) throws RemoteException;
  
  int openInstall(String paramString) throws RemoteException;
  
  boolean removeGsi() throws RemoteException;
  
  void removeGsiAsync(IGsiServiceCallback paramIGsiServiceCallback) throws RemoteException;
  
  boolean setGsiAshmem(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong) throws RemoteException;
  
  int zeroPartition(String paramString) throws RemoteException;
  
  public static class Default implements IGsiService {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean cancelGsiInstall() throws RemoteException {
      return false;
    }
    
    public int closeInstall() throws RemoteException {
      return 0;
    }
    
    public boolean commitGsiChunkFromAshmem(long param1Long) throws RemoteException {
      return false;
    }
    
    public boolean commitGsiChunkFromStream(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long) throws RemoteException {
      return false;
    }
    
    public int createPartition(String param1String, long param1Long, boolean param1Boolean) throws RemoteException {
      return 0;
    }
    
    public boolean disableGsi() throws RemoteException {
      return false;
    }
    
    public String dumpDeviceMapperDevices() throws RemoteException {
      return null;
    }
    
    public int enableGsi(boolean param1Boolean, String param1String) throws RemoteException {
      return 0;
    }
    
    public void enableGsiAsync(boolean param1Boolean, String param1String, IGsiServiceCallback param1IGsiServiceCallback) throws RemoteException {}
    
    public String getActiveDsuSlot() throws RemoteException {
      return null;
    }
    
    public int getAvbPublicKey(AvbPublicKey param1AvbPublicKey) throws RemoteException {
      return 0;
    }
    
    public GsiProgress getInstallProgress() throws RemoteException {
      return null;
    }
    
    public List<String> getInstalledDsuSlots() throws RemoteException {
      return null;
    }
    
    public String getInstalledGsiImageDir() throws RemoteException {
      return null;
    }
    
    public boolean isGsiEnabled() throws RemoteException {
      return false;
    }
    
    public boolean isGsiInstallInProgress() throws RemoteException {
      return false;
    }
    
    public boolean isGsiInstalled() throws RemoteException {
      return false;
    }
    
    public boolean isGsiRunning() throws RemoteException {
      return false;
    }
    
    public IImageService openImageService(String param1String) throws RemoteException {
      return null;
    }
    
    public int openInstall(String param1String) throws RemoteException {
      return 0;
    }
    
    public boolean removeGsi() throws RemoteException {
      return false;
    }
    
    public void removeGsiAsync(IGsiServiceCallback param1IGsiServiceCallback) throws RemoteException {}
    
    public boolean setGsiAshmem(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long) throws RemoteException {
      return false;
    }
    
    public int zeroPartition(String param1String) throws RemoteException {
      return 0;
    }
  }
  
  public static abstract class Stub extends Binder implements IGsiService {
    private static final String DESCRIPTOR = "android.gsi.IGsiService";
    
    static final int TRANSACTION_cancelGsiInstall = 8;
    
    static final int TRANSACTION_closeInstall = 19;
    
    static final int TRANSACTION_commitGsiChunkFromAshmem = 4;
    
    static final int TRANSACTION_commitGsiChunkFromStream = 1;
    
    static final int TRANSACTION_createPartition = 20;
    
    static final int TRANSACTION_disableGsi = 12;
    
    static final int TRANSACTION_dumpDeviceMapperDevices = 23;
    
    static final int TRANSACTION_enableGsi = 5;
    
    static final int TRANSACTION_enableGsiAsync = 6;
    
    static final int TRANSACTION_getActiveDsuSlot = 15;
    
    static final int TRANSACTION_getAvbPublicKey = 24;
    
    static final int TRANSACTION_getInstallProgress = 2;
    
    static final int TRANSACTION_getInstalledDsuSlots = 17;
    
    static final int TRANSACTION_getInstalledGsiImageDir = 16;
    
    static final int TRANSACTION_isGsiEnabled = 7;
    
    static final int TRANSACTION_isGsiInstallInProgress = 9;
    
    static final int TRANSACTION_isGsiInstalled = 13;
    
    static final int TRANSACTION_isGsiRunning = 14;
    
    static final int TRANSACTION_openImageService = 22;
    
    static final int TRANSACTION_openInstall = 18;
    
    static final int TRANSACTION_removeGsi = 10;
    
    static final int TRANSACTION_removeGsiAsync = 11;
    
    static final int TRANSACTION_setGsiAshmem = 3;
    
    static final int TRANSACTION_zeroPartition = 21;
    
    public Stub() {
      attachInterface(this, "android.gsi.IGsiService");
    }
    
    public static IGsiService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.gsi.IGsiService");
      return (iInterface != null && iInterface instanceof IGsiService) ? (IGsiService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IGsiService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 24:
          return "getAvbPublicKey";
        case 23:
          return "dumpDeviceMapperDevices";
        case 22:
          return "openImageService";
        case 21:
          return "zeroPartition";
        case 20:
          return "createPartition";
        case 19:
          return "closeInstall";
        case 18:
          return "openInstall";
        case 17:
          return "getInstalledDsuSlots";
        case 16:
          return "getInstalledGsiImageDir";
        case 15:
          return "getActiveDsuSlot";
        case 14:
          return "isGsiRunning";
        case 13:
          return "isGsiInstalled";
        case 12:
          return "disableGsi";
        case 11:
          return "removeGsiAsync";
        case 10:
          return "removeGsi";
        case 9:
          return "isGsiInstallInProgress";
        case 8:
          return "cancelGsiInstall";
        case 7:
          return "isGsiEnabled";
        case 6:
          return "enableGsiAsync";
        case 5:
          return "enableGsi";
        case 4:
          return "commitGsiChunkFromAshmem";
        case 3:
          return "setGsiAshmem";
        case 2:
          return "getInstallProgress";
        case 1:
          break;
      } 
      return "commitGsiChunkFromStream";
    }
    
    public static boolean setDefaultImpl(IGsiService param1IGsiService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IGsiService != null) {
          Proxy.sDefaultImpl = param1IGsiService;
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
        boolean bool2;
        int i;
        AvbPublicKey avbPublicKey;
        String str2;
        IImageService iImageService;
        List<String> list;
        String str1;
        GsiProgress gsiProgress;
        String str3;
        long l;
        boolean bool3 = false;
        boolean bool4 = false;
        boolean bool5 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 24:
            param1Parcel1.enforceInterface("android.gsi.IGsiService");
            avbPublicKey = new AvbPublicKey();
            param1Int1 = getAvbPublicKey(avbPublicKey);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            param1Parcel2.writeInt(1);
            avbPublicKey.writeToParcel(param1Parcel2, 1);
            return true;
          case 23:
            avbPublicKey.enforceInterface("android.gsi.IGsiService");
            str2 = dumpDeviceMapperDevices();
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str2);
            return true;
          case 22:
            str2.enforceInterface("android.gsi.IGsiService");
            iImageService = openImageService(str2.readString());
            param1Parcel2.writeNoException();
            if (iImageService != null) {
              IBinder iBinder = iImageService.asBinder();
            } else {
              iImageService = null;
            } 
            param1Parcel2.writeStrongBinder((IBinder)iImageService);
            return true;
          case 21:
            iImageService.enforceInterface("android.gsi.IGsiService");
            param1Int1 = zeroPartition(iImageService.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 20:
            iImageService.enforceInterface("android.gsi.IGsiService");
            str3 = iImageService.readString();
            l = iImageService.readLong();
            if (iImageService.readInt() != 0)
              bool5 = true; 
            param1Int1 = createPartition(str3, l, bool5);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 19:
            iImageService.enforceInterface("android.gsi.IGsiService");
            param1Int1 = closeInstall();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 18:
            iImageService.enforceInterface("android.gsi.IGsiService");
            param1Int1 = openInstall(iImageService.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 17:
            iImageService.enforceInterface("android.gsi.IGsiService");
            list = getInstalledDsuSlots();
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringList(list);
            return true;
          case 16:
            list.enforceInterface("android.gsi.IGsiService");
            str1 = getInstalledGsiImageDir();
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str1);
            return true;
          case 15:
            str1.enforceInterface("android.gsi.IGsiService");
            str1 = getActiveDsuSlot();
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str1);
            return true;
          case 14:
            str1.enforceInterface("android.gsi.IGsiService");
            bool2 = isGsiRunning();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 13:
            str1.enforceInterface("android.gsi.IGsiService");
            bool2 = isGsiInstalled();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 12:
            str1.enforceInterface("android.gsi.IGsiService");
            bool2 = disableGsi();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 11:
            str1.enforceInterface("android.gsi.IGsiService");
            removeGsiAsync(IGsiServiceCallback.Stub.asInterface(str1.readStrongBinder()));
            return true;
          case 10:
            str1.enforceInterface("android.gsi.IGsiService");
            bool2 = removeGsi();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 9:
            str1.enforceInterface("android.gsi.IGsiService");
            bool2 = isGsiInstallInProgress();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 8:
            str1.enforceInterface("android.gsi.IGsiService");
            bool2 = cancelGsiInstall();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 7:
            str1.enforceInterface("android.gsi.IGsiService");
            bool2 = isGsiEnabled();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 6:
            str1.enforceInterface("android.gsi.IGsiService");
            bool5 = bool3;
            if (str1.readInt() != 0)
              bool5 = true; 
            enableGsiAsync(bool5, str1.readString(), IGsiServiceCallback.Stub.asInterface(str1.readStrongBinder()));
            return true;
          case 5:
            str1.enforceInterface("android.gsi.IGsiService");
            bool5 = bool4;
            if (str1.readInt() != 0)
              bool5 = true; 
            i = enableGsi(bool5, str1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 4:
            str1.enforceInterface("android.gsi.IGsiService");
            bool1 = commitGsiChunkFromAshmem(str1.readLong());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 3:
            str1.enforceInterface("android.gsi.IGsiService");
            if (str1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel((Parcel)str1);
            } else {
              str3 = null;
            } 
            bool1 = setGsiAshmem((ParcelFileDescriptor)str3, str1.readLong());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 2:
            str1.enforceInterface("android.gsi.IGsiService");
            gsiProgress = getInstallProgress();
            param1Parcel2.writeNoException();
            if (gsiProgress != null) {
              param1Parcel2.writeInt(1);
              gsiProgress.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 1:
            break;
        } 
        gsiProgress.enforceInterface("android.gsi.IGsiService");
        if (gsiProgress.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel((Parcel)gsiProgress);
        } else {
          str3 = null;
        } 
        boolean bool1 = commitGsiChunkFromStream((ParcelFileDescriptor)str3, gsiProgress.readLong());
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool1);
        return true;
      } 
      param1Parcel2.writeString("android.gsi.IGsiService");
      return true;
    }
    
    private static class Proxy implements IGsiService {
      public static IGsiService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public boolean cancelGsiInstall() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(8, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().cancelGsiInstall();
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
      
      public int closeInstall() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
            return IGsiService.Stub.getDefaultImpl().closeInstall(); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean commitGsiChunkFromAshmem(long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          parcel1.writeLong(param2Long);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(4, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().commitGsiChunkFromAshmem(param2Long);
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
      
      public boolean commitGsiChunkFromStream(ParcelFileDescriptor param2ParcelFileDescriptor, long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          boolean bool = true;
          if (param2ParcelFileDescriptor != null) {
            parcel1.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeLong(param2Long);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().commitGsiChunkFromStream(param2ParcelFileDescriptor, param2Long);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int createPartition(String param2String, long param2Long, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          parcel1.writeString(param2String);
          parcel1.writeLong(param2Long);
          if (param2Boolean) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            i = IGsiService.Stub.getDefaultImpl().createPartition(param2String, param2Long, param2Boolean);
            return i;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          return i;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean disableGsi() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(12, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().disableGsi();
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
      
      public String dumpDeviceMapperDevices() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
            return IGsiService.Stub.getDefaultImpl().dumpDeviceMapperDevices(); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int enableGsi(boolean param2Boolean, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          if (param2Boolean) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            i = IGsiService.Stub.getDefaultImpl().enableGsi(param2Boolean, param2String);
            return i;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          return i;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void enableGsiAsync(boolean param2Boolean, String param2String, IGsiServiceCallback param2IGsiServiceCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          IBinder iBinder;
          parcel.writeInterfaceToken("android.gsi.IGsiService");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeString(param2String);
          if (param2IGsiServiceCallback != null) {
            iBinder = param2IGsiServiceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(6, parcel, null, 1) && IGsiService.Stub.getDefaultImpl() != null) {
            IGsiService.Stub.getDefaultImpl().enableGsiAsync(param2Boolean, param2String, param2IGsiServiceCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public String getActiveDsuSlot() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
            return IGsiService.Stub.getDefaultImpl().getActiveDsuSlot(); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getAvbPublicKey(AvbPublicKey param2AvbPublicKey) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
            return IGsiService.Stub.getDefaultImpl().getAvbPublicKey(param2AvbPublicKey); 
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
      
      public GsiProgress getInstallProgress() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          GsiProgress gsiProgress;
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            gsiProgress = IGsiService.Stub.getDefaultImpl().getInstallProgress();
            return gsiProgress;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            gsiProgress = (GsiProgress)GsiProgress.CREATOR.createFromParcel(parcel2);
          } else {
            gsiProgress = null;
          } 
          return gsiProgress;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<String> getInstalledDsuSlots() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
            return IGsiService.Stub.getDefaultImpl().getInstalledDsuSlots(); 
          parcel2.readException();
          return parcel2.createStringArrayList();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInstalledGsiImageDir() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
            return IGsiService.Stub.getDefaultImpl().getInstalledGsiImageDir(); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.gsi.IGsiService";
      }
      
      public boolean isGsiEnabled() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(7, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().isGsiEnabled();
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
      
      public boolean isGsiInstallInProgress() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(9, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().isGsiInstallInProgress();
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
      
      public boolean isGsiInstalled() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(13, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().isGsiInstalled();
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
      
      public boolean isGsiRunning() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(14, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().isGsiRunning();
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
      
      public IImageService openImageService(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
            return IGsiService.Stub.getDefaultImpl().openImageService(param2String); 
          parcel2.readException();
          return IImageService.Stub.asInterface(parcel2.readStrongBinder());
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int openInstall(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
            return IGsiService.Stub.getDefaultImpl().openInstall(param2String); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean removeGsi() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(10, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().removeGsi();
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
      
      public void removeGsiAsync(IGsiServiceCallback param2IGsiServiceCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.gsi.IGsiService");
          if (param2IGsiServiceCallback != null) {
            iBinder = param2IGsiServiceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(11, parcel, null, 1) && IGsiService.Stub.getDefaultImpl() != null) {
            IGsiService.Stub.getDefaultImpl().removeGsiAsync(param2IGsiServiceCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public boolean setGsiAshmem(ParcelFileDescriptor param2ParcelFileDescriptor, long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          boolean bool = true;
          if (param2ParcelFileDescriptor != null) {
            parcel1.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeLong(param2Long);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
            bool = IGsiService.Stub.getDefaultImpl().setGsiAshmem(param2ParcelFileDescriptor, param2Long);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int zeroPartition(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IGsiService");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
            return IGsiService.Stub.getDefaultImpl().zeroPartition(param2String); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IGsiService {
    public static IGsiService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean cancelGsiInstall() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().cancelGsiInstall();
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
    
    public int closeInstall() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
          return IGsiService.Stub.getDefaultImpl().closeInstall(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean commitGsiChunkFromAshmem(long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        parcel1.writeLong(param1Long);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().commitGsiChunkFromAshmem(param1Long);
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
    
    public boolean commitGsiChunkFromStream(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        boolean bool = true;
        if (param1ParcelFileDescriptor != null) {
          parcel1.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().commitGsiChunkFromStream(param1ParcelFileDescriptor, param1Long);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int createPartition(String param1String, long param1Long, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        parcel1.writeString(param1String);
        parcel1.writeLong(param1Long);
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          i = IGsiService.Stub.getDefaultImpl().createPartition(param1String, param1Long, param1Boolean);
          return i;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean disableGsi() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(12, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().disableGsi();
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
    
    public String dumpDeviceMapperDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
          return IGsiService.Stub.getDefaultImpl().dumpDeviceMapperDevices(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int enableGsi(boolean param1Boolean, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          i = IGsiService.Stub.getDefaultImpl().enableGsi(param1Boolean, param1String);
          return i;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enableGsiAsync(boolean param1Boolean, String param1String, IGsiServiceCallback param1IGsiServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel.writeInterfaceToken("android.gsi.IGsiService");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeString(param1String);
        if (param1IGsiServiceCallback != null) {
          iBinder = param1IGsiServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel, null, 1) && IGsiService.Stub.getDefaultImpl() != null) {
          IGsiService.Stub.getDefaultImpl().enableGsiAsync(param1Boolean, param1String, param1IGsiServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getActiveDsuSlot() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
          return IGsiService.Stub.getDefaultImpl().getActiveDsuSlot(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getAvbPublicKey(AvbPublicKey param1AvbPublicKey) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
          return IGsiService.Stub.getDefaultImpl().getAvbPublicKey(param1AvbPublicKey); 
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
    
    public GsiProgress getInstallProgress() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        GsiProgress gsiProgress;
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          gsiProgress = IGsiService.Stub.getDefaultImpl().getInstallProgress();
          return gsiProgress;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          gsiProgress = (GsiProgress)GsiProgress.CREATOR.createFromParcel(parcel2);
        } else {
          gsiProgress = null;
        } 
        return gsiProgress;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getInstalledDsuSlots() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
          return IGsiService.Stub.getDefaultImpl().getInstalledDsuSlots(); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInstalledGsiImageDir() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
          return IGsiService.Stub.getDefaultImpl().getInstalledGsiImageDir(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.gsi.IGsiService";
    }
    
    public boolean isGsiEnabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().isGsiEnabled();
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
    
    public boolean isGsiInstallInProgress() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().isGsiInstallInProgress();
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
    
    public boolean isGsiInstalled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(13, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().isGsiInstalled();
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
    
    public boolean isGsiRunning() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(14, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().isGsiRunning();
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
    
    public IImageService openImageService(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
          return IGsiService.Stub.getDefaultImpl().openImageService(param1String); 
        parcel2.readException();
        return IImageService.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int openInstall(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
          return IGsiService.Stub.getDefaultImpl().openInstall(param1String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeGsi() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(10, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().removeGsi();
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
    
    public void removeGsiAsync(IGsiServiceCallback param1IGsiServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.gsi.IGsiService");
        if (param1IGsiServiceCallback != null) {
          iBinder = param1IGsiServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(11, parcel, null, 1) && IGsiService.Stub.getDefaultImpl() != null) {
          IGsiService.Stub.getDefaultImpl().removeGsiAsync(param1IGsiServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public boolean setGsiAshmem(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        boolean bool = true;
        if (param1ParcelFileDescriptor != null) {
          parcel1.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
          bool = IGsiService.Stub.getDefaultImpl().setGsiAshmem(param1ParcelFileDescriptor, param1Long);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int zeroPartition(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IGsiService");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
          return IGsiService.Stub.getDefaultImpl().zeroPartition(param1String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IGsiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
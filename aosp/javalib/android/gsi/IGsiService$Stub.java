package android.gsi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IGsiService {
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
  
  public static IGsiService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.gsi.IGsiService");
    return (iInterface != null && iInterface instanceof IGsiService) ? (IGsiService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IGsiService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IGsiService paramIGsiService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIGsiService != null) {
        Proxy.sDefaultImpl = paramIGsiService;
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
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 24:
          paramParcel1.enforceInterface("android.gsi.IGsiService");
          avbPublicKey = new AvbPublicKey();
          paramInt1 = getAvbPublicKey(avbPublicKey);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          paramParcel2.writeInt(1);
          avbPublicKey.writeToParcel(paramParcel2, 1);
          return true;
        case 23:
          avbPublicKey.enforceInterface("android.gsi.IGsiService");
          str2 = dumpDeviceMapperDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 22:
          str2.enforceInterface("android.gsi.IGsiService");
          iImageService = openImageService(str2.readString());
          paramParcel2.writeNoException();
          if (iImageService != null) {
            IBinder iBinder = iImageService.asBinder();
          } else {
            iImageService = null;
          } 
          paramParcel2.writeStrongBinder((IBinder)iImageService);
          return true;
        case 21:
          iImageService.enforceInterface("android.gsi.IGsiService");
          paramInt1 = zeroPartition(iImageService.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 20:
          iImageService.enforceInterface("android.gsi.IGsiService");
          str3 = iImageService.readString();
          l = iImageService.readLong();
          if (iImageService.readInt() != 0)
            bool5 = true; 
          paramInt1 = createPartition(str3, l, bool5);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 19:
          iImageService.enforceInterface("android.gsi.IGsiService");
          paramInt1 = closeInstall();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 18:
          iImageService.enforceInterface("android.gsi.IGsiService");
          paramInt1 = openInstall(iImageService.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 17:
          iImageService.enforceInterface("android.gsi.IGsiService");
          list = getInstalledDsuSlots();
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list);
          return true;
        case 16:
          list.enforceInterface("android.gsi.IGsiService");
          str1 = getInstalledGsiImageDir();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 15:
          str1.enforceInterface("android.gsi.IGsiService");
          str1 = getActiveDsuSlot();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 14:
          str1.enforceInterface("android.gsi.IGsiService");
          bool2 = isGsiRunning();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 13:
          str1.enforceInterface("android.gsi.IGsiService");
          bool2 = isGsiInstalled();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 12:
          str1.enforceInterface("android.gsi.IGsiService");
          bool2 = disableGsi();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 11:
          str1.enforceInterface("android.gsi.IGsiService");
          removeGsiAsync(IGsiServiceCallback.Stub.asInterface(str1.readStrongBinder()));
          return true;
        case 10:
          str1.enforceInterface("android.gsi.IGsiService");
          bool2 = removeGsi();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 9:
          str1.enforceInterface("android.gsi.IGsiService");
          bool2 = isGsiInstallInProgress();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 8:
          str1.enforceInterface("android.gsi.IGsiService");
          bool2 = cancelGsiInstall();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 7:
          str1.enforceInterface("android.gsi.IGsiService");
          bool2 = isGsiEnabled();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 4:
          str1.enforceInterface("android.gsi.IGsiService");
          bool1 = commitGsiChunkFromAshmem(str1.readLong());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 3:
          str1.enforceInterface("android.gsi.IGsiService");
          if (str1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel((Parcel)str1);
          } else {
            str3 = null;
          } 
          bool1 = setGsiAshmem((ParcelFileDescriptor)str3, str1.readLong());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 2:
          str1.enforceInterface("android.gsi.IGsiService");
          gsiProgress = getInstallProgress();
          paramParcel2.writeNoException();
          if (gsiProgress != null) {
            paramParcel2.writeInt(1);
            gsiProgress.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
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
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool1);
      return true;
    } 
    paramParcel2.writeString("android.gsi.IGsiService");
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


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IGsiService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
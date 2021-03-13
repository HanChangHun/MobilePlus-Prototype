package android.content.pm;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPackageInstallerSession {
  private static final String DESCRIPTOR = "android.content.pm.IPackageInstallerSession";
  
  static final int TRANSACTION_abandon = 11;
  
  static final int TRANSACTION_addChildSessionId = 17;
  
  static final int TRANSACTION_addClientProgress = 2;
  
  static final int TRANSACTION_addFile = 13;
  
  static final int TRANSACTION_close = 8;
  
  static final int TRANSACTION_commit = 9;
  
  static final int TRANSACTION_getChildSessionIds = 16;
  
  static final int TRANSACTION_getDataLoaderParams = 12;
  
  static final int TRANSACTION_getNames = 3;
  
  static final int TRANSACTION_getParentSessionId = 19;
  
  static final int TRANSACTION_isMultiPackage = 15;
  
  static final int TRANSACTION_isStaged = 20;
  
  static final int TRANSACTION_openRead = 5;
  
  static final int TRANSACTION_openWrite = 4;
  
  static final int TRANSACTION_removeChildSessionId = 18;
  
  static final int TRANSACTION_removeFile = 14;
  
  static final int TRANSACTION_removeSplit = 7;
  
  static final int TRANSACTION_setClientProgress = 1;
  
  static final int TRANSACTION_transfer = 10;
  
  static final int TRANSACTION_write = 6;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IPackageInstallerSession");
  }
  
  public static IPackageInstallerSession asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageInstallerSession");
    return (iInterface != null && iInterface instanceof IPackageInstallerSession) ? (IPackageInstallerSession)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPackageInstallerSession getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 20:
        return "isStaged";
      case 19:
        return "getParentSessionId";
      case 18:
        return "removeChildSessionId";
      case 17:
        return "addChildSessionId";
      case 16:
        return "getChildSessionIds";
      case 15:
        return "isMultiPackage";
      case 14:
        return "removeFile";
      case 13:
        return "addFile";
      case 12:
        return "getDataLoaderParams";
      case 11:
        return "abandon";
      case 10:
        return "transfer";
      case 9:
        return "commit";
      case 8:
        return "close";
      case 7:
        return "removeSplit";
      case 6:
        return "write";
      case 5:
        return "openRead";
      case 4:
        return "openWrite";
      case 3:
        return "getNames";
      case 2:
        return "addClientProgress";
      case 1:
        break;
    } 
    return "setClientProgress";
  }
  
  public static boolean setDefaultImpl(IPackageInstallerSession paramIPackageInstallerSession) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPackageInstallerSession != null) {
        Proxy.sDefaultImpl = paramIPackageInstallerSession;
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
      boolean bool1;
      int[] arrayOfInt;
      DataLoaderParamsParcel dataLoaderParamsParcel;
      ParcelFileDescriptor parcelFileDescriptor;
      String[] arrayOfString;
      IntentSender intentSender;
      String str;
      long l1;
      long l2;
      boolean bool = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 20:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstallerSession");
          bool2 = isStaged();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 19:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstallerSession");
          i = getParentSessionId();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 18:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstallerSession");
          removeChildSessionId(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 17:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstallerSession");
          addChildSessionId(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 16:
          paramParcel1.enforceInterface("android.content.pm.IPackageInstallerSession");
          arrayOfInt = getChildSessionIds();
          paramParcel2.writeNoException();
          paramParcel2.writeIntArray(arrayOfInt);
          return true;
        case 15:
          arrayOfInt.enforceInterface("android.content.pm.IPackageInstallerSession");
          bool1 = isMultiPackage();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 14:
          arrayOfInt.enforceInterface("android.content.pm.IPackageInstallerSession");
          removeFile(arrayOfInt.readInt(), arrayOfInt.readString());
          paramParcel2.writeNoException();
          return true;
        case 13:
          arrayOfInt.enforceInterface("android.content.pm.IPackageInstallerSession");
          addFile(arrayOfInt.readInt(), arrayOfInt.readString(), arrayOfInt.readLong(), arrayOfInt.createByteArray(), arrayOfInt.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 12:
          arrayOfInt.enforceInterface("android.content.pm.IPackageInstallerSession");
          dataLoaderParamsParcel = getDataLoaderParams();
          paramParcel2.writeNoException();
          if (dataLoaderParamsParcel != null) {
            paramParcel2.writeInt(1);
            dataLoaderParamsParcel.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 11:
          dataLoaderParamsParcel.enforceInterface("android.content.pm.IPackageInstallerSession");
          abandon();
          paramParcel2.writeNoException();
          return true;
        case 10:
          dataLoaderParamsParcel.enforceInterface("android.content.pm.IPackageInstallerSession");
          transfer(dataLoaderParamsParcel.readString());
          paramParcel2.writeNoException();
          return true;
        case 9:
          dataLoaderParamsParcel.enforceInterface("android.content.pm.IPackageInstallerSession");
          if (dataLoaderParamsParcel.readInt() != 0) {
            intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel((Parcel)dataLoaderParamsParcel);
          } else {
            intentSender = null;
          } 
          if (dataLoaderParamsParcel.readInt() != 0)
            bool = true; 
          commit(intentSender, bool);
          paramParcel2.writeNoException();
          return true;
        case 8:
          dataLoaderParamsParcel.enforceInterface("android.content.pm.IPackageInstallerSession");
          close();
          paramParcel2.writeNoException();
          return true;
        case 7:
          dataLoaderParamsParcel.enforceInterface("android.content.pm.IPackageInstallerSession");
          removeSplit(dataLoaderParamsParcel.readString());
          paramParcel2.writeNoException();
          return true;
        case 6:
          dataLoaderParamsParcel.enforceInterface("android.content.pm.IPackageInstallerSession");
          str = dataLoaderParamsParcel.readString();
          l1 = dataLoaderParamsParcel.readLong();
          l2 = dataLoaderParamsParcel.readLong();
          if (dataLoaderParamsParcel.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel((Parcel)dataLoaderParamsParcel);
          } else {
            dataLoaderParamsParcel = null;
          } 
          write(str, l1, l2, (ParcelFileDescriptor)dataLoaderParamsParcel);
          paramParcel2.writeNoException();
          return true;
        case 5:
          dataLoaderParamsParcel.enforceInterface("android.content.pm.IPackageInstallerSession");
          parcelFileDescriptor = openRead(dataLoaderParamsParcel.readString());
          paramParcel2.writeNoException();
          if (parcelFileDescriptor != null) {
            paramParcel2.writeInt(1);
            parcelFileDescriptor.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 4:
          parcelFileDescriptor.enforceInterface("android.content.pm.IPackageInstallerSession");
          parcelFileDescriptor = openWrite(parcelFileDescriptor.readString(), parcelFileDescriptor.readLong(), parcelFileDescriptor.readLong());
          paramParcel2.writeNoException();
          if (parcelFileDescriptor != null) {
            paramParcel2.writeInt(1);
            parcelFileDescriptor.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 3:
          parcelFileDescriptor.enforceInterface("android.content.pm.IPackageInstallerSession");
          arrayOfString = getNames();
          paramParcel2.writeNoException();
          paramParcel2.writeStringArray(arrayOfString);
          return true;
        case 2:
          arrayOfString.enforceInterface("android.content.pm.IPackageInstallerSession");
          addClientProgress(arrayOfString.readFloat());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      arrayOfString.enforceInterface("android.content.pm.IPackageInstallerSession");
      setClientProgress(arrayOfString.readFloat());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.content.pm.IPackageInstallerSession");
    return true;
  }
  
  private static class Proxy implements IPackageInstallerSession {
    public static IPackageInstallerSession sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void abandon() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().abandon();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addChildSessionId(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().addChildSessionId(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addClientProgress(float param2Float) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        parcel1.writeFloat(param2Float);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().addClientProgress(param2Float);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addFile(int param2Int, String param2String, long param2Long, byte[] param2ArrayOfbyte1, byte[] param2ArrayOfbyte2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        try {
          parcel1.writeInt(param2Int);
          try {
            parcel1.writeString(param2String);
            try {
              parcel1.writeLong(param2Long);
              try {
                parcel1.writeByteArray(param2ArrayOfbyte1);
                try {
                  parcel1.writeByteArray(param2ArrayOfbyte2);
                  if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
                    IPackageInstallerSession.Stub.getDefaultImpl().addFile(param2Int, param2String, param2Long, param2ArrayOfbyte1, param2ArrayOfbyte2);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void close() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().close();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void commit(IntentSender param2IntentSender, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        boolean bool = true;
        if (param2IntentSender != null) {
          parcel1.writeInt(1);
          param2IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!param2Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().commit(param2IntentSender, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int[] getChildSessionIds() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null)
          return IPackageInstallerSession.Stub.getDefaultImpl().getChildSessionIds(); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public DataLoaderParamsParcel getDataLoaderParams() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        DataLoaderParamsParcel dataLoaderParamsParcel;
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          dataLoaderParamsParcel = IPackageInstallerSession.Stub.getDefaultImpl().getDataLoaderParams();
          return dataLoaderParamsParcel;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          dataLoaderParamsParcel = (DataLoaderParamsParcel)DataLoaderParamsParcel.CREATOR.createFromParcel(parcel2);
        } else {
          dataLoaderParamsParcel = null;
        } 
        return dataLoaderParamsParcel;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IPackageInstallerSession";
    }
    
    public String[] getNames() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null)
          return IPackageInstallerSession.Stub.getDefaultImpl().getNames(); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getParentSessionId() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null)
          return IPackageInstallerSession.Stub.getDefaultImpl().getParentSessionId(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isMultiPackage() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(15, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          bool = IPackageInstallerSession.Stub.getDefaultImpl().isMultiPackage();
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
    
    public boolean isStaged() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(20, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          bool = IPackageInstallerSession.Stub.getDefaultImpl().isStaged();
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
    
    public ParcelFileDescriptor openRead(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null)
          return IPackageInstallerSession.Stub.getDefaultImpl().openRead(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParcelFileDescriptor)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor openWrite(String param2String, long param2Long1, long param2Long2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        parcel1.writeString(param2String);
        parcel1.writeLong(param2Long1);
        parcel1.writeLong(param2Long2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null)
          return IPackageInstallerSession.Stub.getDefaultImpl().openWrite(param2String, param2Long1, param2Long2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParcelFileDescriptor)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeChildSessionId(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().removeChildSessionId(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeFile(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().removeFile(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeSplit(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().removeSplit(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setClientProgress(float param2Float) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        parcel1.writeFloat(param2Float);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().setClientProgress(param2Float);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void transfer(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
          IPackageInstallerSession.Stub.getDefaultImpl().transfer(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void write(String param2String, long param2Long1, long param2Long2, ParcelFileDescriptor param2ParcelFileDescriptor) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
        try {
          parcel1.writeString(param2String);
          try {
            parcel1.writeLong(param2Long1);
            try {
              parcel1.writeLong(param2Long2);
              if (param2ParcelFileDescriptor != null) {
                parcel1.writeInt(1);
                param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
                IPackageInstallerSession.Stub.getDefaultImpl().write(param2String, param2Long1, param2Long2, param2ParcelFileDescriptor);
                parcel2.recycle();
                parcel1.recycle();
                return;
              } 
              parcel2.readException();
              parcel2.recycle();
              parcel1.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerSession$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
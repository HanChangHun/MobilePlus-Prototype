package android.app;

import android.app.backup.IBackupCallback;
import android.app.backup.IBackupManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IBackupAgent {
  private static final String DESCRIPTOR = "android.app.IBackupAgent";
  
  static final int TRANSACTION_doBackup = 1;
  
  static final int TRANSACTION_doFullBackup = 4;
  
  static final int TRANSACTION_doMeasureFullBackup = 5;
  
  static final int TRANSACTION_doQuotaExceeded = 6;
  
  static final int TRANSACTION_doRestore = 2;
  
  static final int TRANSACTION_doRestoreFile = 7;
  
  static final int TRANSACTION_doRestoreFinished = 8;
  
  static final int TRANSACTION_doRestoreWithExcludedKeys = 3;
  
  static final int TRANSACTION_fail = 9;
  
  public Stub() {
    attachInterface(this, "android.app.IBackupAgent");
  }
  
  public static IBackupAgent asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IBackupAgent");
    return (iInterface != null && iInterface instanceof IBackupAgent) ? (IBackupAgent)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBackupAgent getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 9:
        return "fail";
      case 8:
        return "doRestoreFinished";
      case 7:
        return "doRestoreFile";
      case 6:
        return "doQuotaExceeded";
      case 5:
        return "doMeasureFullBackup";
      case 4:
        return "doFullBackup";
      case 3:
        return "doRestoreWithExcludedKeys";
      case 2:
        return "doRestore";
      case 1:
        break;
    } 
    return "doBackup";
  }
  
  public static boolean setDefaultImpl(IBackupAgent paramIBackupAgent) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBackupAgent != null) {
        Proxy.sDefaultImpl = paramIBackupAgent;
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
      long l;
      ParcelFileDescriptor parcelFileDescriptor1;
      ParcelFileDescriptor parcelFileDescriptor2;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 9:
          paramParcel1.enforceInterface("android.app.IBackupAgent");
          fail(paramParcel1.readString());
          return true;
        case 8:
          paramParcel1.enforceInterface("android.app.IBackupAgent");
          doRestoreFinished(paramParcel1.readInt(), IBackupManager.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 7:
          paramParcel1.enforceInterface("android.app.IBackupAgent");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          doRestoreFile((ParcelFileDescriptor)paramParcel2, paramParcel1.readLong(), paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readLong(), paramParcel1.readInt(), IBackupManager.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.IBackupAgent");
          doQuotaExceeded(paramParcel1.readLong(), paramParcel1.readLong(), IBackupCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.IBackupAgent");
          doMeasureFullBackup(paramParcel1.readLong(), paramParcel1.readInt(), IBackupManager.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.IBackupAgent");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          doFullBackup((ParcelFileDescriptor)paramParcel2, paramParcel1.readLong(), paramParcel1.readInt(), IBackupManager.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.IBackupAgent");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          l = paramParcel1.readLong();
          if (paramParcel1.readInt() != 0) {
            parcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            parcelFileDescriptor1 = null;
          } 
          doRestoreWithExcludedKeys((ParcelFileDescriptor)paramParcel2, l, parcelFileDescriptor1, paramParcel1.readInt(), IBackupManager.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.createStringArrayList());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.IBackupAgent");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          l = paramParcel1.readLong();
          if (paramParcel1.readInt() != 0) {
            parcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            parcelFileDescriptor1 = null;
          } 
          doRestore((ParcelFileDescriptor)paramParcel2, l, parcelFileDescriptor1, paramParcel1.readInt(), IBackupManager.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.IBackupAgent");
      if (paramParcel1.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel2 = null;
      } 
      if (paramParcel1.readInt() != 0) {
        parcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
      } else {
        parcelFileDescriptor1 = null;
      } 
      if (paramParcel1.readInt() != 0) {
        parcelFileDescriptor2 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
      } else {
        parcelFileDescriptor2 = null;
      } 
      doBackup((ParcelFileDescriptor)paramParcel2, parcelFileDescriptor1, parcelFileDescriptor2, paramParcel1.readLong(), IBackupCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt());
      return true;
    } 
    paramParcel2.writeString("android.app.IBackupAgent");
    return true;
  }
  
  private static class Proxy implements IBackupAgent {
    public static IBackupAgent sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void doBackup(ParcelFileDescriptor param2ParcelFileDescriptor1, ParcelFileDescriptor param2ParcelFileDescriptor2, ParcelFileDescriptor param2ParcelFileDescriptor3, long param2Long, IBackupCallback param2IBackupCallback, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param2ParcelFileDescriptor1 != null) {
          parcel.writeInt(1);
          param2ParcelFileDescriptor1.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2ParcelFileDescriptor2 != null) {
          parcel.writeInt(1);
          param2ParcelFileDescriptor2.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2ParcelFileDescriptor3 != null) {
          parcel.writeInt(1);
          param2ParcelFileDescriptor3.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          IBinder iBinder;
          parcel.writeLong(param2Long);
          if (param2IBackupCallback != null) {
            iBinder = param2IBackupCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          try {
            parcel.writeInt(param2Int);
            if (!this.mRemote.transact(1, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
              IBackupAgent.Stub.getDefaultImpl().doBackup(param2ParcelFileDescriptor1, param2ParcelFileDescriptor2, param2ParcelFileDescriptor3, param2Long, param2IBackupCallback, param2Int);
              parcel.recycle();
              return;
            } 
            parcel.recycle();
            return;
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param2ParcelFileDescriptor1;
    }
    
    public void doFullBackup(ParcelFileDescriptor param2ParcelFileDescriptor, long param2Long, int param2Int1, IBackupManager param2IBackupManager, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param2ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param2ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeLong(param2Long);
          try {
            IBinder iBinder;
            parcel.writeInt(param2Int1);
            if (param2IBackupManager != null) {
              iBinder = param2IBackupManager.asBinder();
            } else {
              iBinder = null;
            } 
            parcel.writeStrongBinder(iBinder);
            try {
              parcel.writeInt(param2Int2);
              try {
                if (!this.mRemote.transact(4, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
                  IBackupAgent.Stub.getDefaultImpl().doFullBackup(param2ParcelFileDescriptor, param2Long, param2Int1, param2IBackupManager, param2Int2);
                  parcel.recycle();
                  return;
                } 
                parcel.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param2ParcelFileDescriptor;
    }
    
    public void doMeasureFullBackup(long param2Long, int param2Int1, IBackupManager param2IBackupManager, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        parcel.writeLong(param2Long);
        parcel.writeInt(param2Int1);
        if (param2IBackupManager != null) {
          iBinder = param2IBackupManager.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().doMeasureFullBackup(param2Long, param2Int1, param2IBackupManager, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void doQuotaExceeded(long param2Long1, long param2Long2, IBackupCallback param2IBackupCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        parcel.writeLong(param2Long1);
        parcel.writeLong(param2Long2);
        if (param2IBackupCallback != null) {
          iBinder = param2IBackupCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().doQuotaExceeded(param2Long1, param2Long2, param2IBackupCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void doRestore(ParcelFileDescriptor param2ParcelFileDescriptor1, long param2Long, ParcelFileDescriptor param2ParcelFileDescriptor2, int param2Int, IBackupManager param2IBackupManager) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param2ParcelFileDescriptor1 != null) {
          parcel.writeInt(1);
          param2ParcelFileDescriptor1.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeLong(param2Long);
          if (param2ParcelFileDescriptor2 != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor2.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            IBinder iBinder;
            parcel.writeInt(param2Int);
            if (param2IBackupManager != null) {
              iBinder = param2IBackupManager.asBinder();
            } else {
              iBinder = null;
            } 
            parcel.writeStrongBinder(iBinder);
            try {
              if (!this.mRemote.transact(2, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
                IBackupAgent.Stub.getDefaultImpl().doRestore(param2ParcelFileDescriptor1, param2Long, param2ParcelFileDescriptor2, param2Int, param2IBackupManager);
                parcel.recycle();
                return;
              } 
              parcel.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param2ParcelFileDescriptor1;
    }
    
    public void doRestoreFile(ParcelFileDescriptor param2ParcelFileDescriptor, long param2Long1, int param2Int1, String param2String1, String param2String2, long param2Long2, long param2Long3, int param2Int2, IBackupManager param2IBackupManager) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param2ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param2ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeLong(param2Long1);
        parcel.writeInt(param2Int1);
        parcel.writeString(param2String1);
        parcel.writeString(param2String2);
        parcel.writeLong(param2Long2);
        parcel.writeLong(param2Long3);
        parcel.writeInt(param2Int2);
        if (param2IBackupManager != null) {
          iBinder = param2IBackupManager.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(7, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().doRestoreFile(param2ParcelFileDescriptor, param2Long1, param2Int1, param2String1, param2String2, param2Long2, param2Long3, param2Int2, param2IBackupManager);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void doRestoreFinished(int param2Int, IBackupManager param2IBackupManager) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        parcel.writeInt(param2Int);
        if (param2IBackupManager != null) {
          iBinder = param2IBackupManager.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(8, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().doRestoreFinished(param2Int, param2IBackupManager);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void doRestoreWithExcludedKeys(ParcelFileDescriptor param2ParcelFileDescriptor1, long param2Long, ParcelFileDescriptor param2ParcelFileDescriptor2, int param2Int, IBackupManager param2IBackupManager, List<String> param2List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param2ParcelFileDescriptor1 != null) {
          parcel.writeInt(1);
          param2ParcelFileDescriptor1.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeLong(param2Long);
          if (param2ParcelFileDescriptor2 != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor2.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            IBinder iBinder;
            parcel.writeInt(param2Int);
            if (param2IBackupManager != null) {
              iBinder = param2IBackupManager.asBinder();
            } else {
              iBinder = null;
            } 
            parcel.writeStrongBinder(iBinder);
            try {
              parcel.writeStringList(param2List);
              if (!this.mRemote.transact(3, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
                IBackupAgent.Stub.getDefaultImpl().doRestoreWithExcludedKeys(param2ParcelFileDescriptor1, param2Long, param2ParcelFileDescriptor2, param2Int, param2IBackupManager, param2List);
                parcel.recycle();
                return;
              } 
              parcel.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param2ParcelFileDescriptor1;
    }
    
    public void fail(String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        parcel.writeString(param2String);
        if (!this.mRemote.transact(9, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().fail(param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IBackupAgent";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IBackupAgent$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
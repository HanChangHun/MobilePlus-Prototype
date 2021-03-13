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

public interface IBackupAgent extends IInterface {
  void doBackup(ParcelFileDescriptor paramParcelFileDescriptor1, ParcelFileDescriptor paramParcelFileDescriptor2, ParcelFileDescriptor paramParcelFileDescriptor3, long paramLong, IBackupCallback paramIBackupCallback, int paramInt) throws RemoteException;
  
  void doFullBackup(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong, int paramInt1, IBackupManager paramIBackupManager, int paramInt2) throws RemoteException;
  
  void doMeasureFullBackup(long paramLong, int paramInt1, IBackupManager paramIBackupManager, int paramInt2) throws RemoteException;
  
  void doQuotaExceeded(long paramLong1, long paramLong2, IBackupCallback paramIBackupCallback) throws RemoteException;
  
  void doRestore(ParcelFileDescriptor paramParcelFileDescriptor1, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor2, int paramInt, IBackupManager paramIBackupManager) throws RemoteException;
  
  void doRestoreFile(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, int paramInt1, String paramString1, String paramString2, long paramLong2, long paramLong3, int paramInt2, IBackupManager paramIBackupManager) throws RemoteException;
  
  void doRestoreFinished(int paramInt, IBackupManager paramIBackupManager) throws RemoteException;
  
  void doRestoreWithExcludedKeys(ParcelFileDescriptor paramParcelFileDescriptor1, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor2, int paramInt, IBackupManager paramIBackupManager, List<String> paramList) throws RemoteException;
  
  void fail(String paramString) throws RemoteException;
  
  public static class Default implements IBackupAgent {
    public IBinder asBinder() {
      return null;
    }
    
    public void doBackup(ParcelFileDescriptor param1ParcelFileDescriptor1, ParcelFileDescriptor param1ParcelFileDescriptor2, ParcelFileDescriptor param1ParcelFileDescriptor3, long param1Long, IBackupCallback param1IBackupCallback, int param1Int) throws RemoteException {}
    
    public void doFullBackup(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long, int param1Int1, IBackupManager param1IBackupManager, int param1Int2) throws RemoteException {}
    
    public void doMeasureFullBackup(long param1Long, int param1Int1, IBackupManager param1IBackupManager, int param1Int2) throws RemoteException {}
    
    public void doQuotaExceeded(long param1Long1, long param1Long2, IBackupCallback param1IBackupCallback) throws RemoteException {}
    
    public void doRestore(ParcelFileDescriptor param1ParcelFileDescriptor1, long param1Long, ParcelFileDescriptor param1ParcelFileDescriptor2, int param1Int, IBackupManager param1IBackupManager) throws RemoteException {}
    
    public void doRestoreFile(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long1, int param1Int1, String param1String1, String param1String2, long param1Long2, long param1Long3, int param1Int2, IBackupManager param1IBackupManager) throws RemoteException {}
    
    public void doRestoreFinished(int param1Int, IBackupManager param1IBackupManager) throws RemoteException {}
    
    public void doRestoreWithExcludedKeys(ParcelFileDescriptor param1ParcelFileDescriptor1, long param1Long, ParcelFileDescriptor param1ParcelFileDescriptor2, int param1Int, IBackupManager param1IBackupManager, List<String> param1List) throws RemoteException {}
    
    public void fail(String param1String) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBackupAgent {
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
    
    public static IBackupAgent asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IBackupAgent");
      return (iInterface != null && iInterface instanceof IBackupAgent) ? (IBackupAgent)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBackupAgent getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IBackupAgent param1IBackupAgent) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBackupAgent != null) {
          Proxy.sDefaultImpl = param1IBackupAgent;
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
        long l;
        ParcelFileDescriptor parcelFileDescriptor1;
        ParcelFileDescriptor parcelFileDescriptor2;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 9:
            param1Parcel1.enforceInterface("android.app.IBackupAgent");
            fail(param1Parcel1.readString());
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.app.IBackupAgent");
            doRestoreFinished(param1Parcel1.readInt(), IBackupManager.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.app.IBackupAgent");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            doRestoreFile((ParcelFileDescriptor)param1Parcel2, param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readString(), param1Parcel1.readString(), param1Parcel1.readLong(), param1Parcel1.readLong(), param1Parcel1.readInt(), IBackupManager.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.app.IBackupAgent");
            doQuotaExceeded(param1Parcel1.readLong(), param1Parcel1.readLong(), IBackupCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.app.IBackupAgent");
            doMeasureFullBackup(param1Parcel1.readLong(), param1Parcel1.readInt(), IBackupManager.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readInt());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.app.IBackupAgent");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            doFullBackup((ParcelFileDescriptor)param1Parcel2, param1Parcel1.readLong(), param1Parcel1.readInt(), IBackupManager.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readInt());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.app.IBackupAgent");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            l = param1Parcel1.readLong();
            if (param1Parcel1.readInt() != 0) {
              parcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              parcelFileDescriptor1 = null;
            } 
            doRestoreWithExcludedKeys((ParcelFileDescriptor)param1Parcel2, l, parcelFileDescriptor1, param1Parcel1.readInt(), IBackupManager.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.createStringArrayList());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.app.IBackupAgent");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            l = param1Parcel1.readLong();
            if (param1Parcel1.readInt() != 0) {
              parcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              parcelFileDescriptor1 = null;
            } 
            doRestore((ParcelFileDescriptor)param1Parcel2, l, parcelFileDescriptor1, param1Parcel1.readInt(), IBackupManager.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.app.IBackupAgent");
        if (param1Parcel1.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel2 = null;
        } 
        if (param1Parcel1.readInt() != 0) {
          parcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
        } else {
          parcelFileDescriptor1 = null;
        } 
        if (param1Parcel1.readInt() != 0) {
          parcelFileDescriptor2 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
        } else {
          parcelFileDescriptor2 = null;
        } 
        doBackup((ParcelFileDescriptor)param1Parcel2, parcelFileDescriptor1, parcelFileDescriptor2, param1Parcel1.readLong(), IBackupCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel2.writeString("android.app.IBackupAgent");
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
  
  private static class Proxy implements IBackupAgent {
    public static IBackupAgent sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void doBackup(ParcelFileDescriptor param1ParcelFileDescriptor1, ParcelFileDescriptor param1ParcelFileDescriptor2, ParcelFileDescriptor param1ParcelFileDescriptor3, long param1Long, IBackupCallback param1IBackupCallback, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param1ParcelFileDescriptor1 != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor1.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1ParcelFileDescriptor2 != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor2.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1ParcelFileDescriptor3 != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor3.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          IBinder iBinder;
          parcel.writeLong(param1Long);
          if (param1IBackupCallback != null) {
            iBinder = param1IBackupCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          try {
            parcel.writeInt(param1Int);
            if (!this.mRemote.transact(1, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
              IBackupAgent.Stub.getDefaultImpl().doBackup(param1ParcelFileDescriptor1, param1ParcelFileDescriptor2, param1ParcelFileDescriptor3, param1Long, param1IBackupCallback, param1Int);
              parcel.recycle();
              return;
            } 
            parcel.recycle();
            return;
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param1ParcelFileDescriptor1;
    }
    
    public void doFullBackup(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long, int param1Int1, IBackupManager param1IBackupManager, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeLong(param1Long);
          try {
            IBinder iBinder;
            parcel.writeInt(param1Int1);
            if (param1IBackupManager != null) {
              iBinder = param1IBackupManager.asBinder();
            } else {
              iBinder = null;
            } 
            parcel.writeStrongBinder(iBinder);
            try {
              parcel.writeInt(param1Int2);
              try {
                if (!this.mRemote.transact(4, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
                  IBackupAgent.Stub.getDefaultImpl().doFullBackup(param1ParcelFileDescriptor, param1Long, param1Int1, param1IBackupManager, param1Int2);
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
      throw param1ParcelFileDescriptor;
    }
    
    public void doMeasureFullBackup(long param1Long, int param1Int1, IBackupManager param1IBackupManager, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        if (param1IBackupManager != null) {
          iBinder = param1IBackupManager.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().doMeasureFullBackup(param1Long, param1Int1, param1IBackupManager, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void doQuotaExceeded(long param1Long1, long param1Long2, IBackupCallback param1IBackupCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        parcel.writeLong(param1Long1);
        parcel.writeLong(param1Long2);
        if (param1IBackupCallback != null) {
          iBinder = param1IBackupCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().doQuotaExceeded(param1Long1, param1Long2, param1IBackupCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void doRestore(ParcelFileDescriptor param1ParcelFileDescriptor1, long param1Long, ParcelFileDescriptor param1ParcelFileDescriptor2, int param1Int, IBackupManager param1IBackupManager) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param1ParcelFileDescriptor1 != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor1.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeLong(param1Long);
          if (param1ParcelFileDescriptor2 != null) {
            parcel.writeInt(1);
            param1ParcelFileDescriptor2.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            IBinder iBinder;
            parcel.writeInt(param1Int);
            if (param1IBackupManager != null) {
              iBinder = param1IBackupManager.asBinder();
            } else {
              iBinder = null;
            } 
            parcel.writeStrongBinder(iBinder);
            try {
              if (!this.mRemote.transact(2, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
                IBackupAgent.Stub.getDefaultImpl().doRestore(param1ParcelFileDescriptor1, param1Long, param1ParcelFileDescriptor2, param1Int, param1IBackupManager);
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
      throw param1ParcelFileDescriptor1;
    }
    
    public void doRestoreFile(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long1, int param1Int1, String param1String1, String param1String2, long param1Long2, long param1Long3, int param1Int2, IBackupManager param1IBackupManager) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeLong(param1Long1);
        parcel.writeInt(param1Int1);
        parcel.writeString(param1String1);
        parcel.writeString(param1String2);
        parcel.writeLong(param1Long2);
        parcel.writeLong(param1Long3);
        parcel.writeInt(param1Int2);
        if (param1IBackupManager != null) {
          iBinder = param1IBackupManager.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(7, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().doRestoreFile(param1ParcelFileDescriptor, param1Long1, param1Int1, param1String1, param1String2, param1Long2, param1Long3, param1Int2, param1IBackupManager);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void doRestoreFinished(int param1Int, IBackupManager param1IBackupManager) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        parcel.writeInt(param1Int);
        if (param1IBackupManager != null) {
          iBinder = param1IBackupManager.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(8, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().doRestoreFinished(param1Int, param1IBackupManager);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void doRestoreWithExcludedKeys(ParcelFileDescriptor param1ParcelFileDescriptor1, long param1Long, ParcelFileDescriptor param1ParcelFileDescriptor2, int param1Int, IBackupManager param1IBackupManager, List<String> param1List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        if (param1ParcelFileDescriptor1 != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor1.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeLong(param1Long);
          if (param1ParcelFileDescriptor2 != null) {
            parcel.writeInt(1);
            param1ParcelFileDescriptor2.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            IBinder iBinder;
            parcel.writeInt(param1Int);
            if (param1IBackupManager != null) {
              iBinder = param1IBackupManager.asBinder();
            } else {
              iBinder = null;
            } 
            parcel.writeStrongBinder(iBinder);
            try {
              parcel.writeStringList(param1List);
              if (!this.mRemote.transact(3, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
                IBackupAgent.Stub.getDefaultImpl().doRestoreWithExcludedKeys(param1ParcelFileDescriptor1, param1Long, param1ParcelFileDescriptor2, param1Int, param1IBackupManager, param1List);
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
      throw param1ParcelFileDescriptor1;
    }
    
    public void fail(String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IBackupAgent");
        parcel.writeString(param1String);
        if (!this.mRemote.transact(9, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
          IBackupAgent.Stub.getDefaultImpl().fail(param1String);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IBackupAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
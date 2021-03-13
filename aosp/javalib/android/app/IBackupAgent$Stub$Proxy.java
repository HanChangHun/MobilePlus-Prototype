package android.app;

import android.app.backup.IBackupCallback;
import android.app.backup.IBackupManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IBackupAgent {
  public static IBackupAgent sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void doBackup(ParcelFileDescriptor paramParcelFileDescriptor1, ParcelFileDescriptor paramParcelFileDescriptor2, ParcelFileDescriptor paramParcelFileDescriptor3, long paramLong, IBackupCallback paramIBackupCallback, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IBackupAgent");
      if (paramParcelFileDescriptor1 != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor1.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramParcelFileDescriptor2 != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor2.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramParcelFileDescriptor3 != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor3.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      try {
        IBinder iBinder;
        parcel.writeLong(paramLong);
        if (paramIBackupCallback != null) {
          iBinder = paramIBackupCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        try {
          parcel.writeInt(paramInt);
          if (!this.mRemote.transact(1, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
            IBackupAgent.Stub.getDefaultImpl().doBackup(paramParcelFileDescriptor1, paramParcelFileDescriptor2, paramParcelFileDescriptor3, paramLong, paramIBackupCallback, paramInt);
            parcel.recycle();
            return;
          } 
          parcel.recycle();
          return;
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramParcelFileDescriptor1;
  }
  
  public void doFullBackup(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong, int paramInt1, IBackupManager paramIBackupManager, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IBackupAgent");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      try {
        parcel.writeLong(paramLong);
        try {
          IBinder iBinder;
          parcel.writeInt(paramInt1);
          if (paramIBackupManager != null) {
            iBinder = paramIBackupManager.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          try {
            parcel.writeInt(paramInt2);
            try {
              if (!this.mRemote.transact(4, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
                IBackupAgent.Stub.getDefaultImpl().doFullBackup(paramParcelFileDescriptor, paramLong, paramInt1, paramIBackupManager, paramInt2);
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
    throw paramParcelFileDescriptor;
  }
  
  public void doMeasureFullBackup(long paramLong, int paramInt1, IBackupManager paramIBackupManager, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IBackupAgent");
      parcel.writeLong(paramLong);
      parcel.writeInt(paramInt1);
      if (paramIBackupManager != null) {
        iBinder = paramIBackupManager.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(5, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
        IBackupAgent.Stub.getDefaultImpl().doMeasureFullBackup(paramLong, paramInt1, paramIBackupManager, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void doQuotaExceeded(long paramLong1, long paramLong2, IBackupCallback paramIBackupCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IBackupAgent");
      parcel.writeLong(paramLong1);
      parcel.writeLong(paramLong2);
      if (paramIBackupCallback != null) {
        iBinder = paramIBackupCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(6, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
        IBackupAgent.Stub.getDefaultImpl().doQuotaExceeded(paramLong1, paramLong2, paramIBackupCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void doRestore(ParcelFileDescriptor paramParcelFileDescriptor1, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor2, int paramInt, IBackupManager paramIBackupManager) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IBackupAgent");
      if (paramParcelFileDescriptor1 != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor1.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      try {
        parcel.writeLong(paramLong);
        if (paramParcelFileDescriptor2 != null) {
          parcel.writeInt(1);
          paramParcelFileDescriptor2.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          IBinder iBinder;
          parcel.writeInt(paramInt);
          if (paramIBackupManager != null) {
            iBinder = paramIBackupManager.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          try {
            if (!this.mRemote.transact(2, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
              IBackupAgent.Stub.getDefaultImpl().doRestore(paramParcelFileDescriptor1, paramLong, paramParcelFileDescriptor2, paramInt, paramIBackupManager);
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
    throw paramParcelFileDescriptor1;
  }
  
  public void doRestoreFile(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, int paramInt1, String paramString1, String paramString2, long paramLong2, long paramLong3, int paramInt2, IBackupManager paramIBackupManager) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IBackupAgent");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeLong(paramLong1);
      parcel.writeInt(paramInt1);
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      parcel.writeLong(paramLong2);
      parcel.writeLong(paramLong3);
      parcel.writeInt(paramInt2);
      if (paramIBackupManager != null) {
        iBinder = paramIBackupManager.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(7, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
        IBackupAgent.Stub.getDefaultImpl().doRestoreFile(paramParcelFileDescriptor, paramLong1, paramInt1, paramString1, paramString2, paramLong2, paramLong3, paramInt2, paramIBackupManager);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void doRestoreFinished(int paramInt, IBackupManager paramIBackupManager) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IBackupAgent");
      parcel.writeInt(paramInt);
      if (paramIBackupManager != null) {
        iBinder = paramIBackupManager.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(8, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
        IBackupAgent.Stub.getDefaultImpl().doRestoreFinished(paramInt, paramIBackupManager);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void doRestoreWithExcludedKeys(ParcelFileDescriptor paramParcelFileDescriptor1, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor2, int paramInt, IBackupManager paramIBackupManager, List<String> paramList) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IBackupAgent");
      if (paramParcelFileDescriptor1 != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor1.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      try {
        parcel.writeLong(paramLong);
        if (paramParcelFileDescriptor2 != null) {
          parcel.writeInt(1);
          paramParcelFileDescriptor2.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          IBinder iBinder;
          parcel.writeInt(paramInt);
          if (paramIBackupManager != null) {
            iBinder = paramIBackupManager.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          try {
            parcel.writeStringList(paramList);
            if (!this.mRemote.transact(3, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
              IBackupAgent.Stub.getDefaultImpl().doRestoreWithExcludedKeys(paramParcelFileDescriptor1, paramLong, paramParcelFileDescriptor2, paramInt, paramIBackupManager, paramList);
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
    throw paramParcelFileDescriptor1;
  }
  
  public void fail(String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IBackupAgent");
      parcel.writeString(paramString);
      if (!this.mRemote.transact(9, parcel, null, 1) && IBackupAgent.Stub.getDefaultImpl() != null) {
        IBackupAgent.Stub.getDefaultImpl().fail(paramString);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IBackupAgent$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
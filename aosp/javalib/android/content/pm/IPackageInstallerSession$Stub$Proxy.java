package android.content.pm;

import android.content.IntentSender;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

class Proxy implements IPackageInstallerSession {
  public static IPackageInstallerSession sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
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
  
  public void addChildSessionId(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
        IPackageInstallerSession.Stub.getDefaultImpl().addChildSessionId(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addClientProgress(float paramFloat) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      parcel1.writeFloat(paramFloat);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
        IPackageInstallerSession.Stub.getDefaultImpl().addClientProgress(paramFloat);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addFile(int paramInt, String paramString, long paramLong, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      try {
        parcel1.writeInt(paramInt);
        try {
          parcel1.writeString(paramString);
          try {
            parcel1.writeLong(paramLong);
            try {
              parcel1.writeByteArray(paramArrayOfbyte1);
              try {
                parcel1.writeByteArray(paramArrayOfbyte2);
                if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
                  IPackageInstallerSession.Stub.getDefaultImpl().addFile(paramInt, paramString, paramLong, paramArrayOfbyte1, paramArrayOfbyte2);
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
    throw paramString;
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
  
  public void commit(IntentSender paramIntentSender, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      boolean bool = true;
      if (paramIntentSender != null) {
        parcel1.writeInt(1);
        paramIntentSender.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
        IPackageInstallerSession.Stub.getDefaultImpl().commit(paramIntentSender, paramBoolean);
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
  
  public ParcelFileDescriptor openRead(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null)
        return IPackageInstallerSession.Stub.getDefaultImpl().openRead(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParcelFileDescriptor)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParcelFileDescriptor openWrite(String paramString, long paramLong1, long paramLong2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      parcel1.writeString(paramString);
      parcel1.writeLong(paramLong1);
      parcel1.writeLong(paramLong2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null)
        return IPackageInstallerSession.Stub.getDefaultImpl().openWrite(paramString, paramLong1, paramLong2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParcelFileDescriptor)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeChildSessionId(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
        IPackageInstallerSession.Stub.getDefaultImpl().removeChildSessionId(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeFile(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
        IPackageInstallerSession.Stub.getDefaultImpl().removeFile(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeSplit(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
        IPackageInstallerSession.Stub.getDefaultImpl().removeSplit(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setClientProgress(float paramFloat) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      parcel1.writeFloat(paramFloat);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
        IPackageInstallerSession.Stub.getDefaultImpl().setClientProgress(paramFloat);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void transfer(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
        IPackageInstallerSession.Stub.getDefaultImpl().transfer(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void write(String paramString, long paramLong1, long paramLong2, ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IPackageInstallerSession");
      try {
        parcel1.writeString(paramString);
        try {
          parcel1.writeLong(paramLong1);
          try {
            parcel1.writeLong(paramLong2);
            if (paramParcelFileDescriptor != null) {
              parcel1.writeInt(1);
              paramParcelFileDescriptor.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IPackageInstallerSession.Stub.getDefaultImpl() != null) {
              IPackageInstallerSession.Stub.getDefaultImpl().write(paramString, paramLong1, paramLong2, paramParcelFileDescriptor);
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
    throw paramString;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerSession$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
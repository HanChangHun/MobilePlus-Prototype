package android.gsi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IGsiService {
  public static IGsiService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
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
  
  public boolean commitGsiChunkFromAshmem(long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IGsiService");
      parcel1.writeLong(paramLong);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(4, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
        bool = IGsiService.Stub.getDefaultImpl().commitGsiChunkFromAshmem(paramLong);
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
  
  public boolean commitGsiChunkFromStream(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IGsiService");
      boolean bool = true;
      if (paramParcelFileDescriptor != null) {
        parcel1.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
        bool = IGsiService.Stub.getDefaultImpl().commitGsiChunkFromStream(paramParcelFileDescriptor, paramLong);
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
  
  public int createPartition(String paramString, long paramLong, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IGsiService");
      parcel1.writeString(paramString);
      parcel1.writeLong(paramLong);
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
        i = IGsiService.Stub.getDefaultImpl().createPartition(paramString, paramLong, paramBoolean);
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
  
  public int enableGsi(boolean paramBoolean, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IGsiService");
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
        i = IGsiService.Stub.getDefaultImpl().enableGsi(paramBoolean, paramString);
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
  
  public void enableGsiAsync(boolean paramBoolean, String paramString, IGsiServiceCallback paramIGsiServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      IBinder iBinder;
      parcel.writeInterfaceToken("android.gsi.IGsiService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeString(paramString);
      if (paramIGsiServiceCallback != null) {
        iBinder = paramIGsiServiceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(6, parcel, null, 1) && IGsiService.Stub.getDefaultImpl() != null) {
        IGsiService.Stub.getDefaultImpl().enableGsiAsync(paramBoolean, paramString, paramIGsiServiceCallback);
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
  
  public int getAvbPublicKey(AvbPublicKey paramAvbPublicKey) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IGsiService");
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
        return IGsiService.Stub.getDefaultImpl().getAvbPublicKey(paramAvbPublicKey); 
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
  
  public IImageService openImageService(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IGsiService");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
        return IGsiService.Stub.getDefaultImpl().openImageService(paramString); 
      parcel2.readException();
      return IImageService.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int openInstall(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IGsiService");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
        return IGsiService.Stub.getDefaultImpl().openInstall(paramString); 
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
  
  public void removeGsiAsync(IGsiServiceCallback paramIGsiServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.gsi.IGsiService");
      if (paramIGsiServiceCallback != null) {
        iBinder = paramIGsiServiceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(11, parcel, null, 1) && IGsiService.Stub.getDefaultImpl() != null) {
        IGsiService.Stub.getDefaultImpl().removeGsiAsync(paramIGsiServiceCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public boolean setGsiAshmem(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IGsiService");
      boolean bool = true;
      if (paramParcelFileDescriptor != null) {
        parcel1.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null) {
        bool = IGsiService.Stub.getDefaultImpl().setGsiAshmem(paramParcelFileDescriptor, paramLong);
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
  
  public int zeroPartition(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IGsiService");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IGsiService.Stub.getDefaultImpl() != null)
        return IGsiService.Stub.getDefaultImpl().zeroPartition(paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IGsiService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
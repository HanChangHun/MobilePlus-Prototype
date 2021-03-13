package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IOtaDexopt {
  public static IOtaDexopt sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void cleanup() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null) {
        IOtaDexopt.Stub.getDefaultImpl().cleanup();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void dexoptNextPackage() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null) {
        IOtaDexopt.Stub.getDefaultImpl().dexoptNextPackage();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IOtaDexopt";
  }
  
  public float getProgress() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null)
        return IOtaDexopt.Stub.getDefaultImpl().getProgress(); 
      parcel2.readException();
      return parcel2.readFloat();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isDone() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(3, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null) {
        bool = IOtaDexopt.Stub.getDefaultImpl().isDone();
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
  
  public String nextDexoptCommand() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null)
        return IOtaDexopt.Stub.getDefaultImpl().nextDexoptCommand(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void prepare() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IOtaDexopt");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IOtaDexopt.Stub.getDefaultImpl() != null) {
        IOtaDexopt.Stub.getDefaultImpl().prepare();
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IOtaDexopt$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
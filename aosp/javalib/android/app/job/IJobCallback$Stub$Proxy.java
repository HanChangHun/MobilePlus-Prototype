package android.app.job;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IJobCallback {
  public static IJobCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void acknowledgeStartMessage(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.job.IJobCallback");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
        IJobCallback.Stub.getDefaultImpl().acknowledgeStartMessage(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void acknowledgeStopMessage(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.job.IJobCallback");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
        IJobCallback.Stub.getDefaultImpl().acknowledgeStopMessage(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean completeWork(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.job.IJobCallback");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(4, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
        bool = IJobCallback.Stub.getDefaultImpl().completeWork(paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public JobWorkItem dequeueWork(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      JobWorkItem jobWorkItem;
      parcel1.writeInterfaceToken("android.app.job.IJobCallback");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
        jobWorkItem = IJobCallback.Stub.getDefaultImpl().dequeueWork(paramInt);
        return jobWorkItem;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        jobWorkItem = (JobWorkItem)JobWorkItem.CREATOR.createFromParcel(parcel2);
      } else {
        jobWorkItem = null;
      } 
      return jobWorkItem;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.job.IJobCallback";
  }
  
  public void jobFinished(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.job.IJobCallback");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
        IJobCallback.Stub.getDefaultImpl().jobFinished(paramInt, paramBoolean);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
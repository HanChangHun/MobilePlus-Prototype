package android.app.job;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IJobCallback {
  private static final String DESCRIPTOR = "android.app.job.IJobCallback";
  
  static final int TRANSACTION_acknowledgeStartMessage = 1;
  
  static final int TRANSACTION_acknowledgeStopMessage = 2;
  
  static final int TRANSACTION_completeWork = 4;
  
  static final int TRANSACTION_dequeueWork = 3;
  
  static final int TRANSACTION_jobFinished = 5;
  
  public Stub() {
    attachInterface(this, "android.app.job.IJobCallback");
  }
  
  public static IJobCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.job.IJobCallback");
    return (iInterface != null && iInterface instanceof IJobCallback) ? (IJobCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IJobCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "jobFinished") : "completeWork") : "dequeueWork") : "acknowledgeStopMessage") : "acknowledgeStartMessage";
  }
  
  public static boolean setDefaultImpl(IJobCallback paramIJobCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIJobCallback != null) {
        Proxy.sDefaultImpl = paramIJobCallback;
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
    JobWorkItem jobWorkItem;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.app.job.IJobCallback");
              return true;
            } 
            paramParcel1.enforceInterface("android.app.job.IJobCallback");
            paramInt1 = paramParcel1.readInt();
            if (paramParcel1.readInt() != 0)
              bool3 = true; 
            jobFinished(paramInt1, bool3);
            paramParcel2.writeNoException();
            return true;
          } 
          paramParcel1.enforceInterface("android.app.job.IJobCallback");
          boolean bool = completeWork(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        } 
        paramParcel1.enforceInterface("android.app.job.IJobCallback");
        jobWorkItem = dequeueWork(paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (jobWorkItem != null) {
          paramParcel2.writeInt(1);
          jobWorkItem.writeToParcel(paramParcel2, 1);
        } else {
          paramParcel2.writeInt(0);
        } 
        return true;
      } 
      jobWorkItem.enforceInterface("android.app.job.IJobCallback");
      paramInt1 = jobWorkItem.readInt();
      bool3 = bool1;
      if (jobWorkItem.readInt() != 0)
        bool3 = true; 
      acknowledgeStopMessage(paramInt1, bool3);
      paramParcel2.writeNoException();
      return true;
    } 
    jobWorkItem.enforceInterface("android.app.job.IJobCallback");
    paramInt1 = jobWorkItem.readInt();
    bool3 = bool2;
    if (jobWorkItem.readInt() != 0)
      bool3 = true; 
    acknowledgeStartMessage(paramInt1, bool3);
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IJobCallback {
    public static IJobCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void acknowledgeStartMessage(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.job.IJobCallback");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
          IJobCallback.Stub.getDefaultImpl().acknowledgeStartMessage(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void acknowledgeStopMessage(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.job.IJobCallback");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
          IJobCallback.Stub.getDefaultImpl().acknowledgeStopMessage(param2Int, param2Boolean);
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
    
    public boolean completeWork(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.job.IJobCallback");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
          bool = IJobCallback.Stub.getDefaultImpl().completeWork(param2Int1, param2Int2);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public JobWorkItem dequeueWork(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        JobWorkItem jobWorkItem;
        parcel1.writeInterfaceToken("android.app.job.IJobCallback");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
          jobWorkItem = IJobCallback.Stub.getDefaultImpl().dequeueWork(param2Int);
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
    
    public void jobFinished(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.job.IJobCallback");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IJobCallback.Stub.getDefaultImpl() != null) {
          IJobCallback.Stub.getDefaultImpl().jobFinished(param2Int, param2Boolean);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
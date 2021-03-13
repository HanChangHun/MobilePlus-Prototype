package android.app.job;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IJobService {
  private static final String DESCRIPTOR = "android.app.job.IJobService";
  
  static final int TRANSACTION_startJob = 1;
  
  static final int TRANSACTION_stopJob = 2;
  
  public Stub() {
    attachInterface(this, "android.app.job.IJobService");
  }
  
  public static IJobService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.job.IJobService");
    return (iInterface != null && iInterface instanceof IJobService) ? (IJobService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IJobService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "stopJob") : "startJob";
  }
  
  public static boolean setDefaultImpl(IJobService paramIJobService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIJobService != null) {
        Proxy.sDefaultImpl = paramIJobService;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.app.job.IJobService");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.job.IJobService");
      if (paramParcel1.readInt() != 0) {
        JobParameters jobParameters = (JobParameters)JobParameters.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      stopJob((JobParameters)paramParcel1);
      return true;
    } 
    paramParcel1.enforceInterface("android.app.job.IJobService");
    if (paramParcel1.readInt() != 0) {
      JobParameters jobParameters = (JobParameters)JobParameters.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    startJob((JobParameters)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IJobService {
    public static IJobService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.job.IJobService";
    }
    
    public void startJob(JobParameters param2JobParameters) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.job.IJobService");
        if (param2JobParameters != null) {
          parcel.writeInt(1);
          param2JobParameters.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IJobService.Stub.getDefaultImpl() != null) {
          IJobService.Stub.getDefaultImpl().startJob(param2JobParameters);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void stopJob(JobParameters param2JobParameters) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.job.IJobService");
        if (param2JobParameters != null) {
          parcel.writeInt(1);
          param2JobParameters.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IJobService.Stub.getDefaultImpl() != null) {
          IJobService.Stub.getDefaultImpl().stopJob(param2JobParameters);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
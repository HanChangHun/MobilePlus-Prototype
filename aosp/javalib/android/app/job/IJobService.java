package android.app.job;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IJobService extends IInterface {
  void startJob(JobParameters paramJobParameters) throws RemoteException;
  
  void stopJob(JobParameters paramJobParameters) throws RemoteException;
  
  public static class Default implements IJobService {
    public IBinder asBinder() {
      return null;
    }
    
    public void startJob(JobParameters param1JobParameters) throws RemoteException {}
    
    public void stopJob(JobParameters param1JobParameters) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IJobService {
    private static final String DESCRIPTOR = "android.app.job.IJobService";
    
    static final int TRANSACTION_startJob = 1;
    
    static final int TRANSACTION_stopJob = 2;
    
    public Stub() {
      attachInterface(this, "android.app.job.IJobService");
    }
    
    public static IJobService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.job.IJobService");
      return (iInterface != null && iInterface instanceof IJobService) ? (IJobService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IJobService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "stopJob") : "startJob";
    }
    
    public static boolean setDefaultImpl(IJobService param1IJobService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IJobService != null) {
          Proxy.sDefaultImpl = param1IJobService;
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
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.job.IJobService");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.job.IJobService");
        if (param1Parcel1.readInt() != 0) {
          JobParameters jobParameters = (JobParameters)JobParameters.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        stopJob((JobParameters)param1Parcel1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.job.IJobService");
      if (param1Parcel1.readInt() != 0) {
        JobParameters jobParameters = (JobParameters)JobParameters.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      startJob((JobParameters)param1Parcel1);
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
  
  private static class Proxy implements IJobService {
    public static IJobService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.job.IJobService";
    }
    
    public void startJob(JobParameters param1JobParameters) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.job.IJobService");
        if (param1JobParameters != null) {
          parcel.writeInt(1);
          param1JobParameters.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IJobService.Stub.getDefaultImpl() != null) {
          IJobService.Stub.getDefaultImpl().startJob(param1JobParameters);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void stopJob(JobParameters param1JobParameters) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.job.IJobService");
        if (param1JobParameters != null) {
          parcel.writeInt(1);
          param1JobParameters.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IJobService.Stub.getDefaultImpl() != null) {
          IJobService.Stub.getDefaultImpl().stopJob(param1JobParameters);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
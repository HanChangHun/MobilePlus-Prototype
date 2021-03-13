package android.app.job;

import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IJobScheduler extends IInterface {
  void cancel(int paramInt) throws RemoteException;
  
  void cancelAll() throws RemoteException;
  
  int enqueue(JobInfo paramJobInfo, JobWorkItem paramJobWorkItem) throws RemoteException;
  
  ParceledListSlice getAllJobSnapshots() throws RemoteException;
  
  ParceledListSlice getAllPendingJobs() throws RemoteException;
  
  JobInfo getPendingJob(int paramInt) throws RemoteException;
  
  List<JobInfo> getStartedJobs() throws RemoteException;
  
  int schedule(JobInfo paramJobInfo) throws RemoteException;
  
  int scheduleAsPackage(JobInfo paramJobInfo, String paramString1, int paramInt, String paramString2) throws RemoteException;
  
  public static class Default implements IJobScheduler {
    public IBinder asBinder() {
      return null;
    }
    
    public void cancel(int param1Int) throws RemoteException {}
    
    public void cancelAll() throws RemoteException {}
    
    public int enqueue(JobInfo param1JobInfo, JobWorkItem param1JobWorkItem) throws RemoteException {
      return 0;
    }
    
    public ParceledListSlice getAllJobSnapshots() throws RemoteException {
      return null;
    }
    
    public ParceledListSlice getAllPendingJobs() throws RemoteException {
      return null;
    }
    
    public JobInfo getPendingJob(int param1Int) throws RemoteException {
      return null;
    }
    
    public List<JobInfo> getStartedJobs() throws RemoteException {
      return null;
    }
    
    public int schedule(JobInfo param1JobInfo) throws RemoteException {
      return 0;
    }
    
    public int scheduleAsPackage(JobInfo param1JobInfo, String param1String1, int param1Int, String param1String2) throws RemoteException {
      return 0;
    }
  }
  
  public static abstract class Stub extends Binder implements IJobScheduler {
    private static final String DESCRIPTOR = "android.app.job.IJobScheduler";
    
    static final int TRANSACTION_cancel = 4;
    
    static final int TRANSACTION_cancelAll = 5;
    
    static final int TRANSACTION_enqueue = 2;
    
    static final int TRANSACTION_getAllJobSnapshots = 9;
    
    static final int TRANSACTION_getAllPendingJobs = 6;
    
    static final int TRANSACTION_getPendingJob = 7;
    
    static final int TRANSACTION_getStartedJobs = 8;
    
    static final int TRANSACTION_schedule = 1;
    
    static final int TRANSACTION_scheduleAsPackage = 3;
    
    public Stub() {
      attachInterface(this, "android.app.job.IJobScheduler");
    }
    
    public static IJobScheduler asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.job.IJobScheduler");
      return (iInterface != null && iInterface instanceof IJobScheduler) ? (IJobScheduler)iInterface : new Proxy(param1IBinder);
    }
    
    public static IJobScheduler getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 9:
          return "getAllJobSnapshots";
        case 8:
          return "getStartedJobs";
        case 7:
          return "getPendingJob";
        case 6:
          return "getAllPendingJobs";
        case 5:
          return "cancelAll";
        case 4:
          return "cancel";
        case 3:
          return "scheduleAsPackage";
        case 2:
          return "enqueue";
        case 1:
          break;
      } 
      return "schedule";
    }
    
    public static boolean setDefaultImpl(IJobScheduler param1IJobScheduler) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IJobScheduler != null) {
          Proxy.sDefaultImpl = param1IJobScheduler;
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
        ParceledListSlice parceledListSlice2;
        List<JobInfo> list;
        JobInfo jobInfo1;
        ParceledListSlice parceledListSlice1;
        JobInfo jobInfo2;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 9:
            param1Parcel1.enforceInterface("android.app.job.IJobScheduler");
            parceledListSlice2 = getAllJobSnapshots();
            param1Parcel2.writeNoException();
            if (parceledListSlice2 != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice2.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 8:
            parceledListSlice2.enforceInterface("android.app.job.IJobScheduler");
            list = getStartedJobs();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 7:
            list.enforceInterface("android.app.job.IJobScheduler");
            jobInfo1 = getPendingJob(list.readInt());
            param1Parcel2.writeNoException();
            if (jobInfo1 != null) {
              param1Parcel2.writeInt(1);
              jobInfo1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 6:
            jobInfo1.enforceInterface("android.app.job.IJobScheduler");
            parceledListSlice1 = getAllPendingJobs();
            param1Parcel2.writeNoException();
            if (parceledListSlice1 != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 5:
            parceledListSlice1.enforceInterface("android.app.job.IJobScheduler");
            cancelAll();
            param1Parcel2.writeNoException();
            return true;
          case 4:
            parceledListSlice1.enforceInterface("android.app.job.IJobScheduler");
            cancel(parceledListSlice1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 3:
            parceledListSlice1.enforceInterface("android.app.job.IJobScheduler");
            if (parceledListSlice1.readInt() != 0) {
              jobInfo2 = (JobInfo)JobInfo.CREATOR.createFromParcel((Parcel)parceledListSlice1);
            } else {
              jobInfo2 = null;
            } 
            param1Int1 = scheduleAsPackage(jobInfo2, parceledListSlice1.readString(), parceledListSlice1.readInt(), parceledListSlice1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 2:
            parceledListSlice1.enforceInterface("android.app.job.IJobScheduler");
            if (parceledListSlice1.readInt() != 0) {
              jobInfo2 = (JobInfo)JobInfo.CREATOR.createFromParcel((Parcel)parceledListSlice1);
            } else {
              jobInfo2 = null;
            } 
            if (parceledListSlice1.readInt() != 0) {
              JobWorkItem jobWorkItem = (JobWorkItem)JobWorkItem.CREATOR.createFromParcel((Parcel)parceledListSlice1);
            } else {
              parceledListSlice1 = null;
            } 
            param1Int1 = enqueue(jobInfo2, (JobWorkItem)parceledListSlice1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 1:
            break;
        } 
        parceledListSlice1.enforceInterface("android.app.job.IJobScheduler");
        if (parceledListSlice1.readInt() != 0) {
          JobInfo jobInfo = (JobInfo)JobInfo.CREATOR.createFromParcel((Parcel)parceledListSlice1);
        } else {
          parceledListSlice1 = null;
        } 
        param1Int1 = schedule((JobInfo)parceledListSlice1);
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(param1Int1);
        return true;
      } 
      param1Parcel2.writeString("android.app.job.IJobScheduler");
      return true;
    }
    
    private static class Proxy implements IJobScheduler {
      public static IJobScheduler sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void cancel(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
            IJobScheduler.Stub.getDefaultImpl().cancel(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void cancelAll() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
            IJobScheduler.Stub.getDefaultImpl().cancelAll();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int enqueue(JobInfo param2JobInfo, JobWorkItem param2JobWorkItem) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
          if (param2JobInfo != null) {
            parcel1.writeInt(1);
            param2JobInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2JobWorkItem != null) {
            parcel1.writeInt(1);
            param2JobWorkItem.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null)
            return IJobScheduler.Stub.getDefaultImpl().enqueue(param2JobInfo, param2JobWorkItem); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParceledListSlice getAllJobSnapshots() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ParceledListSlice parceledListSlice;
          parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
            parceledListSlice = IJobScheduler.Stub.getDefaultImpl().getAllJobSnapshots();
            return parceledListSlice;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
          } else {
            parceledListSlice = null;
          } 
          return parceledListSlice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParceledListSlice getAllPendingJobs() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ParceledListSlice parceledListSlice;
          parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
            parceledListSlice = IJobScheduler.Stub.getDefaultImpl().getAllPendingJobs();
            return parceledListSlice;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
          } else {
            parceledListSlice = null;
          } 
          return parceledListSlice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.job.IJobScheduler";
      }
      
      public JobInfo getPendingJob(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          JobInfo jobInfo;
          parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
            jobInfo = IJobScheduler.Stub.getDefaultImpl().getPendingJob(param2Int);
            return jobInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            jobInfo = (JobInfo)JobInfo.CREATOR.createFromParcel(parcel2);
          } else {
            jobInfo = null;
          } 
          return jobInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<JobInfo> getStartedJobs() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null)
            return IJobScheduler.Stub.getDefaultImpl().getStartedJobs(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(JobInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int schedule(JobInfo param2JobInfo) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
          if (param2JobInfo != null) {
            parcel1.writeInt(1);
            param2JobInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null)
            return IJobScheduler.Stub.getDefaultImpl().schedule(param2JobInfo); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int scheduleAsPackage(JobInfo param2JobInfo, String param2String1, int param2Int, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
          if (param2JobInfo != null) {
            parcel1.writeInt(1);
            param2JobInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String1);
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
            param2Int = IJobScheduler.Stub.getDefaultImpl().scheduleAsPackage(param2JobInfo, param2String1, param2Int, param2String2);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IJobScheduler {
    public static IJobScheduler sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void cancel(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
          IJobScheduler.Stub.getDefaultImpl().cancel(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelAll() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
          IJobScheduler.Stub.getDefaultImpl().cancelAll();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int enqueue(JobInfo param1JobInfo, JobWorkItem param1JobWorkItem) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
        if (param1JobInfo != null) {
          parcel1.writeInt(1);
          param1JobInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1JobWorkItem != null) {
          parcel1.writeInt(1);
          param1JobWorkItem.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null)
          return IJobScheduler.Stub.getDefaultImpl().enqueue(param1JobInfo, param1JobWorkItem); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getAllJobSnapshots() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
          parceledListSlice = IJobScheduler.Stub.getDefaultImpl().getAllJobSnapshots();
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getAllPendingJobs() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
          parceledListSlice = IJobScheduler.Stub.getDefaultImpl().getAllPendingJobs();
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.job.IJobScheduler";
    }
    
    public JobInfo getPendingJob(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        JobInfo jobInfo;
        parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
          jobInfo = IJobScheduler.Stub.getDefaultImpl().getPendingJob(param1Int);
          return jobInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          jobInfo = (JobInfo)JobInfo.CREATOR.createFromParcel(parcel2);
        } else {
          jobInfo = null;
        } 
        return jobInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<JobInfo> getStartedJobs() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null)
          return IJobScheduler.Stub.getDefaultImpl().getStartedJobs(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(JobInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int schedule(JobInfo param1JobInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
        if (param1JobInfo != null) {
          parcel1.writeInt(1);
          param1JobInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null)
          return IJobScheduler.Stub.getDefaultImpl().schedule(param1JobInfo); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int scheduleAsPackage(JobInfo param1JobInfo, String param1String1, int param1Int, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
        if (param1JobInfo != null) {
          parcel1.writeInt(1);
          param1JobInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String1);
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
          param1Int = IJobScheduler.Stub.getDefaultImpl().scheduleAsPackage(param1JobInfo, param1String1, param1Int, param1String2);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
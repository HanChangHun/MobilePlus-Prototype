package android.app.job;

import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IJobScheduler {
  public static IJobScheduler sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void cancel(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
        IJobScheduler.Stub.getDefaultImpl().cancel(paramInt);
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
  
  public int enqueue(JobInfo paramJobInfo, JobWorkItem paramJobWorkItem) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
      if (paramJobInfo != null) {
        parcel1.writeInt(1);
        paramJobInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramJobWorkItem != null) {
        parcel1.writeInt(1);
        paramJobWorkItem.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null)
        return IJobScheduler.Stub.getDefaultImpl().enqueue(paramJobInfo, paramJobWorkItem); 
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
  
  public JobInfo getPendingJob(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      JobInfo jobInfo;
      parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
        jobInfo = IJobScheduler.Stub.getDefaultImpl().getPendingJob(paramInt);
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
  
  public int schedule(JobInfo paramJobInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
      if (paramJobInfo != null) {
        parcel1.writeInt(1);
        paramJobInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null)
        return IJobScheduler.Stub.getDefaultImpl().schedule(paramJobInfo); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int scheduleAsPackage(JobInfo paramJobInfo, String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.job.IJobScheduler");
      if (paramJobInfo != null) {
        parcel1.writeInt(1);
        paramJobInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IJobScheduler.Stub.getDefaultImpl() != null) {
        paramInt = IJobScheduler.Stub.getDefaultImpl().scheduleAsPackage(paramJobInfo, paramString1, paramInt, paramString2);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobScheduler$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
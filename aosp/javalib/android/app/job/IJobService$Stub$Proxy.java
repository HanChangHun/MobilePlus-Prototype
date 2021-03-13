package android.app.job;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IJobService {
  public static IJobService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.job.IJobService";
  }
  
  public void startJob(JobParameters paramJobParameters) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.job.IJobService");
      if (paramJobParameters != null) {
        parcel.writeInt(1);
        paramJobParameters.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IJobService.Stub.getDefaultImpl() != null) {
        IJobService.Stub.getDefaultImpl().startJob(paramJobParameters);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void stopJob(JobParameters paramJobParameters) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.job.IJobService");
      if (paramJobParameters != null) {
        parcel.writeInt(1);
        paramJobParameters.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IJobService.Stub.getDefaultImpl() != null) {
        IJobService.Stub.getDefaultImpl().stopJob(paramJobParameters);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/IJobService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.job;

import android.content.ClipData;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.RemoteException;

public class JobParameters implements Parcelable {
  public static final Parcelable.Creator<JobParameters> CREATOR;
  
  public static final int[] JOB_STOP_REASON_CODES = new int[] { 0, 1, 2, 3, 4, 5, 6 };
  
  public static final int REASON_CANCELED = 0;
  
  public static final int REASON_CONSTRAINTS_NOT_SATISFIED = 1;
  
  public static final int REASON_DEVICE_IDLE = 4;
  
  public static final int REASON_DEVICE_THERMAL = 5;
  
  public static final int REASON_PREEMPT = 2;
  
  public static final int REASON_RESTRICTED_BUCKET = 6;
  
  public static final int REASON_TIMEOUT = 3;
  
  private final IBinder callback;
  
  private final ClipData clipData;
  
  private final int clipGrantFlags;
  
  private String debugStopReason;
  
  private final PersistableBundle extras;
  
  private final int jobId;
  
  private final String[] mTriggeredContentAuthorities;
  
  private final Uri[] mTriggeredContentUris;
  
  private final Network network;
  
  private final boolean overrideDeadlineExpired;
  
  private int stopReason;
  
  private final Bundle transientExtras;
  
  static {
    CREATOR = new Parcelable.Creator<JobParameters>() {
        public JobParameters createFromParcel(Parcel param1Parcel) {
          return new JobParameters(param1Parcel);
        }
        
        public JobParameters[] newArray(int param1Int) {
          return new JobParameters[param1Int];
        }
      };
  }
  
  public JobParameters(IBinder paramIBinder, int paramInt1, PersistableBundle paramPersistableBundle, Bundle paramBundle, ClipData paramClipData, int paramInt2, boolean paramBoolean, Uri[] paramArrayOfUri, String[] paramArrayOfString, Network paramNetwork) {
    this.jobId = paramInt1;
    this.extras = paramPersistableBundle;
    this.transientExtras = paramBundle;
    this.clipData = paramClipData;
    this.clipGrantFlags = paramInt2;
    this.callback = paramIBinder;
    this.overrideDeadlineExpired = paramBoolean;
    this.mTriggeredContentUris = paramArrayOfUri;
    this.mTriggeredContentAuthorities = paramArrayOfString;
    this.network = paramNetwork;
  }
  
  private JobParameters(Parcel paramParcel) {
    this.jobId = paramParcel.readInt();
    this.extras = paramParcel.readPersistableBundle();
    this.transientExtras = paramParcel.readBundle();
    int i = paramParcel.readInt();
    boolean bool = false;
    if (i != 0) {
      this.clipData = (ClipData)ClipData.CREATOR.createFromParcel(paramParcel);
      this.clipGrantFlags = paramParcel.readInt();
    } else {
      this.clipData = null;
      this.clipGrantFlags = 0;
    } 
    this.callback = paramParcel.readStrongBinder();
    if (paramParcel.readInt() == 1)
      bool = true; 
    this.overrideDeadlineExpired = bool;
    this.mTriggeredContentUris = (Uri[])paramParcel.createTypedArray(Uri.CREATOR);
    this.mTriggeredContentAuthorities = paramParcel.createStringArray();
    if (paramParcel.readInt() != 0) {
      this.network = (Network)Network.CREATOR.createFromParcel(paramParcel);
    } else {
      this.network = null;
    } 
    this.stopReason = paramParcel.readInt();
    this.debugStopReason = paramParcel.readString();
  }
  
  public static int[] getJobStopReasonCodes() {
    return JOB_STOP_REASON_CODES;
  }
  
  public static String getReasonCodeDescription(int paramInt) {
    StringBuilder stringBuilder;
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("unknown:");
        stringBuilder.append(paramInt);
        return stringBuilder.toString();
      case 6:
        return "restricted_bucket";
      case 5:
        return "thermal";
      case 4:
        return "device_idle";
      case 3:
        return "timeout";
      case 2:
        return "preempt";
      case 1:
        return "constraints";
      case 0:
        break;
    } 
    return "canceled";
  }
  
  public void completeWork(JobWorkItem paramJobWorkItem) {
    try {
      if (getCallback().completeWork(getJobId(), paramJobWorkItem.getWorkId()))
        return; 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Given work is not active: ");
      stringBuilder.append(paramJobWorkItem);
      this(stringBuilder.toString());
      throw illegalArgumentException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public JobWorkItem dequeueWork() {
    try {
      return getCallback().dequeueWork(getJobId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public IJobCallback getCallback() {
    return IJobCallback.Stub.asInterface(this.callback);
  }
  
  public ClipData getClipData() {
    return this.clipData;
  }
  
  public int getClipGrantFlags() {
    return this.clipGrantFlags;
  }
  
  public String getDebugStopReason() {
    return this.debugStopReason;
  }
  
  public PersistableBundle getExtras() {
    return this.extras;
  }
  
  public int getJobId() {
    return this.jobId;
  }
  
  public Network getNetwork() {
    return this.network;
  }
  
  public int getStopReason() {
    return this.stopReason;
  }
  
  public Bundle getTransientExtras() {
    return this.transientExtras;
  }
  
  public String[] getTriggeredContentAuthorities() {
    return this.mTriggeredContentAuthorities;
  }
  
  public Uri[] getTriggeredContentUris() {
    return this.mTriggeredContentUris;
  }
  
  public boolean isOverrideDeadlineExpired() {
    return this.overrideDeadlineExpired;
  }
  
  public void setStopReason(int paramInt, String paramString) {
    this.stopReason = paramInt;
    this.debugStopReason = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.jobId);
    paramParcel.writePersistableBundle(this.extras);
    paramParcel.writeBundle(this.transientExtras);
    if (this.clipData != null) {
      paramParcel.writeInt(1);
      this.clipData.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.clipGrantFlags);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeStrongBinder(this.callback);
    paramParcel.writeInt(this.overrideDeadlineExpired);
    paramParcel.writeTypedArray((Parcelable[])this.mTriggeredContentUris, paramInt);
    paramParcel.writeStringArray(this.mTriggeredContentAuthorities);
    if (this.network != null) {
      paramParcel.writeInt(1);
      this.network.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.stopReason);
    paramParcel.writeString(this.debugStopReason);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
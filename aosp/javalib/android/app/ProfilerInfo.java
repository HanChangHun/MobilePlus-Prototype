package android.app;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Slog;
import android.util.proto.ProtoOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ProfilerInfo implements Parcelable {
  public static final Parcelable.Creator<ProfilerInfo> CREATOR = new Parcelable.Creator<ProfilerInfo>() {
      public ProfilerInfo createFromParcel(Parcel param1Parcel) {
        return new ProfilerInfo(param1Parcel);
      }
      
      public ProfilerInfo[] newArray(int param1Int) {
        return new ProfilerInfo[param1Int];
      }
    };
  
  private static final String TAG = "ProfilerInfo";
  
  public final String agent;
  
  public final boolean attachAgentDuringBind;
  
  public final boolean autoStopProfiler;
  
  public ParcelFileDescriptor profileFd;
  
  public final String profileFile;
  
  public final int samplingInterval;
  
  public final boolean streamingOutput;
  
  public ProfilerInfo(ProfilerInfo paramProfilerInfo) {
    this.profileFile = paramProfilerInfo.profileFile;
    this.profileFd = paramProfilerInfo.profileFd;
    this.samplingInterval = paramProfilerInfo.samplingInterval;
    this.autoStopProfiler = paramProfilerInfo.autoStopProfiler;
    this.streamingOutput = paramProfilerInfo.streamingOutput;
    this.agent = paramProfilerInfo.agent;
    this.attachAgentDuringBind = paramProfilerInfo.attachAgentDuringBind;
  }
  
  private ProfilerInfo(Parcel paramParcel) {
    ParcelFileDescriptor parcelFileDescriptor;
    boolean bool2;
    this.profileFile = paramParcel.readString();
    if (paramParcel.readInt() != 0) {
      parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel);
    } else {
      parcelFileDescriptor = null;
    } 
    this.profileFd = parcelFileDescriptor;
    this.samplingInterval = paramParcel.readInt();
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.autoStopProfiler = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.streamingOutput = bool2;
    this.agent = paramParcel.readString();
    this.attachAgentDuringBind = paramParcel.readBoolean();
  }
  
  public ProfilerInfo(String paramString1, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt, boolean paramBoolean1, boolean paramBoolean2, String paramString2, boolean paramBoolean3) {
    this.profileFile = paramString1;
    this.profileFd = paramParcelFileDescriptor;
    this.samplingInterval = paramInt;
    this.autoStopProfiler = paramBoolean1;
    this.streamingOutput = paramBoolean2;
    this.agent = paramString2;
    this.attachAgentDuringBind = paramBoolean3;
  }
  
  public void closeFd() {
    ParcelFileDescriptor parcelFileDescriptor = this.profileFd;
    if (parcelFileDescriptor != null) {
      try {
        parcelFileDescriptor.close();
      } catch (IOException iOException) {
        Slog.w("ProfilerInfo", "Failure closing profile fd", iOException);
      } 
      this.profileFd = null;
    } 
  }
  
  public int describeContents() {
    ParcelFileDescriptor parcelFileDescriptor = this.profileFd;
    return (parcelFileDescriptor != null) ? parcelFileDescriptor.describeContents() : 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1138166333441L, this.profileFile);
    ParcelFileDescriptor parcelFileDescriptor = this.profileFd;
    if (parcelFileDescriptor != null)
      paramProtoOutputStream.write(1120986464258L, parcelFileDescriptor.getFd()); 
    paramProtoOutputStream.write(1120986464259L, this.samplingInterval);
    paramProtoOutputStream.write(1133871366148L, this.autoStopProfiler);
    paramProtoOutputStream.write(1133871366149L, this.streamingOutput);
    paramProtoOutputStream.write(1138166333446L, this.agent);
    paramProtoOutputStream.end(paramLong);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!Objects.equals(this.profileFile, ((ProfilerInfo)paramObject).profileFile) || this.autoStopProfiler != ((ProfilerInfo)paramObject).autoStopProfiler || this.samplingInterval != ((ProfilerInfo)paramObject).samplingInterval || this.streamingOutput != ((ProfilerInfo)paramObject).streamingOutput || !Objects.equals(this.agent, ((ProfilerInfo)paramObject).agent))
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    return ((((17 * 31 + Objects.hashCode(this.profileFile)) * 31 + this.samplingInterval) * 31 + this.autoStopProfiler) * 31 + this.streamingOutput) * 31 + Objects.hashCode(this.agent);
  }
  
  public ProfilerInfo setAgent(String paramString, boolean paramBoolean) {
    return new ProfilerInfo(this.profileFile, this.profileFd, this.samplingInterval, this.autoStopProfiler, this.streamingOutput, paramString, paramBoolean);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.profileFile);
    if (this.profileFd != null) {
      paramParcel.writeInt(1);
      this.profileFd.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.samplingInterval);
    paramParcel.writeInt(this.autoStopProfiler);
    paramParcel.writeInt(this.streamingOutput);
    paramParcel.writeString(this.agent);
    paramParcel.writeBoolean(this.attachAgentDuringBind);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ProfilerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
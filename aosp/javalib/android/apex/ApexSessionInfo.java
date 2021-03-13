package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

public class ApexSessionInfo implements Parcelable {
  public static final Parcelable.Creator<ApexSessionInfo> CREATOR = new Parcelable.Creator<ApexSessionInfo>() {
      public ApexSessionInfo createFromParcel(Parcel param1Parcel) {
        ApexSessionInfo apexSessionInfo = new ApexSessionInfo();
        apexSessionInfo.readFromParcel(param1Parcel);
        return apexSessionInfo;
      }
      
      public ApexSessionInfo[] newArray(int param1Int) {
        return new ApexSessionInfo[param1Int];
      }
    };
  
  public String crashingNativeProcess;
  
  public boolean isActivated;
  
  public boolean isActivationFailed;
  
  public boolean isRevertFailed;
  
  public boolean isRevertInProgress;
  
  public boolean isReverted;
  
  public boolean isStaged;
  
  public boolean isSuccess;
  
  public boolean isUnknown;
  
  public boolean isVerified;
  
  public int sessionId;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      boolean bool2;
      this.sessionId = paramParcel.readInt();
      int k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      k = paramParcel.readInt();
      boolean bool1 = true;
      if (k != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.isUnknown = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.isVerified = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.isStaged = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.isActivated = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.isRevertInProgress = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.isActivationFailed = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.isSuccess = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.isReverted = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.isRevertFailed = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.crashingNativeProcess = paramParcel.readString();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      return;
    } finally {
      paramParcel.setDataPosition(i + j);
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = paramParcel.dataPosition();
    paramParcel.writeInt(0);
    paramParcel.writeInt(this.sessionId);
    paramParcel.writeInt(this.isUnknown);
    paramParcel.writeInt(this.isVerified);
    paramParcel.writeInt(this.isStaged);
    paramParcel.writeInt(this.isActivated);
    paramParcel.writeInt(this.isRevertInProgress);
    paramParcel.writeInt(this.isActivationFailed);
    paramParcel.writeInt(this.isSuccess);
    paramParcel.writeInt(this.isReverted);
    paramParcel.writeInt(this.isRevertFailed);
    paramParcel.writeString(this.crashingNativeProcess);
    paramInt = paramParcel.dataPosition();
    paramParcel.setDataPosition(i);
    paramParcel.writeInt(paramInt - i);
    paramParcel.setDataPosition(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/apex/ApexSessionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

public class ApexSessionParams implements Parcelable {
  public static final Parcelable.Creator<ApexSessionParams> CREATOR = new Parcelable.Creator<ApexSessionParams>() {
      public ApexSessionParams createFromParcel(Parcel param1Parcel) {
        ApexSessionParams apexSessionParams = new ApexSessionParams();
        apexSessionParams.readFromParcel(param1Parcel);
        return apexSessionParams;
      }
      
      public ApexSessionParams[] newArray(int param1Int) {
        return new ApexSessionParams[param1Int];
      }
    };
  
  public int[] childSessionIds = new int[0];
  
  public boolean hasRollbackEnabled = false;
  
  public boolean isRollback = false;
  
  public int rollbackId = 0;
  
  public int sessionId = 0;
  
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
      this.childSessionIds = paramParcel.createIntArray();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      k = paramParcel.readInt();
      boolean bool1 = true;
      if (k != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.hasRollbackEnabled = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      if (paramParcel.readInt() != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.isRollback = bool2;
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.rollbackId = paramParcel.readInt();
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
    paramParcel.writeIntArray(this.childSessionIds);
    paramParcel.writeInt(this.hasRollbackEnabled);
    paramParcel.writeInt(this.isRollback);
    paramParcel.writeInt(this.rollbackId);
    paramInt = paramParcel.dataPosition();
    paramParcel.setDataPosition(i);
    paramParcel.writeInt(paramInt - i);
    paramParcel.setDataPosition(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/apex/ApexSessionParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
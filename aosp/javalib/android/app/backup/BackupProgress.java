package android.app.backup;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public class BackupProgress implements Parcelable {
  public static final Parcelable.Creator<BackupProgress> CREATOR = new Parcelable.Creator<BackupProgress>() {
      public BackupProgress createFromParcel(Parcel param1Parcel) {
        return new BackupProgress(param1Parcel);
      }
      
      public BackupProgress[] newArray(int param1Int) {
        return new BackupProgress[param1Int];
      }
    };
  
  public final long bytesExpected;
  
  public final long bytesTransferred;
  
  public BackupProgress(long paramLong1, long paramLong2) {
    this.bytesExpected = paramLong1;
    this.bytesTransferred = paramLong2;
  }
  
  private BackupProgress(Parcel paramParcel) {
    this.bytesExpected = paramParcel.readLong();
    this.bytesTransferred = paramParcel.readLong();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.bytesExpected);
    paramParcel.writeLong(this.bytesTransferred);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
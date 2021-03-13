package android.app.job;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public final class TriggerContentUri implements Parcelable {
  public static final Parcelable.Creator<TriggerContentUri> CREATOR = new Parcelable.Creator<TriggerContentUri>() {
      public JobInfo.TriggerContentUri createFromParcel(Parcel param2Parcel) {
        return new JobInfo.TriggerContentUri(param2Parcel);
      }
      
      public JobInfo.TriggerContentUri[] newArray(int param2Int) {
        return new JobInfo.TriggerContentUri[param2Int];
      }
    };
  
  public static final int FLAG_NOTIFY_FOR_DESCENDANTS = 1;
  
  private final int mFlags;
  
  private final Uri mUri;
  
  public TriggerContentUri(Uri paramUri, int paramInt) {
    Objects.requireNonNull(paramUri);
    this.mUri = paramUri;
    this.mFlags = paramInt;
  }
  
  private TriggerContentUri(Parcel paramParcel) {
    this.mUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel);
    this.mFlags = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof TriggerContentUri;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (Objects.equals(((TriggerContentUri)paramObject).mUri, this.mUri)) {
      bool = bool1;
      if (((TriggerContentUri)paramObject).mFlags == this.mFlags)
        bool = true; 
    } 
    return bool;
  }
  
  public int getFlags() {
    return this.mFlags;
  }
  
  public Uri getUri() {
    return this.mUri;
  }
  
  public int hashCode() {
    int i;
    Uri uri = this.mUri;
    if (uri == null) {
      i = 0;
    } else {
      i = uri.hashCode();
    } 
    return i ^ this.mFlags;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.mUri.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.mFlags);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Flags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/job/JobInfo$TriggerContentUri.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
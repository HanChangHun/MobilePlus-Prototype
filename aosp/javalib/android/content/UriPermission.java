package android.content;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public final class UriPermission implements Parcelable {
  public static final Parcelable.Creator<UriPermission> CREATOR = new Parcelable.Creator<UriPermission>() {
      public UriPermission createFromParcel(Parcel param1Parcel) {
        return new UriPermission(param1Parcel);
      }
      
      public UriPermission[] newArray(int param1Int) {
        return new UriPermission[param1Int];
      }
    };
  
  public static final long INVALID_TIME = -9223372036854775808L;
  
  private final int mModeFlags;
  
  private final long mPersistedTime;
  
  private final Uri mUri;
  
  public UriPermission(Uri paramUri, int paramInt, long paramLong) {
    this.mUri = paramUri;
    this.mModeFlags = paramInt;
    this.mPersistedTime = paramLong;
  }
  
  public UriPermission(Parcel paramParcel) {
    this.mUri = (Uri)paramParcel.readParcelable(null);
    this.mModeFlags = paramParcel.readInt();
    this.mPersistedTime = paramParcel.readLong();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getPersistedTime() {
    return this.mPersistedTime;
  }
  
  public Uri getUri() {
    return this.mUri;
  }
  
  public boolean isReadPermission() {
    int i = this.mModeFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean isWritePermission() {
    boolean bool;
    if ((this.mModeFlags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UriPermission {uri=");
    stringBuilder.append(this.mUri);
    stringBuilder.append(", modeFlags=");
    stringBuilder.append(this.mModeFlags);
    stringBuilder.append(", persistedTime=");
    stringBuilder.append(this.mPersistedTime);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable((Parcelable)this.mUri, paramInt);
    paramParcel.writeInt(this.mModeFlags);
    paramParcel.writeLong(this.mPersistedTime);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/UriPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.blob;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class LeaseInfo implements Parcelable {
  public static final Parcelable.Creator<LeaseInfo> CREATOR = new Parcelable.Creator<LeaseInfo>() {
      public LeaseInfo createFromParcel(Parcel param1Parcel) {
        return new LeaseInfo(param1Parcel);
      }
      
      public LeaseInfo[] newArray(int param1Int) {
        return new LeaseInfo[param1Int];
      }
    };
  
  private final CharSequence mDescription;
  
  private final int mDescriptionResId;
  
  private final long mExpiryTimeMillis;
  
  private final String mPackageName;
  
  private LeaseInfo(Parcel paramParcel) {
    this.mPackageName = paramParcel.readString();
    this.mExpiryTimeMillis = paramParcel.readLong();
    this.mDescriptionResId = paramParcel.readInt();
    this.mDescription = paramParcel.readCharSequence();
  }
  
  public LeaseInfo(String paramString, long paramLong, int paramInt, CharSequence paramCharSequence) {
    this.mPackageName = paramString;
    this.mExpiryTimeMillis = paramLong;
    this.mDescriptionResId = paramInt;
    this.mDescription = paramCharSequence;
  }
  
  private String toShortString() {
    return this.mPackageName;
  }
  
  static String toShortString(List<LeaseInfo> paramList) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    byte b = 0;
    int i = paramList.size();
    while (b < i) {
      stringBuilder.append(((LeaseInfo)paramList.get(b)).toShortString());
      stringBuilder.append(",");
      b++;
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public CharSequence getDescription() {
    return this.mDescription;
  }
  
  public int getDescriptionResId() {
    return this.mDescriptionResId;
  }
  
  public long getExpiryTimeMillis() {
    return this.mExpiryTimeMillis;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("LeaseInfo {package: ");
    stringBuilder.append(this.mPackageName);
    stringBuilder.append(",expiryMs: ");
    stringBuilder.append(this.mExpiryTimeMillis);
    stringBuilder.append(",descriptionResId: ");
    stringBuilder.append(this.mDescriptionResId);
    stringBuilder.append(",description: ");
    stringBuilder.append(this.mDescription);
    stringBuilder.append(",}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeLong(this.mExpiryTimeMillis);
    paramParcel.writeInt(this.mDescriptionResId);
    paramParcel.writeCharSequence(this.mDescription);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/LeaseInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
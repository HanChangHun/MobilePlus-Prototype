package android.app.blob;

import android.app.AppGlobals;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.Formatter;
import java.util.Collections;
import java.util.List;

public final class BlobInfo implements Parcelable {
  public static final Parcelable.Creator<BlobInfo> CREATOR = new Parcelable.Creator<BlobInfo>() {
      public BlobInfo createFromParcel(Parcel param1Parcel) {
        return new BlobInfo(param1Parcel);
      }
      
      public BlobInfo[] newArray(int param1Int) {
        return new BlobInfo[param1Int];
      }
    };
  
  private final long mExpiryTimeMs;
  
  private final long mId;
  
  private final CharSequence mLabel;
  
  private final List<LeaseInfo> mLeaseInfos;
  
  private final long mSizeBytes;
  
  public BlobInfo(long paramLong1, long paramLong2, CharSequence paramCharSequence, long paramLong3, List<LeaseInfo> paramList) {
    this.mId = paramLong1;
    this.mExpiryTimeMs = paramLong2;
    this.mLabel = paramCharSequence;
    this.mSizeBytes = paramLong3;
    this.mLeaseInfos = paramList;
  }
  
  private BlobInfo(Parcel paramParcel) {
    this.mId = paramParcel.readLong();
    this.mExpiryTimeMs = paramParcel.readLong();
    this.mLabel = paramParcel.readCharSequence();
    this.mSizeBytes = paramParcel.readLong();
    this.mLeaseInfos = paramParcel.readArrayList(null);
  }
  
  private static String formatBlobSize(long paramLong) {
    return Formatter.formatFileSize((Context)AppGlobals.getInitialApplication(), paramLong, 8);
  }
  
  private String toShortString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BlobInfo {id: ");
    stringBuilder.append(this.mId);
    stringBuilder.append(",expiryMs: ");
    stringBuilder.append(this.mExpiryTimeMs);
    stringBuilder.append(",label: ");
    stringBuilder.append(this.mLabel);
    stringBuilder.append(",size: ");
    stringBuilder.append(formatBlobSize(this.mSizeBytes));
    stringBuilder.append(",leases: ");
    stringBuilder.append(LeaseInfo.toShortString(this.mLeaseInfos));
    stringBuilder.append(",}");
    return stringBuilder.toString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getExpiryTimeMs() {
    return this.mExpiryTimeMs;
  }
  
  public long getId() {
    return this.mId;
  }
  
  public CharSequence getLabel() {
    return this.mLabel;
  }
  
  public List<LeaseInfo> getLeases() {
    return Collections.unmodifiableList(this.mLeaseInfos);
  }
  
  public long getSizeBytes() {
    return this.mSizeBytes;
  }
  
  public String toString() {
    return toShortString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mId);
    paramParcel.writeLong(this.mExpiryTimeMs);
    paramParcel.writeCharSequence(this.mLabel);
    paramParcel.writeLong(this.mSizeBytes);
    paramParcel.writeList(this.mLeaseInfos);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/BlobInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
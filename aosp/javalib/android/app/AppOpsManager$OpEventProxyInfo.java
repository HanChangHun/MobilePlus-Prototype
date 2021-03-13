package android.app;

import android.annotation.IntRange;
import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Preconditions;

@SystemApi
public final class OpEventProxyInfo implements Parcelable {
  public static final Parcelable.Creator<OpEventProxyInfo> CREATOR = new Parcelable.Creator<OpEventProxyInfo>() {
      public AppOpsManager.OpEventProxyInfo createFromParcel(Parcel param2Parcel) {
        return new AppOpsManager.OpEventProxyInfo(param2Parcel);
      }
      
      public AppOpsManager.OpEventProxyInfo[] newArray(int param2Int) {
        return new AppOpsManager.OpEventProxyInfo[param2Int];
      }
    };
  
  private String mAttributionTag;
  
  private String mPackageName;
  
  private int mUid;
  
  public OpEventProxyInfo(int paramInt, String paramString1, String paramString2) {
    this.mUid = paramInt;
    AnnotationValidations.validate(IntRange.class, null, paramInt, "from", 0L);
    this.mPackageName = paramString1;
    this.mAttributionTag = paramString2;
  }
  
  public OpEventProxyInfo(OpEventProxyInfo paramOpEventProxyInfo) {
    this.mUid = paramOpEventProxyInfo.mUid;
    this.mPackageName = paramOpEventProxyInfo.mPackageName;
    this.mAttributionTag = paramOpEventProxyInfo.mAttributionTag;
  }
  
  OpEventProxyInfo(Parcel paramParcel) {
    String str1;
    String str2;
    byte b = paramParcel.readByte();
    int i = paramParcel.readInt();
    Parcel parcel = null;
    if ((b & 0x2) == 0) {
      str2 = null;
    } else {
      str2 = paramParcel.readString();
    } 
    if ((b & 0x4) == 0) {
      paramParcel = parcel;
    } else {
      str1 = paramParcel.readString();
    } 
    this.mUid = i;
    AnnotationValidations.validate(IntRange.class, null, i, "from", 0L);
    this.mPackageName = str2;
    this.mAttributionTag = str1;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getAttributionTag() {
    return this.mAttributionTag;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int getUid() {
    return this.mUid;
  }
  
  public void reinit(int paramInt, String paramString1, String paramString2) {
    this.mUid = Preconditions.checkArgumentNonnegative(paramInt);
    this.mPackageName = paramString1;
    this.mAttributionTag = paramString2;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = 0;
    if (this.mPackageName != null)
      paramInt = (byte)(0x0 | 0x2); 
    int i = paramInt;
    if (this.mAttributionTag != null) {
      paramInt = (byte)(paramInt | 0x4);
      i = paramInt;
    } 
    paramParcel.writeByte(i);
    paramParcel.writeInt(this.mUid);
    String str = this.mPackageName;
    if (str != null)
      paramParcel.writeString(str); 
    str = this.mAttributionTag;
    if (str != null)
      paramParcel.writeString(str); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$OpEventProxyInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
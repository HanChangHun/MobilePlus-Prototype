package android.app;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;

@SystemApi
public final class RuntimeAppOpAccessMessage implements Parcelable {
  public static final Parcelable.Creator<RuntimeAppOpAccessMessage> CREATOR = new Parcelable.Creator<RuntimeAppOpAccessMessage>() {
      public RuntimeAppOpAccessMessage createFromParcel(Parcel param1Parcel) {
        return new RuntimeAppOpAccessMessage(param1Parcel);
      }
      
      public RuntimeAppOpAccessMessage[] newArray(int param1Int) {
        return new RuntimeAppOpAccessMessage[param1Int];
      }
    };
  
  private final String mAttributionTag;
  
  private final String mMessage;
  
  private final int mOpCode;
  
  private final String mPackageName;
  
  private final int mSamplingStrategy;
  
  private final int mUid;
  
  public RuntimeAppOpAccessMessage(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, int paramInt3) {
    this.mUid = paramInt1;
    AnnotationValidations.validate(IntRange.class, null, paramInt1, "from", 0L);
    this.mOpCode = paramInt2;
    AnnotationValidations.validate(IntRange.class, null, paramInt2, "from", 0L, "to", 102L);
    this.mPackageName = paramString1;
    AnnotationValidations.validate(NonNull.class, null, paramString1);
    this.mAttributionTag = paramString2;
    this.mMessage = paramString3;
    AnnotationValidations.validate(NonNull.class, null, paramString3);
    this.mSamplingStrategy = paramInt3;
    AnnotationValidations.validate(AppOpsManager.SamplingStrategy.class, null, paramInt3);
  }
  
  RuntimeAppOpAccessMessage(Parcel paramParcel) {
    String str2;
    byte b = paramParcel.readByte();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    String str1 = paramParcel.readString();
    if ((b & 0x8) == 0) {
      str2 = null;
    } else {
      str2 = paramParcel.readString();
    } 
    String str3 = paramParcel.readString();
    int i = paramParcel.readInt();
    this.mUid = j;
    AnnotationValidations.validate(IntRange.class, null, j, "from", 0L);
    this.mOpCode = k;
    AnnotationValidations.validate(IntRange.class, null, k, "from", 0L, "to", 102L);
    this.mPackageName = str1;
    AnnotationValidations.validate(NonNull.class, null, str1);
    this.mAttributionTag = str2;
    this.mMessage = str3;
    AnnotationValidations.validate(NonNull.class, null, str3);
    this.mSamplingStrategy = i;
    AnnotationValidations.validate(AppOpsManager.SamplingStrategy.class, null, i);
  }
  
  @Deprecated
  private void __metadata() {}
  
  public int describeContents() {
    return 0;
  }
  
  public String getAttributionTag() {
    return this.mAttributionTag;
  }
  
  public String getMessage() {
    return this.mMessage;
  }
  
  public String getOp() {
    return AppOpsManager.opToPublicName(this.mOpCode);
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int getSamplingStrategy() {
    return this.mSamplingStrategy;
  }
  
  public int getUid() {
    return this.mUid;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = 0;
    int i = paramInt;
    if (this.mAttributionTag != null) {
      paramInt = (byte)(0x0 | 0x8);
      i = paramInt;
    } 
    paramParcel.writeByte(i);
    paramParcel.writeInt(this.mUid);
    paramParcel.writeInt(this.mOpCode);
    paramParcel.writeString(this.mPackageName);
    String str = this.mAttributionTag;
    if (str != null)
      paramParcel.writeString(str); 
    paramParcel.writeString(this.mMessage);
    paramParcel.writeInt(this.mSamplingStrategy);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RuntimeAppOpAccessMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
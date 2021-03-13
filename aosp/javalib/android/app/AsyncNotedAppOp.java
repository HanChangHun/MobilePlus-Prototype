package android.app;

import android.annotation.CurrentTimeMillisLong;
import android.annotation.IntRange;
import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Preconditions;
import java.util.Objects;

public final class AsyncNotedAppOp implements Parcelable {
  public static final Parcelable.Creator<AsyncNotedAppOp> CREATOR = new Parcelable.Creator<AsyncNotedAppOp>() {
      public AsyncNotedAppOp createFromParcel(Parcel param1Parcel) {
        return new AsyncNotedAppOp(param1Parcel);
      }
      
      public AsyncNotedAppOp[] newArray(int param1Int) {
        return new AsyncNotedAppOp[param1Int];
      }
    };
  
  private final String mAttributionTag;
  
  private final String mMessage;
  
  private final int mNotingUid;
  
  private final int mOpCode;
  
  private final long mTime;
  
  public AsyncNotedAppOp(int paramInt1, int paramInt2, String paramString1, String paramString2, long paramLong) {
    this.mOpCode = paramInt1;
    AnnotationValidations.validate(IntRange.class, null, paramInt1, "from", 0L);
    this.mNotingUid = paramInt2;
    AnnotationValidations.validate(IntRange.class, null, paramInt2, "from", 0L);
    this.mAttributionTag = paramString1;
    this.mMessage = paramString2;
    AnnotationValidations.validate(NonNull.class, null, paramString2);
    this.mTime = paramLong;
    AnnotationValidations.validate(CurrentTimeMillisLong.class, null, paramLong);
    onConstructed();
  }
  
  AsyncNotedAppOp(Parcel paramParcel) {
    String str1;
    byte b = paramParcel.readByte();
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    if ((b & 0x4) == 0) {
      str1 = null;
    } else {
      str1 = paramParcel.readString();
    } 
    String str2 = paramParcel.readString();
    long l = paramParcel.readLong();
    this.mOpCode = i;
    AnnotationValidations.validate(IntRange.class, null, i, "from", 0L);
    this.mNotingUid = j;
    AnnotationValidations.validate(IntRange.class, null, j, "from", 0L);
    this.mAttributionTag = str1;
    this.mMessage = str2;
    AnnotationValidations.validate(NonNull.class, null, str2);
    this.mTime = l;
    AnnotationValidations.validate(CurrentTimeMillisLong.class, null, l);
    onConstructed();
  }
  
  @Deprecated
  private void __metadata() {}
  
  private void onConstructed() {
    Preconditions.checkArgumentInRange(this.mOpCode, 0, 102, "opCode");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mOpCode != ((AsyncNotedAppOp)paramObject).mOpCode || this.mNotingUid != ((AsyncNotedAppOp)paramObject).mNotingUid || !Objects.equals(this.mAttributionTag, ((AsyncNotedAppOp)paramObject).mAttributionTag) || !Objects.equals(this.mMessage, ((AsyncNotedAppOp)paramObject).mMessage) || this.mTime != ((AsyncNotedAppOp)paramObject).mTime)
      bool = false; 
    return bool;
  }
  
  public String getAttributionTag() {
    return this.mAttributionTag;
  }
  
  public String getMessage() {
    return this.mMessage;
  }
  
  public int getNotingUid() {
    return this.mNotingUid;
  }
  
  public String getOp() {
    return AppOpsManager.opToPublicName(this.mOpCode);
  }
  
  public long getTime() {
    return this.mTime;
  }
  
  public int hashCode() {
    return ((((1 * 31 + this.mOpCode) * 31 + this.mNotingUid) * 31 + Objects.hashCode(this.mAttributionTag)) * 31 + Objects.hashCode(this.mMessage)) * 31 + Long.hashCode(this.mTime);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = 0;
    int i = paramInt;
    if (this.mAttributionTag != null) {
      paramInt = (byte)(0x0 | 0x4);
      i = paramInt;
    } 
    paramParcel.writeByte(i);
    paramParcel.writeInt(this.mOpCode);
    paramParcel.writeInt(this.mNotingUid);
    String str = this.mAttributionTag;
    if (str != null)
      paramParcel.writeString(str); 
    paramParcel.writeString(this.mMessage);
    paramParcel.writeLong(this.mTime);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AsyncNotedAppOp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
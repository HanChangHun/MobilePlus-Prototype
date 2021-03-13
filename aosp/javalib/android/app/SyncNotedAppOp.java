package android.app;

import android.annotation.IntRange;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import java.util.Objects;

public final class SyncNotedAppOp implements Parcelable {
  public static final Parcelable.Creator<SyncNotedAppOp> CREATOR = new Parcelable.Creator<SyncNotedAppOp>() {
      public SyncNotedAppOp createFromParcel(Parcel param1Parcel) {
        return new SyncNotedAppOp(param1Parcel);
      }
      
      public SyncNotedAppOp[] newArray(int param1Int) {
        return new SyncNotedAppOp[param1Int];
      }
    };
  
  private final String mAttributionTag;
  
  private final int mOpCode;
  
  public SyncNotedAppOp(int paramInt, String paramString) {
    this.mOpCode = paramInt;
    AnnotationValidations.validate(IntRange.class, null, paramInt, "from", 0L, "to", 102L);
    this.mAttributionTag = paramString;
  }
  
  SyncNotedAppOp(Parcel paramParcel) {
    String str;
    byte b = paramParcel.readByte();
    int i = paramParcel.readInt();
    if ((b & 0x2) == 0) {
      paramParcel = null;
    } else {
      str = paramParcel.readString();
    } 
    this.mOpCode = i;
    AnnotationValidations.validate(IntRange.class, null, i, "from", 0L, "to", 102L);
    this.mAttributionTag = str;
  }
  
  @Deprecated
  private void __metadata() {}
  
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
    if (this.mOpCode != ((SyncNotedAppOp)paramObject).mOpCode || !Objects.equals(this.mAttributionTag, ((SyncNotedAppOp)paramObject).mAttributionTag))
      bool = false; 
    return bool;
  }
  
  public String getAttributionTag() {
    return this.mAttributionTag;
  }
  
  public String getOp() {
    return AppOpsManager.opToPublicName(this.mOpCode);
  }
  
  public int hashCode() {
    return (1 * 31 + this.mOpCode) * 31 + Objects.hashCode(this.mAttributionTag);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = 0;
    int i = paramInt;
    if (this.mAttributionTag != null) {
      paramInt = (byte)(0x0 | 0x2);
      i = paramInt;
    } 
    paramParcel.writeByte(i);
    paramParcel.writeInt(this.mOpCode);
    String str = this.mAttributionTag;
    if (str != null)
      paramParcel.writeString(str); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SyncNotedAppOp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
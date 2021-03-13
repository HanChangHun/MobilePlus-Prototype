package android.app.slice;

import android.os.Parcel;
import android.os.Parcelable;

public final class SliceSpec implements Parcelable {
  public static final Parcelable.Creator<SliceSpec> CREATOR = new Parcelable.Creator<SliceSpec>() {
      public SliceSpec createFromParcel(Parcel param1Parcel) {
        return new SliceSpec(param1Parcel);
      }
      
      public SliceSpec[] newArray(int param1Int) {
        return new SliceSpec[param1Int];
      }
    };
  
  private final int mRevision;
  
  private final String mType;
  
  public SliceSpec(Parcel paramParcel) {
    this.mType = paramParcel.readString();
    this.mRevision = paramParcel.readInt();
  }
  
  public SliceSpec(String paramString, int paramInt) {
    this.mType = paramString;
    this.mRevision = paramInt;
  }
  
  public boolean canRender(SliceSpec paramSliceSpec) {
    boolean bool = this.mType.equals(paramSliceSpec.mType);
    boolean bool1 = false;
    if (!bool)
      return false; 
    if (this.mRevision >= paramSliceSpec.mRevision)
      bool1 = true; 
    return bool1;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof SliceSpec;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.mType.equals(((SliceSpec)paramObject).mType)) {
      bool = bool1;
      if (this.mRevision == ((SliceSpec)paramObject).mRevision)
        bool = true; 
    } 
    return bool;
  }
  
  public int getRevision() {
    return this.mRevision;
  }
  
  public String getType() {
    return this.mType;
  }
  
  public String toString() {
    return String.format("SliceSpec{%s,%d}", new Object[] { this.mType, Integer.valueOf(this.mRevision) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mType);
    paramParcel.writeInt(this.mRevision);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/SliceSpec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
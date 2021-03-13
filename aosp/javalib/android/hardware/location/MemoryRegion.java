package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public class MemoryRegion implements Parcelable {
  public static final Parcelable.Creator<MemoryRegion> CREATOR = new Parcelable.Creator<MemoryRegion>() {
      public MemoryRegion createFromParcel(Parcel param1Parcel) {
        return new MemoryRegion(param1Parcel);
      }
      
      public MemoryRegion[] newArray(int param1Int) {
        return new MemoryRegion[param1Int];
      }
    };
  
  private boolean mIsExecutable;
  
  private boolean mIsReadable;
  
  private boolean mIsWritable;
  
  private int mSizeBytes;
  
  private int mSizeBytesFree;
  
  public MemoryRegion(Parcel paramParcel) {
    boolean bool2;
    this.mSizeBytes = paramParcel.readInt();
    this.mSizeBytesFree = paramParcel.readInt();
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsReadable = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsWritable = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mIsExecutable = bool2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool1 = true;
    if (paramObject == this)
      return true; 
    boolean bool2 = false;
    if (paramObject instanceof MemoryRegion) {
      paramObject = paramObject;
      if (paramObject.getCapacityBytes() == this.mSizeBytes && paramObject.getFreeCapacityBytes() == this.mSizeBytesFree && paramObject.isReadable() == this.mIsReadable && paramObject.isWritable() == this.mIsWritable && paramObject.isExecutable() == this.mIsExecutable) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
    } 
    return bool2;
  }
  
  public int getCapacityBytes() {
    return this.mSizeBytes;
  }
  
  public int getFreeCapacityBytes() {
    return this.mSizeBytesFree;
  }
  
  public boolean isExecutable() {
    return this.mIsExecutable;
  }
  
  public boolean isReadable() {
    return this.mIsReadable;
  }
  
  public boolean isWritable() {
    return this.mIsWritable;
  }
  
  public String toString() {
    String str;
    if (isReadable()) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append("r");
      str = stringBuilder1.toString();
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append("-");
      str = stringBuilder1.toString();
    } 
    if (isWritable()) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str);
      stringBuilder1.append("w");
      str = stringBuilder1.toString();
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str);
      stringBuilder1.append("-");
      str = stringBuilder1.toString();
    } 
    if (isExecutable()) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str);
      stringBuilder1.append("x");
      str = stringBuilder1.toString();
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str);
      stringBuilder1.append("-");
      str = stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[ ");
    stringBuilder.append(this.mSizeBytesFree);
    stringBuilder.append("/ ");
    stringBuilder.append(this.mSizeBytes);
    stringBuilder.append(" ] : ");
    stringBuilder.append(str);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mSizeBytes);
    paramParcel.writeInt(this.mSizeBytesFree);
    paramParcel.writeInt(this.mIsReadable);
    paramParcel.writeInt(this.mIsWritable);
    paramParcel.writeInt(this.mIsExecutable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/MemoryRegion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
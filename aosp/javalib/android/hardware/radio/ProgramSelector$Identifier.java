package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class Identifier implements Parcelable {
  public static final Parcelable.Creator<Identifier> CREATOR = new Parcelable.Creator<Identifier>() {
      public ProgramSelector.Identifier createFromParcel(Parcel param2Parcel) {
        return new ProgramSelector.Identifier(param2Parcel);
      }
      
      public ProgramSelector.Identifier[] newArray(int param2Int) {
        return new ProgramSelector.Identifier[param2Int];
      }
    };
  
  private final int mType;
  
  private final long mValue;
  
  public Identifier(int paramInt, long paramLong) {
    int i = paramInt;
    if (paramInt == 10004)
      i = 4; 
    this.mType = i;
    this.mValue = paramLong;
  }
  
  private Identifier(Parcel paramParcel) {
    this.mType = paramParcel.readInt();
    this.mValue = paramParcel.readLong();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof Identifier))
      return false; 
    paramObject = paramObject;
    if (paramObject.getType() != this.mType || paramObject.getValue() != this.mValue)
      bool = false; 
    return bool;
  }
  
  public int getType() {
    return (this.mType == 4 && this.mValue > 10L) ? 10004 : this.mType;
  }
  
  public long getValue() {
    return this.mValue;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.mType), Long.valueOf(this.mValue) });
  }
  
  public boolean isCategoryType() {
    boolean bool;
    int i = this.mType;
    if ((i >= 1000 && i <= 1999) || this.mType == 6) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Identifier(");
    stringBuilder.append(this.mType);
    stringBuilder.append(", ");
    stringBuilder.append(this.mValue);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mType);
    paramParcel.writeLong(this.mValue);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramSelector$Identifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
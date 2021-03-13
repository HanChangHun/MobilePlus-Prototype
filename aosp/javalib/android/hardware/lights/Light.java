package android.hardware.lights;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class Light implements Parcelable {
  public static final Parcelable.Creator<Light> CREATOR = new Parcelable.Creator<Light>() {
      public Light createFromParcel(Parcel param1Parcel) {
        return new Light(param1Parcel);
      }
      
      public Light[] newArray(int param1Int) {
        return new Light[param1Int];
      }
    };
  
  private final int mId;
  
  private final int mOrdinal;
  
  private final int mType;
  
  public Light(int paramInt1, int paramInt2, int paramInt3) {
    this.mId = paramInt1;
    this.mOrdinal = paramInt2;
    this.mType = paramInt3;
  }
  
  private Light(Parcel paramParcel) {
    this.mId = paramParcel.readInt();
    this.mOrdinal = paramParcel.readInt();
    this.mType = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof Light;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      bool = bool1;
      if (this.mId == ((Light)paramObject).mId) {
        bool = bool1;
        if (this.mOrdinal == ((Light)paramObject).mOrdinal) {
          bool = bool1;
          if (this.mType == ((Light)paramObject).mType)
            bool = true; 
        } 
      } 
      return bool;
    } 
    return false;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public int getOrdinal() {
    return this.mOrdinal;
  }
  
  public int getType() {
    return this.mType;
  }
  
  public int hashCode() {
    return this.mId;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mId);
    paramParcel.writeInt(this.mOrdinal);
    paramParcel.writeInt(this.mType);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/Light.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
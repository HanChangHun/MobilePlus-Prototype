package android.bluetooth.le;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class ResultStorageDescriptor implements Parcelable {
  public static final Parcelable.Creator<ResultStorageDescriptor> CREATOR = new Parcelable.Creator<ResultStorageDescriptor>() {
      public ResultStorageDescriptor createFromParcel(Parcel param1Parcel) {
        return new ResultStorageDescriptor(param1Parcel);
      }
      
      public ResultStorageDescriptor[] newArray(int param1Int) {
        return new ResultStorageDescriptor[param1Int];
      }
    };
  
  private int mLength;
  
  private int mOffset;
  
  private int mType;
  
  public ResultStorageDescriptor(int paramInt1, int paramInt2, int paramInt3) {
    this.mType = paramInt1;
    this.mOffset = paramInt2;
    this.mLength = paramInt3;
  }
  
  private ResultStorageDescriptor(Parcel paramParcel) {
    ReadFromParcel(paramParcel);
  }
  
  private void ReadFromParcel(Parcel paramParcel) {
    this.mType = paramParcel.readInt();
    this.mOffset = paramParcel.readInt();
    this.mLength = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getLength() {
    return this.mLength;
  }
  
  public int getOffset() {
    return this.mOffset;
  }
  
  public int getType() {
    return this.mType;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mType);
    paramParcel.writeInt(this.mOffset);
    paramParcel.writeInt(this.mLength);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ResultStorageDescriptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
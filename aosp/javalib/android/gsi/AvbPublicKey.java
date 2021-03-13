package android.gsi;

import android.os.Parcel;
import android.os.Parcelable;

public class AvbPublicKey implements Parcelable {
  public static final Parcelable.Creator<AvbPublicKey> CREATOR = new Parcelable.Creator<AvbPublicKey>() {
      public AvbPublicKey createFromParcel(Parcel param1Parcel) {
        AvbPublicKey avbPublicKey = new AvbPublicKey();
        avbPublicKey.readFromParcel(param1Parcel);
        return avbPublicKey;
      }
      
      public AvbPublicKey[] newArray(int param1Int) {
        return new AvbPublicKey[param1Int];
      }
    };
  
  public byte[] bytes;
  
  public byte[] sha1;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      this.bytes = paramParcel.createByteArray();
      int k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.sha1 = paramParcel.createByteArray();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      return;
    } finally {
      paramParcel.setDataPosition(i + j);
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = paramParcel.dataPosition();
    paramParcel.writeInt(0);
    paramParcel.writeByteArray(this.bytes);
    paramParcel.writeByteArray(this.sha1);
    int i = paramParcel.dataPosition();
    paramParcel.setDataPosition(paramInt);
    paramParcel.writeInt(i - paramInt);
    paramParcel.setDataPosition(i);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/AvbPublicKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.gsi;

import android.os.Parcel;
import android.os.Parcelable;

public class MappedImage implements Parcelable {
  public static final Parcelable.Creator<MappedImage> CREATOR = new Parcelable.Creator<MappedImage>() {
      public MappedImage createFromParcel(Parcel param1Parcel) {
        MappedImage mappedImage = new MappedImage();
        mappedImage.readFromParcel(param1Parcel);
        return mappedImage;
      }
      
      public MappedImage[] newArray(int param1Int) {
        return new MappedImage[param1Int];
      }
    };
  
  public String path;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      this.path = paramParcel.readString();
      int k = paramParcel.dataPosition();
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
    paramParcel.writeString(this.path);
    int i = paramParcel.dataPosition();
    paramParcel.setDataPosition(paramInt);
    paramParcel.writeInt(i - paramInt);
    paramParcel.setDataPosition(i);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/MappedImage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
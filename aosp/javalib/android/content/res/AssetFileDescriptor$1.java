package android.content.res;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AssetFileDescriptor> {
  public AssetFileDescriptor createFromParcel(Parcel paramParcel) {
    return new AssetFileDescriptor(paramParcel);
  }
  
  public AssetFileDescriptor[] newArray(int paramInt) {
    return new AssetFileDescriptor[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/AssetFileDescriptor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
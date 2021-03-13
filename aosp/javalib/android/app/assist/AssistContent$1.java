package android.app.assist;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AssistContent> {
  public AssistContent createFromParcel(Parcel paramParcel) {
    return new AssistContent(paramParcel);
  }
  
  public AssistContent[] newArray(int paramInt) {
    return new AssistContent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistContent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<VoiceInteractor.Prompt> {
  public VoiceInteractor.Prompt createFromParcel(Parcel paramParcel) {
    return new VoiceInteractor.Prompt(paramParcel);
  }
  
  public VoiceInteractor.Prompt[] newArray(int paramInt) {
    return new VoiceInteractor.Prompt[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$Prompt$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
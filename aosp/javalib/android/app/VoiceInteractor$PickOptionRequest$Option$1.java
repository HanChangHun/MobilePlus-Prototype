package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<VoiceInteractor.PickOptionRequest.Option> {
  public VoiceInteractor.PickOptionRequest.Option createFromParcel(Parcel paramParcel) {
    return new VoiceInteractor.PickOptionRequest.Option(paramParcel);
  }
  
  public VoiceInteractor.PickOptionRequest.Option[] newArray(int paramInt) {
    return new VoiceInteractor.PickOptionRequest.Option[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$PickOptionRequest$Option$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
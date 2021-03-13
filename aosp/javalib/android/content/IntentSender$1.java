package android.content;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<IntentSender> {
  public IntentSender createFromParcel(Parcel paramParcel) {
    IBinder iBinder = paramParcel.readStrongBinder();
    if (iBinder != null) {
      IntentSender intentSender = new IntentSender(iBinder);
    } else {
      iBinder = null;
    } 
    return (IntentSender)iBinder;
  }
  
  public IntentSender[] newArray(int paramInt) {
    return new IntentSender[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IntentSender$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.gesture;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

class null implements Parcelable.Creator<Gesture> {
  public Gesture createFromParcel(Parcel paramParcel) {
    IOException iOException = null;
    long l = paramParcel.readLong();
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramParcel.createByteArray()));
    try {
      Gesture gesture = Gesture.deserialize(dataInputStream);
      GestureUtils.closeStream(dataInputStream);
    } catch (IOException iOException1) {
      Log.e("Gestures", "Error reading Gesture from parcel:", iOException1);
      iOException1 = iOException;
      GestureUtils.closeStream(dataInputStream);
    } finally {}
    if (paramParcel != null)
      Gesture.access$002((Gesture)paramParcel, l); 
    return (Gesture)paramParcel;
  }
  
  public Gesture[] newArray(int paramInt) {
    return new Gesture[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/Gesture$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
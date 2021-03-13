package android.accessibilityservice;

import android.os.Parcel;
import android.os.Parcelable;

public class GestureStep implements Parcelable {
  public static final Parcelable.Creator<GestureStep> CREATOR = new Parcelable.Creator<GestureStep>() {
      public GestureDescription.GestureStep createFromParcel(Parcel param2Parcel) {
        return new GestureDescription.GestureStep(param2Parcel);
      }
      
      public GestureDescription.GestureStep[] newArray(int param2Int) {
        return new GestureDescription.GestureStep[param2Int];
      }
    };
  
  public int numTouchPoints;
  
  public long timeSinceGestureStart;
  
  public GestureDescription.TouchPoint[] touchPoints;
  
  public GestureStep(long paramLong, int paramInt, GestureDescription.TouchPoint[] paramArrayOfTouchPoint) {
    this.timeSinceGestureStart = paramLong;
    this.numTouchPoints = paramInt;
    this.touchPoints = new GestureDescription.TouchPoint[paramInt];
    for (byte b = 0; b < paramInt; b++)
      this.touchPoints[b] = new GestureDescription.TouchPoint(paramArrayOfTouchPoint[b]); 
  }
  
  public GestureStep(Parcel paramParcel) {
    this.timeSinceGestureStart = paramParcel.readLong();
    Parcelable[] arrayOfParcelable = paramParcel.readParcelableArray(GestureDescription.TouchPoint.class.getClassLoader());
    if (arrayOfParcelable == null) {
      i = 0;
    } else {
      i = arrayOfParcelable.length;
    } 
    this.numTouchPoints = i;
    this.touchPoints = new GestureDescription.TouchPoint[i];
    for (int i = 0; i < this.numTouchPoints; i++)
      this.touchPoints[i] = (GestureDescription.TouchPoint)arrayOfParcelable[i]; 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.timeSinceGestureStart);
    paramParcel.writeParcelableArray((Parcelable[])this.touchPoints, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/GestureDescription$GestureStep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.accessibilityservice;

import android.os.Parcel;
import android.os.Parcelable;

public class TouchPoint implements Parcelable {
  public static final Parcelable.Creator<TouchPoint> CREATOR = new Parcelable.Creator<TouchPoint>() {
      public GestureDescription.TouchPoint createFromParcel(Parcel param2Parcel) {
        return new GestureDescription.TouchPoint(param2Parcel);
      }
      
      public GestureDescription.TouchPoint[] newArray(int param2Int) {
        return new GestureDescription.TouchPoint[param2Int];
      }
    };
  
  private static final int FLAG_IS_END_OF_PATH = 2;
  
  private static final int FLAG_IS_START_OF_PATH = 1;
  
  public int mContinuedStrokeId;
  
  public boolean mIsEndOfPath;
  
  public boolean mIsStartOfPath;
  
  public int mStrokeId;
  
  public float mX;
  
  public float mY;
  
  public TouchPoint() {}
  
  public TouchPoint(TouchPoint paramTouchPoint) {
    copyFrom(paramTouchPoint);
  }
  
  public TouchPoint(Parcel paramParcel) {
    this.mStrokeId = paramParcel.readInt();
    this.mContinuedStrokeId = paramParcel.readInt();
    int i = paramParcel.readInt();
    boolean bool1 = false;
    if ((i & 0x1) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsStartOfPath = bool2;
    boolean bool2 = bool1;
    if ((i & 0x2) != 0)
      bool2 = true; 
    this.mIsEndOfPath = bool2;
    this.mX = paramParcel.readFloat();
    this.mY = paramParcel.readFloat();
  }
  
  public void copyFrom(TouchPoint paramTouchPoint) {
    this.mStrokeId = paramTouchPoint.mStrokeId;
    this.mContinuedStrokeId = paramTouchPoint.mContinuedStrokeId;
    this.mIsStartOfPath = paramTouchPoint.mIsStartOfPath;
    this.mIsEndOfPath = paramTouchPoint.mIsEndOfPath;
    this.mX = paramTouchPoint.mX;
    this.mY = paramTouchPoint.mY;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("TouchPoint{mStrokeId=");
    stringBuilder.append(this.mStrokeId);
    stringBuilder.append(", mContinuedStrokeId=");
    stringBuilder.append(this.mContinuedStrokeId);
    stringBuilder.append(", mIsStartOfPath=");
    stringBuilder.append(this.mIsStartOfPath);
    stringBuilder.append(", mIsEndOfPath=");
    stringBuilder.append(this.mIsEndOfPath);
    stringBuilder.append(", mX=");
    stringBuilder.append(this.mX);
    stringBuilder.append(", mY=");
    stringBuilder.append(this.mY);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mStrokeId);
    paramParcel.writeInt(this.mContinuedStrokeId);
    int i = this.mIsStartOfPath;
    if (this.mIsEndOfPath) {
      paramInt = 2;
    } else {
      paramInt = 0;
    } 
    paramParcel.writeInt(i | paramInt);
    paramParcel.writeFloat(this.mX);
    paramParcel.writeFloat(this.mY);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/GestureDescription$TouchPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
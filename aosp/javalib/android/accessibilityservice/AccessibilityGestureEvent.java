package android.accessibilityservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class AccessibilityGestureEvent implements Parcelable {
  public static final Parcelable.Creator<AccessibilityGestureEvent> CREATOR = new Parcelable.Creator<AccessibilityGestureEvent>() {
      public AccessibilityGestureEvent createFromParcel(Parcel param1Parcel) {
        return new AccessibilityGestureEvent(param1Parcel);
      }
      
      public AccessibilityGestureEvent[] newArray(int param1Int) {
        return new AccessibilityGestureEvent[param1Int];
      }
    };
  
  private final int mDisplayId;
  
  private final int mGestureId;
  
  public AccessibilityGestureEvent(int paramInt1, int paramInt2) {
    this.mGestureId = paramInt1;
    this.mDisplayId = paramInt2;
  }
  
  private AccessibilityGestureEvent(Parcel paramParcel) {
    this.mGestureId = paramParcel.readInt();
    this.mDisplayId = paramParcel.readInt();
  }
  
  private static String eventTypeToString(int paramInt) {
    switch (paramInt) {
      default:
        return Integer.toHexString(paramInt);
      case 42:
        return "GESTURE_4_FINGER_DOUBLE_TAP_AND_HOLD";
      case 41:
        return "GESTURE_3_FINGER_DOUBLE_TAP_AND_HOLD";
      case 40:
        return "GESTURE_2_FINGER_DOUBLE_TAP_AND_HOLD";
      case 39:
        return "GESTURE_4_FINGER_TRIPLE_TAP";
      case 38:
        return "GESTURE_4_FINGER_DOUBLE_TAP";
      case 37:
        return "GESTURE_4_FINGER_SINGLE_TAP";
      case 36:
        return "GESTURE_4_FINGER_SWIPE_RIGHT";
      case 35:
        return "GESTURE_4_FINGER_SWIPE_LEFT";
      case 34:
        return "GESTURE_4_FINGER_SWIPE_DOWN";
      case 33:
        return "GESTURE_4_FINGER_SWIPE_UP";
      case 32:
        return "GESTURE_3_FINGER_SWIPE_RIGHT";
      case 31:
        return "GESTURE_3_FINGER_SWIPE_LEFT";
      case 30:
        return "GESTURE_3_FINGER_SWIPE_DOWN";
      case 29:
        return "GESTURE_3_FINGER_SWIPE_UP";
      case 28:
        return "GESTURE_2_FINGER_SWIPE_RIGHT";
      case 27:
        return "GESTURE_2_FINGER_SWIPE_LEFT";
      case 26:
        return "GESTURE_2_FINGER_SWIPE_DOWN";
      case 25:
        return "GESTURE_2_FINGER_SWIPE_UP";
      case 24:
        return "GESTURE_3_FINGER_TRIPLE_TAP";
      case 23:
        return "GESTURE_3_FINGER_DOUBLE_TAP";
      case 22:
        return "GESTURE_3_FINGER_SINGLE_TAP";
      case 21:
        return "GESTURE_2_FINGER_TRIPLE_TAP";
      case 20:
        return "GESTURE_2_FINGER_DOUBLE_TAP";
      case 19:
        return "GESTURE_2_FINGER_SINGLE_TAP";
      case 18:
        return "GESTURE_DOUBLE_TAP_AND_HOLD";
      case 17:
        return "GESTURE_DOUBLE_TAP";
      case 16:
        return "GESTURE_SWIPE_DOWN_AND_RIGHT";
      case 15:
        return "GESTURE_SWIPE_DOWN_AND_LEFT";
      case 14:
        return "GESTURE_SWIPE_UP_AND_RIGHT";
      case 13:
        return "GESTURE_SWIPE_UP_AND_LEFT";
      case 12:
        return "GESTURE_SWIPE_RIGHT_AND_DOWN";
      case 11:
        return "GESTURE_SWIPE_RIGHT_AND_UP";
      case 10:
        return "GESTURE_SWIPE_LEFT_AND_DOWN";
      case 9:
        return "GESTURE_SWIPE_LEFT_AND_UP";
      case 8:
        return "GESTURE_SWIPE_DOWN_AND_UP";
      case 7:
        return "GESTURE_SWIPE_UP_AND_DOWN";
      case 6:
        return "GESTURE_SWIPE_RIGHT_AND_LEFT";
      case 5:
        return "GESTURE_SWIPE_LEFT_AND_RIGHT";
      case 4:
        return "GESTURE_SWIPE_RIGHT";
      case 3:
        return "GESTURE_SWIPE_LEFT";
      case 2:
        return "GESTURE_SWIPE_DOWN";
      case 1:
        break;
    } 
    return "GESTURE_SWIPE_UP";
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getDisplayId() {
    return this.mDisplayId;
  }
  
  public int getGestureId() {
    return this.mGestureId;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("AccessibilityGestureEvent[");
    stringBuilder.append("gestureId: ");
    stringBuilder.append(eventTypeToString(this.mGestureId));
    stringBuilder.append(", ");
    stringBuilder.append("displayId: ");
    stringBuilder.append(this.mDisplayId);
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mGestureId);
    paramParcel.writeInt(this.mDisplayId);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface GestureId {}
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityGestureEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
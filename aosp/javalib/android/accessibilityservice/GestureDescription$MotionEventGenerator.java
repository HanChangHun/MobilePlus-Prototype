package android.accessibilityservice;

import java.util.ArrayList;
import java.util.List;

public class MotionEventGenerator {
  private static GestureDescription.TouchPoint[] sCurrentTouchPoints;
  
  private static GestureDescription.TouchPoint[] getCurrentTouchPoints(int paramInt) {
    GestureDescription.TouchPoint[] arrayOfTouchPoint = sCurrentTouchPoints;
    if (arrayOfTouchPoint == null || arrayOfTouchPoint.length < paramInt) {
      sCurrentTouchPoints = new GestureDescription.TouchPoint[paramInt];
      for (byte b = 0; b < paramInt; b++)
        sCurrentTouchPoints[b] = new GestureDescription.TouchPoint(); 
    } 
    return sCurrentTouchPoints;
  }
  
  public static List<GestureDescription.GestureStep> getGestureStepsFromGestureDescription(GestureDescription paramGestureDescription, int paramInt) {
    ArrayList<GestureDescription.GestureStep> arrayList = new ArrayList();
    GestureDescription.TouchPoint[] arrayOfTouchPoint = getCurrentTouchPoints(paramGestureDescription.getStrokeCount());
    int i = 0;
    long l1 = 0L;
    long l2;
    for (l2 = GestureDescription.access$200(paramGestureDescription, 0L); l2 >= 0L; l2 = l) {
      if (i)
        l2 = Math.min(l2, paramInt + l1); 
      i = GestureDescription.access$300(paramGestureDescription, l2, arrayOfTouchPoint);
      arrayList.add(new GestureDescription.GestureStep(l2, i, arrayOfTouchPoint));
      long l = GestureDescription.access$200(paramGestureDescription, 1L + l2);
      l1 = l2;
    } 
    return arrayList;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/GestureDescription$MotionEventGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
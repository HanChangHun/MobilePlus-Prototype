package android.accessibilityservice;

import java.util.ArrayList;
import java.util.List;

public class Builder {
  private int mDisplayId = 0;
  
  private final List<GestureDescription.StrokeDescription> mStrokes = new ArrayList<>();
  
  public Builder addStroke(GestureDescription.StrokeDescription paramStrokeDescription) {
    if (this.mStrokes.size() < 20) {
      this.mStrokes.add(paramStrokeDescription);
      if (GestureDescription.access$000(this.mStrokes) <= 60000L)
        return this; 
      this.mStrokes.remove(paramStrokeDescription);
      throw new IllegalStateException("Gesture would exceed maximum duration with new stroke");
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Attempting to add too many strokes to a gesture. Maximum is 20, got ");
    stringBuilder.append(this.mStrokes.size());
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public GestureDescription build() {
    if (this.mStrokes.size() != 0)
      return new GestureDescription(this.mStrokes, this.mDisplayId, null); 
    throw new IllegalStateException("Gestures must have at least one stroke");
  }
  
  public Builder setDisplayId(int paramInt) {
    this.mDisplayId = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/GestureDescription$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
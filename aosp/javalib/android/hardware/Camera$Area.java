package android.hardware;

import android.graphics.Rect;

@Deprecated
public class Area {
  public Rect rect;
  
  public int weight;
  
  public Area(Rect paramRect, int paramInt) {
    this.rect = paramRect;
    this.weight = paramInt;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof Area;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    Rect rect = this.rect;
    if (rect == null) {
      if (((Area)paramObject).rect != null)
        return false; 
    } else if (!rect.equals(((Area)paramObject).rect)) {
      return false;
    } 
    if (this.weight == ((Area)paramObject).weight)
      bool1 = true; 
    return bool1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/Camera$Area.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
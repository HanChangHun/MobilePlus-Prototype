package android.hardware.camera2.legacy;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.MeteringRectangle;
import android.util.Log;
import com.android.internal.util.Preconditions;

public class WeightedRectangle {
  public final Rect rect;
  
  public final int weight;
  
  public WeightedRectangle(Rect paramRect, int paramInt) {
    this.rect = (Rect)Preconditions.checkNotNull(paramRect, "rect must not be null");
    this.weight = paramInt;
  }
  
  private static int clip(int paramInt1, int paramInt2, int paramInt3, Rect paramRect, String paramString) {
    if (paramInt1 < paramInt2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("toMetering - Rectangle ");
      stringBuilder.append(paramRect);
      stringBuilder.append(" ");
      stringBuilder.append(paramString);
      stringBuilder.append(" too small, clip to ");
      stringBuilder.append(paramInt2);
      Log.w("ParameterUtils", stringBuilder.toString());
    } else {
      paramInt2 = paramInt1;
      if (paramInt1 > paramInt3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toMetering - Rectangle ");
        stringBuilder.append(paramRect);
        stringBuilder.append(" ");
        stringBuilder.append(paramString);
        stringBuilder.append(" too small, clip to ");
        stringBuilder.append(paramInt3);
        Log.w("ParameterUtils", stringBuilder.toString());
        paramInt2 = paramInt3;
      } 
    } 
    return paramInt2;
  }
  
  private static int clipLower(int paramInt1, int paramInt2, Rect paramRect, String paramString) {
    return clip(paramInt1, paramInt2, 2147483647, paramRect, paramString);
  }
  
  public Face toFace() {
    int i = clip(this.weight, 1, 100, this.rect, "score");
    return new Face(this.rect, i);
  }
  
  public Face toFace(int paramInt, Point paramPoint1, Point paramPoint2, Point paramPoint3) {
    int i = clipLower(paramInt, 0, this.rect, "id");
    paramInt = clip(this.weight, 1, 100, this.rect, "score");
    return new Face(this.rect, paramInt, i, paramPoint1, paramPoint2, paramPoint3);
  }
  
  public MeteringRectangle toMetering() {
    int i = clip(this.weight, 0, 1000, this.rect, "weight");
    return new MeteringRectangle(clipLower(this.rect.left, 0, this.rect, "left"), clipLower(this.rect.top, 0, this.rect, "top"), clipLower(this.rect.width(), 0, this.rect, "width"), clipLower(this.rect.height(), 0, this.rect, "height"), i);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/ParameterUtils$WeightedRectangle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.graphics;

public class InsetStruct {
  public final Rect opticalRect;
  
  public final float outlineAlpha;
  
  public final float outlineRadius;
  
  public final Rect outlineRect;
  
  InsetStruct(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, float paramFloat1, int paramInt9, float paramFloat2) {
    Rect rect = new Rect(paramInt1, paramInt2, paramInt3, paramInt4);
    this.opticalRect = rect;
    rect.scale(paramFloat2);
    this.outlineRect = scaleInsets(paramInt5, paramInt6, paramInt7, paramInt8, paramFloat2);
    this.outlineRadius = paramFloat1 * paramFloat2;
    this.outlineAlpha = paramInt9 / 255.0F;
  }
  
  public static Rect scaleInsets(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat) {
    if (paramFloat == 1.0F)
      return new Rect(paramInt1, paramInt2, paramInt3, paramInt4); 
    Rect rect = new Rect();
    rect.left = (int)Math.ceil((paramInt1 * paramFloat));
    rect.top = (int)Math.ceil((paramInt2 * paramFloat));
    rect.right = (int)Math.ceil((paramInt3 * paramFloat));
    rect.bottom = (int)Math.ceil((paramInt4 * paramFloat));
    return rect;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/NinePatch$InsetStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
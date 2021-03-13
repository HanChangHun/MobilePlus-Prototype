package android.graphics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Outline {
  public static final int MODE_EMPTY = 0;
  
  public static final int MODE_PATH = 2;
  
  public static final int MODE_ROUND_RECT = 1;
  
  private static final float RADIUS_UNDEFINED = -InfinityF;
  
  public float mAlpha;
  
  public int mMode = 0;
  
  public Path mPath;
  
  public float mRadius = Float.NEGATIVE_INFINITY;
  
  public final Rect mRect = new Rect();
  
  public Outline() {}
  
  public Outline(Outline paramOutline) {
    set(paramOutline);
  }
  
  public boolean canClip() {
    boolean bool;
    if (this.mMode != 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public float getAlpha() {
    return this.mAlpha;
  }
  
  public float getRadius() {
    return this.mRadius;
  }
  
  public boolean getRect(Rect paramRect) {
    if (this.mMode != 1)
      return false; 
    paramRect.set(this.mRect);
    return true;
  }
  
  public boolean isEmpty() {
    boolean bool;
    if (this.mMode == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void offset(int paramInt1, int paramInt2) {
    int i = this.mMode;
    if (i == 1) {
      this.mRect.offset(paramInt1, paramInt2);
    } else if (i == 2) {
      this.mPath.offset(paramInt1, paramInt2);
    } 
  }
  
  public void set(Outline paramOutline) {
    this.mMode = paramOutline.mMode;
    if (paramOutline.mMode == 2) {
      if (this.mPath == null)
        this.mPath = new Path(); 
      this.mPath.set(paramOutline.mPath);
    } 
    this.mRect.set(paramOutline.mRect);
    this.mRadius = paramOutline.mRadius;
    this.mAlpha = paramOutline.mAlpha;
  }
  
  public void setAlpha(float paramFloat) {
    this.mAlpha = paramFloat;
  }
  
  @Deprecated
  public void setConvexPath(Path paramPath) {
    setPath(paramPath);
  }
  
  public void setEmpty() {
    Path path = this.mPath;
    if (path != null)
      path.rewind(); 
    this.mMode = 0;
    this.mRect.setEmpty();
    this.mRadius = Float.NEGATIVE_INFINITY;
  }
  
  public void setOval(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 >= paramInt3 || paramInt2 >= paramInt4) {
      setEmpty();
      return;
    } 
    if (paramInt4 - paramInt2 == paramInt3 - paramInt1) {
      setRoundRect(paramInt1, paramInt2, paramInt3, paramInt4, (paramInt4 - paramInt2) / 2.0F);
      return;
    } 
    Path path = this.mPath;
    if (path == null) {
      this.mPath = new Path();
    } else {
      path.rewind();
    } 
    this.mMode = 2;
    this.mPath.addOval(paramInt1, paramInt2, paramInt3, paramInt4, Path.Direction.CW);
    this.mRect.setEmpty();
    this.mRadius = Float.NEGATIVE_INFINITY;
  }
  
  public void setOval(Rect paramRect) {
    setOval(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public void setPath(Path paramPath) {
    if (paramPath.isEmpty()) {
      setEmpty();
      return;
    } 
    if (this.mPath == null)
      this.mPath = new Path(); 
    this.mMode = 2;
    this.mPath.set(paramPath);
    this.mRect.setEmpty();
    this.mRadius = Float.NEGATIVE_INFINITY;
  }
  
  public void setRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    setRoundRect(paramInt1, paramInt2, paramInt3, paramInt4, 0.0F);
  }
  
  public void setRect(Rect paramRect) {
    setRect(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public void setRoundRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat) {
    if (paramInt1 >= paramInt3 || paramInt2 >= paramInt4) {
      setEmpty();
      return;
    } 
    if (this.mMode == 2)
      this.mPath.rewind(); 
    this.mMode = 1;
    this.mRect.set(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mRadius = paramFloat;
  }
  
  public void setRoundRect(Rect paramRect, float paramFloat) {
    setRoundRect(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom, paramFloat);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Mode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Outline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
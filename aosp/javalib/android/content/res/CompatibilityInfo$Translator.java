package android.content.res;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.WindowManager;

public class Translator {
  public final float applicationInvertedScale;
  
  public final float applicationScale;
  
  private Rect mContentInsetsBuffer = null;
  
  private Region mTouchableAreaBuffer = null;
  
  private Rect mVisibleInsetsBuffer = null;
  
  Translator(CompatibilityInfo paramCompatibilityInfo) {
    this(paramCompatibilityInfo.applicationScale, paramCompatibilityInfo.applicationInvertedScale);
  }
  
  Translator(float paramFloat1, float paramFloat2) {
    this.applicationScale = paramFloat1;
    this.applicationInvertedScale = paramFloat2;
  }
  
  public Rect getTranslatedContentInsets(Rect paramRect) {
    if (this.mContentInsetsBuffer == null)
      this.mContentInsetsBuffer = new Rect(); 
    this.mContentInsetsBuffer.set(paramRect);
    translateRectInAppWindowToScreen(this.mContentInsetsBuffer);
    return this.mContentInsetsBuffer;
  }
  
  public Region getTranslatedTouchableArea(Region paramRegion) {
    if (this.mTouchableAreaBuffer == null)
      this.mTouchableAreaBuffer = new Region(); 
    this.mTouchableAreaBuffer.set(paramRegion);
    this.mTouchableAreaBuffer.scale(this.applicationScale);
    return this.mTouchableAreaBuffer;
  }
  
  public Rect getTranslatedVisibleInsets(Rect paramRect) {
    if (this.mVisibleInsetsBuffer == null)
      this.mVisibleInsetsBuffer = new Rect(); 
    this.mVisibleInsetsBuffer.set(paramRect);
    translateRectInAppWindowToScreen(this.mVisibleInsetsBuffer);
    return this.mVisibleInsetsBuffer;
  }
  
  public void translateCanvas(Canvas paramCanvas) {
    if (this.applicationScale == 1.5F)
      paramCanvas.translate(0.0026143792F, 0.0026143792F); 
    float f = this.applicationScale;
    paramCanvas.scale(f, f);
  }
  
  public void translateEventInScreenToAppWindow(MotionEvent paramMotionEvent) {
    paramMotionEvent.scale(this.applicationInvertedScale);
  }
  
  public void translateLayoutParamsInAppWindowToScreen(WindowManager.LayoutParams paramLayoutParams) {
    paramLayoutParams.scale(this.applicationScale);
  }
  
  public void translatePointInScreenToAppWindow(PointF paramPointF) {
    float f = this.applicationInvertedScale;
    if (f != 1.0F) {
      paramPointF.x *= f;
      paramPointF.y *= f;
    } 
  }
  
  public void translateRectInAppWindowToScreen(Rect paramRect) {
    paramRect.scale(this.applicationScale);
  }
  
  public void translateRectInScreenToAppWinFrame(Rect paramRect) {
    paramRect.scale(this.applicationInvertedScale);
  }
  
  public void translateRectInScreenToAppWindow(Rect paramRect) {
    paramRect.scale(this.applicationInvertedScale);
  }
  
  public void translateRegionInWindowToScreen(Region paramRegion) {
    paramRegion.scale(this.applicationScale);
  }
  
  public void translateWindowLayout(WindowManager.LayoutParams paramLayoutParams) {
    paramLayoutParams.scale(this.applicationScale);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/CompatibilityInfo$Translator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.ComponentName;
import android.graphics.ColorSpace;
import android.graphics.GraphicBuffer;
import android.graphics.Point;
import android.graphics.Rect;

public final class Builder {
  private ColorSpace mColorSpace;
  
  private Rect mContentInsets;
  
  private long mId;
  
  private boolean mIsRealSnapshot;
  
  private boolean mIsTranslucent;
  
  private int mOrientation;
  
  private int mPixelFormat;
  
  private int mRotation;
  
  private GraphicBuffer mSnapshot;
  
  private int mSystemUiVisibility;
  
  private Point mTaskSize;
  
  private ComponentName mTopActivity;
  
  private int mWindowingMode;
  
  public ActivityManager.TaskSnapshot build() {
    return new ActivityManager.TaskSnapshot(this.mId, this.mTopActivity, this.mSnapshot, this.mColorSpace, this.mOrientation, this.mRotation, this.mTaskSize, this.mContentInsets, false, this.mIsRealSnapshot, this.mWindowingMode, this.mSystemUiVisibility, this.mIsTranslucent);
  }
  
  public int getPixelFormat() {
    return this.mPixelFormat;
  }
  
  public Builder setColorSpace(ColorSpace paramColorSpace) {
    this.mColorSpace = paramColorSpace;
    return this;
  }
  
  public Builder setContentInsets(Rect paramRect) {
    this.mContentInsets = paramRect;
    return this;
  }
  
  public Builder setId(long paramLong) {
    this.mId = paramLong;
    return this;
  }
  
  public Builder setIsRealSnapshot(boolean paramBoolean) {
    this.mIsRealSnapshot = paramBoolean;
    return this;
  }
  
  public Builder setIsTranslucent(boolean paramBoolean) {
    this.mIsTranslucent = paramBoolean;
    return this;
  }
  
  public Builder setOrientation(int paramInt) {
    this.mOrientation = paramInt;
    return this;
  }
  
  public Builder setPixelFormat(int paramInt) {
    this.mPixelFormat = paramInt;
    return this;
  }
  
  public Builder setRotation(int paramInt) {
    this.mRotation = paramInt;
    return this;
  }
  
  public Builder setSnapshot(GraphicBuffer paramGraphicBuffer) {
    this.mSnapshot = paramGraphicBuffer;
    return this;
  }
  
  public Builder setSystemUiVisibility(int paramInt) {
    this.mSystemUiVisibility = paramInt;
    return this;
  }
  
  public Builder setTaskSize(Point paramPoint) {
    this.mTaskSize = paramPoint;
    return this;
  }
  
  public Builder setTopActivityComponent(ComponentName paramComponentName) {
    this.mTopActivity = paramComponentName;
    return this;
  }
  
  public Builder setWindowingMode(int paramInt) {
    this.mWindowingMode = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$TaskSnapshot$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
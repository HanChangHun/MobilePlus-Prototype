package android.accessibilityservice;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import com.android.internal.util.Preconditions;

public class StrokeDescription {
  private static final int INVALID_STROKE_ID = -1;
  
  static int sIdCounter;
  
  boolean mContinued;
  
  int mContinuedStrokeId = -1;
  
  long mEndTime;
  
  int mId;
  
  Path mPath;
  
  private PathMeasure mPathMeasure;
  
  long mStartTime;
  
  float[] mTapLocation;
  
  private float mTimeToLengthConversion;
  
  public StrokeDescription(Path paramPath, long paramLong1, long paramLong2) {
    this(paramPath, paramLong1, paramLong2, false);
  }
  
  public StrokeDescription(Path paramPath, long paramLong1, long paramLong2, boolean paramBoolean) {
    this.mContinued = paramBoolean;
    boolean bool = true;
    if (paramLong2 > 0L) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    Preconditions.checkArgument(paramBoolean, "Duration must be positive");
    if (paramLong1 >= 0L) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    Preconditions.checkArgument(paramBoolean, "Start time must not be negative");
    Preconditions.checkArgument(paramPath.isEmpty() ^ true, "Path is empty");
    RectF rectF = new RectF();
    paramPath.computeBounds(rectF, false);
    if (rectF.bottom >= 0.0F && rectF.top >= 0.0F && rectF.right >= 0.0F && rectF.left >= 0.0F) {
      paramBoolean = bool;
    } else {
      paramBoolean = false;
    } 
    Preconditions.checkArgument(paramBoolean, "Path bounds must not be negative");
    this.mPath = new Path(paramPath);
    PathMeasure pathMeasure = new PathMeasure(paramPath, false);
    this.mPathMeasure = pathMeasure;
    if (pathMeasure.getLength() == 0.0F) {
      paramPath = new Path(paramPath);
      paramPath.lineTo(-1.0F, -1.0F);
      this.mTapLocation = new float[2];
      (new PathMeasure(paramPath, false)).getPosTan(0.0F, this.mTapLocation, null);
    } 
    if (!this.mPathMeasure.nextContour()) {
      this.mPathMeasure.setPath(this.mPath, false);
      this.mStartTime = paramLong1;
      this.mEndTime = paramLong1 + paramLong2;
      this.mTimeToLengthConversion = getLength() / (float)paramLong2;
      int i = sIdCounter;
      sIdCounter = i + 1;
      this.mId = i;
      return;
    } 
    throw new IllegalArgumentException("Path has more than one contour");
  }
  
  public StrokeDescription continueStroke(Path paramPath, long paramLong1, long paramLong2, boolean paramBoolean) {
    if (this.mContinued) {
      StrokeDescription strokeDescription = new StrokeDescription(paramPath, paramLong1, paramLong2, paramBoolean);
      strokeDescription.mContinuedStrokeId = this.mId;
      return strokeDescription;
    } 
    throw new IllegalStateException("Only strokes marked willContinue can be continued");
  }
  
  public int getContinuedStrokeId() {
    return this.mContinuedStrokeId;
  }
  
  public long getDuration() {
    return this.mEndTime - this.mStartTime;
  }
  
  public int getId() {
    return this.mId;
  }
  
  float getLength() {
    return this.mPathMeasure.getLength();
  }
  
  public Path getPath() {
    return new Path(this.mPath);
  }
  
  boolean getPosForTime(long paramLong, float[] paramArrayOffloat) {
    float[] arrayOfFloat = this.mTapLocation;
    if (arrayOfFloat != null) {
      paramArrayOffloat[0] = arrayOfFloat[0];
      paramArrayOffloat[1] = arrayOfFloat[1];
      return true;
    } 
    if (paramLong == this.mEndTime)
      return this.mPathMeasure.getPosTan(getLength(), paramArrayOffloat, null); 
    float f1 = this.mTimeToLengthConversion;
    float f2 = (float)(paramLong - this.mStartTime);
    return this.mPathMeasure.getPosTan(f1 * f2, paramArrayOffloat, null);
  }
  
  public long getStartTime() {
    return this.mStartTime;
  }
  
  boolean hasPointForTime(long paramLong) {
    boolean bool;
    if (paramLong >= this.mStartTime && paramLong <= this.mEndTime) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean willContinue() {
    return this.mContinued;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/GestureDescription$StrokeDescription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
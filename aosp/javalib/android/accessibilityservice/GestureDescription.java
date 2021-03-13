package android.accessibilityservice;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

public final class GestureDescription {
  private static final long MAX_GESTURE_DURATION_MS = 60000L;
  
  private static final int MAX_STROKE_COUNT = 20;
  
  private final int mDisplayId;
  
  private final List<StrokeDescription> mStrokes;
  
  private final float[] mTempPos;
  
  private GestureDescription() {
    this(new ArrayList<>());
  }
  
  private GestureDescription(List<StrokeDescription> paramList) {
    this(paramList, 0);
  }
  
  private GestureDescription(List<StrokeDescription> paramList, int paramInt) {
    ArrayList<StrokeDescription> arrayList = new ArrayList();
    this.mStrokes = arrayList;
    this.mTempPos = new float[2];
    arrayList.addAll(paramList);
    this.mDisplayId = paramInt;
  }
  
  public static long getMaxGestureDuration() {
    return 60000L;
  }
  
  public static int getMaxStrokeCount() {
    return 20;
  }
  
  private long getNextKeyPointAtLeast(long paramLong) {
    long l = Long.MAX_VALUE;
    for (byte b = 0; b < this.mStrokes.size(); b++) {
      long l1 = ((StrokeDescription)this.mStrokes.get(b)).mStartTime;
      long l2 = l;
      if (l1 < l) {
        l2 = l;
        if (l1 >= paramLong)
          l2 = l1; 
      } 
      l1 = ((StrokeDescription)this.mStrokes.get(b)).mEndTime;
      l = l2;
      if (l1 < l2) {
        l = l2;
        if (l1 >= paramLong)
          l = l1; 
      } 
    } 
    if (l == Long.MAX_VALUE)
      l = -1L; 
    return l;
  }
  
  private int getPointsForTime(long paramLong, TouchPoint[] paramArrayOfTouchPoint) {
    int i = 0;
    byte b = 0;
    while (b < this.mStrokes.size()) {
      StrokeDescription strokeDescription = this.mStrokes.get(b);
      int j = i;
      if (strokeDescription.hasPointForTime(paramLong)) {
        boolean bool;
        (paramArrayOfTouchPoint[i]).mStrokeId = strokeDescription.getId();
        (paramArrayOfTouchPoint[i]).mContinuedStrokeId = strokeDescription.getContinuedStrokeId();
        TouchPoint touchPoint = paramArrayOfTouchPoint[i];
        if (strokeDescription.getContinuedStrokeId() < 0 && paramLong == strokeDescription.mStartTime) {
          bool = true;
        } else {
          bool = false;
        } 
        touchPoint.mIsStartOfPath = bool;
        touchPoint = paramArrayOfTouchPoint[i];
        if (!strokeDescription.willContinue() && paramLong == strokeDescription.mEndTime) {
          bool = true;
        } else {
          bool = false;
        } 
        touchPoint.mIsEndOfPath = bool;
        strokeDescription.getPosForTime(paramLong, this.mTempPos);
        (paramArrayOfTouchPoint[i]).mX = Math.round(this.mTempPos[0]);
        (paramArrayOfTouchPoint[i]).mY = Math.round(this.mTempPos[1]);
        j = i + 1;
      } 
      b++;
      i = j;
    } 
    return i;
  }
  
  private static long getTotalDuration(List<StrokeDescription> paramList) {
    long l = Long.MIN_VALUE;
    for (byte b = 0; b < paramList.size(); b++)
      l = Math.max(l, ((StrokeDescription)paramList.get(b)).mEndTime); 
    return Math.max(l, 0L);
  }
  
  public int getDisplayId() {
    return this.mDisplayId;
  }
  
  public StrokeDescription getStroke(int paramInt) {
    return this.mStrokes.get(paramInt);
  }
  
  public int getStrokeCount() {
    return this.mStrokes.size();
  }
  
  public static class Builder {
    private int mDisplayId = 0;
    
    private final List<GestureDescription.StrokeDescription> mStrokes = new ArrayList<>();
    
    public Builder addStroke(GestureDescription.StrokeDescription param1StrokeDescription) {
      if (this.mStrokes.size() < 20) {
        this.mStrokes.add(param1StrokeDescription);
        if (GestureDescription.getTotalDuration(this.mStrokes) <= 60000L)
          return this; 
        this.mStrokes.remove(param1StrokeDescription);
        throw new IllegalStateException("Gesture would exceed maximum duration with new stroke");
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Attempting to add too many strokes to a gesture. Maximum is 20, got ");
      stringBuilder.append(this.mStrokes.size());
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    public GestureDescription build() {
      if (this.mStrokes.size() != 0)
        return new GestureDescription(this.mStrokes, this.mDisplayId); 
      throw new IllegalStateException("Gestures must have at least one stroke");
    }
    
    public Builder setDisplayId(int param1Int) {
      this.mDisplayId = param1Int;
      return this;
    }
  }
  
  public static class GestureStep implements Parcelable {
    public static final Parcelable.Creator<GestureStep> CREATOR = new Parcelable.Creator<GestureStep>() {
        public GestureDescription.GestureStep createFromParcel(Parcel param2Parcel) {
          return new GestureDescription.GestureStep(param2Parcel);
        }
        
        public GestureDescription.GestureStep[] newArray(int param2Int) {
          return new GestureDescription.GestureStep[param2Int];
        }
      };
    
    public int numTouchPoints;
    
    public long timeSinceGestureStart;
    
    public GestureDescription.TouchPoint[] touchPoints;
    
    public GestureStep(long param1Long, int param1Int, GestureDescription.TouchPoint[] param1ArrayOfTouchPoint) {
      this.timeSinceGestureStart = param1Long;
      this.numTouchPoints = param1Int;
      this.touchPoints = new GestureDescription.TouchPoint[param1Int];
      for (byte b = 0; b < param1Int; b++)
        this.touchPoints[b] = new GestureDescription.TouchPoint(param1ArrayOfTouchPoint[b]); 
    }
    
    public GestureStep(Parcel param1Parcel) {
      this.timeSinceGestureStart = param1Parcel.readLong();
      Parcelable[] arrayOfParcelable = param1Parcel.readParcelableArray(GestureDescription.TouchPoint.class.getClassLoader());
      if (arrayOfParcelable == null) {
        i = 0;
      } else {
        i = arrayOfParcelable.length;
      } 
      this.numTouchPoints = i;
      this.touchPoints = new GestureDescription.TouchPoint[i];
      for (int i = 0; i < this.numTouchPoints; i++)
        this.touchPoints[i] = (GestureDescription.TouchPoint)arrayOfParcelable[i]; 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeLong(this.timeSinceGestureStart);
      param1Parcel.writeParcelableArray((Parcelable[])this.touchPoints, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<GestureStep> {
    public GestureDescription.GestureStep createFromParcel(Parcel param1Parcel) {
      return new GestureDescription.GestureStep(param1Parcel);
    }
    
    public GestureDescription.GestureStep[] newArray(int param1Int) {
      return new GestureDescription.GestureStep[param1Int];
    }
  }
  
  public static class MotionEventGenerator {
    private static GestureDescription.TouchPoint[] sCurrentTouchPoints;
    
    private static GestureDescription.TouchPoint[] getCurrentTouchPoints(int param1Int) {
      GestureDescription.TouchPoint[] arrayOfTouchPoint = sCurrentTouchPoints;
      if (arrayOfTouchPoint == null || arrayOfTouchPoint.length < param1Int) {
        sCurrentTouchPoints = new GestureDescription.TouchPoint[param1Int];
        for (byte b = 0; b < param1Int; b++)
          sCurrentTouchPoints[b] = new GestureDescription.TouchPoint(); 
      } 
      return sCurrentTouchPoints;
    }
    
    public static List<GestureDescription.GestureStep> getGestureStepsFromGestureDescription(GestureDescription param1GestureDescription, int param1Int) {
      ArrayList<GestureDescription.GestureStep> arrayList = new ArrayList();
      GestureDescription.TouchPoint[] arrayOfTouchPoint = getCurrentTouchPoints(param1GestureDescription.getStrokeCount());
      int i = 0;
      long l1 = 0L;
      long l2;
      for (l2 = param1GestureDescription.getNextKeyPointAtLeast(0L); l2 >= 0L; l2 = l) {
        if (i)
          l2 = Math.min(l2, param1Int + l1); 
        i = param1GestureDescription.getPointsForTime(l2, arrayOfTouchPoint);
        arrayList.add(new GestureDescription.GestureStep(l2, i, arrayOfTouchPoint));
        long l = param1GestureDescription.getNextKeyPointAtLeast(1L + l2);
        l1 = l2;
      } 
      return arrayList;
    }
  }
  
  public static class StrokeDescription {
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
    
    public StrokeDescription(Path param1Path, long param1Long1, long param1Long2) {
      this(param1Path, param1Long1, param1Long2, false);
    }
    
    public StrokeDescription(Path param1Path, long param1Long1, long param1Long2, boolean param1Boolean) {
      this.mContinued = param1Boolean;
      boolean bool = true;
      if (param1Long2 > 0L) {
        param1Boolean = true;
      } else {
        param1Boolean = false;
      } 
      Preconditions.checkArgument(param1Boolean, "Duration must be positive");
      if (param1Long1 >= 0L) {
        param1Boolean = true;
      } else {
        param1Boolean = false;
      } 
      Preconditions.checkArgument(param1Boolean, "Start time must not be negative");
      Preconditions.checkArgument(param1Path.isEmpty() ^ true, "Path is empty");
      RectF rectF = new RectF();
      param1Path.computeBounds(rectF, false);
      if (rectF.bottom >= 0.0F && rectF.top >= 0.0F && rectF.right >= 0.0F && rectF.left >= 0.0F) {
        param1Boolean = bool;
      } else {
        param1Boolean = false;
      } 
      Preconditions.checkArgument(param1Boolean, "Path bounds must not be negative");
      this.mPath = new Path(param1Path);
      PathMeasure pathMeasure = new PathMeasure(param1Path, false);
      this.mPathMeasure = pathMeasure;
      if (pathMeasure.getLength() == 0.0F) {
        param1Path = new Path(param1Path);
        param1Path.lineTo(-1.0F, -1.0F);
        this.mTapLocation = new float[2];
        (new PathMeasure(param1Path, false)).getPosTan(0.0F, this.mTapLocation, null);
      } 
      if (!this.mPathMeasure.nextContour()) {
        this.mPathMeasure.setPath(this.mPath, false);
        this.mStartTime = param1Long1;
        this.mEndTime = param1Long1 + param1Long2;
        this.mTimeToLengthConversion = getLength() / (float)param1Long2;
        int i = sIdCounter;
        sIdCounter = i + 1;
        this.mId = i;
        return;
      } 
      throw new IllegalArgumentException("Path has more than one contour");
    }
    
    public StrokeDescription continueStroke(Path param1Path, long param1Long1, long param1Long2, boolean param1Boolean) {
      if (this.mContinued) {
        StrokeDescription strokeDescription = new StrokeDescription(param1Path, param1Long1, param1Long2, param1Boolean);
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
    
    boolean getPosForTime(long param1Long, float[] param1ArrayOffloat) {
      float[] arrayOfFloat = this.mTapLocation;
      if (arrayOfFloat != null) {
        param1ArrayOffloat[0] = arrayOfFloat[0];
        param1ArrayOffloat[1] = arrayOfFloat[1];
        return true;
      } 
      if (param1Long == this.mEndTime)
        return this.mPathMeasure.getPosTan(getLength(), param1ArrayOffloat, null); 
      float f1 = this.mTimeToLengthConversion;
      float f2 = (float)(param1Long - this.mStartTime);
      return this.mPathMeasure.getPosTan(f1 * f2, param1ArrayOffloat, null);
    }
    
    public long getStartTime() {
      return this.mStartTime;
    }
    
    boolean hasPointForTime(long param1Long) {
      boolean bool;
      if (param1Long >= this.mStartTime && param1Long <= this.mEndTime) {
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
  
  public static class TouchPoint implements Parcelable {
    public static final Parcelable.Creator<TouchPoint> CREATOR = new Parcelable.Creator<TouchPoint>() {
        public GestureDescription.TouchPoint createFromParcel(Parcel param2Parcel) {
          return new GestureDescription.TouchPoint(param2Parcel);
        }
        
        public GestureDescription.TouchPoint[] newArray(int param2Int) {
          return new GestureDescription.TouchPoint[param2Int];
        }
      };
    
    private static final int FLAG_IS_END_OF_PATH = 2;
    
    private static final int FLAG_IS_START_OF_PATH = 1;
    
    public int mContinuedStrokeId;
    
    public boolean mIsEndOfPath;
    
    public boolean mIsStartOfPath;
    
    public int mStrokeId;
    
    public float mX;
    
    public float mY;
    
    public TouchPoint() {}
    
    public TouchPoint(TouchPoint param1TouchPoint) {
      copyFrom(param1TouchPoint);
    }
    
    public TouchPoint(Parcel param1Parcel) {
      this.mStrokeId = param1Parcel.readInt();
      this.mContinuedStrokeId = param1Parcel.readInt();
      int i = param1Parcel.readInt();
      boolean bool1 = false;
      if ((i & 0x1) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mIsStartOfPath = bool2;
      boolean bool2 = bool1;
      if ((i & 0x2) != 0)
        bool2 = true; 
      this.mIsEndOfPath = bool2;
      this.mX = param1Parcel.readFloat();
      this.mY = param1Parcel.readFloat();
    }
    
    public void copyFrom(TouchPoint param1TouchPoint) {
      this.mStrokeId = param1TouchPoint.mStrokeId;
      this.mContinuedStrokeId = param1TouchPoint.mContinuedStrokeId;
      this.mIsStartOfPath = param1TouchPoint.mIsStartOfPath;
      this.mIsEndOfPath = param1TouchPoint.mIsEndOfPath;
      this.mX = param1TouchPoint.mX;
      this.mY = param1TouchPoint.mY;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("TouchPoint{mStrokeId=");
      stringBuilder.append(this.mStrokeId);
      stringBuilder.append(", mContinuedStrokeId=");
      stringBuilder.append(this.mContinuedStrokeId);
      stringBuilder.append(", mIsStartOfPath=");
      stringBuilder.append(this.mIsStartOfPath);
      stringBuilder.append(", mIsEndOfPath=");
      stringBuilder.append(this.mIsEndOfPath);
      stringBuilder.append(", mX=");
      stringBuilder.append(this.mX);
      stringBuilder.append(", mY=");
      stringBuilder.append(this.mY);
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mStrokeId);
      param1Parcel.writeInt(this.mContinuedStrokeId);
      int i = this.mIsStartOfPath;
      if (this.mIsEndOfPath) {
        param1Int = 2;
      } else {
        param1Int = 0;
      } 
      param1Parcel.writeInt(i | param1Int);
      param1Parcel.writeFloat(this.mX);
      param1Parcel.writeFloat(this.mY);
    }
  }
  
  class null implements Parcelable.Creator<TouchPoint> {
    public GestureDescription.TouchPoint createFromParcel(Parcel param1Parcel) {
      return new GestureDescription.TouchPoint(param1Parcel);
    }
    
    public GestureDescription.TouchPoint[] newArray(int param1Int) {
      return new GestureDescription.TouchPoint[param1Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/GestureDescription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
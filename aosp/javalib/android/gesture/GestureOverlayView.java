package android.gesture;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.android.internal.R;
import java.util.ArrayList;

public class GestureOverlayView extends FrameLayout {
  private static final boolean DITHER_FLAG = true;
  
  private static final int FADE_ANIMATION_RATE = 16;
  
  private static final boolean GESTURE_RENDERING_ANTIALIAS = true;
  
  public static final int GESTURE_STROKE_TYPE_MULTIPLE = 1;
  
  public static final int GESTURE_STROKE_TYPE_SINGLE = 0;
  
  public static final int ORIENTATION_HORIZONTAL = 0;
  
  public static final int ORIENTATION_VERTICAL = 1;
  
  private int mCertainGestureColor = -256;
  
  private int mCurrentColor;
  
  private Gesture mCurrentGesture;
  
  private float mCurveEndX;
  
  private float mCurveEndY;
  
  private long mFadeDuration = 150L;
  
  private boolean mFadeEnabled = true;
  
  private long mFadeOffset = 420L;
  
  private float mFadingAlpha = 1.0F;
  
  private boolean mFadingHasStarted;
  
  private final FadeOutRunnable mFadingOut = new FadeOutRunnable();
  
  private long mFadingStart;
  
  private final Paint mGesturePaint = new Paint();
  
  private float mGestureStrokeAngleThreshold = 40.0F;
  
  private float mGestureStrokeLengthThreshold = 50.0F;
  
  private float mGestureStrokeSquarenessTreshold = 0.275F;
  
  private int mGestureStrokeType = 0;
  
  private float mGestureStrokeWidth = 12.0F;
  
  private boolean mGestureVisible = true;
  
  private boolean mHandleGestureActions;
  
  private boolean mInterceptEvents = true;
  
  private final AccelerateDecelerateInterpolator mInterpolator = new AccelerateDecelerateInterpolator();
  
  private final Rect mInvalidRect = new Rect();
  
  private int mInvalidateExtraBorder = 10;
  
  private boolean mIsFadingOut = false;
  
  private boolean mIsGesturing = false;
  
  private boolean mIsListeningForGestures;
  
  private final ArrayList<OnGestureListener> mOnGestureListeners = new ArrayList<>();
  
  private final ArrayList<OnGesturePerformedListener> mOnGesturePerformedListeners = new ArrayList<>();
  
  private final ArrayList<OnGesturingListener> mOnGesturingListeners = new ArrayList<>();
  
  private int mOrientation = 1;
  
  private final Path mPath = new Path();
  
  private boolean mPreviousWasGesturing = false;
  
  private boolean mResetGesture;
  
  private final ArrayList<GesturePoint> mStrokeBuffer = new ArrayList<>(100);
  
  private float mTotalLength;
  
  private int mUncertainGestureColor = 1224736512;
  
  private float mX;
  
  private float mY;
  
  public GestureOverlayView(Context paramContext) {
    super(paramContext);
    init();
  }
  
  public GestureOverlayView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 17956942);
  }
  
  public GestureOverlayView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public GestureOverlayView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.GestureOverlayView, paramInt1, paramInt2);
    float f = typedArray.getFloat(1, this.mGestureStrokeWidth);
    this.mGestureStrokeWidth = f;
    this.mInvalidateExtraBorder = Math.max(1, (int)f - 1);
    this.mCertainGestureColor = typedArray.getColor(2, this.mCertainGestureColor);
    this.mUncertainGestureColor = typedArray.getColor(3, this.mUncertainGestureColor);
    this.mFadeDuration = typedArray.getInt(5, (int)this.mFadeDuration);
    this.mFadeOffset = typedArray.getInt(4, (int)this.mFadeOffset);
    this.mGestureStrokeType = typedArray.getInt(6, this.mGestureStrokeType);
    this.mGestureStrokeLengthThreshold = typedArray.getFloat(7, this.mGestureStrokeLengthThreshold);
    this.mGestureStrokeAngleThreshold = typedArray.getFloat(9, this.mGestureStrokeAngleThreshold);
    this.mGestureStrokeSquarenessTreshold = typedArray.getFloat(8, this.mGestureStrokeSquarenessTreshold);
    this.mInterceptEvents = typedArray.getBoolean(10, this.mInterceptEvents);
    this.mFadeEnabled = typedArray.getBoolean(11, this.mFadeEnabled);
    this.mOrientation = typedArray.getInt(0, this.mOrientation);
    typedArray.recycle();
    init();
  }
  
  private void cancelGesture(MotionEvent paramMotionEvent) {
    ArrayList<OnGestureListener> arrayList = this.mOnGestureListeners;
    int i = arrayList.size();
    for (byte b = 0; b < i; b++)
      ((OnGestureListener)arrayList.get(b)).onGestureCancelled(this, paramMotionEvent); 
    clear(false);
  }
  
  private void clear(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    setPaintAlpha(255);
    removeCallbacks(this.mFadingOut);
    this.mResetGesture = false;
    this.mFadingOut.fireActionPerformed = paramBoolean2;
    this.mFadingOut.resetMultipleStrokes = false;
    if (paramBoolean1 && this.mCurrentGesture != null) {
      this.mFadingAlpha = 1.0F;
      this.mIsFadingOut = true;
      this.mFadingHasStarted = false;
      long l1 = AnimationUtils.currentAnimationTimeMillis();
      long l2 = this.mFadeOffset;
      this.mFadingStart = l1 + l2;
      postDelayed(this.mFadingOut, l2);
    } else {
      this.mFadingAlpha = 1.0F;
      this.mIsFadingOut = false;
      this.mFadingHasStarted = false;
      if (paramBoolean3) {
        this.mCurrentGesture = null;
        this.mPath.rewind();
        invalidate();
      } else if (paramBoolean2) {
        postDelayed(this.mFadingOut, this.mFadeOffset);
      } else if (this.mGestureStrokeType == 1) {
        this.mFadingOut.resetMultipleStrokes = true;
        postDelayed(this.mFadingOut, this.mFadeOffset);
      } else {
        this.mCurrentGesture = null;
        this.mPath.rewind();
        invalidate();
      } 
    } 
  }
  
  private void fireOnGesturePerformed() {
    ArrayList<OnGesturePerformedListener> arrayList = this.mOnGesturePerformedListeners;
    int i = arrayList.size();
    for (byte b = 0; b < i; b++)
      ((OnGesturePerformedListener)arrayList.get(b)).onGesturePerformed(this, this.mCurrentGesture); 
  }
  
  private void init() {
    setWillNotDraw(false);
    Paint paint = this.mGesturePaint;
    paint.setAntiAlias(true);
    paint.setColor(this.mCertainGestureColor);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeJoin(Paint.Join.ROUND);
    paint.setStrokeCap(Paint.Cap.ROUND);
    paint.setStrokeWidth(this.mGestureStrokeWidth);
    paint.setDither(true);
    this.mCurrentColor = this.mCertainGestureColor;
    setPaintAlpha(255);
  }
  
  private boolean processEvent(MotionEvent paramMotionEvent) {
    Rect rect;
    int i = paramMotionEvent.getAction();
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i == 3 && this.mIsListeningForGestures) {
            touchUp(paramMotionEvent, true);
            invalidate();
            return true;
          } 
        } else if (this.mIsListeningForGestures) {
          rect = touchMove(paramMotionEvent);
          if (rect != null)
            invalidate(rect); 
          return true;
        } 
      } else if (this.mIsListeningForGestures) {
        touchUp((MotionEvent)rect, false);
        invalidate();
        return true;
      } 
      return false;
    } 
    touchDown((MotionEvent)rect);
    invalidate();
    return true;
  }
  
  private void setCurrentColor(int paramInt) {
    this.mCurrentColor = paramInt;
    if (this.mFadingHasStarted) {
      setPaintAlpha((int)(this.mFadingAlpha * 255.0F));
    } else {
      setPaintAlpha(255);
    } 
    invalidate();
  }
  
  private void setPaintAlpha(int paramInt) {
    int i = this.mCurrentColor;
    this.mGesturePaint.setColor(i << 8 >>> 8 | (i >>> 24) * (paramInt + (paramInt >> 7)) >> 8 << 24);
  }
  
  private void touchDown(MotionEvent paramMotionEvent) {
    this.mIsListeningForGestures = true;
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    this.mX = f1;
    this.mY = f2;
    this.mTotalLength = 0.0F;
    this.mIsGesturing = false;
    if (this.mGestureStrokeType == 0 || this.mResetGesture) {
      if (this.mHandleGestureActions)
        setCurrentColor(this.mUncertainGestureColor); 
      this.mResetGesture = false;
      this.mCurrentGesture = null;
      this.mPath.rewind();
    } else {
      Gesture gesture = this.mCurrentGesture;
      if ((gesture == null || gesture.getStrokesCount() == 0) && this.mHandleGestureActions)
        setCurrentColor(this.mUncertainGestureColor); 
    } 
    if (this.mFadingHasStarted) {
      cancelClearAnimation();
    } else if (this.mIsFadingOut) {
      setPaintAlpha(255);
      this.mIsFadingOut = false;
      this.mFadingHasStarted = false;
      removeCallbacks(this.mFadingOut);
    } 
    if (this.mCurrentGesture == null)
      this.mCurrentGesture = new Gesture(); 
    this.mStrokeBuffer.add(new GesturePoint(f1, f2, paramMotionEvent.getEventTime()));
    this.mPath.moveTo(f1, f2);
    int i = this.mInvalidateExtraBorder;
    this.mInvalidRect.set((int)f1 - i, (int)f2 - i, (int)f1 + i, (int)f2 + i);
    this.mCurveEndX = f1;
    this.mCurveEndY = f2;
    ArrayList<OnGestureListener> arrayList = this.mOnGestureListeners;
    int j = arrayList.size();
    for (i = 0; i < j; i++)
      ((OnGestureListener)arrayList.get(i)).onGestureStarted(this, paramMotionEvent); 
  }
  
  private Rect touchMove(MotionEvent paramMotionEvent) {
    Rect rect = null;
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    float f3 = this.mX;
    float f4 = this.mY;
    float f5 = Math.abs(f1 - f3);
    float f6 = Math.abs(f2 - f4);
    if (f5 >= 3.0F || f6 >= 3.0F) {
      rect = this.mInvalidRect;
      int i = this.mInvalidateExtraBorder;
      float f7 = this.mCurveEndX;
      int j = (int)f7;
      float f8 = this.mCurveEndY;
      rect.set(j - i, (int)f8 - i, (int)f7 + i, (int)f8 + i);
      f7 = (f1 + f3) / 2.0F;
      this.mCurveEndX = f7;
      f8 = (f2 + f4) / 2.0F;
      this.mCurveEndY = f8;
      this.mPath.quadTo(f3, f4, f7, f8);
      rect.union((int)f3 - i, (int)f4 - i, (int)f3 + i, (int)f4 + i);
      rect.union((int)f7 - i, (int)f8 - i, (int)f7 + i, (int)f8 + i);
      this.mX = f1;
      this.mY = f2;
      this.mStrokeBuffer.add(new GesturePoint(f1, f2, paramMotionEvent.getEventTime()));
      if (this.mHandleGestureActions && !this.mIsGesturing) {
        f4 = this.mTotalLength + (float)Math.hypot(f5, f6);
        this.mTotalLength = f4;
        if (f4 > this.mGestureStrokeLengthThreshold) {
          OrientedBoundingBox orientedBoundingBox = GestureUtils.computeOrientedBoundingBox(this.mStrokeBuffer);
          f2 = Math.abs(orientedBoundingBox.orientation);
          f4 = f2;
          if (f2 > 90.0F)
            f4 = 180.0F - f2; 
          if (orientedBoundingBox.squareness > this.mGestureStrokeSquarenessTreshold || ((this.mOrientation == 1) ? (f4 < this.mGestureStrokeAngleThreshold) : (f4 > this.mGestureStrokeAngleThreshold))) {
            this.mIsGesturing = true;
            setCurrentColor(this.mCertainGestureColor);
            ArrayList<OnGesturingListener> arrayList1 = this.mOnGesturingListeners;
            i = arrayList1.size();
            for (j = 0; j < i; j++)
              ((OnGesturingListener)arrayList1.get(j)).onGesturingStarted(this); 
          } 
        } 
      } 
      ArrayList<OnGestureListener> arrayList = this.mOnGestureListeners;
      i = arrayList.size();
      for (j = 0; j < i; j++)
        ((OnGestureListener)arrayList.get(j)).onGesture(this, paramMotionEvent); 
    } 
    return rect;
  }
  
  private void touchUp(MotionEvent paramMotionEvent, boolean paramBoolean) {
    this.mIsListeningForGestures = false;
    Gesture gesture = this.mCurrentGesture;
    if (gesture != null) {
      gesture.addStroke(new GestureStroke(this.mStrokeBuffer));
      if (!paramBoolean) {
        ArrayList<OnGestureListener> arrayList1 = this.mOnGestureListeners;
        int j = arrayList1.size();
        for (byte b1 = 0; b1 < j; b1++)
          ((OnGestureListener)arrayList1.get(b1)).onGestureEnded(this, paramMotionEvent); 
        paramBoolean = this.mHandleGestureActions;
        boolean bool = true;
        if (paramBoolean && this.mFadeEnabled) {
          paramBoolean = true;
        } else {
          paramBoolean = false;
        } 
        if (!this.mHandleGestureActions || !this.mIsGesturing)
          bool = false; 
        clear(paramBoolean, bool, false);
      } else {
        cancelGesture(paramMotionEvent);
      } 
    } else {
      cancelGesture(paramMotionEvent);
    } 
    this.mStrokeBuffer.clear();
    this.mPreviousWasGesturing = this.mIsGesturing;
    this.mIsGesturing = false;
    ArrayList<OnGesturingListener> arrayList = this.mOnGesturingListeners;
    int i = arrayList.size();
    for (byte b = 0; b < i; b++)
      ((OnGesturingListener)arrayList.get(b)).onGesturingEnded(this); 
  }
  
  public void addOnGestureListener(OnGestureListener paramOnGestureListener) {
    this.mOnGestureListeners.add(paramOnGestureListener);
  }
  
  public void addOnGesturePerformedListener(OnGesturePerformedListener paramOnGesturePerformedListener) {
    this.mOnGesturePerformedListeners.add(paramOnGesturePerformedListener);
    if (this.mOnGesturePerformedListeners.size() > 0)
      this.mHandleGestureActions = true; 
  }
  
  public void addOnGesturingListener(OnGesturingListener paramOnGesturingListener) {
    this.mOnGesturingListeners.add(paramOnGesturingListener);
  }
  
  public void cancelClearAnimation() {
    setPaintAlpha(255);
    this.mIsFadingOut = false;
    this.mFadingHasStarted = false;
    removeCallbacks(this.mFadingOut);
    this.mPath.rewind();
    this.mCurrentGesture = null;
  }
  
  public void cancelGesture() {
    this.mIsListeningForGestures = false;
    this.mCurrentGesture.addStroke(new GestureStroke(this.mStrokeBuffer));
    long l = SystemClock.uptimeMillis();
    MotionEvent motionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
    ArrayList<OnGestureListener> arrayList1 = this.mOnGestureListeners;
    int i = arrayList1.size();
    byte b;
    for (b = 0; b < i; b++)
      ((OnGestureListener)arrayList1.get(b)).onGestureCancelled(this, motionEvent); 
    motionEvent.recycle();
    clear(false);
    this.mIsGesturing = false;
    this.mPreviousWasGesturing = false;
    this.mStrokeBuffer.clear();
    ArrayList<OnGesturingListener> arrayList = this.mOnGesturingListeners;
    i = arrayList.size();
    for (b = 0; b < i; b++)
      ((OnGesturingListener)arrayList.get(b)).onGesturingEnded(this); 
  }
  
  public void clear(boolean paramBoolean) {
    clear(paramBoolean, false, true);
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual isEnabled : ()Z
    //   4: ifeq -> 74
    //   7: aload_0
    //   8: getfield mIsGesturing : Z
    //   11: ifne -> 37
    //   14: aload_0
    //   15: getfield mCurrentGesture : Landroid/gesture/Gesture;
    //   18: astore_2
    //   19: aload_2
    //   20: ifnull -> 49
    //   23: aload_2
    //   24: invokevirtual getStrokesCount : ()I
    //   27: ifle -> 49
    //   30: aload_0
    //   31: getfield mPreviousWasGesturing : Z
    //   34: ifeq -> 49
    //   37: aload_0
    //   38: getfield mInterceptEvents : Z
    //   41: ifeq -> 49
    //   44: iconst_1
    //   45: istore_3
    //   46: goto -> 51
    //   49: iconst_0
    //   50: istore_3
    //   51: aload_0
    //   52: aload_1
    //   53: invokespecial processEvent : (Landroid/view/MotionEvent;)Z
    //   56: pop
    //   57: iload_3
    //   58: ifeq -> 66
    //   61: aload_1
    //   62: iconst_3
    //   63: invokevirtual setAction : (I)V
    //   66: aload_0
    //   67: aload_1
    //   68: invokespecial dispatchTouchEvent : (Landroid/view/MotionEvent;)Z
    //   71: pop
    //   72: iconst_1
    //   73: ireturn
    //   74: aload_0
    //   75: aload_1
    //   76: invokespecial dispatchTouchEvent : (Landroid/view/MotionEvent;)Z
    //   79: ireturn
  }
  
  public void draw(Canvas paramCanvas) {
    super.draw(paramCanvas);
    if (this.mCurrentGesture != null && this.mGestureVisible)
      paramCanvas.drawPath(this.mPath, this.mGesturePaint); 
  }
  
  public ArrayList<GesturePoint> getCurrentStroke() {
    return this.mStrokeBuffer;
  }
  
  public long getFadeOffset() {
    return this.mFadeOffset;
  }
  
  public Gesture getGesture() {
    return this.mCurrentGesture;
  }
  
  public int getGestureColor() {
    return this.mCertainGestureColor;
  }
  
  public Paint getGesturePaint() {
    return this.mGesturePaint;
  }
  
  public Path getGesturePath() {
    return this.mPath;
  }
  
  public Path getGesturePath(Path paramPath) {
    paramPath.set(this.mPath);
    return paramPath;
  }
  
  public float getGestureStrokeAngleThreshold() {
    return this.mGestureStrokeAngleThreshold;
  }
  
  public float getGestureStrokeLengthThreshold() {
    return this.mGestureStrokeLengthThreshold;
  }
  
  public float getGestureStrokeSquarenessTreshold() {
    return this.mGestureStrokeSquarenessTreshold;
  }
  
  public int getGestureStrokeType() {
    return this.mGestureStrokeType;
  }
  
  public float getGestureStrokeWidth() {
    return this.mGestureStrokeWidth;
  }
  
  public int getOrientation() {
    return this.mOrientation;
  }
  
  public int getUncertainGestureColor() {
    return this.mUncertainGestureColor;
  }
  
  public boolean isEventsInterceptionEnabled() {
    return this.mInterceptEvents;
  }
  
  public boolean isFadeEnabled() {
    return this.mFadeEnabled;
  }
  
  public boolean isGestureVisible() {
    return this.mGestureVisible;
  }
  
  public boolean isGesturing() {
    return this.mIsGesturing;
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    cancelClearAnimation();
  }
  
  public void removeAllOnGestureListeners() {
    this.mOnGestureListeners.clear();
  }
  
  public void removeAllOnGesturePerformedListeners() {
    this.mOnGesturePerformedListeners.clear();
    this.mHandleGestureActions = false;
  }
  
  public void removeAllOnGesturingListeners() {
    this.mOnGesturingListeners.clear();
  }
  
  public void removeOnGestureListener(OnGestureListener paramOnGestureListener) {
    this.mOnGestureListeners.remove(paramOnGestureListener);
  }
  
  public void removeOnGesturePerformedListener(OnGesturePerformedListener paramOnGesturePerformedListener) {
    this.mOnGesturePerformedListeners.remove(paramOnGesturePerformedListener);
    if (this.mOnGesturePerformedListeners.size() <= 0)
      this.mHandleGestureActions = false; 
  }
  
  public void removeOnGesturingListener(OnGesturingListener paramOnGesturingListener) {
    this.mOnGesturingListeners.remove(paramOnGesturingListener);
  }
  
  public void setEventsInterceptionEnabled(boolean paramBoolean) {
    this.mInterceptEvents = paramBoolean;
  }
  
  public void setFadeEnabled(boolean paramBoolean) {
    this.mFadeEnabled = paramBoolean;
  }
  
  public void setFadeOffset(long paramLong) {
    this.mFadeOffset = paramLong;
  }
  
  public void setGesture(Gesture paramGesture) {
    if (this.mCurrentGesture != null)
      clear(false); 
    setCurrentColor(this.mCertainGestureColor);
    this.mCurrentGesture = paramGesture;
    Path path = paramGesture.toPath();
    RectF rectF = new RectF();
    path.computeBounds(rectF, true);
    this.mPath.rewind();
    this.mPath.addPath(path, -rectF.left + (getWidth() - rectF.width()) / 2.0F, -rectF.top + (getHeight() - rectF.height()) / 2.0F);
    this.mResetGesture = true;
    invalidate();
  }
  
  public void setGestureColor(int paramInt) {
    this.mCertainGestureColor = paramInt;
  }
  
  public void setGestureStrokeAngleThreshold(float paramFloat) {
    this.mGestureStrokeAngleThreshold = paramFloat;
  }
  
  public void setGestureStrokeLengthThreshold(float paramFloat) {
    this.mGestureStrokeLengthThreshold = paramFloat;
  }
  
  public void setGestureStrokeSquarenessTreshold(float paramFloat) {
    this.mGestureStrokeSquarenessTreshold = paramFloat;
  }
  
  public void setGestureStrokeType(int paramInt) {
    this.mGestureStrokeType = paramInt;
  }
  
  public void setGestureStrokeWidth(float paramFloat) {
    this.mGestureStrokeWidth = paramFloat;
    this.mInvalidateExtraBorder = Math.max(1, (int)paramFloat - 1);
    this.mGesturePaint.setStrokeWidth(paramFloat);
  }
  
  public void setGestureVisible(boolean paramBoolean) {
    this.mGestureVisible = paramBoolean;
  }
  
  public void setOrientation(int paramInt) {
    this.mOrientation = paramInt;
  }
  
  public void setUncertainGestureColor(int paramInt) {
    this.mUncertainGestureColor = paramInt;
  }
  
  private class FadeOutRunnable implements Runnable {
    boolean fireActionPerformed;
    
    boolean resetMultipleStrokes;
    
    private FadeOutRunnable() {}
    
    public void run() {
      if (GestureOverlayView.this.mIsFadingOut) {
        long l = AnimationUtils.currentAnimationTimeMillis() - GestureOverlayView.this.mFadingStart;
        if (l > GestureOverlayView.this.mFadeDuration) {
          if (this.fireActionPerformed)
            GestureOverlayView.this.fireOnGesturePerformed(); 
          GestureOverlayView.access$502(GestureOverlayView.this, false);
          GestureOverlayView.access$102(GestureOverlayView.this, false);
          GestureOverlayView.access$602(GestureOverlayView.this, false);
          GestureOverlayView.this.mPath.rewind();
          GestureOverlayView.access$802(GestureOverlayView.this, (Gesture)null);
          GestureOverlayView.this.setPaintAlpha(255);
        } else {
          GestureOverlayView.access$602(GestureOverlayView.this, true);
          float f = Math.max(0.0F, Math.min(1.0F, (float)l / (float)GestureOverlayView.this.mFadeDuration));
          GestureOverlayView gestureOverlayView = GestureOverlayView.this;
          GestureOverlayView.access$1002(gestureOverlayView, 1.0F - gestureOverlayView.mInterpolator.getInterpolation(f));
          gestureOverlayView = GestureOverlayView.this;
          gestureOverlayView.setPaintAlpha((int)(gestureOverlayView.mFadingAlpha * 255.0F));
          GestureOverlayView.this.postDelayed(this, 16L);
        } 
      } else if (this.resetMultipleStrokes) {
        GestureOverlayView.access$1202(GestureOverlayView.this, true);
      } else {
        GestureOverlayView.this.fireOnGesturePerformed();
        GestureOverlayView.access$602(GestureOverlayView.this, false);
        GestureOverlayView.this.mPath.rewind();
        GestureOverlayView.access$802(GestureOverlayView.this, (Gesture)null);
        GestureOverlayView.access$502(GestureOverlayView.this, false);
        GestureOverlayView.this.setPaintAlpha(255);
      } 
      GestureOverlayView.this.invalidate();
    }
  }
  
  public static interface OnGestureListener {
    void onGesture(GestureOverlayView param1GestureOverlayView, MotionEvent param1MotionEvent);
    
    void onGestureCancelled(GestureOverlayView param1GestureOverlayView, MotionEvent param1MotionEvent);
    
    void onGestureEnded(GestureOverlayView param1GestureOverlayView, MotionEvent param1MotionEvent);
    
    void onGestureStarted(GestureOverlayView param1GestureOverlayView, MotionEvent param1MotionEvent);
  }
  
  public static interface OnGesturePerformedListener {
    void onGesturePerformed(GestureOverlayView param1GestureOverlayView, Gesture param1Gesture);
  }
  
  public static interface OnGesturingListener {
    void onGesturingEnded(GestureOverlayView param1GestureOverlayView);
    
    void onGesturingStarted(GestureOverlayView param1GestureOverlayView);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/GestureOverlayView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.graphics.drawable;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ImageDecoder;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.android.internal.R;
import dalvik.annotation.optimization.FastNative;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import libcore.util.NativeAllocationRegistry;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedImageDrawable extends Drawable implements Animatable2 {
  private static final int FINISHED = -1;
  
  @Deprecated
  public static final int LOOP_INFINITE = -1;
  
  public static final int REPEAT_INFINITE = -1;
  
  private static final int REPEAT_UNDEFINED = -2;
  
  private ArrayList<Animatable2.AnimationCallback> mAnimationCallbacks = null;
  
  private ColorFilter mColorFilter;
  
  private Handler mHandler;
  
  private int mIntrinsicHeight;
  
  private int mIntrinsicWidth;
  
  private Runnable mRunnable;
  
  private boolean mStarting;
  
  private State mState;
  
  public AnimatedImageDrawable() {
    this.mState = new State(0L, null, null);
  }
  
  public AnimatedImageDrawable(long paramLong1, ImageDecoder paramImageDecoder, int paramInt1, int paramInt2, long paramLong2, boolean paramBoolean, int paramInt3, int paramInt4, Rect paramRect, InputStream paramInputStream, AssetFileDescriptor paramAssetFileDescriptor) throws IOException {
    paramInt1 = Bitmap.scaleFromDensity(paramInt1, paramInt3, paramInt4);
    paramInt2 = Bitmap.scaleFromDensity(paramInt2, paramInt3, paramInt4);
    if (paramRect == null) {
      this.mIntrinsicWidth = paramInt1;
      this.mIntrinsicHeight = paramInt2;
    } else {
      paramRect.set(Bitmap.scaleFromDensity(paramRect.left, paramInt3, paramInt4), Bitmap.scaleFromDensity(paramRect.top, paramInt3, paramInt4), Bitmap.scaleFromDensity(paramRect.right, paramInt3, paramInt4), Bitmap.scaleFromDensity(paramRect.bottom, paramInt3, paramInt4));
      this.mIntrinsicWidth = paramRect.width();
      this.mIntrinsicHeight = paramRect.height();
    } 
    State state1 = new State(nCreate(paramLong1, paramImageDecoder, paramInt1, paramInt2, paramLong2, paramBoolean, paramRect), paramInputStream, paramAssetFileDescriptor);
    this.mState = state1;
    paramLong1 = nNativeByteSize(state1.mNativePtr);
    NativeAllocationRegistry nativeAllocationRegistry = NativeAllocationRegistry.createMalloced(AnimatedImageDrawable.class.getClassLoader(), nGetNativeFinalizer(), paramLong1);
    State state2 = this.mState;
    nativeAllocationRegistry.registerNativeAllocation(state2, state2.mNativePtr);
  }
  
  private Handler getHandler() {
    if (this.mHandler == null)
      this.mHandler = new Handler(Looper.getMainLooper()); 
    return this.mHandler;
  }
  
  private static native long nCreate(long paramLong1, ImageDecoder paramImageDecoder, int paramInt1, int paramInt2, long paramLong2, boolean paramBoolean, Rect paramRect) throws IOException;
  
  private static native long nDraw(long paramLong1, long paramLong2);
  
  @FastNative
  private static native int nGetAlpha(long paramLong);
  
  @FastNative
  private static native long nGetNativeFinalizer();
  
  @FastNative
  private static native int nGetRepeatCount(long paramLong);
  
  @FastNative
  private static native boolean nIsRunning(long paramLong);
  
  @FastNative
  private static native long nNativeByteSize(long paramLong);
  
  @FastNative
  private static native void nSetAlpha(long paramLong, int paramInt);
  
  @FastNative
  private static native void nSetColorFilter(long paramLong1, long paramLong2);
  
  @FastNative
  private static native void nSetMirrored(long paramLong, boolean paramBoolean);
  
  private static native void nSetOnAnimationEndListener(long paramLong, AnimatedImageDrawable paramAnimatedImageDrawable);
  
  @FastNative
  private static native void nSetRepeatCount(long paramLong, int paramInt);
  
  @FastNative
  private static native boolean nStart(long paramLong);
  
  @FastNative
  private static native boolean nStop(long paramLong);
  
  private void onAnimationEnd() {
    ArrayList<Animatable2.AnimationCallback> arrayList = this.mAnimationCallbacks;
    if (arrayList != null) {
      Iterator<Animatable2.AnimationCallback> iterator = arrayList.iterator();
      while (iterator.hasNext())
        ((Animatable2.AnimationCallback)iterator.next()).onAnimationEnd(this); 
    } 
  }
  
  private void postOnAnimationEnd() {
    if (this.mAnimationCallbacks == null)
      return; 
    getHandler().post(new _$$Lambda$AnimatedImageDrawable$dGAkP_tKNvqn_qCWdrQRL806ExQ(this));
  }
  
  private void postOnAnimationStart() {
    if (this.mAnimationCallbacks == null)
      return; 
    getHandler().post(new _$$Lambda$AnimatedImageDrawable$6aWLU8OYhdfACSejz5_iGirYxUk(this));
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray, int paramInt) throws XmlPullParserException {
    State state = this.mState;
    Resources resources = paramTypedArray.getResources();
    int i = paramTypedArray.getResourceId(0, 0);
    if (i != 0) {
      TypedValue typedValue = new TypedValue();
      resources.getValueForDensity(i, paramInt, typedValue, true);
      if (paramInt > 0 && typedValue.density > 0 && typedValue.density != 65535)
        if (typedValue.density == paramInt) {
          typedValue.density = (resources.getDisplayMetrics()).densityDpi;
        } else {
          typedValue.density = typedValue.density * (resources.getDisplayMetrics()).densityDpi / paramInt;
        }  
      paramInt = 0;
      if (typedValue.density == 0) {
        paramInt = 160;
      } else if (typedValue.density != 65535) {
        paramInt = typedValue.density;
      } 
      try {
        Drawable drawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource(resources, resources.openRawResource(i, typedValue), paramInt), (ImageDecoder.OnHeaderDecodedListener)_$$Lambda$AnimatedImageDrawable$Cgt3NliB7ZYUONyDd_eQGdYbEKc.INSTANCE);
        if (drawable instanceof AnimatedImageDrawable) {
          paramInt = this.mState.mRepeatCount;
          drawable = drawable;
          this.mState = ((AnimatedImageDrawable)drawable).mState;
          ((AnimatedImageDrawable)drawable).mState = null;
          this.mIntrinsicWidth = ((AnimatedImageDrawable)drawable).mIntrinsicWidth;
          this.mIntrinsicHeight = ((AnimatedImageDrawable)drawable).mIntrinsicHeight;
          if (paramInt != -2)
            setRepeatCount(paramInt); 
        } else {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(paramTypedArray.getPositionDescription());
          stringBuilder1.append(": <animated-image> did not decode animated");
          throw new XmlPullParserException(stringBuilder1.toString());
        } 
      } catch (IOException iOException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramTypedArray.getPositionDescription());
        stringBuilder1.append(": <animated-image> requires a valid 'src' attribute");
        throw new XmlPullParserException(stringBuilder1.toString(), null, iOException);
      } 
    } 
    this.mState.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    if (this.mState.mNativePtr != 0L || (this.mState.mThemeAttrs != null && this.mState.mThemeAttrs[0] != 0)) {
      this.mState.mAutoMirrored = paramTypedArray.getBoolean(3, ((State)iOException).mAutoMirrored);
      paramInt = paramTypedArray.getInt(1, -2);
      if (paramInt != -2)
        setRepeatCount(paramInt); 
      if (paramTypedArray.getBoolean(2, false) && this.mState.mNativePtr != 0L)
        start(); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramTypedArray.getPositionDescription());
    stringBuilder.append(": <animated-image> requires a valid 'src' attribute");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void clearAnimationCallbacks() {
    if (this.mAnimationCallbacks != null) {
      this.mAnimationCallbacks = null;
      nSetOnAnimationEndListener(this.mState.mNativePtr, (AnimatedImageDrawable)null);
    } 
  }
  
  public void draw(Canvas paramCanvas) {
    if (this.mState.mNativePtr != 0L) {
      if (this.mStarting) {
        this.mStarting = false;
        postOnAnimationStart();
      } 
      long l = nDraw(this.mState.mNativePtr, paramCanvas.getNativeCanvasWrapper());
      if (l > 0L) {
        if (this.mRunnable == null)
          this.mRunnable = new _$$Lambda$AlQeVq8Y_kfuQeb_JLZ0ueV4DE8(this); 
        scheduleSelf(this.mRunnable, SystemClock.uptimeMillis() + l);
      } else if (l == -1L) {
        postOnAnimationEnd();
      } 
      return;
    } 
    throw new IllegalStateException("called draw on empty AnimatedImageDrawable");
  }
  
  public int getAlpha() {
    if (this.mState.mNativePtr != 0L)
      return nGetAlpha(this.mState.mNativePtr); 
    throw new IllegalStateException("called getAlpha on empty AnimatedImageDrawable");
  }
  
  public ColorFilter getColorFilter() {
    return this.mColorFilter;
  }
  
  public int getIntrinsicHeight() {
    return this.mIntrinsicHeight;
  }
  
  public int getIntrinsicWidth() {
    return this.mIntrinsicWidth;
  }
  
  @Deprecated
  public int getLoopCount(int paramInt) {
    return getRepeatCount();
  }
  
  public int getOpacity() {
    return -3;
  }
  
  public int getRepeatCount() {
    if (this.mState.mNativePtr != 0L) {
      if (this.mState.mRepeatCount == -2) {
        State state = this.mState;
        state.mRepeatCount = nGetRepeatCount(state.mNativePtr);
      } 
      return this.mState.mRepeatCount;
    } 
    throw new IllegalStateException("called getRepeatCount on empty AnimatedImageDrawable");
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateStateFromTypedArray(obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedImageDrawable), this.mSrcDensityOverride);
  }
  
  public final boolean isAutoMirrored() {
    return this.mState.mAutoMirrored;
  }
  
  public boolean isRunning() {
    if (this.mState.mNativePtr != 0L)
      return nIsRunning(this.mState.mNativePtr); 
    throw new IllegalStateException("called isRunning on empty AnimatedImageDrawable");
  }
  
  public boolean onLayoutDirectionChanged(int paramInt) {
    boolean bool = this.mState.mAutoMirrored;
    boolean bool1 = false;
    if (!bool || this.mState.mNativePtr == 0L)
      return false; 
    if (paramInt == 1)
      bool1 = true; 
    nSetMirrored(this.mState.mNativePtr, bool1);
    return true;
  }
  
  public void registerAnimationCallback(Animatable2.AnimationCallback paramAnimationCallback) {
    if (paramAnimationCallback == null)
      return; 
    if (this.mAnimationCallbacks == null) {
      this.mAnimationCallbacks = new ArrayList<>();
      nSetOnAnimationEndListener(this.mState.mNativePtr, this);
    } 
    if (!this.mAnimationCallbacks.contains(paramAnimationCallback))
      this.mAnimationCallbacks.add(paramAnimationCallback); 
  }
  
  public void setAlpha(int paramInt) {
    if (paramInt >= 0 && paramInt <= 255) {
      if (this.mState.mNativePtr != 0L) {
        nSetAlpha(this.mState.mNativePtr, paramInt);
        invalidateSelf();
        return;
      } 
      throw new IllegalStateException("called setAlpha on empty AnimatedImageDrawable");
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Alpha must be between 0 and 255! provided ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    if (this.mState.mAutoMirrored != paramBoolean) {
      this.mState.mAutoMirrored = paramBoolean;
      if (getLayoutDirection() == 1 && this.mState.mNativePtr != 0L) {
        nSetMirrored(this.mState.mNativePtr, paramBoolean);
        invalidateSelf();
      } 
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    long l1 = this.mState.mNativePtr;
    long l2 = 0L;
    if (l1 != 0L) {
      if (paramColorFilter != this.mColorFilter) {
        this.mColorFilter = paramColorFilter;
        if (paramColorFilter != null)
          l2 = paramColorFilter.getNativeInstance(); 
        nSetColorFilter(this.mState.mNativePtr, l2);
        invalidateSelf();
      } 
      return;
    } 
    throw new IllegalStateException("called setColorFilter on empty AnimatedImageDrawable");
  }
  
  @Deprecated
  public void setLoopCount(int paramInt) {
    setRepeatCount(paramInt);
  }
  
  public void setRepeatCount(int paramInt) {
    if (paramInt >= -1) {
      if (this.mState.mRepeatCount != paramInt) {
        this.mState.mRepeatCount = paramInt;
        if (this.mState.mNativePtr != 0L)
          nSetRepeatCount(this.mState.mNativePtr, paramInt); 
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("invalid value passed to setRepeatCount");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void start() {
    if (this.mState.mNativePtr != 0L) {
      if (nStart(this.mState.mNativePtr)) {
        this.mStarting = true;
        invalidateSelf();
      } 
      return;
    } 
    throw new IllegalStateException("called start on empty AnimatedImageDrawable");
  }
  
  public void stop() {
    if (this.mState.mNativePtr != 0L) {
      if (nStop(this.mState.mNativePtr))
        postOnAnimationEnd(); 
      return;
    } 
    throw new IllegalStateException("called stop on empty AnimatedImageDrawable");
  }
  
  public boolean unregisterAnimationCallback(Animatable2.AnimationCallback paramAnimationCallback) {
    if (paramAnimationCallback != null) {
      ArrayList<Animatable2.AnimationCallback> arrayList = this.mAnimationCallbacks;
      if (arrayList != null && arrayList.remove(paramAnimationCallback)) {
        if (this.mAnimationCallbacks.isEmpty())
          clearAnimationCallbacks(); 
        return true;
      } 
    } 
    return false;
  }
  
  private class State {
    private final AssetFileDescriptor mAssetFd;
    
    boolean mAutoMirrored = false;
    
    private final InputStream mInputStream;
    
    final long mNativePtr;
    
    int mRepeatCount = -2;
    
    int[] mThemeAttrs = null;
    
    State(long param1Long, InputStream param1InputStream, AssetFileDescriptor param1AssetFileDescriptor) {
      this.mNativePtr = param1Long;
      this.mInputStream = param1InputStream;
      this.mAssetFd = param1AssetFileDescriptor;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedImageDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
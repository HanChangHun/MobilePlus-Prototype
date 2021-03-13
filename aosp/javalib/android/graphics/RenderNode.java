package android.graphics;

import android.graphics.animation.RenderNodeAnimator;
import android.view.NativeVectorDrawableAnimator;
import com.android.internal.util.ArrayUtils;
import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import libcore.util.NativeAllocationRegistry;

public final class RenderNode {
  public static final int USAGE_BACKGROUND = 1;
  
  public static final int USAGE_UNKNOWN = 0;
  
  private final AnimationHost mAnimationHost;
  
  private CompositePositionUpdateListener mCompositePositionUpdateListener;
  
  private RecordingCanvas mCurrentRecordingCanvas;
  
  public final long mNativeRenderNode;
  
  private RenderNode(long paramLong) {
    this.mNativeRenderNode = paramLong;
    NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeRenderNode);
    this.mAnimationHost = null;
  }
  
  public RenderNode(String paramString) {
    this(paramString, null);
  }
  
  private RenderNode(String paramString, AnimationHost paramAnimationHost) {
    this.mNativeRenderNode = nCreate(paramString);
    NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeRenderNode);
    this.mAnimationHost = paramAnimationHost;
  }
  
  public static RenderNode adopt(long paramLong) {
    return new RenderNode(paramLong);
  }
  
  public static RenderNode create(String paramString, AnimationHost paramAnimationHost) {
    return new RenderNode(paramString, paramAnimationHost);
  }
  
  private static native void nAddAnimator(long paramLong1, long paramLong2);
  
  private static native long nCreate(String paramString);
  
  private static native void nEndAllAnimators(long paramLong);
  
  private static native int nGetAllocatedSize(long paramLong);
  
  @CriticalNative
  private static native boolean nGetAllowForceDark(long paramLong);
  
  @CriticalNative
  private static native float nGetAlpha(long paramLong);
  
  @CriticalNative
  private static native int nGetAmbientShadowColor(long paramLong);
  
  @CriticalNative
  private static native boolean nGetAnimationMatrix(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native int nGetBottom(long paramLong);
  
  @CriticalNative
  private static native float nGetCameraDistance(long paramLong);
  
  @CriticalNative
  private static native boolean nGetClipToBounds(long paramLong);
  
  @CriticalNative
  private static native boolean nGetClipToOutline(long paramLong);
  
  @CriticalNative
  private static native float nGetElevation(long paramLong);
  
  @CriticalNative
  private static native int nGetHeight(long paramLong);
  
  @CriticalNative
  private static native void nGetInverseTransformMatrix(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native int nGetLayerType(long paramLong);
  
  @CriticalNative
  private static native int nGetLeft(long paramLong);
  
  private static native long nGetNativeFinalizer();
  
  @CriticalNative
  private static native float nGetPivotX(long paramLong);
  
  @CriticalNative
  private static native float nGetPivotY(long paramLong);
  
  @CriticalNative
  private static native int nGetRight(long paramLong);
  
  @CriticalNative
  private static native float nGetRotation(long paramLong);
  
  @CriticalNative
  private static native float nGetRotationX(long paramLong);
  
  @CriticalNative
  private static native float nGetRotationY(long paramLong);
  
  @CriticalNative
  private static native float nGetScaleX(long paramLong);
  
  @CriticalNative
  private static native float nGetScaleY(long paramLong);
  
  @CriticalNative
  private static native int nGetSpotShadowColor(long paramLong);
  
  @CriticalNative
  private static native int nGetTop(long paramLong);
  
  @CriticalNative
  private static native void nGetTransformMatrix(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native float nGetTranslationX(long paramLong);
  
  @CriticalNative
  private static native float nGetTranslationY(long paramLong);
  
  @CriticalNative
  private static native float nGetTranslationZ(long paramLong);
  
  @CriticalNative
  private static native long nGetUniqueId(long paramLong);
  
  private static native int nGetUsageSize(long paramLong);
  
  @CriticalNative
  private static native int nGetWidth(long paramLong);
  
  @CriticalNative
  private static native boolean nHasIdentityMatrix(long paramLong);
  
  @CriticalNative
  private static native boolean nHasOverlappingRendering(long paramLong);
  
  @CriticalNative
  private static native boolean nHasShadow(long paramLong);
  
  @CriticalNative
  private static native boolean nIsPivotExplicitlySet(long paramLong);
  
  @CriticalNative
  private static native boolean nIsValid(long paramLong);
  
  @CriticalNative
  private static native boolean nOffsetLeftAndRight(long paramLong, int paramInt);
  
  @CriticalNative
  private static native boolean nOffsetTopAndBottom(long paramLong, int paramInt);
  
  private static native void nOutput(long paramLong);
  
  private static native void nRequestPositionUpdates(long paramLong, PositionUpdateListener paramPositionUpdateListener);
  
  @CriticalNative
  private static native boolean nResetPivot(long paramLong);
  
  @CriticalNative
  private static native boolean nSetAllowForceDark(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native boolean nSetAlpha(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetAmbientShadowColor(long paramLong, int paramInt);
  
  @CriticalNative
  private static native boolean nSetAnimationMatrix(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native boolean nSetBottom(long paramLong, int paramInt);
  
  @CriticalNative
  private static native boolean nSetCameraDistance(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetClipBounds(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  @CriticalNative
  private static native boolean nSetClipBoundsEmpty(long paramLong);
  
  @CriticalNative
  private static native boolean nSetClipToBounds(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native boolean nSetClipToOutline(long paramLong, boolean paramBoolean);
  
  @FastNative
  private static native void nSetDisplayList(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native boolean nSetElevation(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetHasOverlappingRendering(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native boolean nSetLayerPaint(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native boolean nSetLayerType(long paramLong, int paramInt);
  
  @CriticalNative
  private static native boolean nSetLeft(long paramLong, int paramInt);
  
  @CriticalNative
  private static native boolean nSetLeftTopRightBottom(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  @CriticalNative
  private static native boolean nSetOutlineEmpty(long paramLong);
  
  @CriticalNative
  private static native boolean nSetOutlineNone(long paramLong);
  
  @CriticalNative
  private static native boolean nSetOutlinePath(long paramLong1, long paramLong2, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetOutlineRoundRect(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native boolean nSetPivotX(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetPivotY(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetProjectBackwards(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native boolean nSetProjectionReceiver(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native boolean nSetRevealClip(long paramLong, boolean paramBoolean, float paramFloat1, float paramFloat2, float paramFloat3);
  
  @CriticalNative
  private static native boolean nSetRight(long paramLong, int paramInt);
  
  @CriticalNative
  private static native boolean nSetRotation(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetRotationX(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetRotationY(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetScaleX(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetScaleY(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetSpotShadowColor(long paramLong, int paramInt);
  
  @CriticalNative
  private static native boolean nSetStaticMatrix(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native boolean nSetTop(long paramLong, int paramInt);
  
  @CriticalNative
  private static native boolean nSetTranslationX(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetTranslationY(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native boolean nSetTranslationZ(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native void nSetUsageHint(long paramLong, int paramInt);
  
  public void addAnimator(RenderNodeAnimator paramRenderNodeAnimator) {
    if (isAttached()) {
      nAddAnimator(this.mNativeRenderNode, paramRenderNodeAnimator.getNativeAnimator());
      this.mAnimationHost.registerAnimatingRenderNode(this);
      return;
    } 
    throw new IllegalStateException("Cannot start this animator on a detached view!");
  }
  
  public void addPositionUpdateListener(PositionUpdateListener paramPositionUpdateListener) {
    CompositePositionUpdateListener compositePositionUpdateListener = this.mCompositePositionUpdateListener;
    if (compositePositionUpdateListener == null) {
      paramPositionUpdateListener = new CompositePositionUpdateListener(new PositionUpdateListener[] { paramPositionUpdateListener });
    } else {
      paramPositionUpdateListener = compositePositionUpdateListener.with(paramPositionUpdateListener);
    } 
    this.mCompositePositionUpdateListener = (CompositePositionUpdateListener)paramPositionUpdateListener;
    nRequestPositionUpdates(this.mNativeRenderNode, paramPositionUpdateListener);
  }
  
  public RecordingCanvas beginRecording() {
    return beginRecording(nGetWidth(this.mNativeRenderNode), nGetHeight(this.mNativeRenderNode));
  }
  
  public RecordingCanvas beginRecording(int paramInt1, int paramInt2) {
    if (this.mCurrentRecordingCanvas == null) {
      RecordingCanvas recordingCanvas = RecordingCanvas.obtain(this, paramInt1, paramInt2);
      this.mCurrentRecordingCanvas = recordingCanvas;
      return recordingCanvas;
    } 
    throw new IllegalStateException("Recording currently in progress - missing #endRecording() call?");
  }
  
  public long computeApproximateMemoryAllocated() {
    return nGetAllocatedSize(this.mNativeRenderNode);
  }
  
  public long computeApproximateMemoryUsage() {
    return nGetUsageSize(this.mNativeRenderNode);
  }
  
  public void discardDisplayList() {
    nSetDisplayList(this.mNativeRenderNode, 0L);
  }
  
  @Deprecated
  public void end(RecordingCanvas paramRecordingCanvas) {
    if (paramRecordingCanvas == this.mCurrentRecordingCanvas) {
      endRecording();
      return;
    } 
    throw new IllegalArgumentException("Wrong canvas");
  }
  
  public void endAllAnimators() {
    nEndAllAnimators(this.mNativeRenderNode);
  }
  
  public void endRecording() {
    if (this.mCurrentRecordingCanvas != null) {
      RecordingCanvas recordingCanvas = this.mCurrentRecordingCanvas;
      this.mCurrentRecordingCanvas = null;
      long l = recordingCanvas.finishRecording();
      nSetDisplayList(this.mNativeRenderNode, l);
      recordingCanvas.recycle();
      return;
    } 
    throw new IllegalStateException("No recording in progress, forgot to call #beginRecording()?");
  }
  
  public float getAlpha() {
    return nGetAlpha(this.mNativeRenderNode);
  }
  
  public int getAmbientShadowColor() {
    return nGetAmbientShadowColor(this.mNativeRenderNode);
  }
  
  public Matrix getAnimationMatrix() {
    Matrix matrix = new Matrix();
    return nGetAnimationMatrix(this.mNativeRenderNode, matrix.native_instance) ? matrix : null;
  }
  
  public int getBottom() {
    return nGetBottom(this.mNativeRenderNode);
  }
  
  public float getCameraDistance() {
    return -nGetCameraDistance(this.mNativeRenderNode);
  }
  
  public boolean getClipToBounds() {
    return nGetClipToBounds(this.mNativeRenderNode);
  }
  
  public boolean getClipToOutline() {
    return nGetClipToOutline(this.mNativeRenderNode);
  }
  
  public float getElevation() {
    return nGetElevation(this.mNativeRenderNode);
  }
  
  public int getHeight() {
    return nGetHeight(this.mNativeRenderNode);
  }
  
  public void getInverseMatrix(Matrix paramMatrix) {
    nGetInverseTransformMatrix(this.mNativeRenderNode, paramMatrix.native_instance);
  }
  
  public int getLeft() {
    return nGetLeft(this.mNativeRenderNode);
  }
  
  public void getMatrix(Matrix paramMatrix) {
    nGetTransformMatrix(this.mNativeRenderNode, paramMatrix.native_instance);
  }
  
  public float getPivotX() {
    return nGetPivotX(this.mNativeRenderNode);
  }
  
  public float getPivotY() {
    return nGetPivotY(this.mNativeRenderNode);
  }
  
  public int getRight() {
    return nGetRight(this.mNativeRenderNode);
  }
  
  public float getRotationX() {
    return nGetRotationX(this.mNativeRenderNode);
  }
  
  public float getRotationY() {
    return nGetRotationY(this.mNativeRenderNode);
  }
  
  public float getRotationZ() {
    return nGetRotation(this.mNativeRenderNode);
  }
  
  public float getScaleX() {
    return nGetScaleX(this.mNativeRenderNode);
  }
  
  public float getScaleY() {
    return nGetScaleY(this.mNativeRenderNode);
  }
  
  public int getSpotShadowColor() {
    return nGetSpotShadowColor(this.mNativeRenderNode);
  }
  
  public int getTop() {
    return nGetTop(this.mNativeRenderNode);
  }
  
  public float getTranslationX() {
    return nGetTranslationX(this.mNativeRenderNode);
  }
  
  public float getTranslationY() {
    return nGetTranslationY(this.mNativeRenderNode);
  }
  
  public float getTranslationZ() {
    return nGetTranslationZ(this.mNativeRenderNode);
  }
  
  public long getUniqueId() {
    return nGetUniqueId(this.mNativeRenderNode);
  }
  
  public boolean getUseCompositingLayer() {
    boolean bool;
    if (nGetLayerType(this.mNativeRenderNode) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int getWidth() {
    return nGetWidth(this.mNativeRenderNode);
  }
  
  public boolean hasDisplayList() {
    return nIsValid(this.mNativeRenderNode);
  }
  
  public boolean hasIdentityMatrix() {
    return nHasIdentityMatrix(this.mNativeRenderNode);
  }
  
  public boolean hasOverlappingRendering() {
    return nHasOverlappingRendering(this.mNativeRenderNode);
  }
  
  public boolean hasShadow() {
    return nHasShadow(this.mNativeRenderNode);
  }
  
  public boolean isAttached() {
    boolean bool;
    AnimationHost animationHost = this.mAnimationHost;
    if (animationHost != null && animationHost.isAttached()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isForceDarkAllowed() {
    return nGetAllowForceDark(this.mNativeRenderNode);
  }
  
  public boolean isPivotExplicitlySet() {
    return nIsPivotExplicitlySet(this.mNativeRenderNode);
  }
  
  public boolean offsetLeftAndRight(int paramInt) {
    return nOffsetLeftAndRight(this.mNativeRenderNode, paramInt);
  }
  
  public boolean offsetTopAndBottom(int paramInt) {
    return nOffsetTopAndBottom(this.mNativeRenderNode, paramInt);
  }
  
  public void output() {
    nOutput(this.mNativeRenderNode);
  }
  
  public void registerVectorDrawableAnimator(NativeVectorDrawableAnimator paramNativeVectorDrawableAnimator) {
    if (isAttached()) {
      this.mAnimationHost.registerVectorDrawableAnimator(paramNativeVectorDrawableAnimator);
      return;
    } 
    throw new IllegalStateException("Cannot start this animator on a detached view!");
  }
  
  public void removePositionUpdateListener(PositionUpdateListener paramPositionUpdateListener) {
    CompositePositionUpdateListener compositePositionUpdateListener = this.mCompositePositionUpdateListener;
    if (compositePositionUpdateListener != null) {
      paramPositionUpdateListener = compositePositionUpdateListener.without(paramPositionUpdateListener);
      this.mCompositePositionUpdateListener = (CompositePositionUpdateListener)paramPositionUpdateListener;
      nRequestPositionUpdates(this.mNativeRenderNode, paramPositionUpdateListener);
    } 
  }
  
  public boolean resetPivot() {
    return nResetPivot(this.mNativeRenderNode);
  }
  
  public boolean setAlpha(float paramFloat) {
    return nSetAlpha(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setAmbientShadowColor(int paramInt) {
    return nSetAmbientShadowColor(this.mNativeRenderNode, paramInt);
  }
  
  public boolean setAnimationMatrix(Matrix paramMatrix) {
    long l2;
    long l1 = this.mNativeRenderNode;
    if (paramMatrix != null) {
      l2 = paramMatrix.native_instance;
    } else {
      l2 = 0L;
    } 
    return nSetAnimationMatrix(l1, l2);
  }
  
  public boolean setBottom(int paramInt) {
    return nSetBottom(this.mNativeRenderNode, paramInt);
  }
  
  public boolean setCameraDistance(float paramFloat) {
    if (Float.isFinite(paramFloat) && paramFloat >= 0.0F)
      return nSetCameraDistance(this.mNativeRenderNode, -paramFloat); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("distance must be finite & positive, given=");
    stringBuilder.append(paramFloat);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean setClipRect(Rect paramRect) {
    return (paramRect == null) ? nSetClipBoundsEmpty(this.mNativeRenderNode) : nSetClipBounds(this.mNativeRenderNode, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public boolean setClipToBounds(boolean paramBoolean) {
    return nSetClipToBounds(this.mNativeRenderNode, paramBoolean);
  }
  
  public boolean setClipToOutline(boolean paramBoolean) {
    return nSetClipToOutline(this.mNativeRenderNode, paramBoolean);
  }
  
  public boolean setElevation(float paramFloat) {
    return nSetElevation(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setForceDarkAllowed(boolean paramBoolean) {
    return nSetAllowForceDark(this.mNativeRenderNode, paramBoolean);
  }
  
  public boolean setHasOverlappingRendering(boolean paramBoolean) {
    return nSetHasOverlappingRendering(this.mNativeRenderNode, paramBoolean);
  }
  
  @Deprecated
  public boolean setLayerPaint(Paint paramPaint) {
    long l2;
    long l1 = this.mNativeRenderNode;
    if (paramPaint != null) {
      l2 = paramPaint.getNativeInstance();
    } else {
      l2 = 0L;
    } 
    return nSetLayerPaint(l1, l2);
  }
  
  @Deprecated
  public boolean setLayerType(int paramInt) {
    return nSetLayerType(this.mNativeRenderNode, paramInt);
  }
  
  public boolean setLeft(int paramInt) {
    return nSetLeft(this.mNativeRenderNode, paramInt);
  }
  
  public boolean setLeftTopRightBottom(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return nSetLeftTopRightBottom(this.mNativeRenderNode, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean setOutline(Outline paramOutline) {
    if (paramOutline == null)
      return nSetOutlineNone(this.mNativeRenderNode); 
    int i = paramOutline.mMode;
    if (i != 0) {
      if (i != 1) {
        if (i == 2)
          return nSetOutlinePath(this.mNativeRenderNode, paramOutline.mPath.mNativePath, paramOutline.mAlpha); 
        throw new IllegalArgumentException("Unrecognized outline?");
      } 
      return nSetOutlineRoundRect(this.mNativeRenderNode, paramOutline.mRect.left, paramOutline.mRect.top, paramOutline.mRect.right, paramOutline.mRect.bottom, paramOutline.mRadius, paramOutline.mAlpha);
    } 
    return nSetOutlineEmpty(this.mNativeRenderNode);
  }
  
  public boolean setPivotX(float paramFloat) {
    return nSetPivotX(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setPivotY(float paramFloat) {
    return nSetPivotY(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return nSetLeftTopRightBottom(this.mNativeRenderNode, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean setPosition(Rect paramRect) {
    return nSetLeftTopRightBottom(this.mNativeRenderNode, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public boolean setProjectBackwards(boolean paramBoolean) {
    return nSetProjectBackwards(this.mNativeRenderNode, paramBoolean);
  }
  
  public boolean setProjectionReceiver(boolean paramBoolean) {
    return nSetProjectionReceiver(this.mNativeRenderNode, paramBoolean);
  }
  
  public boolean setRevealClip(boolean paramBoolean, float paramFloat1, float paramFloat2, float paramFloat3) {
    return nSetRevealClip(this.mNativeRenderNode, paramBoolean, paramFloat1, paramFloat2, paramFloat3);
  }
  
  public boolean setRight(int paramInt) {
    return nSetRight(this.mNativeRenderNode, paramInt);
  }
  
  public boolean setRotationX(float paramFloat) {
    return nSetRotationX(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setRotationY(float paramFloat) {
    return nSetRotationY(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setRotationZ(float paramFloat) {
    return nSetRotation(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setScaleX(float paramFloat) {
    return nSetScaleX(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setScaleY(float paramFloat) {
    return nSetScaleY(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setSpotShadowColor(int paramInt) {
    return nSetSpotShadowColor(this.mNativeRenderNode, paramInt);
  }
  
  public boolean setStaticMatrix(Matrix paramMatrix) {
    return nSetStaticMatrix(this.mNativeRenderNode, paramMatrix.native_instance);
  }
  
  public boolean setTop(int paramInt) {
    return nSetTop(this.mNativeRenderNode, paramInt);
  }
  
  public boolean setTranslationX(float paramFloat) {
    return nSetTranslationX(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setTranslationY(float paramFloat) {
    return nSetTranslationY(this.mNativeRenderNode, paramFloat);
  }
  
  public boolean setTranslationZ(float paramFloat) {
    return nSetTranslationZ(this.mNativeRenderNode, paramFloat);
  }
  
  public void setUsageHint(int paramInt) {
    nSetUsageHint(this.mNativeRenderNode, paramInt);
  }
  
  public boolean setUseCompositingLayer(boolean paramBoolean, Paint paramPaint) {
    boolean bool;
    long l1 = this.mNativeRenderNode;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    paramBoolean = nSetLayerType(l1, bool);
    long l2 = this.mNativeRenderNode;
    if (paramPaint != null) {
      l1 = paramPaint.getNativeInstance();
    } else {
      l1 = 0L;
    } 
    return paramBoolean | nSetLayerPaint(l2, l1);
  }
  
  @Deprecated
  public RecordingCanvas start(int paramInt1, int paramInt2) {
    return beginRecording(paramInt1, paramInt2);
  }
  
  public static interface AnimationHost {
    boolean isAttached();
    
    void registerAnimatingRenderNode(RenderNode param1RenderNode);
    
    void registerVectorDrawableAnimator(NativeVectorDrawableAnimator param1NativeVectorDrawableAnimator);
  }
  
  private static final class CompositePositionUpdateListener implements PositionUpdateListener {
    private static final RenderNode.PositionUpdateListener[] sEmpty = new RenderNode.PositionUpdateListener[0];
    
    private final RenderNode.PositionUpdateListener[] mListeners;
    
    CompositePositionUpdateListener(RenderNode.PositionUpdateListener... param1VarArgs) {
      if (param1VarArgs == null)
        param1VarArgs = sEmpty; 
      this.mListeners = param1VarArgs;
    }
    
    public void positionChanged(long param1Long, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      RenderNode.PositionUpdateListener[] arrayOfPositionUpdateListener = this.mListeners;
      int i = arrayOfPositionUpdateListener.length;
      for (byte b = 0; b < i; b++)
        arrayOfPositionUpdateListener[b].positionChanged(param1Long, param1Int1, param1Int2, param1Int3, param1Int4); 
    }
    
    public void positionLost(long param1Long) {
      RenderNode.PositionUpdateListener[] arrayOfPositionUpdateListener = this.mListeners;
      int i = arrayOfPositionUpdateListener.length;
      for (byte b = 0; b < i; b++)
        arrayOfPositionUpdateListener[b].positionLost(param1Long); 
    }
    
    public CompositePositionUpdateListener with(RenderNode.PositionUpdateListener param1PositionUpdateListener) {
      return new CompositePositionUpdateListener((RenderNode.PositionUpdateListener[])ArrayUtils.appendElement(RenderNode.PositionUpdateListener.class, (Object[])this.mListeners, param1PositionUpdateListener));
    }
    
    public CompositePositionUpdateListener without(RenderNode.PositionUpdateListener param1PositionUpdateListener) {
      return new CompositePositionUpdateListener((RenderNode.PositionUpdateListener[])ArrayUtils.removeElement(RenderNode.PositionUpdateListener.class, (Object[])this.mListeners, param1PositionUpdateListener));
    }
  }
  
  private static class NoImagePreloadHolder {
    public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(RenderNode.class.getClassLoader(), RenderNode.nGetNativeFinalizer());
  }
  
  public static interface PositionUpdateListener {
    void positionChanged(long param1Long, int param1Int1, int param1Int2, int param1Int3, int param1Int4);
    
    void positionLost(long param1Long);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface UsageHint {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/RenderNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
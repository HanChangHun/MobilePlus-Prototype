package android.graphics;

import android.graphics.text.MeasuredText;
import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.microedition.khronos.opengles.GL;
import libcore.util.NativeAllocationRegistry;

public class Canvas extends BaseCanvas {
  public static final int ALL_SAVE_FLAG = 31;
  
  public static final int CLIP_SAVE_FLAG = 2;
  
  public static final int CLIP_TO_LAYER_SAVE_FLAG = 16;
  
  public static final int FULL_COLOR_LAYER_SAVE_FLAG = 8;
  
  public static final int HAS_ALPHA_LAYER_SAVE_FLAG = 4;
  
  public static final int MATRIX_SAVE_FLAG = 1;
  
  private static final int MAXMIMUM_BITMAP_SIZE = 32766;
  
  public static boolean sCompatibilityRestore;
  
  public static boolean sCompatibilitySetBitmap;
  
  private static int sCompatiblityVersion = 0;
  
  private Bitmap mBitmap;
  
  private DrawFilter mDrawFilter;
  
  private Runnable mFinalizer;
  
  static {
    sCompatibilityRestore = false;
    sCompatibilitySetBitmap = false;
  }
  
  public Canvas() {
    if (!isHardwareAccelerated()) {
      this.mNativeCanvasWrapper = nInitRaster(0L);
      this.mFinalizer = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeCanvasWrapper);
    } else {
      this.mFinalizer = null;
    } 
  }
  
  public Canvas(long paramLong) {
    if (paramLong != 0L) {
      this.mNativeCanvasWrapper = paramLong;
      this.mFinalizer = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeCanvasWrapper);
      this.mDensity = Bitmap.getDefaultDensity();
      return;
    } 
    throw new IllegalStateException();
  }
  
  public Canvas(Bitmap paramBitmap) {
    if (paramBitmap.isMutable()) {
      throwIfCannotDraw(paramBitmap);
      this.mNativeCanvasWrapper = nInitRaster(paramBitmap.getNativeInstance());
      this.mFinalizer = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeCanvasWrapper);
      this.mBitmap = paramBitmap;
      this.mDensity = paramBitmap.mDensity;
      return;
    } 
    throw new IllegalStateException("Immutable bitmap passed to Canvas constructor");
  }
  
  private static void checkValidClipOp(Region.Op paramOp) {
    if (sCompatiblityVersion < 28 || paramOp == Region.Op.INTERSECT || paramOp == Region.Op.DIFFERENCE)
      return; 
    throw new IllegalArgumentException("Invalid Region.Op - only INTERSECT and DIFFERENCE are allowed");
  }
  
  private static void checkValidSaveFlags(int paramInt) {
    if (sCompatiblityVersion < 28 || paramInt == 31)
      return; 
    throw new IllegalArgumentException("Invalid Layer Save Flag - only ALL_SAVE_FLAGS is allowed");
  }
  
  public static void freeCaches() {
    nFreeCaches();
  }
  
  public static void freeTextLayoutCaches() {
    nFreeTextLayoutCaches();
  }
  
  @CriticalNative
  private static native boolean nClipPath(long paramLong1, long paramLong2, int paramInt);
  
  @CriticalNative
  private static native boolean nClipRect(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt);
  
  @CriticalNative
  private static native void nConcat(long paramLong1, long paramLong2);
  
  private static native void nFreeCaches();
  
  private static native void nFreeTextLayoutCaches();
  
  @FastNative
  private static native boolean nGetClipBounds(long paramLong, Rect paramRect);
  
  @CriticalNative
  private static native int nGetHeight(long paramLong);
  
  @CriticalNative
  private static native void nGetMatrix(long paramLong1, long paramLong2);
  
  private static native long nGetNativeFinalizer();
  
  @CriticalNative
  private static native int nGetSaveCount(long paramLong);
  
  @CriticalNative
  private static native int nGetWidth(long paramLong);
  
  @FastNative
  private static native long nInitRaster(long paramLong);
  
  @CriticalNative
  private static native boolean nIsOpaque(long paramLong);
  
  @CriticalNative
  private static native boolean nQuickReject(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  @CriticalNative
  private static native boolean nQuickReject(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native boolean nRestore(long paramLong);
  
  @CriticalNative
  private static native void nRestoreToCount(long paramLong, int paramInt);
  
  @CriticalNative
  private static native void nRestoreUnclippedLayer(long paramLong1, int paramInt, long paramLong2);
  
  @CriticalNative
  private static native void nRotate(long paramLong, float paramFloat);
  
  @CriticalNative
  private static native int nSave(long paramLong, int paramInt);
  
  @CriticalNative
  private static native int nSaveLayer(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong2, int paramInt);
  
  @CriticalNative
  private static native int nSaveLayerAlpha(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2);
  
  @CriticalNative
  private static native int nSaveUnclippedLayer(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  @CriticalNative
  private static native void nScale(long paramLong, float paramFloat1, float paramFloat2);
  
  @FastNative
  private static native void nSetBitmap(long paramLong1, long paramLong2);
  
  private static native void nSetCompatibilityVersion(int paramInt);
  
  @CriticalNative
  private static native void nSetDrawFilter(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nSetMatrix(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nSkew(long paramLong, float paramFloat1, float paramFloat2);
  
  @CriticalNative
  private static native void nTranslate(long paramLong, float paramFloat1, float paramFloat2);
  
  public static void setCompatibilityVersion(int paramInt) {
    sCompatiblityVersion = paramInt;
    nSetCompatibilityVersion(paramInt);
  }
  
  public boolean clipOutPath(Path paramPath) {
    return clipPath(paramPath, Region.Op.DIFFERENCE);
  }
  
  public boolean clipOutRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    return nClipRect(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4, Region.Op.DIFFERENCE.nativeInt);
  }
  
  public boolean clipOutRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return nClipRect(this.mNativeCanvasWrapper, paramInt1, paramInt2, paramInt3, paramInt4, Region.Op.DIFFERENCE.nativeInt);
  }
  
  public boolean clipOutRect(Rect paramRect) {
    return nClipRect(this.mNativeCanvasWrapper, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom, Region.Op.DIFFERENCE.nativeInt);
  }
  
  public boolean clipOutRect(RectF paramRectF) {
    return nClipRect(this.mNativeCanvasWrapper, paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, Region.Op.DIFFERENCE.nativeInt);
  }
  
  public boolean clipPath(Path paramPath) {
    return clipPath(paramPath, Region.Op.INTERSECT);
  }
  
  @Deprecated
  public boolean clipPath(Path paramPath, Region.Op paramOp) {
    checkValidClipOp(paramOp);
    return nClipPath(this.mNativeCanvasWrapper, paramPath.readOnlyNI(), paramOp.nativeInt);
  }
  
  public boolean clipRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    return nClipRect(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4, Region.Op.INTERSECT.nativeInt);
  }
  
  @Deprecated
  public boolean clipRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Region.Op paramOp) {
    checkValidClipOp(paramOp);
    return nClipRect(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramOp.nativeInt);
  }
  
  public boolean clipRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return nClipRect(this.mNativeCanvasWrapper, paramInt1, paramInt2, paramInt3, paramInt4, Region.Op.INTERSECT.nativeInt);
  }
  
  public boolean clipRect(Rect paramRect) {
    return nClipRect(this.mNativeCanvasWrapper, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom, Region.Op.INTERSECT.nativeInt);
  }
  
  @Deprecated
  public boolean clipRect(Rect paramRect, Region.Op paramOp) {
    checkValidClipOp(paramOp);
    return nClipRect(this.mNativeCanvasWrapper, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom, paramOp.nativeInt);
  }
  
  public boolean clipRect(RectF paramRectF) {
    return nClipRect(this.mNativeCanvasWrapper, paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, Region.Op.INTERSECT.nativeInt);
  }
  
  @Deprecated
  public boolean clipRect(RectF paramRectF, Region.Op paramOp) {
    checkValidClipOp(paramOp);
    return nClipRect(this.mNativeCanvasWrapper, paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramOp.nativeInt);
  }
  
  public boolean clipRectUnion(Rect paramRect) {
    return nClipRect(this.mNativeCanvasWrapper, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom, Region.Op.UNION.nativeInt);
  }
  
  @Deprecated
  public boolean clipRegion(Region paramRegion) {
    return false;
  }
  
  @Deprecated
  public boolean clipRegion(Region paramRegion, Region.Op paramOp) {
    return false;
  }
  
  public void concat(Matrix paramMatrix) {
    if (paramMatrix != null)
      nConcat(this.mNativeCanvasWrapper, paramMatrix.native_instance); 
  }
  
  public void disableZ() {}
  
  public void drawARGB(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.drawARGB(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void drawArc(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean, Paint paramPaint) {
    super.drawArc(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramBoolean, paramPaint);
  }
  
  public void drawArc(RectF paramRectF, float paramFloat1, float paramFloat2, boolean paramBoolean, Paint paramPaint) {
    super.drawArc(paramRectF, paramFloat1, paramFloat2, paramBoolean, paramPaint);
  }
  
  public void drawBitmap(Bitmap paramBitmap, float paramFloat1, float paramFloat2, Paint paramPaint) {
    super.drawBitmap(paramBitmap, paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawBitmap(Bitmap paramBitmap, Matrix paramMatrix, Paint paramPaint) {
    super.drawBitmap(paramBitmap, paramMatrix, paramPaint);
  }
  
  public void drawBitmap(Bitmap paramBitmap, Rect paramRect1, Rect paramRect2, Paint paramPaint) {
    super.drawBitmap(paramBitmap, paramRect1, paramRect2, paramPaint);
  }
  
  public void drawBitmap(Bitmap paramBitmap, Rect paramRect, RectF paramRectF, Paint paramPaint) {
    super.drawBitmap(paramBitmap, paramRect, paramRectF, paramPaint);
  }
  
  @Deprecated
  public void drawBitmap(int[] paramArrayOfint, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, int paramInt3, int paramInt4, boolean paramBoolean, Paint paramPaint) {
    super.drawBitmap(paramArrayOfint, paramInt1, paramInt2, paramFloat1, paramFloat2, paramInt3, paramInt4, paramBoolean, paramPaint);
  }
  
  @Deprecated
  public void drawBitmap(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, Paint paramPaint) {
    super.drawBitmap(paramArrayOfint, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramBoolean, paramPaint);
  }
  
  public void drawBitmapMesh(Bitmap paramBitmap, int paramInt1, int paramInt2, float[] paramArrayOffloat, int paramInt3, int[] paramArrayOfint, int paramInt4, Paint paramPaint) {
    super.drawBitmapMesh(paramBitmap, paramInt1, paramInt2, paramArrayOffloat, paramInt3, paramArrayOfint, paramInt4, paramPaint);
  }
  
  public void drawCircle(float paramFloat1, float paramFloat2, float paramFloat3, Paint paramPaint) {
    super.drawCircle(paramFloat1, paramFloat2, paramFloat3, paramPaint);
  }
  
  public void drawColor(int paramInt) {
    super.drawColor(paramInt);
  }
  
  public void drawColor(int paramInt, BlendMode paramBlendMode) {
    super.drawColor(paramInt, paramBlendMode);
  }
  
  public void drawColor(int paramInt, PorterDuff.Mode paramMode) {
    super.drawColor(paramInt, paramMode);
  }
  
  public void drawColor(long paramLong) {
    super.drawColor(paramLong, BlendMode.SRC_OVER);
  }
  
  public void drawColor(long paramLong, BlendMode paramBlendMode) {
    super.drawColor(paramLong, paramBlendMode);
  }
  
  public void drawDoubleRoundRect(RectF paramRectF1, float paramFloat1, float paramFloat2, RectF paramRectF2, float paramFloat3, float paramFloat4, Paint paramPaint) {
    super.drawDoubleRoundRect(paramRectF1, paramFloat1, paramFloat2, paramRectF2, paramFloat3, paramFloat4, paramPaint);
  }
  
  public void drawDoubleRoundRect(RectF paramRectF1, float[] paramArrayOffloat1, RectF paramRectF2, float[] paramArrayOffloat2, Paint paramPaint) {
    super.drawDoubleRoundRect(paramRectF1, paramArrayOffloat1, paramRectF2, paramArrayOffloat2, paramPaint);
  }
  
  public void drawLine(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Paint paramPaint) {
    super.drawLine(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramPaint);
  }
  
  public void drawLines(float[] paramArrayOffloat, int paramInt1, int paramInt2, Paint paramPaint) {
    super.drawLines(paramArrayOffloat, paramInt1, paramInt2, paramPaint);
  }
  
  public void drawLines(float[] paramArrayOffloat, Paint paramPaint) {
    super.drawLines(paramArrayOffloat, paramPaint);
  }
  
  public void drawOval(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Paint paramPaint) {
    super.drawOval(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramPaint);
  }
  
  public void drawOval(RectF paramRectF, Paint paramPaint) {
    super.drawOval(paramRectF, paramPaint);
  }
  
  public void drawPaint(Paint paramPaint) {
    super.drawPaint(paramPaint);
  }
  
  public void drawPatch(NinePatch paramNinePatch, Rect paramRect, Paint paramPaint) {
    super.drawPatch(paramNinePatch, paramRect, paramPaint);
  }
  
  public void drawPatch(NinePatch paramNinePatch, RectF paramRectF, Paint paramPaint) {
    super.drawPatch(paramNinePatch, paramRectF, paramPaint);
  }
  
  public void drawPath(Path paramPath, Paint paramPaint) {
    super.drawPath(paramPath, paramPaint);
  }
  
  public void drawPicture(Picture paramPicture) {
    paramPicture.endRecording();
    int i = save();
    paramPicture.draw(this);
    restoreToCount(i);
  }
  
  public void drawPicture(Picture paramPicture, Rect paramRect) {
    save();
    translate(paramRect.left, paramRect.top);
    if (paramPicture.getWidth() > 0 && paramPicture.getHeight() > 0)
      scale(paramRect.width() / paramPicture.getWidth(), paramRect.height() / paramPicture.getHeight()); 
    drawPicture(paramPicture);
    restore();
  }
  
  public void drawPicture(Picture paramPicture, RectF paramRectF) {
    save();
    translate(paramRectF.left, paramRectF.top);
    if (paramPicture.getWidth() > 0 && paramPicture.getHeight() > 0)
      scale(paramRectF.width() / paramPicture.getWidth(), paramRectF.height() / paramPicture.getHeight()); 
    drawPicture(paramPicture);
    restore();
  }
  
  public void drawPoint(float paramFloat1, float paramFloat2, Paint paramPaint) {
    super.drawPoint(paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawPoints(float[] paramArrayOffloat, int paramInt1, int paramInt2, Paint paramPaint) {
    super.drawPoints(paramArrayOffloat, paramInt1, paramInt2, paramPaint);
  }
  
  public void drawPoints(float[] paramArrayOffloat, Paint paramPaint) {
    super.drawPoints(paramArrayOffloat, paramPaint);
  }
  
  @Deprecated
  public void drawPosText(String paramString, float[] paramArrayOffloat, Paint paramPaint) {
    super.drawPosText(paramString, paramArrayOffloat, paramPaint);
  }
  
  @Deprecated
  public void drawPosText(char[] paramArrayOfchar, int paramInt1, int paramInt2, float[] paramArrayOffloat, Paint paramPaint) {
    super.drawPosText(paramArrayOfchar, paramInt1, paramInt2, paramArrayOffloat, paramPaint);
  }
  
  public void drawRGB(int paramInt1, int paramInt2, int paramInt3) {
    super.drawRGB(paramInt1, paramInt2, paramInt3);
  }
  
  public void drawRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Paint paramPaint) {
    super.drawRect(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramPaint);
  }
  
  public void drawRect(Rect paramRect, Paint paramPaint) {
    super.drawRect(paramRect, paramPaint);
  }
  
  public void drawRect(RectF paramRectF, Paint paramPaint) {
    super.drawRect(paramRectF, paramPaint);
  }
  
  public void drawRenderNode(RenderNode paramRenderNode) {
    throw new IllegalArgumentException("Software rendering doesn't support drawRenderNode");
  }
  
  public void drawRoundRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, Paint paramPaint) {
    super.drawRoundRect(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramPaint);
  }
  
  public void drawRoundRect(RectF paramRectF, float paramFloat1, float paramFloat2, Paint paramPaint) {
    super.drawRoundRect(paramRectF, paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawText(CharSequence paramCharSequence, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, Paint paramPaint) {
    super.drawText(paramCharSequence, paramInt1, paramInt2, paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawText(String paramString, float paramFloat1, float paramFloat2, Paint paramPaint) {
    super.drawText(paramString, paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawText(String paramString, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, Paint paramPaint) {
    super.drawText(paramString, paramInt1, paramInt2, paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawText(char[] paramArrayOfchar, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, Paint paramPaint) {
    super.drawText(paramArrayOfchar, paramInt1, paramInt2, paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawTextOnPath(String paramString, Path paramPath, float paramFloat1, float paramFloat2, Paint paramPaint) {
    super.drawTextOnPath(paramString, paramPath, paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawTextOnPath(char[] paramArrayOfchar, int paramInt1, int paramInt2, Path paramPath, float paramFloat1, float paramFloat2, Paint paramPaint) {
    super.drawTextOnPath(paramArrayOfchar, paramInt1, paramInt2, paramPath, paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawTextRun(MeasuredText paramMeasuredText, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, boolean paramBoolean, Paint paramPaint) {
    super.drawTextRun(paramMeasuredText, paramInt1, paramInt2, paramInt3, paramInt4, paramFloat1, paramFloat2, paramBoolean, paramPaint);
  }
  
  public void drawTextRun(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, boolean paramBoolean, Paint paramPaint) {
    super.drawTextRun(paramCharSequence, paramInt1, paramInt2, paramInt3, paramInt4, paramFloat1, paramFloat2, paramBoolean, paramPaint);
  }
  
  public void drawTextRun(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, boolean paramBoolean, Paint paramPaint) {
    super.drawTextRun(paramArrayOfchar, paramInt1, paramInt2, paramInt3, paramInt4, paramFloat1, paramFloat2, paramBoolean, paramPaint);
  }
  
  public void drawVertices(VertexMode paramVertexMode, int paramInt1, float[] paramArrayOffloat1, int paramInt2, float[] paramArrayOffloat2, int paramInt3, int[] paramArrayOfint, int paramInt4, short[] paramArrayOfshort, int paramInt5, int paramInt6, Paint paramPaint) {
    super.drawVertices(paramVertexMode, paramInt1, paramArrayOffloat1, paramInt2, paramArrayOffloat2, paramInt3, paramArrayOfint, paramInt4, paramArrayOfshort, paramInt5, paramInt6, paramPaint);
  }
  
  public void enableZ() {}
  
  public final Rect getClipBounds() {
    Rect rect = new Rect();
    getClipBounds(rect);
    return rect;
  }
  
  public boolean getClipBounds(Rect paramRect) {
    return nGetClipBounds(this.mNativeCanvasWrapper, paramRect);
  }
  
  public int getDensity() {
    return this.mDensity;
  }
  
  public DrawFilter getDrawFilter() {
    return this.mDrawFilter;
  }
  
  @Deprecated
  protected GL getGL() {
    return null;
  }
  
  public int getHeight() {
    return nGetHeight(this.mNativeCanvasWrapper);
  }
  
  @Deprecated
  public final Matrix getMatrix() {
    Matrix matrix = new Matrix();
    getMatrix(matrix);
    return matrix;
  }
  
  @Deprecated
  public void getMatrix(Matrix paramMatrix) {
    nGetMatrix(this.mNativeCanvasWrapper, paramMatrix.native_instance);
  }
  
  public int getMaximumBitmapHeight() {
    return 32766;
  }
  
  public int getMaximumBitmapWidth() {
    return 32766;
  }
  
  public long getNativeCanvasWrapper() {
    return this.mNativeCanvasWrapper;
  }
  
  public int getSaveCount() {
    return nGetSaveCount(this.mNativeCanvasWrapper);
  }
  
  public int getWidth() {
    return nGetWidth(this.mNativeCanvasWrapper);
  }
  
  @Deprecated
  public void insertInorderBarrier() {
    disableZ();
  }
  
  @Deprecated
  public void insertReorderBarrier() {
    enableZ();
  }
  
  public boolean isHardwareAccelerated() {
    return false;
  }
  
  public boolean isOpaque() {
    return nIsOpaque(this.mNativeCanvasWrapper);
  }
  
  public boolean isRecordingFor(Object paramObject) {
    return false;
  }
  
  public boolean quickReject(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    return nQuickReject(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  @Deprecated
  public boolean quickReject(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, EdgeType paramEdgeType) {
    return nQuickReject(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public boolean quickReject(Path paramPath) {
    return nQuickReject(this.mNativeCanvasWrapper, paramPath.readOnlyNI());
  }
  
  @Deprecated
  public boolean quickReject(Path paramPath, EdgeType paramEdgeType) {
    return nQuickReject(this.mNativeCanvasWrapper, paramPath.readOnlyNI());
  }
  
  public boolean quickReject(RectF paramRectF) {
    return nQuickReject(this.mNativeCanvasWrapper, paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom);
  }
  
  @Deprecated
  public boolean quickReject(RectF paramRectF, EdgeType paramEdgeType) {
    return nQuickReject(this.mNativeCanvasWrapper, paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom);
  }
  
  public void release() {
    this.mNativeCanvasWrapper = 0L;
    Runnable runnable = this.mFinalizer;
    if (runnable != null) {
      runnable.run();
      this.mFinalizer = null;
    } 
  }
  
  public void restore() {
    if (nRestore(this.mNativeCanvasWrapper) || (sCompatibilityRestore && isHardwareAccelerated()))
      return; 
    throw new IllegalStateException("Underflow in restore - more restores than saves");
  }
  
  public void restoreToCount(int paramInt) {
    int i = paramInt;
    if (paramInt < 1)
      if (sCompatibilityRestore && isHardwareAccelerated()) {
        i = 1;
      } else {
        throw new IllegalArgumentException("Underflow in restoreToCount - more restores than saves");
      }  
    nRestoreToCount(this.mNativeCanvasWrapper, i);
  }
  
  public void restoreUnclippedLayer(int paramInt, Paint paramPaint) {
    nRestoreUnclippedLayer(this.mNativeCanvasWrapper, paramInt, paramPaint.getNativeInstance());
  }
  
  public void rotate(float paramFloat) {
    if (paramFloat == 0.0F)
      return; 
    nRotate(this.mNativeCanvasWrapper, paramFloat);
  }
  
  public final void rotate(float paramFloat1, float paramFloat2, float paramFloat3) {
    if (paramFloat1 == 0.0F)
      return; 
    translate(paramFloat2, paramFloat3);
    rotate(paramFloat1);
    translate(-paramFloat2, -paramFloat3);
  }
  
  public int save() {
    return nSave(this.mNativeCanvasWrapper, 3);
  }
  
  public int save(int paramInt) {
    return nSave(this.mNativeCanvasWrapper, paramInt);
  }
  
  public int saveLayer(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Paint paramPaint) {
    return saveLayer(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramPaint, 31);
  }
  
  public int saveLayer(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Paint paramPaint, int paramInt) {
    long l2;
    checkValidSaveFlags(paramInt);
    long l1 = this.mNativeCanvasWrapper;
    if (paramPaint != null) {
      l2 = paramPaint.getNativeInstance();
    } else {
      l2 = 0L;
    } 
    return nSaveLayer(l1, paramFloat1, paramFloat2, paramFloat3, paramFloat4, l2, 31);
  }
  
  public int saveLayer(RectF paramRectF, Paint paramPaint) {
    return saveLayer(paramRectF, paramPaint, 31);
  }
  
  public int saveLayer(RectF paramRectF, Paint paramPaint, int paramInt) {
    RectF rectF = paramRectF;
    if (paramRectF == null)
      rectF = new RectF(getClipBounds()); 
    checkValidSaveFlags(paramInt);
    return saveLayer(rectF.left, rectF.top, rectF.right, rectF.bottom, paramPaint, 31);
  }
  
  public int saveLayerAlpha(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt) {
    return saveLayerAlpha(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt, 31);
  }
  
  public int saveLayerAlpha(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2) {
    checkValidSaveFlags(paramInt2);
    paramInt1 = Math.min(255, Math.max(0, paramInt1));
    return nSaveLayerAlpha(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt1, 31);
  }
  
  public int saveLayerAlpha(RectF paramRectF, int paramInt) {
    return saveLayerAlpha(paramRectF, paramInt, 31);
  }
  
  public int saveLayerAlpha(RectF paramRectF, int paramInt1, int paramInt2) {
    RectF rectF = paramRectF;
    if (paramRectF == null)
      rectF = new RectF(getClipBounds()); 
    checkValidSaveFlags(paramInt2);
    return saveLayerAlpha(rectF.left, rectF.top, rectF.right, rectF.bottom, paramInt1, 31);
  }
  
  public int saveUnclippedLayer(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return nSaveUnclippedLayer(this.mNativeCanvasWrapper, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void scale(float paramFloat1, float paramFloat2) {
    if (paramFloat1 == 1.0F && paramFloat2 == 1.0F)
      return; 
    nScale(this.mNativeCanvasWrapper, paramFloat1, paramFloat2);
  }
  
  public final void scale(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    if (paramFloat1 == 1.0F && paramFloat2 == 1.0F)
      return; 
    translate(paramFloat3, paramFloat4);
    scale(paramFloat1, paramFloat2);
    translate(-paramFloat3, -paramFloat4);
  }
  
  public void setBitmap(Bitmap paramBitmap) {
    if (!isHardwareAccelerated()) {
      Matrix matrix1 = null;
      Matrix matrix2 = matrix1;
      if (paramBitmap != null) {
        matrix2 = matrix1;
        if (sCompatibilitySetBitmap)
          matrix2 = getMatrix(); 
      } 
      if (paramBitmap == null) {
        nSetBitmap(this.mNativeCanvasWrapper, 0L);
        this.mDensity = 0;
      } else if (paramBitmap.isMutable()) {
        throwIfCannotDraw(paramBitmap);
        nSetBitmap(this.mNativeCanvasWrapper, paramBitmap.getNativeInstance());
        this.mDensity = paramBitmap.mDensity;
      } else {
        throw new IllegalStateException();
      } 
      if (matrix2 != null)
        setMatrix(matrix2); 
      this.mBitmap = paramBitmap;
      return;
    } 
    throw new RuntimeException("Can't set a bitmap device on a HW accelerated canvas");
  }
  
  public void setDensity(int paramInt) {
    Bitmap bitmap = this.mBitmap;
    if (bitmap != null)
      bitmap.setDensity(paramInt); 
    this.mDensity = paramInt;
  }
  
  public void setDrawFilter(DrawFilter paramDrawFilter) {
    long l = 0L;
    if (paramDrawFilter != null)
      l = paramDrawFilter.mNativeInt; 
    this.mDrawFilter = paramDrawFilter;
    nSetDrawFilter(this.mNativeCanvasWrapper, l);
  }
  
  public void setMatrix(Matrix paramMatrix) {
    long l2;
    long l1 = this.mNativeCanvasWrapper;
    if (paramMatrix == null) {
      l2 = 0L;
    } else {
      l2 = paramMatrix.native_instance;
    } 
    nSetMatrix(l1, l2);
  }
  
  public void setScreenDensity(int paramInt) {
    this.mScreenDensity = paramInt;
  }
  
  public void skew(float paramFloat1, float paramFloat2) {
    if (paramFloat1 == 0.0F && paramFloat2 == 0.0F)
      return; 
    nSkew(this.mNativeCanvasWrapper, paramFloat1, paramFloat2);
  }
  
  public void translate(float paramFloat1, float paramFloat2) {
    if (paramFloat1 == 0.0F && paramFloat2 == 0.0F)
      return; 
    nTranslate(this.mNativeCanvasWrapper, paramFloat1, paramFloat2);
  }
  
  public enum EdgeType {
    AA,
    BW(0);
    
    public final int nativeInt;
    
    static {
      EdgeType edgeType = new EdgeType("AA", 1, 1);
      AA = edgeType;
      $VALUES = new EdgeType[] { BW, edgeType };
    }
    
    EdgeType(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
  
  private static class NoImagePreloadHolder {
    public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Canvas.class.getClassLoader(), Canvas.nGetNativeFinalizer());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Saveflags {}
  
  public enum VertexMode {
    TRIANGLES(0),
    TRIANGLE_FAN(0),
    TRIANGLE_STRIP(1);
    
    public final int nativeInt;
    
    static {
      VertexMode vertexMode = new VertexMode("TRIANGLE_FAN", 2, 2);
      TRIANGLE_FAN = vertexMode;
      $VALUES = new VertexMode[] { TRIANGLES, TRIANGLE_STRIP, vertexMode };
    }
    
    VertexMode(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Canvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.graphics;

import android.graphics.text.MeasuredText;
import android.text.GraphicsOperations;
import android.text.PrecomputedText;
import android.text.TextUtils;

public abstract class BaseCanvas {
  private boolean mAllowHwBitmapsInSwMode = false;
  
  protected int mDensity = 0;
  
  protected long mNativeCanvasWrapper;
  
  protected int mScreenDensity = 0;
  
  protected static final void checkRange(int paramInt1, int paramInt2, int paramInt3) {
    if ((paramInt2 | paramInt3) >= 0 && paramInt2 + paramInt3 <= paramInt1)
      return; 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  private static native void nDrawArc(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean, long paramLong2);
  
  private static native void nDrawBitmap(long paramLong1, long paramLong2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, long paramLong3, int paramInt1, int paramInt2);
  
  private static native void nDrawBitmap(long paramLong1, long paramLong2, float paramFloat1, float paramFloat2, long paramLong3, int paramInt1, int paramInt2, int paramInt3);
  
  private static native void nDrawBitmap(long paramLong1, int[] paramArrayOfint, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, int paramInt3, int paramInt4, boolean paramBoolean, long paramLong2);
  
  private static native void nDrawBitmapMatrix(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
  
  private static native void nDrawBitmapMesh(long paramLong1, long paramLong2, int paramInt1, int paramInt2, float[] paramArrayOffloat, int paramInt3, int[] paramArrayOfint, int paramInt4, long paramLong3);
  
  private static native void nDrawCircle(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong2);
  
  private static native void nDrawColor(long paramLong, int paramInt1, int paramInt2);
  
  private static native void nDrawColor(long paramLong1, long paramLong2, long paramLong3, int paramInt);
  
  private static native void nDrawDoubleRoundRect(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, long paramLong2);
  
  private static native void nDrawDoubleRoundRect(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float[] paramArrayOffloat1, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float[] paramArrayOffloat2, long paramLong2);
  
  private static native void nDrawLine(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong2);
  
  private static native void nDrawLines(long paramLong1, float[] paramArrayOffloat, int paramInt1, int paramInt2, long paramLong2);
  
  private static native void nDrawNinePatch(long paramLong1, long paramLong2, long paramLong3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong4, int paramInt1, int paramInt2);
  
  private static native void nDrawOval(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong2);
  
  private static native void nDrawPaint(long paramLong1, long paramLong2);
  
  private static native void nDrawPath(long paramLong1, long paramLong2, long paramLong3);
  
  private static native void nDrawPoint(long paramLong1, float paramFloat1, float paramFloat2, long paramLong2);
  
  private static native void nDrawPoints(long paramLong1, float[] paramArrayOffloat, int paramInt1, int paramInt2, long paramLong2);
  
  private static native void nDrawRect(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong2);
  
  private static native void nDrawRegion(long paramLong1, long paramLong2, long paramLong3);
  
  private static native void nDrawRoundRect(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, long paramLong2);
  
  private static native void nDrawText(long paramLong1, String paramString, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, int paramInt3, long paramLong2);
  
  private static native void nDrawText(long paramLong1, char[] paramArrayOfchar, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, int paramInt3, long paramLong2);
  
  private static native void nDrawTextOnPath(long paramLong1, String paramString, long paramLong2, float paramFloat1, float paramFloat2, int paramInt, long paramLong3);
  
  private static native void nDrawTextOnPath(long paramLong1, char[] paramArrayOfchar, int paramInt1, int paramInt2, long paramLong2, float paramFloat1, float paramFloat2, int paramInt3, long paramLong3);
  
  private static native void nDrawTextRun(long paramLong1, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, boolean paramBoolean, long paramLong2);
  
  private static native void nDrawTextRun(long paramLong1, char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, boolean paramBoolean, long paramLong2, long paramLong3);
  
  private static native void nDrawVertices(long paramLong1, int paramInt1, int paramInt2, float[] paramArrayOffloat1, int paramInt3, float[] paramArrayOffloat2, int paramInt4, int[] paramArrayOfint, int paramInt5, short[] paramArrayOfshort, int paramInt6, int paramInt7, long paramLong2);
  
  private void throwIfHasHwBitmapInSwMode(Paint paramPaint) {
    if (isHardwareAccelerated() || paramPaint == null)
      return; 
    throwIfHasHwBitmapInSwMode(paramPaint.getShader());
  }
  
  private void throwIfHasHwBitmapInSwMode(Shader paramShader) {
    if (paramShader == null)
      return; 
    if (paramShader instanceof BitmapShader)
      throwIfHwBitmapInSwMode(((BitmapShader)paramShader).mBitmap); 
    if (paramShader instanceof ComposeShader) {
      throwIfHasHwBitmapInSwMode(((ComposeShader)paramShader).mShaderA);
      throwIfHasHwBitmapInSwMode(((ComposeShader)paramShader).mShaderB);
    } 
  }
  
  private void throwIfHwBitmapInSwMode(Bitmap paramBitmap) {
    if (!isHardwareAccelerated() && paramBitmap.getConfig() == Bitmap.Config.HARDWARE)
      onHwBitmapInSwMode(); 
  }
  
  public void drawARGB(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    drawColor(Color.argb(paramInt1, paramInt2, paramInt3, paramInt4));
  }
  
  public void drawArc(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawArc(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramBoolean, paramPaint.getNativeInstance());
  }
  
  public void drawArc(RectF paramRectF, float paramFloat1, float paramFloat2, boolean paramBoolean, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    drawArc(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramFloat1, paramFloat2, paramBoolean, paramPaint);
  }
  
  public void drawBitmap(Bitmap paramBitmap, float paramFloat1, float paramFloat2, Paint paramPaint) {
    long l3;
    throwIfCannotDraw(paramBitmap);
    throwIfHasHwBitmapInSwMode(paramPaint);
    long l1 = this.mNativeCanvasWrapper;
    long l2 = paramBitmap.getNativeInstance();
    if (paramPaint != null) {
      l3 = paramPaint.getNativeInstance();
    } else {
      l3 = 0L;
    } 
    nDrawBitmap(l1, l2, paramFloat1, paramFloat2, l3, this.mDensity, this.mScreenDensity, paramBitmap.mDensity);
  }
  
  public void drawBitmap(Bitmap paramBitmap, Matrix paramMatrix, Paint paramPaint) {
    long l4;
    throwIfHasHwBitmapInSwMode(paramPaint);
    long l1 = this.mNativeCanvasWrapper;
    long l2 = paramBitmap.getNativeInstance();
    long l3 = paramMatrix.ni();
    if (paramPaint != null) {
      l4 = paramPaint.getNativeInstance();
    } else {
      l4 = 0L;
    } 
    nDrawBitmapMatrix(l1, l2, l3, l4);
  }
  
  public void drawBitmap(Bitmap paramBitmap, Rect paramRect1, Rect paramRect2, Paint paramPaint) {
    if (paramRect2 != null) {
      long l;
      int i;
      int j;
      int k;
      int m;
      throwIfCannotDraw(paramBitmap);
      throwIfHasHwBitmapInSwMode(paramPaint);
      if (paramPaint == null) {
        l = 0L;
      } else {
        l = paramPaint.getNativeInstance();
      } 
      if (paramRect1 == null) {
        i = 0;
        j = 0;
        k = paramBitmap.getWidth();
        m = paramBitmap.getHeight();
      } else {
        i = paramRect1.left;
        k = paramRect1.right;
        j = paramRect1.top;
        m = paramRect1.bottom;
      } 
      nDrawBitmap(this.mNativeCanvasWrapper, paramBitmap.getNativeInstance(), i, j, k, m, paramRect2.left, paramRect2.top, paramRect2.right, paramRect2.bottom, l, this.mScreenDensity, paramBitmap.mDensity);
      return;
    } 
    throw null;
  }
  
  public void drawBitmap(Bitmap paramBitmap, Rect paramRect, RectF paramRectF, Paint paramPaint) {
    if (paramRectF != null) {
      long l;
      float f1;
      float f2;
      float f3;
      float f4;
      throwIfCannotDraw(paramBitmap);
      throwIfHasHwBitmapInSwMode(paramPaint);
      if (paramPaint == null) {
        l = 0L;
      } else {
        l = paramPaint.getNativeInstance();
      } 
      if (paramRect == null) {
        f1 = 0.0F;
        f2 = 0.0F;
        f3 = paramBitmap.getWidth();
        f4 = paramBitmap.getHeight();
      } else {
        f1 = paramRect.left;
        f3 = paramRect.right;
        f2 = paramRect.top;
        f4 = paramRect.bottom;
      } 
      nDrawBitmap(this.mNativeCanvasWrapper, paramBitmap.getNativeInstance(), f1, f2, f3, f4, paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, l, this.mScreenDensity, paramBitmap.mDensity);
      return;
    } 
    throw null;
  }
  
  @Deprecated
  public void drawBitmap(int[] paramArrayOfint, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, int paramInt3, int paramInt4, boolean paramBoolean, Paint paramPaint) {
    if (paramInt3 >= 0) {
      if (paramInt4 >= 0) {
        if (Math.abs(paramInt2) >= paramInt3) {
          int i = paramInt1 + (paramInt4 - 1) * paramInt2;
          int j = paramArrayOfint.length;
          if (paramInt1 >= 0 && paramInt1 + paramInt3 <= j && i >= 0 && i + paramInt3 <= j) {
            long l2;
            throwIfHasHwBitmapInSwMode(paramPaint);
            if (paramInt3 == 0 || paramInt4 == 0)
              return; 
            long l1 = this.mNativeCanvasWrapper;
            if (paramPaint != null) {
              l2 = paramPaint.getNativeInstance();
            } else {
              l2 = 0L;
            } 
            nDrawBitmap(l1, paramArrayOfint, paramInt1, paramInt2, paramFloat1, paramFloat2, paramInt3, paramInt4, paramBoolean, l2);
            return;
          } 
          throw new ArrayIndexOutOfBoundsException();
        } 
        throw new IllegalArgumentException("abs(stride) must be >= width");
      } 
      throw new IllegalArgumentException("height must be >= 0");
    } 
    throw new IllegalArgumentException("width must be >= 0");
  }
  
  @Deprecated
  public void drawBitmap(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, Paint paramPaint) {
    drawBitmap(paramArrayOfint, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramBoolean, paramPaint);
  }
  
  public void drawBitmapMesh(Bitmap paramBitmap, int paramInt1, int paramInt2, float[] paramArrayOffloat, int paramInt3, int[] paramArrayOfint, int paramInt4, Paint paramPaint) {
    if ((paramInt1 | paramInt2 | paramInt3 | paramInt4) >= 0) {
      long l3;
      throwIfHasHwBitmapInSwMode(paramPaint);
      if (paramInt1 == 0 || paramInt2 == 0)
        return; 
      int i = (paramInt1 + 1) * (paramInt2 + 1);
      checkRange(paramArrayOffloat.length, paramInt3, i * 2);
      if (paramArrayOfint != null)
        checkRange(paramArrayOfint.length, paramInt4, i); 
      long l1 = this.mNativeCanvasWrapper;
      long l2 = paramBitmap.getNativeInstance();
      if (paramPaint != null) {
        l3 = paramPaint.getNativeInstance();
      } else {
        l3 = 0L;
      } 
      nDrawBitmapMesh(l1, l2, paramInt1, paramInt2, paramArrayOffloat, paramInt3, paramArrayOfint, paramInt4, l3);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public void drawCircle(float paramFloat1, float paramFloat2, float paramFloat3, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawCircle(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramPaint.getNativeInstance());
  }
  
  public void drawColor(int paramInt) {
    nDrawColor(this.mNativeCanvasWrapper, paramInt, (BlendMode.SRC_OVER.getXfermode()).porterDuffMode);
  }
  
  public void drawColor(int paramInt, BlendMode paramBlendMode) {
    nDrawColor(this.mNativeCanvasWrapper, paramInt, (paramBlendMode.getXfermode()).porterDuffMode);
  }
  
  public void drawColor(int paramInt, PorterDuff.Mode paramMode) {
    nDrawColor(this.mNativeCanvasWrapper, paramInt, paramMode.nativeInt);
  }
  
  public void drawColor(long paramLong, BlendMode paramBlendMode) {
    ColorSpace colorSpace = Color.colorSpace(paramLong);
    nDrawColor(this.mNativeCanvasWrapper, colorSpace.getNativeInstance(), paramLong, (paramBlendMode.getXfermode()).porterDuffMode);
  }
  
  public void drawDoubleRoundRect(RectF paramRectF1, float paramFloat1, float paramFloat2, RectF paramRectF2, float paramFloat3, float paramFloat4, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    float f1 = paramRectF1.left;
    float f2 = paramRectF1.top;
    float f3 = paramRectF1.right;
    float f4 = paramRectF1.bottom;
    float f5 = paramRectF2.left;
    float f6 = paramRectF2.top;
    float f7 = paramRectF2.right;
    float f8 = paramRectF2.bottom;
    nDrawDoubleRoundRect(this.mNativeCanvasWrapper, f1, f2, f3, f4, paramFloat1, paramFloat2, f5, f6, f7, f8, paramFloat3, paramFloat4, paramPaint.getNativeInstance());
  }
  
  public void drawDoubleRoundRect(RectF paramRectF1, float[] paramArrayOffloat1, RectF paramRectF2, float[] paramArrayOffloat2, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    if (paramArrayOffloat2 != null && paramArrayOffloat1 != null && paramArrayOffloat2.length == 8 && paramArrayOffloat1.length == 8) {
      float f1 = paramRectF1.left;
      float f2 = paramRectF1.top;
      float f3 = paramRectF1.right;
      float f4 = paramRectF1.bottom;
      float f5 = paramRectF2.left;
      float f6 = paramRectF2.top;
      float f7 = paramRectF2.right;
      float f8 = paramRectF2.bottom;
      nDrawDoubleRoundRect(this.mNativeCanvasWrapper, f1, f2, f3, f4, paramArrayOffloat1, f5, f6, f7, f8, paramArrayOffloat2, paramPaint.getNativeInstance());
      return;
    } 
    throw new IllegalArgumentException("Both inner and outer radii arrays must contain exactly 8 values");
  }
  
  public void drawLine(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawLine(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramPaint.getNativeInstance());
  }
  
  public void drawLines(float[] paramArrayOffloat, int paramInt1, int paramInt2, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawLines(this.mNativeCanvasWrapper, paramArrayOffloat, paramInt1, paramInt2, paramPaint.getNativeInstance());
  }
  
  public void drawLines(float[] paramArrayOffloat, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    drawLines(paramArrayOffloat, 0, paramArrayOffloat.length, paramPaint);
  }
  
  public void drawOval(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawOval(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramPaint.getNativeInstance());
  }
  
  public void drawOval(RectF paramRectF, Paint paramPaint) {
    if (paramRectF != null) {
      throwIfHasHwBitmapInSwMode(paramPaint);
      drawOval(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramPaint);
      return;
    } 
    throw null;
  }
  
  public void drawPaint(Paint paramPaint) {
    nDrawPaint(this.mNativeCanvasWrapper, paramPaint.getNativeInstance());
  }
  
  public void drawPatch(NinePatch paramNinePatch, Rect paramRect, Paint paramPaint) {
    long l;
    Bitmap bitmap = paramNinePatch.getBitmap();
    throwIfCannotDraw(bitmap);
    throwIfHasHwBitmapInSwMode(paramPaint);
    if (paramPaint == null) {
      l = 0L;
    } else {
      l = paramPaint.getNativeInstance();
    } 
    nDrawNinePatch(this.mNativeCanvasWrapper, bitmap.getNativeInstance(), paramNinePatch.mNativeChunk, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom, l, this.mDensity, paramNinePatch.getDensity());
  }
  
  public void drawPatch(NinePatch paramNinePatch, RectF paramRectF, Paint paramPaint) {
    long l;
    Bitmap bitmap = paramNinePatch.getBitmap();
    throwIfCannotDraw(bitmap);
    throwIfHasHwBitmapInSwMode(paramPaint);
    if (paramPaint == null) {
      l = 0L;
    } else {
      l = paramPaint.getNativeInstance();
    } 
    nDrawNinePatch(this.mNativeCanvasWrapper, bitmap.getNativeInstance(), paramNinePatch.mNativeChunk, paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, l, this.mDensity, paramNinePatch.getDensity());
  }
  
  public void drawPath(Path paramPath, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    if (paramPath.isSimplePath && paramPath.rects != null) {
      nDrawRegion(this.mNativeCanvasWrapper, paramPath.rects.mNativeRegion, paramPaint.getNativeInstance());
    } else {
      nDrawPath(this.mNativeCanvasWrapper, paramPath.readOnlyNI(), paramPaint.getNativeInstance());
    } 
  }
  
  public void drawPoint(float paramFloat1, float paramFloat2, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawPoint(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramPaint.getNativeInstance());
  }
  
  public void drawPoints(float[] paramArrayOffloat, int paramInt1, int paramInt2, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawPoints(this.mNativeCanvasWrapper, paramArrayOffloat, paramInt1, paramInt2, paramPaint.getNativeInstance());
  }
  
  public void drawPoints(float[] paramArrayOffloat, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    drawPoints(paramArrayOffloat, 0, paramArrayOffloat.length, paramPaint);
  }
  
  @Deprecated
  public void drawPosText(String paramString, float[] paramArrayOffloat, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    drawPosText(paramString.toCharArray(), 0, paramString.length(), paramArrayOffloat, paramPaint);
  }
  
  @Deprecated
  public void drawPosText(char[] paramArrayOfchar, int paramInt1, int paramInt2, float[] paramArrayOffloat, Paint paramPaint) {
    if (paramInt1 >= 0 && paramInt1 + paramInt2 <= paramArrayOfchar.length && paramInt2 * 2 <= paramArrayOffloat.length) {
      throwIfHasHwBitmapInSwMode(paramPaint);
      for (byte b = 0; b < paramInt2; b++)
        drawText(paramArrayOfchar, paramInt1 + b, 1, paramArrayOffloat[b * 2], paramArrayOffloat[b * 2 + 1], paramPaint); 
      return;
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public void drawRGB(int paramInt1, int paramInt2, int paramInt3) {
    drawColor(Color.rgb(paramInt1, paramInt2, paramInt3));
  }
  
  public void drawRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawRect(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramPaint.getNativeInstance());
  }
  
  public void drawRect(Rect paramRect, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    drawRect(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom, paramPaint);
  }
  
  public void drawRect(RectF paramRectF, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawRect(this.mNativeCanvasWrapper, paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramPaint.getNativeInstance());
  }
  
  public void drawRoundRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawRoundRect(this.mNativeCanvasWrapper, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramPaint.getNativeInstance());
  }
  
  public void drawRoundRect(RectF paramRectF, float paramFloat1, float paramFloat2, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    drawRoundRect(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom, paramFloat1, paramFloat2, paramPaint);
  }
  
  public void drawText(CharSequence paramCharSequence, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, Paint paramPaint) {
    if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramCharSequence.length() - paramInt2) >= 0) {
      throwIfHasHwBitmapInSwMode(paramPaint);
      if (paramCharSequence instanceof String || paramCharSequence instanceof android.text.SpannedString || paramCharSequence instanceof android.text.SpannableString) {
        nDrawText(this.mNativeCanvasWrapper, paramCharSequence.toString(), paramInt1, paramInt2, paramFloat1, paramFloat2, paramPaint.mBidiFlags, paramPaint.getNativeInstance());
        return;
      } 
      if (paramCharSequence instanceof GraphicsOperations) {
        ((GraphicsOperations)paramCharSequence).drawText(this, paramInt1, paramInt2, paramFloat1, paramFloat2, paramPaint);
      } else {
        char[] arrayOfChar = TemporaryBuffer.obtain(paramInt2 - paramInt1);
        TextUtils.getChars(paramCharSequence, paramInt1, paramInt2, arrayOfChar, 0);
        nDrawText(this.mNativeCanvasWrapper, arrayOfChar, 0, paramInt2 - paramInt1, paramFloat1, paramFloat2, paramPaint.mBidiFlags, paramPaint.getNativeInstance());
        TemporaryBuffer.recycle(arrayOfChar);
      } 
      return;
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public void drawText(String paramString, float paramFloat1, float paramFloat2, Paint paramPaint) {
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawText(this.mNativeCanvasWrapper, paramString, 0, paramString.length(), paramFloat1, paramFloat2, paramPaint.mBidiFlags, paramPaint.getNativeInstance());
  }
  
  public void drawText(String paramString, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, Paint paramPaint) {
    if ((paramInt1 | paramInt2 | paramInt2 - paramInt1 | paramString.length() - paramInt2) >= 0) {
      throwIfHasHwBitmapInSwMode(paramPaint);
      nDrawText(this.mNativeCanvasWrapper, paramString, paramInt1, paramInt2, paramFloat1, paramFloat2, paramPaint.mBidiFlags, paramPaint.getNativeInstance());
      return;
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public void drawText(char[] paramArrayOfchar, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, Paint paramPaint) {
    if ((paramInt1 | paramInt2 | paramInt1 + paramInt2 | paramArrayOfchar.length - paramInt1 - paramInt2) >= 0) {
      throwIfHasHwBitmapInSwMode(paramPaint);
      nDrawText(this.mNativeCanvasWrapper, paramArrayOfchar, paramInt1, paramInt2, paramFloat1, paramFloat2, paramPaint.mBidiFlags, paramPaint.getNativeInstance());
      return;
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public void drawTextOnPath(String paramString, Path paramPath, float paramFloat1, float paramFloat2, Paint paramPaint) {
    if (paramString.length() > 0) {
      throwIfHasHwBitmapInSwMode(paramPaint);
      nDrawTextOnPath(this.mNativeCanvasWrapper, paramString, paramPath.readOnlyNI(), paramFloat1, paramFloat2, paramPaint.mBidiFlags, paramPaint.getNativeInstance());
    } 
  }
  
  public void drawTextOnPath(char[] paramArrayOfchar, int paramInt1, int paramInt2, Path paramPath, float paramFloat1, float paramFloat2, Paint paramPaint) {
    if (paramInt1 >= 0 && paramInt1 + paramInt2 <= paramArrayOfchar.length) {
      throwIfHasHwBitmapInSwMode(paramPaint);
      nDrawTextOnPath(this.mNativeCanvasWrapper, paramArrayOfchar, paramInt1, paramInt2, paramPath.readOnlyNI(), paramFloat1, paramFloat2, paramPaint.mBidiFlags, paramPaint.getNativeInstance());
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public void drawTextRun(MeasuredText paramMeasuredText, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, boolean paramBoolean, Paint paramPaint) {
    nDrawTextRun(this.mNativeCanvasWrapper, paramMeasuredText.getChars(), paramInt1, paramInt2 - paramInt1, paramInt3, paramInt4 - paramInt3, paramFloat1, paramFloat2, paramBoolean, paramPaint.getNativeInstance(), paramMeasuredText.getNativePtr());
  }
  
  public void drawTextRun(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, boolean paramBoolean, Paint paramPaint) {
    if (paramCharSequence != null) {
      if (paramPaint != null) {
        if ((paramInt1 | paramInt2 | paramInt3 | paramInt4 | paramInt1 - paramInt3 | paramInt2 - paramInt1 | paramInt4 - paramInt2 | paramCharSequence.length() - paramInt4) >= 0) {
          throwIfHasHwBitmapInSwMode(paramPaint);
          if (paramCharSequence instanceof String || paramCharSequence instanceof android.text.SpannedString || paramCharSequence instanceof android.text.SpannableString) {
            nDrawTextRun(this.mNativeCanvasWrapper, paramCharSequence.toString(), paramInt1, paramInt2, paramInt3, paramInt4, paramFloat1, paramFloat2, paramBoolean, paramPaint.getNativeInstance());
            return;
          } 
          if (paramCharSequence instanceof GraphicsOperations) {
            ((GraphicsOperations)paramCharSequence).drawTextRun(this, paramInt1, paramInt2, paramInt3, paramInt4, paramFloat1, paramFloat2, paramBoolean, paramPaint);
          } else {
            if (paramCharSequence instanceof PrecomputedText) {
              PrecomputedText precomputedText = (PrecomputedText)paramCharSequence;
              int j = precomputedText.findParaIndex(paramInt1);
              if (paramInt2 <= precomputedText.getParagraphEnd(j)) {
                int k = precomputedText.getParagraphStart(j);
                drawTextRun(precomputedText.getMeasuredParagraph(j).getMeasuredText(), paramInt1 - k, paramInt2 - k, paramInt3 - k, paramInt4 - k, paramFloat1, paramFloat2, paramBoolean, paramPaint);
                return;
              } 
            } 
            int i = paramInt4 - paramInt3;
            char[] arrayOfChar = TemporaryBuffer.obtain(i);
            TextUtils.getChars(paramCharSequence, paramInt3, paramInt4, arrayOfChar, 0);
            nDrawTextRun(this.mNativeCanvasWrapper, arrayOfChar, paramInt1 - paramInt3, paramInt2 - paramInt1, 0, i, paramFloat1, paramFloat2, paramBoolean, paramPaint.getNativeInstance(), 0L);
            TemporaryBuffer.recycle(arrayOfChar);
          } 
          return;
        } 
        throw new IndexOutOfBoundsException();
      } 
      throw new NullPointerException("paint is null");
    } 
    throw new NullPointerException("text is null");
  }
  
  public void drawTextRun(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, boolean paramBoolean, Paint paramPaint) {
    if (paramArrayOfchar != null) {
      if (paramPaint != null) {
        if ((paramInt1 | paramInt2 | paramInt3 | paramInt4 | paramInt1 - paramInt3 | paramInt3 + paramInt4 - paramInt1 + paramInt2 | paramArrayOfchar.length - paramInt3 + paramInt4) >= 0) {
          throwIfHasHwBitmapInSwMode(paramPaint);
          nDrawTextRun(this.mNativeCanvasWrapper, paramArrayOfchar, paramInt1, paramInt2, paramInt3, paramInt4, paramFloat1, paramFloat2, paramBoolean, paramPaint.getNativeInstance(), 0L);
          return;
        } 
        throw new IndexOutOfBoundsException();
      } 
      throw new NullPointerException("paint is null");
    } 
    throw new NullPointerException("text is null");
  }
  
  public void drawVertices(Canvas.VertexMode paramVertexMode, int paramInt1, float[] paramArrayOffloat1, int paramInt2, float[] paramArrayOffloat2, int paramInt3, int[] paramArrayOfint, int paramInt4, short[] paramArrayOfshort, int paramInt5, int paramInt6, Paint paramPaint) {
    checkRange(paramArrayOffloat1.length, paramInt2, paramInt1);
    if (paramArrayOffloat2 != null)
      checkRange(paramArrayOffloat2.length, paramInt3, paramInt1); 
    if (paramArrayOfint != null)
      checkRange(paramArrayOfint.length, paramInt4, paramInt1 / 2); 
    if (paramArrayOfshort != null)
      checkRange(paramArrayOfshort.length, paramInt5, paramInt6); 
    throwIfHasHwBitmapInSwMode(paramPaint);
    nDrawVertices(this.mNativeCanvasWrapper, paramVertexMode.nativeInt, paramInt1, paramArrayOffloat1, paramInt2, paramArrayOffloat2, paramInt3, paramArrayOfint, paramInt4, paramArrayOfshort, paramInt5, paramInt6, paramPaint.getNativeInstance());
  }
  
  public boolean isHardwareAccelerated() {
    return false;
  }
  
  public boolean isHwBitmapsInSwModeEnabled() {
    return this.mAllowHwBitmapsInSwMode;
  }
  
  protected void onHwBitmapInSwMode() {
    if (this.mAllowHwBitmapsInSwMode)
      return; 
    throw new IllegalArgumentException("Software rendering doesn't support hardware bitmaps");
  }
  
  public void setHwBitmapsInSwModeEnabled(boolean paramBoolean) {
    this.mAllowHwBitmapsInSwMode = paramBoolean;
  }
  
  protected void throwIfCannotDraw(Bitmap paramBitmap) {
    if (!paramBitmap.isRecycled()) {
      if (paramBitmap.isPremultiplied() || paramBitmap.getConfig() != Bitmap.Config.ARGB_8888 || !paramBitmap.hasAlpha()) {
        throwIfHwBitmapInSwMode(paramBitmap);
        return;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Canvas: trying to use a non-premultiplied bitmap ");
      stringBuilder1.append(paramBitmap);
      throw new RuntimeException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Canvas: trying to use a recycled bitmap ");
    stringBuilder.append(paramBitmap);
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BaseCanvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
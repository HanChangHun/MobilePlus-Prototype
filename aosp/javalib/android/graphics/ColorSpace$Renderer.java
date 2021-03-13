package android.graphics;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Renderer {
  private static final int CHROMATICITY_RESOLUTION = 32;
  
  private static final int NATIVE_SIZE = 1440;
  
  private static final double ONE_THIRD = 0.3333333333333333D;
  
  private static final float[] SPECTRUM_LOCUS_X = new float[] { 
      0.175596F, 0.172787F, 0.170806F, 0.170085F, 0.160343F, 0.146958F, 0.139149F, 0.133536F, 0.126688F, 0.11583F, 
      0.109616F, 0.099146F, 0.09131F, 0.07813F, 0.068717F, 0.054675F, 0.040763F, 0.027497F, 0.01627F, 0.008169F, 
      0.004876F, 0.003983F, 0.003859F, 0.004646F, 0.007988F, 0.01387F, 0.022244F, 0.027273F, 0.03282F, 0.038851F, 
      0.045327F, 0.052175F, 0.059323F, 0.066713F, 0.074299F, 0.089937F, 0.114155F, 0.138695F, 0.154714F, 0.192865F, 
      0.229607F, 0.26576F, 0.301588F, 0.337346F, 0.373083F, 0.408717F, 0.444043F, 0.478755F, 0.512467F, 0.544767F, 
      0.575132F, 0.602914F, 0.627018F, 0.648215F, 0.665746F, 0.680061F, 0.691487F, 0.700589F, 0.707901F, 0.714015F, 
      0.719017F, 0.723016F, 0.734674F, 0.717203F, 0.699732F, 0.68226F, 0.664789F, 0.647318F, 0.629847F, 0.612376F, 
      0.594905F, 0.577433F, 0.559962F, 0.542491F, 0.52502F, 0.507549F, 0.490077F, 0.472606F, 0.455135F, 0.437664F, 
      0.420193F, 0.402721F, 0.38525F, 0.367779F, 0.350308F, 0.332837F, 0.315366F, 0.297894F, 0.280423F, 0.262952F, 
      0.245481F, 0.22801F, 0.210538F, 0.193067F, 0.175596F };
  
  private static final float[] SPECTRUM_LOCUS_Y = new float[] { 
      0.005295F, 0.0048F, 0.005472F, 0.005976F, 0.014496F, 0.026643F, 0.035211F, 0.042704F, 0.053441F, 0.073601F, 
      0.086866F, 0.112037F, 0.132737F, 0.170464F, 0.200773F, 0.254155F, 0.317049F, 0.387997F, 0.463035F, 0.538504F, 
      0.587196F, 0.610526F, 0.654897F, 0.67597F, 0.715407F, 0.750246F, 0.779682F, 0.792153F, 0.802971F, 0.812059F, 
      0.81943F, 0.8252F, 0.82946F, 0.832306F, 0.833833F, 0.833316F, 0.826231F, 0.814796F, 0.805884F, 0.781648F, 
      0.754347F, 0.724342F, 0.692326F, 0.658867F, 0.62447F, 0.589626F, 0.554734F, 0.520222F, 0.486611F, 0.454454F, 
      0.424252F, 0.396516F, 0.37251F, 0.351413F, 0.334028F, 0.319765F, 0.308359F, 0.299317F, 0.292044F, 0.285945F, 
      0.280951F, 0.276964F, 0.265326F, 0.2572F, 0.249074F, 0.240948F, 0.232822F, 0.224696F, 0.21657F, 0.208444F, 
      0.200318F, 0.192192F, 0.184066F, 0.17594F, 0.167814F, 0.159688F, 0.151562F, 0.143436F, 0.135311F, 0.127185F, 
      0.119059F, 0.110933F, 0.102807F, 0.094681F, 0.086555F, 0.078429F, 0.070303F, 0.062177F, 0.054051F, 0.045925F, 
      0.037799F, 0.029673F, 0.021547F, 0.013421F, 0.005295F };
  
  private static final float UCS_SCALE = 1.5F;
  
  private boolean mClip = false;
  
  private final List<Pair<ColorSpace, Integer>> mColorSpaces = new ArrayList<>(2);
  
  private final List<Point> mPoints = new ArrayList<>(0);
  
  private boolean mShowWhitePoint = true;
  
  private int mSize = 1024;
  
  private boolean mUcs = false;
  
  private Renderer() {}
  
  private static void computeChromaticityMesh(float[] paramArrayOffloat, int[] paramArrayOfint) {
    ColorSpace colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
    float[] arrayOfFloat = new float[3];
    int i = 0;
    int j = 0;
    int k = 0;
    while (true) {
      float[] arrayOfFloat1 = SPECTRUM_LOCUS_X;
      if (k < arrayOfFloat1.length) {
        int m = k % (arrayOfFloat1.length - 1) + 1;
        float f1 = (float)Math.atan2(SPECTRUM_LOCUS_Y[k] - 0.3333333333333333D, arrayOfFloat1[k] - 0.3333333333333333D);
        float f2 = (float)Math.atan2(SPECTRUM_LOCUS_Y[m] - 0.3333333333333333D, SPECTRUM_LOCUS_X[m] - 0.3333333333333333D);
        float f3 = (float)Math.pow(sqr(SPECTRUM_LOCUS_X[k] - 0.3333333333333333D) + sqr(SPECTRUM_LOCUS_Y[k] - 0.3333333333333333D), 0.5D);
        float f4 = (float)Math.pow(sqr(SPECTRUM_LOCUS_X[m] - 0.3333333333333333D) + sqr(SPECTRUM_LOCUS_Y[m] - 0.3333333333333333D), 0.5D);
        byte b = 1;
        int n = k;
        k = j;
        while (b <= 32) {
          float f5 = b / 32.0F;
          float f6 = (b - 1) / 32.0F;
          double d1 = f3 * Math.cos(f1);
          double d2 = f3 * Math.sin(f1);
          double d3 = f4 * Math.cos(f2);
          double d4 = f4 * Math.sin(f2);
          float f7 = (float)(f5 * d1 + 0.3333333333333333D);
          float f8 = (float)(f5 * d2 + 0.3333333333333333D);
          float f9 = (float)(f6 * d1 + 0.3333333333333333D);
          float f10 = (float)(f6 * d2 + 0.3333333333333333D);
          float f11 = (float)(f6 * d3 + 0.3333333333333333D);
          f6 = (float)(f6 * d4 + 0.3333333333333333D);
          float f12 = (float)(f5 * d3 + 0.3333333333333333D);
          f5 = (float)(f5 * d4 + 0.3333333333333333D);
          paramArrayOfint[k] = computeColor(arrayOfFloat, f7, f8, 1.0F - f7 - f8, colorSpace);
          paramArrayOfint[k + 1] = computeColor(arrayOfFloat, f9, f10, 1.0F - f9 - f10, colorSpace);
          paramArrayOfint[k + 2] = computeColor(arrayOfFloat, f11, f6, 1.0F - f11 - f6, colorSpace);
          paramArrayOfint[k + 3] = paramArrayOfint[k];
          paramArrayOfint[k + 4] = paramArrayOfint[k + 2];
          paramArrayOfint[k + 5] = computeColor(arrayOfFloat, f12, f5, 1.0F - f12 - f5, colorSpace);
          j = i + 1;
          paramArrayOffloat[i] = f7;
          i = j + 1;
          paramArrayOffloat[j] = f8;
          int i1 = i + 1;
          paramArrayOffloat[i] = f9;
          j = i1 + 1;
          paramArrayOffloat[i1] = f10;
          i = j + 1;
          paramArrayOffloat[j] = f11;
          j = i + 1;
          paramArrayOffloat[i] = f6;
          i1 = j + 1;
          paramArrayOffloat[j] = f7;
          i = i1 + 1;
          paramArrayOffloat[i1] = f8;
          i1 = i + 1;
          paramArrayOffloat[i] = f11;
          j = i1 + 1;
          paramArrayOffloat[i1] = f6;
          i = j + 1;
          paramArrayOffloat[j] = f12;
          paramArrayOffloat[i] = f5;
          b++;
          k += 6;
          i++;
        } 
        m = n + 1;
        j = k;
        k = m;
        continue;
      } 
      break;
    } 
  }
  
  private static int computeColor(float[] paramArrayOffloat, float paramFloat1, float paramFloat2, float paramFloat3, ColorSpace paramColorSpace) {
    paramArrayOffloat[0] = paramFloat1;
    paramArrayOffloat[1] = paramFloat2;
    paramArrayOffloat[2] = paramFloat3;
    paramColorSpace.fromXyz(paramArrayOffloat);
    return ((int)(paramArrayOffloat[0] * 255.0F) & 0xFF) << 16 | 0xFF000000 | ((int)(paramArrayOffloat[1] * 255.0F) & 0xFF) << 8 | (int)(paramArrayOffloat[2] * 255.0F) & 0xFF;
  }
  
  private void drawBox(Canvas paramCanvas, int paramInt1, int paramInt2, Paint paramPaint, Path paramPath) {
    byte b1;
    float f;
    if (this.mUcs) {
      b1 = 7;
      f = 1.5F;
    } else {
      b1 = 10;
      f = 1.0F;
    } 
    paramPaint.setStyle(Paint.Style.STROKE);
    paramPaint.setStrokeWidth(2.0F);
    paramPaint.setColor(-4144960);
    byte b2;
    for (b2 = 1; b2 < b1 - 1; b2++) {
      float f1 = b2 / 10.0F;
      float f2 = paramInt1 * f1 * f;
      f1 = paramInt2 - paramInt2 * f1 * f;
      paramCanvas.drawLine(0.0F, f1, paramInt1 * 0.9F, f1, paramPaint);
      paramCanvas.drawLine(f2, paramInt2, f2, paramInt2 * 0.1F, paramPaint);
    } 
    paramPaint.setStrokeWidth(4.0F);
    paramPaint.setColor(-16777216);
    for (b2 = 1; b2 < b1 - 1; b2++) {
      float f1 = b2 / 10.0F;
      float f2 = paramInt1 * f1 * f;
      f1 = paramInt2 - paramInt2 * f1 * f;
      paramCanvas.drawLine(0.0F, f1, paramInt1 / 100.0F, f1, paramPaint);
      paramCanvas.drawLine(f2, paramInt2, f2, paramInt2 - paramInt2 / 100.0F, paramPaint);
    } 
    paramPaint.setStyle(Paint.Style.FILL);
    paramPaint.setTextSize(36.0F);
    paramPaint.setTypeface(Typeface.create("sans-serif-light", 0));
    Rect rect = new Rect();
    for (b2 = 1; b2 < b1 - 1; b2++) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0.");
      stringBuilder.append(b2);
      String str = stringBuilder.toString();
      paramPaint.getTextBounds(str, 0, str.length(), rect);
      float f1 = b2 / 10.0F;
      float f3 = paramInt1;
      float f4 = paramInt2;
      float f2 = paramInt2;
      paramCanvas.drawText(str, paramInt1 * -0.05F + 10.0F, rect.height() / 2.0F + f4 - f2 * f1 * f, paramPaint);
      paramCanvas.drawText(str, f3 * f1 * f - rect.width() / 2.0F, (rect.height() + paramInt2 + 16), paramPaint);
    } 
    paramPaint.setStyle(Paint.Style.STROKE);
    paramPath.moveTo(0.0F, paramInt2);
    paramPath.lineTo(paramInt1 * 0.9F, paramInt2);
    paramPath.lineTo(paramInt1 * 0.9F, paramInt2 * 0.1F);
    paramPath.lineTo(0.0F, paramInt2 * 0.1F);
    paramPath.close();
    paramCanvas.drawPath(paramPath, paramPaint);
  }
  
  private void drawGamuts(Canvas paramCanvas, int paramInt1, int paramInt2, Paint paramPaint, Path paramPath, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    if (this.mUcs) {
      f = 1.5F;
    } else {
      f = 1.0F;
    } 
    float f = 4.0F / f;
    for (Pair<ColorSpace, Integer> pair : this.mColorSpaces) {
      ColorSpace colorSpace = (ColorSpace)pair.first;
      int i = ((Integer)pair.second).intValue();
      if (colorSpace.getModel() != ColorSpace.Model.RGB)
        continue; 
      ColorSpace.Rgb rgb = (ColorSpace.Rgb)colorSpace;
      getPrimaries(rgb, paramArrayOffloat1, this.mUcs);
      paramPath.rewind();
      paramPath.moveTo(paramInt1 * paramArrayOffloat1[0], paramInt2 - paramInt2 * paramArrayOffloat1[1]);
      paramPath.lineTo(paramInt1 * paramArrayOffloat1[2], paramInt2 - paramInt2 * paramArrayOffloat1[3]);
      paramPath.lineTo(paramInt1 * paramArrayOffloat1[4], paramInt2 - paramInt2 * paramArrayOffloat1[5]);
      paramPath.close();
      paramPaint.setStyle(Paint.Style.STROKE);
      paramPaint.setColor(i);
      paramCanvas.drawPath(paramPath, paramPaint);
      if (this.mShowWhitePoint) {
        rgb.getWhitePoint(paramArrayOffloat2);
        if (this.mUcs)
          ColorSpace.access$3000(paramArrayOffloat2); 
        paramPaint.setStyle(Paint.Style.FILL);
        paramPaint.setColor(i);
        paramCanvas.drawCircle(paramInt1 * paramArrayOffloat2[0], paramInt2 - paramInt2 * paramArrayOffloat2[1], f, paramPaint);
      } 
    } 
  }
  
  private void drawLocus(Canvas paramCanvas, int paramInt1, int paramInt2, Paint paramPaint, Path paramPath, float[] paramArrayOffloat) {
    float f;
    float[] arrayOfFloat = new float[SPECTRUM_LOCUS_X.length * 32 * 6 * 2];
    int[] arrayOfInt = new int[arrayOfFloat.length];
    computeChromaticityMesh(arrayOfFloat, arrayOfInt);
    if (this.mUcs)
      ColorSpace.access$3000(arrayOfFloat); 
    for (byte b = 0; b < arrayOfFloat.length; b += 2) {
      arrayOfFloat[b] = arrayOfFloat[b] * paramInt1;
      arrayOfFloat[b + 1] = paramInt2 - arrayOfFloat[b + 1] * paramInt2;
    } 
    if (this.mClip && this.mColorSpaces.size() > 0) {
      Iterator<Pair<ColorSpace, Integer>> iterator = this.mColorSpaces.iterator();
      while (iterator.hasNext()) {
        ColorSpace colorSpace = (ColorSpace)((Pair)iterator.next()).first;
        if (colorSpace.getModel() != ColorSpace.Model.RGB)
          continue; 
        getPrimaries((ColorSpace.Rgb)colorSpace, paramArrayOffloat, this.mUcs);
      } 
      paramPath.rewind();
      paramPath.moveTo(paramInt1 * paramArrayOffloat[0], paramInt2 - paramInt2 * paramArrayOffloat[1]);
      paramPath.lineTo(paramInt1 * paramArrayOffloat[2], paramInt2 - paramInt2 * paramArrayOffloat[3]);
      paramPath.lineTo(paramInt1 * paramArrayOffloat[4], paramInt2 - paramInt2 * paramArrayOffloat[5]);
      paramPath.close();
      int[] arrayOfInt1 = new int[arrayOfInt.length];
      Arrays.fill(arrayOfInt1, -9671572);
      paramCanvas.drawVertices(Canvas.VertexMode.TRIANGLES, arrayOfFloat.length, arrayOfFloat, 0, null, 0, arrayOfInt1, 0, null, 0, 0, paramPaint);
      paramCanvas.save();
      paramCanvas.clipPath(paramPath);
      paramCanvas.drawVertices(Canvas.VertexMode.TRIANGLES, arrayOfFloat.length, arrayOfFloat, 0, null, 0, arrayOfInt, 0, null, 0, 0, paramPaint);
      paramCanvas.restore();
    } else {
      paramCanvas.drawVertices(Canvas.VertexMode.TRIANGLES, arrayOfFloat.length, arrayOfFloat, 0, null, 0, arrayOfInt, 0, null, 0, 0, paramPaint);
    } 
    Path path = paramPath;
    paramInt2 = 372;
    paramPath.reset();
    path.moveTo(arrayOfFloat[372], arrayOfFloat[372 + 1]);
    for (paramInt1 = 2; paramInt1 < SPECTRUM_LOCUS_X.length; paramInt1++) {
      paramInt2 += 384;
      path.lineTo(arrayOfFloat[paramInt2], arrayOfFloat[paramInt2 + 1]);
    } 
    paramPath.close();
    if (this.mUcs) {
      f = 1.5F;
    } else {
      f = 1.0F;
    } 
    paramPaint.setStrokeWidth(4.0F / f);
    paramPaint.setStyle(Paint.Style.STROKE);
    paramPaint.setColor(-16777216);
    paramCanvas.drawPath(path, paramPaint);
  }
  
  private void drawPoints(Canvas paramCanvas, int paramInt1, int paramInt2, Paint paramPaint) {
    paramPaint.setStyle(Paint.Style.FILL);
    if (this.mUcs) {
      f = 1.5F;
    } else {
      f = 1.0F;
    } 
    float f = 4.0F / f;
    float[] arrayOfFloat1 = new float[3];
    float[] arrayOfFloat2 = new float[2];
    for (Point point : this.mPoints) {
      arrayOfFloat1[0] = point.mRgb[0];
      arrayOfFloat1[1] = point.mRgb[1];
      arrayOfFloat1[2] = point.mRgb[2];
      point.mColorSpace.toXyz(arrayOfFloat1);
      paramPaint.setColor(point.mColor);
      float f1 = arrayOfFloat1[0] + arrayOfFloat1[1] + arrayOfFloat1[2];
      arrayOfFloat2[0] = arrayOfFloat1[0] / f1;
      arrayOfFloat2[1] = arrayOfFloat1[1] / f1;
      if (this.mUcs)
        ColorSpace.access$3000(arrayOfFloat2); 
      paramCanvas.drawCircle(paramInt1 * arrayOfFloat2[0], paramInt2 - paramInt2 * arrayOfFloat2[1], f, paramPaint);
    } 
  }
  
  private static void getPrimaries(ColorSpace.Rgb paramRgb, float[] paramArrayOffloat, boolean paramBoolean) {
    if (paramRgb.equals(ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB)) || paramRgb.equals(ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB))) {
      paramArrayOffloat[0] = 1.41F;
      paramArrayOffloat[1] = 0.33F;
      paramArrayOffloat[2] = 0.27F;
      paramArrayOffloat[3] = 1.24F;
      paramArrayOffloat[4] = -0.23F;
      paramArrayOffloat[5] = -0.57F;
    } else {
      paramRgb.getPrimaries(paramArrayOffloat);
    } 
    if (paramBoolean)
      ColorSpace.access$3000(paramArrayOffloat); 
  }
  
  private void setTransform(Canvas paramCanvas, int paramInt1, int paramInt2, float[] paramArrayOffloat) {
    float f1;
    RectF rectF = new RectF();
    Iterator<Pair<ColorSpace, Integer>> iterator = this.mColorSpaces.iterator();
    while (iterator.hasNext()) {
      ColorSpace colorSpace = (ColorSpace)((Pair)iterator.next()).first;
      if (colorSpace.getModel() != ColorSpace.Model.RGB)
        continue; 
      getPrimaries((ColorSpace.Rgb)colorSpace, paramArrayOffloat, this.mUcs);
      rectF.left = Math.min(rectF.left, paramArrayOffloat[4]);
      rectF.top = Math.min(rectF.top, paramArrayOffloat[5]);
      rectF.right = Math.max(rectF.right, paramArrayOffloat[0]);
      rectF.bottom = Math.max(rectF.bottom, paramArrayOffloat[3]);
    } 
    if (this.mUcs) {
      f1 = 0.6F;
    } else {
      f1 = 0.9F;
    } 
    rectF.left = Math.min(0.0F, rectF.left);
    rectF.top = Math.min(0.0F, rectF.top);
    rectF.right = Math.max(f1, rectF.right);
    rectF.bottom = Math.max(f1, rectF.bottom);
    float f2 = Math.min(f1 / rectF.width(), f1 / rectF.height());
    int i = this.mSize;
    paramCanvas.scale(i / 1440.0F, i / 1440.0F);
    paramCanvas.scale(f2, f2);
    paramCanvas.translate((rectF.width() - f1) * paramInt1 / 2.0F, (rectF.height() - f1) * paramInt2 / 2.0F);
    paramCanvas.translate(paramInt1 * 0.05F, paramInt2 * -0.05F);
  }
  
  private void setUcsTransform(Canvas paramCanvas, int paramInt) {
    if (this.mUcs) {
      paramCanvas.translate(0.0F, paramInt - paramInt * 1.5F);
      paramCanvas.scale(1.5F, 1.5F);
    } 
  }
  
  private static double sqr(double paramDouble) {
    return paramDouble * paramDouble;
  }
  
  public Renderer add(ColorSpace paramColorSpace, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt) {
    this.mPoints.add(new Point(paramColorSpace, new float[] { paramFloat1, paramFloat2, paramFloat3 }, paramInt));
    return this;
  }
  
  public Renderer add(ColorSpace paramColorSpace, int paramInt) {
    this.mColorSpaces.add(new Pair(paramColorSpace, Integer.valueOf(paramInt)));
    return this;
  }
  
  public Renderer clip(boolean paramBoolean) {
    this.mClip = paramBoolean;
    return this;
  }
  
  public Bitmap render() {
    Paint paint = new Paint(1);
    int i = this.mSize;
    Bitmap bitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    float[] arrayOfFloat1 = new float[6];
    float[] arrayOfFloat2 = new float[2];
    Path path = new Path();
    setTransform(canvas, 1440, 1440, arrayOfFloat1);
    drawBox(canvas, 1440, 1440, paint, path);
    setUcsTransform(canvas, 1440);
    drawLocus(canvas, 1440, 1440, paint, path, arrayOfFloat1);
    drawGamuts(canvas, 1440, 1440, paint, path, arrayOfFloat1, arrayOfFloat2);
    drawPoints(canvas, 1440, 1440, paint);
    return bitmap;
  }
  
  public Renderer showWhitePoint(boolean paramBoolean) {
    this.mShowWhitePoint = paramBoolean;
    return this;
  }
  
  public Renderer size(int paramInt) {
    this.mSize = Math.max(128, paramInt);
    return this;
  }
  
  public Renderer uniformChromaticityScale(boolean paramBoolean) {
    this.mUcs = paramBoolean;
    return this;
  }
  
  private static class Point {
    final int mColor;
    
    final ColorSpace mColorSpace;
    
    final float[] mRgb;
    
    Point(ColorSpace param2ColorSpace, float[] param2ArrayOffloat, int param2Int) {
      this.mColorSpace = param2ColorSpace;
      this.mRgb = param2ArrayOffloat;
      this.mColor = param2Int;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Renderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
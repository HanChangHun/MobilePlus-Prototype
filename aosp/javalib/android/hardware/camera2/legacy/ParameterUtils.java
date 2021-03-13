package android.hardware.camera2.legacy;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.ParamsUtils;
import android.hardware.camera2.utils.SizeAreaComparator;
import android.util.Log;
import android.util.Size;
import android.util.SizeF;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ParameterUtils {
  private static final double ASPECT_RATIO_TOLERANCE = 0.05000000074505806D;
  
  public static final Camera.Area CAMERA_AREA_DEFAULT;
  
  private static final boolean DEBUG = false;
  
  public static final Rect NORMALIZED_RECTANGLE_DEFAULT = new Rect(-1000, -1000, 1000, 1000);
  
  public static final int NORMALIZED_RECTANGLE_MAX = 1000;
  
  public static final int NORMALIZED_RECTANGLE_MIN = -1000;
  
  public static final Rect RECTANGLE_EMPTY;
  
  private static final String TAG = "ParameterUtils";
  
  private static final int ZOOM_RATIO_MULTIPLIER = 100;
  
  static {
    CAMERA_AREA_DEFAULT = new Camera.Area(new Rect(NORMALIZED_RECTANGLE_DEFAULT), 1);
    RECTANGLE_EMPTY = new Rect(0, 0, 0, 0);
  }
  
  private ParameterUtils() {
    throw new AssertionError();
  }
  
  public static boolean containsSize(List<Camera.Size> paramList, int paramInt1, int paramInt2) {
    Preconditions.checkNotNull(paramList, "sizeList must not be null");
    for (Camera.Size size : paramList) {
      if (size.height == paramInt2 && size.width == paramInt1)
        return true; 
    } 
    return false;
  }
  
  public static WeightedRectangle convertCameraAreaToActiveArrayRectangle(Rect paramRect, ZoomData paramZoomData, Camera.Area paramArea) {
    return convertCameraAreaToActiveArrayRectangle(paramRect, paramZoomData, paramArea, true);
  }
  
  private static WeightedRectangle convertCameraAreaToActiveArrayRectangle(Rect paramRect, ZoomData paramZoomData, Camera.Area paramArea, boolean paramBoolean) {
    paramRect = paramZoomData.previewCrop;
    Rect rect = paramZoomData.reportedCrop;
    float f1 = paramRect.width() * 1.0F / 2000.0F;
    float f2 = paramRect.height() * 1.0F / 2000.0F;
    Matrix matrix = new Matrix();
    matrix.setTranslate(1000.0F, 1000.0F);
    matrix.postScale(f1, f2);
    matrix.postTranslate(paramRect.left, paramRect.top);
    if (!paramBoolean)
      paramRect = rect; 
    rect = ParamsUtils.mapRect(matrix, paramArea.rect);
    if (!rect.intersect(paramRect))
      rect.set(RECTANGLE_EMPTY); 
    if (paramArea.weight < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("convertCameraAreaToMeteringRectangle - rectangle ");
      stringBuilder.append(stringFromArea(paramArea));
      stringBuilder.append(" has too small weight, clip to 0");
      Log.w("ParameterUtils", stringBuilder.toString());
    } 
    return new WeightedRectangle(rect, paramArea.weight);
  }
  
  private static Point convertCameraPointToActiveArrayPoint(Rect paramRect, ZoomData paramZoomData, Point paramPoint, boolean paramBoolean) {
    Camera.Area area = new Camera.Area(new Rect(paramPoint.x, paramPoint.y, paramPoint.x, paramPoint.y), 1);
    WeightedRectangle weightedRectangle = convertCameraAreaToActiveArrayRectangle(paramRect, paramZoomData, area, paramBoolean);
    return new Point(weightedRectangle.rect.left, weightedRectangle.rect.top);
  }
  
  public static Face convertFaceFromLegacy(Camera.Face paramFace, Rect paramRect, ZoomData paramZoomData) {
    Face face;
    Preconditions.checkNotNull(paramFace, "face must not be null");
    Camera.Area area = new Camera.Area(paramFace.rect, 1);
    WeightedRectangle weightedRectangle = convertCameraAreaToActiveArrayRectangle(paramRect, paramZoomData, area);
    Point point1 = paramFace.leftEye;
    Point point2 = paramFace.rightEye;
    Point point3 = paramFace.mouth;
    if (point1 != null && point2 != null && point3 != null && point1.x != -2000 && point1.y != -2000 && point2.x != -2000 && point2.y != -2000 && point3.x != -2000 && point3.y != -2000) {
      point1 = convertCameraPointToActiveArrayPoint(paramRect, paramZoomData, point1, true);
      point3 = convertCameraPointToActiveArrayPoint(paramRect, paramZoomData, point1, true);
      Point point = convertCameraPointToActiveArrayPoint(paramRect, paramZoomData, point1, true);
      face = weightedRectangle.toFace(paramFace.id, point1, point3, point);
    } else {
      face = weightedRectangle.toFace();
    } 
    return face;
  }
  
  public static MeteringData convertMeteringRectangleToLegacy(Rect paramRect, MeteringRectangle paramMeteringRectangle, ZoomData paramZoomData) {
    Camera.Area area;
    Rect rect1 = paramZoomData.previewCrop;
    float f1 = 2000.0F / rect1.width();
    float f2 = 2000.0F / rect1.height();
    Matrix matrix = new Matrix();
    matrix.setTranslate(-rect1.left, -rect1.top);
    matrix.postScale(f1, f2);
    matrix.postTranslate(-1000.0F, -1000.0F);
    Rect rect3 = ParamsUtils.mapRect(matrix, paramMeteringRectangle.getRect());
    Rect rect2 = new Rect(rect3);
    if (!rect2.intersect(NORMALIZED_RECTANGLE_DEFAULT)) {
      Log.w("ParameterUtils", "convertMeteringRectangleToLegacy - metering rectangle too small, no metering will be done");
      rect2.set(RECTANGLE_EMPTY);
      area = new Camera.Area(RECTANGLE_EMPTY, 0);
    } else {
      area = new Camera.Area((Rect)area, paramMeteringRectangle.getMeteringWeight());
    } 
    Rect rect4 = paramMeteringRectangle.getRect();
    if (!rect4.intersect(rect1))
      rect4.set(RECTANGLE_EMPTY); 
    return new MeteringData(area, rect4, (convertCameraAreaToActiveArrayRectangle(paramRect, paramZoomData, new Camera.Area(rect3, paramMeteringRectangle.getMeteringWeight()), false)).rect);
  }
  
  public static ZoomData convertScalerCropRegion(Rect paramRect1, Rect paramRect2, Size paramSize, Camera.Parameters paramParameters) {
    Rect rect1 = new Rect(0, 0, paramRect1.width(), paramRect1.height());
    if (paramRect2 == null) {
      paramRect1 = rect1;
    } else {
      paramRect1 = paramRect2;
    } 
    paramRect2 = new Rect();
    Rect rect2 = new Rect();
    return new ZoomData(getClosestAvailableZoomCrop(paramParameters, rect1, paramSize, paramRect1, paramRect2, rect2), rect2, paramRect2, 1.0F);
  }
  
  public static Size convertSize(Camera.Size paramSize) {
    Preconditions.checkNotNull(paramSize, "size must not be null");
    return new Size(paramSize.width, paramSize.height);
  }
  
  public static List<Size> convertSizeList(List<Camera.Size> paramList) {
    Preconditions.checkNotNull(paramList, "sizeList must not be null");
    ArrayList<Size> arrayList = new ArrayList(paramList.size());
    for (Camera.Size size : paramList)
      arrayList.add(new Size(size.width, size.height)); 
    return arrayList;
  }
  
  public static Size[] convertSizeListToArray(List<Camera.Size> paramList) {
    Preconditions.checkNotNull(paramList, "sizeList must not be null");
    Size[] arrayOfSize = new Size[paramList.size()];
    byte b = 0;
    for (Camera.Size size : paramList) {
      arrayOfSize[b] = new Size(size.width, size.height);
      b++;
    } 
    return arrayOfSize;
  }
  
  public static ZoomData convertToLegacyZoom(Rect paramRect1, Rect paramRect2, Float paramFloat, Size paramSize, Camera.Parameters paramParameters) {
    return (paramFloat != null && Math.abs(1.0F - paramFloat.floatValue()) > 1.0E-4F) ? convertZoomRatio(paramRect1, paramFloat.floatValue(), paramSize, paramParameters) : convertScalerCropRegion(paramRect1, paramRect2, paramSize, paramParameters);
  }
  
  public static ZoomData convertZoomRatio(Rect paramRect, float paramFloat, Size paramSize, Camera.Parameters paramParameters) {
    List<Rect> list1 = getAvailableZoomCropRectangles(paramParameters, paramRect);
    List<Rect> list2 = getAvailablePreviewZoomCropRectangles(paramParameters, paramRect, paramSize);
    if (list1.size() == list2.size()) {
      byte b1 = 0;
      byte b2 = 0;
      paramRect = new Rect(list1.get(0));
      Rect rect1 = new Rect(list2.get(0));
      float f1 = 1.0F;
      Rect rect2 = paramRect;
      Rect rect3 = rect1;
      float f2 = f1;
      if (paramParameters.isZoomSupported()) {
        List<Integer> list = paramParameters.getZoomRatios();
        byte b = 1;
        while (true) {
          b1 = b2;
          rect2 = paramRect;
          rect3 = rect1;
          f2 = f1;
          if (b < list.size()) {
            b1 = b2;
            rect2 = paramRect;
            rect3 = rect1;
            f2 = f1;
            if (100.0F * paramFloat >= ((Integer)list.get(b)).intValue()) {
              b2 = b;
              paramRect = list1.get(b);
              rect1 = list2.get(b);
              f1 = ((Integer)list.get(b)).intValue();
              b++;
              continue;
            } 
          } 
          break;
        } 
      } 
      return new ZoomData(b1, rect2, rect3, f2);
    } 
    throw new AssertionError("available reported/preview crop region size mismatch");
  }
  
  private static List<Rect> getAvailableCropRectangles(Camera.Parameters paramParameters, Rect paramRect, Size paramSize) {
    Preconditions.checkNotNull(paramParameters, "params must not be null");
    Preconditions.checkNotNull(paramRect, "activeArray must not be null");
    Preconditions.checkNotNull(paramSize, "streamSize must not be null");
    Rect rect = getPreviewCropRectangleUnzoomed(paramRect, paramSize);
    if (!paramParameters.isZoomSupported())
      return new ArrayList<>(Arrays.asList(new Rect[] { rect })); 
    ArrayList<Rect> arrayList = new ArrayList(paramParameters.getMaxZoom() + 1);
    Matrix matrix = new Matrix();
    RectF rectF = new RectF();
    Iterator<Integer> iterator = paramParameters.getZoomRatios().iterator();
    while (iterator.hasNext()) {
      float f = 100.0F / ((Integer)iterator.next()).intValue();
      ParamsUtils.convertRectF(rect, rectF);
      matrix.setScale(f, f, paramRect.exactCenterX(), paramRect.exactCenterY());
      matrix.mapRect(rectF);
      arrayList.add(ParamsUtils.createRect(rectF));
    } 
    return arrayList;
  }
  
  public static List<Rect> getAvailablePreviewZoomCropRectangles(Camera.Parameters paramParameters, Rect paramRect, Size paramSize) {
    Preconditions.checkNotNull(paramParameters, "params must not be null");
    Preconditions.checkNotNull(paramRect, "activeArray must not be null");
    Preconditions.checkNotNull(paramSize, "previewSize must not be null");
    return getAvailableCropRectangles(paramParameters, paramRect, paramSize);
  }
  
  public static List<Rect> getAvailableZoomCropRectangles(Camera.Parameters paramParameters, Rect paramRect) {
    Preconditions.checkNotNull(paramParameters, "params must not be null");
    Preconditions.checkNotNull(paramRect, "activeArray must not be null");
    return getAvailableCropRectangles(paramParameters, paramRect, ParamsUtils.createSize(paramRect));
  }
  
  public static int getClosestAvailableZoomCrop(Camera.Parameters paramParameters, Rect paramRect1, Size paramSize, Rect paramRect2, Rect paramRect3, Rect paramRect4) {
    Preconditions.checkNotNull(paramParameters, "params must not be null");
    Preconditions.checkNotNull(paramRect1, "activeArray must not be null");
    Preconditions.checkNotNull(paramSize, "streamSize must not be null");
    Preconditions.checkNotNull(paramRect3, "reportedCropRegion must not be null");
    Preconditions.checkNotNull(paramRect4, "previewCropRegion must not be null");
    paramRect2 = new Rect(paramRect2);
    if (!paramRect2.intersect(paramRect1)) {
      Log.w("ParameterUtils", "getClosestAvailableZoomCrop - Crop region out of range; setting to active array size");
      paramRect2.set(paramRect1);
    } 
    Rect rect1 = getPreviewCropRectangleUnzoomed(paramRect1, paramSize);
    Rect rect2 = shrinkToSameAspectRatioCentered(rect1, paramRect2);
    rect1 = null;
    Rect rect3 = null;
    byte b = -1;
    List<Rect> list1 = getAvailableZoomCropRectangles(paramParameters, paramRect1);
    List<Rect> list2 = getAvailablePreviewZoomCropRectangles(paramParameters, paramRect1, paramSize);
    if (list1.size() == list2.size()) {
      byte b1 = 0;
      paramRect1 = rect3;
      Rect rect4 = rect1;
      Rect rect5 = paramRect2;
      while (b1 < list1.size()) {
        boolean bool;
        rect1 = list2.get(b1);
        paramRect2 = list1.get(b1);
        if (b == -1) {
          bool = true;
        } else if (rect1.width() >= rect2.width() && rect1.height() >= rect2.height()) {
          bool = true;
        } else {
          bool = false;
        } 
        if (bool) {
          paramRect1 = rect1;
          rect4 = paramRect2;
          b = b1;
          b1++;
        } 
      } 
      if (b != -1) {
        paramRect3.set(rect4);
        paramRect4.set(paramRect1);
        return b;
      } 
      throw new AssertionError("Should've found at least one valid zoom index");
    } 
    throw new AssertionError("available reported/preview crop region size mismatch");
  }
  
  public static Size getLargestSupportedJpegSizeByArea(Camera.Parameters paramParameters) {
    Preconditions.checkNotNull(paramParameters, "params must not be null");
    return SizeAreaComparator.findLargestByArea(convertSizeList(paramParameters.getSupportedPictureSizes()));
  }
  
  public static float getMaxZoomRatio(Camera.Parameters paramParameters) {
    if (!paramParameters.isZoomSupported())
      return 1.0F; 
    List<Integer> list = paramParameters.getZoomRatios();
    return ((Integer)list.get(list.size() - 1)).intValue() * 1.0F / 100.0F;
  }
  
  private static Rect getPreviewCropRectangleUnzoomed(Rect paramRect, Size paramSize) {
    if (paramSize.getWidth() <= paramRect.width()) {
      if (paramSize.getHeight() <= paramRect.height()) {
        float f3;
        float f1 = paramRect.width() * 1.0F / paramRect.height();
        float f2 = paramSize.getWidth() * 1.0F / paramSize.getHeight();
        if (Math.abs(f2 - f1) < 0.05000000074505806D) {
          f1 = paramRect.height();
          f3 = paramRect.width();
        } else if (f2 < f1) {
          f1 = paramRect.height();
          f3 = f1 * f2;
        } else {
          f3 = paramRect.width();
          f1 = f3 / f2;
        } 
        Matrix matrix = new Matrix();
        RectF rectF = new RectF(0.0F, 0.0F, f3, f1);
        matrix.setTranslate(paramRect.exactCenterX(), paramRect.exactCenterY());
        matrix.postTranslate(-rectF.centerX(), -rectF.centerY());
        matrix.mapRect(rectF);
        return ParamsUtils.createRect(rectF);
      } 
      throw new IllegalArgumentException("previewSize must not be taller than activeArray");
    } 
    throw new IllegalArgumentException("previewSize must not be wider than activeArray");
  }
  
  private static SizeF getZoomRatio(Size paramSize1, Size paramSize2) {
    Preconditions.checkNotNull(paramSize1, "activeArraySize must not be null");
    Preconditions.checkNotNull(paramSize2, "cropSize must not be null");
    Preconditions.checkArgumentPositive(paramSize2.getWidth(), "cropSize.width must be positive");
    Preconditions.checkArgumentPositive(paramSize2.getHeight(), "cropSize.height must be positive");
    return new SizeF(paramSize1.getWidth() * 1.0F / paramSize2.getWidth(), paramSize1.getHeight() * 1.0F / paramSize2.getHeight());
  }
  
  private static Rect shrinkToSameAspectRatioCentered(Rect paramRect1, Rect paramRect2) {
    float f3;
    float f1 = paramRect1.width() * 1.0F / paramRect1.height();
    float f2 = paramRect2.width() * 1.0F / paramRect2.height();
    if (f2 < f1) {
      f1 = paramRect1.height();
      f3 = f1 * f2;
    } else {
      f3 = paramRect1.width();
      f1 = f3 / f2;
    } 
    Matrix matrix = new Matrix();
    RectF rectF = new RectF(paramRect2);
    matrix.setScale(f3 / paramRect1.width(), f1 / paramRect1.height(), paramRect2.exactCenterX(), paramRect2.exactCenterY());
    matrix.mapRect(rectF);
    return ParamsUtils.createRect(rectF);
  }
  
  public static String stringFromArea(Camera.Area paramArea) {
    if (paramArea == null)
      return null; 
    StringBuilder stringBuilder = new StringBuilder();
    Rect rect = paramArea.rect;
    stringBuilder.setLength(0);
    stringBuilder.append("([");
    stringBuilder.append(rect.left);
    stringBuilder.append(',');
    stringBuilder.append(rect.top);
    stringBuilder.append("][");
    stringBuilder.append(rect.right);
    stringBuilder.append(',');
    stringBuilder.append(rect.bottom);
    stringBuilder.append(']');
    stringBuilder.append(',');
    stringBuilder.append(paramArea.weight);
    stringBuilder.append(')');
    return stringBuilder.toString();
  }
  
  public static String stringFromAreaList(List<Camera.Area> paramList) {
    StringBuilder stringBuilder = new StringBuilder();
    if (paramList == null)
      return null; 
    byte b = 0;
    for (Camera.Area area : paramList) {
      if (area == null) {
        stringBuilder.append("null");
      } else {
        stringBuilder.append(stringFromArea(area));
      } 
      if (b != paramList.size() - 1)
        stringBuilder.append(", "); 
      b++;
    } 
    return stringBuilder.toString();
  }
  
  public static class MeteringData {
    public final Camera.Area meteringArea;
    
    public final Rect previewMetering;
    
    public final Rect reportedMetering;
    
    public MeteringData(Camera.Area param1Area, Rect param1Rect1, Rect param1Rect2) {
      this.meteringArea = param1Area;
      this.previewMetering = param1Rect1;
      this.reportedMetering = param1Rect2;
    }
  }
  
  public static class WeightedRectangle {
    public final Rect rect;
    
    public final int weight;
    
    public WeightedRectangle(Rect param1Rect, int param1Int) {
      this.rect = (Rect)Preconditions.checkNotNull(param1Rect, "rect must not be null");
      this.weight = param1Int;
    }
    
    private static int clip(int param1Int1, int param1Int2, int param1Int3, Rect param1Rect, String param1String) {
      if (param1Int1 < param1Int2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("toMetering - Rectangle ");
        stringBuilder.append(param1Rect);
        stringBuilder.append(" ");
        stringBuilder.append(param1String);
        stringBuilder.append(" too small, clip to ");
        stringBuilder.append(param1Int2);
        Log.w("ParameterUtils", stringBuilder.toString());
      } else {
        param1Int2 = param1Int1;
        if (param1Int1 > param1Int3) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("toMetering - Rectangle ");
          stringBuilder.append(param1Rect);
          stringBuilder.append(" ");
          stringBuilder.append(param1String);
          stringBuilder.append(" too small, clip to ");
          stringBuilder.append(param1Int3);
          Log.w("ParameterUtils", stringBuilder.toString());
          param1Int2 = param1Int3;
        } 
      } 
      return param1Int2;
    }
    
    private static int clipLower(int param1Int1, int param1Int2, Rect param1Rect, String param1String) {
      return clip(param1Int1, param1Int2, 2147483647, param1Rect, param1String);
    }
    
    public Face toFace() {
      int i = clip(this.weight, 1, 100, this.rect, "score");
      return new Face(this.rect, i);
    }
    
    public Face toFace(int param1Int, Point param1Point1, Point param1Point2, Point param1Point3) {
      int i = clipLower(param1Int, 0, this.rect, "id");
      param1Int = clip(this.weight, 1, 100, this.rect, "score");
      return new Face(this.rect, param1Int, i, param1Point1, param1Point2, param1Point3);
    }
    
    public MeteringRectangle toMetering() {
      int i = clip(this.weight, 0, 1000, this.rect, "weight");
      return new MeteringRectangle(clipLower(this.rect.left, 0, this.rect, "left"), clipLower(this.rect.top, 0, this.rect, "top"), clipLower(this.rect.width(), 0, this.rect, "width"), clipLower(this.rect.height(), 0, this.rect, "height"), i);
    }
  }
  
  public static class ZoomData {
    public final Rect previewCrop;
    
    public final Rect reportedCrop;
    
    public final float reportedZoomRatio;
    
    public final int zoomIndex;
    
    public ZoomData(int param1Int, Rect param1Rect1, Rect param1Rect2, float param1Float) {
      this.zoomIndex = param1Int;
      this.previewCrop = param1Rect1;
      this.reportedCrop = param1Rect2;
      this.reportedZoomRatio = param1Float;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/ParameterUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
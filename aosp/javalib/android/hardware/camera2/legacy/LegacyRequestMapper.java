package android.hardware.camera2.legacy;

import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.ListUtils;
import android.hardware.camera2.utils.ParamsUtils;
import android.location.Location;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class LegacyRequestMapper {
  private static final boolean DEBUG = false;
  
  private static final byte DEFAULT_JPEG_QUALITY = 85;
  
  private static final String TAG = "LegacyRequestMapper";
  
  private static boolean checkForCompleteGpsData(Location paramLocation) {
    boolean bool;
    if (paramLocation != null && paramLocation.getProvider() != null && paramLocation.getTime() != 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static String convertAeAntiBandingModeToLegacy(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "auto") : "60hz") : "50hz") : "off";
  }
  
  private static int[] convertAeFpsRangeToLegacy(Range<Integer> paramRange) {
    return new int[] { ((Integer)paramRange.getLower()).intValue() * 1000, ((Integer)paramRange.getUpper()).intValue() * 1000 };
  }
  
  private static String convertAwbModeToLegacy(int paramInt) {
    StringBuilder stringBuilder;
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("convertAwbModeToLegacy - unrecognized control.awbMode");
        stringBuilder.append(paramInt);
        Log.w("LegacyRequestMapper", stringBuilder.toString());
        return "auto";
      case 8:
        return "shade";
      case 7:
        return "twilight";
      case 6:
        return "cloudy-daylight";
      case 5:
        return "daylight";
      case 4:
        return "warm-fluorescent";
      case 3:
        return "fluorescent";
      case 2:
        return "incandescent";
      case 1:
        break;
    } 
    return "auto";
  }
  
  private static List<Camera.Area> convertMeteringRegionsToLegacy(Rect paramRect, ParameterUtils.ZoomData paramZoomData, MeteringRectangle[] paramArrayOfMeteringRectangle, int paramInt, String paramString) {
    if (paramArrayOfMeteringRectangle == null || paramInt <= 0)
      return (paramInt > 0) ? Arrays.asList(new Camera.Area[] { ParameterUtils.CAMERA_AREA_DEFAULT }) : null; 
    ArrayList<MeteringRectangle> arrayList1 = new ArrayList();
    int i = paramArrayOfMeteringRectangle.length;
    byte b;
    for (b = 0; b < i; b++) {
      MeteringRectangle meteringRectangle = paramArrayOfMeteringRectangle[b];
      if (meteringRectangle.getMeteringWeight() != 0)
        arrayList1.add(meteringRectangle); 
    } 
    if (arrayList1.size() == 0) {
      Log.w("LegacyRequestMapper", "Only received metering rectangles with weight 0.");
      return Arrays.asList(new Camera.Area[] { ParameterUtils.CAMERA_AREA_DEFAULT });
    } 
    i = Math.min(paramInt, arrayList1.size());
    ArrayList<Camera.Area> arrayList = new ArrayList(i);
    for (b = 0; b < i; b++) {
      MeteringRectangle meteringRectangle = arrayList1.get(b);
      arrayList.add((ParameterUtils.convertMeteringRectangleToLegacy(paramRect, meteringRectangle, paramZoomData)).meteringArea);
    } 
    if (paramInt < arrayList1.size()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("convertMeteringRegionsToLegacy - Too many requested ");
      stringBuilder.append(paramString);
      stringBuilder.append(" regions, ignoring all beyond the first ");
      stringBuilder.append(paramInt);
      Log.w("LegacyRequestMapper", stringBuilder.toString());
    } 
    return arrayList;
  }
  
  public static void convertRequestMetadata(LegacyRequest paramLegacyRequest) {
    String str2;
    CameraCharacteristics cameraCharacteristics = paramLegacyRequest.characteristics;
    CaptureRequest captureRequest = paramLegacyRequest.captureRequest;
    Size size = paramLegacyRequest.previewSize;
    Camera.Parameters parameters = paramLegacyRequest.parameters;
    Rect rect = (Rect)cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
    ParameterUtils.ZoomData zoomData = ParameterUtils.convertToLegacyZoom(rect, (Rect)captureRequest.get(CaptureRequest.SCALER_CROP_REGION), (Float)captureRequest.get(CaptureRequest.CONTROL_ZOOM_RATIO), size, parameters);
    if (parameters.isZoomSupported())
      parameters.setZoom(zoomData.zoomIndex); 
    int i = ((Integer)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE, Integer.valueOf(1))).intValue();
    if (i != 1 && i != 2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("convertRequestToMetadata - Ignoring unsupported colorCorrection.aberrationMode = ");
      stringBuilder.append(i);
      Log.w("LegacyRequestMapper", stringBuilder.toString());
    } 
    Integer integer4 = (Integer)captureRequest.get(CaptureRequest.CONTROL_AE_ANTIBANDING_MODE);
    if (integer4 != null) {
      str2 = convertAeAntiBandingModeToLegacy(integer4.intValue());
    } else {
      str2 = (String)ListUtils.listSelectFirstFrom(parameters.getSupportedAntibanding(), (Object[])new String[] { "auto", "off", "50hz", "60hz" });
    } 
    if (str2 != null)
      parameters.setAntibanding(str2); 
    MeteringRectangle[] arrayOfMeteringRectangle2 = (MeteringRectangle[])captureRequest.get(CaptureRequest.CONTROL_AE_REGIONS);
    if (captureRequest.get(CaptureRequest.CONTROL_AWB_REGIONS) != null)
      Log.w("LegacyRequestMapper", "convertRequestMetadata - control.awbRegions setting is not supported, ignoring value"); 
    i = parameters.getMaxNumMeteringAreas();
    List<Camera.Area> list2 = convertMeteringRegionsToLegacy(rect, zoomData, arrayOfMeteringRectangle2, i, "AE");
    if (i > 0)
      parameters.setMeteringAreas(list2); 
    MeteringRectangle[] arrayOfMeteringRectangle1 = (MeteringRectangle[])captureRequest.get(CaptureRequest.CONTROL_AF_REGIONS);
    i = parameters.getMaxNumFocusAreas();
    List<Camera.Area> list1 = convertMeteringRegionsToLegacy(rect, zoomData, arrayOfMeteringRectangle1, i, "AF");
    if (i > 0)
      parameters.setFocusAreas(list1); 
    Range<Integer> range = (Range)captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
    if (range != null) {
      ParameterUtils.ZoomData zoomData1;
      int[] arrayOfInt = convertAeFpsRangeToLegacy(range);
      zoomData = null;
      Iterator<int[]> iterator = parameters.getSupportedPreviewFpsRange().iterator();
      while (true) {
        if (iterator.hasNext()) {
          int[] arrayOfInt2 = iterator.next();
          i = arrayOfInt2[0];
          int[] arrayOfInt1 = arrayOfInt;
          i = (int)Math.floor(i / 1000.0D);
          int k = (int)Math.ceil(arrayOfInt2[1] / 1000.0D);
          if (arrayOfInt1[0] == i * 1000 && arrayOfInt1[1] == k * 1000) {
            arrayOfInt1 = arrayOfInt2;
            break;
          } 
          arrayOfInt = arrayOfInt1;
          continue;
        } 
        zoomData1 = zoomData;
        break;
      } 
      if (zoomData1 != null) {
        parameters.setPreviewFpsRange(zoomData1[0], zoomData1[1]);
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported FPS range set [");
        stringBuilder.append(arrayOfInt[0]);
        stringBuilder.append(",");
        stringBuilder.append(arrayOfInt[1]);
        stringBuilder.append("]");
        Log.w("LegacyRequestMapper", stringBuilder.toString());
      } 
    } 
    range = (Range<Integer>)cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
    int j = ((Integer)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(0))).intValue();
    i = j;
    if (!range.contains(Integer.valueOf(j))) {
      Log.w("LegacyRequestMapper", "convertRequestMetadata - control.aeExposureCompensation is out of range, ignoring value");
      i = 0;
    } 
    parameters.setExposureCompensation(i);
    Boolean bool2 = getIfSupported(captureRequest, CaptureRequest.CONTROL_AE_LOCK, Boolean.valueOf(false), parameters.isAutoExposureLockSupported(), Boolean.valueOf(false));
    if (bool2 != null)
      parameters.setAutoExposureLock(bool2.booleanValue()); 
    mapAeAndFlashMode(captureRequest, parameters);
    i = ((Integer)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(0))).intValue();
    String str1 = LegacyMetadataMapper.convertAfModeToLegacy(i, parameters.getSupportedFocusModes());
    if (str1 != null)
      parameters.setFocusMode(str1); 
    CaptureRequest.Key<Integer> key = CaptureRequest.CONTROL_AWB_MODE;
    if (parameters.getSupportedWhiteBalance() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Integer integer3 = getIfSupported(captureRequest, key, Integer.valueOf(1), bool, Integer.valueOf(1));
    if (integer3 != null)
      parameters.setWhiteBalance(convertAwbModeToLegacy(integer3.intValue())); 
    Boolean bool1 = getIfSupported(captureRequest, CaptureRequest.CONTROL_AWB_LOCK, Boolean.valueOf(false), parameters.isAutoWhiteBalanceLockSupported(), Boolean.valueOf(false));
    if (bool1 != null)
      parameters.setAutoWhiteBalanceLock(bool1.booleanValue()); 
    i = filterSupportedCaptureIntent(((Integer)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(1))).intValue());
    if (i == 3 || i == 4) {
      bool = true;
    } else {
      bool = false;
    } 
    parameters.setRecordingHint(bool);
    Integer integer2 = getIfSupported(captureRequest, CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, Integer.valueOf(0), parameters.isVideoStabilizationSupported(), Integer.valueOf(0));
    if (integer2 != null) {
      if (integer2.intValue() == 1) {
        bool = true;
      } else {
        bool = false;
      } 
      parameters.setVideoStabilization(bool);
    } 
    boolean bool = ListUtils.listContains(parameters.getSupportedFocusModes(), "infinity");
    Float float_ = getIfSupported(captureRequest, CaptureRequest.LENS_FOCUS_DISTANCE, Float.valueOf(0.0F), bool, Float.valueOf(0.0F));
    if (float_ == null || float_.floatValue() != 0.0F) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("convertRequestToMetadata - Ignoring android.lens.focusDistance ");
      stringBuilder.append(bool);
      stringBuilder.append(", only 0.0f is supported");
      Log.w("LegacyRequestMapper", stringBuilder.toString());
    } 
    if (parameters.getSupportedSceneModes() != null) {
      String str;
      i = ((Integer)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_MODE, Integer.valueOf(1))).intValue();
      if (i != 1) {
        if (i != 2) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Control mode ");
          stringBuilder.append(i);
          stringBuilder.append(" is unsupported, defaulting to AUTO");
          Log.w("LegacyRequestMapper", stringBuilder.toString());
          str = "auto";
        } else {
          i = ((Integer)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_SCENE_MODE, Integer.valueOf(0))).intValue();
          str = LegacyMetadataMapper.convertSceneModeToLegacy(i);
          if (str == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Skipping unknown requested scene mode: ");
            stringBuilder.append(i);
            Log.w("LegacyRequestMapper", stringBuilder.toString());
            String str3 = "auto";
          } 
        } 
      } else {
        str = "auto";
      } 
      parameters.setSceneMode(str);
    } 
    if (parameters.getSupportedColorEffects() != null) {
      i = ((Integer)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_EFFECT_MODE, Integer.valueOf(0))).intValue();
      String str = LegacyMetadataMapper.convertEffectModeToLegacy(i);
      if (str != null) {
        parameters.setColorEffect(str);
      } else {
        parameters.setColorEffect("none");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Skipping unknown requested effect mode: ");
        stringBuilder.append(i);
        Log.w("LegacyRequestMapper", stringBuilder.toString());
      } 
    } 
    i = ((Integer)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.SENSOR_TEST_PATTERN_MODE, Integer.valueOf(0))).intValue();
    if (i != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("convertRequestToMetadata - ignoring sensor.testPatternMode ");
      stringBuilder.append(i);
      stringBuilder.append("; only OFF is supported");
      Log.w("LegacyRequestMapper", stringBuilder.toString());
    } 
    Location location = (Location)captureRequest.get(CaptureRequest.JPEG_GPS_LOCATION);
    if (location != null) {
      if (checkForCompleteGpsData(location)) {
        parameters.setGpsAltitude(location.getAltitude());
        parameters.setGpsLatitude(location.getLatitude());
        parameters.setGpsLongitude(location.getLongitude());
        parameters.setGpsProcessingMethod(location.getProvider().toUpperCase());
        parameters.setGpsTimestamp(location.getTime());
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Incomplete GPS parameters provided in location ");
        stringBuilder.append(location);
        Log.w("LegacyRequestMapper", stringBuilder.toString());
      } 
    } else {
      parameters.removeGpsData();
    } 
    Integer integer1 = (Integer)captureRequest.get(CaptureRequest.JPEG_ORIENTATION);
    CaptureRequest.Key key1 = CaptureRequest.JPEG_ORIENTATION;
    if (integer1 == null) {
      i = 0;
    } else {
      i = integer1.intValue();
    } 
    parameters.setRotation(((Integer)ParamsUtils.getOrDefault(captureRequest, key1, Integer.valueOf(i))).intValue());
    parameters.setJpegQuality(((Byte)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.JPEG_QUALITY, Byte.valueOf((byte)85))).byteValue() & 0xFF);
    parameters.setJpegThumbnailQuality(((Byte)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.JPEG_THUMBNAIL_QUALITY, Byte.valueOf((byte)85))).byteValue() & 0xFF);
    List<Camera.Size> list = parameters.getSupportedJpegThumbnailSizes();
    if (list != null && list.size() > 0) {
      Size size1 = (Size)captureRequest.get(CaptureRequest.JPEG_THUMBNAIL_SIZE);
      if (size1 != null && !ParameterUtils.containsSize(list, size1.getWidth(), size1.getHeight())) {
        i = 1;
      } else {
        i = 0;
      } 
      if (i != 0) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid JPEG thumbnail size set ");
        stringBuilder.append(size1);
        stringBuilder.append(", skipping thumbnail...");
        Log.w("LegacyRequestMapper", stringBuilder.toString());
      } 
      if (size1 == null || i != 0) {
        parameters.setJpegThumbnailSize(0, 0);
      } else {
        parameters.setJpegThumbnailSize(size1.getWidth(), size1.getHeight());
      } 
    } 
    i = ((Integer)ParamsUtils.getOrDefault(captureRequest, CaptureRequest.NOISE_REDUCTION_MODE, Integer.valueOf(1))).intValue();
    if (i != 1 && i != 2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("convertRequestToMetadata - Ignoring unsupported noiseReduction.mode = ");
      stringBuilder.append(i);
      Log.w("LegacyRequestMapper", stringBuilder.toString());
    } 
  }
  
  static int filterSupportedCaptureIntent(int paramInt) {
    switch (paramInt) {
      case 5:
      case 6:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported control.captureIntent value ");
        stringBuilder.append(1);
        stringBuilder.append("; default to PREVIEW");
        Log.w("LegacyRequestMapper", stringBuilder.toString());
        break;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
        return paramInt;
    } 
    paramInt = 1;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown control.captureIntent value ");
    stringBuilder.append(1);
    stringBuilder.append("; default to PREVIEW");
    Log.w("LegacyRequestMapper", stringBuilder.toString());
  }
  
  private static <T> T getIfSupported(CaptureRequest paramCaptureRequest, CaptureRequest.Key<T> paramKey, T paramT1, boolean paramBoolean, T paramT2) {
    Object object = ParamsUtils.getOrDefault(paramCaptureRequest, paramKey, paramT1);
    if (!paramBoolean) {
      if (!Objects.equals(object, paramT2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramKey.getName());
        stringBuilder.append(" is not supported; ignoring requested value ");
        stringBuilder.append(object);
        Log.w("LegacyRequestMapper", stringBuilder.toString());
      } 
      return null;
    } 
    return (T)object;
  }
  
  private static void mapAeAndFlashMode(CaptureRequest paramCaptureRequest, Camera.Parameters paramParameters) {
    String str1;
    int i = ((Integer)ParamsUtils.getOrDefault(paramCaptureRequest, CaptureRequest.FLASH_MODE, Integer.valueOf(0))).intValue();
    int j = ((Integer)ParamsUtils.getOrDefault(paramCaptureRequest, CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1))).intValue();
    List list = paramParameters.getSupportedFlashModes();
    String str2 = null;
    if (ListUtils.listContains(list, "off"))
      str2 = "off"; 
    if (j == 1) {
      if (i == 2) {
        if (ListUtils.listContains(list, "torch")) {
          str1 = "torch";
        } else {
          Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore flash.mode == TORCH;camera does not support it");
          str1 = str2;
        } 
      } else {
        str1 = str2;
        if (i == 1)
          if (ListUtils.listContains(list, "on")) {
            str1 = "on";
          } else {
            Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore flash.mode == SINGLE;camera does not support it");
            str1 = str2;
          }  
      } 
    } else if (j == 3) {
      if (ListUtils.listContains(list, "on")) {
        str1 = "on";
      } else {
        Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore control.aeMode == ON_ALWAYS_FLASH;camera does not support it");
        str1 = str2;
      } 
    } else if (j == 2) {
      if (ListUtils.listContains(list, "auto")) {
        str1 = "auto";
      } else {
        Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore control.aeMode == ON_AUTO_FLASH;camera does not support it");
        str1 = str2;
      } 
    } else {
      str1 = str2;
      if (j == 4)
        if (ListUtils.listContains(list, "red-eye")) {
          str1 = "red-eye";
        } else {
          Log.w("LegacyRequestMapper", "mapAeAndFlashMode - Ignore control.aeMode == ON_AUTO_FLASH_REDEYE;camera does not support it");
          str1 = str2;
        }  
    } 
    if (str1 != null)
      paramParameters.setFlashMode(str1); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyRequestMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
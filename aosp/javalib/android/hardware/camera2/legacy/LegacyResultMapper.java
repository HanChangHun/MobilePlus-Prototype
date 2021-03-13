package android.hardware.camera2.legacy;

import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.ParamsUtils;
import android.util.Log;
import android.util.Size;
import java.util.ArrayList;
import java.util.List;

public class LegacyResultMapper {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "LegacyResultMapper";
  
  private LegacyRequest mCachedRequest = null;
  
  private CameraMetadataNative mCachedResult = null;
  
  private static int convertLegacyAfMode(String paramString) {
    StringBuilder stringBuilder;
    if (paramString == null) {
      Log.w("LegacyResultMapper", "convertLegacyAfMode - no AF mode, default to OFF");
      return 0;
    } 
    byte b = -1;
    switch (paramString.hashCode()) {
      case 910005312:
        if (paramString.equals("continuous-picture"))
          b = 1; 
        break;
      case 173173288:
        if (paramString.equals("infinity"))
          b = 6; 
        break;
      case 103652300:
        if (paramString.equals("macro"))
          b = 4; 
        break;
      case 97445748:
        if (paramString.equals("fixed"))
          b = 5; 
        break;
      case 3108534:
        if (paramString.equals("edof"))
          b = 3; 
        break;
      case 3005871:
        if (paramString.equals("auto"))
          b = 0; 
        break;
      case -194628547:
        if (paramString.equals("continuous-video"))
          b = 2; 
        break;
    } 
    switch (b) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("convertLegacyAfMode - unknown mode ");
        stringBuilder.append(paramString);
        stringBuilder.append(" , ignoring");
        Log.w("LegacyResultMapper", stringBuilder.toString());
        return 0;
      case 6:
        return 0;
      case 5:
        return 0;
      case 4:
        return 2;
      case 3:
        return 5;
      case 2:
        return 3;
      case 1:
        return 4;
      case 0:
        break;
    } 
    return 1;
  }
  
  private static int convertLegacyAwbMode(String paramString) {
    StringBuilder stringBuilder;
    if (paramString == null)
      return 1; 
    byte b = -1;
    switch (paramString.hashCode()) {
      case 1942983418:
        if (paramString.equals("daylight"))
          b = 4; 
        break;
      case 1902580840:
        if (paramString.equals("fluorescent"))
          b = 2; 
        break;
      case 1650323088:
        if (paramString.equals("twilight"))
          b = 6; 
        break;
      case 474934723:
        if (paramString.equals("cloudy-daylight"))
          b = 5; 
        break;
      case 109399597:
        if (paramString.equals("shade"))
          b = 7; 
        break;
      case 3005871:
        if (paramString.equals("auto"))
          b = 0; 
        break;
      case -719316704:
        if (paramString.equals("warm-fluorescent"))
          b = 3; 
        break;
      case -939299377:
        if (paramString.equals("incandescent"))
          b = 1; 
        break;
    } 
    switch (b) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("convertAwbMode - unrecognized WB mode ");
        stringBuilder.append(paramString);
        Log.w("LegacyResultMapper", stringBuilder.toString());
        return 1;
      case 7:
        return 8;
      case 6:
        return 7;
      case 5:
        return 6;
      case 4:
        return 5;
      case 3:
        return 4;
      case 2:
        return 3;
      case 1:
        return 2;
      case 0:
        break;
    } 
    return 1;
  }
  
  private static CameraMetadataNative convertResultMetadata(LegacyRequest paramLegacyRequest) {
    CameraCharacteristics cameraCharacteristics = paramLegacyRequest.characteristics;
    CaptureRequest captureRequest = paramLegacyRequest.captureRequest;
    Size size1 = paramLegacyRequest.previewSize;
    Camera.Parameters parameters = paramLegacyRequest.parameters;
    CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
    Rect rect = (Rect)cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
    ParameterUtils.ZoomData zoomData = ParameterUtils.convertToLegacyZoom(rect, (Rect)captureRequest.get(CaptureRequest.SCALER_CROP_REGION), (Float)captureRequest.get(CaptureRequest.CONTROL_ZOOM_RATIO), size1, parameters);
    cameraMetadataNative.set(CaptureResult.COLOR_CORRECTION_ABERRATION_MODE, captureRequest.get(CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE));
    mapAe(cameraMetadataNative, cameraCharacteristics, captureRequest, rect, zoomData, parameters);
    mapAf(cameraMetadataNative, rect, zoomData, parameters);
    mapAwb(cameraMetadataNative, parameters);
    CaptureRequest.Key key = CaptureRequest.CONTROL_CAPTURE_INTENT;
    boolean bool = true;
    Integer integer = Integer.valueOf(1);
    int i = LegacyRequestMapper.filterSupportedCaptureIntent(((Integer)ParamsUtils.getOrDefault(captureRequest, key, integer)).intValue());
    cameraMetadataNative.set(CaptureResult.CONTROL_CAPTURE_INTENT, Integer.valueOf(i));
    key = CaptureRequest.CONTROL_MODE;
    if (((Integer)ParamsUtils.getOrDefault(captureRequest, key, integer)).intValue() == 2) {
      cameraMetadataNative.set(CaptureResult.CONTROL_MODE, Integer.valueOf(2));
    } else {
      cameraMetadataNative.set(CaptureResult.CONTROL_MODE, integer);
    } 
    String str = parameters.getSceneMode();
    i = LegacyMetadataMapper.convertSceneModeFromLegacy(str);
    if (i != -1) {
      cameraMetadataNative.set(CaptureResult.CONTROL_SCENE_MODE, Integer.valueOf(i));
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown scene mode ");
      stringBuilder.append(str);
      stringBuilder.append(" returned by camera HAL, setting to disabled.");
      Log.w("LegacyResultMapper", stringBuilder.toString());
      cameraMetadataNative.set(CaptureResult.CONTROL_SCENE_MODE, Integer.valueOf(0));
    } 
    str = parameters.getColorEffect();
    i = LegacyMetadataMapper.convertEffectModeFromLegacy(str);
    if (i != -1) {
      cameraMetadataNative.set(CaptureResult.CONTROL_EFFECT_MODE, Integer.valueOf(i));
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown effect mode ");
      stringBuilder.append(str);
      stringBuilder.append(" returned by camera HAL, setting to off.");
      Log.w("LegacyResultMapper", stringBuilder.toString());
      cameraMetadataNative.set(CaptureResult.CONTROL_EFFECT_MODE, Integer.valueOf(0));
    } 
    if (!parameters.isVideoStabilizationSupported() || !parameters.getVideoStabilization())
      bool = false; 
    cameraMetadataNative.set(CaptureResult.CONTROL_VIDEO_STABILIZATION_MODE, Integer.valueOf(bool));
    if ("infinity".equals(parameters.getFocusMode()))
      cameraMetadataNative.set(CaptureResult.LENS_FOCUS_DISTANCE, Float.valueOf(0.0F)); 
    cameraMetadataNative.set(CaptureResult.LENS_FOCAL_LENGTH, Float.valueOf(parameters.getFocalLength()));
    cameraMetadataNative.set(CaptureResult.REQUEST_PIPELINE_DEPTH, cameraCharacteristics.get(CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH));
    mapScaler(cameraMetadataNative, zoomData, parameters);
    cameraMetadataNative.set(CaptureResult.SENSOR_TEST_PATTERN_MODE, Integer.valueOf(0));
    cameraMetadataNative.set(CaptureResult.JPEG_GPS_LOCATION, captureRequest.get(CaptureRequest.JPEG_GPS_LOCATION));
    cameraMetadataNative.set(CaptureResult.JPEG_ORIENTATION, captureRequest.get(CaptureRequest.JPEG_ORIENTATION));
    cameraMetadataNative.set(CaptureResult.JPEG_QUALITY, Byte.valueOf((byte)parameters.getJpegQuality()));
    cameraMetadataNative.set(CaptureResult.JPEG_THUMBNAIL_QUALITY, Byte.valueOf((byte)parameters.getJpegThumbnailQuality()));
    Camera.Size size = parameters.getJpegThumbnailSize();
    if (size != null) {
      cameraMetadataNative.set(CaptureResult.JPEG_THUMBNAIL_SIZE, ParameterUtils.convertSize(size));
    } else {
      Log.w("LegacyResultMapper", "Null thumbnail size received from parameters.");
    } 
    cameraMetadataNative.set(CaptureResult.NOISE_REDUCTION_MODE, captureRequest.get(CaptureRequest.NOISE_REDUCTION_MODE));
    return cameraMetadataNative;
  }
  
  private static MeteringRectangle[] getMeteringRectangles(Rect paramRect, ParameterUtils.ZoomData paramZoomData, List<Camera.Area> paramList, String paramString) {
    ArrayList<MeteringRectangle> arrayList = new ArrayList();
    if (paramList != null)
      for (Camera.Area area : paramList)
        arrayList.add(ParameterUtils.convertCameraAreaToActiveArrayRectangle(paramRect, paramZoomData, area).toMetering());  
    return arrayList.<MeteringRectangle>toArray(new MeteringRectangle[0]);
  }
  
  private static void mapAe(CameraMetadataNative paramCameraMetadataNative, CameraCharacteristics paramCameraCharacteristics, CaptureRequest paramCaptureRequest, Rect paramRect, ParameterUtils.ZoomData paramZoomData, Camera.Parameters paramParameters) {
    boolean bool;
    int i = LegacyMetadataMapper.convertAntiBandingModeOrDefault(paramParameters.getAntibanding());
    paramCameraMetadataNative.set(CaptureResult.CONTROL_AE_ANTIBANDING_MODE, Integer.valueOf(i));
    paramCameraMetadataNative.set(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(paramParameters.getExposureCompensation()));
    if (paramParameters.isAutoExposureLockSupported()) {
      bool = paramParameters.getAutoExposureLock();
    } else {
      bool = false;
    } 
    paramCameraMetadataNative.set(CaptureResult.CONTROL_AE_LOCK, Boolean.valueOf(bool));
    Boolean bool1 = (Boolean)paramCaptureRequest.get(CaptureRequest.CONTROL_AE_LOCK);
    if (bool1 != null && bool1.booleanValue() != bool) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("mapAe - android.control.aeLock was requested to ");
      stringBuilder.append(bool1);
      stringBuilder.append(" but resulted in ");
      stringBuilder.append(bool);
      Log.w("LegacyResultMapper", stringBuilder.toString());
    } 
    mapAeAndFlashMode(paramCameraMetadataNative, paramCameraCharacteristics, paramParameters);
    if (paramParameters.getMaxNumMeteringAreas() > 0) {
      MeteringRectangle[] arrayOfMeteringRectangle = getMeteringRectangles(paramRect, paramZoomData, paramParameters.getMeteringAreas(), "AE");
      paramCameraMetadataNative.set(CaptureResult.CONTROL_AE_REGIONS, arrayOfMeteringRectangle);
    } 
  }
  
  private static void mapAeAndFlashMode(CameraMetadataNative paramCameraMetadataNative, CameraCharacteristics paramCameraCharacteristics, Camera.Parameters paramParameters) {
    Integer integer1;
    byte b1 = 0;
    boolean bool = ((Boolean)paramCameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)).booleanValue();
    byte b = 0;
    if (bool) {
      paramCameraCharacteristics = null;
    } else {
      integer1 = Integer.valueOf(0);
    } 
    byte b2 = 1;
    String str = paramParameters.getFlashMode();
    byte b3 = b1;
    Integer integer2 = integer1;
    byte b4 = b2;
    if (str != null) {
      switch (str.hashCode()) {
        default:
          b = -1;
          break;
        case 1081542389:
          if (str.equals("red-eye")) {
            b = 3;
            break;
          } 
        case 110547964:
          if (str.equals("torch")) {
            b = 4;
            break;
          } 
        case 3005871:
          if (str.equals("auto")) {
            b = 1;
            break;
          } 
        case 109935:
          if (str.equals("off"))
            break; 
        case 3551:
          if (str.equals("on")) {
            b = 2;
            break;
          } 
      } 
      b3 = b1;
      integer2 = integer1;
      b4 = b2;
      if (b != 0)
        if (b != 1) {
          if (b != 2) {
            if (b != 3) {
              if (b != 4) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("mapAeAndFlashMode - Ignoring unknown flash mode ");
                stringBuilder.append(paramParameters.getFlashMode());
                Log.w("LegacyResultMapper", stringBuilder.toString());
                b3 = b1;
                Integer integer = integer1;
                b4 = b2;
              } else {
                b3 = 2;
                integer2 = Integer.valueOf(3);
                b4 = b2;
              } 
            } else {
              b4 = 4;
              b3 = b1;
              integer2 = integer1;
            } 
          } else {
            b3 = 1;
            b4 = 3;
            integer2 = Integer.valueOf(3);
          } 
        } else {
          b4 = 2;
          integer2 = integer1;
          b3 = b1;
        }  
    } 
    paramCameraMetadataNative.set(CaptureResult.FLASH_STATE, integer2);
    paramCameraMetadataNative.set(CaptureResult.FLASH_MODE, Integer.valueOf(b3));
    paramCameraMetadataNative.set(CaptureResult.CONTROL_AE_MODE, Integer.valueOf(b4));
  }
  
  private static void mapAf(CameraMetadataNative paramCameraMetadataNative, Rect paramRect, ParameterUtils.ZoomData paramZoomData, Camera.Parameters paramParameters) {
    paramCameraMetadataNative.set(CaptureResult.CONTROL_AF_MODE, Integer.valueOf(convertLegacyAfMode(paramParameters.getFocusMode())));
    if (paramParameters.getMaxNumFocusAreas() > 0) {
      MeteringRectangle[] arrayOfMeteringRectangle = getMeteringRectangles(paramRect, paramZoomData, paramParameters.getFocusAreas(), "AF");
      paramCameraMetadataNative.set(CaptureResult.CONTROL_AF_REGIONS, arrayOfMeteringRectangle);
    } 
  }
  
  private static void mapAwb(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    boolean bool;
    if (paramParameters.isAutoWhiteBalanceLockSupported()) {
      bool = paramParameters.getAutoWhiteBalanceLock();
    } else {
      bool = false;
    } 
    paramCameraMetadataNative.set(CaptureResult.CONTROL_AWB_LOCK, Boolean.valueOf(bool));
    int i = convertLegacyAwbMode(paramParameters.getWhiteBalance());
    paramCameraMetadataNative.set(CaptureResult.CONTROL_AWB_MODE, Integer.valueOf(i));
  }
  
  private static void mapScaler(CameraMetadataNative paramCameraMetadataNative, ParameterUtils.ZoomData paramZoomData, Camera.Parameters paramParameters) {
    paramCameraMetadataNative.set(CaptureResult.SCALER_CROP_REGION, paramZoomData.reportedCrop);
    paramCameraMetadataNative.set(CaptureResult.CONTROL_ZOOM_RATIO, Float.valueOf(paramZoomData.reportedZoomRatio));
  }
  
  public CameraMetadataNative cachedConvertResultMetadata(LegacyRequest paramLegacyRequest, long paramLong) {
    CameraMetadataNative cameraMetadataNative;
    if (this.mCachedRequest != null && paramLegacyRequest.parameters.same(this.mCachedRequest.parameters) && paramLegacyRequest.captureRequest.equals(this.mCachedRequest.captureRequest)) {
      cameraMetadataNative = new CameraMetadataNative(this.mCachedResult);
    } else {
      CameraMetadataNative cameraMetadataNative1 = convertResultMetadata((LegacyRequest)cameraMetadataNative);
      this.mCachedRequest = (LegacyRequest)cameraMetadataNative;
      this.mCachedResult = new CameraMetadataNative(cameraMetadataNative1);
      cameraMetadataNative = cameraMetadataNative1;
    } 
    cameraMetadataNative.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(paramLong));
    return cameraMetadataNative;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyResultMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
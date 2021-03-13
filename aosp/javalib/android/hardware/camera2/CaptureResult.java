package android.hardware.camera2;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.impl.PublicKey;
import android.hardware.camera2.impl.SyntheticKey;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.LensShadingMap;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.OisSample;
import android.hardware.camera2.params.RggbChannelVector;
import android.hardware.camera2.params.TonemapCurve;
import android.hardware.camera2.utils.TypeReference;
import android.location.Location;
import android.util.Pair;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import java.util.List;

public class CaptureResult extends CameraMetadata<CaptureResult.Key<?>> {
  @PublicKey
  public static final Key<Boolean> BLACK_LEVEL_LOCK;
  
  @PublicKey
  public static final Key<Integer> COLOR_CORRECTION_ABERRATION_MODE;
  
  @PublicKey
  public static final Key<RggbChannelVector> COLOR_CORRECTION_GAINS;
  
  @PublicKey
  public static final Key<Integer> COLOR_CORRECTION_MODE = new Key<>("android.colorCorrection.mode", (Class)int.class);
  
  @PublicKey
  public static final Key<ColorSpaceTransform> COLOR_CORRECTION_TRANSFORM = new Key<>("android.colorCorrection.transform", ColorSpaceTransform.class);
  
  @PublicKey
  public static final Key<Integer> CONTROL_AE_ANTIBANDING_MODE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AE_EXPOSURE_COMPENSATION;
  
  @PublicKey
  public static final Key<Boolean> CONTROL_AE_LOCK;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AE_MODE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AE_PRECAPTURE_TRIGGER;
  
  @PublicKey
  public static final Key<MeteringRectangle[]> CONTROL_AE_REGIONS;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AE_STATE;
  
  @PublicKey
  public static final Key<Range<Integer>> CONTROL_AE_TARGET_FPS_RANGE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AF_MODE;
  
  @PublicKey
  public static final Key<MeteringRectangle[]> CONTROL_AF_REGIONS;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AF_SCENE_CHANGE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AF_STATE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AF_TRIGGER;
  
  @PublicKey
  public static final Key<Boolean> CONTROL_AWB_LOCK;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AWB_MODE;
  
  @PublicKey
  public static final Key<MeteringRectangle[]> CONTROL_AWB_REGIONS;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AWB_STATE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_CAPTURE_INTENT;
  
  @PublicKey
  public static final Key<Integer> CONTROL_EFFECT_MODE;
  
  @PublicKey
  public static final Key<Boolean> CONTROL_ENABLE_ZSL;
  
  @PublicKey
  public static final Key<Integer> CONTROL_EXTENDED_SCENE_MODE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_MODE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_POST_RAW_SENSITIVITY_BOOST;
  
  @PublicKey
  public static final Key<Integer> CONTROL_SCENE_MODE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_VIDEO_STABILIZATION_MODE;
  
  @PublicKey
  public static final Key<Float> CONTROL_ZOOM_RATIO;
  
  @PublicKey
  public static final Key<Integer> DISTORTION_CORRECTION_MODE;
  
  @PublicKey
  public static final Key<Integer> EDGE_MODE;
  
  @PublicKey
  public static final Key<Integer> FLASH_MODE;
  
  @PublicKey
  public static final Key<Integer> FLASH_STATE;
  
  @PublicKey
  public static final Key<Integer> HOT_PIXEL_MODE;
  
  public static final Key<double[]> JPEG_GPS_COORDINATES;
  
  @PublicKey
  @SyntheticKey
  public static final Key<Location> JPEG_GPS_LOCATION;
  
  public static final Key<String> JPEG_GPS_PROCESSING_METHOD;
  
  public static final Key<Long> JPEG_GPS_TIMESTAMP;
  
  @PublicKey
  public static final Key<Integer> JPEG_ORIENTATION;
  
  @PublicKey
  public static final Key<Byte> JPEG_QUALITY;
  
  @PublicKey
  public static final Key<Byte> JPEG_THUMBNAIL_QUALITY;
  
  @PublicKey
  public static final Key<Size> JPEG_THUMBNAIL_SIZE;
  
  public static final Key<Boolean> LED_TRANSMIT;
  
  @PublicKey
  public static final Key<Float> LENS_APERTURE;
  
  @PublicKey
  public static final Key<float[]> LENS_DISTORTION;
  
  @PublicKey
  public static final Key<Float> LENS_FILTER_DENSITY;
  
  @PublicKey
  public static final Key<Float> LENS_FOCAL_LENGTH;
  
  @PublicKey
  public static final Key<Float> LENS_FOCUS_DISTANCE;
  
  @PublicKey
  public static final Key<Pair<Float, Float>> LENS_FOCUS_RANGE;
  
  @PublicKey
  public static final Key<float[]> LENS_INTRINSIC_CALIBRATION;
  
  @PublicKey
  public static final Key<Integer> LENS_OPTICAL_STABILIZATION_MODE;
  
  @PublicKey
  public static final Key<float[]> LENS_POSE_ROTATION;
  
  @PublicKey
  public static final Key<float[]> LENS_POSE_TRANSLATION;
  
  @PublicKey
  @Deprecated
  public static final Key<float[]> LENS_RADIAL_DISTORTION;
  
  @PublicKey
  public static final Key<Integer> LENS_STATE;
  
  @PublicKey
  public static final Key<String> LOGICAL_MULTI_CAMERA_ACTIVE_PHYSICAL_ID;
  
  @PublicKey
  public static final Key<Integer> NOISE_REDUCTION_MODE;
  
  @Deprecated
  public static final Key<Boolean> QUIRKS_PARTIAL_RESULT;
  
  @PublicKey
  public static final Key<Float> REPROCESS_EFFECTIVE_EXPOSURE_FACTOR;
  
  @Deprecated
  public static final Key<Integer> REQUEST_FRAME_COUNT;
  
  public static final Key<Integer> REQUEST_ID;
  
  @PublicKey
  public static final Key<Byte> REQUEST_PIPELINE_DEPTH;
  
  @PublicKey
  public static final Key<Rect> SCALER_CROP_REGION;
  
  public static final Key<Integer> SCALER_ROTATE_AND_CROP;
  
  @PublicKey
  public static final Key<float[]> SENSOR_DYNAMIC_BLACK_LEVEL;
  
  @PublicKey
  public static final Key<Integer> SENSOR_DYNAMIC_WHITE_LEVEL;
  
  @PublicKey
  public static final Key<Long> SENSOR_EXPOSURE_TIME;
  
  @PublicKey
  public static final Key<Long> SENSOR_FRAME_DURATION;
  
  @PublicKey
  public static final Key<Float> SENSOR_GREEN_SPLIT;
  
  @PublicKey
  public static final Key<Rational[]> SENSOR_NEUTRAL_COLOR_POINT;
  
  @PublicKey
  public static final Key<Pair<Double, Double>[]> SENSOR_NOISE_PROFILE;
  
  @PublicKey
  public static final Key<Long> SENSOR_ROLLING_SHUTTER_SKEW;
  
  @PublicKey
  public static final Key<Integer> SENSOR_SENSITIVITY;
  
  @PublicKey
  public static final Key<int[]> SENSOR_TEST_PATTERN_DATA;
  
  @PublicKey
  public static final Key<Integer> SENSOR_TEST_PATTERN_MODE;
  
  @PublicKey
  public static final Key<Long> SENSOR_TIMESTAMP;
  
  @PublicKey
  public static final Key<Integer> SHADING_MODE;
  
  @PublicKey
  @SyntheticKey
  public static final Key<Face[]> STATISTICS_FACES;
  
  @PublicKey
  public static final Key<Integer> STATISTICS_FACE_DETECT_MODE;
  
  public static final Key<int[]> STATISTICS_FACE_IDS;
  
  public static final Key<int[]> STATISTICS_FACE_LANDMARKS;
  
  public static final Key<Rect[]> STATISTICS_FACE_RECTANGLES;
  
  public static final Key<byte[]> STATISTICS_FACE_SCORES;
  
  @PublicKey
  public static final Key<Point[]> STATISTICS_HOT_PIXEL_MAP;
  
  @PublicKey
  public static final Key<Boolean> STATISTICS_HOT_PIXEL_MAP_MODE;
  
  @PublicKey
  public static final Key<LensShadingMap> STATISTICS_LENS_SHADING_CORRECTION_MAP;
  
  public static final Key<float[]> STATISTICS_LENS_SHADING_MAP;
  
  @PublicKey
  public static final Key<Integer> STATISTICS_LENS_SHADING_MAP_MODE;
  
  @PublicKey
  public static final Key<Integer> STATISTICS_OIS_DATA_MODE;
  
  @PublicKey
  @SyntheticKey
  public static final Key<OisSample[]> STATISTICS_OIS_SAMPLES;
  
  public static final Key<long[]> STATISTICS_OIS_TIMESTAMPS;
  
  public static final Key<float[]> STATISTICS_OIS_X_SHIFTS;
  
  public static final Key<float[]> STATISTICS_OIS_Y_SHIFTS;
  
  @Deprecated
  public static final Key<float[]> STATISTICS_PREDICTED_COLOR_GAINS;
  
  @Deprecated
  public static final Key<Rational[]> STATISTICS_PREDICTED_COLOR_TRANSFORM;
  
  @PublicKey
  public static final Key<Integer> STATISTICS_SCENE_FLICKER;
  
  public static final Key<Long> SYNC_FRAME_NUMBER;
  
  private static final String TAG = "CaptureResult";
  
  @PublicKey
  @SyntheticKey
  public static final Key<TonemapCurve> TONEMAP_CURVE;
  
  public static final Key<float[]> TONEMAP_CURVE_BLUE;
  
  public static final Key<float[]> TONEMAP_CURVE_GREEN;
  
  public static final Key<float[]> TONEMAP_CURVE_RED;
  
  @PublicKey
  public static final Key<Float> TONEMAP_GAMMA;
  
  @PublicKey
  public static final Key<Integer> TONEMAP_MODE;
  
  @PublicKey
  public static final Key<Integer> TONEMAP_PRESET_CURVE;
  
  private static final boolean VERBOSE = false;
  
  private final long mFrameNumber;
  
  private final CaptureRequest mRequest;
  
  private final CameraMetadataNative mResults;
  
  private final int mSequenceId;
  
  static {
    COLOR_CORRECTION_GAINS = new Key<>("android.colorCorrection.gains", RggbChannelVector.class);
    COLOR_CORRECTION_ABERRATION_MODE = new Key<>("android.colorCorrection.aberrationMode", (Class)int.class);
    CONTROL_AE_ANTIBANDING_MODE = new Key<>("android.control.aeAntibandingMode", (Class)int.class);
    CONTROL_AE_EXPOSURE_COMPENSATION = new Key<>("android.control.aeExposureCompensation", (Class)int.class);
    CONTROL_AE_LOCK = new Key<>("android.control.aeLock", (Class)boolean.class);
    CONTROL_AE_MODE = new Key<>("android.control.aeMode", (Class)int.class);
    CONTROL_AE_REGIONS = (Key)new Key<>("android.control.aeRegions", (Class)MeteringRectangle[].class);
    CONTROL_AE_TARGET_FPS_RANGE = new Key<>("android.control.aeTargetFpsRange", new TypeReference<Range<Integer>>() {
        
        });
    CONTROL_AE_PRECAPTURE_TRIGGER = new Key<>("android.control.aePrecaptureTrigger", (Class)int.class);
    CONTROL_AE_STATE = new Key<>("android.control.aeState", (Class)int.class);
    CONTROL_AF_MODE = new Key<>("android.control.afMode", (Class)int.class);
    CONTROL_AF_REGIONS = (Key)new Key<>("android.control.afRegions", (Class)MeteringRectangle[].class);
    CONTROL_AF_TRIGGER = new Key<>("android.control.afTrigger", (Class)int.class);
    CONTROL_AF_STATE = new Key<>("android.control.afState", (Class)int.class);
    CONTROL_AWB_LOCK = new Key<>("android.control.awbLock", (Class)boolean.class);
    CONTROL_AWB_MODE = new Key<>("android.control.awbMode", (Class)int.class);
    CONTROL_AWB_REGIONS = (Key)new Key<>("android.control.awbRegions", (Class)MeteringRectangle[].class);
    CONTROL_CAPTURE_INTENT = new Key<>("android.control.captureIntent", (Class)int.class);
    CONTROL_AWB_STATE = new Key<>("android.control.awbState", (Class)int.class);
    CONTROL_EFFECT_MODE = new Key<>("android.control.effectMode", (Class)int.class);
    CONTROL_MODE = new Key<>("android.control.mode", (Class)int.class);
    CONTROL_SCENE_MODE = new Key<>("android.control.sceneMode", (Class)int.class);
    CONTROL_VIDEO_STABILIZATION_MODE = new Key<>("android.control.videoStabilizationMode", (Class)int.class);
    CONTROL_POST_RAW_SENSITIVITY_BOOST = new Key<>("android.control.postRawSensitivityBoost", (Class)int.class);
    CONTROL_ENABLE_ZSL = new Key<>("android.control.enableZsl", (Class)boolean.class);
    CONTROL_AF_SCENE_CHANGE = new Key<>("android.control.afSceneChange", (Class)int.class);
    CONTROL_EXTENDED_SCENE_MODE = new Key<>("android.control.extendedSceneMode", (Class)int.class);
    CONTROL_ZOOM_RATIO = new Key<>("android.control.zoomRatio", (Class)float.class);
    EDGE_MODE = new Key<>("android.edge.mode", (Class)int.class);
    FLASH_MODE = new Key<>("android.flash.mode", (Class)int.class);
    FLASH_STATE = new Key<>("android.flash.state", (Class)int.class);
    HOT_PIXEL_MODE = new Key<>("android.hotPixel.mode", (Class)int.class);
    JPEG_GPS_LOCATION = new Key<>("android.jpeg.gpsLocation", Location.class);
    JPEG_GPS_COORDINATES = (Key)new Key<>("android.jpeg.gpsCoordinates", (Class)double[].class);
    JPEG_GPS_PROCESSING_METHOD = new Key<>("android.jpeg.gpsProcessingMethod", String.class);
    JPEG_GPS_TIMESTAMP = new Key<>("android.jpeg.gpsTimestamp", (Class)long.class);
    JPEG_ORIENTATION = new Key<>("android.jpeg.orientation", (Class)int.class);
    JPEG_QUALITY = new Key<>("android.jpeg.quality", (Class)byte.class);
    JPEG_THUMBNAIL_QUALITY = new Key<>("android.jpeg.thumbnailQuality", (Class)byte.class);
    JPEG_THUMBNAIL_SIZE = new Key<>("android.jpeg.thumbnailSize", Size.class);
    LENS_APERTURE = new Key<>("android.lens.aperture", (Class)float.class);
    LENS_FILTER_DENSITY = new Key<>("android.lens.filterDensity", (Class)float.class);
    LENS_FOCAL_LENGTH = new Key<>("android.lens.focalLength", (Class)float.class);
    LENS_FOCUS_DISTANCE = new Key<>("android.lens.focusDistance", (Class)float.class);
    LENS_FOCUS_RANGE = new Key<>("android.lens.focusRange", new TypeReference<Pair<Float, Float>>() {
        
        });
    LENS_OPTICAL_STABILIZATION_MODE = new Key<>("android.lens.opticalStabilizationMode", (Class)int.class);
    LENS_STATE = new Key<>("android.lens.state", (Class)int.class);
    LENS_POSE_ROTATION = (Key)new Key<>("android.lens.poseRotation", (Class)float[].class);
    LENS_POSE_TRANSLATION = (Key)new Key<>("android.lens.poseTranslation", (Class)float[].class);
    LENS_INTRINSIC_CALIBRATION = (Key)new Key<>("android.lens.intrinsicCalibration", (Class)float[].class);
    LENS_RADIAL_DISTORTION = (Key)new Key<>("android.lens.radialDistortion", (Class)float[].class);
    LENS_DISTORTION = (Key)new Key<>("android.lens.distortion", (Class)float[].class);
    NOISE_REDUCTION_MODE = new Key<>("android.noiseReduction.mode", (Class)int.class);
    QUIRKS_PARTIAL_RESULT = new Key<>("android.quirks.partialResult", (Class)boolean.class);
    REQUEST_FRAME_COUNT = new Key<>("android.request.frameCount", (Class)int.class);
    REQUEST_ID = new Key<>("android.request.id", (Class)int.class);
    REQUEST_PIPELINE_DEPTH = new Key<>("android.request.pipelineDepth", (Class)byte.class);
    SCALER_CROP_REGION = new Key<>("android.scaler.cropRegion", Rect.class);
    SCALER_ROTATE_AND_CROP = new Key<>("android.scaler.rotateAndCrop", (Class)int.class);
    SENSOR_EXPOSURE_TIME = new Key<>("android.sensor.exposureTime", (Class)long.class);
    SENSOR_FRAME_DURATION = new Key<>("android.sensor.frameDuration", (Class)long.class);
    SENSOR_SENSITIVITY = new Key<>("android.sensor.sensitivity", (Class)int.class);
    SENSOR_TIMESTAMP = new Key<>("android.sensor.timestamp", (Class)long.class);
    SENSOR_NEUTRAL_COLOR_POINT = (Key)new Key<>("android.sensor.neutralColorPoint", (Class)Rational[].class);
    SENSOR_NOISE_PROFILE = (Key)new Key<>("android.sensor.noiseProfile", (TypeReference)new TypeReference<Pair<Double, Double>[]>() {
        
        });
    SENSOR_GREEN_SPLIT = new Key<>("android.sensor.greenSplit", (Class)float.class);
    SENSOR_TEST_PATTERN_DATA = (Key)new Key<>("android.sensor.testPatternData", (Class)int[].class);
    SENSOR_TEST_PATTERN_MODE = new Key<>("android.sensor.testPatternMode", (Class)int.class);
    SENSOR_ROLLING_SHUTTER_SKEW = new Key<>("android.sensor.rollingShutterSkew", (Class)long.class);
    SENSOR_DYNAMIC_BLACK_LEVEL = (Key)new Key<>("android.sensor.dynamicBlackLevel", (Class)float[].class);
    SENSOR_DYNAMIC_WHITE_LEVEL = new Key<>("android.sensor.dynamicWhiteLevel", (Class)int.class);
    SHADING_MODE = new Key<>("android.shading.mode", (Class)int.class);
    STATISTICS_FACE_DETECT_MODE = new Key<>("android.statistics.faceDetectMode", (Class)int.class);
    STATISTICS_FACE_IDS = (Key)new Key<>("android.statistics.faceIds", (Class)int[].class);
    STATISTICS_FACE_LANDMARKS = (Key)new Key<>("android.statistics.faceLandmarks", (Class)int[].class);
    STATISTICS_FACE_RECTANGLES = (Key)new Key<>("android.statistics.faceRectangles", (Class)Rect[].class);
    STATISTICS_FACE_SCORES = (Key)new Key<>("android.statistics.faceScores", (Class)byte[].class);
    STATISTICS_FACES = (Key)new Key<>("android.statistics.faces", (Class)Face[].class);
    STATISTICS_LENS_SHADING_CORRECTION_MAP = new Key<>("android.statistics.lensShadingCorrectionMap", LensShadingMap.class);
    STATISTICS_LENS_SHADING_MAP = (Key)new Key<>("android.statistics.lensShadingMap", (Class)float[].class);
    STATISTICS_PREDICTED_COLOR_GAINS = (Key)new Key<>("android.statistics.predictedColorGains", (Class)float[].class);
    STATISTICS_PREDICTED_COLOR_TRANSFORM = (Key)new Key<>("android.statistics.predictedColorTransform", (Class)Rational[].class);
    STATISTICS_SCENE_FLICKER = new Key<>("android.statistics.sceneFlicker", (Class)int.class);
    STATISTICS_HOT_PIXEL_MAP_MODE = new Key<>("android.statistics.hotPixelMapMode", (Class)boolean.class);
    STATISTICS_HOT_PIXEL_MAP = (Key)new Key<>("android.statistics.hotPixelMap", (Class)Point[].class);
    STATISTICS_LENS_SHADING_MAP_MODE = new Key<>("android.statistics.lensShadingMapMode", (Class)int.class);
    STATISTICS_OIS_DATA_MODE = new Key<>("android.statistics.oisDataMode", (Class)int.class);
    STATISTICS_OIS_TIMESTAMPS = (Key)new Key<>("android.statistics.oisTimestamps", (Class)long[].class);
    STATISTICS_OIS_X_SHIFTS = (Key)new Key<>("android.statistics.oisXShifts", (Class)float[].class);
    STATISTICS_OIS_Y_SHIFTS = (Key)new Key<>("android.statistics.oisYShifts", (Class)float[].class);
    STATISTICS_OIS_SAMPLES = (Key)new Key<>("android.statistics.oisSamples", (Class)OisSample[].class);
    TONEMAP_CURVE_BLUE = (Key)new Key<>("android.tonemap.curveBlue", (Class)float[].class);
    TONEMAP_CURVE_GREEN = (Key)new Key<>("android.tonemap.curveGreen", (Class)float[].class);
    TONEMAP_CURVE_RED = (Key)new Key<>("android.tonemap.curveRed", (Class)float[].class);
    TONEMAP_CURVE = new Key<>("android.tonemap.curve", TonemapCurve.class);
    TONEMAP_MODE = new Key<>("android.tonemap.mode", (Class)int.class);
    TONEMAP_GAMMA = new Key<>("android.tonemap.gamma", (Class)float.class);
    TONEMAP_PRESET_CURVE = new Key<>("android.tonemap.presetCurve", (Class)int.class);
    LED_TRANSMIT = new Key<>("android.led.transmit", (Class)boolean.class);
    BLACK_LEVEL_LOCK = new Key<>("android.blackLevel.lock", (Class)boolean.class);
    SYNC_FRAME_NUMBER = new Key<>("android.sync.frameNumber", (Class)long.class);
    REPROCESS_EFFECTIVE_EXPOSURE_FACTOR = new Key<>("android.reprocess.effectiveExposureFactor", (Class)float.class);
    LOGICAL_MULTI_CAMERA_ACTIVE_PHYSICAL_ID = new Key<>("android.logicalMultiCamera.activePhysicalId", String.class);
    DISTORTION_CORRECTION_MODE = new Key<>("android.distortionCorrection.mode", (Class)int.class);
  }
  
  public CaptureResult(CameraMetadataNative paramCameraMetadataNative, int paramInt) {
    if (paramCameraMetadataNative != null) {
      paramCameraMetadataNative = CameraMetadataNative.move(paramCameraMetadataNative);
      this.mResults = paramCameraMetadataNative;
      if (!paramCameraMetadataNative.isEmpty()) {
        setNativeInstance(this.mResults);
        this.mRequest = null;
        this.mSequenceId = paramInt;
        this.mFrameNumber = -1L;
        return;
      } 
      throw new AssertionError("Results must not be empty");
    } 
    throw new IllegalArgumentException("results was null");
  }
  
  public CaptureResult(CameraMetadataNative paramCameraMetadataNative, CaptureRequest paramCaptureRequest, CaptureResultExtras paramCaptureResultExtras) {
    if (paramCameraMetadataNative != null) {
      if (paramCaptureRequest != null) {
        if (paramCaptureResultExtras != null) {
          paramCameraMetadataNative = CameraMetadataNative.move(paramCameraMetadataNative);
          this.mResults = paramCameraMetadataNative;
          if (!paramCameraMetadataNative.isEmpty()) {
            setNativeInstance(this.mResults);
            this.mRequest = paramCaptureRequest;
            this.mSequenceId = paramCaptureResultExtras.getRequestId();
            this.mFrameNumber = paramCaptureResultExtras.getFrameNumber();
            return;
          } 
          throw new AssertionError("Results must not be empty");
        } 
        throw new IllegalArgumentException("extras was null");
      } 
      throw new IllegalArgumentException("parent was null");
    } 
    throw new IllegalArgumentException("results was null");
  }
  
  public void dumpToLog() {
    this.mResults.dumpToLog();
  }
  
  public <T> T get(Key<T> paramKey) {
    return (T)this.mResults.get(paramKey);
  }
  
  public long getFrameNumber() {
    return this.mFrameNumber;
  }
  
  protected Class<Key<?>> getKeyClass() {
    return (Class)Key.class;
  }
  
  public List<Key<?>> getKeys() {
    return super.getKeys();
  }
  
  public CameraMetadataNative getNativeCopy() {
    return new CameraMetadataNative(this.mResults);
  }
  
  protected <T> T getProtected(Key<?> paramKey) {
    return (T)this.mResults.get(paramKey);
  }
  
  public CaptureRequest getRequest() {
    return this.mRequest;
  }
  
  public int getSequenceId() {
    return this.mSequenceId;
  }
  
  public static final class Key<T> {
    private final CameraMetadataNative.Key<T> mKey;
    
    Key(CameraMetadataNative.Key<?> param1Key) {
      this.mKey = (CameraMetadataNative.Key)param1Key;
    }
    
    public Key(String param1String, TypeReference<T> param1TypeReference) {
      this.mKey = new CameraMetadataNative.Key(param1String, param1TypeReference);
    }
    
    public Key(String param1String, Class<T> param1Class) {
      this.mKey = new CameraMetadataNative.Key(param1String, param1Class);
    }
    
    public Key(String param1String, Class<T> param1Class, long param1Long) {
      this.mKey = new CameraMetadataNative.Key(param1String, param1Class, param1Long);
    }
    
    public Key(String param1String1, String param1String2, Class<T> param1Class) {
      this.mKey = new CameraMetadataNative.Key(param1String1, param1String2, param1Class);
    }
    
    public final boolean equals(Object param1Object) {
      boolean bool;
      if (param1Object instanceof Key && ((Key)param1Object).mKey.equals(this.mKey)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public String getName() {
      return this.mKey.getName();
    }
    
    public CameraMetadataNative.Key<T> getNativeKey() {
      return this.mKey;
    }
    
    public long getVendorId() {
      return this.mKey.getVendorId();
    }
    
    public final int hashCode() {
      return this.mKey.hashCode();
    }
    
    public String toString() {
      return String.format("CaptureResult.Key(%s)", new Object[] { this.mKey.getName() });
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CaptureResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
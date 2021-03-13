package android.hardware.camera2;

import android.graphics.Rect;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.PublicKey;
import android.hardware.camera2.impl.SyntheticKey;
import android.hardware.camera2.params.BlackLevelPattern;
import android.hardware.camera2.params.Capability;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.params.HighSpeedVideoConfiguration;
import android.hardware.camera2.params.MandatoryStreamCombination;
import android.hardware.camera2.params.RecommendedStreamConfiguration;
import android.hardware.camera2.params.RecommendedStreamConfigurationMap;
import android.hardware.camera2.params.ReprocessFormatsMap;
import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.params.StreamConfigurationDuration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.ArrayUtils;
import android.hardware.camera2.utils.TypeReference;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.util.SizeF;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CameraCharacteristics extends CameraMetadata<CameraCharacteristics.Key<?>> {
  @PublicKey
  public static final Key<int[]> COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES = (Key)new Key<>("android.colorCorrection.availableAberrationModes", (Class)int[].class);
  
  @PublicKey
  public static final Key<int[]> CONTROL_AE_AVAILABLE_ANTIBANDING_MODES = (Key)new Key<>("android.control.aeAvailableAntibandingModes", (Class)int[].class);
  
  @PublicKey
  public static final Key<int[]> CONTROL_AE_AVAILABLE_MODES = (Key)new Key<>("android.control.aeAvailableModes", (Class)int[].class);
  
  @PublicKey
  public static final Key<Range<Integer>[]> CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES = (Key)new Key<>("android.control.aeAvailableTargetFpsRanges", (TypeReference)new TypeReference<Range<Integer>[]>() {
      
      });
  
  @PublicKey
  public static final Key<Range<Integer>> CONTROL_AE_COMPENSATION_RANGE = new Key<>("android.control.aeCompensationRange", new TypeReference<Range<Integer>>() {
      
      });
  
  @PublicKey
  public static final Key<Rational> CONTROL_AE_COMPENSATION_STEP = new Key<>("android.control.aeCompensationStep", Rational.class);
  
  @PublicKey
  public static final Key<Boolean> CONTROL_AE_LOCK_AVAILABLE;
  
  @PublicKey
  public static final Key<int[]> CONTROL_AF_AVAILABLE_MODES = (Key)new Key<>("android.control.afAvailableModes", (Class)int[].class);
  
  @PublicKey
  public static final Key<int[]> CONTROL_AVAILABLE_EFFECTS = (Key)new Key<>("android.control.availableEffects", (Class)int[].class);
  
  @PublicKey
  @SyntheticKey
  public static final Key<Capability[]> CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_CAPABILITIES;
  
  public static final Key<int[]> CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_MAX_SIZES;
  
  public static final Key<float[]> CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_ZOOM_RATIO_RANGES;
  
  public static final Key<HighSpeedVideoConfiguration[]> CONTROL_AVAILABLE_HIGH_SPEED_VIDEO_CONFIGURATIONS;
  
  @PublicKey
  public static final Key<int[]> CONTROL_AVAILABLE_MODES;
  
  @PublicKey
  public static final Key<int[]> CONTROL_AVAILABLE_SCENE_MODES = (Key)new Key<>("android.control.availableSceneModes", (Class)int[].class);
  
  @PublicKey
  public static final Key<int[]> CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES = (Key)new Key<>("android.control.availableVideoStabilizationModes", (Class)int[].class);
  
  @PublicKey
  public static final Key<int[]> CONTROL_AWB_AVAILABLE_MODES = (Key)new Key<>("android.control.awbAvailableModes", (Class)int[].class);
  
  @PublicKey
  public static final Key<Boolean> CONTROL_AWB_LOCK_AVAILABLE;
  
  public static final Key<int[]> CONTROL_MAX_REGIONS = (Key)new Key<>("android.control.maxRegions", (Class)int[].class);
  
  @PublicKey
  @SyntheticKey
  public static final Key<Integer> CONTROL_MAX_REGIONS_AE = new Key<>("android.control.maxRegionsAe", (Class)int.class);
  
  @PublicKey
  @SyntheticKey
  public static final Key<Integer> CONTROL_MAX_REGIONS_AF;
  
  @PublicKey
  @SyntheticKey
  public static final Key<Integer> CONTROL_MAX_REGIONS_AWB = new Key<>("android.control.maxRegionsAwb", (Class)int.class);
  
  @PublicKey
  public static final Key<Range<Integer>> CONTROL_POST_RAW_SENSITIVITY_BOOST_RANGE;
  
  @PublicKey
  public static final Key<Range<Float>> CONTROL_ZOOM_RATIO_RANGE;
  
  public static final Key<StreamConfigurationDuration[]> DEPTH_AVAILABLE_DEPTH_MIN_FRAME_DURATIONS;
  
  public static final Key<StreamConfigurationDuration[]> DEPTH_AVAILABLE_DEPTH_STALL_DURATIONS;
  
  public static final Key<StreamConfiguration[]> DEPTH_AVAILABLE_DEPTH_STREAM_CONFIGURATIONS;
  
  public static final Key<StreamConfigurationDuration[]> DEPTH_AVAILABLE_DYNAMIC_DEPTH_MIN_FRAME_DURATIONS;
  
  public static final Key<StreamConfigurationDuration[]> DEPTH_AVAILABLE_DYNAMIC_DEPTH_STALL_DURATIONS;
  
  public static final Key<StreamConfiguration[]> DEPTH_AVAILABLE_DYNAMIC_DEPTH_STREAM_CONFIGURATIONS;
  
  public static final Key<RecommendedStreamConfiguration[]> DEPTH_AVAILABLE_RECOMMENDED_DEPTH_STREAM_CONFIGURATIONS;
  
  @PublicKey
  public static final Key<Boolean> DEPTH_DEPTH_IS_EXCLUSIVE;
  
  @PublicKey
  public static final Key<int[]> DISTORTION_CORRECTION_AVAILABLE_MODES;
  
  @PublicKey
  public static final Key<int[]> EDGE_AVAILABLE_EDGE_MODES;
  
  @PublicKey
  public static final Key<Boolean> FLASH_INFO_AVAILABLE;
  
  public static final Key<StreamConfigurationDuration[]> HEIC_AVAILABLE_HEIC_MIN_FRAME_DURATIONS;
  
  public static final Key<StreamConfigurationDuration[]> HEIC_AVAILABLE_HEIC_STALL_DURATIONS;
  
  public static final Key<StreamConfiguration[]> HEIC_AVAILABLE_HEIC_STREAM_CONFIGURATIONS;
  
  @PublicKey
  public static final Key<int[]> HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES;
  
  @PublicKey
  public static final Key<Integer> INFO_SUPPORTED_HARDWARE_LEVEL;
  
  @PublicKey
  public static final Key<String> INFO_VERSION;
  
  @PublicKey
  public static final Key<Size[]> JPEG_AVAILABLE_THUMBNAIL_SIZES;
  
  public static final Key<int[]> LED_AVAILABLE_LEDS;
  
  @PublicKey
  public static final Key<float[]> LENS_DISTORTION;
  
  @PublicKey
  public static final Key<Integer> LENS_FACING;
  
  @PublicKey
  public static final Key<float[]> LENS_INFO_AVAILABLE_APERTURES;
  
  @PublicKey
  public static final Key<float[]> LENS_INFO_AVAILABLE_FILTER_DENSITIES;
  
  @PublicKey
  public static final Key<float[]> LENS_INFO_AVAILABLE_FOCAL_LENGTHS;
  
  @PublicKey
  public static final Key<int[]> LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION;
  
  @PublicKey
  public static final Key<Integer> LENS_INFO_FOCUS_DISTANCE_CALIBRATION;
  
  @PublicKey
  public static final Key<Float> LENS_INFO_HYPERFOCAL_DISTANCE;
  
  @PublicKey
  public static final Key<Float> LENS_INFO_MINIMUM_FOCUS_DISTANCE;
  
  public static final Key<Size> LENS_INFO_SHADING_MAP_SIZE;
  
  @PublicKey
  public static final Key<float[]> LENS_INTRINSIC_CALIBRATION;
  
  @PublicKey
  public static final Key<Integer> LENS_POSE_REFERENCE;
  
  @PublicKey
  public static final Key<float[]> LENS_POSE_ROTATION;
  
  @PublicKey
  public static final Key<float[]> LENS_POSE_TRANSLATION;
  
  @PublicKey
  @Deprecated
  public static final Key<float[]> LENS_RADIAL_DISTORTION;
  
  public static final Key<byte[]> LOGICAL_MULTI_CAMERA_PHYSICAL_IDS;
  
  @PublicKey
  public static final Key<Integer> LOGICAL_MULTI_CAMERA_SENSOR_SYNC_TYPE;
  
  @PublicKey
  public static final Key<int[]> NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES;
  
  @Deprecated
  public static final Key<Byte> QUIRKS_USE_PARTIAL_RESULT;
  
  @PublicKey
  public static final Key<Integer> REPROCESS_MAX_CAPTURE_STALL;
  
  @PublicKey
  public static final Key<int[]> REQUEST_AVAILABLE_CAPABILITIES;
  
  public static final Key<int[]> REQUEST_AVAILABLE_CHARACTERISTICS_KEYS;
  
  public static final Key<int[]> REQUEST_AVAILABLE_PHYSICAL_CAMERA_REQUEST_KEYS;
  
  public static final Key<int[]> REQUEST_AVAILABLE_REQUEST_KEYS;
  
  public static final Key<int[]> REQUEST_AVAILABLE_RESULT_KEYS;
  
  public static final Key<int[]> REQUEST_AVAILABLE_SESSION_KEYS;
  
  public static final Key<int[]> REQUEST_CHARACTERISTIC_KEYS_NEEDING_PERMISSION;
  
  @PublicKey
  public static final Key<Integer> REQUEST_MAX_NUM_INPUT_STREAMS;
  
  @PublicKey
  @SyntheticKey
  public static final Key<Integer> REQUEST_MAX_NUM_OUTPUT_PROC;
  
  @PublicKey
  @SyntheticKey
  public static final Key<Integer> REQUEST_MAX_NUM_OUTPUT_PROC_STALLING;
  
  @PublicKey
  @SyntheticKey
  public static final Key<Integer> REQUEST_MAX_NUM_OUTPUT_RAW;
  
  public static final Key<int[]> REQUEST_MAX_NUM_OUTPUT_STREAMS;
  
  @PublicKey
  public static final Key<Integer> REQUEST_PARTIAL_RESULT_COUNT;
  
  @PublicKey
  public static final Key<Byte> REQUEST_PIPELINE_MAX_DEPTH;
  
  @Deprecated
  public static final Key<int[]> SCALER_AVAILABLE_FORMATS;
  
  public static final Key<ReprocessFormatsMap> SCALER_AVAILABLE_INPUT_OUTPUT_FORMATS_MAP;
  
  @Deprecated
  public static final Key<long[]> SCALER_AVAILABLE_JPEG_MIN_DURATIONS;
  
  @Deprecated
  public static final Key<Size[]> SCALER_AVAILABLE_JPEG_SIZES;
  
  @PublicKey
  public static final Key<Float> SCALER_AVAILABLE_MAX_DIGITAL_ZOOM;
  
  public static final Key<StreamConfigurationDuration[]> SCALER_AVAILABLE_MIN_FRAME_DURATIONS;
  
  @Deprecated
  public static final Key<long[]> SCALER_AVAILABLE_PROCESSED_MIN_DURATIONS;
  
  @Deprecated
  public static final Key<Size[]> SCALER_AVAILABLE_PROCESSED_SIZES;
  
  public static final Key<ReprocessFormatsMap> SCALER_AVAILABLE_RECOMMENDED_INPUT_OUTPUT_FORMATS_MAP;
  
  public static final Key<RecommendedStreamConfiguration[]> SCALER_AVAILABLE_RECOMMENDED_STREAM_CONFIGURATIONS;
  
  public static final Key<int[]> SCALER_AVAILABLE_ROTATE_AND_CROP_MODES;
  
  public static final Key<StreamConfigurationDuration[]> SCALER_AVAILABLE_STALL_DURATIONS;
  
  public static final Key<StreamConfiguration[]> SCALER_AVAILABLE_STREAM_CONFIGURATIONS;
  
  @PublicKey
  public static final Key<Integer> SCALER_CROPPING_TYPE;
  
  @PublicKey
  @SyntheticKey
  public static final Key<MandatoryStreamCombination[]> SCALER_MANDATORY_CONCURRENT_STREAM_COMBINATIONS;
  
  @PublicKey
  @SyntheticKey
  public static final Key<MandatoryStreamCombination[]> SCALER_MANDATORY_STREAM_COMBINATIONS;
  
  @PublicKey
  @SyntheticKey
  public static final Key<StreamConfigurationMap> SCALER_STREAM_CONFIGURATION_MAP;
  
  @PublicKey
  public static final Key<int[]> SENSOR_AVAILABLE_TEST_PATTERN_MODES;
  
  @PublicKey
  public static final Key<BlackLevelPattern> SENSOR_BLACK_LEVEL_PATTERN;
  
  @PublicKey
  public static final Key<ColorSpaceTransform> SENSOR_CALIBRATION_TRANSFORM1;
  
  @PublicKey
  public static final Key<ColorSpaceTransform> SENSOR_CALIBRATION_TRANSFORM2;
  
  @PublicKey
  public static final Key<ColorSpaceTransform> SENSOR_COLOR_TRANSFORM1;
  
  @PublicKey
  public static final Key<ColorSpaceTransform> SENSOR_COLOR_TRANSFORM2;
  
  @PublicKey
  public static final Key<ColorSpaceTransform> SENSOR_FORWARD_MATRIX1;
  
  @PublicKey
  public static final Key<ColorSpaceTransform> SENSOR_FORWARD_MATRIX2;
  
  @PublicKey
  public static final Key<Rect> SENSOR_INFO_ACTIVE_ARRAY_SIZE;
  
  @PublicKey
  public static final Key<Integer> SENSOR_INFO_COLOR_FILTER_ARRANGEMENT;
  
  @PublicKey
  public static final Key<Range<Long>> SENSOR_INFO_EXPOSURE_TIME_RANGE;
  
  @PublicKey
  public static final Key<Boolean> SENSOR_INFO_LENS_SHADING_APPLIED;
  
  @PublicKey
  public static final Key<Long> SENSOR_INFO_MAX_FRAME_DURATION;
  
  @PublicKey
  public static final Key<SizeF> SENSOR_INFO_PHYSICAL_SIZE;
  
  @PublicKey
  public static final Key<Size> SENSOR_INFO_PIXEL_ARRAY_SIZE;
  
  @PublicKey
  public static final Key<Rect> SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE;
  
  @PublicKey
  public static final Key<Range<Integer>> SENSOR_INFO_SENSITIVITY_RANGE;
  
  @PublicKey
  public static final Key<Integer> SENSOR_INFO_TIMESTAMP_SOURCE;
  
  @PublicKey
  public static final Key<Integer> SENSOR_INFO_WHITE_LEVEL;
  
  @PublicKey
  public static final Key<Integer> SENSOR_MAX_ANALOG_SENSITIVITY;
  
  @PublicKey
  public static final Key<Rect[]> SENSOR_OPTICAL_BLACK_REGIONS;
  
  @PublicKey
  public static final Key<Integer> SENSOR_ORIENTATION;
  
  @PublicKey
  public static final Key<Integer> SENSOR_REFERENCE_ILLUMINANT1;
  
  @PublicKey
  public static final Key<Byte> SENSOR_REFERENCE_ILLUMINANT2;
  
  @PublicKey
  public static final Key<int[]> SHADING_AVAILABLE_MODES;
  
  @PublicKey
  public static final Key<int[]> STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES;
  
  @PublicKey
  public static final Key<boolean[]> STATISTICS_INFO_AVAILABLE_HOT_PIXEL_MAP_MODES;
  
  @PublicKey
  public static final Key<int[]> STATISTICS_INFO_AVAILABLE_LENS_SHADING_MAP_MODES;
  
  @PublicKey
  public static final Key<int[]> STATISTICS_INFO_AVAILABLE_OIS_DATA_MODES;
  
  @PublicKey
  public static final Key<Integer> STATISTICS_INFO_MAX_FACE_COUNT;
  
  @PublicKey
  public static final Key<Integer> SYNC_MAX_LATENCY;
  
  @PublicKey
  public static final Key<int[]> TONEMAP_AVAILABLE_TONE_MAP_MODES;
  
  @PublicKey
  public static final Key<Integer> TONEMAP_MAX_CURVE_POINTS;
  
  private List<CaptureRequest.Key<?>> mAvailablePhysicalRequestKeys;
  
  private List<CaptureRequest.Key<?>> mAvailableRequestKeys;
  
  private List<CaptureResult.Key<?>> mAvailableResultKeys;
  
  private List<CaptureRequest.Key<?>> mAvailableSessionKeys;
  
  private List<Key<?>> mKeys;
  
  private List<Key<?>> mKeysNeedingPermission;
  
  private final CameraMetadataNative mProperties;
  
  private ArrayList<RecommendedStreamConfigurationMap> mRecommendedConfigurations;
  
  static {
    CONTROL_MAX_REGIONS_AF = new Key<>("android.control.maxRegionsAf", (Class)int.class);
    CONTROL_AVAILABLE_HIGH_SPEED_VIDEO_CONFIGURATIONS = (Key)new Key<>("android.control.availableHighSpeedVideoConfigurations", (Class)HighSpeedVideoConfiguration[].class);
    CONTROL_AE_LOCK_AVAILABLE = new Key<>("android.control.aeLockAvailable", (Class)boolean.class);
    CONTROL_AWB_LOCK_AVAILABLE = new Key<>("android.control.awbLockAvailable", (Class)boolean.class);
    CONTROL_AVAILABLE_MODES = (Key)new Key<>("android.control.availableModes", (Class)int[].class);
    CONTROL_POST_RAW_SENSITIVITY_BOOST_RANGE = new Key<>("android.control.postRawSensitivityBoostRange", new TypeReference<Range<Integer>>() {
        
        });
    CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_MAX_SIZES = (Key)new Key<>("android.control.availableExtendedSceneModeMaxSizes", (Class)int[].class);
    CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_ZOOM_RATIO_RANGES = (Key)new Key<>("android.control.availableExtendedSceneModeZoomRatioRanges", (Class)float[].class);
    CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_CAPABILITIES = (Key)new Key<>("android.control.availableExtendedSceneModeCapabilities", (Class)Capability[].class);
    CONTROL_ZOOM_RATIO_RANGE = new Key<>("android.control.zoomRatioRange", new TypeReference<Range<Float>>() {
        
        });
    EDGE_AVAILABLE_EDGE_MODES = (Key)new Key<>("android.edge.availableEdgeModes", (Class)int[].class);
    FLASH_INFO_AVAILABLE = new Key<>("android.flash.info.available", (Class)boolean.class);
    HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES = (Key)new Key<>("android.hotPixel.availableHotPixelModes", (Class)int[].class);
    JPEG_AVAILABLE_THUMBNAIL_SIZES = (Key)new Key<>("android.jpeg.availableThumbnailSizes", (Class)Size[].class);
    LENS_INFO_AVAILABLE_APERTURES = (Key)new Key<>("android.lens.info.availableApertures", (Class)float[].class);
    LENS_INFO_AVAILABLE_FILTER_DENSITIES = (Key)new Key<>("android.lens.info.availableFilterDensities", (Class)float[].class);
    LENS_INFO_AVAILABLE_FOCAL_LENGTHS = (Key)new Key<>("android.lens.info.availableFocalLengths", (Class)float[].class);
    LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION = (Key)new Key<>("android.lens.info.availableOpticalStabilization", (Class)int[].class);
    LENS_INFO_HYPERFOCAL_DISTANCE = new Key<>("android.lens.info.hyperfocalDistance", (Class)float.class);
    LENS_INFO_MINIMUM_FOCUS_DISTANCE = new Key<>("android.lens.info.minimumFocusDistance", (Class)float.class);
    LENS_INFO_SHADING_MAP_SIZE = new Key<>("android.lens.info.shadingMapSize", Size.class);
    LENS_INFO_FOCUS_DISTANCE_CALIBRATION = new Key<>("android.lens.info.focusDistanceCalibration", (Class)int.class);
    LENS_FACING = new Key<>("android.lens.facing", (Class)int.class);
    LENS_POSE_ROTATION = (Key)new Key<>("android.lens.poseRotation", (Class)float[].class);
    LENS_POSE_TRANSLATION = (Key)new Key<>("android.lens.poseTranslation", (Class)float[].class);
    LENS_INTRINSIC_CALIBRATION = (Key)new Key<>("android.lens.intrinsicCalibration", (Class)float[].class);
    LENS_RADIAL_DISTORTION = (Key)new Key<>("android.lens.radialDistortion", (Class)float[].class);
    LENS_POSE_REFERENCE = new Key<>("android.lens.poseReference", (Class)int.class);
    LENS_DISTORTION = (Key)new Key<>("android.lens.distortion", (Class)float[].class);
    NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES = (Key)new Key<>("android.noiseReduction.availableNoiseReductionModes", (Class)int[].class);
    QUIRKS_USE_PARTIAL_RESULT = new Key<>("android.quirks.usePartialResult", (Class)byte.class);
    REQUEST_MAX_NUM_OUTPUT_STREAMS = (Key)new Key<>("android.request.maxNumOutputStreams", (Class)int[].class);
    REQUEST_MAX_NUM_OUTPUT_RAW = new Key<>("android.request.maxNumOutputRaw", (Class)int.class);
    REQUEST_MAX_NUM_OUTPUT_PROC = new Key<>("android.request.maxNumOutputProc", (Class)int.class);
    REQUEST_MAX_NUM_OUTPUT_PROC_STALLING = new Key<>("android.request.maxNumOutputProcStalling", (Class)int.class);
    REQUEST_MAX_NUM_INPUT_STREAMS = new Key<>("android.request.maxNumInputStreams", (Class)int.class);
    REQUEST_PIPELINE_MAX_DEPTH = new Key<>("android.request.pipelineMaxDepth", (Class)byte.class);
    REQUEST_PARTIAL_RESULT_COUNT = new Key<>("android.request.partialResultCount", (Class)int.class);
    REQUEST_AVAILABLE_CAPABILITIES = (Key)new Key<>("android.request.availableCapabilities", (Class)int[].class);
    REQUEST_AVAILABLE_REQUEST_KEYS = (Key)new Key<>("android.request.availableRequestKeys", (Class)int[].class);
    REQUEST_AVAILABLE_RESULT_KEYS = (Key)new Key<>("android.request.availableResultKeys", (Class)int[].class);
    REQUEST_AVAILABLE_CHARACTERISTICS_KEYS = (Key)new Key<>("android.request.availableCharacteristicsKeys", (Class)int[].class);
    REQUEST_AVAILABLE_SESSION_KEYS = (Key)new Key<>("android.request.availableSessionKeys", (Class)int[].class);
    REQUEST_AVAILABLE_PHYSICAL_CAMERA_REQUEST_KEYS = (Key)new Key<>("android.request.availablePhysicalCameraRequestKeys", (Class)int[].class);
    REQUEST_CHARACTERISTIC_KEYS_NEEDING_PERMISSION = (Key)new Key<>("android.request.characteristicKeysNeedingPermission", (Class)int[].class);
    SCALER_AVAILABLE_FORMATS = (Key)new Key<>("android.scaler.availableFormats", (Class)int[].class);
    SCALER_AVAILABLE_JPEG_MIN_DURATIONS = (Key)new Key<>("android.scaler.availableJpegMinDurations", (Class)long[].class);
    SCALER_AVAILABLE_JPEG_SIZES = (Key)new Key<>("android.scaler.availableJpegSizes", (Class)Size[].class);
    SCALER_AVAILABLE_MAX_DIGITAL_ZOOM = new Key<>("android.scaler.availableMaxDigitalZoom", (Class)float.class);
    SCALER_AVAILABLE_PROCESSED_MIN_DURATIONS = (Key)new Key<>("android.scaler.availableProcessedMinDurations", (Class)long[].class);
    SCALER_AVAILABLE_PROCESSED_SIZES = (Key)new Key<>("android.scaler.availableProcessedSizes", (Class)Size[].class);
    SCALER_AVAILABLE_INPUT_OUTPUT_FORMATS_MAP = new Key<>("android.scaler.availableInputOutputFormatsMap", ReprocessFormatsMap.class);
    SCALER_AVAILABLE_STREAM_CONFIGURATIONS = (Key)new Key<>("android.scaler.availableStreamConfigurations", (Class)StreamConfiguration[].class);
    SCALER_AVAILABLE_MIN_FRAME_DURATIONS = (Key)new Key<>("android.scaler.availableMinFrameDurations", (Class)StreamConfigurationDuration[].class);
    SCALER_AVAILABLE_STALL_DURATIONS = (Key)new Key<>("android.scaler.availableStallDurations", (Class)StreamConfigurationDuration[].class);
    SCALER_STREAM_CONFIGURATION_MAP = new Key<>("android.scaler.streamConfigurationMap", StreamConfigurationMap.class);
    SCALER_CROPPING_TYPE = new Key<>("android.scaler.croppingType", (Class)int.class);
    SCALER_AVAILABLE_RECOMMENDED_STREAM_CONFIGURATIONS = (Key)new Key<>("android.scaler.availableRecommendedStreamConfigurations", (Class)RecommendedStreamConfiguration[].class);
    SCALER_AVAILABLE_RECOMMENDED_INPUT_OUTPUT_FORMATS_MAP = new Key<>("android.scaler.availableRecommendedInputOutputFormatsMap", ReprocessFormatsMap.class);
    SCALER_MANDATORY_STREAM_COMBINATIONS = (Key)new Key<>("android.scaler.mandatoryStreamCombinations", (Class)MandatoryStreamCombination[].class);
    SCALER_MANDATORY_CONCURRENT_STREAM_COMBINATIONS = (Key)new Key<>("android.scaler.mandatoryConcurrentStreamCombinations", (Class)MandatoryStreamCombination[].class);
    SCALER_AVAILABLE_ROTATE_AND_CROP_MODES = (Key)new Key<>("android.scaler.availableRotateAndCropModes", (Class)int[].class);
    SENSOR_INFO_ACTIVE_ARRAY_SIZE = new Key<>("android.sensor.info.activeArraySize", Rect.class);
    SENSOR_INFO_SENSITIVITY_RANGE = new Key<>("android.sensor.info.sensitivityRange", new TypeReference<Range<Integer>>() {
        
        });
    SENSOR_INFO_COLOR_FILTER_ARRANGEMENT = new Key<>("android.sensor.info.colorFilterArrangement", (Class)int.class);
    SENSOR_INFO_EXPOSURE_TIME_RANGE = new Key<>("android.sensor.info.exposureTimeRange", new TypeReference<Range<Long>>() {
        
        });
    SENSOR_INFO_MAX_FRAME_DURATION = new Key<>("android.sensor.info.maxFrameDuration", (Class)long.class);
    SENSOR_INFO_PHYSICAL_SIZE = new Key<>("android.sensor.info.physicalSize", SizeF.class);
    SENSOR_INFO_PIXEL_ARRAY_SIZE = new Key<>("android.sensor.info.pixelArraySize", Size.class);
    SENSOR_INFO_WHITE_LEVEL = new Key<>("android.sensor.info.whiteLevel", (Class)int.class);
    SENSOR_INFO_TIMESTAMP_SOURCE = new Key<>("android.sensor.info.timestampSource", (Class)int.class);
    SENSOR_INFO_LENS_SHADING_APPLIED = new Key<>("android.sensor.info.lensShadingApplied", (Class)boolean.class);
    SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE = new Key<>("android.sensor.info.preCorrectionActiveArraySize", Rect.class);
    SENSOR_REFERENCE_ILLUMINANT1 = new Key<>("android.sensor.referenceIlluminant1", (Class)int.class);
    SENSOR_REFERENCE_ILLUMINANT2 = new Key<>("android.sensor.referenceIlluminant2", (Class)byte.class);
    SENSOR_CALIBRATION_TRANSFORM1 = new Key<>("android.sensor.calibrationTransform1", ColorSpaceTransform.class);
    SENSOR_CALIBRATION_TRANSFORM2 = new Key<>("android.sensor.calibrationTransform2", ColorSpaceTransform.class);
    SENSOR_COLOR_TRANSFORM1 = new Key<>("android.sensor.colorTransform1", ColorSpaceTransform.class);
    SENSOR_COLOR_TRANSFORM2 = new Key<>("android.sensor.colorTransform2", ColorSpaceTransform.class);
    SENSOR_FORWARD_MATRIX1 = new Key<>("android.sensor.forwardMatrix1", ColorSpaceTransform.class);
    SENSOR_FORWARD_MATRIX2 = new Key<>("android.sensor.forwardMatrix2", ColorSpaceTransform.class);
    SENSOR_BLACK_LEVEL_PATTERN = new Key<>("android.sensor.blackLevelPattern", BlackLevelPattern.class);
    SENSOR_MAX_ANALOG_SENSITIVITY = new Key<>("android.sensor.maxAnalogSensitivity", (Class)int.class);
    SENSOR_ORIENTATION = new Key<>("android.sensor.orientation", (Class)int.class);
    SENSOR_AVAILABLE_TEST_PATTERN_MODES = (Key)new Key<>("android.sensor.availableTestPatternModes", (Class)int[].class);
    SENSOR_OPTICAL_BLACK_REGIONS = (Key)new Key<>("android.sensor.opticalBlackRegions", (Class)Rect[].class);
    SHADING_AVAILABLE_MODES = (Key)new Key<>("android.shading.availableModes", (Class)int[].class);
    STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES = (Key)new Key<>("android.statistics.info.availableFaceDetectModes", (Class)int[].class);
    STATISTICS_INFO_MAX_FACE_COUNT = new Key<>("android.statistics.info.maxFaceCount", (Class)int.class);
    STATISTICS_INFO_AVAILABLE_HOT_PIXEL_MAP_MODES = (Key)new Key<>("android.statistics.info.availableHotPixelMapModes", (Class)boolean[].class);
    STATISTICS_INFO_AVAILABLE_LENS_SHADING_MAP_MODES = (Key)new Key<>("android.statistics.info.availableLensShadingMapModes", (Class)int[].class);
    STATISTICS_INFO_AVAILABLE_OIS_DATA_MODES = (Key)new Key<>("android.statistics.info.availableOisDataModes", (Class)int[].class);
    TONEMAP_MAX_CURVE_POINTS = new Key<>("android.tonemap.maxCurvePoints", (Class)int.class);
    TONEMAP_AVAILABLE_TONE_MAP_MODES = (Key)new Key<>("android.tonemap.availableToneMapModes", (Class)int[].class);
    LED_AVAILABLE_LEDS = (Key)new Key<>("android.led.availableLeds", (Class)int[].class);
    INFO_SUPPORTED_HARDWARE_LEVEL = new Key<>("android.info.supportedHardwareLevel", (Class)int.class);
    INFO_VERSION = new Key<>("android.info.version", String.class);
    SYNC_MAX_LATENCY = new Key<>("android.sync.maxLatency", (Class)int.class);
    REPROCESS_MAX_CAPTURE_STALL = new Key<>("android.reprocess.maxCaptureStall", (Class)int.class);
    DEPTH_AVAILABLE_DEPTH_STREAM_CONFIGURATIONS = (Key)new Key<>("android.depth.availableDepthStreamConfigurations", (Class)StreamConfiguration[].class);
    DEPTH_AVAILABLE_DEPTH_MIN_FRAME_DURATIONS = (Key)new Key<>("android.depth.availableDepthMinFrameDurations", (Class)StreamConfigurationDuration[].class);
    DEPTH_AVAILABLE_DEPTH_STALL_DURATIONS = (Key)new Key<>("android.depth.availableDepthStallDurations", (Class)StreamConfigurationDuration[].class);
    DEPTH_DEPTH_IS_EXCLUSIVE = new Key<>("android.depth.depthIsExclusive", (Class)boolean.class);
    DEPTH_AVAILABLE_RECOMMENDED_DEPTH_STREAM_CONFIGURATIONS = (Key)new Key<>("android.depth.availableRecommendedDepthStreamConfigurations", (Class)RecommendedStreamConfiguration[].class);
    DEPTH_AVAILABLE_DYNAMIC_DEPTH_STREAM_CONFIGURATIONS = (Key)new Key<>("android.depth.availableDynamicDepthStreamConfigurations", (Class)StreamConfiguration[].class);
    DEPTH_AVAILABLE_DYNAMIC_DEPTH_MIN_FRAME_DURATIONS = (Key)new Key<>("android.depth.availableDynamicDepthMinFrameDurations", (Class)StreamConfigurationDuration[].class);
    DEPTH_AVAILABLE_DYNAMIC_DEPTH_STALL_DURATIONS = (Key)new Key<>("android.depth.availableDynamicDepthStallDurations", (Class)StreamConfigurationDuration[].class);
    LOGICAL_MULTI_CAMERA_PHYSICAL_IDS = (Key)new Key<>("android.logicalMultiCamera.physicalIds", (Class)byte[].class);
    LOGICAL_MULTI_CAMERA_SENSOR_SYNC_TYPE = new Key<>("android.logicalMultiCamera.sensorSyncType", (Class)int.class);
    DISTORTION_CORRECTION_AVAILABLE_MODES = (Key)new Key<>("android.distortionCorrection.availableModes", (Class)int[].class);
    HEIC_AVAILABLE_HEIC_STREAM_CONFIGURATIONS = (Key)new Key<>("android.heic.availableHeicStreamConfigurations", (Class)StreamConfiguration[].class);
    HEIC_AVAILABLE_HEIC_MIN_FRAME_DURATIONS = (Key)new Key<>("android.heic.availableHeicMinFrameDurations", (Class)StreamConfigurationDuration[].class);
    HEIC_AVAILABLE_HEIC_STALL_DURATIONS = (Key)new Key<>("android.heic.availableHeicStallDurations", (Class)StreamConfigurationDuration[].class);
  }
  
  public CameraCharacteristics(CameraMetadataNative paramCameraMetadataNative) {
    paramCameraMetadataNative = CameraMetadataNative.move(paramCameraMetadataNative);
    this.mProperties = paramCameraMetadataNative;
    setNativeInstance(paramCameraMetadataNative);
  }
  
  private <TKey> List<TKey> getAvailableKeyList(Class<?> paramClass, Class<TKey> paramClass1, int[] paramArrayOfint, boolean paramBoolean) {
    if (!paramClass.equals(CameraMetadata.class)) {
      if (CameraMetadata.class.isAssignableFrom(paramClass))
        return Collections.unmodifiableList((List)getKeys(paramClass, (Class)paramClass1, null, paramArrayOfint, paramBoolean)); 
      throw new AssertionError("metadataClass must be a subclass of CameraMetadata");
    } 
    throw new AssertionError("metadataClass must be a strict subclass of CameraMetadata");
  }
  
  public <T> T get(Key<T> paramKey) {
    return (T)this.mProperties.get(paramKey);
  }
  
  public List<CaptureRequest.Key<?>> getAvailableCaptureRequestKeys() {
    if (this.mAvailableRequestKeys == null) {
      Class<CaptureRequest.Key> clazz = CaptureRequest.Key.class;
      int[] arrayOfInt = get((Key)REQUEST_AVAILABLE_REQUEST_KEYS);
      if (arrayOfInt != null) {
        this.mAvailableRequestKeys = getAvailableKeyList(CaptureRequest.class, (Class)clazz, arrayOfInt, true);
      } else {
        throw new AssertionError("android.request.availableRequestKeys must be non-null in the characteristics");
      } 
    } 
    return this.mAvailableRequestKeys;
  }
  
  public List<CaptureResult.Key<?>> getAvailableCaptureResultKeys() {
    if (this.mAvailableResultKeys == null) {
      Class<CaptureResult.Key> clazz = CaptureResult.Key.class;
      int[] arrayOfInt = get((Key)REQUEST_AVAILABLE_RESULT_KEYS);
      if (arrayOfInt != null) {
        this.mAvailableResultKeys = getAvailableKeyList(CaptureResult.class, (Class)clazz, arrayOfInt, true);
      } else {
        throw new AssertionError("android.request.availableResultKeys must be non-null in the characteristics");
      } 
    } 
    return this.mAvailableResultKeys;
  }
  
  public List<CaptureRequest.Key<?>> getAvailablePhysicalCameraRequestKeys() {
    if (this.mAvailablePhysicalRequestKeys == null) {
      Class<CaptureRequest.Key> clazz = CaptureRequest.Key.class;
      int[] arrayOfInt = get((Key)REQUEST_AVAILABLE_PHYSICAL_CAMERA_REQUEST_KEYS);
      if (arrayOfInt == null)
        return null; 
      this.mAvailablePhysicalRequestKeys = getAvailableKeyList(CaptureRequest.class, (Class)clazz, arrayOfInt, false);
    } 
    return this.mAvailablePhysicalRequestKeys;
  }
  
  public List<CaptureRequest.Key<?>> getAvailableSessionKeys() {
    if (this.mAvailableSessionKeys == null) {
      Class<CaptureRequest.Key> clazz = CaptureRequest.Key.class;
      int[] arrayOfInt = get((Key)REQUEST_AVAILABLE_SESSION_KEYS);
      if (arrayOfInt == null)
        return null; 
      this.mAvailableSessionKeys = getAvailableKeyList(CaptureRequest.class, (Class)clazz, arrayOfInt, false);
    } 
    return this.mAvailableSessionKeys;
  }
  
  protected Class<Key<?>> getKeyClass() {
    return (Class)Key.class;
  }
  
  public List<Key<?>> getKeys() {
    List<Key<?>> list = this.mKeys;
    if (list != null)
      return list; 
    int[] arrayOfInt = get((Key)REQUEST_AVAILABLE_CHARACTERISTICS_KEYS);
    if (arrayOfInt != null) {
      List<Key<?>> list1 = Collections.unmodifiableList(getKeys(getClass(), getKeyClass(), this, arrayOfInt, true));
      this.mKeys = list1;
      return list1;
    } 
    throw new AssertionError("android.request.availableCharacteristicsKeys must be non-null in the characteristics");
  }
  
  public List<Key<?>> getKeysNeedingPermission() {
    if (this.mKeysNeedingPermission == null) {
      List<?> list;
      Class<Key> clazz = Key.class;
      int[] arrayOfInt = get((Key)REQUEST_CHARACTERISTIC_KEYS_NEEDING_PERMISSION);
      if (arrayOfInt == null) {
        list = Collections.unmodifiableList(new ArrayList());
        this.mKeysNeedingPermission = (List)list;
        return (List)list;
      } 
      this.mKeysNeedingPermission = getAvailableKeyList(CameraCharacteristics.class, (Class<?>)list, arrayOfInt, false);
    } 
    return this.mKeysNeedingPermission;
  }
  
  public CameraMetadataNative getNativeCopy() {
    return new CameraMetadataNative(this.mProperties);
  }
  
  public Set<String> getPhysicalCameraIds() {
    int[] arrayOfInt = get((Key)REQUEST_AVAILABLE_CAPABILITIES);
    if (arrayOfInt != null) {
      if (!ArrayUtils.contains(arrayOfInt, 11))
        return Collections.emptySet(); 
      byte[] arrayOfByte = get((Key)LOGICAL_MULTI_CAMERA_PHYSICAL_IDS);
      try {
        String str = new String(arrayOfByte, "UTF-8");
        return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(str.split("\000"))));
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        throw new AssertionError("android.logicalCam.physicalIds must be UTF-8 string");
      } 
    } 
    throw new AssertionError("android.request.availableCapabilities must be non-null in the characteristics");
  }
  
  protected <T> T getProtected(Key<?> paramKey) {
    return (T)this.mProperties.get(paramKey);
  }
  
  public RecommendedStreamConfigurationMap getRecommendedStreamConfigurationMap(int paramInt) {
    if ((paramInt >= 0 && paramInt <= 6) || (paramInt >= 24 && paramInt < 32)) {
      if (this.mRecommendedConfigurations == null) {
        ArrayList<RecommendedStreamConfigurationMap> arrayList = this.mProperties.getRecommendedStreamConfigurations();
        this.mRecommendedConfigurations = arrayList;
        if (arrayList == null)
          return null; 
      } 
      return this.mRecommendedConfigurations.get(paramInt);
    } 
    throw new IllegalArgumentException(String.format("Invalid use case: %d", new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public static final class Key<T> {
    private final CameraMetadataNative.Key<T> mKey;
    
    private Key(CameraMetadataNative.Key<?> param1Key) {
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
      return String.format("CameraCharacteristics.Key(%s)", new Object[] { this.mKey.getName() });
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraCharacteristics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.hardware.camera2;

import android.graphics.Rect;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.PublicKey;
import android.hardware.camera2.impl.SyntheticKey;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.RggbChannelVector;
import android.hardware.camera2.params.TonemapCurve;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.hardware.camera2.utils.SurfaceUtils;
import android.hardware.camera2.utils.TypeReference;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.util.SparseArray;
import android.view.Surface;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class CaptureRequest extends CameraMetadata<CaptureRequest.Key<?>> implements Parcelable {
  @PublicKey
  public static final Key<Boolean> BLACK_LEVEL_LOCK;
  
  @PublicKey
  public static final Key<Integer> COLOR_CORRECTION_ABERRATION_MODE;
  
  @PublicKey
  public static final Key<RggbChannelVector> COLOR_CORRECTION_GAINS;
  
  @PublicKey
  public static final Key<Integer> COLOR_CORRECTION_MODE;
  
  @PublicKey
  public static final Key<ColorSpaceTransform> COLOR_CORRECTION_TRANSFORM;
  
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
  public static final Key<Range<Integer>> CONTROL_AE_TARGET_FPS_RANGE;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AF_MODE;
  
  @PublicKey
  public static final Key<MeteringRectangle[]> CONTROL_AF_REGIONS;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AF_TRIGGER;
  
  @PublicKey
  public static final Key<Boolean> CONTROL_AWB_LOCK;
  
  @PublicKey
  public static final Key<Integer> CONTROL_AWB_MODE;
  
  @PublicKey
  public static final Key<MeteringRectangle[]> CONTROL_AWB_REGIONS;
  
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
  
  public static final Parcelable.Creator<CaptureRequest> CREATOR;
  
  @PublicKey
  public static final Key<Integer> DISTORTION_CORRECTION_MODE;
  
  @PublicKey
  public static final Key<Integer> EDGE_MODE;
  
  @PublicKey
  public static final Key<Integer> FLASH_MODE;
  
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
  public static final Key<Float> LENS_FILTER_DENSITY;
  
  @PublicKey
  public static final Key<Float> LENS_FOCAL_LENGTH;
  
  @PublicKey
  public static final Key<Float> LENS_FOCUS_DISTANCE;
  
  @PublicKey
  public static final Key<Integer> LENS_OPTICAL_STABILIZATION_MODE;
  
  @PublicKey
  public static final Key<Integer> NOISE_REDUCTION_MODE;
  
  @PublicKey
  public static final Key<Float> REPROCESS_EFFECTIVE_EXPOSURE_FACTOR;
  
  public static final Key<Integer> REQUEST_ID;
  
  public static final int REQUEST_TYPE_COUNT = 3;
  
  public static final int REQUEST_TYPE_REGULAR = 0;
  
  public static final int REQUEST_TYPE_REPROCESS = 1;
  
  public static final int REQUEST_TYPE_ZSL_STILL = 2;
  
  @PublicKey
  public static final Key<Rect> SCALER_CROP_REGION;
  
  public static final Key<Integer> SCALER_ROTATE_AND_CROP;
  
  @PublicKey
  public static final Key<Long> SENSOR_EXPOSURE_TIME;
  
  @PublicKey
  public static final Key<Long> SENSOR_FRAME_DURATION;
  
  @PublicKey
  public static final Key<Integer> SENSOR_SENSITIVITY;
  
  @PublicKey
  public static final Key<int[]> SENSOR_TEST_PATTERN_DATA;
  
  @PublicKey
  public static final Key<Integer> SENSOR_TEST_PATTERN_MODE;
  
  @PublicKey
  public static final Key<Integer> SHADING_MODE;
  
  @PublicKey
  public static final Key<Integer> STATISTICS_FACE_DETECT_MODE;
  
  @PublicKey
  public static final Key<Boolean> STATISTICS_HOT_PIXEL_MAP_MODE;
  
  @PublicKey
  public static final Key<Integer> STATISTICS_LENS_SHADING_MAP_MODE;
  
  @PublicKey
  public static final Key<Integer> STATISTICS_OIS_DATA_MODE;
  
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
  
  private static final ArraySet<Surface> mEmptySurfaceSet = new ArraySet();
  
  private final String TAG = "CaptureRequest-JV";
  
  private boolean mIsPartOfCHSRequestList = false;
  
  private boolean mIsReprocess;
  
  private String mLogicalCameraId;
  
  private CameraMetadataNative mLogicalCameraSettings;
  
  private final HashMap<String, CameraMetadataNative> mPhysicalCameraSettings = new HashMap<>();
  
  private int mReprocessableSessionId;
  
  private int mRequestType = -1;
  
  private int[] mStreamIdxArray;
  
  private boolean mSurfaceConverted = false;
  
  private int[] mSurfaceIdxArray;
  
  private final ArraySet<Surface> mSurfaceSet = new ArraySet();
  
  private final Object mSurfacesLock = new Object();
  
  private Object mUserTag;
  
  static {
    CREATOR = new Parcelable.Creator<CaptureRequest>() {
        public CaptureRequest createFromParcel(Parcel param1Parcel) {
          CaptureRequest captureRequest = new CaptureRequest();
          captureRequest.readFromParcel(param1Parcel);
          return captureRequest;
        }
        
        public CaptureRequest[] newArray(int param1Int) {
          return new CaptureRequest[param1Int];
        }
      };
    COLOR_CORRECTION_MODE = new Key<>("android.colorCorrection.mode", (Class)int.class);
    COLOR_CORRECTION_TRANSFORM = new Key<>("android.colorCorrection.transform", ColorSpaceTransform.class);
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
    CONTROL_AF_MODE = new Key<>("android.control.afMode", (Class)int.class);
    CONTROL_AF_REGIONS = (Key)new Key<>("android.control.afRegions", (Class)MeteringRectangle[].class);
    CONTROL_AF_TRIGGER = new Key<>("android.control.afTrigger", (Class)int.class);
    CONTROL_AWB_LOCK = new Key<>("android.control.awbLock", (Class)boolean.class);
    CONTROL_AWB_MODE = new Key<>("android.control.awbMode", (Class)int.class);
    CONTROL_AWB_REGIONS = (Key)new Key<>("android.control.awbRegions", (Class)MeteringRectangle[].class);
    CONTROL_CAPTURE_INTENT = new Key<>("android.control.captureIntent", (Class)int.class);
    CONTROL_EFFECT_MODE = new Key<>("android.control.effectMode", (Class)int.class);
    CONTROL_MODE = new Key<>("android.control.mode", (Class)int.class);
    CONTROL_SCENE_MODE = new Key<>("android.control.sceneMode", (Class)int.class);
    CONTROL_VIDEO_STABILIZATION_MODE = new Key<>("android.control.videoStabilizationMode", (Class)int.class);
    CONTROL_POST_RAW_SENSITIVITY_BOOST = new Key<>("android.control.postRawSensitivityBoost", (Class)int.class);
    CONTROL_ENABLE_ZSL = new Key<>("android.control.enableZsl", (Class)boolean.class);
    CONTROL_EXTENDED_SCENE_MODE = new Key<>("android.control.extendedSceneMode", (Class)int.class);
    CONTROL_ZOOM_RATIO = new Key<>("android.control.zoomRatio", (Class)float.class);
    EDGE_MODE = new Key<>("android.edge.mode", (Class)int.class);
    FLASH_MODE = new Key<>("android.flash.mode", (Class)int.class);
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
    LENS_OPTICAL_STABILIZATION_MODE = new Key<>("android.lens.opticalStabilizationMode", (Class)int.class);
    NOISE_REDUCTION_MODE = new Key<>("android.noiseReduction.mode", (Class)int.class);
    REQUEST_ID = new Key<>("android.request.id", (Class)int.class);
    SCALER_CROP_REGION = new Key<>("android.scaler.cropRegion", Rect.class);
    SCALER_ROTATE_AND_CROP = new Key<>("android.scaler.rotateAndCrop", (Class)int.class);
    SENSOR_EXPOSURE_TIME = new Key<>("android.sensor.exposureTime", (Class)long.class);
    SENSOR_FRAME_DURATION = new Key<>("android.sensor.frameDuration", (Class)long.class);
    SENSOR_SENSITIVITY = new Key<>("android.sensor.sensitivity", (Class)int.class);
    SENSOR_TEST_PATTERN_DATA = (Key)new Key<>("android.sensor.testPatternData", (Class)int[].class);
    SENSOR_TEST_PATTERN_MODE = new Key<>("android.sensor.testPatternMode", (Class)int.class);
    SHADING_MODE = new Key<>("android.shading.mode", (Class)int.class);
    STATISTICS_FACE_DETECT_MODE = new Key<>("android.statistics.faceDetectMode", (Class)int.class);
    STATISTICS_HOT_PIXEL_MAP_MODE = new Key<>("android.statistics.hotPixelMapMode", (Class)boolean.class);
    STATISTICS_LENS_SHADING_MAP_MODE = new Key<>("android.statistics.lensShadingMapMode", (Class)int.class);
    STATISTICS_OIS_DATA_MODE = new Key<>("android.statistics.oisDataMode", (Class)int.class);
    TONEMAP_CURVE_BLUE = (Key)new Key<>("android.tonemap.curveBlue", (Class)float[].class);
    TONEMAP_CURVE_GREEN = (Key)new Key<>("android.tonemap.curveGreen", (Class)float[].class);
    TONEMAP_CURVE_RED = (Key)new Key<>("android.tonemap.curveRed", (Class)float[].class);
    TONEMAP_CURVE = new Key<>("android.tonemap.curve", TonemapCurve.class);
    TONEMAP_MODE = new Key<>("android.tonemap.mode", (Class)int.class);
    TONEMAP_GAMMA = new Key<>("android.tonemap.gamma", (Class)float.class);
    TONEMAP_PRESET_CURVE = new Key<>("android.tonemap.presetCurve", (Class)int.class);
    LED_TRANSMIT = new Key<>("android.led.transmit", (Class)boolean.class);
    BLACK_LEVEL_LOCK = new Key<>("android.blackLevel.lock", (Class)boolean.class);
    REPROCESS_EFFECTIVE_EXPOSURE_FACTOR = new Key<>("android.reprocess.effectiveExposureFactor", (Class)float.class);
    DISTORTION_CORRECTION_MODE = new Key<>("android.distortionCorrection.mode", (Class)int.class);
  }
  
  private CaptureRequest() {
    this.mIsReprocess = false;
    this.mReprocessableSessionId = -1;
  }
  
  private CaptureRequest(CaptureRequest paramCaptureRequest) {
    this.mLogicalCameraId = new String(paramCaptureRequest.mLogicalCameraId);
    for (Map.Entry<String, CameraMetadataNative> entry : paramCaptureRequest.mPhysicalCameraSettings.entrySet())
      this.mPhysicalCameraSettings.put(new String((String)entry.getKey()), new CameraMetadataNative((CameraMetadataNative)entry.getValue())); 
    CameraMetadataNative cameraMetadataNative = this.mPhysicalCameraSettings.get(this.mLogicalCameraId);
    this.mLogicalCameraSettings = cameraMetadataNative;
    setNativeInstance(cameraMetadataNative);
    this.mSurfaceSet.addAll(paramCaptureRequest.mSurfaceSet);
    this.mIsReprocess = paramCaptureRequest.mIsReprocess;
    this.mIsPartOfCHSRequestList = paramCaptureRequest.mIsPartOfCHSRequestList;
    this.mReprocessableSessionId = paramCaptureRequest.mReprocessableSessionId;
    this.mUserTag = paramCaptureRequest.mUserTag;
  }
  
  private CaptureRequest(CameraMetadataNative paramCameraMetadataNative, boolean paramBoolean, int paramInt, String paramString, Set<String> paramSet) {
    if (paramSet == null || !paramBoolean) {
      this.mLogicalCameraId = paramString;
      paramCameraMetadataNative = CameraMetadataNative.move(paramCameraMetadataNative);
      this.mLogicalCameraSettings = paramCameraMetadataNative;
      this.mPhysicalCameraSettings.put(this.mLogicalCameraId, paramCameraMetadataNative);
      if (paramSet != null)
        for (String paramString : paramSet)
          this.mPhysicalCameraSettings.put(paramString, new CameraMetadataNative(this.mLogicalCameraSettings));  
      setNativeInstance(this.mLogicalCameraSettings);
      this.mIsReprocess = paramBoolean;
      if (paramBoolean) {
        if (paramInt != -1) {
          this.mReprocessableSessionId = paramInt;
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Create a reprocess capture request with an invalid session ID: ");
          stringBuilder.append(paramInt);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } else {
        this.mReprocessableSessionId = -1;
      } 
      return;
    } 
    throw new IllegalArgumentException("Create a reprocess capture request with with more than one physical camera is not supported!");
  }
  
  private boolean equals(CaptureRequest paramCaptureRequest) {
    boolean bool;
    if (paramCaptureRequest != null && Objects.equals(this.mUserTag, paramCaptureRequest.mUserTag) && this.mSurfaceSet.equals(paramCaptureRequest.mSurfaceSet) && this.mPhysicalCameraSettings.equals(paramCaptureRequest.mPhysicalCameraSettings) && this.mLogicalCameraId.equals(paramCaptureRequest.mLogicalCameraId) && this.mLogicalCameraSettings.equals(paramCaptureRequest.mLogicalCameraSettings) && this.mIsReprocess == paramCaptureRequest.mIsReprocess && this.mReprocessableSessionId == paramCaptureRequest.mReprocessableSessionId) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    if (i > 0) {
      boolean bool;
      this.mLogicalCameraId = paramParcel.readString();
      CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
      this.mLogicalCameraSettings = cameraMetadataNative;
      cameraMetadataNative.readFromParcel(paramParcel);
      setNativeInstance(this.mLogicalCameraSettings);
      this.mPhysicalCameraSettings.put(this.mLogicalCameraId, this.mLogicalCameraSettings);
      byte b;
      for (b = 1; b < i; b++) {
        String str = paramParcel.readString();
        CameraMetadataNative cameraMetadataNative1 = new CameraMetadataNative();
        cameraMetadataNative1.readFromParcel(paramParcel);
        this.mPhysicalCameraSettings.put(str, cameraMetadataNative1);
      } 
      i = paramParcel.readInt();
      b = 0;
      if (i == 0) {
        bool = false;
      } else {
        bool = true;
      } 
      this.mIsReprocess = bool;
      this.mReprocessableSessionId = -1;
      synchronized (this.mSurfacesLock) {
        this.mSurfaceSet.clear();
        Parcelable[] arrayOfParcelable = paramParcel.readParcelableArray(Surface.class.getClassLoader());
        if (arrayOfParcelable != null) {
          i = arrayOfParcelable.length;
          while (b < i) {
            Surface surface = (Surface)arrayOfParcelable[b];
            this.mSurfaceSet.add(surface);
            b++;
          } 
        } 
        if (paramParcel.readInt() == 0)
          return; 
        RuntimeException runtimeException = new RuntimeException();
        this("Reading cached CaptureRequest is not supported");
        throw runtimeException;
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Physical camera count");
    stringBuilder.append(i);
    stringBuilder.append(" should always be positive");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public boolean containsTarget(Surface paramSurface) {
    return this.mSurfaceSet.contains(paramSurface);
  }
  
  public void convertSurfaceToStreamId(SparseArray<OutputConfiguration> paramSparseArray) {
    synchronized (this.mSurfacesLock) {
      if (this.mSurfaceConverted) {
        Log.v("CaptureRequest-JV", "Cannot convert already converted surfaces!");
        return;
      } 
      this.mStreamIdxArray = new int[this.mSurfaceSet.size()];
      this.mSurfaceIdxArray = new int[this.mSurfaceSet.size()];
      int i = 0;
      for (Surface surface : this.mSurfaceSet) {
        int j;
        boolean bool2;
        boolean bool1 = false;
        byte b = 0;
        while (true) {
          j = i;
          bool2 = bool1;
          if (b < paramSparseArray.size()) {
            int k = paramSparseArray.keyAt(b);
            OutputConfiguration outputConfiguration = (OutputConfiguration)paramSparseArray.valueAt(b);
            byte b1 = 0;
            Iterator<Surface> iterator = outputConfiguration.getSurfaces().iterator();
            while (true) {
              j = i;
              bool2 = bool1;
              if (iterator.hasNext()) {
                if (surface == (Surface)iterator.next()) {
                  bool2 = true;
                  this.mStreamIdxArray[i] = k;
                  this.mSurfaceIdxArray[i] = b1;
                  j = i + 1;
                  break;
                } 
                b1++;
                continue;
              } 
              break;
            } 
            if (bool2)
              break; 
            b++;
            i = j;
            bool1 = bool2;
            continue;
          } 
          break;
        } 
        i = j;
        bool1 = bool2;
        if (!bool2) {
          long l = SurfaceUtils.getSurfaceId(surface);
          b = 0;
          while (true) {
            i = j;
            bool1 = bool2;
            if (b < paramSparseArray.size()) {
              int k = paramSparseArray.keyAt(b);
              OutputConfiguration outputConfiguration = (OutputConfiguration)paramSparseArray.valueAt(b);
              byte b1 = 0;
              Iterator<Surface> iterator = outputConfiguration.getSurfaces().iterator();
              while (true) {
                i = j;
                bool1 = bool2;
                if (iterator.hasNext()) {
                  if (l == SurfaceUtils.getSurfaceId(iterator.next())) {
                    bool1 = true;
                    this.mStreamIdxArray[j] = k;
                    this.mSurfaceIdxArray[j] = b1;
                    i = j + 1;
                    break;
                  } 
                  b1++;
                  continue;
                } 
                break;
              } 
              if (bool1)
                break; 
              b++;
              j = i;
              bool2 = bool1;
              continue;
            } 
            break;
          } 
        } 
        if (bool1)
          continue; 
        this.mStreamIdxArray = null;
        this.mSurfaceIdxArray = null;
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this("CaptureRequest contains unconfigured Input/Output Surface!");
        throw illegalArgumentException;
      } 
      this.mSurfaceConverted = true;
      return;
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool;
    if (paramObject instanceof CaptureRequest && equals((CaptureRequest)paramObject)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public <T> T get(Key<T> paramKey) {
    return (T)this.mLogicalCameraSettings.get(paramKey);
  }
  
  protected Class<Key<?>> getKeyClass() {
    return (Class)Key.class;
  }
  
  public List<Key<?>> getKeys() {
    return super.getKeys();
  }
  
  public String getLogicalCameraId() {
    return this.mLogicalCameraId;
  }
  
  public CameraMetadataNative getNativeCopy() {
    return new CameraMetadataNative(this.mLogicalCameraSettings);
  }
  
  protected <T> T getProtected(Key<?> paramKey) {
    return (T)this.mLogicalCameraSettings.get(paramKey);
  }
  
  public int getReprocessableSessionId() {
    if (this.mIsReprocess) {
      int i = this.mReprocessableSessionId;
      if (i != -1)
        return i; 
    } 
    throw new IllegalStateException("Getting the reprocessable session ID for a non-reprocess capture request is illegal.");
  }
  
  public int getRequestType() {
    if (this.mRequestType == -1)
      if (this.mIsReprocess) {
        this.mRequestType = 1;
      } else {
        Boolean bool = (Boolean)this.mLogicalCameraSettings.get(CONTROL_ENABLE_ZSL);
        byte b1 = 0;
        byte b2 = 2;
        byte b3 = b1;
        if (bool != null) {
          b3 = b1;
          if (bool.booleanValue()) {
            b3 = b1;
            if (((Integer)this.mLogicalCameraSettings.get(CONTROL_CAPTURE_INTENT)).intValue() == 2)
              b3 = 1; 
          } 
        } 
        if (b3) {
          b3 = b2;
        } else {
          b3 = 0;
        } 
        this.mRequestType = b3;
      }  
    return this.mRequestType;
  }
  
  public Object getTag() {
    return this.mUserTag;
  }
  
  public Collection<Surface> getTargets() {
    return Collections.unmodifiableCollection((Collection<? extends Surface>)this.mSurfaceSet);
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCodeGeneric(new Object[] { this.mPhysicalCameraSettings, this.mSurfaceSet, this.mUserTag });
  }
  
  public boolean isPartOfCRequestList() {
    return this.mIsPartOfCHSRequestList;
  }
  
  public boolean isReprocess() {
    return this.mIsReprocess;
  }
  
  public void recoverStreamIdToSurface() {
    synchronized (this.mSurfacesLock) {
      if (!this.mSurfaceConverted) {
        Log.v("CaptureRequest-JV", "Cannot convert already converted surfaces!");
        return;
      } 
      this.mStreamIdxArray = null;
      this.mSurfaceIdxArray = null;
      this.mSurfaceConverted = false;
      return;
    } 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mPhysicalCameraSettings.size());
    paramParcel.writeString(this.mLogicalCameraId);
    this.mLogicalCameraSettings.writeToParcel(paramParcel, paramInt);
    for (Map.Entry<String, CameraMetadataNative> entry : this.mPhysicalCameraSettings.entrySet()) {
      if (((String)entry.getKey()).equals(this.mLogicalCameraId))
        continue; 
      paramParcel.writeString((String)entry.getKey());
      ((CameraMetadataNative)entry.getValue()).writeToParcel(paramParcel, paramInt);
    } 
    paramParcel.writeInt(this.mIsReprocess);
    synchronized (this.mSurfacesLock) {
      ArraySet<Surface> arraySet;
      if (this.mSurfaceConverted) {
        arraySet = mEmptySurfaceSet;
      } else {
        arraySet = this.mSurfaceSet;
      } 
      paramParcel.writeParcelableArray((Parcelable[])arraySet.toArray((Object[])new Surface[arraySet.size()]), paramInt);
      if (this.mSurfaceConverted) {
        paramParcel.writeInt(this.mStreamIdxArray.length);
        for (paramInt = 0; paramInt < this.mStreamIdxArray.length; paramInt++) {
          paramParcel.writeInt(this.mStreamIdxArray[paramInt]);
          paramParcel.writeInt(this.mSurfaceIdxArray[paramInt]);
        } 
      } else {
        paramParcel.writeInt(0);
      } 
      return;
    } 
  }
  
  public static final class Builder {
    private final CaptureRequest mRequest;
    
    public Builder(CameraMetadataNative param1CameraMetadataNative, boolean param1Boolean, int param1Int, String param1String, Set<String> param1Set) {
      this.mRequest = new CaptureRequest(param1CameraMetadataNative, param1Boolean, param1Int, param1String, param1Set);
    }
    
    public void addTarget(Surface param1Surface) {
      this.mRequest.mSurfaceSet.add(param1Surface);
    }
    
    public CaptureRequest build() {
      return new CaptureRequest(this.mRequest);
    }
    
    public <T> T get(CaptureRequest.Key<T> param1Key) {
      return (T)this.mRequest.mLogicalCameraSettings.get(param1Key);
    }
    
    public <T> T getPhysicalCameraKey(CaptureRequest.Key<T> param1Key, String param1String) {
      if (this.mRequest.mPhysicalCameraSettings.containsKey(param1String))
        return (T)((CameraMetadataNative)this.mRequest.mPhysicalCameraSettings.get(param1String)).get(param1Key); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Physical camera id: ");
      stringBuilder.append(param1String);
      stringBuilder.append(" is not valid!");
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public boolean isEmpty() {
      return this.mRequest.mLogicalCameraSettings.isEmpty();
    }
    
    public void removeTarget(Surface param1Surface) {
      this.mRequest.mSurfaceSet.remove(param1Surface);
    }
    
    public <T> void set(CaptureRequest.Key<T> param1Key, T param1T) {
      this.mRequest.mLogicalCameraSettings.set(param1Key, param1T);
    }
    
    public void setPartOfCHSRequestList(boolean param1Boolean) {
      CaptureRequest.access$702(this.mRequest, param1Boolean);
    }
    
    public <T> Builder setPhysicalCameraKey(CaptureRequest.Key<T> param1Key, T param1T, String param1String) {
      if (this.mRequest.mPhysicalCameraSettings.containsKey(param1String)) {
        ((CameraMetadataNative)this.mRequest.mPhysicalCameraSettings.get(param1String)).set(param1Key, param1T);
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Physical camera id: ");
      stringBuilder.append(param1String);
      stringBuilder.append(" is not valid!");
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public void setTag(Object param1Object) {
      CaptureRequest.access$602(this.mRequest, param1Object);
    }
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
      return String.format("CaptureRequest.Key(%s)", new Object[] { this.mKey.getName() });
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CaptureRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
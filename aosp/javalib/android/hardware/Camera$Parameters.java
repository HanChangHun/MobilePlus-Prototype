package android.hardware;

import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

@Deprecated
public class Parameters {
  public static final String ANTIBANDING_50HZ = "50hz";
  
  public static final String ANTIBANDING_60HZ = "60hz";
  
  public static final String ANTIBANDING_AUTO = "auto";
  
  public static final String ANTIBANDING_OFF = "off";
  
  public static final String EFFECT_AQUA = "aqua";
  
  public static final String EFFECT_BLACKBOARD = "blackboard";
  
  public static final String EFFECT_MONO = "mono";
  
  public static final String EFFECT_NEGATIVE = "negative";
  
  public static final String EFFECT_NONE = "none";
  
  public static final String EFFECT_POSTERIZE = "posterize";
  
  public static final String EFFECT_SEPIA = "sepia";
  
  public static final String EFFECT_SOLARIZE = "solarize";
  
  public static final String EFFECT_WHITEBOARD = "whiteboard";
  
  private static final String FALSE = "false";
  
  public static final String FLASH_MODE_AUTO = "auto";
  
  public static final String FLASH_MODE_OFF = "off";
  
  public static final String FLASH_MODE_ON = "on";
  
  public static final String FLASH_MODE_RED_EYE = "red-eye";
  
  public static final String FLASH_MODE_TORCH = "torch";
  
  public static final int FOCUS_DISTANCE_FAR_INDEX = 2;
  
  public static final int FOCUS_DISTANCE_NEAR_INDEX = 0;
  
  public static final int FOCUS_DISTANCE_OPTIMAL_INDEX = 1;
  
  public static final String FOCUS_MODE_AUTO = "auto";
  
  public static final String FOCUS_MODE_CONTINUOUS_PICTURE = "continuous-picture";
  
  public static final String FOCUS_MODE_CONTINUOUS_VIDEO = "continuous-video";
  
  public static final String FOCUS_MODE_EDOF = "edof";
  
  public static final String FOCUS_MODE_FIXED = "fixed";
  
  public static final String FOCUS_MODE_INFINITY = "infinity";
  
  public static final String FOCUS_MODE_MACRO = "macro";
  
  private static final String KEY_ANTIBANDING = "antibanding";
  
  private static final String KEY_AUTO_EXPOSURE_LOCK = "auto-exposure-lock";
  
  private static final String KEY_AUTO_EXPOSURE_LOCK_SUPPORTED = "auto-exposure-lock-supported";
  
  private static final String KEY_AUTO_WHITEBALANCE_LOCK = "auto-whitebalance-lock";
  
  private static final String KEY_AUTO_WHITEBALANCE_LOCK_SUPPORTED = "auto-whitebalance-lock-supported";
  
  private static final String KEY_EFFECT = "effect";
  
  private static final String KEY_EXPOSURE_COMPENSATION = "exposure-compensation";
  
  private static final String KEY_EXPOSURE_COMPENSATION_STEP = "exposure-compensation-step";
  
  private static final String KEY_FLASH_MODE = "flash-mode";
  
  private static final String KEY_FOCAL_LENGTH = "focal-length";
  
  private static final String KEY_FOCUS_AREAS = "focus-areas";
  
  private static final String KEY_FOCUS_DISTANCES = "focus-distances";
  
  private static final String KEY_FOCUS_MODE = "focus-mode";
  
  private static final String KEY_GPS_ALTITUDE = "gps-altitude";
  
  private static final String KEY_GPS_LATITUDE = "gps-latitude";
  
  private static final String KEY_GPS_LONGITUDE = "gps-longitude";
  
  private static final String KEY_GPS_PROCESSING_METHOD = "gps-processing-method";
  
  private static final String KEY_GPS_TIMESTAMP = "gps-timestamp";
  
  private static final String KEY_HORIZONTAL_VIEW_ANGLE = "horizontal-view-angle";
  
  private static final String KEY_JPEG_QUALITY = "jpeg-quality";
  
  private static final String KEY_JPEG_THUMBNAIL_HEIGHT = "jpeg-thumbnail-height";
  
  private static final String KEY_JPEG_THUMBNAIL_QUALITY = "jpeg-thumbnail-quality";
  
  private static final String KEY_JPEG_THUMBNAIL_SIZE = "jpeg-thumbnail-size";
  
  private static final String KEY_JPEG_THUMBNAIL_WIDTH = "jpeg-thumbnail-width";
  
  private static final String KEY_MAX_EXPOSURE_COMPENSATION = "max-exposure-compensation";
  
  private static final String KEY_MAX_NUM_DETECTED_FACES_HW = "max-num-detected-faces-hw";
  
  private static final String KEY_MAX_NUM_DETECTED_FACES_SW = "max-num-detected-faces-sw";
  
  private static final String KEY_MAX_NUM_FOCUS_AREAS = "max-num-focus-areas";
  
  private static final String KEY_MAX_NUM_METERING_AREAS = "max-num-metering-areas";
  
  private static final String KEY_MAX_ZOOM = "max-zoom";
  
  private static final String KEY_METERING_AREAS = "metering-areas";
  
  private static final String KEY_MIN_EXPOSURE_COMPENSATION = "min-exposure-compensation";
  
  private static final String KEY_PICTURE_FORMAT = "picture-format";
  
  private static final String KEY_PICTURE_SIZE = "picture-size";
  
  private static final String KEY_PREFERRED_PREVIEW_SIZE_FOR_VIDEO = "preferred-preview-size-for-video";
  
  private static final String KEY_PREVIEW_FORMAT = "preview-format";
  
  private static final String KEY_PREVIEW_FPS_RANGE = "preview-fps-range";
  
  private static final String KEY_PREVIEW_FRAME_RATE = "preview-frame-rate";
  
  private static final String KEY_PREVIEW_SIZE = "preview-size";
  
  private static final String KEY_RECORDING_HINT = "recording-hint";
  
  private static final String KEY_ROTATION = "rotation";
  
  private static final String KEY_SCENE_MODE = "scene-mode";
  
  private static final String KEY_SMOOTH_ZOOM_SUPPORTED = "smooth-zoom-supported";
  
  private static final String KEY_VERTICAL_VIEW_ANGLE = "vertical-view-angle";
  
  private static final String KEY_VIDEO_SIZE = "video-size";
  
  private static final String KEY_VIDEO_SNAPSHOT_SUPPORTED = "video-snapshot-supported";
  
  private static final String KEY_VIDEO_STABILIZATION = "video-stabilization";
  
  private static final String KEY_VIDEO_STABILIZATION_SUPPORTED = "video-stabilization-supported";
  
  private static final String KEY_WHITE_BALANCE = "whitebalance";
  
  private static final String KEY_ZOOM = "zoom";
  
  private static final String KEY_ZOOM_RATIOS = "zoom-ratios";
  
  private static final String KEY_ZOOM_SUPPORTED = "zoom-supported";
  
  private static final String PIXEL_FORMAT_BAYER_RGGB = "bayer-rggb";
  
  private static final String PIXEL_FORMAT_JPEG = "jpeg";
  
  private static final String PIXEL_FORMAT_RGB565 = "rgb565";
  
  private static final String PIXEL_FORMAT_YUV420P = "yuv420p";
  
  private static final String PIXEL_FORMAT_YUV420SP = "yuv420sp";
  
  private static final String PIXEL_FORMAT_YUV422I = "yuv422i-yuyv";
  
  private static final String PIXEL_FORMAT_YUV422SP = "yuv422sp";
  
  public static final int PREVIEW_FPS_MAX_INDEX = 1;
  
  public static final int PREVIEW_FPS_MIN_INDEX = 0;
  
  public static final String SCENE_MODE_ACTION = "action";
  
  public static final String SCENE_MODE_AUTO = "auto";
  
  public static final String SCENE_MODE_BARCODE = "barcode";
  
  public static final String SCENE_MODE_BEACH = "beach";
  
  public static final String SCENE_MODE_CANDLELIGHT = "candlelight";
  
  public static final String SCENE_MODE_FIREWORKS = "fireworks";
  
  public static final String SCENE_MODE_HDR = "hdr";
  
  public static final String SCENE_MODE_LANDSCAPE = "landscape";
  
  public static final String SCENE_MODE_NIGHT = "night";
  
  public static final String SCENE_MODE_NIGHT_PORTRAIT = "night-portrait";
  
  public static final String SCENE_MODE_PARTY = "party";
  
  public static final String SCENE_MODE_PORTRAIT = "portrait";
  
  public static final String SCENE_MODE_SNOW = "snow";
  
  public static final String SCENE_MODE_SPORTS = "sports";
  
  public static final String SCENE_MODE_STEADYPHOTO = "steadyphoto";
  
  public static final String SCENE_MODE_SUNSET = "sunset";
  
  public static final String SCENE_MODE_THEATRE = "theatre";
  
  private static final String SUPPORTED_VALUES_SUFFIX = "-values";
  
  private static final String TRUE = "true";
  
  public static final String WHITE_BALANCE_AUTO = "auto";
  
  public static final String WHITE_BALANCE_CLOUDY_DAYLIGHT = "cloudy-daylight";
  
  public static final String WHITE_BALANCE_DAYLIGHT = "daylight";
  
  public static final String WHITE_BALANCE_FLUORESCENT = "fluorescent";
  
  public static final String WHITE_BALANCE_INCANDESCENT = "incandescent";
  
  public static final String WHITE_BALANCE_SHADE = "shade";
  
  public static final String WHITE_BALANCE_TWILIGHT = "twilight";
  
  public static final String WHITE_BALANCE_WARM_FLUORESCENT = "warm-fluorescent";
  
  private final LinkedHashMap<String, String> mMap = new LinkedHashMap<>(64);
  
  private Parameters() {}
  
  private String cameraFormatForPixelFormat(int paramInt) {
    return (paramInt != 4) ? ((paramInt != 20) ? ((paramInt != 256) ? ((paramInt != 842094169) ? ((paramInt != 16) ? ((paramInt != 17) ? null : "yuv420sp") : "yuv422sp") : "yuv420p") : "jpeg") : "yuv422i-yuyv") : "rgb565";
  }
  
  private float getFloat(String paramString, float paramFloat) {
    try {
      return Float.parseFloat(this.mMap.get(paramString));
    } catch (NumberFormatException numberFormatException) {
      return paramFloat;
    } 
  }
  
  private int getInt(String paramString, int paramInt) {
    try {
      return Integer.parseInt(this.mMap.get(paramString));
    } catch (NumberFormatException numberFormatException) {
      return paramInt;
    } 
  }
  
  private Camera getOuter() {
    return Camera.this;
  }
  
  private int pixelFormatForCameraFormat(String paramString) {
    return (paramString == null) ? 0 : (paramString.equals("yuv422sp") ? 16 : (paramString.equals("yuv420sp") ? 17 : (paramString.equals("yuv422i-yuyv") ? 20 : (paramString.equals("yuv420p") ? 842094169 : (paramString.equals("rgb565") ? 4 : (paramString.equals("jpeg") ? 256 : 0))))));
  }
  
  private void put(String paramString1, String paramString2) {
    this.mMap.remove(paramString1);
    this.mMap.put(paramString1, paramString2);
  }
  
  private boolean same(String paramString1, String paramString2) {
    return (paramString1 == null && paramString2 == null) ? true : ((paramString1 != null && paramString1.equals(paramString2)));
  }
  
  private void set(String paramString, List<Camera.Area> paramList) {
    if (paramList == null) {
      set(paramString, "(0,0,0,0,0)");
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      for (byte b = 0; b < paramList.size(); b++) {
        Camera.Area area = paramList.get(b);
        Rect rect = area.rect;
        stringBuilder.append('(');
        stringBuilder.append(rect.left);
        stringBuilder.append(',');
        stringBuilder.append(rect.top);
        stringBuilder.append(',');
        stringBuilder.append(rect.right);
        stringBuilder.append(',');
        stringBuilder.append(rect.bottom);
        stringBuilder.append(',');
        stringBuilder.append(area.weight);
        stringBuilder.append(')');
        if (b != paramList.size() - 1)
          stringBuilder.append(','); 
      } 
      set(paramString, stringBuilder.toString());
    } 
  }
  
  private ArrayList<String> split(String paramString) {
    if (paramString == null)
      return null; 
    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
    simpleStringSplitter.setString(paramString);
    ArrayList<String> arrayList = new ArrayList();
    Iterator<String> iterator = simpleStringSplitter.iterator();
    while (iterator.hasNext())
      arrayList.add(iterator.next()); 
    return arrayList;
  }
  
  private ArrayList<Camera.Area> splitArea(String paramString) {
    if (paramString == null || paramString.charAt(0) != '(' || paramString.charAt(paramString.length() - 1) != ')') {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid area string=");
      stringBuilder.append(paramString);
      Log.e("Camera", stringBuilder.toString());
      return null;
    } 
    ArrayList<Camera.Area> arrayList = new ArrayList();
    int i = 1;
    int[] arrayOfInt = new int[5];
    while (true) {
      int j = paramString.indexOf("),(", i);
      int k = j;
      if (j == -1)
        k = paramString.length() - 1; 
      splitInt(paramString.substring(i, k), arrayOfInt);
      arrayList.add(new Camera.Area(new Rect(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]), arrayOfInt[4]));
      i = k + 3;
      if (k == paramString.length() - 1) {
        if (arrayList.size() == 0)
          return null; 
        if (arrayList.size() == 1) {
          Camera.Area area = arrayList.get(0);
          Rect rect = area.rect;
          if (rect.left == 0 && rect.top == 0 && rect.right == 0 && rect.bottom == 0 && area.weight == 0)
            return null; 
        } 
        return arrayList;
      } 
    } 
  }
  
  private void splitFloat(String paramString, float[] paramArrayOffloat) {
    if (paramString == null)
      return; 
    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
    simpleStringSplitter.setString(paramString);
    byte b = 0;
    Iterator iterator = simpleStringSplitter.iterator();
    while (iterator.hasNext()) {
      paramArrayOffloat[b] = Float.parseFloat((String)iterator.next());
      b++;
    } 
  }
  
  private ArrayList<Integer> splitInt(String paramString) {
    if (paramString == null)
      return null; 
    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
    simpleStringSplitter.setString(paramString);
    ArrayList<Integer> arrayList = new ArrayList();
    Iterator<String> iterator = simpleStringSplitter.iterator();
    while (iterator.hasNext())
      arrayList.add(Integer.valueOf(Integer.parseInt(iterator.next()))); 
    return (arrayList.size() == 0) ? null : arrayList;
  }
  
  private void splitInt(String paramString, int[] paramArrayOfint) {
    if (paramString == null)
      return; 
    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
    simpleStringSplitter.setString(paramString);
    byte b = 0;
    Iterator iterator = simpleStringSplitter.iterator();
    while (iterator.hasNext()) {
      paramArrayOfint[b] = Integer.parseInt((String)iterator.next());
      b++;
    } 
  }
  
  private ArrayList<int[]> splitRange(String paramString) {
    if (paramString == null || paramString.charAt(0) != '(' || paramString.charAt(paramString.length() - 1) != ')') {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid range list string=");
      stringBuilder.append(paramString);
      Log.e("Camera", stringBuilder.toString());
      return null;
    } 
    ArrayList<int[]> arrayList = new ArrayList();
    int i = 1;
    while (true) {
      int[] arrayOfInt = new int[2];
      int j = paramString.indexOf("),(", i);
      int k = j;
      if (j == -1)
        k = paramString.length() - 1; 
      splitInt(paramString.substring(i, k), arrayOfInt);
      arrayList.add(arrayOfInt);
      i = k + 3;
      if (k == paramString.length() - 1)
        return (arrayList.size() == 0) ? null : arrayList; 
    } 
  }
  
  private ArrayList<Camera.Size> splitSize(String paramString) {
    if (paramString == null)
      return null; 
    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
    simpleStringSplitter.setString(paramString);
    ArrayList<Camera.Size> arrayList = new ArrayList();
    Iterator<String> iterator = simpleStringSplitter.iterator();
    while (iterator.hasNext()) {
      Camera.Size size = strToSize(iterator.next());
      if (size != null)
        arrayList.add(size); 
    } 
    return (arrayList.size() == 0) ? null : arrayList;
  }
  
  private Camera.Size strToSize(String paramString) {
    if (paramString == null)
      return null; 
    int i = paramString.indexOf('x');
    if (i != -1) {
      String str = paramString.substring(0, i);
      paramString = paramString.substring(i + 1);
      return new Camera.Size(Camera.this, Integer.parseInt(str), Integer.parseInt(paramString));
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid size parameter string=");
    stringBuilder.append(paramString);
    Log.e("Camera", stringBuilder.toString());
    return null;
  }
  
  public void copyFrom(Parameters paramParameters) {
    if (paramParameters != null) {
      this.mMap.putAll(paramParameters.mMap);
      return;
    } 
    throw new NullPointerException("other must not be null");
  }
  
  @Deprecated
  public void dump() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("dump: size=");
    stringBuilder.append(this.mMap.size());
    Log.e("Camera", stringBuilder.toString());
    for (String str : this.mMap.keySet()) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("dump: ");
      stringBuilder1.append(str);
      stringBuilder1.append("=");
      stringBuilder1.append(this.mMap.get(str));
      Log.e("Camera", stringBuilder1.toString());
    } 
  }
  
  public String flatten() {
    StringBuilder stringBuilder = new StringBuilder(128);
    for (String str : this.mMap.keySet()) {
      stringBuilder.append(str);
      stringBuilder.append("=");
      stringBuilder.append(this.mMap.get(str));
      stringBuilder.append(";");
    } 
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    return stringBuilder.toString();
  }
  
  public String get(String paramString) {
    return this.mMap.get(paramString);
  }
  
  public String getAntibanding() {
    return get("antibanding");
  }
  
  public boolean getAutoExposureLock() {
    return "true".equals(get("auto-exposure-lock"));
  }
  
  public boolean getAutoWhiteBalanceLock() {
    return "true".equals(get("auto-whitebalance-lock"));
  }
  
  public String getColorEffect() {
    return get("effect");
  }
  
  public int getExposureCompensation() {
    return getInt("exposure-compensation", 0);
  }
  
  public float getExposureCompensationStep() {
    return getFloat("exposure-compensation-step", 0.0F);
  }
  
  public String getFlashMode() {
    return get("flash-mode");
  }
  
  public float getFocalLength() {
    return Float.parseFloat(get("focal-length"));
  }
  
  public List<Camera.Area> getFocusAreas() {
    return splitArea(get("focus-areas"));
  }
  
  public void getFocusDistances(float[] paramArrayOffloat) {
    if (paramArrayOffloat != null && paramArrayOffloat.length == 3) {
      splitFloat(get("focus-distances"), paramArrayOffloat);
      return;
    } 
    throw new IllegalArgumentException("output must be a float array with three elements.");
  }
  
  public String getFocusMode() {
    return get("focus-mode");
  }
  
  public float getHorizontalViewAngle() {
    return Float.parseFloat(get("horizontal-view-angle"));
  }
  
  public int getInt(String paramString) {
    return Integer.parseInt(this.mMap.get(paramString));
  }
  
  public int getJpegQuality() {
    return getInt("jpeg-quality");
  }
  
  public int getJpegThumbnailQuality() {
    return getInt("jpeg-thumbnail-quality");
  }
  
  public Camera.Size getJpegThumbnailSize() {
    return new Camera.Size(Camera.this, getInt("jpeg-thumbnail-width"), getInt("jpeg-thumbnail-height"));
  }
  
  public int getMaxExposureCompensation() {
    return getInt("max-exposure-compensation", 0);
  }
  
  public int getMaxNumDetectedFaces() {
    return getInt("max-num-detected-faces-hw", 0);
  }
  
  public int getMaxNumFocusAreas() {
    return getInt("max-num-focus-areas", 0);
  }
  
  public int getMaxNumMeteringAreas() {
    return getInt("max-num-metering-areas", 0);
  }
  
  public int getMaxZoom() {
    return getInt("max-zoom", 0);
  }
  
  public List<Camera.Area> getMeteringAreas() {
    return splitArea(get("metering-areas"));
  }
  
  public int getMinExposureCompensation() {
    return getInt("min-exposure-compensation", 0);
  }
  
  public int getPictureFormat() {
    return pixelFormatForCameraFormat(get("picture-format"));
  }
  
  public Camera.Size getPictureSize() {
    return strToSize(get("picture-size"));
  }
  
  public Camera.Size getPreferredPreviewSizeForVideo() {
    return strToSize(get("preferred-preview-size-for-video"));
  }
  
  public int getPreviewFormat() {
    return pixelFormatForCameraFormat(get("preview-format"));
  }
  
  public void getPreviewFpsRange(int[] paramArrayOfint) {
    if (paramArrayOfint != null && paramArrayOfint.length == 2) {
      splitInt(get("preview-fps-range"), paramArrayOfint);
      return;
    } 
    throw new IllegalArgumentException("range must be an array with two elements.");
  }
  
  @Deprecated
  public int getPreviewFrameRate() {
    return getInt("preview-frame-rate");
  }
  
  public Camera.Size getPreviewSize() {
    return strToSize(get("preview-size"));
  }
  
  public String getSceneMode() {
    return get("scene-mode");
  }
  
  public List<String> getSupportedAntibanding() {
    return split(get("antibanding-values"));
  }
  
  public List<String> getSupportedColorEffects() {
    return split(get("effect-values"));
  }
  
  public List<String> getSupportedFlashModes() {
    return split(get("flash-mode-values"));
  }
  
  public List<String> getSupportedFocusModes() {
    return split(get("focus-mode-values"));
  }
  
  public List<Camera.Size> getSupportedJpegThumbnailSizes() {
    return splitSize(get("jpeg-thumbnail-size-values"));
  }
  
  public List<Integer> getSupportedPictureFormats() {
    String str = get("picture-format-values");
    ArrayList<Integer> arrayList = new ArrayList();
    Iterator<String> iterator = split(str).iterator();
    while (iterator.hasNext()) {
      int i = pixelFormatForCameraFormat(iterator.next());
      if (i == 0)
        continue; 
      arrayList.add(Integer.valueOf(i));
    } 
    return arrayList;
  }
  
  public List<Camera.Size> getSupportedPictureSizes() {
    return splitSize(get("picture-size-values"));
  }
  
  public List<Integer> getSupportedPreviewFormats() {
    String str = get("preview-format-values");
    ArrayList<Integer> arrayList = new ArrayList();
    Iterator<String> iterator = split(str).iterator();
    while (iterator.hasNext()) {
      int i = pixelFormatForCameraFormat(iterator.next());
      if (i == 0)
        continue; 
      arrayList.add(Integer.valueOf(i));
    } 
    return arrayList;
  }
  
  public List<int[]> getSupportedPreviewFpsRange() {
    return (List<int[]>)splitRange(get("preview-fps-range-values"));
  }
  
  @Deprecated
  public List<Integer> getSupportedPreviewFrameRates() {
    return splitInt(get("preview-frame-rate-values"));
  }
  
  public List<Camera.Size> getSupportedPreviewSizes() {
    return splitSize(get("preview-size-values"));
  }
  
  public List<String> getSupportedSceneModes() {
    return split(get("scene-mode-values"));
  }
  
  public List<Camera.Size> getSupportedVideoSizes() {
    return splitSize(get("video-size-values"));
  }
  
  public List<String> getSupportedWhiteBalance() {
    return split(get("whitebalance-values"));
  }
  
  public float getVerticalViewAngle() {
    return Float.parseFloat(get("vertical-view-angle"));
  }
  
  public boolean getVideoStabilization() {
    return "true".equals(get("video-stabilization"));
  }
  
  public String getWhiteBalance() {
    return get("whitebalance");
  }
  
  public int getZoom() {
    return getInt("zoom", 0);
  }
  
  public List<Integer> getZoomRatios() {
    return splitInt(get("zoom-ratios"));
  }
  
  public boolean isAutoExposureLockSupported() {
    return "true".equals(get("auto-exposure-lock-supported"));
  }
  
  public boolean isAutoWhiteBalanceLockSupported() {
    return "true".equals(get("auto-whitebalance-lock-supported"));
  }
  
  public boolean isSmoothZoomSupported() {
    return "true".equals(get("smooth-zoom-supported"));
  }
  
  public boolean isVideoSnapshotSupported() {
    return "true".equals(get("video-snapshot-supported"));
  }
  
  public boolean isVideoStabilizationSupported() {
    return "true".equals(get("video-stabilization-supported"));
  }
  
  public boolean isZoomSupported() {
    return "true".equals(get("zoom-supported"));
  }
  
  public void remove(String paramString) {
    this.mMap.remove(paramString);
  }
  
  public void removeGpsData() {
    remove("gps-latitude");
    remove("gps-longitude");
    remove("gps-altitude");
    remove("gps-timestamp");
    remove("gps-processing-method");
  }
  
  public boolean same(Parameters paramParameters) {
    boolean bool = true;
    if (this == paramParameters)
      return true; 
    if (paramParameters == null || !this.mMap.equals(paramParameters.mMap))
      bool = false; 
    return bool;
  }
  
  public void set(String paramString, int paramInt) {
    put(paramString, Integer.toString(paramInt));
  }
  
  public void set(String paramString1, String paramString2) {
    StringBuilder stringBuilder1;
    StringBuilder stringBuilder2;
    if (paramString1.indexOf('=') != -1 || paramString1.indexOf(';') != -1 || paramString1.indexOf(false) != -1) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Key \"");
      stringBuilder2.append(paramString1);
      stringBuilder2.append("\" contains invalid character (= or ; or \\0)");
      Log.e("Camera", stringBuilder2.toString());
      return;
    } 
    if (stringBuilder2.indexOf('=') != -1 || stringBuilder2.indexOf(';') != -1 || stringBuilder2.indexOf(false) != -1) {
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Value \"");
      stringBuilder1.append((String)stringBuilder2);
      stringBuilder1.append("\" contains invalid character (= or ; or \\0)");
      Log.e("Camera", stringBuilder1.toString());
      return;
    } 
    put((String)stringBuilder1, (String)stringBuilder2);
  }
  
  public void setAntibanding(String paramString) {
    set("antibanding", paramString);
  }
  
  public void setAutoExposureLock(boolean paramBoolean) {
    String str;
    if (paramBoolean) {
      str = "true";
    } else {
      str = "false";
    } 
    set("auto-exposure-lock", str);
  }
  
  public void setAutoWhiteBalanceLock(boolean paramBoolean) {
    String str;
    if (paramBoolean) {
      str = "true";
    } else {
      str = "false";
    } 
    set("auto-whitebalance-lock", str);
  }
  
  public void setColorEffect(String paramString) {
    set("effect", paramString);
  }
  
  public void setExposureCompensation(int paramInt) {
    set("exposure-compensation", paramInt);
  }
  
  public void setFlashMode(String paramString) {
    set("flash-mode", paramString);
  }
  
  public void setFocusAreas(List<Camera.Area> paramList) {
    set("focus-areas", paramList);
  }
  
  public void setFocusMode(String paramString) {
    set("focus-mode", paramString);
  }
  
  public void setGpsAltitude(double paramDouble) {
    set("gps-altitude", Double.toString(paramDouble));
  }
  
  public void setGpsLatitude(double paramDouble) {
    set("gps-latitude", Double.toString(paramDouble));
  }
  
  public void setGpsLongitude(double paramDouble) {
    set("gps-longitude", Double.toString(paramDouble));
  }
  
  public void setGpsProcessingMethod(String paramString) {
    set("gps-processing-method", paramString);
  }
  
  public void setGpsTimestamp(long paramLong) {
    set("gps-timestamp", Long.toString(paramLong));
  }
  
  public void setJpegQuality(int paramInt) {
    set("jpeg-quality", paramInt);
  }
  
  public void setJpegThumbnailQuality(int paramInt) {
    set("jpeg-thumbnail-quality", paramInt);
  }
  
  public void setJpegThumbnailSize(int paramInt1, int paramInt2) {
    set("jpeg-thumbnail-width", paramInt1);
    set("jpeg-thumbnail-height", paramInt2);
  }
  
  public void setMeteringAreas(List<Camera.Area> paramList) {
    set("metering-areas", paramList);
  }
  
  public void setPictureFormat(int paramInt) {
    String str = cameraFormatForPixelFormat(paramInt);
    if (str != null) {
      set("picture-format", str);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid pixel_format=");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setPictureSize(int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Integer.toString(paramInt1));
    stringBuilder.append("x");
    stringBuilder.append(Integer.toString(paramInt2));
    set("picture-size", stringBuilder.toString());
  }
  
  public void setPreviewFormat(int paramInt) {
    String str = cameraFormatForPixelFormat(paramInt);
    if (str != null) {
      set("preview-format", str);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid pixel_format=");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setPreviewFpsRange(int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramInt1);
    stringBuilder.append(",");
    stringBuilder.append(paramInt2);
    set("preview-fps-range", stringBuilder.toString());
  }
  
  @Deprecated
  public void setPreviewFrameRate(int paramInt) {
    set("preview-frame-rate", paramInt);
  }
  
  public void setPreviewSize(int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Integer.toString(paramInt1));
    stringBuilder.append("x");
    stringBuilder.append(Integer.toString(paramInt2));
    set("preview-size", stringBuilder.toString());
  }
  
  public void setRecordingHint(boolean paramBoolean) {
    String str;
    if (paramBoolean) {
      str = "true";
    } else {
      str = "false";
    } 
    set("recording-hint", str);
  }
  
  public void setRotation(int paramInt) {
    if (paramInt == 0 || paramInt == 90 || paramInt == 180 || paramInt == 270) {
      set("rotation", Integer.toString(paramInt));
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid rotation=");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setSceneMode(String paramString) {
    set("scene-mode", paramString);
  }
  
  public void setVideoStabilization(boolean paramBoolean) {
    String str;
    if (paramBoolean) {
      str = "true";
    } else {
      str = "false";
    } 
    set("video-stabilization", str);
  }
  
  public void setWhiteBalance(String paramString) {
    if (same(paramString, get("whitebalance")))
      return; 
    set("whitebalance", paramString);
    set("auto-whitebalance-lock", "false");
  }
  
  public void setZoom(int paramInt) {
    set("zoom", paramInt);
  }
  
  public void unflatten(String paramString) {
    this.mMap.clear();
    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(';');
    simpleStringSplitter.setString(paramString);
    for (String str : simpleStringSplitter) {
      int i = str.indexOf('=');
      if (i == -1)
        continue; 
      paramString = str.substring(0, i);
      str = str.substring(i + 1);
      this.mMap.put(paramString, str);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/Camera$Parameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
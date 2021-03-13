package android.hardware.camera2.legacy;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.CameraInfo;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.params.StreamConfigurationDuration;
import android.hardware.camera2.utils.ArrayUtils;
import android.hardware.camera2.utils.ListUtils;
import android.hardware.camera2.utils.ParamsUtils;
import android.hardware.camera2.utils.SizeAreaComparator;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.util.SizeF;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class LegacyMetadataMapper {
  private static final long APPROXIMATE_CAPTURE_DELAY_MS = 200L;
  
  private static final long APPROXIMATE_JPEG_ENCODE_TIME_MS = 600L;
  
  private static final long APPROXIMATE_SENSOR_AREA_PX = 8388608L;
  
  private static final boolean DEBUG = false;
  
  public static final int HAL_PIXEL_FORMAT_BGRA_8888 = 5;
  
  public static final int HAL_PIXEL_FORMAT_BLOB = 33;
  
  public static final int HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED = 34;
  
  public static final int HAL_PIXEL_FORMAT_RGBA_8888 = 1;
  
  private static final float LENS_INFO_MINIMUM_FOCUS_DISTANCE_FIXED_FOCUS = 0.0F;
  
  static final boolean LIE_ABOUT_AE_MAX_REGIONS = false;
  
  static final boolean LIE_ABOUT_AE_STATE = false;
  
  static final boolean LIE_ABOUT_AF = false;
  
  static final boolean LIE_ABOUT_AF_MAX_REGIONS = false;
  
  static final boolean LIE_ABOUT_AWB = false;
  
  static final boolean LIE_ABOUT_AWB_STATE = false;
  
  private static final long NS_PER_MS = 1000000L;
  
  private static final float PREVIEW_ASPECT_RATIO_TOLERANCE = 0.01F;
  
  private static final int REQUEST_MAX_NUM_INPUT_STREAMS_COUNT = 0;
  
  private static final int REQUEST_MAX_NUM_OUTPUT_STREAMS_COUNT_PROC = 3;
  
  private static final int REQUEST_MAX_NUM_OUTPUT_STREAMS_COUNT_PROC_STALL = 1;
  
  private static final int REQUEST_MAX_NUM_OUTPUT_STREAMS_COUNT_RAW = 0;
  
  private static final int REQUEST_PIPELINE_MAX_DEPTH_HAL1 = 3;
  
  private static final int REQUEST_PIPELINE_MAX_DEPTH_OURS = 3;
  
  private static final String TAG = "LegacyMetadataMapper";
  
  static final int UNKNOWN_MODE = -1;
  
  private static final int[] sAllowedTemplates;
  
  private static final int[] sEffectModes;
  
  private static final String[] sLegacyEffectMode;
  
  private static final String[] sLegacySceneModes = new String[] { 
      "auto", "action", "portrait", "landscape", "night", "night-portrait", "theatre", "beach", "snow", "sunset", 
      "steadyphoto", "fireworks", "sports", "party", "candlelight", "barcode", "hdr" };
  
  private static final int[] sSceneModes = new int[] { 
      0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
      11, 12, 13, 14, 15, 16, 18 };
  
  static {
    sLegacyEffectMode = new String[] { "none", "mono", "negative", "solarize", "sepia", "posterize", "whiteboard", "blackboard", "aqua" };
    sEffectModes = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    sAllowedTemplates = new int[] { 1, 2, 3 };
  }
  
  private static void appendStreamConfig(ArrayList<StreamConfiguration> paramArrayList, int paramInt, List<Camera.Size> paramList) {
    for (Camera.Size size : paramList)
      paramArrayList.add(new StreamConfiguration(paramInt, size.width, size.height, false)); 
  }
  
  private static long calculateJpegStallDuration(Camera.Size paramSize) {
    return paramSize.width * paramSize.height * 71L + 200000000L;
  }
  
  private static int[] convertAeFpsRangeToLegacy(Range<Integer> paramRange) {
    return new int[] { ((Integer)paramRange.getLower()).intValue(), ((Integer)paramRange.getUpper()).intValue() };
  }
  
  static String convertAfModeToLegacy(int paramInt, List<String> paramList) {
    if (paramList == null || paramList.isEmpty()) {
      Log.w("LegacyMetadataMapper", "No focus modes supported; API1 bug");
      return null;
    } 
    String str1 = null;
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt != 4) {
              if (paramInt == 5)
                str1 = "edof"; 
            } else {
              str1 = "continuous-picture";
            } 
          } else {
            str1 = "continuous-video";
          } 
        } else {
          str1 = "macro";
        } 
      } else {
        str1 = "auto";
      } 
    } else if (paramList.contains("fixed")) {
      str1 = "fixed";
    } else {
      str1 = "infinity";
    } 
    String str2 = str1;
    if (!paramList.contains(str1)) {
      str2 = paramList.get(0);
      Log.w("LegacyMetadataMapper", String.format("convertAfModeToLegacy - ignoring unsupported mode %d, defaulting to %s", new Object[] { Integer.valueOf(paramInt), str2 }));
    } 
    return str2;
  }
  
  private static int convertAntiBandingMode(String paramString) {
    byte b;
    if (paramString == null)
      return -1; 
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 3005871:
        if (paramString.equals("auto")) {
          b = 3;
          break;
        } 
      case 1658188:
        if (paramString.equals("60hz")) {
          b = 2;
          break;
        } 
      case 1628397:
        if (paramString.equals("50hz")) {
          b = 1;
          break;
        } 
      case 109935:
        if (paramString.equals("off")) {
          b = 0;
          break;
        } 
    } 
    if (b != 0) {
      if (b != 1) {
        if (b != 2) {
          if (b != 3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("convertAntiBandingMode - Unknown antibanding mode ");
            stringBuilder.append(paramString);
            Log.w("LegacyMetadataMapper", stringBuilder.toString());
            return -1;
          } 
          return 3;
        } 
        return 2;
      } 
      return 1;
    } 
    return 0;
  }
  
  static int convertAntiBandingModeOrDefault(String paramString) {
    int i = convertAntiBandingMode(paramString);
    return (i == -1) ? 0 : i;
  }
  
  static int convertEffectModeFromLegacy(String paramString) {
    if (paramString == null)
      return 0; 
    int i = ArrayUtils.getArrayIndex((Object[])sLegacyEffectMode, paramString);
    return (i < 0) ? -1 : sEffectModes[i];
  }
  
  static String convertEffectModeToLegacy(int paramInt) {
    paramInt = ArrayUtils.getArrayIndex(sEffectModes, paramInt);
    return (paramInt < 0) ? null : sLegacyEffectMode[paramInt];
  }
  
  public static void convertRequestMetadata(LegacyRequest paramLegacyRequest) {
    LegacyRequestMapper.convertRequestMetadata(paramLegacyRequest);
  }
  
  static int convertSceneModeFromLegacy(String paramString) {
    if (paramString == null)
      return 0; 
    int i = ArrayUtils.getArrayIndex((Object[])sLegacySceneModes, paramString);
    return (i < 0) ? -1 : sSceneModes[i];
  }
  
  static String convertSceneModeToLegacy(int paramInt) {
    if (paramInt == 1)
      return "auto"; 
    paramInt = ArrayUtils.getArrayIndex(sSceneModes, paramInt);
    return (paramInt < 0) ? null : sLegacySceneModes[paramInt];
  }
  
  public static CameraCharacteristics createCharacteristics(Camera.Parameters paramParameters, Camera.CameraInfo paramCameraInfo, int paramInt, Size paramSize) {
    Preconditions.checkNotNull(paramParameters, "parameters must not be null");
    Preconditions.checkNotNull(paramCameraInfo, "info must not be null");
    String str = paramParameters.flatten();
    CameraInfo cameraInfo = new CameraInfo();
    cameraInfo.info = paramCameraInfo;
    return createCharacteristics(str, cameraInfo, paramInt, paramSize);
  }
  
  public static CameraCharacteristics createCharacteristics(String paramString, CameraInfo paramCameraInfo, int paramInt, Size paramSize) {
    Preconditions.checkNotNull(paramString, "parameters must not be null");
    Preconditions.checkNotNull(paramCameraInfo, "info must not be null");
    Preconditions.checkNotNull(paramCameraInfo.info, "info.info must not be null");
    CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
    mapCharacteristicsFromInfo(cameraMetadataNative, paramCameraInfo.info);
    Camera.Parameters parameters = Camera.getEmptyParameters();
    parameters.unflatten(paramString);
    mapCharacteristicsFromParameters(cameraMetadataNative, parameters);
    cameraMetadataNative.setCameraId(paramInt);
    cameraMetadataNative.setDisplaySize(paramSize);
    return new CameraCharacteristics(cameraMetadataNative);
  }
  
  public static CameraMetadataNative createRequestTemplate(CameraCharacteristics paramCameraCharacteristics, int paramInt) {
    // Byte code:
    //   0: getstatic android/hardware/camera2/legacy/LegacyMetadataMapper.sAllowedTemplates : [I
    //   3: iload_1
    //   4: invokestatic contains : ([II)Z
    //   7: ifeq -> 667
    //   10: new android/hardware/camera2/impl/CameraMetadataNative
    //   13: dup
    //   14: invokespecial <init> : ()V
    //   17: astore_2
    //   18: aload_2
    //   19: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AWB_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   22: iconst_1
    //   23: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   26: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   29: aload_2
    //   30: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_ANTIBANDING_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   33: iconst_3
    //   34: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   37: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   40: aload_2
    //   41: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION : Landroid/hardware/camera2/CaptureRequest$Key;
    //   44: iconst_0
    //   45: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   48: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   51: aload_2
    //   52: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_LOCK : Landroid/hardware/camera2/CaptureRequest$Key;
    //   55: iconst_0
    //   56: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   59: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   62: aload_2
    //   63: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER : Landroid/hardware/camera2/CaptureRequest$Key;
    //   66: iconst_0
    //   67: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   70: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   73: aload_2
    //   74: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AF_TRIGGER : Landroid/hardware/camera2/CaptureRequest$Key;
    //   77: iconst_0
    //   78: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   81: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   84: aload_2
    //   85: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AWB_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   88: iconst_1
    //   89: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   92: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   95: aload_2
    //   96: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AWB_LOCK : Landroid/hardware/camera2/CaptureRequest$Key;
    //   99: iconst_0
    //   100: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   103: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   106: aload_0
    //   107: getstatic android/hardware/camera2/CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   110: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   113: checkcast android/graphics/Rect
    //   116: astore_3
    //   117: iconst_1
    //   118: anewarray android/hardware/camera2/params/MeteringRectangle
    //   121: astore #4
    //   123: aload #4
    //   125: iconst_0
    //   126: new android/hardware/camera2/params/MeteringRectangle
    //   129: dup
    //   130: iconst_0
    //   131: iconst_0
    //   132: aload_3
    //   133: invokevirtual width : ()I
    //   136: iconst_1
    //   137: isub
    //   138: aload_3
    //   139: invokevirtual height : ()I
    //   142: iconst_1
    //   143: isub
    //   144: iconst_0
    //   145: invokespecial <init> : (IIIII)V
    //   148: aastore
    //   149: aload_2
    //   150: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_REGIONS : Landroid/hardware/camera2/CaptureRequest$Key;
    //   153: aload #4
    //   155: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   158: aload_2
    //   159: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AWB_REGIONS : Landroid/hardware/camera2/CaptureRequest$Key;
    //   162: aload #4
    //   164: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   167: aload_2
    //   168: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AF_REGIONS : Landroid/hardware/camera2/CaptureRequest$Key;
    //   171: aload #4
    //   173: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   176: iload_1
    //   177: iconst_1
    //   178: if_icmpeq -> 214
    //   181: iload_1
    //   182: iconst_2
    //   183: if_icmpeq -> 208
    //   186: iload_1
    //   187: iconst_3
    //   188: if_icmpne -> 197
    //   191: iconst_3
    //   192: istore #5
    //   194: goto -> 217
    //   197: new java/lang/AssertionError
    //   200: dup
    //   201: ldc_w 'Impossible; keep in sync with sAllowedTemplates'
    //   204: invokespecial <init> : (Ljava/lang/Object;)V
    //   207: athrow
    //   208: iconst_2
    //   209: istore #5
    //   211: goto -> 217
    //   214: iconst_1
    //   215: istore #5
    //   217: aload_2
    //   218: getstatic android/hardware/camera2/CaptureRequest.CONTROL_CAPTURE_INTENT : Landroid/hardware/camera2/CaptureRequest$Key;
    //   221: iload #5
    //   223: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   226: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   229: aload_2
    //   230: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   233: iconst_1
    //   234: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   237: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   240: aload_2
    //   241: getstatic android/hardware/camera2/CaptureRequest.CONTROL_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   244: iconst_1
    //   245: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   248: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   251: aload_0
    //   252: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   255: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   258: checkcast java/lang/Float
    //   261: astore_3
    //   262: aload_3
    //   263: ifnull -> 281
    //   266: aload_3
    //   267: invokevirtual floatValue : ()F
    //   270: fconst_0
    //   271: fcmpl
    //   272: ifne -> 281
    //   275: iconst_0
    //   276: istore #5
    //   278: goto -> 353
    //   281: iload_1
    //   282: iconst_3
    //   283: if_icmpeq -> 327
    //   286: iload_1
    //   287: iconst_4
    //   288: if_icmpne -> 294
    //   291: goto -> 327
    //   294: iload_1
    //   295: iconst_1
    //   296: if_icmpeq -> 304
    //   299: iload_1
    //   300: iconst_2
    //   301: if_icmpne -> 350
    //   304: aload_0
    //   305: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   308: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   311: checkcast [I
    //   314: iconst_4
    //   315: invokestatic contains : ([II)Z
    //   318: ifeq -> 350
    //   321: iconst_4
    //   322: istore #5
    //   324: goto -> 353
    //   327: aload_0
    //   328: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   331: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   334: checkcast [I
    //   337: iconst_3
    //   338: invokestatic contains : ([II)Z
    //   341: ifeq -> 350
    //   344: iconst_3
    //   345: istore #5
    //   347: goto -> 353
    //   350: iconst_1
    //   351: istore #5
    //   353: aload_2
    //   354: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AF_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   357: iload #5
    //   359: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   362: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   365: aload_0
    //   366: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   369: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   372: checkcast [Landroid/util/Range;
    //   375: astore #6
    //   377: aload #6
    //   379: iconst_0
    //   380: aaload
    //   381: astore #4
    //   383: aload #6
    //   385: arraylength
    //   386: istore #7
    //   388: iconst_0
    //   389: istore #5
    //   391: iload #5
    //   393: iload #7
    //   395: if_icmpge -> 492
    //   398: aload #6
    //   400: iload #5
    //   402: aaload
    //   403: astore #8
    //   405: aload #4
    //   407: invokevirtual getUpper : ()Ljava/lang/Comparable;
    //   410: checkcast java/lang/Integer
    //   413: invokevirtual intValue : ()I
    //   416: aload #8
    //   418: invokevirtual getUpper : ()Ljava/lang/Comparable;
    //   421: checkcast java/lang/Integer
    //   424: invokevirtual intValue : ()I
    //   427: if_icmpge -> 436
    //   430: aload #8
    //   432: astore_3
    //   433: goto -> 483
    //   436: aload #4
    //   438: astore_3
    //   439: aload #4
    //   441: invokevirtual getUpper : ()Ljava/lang/Comparable;
    //   444: aload #8
    //   446: invokevirtual getUpper : ()Ljava/lang/Comparable;
    //   449: if_acmpne -> 483
    //   452: aload #4
    //   454: astore_3
    //   455: aload #4
    //   457: invokevirtual getLower : ()Ljava/lang/Comparable;
    //   460: checkcast java/lang/Integer
    //   463: invokevirtual intValue : ()I
    //   466: aload #8
    //   468: invokevirtual getLower : ()Ljava/lang/Comparable;
    //   471: checkcast java/lang/Integer
    //   474: invokevirtual intValue : ()I
    //   477: if_icmpge -> 483
    //   480: aload #8
    //   482: astore_3
    //   483: iinc #5, 1
    //   486: aload_3
    //   487: astore #4
    //   489: goto -> 391
    //   492: aload_2
    //   493: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   496: aload #4
    //   498: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   501: aload_2
    //   502: getstatic android/hardware/camera2/CaptureRequest.CONTROL_SCENE_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   505: iconst_0
    //   506: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   509: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   512: aload_2
    //   513: getstatic android/hardware/camera2/CaptureRequest.CONTROL_ZOOM_RATIO : Landroid/hardware/camera2/CaptureRequest$Key;
    //   516: fconst_1
    //   517: invokestatic valueOf : (F)Ljava/lang/Float;
    //   520: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   523: aload_2
    //   524: getstatic android/hardware/camera2/CaptureRequest.STATISTICS_FACE_DETECT_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   527: iconst_0
    //   528: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   531: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   534: aload_2
    //   535: getstatic android/hardware/camera2/CaptureRequest.FLASH_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   538: iconst_0
    //   539: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   542: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   545: iload_1
    //   546: iconst_2
    //   547: if_icmpne -> 564
    //   550: aload_2
    //   551: getstatic android/hardware/camera2/CaptureRequest.NOISE_REDUCTION_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   554: iconst_2
    //   555: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   558: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   561: goto -> 575
    //   564: aload_2
    //   565: getstatic android/hardware/camera2/CaptureRequest.NOISE_REDUCTION_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   568: iconst_1
    //   569: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   572: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   575: iload_1
    //   576: iconst_2
    //   577: if_icmpne -> 594
    //   580: aload_2
    //   581: getstatic android/hardware/camera2/CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   584: iconst_2
    //   585: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   588: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   591: goto -> 605
    //   594: aload_2
    //   595: getstatic android/hardware/camera2/CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   598: iconst_1
    //   599: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   602: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   605: aload_2
    //   606: getstatic android/hardware/camera2/CaptureRequest.LENS_FOCAL_LENGTH : Landroid/hardware/camera2/CaptureRequest$Key;
    //   609: aload_0
    //   610: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   613: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   616: checkcast [F
    //   619: iconst_0
    //   620: faload
    //   621: invokestatic valueOf : (F)Ljava/lang/Float;
    //   624: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   627: aload_0
    //   628: getstatic android/hardware/camera2/CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   631: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   634: checkcast [Landroid/util/Size;
    //   637: astore_0
    //   638: getstatic android/hardware/camera2/CaptureRequest.JPEG_THUMBNAIL_SIZE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   641: astore_3
    //   642: aload_0
    //   643: arraylength
    //   644: iconst_1
    //   645: if_icmple -> 655
    //   648: aload_0
    //   649: iconst_1
    //   650: aaload
    //   651: astore_0
    //   652: goto -> 659
    //   655: aload_0
    //   656: iconst_0
    //   657: aaload
    //   658: astore_0
    //   659: aload_2
    //   660: aload_3
    //   661: aload_0
    //   662: invokevirtual set : (Landroid/hardware/camera2/CaptureRequest$Key;Ljava/lang/Object;)V
    //   665: aload_2
    //   666: areturn
    //   667: new java/lang/IllegalArgumentException
    //   670: dup
    //   671: ldc_w 'templateId out of range'
    //   674: invokespecial <init> : (Ljava/lang/String;)V
    //   677: athrow
  }
  
  private static int[] getTagsForKeys(CameraCharacteristics.Key<?>[] paramArrayOfKey) {
    int[] arrayOfInt = new int[paramArrayOfKey.length];
    for (byte b = 0; b < paramArrayOfKey.length; b++)
      arrayOfInt[b] = paramArrayOfKey[b].getNativeKey().getTag(); 
    return arrayOfInt;
  }
  
  private static int[] getTagsForKeys(CaptureRequest.Key<?>[] paramArrayOfKey) {
    int[] arrayOfInt = new int[paramArrayOfKey.length];
    for (byte b = 0; b < paramArrayOfKey.length; b++)
      arrayOfInt[b] = paramArrayOfKey[b].getNativeKey().getTag(); 
    return arrayOfInt;
  }
  
  private static int[] getTagsForKeys(CaptureResult.Key<?>[] paramArrayOfKey) {
    int[] arrayOfInt = new int[paramArrayOfKey.length];
    for (byte b = 0; b < paramArrayOfKey.length; b++)
      arrayOfInt[b] = paramArrayOfKey[b].getNativeKey().getTag(); 
    return arrayOfInt;
  }
  
  private static void mapCharacteristicsFromInfo(CameraMetadataNative paramCameraMetadataNative, Camera.CameraInfo paramCameraInfo) {
    boolean bool;
    CameraCharacteristics.Key key = CameraCharacteristics.LENS_FACING;
    if (paramCameraInfo.facing == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    paramCameraMetadataNative.set(key, Integer.valueOf(bool));
    paramCameraMetadataNative.set(CameraCharacteristics.SENSOR_ORIENTATION, Integer.valueOf(paramCameraInfo.orientation));
  }
  
  private static void mapCharacteristicsFromParameters(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    paramCameraMetadataNative.set(CameraCharacteristics.COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES, new int[] { 1, 2 });
    mapControlAe(paramCameraMetadataNative, paramParameters);
    mapControlAf(paramCameraMetadataNative, paramParameters);
    mapControlAwb(paramCameraMetadataNative, paramParameters);
    mapControlOther(paramCameraMetadataNative, paramParameters);
    mapLens(paramCameraMetadataNative, paramParameters);
    mapFlash(paramCameraMetadataNative, paramParameters);
    mapJpeg(paramCameraMetadataNative, paramParameters);
    paramCameraMetadataNative.set(CameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES, new int[] { 1, 2 });
    mapScaler(paramCameraMetadataNative, paramParameters);
    mapSensor(paramCameraMetadataNative, paramParameters);
    mapStatistics(paramCameraMetadataNative, paramParameters);
    mapSync(paramCameraMetadataNative, paramParameters);
    paramCameraMetadataNative.set(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, Integer.valueOf(2));
    mapScalerStreamConfigs(paramCameraMetadataNative, paramParameters);
    mapRequest(paramCameraMetadataNative, paramParameters);
  }
  
  private static void mapControlAe(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getSupportedAntibanding : ()Ljava/util/List;
    //   4: astore_2
    //   5: aload_2
    //   6: ifnull -> 84
    //   9: aload_2
    //   10: invokeinterface size : ()I
    //   15: ifle -> 84
    //   18: aload_2
    //   19: invokeinterface size : ()I
    //   24: newarray int
    //   26: astore_3
    //   27: iconst_0
    //   28: istore #4
    //   30: aload_2
    //   31: invokeinterface iterator : ()Ljava/util/Iterator;
    //   36: astore_2
    //   37: aload_2
    //   38: invokeinterface hasNext : ()Z
    //   43: ifeq -> 68
    //   46: aload_3
    //   47: iload #4
    //   49: aload_2
    //   50: invokeinterface next : ()Ljava/lang/Object;
    //   55: checkcast java/lang/String
    //   58: invokestatic convertAntiBandingMode : (Ljava/lang/String;)I
    //   61: iastore
    //   62: iinc #4, 1
    //   65: goto -> 37
    //   68: aload_0
    //   69: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   72: aload_3
    //   73: iload #4
    //   75: invokestatic copyOf : ([II)[I
    //   78: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   81: goto -> 94
    //   84: aload_0
    //   85: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   88: iconst_0
    //   89: newarray int
    //   91: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   94: aload_1
    //   95: invokevirtual getSupportedPreviewFpsRange : ()Ljava/util/List;
    //   98: astore_3
    //   99: aload_3
    //   100: ifnull -> 374
    //   103: aload_3
    //   104: invokeinterface size : ()I
    //   109: istore #4
    //   111: iload #4
    //   113: ifle -> 363
    //   116: iload #4
    //   118: anewarray android/util/Range
    //   121: astore_2
    //   122: iconst_0
    //   123: istore #4
    //   125: aload_3
    //   126: invokeinterface iterator : ()Ljava/util/Iterator;
    //   131: astore #5
    //   133: aload #5
    //   135: invokeinterface hasNext : ()Z
    //   140: ifeq -> 200
    //   143: aload #5
    //   145: invokeinterface next : ()Ljava/lang/Object;
    //   150: checkcast [I
    //   153: astore #6
    //   155: aload_2
    //   156: iload #4
    //   158: aload #6
    //   160: iconst_0
    //   161: iaload
    //   162: i2d
    //   163: ldc2_w 1000.0
    //   166: ddiv
    //   167: invokestatic floor : (D)D
    //   170: d2i
    //   171: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   174: aload #6
    //   176: iconst_1
    //   177: iaload
    //   178: i2d
    //   179: ldc2_w 1000.0
    //   182: ddiv
    //   183: invokestatic ceil : (D)D
    //   186: d2i
    //   187: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   190: invokestatic create : (Ljava/lang/Comparable;Ljava/lang/Comparable;)Landroid/util/Range;
    //   193: aastore
    //   194: iinc #4, 1
    //   197: goto -> 133
    //   200: aload_0
    //   201: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   204: aload_2
    //   205: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   208: aload_1
    //   209: invokevirtual getSupportedFlashModes : ()Ljava/util/List;
    //   212: iconst_5
    //   213: anewarray java/lang/String
    //   216: dup
    //   217: iconst_0
    //   218: ldc 'off'
    //   220: aastore
    //   221: dup
    //   222: iconst_1
    //   223: ldc 'auto'
    //   225: aastore
    //   226: dup
    //   227: iconst_2
    //   228: ldc_w 'on'
    //   231: aastore
    //   232: dup
    //   233: iconst_3
    //   234: ldc_w 'red-eye'
    //   237: aastore
    //   238: dup
    //   239: iconst_4
    //   240: ldc_w 'torch'
    //   243: aastore
    //   244: iconst_4
    //   245: newarray int
    //   247: dup
    //   248: iconst_0
    //   249: iconst_1
    //   250: iastore
    //   251: dup
    //   252: iconst_1
    //   253: iconst_2
    //   254: iastore
    //   255: dup
    //   256: iconst_2
    //   257: iconst_3
    //   258: iastore
    //   259: dup
    //   260: iconst_3
    //   261: iconst_4
    //   262: iastore
    //   263: invokestatic convertStringListToIntArray : (Ljava/util/List;[Ljava/lang/String;[I)[I
    //   266: astore_2
    //   267: aload_2
    //   268: ifnull -> 278
    //   271: aload_2
    //   272: astore_3
    //   273: aload_2
    //   274: arraylength
    //   275: ifne -> 286
    //   278: iconst_1
    //   279: newarray int
    //   281: dup
    //   282: iconst_0
    //   283: iconst_1
    //   284: iastore
    //   285: astore_3
    //   286: aload_0
    //   287: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   290: aload_3
    //   291: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   294: aload_1
    //   295: invokevirtual getMinExposureCompensation : ()I
    //   298: istore #4
    //   300: aload_1
    //   301: invokevirtual getMaxExposureCompensation : ()I
    //   304: istore #7
    //   306: aload_0
    //   307: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   310: iload #4
    //   312: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   315: iload #7
    //   317: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   320: invokestatic create : (Ljava/lang/Comparable;Ljava/lang/Comparable;)Landroid/util/Range;
    //   323: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   326: aload_1
    //   327: invokevirtual getExposureCompensationStep : ()F
    //   330: fstore #8
    //   332: aload_0
    //   333: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   336: fload #8
    //   338: invokestatic createRational : (F)Landroid/util/Rational;
    //   341: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   344: aload_1
    //   345: invokevirtual isAutoExposureLockSupported : ()Z
    //   348: istore #9
    //   350: aload_0
    //   351: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AE_LOCK_AVAILABLE : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   354: iload #9
    //   356: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   359: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   362: return
    //   363: new java/lang/AssertionError
    //   366: dup
    //   367: ldc_w 'At least one FPS range must be supported.'
    //   370: invokespecial <init> : (Ljava/lang/Object;)V
    //   373: athrow
    //   374: new java/lang/AssertionError
    //   377: dup
    //   378: ldc_w 'Supported FPS ranges cannot be null.'
    //   381: invokespecial <init> : (Ljava/lang/Object;)V
    //   384: athrow
  }
  
  private static void mapControlAf(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getSupportedFocusModes : ()Ljava/util/List;
    //   4: bipush #7
    //   6: anewarray java/lang/String
    //   9: dup
    //   10: iconst_0
    //   11: ldc 'auto'
    //   13: aastore
    //   14: dup
    //   15: iconst_1
    //   16: ldc 'continuous-picture'
    //   18: aastore
    //   19: dup
    //   20: iconst_2
    //   21: ldc 'continuous-video'
    //   23: aastore
    //   24: dup
    //   25: iconst_3
    //   26: ldc 'edof'
    //   28: aastore
    //   29: dup
    //   30: iconst_4
    //   31: ldc 'infinity'
    //   33: aastore
    //   34: dup
    //   35: iconst_5
    //   36: ldc 'macro'
    //   38: aastore
    //   39: dup
    //   40: bipush #6
    //   42: ldc 'fixed'
    //   44: aastore
    //   45: bipush #7
    //   47: newarray int
    //   49: dup
    //   50: iconst_0
    //   51: iconst_1
    //   52: iastore
    //   53: dup
    //   54: iconst_1
    //   55: iconst_4
    //   56: iastore
    //   57: dup
    //   58: iconst_2
    //   59: iconst_3
    //   60: iastore
    //   61: dup
    //   62: iconst_3
    //   63: iconst_5
    //   64: iastore
    //   65: dup
    //   66: iconst_4
    //   67: iconst_0
    //   68: iastore
    //   69: dup
    //   70: iconst_5
    //   71: iconst_2
    //   72: iastore
    //   73: dup
    //   74: bipush #6
    //   76: iconst_0
    //   77: iastore
    //   78: invokestatic convertStringListToIntList : (Ljava/util/List;[Ljava/lang/String;[I)Ljava/util/List;
    //   81: astore_2
    //   82: aload_2
    //   83: ifnull -> 97
    //   86: aload_2
    //   87: astore_1
    //   88: aload_2
    //   89: invokeinterface size : ()I
    //   94: ifne -> 126
    //   97: ldc 'LegacyMetadataMapper'
    //   99: ldc_w 'No AF modes supported (HAL bug); defaulting to AF_MODE_OFF only'
    //   102: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   105: pop
    //   106: new java/util/ArrayList
    //   109: dup
    //   110: iconst_1
    //   111: invokespecial <init> : (I)V
    //   114: astore_1
    //   115: aload_1
    //   116: iconst_0
    //   117: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   120: invokeinterface add : (Ljava/lang/Object;)Z
    //   125: pop
    //   126: aload_0
    //   127: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   130: aload_1
    //   131: invokestatic toIntArray : (Ljava/util/List;)[I
    //   134: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   137: return
  }
  
  private static void mapControlAwb(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getSupportedWhiteBalance : ()Ljava/util/List;
    //   4: bipush #8
    //   6: anewarray java/lang/String
    //   9: dup
    //   10: iconst_0
    //   11: ldc 'auto'
    //   13: aastore
    //   14: dup
    //   15: iconst_1
    //   16: ldc_w 'incandescent'
    //   19: aastore
    //   20: dup
    //   21: iconst_2
    //   22: ldc_w 'fluorescent'
    //   25: aastore
    //   26: dup
    //   27: iconst_3
    //   28: ldc_w 'warm-fluorescent'
    //   31: aastore
    //   32: dup
    //   33: iconst_4
    //   34: ldc_w 'daylight'
    //   37: aastore
    //   38: dup
    //   39: iconst_5
    //   40: ldc_w 'cloudy-daylight'
    //   43: aastore
    //   44: dup
    //   45: bipush #6
    //   47: ldc_w 'twilight'
    //   50: aastore
    //   51: dup
    //   52: bipush #7
    //   54: ldc_w 'shade'
    //   57: aastore
    //   58: bipush #8
    //   60: newarray int
    //   62: dup
    //   63: iconst_0
    //   64: iconst_1
    //   65: iastore
    //   66: dup
    //   67: iconst_1
    //   68: iconst_2
    //   69: iastore
    //   70: dup
    //   71: iconst_2
    //   72: iconst_3
    //   73: iastore
    //   74: dup
    //   75: iconst_3
    //   76: iconst_4
    //   77: iastore
    //   78: dup
    //   79: iconst_4
    //   80: iconst_5
    //   81: iastore
    //   82: dup
    //   83: iconst_5
    //   84: bipush #6
    //   86: iastore
    //   87: dup
    //   88: bipush #6
    //   90: bipush #7
    //   92: iastore
    //   93: dup
    //   94: bipush #7
    //   96: bipush #8
    //   98: iastore
    //   99: invokestatic convertStringListToIntList : (Ljava/util/List;[Ljava/lang/String;[I)Ljava/util/List;
    //   102: astore_2
    //   103: aload_2
    //   104: ifnull -> 118
    //   107: aload_2
    //   108: astore_3
    //   109: aload_2
    //   110: invokeinterface size : ()I
    //   115: ifne -> 147
    //   118: ldc 'LegacyMetadataMapper'
    //   120: ldc_w 'No AWB modes supported (HAL bug); defaulting to AWB_MODE_AUTO only'
    //   123: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   126: pop
    //   127: new java/util/ArrayList
    //   130: dup
    //   131: iconst_1
    //   132: invokespecial <init> : (I)V
    //   135: astore_3
    //   136: aload_3
    //   137: iconst_1
    //   138: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   141: invokeinterface add : (Ljava/lang/Object;)Z
    //   146: pop
    //   147: aload_0
    //   148: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   151: aload_3
    //   152: invokestatic toIntArray : (Ljava/util/List;)[I
    //   155: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   158: aload_1
    //   159: invokevirtual isAutoWhiteBalanceLockSupported : ()Z
    //   162: istore #4
    //   164: aload_0
    //   165: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AWB_LOCK_AVAILABLE : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   168: iload #4
    //   170: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   173: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   176: return
  }
  
  private static void mapControlOther(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    int[] arrayOfInt1;
    int[] arrayOfInt3;
    int[] arrayOfInt2;
    if (paramParameters.isVideoStabilizationSupported()) {
      arrayOfInt3 = new int[2];
      arrayOfInt3[0] = 0;
      arrayOfInt3[1] = 1;
    } else {
      arrayOfInt3 = new int[1];
      arrayOfInt3[0] = 0;
    } 
    paramCameraMetadataNative.set(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES, arrayOfInt3);
    int i = paramParameters.getMaxNumMeteringAreas();
    int j = paramParameters.getMaxNumFocusAreas();
    paramCameraMetadataNative.set(CameraCharacteristics.CONTROL_MAX_REGIONS, new int[] { i, 0, j });
    List list = paramParameters.getSupportedColorEffects();
    if (list == null) {
      arrayOfInt2 = new int[0];
    } else {
      arrayOfInt2 = ArrayUtils.convertStringListToIntArray((List)arrayOfInt2, sLegacyEffectMode, sEffectModes);
    } 
    paramCameraMetadataNative.set(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS, arrayOfInt2);
    int k = paramParameters.getMaxNumDetectedFaces();
    List<String> list3 = paramParameters.getSupportedSceneModes();
    List<Integer> list2 = ArrayUtils.convertStringListToIntList(list3, sLegacySceneModes, sSceneModes);
    List<Integer> list1 = list2;
    if (list3 != null) {
      list1 = list2;
      if (list3.size() == 1) {
        list1 = list2;
        if (((String)list3.get(0)).equals("auto"))
          list1 = null; 
      } 
    } 
    i = 1;
    j = i;
    if (list1 == null) {
      j = i;
      if (k == 0)
        j = 0; 
    } 
    if (j != 0) {
      list2 = list1;
      if (list1 == null)
        list2 = new ArrayList(); 
      if (k > 0)
        list2.add(Integer.valueOf(1)); 
      if (list2.contains(Integer.valueOf(0)))
        while (list2.remove(new Integer(0))); 
      paramCameraMetadataNative.set(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES, ArrayUtils.toIntArray(list2));
    } else {
      paramCameraMetadataNative.set(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES, new int[] { 0 });
    } 
    CameraCharacteristics.Key key = CameraCharacteristics.CONTROL_AVAILABLE_MODES;
    if (j != 0) {
      arrayOfInt1 = new int[2];
      arrayOfInt1[0] = 1;
      arrayOfInt1[1] = 2;
    } else {
      arrayOfInt1 = new int[1];
      arrayOfInt1[0] = 1;
    } 
    paramCameraMetadataNative.set(key, arrayOfInt1);
  }
  
  private static void mapFlash(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    int i = 0;
    List list = paramParameters.getSupportedFlashModes();
    if (list != null)
      i = ListUtils.listElementsEqualTo(list, "off") ^ true; 
    paramCameraMetadataNative.set(CameraCharacteristics.FLASH_INFO_AVAILABLE, Boolean.valueOf(i));
  }
  
  private static void mapJpeg(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    List<Camera.Size> list = paramParameters.getSupportedJpegThumbnailSizes();
    if (list != null) {
      Size[] arrayOfSize = ParameterUtils.convertSizeListToArray(list);
      Arrays.sort(arrayOfSize, (Comparator<? super Size>)new SizeAreaComparator());
      paramCameraMetadataNative.set(CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES, arrayOfSize);
    } 
  }
  
  private static void mapLens(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    if ("fixed".equals(paramParameters.getFocusMode()))
      paramCameraMetadataNative.set(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE, Float.valueOf(0.0F)); 
    float f = paramParameters.getFocalLength();
    paramCameraMetadataNative.set(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS, new float[] { f });
  }
  
  private static void mapRequest(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    paramCameraMetadataNative.set(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES, new int[] { 0 });
    ArrayList<CameraCharacteristics.Key> arrayList1 = new ArrayList(Arrays.asList((Object[])new CameraCharacteristics.Key[] { 
            CameraCharacteristics.COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES, CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES, CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES, CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES, CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE, CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP, CameraCharacteristics.CONTROL_AE_LOCK_AVAILABLE, CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES, CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS, CameraCharacteristics.CONTROL_AVAILABLE_MODES, 
            CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES, CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES, CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES, CameraCharacteristics.CONTROL_AWB_LOCK_AVAILABLE, CameraCharacteristics.CONTROL_MAX_REGIONS, CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE, CameraCharacteristics.FLASH_INFO_AVAILABLE, CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL, CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES, CameraCharacteristics.LENS_FACING, 
            CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS, CameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES, CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES, CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_STREAMS, CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT, CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH, CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM, CameraCharacteristics.SCALER_CROPPING_TYPE, CameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES, CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE, 
            CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE, CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE, CameraCharacteristics.SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE, CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE, CameraCharacteristics.SENSOR_ORIENTATION, CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES, CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT, CameraCharacteristics.SYNC_MAX_LATENCY }));
    if (paramCameraMetadataNative.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE) != null)
      arrayList1.add(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE); 
    paramCameraMetadataNative.set(CameraCharacteristics.REQUEST_AVAILABLE_CHARACTERISTICS_KEYS, getTagsForKeys((CameraCharacteristics.Key<?>[])arrayList1.<CameraCharacteristics.Key>toArray(new CameraCharacteristics.Key[0])));
    ArrayList<CaptureRequest.Key> arrayList2 = new ArrayList(Arrays.asList((Object[])new CaptureRequest.Key[] { 
            CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE, CaptureRequest.CONTROL_AE_ANTIBANDING_MODE, CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, CaptureRequest.CONTROL_AE_LOCK, CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AWB_LOCK, CaptureRequest.CONTROL_AWB_MODE, 
            CaptureRequest.CONTROL_CAPTURE_INTENT, CaptureRequest.CONTROL_EFFECT_MODE, CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_SCENE_MODE, CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, CaptureRequest.CONTROL_ZOOM_RATIO, CaptureRequest.FLASH_MODE, CaptureRequest.JPEG_GPS_COORDINATES, CaptureRequest.JPEG_GPS_PROCESSING_METHOD, CaptureRequest.JPEG_GPS_TIMESTAMP, 
            CaptureRequest.JPEG_ORIENTATION, CaptureRequest.JPEG_QUALITY, CaptureRequest.JPEG_THUMBNAIL_QUALITY, CaptureRequest.JPEG_THUMBNAIL_SIZE, CaptureRequest.LENS_FOCAL_LENGTH, CaptureRequest.NOISE_REDUCTION_MODE, CaptureRequest.SCALER_CROP_REGION, CaptureRequest.STATISTICS_FACE_DETECT_MODE }));
    if (paramParameters.getMaxNumMeteringAreas() > 0)
      arrayList2.add(CaptureRequest.CONTROL_AE_REGIONS); 
    if (paramParameters.getMaxNumFocusAreas() > 0)
      arrayList2.add(CaptureRequest.CONTROL_AF_REGIONS); 
    CaptureRequest.Key[] arrayOfKey1 = new CaptureRequest.Key[arrayList2.size()];
    arrayList2.toArray(arrayOfKey1);
    paramCameraMetadataNative.set(CameraCharacteristics.REQUEST_AVAILABLE_REQUEST_KEYS, getTagsForKeys((CaptureRequest.Key<?>[])arrayOfKey1));
    ArrayList<CaptureResult.Key> arrayList = new ArrayList(Arrays.asList((Object[])new CaptureResult.Key[] { 
            CaptureResult.COLOR_CORRECTION_ABERRATION_MODE, CaptureResult.CONTROL_AE_ANTIBANDING_MODE, CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION, CaptureResult.CONTROL_AE_LOCK, CaptureResult.CONTROL_AE_MODE, CaptureResult.CONTROL_AF_MODE, CaptureResult.CONTROL_AF_STATE, CaptureResult.CONTROL_AWB_MODE, CaptureResult.CONTROL_AWB_LOCK, CaptureResult.CONTROL_MODE, 
            CaptureResult.CONTROL_ZOOM_RATIO, CaptureResult.FLASH_MODE, CaptureResult.JPEG_GPS_COORDINATES, CaptureResult.JPEG_GPS_PROCESSING_METHOD, CaptureResult.JPEG_GPS_TIMESTAMP, CaptureResult.JPEG_ORIENTATION, CaptureResult.JPEG_QUALITY, CaptureResult.JPEG_THUMBNAIL_QUALITY, CaptureResult.LENS_FOCAL_LENGTH, CaptureResult.NOISE_REDUCTION_MODE, 
            CaptureResult.REQUEST_PIPELINE_DEPTH, CaptureResult.SCALER_CROP_REGION, CaptureResult.SENSOR_TIMESTAMP, CaptureResult.STATISTICS_FACE_DETECT_MODE }));
    if (paramParameters.getMaxNumMeteringAreas() > 0)
      arrayList.add(CaptureResult.CONTROL_AE_REGIONS); 
    if (paramParameters.getMaxNumFocusAreas() > 0)
      arrayList.add(CaptureResult.CONTROL_AF_REGIONS); 
    CaptureResult.Key[] arrayOfKey = new CaptureResult.Key[arrayList.size()];
    arrayList.toArray(arrayOfKey);
    paramCameraMetadataNative.set(CameraCharacteristics.REQUEST_AVAILABLE_RESULT_KEYS, getTagsForKeys((CaptureResult.Key<?>[])arrayOfKey));
    paramCameraMetadataNative.set(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_STREAMS, new int[] { 0, 3, 1 });
    paramCameraMetadataNative.set(CameraCharacteristics.REQUEST_MAX_NUM_INPUT_STREAMS, Integer.valueOf(0));
    paramCameraMetadataNative.set(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT, Integer.valueOf(1));
    paramCameraMetadataNative.set(CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH, Byte.valueOf((byte)6));
  }
  
  private static void mapScaler(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    Range range = new Range(Float.valueOf(1.0F), Float.valueOf(ParameterUtils.getMaxZoomRatio(paramParameters)));
    paramCameraMetadataNative.set(CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE, range);
    paramCameraMetadataNative.set(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM, Float.valueOf(ParameterUtils.getMaxZoomRatio(paramParameters)));
    paramCameraMetadataNative.set(CameraCharacteristics.SCALER_CROPPING_TYPE, Integer.valueOf(0));
  }
  
  private static void mapScalerStreamConfigs(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    ArrayList<StreamConfiguration> arrayList = new ArrayList();
    List<?> list1 = paramParameters.getSupportedPreviewSizes();
    List<Camera.Size> list = paramParameters.getSupportedPictureSizes();
    SizeAreaComparator sizeAreaComparator = new SizeAreaComparator();
    Collections.sort(list1, sizeAreaComparator);
    Camera.Size size = SizeAreaComparator.findLargestByArea(list);
    float f = size.width * 1.0F / size.height;
    while (!list1.isEmpty()) {
      int i = list1.size() - 1;
      size = (Camera.Size)list1.get(i);
      if (Math.abs(f - size.width * 1.0F / size.height) >= 0.01F)
        list1.remove(i); 
    } 
    List<?> list2 = list1;
    if (list1.isEmpty()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("mapScalerStreamConfigs - failed to find any preview size matching JPEG aspect ratio ");
      stringBuilder.append(f);
      Log.w("LegacyMetadataMapper", stringBuilder.toString());
      list2 = paramParameters.getSupportedPreviewSizes();
    } 
    Collections.sort(list2, Collections.reverseOrder(sizeAreaComparator));
    appendStreamConfig(arrayList, 34, (List)list2);
    appendStreamConfig(arrayList, 35, (List)list2);
    null = paramParameters.getSupportedPreviewFormats().iterator();
    while (null.hasNext()) {
      int i = ((Integer)null.next()).intValue();
      if (ImageFormat.isPublicFormat(i) && i != 17)
        appendStreamConfig(arrayList, i, (List)list2); 
    } 
    appendStreamConfig(arrayList, 33, paramParameters.getSupportedPictureSizes());
    paramCameraMetadataNative.set(CameraCharacteristics.SCALER_AVAILABLE_STREAM_CONFIGURATIONS, arrayList.<StreamConfiguration>toArray(new StreamConfiguration[0]));
    paramCameraMetadataNative.set(CameraCharacteristics.SCALER_AVAILABLE_MIN_FRAME_DURATIONS, new StreamConfigurationDuration[0]);
    StreamConfigurationDuration[] arrayOfStreamConfigurationDuration = new StreamConfigurationDuration[list.size()];
    byte b = 0;
    long l = -1L;
    for (Camera.Size size1 : list) {
      long l1 = calculateJpegStallDuration(size1);
      arrayOfStreamConfigurationDuration[b] = new StreamConfigurationDuration(33, size1.width, size1.height, l1);
      long l2 = l;
      if (l < l1)
        l2 = l1; 
      b++;
      l = l2;
    } 
    paramCameraMetadataNative.set(CameraCharacteristics.SCALER_AVAILABLE_STALL_DURATIONS, arrayOfStreamConfigurationDuration);
    paramCameraMetadataNative.set(CameraCharacteristics.SENSOR_INFO_MAX_FRAME_DURATION, Long.valueOf(l));
  }
  
  private static void mapSensor(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    Size size = ParameterUtils.getLargestSupportedJpegSizeByArea(paramParameters);
    Rect rect = ParamsUtils.createRect(size);
    paramCameraMetadataNative.set(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE, rect);
    paramCameraMetadataNative.set(CameraCharacteristics.SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE, rect);
    paramCameraMetadataNative.set(CameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES, new int[] { 0 });
    paramCameraMetadataNative.set(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE, size);
    float f1 = paramParameters.getFocalLength();
    double d1 = paramParameters.getHorizontalViewAngle() * Math.PI / 180.0D;
    double d2 = paramParameters.getVerticalViewAngle() * Math.PI / 180.0D;
    float f2 = (float)Math.abs((f1 * 2.0F) * Math.tan(d2 / 2.0D));
    f1 = (float)Math.abs((2.0F * f1) * Math.tan(d1 / 2.0D));
    paramCameraMetadataNative.set(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE, new SizeF(f1, f2));
    paramCameraMetadataNative.set(CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE, Integer.valueOf(0));
  }
  
  private static void mapStatistics(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    int[] arrayOfInt;
    if (paramParameters.getMaxNumDetectedFaces() > 0) {
      arrayOfInt = new int[2];
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 1;
    } else {
      arrayOfInt = new int[1];
      arrayOfInt[0] = 0;
    } 
    paramCameraMetadataNative.set(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES, arrayOfInt);
    paramCameraMetadataNative.set(CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT, Integer.valueOf(paramParameters.getMaxNumDetectedFaces()));
  }
  
  private static void mapSync(CameraMetadataNative paramCameraMetadataNative, Camera.Parameters paramParameters) {
    paramCameraMetadataNative.set(CameraCharacteristics.SYNC_MAX_LATENCY, Integer.valueOf(-1));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyMetadataMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
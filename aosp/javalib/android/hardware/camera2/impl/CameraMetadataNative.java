package android.hardware.camera2.impl;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.MarshalRegistry;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.marshal.impl.MarshalQueryableArray;
import android.hardware.camera2.marshal.impl.MarshalQueryableBlackLevelPattern;
import android.hardware.camera2.marshal.impl.MarshalQueryableBoolean;
import android.hardware.camera2.marshal.impl.MarshalQueryableColorSpaceTransform;
import android.hardware.camera2.marshal.impl.MarshalQueryableEnum;
import android.hardware.camera2.marshal.impl.MarshalQueryableHighSpeedVideoConfiguration;
import android.hardware.camera2.marshal.impl.MarshalQueryableMeteringRectangle;
import android.hardware.camera2.marshal.impl.MarshalQueryableNativeByteToInteger;
import android.hardware.camera2.marshal.impl.MarshalQueryablePair;
import android.hardware.camera2.marshal.impl.MarshalQueryableParcelable;
import android.hardware.camera2.marshal.impl.MarshalQueryablePrimitive;
import android.hardware.camera2.marshal.impl.MarshalQueryableRange;
import android.hardware.camera2.marshal.impl.MarshalQueryableRecommendedStreamConfiguration;
import android.hardware.camera2.marshal.impl.MarshalQueryableRect;
import android.hardware.camera2.marshal.impl.MarshalQueryableReprocessFormatsMap;
import android.hardware.camera2.marshal.impl.MarshalQueryableRggbChannelVector;
import android.hardware.camera2.marshal.impl.MarshalQueryableSize;
import android.hardware.camera2.marshal.impl.MarshalQueryableSizeF;
import android.hardware.camera2.marshal.impl.MarshalQueryableStreamConfiguration;
import android.hardware.camera2.marshal.impl.MarshalQueryableStreamConfigurationDuration;
import android.hardware.camera2.marshal.impl.MarshalQueryableString;
import android.hardware.camera2.params.Capability;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.HighSpeedVideoConfiguration;
import android.hardware.camera2.params.LensShadingMap;
import android.hardware.camera2.params.MandatoryStreamCombination;
import android.hardware.camera2.params.OisSample;
import android.hardware.camera2.params.RecommendedStreamConfiguration;
import android.hardware.camera2.params.RecommendedStreamConfigurationMap;
import android.hardware.camera2.params.ReprocessFormatsMap;
import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.params.StreamConfigurationDuration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.params.TonemapCurve;
import android.hardware.camera2.utils.TypeReference;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ServiceSpecificException;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import dalvik.annotation.optimization.FastNative;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CameraMetadataNative implements Parcelable {
  private static final String CELLID_PROCESS = "CELLID";
  
  public static final Parcelable.Creator<CameraMetadataNative> CREATOR = new Parcelable.Creator<CameraMetadataNative>() {
      public CameraMetadataNative createFromParcel(Parcel param1Parcel) {
        CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
        cameraMetadataNative.readFromParcel(param1Parcel);
        return cameraMetadataNative;
      }
      
      public CameraMetadataNative[] newArray(int param1Int) {
        return new CameraMetadataNative[param1Int];
      }
    };
  
  private static final boolean DEBUG = false;
  
  private static final int FACE_LANDMARK_SIZE = 6;
  
  private static final String GPS_PROCESS = "GPS";
  
  public static final int NATIVE_JPEG_FORMAT = 33;
  
  public static final int NUM_TYPES = 6;
  
  private static final String TAG = "CameraMetadataJV";
  
  public static final int TYPE_BYTE = 0;
  
  public static final int TYPE_DOUBLE = 4;
  
  public static final int TYPE_FLOAT = 2;
  
  public static final int TYPE_INT32 = 1;
  
  public static final int TYPE_INT64 = 3;
  
  public static final int TYPE_RATIONAL = 5;
  
  private static final HashMap<Key<?>, GetCommand> sGetCommandMap;
  
  private static final HashMap<Key<?>, SetCommand> sSetCommandMap;
  
  private int mCameraId = -1;
  
  private Size mDisplaySize = new Size(0, 0);
  
  private boolean mHasMandatoryConcurrentStreams = false;
  
  private long mMetadataPtr;
  
  static {
    HashMap<Object, Object> hashMap = new HashMap<>();
    sGetCommandMap = (HashMap)hashMap;
    hashMap.put(CameraCharacteristics.SCALER_AVAILABLE_FORMATS.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getAvailableFormats();
          }
        });
    sGetCommandMap.put(CaptureResult.STATISTICS_FACES.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getFaces();
          }
        });
    sGetCommandMap.put(CaptureResult.STATISTICS_FACE_RECTANGLES.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getFaceRectangles();
          }
        });
    sGetCommandMap.put(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getStreamConfigurationMap();
          }
        });
    sGetCommandMap.put(CameraCharacteristics.SCALER_MANDATORY_STREAM_COMBINATIONS.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getMandatoryStreamCombinations();
          }
        });
    sGetCommandMap.put(CameraCharacteristics.SCALER_MANDATORY_CONCURRENT_STREAM_COMBINATIONS.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getMandatoryConcurrentStreamCombinations();
          }
        });
    sGetCommandMap.put(CameraCharacteristics.CONTROL_MAX_REGIONS_AE.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getMaxRegions(param1Key);
          }
        });
    sGetCommandMap.put(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getMaxRegions(param1Key);
          }
        });
    sGetCommandMap.put(CameraCharacteristics.CONTROL_MAX_REGIONS_AF.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getMaxRegions(param1Key);
          }
        });
    sGetCommandMap.put(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_RAW.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getMaxNumOutputs(param1Key);
          }
        });
    sGetCommandMap.put(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getMaxNumOutputs(param1Key);
          }
        });
    sGetCommandMap.put(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC_STALLING.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getMaxNumOutputs(param1Key);
          }
        });
    sGetCommandMap.put(CaptureRequest.TONEMAP_CURVE.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getTonemapCurve();
          }
        });
    sGetCommandMap.put(CaptureResult.JPEG_GPS_LOCATION.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getGpsLocation();
          }
        });
    sGetCommandMap.put(CaptureResult.STATISTICS_LENS_SHADING_CORRECTION_MAP.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getLensShadingMap();
          }
        });
    sGetCommandMap.put(CaptureResult.STATISTICS_OIS_SAMPLES.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getOisSamples();
          }
        });
    sGetCommandMap.put(CameraCharacteristics.CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_CAPABILITIES.getNativeKey(), new GetCommand() {
          public <T> T getValue(CameraMetadataNative param1CameraMetadataNative, CameraMetadataNative.Key<T> param1Key) {
            return (T)param1CameraMetadataNative.getExtendedSceneModeCapabilities();
          }
        });
    hashMap = new HashMap<>();
    sSetCommandMap = (HashMap)hashMap;
    hashMap.put(CameraCharacteristics.SCALER_AVAILABLE_FORMATS.getNativeKey(), new SetCommand() {
          public <T> void setValue(CameraMetadataNative param1CameraMetadataNative, T param1T) {
            param1CameraMetadataNative.setAvailableFormats((int[])param1T);
          }
        });
    sSetCommandMap.put(CaptureResult.STATISTICS_FACE_RECTANGLES.getNativeKey(), new SetCommand() {
          public <T> void setValue(CameraMetadataNative param1CameraMetadataNative, T param1T) {
            param1CameraMetadataNative.setFaceRectangles((Rect[])param1T);
          }
        });
    sSetCommandMap.put(CaptureResult.STATISTICS_FACES.getNativeKey(), new SetCommand() {
          public <T> void setValue(CameraMetadataNative param1CameraMetadataNative, T param1T) {
            param1CameraMetadataNative.setFaces((Face[])param1T);
          }
        });
    sSetCommandMap.put(CaptureRequest.TONEMAP_CURVE.getNativeKey(), new SetCommand() {
          public <T> void setValue(CameraMetadataNative param1CameraMetadataNative, T param1T) {
            param1CameraMetadataNative.setTonemapCurve((TonemapCurve)param1T);
          }
        });
    sSetCommandMap.put(CaptureResult.JPEG_GPS_LOCATION.getNativeKey(), new SetCommand() {
          public <T> void setValue(CameraMetadataNative param1CameraMetadataNative, T param1T) {
            param1CameraMetadataNative.setGpsLocation((Location)param1T);
          }
        });
    registerAllMarshalers();
  }
  
  public CameraMetadataNative() {
    long l = nativeAllocate();
    this.mMetadataPtr = l;
    if (l != 0L)
      return; 
    throw new OutOfMemoryError("Failed to allocate native CameraMetadata");
  }
  
  public CameraMetadataNative(CameraMetadataNative paramCameraMetadataNative) {
    long l = nativeAllocateCopy(paramCameraMetadataNative.mMetadataPtr);
    this.mMetadataPtr = l;
    if (l != 0L)
      return; 
    throw new OutOfMemoryError("Failed to allocate native CameraMetadata");
  }
  
  private static boolean areValuesAllNull(Object... paramVarArgs) {
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++) {
      if (paramVarArgs[b] != null)
        return false; 
    } 
    return true;
  }
  
  private void close() {
    nativeClose(this.mMetadataPtr);
    this.mMetadataPtr = 0L;
  }
  
  private int[] getAvailableFormats() {
    int[] arrayOfInt = getBase(CameraCharacteristics.SCALER_AVAILABLE_FORMATS);
    if (arrayOfInt != null)
      for (byte b = 0; b < arrayOfInt.length; b++) {
        if (arrayOfInt[b] == 33)
          arrayOfInt[b] = 256; 
      }  
    return arrayOfInt;
  }
  
  private <T> T getBase(CameraCharacteristics.Key<T> paramKey) {
    return getBase(paramKey.getNativeKey());
  }
  
  private <T> T getBase(CaptureRequest.Key<T> paramKey) {
    return getBase(paramKey.getNativeKey());
  }
  
  private <T> T getBase(CaptureResult.Key<T> paramKey) {
    return getBase(paramKey.getNativeKey());
  }
  
  private <T> T getBase(Key<T> paramKey) {
    int i;
    if (paramKey.hasTag()) {
      i = paramKey.getTag();
    } else {
      i = nativeGetTagFromKeyLocal(this.mMetadataPtr, paramKey.getName());
      paramKey.cacheTag(i);
    } 
    byte[] arrayOfByte1 = readValues(i);
    byte[] arrayOfByte2 = arrayOfByte1;
    if (arrayOfByte1 == null) {
      if (paramKey.mFallbackName == null)
        return null; 
      i = nativeGetTagFromKeyLocal(this.mMetadataPtr, paramKey.mFallbackName);
      arrayOfByte1 = readValues(i);
      arrayOfByte2 = arrayOfByte1;
      if (arrayOfByte1 == null)
        return null; 
    } 
    return (T)getMarshalerForKey(paramKey, nativeGetTypeFromTagLocal(this.mMetadataPtr, i)).unmarshal(ByteBuffer.wrap(arrayOfByte2).order(ByteOrder.nativeOrder()));
  }
  
  private Capability[] getExtendedSceneModeCapabilities() {
    int[] arrayOfInt = getBase(CameraCharacteristics.CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_MAX_SIZES);
    float[] arrayOfFloat = getBase(CameraCharacteristics.CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_ZOOM_RATIO_RANGES);
    Range range = getBase(CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE);
    float f = ((Float)getBase(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue();
    if (arrayOfInt == null)
      return null; 
    if (arrayOfInt.length % 3 == 0) {
      int i = arrayOfInt.length / 3;
      int j = 0;
      if (arrayOfFloat != null)
        if (arrayOfFloat.length % 2 == 0) {
          j = arrayOfFloat.length / 2;
          if (i - j != 1)
            throw new AssertionError("Number of extended scene mode zoom ranges must be 1 less than number of supported modes"); 
        } else {
          throw new AssertionError("availableExtendedSceneModeZoomRanges must be tuples of [minZoom, maxZoom]");
        }  
      float f1 = 1.0F;
      if (range != null) {
        f1 = ((Float)range.getLower()).floatValue();
        f = ((Float)range.getUpper()).floatValue();
      } 
      Capability[] arrayOfCapability = new Capability[i];
      byte b1 = 0;
      byte b2 = 0;
      while (b1 < i) {
        int k = arrayOfInt[b1 * 3];
        int m = arrayOfInt[b1 * 3 + 1];
        int n = arrayOfInt[b1 * 3 + 2];
        if (k != 0 && b2 < j) {
          arrayOfCapability[b1] = new Capability(k, m, n, arrayOfFloat[b2 * 2], arrayOfFloat[b2 * 2 + 1]);
          b2++;
        } else {
          arrayOfCapability[b1] = new Capability(k, m, n, f1, f);
        } 
        b1++;
      } 
      return arrayOfCapability;
    } 
    throw new AssertionError("availableExtendedSceneModeMaxSizes must be tuples of [mode, width, height]");
  }
  
  private Rect[] getFaceRectangles() {
    Rect[] arrayOfRect1 = getBase(CaptureResult.STATISTICS_FACE_RECTANGLES);
    if (arrayOfRect1 == null)
      return null; 
    Rect[] arrayOfRect2 = new Rect[arrayOfRect1.length];
    for (byte b = 0; b < arrayOfRect1.length; b++)
      arrayOfRect2[b] = new Rect((arrayOfRect1[b]).left, (arrayOfRect1[b]).top, (arrayOfRect1[b]).right - (arrayOfRect1[b]).left, (arrayOfRect1[b]).bottom - (arrayOfRect1[b]).top); 
    return arrayOfRect2;
  }
  
  private Face[] getFaces() {
    StringBuilder stringBuilder2;
    Integer integer = get(CaptureResult.STATISTICS_FACE_DETECT_MODE);
    byte[] arrayOfByte = get(CaptureResult.STATISTICS_FACE_SCORES);
    Rect[] arrayOfRect = get(CaptureResult.STATISTICS_FACE_RECTANGLES);
    int[] arrayOfInt1 = get(CaptureResult.STATISTICS_FACE_IDS);
    int[] arrayOfInt2 = get(CaptureResult.STATISTICS_FACE_LANDMARKS);
    if (areValuesAllNull(new Object[] { integer, arrayOfByte, arrayOfRect, arrayOfInt1, arrayOfInt2 }))
      return null; 
    if (integer == null) {
      Log.w("CameraMetadataJV", "Face detect mode metadata is null, assuming the mode is SIMPLE");
      Integer integer1 = Integer.valueOf(1);
    } else if (integer.intValue() > 2) {
      Integer integer1 = Integer.valueOf(2);
    } else {
      if (integer.intValue() == 0)
        return new Face[0]; 
      Integer integer1 = integer;
      if (integer.intValue() != 1) {
        integer1 = integer;
        if (integer.intValue() != 2) {
          stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Unknown face detect mode: ");
          stringBuilder2.append(integer);
          Log.w("CameraMetadataJV", stringBuilder2.toString());
          return new Face[0];
        } 
      } 
    } 
    if (arrayOfByte == null || arrayOfRect == null) {
      Log.w("CameraMetadataJV", "Expect face scores and rectangles to be non-null");
      return new Face[0];
    } 
    if (arrayOfByte.length != arrayOfRect.length)
      Log.w("CameraMetadataJV", String.format("Face score size(%d) doesn match face rectangle size(%d)!", new Object[] { Integer.valueOf(arrayOfByte.length), Integer.valueOf(arrayOfRect.length) })); 
    int i = Math.min(arrayOfByte.length, arrayOfRect.length);
    StringBuilder stringBuilder1 = stringBuilder2;
    int j = i;
    if (stringBuilder2.intValue() == 2)
      if (arrayOfInt1 == null || arrayOfInt2 == null) {
        Log.w("CameraMetadataJV", "Expect face ids and landmarks to be non-null for FULL mode,fallback to SIMPLE mode");
        Integer integer1 = Integer.valueOf(1);
        j = i;
      } else {
        if (arrayOfInt1.length != i || arrayOfInt2.length != i * 6)
          Log.w("CameraMetadataJV", String.format("Face id size(%d), or face landmark size(%d) don'tmatch face number(%d)!", new Object[] { Integer.valueOf(arrayOfInt1.length), Integer.valueOf(arrayOfInt2.length * 6), Integer.valueOf(i) })); 
        j = Math.min(Math.min(i, arrayOfInt1.length), arrayOfInt2.length / 6);
        stringBuilder1 = stringBuilder2;
      }  
    ArrayList<Face> arrayList = new ArrayList();
    if (stringBuilder1.intValue() == 1) {
      for (i = 0; i < j; i++) {
        if (arrayOfByte[i] <= 100 && arrayOfByte[i] >= 1)
          arrayList.add(new Face(arrayOfRect[i], arrayOfByte[i])); 
      } 
    } else {
      for (i = 0; i < j; i++) {
        if (arrayOfByte[i] <= 100 && arrayOfByte[i] >= 1 && arrayOfInt1[i] >= 0) {
          Point point2 = new Point(arrayOfInt2[i * 6], arrayOfInt2[i * 6 + 1]);
          Point point1 = new Point(arrayOfInt2[i * 6 + 2], arrayOfInt2[i * 6 + 3]);
          Point point3 = new Point(arrayOfInt2[i * 6 + 4], arrayOfInt2[i * 6 + 5]);
          arrayList.add(new Face(arrayOfRect[i], arrayOfByte[i], arrayOfInt1[i], point2, point1, point3));
        } 
      } 
    } 
    Face[] arrayOfFace = new Face[arrayList.size()];
    arrayList.toArray(arrayOfFace);
    return arrayOfFace;
  }
  
  private Location getGpsLocation() {
    String str = get(CaptureResult.JPEG_GPS_PROCESSING_METHOD);
    double[] arrayOfDouble = get(CaptureResult.JPEG_GPS_COORDINATES);
    Long long_ = get(CaptureResult.JPEG_GPS_TIMESTAMP);
    if (areValuesAllNull(new Object[] { str, arrayOfDouble, long_ }))
      return null; 
    Location location = new Location(translateProcessToLocationProvider(str));
    if (long_ != null) {
      location.setTime(long_.longValue() * 1000L);
    } else {
      Log.w("CameraMetadataJV", "getGpsLocation - No timestamp for GPS location.");
    } 
    if (arrayOfDouble != null) {
      location.setLatitude(arrayOfDouble[0]);
      location.setLongitude(arrayOfDouble[1]);
      location.setAltitude(arrayOfDouble[2]);
    } else {
      Log.w("CameraMetadataJV", "getGpsLocation - No coordinates for GPS location");
    } 
    return location;
  }
  
  private LensShadingMap getLensShadingMap() {
    float[] arrayOfFloat = getBase(CaptureResult.STATISTICS_LENS_SHADING_MAP);
    Size size = get(CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE);
    if (arrayOfFloat == null)
      return null; 
    if (size == null) {
      Log.w("CameraMetadataJV", "getLensShadingMap - Lens shading map size was null.");
      return null;
    } 
    return new LensShadingMap(arrayOfFloat, size.getHeight(), size.getWidth());
  }
  
  private MandatoryStreamCombination[] getMandatoryConcurrentStreamCombinations() {
    return !this.mHasMandatoryConcurrentStreams ? null : getMandatoryStreamCombinationsHelper(true);
  }
  
  private MandatoryStreamCombination[] getMandatoryStreamCombinations() {
    return getMandatoryStreamCombinationsHelper(false);
  }
  
  private MandatoryStreamCombination[] getMandatoryStreamCombinationsHelper(boolean paramBoolean) {
    List list;
    int[] arrayOfInt = getBase(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
    ArrayList<Integer> arrayList = new ArrayList();
    arrayList.ensureCapacity(arrayOfInt.length);
    int i = arrayOfInt.length;
    int j;
    for (j = 0; j < i; j++)
      arrayList.add(new Integer(arrayOfInt[j])); 
    j = ((Integer)getBase(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
    MandatoryStreamCombination.Builder builder = new MandatoryStreamCombination.Builder(this.mCameraId, j, this.mDisplaySize, arrayList, getStreamConfigurationMap());
    if (paramBoolean) {
      list = builder.getAvailableMandatoryConcurrentStreamCombinations();
    } else {
      list = list.getAvailableMandatoryStreamCombinations();
    } 
    return (list != null && !list.isEmpty()) ? (MandatoryStreamCombination[])list.toArray((Object[])new MandatoryStreamCombination[list.size()]) : null;
  }
  
  private static <T> Marshaler<T> getMarshalerForKey(Key<T> paramKey, int paramInt) {
    return MarshalRegistry.getMarshaler(paramKey.getTypeReference(), paramInt);
  }
  
  private <T> Integer getMaxNumOutputs(Key<T> paramKey) {
    int[] arrayOfInt = getBase(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_STREAMS);
    if (arrayOfInt == null)
      return null; 
    if (paramKey.equals(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_RAW))
      return Integer.valueOf(arrayOfInt[0]); 
    if (paramKey.equals(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC))
      return Integer.valueOf(arrayOfInt[1]); 
    if (paramKey.equals(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC_STALLING))
      return Integer.valueOf(arrayOfInt[2]); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid key ");
    stringBuilder.append(paramKey);
    throw new AssertionError(stringBuilder.toString());
  }
  
  private <T> Integer getMaxRegions(Key<T> paramKey) {
    int[] arrayOfInt = getBase(CameraCharacteristics.CONTROL_MAX_REGIONS);
    if (arrayOfInt == null)
      return null; 
    if (paramKey.equals(CameraCharacteristics.CONTROL_MAX_REGIONS_AE))
      return Integer.valueOf(arrayOfInt[0]); 
    if (paramKey.equals(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB))
      return Integer.valueOf(arrayOfInt[1]); 
    if (paramKey.equals(CameraCharacteristics.CONTROL_MAX_REGIONS_AF))
      return Integer.valueOf(arrayOfInt[2]); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid key ");
    stringBuilder.append(paramKey);
    throw new AssertionError(stringBuilder.toString());
  }
  
  public static int getNativeType(int paramInt, long paramLong) {
    return nativeGetTypeFromTag(paramInt, paramLong);
  }
  
  private OisSample[] getOisSamples() {
    long[] arrayOfLong = getBase(CaptureResult.STATISTICS_OIS_TIMESTAMPS);
    float[] arrayOfFloat1 = getBase(CaptureResult.STATISTICS_OIS_X_SHIFTS);
    float[] arrayOfFloat2 = getBase(CaptureResult.STATISTICS_OIS_Y_SHIFTS);
    if (arrayOfLong == null) {
      if (arrayOfFloat1 == null) {
        if (arrayOfFloat2 == null)
          return null; 
        throw new AssertionError("timestamps is null but yShifts is not");
      } 
      throw new AssertionError("timestamps is null but xShifts is not");
    } 
    if (arrayOfFloat1 != null) {
      if (arrayOfFloat2 != null) {
        if (arrayOfFloat1.length == arrayOfLong.length) {
          if (arrayOfFloat2.length == arrayOfLong.length) {
            OisSample[] arrayOfOisSample = new OisSample[arrayOfLong.length];
            for (byte b = 0; b < arrayOfLong.length; b++)
              arrayOfOisSample[b] = new OisSample(arrayOfLong[b], arrayOfFloat1[b], arrayOfFloat2[b]); 
            return arrayOfOisSample;
          } 
          throw new AssertionError(String.format("timestamps has %d entries but yShifts has %d", new Object[] { Integer.valueOf(arrayOfLong.length), Integer.valueOf(arrayOfFloat2.length) }));
        } 
        throw new AssertionError(String.format("timestamps has %d entries but xShifts has %d", new Object[] { Integer.valueOf(arrayOfLong.length), Integer.valueOf(arrayOfFloat1.length) }));
      } 
      throw new AssertionError("timestamps is not null but yShifts is");
    } 
    throw new AssertionError("timestamps is not null but xShifts is");
  }
  
  private StreamConfigurationMap getStreamConfigurationMap() {
    return new StreamConfigurationMap(getBase(CameraCharacteristics.SCALER_AVAILABLE_STREAM_CONFIGURATIONS), getBase(CameraCharacteristics.SCALER_AVAILABLE_MIN_FRAME_DURATIONS), getBase(CameraCharacteristics.SCALER_AVAILABLE_STALL_DURATIONS), getBase(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_STREAM_CONFIGURATIONS), getBase(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_MIN_FRAME_DURATIONS), getBase(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_STALL_DURATIONS), getBase(CameraCharacteristics.DEPTH_AVAILABLE_DYNAMIC_DEPTH_STREAM_CONFIGURATIONS), getBase(CameraCharacteristics.DEPTH_AVAILABLE_DYNAMIC_DEPTH_MIN_FRAME_DURATIONS), getBase(CameraCharacteristics.DEPTH_AVAILABLE_DYNAMIC_DEPTH_STALL_DURATIONS), getBase(CameraCharacteristics.HEIC_AVAILABLE_HEIC_STREAM_CONFIGURATIONS), getBase(CameraCharacteristics.HEIC_AVAILABLE_HEIC_MIN_FRAME_DURATIONS), getBase(CameraCharacteristics.HEIC_AVAILABLE_HEIC_STALL_DURATIONS), getBase(CameraCharacteristics.CONTROL_AVAILABLE_HIGH_SPEED_VIDEO_CONFIGURATIONS), getBase(CameraCharacteristics.SCALER_AVAILABLE_INPUT_OUTPUT_FORMATS_MAP), isBurstSupported());
  }
  
  public static int getTag(String paramString) {
    return nativeGetTagFromKey(paramString, Long.MAX_VALUE);
  }
  
  public static int getTag(String paramString, long paramLong) {
    return nativeGetTagFromKey(paramString, paramLong);
  }
  
  private <T> TonemapCurve getTonemapCurve() {
    float[] arrayOfFloat1 = getBase(CaptureRequest.TONEMAP_CURVE_RED);
    float[] arrayOfFloat2 = getBase(CaptureRequest.TONEMAP_CURVE_GREEN);
    float[] arrayOfFloat3 = getBase(CaptureRequest.TONEMAP_CURVE_BLUE);
    if (areValuesAllNull(new Object[] { arrayOfFloat1, arrayOfFloat2, arrayOfFloat3 }))
      return null; 
    if (arrayOfFloat1 == null || arrayOfFloat2 == null || arrayOfFloat3 == null) {
      Log.w("CameraMetadataJV", "getTonemapCurve - missing tone curve components");
      return null;
    } 
    return new TonemapCurve(arrayOfFloat1, arrayOfFloat2, arrayOfFloat3);
  }
  
  private boolean isBurstSupported() {
    boolean bool2;
    boolean bool1 = false;
    int[] arrayOfInt = getBase(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
    int i = arrayOfInt.length;
    byte b = 0;
    while (true) {
      bool2 = bool1;
      if (b < i) {
        if (arrayOfInt[b] == 6) {
          bool2 = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    return bool2;
  }
  
  public static CameraMetadataNative move(CameraMetadataNative paramCameraMetadataNative) {
    CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
    cameraMetadataNative.swap(paramCameraMetadataNative);
    return cameraMetadataNative;
  }
  
  @FastNative
  private static native long nativeAllocate();
  
  @FastNative
  private static native long nativeAllocateCopy(long paramLong) throws NullPointerException;
  
  @FastNative
  private static synchronized native void nativeClose(long paramLong);
  
  private static synchronized native void nativeDump(long paramLong) throws IOException;
  
  @FastNative
  private static synchronized native ArrayList nativeGetAllVendorKeys(long paramLong, Class paramClass);
  
  @FastNative
  private static synchronized native int nativeGetEntryCount(long paramLong);
  
  @FastNative
  private static native int nativeGetTagFromKey(String paramString, long paramLong) throws IllegalArgumentException;
  
  @FastNative
  private static synchronized native int nativeGetTagFromKeyLocal(long paramLong, String paramString) throws IllegalArgumentException;
  
  @FastNative
  private static native int nativeGetTypeFromTag(int paramInt, long paramLong) throws IllegalArgumentException;
  
  @FastNative
  private static synchronized native int nativeGetTypeFromTagLocal(long paramLong, int paramInt) throws IllegalArgumentException;
  
  @FastNative
  private static synchronized native boolean nativeIsEmpty(long paramLong);
  
  @FastNative
  private static synchronized native void nativeReadFromParcel(Parcel paramParcel, long paramLong);
  
  @FastNative
  private static synchronized native byte[] nativeReadValues(int paramInt, long paramLong);
  
  private static native int nativeSetupGlobalVendorTagDescriptor();
  
  @FastNative
  private static synchronized native void nativeSwap(long paramLong1, long paramLong2) throws NullPointerException;
  
  @FastNative
  private static synchronized native void nativeWriteToParcel(Parcel paramParcel, long paramLong);
  
  @FastNative
  private static synchronized native void nativeWriteValues(int paramInt, byte[] paramArrayOfbyte, long paramLong);
  
  private void parseRecommendedConfigurations(RecommendedStreamConfiguration[] paramArrayOfRecommendedStreamConfiguration, StreamConfigurationMap paramStreamConfigurationMap, boolean paramBoolean, ArrayList<ArrayList<StreamConfiguration>> paramArrayList, ArrayList<ArrayList<StreamConfigurationDuration>> paramArrayList1, ArrayList<ArrayList<StreamConfigurationDuration>> paramArrayList2, boolean[] paramArrayOfboolean) {
    paramArrayList.ensureCapacity(32);
    paramArrayList1.ensureCapacity(32);
    paramArrayList2.ensureCapacity(32);
    int i;
    for (i = 0; i < 32; i++) {
      paramArrayList.add(new ArrayList<>());
      paramArrayList1.add(new ArrayList<>());
      paramArrayList2.add(new ArrayList<>());
    } 
    int j = paramArrayOfRecommendedStreamConfiguration.length;
    for (byte b = 0; b < j; b++) {
      RecommendedStreamConfiguration recommendedStreamConfiguration = paramArrayOfRecommendedStreamConfiguration[b];
      int k = recommendedStreamConfiguration.getWidth();
      int m = recommendedStreamConfiguration.getHeight();
      int n = recommendedStreamConfiguration.getFormat();
      if (paramBoolean) {
        i = StreamConfigurationMap.depthFormatToPublic(n);
      } else {
        i = StreamConfigurationMap.imageFormatToPublic(n);
      } 
      Size size = new Size(k, m);
      int i1 = recommendedStreamConfiguration.getUsecaseBitmap();
      if (!recommendedStreamConfiguration.isInput()) {
        StreamConfiguration streamConfiguration = new StreamConfiguration(n, k, m, false);
        long l1 = paramStreamConfigurationMap.getOutputMinFrameDuration(i, size);
        if (l1 > 0L) {
          StreamConfigurationDuration streamConfigurationDuration = new StreamConfigurationDuration(n, k, m, l1);
        } else {
          recommendedStreamConfiguration = null;
        } 
        int i2 = i;
        long l2 = paramStreamConfigurationMap.getOutputStallDuration(i2, size);
        if (l2 > 0L) {
          StreamConfigurationDuration streamConfigurationDuration = new StreamConfigurationDuration(n, k, m, l2);
        } else {
          size = null;
        } 
        for (i = 0; i < 32; i++) {
          if ((i1 & 1 << i) != 0) {
            ((ArrayList<StreamConfiguration>)paramArrayList.get(i)).add(streamConfiguration);
            if (l1 > 0L)
              ((ArrayList<RecommendedStreamConfiguration>)paramArrayList1.get(i)).add(recommendedStreamConfiguration); 
            if (l2 > 0L)
              ((ArrayList<Size>)paramArrayList2.get(i)).add(size); 
            if (paramArrayOfboolean != null && !paramArrayOfboolean[i] && i2 == 34)
              paramArrayOfboolean[i] = true; 
          } 
        } 
      } else if (i1 == 16) {
        ((ArrayList<StreamConfiguration>)paramArrayList.get(4)).add(new StreamConfiguration(n, k, m, true));
      } else {
        throw new IllegalArgumentException("Recommended input stream configurations should only be advertised in the ZSL use case!");
      } 
    } 
  }
  
  private static void registerAllMarshalers() {
    MarshalQueryable[] arrayOfMarshalQueryable = new MarshalQueryable[21];
    MarshalQueryablePrimitive marshalQueryablePrimitive = new MarshalQueryablePrimitive();
    byte b = 0;
    arrayOfMarshalQueryable[0] = (MarshalQueryable)marshalQueryablePrimitive;
    arrayOfMarshalQueryable[1] = (MarshalQueryable)new MarshalQueryableEnum();
    arrayOfMarshalQueryable[2] = (MarshalQueryable)new MarshalQueryableArray();
    arrayOfMarshalQueryable[3] = (MarshalQueryable)new MarshalQueryableBoolean();
    arrayOfMarshalQueryable[4] = (MarshalQueryable)new MarshalQueryableNativeByteToInteger();
    arrayOfMarshalQueryable[5] = (MarshalQueryable)new MarshalQueryableRect();
    arrayOfMarshalQueryable[6] = (MarshalQueryable)new MarshalQueryableSize();
    arrayOfMarshalQueryable[7] = (MarshalQueryable)new MarshalQueryableSizeF();
    arrayOfMarshalQueryable[8] = (MarshalQueryable)new MarshalQueryableString();
    arrayOfMarshalQueryable[9] = (MarshalQueryable)new MarshalQueryableReprocessFormatsMap();
    arrayOfMarshalQueryable[10] = (MarshalQueryable)new MarshalQueryableRange();
    arrayOfMarshalQueryable[11] = (MarshalQueryable)new MarshalQueryablePair();
    arrayOfMarshalQueryable[12] = (MarshalQueryable)new MarshalQueryableMeteringRectangle();
    arrayOfMarshalQueryable[13] = (MarshalQueryable)new MarshalQueryableColorSpaceTransform();
    arrayOfMarshalQueryable[14] = (MarshalQueryable)new MarshalQueryableStreamConfiguration();
    arrayOfMarshalQueryable[15] = (MarshalQueryable)new MarshalQueryableStreamConfigurationDuration();
    arrayOfMarshalQueryable[16] = (MarshalQueryable)new MarshalQueryableRggbChannelVector();
    arrayOfMarshalQueryable[17] = (MarshalQueryable)new MarshalQueryableBlackLevelPattern();
    arrayOfMarshalQueryable[18] = (MarshalQueryable)new MarshalQueryableHighSpeedVideoConfiguration();
    arrayOfMarshalQueryable[19] = (MarshalQueryable)new MarshalQueryableRecommendedStreamConfiguration();
    arrayOfMarshalQueryable[20] = (MarshalQueryable)new MarshalQueryableParcelable();
    int i = arrayOfMarshalQueryable.length;
    while (b < i) {
      MarshalRegistry.registerMarshalQueryable(arrayOfMarshalQueryable[b]);
      b++;
    } 
  }
  
  private boolean setAvailableFormats(int[] paramArrayOfint) {
    if (paramArrayOfint == null)
      return false; 
    int[] arrayOfInt = new int[paramArrayOfint.length];
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      arrayOfInt[b] = paramArrayOfint[b];
      if (paramArrayOfint[b] == 256)
        arrayOfInt[b] = 33; 
    } 
    setBase(CameraCharacteristics.SCALER_AVAILABLE_FORMATS, arrayOfInt);
    return true;
  }
  
  private <T> void setBase(CameraCharacteristics.Key<T> paramKey, T paramT) {
    setBase(paramKey.getNativeKey(), paramT);
  }
  
  private <T> void setBase(CaptureRequest.Key<T> paramKey, T paramT) {
    setBase(paramKey.getNativeKey(), paramT);
  }
  
  private <T> void setBase(CaptureResult.Key<T> paramKey, T paramT) {
    setBase(paramKey.getNativeKey(), paramT);
  }
  
  private <T> void setBase(Key<T> paramKey, T paramT) {
    int i;
    if (paramKey.hasTag()) {
      i = paramKey.getTag();
    } else {
      i = nativeGetTagFromKeyLocal(this.mMetadataPtr, paramKey.getName());
      paramKey.cacheTag(i);
    } 
    if (paramT == null) {
      writeValues(i, null);
      return;
    } 
    Marshaler<T> marshaler = getMarshalerForKey(paramKey, nativeGetTypeFromTagLocal(this.mMetadataPtr, i));
    byte[] arrayOfByte = new byte[marshaler.calculateMarshalSize(paramT)];
    marshaler.marshal(paramT, ByteBuffer.wrap(arrayOfByte).order(ByteOrder.nativeOrder()));
    writeValues(i, arrayOfByte);
  }
  
  private boolean setFaceRectangles(Rect[] paramArrayOfRect) {
    if (paramArrayOfRect == null)
      return false; 
    Rect[] arrayOfRect = new Rect[paramArrayOfRect.length];
    for (byte b = 0; b < arrayOfRect.length; b++)
      arrayOfRect[b] = new Rect((paramArrayOfRect[b]).left, (paramArrayOfRect[b]).top, (paramArrayOfRect[b]).right + (paramArrayOfRect[b]).left, (paramArrayOfRect[b]).bottom + (paramArrayOfRect[b]).top); 
    setBase(CaptureResult.STATISTICS_FACE_RECTANGLES, arrayOfRect);
    return true;
  }
  
  private boolean setFaces(Face[] paramArrayOfFace) {
    int i = 0;
    if (paramArrayOfFace == null)
      return false; 
    int j = paramArrayOfFace.length;
    boolean bool = true;
    int k = paramArrayOfFace.length;
    int m = 0;
    while (m < k) {
      int i1;
      Face face = paramArrayOfFace[m];
      if (face == null) {
        i1 = j - 1;
        Log.w("CameraMetadataJV", "setFaces - null face detected, skipping");
      } else {
        i1 = j;
        if (face.getId() == -1) {
          bool = false;
          i1 = j;
        } 
      } 
      m++;
      j = i1;
    } 
    Rect[] arrayOfRect = new Rect[j];
    byte[] arrayOfByte = new byte[j];
    int[] arrayOfInt2 = null;
    int[] arrayOfInt1 = null;
    if (bool) {
      arrayOfInt2 = new int[j];
      arrayOfInt1 = new int[j * 6];
    } 
    j = 0;
    int n = paramArrayOfFace.length;
    for (m = i; m < n; m++) {
      Face face = paramArrayOfFace[m];
      if (face != null) {
        arrayOfRect[j] = face.getBounds();
        arrayOfByte[j] = (byte)(byte)face.getScore();
        if (bool) {
          arrayOfInt2[j] = face.getId();
          k = 0 + 1;
          arrayOfInt1[j * 6 + 0] = (face.getLeftEyePosition()).x;
          i = k + 1;
          arrayOfInt1[j * 6 + k] = (face.getLeftEyePosition()).y;
          k = i + 1;
          arrayOfInt1[j * 6 + i] = (face.getRightEyePosition()).x;
          i = k + 1;
          arrayOfInt1[j * 6 + k] = (face.getRightEyePosition()).y;
          k = i + 1;
          arrayOfInt1[j * 6 + i] = (face.getMouthPosition()).x;
          arrayOfInt1[j * 6 + k] = (face.getMouthPosition()).y;
        } 
        j++;
      } 
    } 
    set(CaptureResult.STATISTICS_FACE_RECTANGLES, arrayOfRect);
    set(CaptureResult.STATISTICS_FACE_IDS, arrayOfInt2);
    set(CaptureResult.STATISTICS_FACE_LANDMARKS, arrayOfInt1);
    set(CaptureResult.STATISTICS_FACE_SCORES, arrayOfByte);
    return true;
  }
  
  private boolean setGpsLocation(Location paramLocation) {
    if (paramLocation == null)
      return false; 
    double d1 = paramLocation.getLatitude();
    double d2 = paramLocation.getLongitude();
    double d3 = paramLocation.getAltitude();
    String str = translateLocationProviderToProcess(paramLocation.getProvider());
    long l = paramLocation.getTime() / 1000L;
    set(CaptureRequest.JPEG_GPS_TIMESTAMP, Long.valueOf(l));
    set(CaptureRequest.JPEG_GPS_COORDINATES, new double[] { d1, d2, d3 });
    if (str == null) {
      Log.w("CameraMetadataJV", "setGpsLocation - No process method, Location is not from a GPS or NETWORKprovider");
    } else {
      setBase(CaptureRequest.JPEG_GPS_PROCESSING_METHOD, str);
    } 
    return true;
  }
  
  private <T> boolean setTonemapCurve(TonemapCurve paramTonemapCurve) {
    if (paramTonemapCurve == null)
      return false; 
    float[][] arrayOfFloat = new float[3][];
    for (byte b = 0; b <= 2; b++) {
      arrayOfFloat[b] = new float[paramTonemapCurve.getPointCount(b) * 2];
      paramTonemapCurve.copyColorCurve(b, arrayOfFloat[b], 0);
    } 
    setBase(CaptureRequest.TONEMAP_CURVE_RED, arrayOfFloat[0]);
    setBase(CaptureRequest.TONEMAP_CURVE_GREEN, arrayOfFloat[1]);
    setBase(CaptureRequest.TONEMAP_CURVE_BLUE, arrayOfFloat[2]);
    return true;
  }
  
  public static void setupGlobalVendorTagDescriptor() throws ServiceSpecificException {
    int i = nativeSetupGlobalVendorTagDescriptor();
    if (i == 0)
      return; 
    throw new ServiceSpecificException(i, "Failure to set up global vendor tags");
  }
  
  private static String translateLocationProviderToProcess(String paramString) {
    if (paramString == null)
      return null; 
    byte b = -1;
    int i = paramString.hashCode();
    if (i != 102570) {
      if (i == 1843485230 && paramString.equals("network"))
        b = 1; 
    } else if (paramString.equals("gps")) {
      b = 0;
    } 
    return (b != 0) ? ((b != 1) ? null : "CELLID") : "GPS";
  }
  
  private static String translateProcessToLocationProvider(String paramString) {
    if (paramString == null)
      return null; 
    byte b = -1;
    int i = paramString.hashCode();
    if (i != 70794) {
      if (i == 1984215549 && paramString.equals("CELLID"))
        b = 1; 
    } else if (paramString.equals("GPS")) {
      b = 0;
    } 
    return (b != 0) ? ((b != 1) ? null : "network") : "gps";
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpToLog() {
    try {
      nativeDump(this.mMetadataPtr);
    } catch (IOException iOException) {
      Log.wtf("CameraMetadataJV", "Dump logging failed", iOException);
    } 
  }
  
  protected void finalize() throws Throwable {
    try {
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public <T> T get(CameraCharacteristics.Key<T> paramKey) {
    return get(paramKey.getNativeKey());
  }
  
  public <T> T get(CaptureRequest.Key<T> paramKey) {
    return get(paramKey.getNativeKey());
  }
  
  public <T> T get(CaptureResult.Key<T> paramKey) {
    return get(paramKey.getNativeKey());
  }
  
  public <T> T get(Key<T> paramKey) {
    Objects.requireNonNull(paramKey, "key must not be null");
    GetCommand getCommand = sGetCommandMap.get(paramKey);
    return (getCommand != null) ? getCommand.getValue(this, paramKey) : getBase(paramKey);
  }
  
  public <K> ArrayList<K> getAllVendorKeys(Class<K> paramClass) {
    if (paramClass != null)
      return nativeGetAllVendorKeys(this.mMetadataPtr, paramClass); 
    throw null;
  }
  
  public int getEntryCount() {
    return nativeGetEntryCount(this.mMetadataPtr);
  }
  
  public long getMetadataPtr() {
    return this.mMetadataPtr;
  }
  
  public ArrayList<RecommendedStreamConfigurationMap> getRecommendedStreamConfigurations() {
    // Byte code:
    //   0: aload_0
    //   1: getstatic android/hardware/camera2/CameraCharacteristics.SCALER_AVAILABLE_RECOMMENDED_STREAM_CONFIGURATIONS : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   4: invokespecial getBase : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   7: checkcast [Landroid/hardware/camera2/params/RecommendedStreamConfiguration;
    //   10: astore_1
    //   11: aload_0
    //   12: getstatic android/hardware/camera2/CameraCharacteristics.DEPTH_AVAILABLE_RECOMMENDED_DEPTH_STREAM_CONFIGURATIONS : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   15: invokespecial getBase : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   18: checkcast [Landroid/hardware/camera2/params/RecommendedStreamConfiguration;
    //   21: astore_2
    //   22: aload_1
    //   23: ifnonnull -> 32
    //   26: aload_2
    //   27: ifnonnull -> 32
    //   30: aconst_null
    //   31: areturn
    //   32: aload_0
    //   33: invokespecial getStreamConfigurationMap : ()Landroid/hardware/camera2/params/StreamConfigurationMap;
    //   36: astore_3
    //   37: new java/util/ArrayList
    //   40: dup
    //   41: invokespecial <init> : ()V
    //   44: astore #4
    //   46: new java/util/ArrayList
    //   49: dup
    //   50: invokespecial <init> : ()V
    //   53: astore #5
    //   55: new java/util/ArrayList
    //   58: dup
    //   59: invokespecial <init> : ()V
    //   62: astore #6
    //   64: new java/util/ArrayList
    //   67: dup
    //   68: invokespecial <init> : ()V
    //   71: astore #7
    //   73: bipush #32
    //   75: newarray boolean
    //   77: astore #8
    //   79: aload_1
    //   80: ifnull -> 114
    //   83: aload_0
    //   84: aload_1
    //   85: aload_3
    //   86: iconst_0
    //   87: aload #5
    //   89: aload #6
    //   91: aload #7
    //   93: aload #8
    //   95: invokespecial parseRecommendedConfigurations : ([Landroid/hardware/camera2/params/RecommendedStreamConfiguration;Landroid/hardware/camera2/params/StreamConfigurationMap;ZLjava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;[Z)V
    //   98: goto -> 114
    //   101: astore #6
    //   103: ldc 'CameraMetadataJV'
    //   105: ldc_w 'Failed parsing the recommended stream configurations!'
    //   108: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   111: pop
    //   112: aconst_null
    //   113: areturn
    //   114: new java/util/ArrayList
    //   117: dup
    //   118: invokespecial <init> : ()V
    //   121: astore #9
    //   123: new java/util/ArrayList
    //   126: dup
    //   127: invokespecial <init> : ()V
    //   130: astore #10
    //   132: new java/util/ArrayList
    //   135: dup
    //   136: invokespecial <init> : ()V
    //   139: astore #11
    //   141: aload_2
    //   142: ifnull -> 175
    //   145: aload_0
    //   146: aload_2
    //   147: aload_3
    //   148: iconst_1
    //   149: aload #9
    //   151: aload #10
    //   153: aload #11
    //   155: aconst_null
    //   156: invokespecial parseRecommendedConfigurations : ([Landroid/hardware/camera2/params/RecommendedStreamConfiguration;Landroid/hardware/camera2/params/StreamConfigurationMap;ZLjava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;[Z)V
    //   159: goto -> 175
    //   162: astore #6
    //   164: ldc 'CameraMetadataJV'
    //   166: ldc_w 'Failed parsing the recommended depth stream configurations!'
    //   169: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   172: pop
    //   173: aconst_null
    //   174: areturn
    //   175: aload_0
    //   176: getstatic android/hardware/camera2/CameraCharacteristics.SCALER_AVAILABLE_RECOMMENDED_INPUT_OUTPUT_FORMATS_MAP : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   179: invokespecial getBase : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   182: checkcast android/hardware/camera2/params/ReprocessFormatsMap
    //   185: astore #12
    //   187: aload_0
    //   188: getstatic android/hardware/camera2/CameraCharacteristics.CONTROL_AVAILABLE_HIGH_SPEED_VIDEO_CONFIGURATIONS : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   191: invokespecial getBase : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   194: checkcast [Landroid/hardware/camera2/params/HighSpeedVideoConfiguration;
    //   197: astore #13
    //   199: aload_0
    //   200: invokespecial isBurstSupported : ()Z
    //   203: istore #14
    //   205: aload #4
    //   207: bipush #32
    //   209: invokevirtual ensureCapacity : (I)V
    //   212: iconst_0
    //   213: istore #15
    //   215: iload #15
    //   217: bipush #32
    //   219: if_icmpge -> 650
    //   222: new android/hardware/camera2/impl/CameraMetadataNative$StreamConfigurationData
    //   225: dup
    //   226: aload_0
    //   227: aconst_null
    //   228: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/impl/CameraMetadataNative$1;)V
    //   231: astore #16
    //   233: aload_1
    //   234: ifnull -> 276
    //   237: aload_0
    //   238: aload #5
    //   240: iload #15
    //   242: invokevirtual get : (I)Ljava/lang/Object;
    //   245: checkcast java/util/ArrayList
    //   248: aload #6
    //   250: iload #15
    //   252: invokevirtual get : (I)Ljava/lang/Object;
    //   255: checkcast java/util/ArrayList
    //   258: aload #7
    //   260: iload #15
    //   262: invokevirtual get : (I)Ljava/lang/Object;
    //   265: checkcast java/util/ArrayList
    //   268: aload #16
    //   270: invokevirtual initializeStreamConfigurationData : (Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Landroid/hardware/camera2/impl/CameraMetadataNative$StreamConfigurationData;)V
    //   273: goto -> 276
    //   276: aload #6
    //   278: astore_3
    //   279: new android/hardware/camera2/impl/CameraMetadataNative$StreamConfigurationData
    //   282: dup
    //   283: aload_0
    //   284: aconst_null
    //   285: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/impl/CameraMetadataNative$1;)V
    //   288: astore #6
    //   290: aload_2
    //   291: ifnull -> 333
    //   294: aload_0
    //   295: aload #9
    //   297: iload #15
    //   299: invokevirtual get : (I)Ljava/lang/Object;
    //   302: checkcast java/util/ArrayList
    //   305: aload #10
    //   307: iload #15
    //   309: invokevirtual get : (I)Ljava/lang/Object;
    //   312: checkcast java/util/ArrayList
    //   315: aload #11
    //   317: iload #15
    //   319: invokevirtual get : (I)Ljava/lang/Object;
    //   322: checkcast java/util/ArrayList
    //   325: aload #6
    //   327: invokevirtual initializeStreamConfigurationData : (Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Landroid/hardware/camera2/impl/CameraMetadataNative$StreamConfigurationData;)V
    //   330: goto -> 333
    //   333: aload #16
    //   335: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   338: ifnull -> 350
    //   341: aload #16
    //   343: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   346: arraylength
    //   347: ifne -> 370
    //   350: aload #6
    //   352: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   355: ifnull -> 634
    //   358: aload #6
    //   360: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   363: arraylength
    //   364: ifne -> 370
    //   367: goto -> 634
    //   370: iload #15
    //   372: ifeq -> 567
    //   375: iload #15
    //   377: iconst_1
    //   378: if_icmpeq -> 521
    //   381: iload #15
    //   383: iconst_2
    //   384: if_icmpeq -> 567
    //   387: iload #15
    //   389: iconst_4
    //   390: if_icmpeq -> 463
    //   393: iload #15
    //   395: iconst_5
    //   396: if_icmpeq -> 567
    //   399: iload #15
    //   401: bipush #6
    //   403: if_icmpeq -> 567
    //   406: new android/hardware/camera2/params/StreamConfigurationMap
    //   409: dup
    //   410: aload #16
    //   412: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   415: aload #16
    //   417: getfield minDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   420: aload #16
    //   422: getfield stallDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   425: aload #6
    //   427: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   430: aload #6
    //   432: getfield minDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   435: aload #6
    //   437: getfield stallDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   440: aconst_null
    //   441: aconst_null
    //   442: aconst_null
    //   443: aconst_null
    //   444: aconst_null
    //   445: aconst_null
    //   446: aconst_null
    //   447: aconst_null
    //   448: iload #14
    //   450: aload #8
    //   452: iload #15
    //   454: baload
    //   455: invokespecial <init> : ([Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/HighSpeedVideoConfiguration;Landroid/hardware/camera2/params/ReprocessFormatsMap;ZZ)V
    //   458: astore #6
    //   460: goto -> 609
    //   463: new android/hardware/camera2/params/StreamConfigurationMap
    //   466: dup
    //   467: aload #16
    //   469: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   472: aload #16
    //   474: getfield minDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   477: aload #16
    //   479: getfield stallDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   482: aload #6
    //   484: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   487: aload #6
    //   489: getfield minDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   492: aload #6
    //   494: getfield stallDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   497: aconst_null
    //   498: aconst_null
    //   499: aconst_null
    //   500: aconst_null
    //   501: aconst_null
    //   502: aconst_null
    //   503: aconst_null
    //   504: aload #12
    //   506: iload #14
    //   508: aload #8
    //   510: iload #15
    //   512: baload
    //   513: invokespecial <init> : ([Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/HighSpeedVideoConfiguration;Landroid/hardware/camera2/params/ReprocessFormatsMap;ZZ)V
    //   516: astore #6
    //   518: goto -> 609
    //   521: new android/hardware/camera2/params/StreamConfigurationMap
    //   524: dup
    //   525: aload #16
    //   527: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   530: aload #16
    //   532: getfield minDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   535: aload #16
    //   537: getfield stallDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   540: aconst_null
    //   541: aconst_null
    //   542: aconst_null
    //   543: aconst_null
    //   544: aconst_null
    //   545: aconst_null
    //   546: aconst_null
    //   547: aconst_null
    //   548: aconst_null
    //   549: aload #13
    //   551: aconst_null
    //   552: iload #14
    //   554: aload #8
    //   556: iload #15
    //   558: baload
    //   559: invokespecial <init> : ([Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/HighSpeedVideoConfiguration;Landroid/hardware/camera2/params/ReprocessFormatsMap;ZZ)V
    //   562: astore #6
    //   564: goto -> 609
    //   567: new android/hardware/camera2/params/StreamConfigurationMap
    //   570: dup
    //   571: aload #16
    //   573: getfield streamConfigurationArray : [Landroid/hardware/camera2/params/StreamConfiguration;
    //   576: aload #16
    //   578: getfield minDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   581: aload #16
    //   583: getfield stallDurationArray : [Landroid/hardware/camera2/params/StreamConfigurationDuration;
    //   586: aconst_null
    //   587: aconst_null
    //   588: aconst_null
    //   589: aconst_null
    //   590: aconst_null
    //   591: aconst_null
    //   592: aconst_null
    //   593: aconst_null
    //   594: aconst_null
    //   595: aconst_null
    //   596: aconst_null
    //   597: iload #14
    //   599: aload #8
    //   601: iload #15
    //   603: baload
    //   604: invokespecial <init> : ([Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfiguration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/StreamConfigurationDuration;[Landroid/hardware/camera2/params/HighSpeedVideoConfiguration;Landroid/hardware/camera2/params/ReprocessFormatsMap;ZZ)V
    //   607: astore #6
    //   609: aload #4
    //   611: new android/hardware/camera2/params/RecommendedStreamConfigurationMap
    //   614: dup
    //   615: aload #6
    //   617: iload #15
    //   619: aload #8
    //   621: iload #15
    //   623: baload
    //   624: invokespecial <init> : (Landroid/hardware/camera2/params/StreamConfigurationMap;IZ)V
    //   627: invokevirtual add : (Ljava/lang/Object;)Z
    //   630: pop
    //   631: goto -> 641
    //   634: aload #4
    //   636: aconst_null
    //   637: invokevirtual add : (Ljava/lang/Object;)Z
    //   640: pop
    //   641: iinc #15, 1
    //   644: aload_3
    //   645: astore #6
    //   647: goto -> 215
    //   650: aload #4
    //   652: areturn
    // Exception table:
    //   from	to	target	type
    //   83	98	101	java/lang/IllegalArgumentException
    //   145	159	162	java/lang/IllegalArgumentException
  }
  
  public void initializeStreamConfigurationData(ArrayList<StreamConfiguration> paramArrayList, ArrayList<StreamConfigurationDuration> paramArrayList1, ArrayList<StreamConfigurationDuration> paramArrayList2, StreamConfigurationData paramStreamConfigurationData) {
    if (paramStreamConfigurationData == null || paramArrayList == null)
      return; 
    paramStreamConfigurationData.streamConfigurationArray = new StreamConfiguration[paramArrayList.size()];
    paramStreamConfigurationData.streamConfigurationArray = paramArrayList.<StreamConfiguration>toArray(paramStreamConfigurationData.streamConfigurationArray);
    if (paramArrayList1 != null && !paramArrayList1.isEmpty()) {
      paramStreamConfigurationData.minDurationArray = new StreamConfigurationDuration[paramArrayList1.size()];
      paramStreamConfigurationData.minDurationArray = paramArrayList1.<StreamConfigurationDuration>toArray(paramStreamConfigurationData.minDurationArray);
    } else {
      paramStreamConfigurationData.minDurationArray = new StreamConfigurationDuration[0];
    } 
    if (paramArrayList2 != null && !paramArrayList2.isEmpty()) {
      paramStreamConfigurationData.stallDurationArray = new StreamConfigurationDuration[paramArrayList2.size()];
      paramStreamConfigurationData.stallDurationArray = paramArrayList2.<StreamConfigurationDuration>toArray(paramStreamConfigurationData.stallDurationArray);
    } else {
      paramStreamConfigurationData.stallDurationArray = new StreamConfigurationDuration[0];
    } 
  }
  
  public boolean isEmpty() {
    return nativeIsEmpty(this.mMetadataPtr);
  }
  
  public void readFromParcel(Parcel paramParcel) {
    nativeReadFromParcel(paramParcel, this.mMetadataPtr);
  }
  
  public byte[] readValues(int paramInt) {
    return nativeReadValues(paramInt, this.mMetadataPtr);
  }
  
  public <T> void set(CameraCharacteristics.Key<T> paramKey, T paramT) {
    set(paramKey.getNativeKey(), paramT);
  }
  
  public <T> void set(CaptureRequest.Key<T> paramKey, T paramT) {
    set(paramKey.getNativeKey(), paramT);
  }
  
  public <T> void set(CaptureResult.Key<T> paramKey, T paramT) {
    set(paramKey.getNativeKey(), paramT);
  }
  
  public <T> void set(Key<T> paramKey, T paramT) {
    SetCommand setCommand = sSetCommandMap.get(paramKey);
    if (setCommand != null) {
      setCommand.setValue(this, paramT);
      return;
    } 
    setBase(paramKey, paramT);
  }
  
  public void setCameraId(int paramInt) {
    this.mCameraId = paramInt;
  }
  
  public void setDisplaySize(Size paramSize) {
    this.mDisplaySize = paramSize;
  }
  
  public void setHasMandatoryConcurrentStreams(boolean paramBoolean) {
    this.mHasMandatoryConcurrentStreams = paramBoolean;
  }
  
  public void swap(CameraMetadataNative paramCameraMetadataNative) {
    nativeSwap(this.mMetadataPtr, paramCameraMetadataNative.mMetadataPtr);
    this.mCameraId = paramCameraMetadataNative.mCameraId;
    this.mHasMandatoryConcurrentStreams = paramCameraMetadataNative.mHasMandatoryConcurrentStreams;
    this.mDisplaySize = paramCameraMetadataNative.mDisplaySize;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    nativeWriteToParcel(paramParcel, this.mMetadataPtr);
  }
  
  public void writeValues(int paramInt, byte[] paramArrayOfbyte) {
    nativeWriteValues(paramInt, paramArrayOfbyte, this.mMetadataPtr);
  }
  
  public static class Key<T> {
    private final String mFallbackName;
    
    private boolean mHasTag;
    
    private final int mHash;
    
    private final String mName;
    
    private int mTag;
    
    private final Class<T> mType;
    
    private final TypeReference<T> mTypeReference;
    
    private long mVendorId = Long.MAX_VALUE;
    
    public Key(String param1String, TypeReference<T> param1TypeReference) {
      if (param1String != null) {
        if (param1TypeReference != null) {
          this.mName = param1String;
          this.mFallbackName = null;
          this.mType = param1TypeReference.getRawType();
          this.mTypeReference = param1TypeReference;
          this.mHash = this.mName.hashCode() ^ this.mTypeReference.hashCode();
          return;
        } 
        throw new NullPointerException("TypeReference needs to be non-null");
      } 
      throw new NullPointerException("Key needs a valid name");
    }
    
    public Key(String param1String, Class<T> param1Class) {
      if (param1String != null) {
        if (param1Class != null) {
          this.mName = param1String;
          this.mFallbackName = null;
          this.mType = param1Class;
          this.mTypeReference = TypeReference.createSpecializedTypeReference(param1Class);
          this.mHash = this.mName.hashCode() ^ this.mTypeReference.hashCode();
          return;
        } 
        throw new NullPointerException("Type needs to be non-null");
      } 
      throw new NullPointerException("Key needs a valid name");
    }
    
    public Key(String param1String, Class<T> param1Class, long param1Long) {
      if (param1String != null) {
        if (param1Class != null) {
          this.mName = param1String;
          this.mFallbackName = null;
          this.mType = param1Class;
          this.mVendorId = param1Long;
          this.mTypeReference = TypeReference.createSpecializedTypeReference(param1Class);
          this.mHash = this.mName.hashCode() ^ this.mTypeReference.hashCode();
          return;
        } 
        throw new NullPointerException("Type needs to be non-null");
      } 
      throw new NullPointerException("Key needs a valid name");
    }
    
    public Key(String param1String1, String param1String2, Class<T> param1Class) {
      if (param1String1 != null) {
        if (param1Class != null) {
          this.mName = param1String1;
          this.mFallbackName = param1String2;
          this.mType = param1Class;
          this.mTypeReference = TypeReference.createSpecializedTypeReference(param1Class);
          this.mHash = this.mName.hashCode() ^ this.mTypeReference.hashCode();
          return;
        } 
        throw new NullPointerException("Type needs to be non-null");
      } 
      throw new NullPointerException("Key needs a valid name");
    }
    
    public final void cacheTag(int param1Int) {
      this.mHasTag = true;
      this.mTag = param1Int;
    }
    
    public final boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || hashCode() != param1Object.hashCode())
        return false; 
      if (param1Object instanceof CaptureResult.Key) {
        param1Object = ((CaptureResult.Key)param1Object).getNativeKey();
      } else if (param1Object instanceof CaptureRequest.Key) {
        param1Object = ((CaptureRequest.Key)param1Object).getNativeKey();
      } else if (param1Object instanceof CameraCharacteristics.Key) {
        param1Object = ((CameraCharacteristics.Key)param1Object).getNativeKey();
      } else if (param1Object instanceof Key) {
        param1Object = param1Object;
      } else {
        return false;
      } 
      if (!this.mName.equals(((Key)param1Object).mName) || !this.mTypeReference.equals(((Key)param1Object).mTypeReference))
        bool = false; 
      return bool;
    }
    
    public final String getName() {
      return this.mName;
    }
    
    public final int getTag() {
      if (!this.mHasTag) {
        this.mTag = CameraMetadataNative.getTag(this.mName, this.mVendorId);
        this.mHasTag = true;
      } 
      return this.mTag;
    }
    
    public final Class<T> getType() {
      return this.mType;
    }
    
    public final TypeReference<T> getTypeReference() {
      return this.mTypeReference;
    }
    
    public final long getVendorId() {
      return this.mVendorId;
    }
    
    public final boolean hasTag() {
      return this.mHasTag;
    }
    
    public final int hashCode() {
      return this.mHash;
    }
  }
  
  private class StreamConfigurationData {
    StreamConfigurationDuration[] minDurationArray = null;
    
    StreamConfigurationDuration[] stallDurationArray = null;
    
    StreamConfiguration[] streamConfigurationArray = null;
    
    private StreamConfigurationData() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraMetadataNative.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
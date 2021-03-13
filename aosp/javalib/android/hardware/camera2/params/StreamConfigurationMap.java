package android.hardware.camera2.params;

import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.hardware.camera2.utils.SurfaceUtils;
import android.media.ImageReader;
import android.media.MediaCodec;
import android.media.MediaRecorder;
import android.renderscript.Allocation;
import android.util.Range;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.android.internal.util.Preconditions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public final class StreamConfigurationMap {
  private static final long DURATION_20FPS_NS = 50000000L;
  
  private static final int DURATION_MIN_FRAME = 0;
  
  private static final int DURATION_STALL = 1;
  
  private static final int HAL_DATASPACE_DEPTH = 4096;
  
  private static final int HAL_DATASPACE_DYNAMIC_DEPTH = 4098;
  
  private static final int HAL_DATASPACE_HEIF = 4099;
  
  private static final int HAL_DATASPACE_RANGE_SHIFT = 27;
  
  private static final int HAL_DATASPACE_STANDARD_SHIFT = 16;
  
  private static final int HAL_DATASPACE_TRANSFER_SHIFT = 22;
  
  private static final int HAL_DATASPACE_UNKNOWN = 0;
  
  private static final int HAL_DATASPACE_V0_JFIF = 146931712;
  
  private static final int HAL_PIXEL_FORMAT_BLOB = 33;
  
  private static final int HAL_PIXEL_FORMAT_IMPLEMENTATION_DEFINED = 34;
  
  private static final int HAL_PIXEL_FORMAT_RAW10 = 37;
  
  private static final int HAL_PIXEL_FORMAT_RAW12 = 38;
  
  private static final int HAL_PIXEL_FORMAT_RAW16 = 32;
  
  private static final int HAL_PIXEL_FORMAT_RAW_OPAQUE = 36;
  
  private static final int HAL_PIXEL_FORMAT_Y16 = 540422489;
  
  private static final int HAL_PIXEL_FORMAT_YCbCr_420_888 = 35;
  
  private static final String TAG = "StreamConfigurationMap";
  
  private final SparseIntArray mAllOutputFormats = new SparseIntArray();
  
  private final StreamConfiguration[] mConfigurations;
  
  private final StreamConfiguration[] mDepthConfigurations;
  
  private final StreamConfigurationDuration[] mDepthMinFrameDurations;
  
  private final SparseIntArray mDepthOutputFormats = new SparseIntArray();
  
  private final StreamConfigurationDuration[] mDepthStallDurations;
  
  private final StreamConfiguration[] mDynamicDepthConfigurations;
  
  private final StreamConfigurationDuration[] mDynamicDepthMinFrameDurations;
  
  private final SparseIntArray mDynamicDepthOutputFormats = new SparseIntArray();
  
  private final StreamConfigurationDuration[] mDynamicDepthStallDurations;
  
  private final StreamConfiguration[] mHeicConfigurations;
  
  private final StreamConfigurationDuration[] mHeicMinFrameDurations;
  
  private final SparseIntArray mHeicOutputFormats = new SparseIntArray();
  
  private final StreamConfigurationDuration[] mHeicStallDurations;
  
  private final SparseIntArray mHighResOutputFormats = new SparseIntArray();
  
  private final HighSpeedVideoConfiguration[] mHighSpeedVideoConfigurations;
  
  private final HashMap<Range<Integer>, Integer> mHighSpeedVideoFpsRangeMap = new HashMap<>();
  
  private final HashMap<Size, Integer> mHighSpeedVideoSizeMap = new HashMap<>();
  
  private final SparseIntArray mInputFormats = new SparseIntArray();
  
  private final ReprocessFormatsMap mInputOutputFormatsMap;
  
  private final boolean mListHighResolution;
  
  private final StreamConfigurationDuration[] mMinFrameDurations;
  
  private final SparseIntArray mOutputFormats = new SparseIntArray();
  
  private final StreamConfigurationDuration[] mStallDurations;
  
  public StreamConfigurationMap(StreamConfiguration[] paramArrayOfStreamConfiguration1, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration1, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration2, StreamConfiguration[] paramArrayOfStreamConfiguration2, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration3, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration4, StreamConfiguration[] paramArrayOfStreamConfiguration3, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration5, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration6, StreamConfiguration[] paramArrayOfStreamConfiguration4, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration7, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration8, HighSpeedVideoConfiguration[] paramArrayOfHighSpeedVideoConfiguration, ReprocessFormatsMap paramReprocessFormatsMap, boolean paramBoolean) {
    this(paramArrayOfStreamConfiguration1, paramArrayOfStreamConfigurationDuration1, paramArrayOfStreamConfigurationDuration2, paramArrayOfStreamConfiguration2, paramArrayOfStreamConfigurationDuration3, paramArrayOfStreamConfigurationDuration4, paramArrayOfStreamConfiguration3, paramArrayOfStreamConfigurationDuration5, paramArrayOfStreamConfigurationDuration6, paramArrayOfStreamConfiguration4, paramArrayOfStreamConfigurationDuration7, paramArrayOfStreamConfigurationDuration8, paramArrayOfHighSpeedVideoConfiguration, paramReprocessFormatsMap, paramBoolean, true);
  }
  
  public StreamConfigurationMap(StreamConfiguration[] paramArrayOfStreamConfiguration1, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration1, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration2, StreamConfiguration[] paramArrayOfStreamConfiguration2, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration3, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration4, StreamConfiguration[] paramArrayOfStreamConfiguration3, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration5, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration6, StreamConfiguration[] paramArrayOfStreamConfiguration4, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration7, StreamConfigurationDuration[] paramArrayOfStreamConfigurationDuration8, HighSpeedVideoConfiguration[] paramArrayOfHighSpeedVideoConfiguration, ReprocessFormatsMap paramReprocessFormatsMap, boolean paramBoolean1, boolean paramBoolean2) {
    if (paramArrayOfStreamConfiguration1 != null || paramArrayOfStreamConfiguration2 != null || paramArrayOfStreamConfiguration4 != null) {
      if (paramArrayOfStreamConfiguration1 == null) {
        this.mConfigurations = new StreamConfiguration[0];
        this.mMinFrameDurations = new StreamConfigurationDuration[0];
        this.mStallDurations = new StreamConfigurationDuration[0];
      } else {
        this.mConfigurations = (StreamConfiguration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfiguration1, "configurations");
        this.mMinFrameDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfigurationDuration1, "minFrameDurations");
        this.mStallDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfigurationDuration2, "stallDurations");
      } 
      this.mListHighResolution = paramBoolean1;
      if (paramArrayOfStreamConfiguration2 == null) {
        this.mDepthConfigurations = new StreamConfiguration[0];
        this.mDepthMinFrameDurations = new StreamConfigurationDuration[0];
        this.mDepthStallDurations = new StreamConfigurationDuration[0];
      } else {
        this.mDepthConfigurations = (StreamConfiguration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfiguration2, "depthConfigurations");
        this.mDepthMinFrameDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfigurationDuration3, "depthMinFrameDurations");
        this.mDepthStallDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfigurationDuration4, "depthStallDurations");
      } 
      if (paramArrayOfStreamConfiguration3 == null) {
        this.mDynamicDepthConfigurations = new StreamConfiguration[0];
        this.mDynamicDepthMinFrameDurations = new StreamConfigurationDuration[0];
        this.mDynamicDepthStallDurations = new StreamConfigurationDuration[0];
      } else {
        this.mDynamicDepthConfigurations = (StreamConfiguration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfiguration3, "dynamicDepthConfigurations");
        this.mDynamicDepthMinFrameDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfigurationDuration5, "dynamicDepthMinFrameDurations");
        this.mDynamicDepthStallDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfigurationDuration6, "dynamicDepthStallDurations");
      } 
      if (paramArrayOfStreamConfiguration4 == null) {
        this.mHeicConfigurations = new StreamConfiguration[0];
        this.mHeicMinFrameDurations = new StreamConfigurationDuration[0];
        this.mHeicStallDurations = new StreamConfigurationDuration[0];
      } else {
        this.mHeicConfigurations = (StreamConfiguration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfiguration4, "heicConfigurations");
        this.mHeicMinFrameDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfigurationDuration7, "heicMinFrameDurations");
        this.mHeicStallDurations = (StreamConfigurationDuration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfStreamConfigurationDuration8, "heicStallDurations");
      } 
      if (paramArrayOfHighSpeedVideoConfiguration == null) {
        this.mHighSpeedVideoConfigurations = new HighSpeedVideoConfiguration[0];
      } else {
        this.mHighSpeedVideoConfigurations = (HighSpeedVideoConfiguration[])Preconditions.checkArrayElementsNotNull((Object[])paramArrayOfHighSpeedVideoConfiguration, "highSpeedVideoConfigurations");
      } 
      label101: for (StreamConfiguration streamConfiguration : this.mConfigurations) {
        SparseIntArray sparseIntArray;
        int i = streamConfiguration.getFormat();
        if (streamConfiguration.isOutput()) {
          sparseIntArray = this.mAllOutputFormats;
          sparseIntArray.put(i, sparseIntArray.get(i) + 1);
          if (this.mListHighResolution)
            for (StreamConfigurationDuration streamConfigurationDuration : this.mMinFrameDurations) {
              if (streamConfigurationDuration.getFormat() == i && streamConfigurationDuration.getWidth() == streamConfiguration.getSize().getWidth() && streamConfigurationDuration.getHeight() == streamConfiguration.getSize().getHeight()) {
                long l1 = streamConfigurationDuration.getDuration();
                continue label101;
              } 
            }  
          long l = 0L;
          if (l <= 50000000L) {
            sparseIntArray = this.mOutputFormats;
          } else {
            sparseIntArray = this.mHighResOutputFormats;
          } 
        } else {
          sparseIntArray = this.mInputFormats;
        } 
        sparseIntArray.put(i, sparseIntArray.get(i) + 1);
      } 
      for (StreamConfiguration streamConfiguration : this.mDepthConfigurations) {
        if (streamConfiguration.isOutput())
          this.mDepthOutputFormats.put(streamConfiguration.getFormat(), this.mDepthOutputFormats.get(streamConfiguration.getFormat()) + 1); 
      } 
      for (StreamConfiguration streamConfiguration : this.mDynamicDepthConfigurations) {
        if (streamConfiguration.isOutput())
          this.mDynamicDepthOutputFormats.put(streamConfiguration.getFormat(), this.mDynamicDepthOutputFormats.get(streamConfiguration.getFormat()) + 1); 
      } 
      for (StreamConfiguration streamConfiguration : this.mHeicConfigurations) {
        if (streamConfiguration.isOutput())
          this.mHeicOutputFormats.put(streamConfiguration.getFormat(), this.mHeicOutputFormats.get(streamConfiguration.getFormat()) + 1); 
      } 
      if (paramArrayOfStreamConfiguration1 == null || !paramBoolean2 || this.mOutputFormats.indexOfKey(34) >= 0) {
        for (HighSpeedVideoConfiguration highSpeedVideoConfiguration : this.mHighSpeedVideoConfigurations) {
          Size size = highSpeedVideoConfiguration.getSize();
          Range<Integer> range = highSpeedVideoConfiguration.getFpsRange();
          Integer integer2 = this.mHighSpeedVideoSizeMap.get(size);
          Integer integer1 = integer2;
          if (integer2 == null)
            integer1 = Integer.valueOf(0); 
          this.mHighSpeedVideoSizeMap.put(size, Integer.valueOf(integer1.intValue() + 1));
          integer1 = this.mHighSpeedVideoFpsRangeMap.get(range);
          if (integer1 == null)
            integer1 = Integer.valueOf(0); 
          this.mHighSpeedVideoFpsRangeMap.put(range, Integer.valueOf(integer1.intValue() + 1));
        } 
        this.mInputOutputFormatsMap = paramReprocessFormatsMap;
        return;
      } 
      throw new AssertionError("At least one stream configuration for IMPLEMENTATION_DEFINED must exist");
    } 
    throw new NullPointerException("At least one of color/depth/heic configurations must not be null");
  }
  
  private void appendHighResOutputsString(StringBuilder paramStringBuilder) {
    paramStringBuilder.append("HighResolutionOutputs(");
    for (int i : getOutputFormats()) {
      Size[] arrayOfSize = getHighResolutionOutputSizes(i);
      if (arrayOfSize != null) {
        int j = arrayOfSize.length;
        for (byte b = 0; b < j; b++) {
          Size size = arrayOfSize[b];
          long l1 = getOutputMinFrameDuration(i, size);
          long l2 = getOutputStallDuration(i, size);
          paramStringBuilder.append(String.format("[w:%d, h:%d, format:%s(%d), min_duration:%d, stall:%d], ", new Object[] { Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()), formatToString(i), Integer.valueOf(i), Long.valueOf(l1), Long.valueOf(l2) }));
        } 
      } 
    } 
    if (paramStringBuilder.charAt(paramStringBuilder.length() - 1) == ' ')
      paramStringBuilder.delete(paramStringBuilder.length() - 2, paramStringBuilder.length()); 
    paramStringBuilder.append(")");
  }
  
  private void appendHighSpeedVideoConfigurationsString(StringBuilder paramStringBuilder) {
    paramStringBuilder.append("HighSpeedVideoConfigurations(");
    for (Size size : getHighSpeedVideoSizes()) {
      for (Range<Integer> range : getHighSpeedVideoFpsRangesFor(size)) {
        paramStringBuilder.append(String.format("[w:%d, h:%d, min_fps:%d, max_fps:%d], ", new Object[] { Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()), range.getLower(), range.getUpper() }));
      } 
    } 
    if (paramStringBuilder.charAt(paramStringBuilder.length() - 1) == ' ')
      paramStringBuilder.delete(paramStringBuilder.length() - 2, paramStringBuilder.length()); 
    paramStringBuilder.append(")");
  }
  
  private void appendInputsString(StringBuilder paramStringBuilder) {
    paramStringBuilder.append("Inputs(");
    for (int i : getInputFormats()) {
      for (Size size : getInputSizes(i)) {
        paramStringBuilder.append(String.format("[w:%d, h:%d, format:%s(%d)], ", new Object[] { Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()), formatToString(i), Integer.valueOf(i) }));
      } 
    } 
    if (paramStringBuilder.charAt(paramStringBuilder.length() - 1) == ' ')
      paramStringBuilder.delete(paramStringBuilder.length() - 2, paramStringBuilder.length()); 
    paramStringBuilder.append(")");
  }
  
  private void appendOutputsString(StringBuilder paramStringBuilder) {
    paramStringBuilder.append("Outputs(");
    for (int i : getOutputFormats()) {
      for (Size size : getOutputSizes(i)) {
        long l1 = getOutputMinFrameDuration(i, size);
        long l2 = getOutputStallDuration(i, size);
        paramStringBuilder.append(String.format("[w:%d, h:%d, format:%s(%d), min_duration:%d, stall:%d], ", new Object[] { Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()), formatToString(i), Integer.valueOf(i), Long.valueOf(l1), Long.valueOf(l2) }));
      } 
    } 
    if (paramStringBuilder.charAt(paramStringBuilder.length() - 1) == ' ')
      paramStringBuilder.delete(paramStringBuilder.length() - 2, paramStringBuilder.length()); 
    paramStringBuilder.append(")");
  }
  
  private void appendValidOutputFormatsForInputString(StringBuilder paramStringBuilder) {
    paramStringBuilder.append("ValidOutputFormatsForInput(");
    for (int i : getInputFormats()) {
      paramStringBuilder.append(String.format("[in:%s(%d), out:", new Object[] { formatToString(i), Integer.valueOf(i) }));
      int[] arrayOfInt = getValidOutputFormatsForInput(i);
      for (i = 0; i < arrayOfInt.length; i++) {
        paramStringBuilder.append(String.format("%s(%d)", new Object[] { formatToString(arrayOfInt[i]), Integer.valueOf(arrayOfInt[i]) }));
        if (i < arrayOfInt.length - 1)
          paramStringBuilder.append(", "); 
      } 
      paramStringBuilder.append("], ");
    } 
    if (paramStringBuilder.charAt(paramStringBuilder.length() - 1) == ' ')
      paramStringBuilder.delete(paramStringBuilder.length() - 2, paramStringBuilder.length()); 
    paramStringBuilder.append(")");
  }
  
  private static <T> boolean arrayContains(T[] paramArrayOfT, T paramT) {
    if (paramArrayOfT == null)
      return false; 
    int i = paramArrayOfT.length;
    for (byte b = 0; b < i; b++) {
      if (Objects.equals(paramArrayOfT[b], paramT))
        return true; 
    } 
    return false;
  }
  
  static int checkArgumentFormat(int paramInt) {
    if (ImageFormat.isPublicFormat(paramInt) || PixelFormat.isPublicFormat(paramInt))
      return paramInt; 
    throw new IllegalArgumentException(String.format("format 0x%x was not defined in either ImageFormat or PixelFormat", new Object[] { Integer.valueOf(paramInt) }));
  }
  
  static int checkArgumentFormatInternal(int paramInt) {
    if (paramInt != 33 && paramInt != 34 && paramInt != 36)
      if (paramInt != 256) {
        if (paramInt != 540422489) {
          if (paramInt != 1212500294)
            return checkArgumentFormat(paramInt); 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("An unknown internal format: ");
          stringBuilder.append(paramInt);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("An unknown internal format: ");
        stringBuilder.append(paramInt);
        throw new IllegalArgumentException(stringBuilder.toString());
      }  
    return paramInt;
  }
  
  private int checkArgumentFormatSupported(int paramInt, boolean paramBoolean) {
    checkArgumentFormat(paramInt);
    int i = imageFormatToInternal(paramInt);
    int j = imageFormatToDataspace(paramInt);
    if (paramBoolean) {
      if (j == 4096) {
        if (this.mDepthOutputFormats.indexOfKey(i) >= 0)
          return paramInt; 
      } else if (j == 4098) {
        if (this.mDynamicDepthOutputFormats.indexOfKey(i) >= 0)
          return paramInt; 
      } else if (j == 4099) {
        if (this.mHeicOutputFormats.indexOfKey(i) >= 0)
          return paramInt; 
      } else if (this.mAllOutputFormats.indexOfKey(i) >= 0) {
        return paramInt;
      } 
    } else if (this.mInputFormats.indexOfKey(i) >= 0) {
      return paramInt;
    } 
    throw new IllegalArgumentException(String.format("format %x is not supported by this stream configuration map", new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public static int depthFormatToPublic(int paramInt) {
    if (paramInt != 256) {
      if (paramInt != 540422489) {
        StringBuilder stringBuilder;
        switch (paramInt) {
          default:
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown DATASPACE_DEPTH format ");
            stringBuilder.append(paramInt);
            throw new IllegalArgumentException(stringBuilder.toString());
          case 34:
            throw new IllegalArgumentException("IMPLEMENTATION_DEFINED must not leak to public API");
          case 33:
            return 257;
          case 32:
            break;
        } 
        return 4098;
      } 
      return 1144402265;
    } 
    throw new IllegalArgumentException("ImageFormat.JPEG is an unknown internal format");
  }
  
  private String formatToString(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 3) {
          if (paramInt != 4) {
            if (paramInt != 16) {
              if (paramInt != 17) {
                if (paramInt != 256) {
                  if (paramInt != 257) {
                    switch (paramInt) {
                      default:
                        switch (paramInt) {
                          default:
                            return "UNKNOWN";
                          case 37:
                            return "RAW10";
                          case 36:
                            return "RAW_PRIVATE";
                          case 35:
                            return "YUV_420_888";
                          case 34:
                            break;
                        } 
                        return "PRIVATE";
                      case 1768253795:
                        return "DEPTH_JPEG";
                      case 1212500294:
                        return "HEIC";
                      case 1144402265:
                        return "DEPTH16";
                      case 842094169:
                        return "YV12";
                      case 540422489:
                        return "Y16";
                      case 538982489:
                        return "Y8";
                      case 4098:
                        return "RAW_DEPTH";
                      case 32:
                        return "RAW_SENSOR";
                      case 20:
                        break;
                    } 
                    return "YUY2";
                  } 
                  return "DEPTH_POINT_CLOUD";
                } 
                return "JPEG";
              } 
              return "NV21";
            } 
            return "NV16";
          } 
          return "RGB_565";
        } 
        return "RGB_888";
      } 
      return "RGBX_8888";
    } 
    return "RGBA_8888";
  }
  
  private StreamConfigurationDuration[] getDurations(int paramInt1, int paramInt2) {
    StreamConfigurationDuration[] arrayOfStreamConfigurationDuration;
    if (paramInt1 != 0) {
      if (paramInt1 == 1) {
        if (paramInt2 == 4096) {
          arrayOfStreamConfigurationDuration = this.mDepthStallDurations;
        } else if (paramInt2 == 4098) {
          arrayOfStreamConfigurationDuration = this.mDynamicDepthStallDurations;
        } else if (paramInt2 == 4099) {
          arrayOfStreamConfigurationDuration = this.mHeicStallDurations;
        } else {
          arrayOfStreamConfigurationDuration = this.mStallDurations;
        } 
        return arrayOfStreamConfigurationDuration;
      } 
      throw new IllegalArgumentException("duration was invalid");
    } 
    if (paramInt2 == 4096) {
      arrayOfStreamConfigurationDuration = this.mDepthMinFrameDurations;
    } else if (paramInt2 == 4098) {
      arrayOfStreamConfigurationDuration = this.mDynamicDepthMinFrameDurations;
    } else if (paramInt2 == 4099) {
      arrayOfStreamConfigurationDuration = this.mHeicMinFrameDurations;
    } else {
      arrayOfStreamConfigurationDuration = this.mMinFrameDurations;
    } 
    return arrayOfStreamConfigurationDuration;
  }
  
  private SparseIntArray getFormatsMap(boolean paramBoolean) {
    SparseIntArray sparseIntArray;
    if (paramBoolean) {
      sparseIntArray = this.mAllOutputFormats;
    } else {
      sparseIntArray = this.mInputFormats;
    } 
    return sparseIntArray;
  }
  
  private long getInternalFormatDuration(int paramInt1, int paramInt2, Size paramSize, int paramInt3) {
    if (isSupportedInternalConfiguration(paramInt1, paramInt2, paramSize)) {
      StreamConfigurationDuration[] arrayOfStreamConfigurationDuration = getDurations(paramInt3, paramInt2);
      paramInt3 = arrayOfStreamConfigurationDuration.length;
      for (paramInt2 = 0; paramInt2 < paramInt3; paramInt2++) {
        StreamConfigurationDuration streamConfigurationDuration = arrayOfStreamConfigurationDuration[paramInt2];
        if (streamConfigurationDuration.getFormat() == paramInt1 && streamConfigurationDuration.getWidth() == paramSize.getWidth() && streamConfigurationDuration.getHeight() == paramSize.getHeight())
          return streamConfigurationDuration.getDuration(); 
      } 
      return 0L;
    } 
    throw new IllegalArgumentException("size was not supported");
  }
  
  private Size[] getInternalFormatSizes(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
    StreamConfigurationDuration[] arrayOfStreamConfigurationDuration;
    SparseIntArray sparseIntArray;
    StreamConfiguration[] arrayOfStreamConfiguration;
    StringBuilder stringBuilder;
    StreamConfigurationMap streamConfigurationMap = this;
    int i = paramInt1;
    byte b = 0;
    int j = 4096;
    if (paramInt2 == 4096 && paramBoolean2)
      return new Size[0]; 
    if (!paramBoolean1) {
      sparseIntArray = streamConfigurationMap.mInputFormats;
    } else if (paramInt2 == 4096) {
      sparseIntArray = streamConfigurationMap.mDepthOutputFormats;
    } else if (paramInt2 == 4098) {
      sparseIntArray = streamConfigurationMap.mDynamicDepthOutputFormats;
    } else if (paramInt2 == 4099) {
      sparseIntArray = streamConfigurationMap.mHeicOutputFormats;
    } else if (paramBoolean2) {
      sparseIntArray = streamConfigurationMap.mHighResOutputFormats;
    } else {
      sparseIntArray = streamConfigurationMap.mOutputFormats;
    } 
    int k = sparseIntArray.get(i);
    if (((!paramBoolean1 || paramInt2 == 4096 || paramInt2 == 4098 || paramInt2 == 4099) && k == 0) || (paramBoolean1 && paramInt2 != 4096 && paramInt2 != 4098 && paramInt2 != 4099 && streamConfigurationMap.mAllOutputFormats.get(i) == 0))
      return null; 
    Size[] arrayOfSize = new Size[k];
    i = 0;
    if (paramInt2 == 4096) {
      arrayOfStreamConfiguration = streamConfigurationMap.mDepthConfigurations;
    } else if (paramInt2 == 4098) {
      arrayOfStreamConfiguration = streamConfigurationMap.mDynamicDepthConfigurations;
    } else if (paramInt2 == 4099) {
      arrayOfStreamConfiguration = streamConfigurationMap.mHeicConfigurations;
    } else {
      arrayOfStreamConfiguration = streamConfigurationMap.mConfigurations;
    } 
    if (paramInt2 == 4096) {
      arrayOfStreamConfigurationDuration = streamConfigurationMap.mDepthMinFrameDurations;
    } else if (paramInt2 == 4098) {
      arrayOfStreamConfigurationDuration = ((StreamConfigurationMap)arrayOfStreamConfigurationDuration).mDynamicDepthMinFrameDurations;
    } else if (paramInt2 == 4099) {
      arrayOfStreamConfigurationDuration = ((StreamConfigurationMap)arrayOfStreamConfigurationDuration).mHeicMinFrameDurations;
    } else {
      arrayOfStreamConfigurationDuration = ((StreamConfigurationMap)arrayOfStreamConfigurationDuration).mMinFrameDurations;
    } 
    int m = arrayOfStreamConfiguration.length;
    while (b < m) {
      StreamConfiguration streamConfiguration = arrayOfStreamConfiguration[b];
      int n = streamConfiguration.getFormat();
      if (n == paramInt1 && streamConfiguration.isOutput() == paramBoolean1) {
        if (paramBoolean1 && this.mListHighResolution) {
          long l2;
          long l1 = 0L;
          j = 0;
          while (true) {
            l2 = l1;
            if (j < arrayOfStreamConfigurationDuration.length) {
              StreamConfigurationDuration streamConfigurationDuration = arrayOfStreamConfigurationDuration[j];
              if (streamConfigurationDuration.getFormat() == n && streamConfigurationDuration.getWidth() == streamConfiguration.getSize().getWidth() && streamConfigurationDuration.getHeight() == streamConfiguration.getSize().getHeight()) {
                l2 = streamConfigurationDuration.getDuration();
                break;
              } 
              j++;
              continue;
            } 
            break;
          } 
          n = 4096;
          j = n;
          if (paramInt2 != 4096) {
            boolean bool;
            if (l2 > 50000000L) {
              bool = true;
            } else {
              bool = false;
            } 
            j = n;
            if (paramBoolean2 != bool) {
              j = n;
              continue;
            } 
          } 
        } 
        arrayOfSize[i] = streamConfiguration.getSize();
        i++;
      } 
      continue;
      b++;
    } 
    if (i != k && (paramInt2 == 4098 || paramInt2 == 4099)) {
      if (i <= k) {
        if (i <= 0) {
          Size[] arrayOfSize1 = new Size[0];
        } else {
          Size[] arrayOfSize1 = Arrays.<Size>copyOf(arrayOfSize, i);
        } 
      } else {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Too many dynamic depth sizes (expected ");
        stringBuilder.append(k);
        stringBuilder.append(", actual ");
        stringBuilder.append(i);
        stringBuilder.append(")");
        throw new AssertionError(stringBuilder.toString());
      } 
    } else {
      if (i == k)
        return arrayOfSize; 
      stringBuilder = new StringBuilder();
      stringBuilder.append("Too few sizes (expected ");
      stringBuilder.append(k);
      stringBuilder.append(", actual ");
      stringBuilder.append(i);
      stringBuilder.append(")");
      throw new AssertionError(stringBuilder.toString());
    } 
    return (Size[])stringBuilder;
  }
  
  private int getPublicFormatCount(boolean paramBoolean) {
    int i = getFormatsMap(paramBoolean).size();
    int j = i;
    if (paramBoolean)
      j = i + this.mDepthOutputFormats.size() + this.mDynamicDepthOutputFormats.size() + this.mHeicOutputFormats.size(); 
    return j;
  }
  
  private Size[] getPublicFormatSizes(int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    try {
      checkArgumentFormatSupported(paramInt, paramBoolean1);
      return getInternalFormatSizes(imageFormatToInternal(paramInt), imageFormatToDataspace(paramInt), paramBoolean1, paramBoolean2);
    } catch (IllegalArgumentException illegalArgumentException) {
      return null;
    } 
  }
  
  private int[] getPublicFormats(boolean paramBoolean) {
    int[] arrayOfInt = new int[getPublicFormatCount(paramBoolean)];
    byte b = 0;
    SparseIntArray sparseIntArray = getFormatsMap(paramBoolean);
    int i = 0;
    while (i < sparseIntArray.size()) {
      arrayOfInt[b] = imageFormatToPublic(sparseIntArray.keyAt(i));
      i++;
      b++;
    } 
    i = b;
    if (paramBoolean) {
      i = 0;
      while (i < this.mDepthOutputFormats.size()) {
        arrayOfInt[b] = depthFormatToPublic(this.mDepthOutputFormats.keyAt(i));
        i++;
        b++;
      } 
      int j = b;
      if (this.mDynamicDepthOutputFormats.size() > 0) {
        arrayOfInt[b] = 1768253795;
        j = b + 1;
      } 
      i = j;
      if (this.mHeicOutputFormats.size() > 0) {
        arrayOfInt[j] = 1212500294;
        i = j + 1;
      } 
    } 
    if (arrayOfInt.length == i)
      return arrayOfInt; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Too few formats ");
    stringBuilder.append(i);
    stringBuilder.append(", expected ");
    stringBuilder.append(arrayOfInt.length);
    throw new AssertionError(stringBuilder.toString());
  }
  
  static int imageFormatToDataspace(int paramInt) {
    return (paramInt != 256) ? ((paramInt != 257 && paramInt != 4098 && paramInt != 1144402265) ? ((paramInt != 1212500294) ? ((paramInt != 1768253795) ? 0 : 4098) : 4099) : 4096) : 146931712;
  }
  
  static int imageFormatToInternal(int paramInt) {
    if (paramInt != 256 && paramInt != 257)
      if (paramInt != 4098) {
        if (paramInt != 1144402265) {
          if (paramInt != 1212500294 && paramInt != 1768253795)
            return paramInt; 
        } else {
          return 540422489;
        } 
      } else {
        return 32;
      }  
    return 33;
  }
  
  public static int[] imageFormatToInternal(int[] paramArrayOfint) {
    if (paramArrayOfint == null)
      return null; 
    for (byte b = 0; b < paramArrayOfint.length; b++)
      paramArrayOfint[b] = imageFormatToInternal(paramArrayOfint[b]); 
    return paramArrayOfint;
  }
  
  public static int imageFormatToPublic(int paramInt) {
    if (paramInt != 33) {
      if (paramInt != 256)
        return paramInt; 
      throw new IllegalArgumentException("ImageFormat.JPEG is an unknown internal format");
    } 
    return 256;
  }
  
  static int[] imageFormatToPublic(int[] paramArrayOfint) {
    if (paramArrayOfint == null)
      return null; 
    for (byte b = 0; b < paramArrayOfint.length; b++)
      paramArrayOfint[b] = imageFormatToPublic(paramArrayOfint[b]); 
    return paramArrayOfint;
  }
  
  public static <T> boolean isOutputSupportedFor(Class<T> paramClass) {
    Objects.requireNonNull(paramClass, "klass must not be null");
    return (paramClass == ImageReader.class) ? true : ((paramClass == MediaRecorder.class) ? true : ((paramClass == MediaCodec.class) ? true : ((paramClass == Allocation.class) ? true : ((paramClass == SurfaceHolder.class) ? true : ((paramClass == SurfaceTexture.class))))));
  }
  
  private boolean isSupportedInternalConfiguration(int paramInt1, int paramInt2, Size paramSize) {
    StreamConfiguration[] arrayOfStreamConfiguration;
    if (paramInt2 == 4096) {
      arrayOfStreamConfiguration = this.mDepthConfigurations;
    } else if (paramInt2 == 4098) {
      arrayOfStreamConfiguration = this.mDynamicDepthConfigurations;
    } else if (paramInt2 == 4099) {
      arrayOfStreamConfiguration = this.mHeicConfigurations;
    } else {
      arrayOfStreamConfiguration = this.mConfigurations;
    } 
    for (paramInt2 = 0; paramInt2 < arrayOfStreamConfiguration.length; paramInt2++) {
      if (arrayOfStreamConfiguration[paramInt2].getFormat() == paramInt1 && arrayOfStreamConfiguration[paramInt2].getSize().equals(paramSize))
        return true; 
    } 
    return false;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof StreamConfigurationMap) {
      paramObject = paramObject;
      if (Arrays.equals((Object[])this.mConfigurations, (Object[])((StreamConfigurationMap)paramObject).mConfigurations) && Arrays.equals((Object[])this.mMinFrameDurations, (Object[])((StreamConfigurationMap)paramObject).mMinFrameDurations) && Arrays.equals((Object[])this.mStallDurations, (Object[])((StreamConfigurationMap)paramObject).mStallDurations) && Arrays.equals((Object[])this.mDepthConfigurations, (Object[])((StreamConfigurationMap)paramObject).mDepthConfigurations) && Arrays.equals((Object[])this.mDepthMinFrameDurations, (Object[])((StreamConfigurationMap)paramObject).mDepthMinFrameDurations) && Arrays.equals((Object[])this.mDepthStallDurations, (Object[])((StreamConfigurationMap)paramObject).mDepthStallDurations) && Arrays.equals((Object[])this.mDynamicDepthConfigurations, (Object[])((StreamConfigurationMap)paramObject).mDynamicDepthConfigurations) && Arrays.equals((Object[])this.mDynamicDepthMinFrameDurations, (Object[])((StreamConfigurationMap)paramObject).mDynamicDepthMinFrameDurations) && Arrays.equals((Object[])this.mDynamicDepthStallDurations, (Object[])((StreamConfigurationMap)paramObject).mDynamicDepthStallDurations) && Arrays.equals((Object[])this.mHeicConfigurations, (Object[])((StreamConfigurationMap)paramObject).mHeicConfigurations) && Arrays.equals((Object[])this.mHeicMinFrameDurations, (Object[])((StreamConfigurationMap)paramObject).mHeicMinFrameDurations) && Arrays.equals((Object[])this.mHeicStallDurations, (Object[])((StreamConfigurationMap)paramObject).mHeicStallDurations) && Arrays.equals((Object[])this.mHighSpeedVideoConfigurations, (Object[])((StreamConfigurationMap)paramObject).mHighSpeedVideoConfigurations))
        bool = true; 
      return bool;
    } 
    return false;
  }
  
  public Size[] getHighResolutionOutputSizes(int paramInt) {
    return !this.mListHighResolution ? null : getPublicFormatSizes(paramInt, true, true);
  }
  
  public Range<Integer>[] getHighSpeedVideoFpsRanges() {
    Set<Range<Integer>> set = this.mHighSpeedVideoFpsRangeMap.keySet();
    return set.<Range<Integer>>toArray((Range<Integer>[])new Range[set.size()]);
  }
  
  public Range<Integer>[] getHighSpeedVideoFpsRangesFor(Size paramSize) {
    Integer integer = this.mHighSpeedVideoSizeMap.get(paramSize);
    byte b = 0;
    if (integer != null && integer.intValue() != 0) {
      Range[] arrayOfRange = new Range[integer.intValue()];
      int i = 0;
      HighSpeedVideoConfiguration[] arrayOfHighSpeedVideoConfiguration = this.mHighSpeedVideoConfigurations;
      int j = arrayOfHighSpeedVideoConfiguration.length;
      while (b < j) {
        HighSpeedVideoConfiguration highSpeedVideoConfiguration = arrayOfHighSpeedVideoConfiguration[b];
        int k = i;
        if (paramSize.equals(highSpeedVideoConfiguration.getSize())) {
          arrayOfRange[i] = highSpeedVideoConfiguration.getFpsRange();
          k = i + 1;
        } 
        b++;
        i = k;
      } 
      return (Range<Integer>[])arrayOfRange;
    } 
    throw new IllegalArgumentException(String.format("Size %s does not support high speed video recording", new Object[] { paramSize }));
  }
  
  public Size[] getHighSpeedVideoSizes() {
    Set<Size> set = this.mHighSpeedVideoSizeMap.keySet();
    return set.<Size>toArray(new Size[set.size()]);
  }
  
  public Size[] getHighSpeedVideoSizesFor(Range<Integer> paramRange) {
    Integer integer = this.mHighSpeedVideoFpsRangeMap.get(paramRange);
    byte b = 0;
    if (integer != null && integer.intValue() != 0) {
      Size[] arrayOfSize = new Size[integer.intValue()];
      int i = 0;
      HighSpeedVideoConfiguration[] arrayOfHighSpeedVideoConfiguration = this.mHighSpeedVideoConfigurations;
      int j = arrayOfHighSpeedVideoConfiguration.length;
      while (b < j) {
        HighSpeedVideoConfiguration highSpeedVideoConfiguration = arrayOfHighSpeedVideoConfiguration[b];
        int k = i;
        if (paramRange.equals(highSpeedVideoConfiguration.getFpsRange())) {
          arrayOfSize[i] = highSpeedVideoConfiguration.getSize();
          k = i + 1;
        } 
        b++;
        i = k;
      } 
      return arrayOfSize;
    } 
    throw new IllegalArgumentException(String.format("FpsRange %s does not support high speed video recording", new Object[] { paramRange }));
  }
  
  public int[] getInputFormats() {
    return getPublicFormats(false);
  }
  
  public Size[] getInputSizes(int paramInt) {
    return getPublicFormatSizes(paramInt, false, false);
  }
  
  public int[] getOutputFormats() {
    return getPublicFormats(true);
  }
  
  public long getOutputMinFrameDuration(int paramInt, Size paramSize) {
    Objects.requireNonNull(paramSize, "size must not be null");
    checkArgumentFormatSupported(paramInt, true);
    return getInternalFormatDuration(imageFormatToInternal(paramInt), imageFormatToDataspace(paramInt), paramSize, 0);
  }
  
  public <T> long getOutputMinFrameDuration(Class<T> paramClass, Size paramSize) {
    if (isOutputSupportedFor(paramClass))
      return getInternalFormatDuration(34, 0, paramSize, 0); 
    throw new IllegalArgumentException("klass was not supported");
  }
  
  public Size[] getOutputSizes(int paramInt) {
    return getPublicFormatSizes(paramInt, true, false);
  }
  
  public <T> Size[] getOutputSizes(Class<T> paramClass) {
    return !isOutputSupportedFor(paramClass) ? null : getInternalFormatSizes(34, 0, true, false);
  }
  
  public long getOutputStallDuration(int paramInt, Size paramSize) {
    checkArgumentFormatSupported(paramInt, true);
    return getInternalFormatDuration(imageFormatToInternal(paramInt), imageFormatToDataspace(paramInt), paramSize, 1);
  }
  
  public <T> long getOutputStallDuration(Class<T> paramClass, Size paramSize) {
    if (isOutputSupportedFor(paramClass))
      return getInternalFormatDuration(34, 0, paramSize, 1); 
    throw new IllegalArgumentException("klass was not supported");
  }
  
  public int[] getValidOutputFormatsForInput(int paramInt) {
    ReprocessFormatsMap reprocessFormatsMap = this.mInputOutputFormatsMap;
    if (reprocessFormatsMap == null)
      return new int[0]; 
    int[] arrayOfInt = reprocessFormatsMap.getOutputs(paramInt);
    if (this.mHeicOutputFormats.size() > 0) {
      int[] arrayOfInt1 = Arrays.copyOf(arrayOfInt, arrayOfInt.length + 1);
      arrayOfInt1[arrayOfInt.length] = 1212500294;
      return arrayOfInt1;
    } 
    return arrayOfInt;
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCodeGeneric((Object[])new Object[][] { 
          (Object[])this.mConfigurations, (Object[])this.mMinFrameDurations, (Object[])this.mStallDurations, (Object[])this.mDepthConfigurations, (Object[])this.mDepthMinFrameDurations, (Object[])this.mDepthStallDurations, (Object[])this.mDynamicDepthConfigurations, (Object[])this.mDynamicDepthMinFrameDurations, (Object[])this.mDynamicDepthStallDurations, (Object[])this.mHeicConfigurations, 
          (Object[])this.mHeicMinFrameDurations, (Object[])this.mHeicStallDurations, (Object[])this.mHighSpeedVideoConfigurations });
  }
  
  public boolean isOutputSupportedFor(int paramInt) {
    checkArgumentFormat(paramInt);
    int i = imageFormatToInternal(paramInt);
    paramInt = imageFormatToDataspace(paramInt);
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    if (paramInt == 4096) {
      if (this.mDepthOutputFormats.indexOfKey(i) >= 0)
        bool4 = true; 
      return bool4;
    } 
    if (paramInt == 4098) {
      bool4 = bool1;
      if (this.mDynamicDepthOutputFormats.indexOfKey(i) >= 0)
        bool4 = true; 
      return bool4;
    } 
    if (paramInt == 4099) {
      bool4 = bool2;
      if (this.mHeicOutputFormats.indexOfKey(i) >= 0)
        bool4 = true; 
      return bool4;
    } 
    bool4 = bool3;
    if (getFormatsMap(true).indexOfKey(i) >= 0)
      bool4 = true; 
    return bool4;
  }
  
  public boolean isOutputSupportedFor(Size paramSize, int paramInt) {
    StreamConfiguration[] arrayOfStreamConfiguration;
    int i = imageFormatToInternal(paramInt);
    paramInt = imageFormatToDataspace(paramInt);
    if (paramInt == 4096) {
      arrayOfStreamConfiguration = this.mDepthConfigurations;
    } else if (paramInt == 4098) {
      arrayOfStreamConfiguration = this.mDynamicDepthConfigurations;
    } else if (paramInt == 4099) {
      arrayOfStreamConfiguration = this.mHeicConfigurations;
    } else {
      arrayOfStreamConfiguration = this.mConfigurations;
    } 
    int j = arrayOfStreamConfiguration.length;
    for (paramInt = 0; paramInt < j; paramInt++) {
      StreamConfiguration streamConfiguration = arrayOfStreamConfiguration[paramInt];
      if (streamConfiguration.getFormat() == i && streamConfiguration.isOutput() && streamConfiguration.getSize().equals(paramSize))
        return true; 
    } 
    return false;
  }
  
  public boolean isOutputSupportedFor(Surface paramSurface) {
    StreamConfiguration[] arrayOfStreamConfiguration;
    Objects.requireNonNull(paramSurface, "surface must not be null");
    Size size = SurfaceUtils.getSurfaceSize(paramSurface);
    int i = SurfaceUtils.getSurfaceFormat(paramSurface);
    int j = SurfaceUtils.getSurfaceDataspace(paramSurface);
    boolean bool = SurfaceUtils.isFlexibleConsumer(paramSurface);
    if (j == 4096) {
      arrayOfStreamConfiguration = this.mDepthConfigurations;
    } else if (j == 4098) {
      arrayOfStreamConfiguration = this.mDynamicDepthConfigurations;
    } else if (j == 4099) {
      arrayOfStreamConfiguration = this.mHeicConfigurations;
    } else {
      arrayOfStreamConfiguration = this.mConfigurations;
    } 
    int k = arrayOfStreamConfiguration.length;
    for (j = 0; j < k; j++) {
      StreamConfiguration streamConfiguration = arrayOfStreamConfiguration[j];
      if (streamConfiguration.getFormat() == i && streamConfiguration.isOutput()) {
        if (streamConfiguration.getSize().equals(size))
          return true; 
        if (bool && streamConfiguration.getSize().getWidth() <= 1920)
          return true; 
      } 
    } 
    return false;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("StreamConfiguration(");
    appendOutputsString(stringBuilder);
    stringBuilder.append(", ");
    appendHighResOutputsString(stringBuilder);
    stringBuilder.append(", ");
    appendInputsString(stringBuilder);
    stringBuilder.append(", ");
    appendValidOutputFormatsForInputString(stringBuilder);
    stringBuilder.append(", ");
    appendHighSpeedVideoConfigurationsString(stringBuilder);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/StreamConfigurationMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
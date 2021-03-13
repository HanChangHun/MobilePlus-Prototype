package android.hardware.camera2.utils;

import android.hardware.camera2.legacy.LegacyCameraDevice;
import android.hardware.camera2.legacy.LegacyExceptionUtils;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SurfaceUtils {
  public static void checkConstrainedHighSpeedSurfaces(Collection<Surface> paramCollection, Range<Integer> paramRange, StreamConfigurationMap paramStreamConfigurationMap) {
    if (paramCollection != null && paramCollection.size() != 0 && paramCollection.size() <= 2) {
      StringBuilder stringBuilder;
      List<Size> list;
      if (paramRange == null) {
        list = Arrays.asList(paramStreamConfigurationMap.getHighSpeedVideoSizes());
      } else {
        Range[] arrayOfRange = paramStreamConfigurationMap.getHighSpeedVideoFpsRanges();
        if (Arrays.<Range>asList(arrayOfRange).contains(list)) {
          list = Arrays.asList(paramStreamConfigurationMap.getHighSpeedVideoSizesFor((Range)list));
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Fps range ");
          stringBuilder.append(list.toString());
          stringBuilder.append(" in the request is not a supported high speed fps range ");
          stringBuilder.append(Arrays.toString((Object[])arrayOfRange));
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } 
      for (Surface surface : stringBuilder) {
        checkHighSpeedSurfaceFormat(surface);
        Size size = getSurfaceSize(surface);
        if (list.contains(size)) {
          if (isSurfaceForPreview(surface) || isSurfaceForHwVideoEncoder(surface)) {
            if (!isSurfaceForPreview(surface) || !isSurfaceForHwVideoEncoder(surface))
              continue; 
            throw new IllegalArgumentException("This output surface can not be both preview and hardware video encoding surface");
          } 
          throw new IllegalArgumentException("This output surface is neither preview nor hardware video encoding surface");
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Surface size ");
        stringBuilder.append(size.toString());
        stringBuilder.append(" is not part of the high speed supported size list ");
        stringBuilder.append(Arrays.toString(list.toArray()));
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      if (stringBuilder.size() == 2) {
        Iterator<Surface> iterator = stringBuilder.iterator();
        boolean bool = isSurfaceForPreview(iterator.next());
        if (bool == isSurfaceForPreview(iterator.next()))
          throw new IllegalArgumentException("The 2 output surfaces must have different type"); 
      } 
      return;
    } 
    throw new IllegalArgumentException("Output target surface list must not be null and the size must be 1 or 2");
  }
  
  private static void checkHighSpeedSurfaceFormat(Surface paramSurface) {
    int i = getSurfaceFormat(paramSurface);
    if (i == 34)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Surface format(");
    stringBuilder.append(i);
    stringBuilder.append(") is not for preview or hardware video encoding!");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static int getSurfaceDataspace(Surface paramSurface) {
    try {
      return LegacyCameraDevice.detectSurfaceDataspace(paramSurface);
    } catch (android.hardware.camera2.legacy.LegacyExceptionUtils.BufferQueueAbandonedException bufferQueueAbandonedException) {
      throw new IllegalArgumentException("Surface was abandoned", bufferQueueAbandonedException);
    } 
  }
  
  public static int getSurfaceFormat(Surface paramSurface) {
    try {
      return LegacyCameraDevice.detectSurfaceType(paramSurface);
    } catch (android.hardware.camera2.legacy.LegacyExceptionUtils.BufferQueueAbandonedException bufferQueueAbandonedException) {
      throw new IllegalArgumentException("Surface was abandoned", bufferQueueAbandonedException);
    } 
  }
  
  public static long getSurfaceId(Surface paramSurface) {
    try {
      return LegacyCameraDevice.getSurfaceId(paramSurface);
    } catch (android.hardware.camera2.legacy.LegacyExceptionUtils.BufferQueueAbandonedException bufferQueueAbandonedException) {
      return 0L;
    } 
  }
  
  public static Size getSurfaceSize(Surface paramSurface) {
    try {
      return LegacyCameraDevice.getSurfaceSize(paramSurface);
    } catch (android.hardware.camera2.legacy.LegacyExceptionUtils.BufferQueueAbandonedException bufferQueueAbandonedException) {
      throw new IllegalArgumentException("Surface was abandoned", bufferQueueAbandonedException);
    } 
  }
  
  public static boolean isFlexibleConsumer(Surface paramSurface) {
    return LegacyCameraDevice.isFlexibleConsumer(paramSurface);
  }
  
  public static boolean isSurfaceForHwVideoEncoder(Surface paramSurface) {
    return LegacyCameraDevice.isVideoEncoderConsumer(paramSurface);
  }
  
  public static boolean isSurfaceForPreview(Surface paramSurface) {
    return LegacyCameraDevice.isPreviewConsumer(paramSurface);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/SurfaceUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.hardware.camera2.params;

import android.util.ArraySet;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public final class RecommendedStreamConfigurationMap {
  public static final int MAX_USECASE_COUNT = 32;
  
  private static final String TAG = "RecommendedStreamConfigurationMap";
  
  public static final int USECASE_LOW_LATENCY_SNAPSHOT = 6;
  
  public static final int USECASE_PREVIEW = 0;
  
  public static final int USECASE_RAW = 5;
  
  public static final int USECASE_RECORD = 1;
  
  public static final int USECASE_SNAPSHOT = 3;
  
  public static final int USECASE_VENDOR_START = 24;
  
  public static final int USECASE_VIDEO_SNAPSHOT = 2;
  
  public static final int USECASE_ZSL = 4;
  
  private StreamConfigurationMap mRecommendedMap;
  
  private boolean mSupportsPrivate;
  
  private int mUsecase;
  
  public RecommendedStreamConfigurationMap(StreamConfigurationMap paramStreamConfigurationMap, int paramInt, boolean paramBoolean) {
    this.mRecommendedMap = paramStreamConfigurationMap;
    this.mUsecase = paramInt;
    this.mSupportsPrivate = paramBoolean;
  }
  
  private Set<Integer> getUnmodifiableIntegerSet(int[] paramArrayOfint) {
    if (paramArrayOfint != null && paramArrayOfint.length > 0) {
      ArraySet arraySet = new ArraySet();
      arraySet.ensureCapacity(paramArrayOfint.length);
      int i = paramArrayOfint.length;
      for (byte b = 0; b < i; b++)
        arraySet.add(Integer.valueOf(paramArrayOfint[b])); 
      return Collections.unmodifiableSet((Set<? extends Integer>)arraySet);
    } 
    return null;
  }
  
  private Set<Range<Integer>> getUnmodifiableRangeSet(Range<Integer>[] paramArrayOfRange) {
    if (paramArrayOfRange != null && paramArrayOfRange.length > 0) {
      ArraySet arraySet = new ArraySet();
      arraySet.addAll(Arrays.asList(paramArrayOfRange));
      return Collections.unmodifiableSet((Set<? extends Range<Integer>>)arraySet);
    } 
    return null;
  }
  
  private Set<Size> getUnmodifiableSizeSet(Size[] paramArrayOfSize) {
    if (paramArrayOfSize != null && paramArrayOfSize.length > 0) {
      ArraySet arraySet = new ArraySet();
      arraySet.addAll(Arrays.asList(paramArrayOfSize));
      return Collections.unmodifiableSet((Set<? extends Size>)arraySet);
    } 
    return null;
  }
  
  public Set<Size> getHighResolutionOutputSizes(int paramInt) {
    return getUnmodifiableSizeSet(this.mRecommendedMap.getHighResolutionOutputSizes(paramInt));
  }
  
  public Set<Range<Integer>> getHighSpeedVideoFpsRanges() {
    return getUnmodifiableRangeSet(this.mRecommendedMap.getHighSpeedVideoFpsRanges());
  }
  
  public Set<Range<Integer>> getHighSpeedVideoFpsRangesFor(Size paramSize) {
    return getUnmodifiableRangeSet(this.mRecommendedMap.getHighSpeedVideoFpsRangesFor(paramSize));
  }
  
  public Set<Size> getHighSpeedVideoSizes() {
    return getUnmodifiableSizeSet(this.mRecommendedMap.getHighSpeedVideoSizes());
  }
  
  public Set<Size> getHighSpeedVideoSizesFor(Range<Integer> paramRange) {
    return getUnmodifiableSizeSet(this.mRecommendedMap.getHighSpeedVideoSizesFor(paramRange));
  }
  
  public Set<Integer> getInputFormats() {
    return getUnmodifiableIntegerSet(this.mRecommendedMap.getInputFormats());
  }
  
  public Set<Size> getInputSizes(int paramInt) {
    return getUnmodifiableSizeSet(this.mRecommendedMap.getInputSizes(paramInt));
  }
  
  public Set<Integer> getOutputFormats() {
    return getUnmodifiableIntegerSet(this.mRecommendedMap.getOutputFormats());
  }
  
  public long getOutputMinFrameDuration(int paramInt, Size paramSize) {
    return this.mRecommendedMap.getOutputMinFrameDuration(paramInt, paramSize);
  }
  
  public <T> long getOutputMinFrameDuration(Class<T> paramClass, Size paramSize) {
    return this.mSupportsPrivate ? this.mRecommendedMap.getOutputMinFrameDuration(paramClass, paramSize) : 0L;
  }
  
  public Set<Size> getOutputSizes(int paramInt) {
    return getUnmodifiableSizeSet(this.mRecommendedMap.getOutputSizes(paramInt));
  }
  
  public <T> Set<Size> getOutputSizes(Class<T> paramClass) {
    return this.mSupportsPrivate ? getUnmodifiableSizeSet(this.mRecommendedMap.getOutputSizes(paramClass)) : null;
  }
  
  public long getOutputStallDuration(int paramInt, Size paramSize) {
    return this.mRecommendedMap.getOutputStallDuration(paramInt, paramSize);
  }
  
  public <T> long getOutputStallDuration(Class<T> paramClass, Size paramSize) {
    return this.mSupportsPrivate ? this.mRecommendedMap.getOutputStallDuration(paramClass, paramSize) : 0L;
  }
  
  public int getRecommendedUseCase() {
    return this.mUsecase;
  }
  
  public Set<Integer> getValidOutputFormatsForInput(int paramInt) {
    return getUnmodifiableIntegerSet(this.mRecommendedMap.getValidOutputFormatsForInput(paramInt));
  }
  
  public boolean isOutputSupportedFor(int paramInt) {
    return this.mRecommendedMap.isOutputSupportedFor(paramInt);
  }
  
  public boolean isOutputSupportedFor(Surface paramSurface) {
    return this.mRecommendedMap.isOutputSupportedFor(paramSurface);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RecommendedUsecase {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/RecommendedStreamConfigurationMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
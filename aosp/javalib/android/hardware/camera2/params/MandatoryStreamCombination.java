package android.hardware.camera2.params;

import android.hardware.camera2.CameraManager;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class MandatoryStreamCombination {
  private static final String TAG = "MandatoryStreamCombination";
  
  private static StreamCombinationTemplate[] sBurstCombinations;
  
  private static StreamCombinationTemplate[] sConcurrentDepthOnlyStreamCombinations;
  
  private static StreamCombinationTemplate[] sConcurrentStreamCombinations;
  
  private static StreamCombinationTemplate[] sFullCombinations;
  
  private static StreamCombinationTemplate[] sFullPrivateReprocCombinations;
  
  private static StreamCombinationTemplate[] sFullYUVReprocCombinations;
  
  private static StreamCombinationTemplate[] sLegacyCombinations = new StreamCombinationTemplate[] { new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.MAXIMUM) }"Simple preview, GPU video processing, or no-preview video recording"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(256, SizeThreshold.MAXIMUM) }"No-viewfinder still image capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.MAXIMUM) }"In-application video/image processing"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM) }"Standard still imaging"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM) }"In-app processing plus still capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.PREVIEW) }"Standard recording"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW) }"Preview plus in-app processing"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM) }"Still capture plus in-app processing") };
  
  private static StreamCombinationTemplate[] sLevel3Combinations;
  
  private static StreamCombinationTemplate[] sLevel3PrivateReprocCombinations;
  
  private static StreamCombinationTemplate[] sLevel3YUVReprocCombinations;
  
  private static StreamCombinationTemplate[] sLimitedCombinations = new StreamCombinationTemplate[] { new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.RECORD) }"High-resolution video recording with preview"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD) }"High-resolution in-app video processing with preview"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD) }"Two-input in-app video processing"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.RECORD), new StreamTemplate(256, SizeThreshold.RECORD) }"High-resolution recording with video snapshot"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.RECORD), new StreamTemplate(256, SizeThreshold.RECORD) }"High-resolution in-app processing with video snapshot"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM) }"Two-input in-app processing with still capture") };
  
  private static StreamCombinationTemplate[] sLimitedPrivateReprocCombinations;
  
  private static StreamCombinationTemplate[] sLimitedYUVReprocCombinations;
  
  private static StreamCombinationTemplate[] sRAWPrivateReprocCombinations;
  
  private static StreamCombinationTemplate[] sRAWYUVReprocCombinations;
  
  private static StreamCombinationTemplate[] sRawCombinations;
  
  private final String mDescription;
  
  private final boolean mIsReprocessable;
  
  private final ArrayList<MandatoryStreamInformation> mStreamsInformation = new ArrayList<>();
  
  static {
    sBurstCombinations = new StreamCombinationTemplate[] { new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.MAXIMUM) }"Maximum-resolution GPU processing with preview"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM) }"Maximum-resolution in-app processing with preview"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM) }"Maximum-resolution two-input in-app processsing") };
    sFullCombinations = new StreamCombinationTemplate[] { new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.MAXIMUM), new StreamTemplate(34, SizeThreshold.MAXIMUM) }"Maximum-resolution GPU processing with preview"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.MAXIMUM), new StreamTemplate(35, SizeThreshold.MAXIMUM) }"Maximum-resolution in-app processing with preview"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.MAXIMUM), new StreamTemplate(35, SizeThreshold.MAXIMUM) }"Maximum-resolution two-input in-app processsing"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM) }"Video recording with maximum-size video snapshot"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.VGA), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM) }"Standard video recording plus maximum-resolution in-app processing"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.VGA), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.MAXIMUM) }"Preview plus two-input maximum-resolution in-app processing") };
    sRawCombinations = new StreamCombinationTemplate[] { new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(32, SizeThreshold.MAXIMUM) }"No-preview DNG capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM) }"Standard DNG capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM) }"In-app processing plus DNG capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM) }"Video recording with DNG capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM) }"Preview with in-app processing and DNG capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(32, SizeThreshold.MAXIMUM) }"Two-input in-app processing plus DNG capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM) }"Still capture with simultaneous JPEG and DNG"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.PREVIEW), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM) }"In-app processing with simultaneous JPEG and DNG") };
    sLevel3Combinations = new StreamCombinationTemplate[] { new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(35, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM) }"In-app viewfinder analysis with dynamic selection of output format"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.PREVIEW), new StreamTemplate(34, SizeThreshold.VGA), new StreamTemplate(256, SizeThreshold.MAXIMUM), new StreamTemplate(32, SizeThreshold.MAXIMUM) }"In-app viewfinder analysis with dynamic selection of output format") };
    StreamTemplate streamTemplate5 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType7 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate6 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate5 }, "No-viewfinder still image reprocessing", reprocessType7);
    StreamTemplate streamTemplate13 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate19 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType16 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate12 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate13, streamTemplate19 }, "ZSL(Zero-Shutter-Lag) still imaging", reprocessType16);
    StreamTemplate streamTemplate29 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate19 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    reprocessType16 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate17 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate29, streamTemplate19 }, "ZSL still and in-app processing imaging", reprocessType16);
    streamTemplate29 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate32 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate25 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType23 = ReprocessType.PRIVATE;
    sLimitedPrivateReprocCombinations = new StreamCombinationTemplate[] { streamCombinationTemplate6, streamCombinationTemplate12, streamCombinationTemplate17, new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate29, streamTemplate32, streamTemplate25 }, "ZSL in-app processing with still capture", reprocessType23) };
    StreamTemplate streamTemplate12 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType2 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate5 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate12 }, "No-viewfinder still image reprocessing", reprocessType2);
    streamTemplate25 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    streamTemplate12 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType11 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate11 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate25, streamTemplate12 }, "ZSL(Zero-Shutter-Lag) still imaging", reprocessType11);
    streamTemplate29 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate25 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    reprocessType11 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate16 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate29, streamTemplate25 }, "ZSL still and in-app processing imaging", reprocessType11);
    StreamTemplate streamTemplate34 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate32 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate25 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType19 = ReprocessType.YUV;
    sLimitedYUVReprocCombinations = new StreamCombinationTemplate[] { streamCombinationTemplate5, streamCombinationTemplate11, streamCombinationTemplate16, new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate34, streamTemplate32, streamTemplate25 }, "ZSL in-app processing with still capture", reprocessType19) };
    StreamTemplate streamTemplate11 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate4 = new StreamTemplate(35, SizeThreshold.RECORD);
    ReprocessType reprocessType15 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate4 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate11, streamTemplate4 }, "High-resolution ZSL in-app video processing with regular preview", reprocessType15);
    StreamTemplate streamTemplate24 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate18 = new StreamTemplate(35, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType6 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate10 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate24, streamTemplate18 }, "Maximum-resolution ZSL in-app processing with regular preview", reprocessType6);
    streamTemplate18 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate24 = new StreamTemplate(35, SizeThreshold.MAXIMUM);
    reprocessType19 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate20 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate18, streamTemplate24 }, "Maximum-resolution two-input ZSL in-app processing", reprocessType19);
    streamTemplate18 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    streamTemplate24 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate34 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    reprocessType19 = ReprocessType.PRIVATE;
    sFullPrivateReprocCombinations = new StreamCombinationTemplate[] { streamCombinationTemplate4, streamCombinationTemplate10, streamCombinationTemplate20, new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate18, streamTemplate24, streamTemplate34 }, "ZSL still capture and in-app processing", reprocessType19) };
    StreamTemplate streamTemplate3 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    ReprocessType reprocessType5 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate3 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate3 }, "Maximum-resolution multi-frame image fusion in-app processing with regular preview", reprocessType5);
    StreamTemplate streamTemplate10 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    ReprocessType reprocessType14 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate9 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate10 }, "Maximum-resolution multi-frame image fusion two-input in-app processing", reprocessType14);
    StreamTemplate streamTemplate28 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    streamTemplate18 = new StreamTemplate(35, SizeThreshold.RECORD);
    reprocessType14 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate15 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate28, streamTemplate18 }, "High-resolution ZSL in-app video processing with regular preview", reprocessType14);
    StreamTemplate streamTemplate31 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    streamTemplate28 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate23 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType22 = ReprocessType.YUV;
    sFullYUVReprocCombinations = new StreamCombinationTemplate[] { streamCombinationTemplate3, streamCombinationTemplate9, streamCombinationTemplate15, new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate31, streamTemplate28, streamTemplate23 }, "ZSL still capture and in-app processing", reprocessType22) };
    StreamTemplate streamTemplate2 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate9 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType13 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate2 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate2, streamTemplate9 }, "Mutually exclusive ZSL in-app processing and DNG capture", reprocessType13);
    streamTemplate28 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate22 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate17 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType4 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate8 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate28, streamTemplate22, streamTemplate17 }, "Mutually exclusive ZSL in-app processing and preview with DNG capture", reprocessType4);
    streamTemplate28 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate31 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate22 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType10 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate19 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate28, streamTemplate31, streamTemplate22 }, "Mutually exclusive ZSL two-input in-app processing and DNG capture", reprocessType10);
    StreamTemplate streamTemplate16 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    streamTemplate31 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    StreamTemplate streamTemplate33 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType18 = ReprocessType.PRIVATE;
    StreamCombinationTemplate streamCombinationTemplate14 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate16, streamTemplate31, streamTemplate33 }, "Mutually exclusive ZSL still capture and preview with DNG capture", reprocessType18);
    StreamTemplate streamTemplate27 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate33 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    StreamTemplate streamTemplate35 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType21 = ReprocessType.PRIVATE;
    sRAWPrivateReprocCombinations = new StreamCombinationTemplate[] { streamCombinationTemplate2, streamCombinationTemplate8, streamCombinationTemplate19, streamCombinationTemplate14, new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate27, streamTemplate33, streamTemplate35 }, "Mutually exclusive ZSL in-app processing with still capture and DNG capture", reprocessType21) };
    StreamTemplate streamTemplate8 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate21 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType1 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate1 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate8, streamTemplate21 }, "Mutually exclusive ZSL in-app processing and DNG capture", reprocessType1);
    streamTemplate27 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    streamTemplate8 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate21 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType9 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate7 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate27, streamTemplate8, streamTemplate21 }, "Mutually exclusive ZSL in-app processing and preview with DNG capture", reprocessType9);
    streamTemplate21 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate27 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate15 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    reprocessType21 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate18 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate21, streamTemplate27, streamTemplate15 }, "Mutually exclusive ZSL two-input in-app processing and DNG capture", reprocessType21);
    streamTemplate27 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    streamTemplate33 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    StreamTemplate streamTemplate30 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType8 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate21 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate27, streamTemplate33, streamTemplate30 }, "Mutually exclusive ZSL still capture and preview with DNG capture", reprocessType8);
    streamTemplate35 = new StreamTemplate(35, SizeThreshold.PREVIEW);
    streamTemplate30 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    StreamTemplate streamTemplate14 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType17 = ReprocessType.YUV;
    sRAWYUVReprocCombinations = new StreamCombinationTemplate[] { streamCombinationTemplate1, streamCombinationTemplate7, streamCombinationTemplate18, streamCombinationTemplate21, new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate35, streamTemplate30, streamTemplate14 }, "Mutually exclusive ZSL in-app processing with still capture and DNG capture", reprocessType17) };
    StreamTemplate streamTemplate26 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    streamTemplate14 = new StreamTemplate(34, SizeThreshold.VGA);
    StreamTemplate streamTemplate7 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    StreamTemplate streamTemplate1 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType12 = ReprocessType.PRIVATE;
    sLevel3PrivateReprocCombinations = new StreamCombinationTemplate[] { new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate26, streamTemplate14, streamTemplate7, streamTemplate1 }, "In-app viewfinder analysis with ZSL, RAW, and JPEG reprocessing output", reprocessType12) };
    streamTemplate1 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    StreamTemplate streamTemplate20 = new StreamTemplate(34, SizeThreshold.VGA);
    streamTemplate14 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType3 = ReprocessType.YUV;
    StreamCombinationTemplate streamCombinationTemplate13 = new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate1, streamTemplate20, streamTemplate14 }, "In-app viewfinder analysis with ZSL and RAW", reprocessType3);
    StreamTemplate streamTemplate6 = new StreamTemplate(34, SizeThreshold.PREVIEW);
    streamTemplate20 = new StreamTemplate(34, SizeThreshold.VGA);
    streamTemplate26 = new StreamTemplate(32, SizeThreshold.MAXIMUM);
    streamTemplate1 = new StreamTemplate(256, SizeThreshold.MAXIMUM);
    ReprocessType reprocessType20 = ReprocessType.YUV;
    sLevel3YUVReprocCombinations = new StreamCombinationTemplate[] { streamCombinationTemplate13, new StreamCombinationTemplate(new StreamTemplate[] { streamTemplate6, streamTemplate20, streamTemplate26, streamTemplate1 }, "In-app viewfinder analysis with ZSL, RAW, and JPEG reprocessing output", reprocessType20) };
    sConcurrentStreamCombinations = new StreamCombinationTemplate[] { new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.s1440p) }"In-app video / image processing"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.s1440p) }"preview / preview to GPU"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(256, SizeThreshold.s1440p) }"No view-finder still image capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.s720p), new StreamTemplate(35, SizeThreshold.s1440p) }"Two-input in app video / image processing"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.s720p), new StreamTemplate(34, SizeThreshold.s1440p) }"High resolution video recording with preview"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.s720p), new StreamTemplate(35, SizeThreshold.s1440p) }"In-app video / image processing with preview"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.s720p), new StreamTemplate(34, SizeThreshold.s1440p) }"In-app video / image processing with preview"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(34, SizeThreshold.s720p), new StreamTemplate(256, SizeThreshold.s1440p) }"Standard stil image capture"), new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(35, SizeThreshold.s720p), new StreamTemplate(256, SizeThreshold.s1440p) }"Standard still image capture") };
    sConcurrentDepthOnlyStreamCombinations = new StreamCombinationTemplate[] { new StreamCombinationTemplate(new StreamTemplate[] { new StreamTemplate(1144402265, SizeThreshold.VGA) }"Depth capture for mesh based object rendering") };
  }
  
  public MandatoryStreamCombination(List<MandatoryStreamInformation> paramList, String paramString, boolean paramBoolean) {
    if (!paramList.isEmpty()) {
      this.mStreamsInformation.addAll(paramList);
      this.mDescription = paramString;
      this.mIsReprocessable = paramBoolean;
      return;
    } 
    throw new IllegalArgumentException("Empty stream information");
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof MandatoryStreamCombination) {
      paramObject = paramObject;
      return (this.mDescription != ((MandatoryStreamCombination)paramObject).mDescription || this.mIsReprocessable != ((MandatoryStreamCombination)paramObject).mIsReprocessable || this.mStreamsInformation.size() != ((MandatoryStreamCombination)paramObject).mStreamsInformation.size()) ? false : this.mStreamsInformation.equals(((MandatoryStreamCombination)paramObject).mStreamsInformation);
    } 
    return false;
  }
  
  public CharSequence getDescription() {
    return this.mDescription;
  }
  
  public List<MandatoryStreamInformation> getStreamsInformation() {
    return Collections.unmodifiableList(this.mStreamsInformation);
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCode(new int[] { Boolean.hashCode(this.mIsReprocessable), this.mDescription.hashCode(), this.mStreamsInformation.hashCode() });
  }
  
  public boolean isReprocessable() {
    return this.mIsReprocessable;
  }
  
  public static final class Builder {
    private final Size kPreviewSizeBound = new Size(1920, 1088);
    
    private int mCameraId;
    
    private List<Integer> mCapabilities;
    
    private Size mDisplaySize;
    
    private int mHwLevel;
    
    private boolean mIsHiddenPhysicalCamera;
    
    private StreamConfigurationMap mStreamConfigMap;
    
    public Builder(int param1Int1, int param1Int2, Size param1Size, List<Integer> param1List, StreamConfigurationMap param1StreamConfigurationMap) {
      this.mCameraId = param1Int1;
      this.mDisplaySize = param1Size;
      this.mCapabilities = param1List;
      this.mStreamConfigMap = param1StreamConfigurationMap;
      this.mHwLevel = param1Int2;
      this.mIsHiddenPhysicalCamera = CameraManager.isHiddenPhysicalCamera(Integer.toString(param1Int1));
    }
    
    private static int compareSizes(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      long l1 = param1Int1 * param1Int2;
      long l2 = param1Int3 * param1Int4;
      long l3 = l1;
      long l4 = l2;
      if (l1 == l2) {
        l3 = param1Int1;
        l4 = param1Int3;
      } 
      if (l3 < l4) {
        param1Int1 = -1;
      } else if (l3 > l4) {
        param1Int1 = 1;
      } else {
        param1Int1 = 0;
      } 
      return param1Int1;
    }
    
    private HashMap<Pair<MandatoryStreamCombination.SizeThreshold, Integer>, List<Size>> enumerateAvailableSizes() {
      Size size2;
      int[] arrayOfInt = new int[3];
      arrayOfInt[0] = 34;
      arrayOfInt[1] = 35;
      arrayOfInt[2] = 256;
      boolean bool = false;
      new Size(0, 0);
      new Size(0, 0);
      Size size1 = new Size(640, 480);
      if (isExternalCamera() || this.mIsHiddenPhysicalCamera) {
        size2 = getMaxCameraRecordingSize();
      } else {
        size2 = getMaxRecordingSize();
      } 
      if (size2 == null) {
        Log.e("MandatoryStreamCombination", "Failed to find maximum recording size!");
        return null;
      } 
      HashMap<Object, Object> hashMap1 = new HashMap<>();
      int i = arrayOfInt.length;
      byte b;
      for (b = 0; b < i; b++) {
        int j = arrayOfInt[b];
        hashMap1.put(new Integer(j), this.mStreamConfigMap.getOutputSizes(j));
      } 
      List<Size> list = getSizesWithinBound((Size[])hashMap1.get(new Integer(34)), this.kPreviewSizeBound);
      if (list == null || list.isEmpty()) {
        Log.e("MandatoryStreamCombination", "No preview sizes within preview size bound!");
        return null;
      } 
      Size size3 = getMaxPreviewSize(getAscendingOrderSizes(list, false));
      HashMap<Object, Object> hashMap2 = new HashMap<>();
      i = arrayOfInt.length;
      for (b = bool; b < i; b++) {
        Integer integer = new Integer(arrayOfInt[b]);
        Size[] arrayOfSize = (Size[])hashMap1.get(integer);
        hashMap2.put(new Pair(MandatoryStreamCombination.SizeThreshold.VGA, integer), getSizesWithinBound(arrayOfSize, size1));
        hashMap2.put(new Pair(MandatoryStreamCombination.SizeThreshold.PREVIEW, integer), getSizesWithinBound(arrayOfSize, size3));
        hashMap2.put(new Pair(MandatoryStreamCombination.SizeThreshold.RECORD, integer), getSizesWithinBound(arrayOfSize, size2));
        hashMap2.put(new Pair(MandatoryStreamCombination.SizeThreshold.MAXIMUM, integer), Arrays.asList(arrayOfSize));
      } 
      return (HashMap)hashMap2;
    }
    
    private List<MandatoryStreamCombination> generateAvailableCombinations(ArrayList<MandatoryStreamCombination.StreamCombinationTemplate> param1ArrayList) {
      if (param1ArrayList.isEmpty()) {
        Log.e("MandatoryStreamCombination", "No available stream templates!");
        return null;
      } 
      HashMap<Pair<MandatoryStreamCombination.SizeThreshold, Integer>, List<Size>> hashMap = enumerateAvailableSizes();
      if (hashMap == null) {
        Log.e("MandatoryStreamCombination", "Available size enumeration failed!");
        return null;
      } 
      Size[] arrayOfSize = this.mStreamConfigMap.getOutputSizes(32);
      ArrayList arrayList = new ArrayList();
      if (arrayOfSize != null) {
        arrayList.ensureCapacity(arrayOfSize.length);
        arrayList.addAll(Arrays.asList(arrayOfSize));
      } 
      Size size1 = new Size(0, 0);
      if (isPrivateReprocessingSupported())
        size1 = getMaxSize(this.mStreamConfigMap.getInputSizes(34)); 
      Size size2 = new Size(0, 0);
      if (isYUVReprocessingSupported())
        size2 = getMaxSize(this.mStreamConfigMap.getInputSizes(35)); 
      ArrayList<MandatoryStreamCombination> arrayList1 = new ArrayList();
      arrayList1.ensureCapacity(param1ArrayList.size());
      Iterator<MandatoryStreamCombination.StreamCombinationTemplate> iterator = param1ArrayList.iterator();
      while (iterator.hasNext()) {
        boolean bool;
        MandatoryStreamCombination.StreamCombinationTemplate streamCombinationTemplate = iterator.next();
        ArrayList<MandatoryStreamCombination.MandatoryStreamInformation> arrayList2 = new ArrayList();
        arrayList2.ensureCapacity(streamCombinationTemplate.mStreamTemplates.length);
        if (streamCombinationTemplate.mReprocessType != MandatoryStreamCombination.ReprocessType.NONE) {
          bool = true;
        } else {
          bool = false;
        } 
        if (bool) {
          byte b1;
          ArrayList<Size> arrayList3 = new ArrayList();
          if (streamCombinationTemplate.mReprocessType == MandatoryStreamCombination.ReprocessType.PRIVATE) {
            arrayList3.add(size1);
            b1 = 34;
          } else {
            arrayList3.add(size2);
            b1 = 35;
          } 
          arrayList2.add(new MandatoryStreamCombination.MandatoryStreamInformation(arrayList3, b1, true));
          arrayList2.add(new MandatoryStreamCombination.MandatoryStreamInformation(arrayList3, b1));
        } 
        MandatoryStreamCombination.StreamTemplate[] arrayOfStreamTemplate = streamCombinationTemplate.mStreamTemplates;
        int i = arrayOfStreamTemplate.length;
        byte b = 0;
        while (b < i) {
          List<Size> list;
          MandatoryStreamCombination.StreamTemplate streamTemplate = arrayOfStreamTemplate[b];
          if (streamTemplate.mFormat == 32) {
            list = arrayList;
          } else {
            list = hashMap.get(new Pair(streamTemplate.mSizeThreshold, new Integer(streamTemplate.mFormat)));
          } 
          try {
            MandatoryStreamCombination.MandatoryStreamInformation mandatoryStreamInformation = new MandatoryStreamCombination.MandatoryStreamInformation(list, streamTemplate.mFormat);
            arrayList2.add(mandatoryStreamInformation);
            b++;
          } catch (IllegalArgumentException illegalArgumentException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("No available sizes found for format: ");
            stringBuilder.append(streamTemplate.mFormat);
            stringBuilder.append(" size threshold: ");
            stringBuilder.append(streamTemplate.mSizeThreshold);
            stringBuilder.append(" combination: ");
            stringBuilder.append(streamCombinationTemplate.mDescription);
            Log.e("MandatoryStreamCombination", stringBuilder.toString());
            return null;
          } 
        } 
        try {
          MandatoryStreamCombination mandatoryStreamCombination = new MandatoryStreamCombination(arrayList2, streamCombinationTemplate.mDescription, bool);
          arrayList1.add(mandatoryStreamCombination);
        } catch (IllegalArgumentException illegalArgumentException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No stream information for mandatory combination: ");
          stringBuilder.append(streamCombinationTemplate.mDescription);
          Log.e("MandatoryStreamCombination", stringBuilder.toString());
          return null;
        } 
      } 
      return Collections.unmodifiableList(arrayList1);
    }
    
    private static List<Size> getAscendingOrderSizes(List<Size> param1List, boolean param1Boolean) {
      SizeComparator sizeComparator = new SizeComparator();
      ArrayList<Size> arrayList = new ArrayList();
      arrayList.addAll(param1List);
      Collections.sort(arrayList, sizeComparator);
      if (!param1Boolean)
        Collections.reverse(arrayList); 
      return arrayList;
    }
    
    private Size getMaxCameraRecordingSize() {
      StringBuilder stringBuilder1;
      Size size = new Size(1920, 1080);
      Size[] arrayOfSize = this.mStreamConfigMap.getOutputSizes(MediaRecorder.class);
      ArrayList<Size> arrayList = new ArrayList();
      int i = arrayOfSize.length;
      for (byte b = 0; b < i; b++) {
        Size size1 = arrayOfSize[b];
        if (size1.getWidth() <= size.getWidth() && size1.getHeight() <= size.getHeight())
          arrayList.add(size1); 
      } 
      for (Size size1 : getAscendingOrderSizes(arrayList, false)) {
        if (this.mStreamConfigMap.<MediaRecorder>getOutputMinFrameDuration(MediaRecorder.class, size1) > 3.3222591362126246E7D) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("External camera ");
          stringBuilder1.append(this.mCameraId);
          stringBuilder1.append(" has max video size:");
          stringBuilder1.append(size1);
          Log.i("MandatoryStreamCombination", stringBuilder1.toString());
          return size1;
        } 
      } 
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Camera ");
      stringBuilder2.append(this.mCameraId);
      stringBuilder2.append(" does not support any 30fps video output");
      Log.w("MandatoryStreamCombination", stringBuilder2.toString());
      return (Size)stringBuilder1;
    }
    
    private Size getMaxPreviewSize(List<Size> param1List) {
      if (param1List != null)
        for (Size size : param1List) {
          if (this.mDisplaySize.getWidth() >= size.getWidth() && this.mDisplaySize.getHeight() >= size.getHeight())
            return size; 
        }  
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Camera ");
      stringBuilder.append(this.mCameraId);
      stringBuilder.append(" maximum preview size search failed with display size ");
      stringBuilder.append(this.mDisplaySize);
      Log.w("MandatoryStreamCombination", stringBuilder.toString());
      return this.kPreviewSizeBound;
    }
    
    private Size getMaxRecordingSize() {
      int i = this.mCameraId;
      byte b = 8;
      if (!CamcorderProfile.hasProfile(i, 8))
        if (CamcorderProfile.hasProfile(this.mCameraId, 6)) {
          b = 6;
        } else if (CamcorderProfile.hasProfile(this.mCameraId, 5)) {
          b = 5;
        } else if (CamcorderProfile.hasProfile(this.mCameraId, 4)) {
          b = 4;
        } else if (CamcorderProfile.hasProfile(this.mCameraId, 7)) {
          b = 7;
        } else if (CamcorderProfile.hasProfile(this.mCameraId, 3)) {
          b = 3;
        } else if (CamcorderProfile.hasProfile(this.mCameraId, 2)) {
          b = 2;
        } else {
          b = -1;
        }  
      if (b < 0)
        return null; 
      CamcorderProfile camcorderProfile = CamcorderProfile.get(this.mCameraId, b);
      return new Size(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
    }
    
    public static Size getMaxSize(Size... param1VarArgs) {
      if (param1VarArgs != null && param1VarArgs.length != 0) {
        byte b = 0;
        Size size = param1VarArgs[0];
        int i = param1VarArgs.length;
        while (b < i) {
          Size size1 = param1VarArgs[b];
          Size size2 = size;
          if (size1.getWidth() * size1.getHeight() > size.getWidth() * size.getHeight())
            size2 = size1; 
          b++;
          size = size2;
        } 
        return size;
      } 
      throw new IllegalArgumentException("sizes was empty");
    }
    
    public static Size getMinSize(Size param1Size1, Size param1Size2) {
      if (param1Size1 != null && param1Size2 != null)
        return (param1Size1.getWidth() * param1Size1.getHeight() < param1Size2.getHeight() * param1Size2.getWidth()) ? param1Size1 : param1Size2; 
      throw new IllegalArgumentException("sizes was empty");
    }
    
    private static List<Size> getSizesWithinBound(Size[] param1ArrayOfSize, Size param1Size) {
      ArrayList<Size> arrayList = new ArrayList();
      int i = param1ArrayOfSize.length;
      for (byte b = 0; b < i; b++) {
        Size size = param1ArrayOfSize[b];
        if (size.getWidth() <= param1Size.getWidth() && size.getHeight() <= param1Size.getHeight())
          arrayList.add(size); 
      } 
      return arrayList;
    }
    
    private boolean isCapabilitySupported(int param1Int) {
      return this.mCapabilities.contains(Integer.valueOf(param1Int));
    }
    
    private boolean isColorOutputSupported() {
      return isCapabilitySupported(0);
    }
    
    private boolean isExternalCamera() {
      boolean bool;
      if (this.mHwLevel == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private boolean isHardwareLevelAtLeast(int param1Int) {
      int[] arrayOfInt = new int[5];
      arrayOfInt[0] = 2;
      arrayOfInt[1] = 4;
      arrayOfInt[2] = 0;
      arrayOfInt[3] = 1;
      arrayOfInt[4] = 3;
      if (param1Int == this.mHwLevel)
        return true; 
      int i = arrayOfInt.length;
      for (byte b = 0; b < i; b++) {
        int j = arrayOfInt[b];
        if (j == param1Int)
          return true; 
        if (j == this.mHwLevel)
          return false; 
      } 
      return false;
    }
    
    private boolean isHardwareLevelAtLeastFull() {
      return isHardwareLevelAtLeast(1);
    }
    
    private boolean isHardwareLevelAtLeastLegacy() {
      return isHardwareLevelAtLeast(2);
    }
    
    private boolean isHardwareLevelAtLeastLevel3() {
      return isHardwareLevelAtLeast(3);
    }
    
    private boolean isHardwareLevelAtLeastLimited() {
      return isHardwareLevelAtLeast(0);
    }
    
    private boolean isPrivateReprocessingSupported() {
      return isCapabilitySupported(4);
    }
    
    private boolean isYUVReprocessingSupported() {
      return isCapabilitySupported(7);
    }
    
    public List<MandatoryStreamCombination> getAvailableMandatoryConcurrentStreamCombinations() {
      MandatoryStreamCombination.StreamCombinationTemplate[] arrayOfStreamCombinationTemplate = MandatoryStreamCombination.sConcurrentStreamCombinations;
      if (!isColorOutputSupported()) {
        Log.v("MandatoryStreamCombination", "Device is not backward compatible, depth streams are mandatory!");
        arrayOfStreamCombinationTemplate = MandatoryStreamCombination.sConcurrentDepthOnlyStreamCombinations;
      } 
      Size size1 = new Size(640, 480);
      Size size2 = new Size(1280, 720);
      Size size3 = new Size(1920, 1440);
      ArrayList<MandatoryStreamCombination> arrayList = new ArrayList();
      arrayList.ensureCapacity(arrayOfStreamCombinationTemplate.length);
      int i = arrayOfStreamCombinationTemplate.length;
      byte b = 0;
      while (b < i) {
        MandatoryStreamCombination.StreamCombinationTemplate streamCombinationTemplate = arrayOfStreamCombinationTemplate[b];
        ArrayList<MandatoryStreamCombination.MandatoryStreamInformation> arrayList1 = new ArrayList();
        arrayList1.ensureCapacity(streamCombinationTemplate.mStreamTemplates.length);
        MandatoryStreamCombination.StreamTemplate[] arrayOfStreamTemplate = streamCombinationTemplate.mStreamTemplates;
        int j = arrayOfStreamTemplate.length;
        byte b1 = 0;
        while (b1 < j) {
          Size size;
          MandatoryStreamCombination.StreamTemplate streamTemplate = arrayOfStreamTemplate[b1];
          ArrayList<Size> arrayList2 = new ArrayList();
          int k = MandatoryStreamCombination.null.$SwitchMap$android$hardware$camera2$params$MandatoryStreamCombination$SizeThreshold[streamTemplate.mSizeThreshold.ordinal()];
          if (k != 1) {
            if (k != 2) {
              size = size2;
            } else {
              size = size1;
            } 
          } else {
            size = size3;
          } 
          arrayList2.add(getMinSize(size, getMaxSize(this.mStreamConfigMap.getOutputSizes(streamTemplate.mFormat))));
          try {
            MandatoryStreamCombination.MandatoryStreamInformation mandatoryStreamInformation = new MandatoryStreamCombination.MandatoryStreamInformation(arrayList2, streamTemplate.mFormat);
            arrayList1.add(mandatoryStreamInformation);
            b1++;
          } catch (IllegalArgumentException illegalArgumentException) {
            continue;
          } 
        } 
        try {
          MandatoryStreamCombination mandatoryStreamCombination = new MandatoryStreamCombination(arrayList1, streamCombinationTemplate.mDescription, false);
          arrayList.add(mandatoryStreamCombination);
          b++;
        } catch (IllegalArgumentException illegalArgumentException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No stream information for mandatory combination: ");
          stringBuilder.append(streamCombinationTemplate.mDescription);
          throw new RuntimeException(stringBuilder.toString(), illegalArgumentException);
        } 
      } 
      return Collections.unmodifiableList(arrayList);
    }
    
    public List<MandatoryStreamCombination> getAvailableMandatoryStreamCombinations() {
      if (!isColorOutputSupported()) {
        Log.v("MandatoryStreamCombination", "Device is not backward compatible!");
        return null;
      } 
      if (this.mCameraId < 0 && !isExternalCamera()) {
        Log.i("MandatoryStreamCombination", "Invalid camera id");
        return null;
      } 
      ArrayList<MandatoryStreamCombination.StreamCombinationTemplate> arrayList = new ArrayList();
      if (isHardwareLevelAtLeastLegacy())
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLegacyCombinations)); 
      if (isHardwareLevelAtLeastLimited() || isExternalCamera()) {
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLimitedCombinations));
        if (isPrivateReprocessingSupported())
          arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLimitedPrivateReprocCombinations)); 
        if (isYUVReprocessingSupported())
          arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLimitedYUVReprocCombinations)); 
      } 
      if (isCapabilitySupported(6))
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sBurstCombinations)); 
      if (isHardwareLevelAtLeastFull()) {
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sFullCombinations));
        if (isPrivateReprocessingSupported())
          arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sFullPrivateReprocCombinations)); 
        if (isYUVReprocessingSupported())
          arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sFullYUVReprocCombinations)); 
      } 
      if (isCapabilitySupported(3)) {
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sRawCombinations));
        if (isPrivateReprocessingSupported())
          arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sRAWPrivateReprocCombinations)); 
        if (isYUVReprocessingSupported())
          arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sRAWYUVReprocCombinations)); 
      } 
      if (isHardwareLevelAtLeastLevel3()) {
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLevel3Combinations));
        if (isPrivateReprocessingSupported())
          arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLevel3PrivateReprocCombinations)); 
        if (isYUVReprocessingSupported())
          arrayList.addAll(Arrays.asList(MandatoryStreamCombination.sLevel3YUVReprocCombinations)); 
      } 
      return generateAvailableCombinations(arrayList);
    }
    
    public static class SizeComparator implements Comparator<Size> {
      public int compare(Size param2Size1, Size param2Size2) {
        return MandatoryStreamCombination.Builder.compareSizes(param2Size1.getWidth(), param2Size1.getHeight(), param2Size2.getWidth(), param2Size2.getHeight());
      }
    }
  }
  
  public static class SizeComparator implements Comparator<Size> {
    public int compare(Size param1Size1, Size param1Size2) {
      return MandatoryStreamCombination.Builder.compareSizes(param1Size1.getWidth(), param1Size1.getHeight(), param1Size2.getWidth(), param1Size2.getHeight());
    }
  }
  
  public static final class MandatoryStreamInformation {
    private final ArrayList<Size> mAvailableSizes = new ArrayList<>();
    
    private final int mFormat;
    
    private final boolean mIsInput;
    
    public MandatoryStreamInformation(List<Size> param1List, int param1Int) {
      this(param1List, param1Int, false);
    }
    
    public MandatoryStreamInformation(List<Size> param1List, int param1Int, boolean param1Boolean) {
      if (!param1List.isEmpty()) {
        this.mAvailableSizes.addAll(param1List);
        this.mFormat = StreamConfigurationMap.checkArgumentFormat(param1Int);
        this.mIsInput = param1Boolean;
        return;
      } 
      throw new IllegalArgumentException("No available sizes");
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == null)
        return false; 
      if (this == param1Object)
        return true; 
      if (param1Object instanceof MandatoryStreamInformation) {
        param1Object = param1Object;
        return (this.mFormat != ((MandatoryStreamInformation)param1Object).mFormat || this.mIsInput != ((MandatoryStreamInformation)param1Object).mIsInput || this.mAvailableSizes.size() != ((MandatoryStreamInformation)param1Object).mAvailableSizes.size()) ? false : this.mAvailableSizes.equals(((MandatoryStreamInformation)param1Object).mAvailableSizes);
      } 
      return false;
    }
    
    public List<Size> getAvailableSizes() {
      return Collections.unmodifiableList(this.mAvailableSizes);
    }
    
    public int getFormat() {
      return this.mFormat;
    }
    
    public int hashCode() {
      return HashCodeHelpers.hashCode(new int[] { this.mFormat, Boolean.hashCode(this.mIsInput), this.mAvailableSizes.hashCode() });
    }
    
    public boolean isInput() {
      return this.mIsInput;
    }
  }
  
  private enum ReprocessType {
    NONE, PRIVATE, YUV;
    
    static {
      ReprocessType reprocessType = new ReprocessType("YUV", 2);
      YUV = reprocessType;
      $VALUES = new ReprocessType[] { NONE, PRIVATE, reprocessType };
    }
  }
  
  private enum SizeThreshold {
    VGA, s1440p, s720p, MAXIMUM, PREVIEW, RECORD;
    
    static {
      MAXIMUM = new SizeThreshold("MAXIMUM", 3);
      s720p = new SizeThreshold("s720p", 4);
      SizeThreshold sizeThreshold = new SizeThreshold("s1440p", 5);
      s1440p = sizeThreshold;
      $VALUES = new SizeThreshold[] { VGA, PREVIEW, RECORD, MAXIMUM, s720p, sizeThreshold };
    }
  }
  
  private static final class StreamCombinationTemplate {
    public String mDescription;
    
    public MandatoryStreamCombination.ReprocessType mReprocessType;
    
    public MandatoryStreamCombination.StreamTemplate[] mStreamTemplates;
    
    public StreamCombinationTemplate(MandatoryStreamCombination.StreamTemplate[] param1ArrayOfStreamTemplate, String param1String) {
      this(param1ArrayOfStreamTemplate, param1String, MandatoryStreamCombination.ReprocessType.NONE);
    }
    
    public StreamCombinationTemplate(MandatoryStreamCombination.StreamTemplate[] param1ArrayOfStreamTemplate, String param1String, MandatoryStreamCombination.ReprocessType param1ReprocessType) {
      this.mStreamTemplates = param1ArrayOfStreamTemplate;
      this.mReprocessType = param1ReprocessType;
      this.mDescription = param1String;
    }
  }
  
  private static final class StreamTemplate {
    public int mFormat;
    
    public boolean mIsInput;
    
    public MandatoryStreamCombination.SizeThreshold mSizeThreshold;
    
    public StreamTemplate(int param1Int, MandatoryStreamCombination.SizeThreshold param1SizeThreshold) {
      this(param1Int, param1SizeThreshold, false);
    }
    
    public StreamTemplate(int param1Int, MandatoryStreamCombination.SizeThreshold param1SizeThreshold, boolean param1Boolean) {
      this.mFormat = param1Int;
      this.mSizeThreshold = param1SizeThreshold;
      this.mIsInput = param1Boolean;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/MandatoryStreamCombination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
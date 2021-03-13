package android.hardware.camera2.params;

import android.hardware.camera2.CameraManager;
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

public final class Builder {
  private final Size kPreviewSizeBound = new Size(1920, 1088);
  
  private int mCameraId;
  
  private List<Integer> mCapabilities;
  
  private Size mDisplaySize;
  
  private int mHwLevel;
  
  private boolean mIsHiddenPhysicalCamera;
  
  private StreamConfigurationMap mStreamConfigMap;
  
  public Builder(int paramInt1, int paramInt2, Size paramSize, List<Integer> paramList, StreamConfigurationMap paramStreamConfigurationMap) {
    this.mCameraId = paramInt1;
    this.mDisplaySize = paramSize;
    this.mCapabilities = paramList;
    this.mStreamConfigMap = paramStreamConfigurationMap;
    this.mHwLevel = paramInt2;
    this.mIsHiddenPhysicalCamera = CameraManager.isHiddenPhysicalCamera(Integer.toString(paramInt1));
  }
  
  private static int compareSizes(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    long l1 = paramInt1 * paramInt2;
    long l2 = paramInt3 * paramInt4;
    long l3 = l1;
    long l4 = l2;
    if (l1 == l2) {
      l3 = paramInt1;
      l4 = paramInt3;
    } 
    if (l3 < l4) {
      paramInt1 = -1;
    } else if (l3 > l4) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    return paramInt1;
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
  
  private List<MandatoryStreamCombination> generateAvailableCombinations(ArrayList<MandatoryStreamCombination.StreamCombinationTemplate> paramArrayList) {
    if (paramArrayList.isEmpty()) {
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
    arrayList1.ensureCapacity(paramArrayList.size());
    Iterator<MandatoryStreamCombination.StreamCombinationTemplate> iterator = paramArrayList.iterator();
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
  
  private static List<Size> getAscendingOrderSizes(List<Size> paramList, boolean paramBoolean) {
    SizeComparator sizeComparator = new SizeComparator();
    ArrayList<Size> arrayList = new ArrayList();
    arrayList.addAll(paramList);
    Collections.sort(arrayList, sizeComparator);
    if (!paramBoolean)
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
  
  private Size getMaxPreviewSize(List<Size> paramList) {
    if (paramList != null)
      for (Size size : paramList) {
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
  
  public static Size getMaxSize(Size... paramVarArgs) {
    if (paramVarArgs != null && paramVarArgs.length != 0) {
      byte b = 0;
      Size size = paramVarArgs[0];
      int i = paramVarArgs.length;
      while (b < i) {
        Size size1 = paramVarArgs[b];
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
  
  public static Size getMinSize(Size paramSize1, Size paramSize2) {
    if (paramSize1 != null && paramSize2 != null)
      return (paramSize1.getWidth() * paramSize1.getHeight() < paramSize2.getHeight() * paramSize2.getWidth()) ? paramSize1 : paramSize2; 
    throw new IllegalArgumentException("sizes was empty");
  }
  
  private static List<Size> getSizesWithinBound(Size[] paramArrayOfSize, Size paramSize) {
    ArrayList<Size> arrayList = new ArrayList();
    int i = paramArrayOfSize.length;
    for (byte b = 0; b < i; b++) {
      Size size = paramArrayOfSize[b];
      if (size.getWidth() <= paramSize.getWidth() && size.getHeight() <= paramSize.getHeight())
        arrayList.add(size); 
    } 
    return arrayList;
  }
  
  private boolean isCapabilitySupported(int paramInt) {
    return this.mCapabilities.contains(Integer.valueOf(paramInt));
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
  
  private boolean isHardwareLevelAtLeast(int paramInt) {
    int[] arrayOfInt = new int[5];
    arrayOfInt[0] = 2;
    arrayOfInt[1] = 4;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 1;
    arrayOfInt[4] = 3;
    if (paramInt == this.mHwLevel)
      return true; 
    int i = arrayOfInt.length;
    for (byte b = 0; b < i; b++) {
      int j = arrayOfInt[b];
      if (j == paramInt)
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
    MandatoryStreamCombination.StreamCombinationTemplate[] arrayOfStreamCombinationTemplate = MandatoryStreamCombination.access$000();
    if (!isColorOutputSupported()) {
      Log.v("MandatoryStreamCombination", "Device is not backward compatible, depth streams are mandatory!");
      arrayOfStreamCombinationTemplate = MandatoryStreamCombination.access$100();
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
      arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$200())); 
    if (isHardwareLevelAtLeastLimited() || isExternalCamera()) {
      arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$300()));
      if (isPrivateReprocessingSupported())
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$400())); 
      if (isYUVReprocessingSupported())
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$500())); 
    } 
    if (isCapabilitySupported(6))
      arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$600())); 
    if (isHardwareLevelAtLeastFull()) {
      arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$700()));
      if (isPrivateReprocessingSupported())
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$800())); 
      if (isYUVReprocessingSupported())
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$900())); 
    } 
    if (isCapabilitySupported(3)) {
      arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$1000()));
      if (isPrivateReprocessingSupported())
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$1100())); 
      if (isYUVReprocessingSupported())
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$1200())); 
    } 
    if (isHardwareLevelAtLeastLevel3()) {
      arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$1300()));
      if (isPrivateReprocessingSupported())
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$1400())); 
      if (isYUVReprocessingSupported())
        arrayList.addAll(Arrays.asList(MandatoryStreamCombination.access$1500())); 
    } 
    return generateAvailableCombinations(arrayList);
  }
  
  public static class SizeComparator implements Comparator<Size> {
    public int compare(Size param2Size1, Size param2Size2) {
      return MandatoryStreamCombination.Builder.compareSizes(param2Size1.getWidth(), param2Size1.getHeight(), param2Size2.getWidth(), param2Size2.getHeight());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/MandatoryStreamCombination$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
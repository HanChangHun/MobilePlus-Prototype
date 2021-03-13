package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MandatoryStreamInformation {
  private final ArrayList<Size> mAvailableSizes = new ArrayList<>();
  
  private final int mFormat;
  
  private final boolean mIsInput;
  
  public MandatoryStreamInformation(List<Size> paramList, int paramInt) {
    this(paramList, paramInt, false);
  }
  
  public MandatoryStreamInformation(List<Size> paramList, int paramInt, boolean paramBoolean) {
    if (!paramList.isEmpty()) {
      this.mAvailableSizes.addAll(paramList);
      this.mFormat = StreamConfigurationMap.checkArgumentFormat(paramInt);
      this.mIsInput = paramBoolean;
      return;
    } 
    throw new IllegalArgumentException("No available sizes");
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof MandatoryStreamInformation) {
      paramObject = paramObject;
      return (this.mFormat != ((MandatoryStreamInformation)paramObject).mFormat || this.mIsInput != ((MandatoryStreamInformation)paramObject).mIsInput || this.mAvailableSizes.size() != ((MandatoryStreamInformation)paramObject).mAvailableSizes.size()) ? false : this.mAvailableSizes.equals(((MandatoryStreamInformation)paramObject).mAvailableSizes);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/MandatoryStreamCombination$MandatoryStreamInformation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
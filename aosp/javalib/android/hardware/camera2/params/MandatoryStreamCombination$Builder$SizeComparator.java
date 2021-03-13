package android.hardware.camera2.params;

import android.util.Size;
import java.util.Comparator;

public class SizeComparator implements Comparator<Size> {
  public int compare(Size paramSize1, Size paramSize2) {
    return MandatoryStreamCombination.Builder.access$1600(paramSize1.getWidth(), paramSize1.getHeight(), paramSize2.getWidth(), paramSize2.getHeight());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/MandatoryStreamCombination$Builder$SizeComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
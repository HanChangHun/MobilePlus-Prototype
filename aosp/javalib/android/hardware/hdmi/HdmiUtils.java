package android.hardware.hdmi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class HdmiUtils {
  public static final int HDMI_RELATIVE_POSITION_ABOVE = 5;
  
  public static final int HDMI_RELATIVE_POSITION_BELOW = 2;
  
  public static final int HDMI_RELATIVE_POSITION_DIFFERENT_BRANCH = 7;
  
  public static final int HDMI_RELATIVE_POSITION_DIRECTLY_ABOVE = 4;
  
  public static final int HDMI_RELATIVE_POSITION_DIRECTLY_BELOW = 1;
  
  public static final int HDMI_RELATIVE_POSITION_SAME = 3;
  
  public static final int HDMI_RELATIVE_POSITION_SIBLING = 6;
  
  public static final int HDMI_RELATIVE_POSITION_UNKNOWN = 0;
  
  private static final int NPOS = -1;
  
  static final int TARGET_NOT_UNDER_LOCAL_DEVICE = -1;
  
  static final int TARGET_SAME_PHYSICAL_ADDRESS = 0;
  
  public static int getHdmiAddressRelativePosition(int paramInt1, int paramInt2) {
    if (paramInt1 == 65535 || paramInt2 == 65535)
      return 0; 
    try {
      int i = physicalAddressFirstDifferentDigitPos(paramInt1, paramInt2);
      if (i == -1)
        return 3; 
      int j = 61440 >> i * 4;
      i++;
      return ((paramInt1 & j) == 0) ? ((i == 4) ? 4 : (((61440 >> i * 4 & paramInt2) == 0) ? 4 : 5)) : (((paramInt2 & j) == 0) ? ((i == 4) ? 1 : (((61440 >> i * 4 & paramInt1) == 0) ? 1 : 2)) : ((i == 4) ? 6 : (((61440 >> i * 4 & paramInt1) == 0 && (61440 >> i * 4 & paramInt2) == 0) ? 6 : 7)));
    } catch (IllegalArgumentException illegalArgumentException) {
      return 0;
    } 
  }
  
  public static int getLocalPortFromPhysicalAddress(int paramInt1, int paramInt2) {
    if (paramInt2 == paramInt1)
      return 0; 
    int i = 61440;
    int j = 61440;
    int k = paramInt2;
    while (k != 0) {
      k = paramInt2 & i;
      j |= i;
      i >>= 4;
    } 
    paramInt1 &= j;
    if ((j << 4 & paramInt1) != paramInt2)
      return -1; 
    for (paramInt1 &= i << 4; paramInt1 >> 4 != 0; paramInt1 >>= 4);
    return paramInt1;
  }
  
  public static boolean isValidPhysicalAddress(int paramInt) {
    if (paramInt < 0 || paramInt >= 65535)
      return false; 
    int i = 61440;
    boolean bool = false;
    byte b = 0;
    while (b < 4) {
      boolean bool1;
      if ((paramInt & i) == 0) {
        bool1 = true;
      } else {
        bool1 = bool;
        if (bool)
          return false; 
      } 
      i >>= 4;
      b++;
      bool = bool1;
    } 
    return true;
  }
  
  private static int physicalAddressFirstDifferentDigitPos(int paramInt1, int paramInt2) throws IllegalArgumentException {
    if (isValidPhysicalAddress(paramInt1)) {
      if (isValidPhysicalAddress(paramInt2)) {
        int i = 61440;
        for (byte b = 0; b < 4; b++) {
          if ((paramInt1 & i) != (paramInt2 & i))
            return b; 
          i >>= 4;
        } 
        return -1;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramInt2);
      stringBuilder1.append(" is not a valid address.");
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt1);
    stringBuilder.append(" is not a valid address.");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface HdmiAddressRelativePosition {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
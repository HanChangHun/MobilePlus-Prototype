package android.hardware.camera2;

import java.util.Comparator;

class null implements Comparator<String> {
  public int compare(String paramString1, String paramString2) {
    byte b1;
    byte b2;
    try {
      b1 = Integer.parseInt(paramString1);
    } catch (NumberFormatException numberFormatException) {
      b1 = -1;
    } 
    try {
      b2 = Integer.parseInt(paramString2);
    } catch (NumberFormatException numberFormatException) {
      b2 = -1;
    } 
    return (b1 >= 0 && b2 >= 0) ? (b1 - b2) : ((b1 >= 0) ? -1 : ((b2 >= 0) ? 1 : paramString1.compareTo(paramString2)));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CameraManager$CameraManagerGlobal$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.hardware.biometrics;

import android.annotation.SystemApi;

public interface Authenticators {
  @SystemApi
  public static final int BIOMETRIC_CONVENIENCE = 4095;
  
  public static final int BIOMETRIC_MAX_STRENGTH = 1;
  
  public static final int BIOMETRIC_MIN_STRENGTH = 32767;
  
  public static final int BIOMETRIC_STRONG = 15;
  
  public static final int BIOMETRIC_WEAK = 255;
  
  public static final int DEVICE_CREDENTIAL = 32768;
  
  @SystemApi
  public static final int EMPTY_SET = 0;
  
  public static @interface Types {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricManager$Authenticators.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
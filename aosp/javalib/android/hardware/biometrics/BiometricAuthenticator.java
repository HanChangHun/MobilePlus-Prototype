package android.hardware.biometrics;

import android.os.CancellationSignal;
import android.os.Parcelable;
import java.util.concurrent.Executor;

public interface BiometricAuthenticator {
  public static final int TYPE_CREDENTIAL = 1;
  
  public static final int TYPE_FACE = 8;
  
  public static final int TYPE_FINGERPRINT = 2;
  
  public static final int TYPE_IRIS = 4;
  
  public static final int TYPE_NONE = 0;
  
  default void authenticate(CryptoObject paramCryptoObject, CancellationSignal paramCancellationSignal, Executor paramExecutor, AuthenticationCallback paramAuthenticationCallback) {
    throw new UnsupportedOperationException("Stub!");
  }
  
  default void authenticate(CancellationSignal paramCancellationSignal, Executor paramExecutor, AuthenticationCallback paramAuthenticationCallback) {
    throw new UnsupportedOperationException("Stub!");
  }
  
  default boolean hasEnrolledTemplates() {
    throw new UnsupportedOperationException("Stub!");
  }
  
  default boolean hasEnrolledTemplates(int paramInt) {
    throw new UnsupportedOperationException("Stub!");
  }
  
  default boolean isHardwareDetected() {
    throw new UnsupportedOperationException("Stub!");
  }
  
  default void setActiveUser(int paramInt) {
    throw new UnsupportedOperationException("Stub!");
  }
  
  public static abstract class AuthenticationCallback {
    public void onAuthenticationAcquired(int param1Int) {}
    
    public void onAuthenticationError(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationFailed() {}
    
    public void onAuthenticationHelp(int param1Int, CharSequence param1CharSequence) {}
  }
  
  public static class AuthenticationResult {
    private int mAuthenticationType;
    
    private CryptoObject mCryptoObject;
    
    private BiometricAuthenticator.Identifier mIdentifier;
    
    private int mUserId;
    
    public AuthenticationResult() {}
    
    public AuthenticationResult(CryptoObject param1CryptoObject, int param1Int1, BiometricAuthenticator.Identifier param1Identifier, int param1Int2) {
      this.mCryptoObject = param1CryptoObject;
      this.mAuthenticationType = param1Int1;
      this.mIdentifier = param1Identifier;
      this.mUserId = param1Int2;
    }
    
    public int getAuthenticationType() {
      return this.mAuthenticationType;
    }
    
    public CryptoObject getCryptoObject() {
      return this.mCryptoObject;
    }
    
    public BiometricAuthenticator.Identifier getId() {
      return this.mIdentifier;
    }
    
    public int getUserId() {
      return this.mUserId;
    }
  }
  
  public static abstract class Identifier implements Parcelable {
    private int mBiometricId;
    
    private long mDeviceId;
    
    private CharSequence mName;
    
    public Identifier() {}
    
    public Identifier(CharSequence param1CharSequence, int param1Int, long param1Long) {
      this.mName = param1CharSequence;
      this.mBiometricId = param1Int;
      this.mDeviceId = param1Long;
    }
    
    public int getBiometricId() {
      return this.mBiometricId;
    }
    
    public long getDeviceId() {
      return this.mDeviceId;
    }
    
    public CharSequence getName() {
      return this.mName;
    }
    
    public void setDeviceId(long param1Long) {
      this.mDeviceId = param1Long;
    }
    
    public void setName(CharSequence param1CharSequence) {
      this.mName = param1CharSequence;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricAuthenticator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
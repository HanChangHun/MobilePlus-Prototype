package android.hardware.fingerprint;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.biometrics.BiometricAuthenticator;
import android.hardware.biometrics.BiometricFingerprintConstants;
import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.os.Binder;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.security.identity.IdentityCredential;
import android.util.Slog;
import java.security.Signature;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@Deprecated
public class FingerprintManager implements BiometricAuthenticator, BiometricFingerprintConstants {
  private static final boolean DEBUG = true;
  
  private static final int MSG_ACQUIRED = 101;
  
  private static final int MSG_AUTHENTICATION_FAILED = 103;
  
  private static final int MSG_AUTHENTICATION_SUCCEEDED = 102;
  
  private static final int MSG_ENROLL_RESULT = 100;
  
  private static final int MSG_ENUMERATED = 106;
  
  private static final int MSG_ERROR = 104;
  
  private static final int MSG_FINGERPRINT_DETECTED = 107;
  
  private static final int MSG_REMOVED = 105;
  
  private static final String TAG = "FingerprintManager";
  
  private AuthenticationCallback mAuthenticationCallback;
  
  private Context mContext;
  
  private CryptoObject mCryptoObject;
  
  private EnrollmentCallback mEnrollmentCallback;
  
  private EnumerateCallback mEnumerateCallback;
  
  private FingerprintDetectionCallback mFingerprintDetectionCallback;
  
  private Handler mHandler;
  
  private RemovalCallback mRemovalCallback;
  
  private Fingerprint mRemovalFingerprint;
  
  private IFingerprintService mService;
  
  private IFingerprintServiceReceiver mServiceReceiver = new IFingerprintServiceReceiver.Stub() {
      public void onAcquired(long param1Long, int param1Int1, int param1Int2) {
        FingerprintManager.this.mHandler.obtainMessage(101, param1Int1, param1Int2, Long.valueOf(param1Long)).sendToTarget();
      }
      
      public void onAuthenticationFailed(long param1Long) {
        FingerprintManager.this.mHandler.obtainMessage(103).sendToTarget();
      }
      
      public void onAuthenticationSucceeded(long param1Long, Fingerprint param1Fingerprint, int param1Int, boolean param1Boolean) {
        FingerprintManager.this.mHandler.obtainMessage(102, param1Int, param1Boolean, param1Fingerprint).sendToTarget();
      }
      
      public void onEnrollResult(long param1Long, int param1Int1, int param1Int2, int param1Int3) {
        FingerprintManager.this.mHandler.obtainMessage(100, param1Int3, 0, new Fingerprint(null, param1Int2, param1Int1, param1Long)).sendToTarget();
      }
      
      public void onEnumerated(long param1Long, int param1Int1, int param1Int2, int param1Int3) {
        FingerprintManager.this.mHandler.obtainMessage(106, param1Int1, param1Int2, Long.valueOf(param1Long)).sendToTarget();
      }
      
      public void onError(long param1Long, int param1Int1, int param1Int2) {
        FingerprintManager.this.mHandler.obtainMessage(104, param1Int1, param1Int2, Long.valueOf(param1Long)).sendToTarget();
      }
      
      public void onFingerprintDetected(long param1Long, int param1Int, boolean param1Boolean) {
        FingerprintManager.this.mHandler.obtainMessage(107, param1Int, 0, Boolean.valueOf(param1Boolean)).sendToTarget();
      }
      
      public void onRemoved(long param1Long, int param1Int1, int param1Int2, int param1Int3) {
        FingerprintManager.this.mHandler.obtainMessage(105, param1Int3, 0, new Fingerprint(null, param1Int2, param1Int1, param1Long)).sendToTarget();
      }
    };
  
  private IBinder mToken = (IBinder)new Binder();
  
  public FingerprintManager(Context paramContext, IFingerprintService paramIFingerprintService) {
    this.mContext = paramContext;
    this.mService = paramIFingerprintService;
    if (paramIFingerprintService == null)
      Slog.v("FingerprintManager", "FingerprintManagerService was null"); 
    this.mHandler = new MyHandler(paramContext);
  }
  
  private void cancelAuthentication(android.hardware.biometrics.CryptoObject paramCryptoObject) {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        iFingerprintService.cancelAuthentication(this.mToken, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  private void cancelEnrollment() {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        iFingerprintService.cancelEnrollment(this.mToken);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  private void cancelFingerprintDetect() {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService == null)
      return; 
    try {
      iFingerprintService.cancelFingerprintDetect(this.mToken, this.mContext.getOpPackageName());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static String getAcquiredString(Context paramContext, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder;
    String[] arrayOfString;
    switch (paramInt1) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid acquired message: ");
        stringBuilder.append(paramInt1);
        stringBuilder.append(", ");
        stringBuilder.append(paramInt2);
        Slog.w("FingerprintManager", stringBuilder.toString());
        return null;
      case 7:
        return null;
      case 6:
        arrayOfString = stringBuilder.getResources().getStringArray(17236104);
        if (paramInt2 < arrayOfString.length)
          return arrayOfString[paramInt2]; 
      case 5:
        return arrayOfString.getString(17040199);
      case 4:
        return arrayOfString.getString(17040200);
      case 3:
        return arrayOfString.getString(17040196);
      case 2:
        return arrayOfString.getString(17040197);
      case 1:
        return arrayOfString.getString(17040198);
      case 0:
        break;
    } 
    return null;
  }
  
  private int getCurrentUserId() {
    try {
      return (ActivityManager.getService().getCurrentUser()).id;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static String getErrorString(Context paramContext, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder;
    String[] arrayOfString;
    switch (paramInt1) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid error message: ");
        stringBuilder.append(paramInt1);
        stringBuilder.append(", ");
        stringBuilder.append(paramInt2);
        Slog.w("FingerprintManager", stringBuilder.toString());
        return null;
      case 15:
        return stringBuilder.getString(17040209);
      case 12:
        return stringBuilder.getString(17040204);
      case 11:
        return stringBuilder.getString(17040207);
      case 10:
        return stringBuilder.getString(17040212);
      case 9:
        return stringBuilder.getString(17040206);
      case 8:
        arrayOfString = stringBuilder.getResources().getStringArray(17236105);
        if (paramInt2 < arrayOfString.length)
          return arrayOfString[paramInt2]; 
      case 7:
        return arrayOfString.getString(17040205);
      case 5:
        return arrayOfString.getString(17040202);
      case 4:
        return arrayOfString.getString(17040208);
      case 3:
        return arrayOfString.getString(17040210);
      case 2:
        return arrayOfString.getString(17040211);
      case 1:
        break;
    } 
    return arrayOfString.getString(17040203);
  }
  
  private void sendAcquiredResult(long paramLong, int paramInt1, int paramInt2) {
    AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
    if (authenticationCallback != null)
      authenticationCallback.onAuthenticationAcquired(paramInt1); 
    String str = getAcquiredString(this.mContext, paramInt1, paramInt2);
    if (str == null)
      return; 
    if (paramInt1 == 6) {
      paramInt2 += 1000;
    } else {
      paramInt2 = paramInt1;
    } 
    EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
    if (enrollmentCallback != null) {
      enrollmentCallback.onEnrollmentHelp(paramInt2, str);
    } else {
      AuthenticationCallback authenticationCallback1 = this.mAuthenticationCallback;
      if (authenticationCallback1 != null && paramInt1 != 7)
        authenticationCallback1.onAuthenticationHelp(paramInt2, str); 
    } 
  }
  
  private void sendAuthenticatedFailed() {
    AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
    if (authenticationCallback != null)
      authenticationCallback.onAuthenticationFailed(); 
  }
  
  private void sendAuthenticatedSucceeded(Fingerprint paramFingerprint, int paramInt, boolean paramBoolean) {
    if (this.mAuthenticationCallback != null) {
      AuthenticationResult authenticationResult = new AuthenticationResult(this.mCryptoObject, paramFingerprint, paramInt, paramBoolean);
      this.mAuthenticationCallback.onAuthenticationSucceeded(authenticationResult);
    } 
  }
  
  private void sendEnrollResult(Fingerprint paramFingerprint, int paramInt) {
    EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
    if (enrollmentCallback != null)
      enrollmentCallback.onEnrollmentProgress(paramInt); 
  }
  
  private void sendEnumeratedResult(long paramLong, int paramInt1, int paramInt2) {
    EnumerateCallback enumerateCallback = this.mEnumerateCallback;
    if (enumerateCallback != null)
      enumerateCallback.onEnumerate(new Fingerprint(null, paramInt2, paramInt1, paramLong)); 
  }
  
  private void sendErrorResult(long paramLong, int paramInt1, int paramInt2) {
    int i;
    if (paramInt1 == 8) {
      i = paramInt2 + 1000;
    } else {
      i = paramInt1;
    } 
    EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
    if (enrollmentCallback != null) {
      enrollmentCallback.onEnrollmentError(i, getErrorString(this.mContext, paramInt1, paramInt2));
    } else {
      AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
      if (authenticationCallback != null) {
        authenticationCallback.onAuthenticationError(i, getErrorString(this.mContext, paramInt1, paramInt2));
      } else {
        RemovalCallback removalCallback = this.mRemovalCallback;
        if (removalCallback != null) {
          removalCallback.onRemovalError(this.mRemovalFingerprint, i, getErrorString(this.mContext, paramInt1, paramInt2));
        } else {
          EnumerateCallback enumerateCallback = this.mEnumerateCallback;
          if (enumerateCallback != null)
            enumerateCallback.onEnumerateError(i, getErrorString(this.mContext, paramInt1, paramInt2)); 
        } 
      } 
    } 
  }
  
  private void sendFingerprintDetected(int paramInt, boolean paramBoolean) {
    FingerprintDetectionCallback fingerprintDetectionCallback = this.mFingerprintDetectionCallback;
    if (fingerprintDetectionCallback == null) {
      Slog.e("FingerprintManager", "sendFingerprintDetected, callback null");
      return;
    } 
    fingerprintDetectionCallback.onFingerprintDetected(paramInt, paramBoolean);
  }
  
  private void sendRemovedResult(Fingerprint paramFingerprint, int paramInt) {
    StringBuilder stringBuilder;
    if (this.mRemovalCallback == null)
      return; 
    if (paramFingerprint == null) {
      Slog.e("FingerprintManager", "Received MSG_REMOVED, but fingerprint is null");
      return;
    } 
    int i = paramFingerprint.getBiometricId();
    int j = this.mRemovalFingerprint.getBiometricId();
    if (j != 0 && i != 0 && i != j) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Finger id didn't match: ");
      stringBuilder.append(i);
      stringBuilder.append(" != ");
      stringBuilder.append(j);
      Slog.w("FingerprintManager", stringBuilder.toString());
      return;
    } 
    j = stringBuilder.getGroupId();
    i = this.mRemovalFingerprint.getGroupId();
    if (j != i) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Group id didn't match: ");
      stringBuilder.append(j);
      stringBuilder.append(" != ");
      stringBuilder.append(i);
      Slog.w("FingerprintManager", stringBuilder.toString());
      return;
    } 
    this.mRemovalCallback.onRemovalSucceeded((Fingerprint)stringBuilder, paramInt);
  }
  
  private void useHandler(Handler paramHandler) {
    if (paramHandler != null) {
      this.mHandler = new MyHandler(paramHandler.getLooper());
    } else if (this.mHandler.getLooper() != this.mContext.getMainLooper()) {
      this.mHandler = new MyHandler(this.mContext.getMainLooper());
    } 
  }
  
  public void addLockoutResetCallback(LockoutResetCallback paramLockoutResetCallback) {
    if (this.mService != null) {
      try {
        PowerManager powerManager = (PowerManager)this.mContext.getSystemService(PowerManager.class);
        IFingerprintService iFingerprintService = this.mService;
        IBiometricServiceLockoutResetCallback.Stub stub = new IBiometricServiceLockoutResetCallback.Stub() {
            public void onLockoutReset(long param1Long, IRemoteCallback param1IRemoteCallback) throws RemoteException {
              try {
                PowerManager.WakeLock wakeLock = powerManager.newWakeLock(1, "lockoutResetCallback");
                wakeLock.acquire();
                Handler handler = FingerprintManager.this.mHandler;
                FingerprintManager.LockoutResetCallback lockoutResetCallback = callback;
                _$$Lambda$FingerprintManager$1$4i3tUU8mafgvA9HaB2UPD31L6UY _$$Lambda$FingerprintManager$1$4i3tUU8mafgvA9HaB2UPD31L6UY = new _$$Lambda$FingerprintManager$1$4i3tUU8mafgvA9HaB2UPD31L6UY();
                this(lockoutResetCallback, wakeLock);
                handler.post(_$$Lambda$FingerprintManager$1$4i3tUU8mafgvA9HaB2UPD31L6UY);
                return;
              } finally {
                param1IRemoteCallback.sendResult(null);
              } 
            }
          };
        super(this, powerManager, paramLockoutResetCallback);
        iFingerprintService.addLockoutResetCallback((IBiometricServiceLockoutResetCallback)stub);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } else {
      Slog.w("FingerprintManager", "addLockoutResetCallback(): Service not connected!");
    } 
  }
  
  @Deprecated
  public void authenticate(CryptoObject paramCryptoObject, CancellationSignal paramCancellationSignal, int paramInt, AuthenticationCallback paramAuthenticationCallback, Handler paramHandler) {
    authenticate(paramCryptoObject, paramCancellationSignal, paramInt, paramAuthenticationCallback, paramHandler, this.mContext.getUserId());
  }
  
  public void authenticate(CryptoObject paramCryptoObject, CancellationSignal paramCancellationSignal, int paramInt1, AuthenticationCallback paramAuthenticationCallback, Handler paramHandler, int paramInt2) {
    if (paramAuthenticationCallback != null) {
      if (paramCancellationSignal != null) {
        if (paramCancellationSignal.isCanceled()) {
          Slog.w("FingerprintManager", "authentication already canceled");
          return;
        } 
        paramCancellationSignal.setOnCancelListener(new OnAuthenticationCancelListener(paramCryptoObject));
      } 
      if (this.mService != null)
        try {
          long l;
          useHandler(paramHandler);
          this.mAuthenticationCallback = paramAuthenticationCallback;
          this.mCryptoObject = paramCryptoObject;
          if (paramCryptoObject != null) {
            l = paramCryptoObject.getOpId();
          } else {
            l = 0L;
          } 
          this.mService.authenticate(this.mToken, l, paramInt2, this.mServiceReceiver, paramInt1, this.mContext.getOpPackageName());
        } catch (RemoteException remoteException) {
          Slog.w("FingerprintManager", "Remote exception while authenticating: ", (Throwable)remoteException);
          if (paramAuthenticationCallback != null)
            paramAuthenticationCallback.onAuthenticationError(1, getErrorString(this.mContext, 1, 0)); 
        }  
      return;
    } 
    throw new IllegalArgumentException("Must supply an authentication callback");
  }
  
  public void detectFingerprint(CancellationSignal paramCancellationSignal, FingerprintDetectionCallback paramFingerprintDetectionCallback, int paramInt) {
    if (this.mService == null)
      return; 
    if (paramCancellationSignal.isCanceled()) {
      Slog.w("FingerprintManager", "Detection already cancelled");
      return;
    } 
    paramCancellationSignal.setOnCancelListener(new OnFingerprintDetectionCancelListener());
    this.mFingerprintDetectionCallback = paramFingerprintDetectionCallback;
    try {
      this.mService.detectFingerprint(this.mToken, paramInt, this.mServiceReceiver, this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      Slog.w("FingerprintManager", "Remote exception when requesting finger detect", (Throwable)remoteException);
    } 
  }
  
  public void enroll(byte[] paramArrayOfbyte, CancellationSignal paramCancellationSignal, int paramInt1, int paramInt2, EnrollmentCallback paramEnrollmentCallback) {
    int i = paramInt2;
    if (paramInt2 == -2)
      i = getCurrentUserId(); 
    if (paramEnrollmentCallback != null) {
      if (paramCancellationSignal != null) {
        if (paramCancellationSignal.isCanceled()) {
          Slog.w("FingerprintManager", "enrollment already canceled");
          return;
        } 
        paramCancellationSignal.setOnCancelListener(new OnEnrollCancelListener());
      } 
      IFingerprintService iFingerprintService = this.mService;
      if (iFingerprintService != null)
        try {
          this.mEnrollmentCallback = paramEnrollmentCallback;
          iFingerprintService.enroll(this.mToken, paramArrayOfbyte, i, this.mServiceReceiver, paramInt1, this.mContext.getOpPackageName());
        } catch (RemoteException remoteException) {
          Slog.w("FingerprintManager", "Remote exception in enroll: ", (Throwable)remoteException);
          if (paramEnrollmentCallback != null)
            paramEnrollmentCallback.onEnrollmentError(1, getErrorString(this.mContext, 1, 0)); 
        }  
      return;
    } 
    throw new IllegalArgumentException("Must supply an enrollment callback");
  }
  
  public void enumerate(int paramInt, EnumerateCallback paramEnumerateCallback) {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        this.mEnumerateCallback = paramEnumerateCallback;
        iFingerprintService.enumerate(this.mToken, paramInt, this.mServiceReceiver);
      } catch (RemoteException remoteException) {
        Slog.w("FingerprintManager", "Remote exception in enumerate: ", (Throwable)remoteException);
        if (paramEnumerateCallback != null)
          paramEnumerateCallback.onEnumerateError(1, getErrorString(this.mContext, 1, 0)); 
      }  
  }
  
  public List<Fingerprint> getEnrolledFingerprints() {
    return getEnrolledFingerprints(this.mContext.getUserId());
  }
  
  public List<Fingerprint> getEnrolledFingerprints(int paramInt) {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        return iFingerprintService.getEnrolledFingerprints(paramInt, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  @Deprecated
  public boolean hasEnrolledFingerprints() {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        return iFingerprintService.hasEnrolledFingerprints(this.mContext.getUserId(), this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean hasEnrolledFingerprints(int paramInt) {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        return iFingerprintService.hasEnrolledFingerprints(paramInt, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean hasEnrolledTemplates() {
    return hasEnrolledFingerprints();
  }
  
  public boolean hasEnrolledTemplates(int paramInt) {
    return hasEnrolledFingerprints(paramInt);
  }
  
  @Deprecated
  public boolean isHardwareDetected() {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        return iFingerprintService.isHardwareDetected(this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Slog.w("FingerprintManager", "isFingerprintHardwareDetected(): Service not connected!");
    return false;
  }
  
  public int postEnroll() {
    int i = 0;
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        i = iFingerprintService.postEnroll(this.mToken);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return i;
  }
  
  public long preEnroll() {
    long l = 0L;
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        l = iFingerprintService.preEnroll(this.mToken);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return l;
  }
  
  public void remove(Fingerprint paramFingerprint, int paramInt, RemovalCallback paramRemovalCallback) {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        this.mRemovalCallback = paramRemovalCallback;
        this.mRemovalFingerprint = paramFingerprint;
        iFingerprintService.remove(this.mToken, paramFingerprint.getBiometricId(), paramFingerprint.getGroupId(), paramInt, this.mServiceReceiver);
      } catch (RemoteException remoteException) {
        Slog.w("FingerprintManager", "Remote exception in remove: ", (Throwable)remoteException);
        if (paramRemovalCallback != null)
          paramRemovalCallback.onRemovalError(paramFingerprint, 1, getErrorString(this.mContext, 1, 0)); 
      }  
  }
  
  public void rename(int paramInt1, int paramInt2, String paramString) {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null) {
      try {
        iFingerprintService.rename(paramInt1, paramInt2, paramString);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } else {
      Slog.w("FingerprintManager", "rename(): Service not connected!");
    } 
  }
  
  public void setActiveUser(int paramInt) {
    IFingerprintService iFingerprintService = this.mService;
    if (iFingerprintService != null)
      try {
        iFingerprintService.setActiveUser(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  @Deprecated
  public static abstract class AuthenticationCallback extends BiometricAuthenticator.AuthenticationCallback {
    public void onAuthenticationAcquired(int param1Int) {}
    
    public void onAuthenticationError(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationFailed() {}
    
    public void onAuthenticationHelp(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult param1AuthenticationResult) {}
  }
  
  @Deprecated
  public static class AuthenticationResult {
    private FingerprintManager.CryptoObject mCryptoObject;
    
    private Fingerprint mFingerprint;
    
    private boolean mIsStrongBiometric;
    
    private int mUserId;
    
    public AuthenticationResult(FingerprintManager.CryptoObject param1CryptoObject, Fingerprint param1Fingerprint, int param1Int, boolean param1Boolean) {
      this.mCryptoObject = param1CryptoObject;
      this.mFingerprint = param1Fingerprint;
      this.mUserId = param1Int;
      this.mIsStrongBiometric = param1Boolean;
    }
    
    public FingerprintManager.CryptoObject getCryptoObject() {
      return this.mCryptoObject;
    }
    
    public Fingerprint getFingerprint() {
      return this.mFingerprint;
    }
    
    public int getUserId() {
      return this.mUserId;
    }
    
    public boolean isStrongBiometric() {
      return this.mIsStrongBiometric;
    }
  }
  
  @Deprecated
  public static final class CryptoObject extends android.hardware.biometrics.CryptoObject {
    public CryptoObject(Signature param1Signature) {
      super(param1Signature);
    }
    
    public CryptoObject(Cipher param1Cipher) {
      super(param1Cipher);
    }
    
    public CryptoObject(Mac param1Mac) {
      super(param1Mac);
    }
    
    public Cipher getCipher() {
      return super.getCipher();
    }
    
    public IdentityCredential getIdentityCredential() {
      return super.getIdentityCredential();
    }
    
    public Mac getMac() {
      return super.getMac();
    }
    
    public Signature getSignature() {
      return super.getSignature();
    }
  }
  
  public static abstract class EnrollmentCallback {
    public void onEnrollmentError(int param1Int, CharSequence param1CharSequence) {}
    
    public void onEnrollmentHelp(int param1Int, CharSequence param1CharSequence) {}
    
    public void onEnrollmentProgress(int param1Int) {}
  }
  
  public static abstract class EnumerateCallback {
    public void onEnumerate(Fingerprint param1Fingerprint) {}
    
    public void onEnumerateError(int param1Int, CharSequence param1CharSequence) {}
  }
  
  public static interface FingerprintDetectionCallback {
    void onFingerprintDetected(int param1Int, boolean param1Boolean);
  }
  
  public static abstract class LockoutResetCallback {
    public void onLockoutReset() {}
  }
  
  private class MyHandler extends Handler {
    private MyHandler(Context param1Context) {
      super(param1Context.getMainLooper());
    }
    
    private MyHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      FingerprintManager fingerprintManager;
      Fingerprint fingerprint;
      int i;
      int j;
      boolean bool;
      switch (param1Message.what) {
        default:
          return;
        case 107:
          FingerprintManager.this.sendFingerprintDetected(param1Message.arg1, ((Boolean)param1Message.obj).booleanValue());
        case 106:
          FingerprintManager.this.sendEnumeratedResult(((Long)param1Message.obj).longValue(), param1Message.arg1, param1Message.arg2);
        case 105:
          FingerprintManager.this.sendRemovedResult((Fingerprint)param1Message.obj, param1Message.arg1);
        case 104:
          FingerprintManager.this.sendErrorResult(((Long)param1Message.obj).longValue(), param1Message.arg1, param1Message.arg2);
        case 103:
          FingerprintManager.this.sendAuthenticatedFailed();
        case 102:
          fingerprintManager = FingerprintManager.this;
          fingerprint = (Fingerprint)param1Message.obj;
          i = param1Message.arg1;
          j = param1Message.arg2;
          bool = true;
          if (j != 1)
            bool = false; 
          fingerprintManager.sendAuthenticatedSucceeded(fingerprint, i, bool);
        case 101:
          FingerprintManager.this.sendAcquiredResult(((Long)param1Message.obj).longValue(), param1Message.arg1, param1Message.arg2);
        case 100:
          break;
      } 
      FingerprintManager.this.sendEnrollResult((Fingerprint)param1Message.obj, param1Message.arg1);
    }
  }
  
  private class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
    private android.hardware.biometrics.CryptoObject mCrypto;
    
    public OnAuthenticationCancelListener(android.hardware.biometrics.CryptoObject param1CryptoObject) {
      this.mCrypto = param1CryptoObject;
    }
    
    public void onCancel() {
      FingerprintManager.this.cancelAuthentication(this.mCrypto);
    }
  }
  
  private class OnEnrollCancelListener implements CancellationSignal.OnCancelListener {
    private OnEnrollCancelListener() {}
    
    public void onCancel() {
      FingerprintManager.this.cancelEnrollment();
    }
  }
  
  private class OnFingerprintDetectionCancelListener implements CancellationSignal.OnCancelListener {
    private OnFingerprintDetectionCancelListener() {}
    
    public void onCancel() {
      FingerprintManager.this.cancelFingerprintDetect();
    }
  }
  
  public static abstract class RemovalCallback {
    public void onRemovalError(Fingerprint param1Fingerprint, int param1Int, CharSequence param1CharSequence) {}
    
    public void onRemovalSucceeded(Fingerprint param1Fingerprint, int param1Int) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/FingerprintManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
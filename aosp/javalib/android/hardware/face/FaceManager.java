package android.hardware.face;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.biometrics.BiometricAuthenticator;
import android.hardware.biometrics.BiometricFaceConstants;
import android.hardware.biometrics.CryptoObject;
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
import android.os.Trace;
import android.os.UserHandle;
import android.util.Log;
import android.util.Slog;
import com.android.internal.os.SomeArgs;
import java.util.List;

public class FaceManager implements BiometricAuthenticator, BiometricFaceConstants {
  private static final boolean DEBUG = true;
  
  private static final int MSG_ACQUIRED = 101;
  
  private static final int MSG_AUTHENTICATION_FAILED = 103;
  
  private static final int MSG_AUTHENTICATION_SUCCEEDED = 102;
  
  private static final int MSG_ENROLL_RESULT = 100;
  
  private static final int MSG_ERROR = 104;
  
  private static final int MSG_GET_FEATURE_COMPLETED = 106;
  
  private static final int MSG_REMOVED = 105;
  
  private static final int MSG_SET_FEATURE_COMPLETED = 107;
  
  private static final String TAG = "FaceManager";
  
  private AuthenticationCallback mAuthenticationCallback;
  
  private final Context mContext;
  
  private CryptoObject mCryptoObject;
  
  private EnrollmentCallback mEnrollmentCallback;
  
  private GetFeatureCallback mGetFeatureCallback;
  
  private Handler mHandler;
  
  private RemovalCallback mRemovalCallback;
  
  private Face mRemovalFace;
  
  private IFaceService mService;
  
  private IFaceServiceReceiver mServiceReceiver = new IFaceServiceReceiver.Stub() {
      public void onAcquired(long param1Long, int param1Int1, int param1Int2) {
        FaceManager.this.mHandler.obtainMessage(101, param1Int1, param1Int2, Long.valueOf(param1Long)).sendToTarget();
      }
      
      public void onAuthenticationFailed(long param1Long) {
        FaceManager.this.mHandler.obtainMessage(103).sendToTarget();
      }
      
      public void onAuthenticationSucceeded(long param1Long, Face param1Face, int param1Int, boolean param1Boolean) {
        FaceManager.this.mHandler.obtainMessage(102, param1Int, param1Boolean, param1Face).sendToTarget();
      }
      
      public void onEnrollResult(long param1Long, int param1Int1, int param1Int2) {
        FaceManager.this.mHandler.obtainMessage(100, param1Int2, 0, new Face(null, param1Int1, param1Long)).sendToTarget();
      }
      
      public void onEnumerated(long param1Long, int param1Int1, int param1Int2) {}
      
      public void onError(long param1Long, int param1Int1, int param1Int2) {
        FaceManager.this.mHandler.obtainMessage(104, param1Int1, param1Int2, Long.valueOf(param1Long)).sendToTarget();
      }
      
      public void onFeatureGet(boolean param1Boolean1, int param1Int, boolean param1Boolean2) {
        SomeArgs someArgs = SomeArgs.obtain();
        someArgs.arg1 = Boolean.valueOf(param1Boolean1);
        someArgs.argi1 = param1Int;
        someArgs.arg2 = Boolean.valueOf(param1Boolean2);
        FaceManager.this.mHandler.obtainMessage(106, someArgs).sendToTarget();
      }
      
      public void onFeatureSet(boolean param1Boolean, int param1Int) {
        FaceManager.this.mHandler.obtainMessage(107, param1Int, 0, Boolean.valueOf(param1Boolean)).sendToTarget();
      }
      
      public void onRemoved(long param1Long, int param1Int1, int param1Int2) {
        FaceManager.this.mHandler.obtainMessage(105, param1Int2, 0, new Face(null, param1Int1, param1Long)).sendToTarget();
      }
    };
  
  private SetFeatureCallback mSetFeatureCallback;
  
  private IBinder mToken = (IBinder)new Binder();
  
  public FaceManager(Context paramContext, IFaceService paramIFaceService) {
    this.mContext = paramContext;
    this.mService = paramIFaceService;
    if (paramIFaceService == null)
      Slog.v("FaceManager", "FaceAuthenticationManagerService was null"); 
    this.mHandler = new MyHandler(paramContext);
  }
  
  private void cancelAuthentication(CryptoObject paramCryptoObject) {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        iFaceService.cancelAuthentication(this.mToken, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  private void cancelEnrollment() {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        iFaceService.cancelEnrollment(this.mToken);
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
        Slog.w("FaceManager", stringBuilder.toString());
        return null;
      case 22:
        arrayOfString = stringBuilder.getResources().getStringArray(17236102);
        if (paramInt2 < arrayOfString.length)
          return arrayOfString[paramInt2]; 
      case 21:
        return arrayOfString.getString(17040148);
      case 20:
        return null;
      case 19:
        return arrayOfString.getString(17040143);
      case 18:
        return arrayOfString.getString(17040147);
      case 17:
        return arrayOfString.getString(17040149);
      case 16:
        return arrayOfString.getString(17040144);
      case 15:
        return arrayOfString.getString(17040160);
      case 14:
        return arrayOfString.getString(17040153);
      case 13:
        return arrayOfString.getString(17040146);
      case 12:
        return arrayOfString.getString(17040158);
      case 11:
        return arrayOfString.getString(17040142);
      case 10:
        return arrayOfString.getString(17040145);
      case 9:
        return arrayOfString.getString(17040156);
      case 8:
        return arrayOfString.getString(17040159);
      case 7:
        return arrayOfString.getString(17040157);
      case 6:
        return arrayOfString.getString(17040155);
      case 5:
        return arrayOfString.getString(17040154);
      case 4:
        return arrayOfString.getString(17040151);
      case 3:
        return arrayOfString.getString(17040152);
      case 2:
        return arrayOfString.getString(17040150);
      case 1:
        return arrayOfString.getString(17040141);
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
        Slog.w("FaceManager", stringBuilder.toString());
        return "";
      case 15:
        return stringBuilder.getString(17040170);
      case 12:
        return stringBuilder.getString(17040165);
      case 11:
        return stringBuilder.getString(17040169);
      case 10:
        return stringBuilder.getString(17040173);
      case 9:
        return stringBuilder.getString(17040167);
      case 8:
        arrayOfString = stringBuilder.getResources().getStringArray(17236103);
        if (paramInt2 < arrayOfString.length)
          return arrayOfString[paramInt2]; 
      case 7:
        return arrayOfString.getString(17040166);
      case 5:
        return arrayOfString.getString(17040163);
      case 4:
        return arrayOfString.getString(17040168);
      case 3:
        return arrayOfString.getString(17040171);
      case 2:
        return arrayOfString.getString(17040172);
      case 1:
        break;
    } 
    return arrayOfString.getString(17040164);
  }
  
  public static int getMappedAcquiredInfo(int paramInt1, int paramInt2) {
    if (paramInt1 != 22) {
      switch (paramInt1) {
        default:
          return 0;
        case 10:
        case 11:
        case 12:
        case 13:
          return 2;
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
          return 1;
        case 1:
        case 2:
        case 3:
          break;
      } 
      return 2;
    } 
    return paramInt2 + 1000;
  }
  
  private void sendAcquiredResult(long paramLong, int paramInt1, int paramInt2) {
    AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
    if (authenticationCallback != null)
      authenticationCallback.onAuthenticationAcquired(paramInt1); 
    String str = getAcquiredString(this.mContext, paramInt1, paramInt2);
    if (paramInt1 == 22)
      paramInt1 = paramInt2 + 1000; 
    EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
    if (enrollmentCallback != null) {
      enrollmentCallback.onEnrollmentHelp(paramInt1, str);
    } else {
      AuthenticationCallback authenticationCallback1 = this.mAuthenticationCallback;
      if (authenticationCallback1 != null && str != null)
        authenticationCallback1.onAuthenticationHelp(paramInt1, str); 
    } 
  }
  
  private void sendAuthenticatedFailed() {
    AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
    if (authenticationCallback != null)
      authenticationCallback.onAuthenticationFailed(); 
  }
  
  private void sendAuthenticatedSucceeded(Face paramFace, int paramInt, boolean paramBoolean) {
    if (this.mAuthenticationCallback != null) {
      AuthenticationResult authenticationResult = new AuthenticationResult(this.mCryptoObject, paramFace, paramInt, paramBoolean);
      this.mAuthenticationCallback.onAuthenticationSucceeded(authenticationResult);
    } 
  }
  
  private void sendEnrollResult(Face paramFace, int paramInt) {
    EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
    if (enrollmentCallback != null)
      enrollmentCallback.onEnrollmentProgress(paramInt); 
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
        if (removalCallback != null)
          removalCallback.onRemovalError(this.mRemovalFace, i, getErrorString(this.mContext, paramInt1, paramInt2)); 
      } 
    } 
  }
  
  private void sendGetFeatureCompleted(boolean paramBoolean1, int paramInt, boolean paramBoolean2) {
    GetFeatureCallback getFeatureCallback = this.mGetFeatureCallback;
    if (getFeatureCallback == null)
      return; 
    getFeatureCallback.onCompleted(paramBoolean1, paramInt, paramBoolean2);
  }
  
  private void sendRemovedResult(Face paramFace, int paramInt) {
    RemovalCallback removalCallback = this.mRemovalCallback;
    if (removalCallback == null)
      return; 
    if (paramFace == null) {
      Log.e("FaceManager", "Received MSG_REMOVED, but face is null");
      return;
    } 
    removalCallback.onRemovalSucceeded(paramFace, paramInt);
  }
  
  private void sendSetFeatureCompleted(boolean paramBoolean, int paramInt) {
    SetFeatureCallback setFeatureCallback = this.mSetFeatureCallback;
    if (setFeatureCallback == null)
      return; 
    setFeatureCallback.onCompleted(paramBoolean, paramInt);
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
        IFaceService iFaceService = this.mService;
        IBiometricServiceLockoutResetCallback.Stub stub = new IBiometricServiceLockoutResetCallback.Stub() {
            public void onLockoutReset(long param1Long, IRemoteCallback param1IRemoteCallback) throws RemoteException {
              try {
                PowerManager.WakeLock wakeLock = powerManager.newWakeLock(1, "faceLockoutResetCallback");
                wakeLock.acquire();
                Handler handler = FaceManager.this.mHandler;
                FaceManager.LockoutResetCallback lockoutResetCallback = callback;
                _$$Lambda$FaceManager$2$IVmrd2VOH7JdDdb7PFFlL5bjZ5w _$$Lambda$FaceManager$2$IVmrd2VOH7JdDdb7PFFlL5bjZ5w = new _$$Lambda$FaceManager$2$IVmrd2VOH7JdDdb7PFFlL5bjZ5w();
                this(lockoutResetCallback, wakeLock);
                handler.post(_$$Lambda$FaceManager$2$IVmrd2VOH7JdDdb7PFFlL5bjZ5w);
                return;
              } finally {
                param1IRemoteCallback.sendResult(null);
              } 
            }
          };
        super(this, powerManager, paramLockoutResetCallback);
        iFaceService.addLockoutResetCallback((IBiometricServiceLockoutResetCallback)stub);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } else {
      Log.w("FaceManager", "addLockoutResetCallback(): Service not connected!");
    } 
  }
  
  public void authenticate(CryptoObject paramCryptoObject, CancellationSignal paramCancellationSignal, int paramInt, AuthenticationCallback paramAuthenticationCallback, Handler paramHandler) {
    authenticate(paramCryptoObject, paramCancellationSignal, paramInt, paramAuthenticationCallback, paramHandler, this.mContext.getUserId());
  }
  
  public void authenticate(CryptoObject paramCryptoObject, CancellationSignal paramCancellationSignal, int paramInt1, AuthenticationCallback paramAuthenticationCallback, Handler paramHandler, int paramInt2) {
    if (paramAuthenticationCallback != null) {
      if (paramCancellationSignal != null) {
        if (paramCancellationSignal.isCanceled()) {
          Log.w("FaceManager", "authentication already canceled");
          return;
        } 
        paramCancellationSignal.setOnCancelListener(new OnAuthenticationCancelListener(paramCryptoObject));
      } 
      if (this.mService != null) {
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
          Trace.beginSection("FaceManager#authenticate");
          this.mService.authenticate(this.mToken, l, paramInt2, this.mServiceReceiver, paramInt1, this.mContext.getOpPackageName());
        } catch (RemoteException remoteException) {
          Log.w("FaceManager", "Remote exception while authenticating: ", (Throwable)remoteException);
          if (paramAuthenticationCallback != null)
            paramAuthenticationCallback.onAuthenticationError(1, getErrorString(this.mContext, 1, 0)); 
        } finally {}
        Trace.endSection();
      } 
      return;
    } 
    throw new IllegalArgumentException("Must supply an authentication callback");
  }
  
  public void enroll(int paramInt, byte[] paramArrayOfbyte, CancellationSignal paramCancellationSignal, EnrollmentCallback paramEnrollmentCallback, int[] paramArrayOfint) {
    if (paramEnrollmentCallback != null) {
      if (paramCancellationSignal != null) {
        if (paramCancellationSignal.isCanceled()) {
          Log.w("FaceManager", "enrollment already canceled");
          return;
        } 
        paramCancellationSignal.setOnCancelListener(new OnEnrollCancelListener());
      } 
      if (this.mService != null) {
        try {
          this.mEnrollmentCallback = paramEnrollmentCallback;
          Trace.beginSection("FaceManager#enroll");
          this.mService.enroll(paramInt, this.mToken, paramArrayOfbyte, this.mServiceReceiver, this.mContext.getOpPackageName(), paramArrayOfint);
        } catch (RemoteException remoteException) {
          Log.w("FaceManager", "Remote exception in enroll: ", (Throwable)remoteException);
          if (paramEnrollmentCallback != null)
            paramEnrollmentCallback.onEnrollmentError(1, getErrorString(this.mContext, 1, 0)); 
        } finally {}
        Trace.endSection();
      } 
      return;
    } 
    throw new IllegalArgumentException("Must supply an enrollment callback");
  }
  
  public long generateChallenge() {
    long l = 0L;
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        l = iFaceService.generateChallenge(this.mToken);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return l;
  }
  
  public List<Face> getEnrolledFaces() {
    return getEnrolledFaces(UserHandle.myUserId());
  }
  
  public List<Face> getEnrolledFaces(int paramInt) {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        return iFaceService.getEnrolledFaces(paramInt, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public void getFeature(int paramInt1, int paramInt2, GetFeatureCallback paramGetFeatureCallback) {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        this.mGetFeatureCallback = paramGetFeatureCallback;
        iFaceService.getFeature(paramInt1, paramInt2, this.mServiceReceiver, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public boolean hasEnrolledTemplates() {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        return iFaceService.hasEnrolledFaces(UserHandle.myUserId(), this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean hasEnrolledTemplates(int paramInt) {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        return iFaceService.hasEnrolledFaces(paramInt, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return false;
  }
  
  public boolean isHardwareDetected() {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        return iFaceService.isHardwareDetected(this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Log.w("FaceManager", "isFaceHardwareDetected(): Service not connected!");
    return false;
  }
  
  public void remove(Face paramFace, int paramInt, RemovalCallback paramRemovalCallback) {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        this.mRemovalCallback = paramRemovalCallback;
        this.mRemovalFace = paramFace;
        iFaceService.remove(this.mToken, paramFace.getBiometricId(), paramInt, this.mServiceReceiver, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        Log.w("FaceManager", "Remote exception in remove: ", (Throwable)remoteException);
        if (paramRemovalCallback != null)
          paramRemovalCallback.onRemovalError(paramFace, 1, getErrorString(this.mContext, 1, 0)); 
      }  
  }
  
  public int revokeChallenge() {
    int i = 0;
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        i = iFaceService.revokeChallenge(this.mToken);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return i;
  }
  
  public void setActiveUser(int paramInt) {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        iFaceService.setActiveUser(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void setFeature(int paramInt1, int paramInt2, boolean paramBoolean, byte[] paramArrayOfbyte, SetFeatureCallback paramSetFeatureCallback) {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        this.mSetFeatureCallback = paramSetFeatureCallback;
        iFaceService.setFeature(paramInt1, paramInt2, paramBoolean, paramArrayOfbyte, this.mServiceReceiver, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public void userActivity() {
    IFaceService iFaceService = this.mService;
    if (iFaceService != null)
      try {
        iFaceService.userActivity();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
  }
  
  public static abstract class AuthenticationCallback extends BiometricAuthenticator.AuthenticationCallback {
    public void onAuthenticationAcquired(int param1Int) {}
    
    public void onAuthenticationError(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationFailed() {}
    
    public void onAuthenticationHelp(int param1Int, CharSequence param1CharSequence) {}
    
    public void onAuthenticationSucceeded(FaceManager.AuthenticationResult param1AuthenticationResult) {}
  }
  
  public static class AuthenticationResult {
    private CryptoObject mCryptoObject;
    
    private Face mFace;
    
    private boolean mIsStrongBiometric;
    
    private int mUserId;
    
    public AuthenticationResult(CryptoObject param1CryptoObject, Face param1Face, int param1Int, boolean param1Boolean) {
      this.mCryptoObject = param1CryptoObject;
      this.mFace = param1Face;
      this.mUserId = param1Int;
      this.mIsStrongBiometric = param1Boolean;
    }
    
    public CryptoObject getCryptoObject() {
      return this.mCryptoObject;
    }
    
    public Face getFace() {
      return this.mFace;
    }
    
    public int getUserId() {
      return this.mUserId;
    }
    
    public boolean isStrongBiometric() {
      return this.mIsStrongBiometric;
    }
  }
  
  public static abstract class EnrollmentCallback {
    public void onEnrollmentError(int param1Int, CharSequence param1CharSequence) {}
    
    public void onEnrollmentHelp(int param1Int, CharSequence param1CharSequence) {}
    
    public void onEnrollmentProgress(int param1Int) {}
  }
  
  public static abstract class GetFeatureCallback {
    public abstract void onCompleted(boolean param1Boolean1, int param1Int, boolean param1Boolean2);
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
      SomeArgs someArgs;
      FaceManager faceManager;
      Face face;
      int i;
      int j;
      boolean bool;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("FaceManager#handleMessage: ");
      stringBuilder.append(Integer.toString(param1Message.what));
      Trace.beginSection(stringBuilder.toString());
      switch (param1Message.what) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown message: ");
          stringBuilder.append(param1Message.what);
          Log.w("FaceManager", stringBuilder.toString());
          break;
        case 107:
          FaceManager.this.sendSetFeatureCompleted(((Boolean)param1Message.obj).booleanValue(), param1Message.arg1);
          break;
        case 106:
          someArgs = (SomeArgs)param1Message.obj;
          FaceManager.this.sendGetFeatureCompleted(((Boolean)someArgs.arg1).booleanValue(), someArgs.argi1, ((Boolean)someArgs.arg2).booleanValue());
          someArgs.recycle();
          break;
        case 105:
          FaceManager.this.sendRemovedResult((Face)((Message)someArgs).obj, ((Message)someArgs).arg1);
          break;
        case 104:
          FaceManager.this.sendErrorResult(((Long)((Message)someArgs).obj).longValue(), ((Message)someArgs).arg1, ((Message)someArgs).arg2);
          break;
        case 103:
          FaceManager.this.sendAuthenticatedFailed();
          break;
        case 102:
          faceManager = FaceManager.this;
          face = (Face)((Message)someArgs).obj;
          i = ((Message)someArgs).arg1;
          j = ((Message)someArgs).arg2;
          bool = true;
          if (j != 1)
            bool = false; 
          faceManager.sendAuthenticatedSucceeded(face, i, bool);
          break;
        case 101:
          FaceManager.this.sendAcquiredResult(((Long)((Message)someArgs).obj).longValue(), ((Message)someArgs).arg1, ((Message)someArgs).arg2);
          break;
        case 100:
          FaceManager.this.sendEnrollResult((Face)((Message)someArgs).obj, ((Message)someArgs).arg1);
          break;
      } 
      Trace.endSection();
    }
  }
  
  private class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
    private CryptoObject mCrypto;
    
    OnAuthenticationCancelListener(CryptoObject param1CryptoObject) {
      this.mCrypto = param1CryptoObject;
    }
    
    public void onCancel() {
      FaceManager.this.cancelAuthentication(this.mCrypto);
    }
  }
  
  private class OnEnrollCancelListener implements CancellationSignal.OnCancelListener {
    private OnEnrollCancelListener() {}
    
    public void onCancel() {
      FaceManager.this.cancelEnrollment();
    }
  }
  
  public static abstract class RemovalCallback {
    public void onRemovalError(Face param1Face, int param1Int, CharSequence param1CharSequence) {}
    
    public void onRemovalSucceeded(Face param1Face, int param1Int) {}
  }
  
  public static abstract class SetFeatureCallback {
    public abstract void onCompleted(boolean param1Boolean, int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/FaceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
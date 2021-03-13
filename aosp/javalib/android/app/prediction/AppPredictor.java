package android.app.prediction;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@SystemApi
public final class AppPredictor {
  private static final String TAG = AppPredictor.class.getSimpleName();
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private final AtomicBoolean mIsClosed = new AtomicBoolean(false);
  
  private final IPredictionManager mPredictionManager = IPredictionManager.Stub.asInterface(ServiceManager.getService("app_prediction"));
  
  private final ArrayMap<Callback, CallbackWrapper> mRegisteredCallbacks = new ArrayMap();
  
  private final AppPredictionSessionId mSessionId;
  
  private final IBinder mToken = (IBinder)new Binder();
  
  AppPredictor(Context paramContext, AppPredictionContext paramAppPredictionContext) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramContext.getPackageName());
    stringBuilder.append(":");
    stringBuilder.append(UUID.randomUUID().toString());
    AppPredictionSessionId appPredictionSessionId = new AppPredictionSessionId(stringBuilder.toString(), paramContext.getUserId());
    this.mSessionId = appPredictionSessionId;
    try {
      this.mPredictionManager.createPredictionSession(paramAppPredictionContext, appPredictionSessionId, this.mToken);
    } catch (RemoteException remoteException) {
      Log.e(TAG, "Failed to create predictor", (Throwable)remoteException);
      remoteException.rethrowAsRuntimeException();
    } 
    this.mCloseGuard.open("close");
  }
  
  public void destroy() {
    if (!this.mIsClosed.getAndSet(true)) {
      this.mCloseGuard.close();
      try {
        this.mPredictionManager.onDestroyPredictionSession(this.mSessionId);
      } catch (RemoteException remoteException) {
        Log.e(TAG, "Failed to notify app target event", (Throwable)remoteException);
        remoteException.rethrowAsRuntimeException();
      } 
      this.mRegisteredCallbacks.clear();
      return;
    } 
    throw new IllegalStateException("This client has already been destroyed.");
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      if (!this.mIsClosed.get())
        destroy(); 
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public AppPredictionSessionId getSessionId() {
    return this.mSessionId;
  }
  
  public void notifyAppTargetEvent(AppTargetEvent paramAppTargetEvent) {
    if (!this.mIsClosed.get()) {
      try {
        this.mPredictionManager.notifyAppTargetEvent(this.mSessionId, paramAppTargetEvent);
      } catch (RemoteException remoteException) {
        Log.e(TAG, "Failed to notify app target event", (Throwable)remoteException);
        remoteException.rethrowAsRuntimeException();
      } 
      return;
    } 
    throw new IllegalStateException("This client has already been destroyed.");
  }
  
  public void notifyLaunchLocationShown(String paramString, List<AppTargetId> paramList) {
    if (!this.mIsClosed.get()) {
      try {
        IPredictionManager iPredictionManager = this.mPredictionManager;
        AppPredictionSessionId appPredictionSessionId = this.mSessionId;
        ParceledListSlice parceledListSlice = new ParceledListSlice();
        this(paramList);
        iPredictionManager.notifyLaunchLocationShown(appPredictionSessionId, paramString, parceledListSlice);
      } catch (RemoteException remoteException) {
        Log.e(TAG, "Failed to notify location shown event", (Throwable)remoteException);
        remoteException.rethrowAsRuntimeException();
      } 
      return;
    } 
    throw new IllegalStateException("This client has already been destroyed.");
  }
  
  public void registerPredictionUpdates(Executor paramExecutor, Callback paramCallback) {
    if (!this.mIsClosed.get()) {
      if (this.mRegisteredCallbacks.containsKey(paramCallback))
        return; 
      try {
        CallbackWrapper callbackWrapper = new CallbackWrapper();
        Objects.requireNonNull(paramCallback);
        _$$Lambda$1lqxDplfWlUwgBrOynX9L0oK_uA _$$Lambda$1lqxDplfWlUwgBrOynX9L0oK_uA = new _$$Lambda$1lqxDplfWlUwgBrOynX9L0oK_uA();
        this(paramCallback);
        this(paramExecutor, _$$Lambda$1lqxDplfWlUwgBrOynX9L0oK_uA);
        this.mPredictionManager.registerPredictionUpdates(this.mSessionId, callbackWrapper);
        this.mRegisteredCallbacks.put(paramCallback, callbackWrapper);
      } catch (RemoteException remoteException) {
        Log.e(TAG, "Failed to register for prediction updates", (Throwable)remoteException);
        remoteException.rethrowAsRuntimeException();
      } 
      return;
    } 
    throw new IllegalStateException("This client has already been destroyed.");
  }
  
  public void requestPredictionUpdate() {
    if (!this.mIsClosed.get()) {
      try {
        this.mPredictionManager.requestPredictionUpdate(this.mSessionId);
      } catch (RemoteException remoteException) {
        Log.e(TAG, "Failed to request prediction update", (Throwable)remoteException);
        remoteException.rethrowAsRuntimeException();
      } 
      return;
    } 
    throw new IllegalStateException("This client has already been destroyed.");
  }
  
  public void sortTargets(List<AppTarget> paramList, Executor paramExecutor, Consumer<List<AppTarget>> paramConsumer) {
    if (!this.mIsClosed.get()) {
      try {
        IPredictionManager iPredictionManager = this.mPredictionManager;
        AppPredictionSessionId appPredictionSessionId = this.mSessionId;
        ParceledListSlice parceledListSlice = new ParceledListSlice();
        this(paramList);
        CallbackWrapper callbackWrapper = new CallbackWrapper();
        this(paramExecutor, paramConsumer);
        iPredictionManager.sortAppTargets(appPredictionSessionId, parceledListSlice, callbackWrapper);
      } catch (RemoteException remoteException) {
        Log.e(TAG, "Failed to sort targets", (Throwable)remoteException);
        remoteException.rethrowAsRuntimeException();
      } 
      return;
    } 
    throw new IllegalStateException("This client has already been destroyed.");
  }
  
  public void unregisterPredictionUpdates(Callback paramCallback) {
    if (!this.mIsClosed.get()) {
      if (!this.mRegisteredCallbacks.containsKey(paramCallback))
        return; 
      try {
        CallbackWrapper callbackWrapper = (CallbackWrapper)this.mRegisteredCallbacks.remove(paramCallback);
        this.mPredictionManager.unregisterPredictionUpdates(this.mSessionId, callbackWrapper);
      } catch (RemoteException remoteException) {
        Log.e(TAG, "Failed to unregister for prediction updates", (Throwable)remoteException);
        remoteException.rethrowAsRuntimeException();
      } 
      return;
    } 
    throw new IllegalStateException("This client has already been destroyed.");
  }
  
  public static interface Callback {
    void onTargetsAvailable(List<AppTarget> param1List);
  }
  
  static class CallbackWrapper extends IPredictionCallback.Stub {
    private final Consumer<List<AppTarget>> mCallback;
    
    private final Executor mExecutor;
    
    CallbackWrapper(Executor param1Executor, Consumer<List<AppTarget>> param1Consumer) {
      this.mCallback = param1Consumer;
      this.mExecutor = param1Executor;
    }
    
    public void onResult(ParceledListSlice param1ParceledListSlice) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = this.mExecutor;
        _$$Lambda$AppPredictor$CallbackWrapper$gCs3O3sYRlsXAOdelds31867YXo _$$Lambda$AppPredictor$CallbackWrapper$gCs3O3sYRlsXAOdelds31867YXo = new _$$Lambda$AppPredictor$CallbackWrapper$gCs3O3sYRlsXAOdelds31867YXo();
        this(this, param1ParceledListSlice);
        executor.execute(_$$Lambda$AppPredictor$CallbackWrapper$gCs3O3sYRlsXAOdelds31867YXo);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppPredictor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
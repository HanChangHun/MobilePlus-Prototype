package android.app.contentsuggestions;

import android.annotation.SystemApi;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.os.IResultReceiver;
import com.android.internal.util.SyncResultReceiver;
import java.util.List;
import java.util.concurrent.Executor;

@SystemApi
public final class ContentSuggestionsManager {
  public static final String EXTRA_BITMAP = "android.contentsuggestions.extra.BITMAP";
  
  private static final int SYNC_CALLS_TIMEOUT_MS = 5000;
  
  private static final String TAG = ContentSuggestionsManager.class.getSimpleName();
  
  private final IContentSuggestionsManager mService;
  
  private final int mUser;
  
  public ContentSuggestionsManager(int paramInt, IContentSuggestionsManager paramIContentSuggestionsManager) {
    this.mService = paramIContentSuggestionsManager;
    this.mUser = paramInt;
  }
  
  public void classifyContentSelections(ClassificationsRequest paramClassificationsRequest, Executor paramExecutor, ClassificationsCallback paramClassificationsCallback) {
    IContentSuggestionsManager iContentSuggestionsManager = this.mService;
    if (iContentSuggestionsManager == null) {
      Log.e(TAG, "classifyContentSelections called, but no ContentSuggestionsManager configured");
      return;
    } 
    try {
      int i = this.mUser;
      ClassificationsCallbackWrapper classificationsCallbackWrapper = new ClassificationsCallbackWrapper();
      this(paramClassificationsCallback, paramExecutor);
      iContentSuggestionsManager.classifyContentSelections(i, paramClassificationsRequest, classificationsCallbackWrapper);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isEnabled() {
    IContentSuggestionsManager iContentSuggestionsManager = this.mService;
    boolean bool = false;
    if (iContentSuggestionsManager == null)
      return false; 
    SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
    try {
      this.mService.isEnabled(this.mUser, (IResultReceiver)syncResultReceiver);
      int i = syncResultReceiver.getIntResult();
      if (i != 0)
        bool = true; 
      return bool;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } catch (com.android.internal.util.SyncResultReceiver.TimeoutException timeoutException) {
      throw new RuntimeException("Fail to get the enable status.");
    } 
  }
  
  public void notifyInteraction(String paramString, Bundle paramBundle) {
    IContentSuggestionsManager iContentSuggestionsManager = this.mService;
    if (iContentSuggestionsManager == null) {
      Log.e(TAG, "notifyInteraction called, but no ContentSuggestionsManager configured");
      return;
    } 
    try {
      iContentSuggestionsManager.notifyInteraction(this.mUser, paramString, paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void provideContextImage(int paramInt, Bundle paramBundle) {
    IContentSuggestionsManager iContentSuggestionsManager = this.mService;
    if (iContentSuggestionsManager == null) {
      Log.e(TAG, "provideContextImage called, but no ContentSuggestionsManager configured");
      return;
    } 
    try {
      iContentSuggestionsManager.provideContextImage(this.mUser, paramInt, paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void provideContextImage(Bitmap paramBitmap, Bundle paramBundle) {
    IContentSuggestionsManager iContentSuggestionsManager = this.mService;
    if (iContentSuggestionsManager == null) {
      Log.e(TAG, "provideContextImage called, but no ContentSuggestionsManager configured");
      return;
    } 
    try {
      iContentSuggestionsManager.provideContextBitmap(this.mUser, paramBitmap, paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void suggestContentSelections(SelectionsRequest paramSelectionsRequest, Executor paramExecutor, SelectionsCallback paramSelectionsCallback) {
    IContentSuggestionsManager iContentSuggestionsManager = this.mService;
    if (iContentSuggestionsManager == null) {
      Log.e(TAG, "suggestContentSelections called, but no ContentSuggestionsManager configured");
      return;
    } 
    try {
      int i = this.mUser;
      SelectionsCallbackWrapper selectionsCallbackWrapper = new SelectionsCallbackWrapper();
      this(paramSelectionsCallback, paramExecutor);
      iContentSuggestionsManager.suggestContentSelections(i, paramSelectionsRequest, selectionsCallbackWrapper);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static interface ClassificationsCallback {
    void onContentClassificationsAvailable(int param1Int, List<ContentClassification> param1List);
  }
  
  private static final class ClassificationsCallbackWrapper extends IClassificationsCallback.Stub {
    private final ContentSuggestionsManager.ClassificationsCallback mCallback;
    
    private final Executor mExecutor;
    
    ClassificationsCallbackWrapper(ContentSuggestionsManager.ClassificationsCallback param1ClassificationsCallback, Executor param1Executor) {
      this.mCallback = param1ClassificationsCallback;
      this.mExecutor = param1Executor;
    }
    
    public void onContentClassificationsAvailable(int param1Int, List<ContentClassification> param1List) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = this.mExecutor;
        _$$Lambda$ContentSuggestionsManager$ClassificationsCallbackWrapper$bS71fhWJJl2gObzWDnBMzvYmM5w _$$Lambda$ContentSuggestionsManager$ClassificationsCallbackWrapper$bS71fhWJJl2gObzWDnBMzvYmM5w = new _$$Lambda$ContentSuggestionsManager$ClassificationsCallbackWrapper$bS71fhWJJl2gObzWDnBMzvYmM5w();
        this(this, param1Int, param1List);
        executor.execute(_$$Lambda$ContentSuggestionsManager$ClassificationsCallbackWrapper$bS71fhWJJl2gObzWDnBMzvYmM5w);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
  }
  
  public static interface SelectionsCallback {
    void onContentSelectionsAvailable(int param1Int, List<ContentSelection> param1List);
  }
  
  private static class SelectionsCallbackWrapper extends ISelectionsCallback.Stub {
    private final ContentSuggestionsManager.SelectionsCallback mCallback;
    
    private final Executor mExecutor;
    
    SelectionsCallbackWrapper(ContentSuggestionsManager.SelectionsCallback param1SelectionsCallback, Executor param1Executor) {
      this.mCallback = param1SelectionsCallback;
      this.mExecutor = param1Executor;
    }
    
    public void onContentSelectionsAvailable(int param1Int, List<ContentSelection> param1List) {
      long l = Binder.clearCallingIdentity();
      try {
        Executor executor = this.mExecutor;
        _$$Lambda$ContentSuggestionsManager$SelectionsCallbackWrapper$1Py0lukljawDYbfwobeRIUDvpNM _$$Lambda$ContentSuggestionsManager$SelectionsCallbackWrapper$1Py0lukljawDYbfwobeRIUDvpNM = new _$$Lambda$ContentSuggestionsManager$SelectionsCallbackWrapper$1Py0lukljawDYbfwobeRIUDvpNM();
        this(this, param1Int, param1List);
        executor.execute(_$$Lambda$ContentSuggestionsManager$SelectionsCallbackWrapper$1Py0lukljawDYbfwobeRIUDvpNM);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ContentSuggestionsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
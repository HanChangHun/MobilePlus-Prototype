package android.app.contentsuggestions;

import android.os.Binder;
import java.util.List;
import java.util.concurrent.Executor;

class SelectionsCallbackWrapper extends ISelectionsCallback.Stub {
  private final ContentSuggestionsManager.SelectionsCallback mCallback;
  
  private final Executor mExecutor;
  
  SelectionsCallbackWrapper(ContentSuggestionsManager.SelectionsCallback paramSelectionsCallback, Executor paramExecutor) {
    this.mCallback = paramSelectionsCallback;
    this.mExecutor = paramExecutor;
  }
  
  public void onContentSelectionsAvailable(int paramInt, List<ContentSelection> paramList) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$ContentSuggestionsManager$SelectionsCallbackWrapper$1Py0lukljawDYbfwobeRIUDvpNM _$$Lambda$ContentSuggestionsManager$SelectionsCallbackWrapper$1Py0lukljawDYbfwobeRIUDvpNM = new _$$Lambda$ContentSuggestionsManager$SelectionsCallbackWrapper$1Py0lukljawDYbfwobeRIUDvpNM();
      this(this, paramInt, paramList);
      executor.execute(_$$Lambda$ContentSuggestionsManager$SelectionsCallbackWrapper$1Py0lukljawDYbfwobeRIUDvpNM);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ContentSuggestionsManager$SelectionsCallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
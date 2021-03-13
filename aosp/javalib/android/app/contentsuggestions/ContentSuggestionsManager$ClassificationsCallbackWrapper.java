package android.app.contentsuggestions;

import android.os.Binder;
import java.util.List;
import java.util.concurrent.Executor;

final class ClassificationsCallbackWrapper extends IClassificationsCallback.Stub {
  private final ContentSuggestionsManager.ClassificationsCallback mCallback;
  
  private final Executor mExecutor;
  
  ClassificationsCallbackWrapper(ContentSuggestionsManager.ClassificationsCallback paramClassificationsCallback, Executor paramExecutor) {
    this.mCallback = paramClassificationsCallback;
    this.mExecutor = paramExecutor;
  }
  
  public void onContentClassificationsAvailable(int paramInt, List<ContentClassification> paramList) {
    long l = Binder.clearCallingIdentity();
    try {
      Executor executor = this.mExecutor;
      _$$Lambda$ContentSuggestionsManager$ClassificationsCallbackWrapper$bS71fhWJJl2gObzWDnBMzvYmM5w _$$Lambda$ContentSuggestionsManager$ClassificationsCallbackWrapper$bS71fhWJJl2gObzWDnBMzvYmM5w = new _$$Lambda$ContentSuggestionsManager$ClassificationsCallbackWrapper$bS71fhWJJl2gObzWDnBMzvYmM5w();
      this(this, paramInt, paramList);
      executor.execute(_$$Lambda$ContentSuggestionsManager$ClassificationsCallbackWrapper$bS71fhWJJl2gObzWDnBMzvYmM5w);
      return;
    } finally {
      Binder.restoreCallingIdentity(l);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ContentSuggestionsManager$ClassificationsCallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
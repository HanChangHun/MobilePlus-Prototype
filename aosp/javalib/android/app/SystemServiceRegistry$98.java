package android.app;

import android.app.contentsuggestions.ContentSuggestionsManager;
import android.app.contentsuggestions.IContentSuggestionsManager;
import android.os.IBinder;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<ContentSuggestionsManager> {
  public ContentSuggestionsManager createService(ContextImpl paramContextImpl) {
    IBinder iBinder = ServiceManager.getService("content_suggestions");
    IContentSuggestionsManager iContentSuggestionsManager = IContentSuggestionsManager.Stub.asInterface(iBinder);
    return new ContentSuggestionsManager(paramContextImpl.getUserId(), iContentSuggestionsManager);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$98.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
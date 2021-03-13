package android.app;

import android.content.ContentCaptureOptions;
import android.content.Context;
import android.os.ServiceManager;
import android.view.contentcapture.ContentCaptureManager;
import android.view.contentcapture.IContentCaptureManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<ContentCaptureManager> {
  public ContentCaptureManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    Context context = paramContextImpl.getOuterContext();
    ContentCaptureOptions contentCaptureOptions = context.getContentCaptureOptions();
    if (contentCaptureOptions != null && (contentCaptureOptions.lite || contentCaptureOptions.isWhitelisted(context))) {
      IContentCaptureManager iContentCaptureManager = IContentCaptureManager.Stub.asInterface(ServiceManager.getService("content_capture"));
      if (iContentCaptureManager != null)
        return new ContentCaptureManager(context, iContentCaptureManager, contentCaptureOptions); 
    } 
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$96.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
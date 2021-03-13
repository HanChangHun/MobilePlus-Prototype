package android.app;

import android.media.MediaRouter;
import java.lang.ref.WeakReference;

class MediaRouterCallback extends MediaRouter.SimpleCallback {
  private final WeakReference<MediaRouteActionProvider> mProviderWeak;
  
  public MediaRouterCallback(MediaRouteActionProvider paramMediaRouteActionProvider) {
    this.mProviderWeak = new WeakReference<>(paramMediaRouteActionProvider);
  }
  
  private void refreshRoute(MediaRouter paramMediaRouter) {
    MediaRouteActionProvider mediaRouteActionProvider = this.mProviderWeak.get();
    if (mediaRouteActionProvider != null) {
      MediaRouteActionProvider.access$000(mediaRouteActionProvider);
    } else {
      paramMediaRouter.removeCallback((MediaRouter.Callback)this);
    } 
  }
  
  public void onRouteAdded(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo) {
    refreshRoute(paramMediaRouter);
  }
  
  public void onRouteChanged(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo) {
    refreshRoute(paramMediaRouter);
  }
  
  public void onRouteRemoved(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo) {
    refreshRoute(paramMediaRouter);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/MediaRouteActionProvider$MediaRouterCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
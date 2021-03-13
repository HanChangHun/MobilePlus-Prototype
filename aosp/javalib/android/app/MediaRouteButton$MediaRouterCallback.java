package android.app;

import android.media.MediaRouter;

final class MediaRouterCallback extends MediaRouter.SimpleCallback {
  private MediaRouterCallback() {}
  
  public void onRouteAdded(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo) {
    MediaRouteButton.access$100(MediaRouteButton.this);
  }
  
  public void onRouteChanged(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo) {
    MediaRouteButton.access$100(MediaRouteButton.this);
  }
  
  public void onRouteGrouped(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo, MediaRouter.RouteGroup paramRouteGroup, int paramInt) {
    MediaRouteButton.access$100(MediaRouteButton.this);
  }
  
  public void onRouteRemoved(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo) {
    MediaRouteButton.access$100(MediaRouteButton.this);
  }
  
  public void onRouteSelected(MediaRouter paramMediaRouter, int paramInt, MediaRouter.RouteInfo paramRouteInfo) {
    MediaRouteButton.access$100(MediaRouteButton.this);
  }
  
  public void onRouteUngrouped(MediaRouter paramMediaRouter, MediaRouter.RouteInfo paramRouteInfo, MediaRouter.RouteGroup paramRouteGroup) {
    MediaRouteButton.access$100(MediaRouteButton.this);
  }
  
  public void onRouteUnselected(MediaRouter paramMediaRouter, int paramInt, MediaRouter.RouteInfo paramRouteInfo) {
    MediaRouteButton.access$100(MediaRouteButton.this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/MediaRouteButton$MediaRouterCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.Context;
import android.media.MediaRouter;
import android.util.Log;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

public class MediaRouteActionProvider extends ActionProvider {
  private static final String TAG = "MediaRouteActionProvider";
  
  private MediaRouteButton mButton;
  
  private final MediaRouterCallback mCallback;
  
  private final Context mContext;
  
  private View.OnClickListener mExtendedSettingsListener;
  
  private int mRouteTypes;
  
  private final MediaRouter mRouter;
  
  public MediaRouteActionProvider(Context paramContext) {
    super(paramContext);
    this.mContext = paramContext;
    this.mRouter = (MediaRouter)paramContext.getSystemService("media_router");
    this.mCallback = new MediaRouterCallback(this);
    setRouteTypes(1);
  }
  
  private void refreshRoute() {
    refreshVisibility();
  }
  
  public boolean isVisible() {
    return this.mRouter.isRouteAvailable(this.mRouteTypes, 1);
  }
  
  public View onCreateActionView() {
    throw new UnsupportedOperationException("Use onCreateActionView(MenuItem) instead.");
  }
  
  public View onCreateActionView(MenuItem paramMenuItem) {
    if (this.mButton != null)
      Log.e("MediaRouteActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old one..."); 
    MediaRouteButton mediaRouteButton = new MediaRouteButton(this.mContext);
    this.mButton = mediaRouteButton;
    mediaRouteButton.setRouteTypes(this.mRouteTypes);
    this.mButton.setExtendedSettingsClickListener(this.mExtendedSettingsListener);
    this.mButton.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
    return this.mButton;
  }
  
  public boolean onPerformDefaultAction() {
    MediaRouteButton mediaRouteButton = this.mButton;
    return (mediaRouteButton != null) ? mediaRouteButton.showDialogInternal() : false;
  }
  
  public boolean overridesItemVisibility() {
    return true;
  }
  
  public void setExtendedSettingsClickListener(View.OnClickListener paramOnClickListener) {
    this.mExtendedSettingsListener = paramOnClickListener;
    MediaRouteButton mediaRouteButton = this.mButton;
    if (mediaRouteButton != null)
      mediaRouteButton.setExtendedSettingsClickListener(paramOnClickListener); 
  }
  
  public void setRouteTypes(int paramInt) {
    int i = this.mRouteTypes;
    if (i != paramInt) {
      if (i != 0)
        this.mRouter.removeCallback((MediaRouter.Callback)this.mCallback); 
      this.mRouteTypes = paramInt;
      if (paramInt != 0)
        this.mRouter.addCallback(paramInt, (MediaRouter.Callback)this.mCallback, 8); 
      refreshRoute();
      MediaRouteButton mediaRouteButton = this.mButton;
      if (mediaRouteButton != null)
        mediaRouteButton.setRouteTypes(this.mRouteTypes); 
    } 
  }
  
  private static class MediaRouterCallback extends MediaRouter.SimpleCallback {
    private final WeakReference<MediaRouteActionProvider> mProviderWeak;
    
    public MediaRouterCallback(MediaRouteActionProvider param1MediaRouteActionProvider) {
      this.mProviderWeak = new WeakReference<>(param1MediaRouteActionProvider);
    }
    
    private void refreshRoute(MediaRouter param1MediaRouter) {
      MediaRouteActionProvider mediaRouteActionProvider = this.mProviderWeak.get();
      if (mediaRouteActionProvider != null) {
        mediaRouteActionProvider.refreshRoute();
      } else {
        param1MediaRouter.removeCallback((MediaRouter.Callback)this);
      } 
    }
    
    public void onRouteAdded(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      refreshRoute(param1MediaRouter);
    }
    
    public void onRouteChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      refreshRoute(param1MediaRouter);
    }
    
    public void onRouteRemoved(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      refreshRoute(param1MediaRouter);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/MediaRouteActionProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
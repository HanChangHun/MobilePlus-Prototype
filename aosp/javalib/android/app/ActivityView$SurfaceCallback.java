package android.app;

import android.util.DisplayMetrics;
import android.view.DisplayInfo;
import android.view.SurfaceHolder;

class SurfaceCallback implements SurfaceHolder.Callback {
  private final DisplayInfo mTempDisplayInfo = new DisplayInfo();
  
  private final DisplayMetrics mTempMetrics = new DisplayMetrics();
  
  private SurfaceCallback() {}
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3) {
    if (!ActivityView.this.getVirtualDisplay().getDisplay().getDisplayInfo(this.mTempDisplayInfo))
      return; 
    this.mTempDisplayInfo.getAppMetrics(this.mTempMetrics);
    if (paramInt2 != this.mTempMetrics.widthPixels || paramInt3 != this.mTempMetrics.heightPixels) {
      ActivityView.access$200(ActivityView.this).resizeTask(paramInt2, paramInt3);
      ActivityView.access$200(ActivityView.this).notifyBoundsChanged();
    } 
  }
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
    if (!ActivityView.access$200(ActivityView.this).isInitialized()) {
      ActivityView activityView = ActivityView.this;
      ActivityView.access$400(activityView, ActivityView.access$300(activityView).getSurfaceControl());
    } else {
      ActivityView.access$500(ActivityView.this).reparent(ActivityView.access$200(ActivityView.this).getSurfaceControl(), ActivityView.access$300(ActivityView.this).getSurfaceControl()).apply();
    } 
    ActivityView.access$200(ActivityView.this).resizeTask(ActivityView.this.getWidth(), ActivityView.this.getHeight());
    ActivityView.access$200(ActivityView.this).start();
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
    ActivityView.access$200(ActivityView.this).stop();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityView$SurfaceCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
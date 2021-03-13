package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.display.VirtualDisplay;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DisplayInfo;
import android.view.IWindow;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.window.TaskEmbedder;
import android.window.TaskOrganizerTaskEmbedder;
import android.window.VirtualDisplayTaskEmbedder;
import dalvik.system.CloseGuard;

public class ActivityView extends ViewGroup implements TaskEmbedder.Host {
  private static final String TAG = "ActivityView";
  
  private final CloseGuard mGuard = CloseGuard.get();
  
  private boolean mOpened;
  
  private final Matrix mScreenSurfaceMatrix = new Matrix();
  
  private final SurfaceCallback mSurfaceCallback;
  
  private final SurfaceView mSurfaceView;
  
  private final Region mTapExcludeRegion = new Region();
  
  private TaskEmbedder mTaskEmbedder;
  
  private final int[] mTmpArray = new int[2];
  
  private final Rect mTmpRect = new Rect();
  
  private final SurfaceControl.Transaction mTmpTransaction = new SurfaceControl.Transaction();
  
  private final Point mWindowPosition = new Point();
  
  public ActivityView(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public ActivityView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ActivityView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    this(paramContext, paramAttributeSet, paramInt, false);
  }
  
  public ActivityView(Context paramContext, AttributeSet paramAttributeSet, int paramInt, boolean paramBoolean) {
    this(paramContext, paramAttributeSet, paramInt, paramBoolean, false);
  }
  
  public ActivityView(Context paramContext, AttributeSet paramAttributeSet, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    this(paramContext, paramAttributeSet, paramInt, paramBoolean1, paramBoolean2, false);
  }
  
  public ActivityView(Context paramContext, AttributeSet paramAttributeSet, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    this(paramContext, paramAttributeSet, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, false);
  }
  
  public ActivityView(Context paramContext, AttributeSet paramAttributeSet, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    super(paramContext, paramAttributeSet, paramInt);
    if (useTaskOrganizer()) {
      this.mTaskEmbedder = (TaskEmbedder)new TaskOrganizerTaskEmbedder(paramContext, this);
    } else {
      this.mTaskEmbedder = (TaskEmbedder)new VirtualDisplayTaskEmbedder(paramContext, this, paramBoolean1, paramBoolean2, paramBoolean4);
    } 
    SurfaceView surfaceView = new SurfaceView(paramContext, null, 0, 0, paramBoolean3);
    this.mSurfaceView = surfaceView;
    surfaceView.setAlpha(super.getAlpha());
    this.mSurfaceView.setUseAlpha();
    this.mSurfaceCallback = new SurfaceCallback();
    this.mSurfaceView.getHolder().addCallback(this.mSurfaceCallback);
    addView((View)this.mSurfaceView);
    this.mOpened = true;
    this.mGuard.open("release");
  }
  
  private boolean initTaskEmbedder(SurfaceControl paramSurfaceControl) {
    if (!this.mTaskEmbedder.initialize(paramSurfaceControl)) {
      Log.e("ActivityView", "Failed to initialize ActivityView");
      return false;
    } 
    return true;
  }
  
  private void performRelease() {
    if (!this.mOpened)
      return; 
    this.mSurfaceView.getHolder().removeCallback(this.mSurfaceCallback);
    if (this.mTaskEmbedder.isInitialized())
      this.mTaskEmbedder.release(); 
    this.mTaskEmbedder.setListener(null);
    this.mGuard.close();
    this.mOpened = false;
  }
  
  public boolean canReceivePointerEvents() {
    return super.canReceivePointerEvents();
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mGuard != null) {
        this.mGuard.warnIfOpen();
        performRelease();
      } 
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public boolean gatherTransparentRegion(Region paramRegion) {
    return (this.mTaskEmbedder.gatherTransparentRegion(paramRegion) || super.gatherTransparentRegion(paramRegion));
  }
  
  public float getAlpha() {
    return this.mSurfaceView.getAlpha();
  }
  
  public float getCornerRadius() {
    return this.mSurfaceView.getCornerRadius();
  }
  
  public Point getPositionInWindow() {
    getLocationInWindow(this.mTmpArray);
    Point point = this.mWindowPosition;
    int[] arrayOfInt = this.mTmpArray;
    point.set(arrayOfInt[0], arrayOfInt[1]);
    return this.mWindowPosition;
  }
  
  public Rect getScreenBounds() {
    getBoundsOnScreen(this.mTmpRect);
    return this.mTmpRect;
  }
  
  public Matrix getScreenToTaskMatrix() {
    getLocationOnScreen(this.mTmpArray);
    this.mScreenSurfaceMatrix.set(getMatrix());
    Matrix matrix = this.mScreenSurfaceMatrix;
    int[] arrayOfInt = this.mTmpArray;
    matrix.postTranslate(arrayOfInt[0], arrayOfInt[1]);
    return this.mScreenSurfaceMatrix;
  }
  
  public boolean getSurfaceClipBounds(Rect paramRect) {
    return this.mSurfaceView.getClipBounds(paramRect);
  }
  
  public Region getTapExcludeRegion() {
    if (isAttachedToWindow() && canReceivePointerEvents()) {
      Point point = getPositionInWindow();
      this.mTapExcludeRegion.set(point.x, point.y, point.x + getWidth(), point.y + getHeight());
      ViewParent viewParent = getParent();
      if (viewParent != null)
        viewParent.subtractObscuredTouchableRegion(this.mTapExcludeRegion, (View)this); 
    } else {
      this.mTapExcludeRegion.setEmpty();
    } 
    return this.mTapExcludeRegion;
  }
  
  public VirtualDisplay getVirtualDisplay() {
    return this.mTaskEmbedder.getVirtualDisplay();
  }
  
  public int getVirtualDisplayId() {
    return this.mTaskEmbedder.getDisplayId();
  }
  
  public IWindow getWindow() {
    return super.getWindow();
  }
  
  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mSurfaceView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
  }
  
  public void onLocationChanged() {
    this.mTaskEmbedder.notifyBoundsChanged();
  }
  
  public void onTaskBackgroundColorChanged(TaskEmbedder paramTaskEmbedder, int paramInt) {
    SurfaceView surfaceView = this.mSurfaceView;
    if (surfaceView != null)
      surfaceView.setResizeBackgroundColor(paramInt); 
  }
  
  protected void onVisibilityChanged(View paramView, int paramInt) {
    super.onVisibilityChanged(paramView, paramInt);
    this.mSurfaceView.setVisibility(paramInt);
  }
  
  public void performBackPress() {
    this.mTaskEmbedder.performBackPress();
  }
  
  public void release() {
    performRelease();
  }
  
  public void setAlpha(float paramFloat) {
    super.setAlpha(paramFloat);
    SurfaceView surfaceView = this.mSurfaceView;
    if (surfaceView != null)
      surfaceView.setAlpha(paramFloat); 
  }
  
  public void setCallback(StateCallback paramStateCallback) {
    if (paramStateCallback == null) {
      this.mTaskEmbedder.setListener(null);
      return;
    } 
    this.mTaskEmbedder.setListener(new StateCallbackAdapter(paramStateCallback));
  }
  
  public void setCornerRadius(float paramFloat) {
    this.mSurfaceView.setCornerRadius(paramFloat);
  }
  
  public void setForwardedInsets(Insets paramInsets) {
    this.mTaskEmbedder.setForwardedInsets(paramInsets);
  }
  
  public void setSurfaceClipBounds(Rect paramRect) {
    this.mSurfaceView.setClipBounds(paramRect);
  }
  
  public void setSurfaceClippingEnabled(boolean paramBoolean) {
    this.mSurfaceView.setEnableSurfaceClipping(paramBoolean);
  }
  
  public void startActivity(PendingIntent paramPendingIntent) {
    this.mTaskEmbedder.startActivity(paramPendingIntent);
  }
  
  public void startActivity(PendingIntent paramPendingIntent, Intent paramIntent, ActivityOptions paramActivityOptions) {
    this.mTaskEmbedder.startActivity(paramPendingIntent, paramIntent, paramActivityOptions);
  }
  
  public void startActivity(Intent paramIntent) {
    this.mTaskEmbedder.startActivity(paramIntent);
  }
  
  public void startActivity(Intent paramIntent, UserHandle paramUserHandle) {
    this.mTaskEmbedder.startActivity(paramIntent, paramUserHandle);
  }
  
  public void startShortcutActivity(ShortcutInfo paramShortcutInfo, ActivityOptions paramActivityOptions, Rect paramRect) {
    this.mTaskEmbedder.startShortcutActivity(paramShortcutInfo, paramActivityOptions, paramRect);
  }
  
  protected boolean useTaskOrganizer() {
    return false;
  }
  
  public static abstract class StateCallback {
    public abstract void onActivityViewDestroyed(ActivityView param1ActivityView);
    
    public abstract void onActivityViewReady(ActivityView param1ActivityView);
    
    public void onBackPressedOnTaskRoot(int param1Int) {}
    
    public void onTaskCreated(int param1Int, ComponentName param1ComponentName) {}
    
    public void onTaskMovedToFront(int param1Int) {}
    
    public void onTaskRemovalStarted(int param1Int) {}
    
    public void onTaskVisibilityChanged(int param1Int, boolean param1Boolean) {}
  }
  
  private final class StateCallbackAdapter implements TaskEmbedder.Listener {
    private final ActivityView.StateCallback mCallback;
    
    private StateCallbackAdapter(ActivityView.StateCallback param1StateCallback) {
      this.mCallback = param1StateCallback;
    }
    
    public void onBackPressedOnTaskRoot(int param1Int) {
      this.mCallback.onBackPressedOnTaskRoot(param1Int);
    }
    
    public void onInitialized() {
      this.mCallback.onActivityViewReady(ActivityView.this);
    }
    
    public void onReleased() {
      this.mCallback.onActivityViewDestroyed(ActivityView.this);
    }
    
    public void onTaskCreated(int param1Int, ComponentName param1ComponentName) {
      this.mCallback.onTaskCreated(param1Int, param1ComponentName);
    }
    
    public void onTaskMovedToFront(int param1Int) {
      this.mCallback.onTaskMovedToFront(param1Int);
    }
    
    public void onTaskRemovalStarted(int param1Int) {
      this.mCallback.onTaskRemovalStarted(param1Int);
    }
    
    public void onTaskVisibilityChanged(int param1Int, boolean param1Boolean) {
      this.mCallback.onTaskVisibilityChanged(param1Int, param1Boolean);
    }
  }
  
  private class SurfaceCallback implements SurfaceHolder.Callback {
    private final DisplayInfo mTempDisplayInfo = new DisplayInfo();
    
    private final DisplayMetrics mTempMetrics = new DisplayMetrics();
    
    private SurfaceCallback() {}
    
    public void surfaceChanged(SurfaceHolder param1SurfaceHolder, int param1Int1, int param1Int2, int param1Int3) {
      if (!ActivityView.this.getVirtualDisplay().getDisplay().getDisplayInfo(this.mTempDisplayInfo))
        return; 
      this.mTempDisplayInfo.getAppMetrics(this.mTempMetrics);
      if (param1Int2 != this.mTempMetrics.widthPixels || param1Int3 != this.mTempMetrics.heightPixels) {
        ActivityView.this.mTaskEmbedder.resizeTask(param1Int2, param1Int3);
        ActivityView.this.mTaskEmbedder.notifyBoundsChanged();
      } 
    }
    
    public void surfaceCreated(SurfaceHolder param1SurfaceHolder) {
      if (!ActivityView.this.mTaskEmbedder.isInitialized()) {
        ActivityView activityView = ActivityView.this;
        activityView.initTaskEmbedder(activityView.mSurfaceView.getSurfaceControl());
      } else {
        ActivityView.this.mTmpTransaction.reparent(ActivityView.this.mTaskEmbedder.getSurfaceControl(), ActivityView.this.mSurfaceView.getSurfaceControl()).apply();
      } 
      ActivityView.this.mTaskEmbedder.resizeTask(ActivityView.this.getWidth(), ActivityView.this.getHeight());
      ActivityView.this.mTaskEmbedder.start();
    }
    
    public void surfaceDestroyed(SurfaceHolder param1SurfaceHolder) {
      ActivityView.this.mTaskEmbedder.stop();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaRouter;
import android.util.AttributeSet;
import android.view.View;
import com.android.internal.R;
import com.android.internal.app.MediaRouteDialogPresenter;

public class MediaRouteButton extends View {
  private static final int[] ACTIVATED_STATE_SET;
  
  private static final int[] CHECKED_STATE_SET = new int[] { 16842912 };
  
  private boolean mAttachedToWindow;
  
  private final MediaRouterCallback mCallback;
  
  private View.OnClickListener mExtendedSettingsClickListener;
  
  private boolean mIsConnecting;
  
  private int mMinHeight;
  
  private int mMinWidth;
  
  private boolean mRemoteActive;
  
  private Drawable mRemoteIndicator;
  
  private int mRouteTypes;
  
  private final MediaRouter mRouter;
  
  static {
    ACTIVATED_STATE_SET = new int[] { 16843518 };
  }
  
  public MediaRouteButton(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public MediaRouteButton(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 16843693);
  }
  
  public MediaRouteButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public MediaRouteButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    this.mRouter = (MediaRouter)paramContext.getSystemService("media_router");
    this.mCallback = new MediaRouterCallback();
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MediaRouteButton, paramInt1, paramInt2);
    setRemoteIndicatorDrawable(typedArray.getDrawable(3));
    this.mMinWidth = typedArray.getDimensionPixelSize(0, 0);
    this.mMinHeight = typedArray.getDimensionPixelSize(1, 0);
    paramInt1 = typedArray.getInteger(2, 1);
    typedArray.recycle();
    setClickable(true);
    setRouteTypes(paramInt1);
  }
  
  private Activity getActivity() {
    for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext()) {
      if (context instanceof Activity)
        return (Activity)context; 
    } 
    throw new IllegalStateException("The MediaRouteButton's Context is not an Activity.");
  }
  
  private void refreshRoute() {
    MediaRouter.RouteInfo routeInfo = this.mRouter.getSelectedRoute();
    boolean bool1 = routeInfo.isDefault();
    boolean bool2 = false;
    if (!bool1 && routeInfo.matchesTypes(this.mRouteTypes)) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    boolean bool3 = bool2;
    if (bool1) {
      bool3 = bool2;
      if (routeInfo.isConnecting())
        bool3 = true; 
    } 
    boolean bool = false;
    if (this.mRemoteActive != bool1) {
      this.mRemoteActive = bool1;
      bool = true;
    } 
    if (this.mIsConnecting != bool3) {
      this.mIsConnecting = bool3;
      bool = true;
    } 
    if (bool)
      refreshDrawableState(); 
    if (this.mAttachedToWindow)
      setEnabled(this.mRouter.isRouteAvailable(this.mRouteTypes, 1)); 
    Drawable drawable = this.mRemoteIndicator;
    if (drawable != null && drawable.getCurrent() instanceof AnimationDrawable) {
      AnimationDrawable animationDrawable = (AnimationDrawable)this.mRemoteIndicator.getCurrent();
      if (this.mAttachedToWindow) {
        if ((bool || bool3) && !animationDrawable.isRunning())
          animationDrawable.start(); 
      } else if (bool1 && !bool3) {
        if (animationDrawable.isRunning())
          animationDrawable.stop(); 
        animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
      } 
    } 
  }
  
  private void setRemoteIndicatorDrawable(Drawable paramDrawable) {
    Drawable drawable = this.mRemoteIndicator;
    if (drawable != null) {
      drawable.setCallback(null);
      unscheduleDrawable(this.mRemoteIndicator);
    } 
    this.mRemoteIndicator = paramDrawable;
    if (paramDrawable != null) {
      boolean bool;
      paramDrawable.setCallback((Drawable.Callback)this);
      paramDrawable.setState(getDrawableState());
      if (getVisibility() == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      paramDrawable.setVisible(bool, false);
    } 
    refreshDrawableState();
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    Drawable drawable = this.mRemoteIndicator;
    if (drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
      invalidateDrawable(drawable); 
  }
  
  public int getRouteTypes() {
    return this.mRouteTypes;
  }
  
  public void jumpDrawablesToCurrentState() {
    super.jumpDrawablesToCurrentState();
    Drawable drawable = this.mRemoteIndicator;
    if (drawable != null)
      drawable.jumpToCurrentState(); 
  }
  
  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mAttachedToWindow = true;
    int i = this.mRouteTypes;
    if (i != 0)
      this.mRouter.addCallback(i, (MediaRouter.Callback)this.mCallback, 8); 
    refreshRoute();
  }
  
  protected int[] onCreateDrawableState(int paramInt) {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (this.mIsConnecting) {
      mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
    } else if (this.mRemoteActive) {
      mergeDrawableStates(arrayOfInt, ACTIVATED_STATE_SET);
    } 
    return arrayOfInt;
  }
  
  public void onDetachedFromWindow() {
    this.mAttachedToWindow = false;
    if (this.mRouteTypes != 0)
      this.mRouter.removeCallback((MediaRouter.Callback)this.mCallback); 
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.mRemoteIndicator == null)
      return; 
    int i = getPaddingLeft();
    int j = getWidth();
    int k = getPaddingRight();
    int m = getPaddingTop();
    int n = getHeight();
    int i1 = getPaddingBottom();
    int i2 = this.mRemoteIndicator.getIntrinsicWidth();
    int i3 = this.mRemoteIndicator.getIntrinsicHeight();
    k = (j - k - i - i2) / 2 + i;
    n = (n - i1 - m - i3) / 2 + m;
    this.mRemoteIndicator.setBounds(k, n, k + i2, n + i3);
    this.mRemoteIndicator.draw(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt2);
    int k = View.MeasureSpec.getMode(paramInt1);
    int m = View.MeasureSpec.getMode(paramInt2);
    int n = this.mMinWidth;
    Drawable drawable = this.mRemoteIndicator;
    paramInt2 = 0;
    if (drawable != null) {
      paramInt1 = drawable.getIntrinsicWidth() + getPaddingLeft() + getPaddingRight();
    } else {
      paramInt1 = 0;
    } 
    n = Math.max(n, paramInt1);
    int i1 = this.mMinHeight;
    drawable = this.mRemoteIndicator;
    if (drawable != null) {
      paramInt1 = drawable.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom();
    } else {
      paramInt1 = paramInt2;
    } 
    paramInt2 = Math.max(i1, paramInt1);
    if (k != Integer.MIN_VALUE) {
      if (k != 1073741824) {
        paramInt1 = n;
      } else {
        paramInt1 = i;
      } 
    } else {
      paramInt1 = Math.min(i, n);
    } 
    if (m != Integer.MIN_VALUE) {
      if (m == 1073741824)
        paramInt2 = j; 
    } else {
      paramInt2 = Math.min(j, paramInt2);
    } 
    setMeasuredDimension(paramInt1, paramInt2);
  }
  
  public boolean performClick() {
    boolean bool = super.performClick();
    boolean bool1 = false;
    if (!bool)
      playSoundEffect(0); 
    if (showDialogInternal() || bool)
      bool1 = true; 
    return bool1;
  }
  
  public void setContentDescription(CharSequence paramCharSequence) {
    super.setContentDescription(paramCharSequence);
    setTooltipText(paramCharSequence);
  }
  
  public void setExtendedSettingsClickListener(View.OnClickListener paramOnClickListener) {
    this.mExtendedSettingsClickListener = paramOnClickListener;
  }
  
  public void setRouteTypes(int paramInt) {
    int i = this.mRouteTypes;
    if (i != paramInt) {
      if (this.mAttachedToWindow && i != 0)
        this.mRouter.removeCallback((MediaRouter.Callback)this.mCallback); 
      this.mRouteTypes = paramInt;
      if (this.mAttachedToWindow && paramInt != 0)
        this.mRouter.addCallback(paramInt, (MediaRouter.Callback)this.mCallback, 8); 
      refreshRoute();
    } 
  }
  
  public void setVisibility(int paramInt) {
    super.setVisibility(paramInt);
    Drawable drawable = this.mRemoteIndicator;
    if (drawable != null) {
      boolean bool;
      if (getVisibility() == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      drawable.setVisible(bool, false);
    } 
  }
  
  public void showDialog() {
    showDialogInternal();
  }
  
  boolean showDialogInternal() {
    boolean bool = this.mAttachedToWindow;
    boolean bool1 = false;
    if (!bool)
      return false; 
    if (MediaRouteDialogPresenter.showDialogFragment(getActivity(), this.mRouteTypes, this.mExtendedSettingsClickListener) != null)
      bool1 = true; 
    return bool1;
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable) {
    return (super.verifyDrawable(paramDrawable) || paramDrawable == this.mRemoteIndicator);
  }
  
  private final class MediaRouterCallback extends MediaRouter.SimpleCallback {
    private MediaRouterCallback() {}
    
    public void onRouteAdded(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteChanged(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteGrouped(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo, MediaRouter.RouteGroup param1RouteGroup, int param1Int) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteRemoved(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteSelected(MediaRouter param1MediaRouter, int param1Int, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteUngrouped(MediaRouter param1MediaRouter, MediaRouter.RouteInfo param1RouteInfo, MediaRouter.RouteGroup param1RouteGroup) {
      MediaRouteButton.this.refreshRoute();
    }
    
    public void onRouteUnselected(MediaRouter param1MediaRouter, int param1Int, MediaRouter.RouteInfo param1RouteInfo) {
      MediaRouteButton.this.refreshRoute();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/MediaRouteButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
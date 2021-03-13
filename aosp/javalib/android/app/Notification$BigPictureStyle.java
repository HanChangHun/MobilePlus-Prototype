package android.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.RemoteViews;

public class BigPictureStyle extends Notification.Style {
  public static final int MIN_ASHMEM_BITMAP_SIZE = 131072;
  
  private Icon mBigLargeIcon;
  
  private boolean mBigLargeIconSet = false;
  
  private Bitmap mPicture;
  
  public BigPictureStyle() {}
  
  @Deprecated
  public BigPictureStyle(Notification.Builder paramBuilder) {
    setBuilder(paramBuilder);
  }
  
  private static boolean areBitmapsObviouslyDifferent(Bitmap paramBitmap1, Bitmap paramBitmap2) {
    boolean bool = false;
    if (paramBitmap1 == paramBitmap2)
      return false; 
    if (paramBitmap1 == null || paramBitmap2 == null)
      return true; 
    if (paramBitmap1.getWidth() != paramBitmap2.getWidth() || paramBitmap1.getHeight() != paramBitmap2.getHeight() || paramBitmap1.getConfig() != paramBitmap2.getConfig() || paramBitmap1.getGenerationId() != paramBitmap2.getGenerationId())
      bool = true; 
    return bool;
  }
  
  public void addExtras(Bundle paramBundle) {
    super.addExtras(paramBundle);
    if (this.mBigLargeIconSet)
      paramBundle.putParcelable("android.largeIcon.big", (Parcelable)this.mBigLargeIcon); 
    paramBundle.putParcelable("android.picture", (Parcelable)this.mPicture);
  }
  
  public boolean areNotificationsVisiblyDifferent(Notification.Style paramStyle) {
    if (paramStyle == null || getClass() != paramStyle.getClass())
      return true; 
    paramStyle = paramStyle;
    return areBitmapsObviouslyDifferent(getBigPicture(), paramStyle.getBigPicture());
  }
  
  public BigPictureStyle bigLargeIcon(Bitmap paramBitmap) {
    if (paramBitmap != null) {
      Icon icon = Icon.createWithBitmap(paramBitmap);
    } else {
      paramBitmap = null;
    } 
    return bigLargeIcon((Icon)paramBitmap);
  }
  
  public BigPictureStyle bigLargeIcon(Icon paramIcon) {
    this.mBigLargeIconSet = true;
    this.mBigLargeIcon = paramIcon;
    return this;
  }
  
  public BigPictureStyle bigPicture(Bitmap paramBitmap) {
    this.mPicture = paramBitmap;
    return this;
  }
  
  public Bitmap getBigPicture() {
    return this.mPicture;
  }
  
  public boolean hasSummaryInHeader() {
    return false;
  }
  
  public RemoteViews makeBigContentView() {
    Icon icon = null;
    Bitmap bitmap = null;
    if (this.mBigLargeIconSet) {
      icon = Notification.access$1400(Notification.Builder.access$400(this.mBuilder));
      Notification.access$1402(Notification.Builder.access$400(this.mBuilder), this.mBigLargeIcon);
      bitmap = (Notification.Builder.access$400(this.mBuilder)).largeIcon;
      (Notification.Builder.access$400(this.mBuilder)).largeIcon = null;
    } 
    Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().fillTextsFrom(this.mBuilder);
    RemoteViews remoteViews = getStandardView(Notification.Builder.access$2500(this.mBuilder), standardTemplateParams, null);
    if (this.mSummaryTextSet) {
      remoteViews.setTextViewText(16909502, Notification.Builder.access$2700(this.mBuilder, Notification.Builder.access$2600(this.mBuilder, this.mSummaryText)));
      Notification.Builder.access$2800(this.mBuilder, remoteViews, 16909502, standardTemplateParams);
      remoteViews.setViewVisibility(16909502, 0);
    } 
    this.mBuilder.setContentMinHeight(remoteViews, Notification.access$1900(Notification.Builder.access$400(this.mBuilder)));
    if (this.mBigLargeIconSet) {
      Notification.access$1402(Notification.Builder.access$400(this.mBuilder), icon);
      (Notification.Builder.access$400(this.mBuilder)).largeIcon = bitmap;
    } 
    remoteViews.setImageViewBitmap(16908790, this.mPicture);
    return remoteViews;
  }
  
  public void purgeResources() {
    super.purgeResources();
    Bitmap bitmap = this.mPicture;
    if (bitmap != null && bitmap.isMutable() && this.mPicture.getAllocationByteCount() >= 131072)
      this.mPicture = this.mPicture.createAshmemBitmap(); 
    Icon icon = this.mBigLargeIcon;
    if (icon != null)
      icon.convertToAshmem(); 
  }
  
  public void reduceImageSizes(Context paramContext) {
    super.reduceImageSizes(paramContext);
    Resources resources = paramContext.getResources();
    boolean bool = ActivityManager.isLowRamDeviceStatic();
    if (this.mPicture != null) {
      if (bool) {
        i = 17105340;
      } else {
        i = 17105339;
      } 
      int j = resources.getDimensionPixelSize(i);
      if (bool) {
        i = 17105342;
      } else {
        i = 17105341;
      } 
      int i = resources.getDimensionPixelSize(i);
      this.mPicture = Icon.scaleDownIfNecessary(this.mPicture, j, i);
    } 
    if (this.mBigLargeIcon != null) {
      if (bool) {
        i = 17105380;
      } else {
        i = 17105379;
      } 
      int i = resources.getDimensionPixelSize(i);
      this.mBigLargeIcon.scaleDownIfNecessary(i, i);
    } 
  }
  
  protected void restoreFromExtras(Bundle paramBundle) {
    super.restoreFromExtras(paramBundle);
    if (paramBundle.containsKey("android.largeIcon.big")) {
      this.mBigLargeIconSet = true;
      this.mBigLargeIcon = (Icon)paramBundle.getParcelable("android.largeIcon.big");
    } 
    this.mPicture = (Bitmap)paramBundle.getParcelable("android.picture");
  }
  
  public BigPictureStyle setBigContentTitle(CharSequence paramCharSequence) {
    internalSetBigContentTitle(Notification.safeCharSequence(paramCharSequence));
    return this;
  }
  
  public BigPictureStyle setSummaryText(CharSequence paramCharSequence) {
    internalSetSummaryText(Notification.safeCharSequence(paramCharSequence));
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$BigPictureStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
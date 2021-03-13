package android.app;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.RemoteViews;
import com.android.internal.util.ContrastColorUtil;

public class MediaStyle extends Notification.Style {
  static final int MAX_MEDIA_BUTTONS = 5;
  
  static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
  
  private static final int[] MEDIA_BUTTON_IDS = new int[] { 16908696, 16908697, 16908698, 16908699, 16908700 };
  
  private int[] mActionsToShowInCompact = null;
  
  private MediaSession.Token mToken;
  
  public MediaStyle() {}
  
  @Deprecated
  public MediaStyle(Notification.Builder paramBuilder) {
    setBuilder(paramBuilder);
  }
  
  private void bindMediaActionButton(RemoteViews paramRemoteViews, int paramInt, Notification.Action paramAction, Notification.StandardTemplateParams paramStandardTemplateParams) {
    boolean bool1;
    boolean bool2;
    int i;
    if (paramAction.actionIntent == null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    paramRemoteViews.setViewVisibility(paramInt, 0);
    if (paramInt != 16909158)
      paramRemoteViews.setImageViewIcon(paramInt, paramAction.getIcon()); 
    if (((Notification.Builder.access$3500(this.mBuilder).getResources().getConfiguration()).uiMode & 0x30) == 32) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (Notification.Builder.access$4300(this.mBuilder) || Notification.Builder.access$4000(this.mBuilder, paramStandardTemplateParams)) {
      i = getActionColor(paramStandardTemplateParams);
    } else {
      i = ContrastColorUtil.resolveColor(Notification.Builder.access$3500(this.mBuilder), 0, bool2);
    } 
    paramRemoteViews.setDrawableTint(paramInt, false, i, PorterDuff.Mode.SRC_ATOP);
    TypedArray typedArray = Notification.Builder.access$3500(this.mBuilder).obtainStyledAttributes(new int[] { 16843820 });
    int j = Color.alpha(typedArray.getColor(0, 0));
    typedArray.recycle();
    paramRemoteViews.setRippleDrawableColor(paramInt, ColorStateList.valueOf(Color.argb(j, Color.red(i), Color.green(i), Color.blue(i))));
    if (!bool1)
      paramRemoteViews.setOnClickPendingIntent(paramInt, paramAction.actionIntent); 
    paramRemoteViews.setContentDescription(paramInt, paramAction.title);
  }
  
  private int getActionColor(Notification.StandardTemplateParams paramStandardTemplateParams) {
    int i;
    if (Notification.Builder.access$4000(this.mBuilder, paramStandardTemplateParams)) {
      i = this.mBuilder.getPrimaryTextColor(paramStandardTemplateParams);
    } else {
      i = this.mBuilder.resolveContrastColor(paramStandardTemplateParams);
    } 
    return i;
  }
  
  private void handleImage(RemoteViews paramRemoteViews) {
    if (Notification.access$1900(Notification.Builder.access$400(this.mBuilder))) {
      paramRemoteViews.setViewLayoutMarginEndDimen(16909124, 0);
      paramRemoteViews.setViewLayoutMarginEndDimen(16909502, 0);
    } 
  }
  
  private RemoteViews makeMediaBigContentView() {
    int i = Math.min(Notification.Builder.access$3000(this.mBuilder).size(), 5);
    int[] arrayOfInt = this.mActionsToShowInCompact;
    if (arrayOfInt == null) {
      j = 0;
    } else {
      j = Math.min(arrayOfInt.length, 3);
    } 
    if (!Notification.access$1900(Notification.Builder.access$400(this.mBuilder)) && i <= j)
      return null; 
    Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().hasProgress(false).fillTextsFrom(this.mBuilder);
    RemoteViews remoteViews = Notification.Builder.access$4400(this.mBuilder, 17367210, standardTemplateParams, null);
    for (int j = 0; j < 5; j++) {
      if (j < i) {
        bindMediaActionButton(remoteViews, MEDIA_BUTTON_IDS[j], Notification.Builder.access$3000(this.mBuilder).get(j), standardTemplateParams);
      } else {
        remoteViews.setViewVisibility(MEDIA_BUTTON_IDS[j], 8);
      } 
    } 
    bindMediaActionButton(remoteViews, 16909158, new Notification.Action(17302678, Notification.Builder.access$3500(this.mBuilder).getString(17040121), null), standardTemplateParams);
    remoteViews.setViewVisibility(16909158, 8);
    handleImage(remoteViews);
    return remoteViews;
  }
  
  private RemoteViews makeMediaContentView() {
    int j;
    Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().hasProgress(false).fillTextsFrom(this.mBuilder);
    RemoteViews remoteViews = Notification.Builder.access$4400(this.mBuilder, 17367215, standardTemplateParams, null);
    int i = Notification.Builder.access$3000(this.mBuilder).size();
    int[] arrayOfInt = this.mActionsToShowInCompact;
    if (arrayOfInt == null) {
      j = 0;
    } else {
      j = Math.min(arrayOfInt.length, 3);
    } 
    if (j <= i) {
      for (i = 0; i < 3; i++) {
        if (i < j) {
          Notification.Action action = Notification.Builder.access$3000(this.mBuilder).get(this.mActionsToShowInCompact[i]);
          bindMediaActionButton(remoteViews, MEDIA_BUTTON_IDS[i], action, standardTemplateParams);
        } else {
          remoteViews.setViewVisibility(MEDIA_BUTTON_IDS[i], 8);
        } 
      } 
      bindMediaActionButton(remoteViews, 16909158, new Notification.Action(17302678, Notification.Builder.access$3500(this.mBuilder).getString(17040121), null), standardTemplateParams);
      remoteViews.setViewVisibility(16909158, 8);
      handleImage(remoteViews);
      j = 17105345;
      if (Notification.access$1900(Notification.Builder.access$400(this.mBuilder)))
        j = 17105368; 
      remoteViews.setViewLayoutMarginEndDimen(16909225, j);
      return remoteViews;
    } 
    throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[] { Integer.valueOf(i), Integer.valueOf(i - 1) }));
  }
  
  public void addExtras(Bundle paramBundle) {
    super.addExtras(paramBundle);
    MediaSession.Token token = this.mToken;
    if (token != null)
      paramBundle.putParcelable("android.mediaSession", (Parcelable)token); 
    int[] arrayOfInt = this.mActionsToShowInCompact;
    if (arrayOfInt != null)
      paramBundle.putIntArray("android.compactActions", arrayOfInt); 
  }
  
  public boolean areNotificationsVisiblyDifferent(Notification.Style paramStyle) {
    return (paramStyle == null || getClass() != paramStyle.getClass());
  }
  
  public Notification buildStyled(Notification paramNotification) {
    super.buildStyled(paramNotification);
    if (paramNotification.category == null)
      paramNotification.category = "transport"; 
    return paramNotification;
  }
  
  protected boolean hasProgress() {
    return false;
  }
  
  public RemoteViews makeBigContentView() {
    return makeMediaBigContentView();
  }
  
  public RemoteViews makeContentView(boolean paramBoolean) {
    return makeMediaContentView();
  }
  
  public RemoteViews makeHeadsUpContentView(boolean paramBoolean) {
    return makeMediaContentView();
  }
  
  protected void restoreFromExtras(Bundle paramBundle) {
    super.restoreFromExtras(paramBundle);
    if (paramBundle.containsKey("android.mediaSession"))
      this.mToken = (MediaSession.Token)paramBundle.getParcelable("android.mediaSession"); 
    if (paramBundle.containsKey("android.compactActions"))
      this.mActionsToShowInCompact = paramBundle.getIntArray("android.compactActions"); 
  }
  
  public MediaStyle setMediaSession(MediaSession.Token paramToken) {
    this.mToken = paramToken;
    return this;
  }
  
  public MediaStyle setShowActionsInCompactView(int... paramVarArgs) {
    this.mActionsToShowInCompact = paramVarArgs;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$MediaStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
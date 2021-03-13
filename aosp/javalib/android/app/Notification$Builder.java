package android.app;

import android.content.Context;
import android.content.LocusId;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.media.PlayerBase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.internal.util.ContrastColorUtil;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Builder {
  public static final String EXTRA_REBUILD_BIG_CONTENT_VIEW_ACTION_COUNT = "android.rebuild.bigViewActionCount";
  
  public static final String EXTRA_REBUILD_CONTENT_VIEW_ACTION_COUNT = "android.rebuild.contentViewActionCount";
  
  public static final String EXTRA_REBUILD_HEADS_UP_CONTENT_VIEW_ACTION_COUNT = "android.rebuild.hudViewActionCount";
  
  private static final int LIGHTNESS_TEXT_DIFFERENCE_DARK = -10;
  
  private static final int LIGHTNESS_TEXT_DIFFERENCE_LIGHT = 20;
  
  private static final boolean USE_ONLY_TITLE_IN_LOW_PRIORITY_SUMMARY = SystemProperties.getBoolean("notifications.only_title", true);
  
  private ArrayList<Notification.Action> mActions = new ArrayList<>(3);
  
  private int mBackgroundColor = 1;
  
  private int mCachedContrastColor = 1;
  
  private int mCachedContrastColorIsFor = 1;
  
  private ContrastColorUtil mColorUtil;
  
  private Context mContext;
  
  private int mForegroundColor = 1;
  
  private boolean mInNightMode;
  
  private boolean mIsLegacy;
  
  private boolean mIsLegacyInitialized;
  
  private Notification mN;
  
  private int mNeutralColor = 1;
  
  private ArrayList<Notification.Action> mOriginalActions;
  
  Notification.StandardTemplateParams mParams = new Notification.StandardTemplateParams(null);
  
  private ArrayList<Person> mPersonList = new ArrayList<>();
  
  private int mPrimaryTextColor = 1;
  
  private boolean mRebuildStyledRemoteViews;
  
  private int mSecondaryTextColor = 1;
  
  private Notification.Style mStyle;
  
  private int mTextColorsAreForBackground = 1;
  
  private boolean mTintActionButtons;
  
  private Bundle mUserExtras = new Bundle();
  
  @Deprecated
  public Builder(Context paramContext) {
    this(paramContext, (Notification)null);
  }
  
  public Builder(Context paramContext, Notification paramNotification) {
    this.mContext = paramContext;
    Resources resources = paramContext.getResources();
    this.mTintActionButtons = resources.getBoolean(17891562);
    if (resources.getBoolean(17891450)) {
      boolean bool;
      if (((resources.getConfiguration()).uiMode & 0x30) == 32) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mInNightMode = bool;
    } 
    if (paramNotification == null) {
      this.mN = new Notification();
      if ((paramContext.getApplicationInfo()).targetSdkVersion < 24)
        this.mN.extras.putBoolean("android.showWhen", true); 
      this.mN.priority = 0;
      this.mN.visibility = 0;
    } else {
      this.mN = paramNotification;
      if (paramNotification.actions != null)
        Collections.addAll(this.mActions, this.mN.actions); 
      if (this.mN.extras.containsKey("android.people.list")) {
        ArrayList<? extends Person> arrayList = this.mN.extras.getParcelableArrayList("android.people.list");
        this.mPersonList.addAll(arrayList);
      } 
      if (this.mN.getSmallIcon() == null && this.mN.icon != 0)
        setSmallIcon(this.mN.icon); 
      if (this.mN.getLargeIcon() == null && this.mN.largeIcon != null)
        setLargeIcon(this.mN.largeIcon); 
      String str = this.mN.extras.getString("android.template");
      if (!TextUtils.isEmpty(str)) {
        StringBuilder stringBuilder;
        Class<? extends Notification.Style> clazz = Notification.getNotificationStyleClass(str);
        if (clazz == null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown style class: ");
          stringBuilder.append(str);
          Log.d("Notification", stringBuilder.toString());
        } else {
          try {
            Constructor<Notification.Style> constructor = stringBuilder.getDeclaredConstructor(new Class[0]);
            constructor.setAccessible(true);
            Notification.Style style = constructor.newInstance(new Object[0]);
            style.restoreFromExtras(this.mN.extras);
          } finally {
            str = null;
          } 
        } 
      } 
    } 
  }
  
  public Builder(Context paramContext, String paramString) {
    this(paramContext, (Notification)null);
    Notification.access$602(this.mN, paramString);
  }
  
  private RemoteViews applyStandardTemplate(int paramInt, Notification.StandardTemplateParams paramStandardTemplateParams, Notification.TemplateBindResult paramTemplateBindResult) {
    Notification.BuilderRemoteViews builderRemoteViews = new Notification.BuilderRemoteViews(this.mContext.getApplicationInfo(), paramInt);
    resetStandardTemplate(builderRemoteViews);
    Bundle bundle = this.mN.extras;
    updateBackgroundColor(builderRemoteViews, paramStandardTemplateParams);
    bindNotificationHeader(builderRemoteViews, paramStandardTemplateParams);
    bindLargeIconAndReply(builderRemoteViews, paramStandardTemplateParams, paramTemplateBindResult);
    boolean bool = handleProgressBar(builderRemoteViews, bundle, paramStandardTemplateParams);
    CharSequence charSequence = paramStandardTemplateParams.title;
    boolean bool1 = false;
    if (charSequence != null) {
      builderRemoteViews.setViewVisibility(16908310, 0);
      builderRemoteViews.setTextViewText(16908310, processTextSpans(paramStandardTemplateParams.title));
      setTextViewColorPrimary(builderRemoteViews, 16908310, paramStandardTemplateParams);
      if (bool) {
        paramInt = -2;
      } else {
        paramInt = -1;
      } 
      builderRemoteViews.setViewLayoutWidth(16908310, paramInt);
    } 
    if (paramStandardTemplateParams.text != null) {
      if (bool) {
        paramInt = 16909530;
      } else {
        paramInt = 16909502;
      } 
      builderRemoteViews.setTextViewText(paramInt, processTextSpans(paramStandardTemplateParams.text));
      setTextViewColorSecondary(builderRemoteViews, paramInt, paramStandardTemplateParams);
      builderRemoteViews.setViewVisibility(paramInt, 0);
    } 
    if (bool || Notification.access$1900(this.mN))
      bool1 = true; 
    setContentMinHeight(builderRemoteViews, bool1);
    return builderRemoteViews;
  }
  
  private RemoteViews applyStandardTemplate(int paramInt, Notification.TemplateBindResult paramTemplateBindResult) {
    return applyStandardTemplate(paramInt, this.mParams.reset().fillTextsFrom(this), paramTemplateBindResult);
  }
  
  private RemoteViews applyStandardTemplateWithActions(int paramInt, Notification.StandardTemplateParams paramStandardTemplateParams, Notification.TemplateBindResult paramTemplateBindResult) {
    int i;
    boolean bool;
    RemoteViews remoteViews = applyStandardTemplate(paramInt, paramStandardTemplateParams, paramTemplateBindResult);
    resetStandardTemplateWithActions(remoteViews);
    int j = 0;
    paramInt = 0;
    List<Notification.Action> list = filterOutContextualActions(this.mActions);
    int k = list.size();
    if (this.mN.fullScreenIntent != null) {
      bool = true;
    } else {
      bool = false;
    } 
    remoteViews.setBoolean(16908721, "setEmphasizedMode", bool);
    byte b = 8;
    if (k > 0) {
      remoteViews.setViewVisibility(16908722, 0);
      remoteViews.setViewVisibility(16908721, 0);
      remoteViews.setViewLayoutMarginBottomDimen(16909221, 0);
      j = k;
      if (k > 3)
        j = 3; 
      for (k = 0; k < j; k++) {
        Notification.Action action = list.get(k);
        int m = hasValidRemoteInput(action);
        i = paramInt | m;
        RemoteViews remoteViews1 = generateActionButton(action, bool, paramStandardTemplateParams);
        if (m != 0 && !bool)
          remoteViews1.setInt(16908696, "setBackgroundResource", 0); 
        remoteViews.addView(16908721, remoteViews1);
      } 
    } else {
      remoteViews.setViewVisibility(16908722, 8);
      i = j;
    } 
    RemoteInputHistoryItem[] arrayOfRemoteInputHistoryItem = (RemoteInputHistoryItem[])Notification.access$000(this.mN.extras, "android.remoteInputHistoryItems", RemoteInputHistoryItem.class);
    if (i != 0 && arrayOfRemoteInputHistoryItem != null && arrayOfRemoteInputHistoryItem.length > 0 && !TextUtils.isEmpty(arrayOfRemoteInputHistoryItem[0].getText()) && paramStandardTemplateParams.maxRemoteInputHistory > 0) {
      bool = this.mN.extras.getBoolean("android.remoteInputSpinner");
      remoteViews.setViewVisibility(16909226, 0);
      remoteViews.setViewVisibility(16909229, 0);
      remoteViews.setTextViewText(16909228, processTextSpans(arrayOfRemoteInputHistoryItem[0].getText()));
      setTextViewColorSecondary(remoteViews, 16909228, paramStandardTemplateParams);
      i = b;
      if (bool)
        i = 0; 
      remoteViews.setViewVisibility(16909227, i);
      if (isColorized(paramStandardTemplateParams)) {
        i = getPrimaryTextColor(paramStandardTemplateParams);
      } else {
        i = resolveContrastColor(paramStandardTemplateParams);
      } 
      remoteViews.setProgressIndeterminateTintList(16909227, ColorStateList.valueOf(i));
      if (arrayOfRemoteInputHistoryItem.length > 1 && !TextUtils.isEmpty(arrayOfRemoteInputHistoryItem[1].getText()) && paramStandardTemplateParams.maxRemoteInputHistory > 1) {
        remoteViews.setViewVisibility(16909230, 0);
        remoteViews.setTextViewText(16909230, processTextSpans(arrayOfRemoteInputHistoryItem[1].getText()));
        setTextViewColorSecondary(remoteViews, 16909230, paramStandardTemplateParams);
        if (arrayOfRemoteInputHistoryItem.length > 2 && !TextUtils.isEmpty(arrayOfRemoteInputHistoryItem[2].getText()) && paramStandardTemplateParams.maxRemoteInputHistory > 2) {
          remoteViews.setViewVisibility(16909231, 0);
          remoteViews.setTextViewText(16909231, processTextSpans(arrayOfRemoteInputHistoryItem[2].getText()));
          setTextViewColorSecondary(remoteViews, 16909231, paramStandardTemplateParams);
        } 
      } 
    } 
    return remoteViews;
  }
  
  private RemoteViews applyStandardTemplateWithActions(int paramInt, Notification.TemplateBindResult paramTemplateBindResult) {
    return applyStandardTemplateWithActions(paramInt, this.mParams.reset().fillTextsFrom(this), paramTemplateBindResult);
  }
  
  private void bindActivePermissions(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    int i = getNeutralColor(paramStandardTemplateParams);
    paramRemoteViews.setDrawableTint(16908823, false, i, PorterDuff.Mode.SRC_ATOP);
    paramRemoteViews.setDrawableTint(16909171, false, i, PorterDuff.Mode.SRC_ATOP);
    paramRemoteViews.setDrawableTint(16909267, false, i, PorterDuff.Mode.SRC_ATOP);
  }
  
  private void bindAlertedIcon(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    paramRemoteViews.setDrawableTint(16908740, false, getNeutralColor(paramStandardTemplateParams), PorterDuff.Mode.SRC_ATOP);
  }
  
  private void bindExpandButton(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    int i;
    if (isColorized(paramStandardTemplateParams)) {
      i = getPrimaryTextColor(paramStandardTemplateParams);
    } else {
      i = getSecondaryTextColor(paramStandardTemplateParams);
    } 
    paramRemoteViews.setDrawableTint(16908949, false, i, PorterDuff.Mode.SRC_ATOP);
    paramRemoteViews.setInt(16908949, "setOriginalNotificationColor", i);
  }
  
  private void bindHeaderAppName(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    paramRemoteViews.setTextViewText(16908761, loadHeaderAppName());
    if (isColorized(paramStandardTemplateParams)) {
      setTextViewColorPrimary(paramRemoteViews, 16908761, paramStandardTemplateParams);
    } else {
      paramRemoteViews.setTextColor(16908761, getSecondaryTextColor(paramStandardTemplateParams));
    } 
  }
  
  private void bindHeaderChronometerAndTime(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    if (showsTimeOrChronometer()) {
      paramRemoteViews.setViewVisibility(16909537, 0);
      setTextViewColorSecondary(paramRemoteViews, 16909537, paramStandardTemplateParams);
      if (this.mN.extras.getBoolean("android.showChronometer")) {
        paramRemoteViews.setViewVisibility(16908842, 0);
        paramRemoteViews.setLong(16908842, "setBase", this.mN.when + SystemClock.elapsedRealtime() - System.currentTimeMillis());
        paramRemoteViews.setBoolean(16908842, "setStarted", true);
        paramRemoteViews.setChronometerCountDown(16908842, this.mN.extras.getBoolean("android.chronometerCountDown"));
        setTextViewColorSecondary(paramRemoteViews, 16908842, paramStandardTemplateParams);
      } else {
        paramRemoteViews.setViewVisibility(16909533, 0);
        paramRemoteViews.setLong(16909533, "setTime", this.mN.when);
        setTextViewColorSecondary(paramRemoteViews, 16909533, paramStandardTemplateParams);
      } 
    } else {
      long l;
      if (this.mN.when != 0L) {
        l = this.mN.when;
      } else {
        l = Notification.access$2100(this.mN);
      } 
      paramRemoteViews.setLong(16909533, "setTime", l);
    } 
  }
  
  private void bindHeaderText(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    CharSequence charSequence1 = paramStandardTemplateParams.summaryText;
    CharSequence charSequence2 = charSequence1;
    if (charSequence1 == null) {
      Notification.Style style = this.mStyle;
      charSequence2 = charSequence1;
      if (style != null) {
        charSequence2 = charSequence1;
        if (style.mSummaryTextSet) {
          charSequence2 = charSequence1;
          if (this.mStyle.hasSummaryInHeader())
            charSequence2 = this.mStyle.mSummaryText; 
        } 
      } 
    } 
    charSequence1 = charSequence2;
    if (charSequence2 == null) {
      charSequence1 = charSequence2;
      if ((this.mContext.getApplicationInfo()).targetSdkVersion < 24) {
        charSequence1 = charSequence2;
        if (this.mN.extras.getCharSequence("android.infoText") != null)
          charSequence1 = this.mN.extras.getCharSequence("android.infoText"); 
      } 
    } 
    if (charSequence1 != null) {
      paramRemoteViews.setTextViewText(16909033, processTextSpans(processLegacyText(charSequence1)));
      setTextViewColorSecondary(paramRemoteViews, 16909033, paramStandardTemplateParams);
      paramRemoteViews.setViewVisibility(16909033, 0);
      paramRemoteViews.setViewVisibility(16909034, 0);
      setTextViewColorSecondary(paramRemoteViews, 16909034, paramStandardTemplateParams);
    } 
  }
  
  private void bindHeaderTextSecondary(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    if (!TextUtils.isEmpty(paramStandardTemplateParams.headerTextSecondary)) {
      paramRemoteViews.setTextViewText(16909035, processTextSpans(processLegacyText(paramStandardTemplateParams.headerTextSecondary)));
      setTextViewColorSecondary(paramRemoteViews, 16909035, paramStandardTemplateParams);
      paramRemoteViews.setViewVisibility(16909035, 0);
      paramRemoteViews.setViewVisibility(16909036, 0);
      setTextViewColorSecondary(paramRemoteViews, 16909036, paramStandardTemplateParams);
    } 
  }
  
  private boolean bindLargeIcon(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    boolean bool;
    if (Notification.access$1400(this.mN) == null && this.mN.largeIcon != null) {
      Notification notification = this.mN;
      Notification.access$1402(notification, Icon.createWithBitmap(notification.largeIcon));
    } 
    if (Notification.access$1400(this.mN) != null && !paramStandardTemplateParams.hideLargeIcon) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      paramRemoteViews.setViewVisibility(16909366, 0);
      paramRemoteViews.setImageViewIcon(16909366, Notification.access$1400(this.mN));
      processLargeLegacyIcon(Notification.access$1400(this.mN), paramRemoteViews, paramStandardTemplateParams);
    } 
    return bool;
  }
  
  private void bindLargeIconAndReply(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams, Notification.TemplateBindResult paramTemplateBindResult) {
    boolean bool;
    boolean bool1 = bindLargeIcon(paramRemoteViews, paramStandardTemplateParams);
    boolean bool2 = bindReplyIcon(paramRemoteViews, paramStandardTemplateParams);
    int i = 0;
    if (bool1 || bool2) {
      bool = true;
    } else {
      bool = false;
    } 
    if (!bool)
      i = 8; 
    paramRemoteViews.setViewVisibility(16909367, i);
    i = calculateMarginEnd(bool1, bool2);
    paramRemoteViews.setViewLayoutMarginEnd(16909124, i);
    paramRemoteViews.setViewLayoutMarginEnd(16909502, i);
    paramRemoteViews.setViewLayoutMarginEnd(16908301, i);
    if (paramTemplateBindResult != null) {
      paramTemplateBindResult.setIconMarginEnd(i);
      paramTemplateBindResult.setRightIconContainerVisible(bool);
    } 
  }
  
  private void bindNotificationHeader(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    bindSmallIcon(paramRemoteViews, paramStandardTemplateParams);
    bindHeaderAppName(paramRemoteViews, paramStandardTemplateParams);
    bindHeaderText(paramRemoteViews, paramStandardTemplateParams);
    bindHeaderTextSecondary(paramRemoteViews, paramStandardTemplateParams);
    bindHeaderChronometerAndTime(paramRemoteViews, paramStandardTemplateParams);
    bindProfileBadge(paramRemoteViews, paramStandardTemplateParams);
    bindAlertedIcon(paramRemoteViews, paramStandardTemplateParams);
    bindActivePermissions(paramRemoteViews, paramStandardTemplateParams);
    bindExpandButton(paramRemoteViews, paramStandardTemplateParams);
    Notification.access$1702(this.mN, true);
  }
  
  private void bindProfileBadge(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    Bitmap bitmap = getProfileBadge();
    if (bitmap != null) {
      paramRemoteViews.setImageViewBitmap(16909317, bitmap);
      paramRemoteViews.setViewVisibility(16909317, 0);
      if (isColorized(paramStandardTemplateParams))
        paramRemoteViews.setDrawableTint(16909317, false, getPrimaryTextColor(paramStandardTemplateParams), PorterDuff.Mode.SRC_ATOP); 
    } 
  }
  
  private boolean bindReplyIcon(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    boolean bool = paramStandardTemplateParams.hideReplyIcon;
    boolean bool1 = true;
    int j = bool ^ true;
    Notification.Action action = null;
    byte b = 0;
    int i = j;
    if (j != 0) {
      action = findReplyAction();
      if (action != null) {
        i = bool1;
      } else {
        i = 0;
      } 
    } 
    if (i != 0) {
      paramRemoteViews.setViewVisibility(16909346, 0);
      paramRemoteViews.setDrawableTint(16909346, false, getNeutralColor(paramStandardTemplateParams), PorterDuff.Mode.SRC_ATOP);
      paramRemoteViews.setOnClickPendingIntent(16909346, action.actionIntent);
      paramRemoteViews.setRemoteInputs(16909346, Notification.Action.access$2000(action));
    } else {
      paramRemoteViews.setRemoteInputs(16909346, null);
    } 
    if (i == 0)
      b = 8; 
    paramRemoteViews.setViewVisibility(16909346, b);
    return i;
  }
  
  private void bindSmallIcon(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    if (Notification.access$2200(this.mN) == null && this.mN.icon != 0) {
      Notification notification = this.mN;
      Notification.access$2202(notification, Icon.createWithResource(this.mContext, notification.icon));
    } 
    paramRemoteViews.setImageViewIcon(16908294, Notification.access$2200(this.mN));
    paramRemoteViews.setInt(16908294, "setImageLevel", this.mN.iconLevel);
    processSmallIconColor(Notification.access$2200(this.mN), paramRemoteViews, paramStandardTemplateParams);
  }
  
  private int calculateMarginEnd(boolean paramBoolean1, boolean paramBoolean2) {
    null = 0;
    int i = this.mContext.getResources().getDimensionPixelSize(17105345);
    int j = this.mContext.getResources().getDimensionPixelSize(17105379);
    if (paramBoolean2)
      null = 0 + j - this.mContext.getResources().getDimensionPixelSize(17105378) * 2; 
    int k = null;
    if (paramBoolean1) {
      null += j;
      k = null;
      if (paramBoolean2)
        k = null + i; 
    } 
    if (!paramBoolean2) {
      null = k;
      return paramBoolean1 ? (k + i) : null;
    } 
    return k + i;
  }
  
  private CharSequence createSummaryText() {
    CharSequence charSequence1 = this.mN.extras.getCharSequence("android.title");
    if (USE_ONLY_TITLE_IN_LOW_PRIORITY_SUMMARY)
      return charSequence1; 
    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
    CharSequence charSequence2 = charSequence1;
    if (charSequence1 == null)
      charSequence2 = this.mN.extras.getCharSequence("android.title.big"); 
    BidiFormatter bidiFormatter = BidiFormatter.getInstance();
    if (charSequence2 != null)
      spannableStringBuilder.append(bidiFormatter.unicodeWrap(charSequence2)); 
    charSequence1 = this.mN.extras.getCharSequence("android.text");
    if (charSequence2 != null && charSequence1 != null)
      spannableStringBuilder.append(bidiFormatter.unicodeWrap(this.mContext.getText(17040708))); 
    if (charSequence1 != null)
      spannableStringBuilder.append(bidiFormatter.unicodeWrap(charSequence1)); 
    return (CharSequence)spannableStringBuilder;
  }
  
  private CharSequence ensureColorSpanContrast(CharSequence paramCharSequence, int paramInt, ColorStateList[] paramArrayOfColorStateList) {
    if (paramCharSequence instanceof Spanned) {
      Spanned spanned = (Spanned)paramCharSequence;
      int i = spanned.length();
      boolean bool = false;
      Object[] arrayOfObject = spanned.getSpans(0, i, Object.class);
      SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned.toString());
      i = arrayOfObject.length;
      byte b = 0;
      while (b < i) {
        Object object1 = arrayOfObject[b];
        Object object2 = object1;
        int j = spanned.getSpanStart(object1);
        int k = spanned.getSpanEnd(object1);
        if (k - j == paramCharSequence.length())
          bool = true; 
        Object object3 = object2;
        if (object2 instanceof CharacterStyle)
          object3 = ((CharacterStyle)object1).getUnderlying(); 
        if (object3 instanceof TextAppearanceSpan) {
          TextAppearanceSpan textAppearanceSpan = (TextAppearanceSpan)object3;
          object2 = textAppearanceSpan.getTextColor();
          if (object2 != null) {
            object3 = object2.getColors();
            int[] arrayOfInt = new int[object3.length];
            for (byte b1 = 0; b1 < arrayOfInt.length; b1++)
              arrayOfInt[b1] = ContrastColorUtil.ensureLargeTextContrast(object3[b1], paramInt, this.mInNightMode); 
            object2 = new ColorStateList((int[][])object2.getStates().clone(), arrayOfInt);
            object3 = object2;
            if (bool) {
              paramArrayOfColorStateList[0] = (ColorStateList)object2;
              object3 = null;
            } 
            object3 = new TextAppearanceSpan(textAppearanceSpan.getFamily(), textAppearanceSpan.getTextStyle(), textAppearanceSpan.getTextSize(), (ColorStateList)object3, textAppearanceSpan.getLinkTextColor());
          } 
        } else if (object3 instanceof ForegroundColorSpan) {
          int m = ContrastColorUtil.ensureLargeTextContrast(((ForegroundColorSpan)object3).getForegroundColor(), paramInt, this.mInNightMode);
          if (bool) {
            paramArrayOfColorStateList[0] = ColorStateList.valueOf(m);
            object3 = null;
          } else {
            object3 = new ForegroundColorSpan(m);
          } 
        } else {
          object3 = object1;
        } 
        if (object3 != null)
          spannableStringBuilder.setSpan(object3, j, k, spanned.getSpanFlags(object1)); 
        b++;
        bool = false;
      } 
      return (CharSequence)spannableStringBuilder;
    } 
    return paramCharSequence;
  }
  
  private void ensureColors(Notification.StandardTemplateParams paramStandardTemplateParams) {
    int i = getBackgroundColor(paramStandardTemplateParams);
    if (this.mPrimaryTextColor == 1 || this.mSecondaryTextColor == 1 || this.mTextColorsAreForBackground != i) {
      int j;
      this.mTextColorsAreForBackground = i;
      if (!hasForegroundColor() || !isColorized(paramStandardTemplateParams)) {
        this.mPrimaryTextColor = ContrastColorUtil.resolvePrimaryColor(this.mContext, i, this.mInNightMode);
        this.mSecondaryTextColor = ContrastColorUtil.resolveSecondaryColor(this.mContext, i, this.mInNightMode);
        if (i != 0 && isColorized(paramStandardTemplateParams)) {
          this.mPrimaryTextColor = ContrastColorUtil.findAlphaToMeetContrast(this.mPrimaryTextColor, i, 4.5D);
          this.mSecondaryTextColor = ContrastColorUtil.findAlphaToMeetContrast(this.mSecondaryTextColor, i, 4.5D);
        } 
        return;
      } 
      double d1 = ContrastColorUtil.calculateLuminance(i);
      double d2 = ContrastColorUtil.calculateLuminance(this.mForegroundColor);
      double d3 = ContrastColorUtil.calculateContrast(this.mForegroundColor, i);
      if ((d1 > d2 && ContrastColorUtil.satisfiesTextContrast(i, -16777216)) || (d1 <= d2 && !ContrastColorUtil.satisfiesTextContrast(i, -1))) {
        j = 1;
      } else {
        j = 0;
      } 
      byte b = -20;
      if (d3 < 4.5D) {
        if (j) {
          j = ContrastColorUtil.findContrastColor(this.mForegroundColor, i, true, 4.5D);
          this.mSecondaryTextColor = j;
          this.mPrimaryTextColor = ContrastColorUtil.changeColorLightness(j, -20);
        } else {
          j = ContrastColorUtil.findContrastColorAgainstDark(this.mForegroundColor, i, true, 4.5D);
          this.mSecondaryTextColor = j;
          this.mPrimaryTextColor = ContrastColorUtil.changeColorLightness(j, 10);
        } 
      } else {
        int k = this.mForegroundColor;
        this.mPrimaryTextColor = k;
        if (j != 0) {
          m = 20;
        } else {
          m = -10;
        } 
        int m = ContrastColorUtil.changeColorLightness(k, m);
        this.mSecondaryTextColor = m;
        if (ContrastColorUtil.calculateContrast(m, i) < 4.5D) {
          if (j != 0) {
            this.mSecondaryTextColor = ContrastColorUtil.findContrastColor(this.mSecondaryTextColor, i, true, 4.5D);
          } else {
            this.mSecondaryTextColor = ContrastColorUtil.findContrastColorAgainstDark(this.mSecondaryTextColor, i, true, 4.5D);
          } 
          m = this.mSecondaryTextColor;
          if (j != 0) {
            j = b;
          } else {
            j = 10;
          } 
          this.mPrimaryTextColor = ContrastColorUtil.changeColorLightness(m, j);
        } 
      } 
    } 
  }
  
  private static List<Notification.Action> filterOutContextualActions(List<Notification.Action> paramList) {
    ArrayList<Notification.Action> arrayList = new ArrayList();
    for (Notification.Action action : paramList) {
      if (!action.isContextual())
        arrayList.add(action); 
    } 
    return arrayList;
  }
  
  private Notification.Action findReplyAction() {
    ArrayList<Notification.Action> arrayList = this.mActions;
    if (this.mOriginalActions != null)
      arrayList = this.mOriginalActions; 
    int i = arrayList.size();
    for (byte b = 0; b < i; b++) {
      Notification.Action action = arrayList.get(b);
      if (hasValidRemoteInput(action))
        return action; 
    } 
    return null;
  }
  
  private RemoteViews generateActionButton(Notification.Action paramAction, boolean paramBoolean, Notification.StandardTemplateParams paramStandardTemplateParams) {
    boolean bool2;
    int i;
    PendingIntent pendingIntent = paramAction.actionIntent;
    boolean bool1 = true;
    if (pendingIntent == null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    ApplicationInfo applicationInfo = this.mContext.getApplicationInfo();
    if (paramBoolean) {
      i = getEmphasizedActionLayoutResource();
    } else if (bool2) {
      i = getActionTombstoneLayoutResource();
    } else {
      i = getActionLayoutResource();
    } 
    Notification.BuilderRemoteViews builderRemoteViews = new Notification.BuilderRemoteViews(applicationInfo, i);
    if (!bool2)
      builderRemoteViews.setOnClickPendingIntent(16908696, paramAction.actionIntent); 
    builderRemoteViews.setContentDescription(16908696, paramAction.title);
    if (Notification.Action.access$2000(paramAction) != null)
      builderRemoteViews.setRemoteInputs(16908696, Notification.Action.access$2000(paramAction)); 
    if (paramBoolean) {
      ColorStateList[] arrayOfColorStateList;
      CharSequence charSequence = paramAction.title;
      applicationInfo = null;
      int j = resolveBackgroundColor(paramStandardTemplateParams);
      if (isLegacy()) {
        charSequence = ContrastColorUtil.clearColorSpans(charSequence);
      } else {
        arrayOfColorStateList = new ColorStateList[1];
        charSequence = ensureColorSpanContrast(charSequence, j, arrayOfColorStateList);
      } 
      builderRemoteViews.setTextViewText(16908696, processTextSpans(charSequence));
      setTextViewColorPrimary(builderRemoteViews, 16908696, paramStandardTemplateParams);
      if (arrayOfColorStateList != null && arrayOfColorStateList[0] != null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (bool2) {
        j = arrayOfColorStateList[0].getDefaultColor();
        i = ContrastColorUtil.resolvePrimaryColor(this.mContext, j, this.mInNightMode);
        builderRemoteViews.setTextColor(16908696, i);
      } else if (getRawColor(paramStandardTemplateParams) != 0 && !isColorized(paramStandardTemplateParams) && this.mTintActionButtons && !this.mInNightMode) {
        i = resolveContrastColor(paramStandardTemplateParams);
        builderRemoteViews.setTextColor(16908696, i);
      } else {
        i = getPrimaryTextColor(paramStandardTemplateParams);
      } 
      builderRemoteViews.setColorStateList(16908696, "setRippleColor", ColorStateList.valueOf(0xFFFFFF & i | 0x33000000));
      builderRemoteViews.setColorStateList(16908696, "setButtonBackground", ColorStateList.valueOf(j));
      if (!bool2) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      builderRemoteViews.setBoolean(16908696, "setHasStroke", paramBoolean);
    } else {
      builderRemoteViews.setTextViewText(16908696, processTextSpans(processLegacyText(paramAction.title)));
      if (isColorized(paramStandardTemplateParams)) {
        setTextViewColorPrimary(builderRemoteViews, 16908696, paramStandardTemplateParams);
      } else if (getRawColor(paramStandardTemplateParams) != 0 && this.mTintActionButtons) {
        builderRemoteViews.setTextColor(16908696, resolveContrastColor(paramStandardTemplateParams));
      } 
    } 
    builderRemoteViews.setIntTag(16908696, 16909220, this.mActions.indexOf(paramAction));
    return builderRemoteViews;
  }
  
  private int getActionLayoutResource() {
    return 17367199;
  }
  
  private int getActionTombstoneLayoutResource() {
    return 17367202;
  }
  
  private Bundle getAllExtras() {
    Bundle bundle = (Bundle)this.mUserExtras.clone();
    bundle.putAll(this.mN.extras);
    return bundle;
  }
  
  private int getBackgroundColor(Notification.StandardTemplateParams paramStandardTemplateParams) {
    if (isColorized(paramStandardTemplateParams)) {
      int i = this.mBackgroundColor;
      if (i == 1)
        i = getRawColor(paramStandardTemplateParams); 
      return i;
    } 
    return 0;
  }
  
  private int getBaseLayoutResource() {
    return 17367208;
  }
  
  private int getBigBaseLayoutResource() {
    return 17367209;
  }
  
  private int getBigPictureLayoutResource() {
    return 17367211;
  }
  
  private int getBigTextLayoutResource() {
    return 17367212;
  }
  
  private ContrastColorUtil getColorUtil() {
    if (this.mColorUtil == null)
      this.mColorUtil = ContrastColorUtil.getInstance(this.mContext); 
    return this.mColorUtil;
  }
  
  private int getConversationLayoutResource() {
    return 17367213;
  }
  
  private int getEmphasizedActionLayoutResource() {
    return 17367200;
  }
  
  private int getInboxLayoutResource() {
    return 17367214;
  }
  
  private int getMessagingLayoutResource() {
    return 17367216;
  }
  
  private int getNeutralColor(Notification.StandardTemplateParams paramStandardTemplateParams) {
    return isColorized(paramStandardTemplateParams) ? getSecondaryTextColor(paramStandardTemplateParams) : resolveNeutralColor();
  }
  
  private Bitmap getProfileBadge() {
    Drawable drawable = getProfileBadgeDrawable();
    if (drawable == null)
      return null; 
    int i = this.mContext.getResources().getDimensionPixelSize(17105338);
    Bitmap bitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, i, i);
    drawable.draw(canvas);
    return bitmap;
  }
  
  private Drawable getProfileBadgeDrawable() {
    return (this.mContext.getUserId() == 0) ? null : this.mContext.getPackageManager().getUserBadgeForDensityNoBackground(new UserHandle(this.mContext.getUserId()), 0);
  }
  
  private int getRawColor(Notification.StandardTemplateParams paramStandardTemplateParams) {
    return paramStandardTemplateParams.forceDefaultColor ? 0 : this.mN.color;
  }
  
  private boolean handleProgressBar(RemoteViews paramRemoteViews, Bundle paramBundle, Notification.StandardTemplateParams paramStandardTemplateParams) {
    int i = paramBundle.getInt("android.progressMax", 0);
    int j = paramBundle.getInt("android.progress", 0);
    boolean bool = paramBundle.getBoolean("android.progressIndeterminate");
    if (paramStandardTemplateParams.hasProgress && (i != 0 || bool)) {
      paramRemoteViews.setViewVisibility(16908301, 0);
      paramRemoteViews.setProgressBar(16908301, i, j, bool);
      paramRemoteViews.setProgressBackgroundTintList(16908301, ColorStateList.valueOf(this.mContext.getColor(17170891)));
      if (getRawColor(paramStandardTemplateParams) != 0) {
        if (isColorized(paramStandardTemplateParams)) {
          i = getPrimaryTextColor(paramStandardTemplateParams);
        } else {
          i = resolveContrastColor(paramStandardTemplateParams);
        } 
        ColorStateList colorStateList = ColorStateList.valueOf(i);
        paramRemoteViews.setProgressTintList(16908301, colorStateList);
        paramRemoteViews.setProgressIndeterminateTintList(16908301, colorStateList);
      } 
      return true;
    } 
    paramRemoteViews.setViewVisibility(16908301, 8);
    return false;
  }
  
  private boolean hasForegroundColor() {
    int i = this.mForegroundColor;
    boolean bool = true;
    if (i == 1)
      bool = false; 
    return bool;
  }
  
  private boolean hasValidRemoteInput(Notification.Action paramAction) {
    if (TextUtils.isEmpty(paramAction.title) || paramAction.actionIntent == null)
      return false; 
    RemoteInput[] arrayOfRemoteInput = paramAction.getRemoteInputs();
    if (arrayOfRemoteInput == null)
      return false; 
    int i = arrayOfRemoteInput.length;
    for (byte b = 0; b < i; b++) {
      RemoteInput remoteInput = arrayOfRemoteInput[b];
      CharSequence[] arrayOfCharSequence = remoteInput.getChoices();
      if (remoteInput.getAllowFreeFormInput() || (arrayOfCharSequence != null && arrayOfCharSequence.length != 0))
        return true; 
    } 
    return false;
  }
  
  private void hideLine1Text(RemoteViews paramRemoteViews) {
    if (paramRemoteViews != null)
      paramRemoteViews.setViewVisibility(16909530, 8); 
  }
  
  private boolean isColorized(Notification.StandardTemplateParams paramStandardTemplateParams) {
    boolean bool;
    if (paramStandardTemplateParams.allowColorization && this.mN.isColorized()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isLegacy() {
    if (!this.mIsLegacyInitialized) {
      boolean bool;
      if ((this.mContext.getApplicationInfo()).targetSdkVersion < 21) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mIsLegacy = bool;
      this.mIsLegacyInitialized = true;
    } 
    return this.mIsLegacy;
  }
  
  public static void makeHeaderExpanded(RemoteViews paramRemoteViews) {
    if (paramRemoteViews != null)
      paramRemoteViews.setBoolean(16909224, "setExpanded", true); 
  }
  
  private RemoteViews makeNotificationHeader(Notification.StandardTemplateParams paramStandardTemplateParams) {
    paramStandardTemplateParams.disallowColorization();
    Notification.BuilderRemoteViews builderRemoteViews = new Notification.BuilderRemoteViews(this.mContext.getApplicationInfo(), 17367207);
    resetNotificationHeader(builderRemoteViews);
    bindNotificationHeader(builderRemoteViews, paramStandardTemplateParams);
    return builderRemoteViews;
  }
  
  public static Notification maybeCloneStrippedForDelivery(Notification paramNotification) {
    boolean bool2;
    boolean bool3;
    String str = paramNotification.extras.getString("android.template");
    if (!TextUtils.isEmpty(str) && Notification.getNotificationStyleClass(str) == null)
      return paramNotification; 
    boolean bool = paramNotification.contentView instanceof Notification.BuilderRemoteViews;
    boolean bool1 = true;
    if (bool && paramNotification.extras.getInt("android.rebuild.contentViewActionCount", -1) == paramNotification.contentView.getSequenceNumber()) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (paramNotification.bigContentView instanceof Notification.BuilderRemoteViews && paramNotification.extras.getInt("android.rebuild.bigViewActionCount", -1) == paramNotification.bigContentView.getSequenceNumber()) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if (!(paramNotification.headsUpContentView instanceof Notification.BuilderRemoteViews) || paramNotification.extras.getInt("android.rebuild.hudViewActionCount", -1) != paramNotification.headsUpContentView.getSequenceNumber())
      bool1 = false; 
    if (!bool2 && !bool3 && !bool1)
      return paramNotification; 
    paramNotification = paramNotification.clone();
    if (bool2) {
      paramNotification.contentView = null;
      paramNotification.extras.remove("android.rebuild.contentViewActionCount");
    } 
    if (bool3) {
      paramNotification.bigContentView = null;
      paramNotification.extras.remove("android.rebuild.bigViewActionCount");
    } 
    if (bool1) {
      paramNotification.headsUpContentView = null;
      paramNotification.extras.remove("android.rebuild.hudViewActionCount");
    } 
    return paramNotification;
  }
  
  private void processLargeLegacyIcon(Icon paramIcon, RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    if (paramIcon != null && isLegacy() && getColorUtil().isGrayscaleIcon(this.mContext, paramIcon))
      paramRemoteViews.setDrawableTint(16908294, false, resolveContrastColor(paramStandardTemplateParams), PorterDuff.Mode.SRC_ATOP); 
  }
  
  private CharSequence processLegacyText(CharSequence paramCharSequence) {
    boolean bool;
    if (isLegacy() || textColorsNeedInversion()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool ? getColorUtil().invertCharSequenceColors(paramCharSequence) : paramCharSequence;
  }
  
  private void processSmallIconColor(Icon paramIcon, RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    boolean bool1;
    int j;
    boolean bool = isLegacy();
    int i = 1;
    if (!bool || getColorUtil().isGrayscaleIcon(this.mContext, paramIcon)) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (isColorized(paramStandardTemplateParams)) {
      j = getPrimaryTextColor(paramStandardTemplateParams);
    } else {
      j = resolveContrastColor(paramStandardTemplateParams);
    } 
    if (bool1)
      paramRemoteViews.setDrawableTint(16908294, false, j, PorterDuff.Mode.SRC_ATOP); 
    if (bool1)
      i = j; 
    paramRemoteViews.setInt(16908294, "setOriginalIconColor", i);
  }
  
  private CharSequence processTextSpans(CharSequence paramCharSequence) {
    return (hasForegroundColor() || this.mInNightMode) ? ContrastColorUtil.clearColorSpans(paramCharSequence) : paramCharSequence;
  }
  
  public static Builder recoverBuilder(Context paramContext, Notification paramNotification) {
    ApplicationInfo applicationInfo = (ApplicationInfo)paramNotification.extras.getParcelable("android.appInfo");
    if (applicationInfo != null)
      try {
        Context context = paramContext.createApplicationContext(applicationInfo, 4);
        paramContext = context;
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ApplicationInfo ");
        stringBuilder.append(applicationInfo);
        stringBuilder.append(" not found");
        Log.e("Notification", stringBuilder.toString());
      }  
    return new Builder(paramContext, paramNotification);
  }
  
  private void resetNotificationHeader(RemoteViews paramRemoteViews) {
    paramRemoteViews.setBoolean(16909224, "setExpanded", false);
    paramRemoteViews.setTextViewText(16908761, null);
    paramRemoteViews.setViewVisibility(16908842, 8);
    paramRemoteViews.setViewVisibility(16909033, 8);
    paramRemoteViews.setTextViewText(16909033, null);
    paramRemoteViews.setViewVisibility(16909035, 8);
    paramRemoteViews.setTextViewText(16909035, null);
    paramRemoteViews.setViewVisibility(16909034, 8);
    paramRemoteViews.setViewVisibility(16909036, 8);
    paramRemoteViews.setViewVisibility(16909537, 8);
    paramRemoteViews.setViewVisibility(16909533, 8);
    paramRemoteViews.setImageViewIcon(16909317, null);
    paramRemoteViews.setViewVisibility(16909317, 8);
    Notification.access$1702(this.mN, false);
  }
  
  private void resetStandardTemplate(RemoteViews paramRemoteViews) {
    resetNotificationHeader(paramRemoteViews);
    paramRemoteViews.setViewVisibility(16909366, 8);
    paramRemoteViews.setViewVisibility(16908310, 8);
    paramRemoteViews.setTextViewText(16908310, null);
    paramRemoteViews.setViewVisibility(16909502, 8);
    paramRemoteViews.setTextViewText(16909502, null);
    paramRemoteViews.setViewVisibility(16909530, 8);
    paramRemoteViews.setTextViewText(16909530, null);
  }
  
  private void resetStandardTemplateWithActions(RemoteViews paramRemoteViews) {
    paramRemoteViews.setViewVisibility(16908721, 8);
    paramRemoteViews.removeAllViews(16908721);
    paramRemoteViews.setViewVisibility(16909226, 8);
    paramRemoteViews.setTextViewText(16909228, null);
    paramRemoteViews.setViewVisibility(16909229, 8);
    paramRemoteViews.setViewVisibility(16909227, 8);
    paramRemoteViews.setViewVisibility(16909230, 8);
    paramRemoteViews.setTextViewText(16909230, null);
    paramRemoteViews.setViewVisibility(16909231, 8);
    paramRemoteViews.setTextViewText(16909231, null);
    paramRemoteViews.setViewLayoutMarginBottomDimen(16909221, 17105344);
  }
  
  private int resolveBackgroundColor(Notification.StandardTemplateParams paramStandardTemplateParams) {
    int i = getBackgroundColor(paramStandardTemplateParams);
    int j = i;
    if (i == 0)
      j = this.mContext.getColor(17170888); 
    return j;
  }
  
  private void sanitizeColor() {
    if (this.mN.color != 0) {
      Notification notification = this.mN;
      notification.color |= 0xFF000000;
    } 
  }
  
  private void setTextViewColorPrimary(RemoteViews paramRemoteViews, int paramInt, Notification.StandardTemplateParams paramStandardTemplateParams) {
    ensureColors(paramStandardTemplateParams);
    paramRemoteViews.setTextColor(paramInt, this.mPrimaryTextColor);
  }
  
  private void setTextViewColorSecondary(RemoteViews paramRemoteViews, int paramInt, Notification.StandardTemplateParams paramStandardTemplateParams) {
    ensureColors(paramStandardTemplateParams);
    paramRemoteViews.setTextColor(paramInt, this.mSecondaryTextColor);
  }
  
  private boolean shouldTintActionButtons() {
    return this.mTintActionButtons;
  }
  
  private boolean showsTimeOrChronometer() {
    return (this.mN.showsTime() || this.mN.showsChronometer());
  }
  
  private boolean textColorsNeedInversion() {
    Notification.Style style = this.mStyle;
    boolean bool1 = false;
    if (style == null || !Notification.MediaStyle.class.equals(style.getClass()))
      return false; 
    int i = (this.mContext.getApplicationInfo()).targetSdkVersion;
    boolean bool2 = bool1;
    if (i > 23) {
      bool2 = bool1;
      if (i < 26)
        bool2 = true; 
    } 
    return bool2;
  }
  
  private void updateBackgroundColor(RemoteViews paramRemoteViews, Notification.StandardTemplateParams paramStandardTemplateParams) {
    if (isColorized(paramStandardTemplateParams)) {
      paramRemoteViews.setInt(16909478, "setBackgroundColor", getBackgroundColor(paramStandardTemplateParams));
    } else {
      paramRemoteViews.setInt(16909478, "setBackgroundResource", 0);
    } 
  }
  
  private boolean useExistingRemoteView() {
    Notification.Style style = this.mStyle;
    return (style == null || (!style.displayCustomViewInline() && !this.mRebuildStyledRemoteViews));
  }
  
  @Deprecated
  public Builder addAction(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent) {
    this.mActions.add(new Notification.Action(paramInt, Notification.safeCharSequence(paramCharSequence), paramPendingIntent));
    return this;
  }
  
  public Builder addAction(Notification.Action paramAction) {
    if (paramAction != null)
      this.mActions.add(paramAction); 
    return this;
  }
  
  public Builder addExtras(Bundle paramBundle) {
    if (paramBundle != null)
      this.mUserExtras.putAll(paramBundle); 
    return this;
  }
  
  public Builder addPerson(Person paramPerson) {
    this.mPersonList.add(paramPerson);
    return this;
  }
  
  public Builder addPerson(String paramString) {
    addPerson((new Person.Builder()).setUri(paramString).build());
    return this;
  }
  
  public Notification build() {
    if (Notification.access$700(this.mN) == null || Notification.access$1100(this.mN) == null || Notification.access$1100(this.mN).getShortcutId() == null || Notification.access$700(this.mN).equals(Notification.access$1100(this.mN).getShortcutId())) {
      if (this.mUserExtras != null)
        this.mN.extras = getAllExtras(); 
      Notification.access$2102(this.mN, System.currentTimeMillis());
      Notification.addFieldsFromContext(this.mContext, this.mN);
      buildUnstyled();
      Notification.Style style = this.mStyle;
      if (style != null) {
        style.reduceImageSizes(this.mContext);
        this.mStyle.purgeResources();
        this.mStyle.validate(this.mContext);
        this.mStyle.buildStyled(this.mN);
      } 
      this.mN.reduceImageSizes(this.mContext);
      if ((this.mContext.getApplicationInfo()).targetSdkVersion < 24 && useExistingRemoteView()) {
        if (this.mN.contentView == null) {
          this.mN.contentView = createContentView();
          this.mN.extras.putInt("android.rebuild.contentViewActionCount", this.mN.contentView.getSequenceNumber());
        } 
        if (this.mN.bigContentView == null) {
          this.mN.bigContentView = createBigContentView();
          if (this.mN.bigContentView != null)
            this.mN.extras.putInt("android.rebuild.bigViewActionCount", this.mN.bigContentView.getSequenceNumber()); 
        } 
        if (this.mN.headsUpContentView == null) {
          this.mN.headsUpContentView = createHeadsUpContentView();
          if (this.mN.headsUpContentView != null)
            this.mN.extras.putInt("android.rebuild.hudViewActionCount", this.mN.headsUpContentView.getSequenceNumber()); 
        } 
      } 
      if ((this.mN.defaults & 0x4) != 0) {
        Notification notification = this.mN;
        notification.flags |= 0x1;
      } 
      this.mN.allPendingIntents = null;
      return this.mN;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Notification and BubbleMetadata shortcut id's don't match, notification: ");
    stringBuilder.append(Notification.access$700(this.mN));
    stringBuilder.append(" vs bubble: ");
    stringBuilder.append(Notification.access$1100(this.mN).getShortcutId());
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Notification buildInto(Notification paramNotification) {
    build().cloneInto(paramNotification, true);
    return paramNotification;
  }
  
  public Notification buildUnstyled() {
    if (this.mActions.size() > 0) {
      this.mN.actions = new Notification.Action[this.mActions.size()];
      this.mActions.toArray(this.mN.actions);
    } 
    if (!this.mPersonList.isEmpty())
      this.mN.extras.putParcelableArrayList("android.people.list", this.mPersonList); 
    if (this.mN.bigContentView != null || this.mN.contentView != null || this.mN.headsUpContentView != null)
      this.mN.extras.putBoolean("android.contains.customView", true); 
    return this.mN;
  }
  
  public RemoteViews createBigContentView() {
    RemoteViews remoteViews = null;
    if (this.mN.bigContentView != null && useExistingRemoteView())
      return this.mN.bigContentView; 
    Notification.Style style = this.mStyle;
    if (style != null) {
      remoteViews = style.makeBigContentView();
      hideLine1Text(remoteViews);
    } else if (this.mActions.size() != 0) {
      remoteViews = applyStandardTemplateWithActions(getBigBaseLayoutResource(), null);
    } 
    makeHeaderExpanded(remoteViews);
    return remoteViews;
  }
  
  public RemoteViews createContentView() {
    return createContentView(false);
  }
  
  public RemoteViews createContentView(boolean paramBoolean) {
    if (this.mN.contentView != null && useExistingRemoteView())
      return this.mN.contentView; 
    Notification.Style style = this.mStyle;
    if (style != null) {
      RemoteViews remoteViews = style.makeContentView(paramBoolean);
      if (remoteViews != null)
        return remoteViews; 
    } 
    return applyStandardTemplate(getBaseLayoutResource(), null);
  }
  
  public RemoteViews createHeadsUpContentView() {
    return createHeadsUpContentView(false);
  }
  
  public RemoteViews createHeadsUpContentView(boolean paramBoolean) {
    if (this.mN.headsUpContentView != null && useExistingRemoteView())
      return this.mN.headsUpContentView; 
    Notification.Style style = this.mStyle;
    if (style != null) {
      RemoteViews remoteViews = style.makeHeadsUpContentView(paramBoolean);
      if (remoteViews != null)
        return remoteViews; 
    } else if (this.mActions.size() == 0) {
      return null;
    } 
    Notification.StandardTemplateParams standardTemplateParams = this.mParams.reset().fillTextsFrom(this).setMaxRemoteInputHistory(1);
    return applyStandardTemplateWithActions(getBigBaseLayoutResource(), standardTemplateParams, null);
  }
  
  public Builder extend(Notification.Extender paramExtender) {
    paramExtender.extend(this);
    return this;
  }
  
  public Bundle getExtras() {
    return this.mUserExtras;
  }
  
  public CharSequence getHeadsUpStatusBarText(boolean paramBoolean) {
    Notification.Style style = this.mStyle;
    if (style != null && !paramBoolean) {
      CharSequence charSequence = style.getHeadsUpStatusBarText();
      if (!TextUtils.isEmpty(charSequence))
        return charSequence; 
    } 
    return loadHeaderAppName();
  }
  
  @Deprecated
  public Notification getNotification() {
    return build();
  }
  
  public int getPrimaryTextColor() {
    return getPrimaryTextColor(this.mParams);
  }
  
  public int getPrimaryTextColor(Notification.StandardTemplateParams paramStandardTemplateParams) {
    ensureColors(paramStandardTemplateParams);
    return this.mPrimaryTextColor;
  }
  
  public int getSecondaryTextColor() {
    return getSecondaryTextColor(this.mParams);
  }
  
  public int getSecondaryTextColor(Notification.StandardTemplateParams paramStandardTemplateParams) {
    ensureColors(paramStandardTemplateParams);
    return this.mSecondaryTextColor;
  }
  
  public Notification.Style getStyle() {
    return this.mStyle;
  }
  
  public String loadHeaderAppName() {
    CharSequence charSequence = null;
    PackageManager packageManager = this.mContext.getPackageManager();
    String str = (String)charSequence;
    if (this.mN.extras.containsKey("android.substName")) {
      String str1 = this.mContext.getPackageName();
      str = this.mN.extras.getString("android.substName");
      if (packageManager.checkPermission("android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME", str1) != 0) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("warning: pkg ");
        stringBuilder.append(str1);
        stringBuilder.append(" attempting to substitute app name '");
        stringBuilder.append(str);
        stringBuilder.append("' without holding perm ");
        stringBuilder.append("android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME");
        Log.w("Notification", stringBuilder.toString());
        str = (String)charSequence;
      } 
    } 
    charSequence = str;
    if (TextUtils.isEmpty(str))
      charSequence = packageManager.getApplicationLabel(this.mContext.getApplicationInfo()); 
    return TextUtils.isEmpty(charSequence) ? null : String.valueOf(charSequence);
  }
  
  public RemoteViews makeAmbientNotification() {
    RemoteViews remoteViews = createHeadsUpContentView(false);
    return (remoteViews != null) ? remoteViews : createContentView();
  }
  
  public RemoteViews makeLowPriorityContentView(boolean paramBoolean) {
    Notification.StandardTemplateParams standardTemplateParams = this.mParams.reset().forceDefaultColor().fillTextsFrom(this);
    if (!paramBoolean || TextUtils.isEmpty(this.mParams.summaryText))
      standardTemplateParams.summaryText(createSummaryText()); 
    RemoteViews remoteViews = makeNotificationHeader(standardTemplateParams);
    remoteViews.setBoolean(16909224, "setAcceptAllTouches", true);
    return remoteViews;
  }
  
  public RemoteViews makeNotificationHeader() {
    return makeNotificationHeader(this.mParams.reset().fillTextsFrom(this));
  }
  
  public RemoteViews makePublicContentView(boolean paramBoolean) {
    if (this.mN.publicVersion != null)
      return recoverBuilder(this.mContext, this.mN.publicVersion).createContentView(); 
    Bundle bundle1 = this.mN.extras;
    Notification.Style style = this.mStyle;
    this.mStyle = null;
    Icon icon = Notification.access$1400(this.mN);
    Notification.access$1402(this.mN, null);
    Bitmap bitmap = this.mN.largeIcon;
    this.mN.largeIcon = null;
    ArrayList<Notification.Action> arrayList = this.mActions;
    this.mActions = new ArrayList<>();
    Bundle bundle2 = new Bundle();
    bundle2.putBoolean("android.showWhen", bundle1.getBoolean("android.showWhen"));
    bundle2.putBoolean("android.showChronometer", bundle1.getBoolean("android.showChronometer"));
    bundle2.putBoolean("android.chronometerCountDown", bundle1.getBoolean("android.chronometerCountDown"));
    String str = bundle1.getString("android.substName");
    if (str != null)
      bundle2.putString("android.substName", str); 
    this.mN.extras = bundle2;
    Notification.StandardTemplateParams standardTemplateParams = this.mParams.reset().fillTextsFrom(this);
    if (paramBoolean)
      standardTemplateParams.forceDefaultColor(); 
    RemoteViews remoteViews = makeNotificationHeader(standardTemplateParams);
    remoteViews.setBoolean(16909224, "setExpandOnlyOnButton", true);
    this.mN.extras = bundle1;
    Notification.access$1402(this.mN, icon);
    this.mN.largeIcon = bitmap;
    this.mActions = arrayList;
    this.mStyle = style;
    return remoteViews;
  }
  
  int resolveContrastColor(Notification.StandardTemplateParams paramStandardTemplateParams) {
    int j;
    int i = getRawColor(paramStandardTemplateParams);
    if (this.mCachedContrastColorIsFor == i) {
      j = this.mCachedContrastColor;
      if (j != 1)
        return j; 
    } 
    int k = this.mContext.getColor(17170888);
    if (i == 0) {
      ensureColors(paramStandardTemplateParams);
      j = ContrastColorUtil.resolveDefaultColor(this.mContext, k, this.mInNightMode);
    } else {
      j = ContrastColorUtil.resolveContrastColor(this.mContext, i, k, this.mInNightMode);
    } 
    int m = j;
    if (Color.alpha(j) < 255)
      m = ContrastColorUtil.compositeColors(j, k); 
    this.mCachedContrastColorIsFor = i;
    this.mCachedContrastColor = m;
    return m;
  }
  
  int resolveNeutralColor() {
    int i = this.mNeutralColor;
    if (i != 1)
      return i; 
    int j = this.mContext.getColor(17170888);
    i = ContrastColorUtil.resolveDefaultColor(this.mContext, j, this.mInNightMode);
    this.mNeutralColor = i;
    if (Color.alpha(i) < 255)
      this.mNeutralColor = ContrastColorUtil.compositeColors(this.mNeutralColor, j); 
    return this.mNeutralColor;
  }
  
  public Builder setActions(Notification.Action... paramVarArgs) {
    this.mActions.clear();
    for (byte b = 0; b < paramVarArgs.length; b++) {
      if (paramVarArgs[b] != null)
        this.mActions.add(paramVarArgs[b]); 
    } 
    return this;
  }
  
  public Builder setAllowSystemGeneratedContextualActions(boolean paramBoolean) {
    Notification.access$2302(this.mN, paramBoolean);
    return this;
  }
  
  public Builder setAutoCancel(boolean paramBoolean) {
    setFlag(16, paramBoolean);
    return this;
  }
  
  public Builder setBadgeIconType(int paramInt) {
    Notification.access$902(this.mN, paramInt);
    return this;
  }
  
  public Builder setBubbleMetadata(Notification.BubbleMetadata paramBubbleMetadata) {
    Notification.access$1102(this.mN, paramBubbleMetadata);
    return this;
  }
  
  public Builder setCategory(String paramString) {
    this.mN.category = paramString;
    return this;
  }
  
  @Deprecated
  public Builder setChannel(String paramString) {
    Notification.access$602(this.mN, paramString);
    return this;
  }
  
  public Builder setChannelId(String paramString) {
    Notification.access$602(this.mN, paramString);
    return this;
  }
  
  public Builder setChronometerCountDown(boolean paramBoolean) {
    this.mN.extras.putBoolean("android.chronometerCountDown", paramBoolean);
    return this;
  }
  
  public Builder setColor(int paramInt) {
    this.mN.color = paramInt;
    sanitizeColor();
    return this;
  }
  
  public void setColorPalette(int paramInt1, int paramInt2) {
    this.mBackgroundColor = paramInt1;
    this.mForegroundColor = paramInt2;
    this.mTextColorsAreForBackground = 1;
    ensureColors(this.mParams.reset().fillTextsFrom(this));
  }
  
  public Builder setColorized(boolean paramBoolean) {
    this.mN.extras.putBoolean("android.colorized", paramBoolean);
    return this;
  }
  
  @Deprecated
  public Builder setContent(RemoteViews paramRemoteViews) {
    return setCustomContentView(paramRemoteViews);
  }
  
  @Deprecated
  public Builder setContentInfo(CharSequence paramCharSequence) {
    this.mN.extras.putCharSequence("android.infoText", Notification.safeCharSequence(paramCharSequence));
    return this;
  }
  
  public Builder setContentIntent(PendingIntent paramPendingIntent) {
    this.mN.contentIntent = paramPendingIntent;
    return this;
  }
  
  void setContentMinHeight(RemoteViews paramRemoteViews, boolean paramBoolean) {
    int i = 0;
    if (paramBoolean)
      i = this.mContext.getResources().getDimensionPixelSize(17105374); 
    paramRemoteViews.setInt(16909225, "setMinimumHeight", i);
  }
  
  public Builder setContentText(CharSequence paramCharSequence) {
    this.mN.extras.putCharSequence("android.text", Notification.safeCharSequence(paramCharSequence));
    return this;
  }
  
  public Builder setContentTitle(CharSequence paramCharSequence) {
    this.mN.extras.putCharSequence("android.title", Notification.safeCharSequence(paramCharSequence));
    return this;
  }
  
  public Builder setCustomBigContentView(RemoteViews paramRemoteViews) {
    this.mN.bigContentView = paramRemoteViews;
    return this;
  }
  
  public Builder setCustomContentView(RemoteViews paramRemoteViews) {
    this.mN.contentView = paramRemoteViews;
    return this;
  }
  
  public Builder setCustomHeadsUpContentView(RemoteViews paramRemoteViews) {
    this.mN.headsUpContentView = paramRemoteViews;
    return this;
  }
  
  @Deprecated
  public Builder setDefaults(int paramInt) {
    this.mN.defaults = paramInt;
    return this;
  }
  
  public Builder setDeleteIntent(PendingIntent paramPendingIntent) {
    this.mN.deleteIntent = paramPendingIntent;
    return this;
  }
  
  public Builder setExtras(Bundle paramBundle) {
    if (paramBundle != null)
      this.mUserExtras = paramBundle; 
    return this;
  }
  
  public Builder setFlag(int paramInt, boolean paramBoolean) {
    if (paramBoolean) {
      Notification notification = this.mN;
      notification.flags |= paramInt;
    } else {
      Notification notification = this.mN;
      notification.flags &= paramInt;
    } 
    return this;
  }
  
  public Builder setFullScreenIntent(PendingIntent paramPendingIntent, boolean paramBoolean) {
    this.mN.fullScreenIntent = paramPendingIntent;
    setFlag(128, paramBoolean);
    return this;
  }
  
  public Builder setGroup(String paramString) {
    Notification.access$1502(this.mN, paramString);
    return this;
  }
  
  public Builder setGroupAlertBehavior(int paramInt) {
    Notification.access$1002(this.mN, paramInt);
    return this;
  }
  
  public Builder setGroupSummary(boolean paramBoolean) {
    setFlag(512, paramBoolean);
    return this;
  }
  
  public Builder setHideSmartReplies(boolean paramBoolean) {
    this.mN.extras.putBoolean("android.hideSmartReplies", paramBoolean);
    return this;
  }
  
  public Builder setLargeIcon(Bitmap paramBitmap) {
    if (paramBitmap != null) {
      Icon icon = Icon.createWithBitmap(paramBitmap);
    } else {
      paramBitmap = null;
    } 
    return setLargeIcon((Icon)paramBitmap);
  }
  
  public Builder setLargeIcon(Icon paramIcon) {
    Notification.access$1402(this.mN, paramIcon);
    this.mN.extras.putParcelable("android.largeIcon", (Parcelable)paramIcon);
    return this;
  }
  
  @Deprecated
  public Builder setLights(int paramInt1, int paramInt2, int paramInt3) {
    this.mN.ledARGB = paramInt1;
    this.mN.ledOnMS = paramInt2;
    this.mN.ledOffMS = paramInt3;
    if (paramInt2 != 0 || paramInt3 != 0) {
      Notification notification = this.mN;
      notification.flags |= 0x1;
    } 
    return this;
  }
  
  public Builder setLocalOnly(boolean paramBoolean) {
    setFlag(256, paramBoolean);
    return this;
  }
  
  public Builder setLocusId(LocusId paramLocusId) {
    Notification.access$802(this.mN, paramLocusId);
    return this;
  }
  
  public Builder setNumber(int paramInt) {
    this.mN.number = paramInt;
    return this;
  }
  
  public Builder setOngoing(boolean paramBoolean) {
    setFlag(2, paramBoolean);
    return this;
  }
  
  public Builder setOnlyAlertOnce(boolean paramBoolean) {
    setFlag(8, paramBoolean);
    return this;
  }
  
  @Deprecated
  public Builder setPriority(int paramInt) {
    this.mN.priority = paramInt;
    return this;
  }
  
  public Builder setProgress(int paramInt1, int paramInt2, boolean paramBoolean) {
    this.mN.extras.putInt("android.progress", paramInt2);
    this.mN.extras.putInt("android.progressMax", paramInt1);
    this.mN.extras.putBoolean("android.progressIndeterminate", paramBoolean);
    return this;
  }
  
  public Builder setPublicVersion(Notification paramNotification) {
    if (paramNotification != null) {
      this.mN.publicVersion = new Notification();
      paramNotification.cloneInto(this.mN.publicVersion, true);
    } else {
      this.mN.publicVersion = null;
    } 
    return this;
  }
  
  public void setRebuildStyledRemoteViews(boolean paramBoolean) {
    this.mRebuildStyledRemoteViews = paramBoolean;
  }
  
  public Builder setRemoteInputHistory(RemoteInputHistoryItem[] paramArrayOfRemoteInputHistoryItem) {
    if (paramArrayOfRemoteInputHistoryItem == null) {
      this.mN.extras.putParcelableArray("android.remoteInputHistoryItems", null);
    } else {
      int i = Math.min(5, paramArrayOfRemoteInputHistoryItem.length);
      RemoteInputHistoryItem[] arrayOfRemoteInputHistoryItem = new RemoteInputHistoryItem[i];
      for (byte b = 0; b < i; b++)
        arrayOfRemoteInputHistoryItem[b] = paramArrayOfRemoteInputHistoryItem[b]; 
      this.mN.extras.putParcelableArray("android.remoteInputHistoryItems", (Parcelable[])arrayOfRemoteInputHistoryItem);
    } 
    return this;
  }
  
  public Builder setRemoteInputHistory(CharSequence[] paramArrayOfCharSequence) {
    if (paramArrayOfCharSequence == null) {
      this.mN.extras.putCharSequenceArray("android.remoteInputHistory", null);
    } else {
      int i = Math.min(5, paramArrayOfCharSequence.length);
      CharSequence[] arrayOfCharSequence = new CharSequence[i];
      RemoteInputHistoryItem[] arrayOfRemoteInputHistoryItem = new RemoteInputHistoryItem[i];
      for (byte b = 0; b < i; b++) {
        arrayOfCharSequence[b] = Notification.safeCharSequence(paramArrayOfCharSequence[b]);
        arrayOfRemoteInputHistoryItem[b] = new RemoteInputHistoryItem(paramArrayOfCharSequence[b]);
      } 
      this.mN.extras.putCharSequenceArray("android.remoteInputHistory", arrayOfCharSequence);
      this.mN.extras.putParcelableArray("android.remoteInputHistoryItems", (Parcelable[])arrayOfRemoteInputHistoryItem);
    } 
    return this;
  }
  
  public Builder setSettingsText(CharSequence paramCharSequence) {
    Notification.access$1302(this.mN, Notification.safeCharSequence(paramCharSequence));
    return this;
  }
  
  public Builder setShortcutId(String paramString) {
    Notification.access$702(this.mN, paramString);
    return this;
  }
  
  public Builder setShowRemoteInputSpinner(boolean paramBoolean) {
    this.mN.extras.putBoolean("android.remoteInputSpinner", paramBoolean);
    return this;
  }
  
  public Builder setShowWhen(boolean paramBoolean) {
    this.mN.extras.putBoolean("android.showWhen", paramBoolean);
    return this;
  }
  
  public Builder setSmallIcon(int paramInt) {
    Icon icon;
    if (paramInt != 0) {
      icon = Icon.createWithResource(this.mContext, paramInt);
    } else {
      icon = null;
    } 
    return setSmallIcon(icon);
  }
  
  public Builder setSmallIcon(int paramInt1, int paramInt2) {
    this.mN.iconLevel = paramInt2;
    return setSmallIcon(paramInt1);
  }
  
  public Builder setSmallIcon(Icon paramIcon) {
    this.mN.setSmallIcon(paramIcon);
    if (paramIcon != null && paramIcon.getType() == 2)
      this.mN.icon = paramIcon.getResId(); 
    return this;
  }
  
  public Builder setSortKey(String paramString) {
    Notification.access$1602(this.mN, paramString);
    return this;
  }
  
  @Deprecated
  public Builder setSound(Uri paramUri) {
    this.mN.sound = paramUri;
    this.mN.audioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
    return this;
  }
  
  @Deprecated
  public Builder setSound(Uri paramUri, int paramInt) {
    PlayerBase.deprecateStreamTypeForPlayback(paramInt, "Notification", "setSound()");
    this.mN.sound = paramUri;
    this.mN.audioStreamType = paramInt;
    return this;
  }
  
  @Deprecated
  public Builder setSound(Uri paramUri, AudioAttributes paramAudioAttributes) {
    this.mN.sound = paramUri;
    this.mN.audioAttributes = paramAudioAttributes;
    return this;
  }
  
  public Builder setStyle(Notification.Style paramStyle) {
    if (this.mStyle != paramStyle) {
      this.mStyle = paramStyle;
      if (paramStyle != null) {
        paramStyle.setBuilder(this);
        this.mN.extras.putString("android.template", paramStyle.getClass().getName());
      } else {
        this.mN.extras.remove("android.template");
      } 
    } 
    return this;
  }
  
  public Builder setSubText(CharSequence paramCharSequence) {
    this.mN.extras.putCharSequence("android.subText", Notification.safeCharSequence(paramCharSequence));
    return this;
  }
  
  public Builder setTicker(CharSequence paramCharSequence) {
    this.mN.tickerText = Notification.safeCharSequence(paramCharSequence);
    return this;
  }
  
  @Deprecated
  public Builder setTicker(CharSequence paramCharSequence, RemoteViews paramRemoteViews) {
    setTicker(paramCharSequence);
    return this;
  }
  
  @Deprecated
  public Builder setTimeout(long paramLong) {
    Notification.access$1202(this.mN, paramLong);
    return this;
  }
  
  public Builder setTimeoutAfter(long paramLong) {
    Notification.access$1202(this.mN, paramLong);
    return this;
  }
  
  public Builder setUsesChronometer(boolean paramBoolean) {
    this.mN.extras.putBoolean("android.showChronometer", paramBoolean);
    return this;
  }
  
  @Deprecated
  public Builder setVibrate(long[] paramArrayOflong) {
    this.mN.vibrate = paramArrayOflong;
    return this;
  }
  
  public Builder setVisibility(int paramInt) {
    this.mN.visibility = paramInt;
    return this;
  }
  
  public Builder setWhen(long paramLong) {
    this.mN.when = paramLong;
    return this;
  }
  
  public boolean usesStandardHeader() {
    boolean bool2;
    boolean bool3;
    boolean bool = Notification.access$1700(this.mN);
    boolean bool1 = true;
    if (bool)
      return true; 
    if ((this.mContext.getApplicationInfo()).targetSdkVersion >= 24 && this.mN.contentView == null && this.mN.bigContentView == null)
      return true; 
    if (this.mN.contentView == null || Notification.access$1800().contains(Integer.valueOf(this.mN.contentView.getLayoutId()))) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (this.mN.bigContentView == null || Notification.access$1800().contains(Integer.valueOf(this.mN.bigContentView.getLayoutId()))) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if (!bool2 || !bool3)
      bool1 = false; 
    return bool1;
  }
  
  public boolean usesTemplate() {
    if (this.mN.contentView != null || this.mN.headsUpContentView != null || this.mN.bigContentView != null) {
      Notification.Style style = this.mStyle;
      return (style != null && style.displayCustomViewInline());
    } 
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
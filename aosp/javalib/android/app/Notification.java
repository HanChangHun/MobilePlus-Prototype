package android.app;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.Intent;
import android.content.LocusId;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.media.PlayerBase;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
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
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.util.proto.ProtoOutputStream;
import android.widget.RemoteViews;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.ContrastColorUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class Notification implements Parcelable {
  public static final AudioAttributes AUDIO_ATTRIBUTES_DEFAULT;
  
  public static final int BADGE_ICON_LARGE = 2;
  
  public static final int BADGE_ICON_NONE = 0;
  
  public static final int BADGE_ICON_SMALL = 1;
  
  public static final String CATEGORY_ALARM = "alarm";
  
  public static final String CATEGORY_CALL = "call";
  
  @SystemApi
  public static final String CATEGORY_CAR_EMERGENCY = "car_emergency";
  
  @SystemApi
  public static final String CATEGORY_CAR_INFORMATION = "car_information";
  
  @SystemApi
  public static final String CATEGORY_CAR_WARNING = "car_warning";
  
  public static final String CATEGORY_EMAIL = "email";
  
  public static final String CATEGORY_ERROR = "err";
  
  public static final String CATEGORY_EVENT = "event";
  
  public static final String CATEGORY_MESSAGE = "msg";
  
  public static final String CATEGORY_NAVIGATION = "navigation";
  
  public static final String CATEGORY_PROGRESS = "progress";
  
  public static final String CATEGORY_PROMO = "promo";
  
  public static final String CATEGORY_RECOMMENDATION = "recommendation";
  
  public static final String CATEGORY_REMINDER = "reminder";
  
  public static final String CATEGORY_SERVICE = "service";
  
  public static final String CATEGORY_SOCIAL = "social";
  
  public static final String CATEGORY_STATUS = "status";
  
  public static final String CATEGORY_SYSTEM = "sys";
  
  public static final String CATEGORY_TRANSPORT = "transport";
  
  public static final int COLOR_DEFAULT = 0;
  
  public static final int COLOR_INVALID = 1;
  
  public static final Parcelable.Creator<Notification> CREATOR;
  
  public static final int DEFAULT_ALL = -1;
  
  public static final int DEFAULT_LIGHTS = 4;
  
  public static final int DEFAULT_SOUND = 1;
  
  public static final int DEFAULT_VIBRATE = 2;
  
  @SystemApi
  public static final String EXTRA_ALLOW_DURING_SETUP = "android.allowDuringSetup";
  
  public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
  
  public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
  
  public static final String EXTRA_BIG_TEXT = "android.bigText";
  
  public static final String EXTRA_BUILDER_APPLICATION_INFO = "android.appInfo";
  
  public static final String EXTRA_CHANNEL_GROUP_ID = "android.intent.extra.CHANNEL_GROUP_ID";
  
  public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";
  
  public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";
  
  public static final String EXTRA_COLORIZED = "android.colorized";
  
  public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
  
  public static final String EXTRA_CONTAINS_CUSTOM_VIEW = "android.contains.customView";
  
  public static final String EXTRA_CONVERSATION_ICON = "android.conversationIcon";
  
  public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
  
  public static final String EXTRA_CONVERSATION_UNREAD_MESSAGE_COUNT = "android.conversationUnreadMessageCount";
  
  public static final String EXTRA_FOREGROUND_APPS = "android.foregroundApps";
  
  public static final String EXTRA_HIDE_SMART_REPLIES = "android.hideSmartReplies";
  
  public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";
  
  public static final String EXTRA_INFO_TEXT = "android.infoText";
  
  public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
  
  @Deprecated
  public static final String EXTRA_LARGE_ICON = "android.largeIcon";
  
  public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
  
  public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
  
  public static final String EXTRA_MESSAGES = "android.messages";
  
  public static final String EXTRA_MESSAGING_PERSON = "android.messagingUser";
  
  public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";
  
  public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";
  
  public static final String EXTRA_PEOPLE = "android.people";
  
  public static final String EXTRA_PEOPLE_LIST = "android.people.list";
  
  public static final String EXTRA_PICTURE = "android.picture";
  
  public static final String EXTRA_PROGRESS = "android.progress";
  
  public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
  
  public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
  
  public static final String EXTRA_REDUCED_IMAGES = "android.reduced.images";
  
  public static final String EXTRA_REMOTE_INPUT_DRAFT = "android.remoteInputDraft";
  
  public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
  
  public static final String EXTRA_REMOTE_INPUT_HISTORY_ITEMS = "android.remoteInputHistoryItems";
  
  public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
  
  public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
  
  public static final String EXTRA_SHOW_REMOTE_INPUT_SPINNER = "android.remoteInputSpinner";
  
  public static final String EXTRA_SHOW_WHEN = "android.showWhen";
  
  @Deprecated
  public static final String EXTRA_SMALL_ICON = "android.icon";
  
  @SystemApi
  public static final String EXTRA_SUBSTITUTE_APP_NAME = "android.substName";
  
  public static final String EXTRA_SUB_TEXT = "android.subText";
  
  public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
  
  public static final String EXTRA_TEMPLATE = "android.template";
  
  public static final String EXTRA_TEXT = "android.text";
  
  public static final String EXTRA_TEXT_LINES = "android.textLines";
  
  public static final String EXTRA_TITLE = "android.title";
  
  public static final String EXTRA_TITLE_BIG = "android.title.big";
  
  @SystemApi
  public static final int FLAG_AUTOGROUP_SUMMARY = 1024;
  
  public static final int FLAG_AUTO_CANCEL = 16;
  
  public static final int FLAG_BUBBLE = 4096;
  
  public static final int FLAG_CAN_COLORIZE = 2048;
  
  public static final int FLAG_FOREGROUND_SERVICE = 64;
  
  public static final int FLAG_GROUP_SUMMARY = 512;
  
  @Deprecated
  public static final int FLAG_HIGH_PRIORITY = 128;
  
  public static final int FLAG_INSISTENT = 4;
  
  public static final int FLAG_LOCAL_ONLY = 256;
  
  public static final int FLAG_NO_CLEAR = 32;
  
  public static final int FLAG_ONGOING_EVENT = 2;
  
  public static final int FLAG_ONLY_ALERT_ONCE = 8;
  
  @Deprecated
  public static final int FLAG_SHOW_LIGHTS = 1;
  
  public static final int GROUP_ALERT_ALL = 0;
  
  public static final int GROUP_ALERT_CHILDREN = 2;
  
  public static final int GROUP_ALERT_SUMMARY = 1;
  
  public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";
  
  public static final int MAX_ACTION_BUTTONS = 3;
  
  private static final int MAX_CHARSEQUENCE_LENGTH = 1024;
  
  private static final int MAX_REPLY_HISTORY = 5;
  
  @Deprecated
  public static final int PRIORITY_DEFAULT = 0;
  
  @Deprecated
  public static final int PRIORITY_HIGH = 1;
  
  @Deprecated
  public static final int PRIORITY_LOW = -1;
  
  @Deprecated
  public static final int PRIORITY_MAX = 2;
  
  @Deprecated
  public static final int PRIORITY_MIN = -2;
  
  private static final ArraySet<Integer> STANDARD_LAYOUTS;
  
  @Deprecated
  public static final int STREAM_DEFAULT = -1;
  
  private static final String TAG = "Notification";
  
  public static final int VISIBILITY_PRIVATE = 0;
  
  public static final int VISIBILITY_PUBLIC = 1;
  
  public static final int VISIBILITY_SECRET = -1;
  
  public static IBinder processWhitelistToken;
  
  public Action[] actions;
  
  public ArraySet<PendingIntent> allPendingIntents;
  
  @Deprecated
  public AudioAttributes audioAttributes = AUDIO_ATTRIBUTES_DEFAULT;
  
  @Deprecated
  public int audioStreamType = -1;
  
  @Deprecated
  public RemoteViews bigContentView;
  
  public String category;
  
  public int color = 0;
  
  public PendingIntent contentIntent;
  
  @Deprecated
  public RemoteViews contentView;
  
  private long creationTime;
  
  @Deprecated
  public int defaults;
  
  public PendingIntent deleteIntent;
  
  public Bundle extras = new Bundle();
  
  public int flags;
  
  public PendingIntent fullScreenIntent;
  
  @Deprecated
  public RemoteViews headsUpContentView;
  
  @Deprecated
  public int icon;
  
  public int iconLevel;
  
  @Deprecated
  public Bitmap largeIcon;
  
  @Deprecated
  public int ledARGB;
  
  @Deprecated
  public int ledOffMS;
  
  @Deprecated
  public int ledOnMS;
  
  private boolean mAllowSystemGeneratedContextualActions = true;
  
  private int mBadgeIcon = 0;
  
  private BubbleMetadata mBubbleMetadata;
  
  private String mChannelId;
  
  private int mGroupAlertBehavior = 0;
  
  private String mGroupKey;
  
  private Icon mLargeIcon;
  
  private LocusId mLocusId;
  
  private CharSequence mSettingsText;
  
  private String mShortcutId;
  
  private Icon mSmallIcon;
  
  private String mSortKey;
  
  private long mTimeout;
  
  private boolean mUsesStandardHeader;
  
  private IBinder mWhitelistToken;
  
  public int number = 0;
  
  @Deprecated
  public int priority;
  
  public Notification publicVersion;
  
  @Deprecated
  public Uri sound;
  
  public CharSequence tickerText;
  
  @Deprecated
  public RemoteViews tickerView;
  
  @Deprecated
  public long[] vibrate;
  
  public int visibility;
  
  public long when;
  
  static {
    ArraySet<Integer> arraySet = new ArraySet();
    STANDARD_LAYOUTS = arraySet;
    arraySet.add(Integer.valueOf(17367208));
    STANDARD_LAYOUTS.add(Integer.valueOf(17367209));
    STANDARD_LAYOUTS.add(Integer.valueOf(17367211));
    STANDARD_LAYOUTS.add(Integer.valueOf(17367212));
    STANDARD_LAYOUTS.add(Integer.valueOf(17367214));
    STANDARD_LAYOUTS.add(Integer.valueOf(17367216));
    STANDARD_LAYOUTS.add(Integer.valueOf(17367213));
    STANDARD_LAYOUTS.add(Integer.valueOf(17367215));
    STANDARD_LAYOUTS.add(Integer.valueOf(17367210));
    STANDARD_LAYOUTS.add(Integer.valueOf(17367207));
    AUDIO_ATTRIBUTES_DEFAULT = (new AudioAttributes.Builder()).setContentType(4).setUsage(5).build();
    CREATOR = new Parcelable.Creator<Notification>() {
        public Notification createFromParcel(Parcel param1Parcel) {
          return new Notification(param1Parcel);
        }
        
        public Notification[] newArray(int param1Int) {
          return new Notification[param1Int];
        }
      };
  }
  
  public Notification() {
    this.when = System.currentTimeMillis();
    this.creationTime = System.currentTimeMillis();
    this.priority = 0;
  }
  
  @Deprecated
  public Notification(int paramInt, CharSequence paramCharSequence, long paramLong) {
    this.icon = paramInt;
    this.tickerText = paramCharSequence;
    this.when = paramLong;
    this.creationTime = System.currentTimeMillis();
  }
  
  public Notification(Context paramContext, int paramInt, CharSequence paramCharSequence1, long paramLong, CharSequence paramCharSequence2, CharSequence paramCharSequence3, Intent paramIntent) {
    (new Builder(paramContext)).setWhen(paramLong).setSmallIcon(paramInt).setTicker(paramCharSequence1).setContentTitle(paramCharSequence2).setContentText(paramCharSequence3).setContentIntent(PendingIntent.getActivity(paramContext, 0, paramIntent, 0)).buildInto(this);
  }
  
  public Notification(Parcel paramParcel) {
    readFromParcelImpl(paramParcel);
    this.allPendingIntents = paramParcel.readArraySet(null);
  }
  
  public static void addFieldsFromContext(Context paramContext, Notification paramNotification) {
    addFieldsFromContext(paramContext.getApplicationInfo(), paramNotification);
  }
  
  public static void addFieldsFromContext(ApplicationInfo paramApplicationInfo, Notification paramNotification) {
    paramNotification.extras.putParcelable("android.appInfo", (Parcelable)paramApplicationInfo);
  }
  
  public static boolean areActionsVisiblyDifferent(Notification paramNotification1, Notification paramNotification2) {
    Action[] arrayOfAction1 = paramNotification1.actions;
    Action[] arrayOfAction2 = paramNotification2.actions;
    if ((arrayOfAction1 == null && arrayOfAction2 != null) || (arrayOfAction1 != null && arrayOfAction2 == null))
      return true; 
    if (arrayOfAction1 != null && arrayOfAction2 != null) {
      if (arrayOfAction1.length != arrayOfAction2.length)
        return true; 
      for (byte b = 0; b < arrayOfAction1.length; b++) {
        if (!Objects.equals(String.valueOf((arrayOfAction1[b]).title), String.valueOf((arrayOfAction2[b]).title)))
          return true; 
        RemoteInput[] arrayOfRemoteInput2 = arrayOfAction1[b].getRemoteInputs();
        RemoteInput[] arrayOfRemoteInput3 = arrayOfAction2[b].getRemoteInputs();
        RemoteInput[] arrayOfRemoteInput1 = arrayOfRemoteInput2;
        if (arrayOfRemoteInput2 == null)
          arrayOfRemoteInput1 = new RemoteInput[0]; 
        arrayOfRemoteInput2 = arrayOfRemoteInput3;
        if (arrayOfRemoteInput3 == null)
          arrayOfRemoteInput2 = new RemoteInput[0]; 
        if (arrayOfRemoteInput1.length != arrayOfRemoteInput2.length)
          return true; 
        for (byte b1 = 0; b1 < arrayOfRemoteInput1.length; b1++) {
          if (!Objects.equals(String.valueOf(arrayOfRemoteInput1[b1].getLabel()), String.valueOf(arrayOfRemoteInput2[b1].getLabel())))
            return true; 
        } 
      } 
    } 
    return false;
  }
  
  public static boolean areRemoteViewsChanged(Builder paramBuilder1, Builder paramBuilder2) {
    return !Objects.equals(Boolean.valueOf(paramBuilder1.usesStandardHeader()), Boolean.valueOf(paramBuilder2.usesStandardHeader())) ? true : (areRemoteViewsChanged(paramBuilder1.mN.contentView, paramBuilder2.mN.contentView) ? true : (areRemoteViewsChanged(paramBuilder1.mN.bigContentView, paramBuilder2.mN.bigContentView) ? true : (areRemoteViewsChanged(paramBuilder1.mN.headsUpContentView, paramBuilder2.mN.headsUpContentView))));
  }
  
  private static boolean areRemoteViewsChanged(RemoteViews paramRemoteViews1, RemoteViews paramRemoteViews2) {
    return (paramRemoteViews1 == null && paramRemoteViews2 == null) ? false : (((paramRemoteViews1 == null && paramRemoteViews2 != null) || (paramRemoteViews1 != null && paramRemoteViews2 == null)) ? true : (!Objects.equals(Integer.valueOf(paramRemoteViews1.getLayoutId()), Integer.valueOf(paramRemoteViews2.getLayoutId())) ? true : (!Objects.equals(Integer.valueOf(paramRemoteViews1.getSequenceNumber()), Integer.valueOf(paramRemoteViews2.getSequenceNumber())))));
  }
  
  public static boolean areStyledNotificationsVisiblyDifferent(Builder paramBuilder1, Builder paramBuilder2) {
    Style style = paramBuilder1.getStyle();
    boolean bool = true;
    if (style == null) {
      if (paramBuilder2.getStyle() == null)
        bool = false; 
      return bool;
    } 
    return (paramBuilder2.getStyle() == null) ? true : paramBuilder1.getStyle().areNotificationsVisiblyDifferent(paramBuilder2.getStyle());
  }
  
  private void fixDuplicateExtra(Parcelable paramParcelable, String paramString) {
    if (paramParcelable != null && this.extras.getParcelable(paramString) != null)
      this.extras.putParcelable(paramString, paramParcelable); 
  }
  
  private void fixDuplicateExtras() {
    if (this.extras != null)
      fixDuplicateExtra((Parcelable)this.mLargeIcon, "android.largeIcon"); 
  }
  
  @SystemApi
  public static Class<? extends Style> getNotificationStyleClass(String paramString) {
    Class[] arrayOfClass = new Class[7];
    byte b = 0;
    arrayOfClass[0] = BigTextStyle.class;
    arrayOfClass[1] = BigPictureStyle.class;
    arrayOfClass[2] = InboxStyle.class;
    arrayOfClass[3] = MediaStyle.class;
    arrayOfClass[4] = DecoratedCustomViewStyle.class;
    arrayOfClass[5] = DecoratedMediaCustomViewStyle.class;
    arrayOfClass[6] = MessagingStyle.class;
    int i = arrayOfClass.length;
    while (b < i) {
      Class<? extends Style> clazz = arrayOfClass[b];
      if (paramString.equals(clazz.getName()))
        return clazz; 
      b++;
    } 
    return null;
  }
  
  private static <T extends Parcelable> T[] getParcelableArrayFromBundle(Bundle paramBundle, String paramString, Class<T> paramClass) {
    Parcelable[] arrayOfParcelable2 = paramBundle.getParcelableArray(paramString);
    if (Array.newInstance(paramClass, 0).getClass().isInstance(arrayOfParcelable2) || arrayOfParcelable2 == null)
      return (T[])arrayOfParcelable2; 
    Parcelable[] arrayOfParcelable1 = (Parcelable[])Array.newInstance(paramClass, arrayOfParcelable2.length);
    for (byte b = 0; b < arrayOfParcelable2.length; b++)
      arrayOfParcelable1[b] = arrayOfParcelable2[b]; 
    paramBundle.putParcelableArray(paramString, arrayOfParcelable1);
    return (T[])arrayOfParcelable1;
  }
  
  private boolean hasColorizedPermission() {
    boolean bool;
    if ((this.flags & 0x800) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean hasLargeIcon() {
    return (this.mLargeIcon != null || this.largeIcon != null);
  }
  
  public static String priorityToString(int paramInt) {
    if (paramInt != -2) {
      if (paramInt != -1) {
        if (paramInt != 0) {
          if (paramInt != 1) {
            if (paramInt != 2) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("UNKNOWN(");
              stringBuilder.append(String.valueOf(paramInt));
              stringBuilder.append(")");
              return stringBuilder.toString();
            } 
            return "MAX";
          } 
          return "HIGH";
        } 
        return "DEFAULT";
      } 
      return "LOW";
    } 
    return "MIN";
  }
  
  private void readFromParcelImpl(Parcel paramParcel) {
    paramParcel.readInt();
    IBinder iBinder = paramParcel.readStrongBinder();
    this.mWhitelistToken = iBinder;
    if (iBinder == null)
      this.mWhitelistToken = processWhitelistToken; 
    paramParcel.setClassCookie(PendingIntent.class, this.mWhitelistToken);
    this.when = paramParcel.readLong();
    this.creationTime = paramParcel.readLong();
    if (paramParcel.readInt() != 0) {
      Icon icon = (Icon)Icon.CREATOR.createFromParcel(paramParcel);
      this.mSmallIcon = icon;
      if (icon.getType() == 2)
        this.icon = this.mSmallIcon.getResId(); 
    } 
    this.number = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.contentIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.deleteIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.tickerText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.tickerView = (RemoteViews)RemoteViews.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.contentView = (RemoteViews)RemoteViews.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.mLargeIcon = (Icon)Icon.CREATOR.createFromParcel(paramParcel); 
    this.defaults = paramParcel.readInt();
    this.flags = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.sound = (Uri)Uri.CREATOR.createFromParcel(paramParcel); 
    this.audioStreamType = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.audioAttributes = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(paramParcel); 
    this.vibrate = paramParcel.createLongArray();
    this.ledARGB = paramParcel.readInt();
    this.ledOnMS = paramParcel.readInt();
    this.ledOffMS = paramParcel.readInt();
    this.iconLevel = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.fullScreenIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel); 
    this.priority = paramParcel.readInt();
    this.category = paramParcel.readString8();
    this.mGroupKey = paramParcel.readString8();
    this.mSortKey = paramParcel.readString8();
    this.extras = Bundle.setDefusable(paramParcel.readBundle(), true);
    fixDuplicateExtras();
    this.actions = (Action[])paramParcel.createTypedArray(Action.CREATOR);
    if (paramParcel.readInt() != 0)
      this.bigContentView = (RemoteViews)RemoteViews.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.headsUpContentView = (RemoteViews)RemoteViews.CREATOR.createFromParcel(paramParcel); 
    this.visibility = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.publicVersion = (Notification)CREATOR.createFromParcel(paramParcel); 
    this.color = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.mChannelId = paramParcel.readString8(); 
    this.mTimeout = paramParcel.readLong();
    if (paramParcel.readInt() != 0)
      this.mShortcutId = paramParcel.readString8(); 
    if (paramParcel.readInt() != 0)
      this.mLocusId = (LocusId)LocusId.CREATOR.createFromParcel(paramParcel); 
    this.mBadgeIcon = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.mSettingsText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel); 
    this.mGroupAlertBehavior = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.mBubbleMetadata = (BubbleMetadata)BubbleMetadata.CREATOR.createFromParcel(paramParcel); 
    this.mAllowSystemGeneratedContextualActions = paramParcel.readBoolean();
  }
  
  private void reduceImageSizesForRemoteView(RemoteViews paramRemoteViews, Context paramContext, boolean paramBoolean) {
    if (paramRemoteViews != null) {
      int i;
      Resources resources = paramContext.getResources();
      if (paramBoolean) {
        i = 17105352;
      } else {
        i = 17105351;
      } 
      int j = resources.getDimensionPixelSize(i);
      if (paramBoolean) {
        i = 17105350;
      } else {
        i = 17105349;
      } 
      paramRemoteViews.reduceImageSizes(j, resources.getDimensionPixelSize(i));
    } 
  }
  
  private static CharSequence removeTextSizeSpans(CharSequence paramCharSequence) {
    Object object;
    if (paramCharSequence instanceof Spanned) {
      Spanned spanned = (Spanned)paramCharSequence;
      int i = spanned.length();
      byte b = 0;
      Object[] arrayOfObject = spanned.getSpans(0, i, Object.class);
      SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned.toString());
      i = arrayOfObject.length;
      while (b < i) {
        object = arrayOfObject[b];
        Object object1 = object;
        Object object2 = object1;
        if (object1 instanceof CharacterStyle)
          object2 = ((CharacterStyle)object).getUnderlying(); 
        if (object2 instanceof TextAppearanceSpan) {
          object2 = object2;
          object2 = new TextAppearanceSpan(object2.getFamily(), object2.getTextStyle(), -1, object2.getTextColor(), object2.getLinkTextColor());
        } else {
          if (object2 instanceof android.text.style.RelativeSizeSpan || object2 instanceof android.text.style.AbsoluteSizeSpan)
            continue; 
          object2 = object;
        } 
        spannableStringBuilder.setSpan(object2, spanned.getSpanStart(object), spanned.getSpanEnd(object), spanned.getSpanFlags(object));
        continue;
        b++;
      } 
      return (CharSequence)spannableStringBuilder;
    } 
    return (CharSequence)object;
  }
  
  public static CharSequence safeCharSequence(CharSequence paramCharSequence) {
    if (paramCharSequence == null)
      return paramCharSequence; 
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence.length() > 1024)
      charSequence = paramCharSequence.subSequence(0, 1024); 
    if (charSequence instanceof Parcelable) {
      paramCharSequence = new StringBuilder();
      paramCharSequence.append("warning: ");
      paramCharSequence.append(charSequence.getClass().getCanonicalName());
      paramCharSequence.append(" instance is a custom Parcelable and not allowed in Notification");
      Log.e("Notification", paramCharSequence.toString());
      return charSequence.toString();
    } 
    return removeTextSizeSpans(charSequence);
  }
  
  public static String visibilityToString(int paramInt) {
    if (paramInt != -1) {
      if (paramInt != 0) {
        if (paramInt != 1) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("UNKNOWN(");
          stringBuilder.append(String.valueOf(paramInt));
          stringBuilder.append(")");
          return stringBuilder.toString();
        } 
        return "PUBLIC";
      } 
      return "PRIVATE";
    } 
    return "SECRET";
  }
  
  private void writeToParcelImpl(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(1);
    paramParcel.writeStrongBinder(this.mWhitelistToken);
    paramParcel.writeLong(this.when);
    paramParcel.writeLong(this.creationTime);
    if (this.mSmallIcon == null) {
      int i = this.icon;
      if (i != 0)
        this.mSmallIcon = Icon.createWithResource("", i); 
    } 
    if (this.mSmallIcon != null) {
      paramParcel.writeInt(1);
      this.mSmallIcon.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.number);
    if (this.contentIntent != null) {
      paramParcel.writeInt(1);
      this.contentIntent.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.deleteIntent != null) {
      paramParcel.writeInt(1);
      this.deleteIntent.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.tickerText != null) {
      paramParcel.writeInt(1);
      TextUtils.writeToParcel(this.tickerText, paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.tickerView != null) {
      paramParcel.writeInt(1);
      this.tickerView.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.contentView != null) {
      paramParcel.writeInt(1);
      this.contentView.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mLargeIcon == null) {
      Bitmap bitmap = this.largeIcon;
      if (bitmap != null)
        this.mLargeIcon = Icon.createWithBitmap(bitmap); 
    } 
    if (this.mLargeIcon != null) {
      paramParcel.writeInt(1);
      this.mLargeIcon.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.defaults);
    paramParcel.writeInt(this.flags);
    if (this.sound != null) {
      paramParcel.writeInt(1);
      this.sound.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.audioStreamType);
    if (this.audioAttributes != null) {
      paramParcel.writeInt(1);
      this.audioAttributes.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeLongArray(this.vibrate);
    paramParcel.writeInt(this.ledARGB);
    paramParcel.writeInt(this.ledOnMS);
    paramParcel.writeInt(this.ledOffMS);
    paramParcel.writeInt(this.iconLevel);
    if (this.fullScreenIntent != null) {
      paramParcel.writeInt(1);
      this.fullScreenIntent.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.priority);
    paramParcel.writeString8(this.category);
    paramParcel.writeString8(this.mGroupKey);
    paramParcel.writeString8(this.mSortKey);
    paramParcel.writeBundle(this.extras);
    paramParcel.writeTypedArray((Parcelable[])this.actions, 0);
    if (this.bigContentView != null) {
      paramParcel.writeInt(1);
      this.bigContentView.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.headsUpContentView != null) {
      paramParcel.writeInt(1);
      this.headsUpContentView.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.visibility);
    if (this.publicVersion != null) {
      paramParcel.writeInt(1);
      this.publicVersion.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.color);
    if (this.mChannelId != null) {
      paramParcel.writeInt(1);
      paramParcel.writeString8(this.mChannelId);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeLong(this.mTimeout);
    if (this.mShortcutId != null) {
      paramParcel.writeInt(1);
      paramParcel.writeString8(this.mShortcutId);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mLocusId != null) {
      paramParcel.writeInt(1);
      this.mLocusId.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.mBadgeIcon);
    if (this.mSettingsText != null) {
      paramParcel.writeInt(1);
      TextUtils.writeToParcel(this.mSettingsText, paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.mGroupAlertBehavior);
    if (this.mBubbleMetadata != null) {
      paramParcel.writeInt(1);
      this.mBubbleMetadata.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeBoolean(this.mAllowSystemGeneratedContextualActions);
  }
  
  public Notification clone() {
    Notification notification = new Notification();
    cloneInto(notification, true);
    return notification;
  }
  
  public void cloneInto(Notification paramNotification, boolean paramBoolean) {
    paramNotification.mWhitelistToken = this.mWhitelistToken;
    paramNotification.when = this.when;
    paramNotification.creationTime = this.creationTime;
    paramNotification.mSmallIcon = this.mSmallIcon;
    paramNotification.number = this.number;
    paramNotification.contentIntent = this.contentIntent;
    paramNotification.deleteIntent = this.deleteIntent;
    paramNotification.fullScreenIntent = this.fullScreenIntent;
    CharSequence charSequence = this.tickerText;
    if (charSequence != null)
      paramNotification.tickerText = charSequence.toString(); 
    if (paramBoolean) {
      RemoteViews remoteViews = this.tickerView;
      if (remoteViews != null)
        paramNotification.tickerView = remoteViews.clone(); 
    } 
    if (paramBoolean) {
      RemoteViews remoteViews = this.contentView;
      if (remoteViews != null)
        paramNotification.contentView = remoteViews.clone(); 
    } 
    if (paramBoolean) {
      Icon icon = this.mLargeIcon;
      if (icon != null)
        paramNotification.mLargeIcon = icon; 
    } 
    paramNotification.iconLevel = this.iconLevel;
    paramNotification.sound = this.sound;
    paramNotification.audioStreamType = this.audioStreamType;
    AudioAttributes audioAttributes = this.audioAttributes;
    if (audioAttributes != null)
      paramNotification.audioAttributes = (new AudioAttributes.Builder(audioAttributes)).build(); 
    long[] arrayOfLong = this.vibrate;
    if (arrayOfLong != null) {
      int i = arrayOfLong.length;
      long[] arrayOfLong1 = new long[i];
      paramNotification.vibrate = arrayOfLong1;
      System.arraycopy(arrayOfLong, 0, arrayOfLong1, 0, i);
    } 
    paramNotification.ledARGB = this.ledARGB;
    paramNotification.ledOnMS = this.ledOnMS;
    paramNotification.ledOffMS = this.ledOffMS;
    paramNotification.defaults = this.defaults;
    paramNotification.flags = this.flags;
    paramNotification.priority = this.priority;
    paramNotification.category = this.category;
    paramNotification.mGroupKey = this.mGroupKey;
    paramNotification.mSortKey = this.mSortKey;
    if (this.extras != null)
      try {
        Bundle bundle = new Bundle();
        this(this.extras);
        paramNotification.extras = bundle;
        bundle.size();
      } catch (BadParcelableException badParcelableException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("could not unparcel extras from notification: ");
        stringBuilder.append(this);
        Log.e("Notification", stringBuilder.toString(), (Throwable)badParcelableException);
        paramNotification.extras = null;
      }  
    if (!ArrayUtils.isEmpty((Collection)this.allPendingIntents))
      paramNotification.allPendingIntents = new ArraySet(this.allPendingIntents); 
    Action[] arrayOfAction = this.actions;
    if (arrayOfAction != null) {
      paramNotification.actions = new Action[arrayOfAction.length];
      byte b = 0;
      while (true) {
        arrayOfAction = this.actions;
        if (b < arrayOfAction.length) {
          if (arrayOfAction[b] != null)
            paramNotification.actions[b] = arrayOfAction[b].clone(); 
          b++;
          continue;
        } 
        break;
      } 
    } 
    if (paramBoolean) {
      RemoteViews remoteViews = this.bigContentView;
      if (remoteViews != null)
        paramNotification.bigContentView = remoteViews.clone(); 
    } 
    if (paramBoolean) {
      RemoteViews remoteViews = this.headsUpContentView;
      if (remoteViews != null)
        paramNotification.headsUpContentView = remoteViews.clone(); 
    } 
    paramNotification.visibility = this.visibility;
    if (this.publicVersion != null) {
      Notification notification = new Notification();
      paramNotification.publicVersion = notification;
      this.publicVersion.cloneInto(notification, paramBoolean);
    } 
    paramNotification.color = this.color;
    paramNotification.mChannelId = this.mChannelId;
    paramNotification.mTimeout = this.mTimeout;
    paramNotification.mShortcutId = this.mShortcutId;
    paramNotification.mLocusId = this.mLocusId;
    paramNotification.mBadgeIcon = this.mBadgeIcon;
    paramNotification.mSettingsText = this.mSettingsText;
    paramNotification.mGroupAlertBehavior = this.mGroupAlertBehavior;
    paramNotification.mBubbleMetadata = this.mBubbleMetadata;
    paramNotification.mAllowSystemGeneratedContextualActions = this.mAllowSystemGeneratedContextualActions;
    if (!paramBoolean)
      paramNotification.lightenPayload(); 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    boolean bool;
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1138166333441L, getChannelId());
    if (this.tickerText != null) {
      bool = true;
    } else {
      bool = false;
    } 
    paramProtoOutputStream.write(1133871366146L, bool);
    paramProtoOutputStream.write(1120986464259L, this.flags);
    paramProtoOutputStream.write(1120986464260L, this.color);
    paramProtoOutputStream.write(1138166333445L, this.category);
    paramProtoOutputStream.write(1138166333446L, this.mGroupKey);
    paramProtoOutputStream.write(1138166333447L, this.mSortKey);
    Action[] arrayOfAction = this.actions;
    if (arrayOfAction != null)
      paramProtoOutputStream.write(1120986464264L, arrayOfAction.length); 
    int i = this.visibility;
    if (i >= -1 && i <= 1)
      paramProtoOutputStream.write(1159641169929L, i); 
    Notification notification = this.publicVersion;
    if (notification != null)
      notification.dumpDebug(paramProtoOutputStream, 1146756268042L); 
    paramProtoOutputStream.end(paramLong);
  }
  
  public Pair<RemoteInput, Action> findRemoteInputActionPair(boolean paramBoolean) {
    Action[] arrayOfAction = this.actions;
    if (arrayOfAction == null)
      return null; 
    int i = arrayOfAction.length;
    for (byte b = 0; b < i; b++) {
      Action action = arrayOfAction[b];
      if (action.getRemoteInputs() != null) {
        RemoteInput remoteInput = null;
        for (RemoteInput remoteInput1 : action.getRemoteInputs()) {
          if (remoteInput1.getAllowFreeFormInput() || !paramBoolean)
            remoteInput = remoteInput1; 
        } 
        if (remoteInput != null)
          return Pair.create(remoteInput, action); 
      } 
    } 
    return null;
  }
  
  public boolean getAllowSystemGeneratedContextualActions() {
    return this.mAllowSystemGeneratedContextualActions;
  }
  
  public int getBadgeIconType() {
    return this.mBadgeIcon;
  }
  
  public BubbleMetadata getBubbleMetadata() {
    return this.mBubbleMetadata;
  }
  
  @Deprecated
  public String getChannel() {
    return this.mChannelId;
  }
  
  public String getChannelId() {
    return this.mChannelId;
  }
  
  public List<Action> getContextualActions() {
    if (this.actions == null)
      return Collections.emptyList(); 
    ArrayList<Action> arrayList = new ArrayList();
    for (Action action : this.actions) {
      if (action.isContextual())
        arrayList.add(action); 
    } 
    return arrayList;
  }
  
  public String getGroup() {
    return this.mGroupKey;
  }
  
  public int getGroupAlertBehavior() {
    return this.mGroupAlertBehavior;
  }
  
  public Icon getLargeIcon() {
    return this.mLargeIcon;
  }
  
  public LocusId getLocusId() {
    return this.mLocusId;
  }
  
  public Class<? extends Style> getNotificationStyle() {
    String str = this.extras.getString("android.template");
    return !TextUtils.isEmpty(str) ? getNotificationStyleClass(str) : null;
  }
  
  public CharSequence getSettingsText() {
    return this.mSettingsText;
  }
  
  public String getShortcutId() {
    return this.mShortcutId;
  }
  
  public Icon getSmallIcon() {
    return this.mSmallIcon;
  }
  
  public String getSortKey() {
    return this.mSortKey;
  }
  
  @Deprecated
  public long getTimeout() {
    return this.mTimeout;
  }
  
  public long getTimeoutAfter() {
    return this.mTimeout;
  }
  
  public boolean hasCompletedProgress() {
    boolean bool = this.extras.containsKey("android.progress");
    boolean bool1 = false;
    if (!bool || !this.extras.containsKey("android.progressMax"))
      return false; 
    if (this.extras.getInt("android.progressMax") == 0)
      return false; 
    if (this.extras.getInt("android.progress") == this.extras.getInt("android.progressMax"))
      bool1 = true; 
    return bool1;
  }
  
  public boolean hasMediaSession() {
    boolean bool;
    if (this.extras.getParcelable("android.mediaSession") != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isBubbleNotification() {
    boolean bool;
    if ((this.flags & 0x1000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isColorized() {
    null = isColorizedMedia();
    boolean bool = true;
    if (null)
      return true; 
    if (this.extras.getBoolean("android.colorized")) {
      null = bool;
      if (!hasColorizedPermission()) {
        if (isForegroundService())
          return bool; 
      } else {
        return null;
      } 
    } 
    return false;
  }
  
  public boolean isColorizedMedia() {
    Boolean bool;
    Class<? extends Style> clazz = getNotificationStyle();
    if (MediaStyle.class.equals(clazz)) {
      bool = (Boolean)this.extras.get("android.colorized");
      if ((bool == null || bool.booleanValue()) && hasMediaSession())
        return true; 
    } else if (DecoratedMediaCustomViewStyle.class.equals(bool) && this.extras.getBoolean("android.colorized") && hasMediaSession()) {
      return true;
    } 
    return false;
  }
  
  public boolean isForegroundService() {
    boolean bool;
    if ((this.flags & 0x40) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isGroupChild() {
    boolean bool;
    if (this.mGroupKey != null && (this.flags & 0x200) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isGroupSummary() {
    boolean bool;
    if (this.mGroupKey != null && (this.flags & 0x200) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isMediaNotification() {
    Class<? extends Style> clazz = getNotificationStyle();
    return MediaStyle.class.equals(clazz) ? true : (DecoratedMediaCustomViewStyle.class.equals(clazz));
  }
  
  public final void lightenPayload() {
    this.tickerView = null;
    this.contentView = null;
    this.bigContentView = null;
    this.headsUpContentView = null;
    this.mLargeIcon = null;
    Bundle bundle = this.extras;
    if (bundle != null && !bundle.isEmpty()) {
      Set set = this.extras.keySet();
      int i = set.size();
      String[] arrayOfString = (String[])set.toArray((Object[])new String[i]);
      for (byte b = 0; b < i; b++) {
        String str = arrayOfString[b];
        if (!"android.tv.EXTENSIONS".equals(str)) {
          Object object = this.extras.get(str);
          if (object != null && (object instanceof Parcelable || object instanceof Parcelable[] || object instanceof android.util.SparseArray || object instanceof ArrayList))
            this.extras.remove(str); 
        } 
      } 
    } 
  }
  
  void reduceImageSizes(Context paramContext) {
    if (this.extras.getBoolean("android.reduced.images"))
      return; 
    boolean bool = ActivityManager.isLowRamDeviceStatic();
    if (this.mLargeIcon != null || this.largeIcon != null) {
      Resources resources = paramContext.getResources();
      Class<? extends Style> clazz = getNotificationStyle();
      if (bool) {
        i = 17105380;
      } else {
        i = 17105379;
      } 
      int i = resources.getDimensionPixelSize(i);
      int j = i;
      if (MediaStyle.class.equals(clazz) || DecoratedMediaCustomViewStyle.class.equals(clazz)) {
        if (bool) {
          i = 17105370;
        } else {
          i = 17105369;
        } 
        j = resources.getDimensionPixelSize(i);
        if (bool) {
          i = 17105372;
        } else {
          i = 17105371;
        } 
        i = resources.getDimensionPixelSize(i);
      } 
      Icon icon = this.mLargeIcon;
      if (icon != null)
        icon.scaleDownIfNecessary(i, j); 
      Bitmap bitmap = this.largeIcon;
      if (bitmap != null)
        this.largeIcon = Icon.scaleDownIfNecessary(bitmap, i, j); 
    } 
    reduceImageSizesForRemoteView(this.contentView, paramContext, bool);
    reduceImageSizesForRemoteView(this.headsUpContentView, paramContext, bool);
    reduceImageSizesForRemoteView(this.bigContentView, paramContext, bool);
    this.extras.putBoolean("android.reduced.images", true);
  }
  
  public void setBubbleMetadata(BubbleMetadata paramBubbleMetadata) {
    this.mBubbleMetadata = paramBubbleMetadata;
  }
  
  @Deprecated
  public void setLatestEventInfo(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, PendingIntent paramPendingIntent) {
    if ((paramContext.getApplicationInfo()).targetSdkVersion > 22)
      Log.e("Notification", "setLatestEventInfo() is deprecated and you should feel deprecated.", new Throwable()); 
    if ((paramContext.getApplicationInfo()).targetSdkVersion < 24)
      this.extras.putBoolean("android.showWhen", true); 
    Builder builder = new Builder(paramContext, this);
    if (paramCharSequence1 != null)
      builder.setContentTitle(paramCharSequence1); 
    if (paramCharSequence2 != null)
      builder.setContentText(paramCharSequence2); 
    builder.setContentIntent(paramPendingIntent);
    builder.build();
  }
  
  public void setSmallIcon(Icon paramIcon) {
    this.mSmallIcon = paramIcon;
  }
  
  public boolean showsChronometer() {
    boolean bool;
    if (this.when != 0L && this.extras.getBoolean("android.showChronometer")) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean showsTime() {
    boolean bool;
    if (this.when != 0L && this.extras.getBoolean("android.showWhen")) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean suppressAlertingDueToGrouping() {
    return (isGroupSummary() && getGroupAlertBehavior() == 2) ? true : ((isGroupChild() && getGroupAlertBehavior() == 1));
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Notification(channel=");
    stringBuilder.append(getChannelId());
    stringBuilder.append(" shortcut=");
    stringBuilder.append(getShortcutId());
    stringBuilder.append(" contentView=");
    RemoteViews remoteViews = this.contentView;
    if (remoteViews != null) {
      stringBuilder.append(remoteViews.getPackage());
      stringBuilder.append("/0x");
      stringBuilder.append(Integer.toHexString(this.contentView.getLayoutId()));
    } else {
      stringBuilder.append("null");
    } 
    stringBuilder.append(" vibrate=");
    if ((this.defaults & 0x2) != 0) {
      stringBuilder.append("default");
    } else {
      long[] arrayOfLong = this.vibrate;
      if (arrayOfLong != null) {
        int i = arrayOfLong.length - 1;
        stringBuilder.append("[");
        for (byte b = 0; b < i; b++) {
          stringBuilder.append(this.vibrate[b]);
          stringBuilder.append(',');
        } 
        if (i != -1)
          stringBuilder.append(this.vibrate[i]); 
        stringBuilder.append("]");
      } else {
        stringBuilder.append("null");
      } 
    } 
    stringBuilder.append(" sound=");
    if ((this.defaults & 0x1) != 0) {
      stringBuilder.append("default");
    } else {
      Uri uri = this.sound;
      if (uri != null) {
        stringBuilder.append(uri.toString());
      } else {
        stringBuilder.append("null");
      } 
    } 
    if (this.tickerText != null)
      stringBuilder.append(" tick"); 
    stringBuilder.append(" defaults=0x");
    stringBuilder.append(Integer.toHexString(this.defaults));
    stringBuilder.append(" flags=0x");
    stringBuilder.append(Integer.toHexString(this.flags));
    stringBuilder.append(String.format(" color=0x%08x", new Object[] { Integer.valueOf(this.color) }));
    if (this.category != null) {
      stringBuilder.append(" category=");
      stringBuilder.append(this.category);
    } 
    if (this.mGroupKey != null) {
      stringBuilder.append(" groupKey=");
      stringBuilder.append(this.mGroupKey);
    } 
    if (this.mSortKey != null) {
      stringBuilder.append(" sortKey=");
      stringBuilder.append(this.mSortKey);
    } 
    if (this.actions != null) {
      stringBuilder.append(" actions=");
      stringBuilder.append(this.actions.length);
    } 
    stringBuilder.append(" vis=");
    stringBuilder.append(visibilityToString(this.visibility));
    if (this.publicVersion != null) {
      stringBuilder.append(" publicVersion=");
      stringBuilder.append(this.publicVersion.toString());
    } 
    if (this.mLocusId != null) {
      stringBuilder.append(" locusId=");
      stringBuilder.append(this.mLocusId);
    } 
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void visitUris(Consumer<Uri> paramConsumer) {
    paramConsumer.accept(this.sound);
    RemoteViews remoteViews = this.tickerView;
    if (remoteViews != null)
      remoteViews.visitUris(paramConsumer); 
    remoteViews = this.contentView;
    if (remoteViews != null)
      remoteViews.visitUris(paramConsumer); 
    remoteViews = this.bigContentView;
    if (remoteViews != null)
      remoteViews.visitUris(paramConsumer); 
    remoteViews = this.headsUpContentView;
    if (remoteViews != null)
      remoteViews.visitUris(paramConsumer); 
    Bundle bundle = this.extras;
    if (bundle != null) {
      paramConsumer.accept((Uri)bundle.getParcelable("android.audioContents"));
      if (this.extras.containsKey("android.backgroundImageUri"))
        paramConsumer.accept(Uri.parse(this.extras.getString("android.backgroundImageUri"))); 
      ArrayList arrayList = this.extras.getParcelableArrayList("android.people.list");
      if (arrayList != null && !arrayList.isEmpty())
        for (Person person1 : arrayList) {
          if (person1.getIconUri() != null)
            paramConsumer.accept(person1.getIconUri()); 
        }  
      Person person = (Person)this.extras.getParcelable("android.messagingUser");
      if (person != null && person.getIconUri() != null)
        paramConsumer.accept(person.getIconUri()); 
    } 
    if (MessagingStyle.class.equals(getNotificationStyle())) {
      bundle = this.extras;
      if (bundle != null) {
        Parcelable[] arrayOfParcelable = bundle.getParcelableArray("android.messages");
        if (!ArrayUtils.isEmpty((Object[])arrayOfParcelable))
          for (MessagingStyle.Message message : MessagingStyle.Message.getMessagesFromBundleArray(arrayOfParcelable)) {
            paramConsumer.accept(message.getDataUri());
            Person person = message.getSenderPerson();
            if (person != null && person.getIconUri() != null)
              paramConsumer.accept(person.getIconUri()); 
          }  
        arrayOfParcelable = this.extras.getParcelableArray("android.messages.historic");
        if (!ArrayUtils.isEmpty((Object[])arrayOfParcelable))
          for (MessagingStyle.Message message : MessagingStyle.Message.getMessagesFromBundleArray(arrayOfParcelable)) {
            paramConsumer.accept(message.getDataUri());
            Person person = message.getSenderPerson();
            if (person != null && person.getIconUri() != null)
              paramConsumer.accept(person.getIconUri()); 
          }  
      } 
    } 
    BubbleMetadata bubbleMetadata = this.mBubbleMetadata;
    if (bubbleMetadata != null && bubbleMetadata.getIcon() != null) {
      Icon icon = this.mBubbleMetadata.getIcon();
      int i = icon.getType();
      if (i == 6 || i == 4)
        paramConsumer.accept(icon.getUri()); 
    } 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield allPendingIntents : Landroid/util/ArraySet;
    //   4: ifnonnull -> 12
    //   7: iconst_1
    //   8: istore_3
    //   9: goto -> 14
    //   12: iconst_0
    //   13: istore_3
    //   14: iload_3
    //   15: ifeq -> 30
    //   18: new android/app/_$$Lambda$Notification$hOCsSZH8tWalFSbIzQ9x9IcPa9M
    //   21: dup
    //   22: aload_0
    //   23: aload_1
    //   24: invokespecial <init> : (Landroid/app/Notification;Landroid/os/Parcel;)V
    //   27: invokestatic setOnMarshaledListener : (Landroid/app/PendingIntent$OnMarshaledListener;)V
    //   30: aload_0
    //   31: aload_1
    //   32: iload_2
    //   33: invokespecial writeToParcelImpl : (Landroid/os/Parcel;I)V
    //   36: aload_0
    //   37: monitorenter
    //   38: aload_1
    //   39: aload_0
    //   40: getfield allPendingIntents : Landroid/util/ArraySet;
    //   43: invokevirtual writeArraySet : (Landroid/util/ArraySet;)V
    //   46: aload_0
    //   47: monitorexit
    //   48: iload_3
    //   49: ifeq -> 56
    //   52: aconst_null
    //   53: invokestatic setOnMarshaledListener : (Landroid/app/PendingIntent$OnMarshaledListener;)V
    //   56: return
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    //   62: astore_1
    //   63: iload_3
    //   64: ifeq -> 71
    //   67: aconst_null
    //   68: invokestatic setOnMarshaledListener : (Landroid/app/PendingIntent$OnMarshaledListener;)V
    //   71: aload_1
    //   72: athrow
    // Exception table:
    //   from	to	target	type
    //   30	38	62	finally
    //   38	48	57	finally
    //   58	60	57	finally
    //   60	62	62	finally
  }
  
  public static class Action implements Parcelable {
    public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {
        public Notification.Action createFromParcel(Parcel param2Parcel) {
          return new Notification.Action(param2Parcel);
        }
        
        public Notification.Action[] newArray(int param2Int) {
          return new Notification.Action[param2Int];
        }
      };
    
    private static final String EXTRA_DATA_ONLY_INPUTS = "android.extra.DATA_ONLY_INPUTS";
    
    public static final int SEMANTIC_ACTION_ARCHIVE = 5;
    
    public static final int SEMANTIC_ACTION_CALL = 10;
    
    public static final int SEMANTIC_ACTION_DELETE = 4;
    
    public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
    
    public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
    
    public static final int SEMANTIC_ACTION_MUTE = 6;
    
    public static final int SEMANTIC_ACTION_NONE = 0;
    
    public static final int SEMANTIC_ACTION_REPLY = 1;
    
    public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
    
    public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
    
    public static final int SEMANTIC_ACTION_UNMUTE = 7;
    
    public PendingIntent actionIntent;
    
    @Deprecated
    public int icon;
    
    private boolean mAllowGeneratedReplies;
    
    private final Bundle mExtras;
    
    private Icon mIcon;
    
    private final boolean mIsContextual;
    
    private final RemoteInput[] mRemoteInputs;
    
    private final int mSemanticAction;
    
    public CharSequence title;
    
    @Deprecated
    public Action(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this(Icon.createWithResource("", param1Int), param1CharSequence, param1PendingIntent, new Bundle(), null, true, 0, false);
    }
    
    private Action(Icon param1Icon, CharSequence param1CharSequence, PendingIntent param1PendingIntent, Bundle param1Bundle, RemoteInput[] param1ArrayOfRemoteInput, boolean param1Boolean1, int param1Int, boolean param1Boolean2) {
      this.mAllowGeneratedReplies = true;
      this.mIcon = param1Icon;
      if (param1Icon != null && param1Icon.getType() == 2)
        this.icon = param1Icon.getResId(); 
      this.title = param1CharSequence;
      this.actionIntent = param1PendingIntent;
      if (param1Bundle == null)
        param1Bundle = new Bundle(); 
      this.mExtras = param1Bundle;
      this.mRemoteInputs = param1ArrayOfRemoteInput;
      this.mAllowGeneratedReplies = param1Boolean1;
      this.mSemanticAction = param1Int;
      this.mIsContextual = param1Boolean2;
    }
    
    private Action(Parcel param1Parcel) {
      boolean bool2;
      boolean bool1 = true;
      this.mAllowGeneratedReplies = true;
      if (param1Parcel.readInt() != 0) {
        Icon icon = (Icon)Icon.CREATOR.createFromParcel(param1Parcel);
        this.mIcon = icon;
        if (icon.getType() == 2)
          this.icon = this.mIcon.getResId(); 
      } 
      this.title = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(param1Parcel);
      if (param1Parcel.readInt() == 1)
        this.actionIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(param1Parcel); 
      this.mExtras = Bundle.setDefusable(param1Parcel.readBundle(), true);
      this.mRemoteInputs = (RemoteInput[])param1Parcel.createTypedArray(RemoteInput.CREATOR);
      if (param1Parcel.readInt() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mAllowGeneratedReplies = bool2;
      this.mSemanticAction = param1Parcel.readInt();
      if (param1Parcel.readInt() == 1) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      this.mIsContextual = bool2;
    }
    
    public Action clone() {
      Bundle bundle;
      Icon icon = getIcon();
      CharSequence charSequence = this.title;
      PendingIntent pendingIntent = this.actionIntent;
      if (this.mExtras == null) {
        bundle = new Bundle();
      } else {
        bundle = new Bundle(this.mExtras);
      } 
      return new Action(icon, charSequence, pendingIntent, bundle, getRemoteInputs(), getAllowGeneratedReplies(), getSemanticAction(), isContextual());
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean getAllowGeneratedReplies() {
      return this.mAllowGeneratedReplies;
    }
    
    public RemoteInput[] getDataOnlyRemoteInputs() {
      return (RemoteInput[])Notification.getParcelableArrayFromBundle(this.mExtras, "android.extra.DATA_ONLY_INPUTS", (Class)RemoteInput.class);
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public Icon getIcon() {
      if (this.mIcon == null) {
        int i = this.icon;
        if (i != 0)
          this.mIcon = Icon.createWithResource("", i); 
      } 
      return this.mIcon;
    }
    
    public RemoteInput[] getRemoteInputs() {
      return this.mRemoteInputs;
    }
    
    public int getSemanticAction() {
      return this.mSemanticAction;
    }
    
    public boolean isContextual() {
      return this.mIsContextual;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      Icon icon = getIcon();
      if (icon != null) {
        param1Parcel.writeInt(1);
        icon.writeToParcel(param1Parcel, 0);
      } else {
        param1Parcel.writeInt(0);
      } 
      TextUtils.writeToParcel(this.title, param1Parcel, param1Int);
      if (this.actionIntent != null) {
        param1Parcel.writeInt(1);
        this.actionIntent.writeToParcel(param1Parcel, param1Int);
      } else {
        param1Parcel.writeInt(0);
      } 
      param1Parcel.writeBundle(this.mExtras);
      param1Parcel.writeTypedArray((Parcelable[])this.mRemoteInputs, param1Int);
      param1Parcel.writeInt(this.mAllowGeneratedReplies);
      param1Parcel.writeInt(this.mSemanticAction);
      param1Parcel.writeInt(this.mIsContextual);
    }
    
    public static final class Builder {
      private boolean mAllowGeneratedReplies = true;
      
      private final Bundle mExtras;
      
      private final Icon mIcon;
      
      private final PendingIntent mIntent;
      
      private boolean mIsContextual;
      
      private ArrayList<RemoteInput> mRemoteInputs;
      
      private int mSemanticAction;
      
      private final CharSequence mTitle;
      
      @Deprecated
      public Builder(int param2Int, CharSequence param2CharSequence, PendingIntent param2PendingIntent) {
        this(Icon.createWithResource("", param2Int), param2CharSequence, param2PendingIntent);
      }
      
      public Builder(Notification.Action param2Action) {
        this(param2Action.getIcon(), param2Action.title, param2Action.actionIntent, new Bundle(param2Action.mExtras), param2Action.getRemoteInputs(), param2Action.getAllowGeneratedReplies(), param2Action.getSemanticAction());
      }
      
      public Builder(Icon param2Icon, CharSequence param2CharSequence, PendingIntent param2PendingIntent) {
        this(param2Icon, param2CharSequence, param2PendingIntent, new Bundle(), null, true, 0);
      }
      
      private Builder(Icon param2Icon, CharSequence param2CharSequence, PendingIntent param2PendingIntent, Bundle param2Bundle, RemoteInput[] param2ArrayOfRemoteInput, boolean param2Boolean, int param2Int) {
        this.mIcon = param2Icon;
        this.mTitle = param2CharSequence;
        this.mIntent = param2PendingIntent;
        this.mExtras = param2Bundle;
        if (param2ArrayOfRemoteInput != null) {
          ArrayList<RemoteInput> arrayList = new ArrayList(param2ArrayOfRemoteInput.length);
          this.mRemoteInputs = arrayList;
          Collections.addAll(arrayList, param2ArrayOfRemoteInput);
        } 
        this.mAllowGeneratedReplies = param2Boolean;
        this.mSemanticAction = param2Int;
      }
      
      private void checkContextualActionNullFields() {
        if (!this.mIsContextual)
          return; 
        if (this.mIcon != null) {
          if (this.mIntent != null)
            return; 
          throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
        } 
        throw new NullPointerException("Contextual Actions must contain a valid icon");
      }
      
      public Builder addExtras(Bundle param2Bundle) {
        if (param2Bundle != null)
          this.mExtras.putAll(param2Bundle); 
        return this;
      }
      
      public Builder addRemoteInput(RemoteInput param2RemoteInput) {
        if (this.mRemoteInputs == null)
          this.mRemoteInputs = new ArrayList<>(); 
        this.mRemoteInputs.add(param2RemoteInput);
        return this;
      }
      
      public Notification.Action build() {
        RemoteInput[] arrayOfRemoteInput1;
        checkContextualActionNullFields();
        ArrayList<RemoteInput> arrayList1 = new ArrayList();
        RemoteInput[] arrayOfRemoteInput2 = (RemoteInput[])Notification.getParcelableArrayFromBundle(this.mExtras, "android.extra.DATA_ONLY_INPUTS", (Class)RemoteInput.class);
        if (arrayOfRemoteInput2 != null) {
          int i = arrayOfRemoteInput2.length;
          for (byte b = 0; b < i; b++)
            arrayList1.add(arrayOfRemoteInput2[b]); 
        } 
        ArrayList<RemoteInput> arrayList2 = new ArrayList();
        ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
        if (arrayList3 != null)
          for (RemoteInput remoteInput : arrayList3) {
            if (remoteInput.isDataOnly()) {
              arrayList1.add(remoteInput);
              continue;
            } 
            arrayList2.add(remoteInput);
          }  
        if (!arrayList1.isEmpty()) {
          RemoteInput[] arrayOfRemoteInput = arrayList1.<RemoteInput>toArray(new RemoteInput[arrayList1.size()]);
          this.mExtras.putParcelableArray("android.extra.DATA_ONLY_INPUTS", (Parcelable[])arrayOfRemoteInput);
        } 
        if (arrayList2.isEmpty()) {
          arrayList2 = null;
        } else {
          arrayOfRemoteInput1 = arrayList2.<RemoteInput>toArray(new RemoteInput[arrayList2.size()]);
        } 
        return new Notification.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayOfRemoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mIsContextual);
      }
      
      public Builder extend(Notification.Action.Extender param2Extender) {
        param2Extender.extend(this);
        return this;
      }
      
      public Bundle getExtras() {
        return this.mExtras;
      }
      
      public Builder setAllowGeneratedReplies(boolean param2Boolean) {
        this.mAllowGeneratedReplies = param2Boolean;
        return this;
      }
      
      public Builder setContextual(boolean param2Boolean) {
        this.mIsContextual = param2Boolean;
        return this;
      }
      
      public Builder setSemanticAction(int param2Int) {
        this.mSemanticAction = param2Int;
        return this;
      }
    }
    
    public static interface Extender {
      Notification.Action.Builder extend(Notification.Action.Builder param2Builder);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface SemanticAction {}
    
    public static final class WearableExtender implements Extender {
      private static final int DEFAULT_FLAGS = 1;
      
      private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
      
      private static final int FLAG_AVAILABLE_OFFLINE = 1;
      
      private static final int FLAG_HINT_DISPLAY_INLINE = 4;
      
      private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
      
      private static final String KEY_CANCEL_LABEL = "cancelLabel";
      
      private static final String KEY_CONFIRM_LABEL = "confirmLabel";
      
      private static final String KEY_FLAGS = "flags";
      
      private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
      
      private CharSequence mCancelLabel;
      
      private CharSequence mConfirmLabel;
      
      private int mFlags = 1;
      
      private CharSequence mInProgressLabel;
      
      public WearableExtender() {}
      
      public WearableExtender(Notification.Action param2Action) {
        Bundle bundle = param2Action.getExtras().getBundle("android.wearable.EXTENSIONS");
        if (bundle != null) {
          this.mFlags = bundle.getInt("flags", 1);
          this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
          this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
          this.mCancelLabel = bundle.getCharSequence("cancelLabel");
        } 
      }
      
      private void setFlag(int param2Int, boolean param2Boolean) {
        if (param2Boolean) {
          this.mFlags |= param2Int;
        } else {
          this.mFlags &= param2Int;
        } 
      }
      
      public WearableExtender clone() {
        WearableExtender wearableExtender = new WearableExtender();
        wearableExtender.mFlags = this.mFlags;
        wearableExtender.mInProgressLabel = this.mInProgressLabel;
        wearableExtender.mConfirmLabel = this.mConfirmLabel;
        wearableExtender.mCancelLabel = this.mCancelLabel;
        return wearableExtender;
      }
      
      public Notification.Action.Builder extend(Notification.Action.Builder param2Builder) {
        Bundle bundle = new Bundle();
        int i = this.mFlags;
        if (i != 1)
          bundle.putInt("flags", i); 
        CharSequence charSequence = this.mInProgressLabel;
        if (charSequence != null)
          bundle.putCharSequence("inProgressLabel", charSequence); 
        charSequence = this.mConfirmLabel;
        if (charSequence != null)
          bundle.putCharSequence("confirmLabel", charSequence); 
        charSequence = this.mCancelLabel;
        if (charSequence != null)
          bundle.putCharSequence("cancelLabel", charSequence); 
        param2Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
        return param2Builder;
      }
      
      @Deprecated
      public CharSequence getCancelLabel() {
        return this.mCancelLabel;
      }
      
      @Deprecated
      public CharSequence getConfirmLabel() {
        return this.mConfirmLabel;
      }
      
      public boolean getHintDisplayActionInline() {
        boolean bool;
        if ((this.mFlags & 0x4) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public boolean getHintLaunchesActivity() {
        boolean bool;
        if ((this.mFlags & 0x2) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      @Deprecated
      public CharSequence getInProgressLabel() {
        return this.mInProgressLabel;
      }
      
      public boolean isAvailableOffline() {
        int i = this.mFlags;
        boolean bool = true;
        if ((i & 0x1) == 0)
          bool = false; 
        return bool;
      }
      
      public WearableExtender setAvailableOffline(boolean param2Boolean) {
        setFlag(1, param2Boolean);
        return this;
      }
      
      @Deprecated
      public WearableExtender setCancelLabel(CharSequence param2CharSequence) {
        this.mCancelLabel = param2CharSequence;
        return this;
      }
      
      @Deprecated
      public WearableExtender setConfirmLabel(CharSequence param2CharSequence) {
        this.mConfirmLabel = param2CharSequence;
        return this;
      }
      
      public WearableExtender setHintDisplayActionInline(boolean param2Boolean) {
        setFlag(4, param2Boolean);
        return this;
      }
      
      public WearableExtender setHintLaunchesActivity(boolean param2Boolean) {
        setFlag(2, param2Boolean);
        return this;
      }
      
      @Deprecated
      public WearableExtender setInProgressLabel(CharSequence param2CharSequence) {
        this.mInProgressLabel = param2CharSequence;
        return this;
      }
    }
  }
  
  class null implements Parcelable.Creator<Action> {
    public Notification.Action createFromParcel(Parcel param1Parcel) {
      return new Notification.Action(param1Parcel);
    }
    
    public Notification.Action[] newArray(int param1Int) {
      return new Notification.Action[param1Int];
    }
  }
  
  public static final class Builder {
    private boolean mAllowGeneratedReplies = true;
    
    private final Bundle mExtras;
    
    private final Icon mIcon;
    
    private final PendingIntent mIntent;
    
    private boolean mIsContextual;
    
    private ArrayList<RemoteInput> mRemoteInputs;
    
    private int mSemanticAction;
    
    private final CharSequence mTitle;
    
    @Deprecated
    public Builder(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this(Icon.createWithResource("", param1Int), param1CharSequence, param1PendingIntent);
    }
    
    public Builder(Notification.Action param1Action) {
      this(param1Action.getIcon(), param1Action.title, param1Action.actionIntent, new Bundle(param1Action.mExtras), param1Action.getRemoteInputs(), param1Action.getAllowGeneratedReplies(), param1Action.getSemanticAction());
    }
    
    public Builder(Icon param1Icon, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this(param1Icon, param1CharSequence, param1PendingIntent, new Bundle(), null, true, 0);
    }
    
    private Builder(Icon param1Icon, CharSequence param1CharSequence, PendingIntent param1PendingIntent, Bundle param1Bundle, RemoteInput[] param1ArrayOfRemoteInput, boolean param1Boolean, int param1Int) {
      this.mIcon = param1Icon;
      this.mTitle = param1CharSequence;
      this.mIntent = param1PendingIntent;
      this.mExtras = param1Bundle;
      if (param1ArrayOfRemoteInput != null) {
        ArrayList<RemoteInput> arrayList = new ArrayList(param1ArrayOfRemoteInput.length);
        this.mRemoteInputs = arrayList;
        Collections.addAll(arrayList, param1ArrayOfRemoteInput);
      } 
      this.mAllowGeneratedReplies = param1Boolean;
      this.mSemanticAction = param1Int;
    }
    
    private void checkContextualActionNullFields() {
      if (!this.mIsContextual)
        return; 
      if (this.mIcon != null) {
        if (this.mIntent != null)
          return; 
        throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
      } 
      throw new NullPointerException("Contextual Actions must contain a valid icon");
    }
    
    public Builder addExtras(Bundle param1Bundle) {
      if (param1Bundle != null)
        this.mExtras.putAll(param1Bundle); 
      return this;
    }
    
    public Builder addRemoteInput(RemoteInput param1RemoteInput) {
      if (this.mRemoteInputs == null)
        this.mRemoteInputs = new ArrayList<>(); 
      this.mRemoteInputs.add(param1RemoteInput);
      return this;
    }
    
    public Notification.Action build() {
      RemoteInput[] arrayOfRemoteInput1;
      checkContextualActionNullFields();
      ArrayList<RemoteInput> arrayList1 = new ArrayList();
      RemoteInput[] arrayOfRemoteInput2 = (RemoteInput[])Notification.getParcelableArrayFromBundle(this.mExtras, "android.extra.DATA_ONLY_INPUTS", (Class)RemoteInput.class);
      if (arrayOfRemoteInput2 != null) {
        int i = arrayOfRemoteInput2.length;
        for (byte b = 0; b < i; b++)
          arrayList1.add(arrayOfRemoteInput2[b]); 
      } 
      ArrayList<RemoteInput> arrayList2 = new ArrayList();
      ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
      if (arrayList3 != null)
        for (RemoteInput remoteInput : arrayList3) {
          if (remoteInput.isDataOnly()) {
            arrayList1.add(remoteInput);
            continue;
          } 
          arrayList2.add(remoteInput);
        }  
      if (!arrayList1.isEmpty()) {
        RemoteInput[] arrayOfRemoteInput = arrayList1.<RemoteInput>toArray(new RemoteInput[arrayList1.size()]);
        this.mExtras.putParcelableArray("android.extra.DATA_ONLY_INPUTS", (Parcelable[])arrayOfRemoteInput);
      } 
      if (arrayList2.isEmpty()) {
        arrayList2 = null;
      } else {
        arrayOfRemoteInput1 = arrayList2.<RemoteInput>toArray(new RemoteInput[arrayList2.size()]);
      } 
      return new Notification.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayOfRemoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mIsContextual);
    }
    
    public Builder extend(Notification.Action.Extender param1Extender) {
      param1Extender.extend(this);
      return this;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public Builder setAllowGeneratedReplies(boolean param1Boolean) {
      this.mAllowGeneratedReplies = param1Boolean;
      return this;
    }
    
    public Builder setContextual(boolean param1Boolean) {
      this.mIsContextual = param1Boolean;
      return this;
    }
    
    public Builder setSemanticAction(int param1Int) {
      this.mSemanticAction = param1Int;
      return this;
    }
  }
  
  public static interface Extender {
    Notification.Action.Builder extend(Notification.Action.Builder param1Builder);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SemanticAction {}
  
  public static final class WearableExtender implements Action.Extender {
    private static final int DEFAULT_FLAGS = 1;
    
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    
    private static final int FLAG_AVAILABLE_OFFLINE = 1;
    
    private static final int FLAG_HINT_DISPLAY_INLINE = 4;
    
    private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
    
    private static final String KEY_CANCEL_LABEL = "cancelLabel";
    
    private static final String KEY_CONFIRM_LABEL = "confirmLabel";
    
    private static final String KEY_FLAGS = "flags";
    
    private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
    
    private CharSequence mCancelLabel;
    
    private CharSequence mConfirmLabel;
    
    private int mFlags = 1;
    
    private CharSequence mInProgressLabel;
    
    public WearableExtender() {}
    
    public WearableExtender(Notification.Action param1Action) {
      Bundle bundle = param1Action.getExtras().getBundle("android.wearable.EXTENSIONS");
      if (bundle != null) {
        this.mFlags = bundle.getInt("flags", 1);
        this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
        this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
        this.mCancelLabel = bundle.getCharSequence("cancelLabel");
      } 
    }
    
    private void setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= param1Int;
      } else {
        this.mFlags &= param1Int;
      } 
    }
    
    public WearableExtender clone() {
      WearableExtender wearableExtender = new WearableExtender();
      wearableExtender.mFlags = this.mFlags;
      wearableExtender.mInProgressLabel = this.mInProgressLabel;
      wearableExtender.mConfirmLabel = this.mConfirmLabel;
      wearableExtender.mCancelLabel = this.mCancelLabel;
      return wearableExtender;
    }
    
    public Notification.Action.Builder extend(Notification.Action.Builder param1Builder) {
      Bundle bundle = new Bundle();
      int i = this.mFlags;
      if (i != 1)
        bundle.putInt("flags", i); 
      CharSequence charSequence = this.mInProgressLabel;
      if (charSequence != null)
        bundle.putCharSequence("inProgressLabel", charSequence); 
      charSequence = this.mConfirmLabel;
      if (charSequence != null)
        bundle.putCharSequence("confirmLabel", charSequence); 
      charSequence = this.mCancelLabel;
      if (charSequence != null)
        bundle.putCharSequence("cancelLabel", charSequence); 
      param1Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    @Deprecated
    public CharSequence getCancelLabel() {
      return this.mCancelLabel;
    }
    
    @Deprecated
    public CharSequence getConfirmLabel() {
      return this.mConfirmLabel;
    }
    
    public boolean getHintDisplayActionInline() {
      boolean bool;
      if ((this.mFlags & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean getHintLaunchesActivity() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Deprecated
    public CharSequence getInProgressLabel() {
      return this.mInProgressLabel;
    }
    
    public boolean isAvailableOffline() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public WearableExtender setAvailableOffline(boolean param1Boolean) {
      setFlag(1, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setCancelLabel(CharSequence param1CharSequence) {
      this.mCancelLabel = param1CharSequence;
      return this;
    }
    
    @Deprecated
    public WearableExtender setConfirmLabel(CharSequence param1CharSequence) {
      this.mConfirmLabel = param1CharSequence;
      return this;
    }
    
    public WearableExtender setHintDisplayActionInline(boolean param1Boolean) {
      setFlag(4, param1Boolean);
      return this;
    }
    
    public WearableExtender setHintLaunchesActivity(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setInProgressLabel(CharSequence param1CharSequence) {
      this.mInProgressLabel = param1CharSequence;
      return this;
    }
  }
  
  public static class BigPictureStyle extends Style {
    public static final int MIN_ASHMEM_BITMAP_SIZE = 131072;
    
    private Icon mBigLargeIcon;
    
    private boolean mBigLargeIconSet = false;
    
    private Bitmap mPicture;
    
    public BigPictureStyle() {}
    
    @Deprecated
    public BigPictureStyle(Notification.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    private static boolean areBitmapsObviouslyDifferent(Bitmap param1Bitmap1, Bitmap param1Bitmap2) {
      boolean bool = false;
      if (param1Bitmap1 == param1Bitmap2)
        return false; 
      if (param1Bitmap1 == null || param1Bitmap2 == null)
        return true; 
      if (param1Bitmap1.getWidth() != param1Bitmap2.getWidth() || param1Bitmap1.getHeight() != param1Bitmap2.getHeight() || param1Bitmap1.getConfig() != param1Bitmap2.getConfig() || param1Bitmap1.getGenerationId() != param1Bitmap2.getGenerationId())
        bool = true; 
      return bool;
    }
    
    public void addExtras(Bundle param1Bundle) {
      super.addExtras(param1Bundle);
      if (this.mBigLargeIconSet)
        param1Bundle.putParcelable("android.largeIcon.big", (Parcelable)this.mBigLargeIcon); 
      param1Bundle.putParcelable("android.picture", (Parcelable)this.mPicture);
    }
    
    public boolean areNotificationsVisiblyDifferent(Notification.Style param1Style) {
      if (param1Style == null || getClass() != param1Style.getClass())
        return true; 
      param1Style = param1Style;
      return areBitmapsObviouslyDifferent(getBigPicture(), param1Style.getBigPicture());
    }
    
    public BigPictureStyle bigLargeIcon(Bitmap param1Bitmap) {
      if (param1Bitmap != null) {
        Icon icon = Icon.createWithBitmap(param1Bitmap);
      } else {
        param1Bitmap = null;
      } 
      return bigLargeIcon((Icon)param1Bitmap);
    }
    
    public BigPictureStyle bigLargeIcon(Icon param1Icon) {
      this.mBigLargeIconSet = true;
      this.mBigLargeIcon = param1Icon;
      return this;
    }
    
    public BigPictureStyle bigPicture(Bitmap param1Bitmap) {
      this.mPicture = param1Bitmap;
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
        icon = this.mBuilder.mN.mLargeIcon;
        Notification.access$1402(this.mBuilder.mN, this.mBigLargeIcon);
        bitmap = this.mBuilder.mN.largeIcon;
        this.mBuilder.mN.largeIcon = null;
      } 
      Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().fillTextsFrom(this.mBuilder);
      RemoteViews remoteViews = getStandardView(this.mBuilder.getBigPictureLayoutResource(), standardTemplateParams, null);
      if (this.mSummaryTextSet) {
        remoteViews.setTextViewText(16909502, this.mBuilder.processTextSpans(this.mBuilder.processLegacyText(this.mSummaryText)));
        this.mBuilder.setTextViewColorSecondary(remoteViews, 16909502, standardTemplateParams);
        remoteViews.setViewVisibility(16909502, 0);
      } 
      this.mBuilder.setContentMinHeight(remoteViews, this.mBuilder.mN.hasLargeIcon());
      if (this.mBigLargeIconSet) {
        Notification.access$1402(this.mBuilder.mN, icon);
        this.mBuilder.mN.largeIcon = bitmap;
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
    
    public void reduceImageSizes(Context param1Context) {
      super.reduceImageSizes(param1Context);
      Resources resources = param1Context.getResources();
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
    
    protected void restoreFromExtras(Bundle param1Bundle) {
      super.restoreFromExtras(param1Bundle);
      if (param1Bundle.containsKey("android.largeIcon.big")) {
        this.mBigLargeIconSet = true;
        this.mBigLargeIcon = (Icon)param1Bundle.getParcelable("android.largeIcon.big");
      } 
      this.mPicture = (Bitmap)param1Bundle.getParcelable("android.picture");
    }
    
    public BigPictureStyle setBigContentTitle(CharSequence param1CharSequence) {
      internalSetBigContentTitle(Notification.safeCharSequence(param1CharSequence));
      return this;
    }
    
    public BigPictureStyle setSummaryText(CharSequence param1CharSequence) {
      internalSetSummaryText(Notification.safeCharSequence(param1CharSequence));
      return this;
    }
  }
  
  public static class BigTextStyle extends Style {
    private CharSequence mBigText;
    
    public BigTextStyle() {}
    
    @Deprecated
    public BigTextStyle(Notification.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    public void addExtras(Bundle param1Bundle) {
      super.addExtras(param1Bundle);
      param1Bundle.putCharSequence("android.bigText", this.mBigText);
    }
    
    public boolean areNotificationsVisiblyDifferent(Notification.Style param1Style) {
      if (param1Style == null || getClass() != param1Style.getClass())
        return true; 
      param1Style = param1Style;
      return true ^ Objects.equals(String.valueOf(getBigText()), String.valueOf(param1Style.getBigText()));
    }
    
    public BigTextStyle bigText(CharSequence param1CharSequence) {
      this.mBigText = Notification.safeCharSequence(param1CharSequence);
      return this;
    }
    
    public CharSequence getBigText() {
      return this.mBigText;
    }
    
    public RemoteViews makeBigContentView() {
      boolean bool;
      Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().fillTextsFrom(this.mBuilder).text(null);
      Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult();
      RemoteViews remoteViews = getStandardView(this.mBuilder.getBigTextLayoutResource(), standardTemplateParams, templateBindResult);
      remoteViews.setInt(16908791, "setImageEndMargin", templateBindResult.getIconMarginEnd());
      CharSequence charSequence1 = this.mBuilder.processLegacyText(this.mBigText);
      CharSequence charSequence2 = charSequence1;
      if (TextUtils.isEmpty(charSequence1))
        charSequence2 = this.mBuilder.processLegacyText(this.mBuilder.getAllExtras().getCharSequence("android.text")); 
      remoteViews.setTextViewText(16908791, this.mBuilder.processTextSpans(charSequence2));
      this.mBuilder.setTextViewColorSecondary(remoteViews, 16908791, standardTemplateParams);
      if (TextUtils.isEmpty(charSequence2)) {
        bool = true;
      } else {
        bool = false;
      } 
      remoteViews.setViewVisibility(16908791, bool);
      remoteViews.setBoolean(16908791, "setHasImage", templateBindResult.isRightIconContainerVisible());
      return remoteViews;
    }
    
    public RemoteViews makeContentView(boolean param1Boolean) {
      if (param1Boolean) {
        Notification.Builder.access$2902(this.mBuilder, this.mBuilder.mActions);
        Notification.Builder.access$3002(this.mBuilder, new ArrayList());
        RemoteViews remoteViews = makeBigContentView();
        Notification.Builder.access$3002(this.mBuilder, this.mBuilder.mOriginalActions);
        Notification.Builder.access$2902(this.mBuilder, null);
        return remoteViews;
      } 
      return super.makeContentView(param1Boolean);
    }
    
    public RemoteViews makeHeadsUpContentView(boolean param1Boolean) {
      return (param1Boolean && this.mBuilder.mActions.size() > 0) ? makeBigContentView() : super.makeHeadsUpContentView(param1Boolean);
    }
    
    protected void restoreFromExtras(Bundle param1Bundle) {
      super.restoreFromExtras(param1Bundle);
      this.mBigText = param1Bundle.getCharSequence("android.bigText");
    }
    
    public BigTextStyle setBigContentTitle(CharSequence param1CharSequence) {
      internalSetBigContentTitle(Notification.safeCharSequence(param1CharSequence));
      return this;
    }
    
    public BigTextStyle setSummaryText(CharSequence param1CharSequence) {
      internalSetSummaryText(Notification.safeCharSequence(param1CharSequence));
      return this;
    }
  }
  
  public static final class BubbleMetadata implements Parcelable {
    public static final Parcelable.Creator<BubbleMetadata> CREATOR = new Parcelable.Creator<BubbleMetadata>() {
        public Notification.BubbleMetadata createFromParcel(Parcel param2Parcel) {
          return new Notification.BubbleMetadata(param2Parcel);
        }
        
        public Notification.BubbleMetadata[] newArray(int param2Int) {
          return new Notification.BubbleMetadata[param2Int];
        }
      };
    
    public static final int FLAG_AUTO_EXPAND_BUBBLE = 1;
    
    public static final int FLAG_SUPPRESS_NOTIFICATION = 2;
    
    private PendingIntent mDeleteIntent;
    
    private int mDesiredHeight;
    
    private int mDesiredHeightResId;
    
    private int mFlags;
    
    private Icon mIcon;
    
    private PendingIntent mPendingIntent;
    
    private String mShortcutId;
    
    private BubbleMetadata(PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, Icon param1Icon, int param1Int1, int param1Int2, String param1String) {
      this.mPendingIntent = param1PendingIntent1;
      this.mIcon = param1Icon;
      this.mDesiredHeight = param1Int1;
      this.mDesiredHeightResId = param1Int2;
      this.mDeleteIntent = param1PendingIntent2;
      this.mShortcutId = param1String;
    }
    
    private BubbleMetadata(Parcel param1Parcel) {
      if (param1Parcel.readInt() != 0)
        this.mPendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(param1Parcel); 
      if (param1Parcel.readInt() != 0)
        this.mIcon = (Icon)Icon.CREATOR.createFromParcel(param1Parcel); 
      this.mDesiredHeight = param1Parcel.readInt();
      this.mFlags = param1Parcel.readInt();
      if (param1Parcel.readInt() != 0)
        this.mDeleteIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(param1Parcel); 
      this.mDesiredHeightResId = param1Parcel.readInt();
      if (param1Parcel.readInt() != 0)
        this.mShortcutId = param1Parcel.readString8(); 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean getAutoExpandBubble() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    @Deprecated
    public Icon getBubbleIcon() {
      return this.mIcon;
    }
    
    @Deprecated
    public PendingIntent getBubbleIntent() {
      return this.mPendingIntent;
    }
    
    public PendingIntent getDeleteIntent() {
      return this.mDeleteIntent;
    }
    
    public int getDesiredHeight() {
      return this.mDesiredHeight;
    }
    
    public int getDesiredHeightResId() {
      return this.mDesiredHeightResId;
    }
    
    public int getFlags() {
      return this.mFlags;
    }
    
    public Icon getIcon() {
      return this.mIcon;
    }
    
    public PendingIntent getIntent() {
      return this.mPendingIntent;
    }
    
    public String getShortcutId() {
      return this.mShortcutId;
    }
    
    public boolean isNotificationSuppressed() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void setFlags(int param1Int) {
      this.mFlags = param1Int;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      if (this.mPendingIntent != null) {
        param1Int = 1;
      } else {
        param1Int = 0;
      } 
      param1Parcel.writeInt(param1Int);
      PendingIntent pendingIntent2 = this.mPendingIntent;
      if (pendingIntent2 != null)
        pendingIntent2.writeToParcel(param1Parcel, 0); 
      if (this.mIcon != null) {
        param1Int = 1;
      } else {
        param1Int = 0;
      } 
      param1Parcel.writeInt(param1Int);
      Icon icon = this.mIcon;
      if (icon != null)
        icon.writeToParcel(param1Parcel, 0); 
      param1Parcel.writeInt(this.mDesiredHeight);
      param1Parcel.writeInt(this.mFlags);
      if (this.mDeleteIntent != null) {
        param1Int = 1;
      } else {
        param1Int = 0;
      } 
      param1Parcel.writeInt(param1Int);
      PendingIntent pendingIntent1 = this.mDeleteIntent;
      if (pendingIntent1 != null)
        pendingIntent1.writeToParcel(param1Parcel, 0); 
      param1Parcel.writeInt(this.mDesiredHeightResId);
      param1Parcel.writeInt(TextUtils.isEmpty(this.mShortcutId) ^ true);
      if (!TextUtils.isEmpty(this.mShortcutId))
        param1Parcel.writeString8(this.mShortcutId); 
    }
    
    public static final class Builder {
      private PendingIntent mDeleteIntent;
      
      private int mDesiredHeight;
      
      private int mDesiredHeightResId;
      
      private int mFlags;
      
      private Icon mIcon;
      
      private PendingIntent mPendingIntent;
      
      private String mShortcutId;
      
      @Deprecated
      public Builder() {}
      
      public Builder(PendingIntent param2PendingIntent, Icon param2Icon) {
        if (param2PendingIntent != null) {
          if (param2Icon != null) {
            if (param2Icon.getType() != 6 && param2Icon.getType() != 4)
              Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
            this.mPendingIntent = param2PendingIntent;
            this.mIcon = param2Icon;
            return;
          } 
          throw new NullPointerException("Bubbles require non-null icon");
        } 
        throw new NullPointerException("Bubble requires non-null pending intent");
      }
      
      public Builder(String param2String) {
        if (!TextUtils.isEmpty(param2String)) {
          this.mShortcutId = param2String;
          return;
        } 
        throw new NullPointerException("Bubble requires a non-null shortcut id");
      }
      
      public Notification.BubbleMetadata build() {
        if (this.mShortcutId != null || this.mPendingIntent != null) {
          if (this.mShortcutId != null || this.mIcon != null) {
            Notification.BubbleMetadata bubbleMetadata = new Notification.BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mShortcutId);
            bubbleMetadata.setFlags(this.mFlags);
            return bubbleMetadata;
          } 
          throw new NullPointerException("Must supply an icon or shortcut for the bubble");
        } 
        throw new NullPointerException("Must supply pending intent or shortcut to bubble");
      }
      
      @Deprecated
      public Builder createIntentBubble(PendingIntent param2PendingIntent, Icon param2Icon) {
        if (param2PendingIntent != null) {
          if (param2Icon != null) {
            if (param2Icon.getType() != 6 && param2Icon.getType() != 4)
              Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
            this.mShortcutId = null;
            this.mPendingIntent = param2PendingIntent;
            this.mIcon = param2Icon;
            return this;
          } 
          throw new IllegalArgumentException("Bubbles require non-null icon");
        } 
        throw new IllegalArgumentException("Bubble requires non-null pending intent");
      }
      
      @Deprecated
      public Builder createShortcutBubble(String param2String) {
        if (!TextUtils.isEmpty(param2String)) {
          this.mPendingIntent = null;
          this.mIcon = null;
        } 
        this.mShortcutId = param2String;
        return this;
      }
      
      public Builder setAutoExpandBubble(boolean param2Boolean) {
        setFlag(1, param2Boolean);
        return this;
      }
      
      public Builder setDeleteIntent(PendingIntent param2PendingIntent) {
        this.mDeleteIntent = param2PendingIntent;
        return this;
      }
      
      public Builder setDesiredHeight(int param2Int) {
        this.mDesiredHeight = Math.max(param2Int, 0);
        this.mDesiredHeightResId = 0;
        return this;
      }
      
      public Builder setDesiredHeightResId(int param2Int) {
        this.mDesiredHeightResId = param2Int;
        this.mDesiredHeight = 0;
        return this;
      }
      
      public Builder setFlag(int param2Int, boolean param2Boolean) {
        if (param2Boolean) {
          this.mFlags |= param2Int;
        } else {
          this.mFlags &= param2Int;
        } 
        return this;
      }
      
      public Builder setIcon(Icon param2Icon) {
        if (this.mShortcutId == null) {
          if (param2Icon != null) {
            if (param2Icon.getType() != 6 && param2Icon.getType() != 4)
              Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
            this.mIcon = param2Icon;
            return this;
          } 
          throw new NullPointerException("Bubbles require non-null icon");
        } 
        throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
      }
      
      public Builder setIntent(PendingIntent param2PendingIntent) {
        if (this.mShortcutId == null) {
          if (param2PendingIntent != null) {
            this.mPendingIntent = param2PendingIntent;
            return this;
          } 
          throw new NullPointerException("Bubble requires non-null pending intent");
        } 
        throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
      }
      
      public Builder setSuppressNotification(boolean param2Boolean) {
        setFlag(2, param2Boolean);
        return this;
      }
    }
  }
  
  class null implements Parcelable.Creator<BubbleMetadata> {
    public Notification.BubbleMetadata createFromParcel(Parcel param1Parcel) {
      return new Notification.BubbleMetadata(param1Parcel);
    }
    
    public Notification.BubbleMetadata[] newArray(int param1Int) {
      return new Notification.BubbleMetadata[param1Int];
    }
  }
  
  public static final class Builder {
    private PendingIntent mDeleteIntent;
    
    private int mDesiredHeight;
    
    private int mDesiredHeightResId;
    
    private int mFlags;
    
    private Icon mIcon;
    
    private PendingIntent mPendingIntent;
    
    private String mShortcutId;
    
    @Deprecated
    public Builder() {}
    
    public Builder(PendingIntent param1PendingIntent, Icon param1Icon) {
      if (param1PendingIntent != null) {
        if (param1Icon != null) {
          if (param1Icon.getType() != 6 && param1Icon.getType() != 4)
            Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
          this.mPendingIntent = param1PendingIntent;
          this.mIcon = param1Icon;
          return;
        } 
        throw new NullPointerException("Bubbles require non-null icon");
      } 
      throw new NullPointerException("Bubble requires non-null pending intent");
    }
    
    public Builder(String param1String) {
      if (!TextUtils.isEmpty(param1String)) {
        this.mShortcutId = param1String;
        return;
      } 
      throw new NullPointerException("Bubble requires a non-null shortcut id");
    }
    
    public Notification.BubbleMetadata build() {
      if (this.mShortcutId != null || this.mPendingIntent != null) {
        if (this.mShortcutId != null || this.mIcon != null) {
          Notification.BubbleMetadata bubbleMetadata = new Notification.BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mShortcutId);
          bubbleMetadata.setFlags(this.mFlags);
          return bubbleMetadata;
        } 
        throw new NullPointerException("Must supply an icon or shortcut for the bubble");
      } 
      throw new NullPointerException("Must supply pending intent or shortcut to bubble");
    }
    
    @Deprecated
    public Builder createIntentBubble(PendingIntent param1PendingIntent, Icon param1Icon) {
      if (param1PendingIntent != null) {
        if (param1Icon != null) {
          if (param1Icon.getType() != 6 && param1Icon.getType() != 4)
            Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
          this.mShortcutId = null;
          this.mPendingIntent = param1PendingIntent;
          this.mIcon = param1Icon;
          return this;
        } 
        throw new IllegalArgumentException("Bubbles require non-null icon");
      } 
      throw new IllegalArgumentException("Bubble requires non-null pending intent");
    }
    
    @Deprecated
    public Builder createShortcutBubble(String param1String) {
      if (!TextUtils.isEmpty(param1String)) {
        this.mPendingIntent = null;
        this.mIcon = null;
      } 
      this.mShortcutId = param1String;
      return this;
    }
    
    public Builder setAutoExpandBubble(boolean param1Boolean) {
      setFlag(1, param1Boolean);
      return this;
    }
    
    public Builder setDeleteIntent(PendingIntent param1PendingIntent) {
      this.mDeleteIntent = param1PendingIntent;
      return this;
    }
    
    public Builder setDesiredHeight(int param1Int) {
      this.mDesiredHeight = Math.max(param1Int, 0);
      this.mDesiredHeightResId = 0;
      return this;
    }
    
    public Builder setDesiredHeightResId(int param1Int) {
      this.mDesiredHeightResId = param1Int;
      this.mDesiredHeight = 0;
      return this;
    }
    
    public Builder setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= param1Int;
      } else {
        this.mFlags &= param1Int;
      } 
      return this;
    }
    
    public Builder setIcon(Icon param1Icon) {
      if (this.mShortcutId == null) {
        if (param1Icon != null) {
          if (param1Icon.getType() != 6 && param1Icon.getType() != 4)
            Log.w("Notification", "Bubbles work best with icons of TYPE_URI or TYPE_URI_ADAPTIVE_BITMAP. In the future, using an icon of this type will be required."); 
          this.mIcon = param1Icon;
          return this;
        } 
        throw new NullPointerException("Bubbles require non-null icon");
      } 
      throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
    }
    
    public Builder setIntent(PendingIntent param1PendingIntent) {
      if (this.mShortcutId == null) {
        if (param1PendingIntent != null) {
          this.mPendingIntent = param1PendingIntent;
          return this;
        } 
        throw new NullPointerException("Bubble requires non-null pending intent");
      } 
      throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
    }
    
    public Builder setSuppressNotification(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
  }
  
  public static class Builder {
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
    
    Notification.StandardTemplateParams mParams = new Notification.StandardTemplateParams();
    
    private ArrayList<Person> mPersonList = new ArrayList<>();
    
    private int mPrimaryTextColor = 1;
    
    private boolean mRebuildStyledRemoteViews;
    
    private int mSecondaryTextColor = 1;
    
    private Notification.Style mStyle;
    
    private int mTextColorsAreForBackground = 1;
    
    private boolean mTintActionButtons;
    
    private Bundle mUserExtras = new Bundle();
    
    @Deprecated
    public Builder(Context param1Context) {
      this(param1Context, (Notification)null);
    }
    
    public Builder(Context param1Context, Notification param1Notification) {
      this.mContext = param1Context;
      Resources resources = param1Context.getResources();
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
      if (param1Notification == null) {
        this.mN = new Notification();
        if ((param1Context.getApplicationInfo()).targetSdkVersion < 24)
          this.mN.extras.putBoolean("android.showWhen", true); 
        this.mN.priority = 0;
        this.mN.visibility = 0;
      } else {
        this.mN = param1Notification;
        if (param1Notification.actions != null)
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
    
    public Builder(Context param1Context, String param1String) {
      this(param1Context, (Notification)null);
      Notification.access$602(this.mN, param1String);
    }
    
    private RemoteViews applyStandardTemplate(int param1Int, Notification.StandardTemplateParams param1StandardTemplateParams, Notification.TemplateBindResult param1TemplateBindResult) {
      Notification.BuilderRemoteViews builderRemoteViews = new Notification.BuilderRemoteViews(this.mContext.getApplicationInfo(), param1Int);
      resetStandardTemplate(builderRemoteViews);
      Bundle bundle = this.mN.extras;
      updateBackgroundColor(builderRemoteViews, param1StandardTemplateParams);
      bindNotificationHeader(builderRemoteViews, param1StandardTemplateParams);
      bindLargeIconAndReply(builderRemoteViews, param1StandardTemplateParams, param1TemplateBindResult);
      boolean bool = handleProgressBar(builderRemoteViews, bundle, param1StandardTemplateParams);
      CharSequence charSequence = param1StandardTemplateParams.title;
      boolean bool1 = false;
      if (charSequence != null) {
        builderRemoteViews.setViewVisibility(16908310, 0);
        builderRemoteViews.setTextViewText(16908310, processTextSpans(param1StandardTemplateParams.title));
        setTextViewColorPrimary(builderRemoteViews, 16908310, param1StandardTemplateParams);
        if (bool) {
          param1Int = -2;
        } else {
          param1Int = -1;
        } 
        builderRemoteViews.setViewLayoutWidth(16908310, param1Int);
      } 
      if (param1StandardTemplateParams.text != null) {
        if (bool) {
          param1Int = 16909530;
        } else {
          param1Int = 16909502;
        } 
        builderRemoteViews.setTextViewText(param1Int, processTextSpans(param1StandardTemplateParams.text));
        setTextViewColorSecondary(builderRemoteViews, param1Int, param1StandardTemplateParams);
        builderRemoteViews.setViewVisibility(param1Int, 0);
      } 
      if (bool || this.mN.hasLargeIcon())
        bool1 = true; 
      setContentMinHeight(builderRemoteViews, bool1);
      return builderRemoteViews;
    }
    
    private RemoteViews applyStandardTemplate(int param1Int, Notification.TemplateBindResult param1TemplateBindResult) {
      return applyStandardTemplate(param1Int, this.mParams.reset().fillTextsFrom(this), param1TemplateBindResult);
    }
    
    private RemoteViews applyStandardTemplateWithActions(int param1Int, Notification.StandardTemplateParams param1StandardTemplateParams, Notification.TemplateBindResult param1TemplateBindResult) {
      int i;
      boolean bool;
      RemoteViews remoteViews = applyStandardTemplate(param1Int, param1StandardTemplateParams, param1TemplateBindResult);
      resetStandardTemplateWithActions(remoteViews);
      int j = 0;
      param1Int = 0;
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
          i = param1Int | m;
          RemoteViews remoteViews1 = generateActionButton(action, bool, param1StandardTemplateParams);
          if (m != 0 && !bool)
            remoteViews1.setInt(16908696, "setBackgroundResource", 0); 
          remoteViews.addView(16908721, remoteViews1);
        } 
      } else {
        remoteViews.setViewVisibility(16908722, 8);
        i = j;
      } 
      RemoteInputHistoryItem[] arrayOfRemoteInputHistoryItem = (RemoteInputHistoryItem[])Notification.getParcelableArrayFromBundle(this.mN.extras, "android.remoteInputHistoryItems", (Class)RemoteInputHistoryItem.class);
      if (i != 0 && arrayOfRemoteInputHistoryItem != null && arrayOfRemoteInputHistoryItem.length > 0 && !TextUtils.isEmpty(arrayOfRemoteInputHistoryItem[0].getText()) && param1StandardTemplateParams.maxRemoteInputHistory > 0) {
        bool = this.mN.extras.getBoolean("android.remoteInputSpinner");
        remoteViews.setViewVisibility(16909226, 0);
        remoteViews.setViewVisibility(16909229, 0);
        remoteViews.setTextViewText(16909228, processTextSpans(arrayOfRemoteInputHistoryItem[0].getText()));
        setTextViewColorSecondary(remoteViews, 16909228, param1StandardTemplateParams);
        i = b;
        if (bool)
          i = 0; 
        remoteViews.setViewVisibility(16909227, i);
        if (isColorized(param1StandardTemplateParams)) {
          i = getPrimaryTextColor(param1StandardTemplateParams);
        } else {
          i = resolveContrastColor(param1StandardTemplateParams);
        } 
        remoteViews.setProgressIndeterminateTintList(16909227, ColorStateList.valueOf(i));
        if (arrayOfRemoteInputHistoryItem.length > 1 && !TextUtils.isEmpty(arrayOfRemoteInputHistoryItem[1].getText()) && param1StandardTemplateParams.maxRemoteInputHistory > 1) {
          remoteViews.setViewVisibility(16909230, 0);
          remoteViews.setTextViewText(16909230, processTextSpans(arrayOfRemoteInputHistoryItem[1].getText()));
          setTextViewColorSecondary(remoteViews, 16909230, param1StandardTemplateParams);
          if (arrayOfRemoteInputHistoryItem.length > 2 && !TextUtils.isEmpty(arrayOfRemoteInputHistoryItem[2].getText()) && param1StandardTemplateParams.maxRemoteInputHistory > 2) {
            remoteViews.setViewVisibility(16909231, 0);
            remoteViews.setTextViewText(16909231, processTextSpans(arrayOfRemoteInputHistoryItem[2].getText()));
            setTextViewColorSecondary(remoteViews, 16909231, param1StandardTemplateParams);
          } 
        } 
      } 
      return remoteViews;
    }
    
    private RemoteViews applyStandardTemplateWithActions(int param1Int, Notification.TemplateBindResult param1TemplateBindResult) {
      return applyStandardTemplateWithActions(param1Int, this.mParams.reset().fillTextsFrom(this), param1TemplateBindResult);
    }
    
    private void bindActivePermissions(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      int i = getNeutralColor(param1StandardTemplateParams);
      param1RemoteViews.setDrawableTint(16908823, false, i, PorterDuff.Mode.SRC_ATOP);
      param1RemoteViews.setDrawableTint(16909171, false, i, PorterDuff.Mode.SRC_ATOP);
      param1RemoteViews.setDrawableTint(16909267, false, i, PorterDuff.Mode.SRC_ATOP);
    }
    
    private void bindAlertedIcon(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      param1RemoteViews.setDrawableTint(16908740, false, getNeutralColor(param1StandardTemplateParams), PorterDuff.Mode.SRC_ATOP);
    }
    
    private void bindExpandButton(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      int i;
      if (isColorized(param1StandardTemplateParams)) {
        i = getPrimaryTextColor(param1StandardTemplateParams);
      } else {
        i = getSecondaryTextColor(param1StandardTemplateParams);
      } 
      param1RemoteViews.setDrawableTint(16908949, false, i, PorterDuff.Mode.SRC_ATOP);
      param1RemoteViews.setInt(16908949, "setOriginalNotificationColor", i);
    }
    
    private void bindHeaderAppName(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      param1RemoteViews.setTextViewText(16908761, loadHeaderAppName());
      if (isColorized(param1StandardTemplateParams)) {
        setTextViewColorPrimary(param1RemoteViews, 16908761, param1StandardTemplateParams);
      } else {
        param1RemoteViews.setTextColor(16908761, getSecondaryTextColor(param1StandardTemplateParams));
      } 
    }
    
    private void bindHeaderChronometerAndTime(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      if (showsTimeOrChronometer()) {
        param1RemoteViews.setViewVisibility(16909537, 0);
        setTextViewColorSecondary(param1RemoteViews, 16909537, param1StandardTemplateParams);
        if (this.mN.extras.getBoolean("android.showChronometer")) {
          param1RemoteViews.setViewVisibility(16908842, 0);
          param1RemoteViews.setLong(16908842, "setBase", this.mN.when + SystemClock.elapsedRealtime() - System.currentTimeMillis());
          param1RemoteViews.setBoolean(16908842, "setStarted", true);
          param1RemoteViews.setChronometerCountDown(16908842, this.mN.extras.getBoolean("android.chronometerCountDown"));
          setTextViewColorSecondary(param1RemoteViews, 16908842, param1StandardTemplateParams);
        } else {
          param1RemoteViews.setViewVisibility(16909533, 0);
          param1RemoteViews.setLong(16909533, "setTime", this.mN.when);
          setTextViewColorSecondary(param1RemoteViews, 16909533, param1StandardTemplateParams);
        } 
      } else {
        long l;
        if (this.mN.when != 0L) {
          l = this.mN.when;
        } else {
          l = this.mN.creationTime;
        } 
        param1RemoteViews.setLong(16909533, "setTime", l);
      } 
    }
    
    private void bindHeaderText(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      CharSequence charSequence1 = param1StandardTemplateParams.summaryText;
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
        param1RemoteViews.setTextViewText(16909033, processTextSpans(processLegacyText(charSequence1)));
        setTextViewColorSecondary(param1RemoteViews, 16909033, param1StandardTemplateParams);
        param1RemoteViews.setViewVisibility(16909033, 0);
        param1RemoteViews.setViewVisibility(16909034, 0);
        setTextViewColorSecondary(param1RemoteViews, 16909034, param1StandardTemplateParams);
      } 
    }
    
    private void bindHeaderTextSecondary(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      if (!TextUtils.isEmpty(param1StandardTemplateParams.headerTextSecondary)) {
        param1RemoteViews.setTextViewText(16909035, processTextSpans(processLegacyText(param1StandardTemplateParams.headerTextSecondary)));
        setTextViewColorSecondary(param1RemoteViews, 16909035, param1StandardTemplateParams);
        param1RemoteViews.setViewVisibility(16909035, 0);
        param1RemoteViews.setViewVisibility(16909036, 0);
        setTextViewColorSecondary(param1RemoteViews, 16909036, param1StandardTemplateParams);
      } 
    }
    
    private boolean bindLargeIcon(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      boolean bool;
      if (this.mN.mLargeIcon == null && this.mN.largeIcon != null) {
        Notification notification = this.mN;
        Notification.access$1402(notification, Icon.createWithBitmap(notification.largeIcon));
      } 
      if (this.mN.mLargeIcon != null && !param1StandardTemplateParams.hideLargeIcon) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool) {
        param1RemoteViews.setViewVisibility(16909366, 0);
        param1RemoteViews.setImageViewIcon(16909366, this.mN.mLargeIcon);
        processLargeLegacyIcon(this.mN.mLargeIcon, param1RemoteViews, param1StandardTemplateParams);
      } 
      return bool;
    }
    
    private void bindLargeIconAndReply(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams, Notification.TemplateBindResult param1TemplateBindResult) {
      boolean bool;
      boolean bool1 = bindLargeIcon(param1RemoteViews, param1StandardTemplateParams);
      boolean bool2 = bindReplyIcon(param1RemoteViews, param1StandardTemplateParams);
      int i = 0;
      if (bool1 || bool2) {
        bool = true;
      } else {
        bool = false;
      } 
      if (!bool)
        i = 8; 
      param1RemoteViews.setViewVisibility(16909367, i);
      i = calculateMarginEnd(bool1, bool2);
      param1RemoteViews.setViewLayoutMarginEnd(16909124, i);
      param1RemoteViews.setViewLayoutMarginEnd(16909502, i);
      param1RemoteViews.setViewLayoutMarginEnd(16908301, i);
      if (param1TemplateBindResult != null) {
        param1TemplateBindResult.setIconMarginEnd(i);
        param1TemplateBindResult.setRightIconContainerVisible(bool);
      } 
    }
    
    private void bindNotificationHeader(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      bindSmallIcon(param1RemoteViews, param1StandardTemplateParams);
      bindHeaderAppName(param1RemoteViews, param1StandardTemplateParams);
      bindHeaderText(param1RemoteViews, param1StandardTemplateParams);
      bindHeaderTextSecondary(param1RemoteViews, param1StandardTemplateParams);
      bindHeaderChronometerAndTime(param1RemoteViews, param1StandardTemplateParams);
      bindProfileBadge(param1RemoteViews, param1StandardTemplateParams);
      bindAlertedIcon(param1RemoteViews, param1StandardTemplateParams);
      bindActivePermissions(param1RemoteViews, param1StandardTemplateParams);
      bindExpandButton(param1RemoteViews, param1StandardTemplateParams);
      Notification.access$1702(this.mN, true);
    }
    
    private void bindProfileBadge(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      Bitmap bitmap = getProfileBadge();
      if (bitmap != null) {
        param1RemoteViews.setImageViewBitmap(16909317, bitmap);
        param1RemoteViews.setViewVisibility(16909317, 0);
        if (isColorized(param1StandardTemplateParams))
          param1RemoteViews.setDrawableTint(16909317, false, getPrimaryTextColor(param1StandardTemplateParams), PorterDuff.Mode.SRC_ATOP); 
      } 
    }
    
    private boolean bindReplyIcon(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      boolean bool = param1StandardTemplateParams.hideReplyIcon;
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
        param1RemoteViews.setViewVisibility(16909346, 0);
        param1RemoteViews.setDrawableTint(16909346, false, getNeutralColor(param1StandardTemplateParams), PorterDuff.Mode.SRC_ATOP);
        param1RemoteViews.setOnClickPendingIntent(16909346, action.actionIntent);
        param1RemoteViews.setRemoteInputs(16909346, action.mRemoteInputs);
      } else {
        param1RemoteViews.setRemoteInputs(16909346, null);
      } 
      if (i == 0)
        b = 8; 
      param1RemoteViews.setViewVisibility(16909346, b);
      return i;
    }
    
    private void bindSmallIcon(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      if (this.mN.mSmallIcon == null && this.mN.icon != 0) {
        Notification notification = this.mN;
        Notification.access$2202(notification, Icon.createWithResource(this.mContext, notification.icon));
      } 
      param1RemoteViews.setImageViewIcon(16908294, this.mN.mSmallIcon);
      param1RemoteViews.setInt(16908294, "setImageLevel", this.mN.iconLevel);
      processSmallIconColor(this.mN.mSmallIcon, param1RemoteViews, param1StandardTemplateParams);
    }
    
    private int calculateMarginEnd(boolean param1Boolean1, boolean param1Boolean2) {
      null = 0;
      int i = this.mContext.getResources().getDimensionPixelSize(17105345);
      int j = this.mContext.getResources().getDimensionPixelSize(17105379);
      if (param1Boolean2)
        null = 0 + j - this.mContext.getResources().getDimensionPixelSize(17105378) * 2; 
      int k = null;
      if (param1Boolean1) {
        null += j;
        k = null;
        if (param1Boolean2)
          k = null + i; 
      } 
      if (!param1Boolean2) {
        null = k;
        return param1Boolean1 ? (k + i) : null;
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
    
    private CharSequence ensureColorSpanContrast(CharSequence param1CharSequence, int param1Int, ColorStateList[] param1ArrayOfColorStateList) {
      if (param1CharSequence instanceof Spanned) {
        Spanned spanned = (Spanned)param1CharSequence;
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
          if (k - j == param1CharSequence.length())
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
                arrayOfInt[b1] = ContrastColorUtil.ensureLargeTextContrast(object3[b1], param1Int, this.mInNightMode); 
              object2 = new ColorStateList((int[][])object2.getStates().clone(), arrayOfInt);
              object3 = object2;
              if (bool) {
                param1ArrayOfColorStateList[0] = (ColorStateList)object2;
                object3 = null;
              } 
              object3 = new TextAppearanceSpan(textAppearanceSpan.getFamily(), textAppearanceSpan.getTextStyle(), textAppearanceSpan.getTextSize(), (ColorStateList)object3, textAppearanceSpan.getLinkTextColor());
            } 
          } else if (object3 instanceof ForegroundColorSpan) {
            int m = ContrastColorUtil.ensureLargeTextContrast(((ForegroundColorSpan)object3).getForegroundColor(), param1Int, this.mInNightMode);
            if (bool) {
              param1ArrayOfColorStateList[0] = ColorStateList.valueOf(m);
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
      return param1CharSequence;
    }
    
    private void ensureColors(Notification.StandardTemplateParams param1StandardTemplateParams) {
      int i = getBackgroundColor(param1StandardTemplateParams);
      if (this.mPrimaryTextColor == 1 || this.mSecondaryTextColor == 1 || this.mTextColorsAreForBackground != i) {
        int j;
        this.mTextColorsAreForBackground = i;
        if (!hasForegroundColor() || !isColorized(param1StandardTemplateParams)) {
          this.mPrimaryTextColor = ContrastColorUtil.resolvePrimaryColor(this.mContext, i, this.mInNightMode);
          this.mSecondaryTextColor = ContrastColorUtil.resolveSecondaryColor(this.mContext, i, this.mInNightMode);
          if (i != 0 && isColorized(param1StandardTemplateParams)) {
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
    
    private static List<Notification.Action> filterOutContextualActions(List<Notification.Action> param1List) {
      ArrayList<Notification.Action> arrayList = new ArrayList();
      for (Notification.Action action : param1List) {
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
    
    private RemoteViews generateActionButton(Notification.Action param1Action, boolean param1Boolean, Notification.StandardTemplateParams param1StandardTemplateParams) {
      boolean bool2;
      int i;
      PendingIntent pendingIntent = param1Action.actionIntent;
      boolean bool1 = true;
      if (pendingIntent == null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      ApplicationInfo applicationInfo = this.mContext.getApplicationInfo();
      if (param1Boolean) {
        i = getEmphasizedActionLayoutResource();
      } else if (bool2) {
        i = getActionTombstoneLayoutResource();
      } else {
        i = getActionLayoutResource();
      } 
      Notification.BuilderRemoteViews builderRemoteViews = new Notification.BuilderRemoteViews(applicationInfo, i);
      if (!bool2)
        builderRemoteViews.setOnClickPendingIntent(16908696, param1Action.actionIntent); 
      builderRemoteViews.setContentDescription(16908696, param1Action.title);
      if (param1Action.mRemoteInputs != null)
        builderRemoteViews.setRemoteInputs(16908696, param1Action.mRemoteInputs); 
      if (param1Boolean) {
        ColorStateList[] arrayOfColorStateList;
        CharSequence charSequence = param1Action.title;
        applicationInfo = null;
        int j = resolveBackgroundColor(param1StandardTemplateParams);
        if (isLegacy()) {
          charSequence = ContrastColorUtil.clearColorSpans(charSequence);
        } else {
          arrayOfColorStateList = new ColorStateList[1];
          charSequence = ensureColorSpanContrast(charSequence, j, arrayOfColorStateList);
        } 
        builderRemoteViews.setTextViewText(16908696, processTextSpans(charSequence));
        setTextViewColorPrimary(builderRemoteViews, 16908696, param1StandardTemplateParams);
        if (arrayOfColorStateList != null && arrayOfColorStateList[0] != null) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        if (bool2) {
          j = arrayOfColorStateList[0].getDefaultColor();
          i = ContrastColorUtil.resolvePrimaryColor(this.mContext, j, this.mInNightMode);
          builderRemoteViews.setTextColor(16908696, i);
        } else if (getRawColor(param1StandardTemplateParams) != 0 && !isColorized(param1StandardTemplateParams) && this.mTintActionButtons && !this.mInNightMode) {
          i = resolveContrastColor(param1StandardTemplateParams);
          builderRemoteViews.setTextColor(16908696, i);
        } else {
          i = getPrimaryTextColor(param1StandardTemplateParams);
        } 
        builderRemoteViews.setColorStateList(16908696, "setRippleColor", ColorStateList.valueOf(0xFFFFFF & i | 0x33000000));
        builderRemoteViews.setColorStateList(16908696, "setButtonBackground", ColorStateList.valueOf(j));
        if (!bool2) {
          param1Boolean = bool1;
        } else {
          param1Boolean = false;
        } 
        builderRemoteViews.setBoolean(16908696, "setHasStroke", param1Boolean);
      } else {
        builderRemoteViews.setTextViewText(16908696, processTextSpans(processLegacyText(param1Action.title)));
        if (isColorized(param1StandardTemplateParams)) {
          setTextViewColorPrimary(builderRemoteViews, 16908696, param1StandardTemplateParams);
        } else if (getRawColor(param1StandardTemplateParams) != 0 && this.mTintActionButtons) {
          builderRemoteViews.setTextColor(16908696, resolveContrastColor(param1StandardTemplateParams));
        } 
      } 
      builderRemoteViews.setIntTag(16908696, 16909220, this.mActions.indexOf(param1Action));
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
    
    private int getBackgroundColor(Notification.StandardTemplateParams param1StandardTemplateParams) {
      if (isColorized(param1StandardTemplateParams)) {
        int i = this.mBackgroundColor;
        if (i == 1)
          i = getRawColor(param1StandardTemplateParams); 
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
    
    private int getNeutralColor(Notification.StandardTemplateParams param1StandardTemplateParams) {
      return isColorized(param1StandardTemplateParams) ? getSecondaryTextColor(param1StandardTemplateParams) : resolveNeutralColor();
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
    
    private int getRawColor(Notification.StandardTemplateParams param1StandardTemplateParams) {
      return param1StandardTemplateParams.forceDefaultColor ? 0 : this.mN.color;
    }
    
    private boolean handleProgressBar(RemoteViews param1RemoteViews, Bundle param1Bundle, Notification.StandardTemplateParams param1StandardTemplateParams) {
      int i = param1Bundle.getInt("android.progressMax", 0);
      int j = param1Bundle.getInt("android.progress", 0);
      boolean bool = param1Bundle.getBoolean("android.progressIndeterminate");
      if (param1StandardTemplateParams.hasProgress && (i != 0 || bool)) {
        param1RemoteViews.setViewVisibility(16908301, 0);
        param1RemoteViews.setProgressBar(16908301, i, j, bool);
        param1RemoteViews.setProgressBackgroundTintList(16908301, ColorStateList.valueOf(this.mContext.getColor(17170891)));
        if (getRawColor(param1StandardTemplateParams) != 0) {
          if (isColorized(param1StandardTemplateParams)) {
            i = getPrimaryTextColor(param1StandardTemplateParams);
          } else {
            i = resolveContrastColor(param1StandardTemplateParams);
          } 
          ColorStateList colorStateList = ColorStateList.valueOf(i);
          param1RemoteViews.setProgressTintList(16908301, colorStateList);
          param1RemoteViews.setProgressIndeterminateTintList(16908301, colorStateList);
        } 
        return true;
      } 
      param1RemoteViews.setViewVisibility(16908301, 8);
      return false;
    }
    
    private boolean hasForegroundColor() {
      int i = this.mForegroundColor;
      boolean bool = true;
      if (i == 1)
        bool = false; 
      return bool;
    }
    
    private boolean hasValidRemoteInput(Notification.Action param1Action) {
      if (TextUtils.isEmpty(param1Action.title) || param1Action.actionIntent == null)
        return false; 
      RemoteInput[] arrayOfRemoteInput = param1Action.getRemoteInputs();
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
    
    private void hideLine1Text(RemoteViews param1RemoteViews) {
      if (param1RemoteViews != null)
        param1RemoteViews.setViewVisibility(16909530, 8); 
    }
    
    private boolean isColorized(Notification.StandardTemplateParams param1StandardTemplateParams) {
      boolean bool;
      if (param1StandardTemplateParams.allowColorization && this.mN.isColorized()) {
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
    
    public static void makeHeaderExpanded(RemoteViews param1RemoteViews) {
      if (param1RemoteViews != null)
        param1RemoteViews.setBoolean(16909224, "setExpanded", true); 
    }
    
    private RemoteViews makeNotificationHeader(Notification.StandardTemplateParams param1StandardTemplateParams) {
      param1StandardTemplateParams.disallowColorization();
      Notification.BuilderRemoteViews builderRemoteViews = new Notification.BuilderRemoteViews(this.mContext.getApplicationInfo(), 17367207);
      resetNotificationHeader(builderRemoteViews);
      bindNotificationHeader(builderRemoteViews, param1StandardTemplateParams);
      return builderRemoteViews;
    }
    
    public static Notification maybeCloneStrippedForDelivery(Notification param1Notification) {
      boolean bool2;
      boolean bool3;
      String str = param1Notification.extras.getString("android.template");
      if (!TextUtils.isEmpty(str) && Notification.getNotificationStyleClass(str) == null)
        return param1Notification; 
      boolean bool = param1Notification.contentView instanceof Notification.BuilderRemoteViews;
      boolean bool1 = true;
      if (bool && param1Notification.extras.getInt("android.rebuild.contentViewActionCount", -1) == param1Notification.contentView.getSequenceNumber()) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (param1Notification.bigContentView instanceof Notification.BuilderRemoteViews && param1Notification.extras.getInt("android.rebuild.bigViewActionCount", -1) == param1Notification.bigContentView.getSequenceNumber()) {
        bool3 = true;
      } else {
        bool3 = false;
      } 
      if (!(param1Notification.headsUpContentView instanceof Notification.BuilderRemoteViews) || param1Notification.extras.getInt("android.rebuild.hudViewActionCount", -1) != param1Notification.headsUpContentView.getSequenceNumber())
        bool1 = false; 
      if (!bool2 && !bool3 && !bool1)
        return param1Notification; 
      param1Notification = param1Notification.clone();
      if (bool2) {
        param1Notification.contentView = null;
        param1Notification.extras.remove("android.rebuild.contentViewActionCount");
      } 
      if (bool3) {
        param1Notification.bigContentView = null;
        param1Notification.extras.remove("android.rebuild.bigViewActionCount");
      } 
      if (bool1) {
        param1Notification.headsUpContentView = null;
        param1Notification.extras.remove("android.rebuild.hudViewActionCount");
      } 
      return param1Notification;
    }
    
    private void processLargeLegacyIcon(Icon param1Icon, RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      if (param1Icon != null && isLegacy() && getColorUtil().isGrayscaleIcon(this.mContext, param1Icon))
        param1RemoteViews.setDrawableTint(16908294, false, resolveContrastColor(param1StandardTemplateParams), PorterDuff.Mode.SRC_ATOP); 
    }
    
    private CharSequence processLegacyText(CharSequence param1CharSequence) {
      boolean bool;
      if (isLegacy() || textColorsNeedInversion()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool ? getColorUtil().invertCharSequenceColors(param1CharSequence) : param1CharSequence;
    }
    
    private void processSmallIconColor(Icon param1Icon, RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      boolean bool1;
      int j;
      boolean bool = isLegacy();
      int i = 1;
      if (!bool || getColorUtil().isGrayscaleIcon(this.mContext, param1Icon)) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (isColorized(param1StandardTemplateParams)) {
        j = getPrimaryTextColor(param1StandardTemplateParams);
      } else {
        j = resolveContrastColor(param1StandardTemplateParams);
      } 
      if (bool1)
        param1RemoteViews.setDrawableTint(16908294, false, j, PorterDuff.Mode.SRC_ATOP); 
      if (bool1)
        i = j; 
      param1RemoteViews.setInt(16908294, "setOriginalIconColor", i);
    }
    
    private CharSequence processTextSpans(CharSequence param1CharSequence) {
      return (hasForegroundColor() || this.mInNightMode) ? ContrastColorUtil.clearColorSpans(param1CharSequence) : param1CharSequence;
    }
    
    public static Builder recoverBuilder(Context param1Context, Notification param1Notification) {
      ApplicationInfo applicationInfo = (ApplicationInfo)param1Notification.extras.getParcelable("android.appInfo");
      if (applicationInfo != null)
        try {
          Context context = param1Context.createApplicationContext(applicationInfo, 4);
          param1Context = context;
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("ApplicationInfo ");
          stringBuilder.append(applicationInfo);
          stringBuilder.append(" not found");
          Log.e("Notification", stringBuilder.toString());
        }  
      return new Builder(param1Context, param1Notification);
    }
    
    private void resetNotificationHeader(RemoteViews param1RemoteViews) {
      param1RemoteViews.setBoolean(16909224, "setExpanded", false);
      param1RemoteViews.setTextViewText(16908761, null);
      param1RemoteViews.setViewVisibility(16908842, 8);
      param1RemoteViews.setViewVisibility(16909033, 8);
      param1RemoteViews.setTextViewText(16909033, null);
      param1RemoteViews.setViewVisibility(16909035, 8);
      param1RemoteViews.setTextViewText(16909035, null);
      param1RemoteViews.setViewVisibility(16909034, 8);
      param1RemoteViews.setViewVisibility(16909036, 8);
      param1RemoteViews.setViewVisibility(16909537, 8);
      param1RemoteViews.setViewVisibility(16909533, 8);
      param1RemoteViews.setImageViewIcon(16909317, null);
      param1RemoteViews.setViewVisibility(16909317, 8);
      Notification.access$1702(this.mN, false);
    }
    
    private void resetStandardTemplate(RemoteViews param1RemoteViews) {
      resetNotificationHeader(param1RemoteViews);
      param1RemoteViews.setViewVisibility(16909366, 8);
      param1RemoteViews.setViewVisibility(16908310, 8);
      param1RemoteViews.setTextViewText(16908310, null);
      param1RemoteViews.setViewVisibility(16909502, 8);
      param1RemoteViews.setTextViewText(16909502, null);
      param1RemoteViews.setViewVisibility(16909530, 8);
      param1RemoteViews.setTextViewText(16909530, null);
    }
    
    private void resetStandardTemplateWithActions(RemoteViews param1RemoteViews) {
      param1RemoteViews.setViewVisibility(16908721, 8);
      param1RemoteViews.removeAllViews(16908721);
      param1RemoteViews.setViewVisibility(16909226, 8);
      param1RemoteViews.setTextViewText(16909228, null);
      param1RemoteViews.setViewVisibility(16909229, 8);
      param1RemoteViews.setViewVisibility(16909227, 8);
      param1RemoteViews.setViewVisibility(16909230, 8);
      param1RemoteViews.setTextViewText(16909230, null);
      param1RemoteViews.setViewVisibility(16909231, 8);
      param1RemoteViews.setTextViewText(16909231, null);
      param1RemoteViews.setViewLayoutMarginBottomDimen(16909221, 17105344);
    }
    
    private int resolveBackgroundColor(Notification.StandardTemplateParams param1StandardTemplateParams) {
      int i = getBackgroundColor(param1StandardTemplateParams);
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
    
    private void setTextViewColorPrimary(RemoteViews param1RemoteViews, int param1Int, Notification.StandardTemplateParams param1StandardTemplateParams) {
      ensureColors(param1StandardTemplateParams);
      param1RemoteViews.setTextColor(param1Int, this.mPrimaryTextColor);
    }
    
    private void setTextViewColorSecondary(RemoteViews param1RemoteViews, int param1Int, Notification.StandardTemplateParams param1StandardTemplateParams) {
      ensureColors(param1StandardTemplateParams);
      param1RemoteViews.setTextColor(param1Int, this.mSecondaryTextColor);
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
    
    private void updateBackgroundColor(RemoteViews param1RemoteViews, Notification.StandardTemplateParams param1StandardTemplateParams) {
      if (isColorized(param1StandardTemplateParams)) {
        param1RemoteViews.setInt(16909478, "setBackgroundColor", getBackgroundColor(param1StandardTemplateParams));
      } else {
        param1RemoteViews.setInt(16909478, "setBackgroundResource", 0);
      } 
    }
    
    private boolean useExistingRemoteView() {
      Notification.Style style = this.mStyle;
      return (style == null || (!style.displayCustomViewInline() && !this.mRebuildStyledRemoteViews));
    }
    
    @Deprecated
    public Builder addAction(int param1Int, CharSequence param1CharSequence, PendingIntent param1PendingIntent) {
      this.mActions.add(new Notification.Action(param1Int, Notification.safeCharSequence(param1CharSequence), param1PendingIntent));
      return this;
    }
    
    public Builder addAction(Notification.Action param1Action) {
      if (param1Action != null)
        this.mActions.add(param1Action); 
      return this;
    }
    
    public Builder addExtras(Bundle param1Bundle) {
      if (param1Bundle != null)
        this.mUserExtras.putAll(param1Bundle); 
      return this;
    }
    
    public Builder addPerson(Person param1Person) {
      this.mPersonList.add(param1Person);
      return this;
    }
    
    public Builder addPerson(String param1String) {
      addPerson((new Person.Builder()).setUri(param1String).build());
      return this;
    }
    
    public Notification build() {
      if (this.mN.mShortcutId == null || this.mN.mBubbleMetadata == null || this.mN.mBubbleMetadata.getShortcutId() == null || this.mN.mShortcutId.equals(this.mN.mBubbleMetadata.getShortcutId())) {
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
      stringBuilder.append(this.mN.mShortcutId);
      stringBuilder.append(" vs bubble: ");
      stringBuilder.append(this.mN.mBubbleMetadata.getShortcutId());
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Notification buildInto(Notification param1Notification) {
      build().cloneInto(param1Notification, true);
      return param1Notification;
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
    
    public RemoteViews createContentView(boolean param1Boolean) {
      if (this.mN.contentView != null && useExistingRemoteView())
        return this.mN.contentView; 
      Notification.Style style = this.mStyle;
      if (style != null) {
        RemoteViews remoteViews = style.makeContentView(param1Boolean);
        if (remoteViews != null)
          return remoteViews; 
      } 
      return applyStandardTemplate(getBaseLayoutResource(), null);
    }
    
    public RemoteViews createHeadsUpContentView() {
      return createHeadsUpContentView(false);
    }
    
    public RemoteViews createHeadsUpContentView(boolean param1Boolean) {
      if (this.mN.headsUpContentView != null && useExistingRemoteView())
        return this.mN.headsUpContentView; 
      Notification.Style style = this.mStyle;
      if (style != null) {
        RemoteViews remoteViews = style.makeHeadsUpContentView(param1Boolean);
        if (remoteViews != null)
          return remoteViews; 
      } else if (this.mActions.size() == 0) {
        return null;
      } 
      Notification.StandardTemplateParams standardTemplateParams = this.mParams.reset().fillTextsFrom(this).setMaxRemoteInputHistory(1);
      return applyStandardTemplateWithActions(getBigBaseLayoutResource(), standardTemplateParams, null);
    }
    
    public Builder extend(Notification.Extender param1Extender) {
      param1Extender.extend(this);
      return this;
    }
    
    public Bundle getExtras() {
      return this.mUserExtras;
    }
    
    public CharSequence getHeadsUpStatusBarText(boolean param1Boolean) {
      Notification.Style style = this.mStyle;
      if (style != null && !param1Boolean) {
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
    
    public int getPrimaryTextColor(Notification.StandardTemplateParams param1StandardTemplateParams) {
      ensureColors(param1StandardTemplateParams);
      return this.mPrimaryTextColor;
    }
    
    public int getSecondaryTextColor() {
      return getSecondaryTextColor(this.mParams);
    }
    
    public int getSecondaryTextColor(Notification.StandardTemplateParams param1StandardTemplateParams) {
      ensureColors(param1StandardTemplateParams);
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
    
    public RemoteViews makeLowPriorityContentView(boolean param1Boolean) {
      Notification.StandardTemplateParams standardTemplateParams = this.mParams.reset().forceDefaultColor().fillTextsFrom(this);
      if (!param1Boolean || TextUtils.isEmpty(this.mParams.summaryText))
        standardTemplateParams.summaryText(createSummaryText()); 
      RemoteViews remoteViews = makeNotificationHeader(standardTemplateParams);
      remoteViews.setBoolean(16909224, "setAcceptAllTouches", true);
      return remoteViews;
    }
    
    public RemoteViews makeNotificationHeader() {
      return makeNotificationHeader(this.mParams.reset().fillTextsFrom(this));
    }
    
    public RemoteViews makePublicContentView(boolean param1Boolean) {
      if (this.mN.publicVersion != null)
        return recoverBuilder(this.mContext, this.mN.publicVersion).createContentView(); 
      Bundle bundle1 = this.mN.extras;
      Notification.Style style = this.mStyle;
      this.mStyle = null;
      Icon icon = this.mN.mLargeIcon;
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
      if (param1Boolean)
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
    
    int resolveContrastColor(Notification.StandardTemplateParams param1StandardTemplateParams) {
      int j;
      int i = getRawColor(param1StandardTemplateParams);
      if (this.mCachedContrastColorIsFor == i) {
        j = this.mCachedContrastColor;
        if (j != 1)
          return j; 
      } 
      int k = this.mContext.getColor(17170888);
      if (i == 0) {
        ensureColors(param1StandardTemplateParams);
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
    
    public Builder setActions(Notification.Action... param1VarArgs) {
      this.mActions.clear();
      for (byte b = 0; b < param1VarArgs.length; b++) {
        if (param1VarArgs[b] != null)
          this.mActions.add(param1VarArgs[b]); 
      } 
      return this;
    }
    
    public Builder setAllowSystemGeneratedContextualActions(boolean param1Boolean) {
      Notification.access$2302(this.mN, param1Boolean);
      return this;
    }
    
    public Builder setAutoCancel(boolean param1Boolean) {
      setFlag(16, param1Boolean);
      return this;
    }
    
    public Builder setBadgeIconType(int param1Int) {
      Notification.access$902(this.mN, param1Int);
      return this;
    }
    
    public Builder setBubbleMetadata(Notification.BubbleMetadata param1BubbleMetadata) {
      Notification.access$1102(this.mN, param1BubbleMetadata);
      return this;
    }
    
    public Builder setCategory(String param1String) {
      this.mN.category = param1String;
      return this;
    }
    
    @Deprecated
    public Builder setChannel(String param1String) {
      Notification.access$602(this.mN, param1String);
      return this;
    }
    
    public Builder setChannelId(String param1String) {
      Notification.access$602(this.mN, param1String);
      return this;
    }
    
    public Builder setChronometerCountDown(boolean param1Boolean) {
      this.mN.extras.putBoolean("android.chronometerCountDown", param1Boolean);
      return this;
    }
    
    public Builder setColor(int param1Int) {
      this.mN.color = param1Int;
      sanitizeColor();
      return this;
    }
    
    public void setColorPalette(int param1Int1, int param1Int2) {
      this.mBackgroundColor = param1Int1;
      this.mForegroundColor = param1Int2;
      this.mTextColorsAreForBackground = 1;
      ensureColors(this.mParams.reset().fillTextsFrom(this));
    }
    
    public Builder setColorized(boolean param1Boolean) {
      this.mN.extras.putBoolean("android.colorized", param1Boolean);
      return this;
    }
    
    @Deprecated
    public Builder setContent(RemoteViews param1RemoteViews) {
      return setCustomContentView(param1RemoteViews);
    }
    
    @Deprecated
    public Builder setContentInfo(CharSequence param1CharSequence) {
      this.mN.extras.putCharSequence("android.infoText", Notification.safeCharSequence(param1CharSequence));
      return this;
    }
    
    public Builder setContentIntent(PendingIntent param1PendingIntent) {
      this.mN.contentIntent = param1PendingIntent;
      return this;
    }
    
    void setContentMinHeight(RemoteViews param1RemoteViews, boolean param1Boolean) {
      int i = 0;
      if (param1Boolean)
        i = this.mContext.getResources().getDimensionPixelSize(17105374); 
      param1RemoteViews.setInt(16909225, "setMinimumHeight", i);
    }
    
    public Builder setContentText(CharSequence param1CharSequence) {
      this.mN.extras.putCharSequence("android.text", Notification.safeCharSequence(param1CharSequence));
      return this;
    }
    
    public Builder setContentTitle(CharSequence param1CharSequence) {
      this.mN.extras.putCharSequence("android.title", Notification.safeCharSequence(param1CharSequence));
      return this;
    }
    
    public Builder setCustomBigContentView(RemoteViews param1RemoteViews) {
      this.mN.bigContentView = param1RemoteViews;
      return this;
    }
    
    public Builder setCustomContentView(RemoteViews param1RemoteViews) {
      this.mN.contentView = param1RemoteViews;
      return this;
    }
    
    public Builder setCustomHeadsUpContentView(RemoteViews param1RemoteViews) {
      this.mN.headsUpContentView = param1RemoteViews;
      return this;
    }
    
    @Deprecated
    public Builder setDefaults(int param1Int) {
      this.mN.defaults = param1Int;
      return this;
    }
    
    public Builder setDeleteIntent(PendingIntent param1PendingIntent) {
      this.mN.deleteIntent = param1PendingIntent;
      return this;
    }
    
    public Builder setExtras(Bundle param1Bundle) {
      if (param1Bundle != null)
        this.mUserExtras = param1Bundle; 
      return this;
    }
    
    public Builder setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        Notification notification = this.mN;
        notification.flags |= param1Int;
      } else {
        Notification notification = this.mN;
        notification.flags &= param1Int;
      } 
      return this;
    }
    
    public Builder setFullScreenIntent(PendingIntent param1PendingIntent, boolean param1Boolean) {
      this.mN.fullScreenIntent = param1PendingIntent;
      setFlag(128, param1Boolean);
      return this;
    }
    
    public Builder setGroup(String param1String) {
      Notification.access$1502(this.mN, param1String);
      return this;
    }
    
    public Builder setGroupAlertBehavior(int param1Int) {
      Notification.access$1002(this.mN, param1Int);
      return this;
    }
    
    public Builder setGroupSummary(boolean param1Boolean) {
      setFlag(512, param1Boolean);
      return this;
    }
    
    public Builder setHideSmartReplies(boolean param1Boolean) {
      this.mN.extras.putBoolean("android.hideSmartReplies", param1Boolean);
      return this;
    }
    
    public Builder setLargeIcon(Bitmap param1Bitmap) {
      if (param1Bitmap != null) {
        Icon icon = Icon.createWithBitmap(param1Bitmap);
      } else {
        param1Bitmap = null;
      } 
      return setLargeIcon((Icon)param1Bitmap);
    }
    
    public Builder setLargeIcon(Icon param1Icon) {
      Notification.access$1402(this.mN, param1Icon);
      this.mN.extras.putParcelable("android.largeIcon", (Parcelable)param1Icon);
      return this;
    }
    
    @Deprecated
    public Builder setLights(int param1Int1, int param1Int2, int param1Int3) {
      this.mN.ledARGB = param1Int1;
      this.mN.ledOnMS = param1Int2;
      this.mN.ledOffMS = param1Int3;
      if (param1Int2 != 0 || param1Int3 != 0) {
        Notification notification = this.mN;
        notification.flags |= 0x1;
      } 
      return this;
    }
    
    public Builder setLocalOnly(boolean param1Boolean) {
      setFlag(256, param1Boolean);
      return this;
    }
    
    public Builder setLocusId(LocusId param1LocusId) {
      Notification.access$802(this.mN, param1LocusId);
      return this;
    }
    
    public Builder setNumber(int param1Int) {
      this.mN.number = param1Int;
      return this;
    }
    
    public Builder setOngoing(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
    
    public Builder setOnlyAlertOnce(boolean param1Boolean) {
      setFlag(8, param1Boolean);
      return this;
    }
    
    @Deprecated
    public Builder setPriority(int param1Int) {
      this.mN.priority = param1Int;
      return this;
    }
    
    public Builder setProgress(int param1Int1, int param1Int2, boolean param1Boolean) {
      this.mN.extras.putInt("android.progress", param1Int2);
      this.mN.extras.putInt("android.progressMax", param1Int1);
      this.mN.extras.putBoolean("android.progressIndeterminate", param1Boolean);
      return this;
    }
    
    public Builder setPublicVersion(Notification param1Notification) {
      if (param1Notification != null) {
        this.mN.publicVersion = new Notification();
        param1Notification.cloneInto(this.mN.publicVersion, true);
      } else {
        this.mN.publicVersion = null;
      } 
      return this;
    }
    
    public void setRebuildStyledRemoteViews(boolean param1Boolean) {
      this.mRebuildStyledRemoteViews = param1Boolean;
    }
    
    public Builder setRemoteInputHistory(RemoteInputHistoryItem[] param1ArrayOfRemoteInputHistoryItem) {
      if (param1ArrayOfRemoteInputHistoryItem == null) {
        this.mN.extras.putParcelableArray("android.remoteInputHistoryItems", null);
      } else {
        int i = Math.min(5, param1ArrayOfRemoteInputHistoryItem.length);
        RemoteInputHistoryItem[] arrayOfRemoteInputHistoryItem = new RemoteInputHistoryItem[i];
        for (byte b = 0; b < i; b++)
          arrayOfRemoteInputHistoryItem[b] = param1ArrayOfRemoteInputHistoryItem[b]; 
        this.mN.extras.putParcelableArray("android.remoteInputHistoryItems", (Parcelable[])arrayOfRemoteInputHistoryItem);
      } 
      return this;
    }
    
    public Builder setRemoteInputHistory(CharSequence[] param1ArrayOfCharSequence) {
      if (param1ArrayOfCharSequence == null) {
        this.mN.extras.putCharSequenceArray("android.remoteInputHistory", null);
      } else {
        int i = Math.min(5, param1ArrayOfCharSequence.length);
        CharSequence[] arrayOfCharSequence = new CharSequence[i];
        RemoteInputHistoryItem[] arrayOfRemoteInputHistoryItem = new RemoteInputHistoryItem[i];
        for (byte b = 0; b < i; b++) {
          arrayOfCharSequence[b] = Notification.safeCharSequence(param1ArrayOfCharSequence[b]);
          arrayOfRemoteInputHistoryItem[b] = new RemoteInputHistoryItem(param1ArrayOfCharSequence[b]);
        } 
        this.mN.extras.putCharSequenceArray("android.remoteInputHistory", arrayOfCharSequence);
        this.mN.extras.putParcelableArray("android.remoteInputHistoryItems", (Parcelable[])arrayOfRemoteInputHistoryItem);
      } 
      return this;
    }
    
    public Builder setSettingsText(CharSequence param1CharSequence) {
      Notification.access$1302(this.mN, Notification.safeCharSequence(param1CharSequence));
      return this;
    }
    
    public Builder setShortcutId(String param1String) {
      Notification.access$702(this.mN, param1String);
      return this;
    }
    
    public Builder setShowRemoteInputSpinner(boolean param1Boolean) {
      this.mN.extras.putBoolean("android.remoteInputSpinner", param1Boolean);
      return this;
    }
    
    public Builder setShowWhen(boolean param1Boolean) {
      this.mN.extras.putBoolean("android.showWhen", param1Boolean);
      return this;
    }
    
    public Builder setSmallIcon(int param1Int) {
      Icon icon;
      if (param1Int != 0) {
        icon = Icon.createWithResource(this.mContext, param1Int);
      } else {
        icon = null;
      } 
      return setSmallIcon(icon);
    }
    
    public Builder setSmallIcon(int param1Int1, int param1Int2) {
      this.mN.iconLevel = param1Int2;
      return setSmallIcon(param1Int1);
    }
    
    public Builder setSmallIcon(Icon param1Icon) {
      this.mN.setSmallIcon(param1Icon);
      if (param1Icon != null && param1Icon.getType() == 2)
        this.mN.icon = param1Icon.getResId(); 
      return this;
    }
    
    public Builder setSortKey(String param1String) {
      Notification.access$1602(this.mN, param1String);
      return this;
    }
    
    @Deprecated
    public Builder setSound(Uri param1Uri) {
      this.mN.sound = param1Uri;
      this.mN.audioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
      return this;
    }
    
    @Deprecated
    public Builder setSound(Uri param1Uri, int param1Int) {
      PlayerBase.deprecateStreamTypeForPlayback(param1Int, "Notification", "setSound()");
      this.mN.sound = param1Uri;
      this.mN.audioStreamType = param1Int;
      return this;
    }
    
    @Deprecated
    public Builder setSound(Uri param1Uri, AudioAttributes param1AudioAttributes) {
      this.mN.sound = param1Uri;
      this.mN.audioAttributes = param1AudioAttributes;
      return this;
    }
    
    public Builder setStyle(Notification.Style param1Style) {
      if (this.mStyle != param1Style) {
        this.mStyle = param1Style;
        if (param1Style != null) {
          param1Style.setBuilder(this);
          this.mN.extras.putString("android.template", param1Style.getClass().getName());
        } else {
          this.mN.extras.remove("android.template");
        } 
      } 
      return this;
    }
    
    public Builder setSubText(CharSequence param1CharSequence) {
      this.mN.extras.putCharSequence("android.subText", Notification.safeCharSequence(param1CharSequence));
      return this;
    }
    
    public Builder setTicker(CharSequence param1CharSequence) {
      this.mN.tickerText = Notification.safeCharSequence(param1CharSequence);
      return this;
    }
    
    @Deprecated
    public Builder setTicker(CharSequence param1CharSequence, RemoteViews param1RemoteViews) {
      setTicker(param1CharSequence);
      return this;
    }
    
    @Deprecated
    public Builder setTimeout(long param1Long) {
      Notification.access$1202(this.mN, param1Long);
      return this;
    }
    
    public Builder setTimeoutAfter(long param1Long) {
      Notification.access$1202(this.mN, param1Long);
      return this;
    }
    
    public Builder setUsesChronometer(boolean param1Boolean) {
      this.mN.extras.putBoolean("android.showChronometer", param1Boolean);
      return this;
    }
    
    @Deprecated
    public Builder setVibrate(long[] param1ArrayOflong) {
      this.mN.vibrate = param1ArrayOflong;
      return this;
    }
    
    public Builder setVisibility(int param1Int) {
      this.mN.visibility = param1Int;
      return this;
    }
    
    public Builder setWhen(long param1Long) {
      this.mN.when = param1Long;
      return this;
    }
    
    public boolean usesStandardHeader() {
      boolean bool2;
      boolean bool3;
      boolean bool = this.mN.mUsesStandardHeader;
      boolean bool1 = true;
      if (bool)
        return true; 
      if ((this.mContext.getApplicationInfo()).targetSdkVersion >= 24 && this.mN.contentView == null && this.mN.bigContentView == null)
        return true; 
      if (this.mN.contentView == null || Notification.STANDARD_LAYOUTS.contains(Integer.valueOf(this.mN.contentView.getLayoutId()))) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (this.mN.bigContentView == null || Notification.STANDARD_LAYOUTS.contains(Integer.valueOf(this.mN.bigContentView.getLayoutId()))) {
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
  
  private static class BuilderRemoteViews extends RemoteViews {
    public BuilderRemoteViews(ApplicationInfo param1ApplicationInfo, int param1Int) {
      super(param1ApplicationInfo, param1Int);
    }
    
    public BuilderRemoteViews(Parcel param1Parcel) {
      super(param1Parcel);
    }
    
    public BuilderRemoteViews clone() {
      Parcel parcel = Parcel.obtain();
      writeToParcel(parcel, 0);
      parcel.setDataPosition(0);
      BuilderRemoteViews builderRemoteViews = new BuilderRemoteViews(parcel);
      parcel.recycle();
      return builderRemoteViews;
    }
    
    protected boolean shouldUseStaticFilter() {
      return true;
    }
  }
  
  public static final class CarExtender implements Extender {
    private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
    
    private static final String EXTRA_COLOR = "app_color";
    
    private static final String EXTRA_CONVERSATION = "car_conversation";
    
    private static final String EXTRA_LARGE_ICON = "large_icon";
    
    private static final String TAG = "CarExtender";
    
    private int mColor;
    
    private Bitmap mLargeIcon;
    
    private UnreadConversation mUnreadConversation;
    
    public CarExtender() {
      this.mColor = 0;
    }
    
    public CarExtender(Notification param1Notification) {
      Bundle bundle;
      this.mColor = 0;
      if (param1Notification.extras == null) {
        param1Notification = null;
      } else {
        bundle = param1Notification.extras.getBundle("android.car.EXTENSIONS");
      } 
      if (bundle != null) {
        this.mLargeIcon = (Bitmap)bundle.getParcelable("large_icon");
        this.mColor = bundle.getInt("app_color", 0);
        this.mUnreadConversation = UnreadConversation.getUnreadConversationFromBundle(bundle.getBundle("car_conversation"));
      } 
    }
    
    public Notification.Builder extend(Notification.Builder param1Builder) {
      Bundle bundle = new Bundle();
      Bitmap bitmap = this.mLargeIcon;
      if (bitmap != null)
        bundle.putParcelable("large_icon", (Parcelable)bitmap); 
      int i = this.mColor;
      if (i != 0)
        bundle.putInt("app_color", i); 
      UnreadConversation unreadConversation = this.mUnreadConversation;
      if (unreadConversation != null)
        bundle.putBundle("car_conversation", unreadConversation.getBundleForUnreadConversation()); 
      param1Builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    public int getColor() {
      return this.mColor;
    }
    
    public Bitmap getLargeIcon() {
      return this.mLargeIcon;
    }
    
    public UnreadConversation getUnreadConversation() {
      return this.mUnreadConversation;
    }
    
    public CarExtender setColor(int param1Int) {
      this.mColor = param1Int;
      return this;
    }
    
    public CarExtender setLargeIcon(Bitmap param1Bitmap) {
      this.mLargeIcon = param1Bitmap;
      return this;
    }
    
    public CarExtender setUnreadConversation(UnreadConversation param1UnreadConversation) {
      this.mUnreadConversation = param1UnreadConversation;
      return this;
    }
    
    public static class Builder {
      private long mLatestTimestamp;
      
      private final List<String> mMessages = new ArrayList<>();
      
      private final String mParticipant;
      
      private PendingIntent mReadPendingIntent;
      
      private RemoteInput mRemoteInput;
      
      private PendingIntent mReplyPendingIntent;
      
      public Builder(String param2String) {
        this.mParticipant = param2String;
      }
      
      public Builder addMessage(String param2String) {
        this.mMessages.add(param2String);
        return this;
      }
      
      public Notification.CarExtender.UnreadConversation build() {
        List<String> list = this.mMessages;
        String[] arrayOfString = list.<String>toArray(new String[list.size()]);
        String str = this.mParticipant;
        RemoteInput remoteInput = this.mRemoteInput;
        PendingIntent pendingIntent1 = this.mReplyPendingIntent;
        PendingIntent pendingIntent2 = this.mReadPendingIntent;
        long l = this.mLatestTimestamp;
        return new Notification.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
      }
      
      public Builder setLatestTimestamp(long param2Long) {
        this.mLatestTimestamp = param2Long;
        return this;
      }
      
      public Builder setReadPendingIntent(PendingIntent param2PendingIntent) {
        this.mReadPendingIntent = param2PendingIntent;
        return this;
      }
      
      public Builder setReplyAction(PendingIntent param2PendingIntent, RemoteInput param2RemoteInput) {
        this.mRemoteInput = param2RemoteInput;
        this.mReplyPendingIntent = param2PendingIntent;
        return this;
      }
    }
    
    public static class UnreadConversation {
      private static final String KEY_AUTHOR = "author";
      
      private static final String KEY_MESSAGES = "messages";
      
      private static final String KEY_ON_READ = "on_read";
      
      private static final String KEY_ON_REPLY = "on_reply";
      
      private static final String KEY_PARTICIPANTS = "participants";
      
      private static final String KEY_REMOTE_INPUT = "remote_input";
      
      private static final String KEY_TEXT = "text";
      
      private static final String KEY_TIMESTAMP = "timestamp";
      
      private final long mLatestTimestamp;
      
      private final String[] mMessages;
      
      private final String[] mParticipants;
      
      private final PendingIntent mReadPendingIntent;
      
      private final RemoteInput mRemoteInput;
      
      private final PendingIntent mReplyPendingIntent;
      
      UnreadConversation(String[] param2ArrayOfString1, RemoteInput param2RemoteInput, PendingIntent param2PendingIntent1, PendingIntent param2PendingIntent2, String[] param2ArrayOfString2, long param2Long) {
        this.mMessages = param2ArrayOfString1;
        this.mRemoteInput = param2RemoteInput;
        this.mReadPendingIntent = param2PendingIntent2;
        this.mReplyPendingIntent = param2PendingIntent1;
        this.mParticipants = param2ArrayOfString2;
        this.mLatestTimestamp = param2Long;
      }
      
      static UnreadConversation getUnreadConversationFromBundle(Bundle param2Bundle) {
        if (param2Bundle == null)
          return null; 
        Parcelable[] arrayOfParcelable = param2Bundle.getParcelableArray("messages");
        String[] arrayOfString1 = null;
        if (arrayOfParcelable != null) {
          boolean bool2;
          arrayOfString1 = new String[arrayOfParcelable.length];
          boolean bool1 = true;
          byte b = 0;
          while (true) {
            bool2 = bool1;
            if (b < arrayOfString1.length) {
              if (!(arrayOfParcelable[b] instanceof Bundle)) {
                bool2 = false;
                break;
              } 
              arrayOfString1[b] = ((Bundle)arrayOfParcelable[b]).getString("text");
              if (arrayOfString1[b] == null) {
                bool2 = false;
                break;
              } 
              b++;
              continue;
            } 
            break;
          } 
          if (!bool2)
            return null; 
        } 
        PendingIntent pendingIntent2 = (PendingIntent)param2Bundle.getParcelable("on_read");
        PendingIntent pendingIntent1 = (PendingIntent)param2Bundle.getParcelable("on_reply");
        RemoteInput remoteInput = (RemoteInput)param2Bundle.getParcelable("remote_input");
        String[] arrayOfString2 = param2Bundle.getStringArray("participants");
        return (arrayOfString2 == null || arrayOfString2.length != 1) ? null : new UnreadConversation(arrayOfString1, remoteInput, pendingIntent1, pendingIntent2, arrayOfString2, param2Bundle.getLong("timestamp"));
      }
      
      Bundle getBundleForUnreadConversation() {
        Bundle bundle = new Bundle();
        String str1 = null;
        String[] arrayOfString = this.mParticipants;
        String str2 = str1;
        if (arrayOfString != null) {
          str2 = str1;
          if (arrayOfString.length > 1)
            str2 = arrayOfString[0]; 
        } 
        Parcelable[] arrayOfParcelable = new Parcelable[this.mMessages.length];
        for (byte b = 0; b < arrayOfParcelable.length; b++) {
          Bundle bundle1 = new Bundle();
          bundle1.putString("text", this.mMessages[b]);
          bundle1.putString("author", str2);
          arrayOfParcelable[b] = (Parcelable)bundle1;
        } 
        bundle.putParcelableArray("messages", arrayOfParcelable);
        RemoteInput remoteInput = this.mRemoteInput;
        if (remoteInput != null)
          bundle.putParcelable("remote_input", remoteInput); 
        bundle.putParcelable("on_reply", this.mReplyPendingIntent);
        bundle.putParcelable("on_read", this.mReadPendingIntent);
        bundle.putStringArray("participants", this.mParticipants);
        bundle.putLong("timestamp", this.mLatestTimestamp);
        return bundle;
      }
      
      public long getLatestTimestamp() {
        return this.mLatestTimestamp;
      }
      
      public String[] getMessages() {
        return this.mMessages;
      }
      
      public String getParticipant() {
        String[] arrayOfString = this.mParticipants;
        if (arrayOfString.length > 0) {
          String str = arrayOfString[0];
        } else {
          arrayOfString = null;
        } 
        return (String)arrayOfString;
      }
      
      public String[] getParticipants() {
        return this.mParticipants;
      }
      
      public PendingIntent getReadPendingIntent() {
        return this.mReadPendingIntent;
      }
      
      public RemoteInput getRemoteInput() {
        return this.mRemoteInput;
      }
      
      public PendingIntent getReplyPendingIntent() {
        return this.mReplyPendingIntent;
      }
    }
  }
  
  public static class Builder {
    private long mLatestTimestamp;
    
    private final List<String> mMessages = new ArrayList<>();
    
    private final String mParticipant;
    
    private PendingIntent mReadPendingIntent;
    
    private RemoteInput mRemoteInput;
    
    private PendingIntent mReplyPendingIntent;
    
    public Builder(String param1String) {
      this.mParticipant = param1String;
    }
    
    public Builder addMessage(String param1String) {
      this.mMessages.add(param1String);
      return this;
    }
    
    public Notification.CarExtender.UnreadConversation build() {
      List<String> list = this.mMessages;
      String[] arrayOfString = list.<String>toArray(new String[list.size()]);
      String str = this.mParticipant;
      RemoteInput remoteInput = this.mRemoteInput;
      PendingIntent pendingIntent1 = this.mReplyPendingIntent;
      PendingIntent pendingIntent2 = this.mReadPendingIntent;
      long l = this.mLatestTimestamp;
      return new Notification.CarExtender.UnreadConversation(arrayOfString, remoteInput, pendingIntent1, pendingIntent2, new String[] { str }, l);
    }
    
    public Builder setLatestTimestamp(long param1Long) {
      this.mLatestTimestamp = param1Long;
      return this;
    }
    
    public Builder setReadPendingIntent(PendingIntent param1PendingIntent) {
      this.mReadPendingIntent = param1PendingIntent;
      return this;
    }
    
    public Builder setReplyAction(PendingIntent param1PendingIntent, RemoteInput param1RemoteInput) {
      this.mRemoteInput = param1RemoteInput;
      this.mReplyPendingIntent = param1PendingIntent;
      return this;
    }
  }
  
  public static class UnreadConversation {
    private static final String KEY_AUTHOR = "author";
    
    private static final String KEY_MESSAGES = "messages";
    
    private static final String KEY_ON_READ = "on_read";
    
    private static final String KEY_ON_REPLY = "on_reply";
    
    private static final String KEY_PARTICIPANTS = "participants";
    
    private static final String KEY_REMOTE_INPUT = "remote_input";
    
    private static final String KEY_TEXT = "text";
    
    private static final String KEY_TIMESTAMP = "timestamp";
    
    private final long mLatestTimestamp;
    
    private final String[] mMessages;
    
    private final String[] mParticipants;
    
    private final PendingIntent mReadPendingIntent;
    
    private final RemoteInput mRemoteInput;
    
    private final PendingIntent mReplyPendingIntent;
    
    UnreadConversation(String[] param1ArrayOfString1, RemoteInput param1RemoteInput, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, String[] param1ArrayOfString2, long param1Long) {
      this.mMessages = param1ArrayOfString1;
      this.mRemoteInput = param1RemoteInput;
      this.mReadPendingIntent = param1PendingIntent2;
      this.mReplyPendingIntent = param1PendingIntent1;
      this.mParticipants = param1ArrayOfString2;
      this.mLatestTimestamp = param1Long;
    }
    
    static UnreadConversation getUnreadConversationFromBundle(Bundle param1Bundle) {
      if (param1Bundle == null)
        return null; 
      Parcelable[] arrayOfParcelable = param1Bundle.getParcelableArray("messages");
      String[] arrayOfString1 = null;
      if (arrayOfParcelable != null) {
        boolean bool2;
        arrayOfString1 = new String[arrayOfParcelable.length];
        boolean bool1 = true;
        byte b = 0;
        while (true) {
          bool2 = bool1;
          if (b < arrayOfString1.length) {
            if (!(arrayOfParcelable[b] instanceof Bundle)) {
              bool2 = false;
              break;
            } 
            arrayOfString1[b] = ((Bundle)arrayOfParcelable[b]).getString("text");
            if (arrayOfString1[b] == null) {
              bool2 = false;
              break;
            } 
            b++;
            continue;
          } 
          break;
        } 
        if (!bool2)
          return null; 
      } 
      PendingIntent pendingIntent2 = (PendingIntent)param1Bundle.getParcelable("on_read");
      PendingIntent pendingIntent1 = (PendingIntent)param1Bundle.getParcelable("on_reply");
      RemoteInput remoteInput = (RemoteInput)param1Bundle.getParcelable("remote_input");
      String[] arrayOfString2 = param1Bundle.getStringArray("participants");
      return (arrayOfString2 == null || arrayOfString2.length != 1) ? null : new UnreadConversation(arrayOfString1, remoteInput, pendingIntent1, pendingIntent2, arrayOfString2, param1Bundle.getLong("timestamp"));
    }
    
    Bundle getBundleForUnreadConversation() {
      Bundle bundle = new Bundle();
      String str1 = null;
      String[] arrayOfString = this.mParticipants;
      String str2 = str1;
      if (arrayOfString != null) {
        str2 = str1;
        if (arrayOfString.length > 1)
          str2 = arrayOfString[0]; 
      } 
      Parcelable[] arrayOfParcelable = new Parcelable[this.mMessages.length];
      for (byte b = 0; b < arrayOfParcelable.length; b++) {
        Bundle bundle1 = new Bundle();
        bundle1.putString("text", this.mMessages[b]);
        bundle1.putString("author", str2);
        arrayOfParcelable[b] = (Parcelable)bundle1;
      } 
      bundle.putParcelableArray("messages", arrayOfParcelable);
      RemoteInput remoteInput = this.mRemoteInput;
      if (remoteInput != null)
        bundle.putParcelable("remote_input", remoteInput); 
      bundle.putParcelable("on_reply", this.mReplyPendingIntent);
      bundle.putParcelable("on_read", this.mReadPendingIntent);
      bundle.putStringArray("participants", this.mParticipants);
      bundle.putLong("timestamp", this.mLatestTimestamp);
      return bundle;
    }
    
    public long getLatestTimestamp() {
      return this.mLatestTimestamp;
    }
    
    public String[] getMessages() {
      return this.mMessages;
    }
    
    public String getParticipant() {
      String[] arrayOfString = this.mParticipants;
      if (arrayOfString.length > 0) {
        String str = arrayOfString[0];
      } else {
        arrayOfString = null;
      } 
      return (String)arrayOfString;
    }
    
    public String[] getParticipants() {
      return this.mParticipants;
    }
    
    public PendingIntent getReadPendingIntent() {
      return this.mReadPendingIntent;
    }
    
    public RemoteInput getRemoteInput() {
      return this.mRemoteInput;
    }
    
    public PendingIntent getReplyPendingIntent() {
      return this.mReplyPendingIntent;
    }
  }
  
  public static class DecoratedCustomViewStyle extends Style {
    private void buildIntoRemoteViewContent(RemoteViews param1RemoteViews1, RemoteViews param1RemoteViews2, Notification.TemplateBindResult param1TemplateBindResult) {
      byte b = -1;
      if (param1RemoteViews2 != null) {
        param1RemoteViews2 = param1RemoteViews2.clone();
        param1RemoteViews1.removeAllViewsExceptId(16909225, 16908301);
        param1RemoteViews1.addView(16909225, param1RemoteViews2, 0);
        param1RemoteViews1.addFlags(1);
        b = 0;
      } 
      param1RemoteViews1.setIntTag(16909225, 16909223, b);
      param1RemoteViews1.setViewLayoutMarginEnd(16909225, this.mBuilder.mContext.getResources().getDimensionPixelSize(17105345) + param1TemplateBindResult.getIconMarginEnd());
    }
    
    private RemoteViews makeDecoratedBigContentView() {
      RemoteViews remoteViews1;
      if (this.mBuilder.mN.bigContentView == null) {
        remoteViews1 = this.mBuilder.mN.contentView;
      } else {
        remoteViews1 = this.mBuilder.mN.bigContentView;
      } 
      if (this.mBuilder.mActions.size() == 0)
        return makeStandardTemplateWithCustomContent(remoteViews1); 
      Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult();
      RemoteViews remoteViews2 = this.mBuilder.applyStandardTemplateWithActions(this.mBuilder.getBigBaseLayoutResource(), templateBindResult);
      buildIntoRemoteViewContent(remoteViews2, remoteViews1, templateBindResult);
      return remoteViews2;
    }
    
    private RemoteViews makeDecoratedHeadsUpContentView() {
      RemoteViews remoteViews1;
      if (this.mBuilder.mN.headsUpContentView == null) {
        remoteViews1 = this.mBuilder.mN.contentView;
      } else {
        remoteViews1 = this.mBuilder.mN.headsUpContentView;
      } 
      if (this.mBuilder.mActions.size() == 0)
        return makeStandardTemplateWithCustomContent(remoteViews1); 
      Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult();
      RemoteViews remoteViews2 = this.mBuilder.applyStandardTemplateWithActions(this.mBuilder.getBigBaseLayoutResource(), templateBindResult);
      buildIntoRemoteViewContent(remoteViews2, remoteViews1, templateBindResult);
      return remoteViews2;
    }
    
    private RemoteViews makeStandardTemplateWithCustomContent(RemoteViews param1RemoteViews) {
      Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult();
      RemoteViews remoteViews = this.mBuilder.applyStandardTemplate(this.mBuilder.getBaseLayoutResource(), templateBindResult);
      buildIntoRemoteViewContent(remoteViews, param1RemoteViews, templateBindResult);
      return remoteViews;
    }
    
    public boolean areNotificationsVisiblyDifferent(Notification.Style param1Style) {
      return (param1Style == null || getClass() != param1Style.getClass());
    }
    
    public boolean displayCustomViewInline() {
      return true;
    }
    
    public RemoteViews makeBigContentView() {
      return makeDecoratedBigContentView();
    }
    
    public RemoteViews makeContentView(boolean param1Boolean) {
      return makeStandardTemplateWithCustomContent(this.mBuilder.mN.contentView);
    }
    
    public RemoteViews makeHeadsUpContentView(boolean param1Boolean) {
      return makeDecoratedHeadsUpContentView();
    }
  }
  
  public static class DecoratedMediaCustomViewStyle extends MediaStyle {
    private RemoteViews buildIntoRemoteView(RemoteViews param1RemoteViews1, int param1Int, RemoteViews param1RemoteViews2) {
      if (param1RemoteViews2 != null) {
        param1RemoteViews2 = param1RemoteViews2.clone();
        param1RemoteViews2.overrideTextColors(this.mBuilder.getPrimaryTextColor(this.mBuilder.mParams));
        param1RemoteViews1.removeAllViews(param1Int);
        param1RemoteViews1.addView(param1Int, param1RemoteViews2);
        param1RemoteViews1.addFlags(1);
      } 
      return param1RemoteViews1;
    }
    
    private RemoteViews makeBigContentViewWithCustomContent(RemoteViews param1RemoteViews) {
      RemoteViews remoteViews = super.makeBigContentView();
      return (remoteViews != null) ? buildIntoRemoteView(remoteViews, 16909225, param1RemoteViews) : ((param1RemoteViews != this.mBuilder.mN.contentView) ? buildIntoRemoteView(super.makeContentView(false), 16909222, param1RemoteViews) : null);
    }
    
    public boolean areNotificationsVisiblyDifferent(Notification.Style param1Style) {
      return (param1Style == null || getClass() != param1Style.getClass());
    }
    
    public boolean displayCustomViewInline() {
      return true;
    }
    
    public RemoteViews makeBigContentView() {
      RemoteViews remoteViews;
      if (this.mBuilder.mN.bigContentView != null) {
        remoteViews = this.mBuilder.mN.bigContentView;
      } else {
        remoteViews = this.mBuilder.mN.contentView;
      } 
      return makeBigContentViewWithCustomContent(remoteViews);
    }
    
    public RemoteViews makeContentView(boolean param1Boolean) {
      return buildIntoRemoteView(super.makeContentView(false), 16909222, this.mBuilder.mN.contentView);
    }
    
    public RemoteViews makeHeadsUpContentView(boolean param1Boolean) {
      RemoteViews remoteViews;
      if (this.mBuilder.mN.headsUpContentView != null) {
        remoteViews = this.mBuilder.mN.headsUpContentView;
      } else {
        remoteViews = this.mBuilder.mN.contentView;
      } 
      return makeBigContentViewWithCustomContent(remoteViews);
    }
  }
  
  public static interface Extender {
    Notification.Builder extend(Notification.Builder param1Builder);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface GroupAlertBehavior {}
  
  public static class InboxStyle extends Style {
    private static final int NUMBER_OF_HISTORY_ALLOWED_UNTIL_REDUCTION = 1;
    
    private ArrayList<CharSequence> mTexts = new ArrayList<>(5);
    
    public InboxStyle() {}
    
    @Deprecated
    public InboxStyle(Notification.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    private void handleInboxImageMargin(RemoteViews param1RemoteViews, int param1Int1, boolean param1Boolean, int param1Int2) {
      byte b = 0;
      int i = b;
      if (param1Boolean) {
        Bundle bundle = this.mBuilder.mN.extras;
        boolean bool = false;
        i = bundle.getInt("android.progressMax", 0);
        param1Boolean = this.mBuilder.mN.extras.getBoolean("android.progressIndeterminate");
        if (i != 0 || param1Boolean)
          bool = true; 
        i = b;
        if (!bool)
          i = param1Int2; 
      } 
      param1RemoteViews.setViewLayoutMarginEnd(param1Int1, i);
    }
    
    public void addExtras(Bundle param1Bundle) {
      super.addExtras(param1Bundle);
      CharSequence[] arrayOfCharSequence = new CharSequence[this.mTexts.size()];
      param1Bundle.putCharSequenceArray("android.textLines", this.mTexts.<CharSequence>toArray(arrayOfCharSequence));
    }
    
    public InboxStyle addLine(CharSequence param1CharSequence) {
      this.mTexts.add(Notification.safeCharSequence(param1CharSequence));
      return this;
    }
    
    public boolean areNotificationsVisiblyDifferent(Notification.Style param1Style) {
      if (param1Style == null || getClass() != param1Style.getClass())
        return true; 
      InboxStyle inboxStyle = (InboxStyle)param1Style;
      ArrayList<CharSequence> arrayList1 = getLines();
      ArrayList<CharSequence> arrayList2 = inboxStyle.getLines();
      int i = arrayList1.size();
      if (i != arrayList2.size())
        return true; 
      for (byte b = 0; b < i; b++) {
        if (!Objects.equals(String.valueOf(arrayList1.get(b)), String.valueOf(arrayList2.get(b))))
          return true; 
      } 
      return false;
    }
    
    public ArrayList<CharSequence> getLines() {
      return this.mTexts;
    }
    
    public RemoteViews makeBigContentView() {
      // Byte code:
      //   0: aload_0
      //   1: getfield mBuilder : Landroid/app/Notification$Builder;
      //   4: getfield mParams : Landroid/app/Notification$StandardTemplateParams;
      //   7: invokevirtual reset : ()Landroid/app/Notification$StandardTemplateParams;
      //   10: aload_0
      //   11: getfield mBuilder : Landroid/app/Notification$Builder;
      //   14: invokevirtual fillTextsFrom : (Landroid/app/Notification$Builder;)Landroid/app/Notification$StandardTemplateParams;
      //   17: aconst_null
      //   18: invokevirtual text : (Ljava/lang/CharSequence;)Landroid/app/Notification$StandardTemplateParams;
      //   21: astore_1
      //   22: new android/app/Notification$TemplateBindResult
      //   25: dup
      //   26: aconst_null
      //   27: invokespecial <init> : (Landroid/app/Notification$1;)V
      //   30: astore_2
      //   31: aload_0
      //   32: aload_0
      //   33: getfield mBuilder : Landroid/app/Notification$Builder;
      //   36: invokestatic access$4200 : (Landroid/app/Notification$Builder;)I
      //   39: aload_1
      //   40: aload_2
      //   41: invokevirtual getStandardView : (ILandroid/app/Notification$StandardTemplateParams;Landroid/app/Notification$TemplateBindResult;)Landroid/widget/RemoteViews;
      //   44: astore_3
      //   45: bipush #7
      //   47: newarray int
      //   49: astore #4
      //   51: aload #4
      //   53: dup
      //   54: iconst_0
      //   55: ldc 16909063
      //   57: iastore
      //   58: dup
      //   59: iconst_1
      //   60: ldc 16909064
      //   62: iastore
      //   63: dup
      //   64: iconst_2
      //   65: ldc 16909065
      //   67: iastore
      //   68: dup
      //   69: iconst_3
      //   70: ldc 16909066
      //   72: iastore
      //   73: dup
      //   74: iconst_4
      //   75: ldc 16909067
      //   77: iastore
      //   78: dup
      //   79: iconst_5
      //   80: ldc 16909068
      //   82: iastore
      //   83: dup
      //   84: bipush #6
      //   86: ldc 16909069
      //   88: iastore
      //   89: pop
      //   90: aload #4
      //   92: arraylength
      //   93: istore #5
      //   95: iconst_0
      //   96: istore #6
      //   98: iload #6
      //   100: iload #5
      //   102: if_icmpge -> 122
      //   105: aload_3
      //   106: aload #4
      //   108: iload #6
      //   110: iaload
      //   111: bipush #8
      //   113: invokevirtual setViewVisibility : (II)V
      //   116: iinc #6, 1
      //   119: goto -> 98
      //   122: aload_0
      //   123: getfield mBuilder : Landroid/app/Notification$Builder;
      //   126: invokestatic access$3500 : (Landroid/app/Notification$Builder;)Landroid/content/Context;
      //   129: invokevirtual getResources : ()Landroid/content/res/Resources;
      //   132: ldc 17105366
      //   134: invokevirtual getDimensionPixelSize : (I)I
      //   137: istore #7
      //   139: aload #4
      //   141: arraylength
      //   142: istore #5
      //   144: iload #5
      //   146: istore #6
      //   148: aload_0
      //   149: getfield mBuilder : Landroid/app/Notification$Builder;
      //   152: invokestatic access$3000 : (Landroid/app/Notification$Builder;)Ljava/util/ArrayList;
      //   155: invokevirtual size : ()I
      //   158: ifle -> 167
      //   161: iload #5
      //   163: iconst_1
      //   164: isub
      //   165: istore #6
      //   167: aload_0
      //   168: getfield mBuilder : Landroid/app/Notification$Builder;
      //   171: invokestatic access$400 : (Landroid/app/Notification$Builder;)Landroid/app/Notification;
      //   174: getfield extras : Landroid/os/Bundle;
      //   177: ldc 'android.remoteInputHistoryItems'
      //   179: ldc android/app/RemoteInputHistoryItem
      //   181: invokestatic access$000 : (Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/Class;)[Landroid/os/Parcelable;
      //   184: checkcast [Landroid/app/RemoteInputHistoryItem;
      //   187: astore #8
      //   189: aload #8
      //   191: ifnull -> 282
      //   194: aload #8
      //   196: arraylength
      //   197: iconst_1
      //   198: if_icmple -> 282
      //   201: aload #8
      //   203: arraylength
      //   204: iconst_3
      //   205: invokestatic min : (II)I
      //   208: istore #5
      //   210: aload_0
      //   211: getfield mTexts : Ljava/util/ArrayList;
      //   214: invokevirtual size : ()I
      //   217: iload #5
      //   219: iadd
      //   220: iconst_1
      //   221: isub
      //   222: istore #5
      //   224: iload #5
      //   226: iload #6
      //   228: if_icmple -> 282
      //   231: iload #5
      //   233: iload #6
      //   235: isub
      //   236: istore #9
      //   238: aload_0
      //   239: getfield mTexts : Ljava/util/ArrayList;
      //   242: invokevirtual size : ()I
      //   245: iload #6
      //   247: if_icmple -> 269
      //   250: iconst_0
      //   251: istore #5
      //   253: iconst_1
      //   254: istore #10
      //   256: iconst_0
      //   257: istore #11
      //   259: iload #6
      //   261: iload #9
      //   263: isub
      //   264: istore #6
      //   266: goto -> 291
      //   269: iload #9
      //   271: istore #5
      //   273: iconst_1
      //   274: istore #10
      //   276: iconst_0
      //   277: istore #11
      //   279: goto -> 291
      //   282: iconst_0
      //   283: istore #5
      //   285: iconst_1
      //   286: istore #10
      //   288: iconst_0
      //   289: istore #11
      //   291: iload #5
      //   293: aload_0
      //   294: getfield mTexts : Ljava/util/ArrayList;
      //   297: invokevirtual size : ()I
      //   300: if_icmpge -> 441
      //   303: iload #5
      //   305: iload #6
      //   307: if_icmpge -> 441
      //   310: aload_0
      //   311: getfield mTexts : Ljava/util/ArrayList;
      //   314: iload #5
      //   316: invokevirtual get : (I)Ljava/lang/Object;
      //   319: checkcast java/lang/CharSequence
      //   322: astore #8
      //   324: aload #8
      //   326: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
      //   329: ifne -> 435
      //   332: aload_3
      //   333: aload #4
      //   335: iload #5
      //   337: iaload
      //   338: iconst_0
      //   339: invokevirtual setViewVisibility : (II)V
      //   342: aload_3
      //   343: aload #4
      //   345: iload #5
      //   347: iaload
      //   348: aload_0
      //   349: getfield mBuilder : Landroid/app/Notification$Builder;
      //   352: aload_0
      //   353: getfield mBuilder : Landroid/app/Notification$Builder;
      //   356: aload #8
      //   358: invokestatic access$2600 : (Landroid/app/Notification$Builder;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
      //   361: invokestatic access$2700 : (Landroid/app/Notification$Builder;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
      //   364: invokevirtual setTextViewText : (ILjava/lang/CharSequence;)V
      //   367: aload_0
      //   368: getfield mBuilder : Landroid/app/Notification$Builder;
      //   371: aload_3
      //   372: aload #4
      //   374: iload #5
      //   376: iaload
      //   377: aload_1
      //   378: invokestatic access$2800 : (Landroid/app/Notification$Builder;Landroid/widget/RemoteViews;ILandroid/app/Notification$StandardTemplateParams;)V
      //   381: aload_3
      //   382: aload #4
      //   384: iload #5
      //   386: iaload
      //   387: iconst_0
      //   388: iload #7
      //   390: iconst_0
      //   391: iconst_0
      //   392: invokevirtual setViewPadding : (IIIII)V
      //   395: aload_0
      //   396: aload_3
      //   397: aload #4
      //   399: iload #5
      //   401: iaload
      //   402: iload #10
      //   404: aload_2
      //   405: invokevirtual getIconMarginEnd : ()I
      //   408: invokespecial handleInboxImageMargin : (Landroid/widget/RemoteViews;IZI)V
      //   411: iload #10
      //   413: ifeq -> 426
      //   416: aload #4
      //   418: iload #5
      //   420: iaload
      //   421: istore #11
      //   423: goto -> 429
      //   426: iconst_0
      //   427: istore #11
      //   429: iconst_0
      //   430: istore #10
      //   432: goto -> 435
      //   435: iinc #5, 1
      //   438: goto -> 291
      //   441: iload #11
      //   443: ifeq -> 470
      //   446: aload_3
      //   447: iload #11
      //   449: iconst_0
      //   450: aload_0
      //   451: getfield mBuilder : Landroid/app/Notification$Builder;
      //   454: invokestatic access$3500 : (Landroid/app/Notification$Builder;)Landroid/content/Context;
      //   457: invokevirtual getResources : ()Landroid/content/res/Resources;
      //   460: ldc 17105383
      //   462: invokevirtual getDimensionPixelSize : (I)I
      //   465: iconst_0
      //   466: iconst_0
      //   467: invokevirtual setViewPadding : (IIIII)V
      //   470: aload_3
      //   471: areturn
    }
    
    protected void restoreFromExtras(Bundle param1Bundle) {
      super.restoreFromExtras(param1Bundle);
      this.mTexts.clear();
      if (param1Bundle.containsKey("android.textLines"))
        Collections.addAll(this.mTexts, param1Bundle.getCharSequenceArray("android.textLines")); 
    }
    
    public InboxStyle setBigContentTitle(CharSequence param1CharSequence) {
      internalSetBigContentTitle(Notification.safeCharSequence(param1CharSequence));
      return this;
    }
    
    public InboxStyle setSummaryText(CharSequence param1CharSequence) {
      internalSetSummaryText(Notification.safeCharSequence(param1CharSequence));
      return this;
    }
  }
  
  public static class MediaStyle extends Style {
    static final int MAX_MEDIA_BUTTONS = 5;
    
    static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
    
    private static final int[] MEDIA_BUTTON_IDS = new int[] { 16908696, 16908697, 16908698, 16908699, 16908700 };
    
    private int[] mActionsToShowInCompact = null;
    
    private MediaSession.Token mToken;
    
    public MediaStyle() {}
    
    @Deprecated
    public MediaStyle(Notification.Builder param1Builder) {
      setBuilder(param1Builder);
    }
    
    private void bindMediaActionButton(RemoteViews param1RemoteViews, int param1Int, Notification.Action param1Action, Notification.StandardTemplateParams param1StandardTemplateParams) {
      boolean bool1;
      boolean bool2;
      int i;
      if (param1Action.actionIntent == null) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      param1RemoteViews.setViewVisibility(param1Int, 0);
      if (param1Int != 16909158)
        param1RemoteViews.setImageViewIcon(param1Int, param1Action.getIcon()); 
      if (((this.mBuilder.mContext.getResources().getConfiguration()).uiMode & 0x30) == 32) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (this.mBuilder.shouldTintActionButtons() || this.mBuilder.isColorized(param1StandardTemplateParams)) {
        i = getActionColor(param1StandardTemplateParams);
      } else {
        i = ContrastColorUtil.resolveColor(this.mBuilder.mContext, 0, bool2);
      } 
      param1RemoteViews.setDrawableTint(param1Int, false, i, PorterDuff.Mode.SRC_ATOP);
      TypedArray typedArray = this.mBuilder.mContext.obtainStyledAttributes(new int[] { 16843820 });
      int j = Color.alpha(typedArray.getColor(0, 0));
      typedArray.recycle();
      param1RemoteViews.setRippleDrawableColor(param1Int, ColorStateList.valueOf(Color.argb(j, Color.red(i), Color.green(i), Color.blue(i))));
      if (!bool1)
        param1RemoteViews.setOnClickPendingIntent(param1Int, param1Action.actionIntent); 
      param1RemoteViews.setContentDescription(param1Int, param1Action.title);
    }
    
    private int getActionColor(Notification.StandardTemplateParams param1StandardTemplateParams) {
      int i;
      if (this.mBuilder.isColorized(param1StandardTemplateParams)) {
        i = this.mBuilder.getPrimaryTextColor(param1StandardTemplateParams);
      } else {
        i = this.mBuilder.resolveContrastColor(param1StandardTemplateParams);
      } 
      return i;
    }
    
    private void handleImage(RemoteViews param1RemoteViews) {
      if (this.mBuilder.mN.hasLargeIcon()) {
        param1RemoteViews.setViewLayoutMarginEndDimen(16909124, 0);
        param1RemoteViews.setViewLayoutMarginEndDimen(16909502, 0);
      } 
    }
    
    private RemoteViews makeMediaBigContentView() {
      int i = Math.min(this.mBuilder.mActions.size(), 5);
      int[] arrayOfInt = this.mActionsToShowInCompact;
      if (arrayOfInt == null) {
        j = 0;
      } else {
        j = Math.min(arrayOfInt.length, 3);
      } 
      if (!this.mBuilder.mN.hasLargeIcon() && i <= j)
        return null; 
      Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().hasProgress(false).fillTextsFrom(this.mBuilder);
      RemoteViews remoteViews = this.mBuilder.applyStandardTemplate(17367210, standardTemplateParams, null);
      for (int j = 0; j < 5; j++) {
        if (j < i) {
          bindMediaActionButton(remoteViews, MEDIA_BUTTON_IDS[j], this.mBuilder.mActions.get(j), standardTemplateParams);
        } else {
          remoteViews.setViewVisibility(MEDIA_BUTTON_IDS[j], 8);
        } 
      } 
      bindMediaActionButton(remoteViews, 16909158, new Notification.Action(17302678, this.mBuilder.mContext.getString(17040121), null), standardTemplateParams);
      remoteViews.setViewVisibility(16909158, 8);
      handleImage(remoteViews);
      return remoteViews;
    }
    
    private RemoteViews makeMediaContentView() {
      int j;
      Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().hasProgress(false).fillTextsFrom(this.mBuilder);
      RemoteViews remoteViews = this.mBuilder.applyStandardTemplate(17367215, standardTemplateParams, null);
      int i = this.mBuilder.mActions.size();
      int[] arrayOfInt = this.mActionsToShowInCompact;
      if (arrayOfInt == null) {
        j = 0;
      } else {
        j = Math.min(arrayOfInt.length, 3);
      } 
      if (j <= i) {
        for (i = 0; i < 3; i++) {
          if (i < j) {
            Notification.Action action = this.mBuilder.mActions.get(this.mActionsToShowInCompact[i]);
            bindMediaActionButton(remoteViews, MEDIA_BUTTON_IDS[i], action, standardTemplateParams);
          } else {
            remoteViews.setViewVisibility(MEDIA_BUTTON_IDS[i], 8);
          } 
        } 
        bindMediaActionButton(remoteViews, 16909158, new Notification.Action(17302678, this.mBuilder.mContext.getString(17040121), null), standardTemplateParams);
        remoteViews.setViewVisibility(16909158, 8);
        handleImage(remoteViews);
        j = 17105345;
        if (this.mBuilder.mN.hasLargeIcon())
          j = 17105368; 
        remoteViews.setViewLayoutMarginEndDimen(16909225, j);
        return remoteViews;
      } 
      throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[] { Integer.valueOf(i), Integer.valueOf(i - 1) }));
    }
    
    public void addExtras(Bundle param1Bundle) {
      super.addExtras(param1Bundle);
      MediaSession.Token token = this.mToken;
      if (token != null)
        param1Bundle.putParcelable("android.mediaSession", (Parcelable)token); 
      int[] arrayOfInt = this.mActionsToShowInCompact;
      if (arrayOfInt != null)
        param1Bundle.putIntArray("android.compactActions", arrayOfInt); 
    }
    
    public boolean areNotificationsVisiblyDifferent(Notification.Style param1Style) {
      return (param1Style == null || getClass() != param1Style.getClass());
    }
    
    public Notification buildStyled(Notification param1Notification) {
      super.buildStyled(param1Notification);
      if (param1Notification.category == null)
        param1Notification.category = "transport"; 
      return param1Notification;
    }
    
    protected boolean hasProgress() {
      return false;
    }
    
    public RemoteViews makeBigContentView() {
      return makeMediaBigContentView();
    }
    
    public RemoteViews makeContentView(boolean param1Boolean) {
      return makeMediaContentView();
    }
    
    public RemoteViews makeHeadsUpContentView(boolean param1Boolean) {
      return makeMediaContentView();
    }
    
    protected void restoreFromExtras(Bundle param1Bundle) {
      super.restoreFromExtras(param1Bundle);
      if (param1Bundle.containsKey("android.mediaSession"))
        this.mToken = (MediaSession.Token)param1Bundle.getParcelable("android.mediaSession"); 
      if (param1Bundle.containsKey("android.compactActions"))
        this.mActionsToShowInCompact = param1Bundle.getIntArray("android.compactActions"); 
    }
    
    public MediaStyle setMediaSession(MediaSession.Token param1Token) {
      this.mToken = param1Token;
      return this;
    }
    
    public MediaStyle setShowActionsInCompactView(int... param1VarArgs) {
      this.mActionsToShowInCompact = param1VarArgs;
      return this;
    }
  }
  
  public static class MessagingStyle extends Style {
    public static final int CONVERSATION_TYPE_IMPORTANT = 2;
    
    public static final int CONVERSATION_TYPE_LEGACY = 0;
    
    public static final int CONVERSATION_TYPE_NORMAL = 1;
    
    public static final int MAXIMUM_RETAINED_MESSAGES = 25;
    
    CharSequence mConversationTitle;
    
    int mConversationType = 0;
    
    List<Message> mHistoricMessages = new ArrayList<>();
    
    boolean mIsGroupConversation;
    
    List<Message> mMessages = new ArrayList<>();
    
    Icon mShortcutIcon;
    
    int mUnreadMessageCount;
    
    Person mUser;
    
    MessagingStyle() {}
    
    public MessagingStyle(Person param1Person) {
      this.mUser = param1Person;
    }
    
    public MessagingStyle(CharSequence param1CharSequence) {
      this((new Person.Builder()).setName(param1CharSequence).build());
    }
    
    private CharSequence createConversationTitleFromMessages() {
      ArraySet arraySet = new ArraySet();
      byte b;
      for (b = 0; b < this.mMessages.size(); b++) {
        Person person = ((Message)this.mMessages.get(b)).getSenderPerson();
        if (person != null)
          arraySet.add(person.getName()); 
      } 
      SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
      int i = arraySet.size();
      for (b = 0; b < i; b++) {
        CharSequence charSequence = (CharSequence)arraySet.valueAt(b);
        if (!TextUtils.isEmpty((CharSequence)spannableStringBuilder))
          spannableStringBuilder.append(", "); 
        spannableStringBuilder.append(BidiFormatter.getInstance().unicodeWrap(charSequence));
      } 
      return (CharSequence)spannableStringBuilder;
    }
    
    private Message findLatestIncomingMessage() {
      return findLatestIncomingMessage(this.mMessages);
    }
    
    public static Message findLatestIncomingMessage(List<Message> param1List) {
      for (int i = param1List.size() - 1; i >= 0; i--) {
        Message message = param1List.get(i);
        if (message.mSender != null && !TextUtils.isEmpty(message.mSender.getName()))
          return message; 
      } 
      return !param1List.isEmpty() ? param1List.get(param1List.size() - 1) : null;
    }
    
    private void fixTitleAndTextExtras(Bundle param1Bundle) {
      CharSequence charSequence1;
      CharSequence charSequence2;
      Message message = findLatestIncomingMessage();
      Person person = null;
      if (message == null) {
        charSequence2 = null;
      } else {
        charSequence2 = message.mText;
      } 
      if (message != null) {
        if (message.mSender == null || TextUtils.isEmpty(message.mSender.getName())) {
          person = this.mUser;
        } else {
          person = message.mSender;
        } 
        charSequence1 = person.getName();
      } 
      if (!TextUtils.isEmpty(this.mConversationTitle))
        if (!TextUtils.isEmpty(charSequence1)) {
          BidiFormatter bidiFormatter = BidiFormatter.getInstance();
          charSequence1 = this.mBuilder.mContext.getString(17040713, new Object[] { bidiFormatter.unicodeWrap(this.mConversationTitle), bidiFormatter.unicodeWrap(charSequence1) });
        } else {
          charSequence1 = this.mConversationTitle;
        }  
      if (charSequence1 != null)
        param1Bundle.putCharSequence("android.title", charSequence1); 
      if (charSequence2 != null)
        param1Bundle.putCharSequence("android.text", charSequence2); 
    }
    
    private boolean hasOnlyWhiteSpaceSenders() {
      for (byte b = 0; b < this.mMessages.size(); b++) {
        Person person = ((Message)this.mMessages.get(b)).getSenderPerson();
        if (person != null && !isWhiteSpace(person.getName()))
          return false; 
      } 
      return true;
    }
    
    private boolean isWhiteSpace(CharSequence param1CharSequence) {
      if (TextUtils.isEmpty(param1CharSequence))
        return true; 
      if (param1CharSequence.toString().matches("^\\s*$"))
        return true; 
      for (byte b = 0; b < param1CharSequence.length(); b++) {
        if (param1CharSequence.charAt(b) != '​')
          return false; 
      } 
      return true;
    }
    
    private static TextAppearanceSpan makeFontColorSpan(int param1Int) {
      return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(param1Int), null);
    }
    
    private RemoteViews makeMessagingView(boolean param1Boolean1, boolean param1Boolean2) {
      CharSequence charSequence1;
      int j;
      CharSequence charSequence3;
      boolean bool2;
      int k;
      if (!TextUtils.isEmpty(this.mBigContentTitle)) {
        charSequence1 = this.mBigContentTitle;
      } else {
        charSequence1 = this.mConversationTitle;
      } 
      int i = (this.mBuilder.mContext.getApplicationInfo()).targetSdkVersion;
      boolean bool1 = true;
      if (i >= 28) {
        i = 1;
      } else {
        i = 0;
      } 
      CharSequence charSequence2 = null;
      if (i == 0) {
        boolean bool = TextUtils.isEmpty(charSequence1);
        charSequence3 = charSequence1;
        if (hasOnlyWhiteSpaceSenders()) {
          bool = true;
          charSequence3 = null;
          charSequence2 = charSequence1;
        } 
      } else {
        j = isGroupConversation() ^ true;
        charSequence3 = charSequence1;
      } 
      if (this.mConversationType != 0) {
        i = 1;
      } else {
        i = 0;
      } 
      if (this.mConversationType == 2) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      Icon icon = this.mBuilder.mN.mLargeIcon;
      Notification.TemplateBindResult templateBindResult = new Notification.TemplateBindResult();
      Notification.StandardTemplateParams standardTemplateParams = this.mBuilder.mParams.reset().hasProgress(false).title(charSequence3).text(null);
      boolean bool3 = bool1;
      if (!param1Boolean2)
        if (j != 0) {
          bool3 = bool1;
        } else {
          bool3 = false;
        }  
      standardTemplateParams = standardTemplateParams.hideLargeIcon(bool3).hideReplyIcon(param1Boolean2).headerTextSecondary(charSequence3);
      Notification.Builder builder = this.mBuilder;
      if (i != 0) {
        k = this.mBuilder.getConversationLayoutResource();
      } else {
        k = this.mBuilder.getMessagingLayoutResource();
      } 
      RemoteViews remoteViews = builder.applyStandardTemplateWithActions(k, standardTemplateParams, templateBindResult);
      addExtras(this.mBuilder.mN.extras);
      if (i == 0)
        remoteViews.setViewLayoutMarginEnd(16909239, templateBindResult.getIconMarginEnd()); 
      if (this.mBuilder.isColorized(standardTemplateParams)) {
        k = this.mBuilder.getPrimaryTextColor(standardTemplateParams);
      } else {
        k = this.mBuilder.resolveContrastColor(standardTemplateParams);
      } 
      remoteViews.setInt(16909478, "setLayoutColor", k);
      remoteViews.setInt(16909478, "setSenderTextColor", this.mBuilder.getPrimaryTextColor(standardTemplateParams));
      remoteViews.setInt(16909478, "setMessageTextColor", this.mBuilder.getSecondaryTextColor(standardTemplateParams));
      remoteViews.setInt(16909478, "setNotificationBackgroundColor", this.mBuilder.resolveBackgroundColor(standardTemplateParams));
      remoteViews.setBoolean(16909478, "setIsCollapsed", param1Boolean1);
      remoteViews.setIcon(16909478, "setAvatarReplacement", this.mBuilder.mN.mLargeIcon);
      remoteViews.setCharSequence(16909478, "setNameReplacement", charSequence2);
      remoteViews.setBoolean(16909478, "setIsOneToOne", j);
      remoteViews.setCharSequence(16909478, "setConversationTitle", charSequence3);
      if (i != 0) {
        remoteViews.setIcon(16909478, "setShortcutIcon", this.mShortcutIcon);
        remoteViews.setBoolean(16909478, "setIsImportantConversation", bool2);
      } 
      remoteViews.setIcon(16909478, "setLargeIcon", icon);
      remoteViews.setBundle(16909478, "setData", this.mBuilder.mN.extras);
      return remoteViews;
    }
    
    public void addExtras(Bundle param1Bundle) {
      super.addExtras(param1Bundle);
      Person person = this.mUser;
      if (person != null) {
        param1Bundle.putCharSequence("android.selfDisplayName", person.getName());
        param1Bundle.putParcelable("android.messagingUser", this.mUser);
      } 
      CharSequence charSequence = this.mConversationTitle;
      if (charSequence != null)
        param1Bundle.putCharSequence("android.conversationTitle", charSequence); 
      if (!this.mMessages.isEmpty())
        param1Bundle.putParcelableArray("android.messages", (Parcelable[])Message.getBundleArrayForMessages(this.mMessages)); 
      if (!this.mHistoricMessages.isEmpty())
        param1Bundle.putParcelableArray("android.messages.historic", (Parcelable[])Message.getBundleArrayForMessages(this.mHistoricMessages)); 
      Icon icon = this.mShortcutIcon;
      if (icon != null)
        param1Bundle.putParcelable("android.conversationIcon", (Parcelable)icon); 
      param1Bundle.putInt("android.conversationUnreadMessageCount", this.mUnreadMessageCount);
      fixTitleAndTextExtras(param1Bundle);
      param1Bundle.putBoolean("android.isGroupConversation", this.mIsGroupConversation);
    }
    
    public MessagingStyle addHistoricMessage(Message param1Message) {
      this.mHistoricMessages.add(param1Message);
      if (this.mHistoricMessages.size() > 25)
        this.mHistoricMessages.remove(0); 
      return this;
    }
    
    public MessagingStyle addMessage(Message param1Message) {
      this.mMessages.add(param1Message);
      if (this.mMessages.size() > 25)
        this.mMessages.remove(0); 
      return this;
    }
    
    public MessagingStyle addMessage(CharSequence param1CharSequence, long param1Long, Person param1Person) {
      return addMessage(new Message(param1CharSequence, param1Long, param1Person));
    }
    
    public MessagingStyle addMessage(CharSequence param1CharSequence1, long param1Long, CharSequence param1CharSequence2) {
      Person person;
      if (param1CharSequence2 == null) {
        param1CharSequence2 = null;
      } else {
        person = (new Person.Builder()).setName(param1CharSequence2).build();
      } 
      return addMessage(param1CharSequence1, param1Long, person);
    }
    
    public boolean areNotificationsVisiblyDifferent(Notification.Style param1Style) {
      // Byte code:
      //   0: aload_1
      //   1: ifnull -> 289
      //   4: aload_0
      //   5: invokevirtual getClass : ()Ljava/lang/Class;
      //   8: aload_1
      //   9: invokevirtual getClass : ()Ljava/lang/Class;
      //   12: if_acmpeq -> 18
      //   15: goto -> 289
      //   18: aload_1
      //   19: checkcast android/app/Notification$MessagingStyle
      //   22: astore_1
      //   23: aload_0
      //   24: invokevirtual getMessages : ()Ljava/util/List;
      //   27: astore_2
      //   28: aload_1
      //   29: invokevirtual getMessages : ()Ljava/util/List;
      //   32: astore_3
      //   33: aload_2
      //   34: ifnull -> 43
      //   37: aload_3
      //   38: astore_1
      //   39: aload_3
      //   40: ifnonnull -> 51
      //   43: new java/util/ArrayList
      //   46: dup
      //   47: invokespecial <init> : ()V
      //   50: astore_1
      //   51: aload_2
      //   52: invokeinterface size : ()I
      //   57: istore #4
      //   59: iload #4
      //   61: aload_1
      //   62: invokeinterface size : ()I
      //   67: if_icmpeq -> 72
      //   70: iconst_1
      //   71: ireturn
      //   72: iconst_0
      //   73: istore #5
      //   75: iload #5
      //   77: iload #4
      //   79: if_icmpge -> 287
      //   82: aload_2
      //   83: iload #5
      //   85: invokeinterface get : (I)Ljava/lang/Object;
      //   90: checkcast android/app/Notification$MessagingStyle$Message
      //   93: astore #6
      //   95: aload_1
      //   96: iload #5
      //   98: invokeinterface get : (I)Ljava/lang/Object;
      //   103: checkcast android/app/Notification$MessagingStyle$Message
      //   106: astore #7
      //   108: aload #6
      //   110: invokevirtual getText : ()Ljava/lang/CharSequence;
      //   113: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   116: aload #7
      //   118: invokevirtual getText : ()Ljava/lang/CharSequence;
      //   121: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   124: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
      //   127: ifne -> 132
      //   130: iconst_1
      //   131: ireturn
      //   132: aload #6
      //   134: invokevirtual getDataUri : ()Landroid/net/Uri;
      //   137: aload #7
      //   139: invokevirtual getDataUri : ()Landroid/net/Uri;
      //   142: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
      //   145: ifne -> 150
      //   148: iconst_1
      //   149: ireturn
      //   150: aload #6
      //   152: invokevirtual getSenderPerson : ()Landroid/app/Person;
      //   155: ifnonnull -> 167
      //   158: aload #6
      //   160: invokevirtual getSender : ()Ljava/lang/CharSequence;
      //   163: astore_3
      //   164: goto -> 176
      //   167: aload #6
      //   169: invokevirtual getSenderPerson : ()Landroid/app/Person;
      //   172: invokevirtual getName : ()Ljava/lang/CharSequence;
      //   175: astore_3
      //   176: aload_3
      //   177: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   180: astore #8
      //   182: aload #7
      //   184: invokevirtual getSenderPerson : ()Landroid/app/Person;
      //   187: ifnonnull -> 199
      //   190: aload #7
      //   192: invokevirtual getSender : ()Ljava/lang/CharSequence;
      //   195: astore_3
      //   196: goto -> 208
      //   199: aload #7
      //   201: invokevirtual getSenderPerson : ()Landroid/app/Person;
      //   204: invokevirtual getName : ()Ljava/lang/CharSequence;
      //   207: astore_3
      //   208: aload #8
      //   210: aload_3
      //   211: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   214: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
      //   217: ifne -> 222
      //   220: iconst_1
      //   221: ireturn
      //   222: aload #6
      //   224: invokevirtual getSenderPerson : ()Landroid/app/Person;
      //   227: astore_3
      //   228: aconst_null
      //   229: astore #8
      //   231: aload_3
      //   232: ifnonnull -> 240
      //   235: aconst_null
      //   236: astore_3
      //   237: goto -> 249
      //   240: aload #6
      //   242: invokevirtual getSenderPerson : ()Landroid/app/Person;
      //   245: invokevirtual getKey : ()Ljava/lang/String;
      //   248: astore_3
      //   249: aload #7
      //   251: invokevirtual getSenderPerson : ()Landroid/app/Person;
      //   254: ifnonnull -> 260
      //   257: goto -> 270
      //   260: aload #7
      //   262: invokevirtual getSenderPerson : ()Landroid/app/Person;
      //   265: invokevirtual getKey : ()Ljava/lang/String;
      //   268: astore #8
      //   270: aload_3
      //   271: aload #8
      //   273: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
      //   276: ifne -> 281
      //   279: iconst_1
      //   280: ireturn
      //   281: iinc #5, 1
      //   284: goto -> 75
      //   287: iconst_0
      //   288: ireturn
      //   289: iconst_1
      //   290: ireturn
    }
    
    public CharSequence getConversationTitle() {
      return this.mConversationTitle;
    }
    
    public int getConversationType() {
      return this.mConversationType;
    }
    
    public CharSequence getHeadsUpStatusBarText() {
      CharSequence charSequence;
      if (!TextUtils.isEmpty(this.mBigContentTitle)) {
        charSequence = this.mBigContentTitle;
      } else {
        charSequence = this.mConversationTitle;
      } 
      return (this.mConversationType == 0 && !TextUtils.isEmpty(charSequence) && !hasOnlyWhiteSpaceSenders()) ? charSequence : null;
    }
    
    public List<Message> getHistoricMessages() {
      return this.mHistoricMessages;
    }
    
    public List<Message> getMessages() {
      return this.mMessages;
    }
    
    public Icon getShortcutIcon() {
      return this.mShortcutIcon;
    }
    
    public int getUnreadMessageCount() {
      return this.mUnreadMessageCount;
    }
    
    public Person getUser() {
      return this.mUser;
    }
    
    public CharSequence getUserDisplayName() {
      return this.mUser.getName();
    }
    
    public boolean isGroupConversation() {
      if (this.mBuilder != null && (this.mBuilder.mContext.getApplicationInfo()).targetSdkVersion < 28) {
        boolean bool;
        if (this.mConversationTitle != null) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      } 
      return this.mIsGroupConversation;
    }
    
    public RemoteViews makeBigContentView() {
      return makeMessagingView(false, true);
    }
    
    public RemoteViews makeContentView(boolean param1Boolean) {
      Notification.Builder.access$2902(this.mBuilder, this.mBuilder.mActions);
      Notification.Builder.access$3002(this.mBuilder, new ArrayList());
      RemoteViews remoteViews = makeMessagingView(true, false);
      Notification.Builder.access$3002(this.mBuilder, this.mBuilder.mOriginalActions);
      Notification.Builder.access$2902(this.mBuilder, null);
      return remoteViews;
    }
    
    public RemoteViews makeHeadsUpContentView(boolean param1Boolean) {
      RemoteViews remoteViews = makeMessagingView(true, true);
      if (this.mConversationType == 0)
        remoteViews.setInt(16909239, "setMaxDisplayedLines", 1); 
      return remoteViews;
    }
    
    protected void restoreFromExtras(Bundle param1Bundle) {
      super.restoreFromExtras(param1Bundle);
      Person person = (Person)param1Bundle.getParcelable("android.messagingUser");
      this.mUser = person;
      if (person == null) {
        CharSequence charSequence = param1Bundle.getCharSequence("android.selfDisplayName");
        this.mUser = (new Person.Builder()).setName(charSequence).build();
      } 
      this.mConversationTitle = param1Bundle.getCharSequence("android.conversationTitle");
      this.mMessages = Message.getMessagesFromBundleArray(param1Bundle.getParcelableArray("android.messages"));
      this.mHistoricMessages = Message.getMessagesFromBundleArray(param1Bundle.getParcelableArray("android.messages.historic"));
      this.mIsGroupConversation = param1Bundle.getBoolean("android.isGroupConversation");
      this.mUnreadMessageCount = param1Bundle.getInt("android.conversationUnreadMessageCount");
      this.mShortcutIcon = (Icon)param1Bundle.getParcelable("android.conversationIcon");
    }
    
    public MessagingStyle setConversationTitle(CharSequence param1CharSequence) {
      this.mConversationTitle = param1CharSequence;
      return this;
    }
    
    public MessagingStyle setConversationType(int param1Int) {
      this.mConversationType = param1Int;
      return this;
    }
    
    public MessagingStyle setGroupConversation(boolean param1Boolean) {
      this.mIsGroupConversation = param1Boolean;
      return this;
    }
    
    public MessagingStyle setShortcutIcon(Icon param1Icon) {
      this.mShortcutIcon = param1Icon;
      return this;
    }
    
    public MessagingStyle setUnreadMessageCount(int param1Int) {
      this.mUnreadMessageCount = param1Int;
      return this;
    }
    
    public void validate(Context param1Context) {
      super.validate(param1Context);
      if ((param1Context.getApplicationInfo()).targetSdkVersion >= 28) {
        Person person = this.mUser;
        if (person == null || person.getName() == null)
          throw new RuntimeException("User must be valid and have a name."); 
      } 
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface ConversationType {}
    
    public static final class Message {
      static final String KEY_DATA_MIME_TYPE = "type";
      
      static final String KEY_DATA_URI = "uri";
      
      static final String KEY_EXTRAS_BUNDLE = "extras";
      
      static final String KEY_REMOTE_INPUT_HISTORY = "remote_input_history";
      
      static final String KEY_SENDER = "sender";
      
      static final String KEY_SENDER_PERSON = "sender_person";
      
      public static final String KEY_TEXT = "text";
      
      static final String KEY_TIMESTAMP = "time";
      
      private String mDataMimeType;
      
      private Uri mDataUri;
      
      private Bundle mExtras = new Bundle();
      
      private final boolean mRemoteInputHistory;
      
      private final Person mSender;
      
      private final CharSequence mText;
      
      private final long mTimestamp;
      
      public Message(CharSequence param2CharSequence, long param2Long, Person param2Person) {
        this(param2CharSequence, param2Long, param2Person, false);
      }
      
      public Message(CharSequence param2CharSequence, long param2Long, Person param2Person, boolean param2Boolean) {
        this.mText = Notification.safeCharSequence(param2CharSequence);
        this.mTimestamp = param2Long;
        this.mSender = param2Person;
        this.mRemoteInputHistory = param2Boolean;
      }
      
      public Message(CharSequence param2CharSequence1, long param2Long, CharSequence param2CharSequence2) {
        this(param2CharSequence1, param2Long, person);
      }
      
      static Bundle[] getBundleArrayForMessages(List<Message> param2List) {
        Bundle[] arrayOfBundle = new Bundle[param2List.size()];
        int i = param2List.size();
        for (byte b = 0; b < i; b++)
          arrayOfBundle[b] = ((Message)param2List.get(b)).toBundle(); 
        return arrayOfBundle;
      }
      
      public static Message getMessageFromBundle(Bundle param2Bundle) {
        try {
          if (!param2Bundle.containsKey("text") || !param2Bundle.containsKey("time"))
            return null; 
          Person person = (Person)param2Bundle.getParcelable("sender_person");
          if (person == null) {
            CharSequence charSequence = param2Bundle.getCharSequence("sender");
            if (charSequence != null) {
              Person.Builder builder = new Person.Builder();
              this();
              person = builder.setName(charSequence).build();
            } 
          } 
          Message message = new Message();
          this(param2Bundle.getCharSequence("text"), param2Bundle.getLong("time"), person, param2Bundle.getBoolean("remote_input_history", false));
          if (param2Bundle.containsKey("type") && param2Bundle.containsKey("uri"))
            message.setData(param2Bundle.getString("type"), (Uri)param2Bundle.getParcelable("uri")); 
          if (param2Bundle.containsKey("extras"))
            message.getExtras().putAll(param2Bundle.getBundle("extras")); 
          return message;
        } catch (ClassCastException classCastException) {
          return null;
        } 
      }
      
      public static List<Message> getMessagesFromBundleArray(Parcelable[] param2ArrayOfParcelable) {
        if (param2ArrayOfParcelable == null)
          return new ArrayList<>(); 
        ArrayList<Message> arrayList = new ArrayList(param2ArrayOfParcelable.length);
        for (byte b = 0; b < param2ArrayOfParcelable.length; b++) {
          if (param2ArrayOfParcelable[b] instanceof Bundle) {
            Message message = getMessageFromBundle((Bundle)param2ArrayOfParcelable[b]);
            if (message != null)
              arrayList.add(message); 
          } 
        } 
        return arrayList;
      }
      
      public String getDataMimeType() {
        return this.mDataMimeType;
      }
      
      public Uri getDataUri() {
        return this.mDataUri;
      }
      
      public Bundle getExtras() {
        return this.mExtras;
      }
      
      public CharSequence getSender() {
        CharSequence charSequence;
        Person person = this.mSender;
        if (person == null) {
          person = null;
        } else {
          charSequence = person.getName();
        } 
        return charSequence;
      }
      
      public Person getSenderPerson() {
        return this.mSender;
      }
      
      public CharSequence getText() {
        return this.mText;
      }
      
      public long getTimestamp() {
        return this.mTimestamp;
      }
      
      public boolean isRemoteInputHistory() {
        return this.mRemoteInputHistory;
      }
      
      public Message setData(String param2String, Uri param2Uri) {
        this.mDataMimeType = param2String;
        this.mDataUri = param2Uri;
        return this;
      }
      
      public Bundle toBundle() {
        Bundle bundle1 = new Bundle();
        CharSequence charSequence = this.mText;
        if (charSequence != null)
          bundle1.putCharSequence("text", charSequence); 
        bundle1.putLong("time", this.mTimestamp);
        Person person = this.mSender;
        if (person != null) {
          bundle1.putCharSequence("sender", Notification.safeCharSequence(person.getName()));
          bundle1.putParcelable("sender_person", this.mSender);
        } 
        String str = this.mDataMimeType;
        if (str != null)
          bundle1.putString("type", str); 
        Uri uri = this.mDataUri;
        if (uri != null)
          bundle1.putParcelable("uri", (Parcelable)uri); 
        Bundle bundle2 = this.mExtras;
        if (bundle2 != null)
          bundle1.putBundle("extras", bundle2); 
        boolean bool = this.mRemoteInputHistory;
        if (bool)
          bundle1.putBoolean("remote_input_history", bool); 
        return bundle1;
      }
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ConversationType {}
  
  public static final class Message {
    static final String KEY_DATA_MIME_TYPE = "type";
    
    static final String KEY_DATA_URI = "uri";
    
    static final String KEY_EXTRAS_BUNDLE = "extras";
    
    static final String KEY_REMOTE_INPUT_HISTORY = "remote_input_history";
    
    static final String KEY_SENDER = "sender";
    
    static final String KEY_SENDER_PERSON = "sender_person";
    
    public static final String KEY_TEXT = "text";
    
    static final String KEY_TIMESTAMP = "time";
    
    private String mDataMimeType;
    
    private Uri mDataUri;
    
    private Bundle mExtras = new Bundle();
    
    private final boolean mRemoteInputHistory;
    
    private final Person mSender;
    
    private final CharSequence mText;
    
    private final long mTimestamp;
    
    public Message(CharSequence param1CharSequence, long param1Long, Person param1Person) {
      this(param1CharSequence, param1Long, param1Person, false);
    }
    
    public Message(CharSequence param1CharSequence, long param1Long, Person param1Person, boolean param1Boolean) {
      this.mText = Notification.safeCharSequence(param1CharSequence);
      this.mTimestamp = param1Long;
      this.mSender = param1Person;
      this.mRemoteInputHistory = param1Boolean;
    }
    
    public Message(CharSequence param1CharSequence1, long param1Long, CharSequence param1CharSequence2) {
      this(param1CharSequence1, param1Long, person);
    }
    
    static Bundle[] getBundleArrayForMessages(List<Message> param1List) {
      Bundle[] arrayOfBundle = new Bundle[param1List.size()];
      int i = param1List.size();
      for (byte b = 0; b < i; b++)
        arrayOfBundle[b] = ((Message)param1List.get(b)).toBundle(); 
      return arrayOfBundle;
    }
    
    public static Message getMessageFromBundle(Bundle param1Bundle) {
      try {
        if (!param1Bundle.containsKey("text") || !param1Bundle.containsKey("time"))
          return null; 
        Person person = (Person)param1Bundle.getParcelable("sender_person");
        if (person == null) {
          CharSequence charSequence = param1Bundle.getCharSequence("sender");
          if (charSequence != null) {
            Person.Builder builder = new Person.Builder();
            this();
            person = builder.setName(charSequence).build();
          } 
        } 
        Message message = new Message();
        this(param1Bundle.getCharSequence("text"), param1Bundle.getLong("time"), person, param1Bundle.getBoolean("remote_input_history", false));
        if (param1Bundle.containsKey("type") && param1Bundle.containsKey("uri"))
          message.setData(param1Bundle.getString("type"), (Uri)param1Bundle.getParcelable("uri")); 
        if (param1Bundle.containsKey("extras"))
          message.getExtras().putAll(param1Bundle.getBundle("extras")); 
        return message;
      } catch (ClassCastException classCastException) {
        return null;
      } 
    }
    
    public static List<Message> getMessagesFromBundleArray(Parcelable[] param1ArrayOfParcelable) {
      if (param1ArrayOfParcelable == null)
        return new ArrayList<>(); 
      ArrayList<Message> arrayList = new ArrayList(param1ArrayOfParcelable.length);
      for (byte b = 0; b < param1ArrayOfParcelable.length; b++) {
        if (param1ArrayOfParcelable[b] instanceof Bundle) {
          Message message = getMessageFromBundle((Bundle)param1ArrayOfParcelable[b]);
          if (message != null)
            arrayList.add(message); 
        } 
      } 
      return arrayList;
    }
    
    public String getDataMimeType() {
      return this.mDataMimeType;
    }
    
    public Uri getDataUri() {
      return this.mDataUri;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public CharSequence getSender() {
      CharSequence charSequence;
      Person person = this.mSender;
      if (person == null) {
        person = null;
      } else {
        charSequence = person.getName();
      } 
      return charSequence;
    }
    
    public Person getSenderPerson() {
      return this.mSender;
    }
    
    public CharSequence getText() {
      return this.mText;
    }
    
    public long getTimestamp() {
      return this.mTimestamp;
    }
    
    public boolean isRemoteInputHistory() {
      return this.mRemoteInputHistory;
    }
    
    public Message setData(String param1String, Uri param1Uri) {
      this.mDataMimeType = param1String;
      this.mDataUri = param1Uri;
      return this;
    }
    
    public Bundle toBundle() {
      Bundle bundle1 = new Bundle();
      CharSequence charSequence = this.mText;
      if (charSequence != null)
        bundle1.putCharSequence("text", charSequence); 
      bundle1.putLong("time", this.mTimestamp);
      Person person = this.mSender;
      if (person != null) {
        bundle1.putCharSequence("sender", Notification.safeCharSequence(person.getName()));
        bundle1.putParcelable("sender_person", this.mSender);
      } 
      String str = this.mDataMimeType;
      if (str != null)
        bundle1.putString("type", str); 
      Uri uri = this.mDataUri;
      if (uri != null)
        bundle1.putParcelable("uri", (Parcelable)uri); 
      Bundle bundle2 = this.mExtras;
      if (bundle2 != null)
        bundle1.putBundle("extras", bundle2); 
      boolean bool = this.mRemoteInputHistory;
      if (bool)
        bundle1.putBoolean("remote_input_history", bool); 
      return bundle1;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface NotificationFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Priority {}
  
  private static class StandardTemplateParams {
    boolean allowColorization = true;
    
    boolean forceDefaultColor = false;
    
    boolean hasProgress = true;
    
    CharSequence headerTextSecondary;
    
    boolean hideLargeIcon;
    
    boolean hideReplyIcon;
    
    int maxRemoteInputHistory = 3;
    
    CharSequence summaryText;
    
    CharSequence text;
    
    CharSequence title;
    
    private StandardTemplateParams() {}
    
    final StandardTemplateParams disallowColorization() {
      this.allowColorization = false;
      return this;
    }
    
    final StandardTemplateParams fillTextsFrom(Notification.Builder param1Builder) {
      Bundle bundle = param1Builder.mN.extras;
      this.title = param1Builder.processLegacyText(bundle.getCharSequence("android.title"));
      this.text = param1Builder.processLegacyText(bundle.getCharSequence("android.text"));
      this.summaryText = bundle.getCharSequence("android.subText");
      return this;
    }
    
    final StandardTemplateParams forceDefaultColor() {
      this.forceDefaultColor = true;
      return this;
    }
    
    final StandardTemplateParams hasProgress(boolean param1Boolean) {
      this.hasProgress = param1Boolean;
      return this;
    }
    
    final StandardTemplateParams headerTextSecondary(CharSequence param1CharSequence) {
      this.headerTextSecondary = param1CharSequence;
      return this;
    }
    
    final StandardTemplateParams hideLargeIcon(boolean param1Boolean) {
      this.hideLargeIcon = param1Boolean;
      return this;
    }
    
    final StandardTemplateParams hideReplyIcon(boolean param1Boolean) {
      this.hideReplyIcon = param1Boolean;
      return this;
    }
    
    final StandardTemplateParams reset() {
      this.hasProgress = true;
      this.title = null;
      this.text = null;
      this.summaryText = null;
      this.headerTextSecondary = null;
      this.maxRemoteInputHistory = 3;
      this.allowColorization = true;
      this.forceDefaultColor = false;
      return this;
    }
    
    public StandardTemplateParams setMaxRemoteInputHistory(int param1Int) {
      this.maxRemoteInputHistory = param1Int;
      return this;
    }
    
    final StandardTemplateParams summaryText(CharSequence param1CharSequence) {
      this.summaryText = param1CharSequence;
      return this;
    }
    
    final StandardTemplateParams text(CharSequence param1CharSequence) {
      this.text = param1CharSequence;
      return this;
    }
    
    final StandardTemplateParams title(CharSequence param1CharSequence) {
      this.title = param1CharSequence;
      return this;
    }
  }
  
  public static abstract class Style {
    static final int MAX_REMOTE_INPUT_HISTORY_LINES = 3;
    
    private CharSequence mBigContentTitle;
    
    protected Notification.Builder mBuilder;
    
    protected CharSequence mSummaryText = null;
    
    protected boolean mSummaryTextSet = false;
    
    public void addExtras(Bundle param1Bundle) {
      if (this.mSummaryTextSet)
        param1Bundle.putCharSequence("android.summaryText", this.mSummaryText); 
      CharSequence charSequence = this.mBigContentTitle;
      if (charSequence != null)
        param1Bundle.putCharSequence("android.title.big", charSequence); 
      param1Bundle.putString("android.template", getClass().getName());
    }
    
    public abstract boolean areNotificationsVisiblyDifferent(Style param1Style);
    
    public Notification build() {
      checkBuilder();
      return this.mBuilder.build();
    }
    
    public Notification buildStyled(Notification param1Notification) {
      addExtras(param1Notification.extras);
      return param1Notification;
    }
    
    protected void checkBuilder() {
      if (this.mBuilder != null)
        return; 
      throw new IllegalArgumentException("Style requires a valid Builder object");
    }
    
    public boolean displayCustomViewInline() {
      return false;
    }
    
    public CharSequence getHeadsUpStatusBarText() {
      return null;
    }
    
    protected RemoteViews getStandardView(int param1Int) {
      return getStandardView(param1Int, this.mBuilder.mParams.reset().fillTextsFrom(this.mBuilder), null);
    }
    
    protected RemoteViews getStandardView(int param1Int, Notification.StandardTemplateParams param1StandardTemplateParams, Notification.TemplateBindResult param1TemplateBindResult) {
      checkBuilder();
      CharSequence charSequence2 = this.mBigContentTitle;
      if (charSequence2 != null)
        param1StandardTemplateParams.title = charSequence2; 
      RemoteViews remoteViews = this.mBuilder.applyStandardTemplateWithActions(param1Int, param1StandardTemplateParams, param1TemplateBindResult);
      CharSequence charSequence1 = this.mBigContentTitle;
      if (charSequence1 != null && charSequence1.equals("")) {
        remoteViews.setViewVisibility(16909124, 8);
      } else {
        remoteViews.setViewVisibility(16909124, 0);
      } 
      return remoteViews;
    }
    
    protected boolean hasProgress() {
      return true;
    }
    
    public boolean hasSummaryInHeader() {
      return true;
    }
    
    protected void internalSetBigContentTitle(CharSequence param1CharSequence) {
      this.mBigContentTitle = param1CharSequence;
    }
    
    protected void internalSetSummaryText(CharSequence param1CharSequence) {
      this.mSummaryText = param1CharSequence;
      this.mSummaryTextSet = true;
    }
    
    public RemoteViews makeBigContentView() {
      return null;
    }
    
    public RemoteViews makeContentView(boolean param1Boolean) {
      return null;
    }
    
    public RemoteViews makeHeadsUpContentView(boolean param1Boolean) {
      return null;
    }
    
    public void purgeResources() {}
    
    public void reduceImageSizes(Context param1Context) {}
    
    protected void restoreFromExtras(Bundle param1Bundle) {
      if (param1Bundle.containsKey("android.summaryText")) {
        this.mSummaryText = param1Bundle.getCharSequence("android.summaryText");
        this.mSummaryTextSet = true;
      } 
      if (param1Bundle.containsKey("android.title.big"))
        this.mBigContentTitle = param1Bundle.getCharSequence("android.title.big"); 
    }
    
    public void setBuilder(Notification.Builder param1Builder) {
      if (this.mBuilder != param1Builder) {
        this.mBuilder = param1Builder;
        if (param1Builder != null)
          param1Builder.setStyle(this); 
      } 
    }
    
    public void validate(Context param1Context) {}
  }
  
  private static class TemplateBindResult {
    int mIconMarginEnd;
    
    boolean mRightIconContainerVisible;
    
    private TemplateBindResult() {}
    
    public int getIconMarginEnd() {
      return this.mIconMarginEnd;
    }
    
    public boolean isRightIconContainerVisible() {
      return this.mRightIconContainerVisible;
    }
    
    public void setIconMarginEnd(int param1Int) {
      this.mIconMarginEnd = param1Int;
    }
    
    public void setRightIconContainerVisible(boolean param1Boolean) {
      this.mRightIconContainerVisible = param1Boolean;
    }
  }
  
  @SystemApi
  public static final class TvExtender implements Extender {
    private static final String EXTRA_CHANNEL_ID = "channel_id";
    
    private static final String EXTRA_CONTENT_INTENT = "content_intent";
    
    private static final String EXTRA_DELETE_INTENT = "delete_intent";
    
    private static final String EXTRA_FLAGS = "flags";
    
    private static final String EXTRA_SUPPRESS_SHOW_OVER_APPS = "suppressShowOverApps";
    
    private static final String EXTRA_TV_EXTENDER = "android.tv.EXTENSIONS";
    
    private static final int FLAG_AVAILABLE_ON_TV = 1;
    
    private static final String TAG = "TvExtender";
    
    private String mChannelId;
    
    private PendingIntent mContentIntent;
    
    private PendingIntent mDeleteIntent;
    
    private int mFlags;
    
    private boolean mSuppressShowOverApps;
    
    public TvExtender() {
      this.mFlags = 1;
    }
    
    public TvExtender(Notification param1Notification) {
      Bundle bundle;
      if (param1Notification.extras == null) {
        param1Notification = null;
      } else {
        bundle = param1Notification.extras.getBundle("android.tv.EXTENSIONS");
      } 
      if (bundle != null) {
        this.mFlags = bundle.getInt("flags");
        this.mChannelId = bundle.getString("channel_id");
        this.mSuppressShowOverApps = bundle.getBoolean("suppressShowOverApps");
        this.mContentIntent = (PendingIntent)bundle.getParcelable("content_intent");
        this.mDeleteIntent = (PendingIntent)bundle.getParcelable("delete_intent");
      } 
    }
    
    public Notification.Builder extend(Notification.Builder param1Builder) {
      Bundle bundle = new Bundle();
      bundle.putInt("flags", this.mFlags);
      bundle.putString("channel_id", this.mChannelId);
      bundle.putBoolean("suppressShowOverApps", this.mSuppressShowOverApps);
      PendingIntent pendingIntent = this.mContentIntent;
      if (pendingIntent != null)
        bundle.putParcelable("content_intent", pendingIntent); 
      pendingIntent = this.mDeleteIntent;
      if (pendingIntent != null)
        bundle.putParcelable("delete_intent", pendingIntent); 
      param1Builder.getExtras().putBundle("android.tv.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    @Deprecated
    public String getChannel() {
      return this.mChannelId;
    }
    
    public String getChannelId() {
      return this.mChannelId;
    }
    
    public PendingIntent getContentIntent() {
      return this.mContentIntent;
    }
    
    public PendingIntent getDeleteIntent() {
      return this.mDeleteIntent;
    }
    
    public boolean getSuppressShowOverApps() {
      return this.mSuppressShowOverApps;
    }
    
    public boolean isAvailableOnTv() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public TvExtender setChannel(String param1String) {
      this.mChannelId = param1String;
      return this;
    }
    
    public TvExtender setChannelId(String param1String) {
      this.mChannelId = param1String;
      return this;
    }
    
    public TvExtender setContentIntent(PendingIntent param1PendingIntent) {
      this.mContentIntent = param1PendingIntent;
      return this;
    }
    
    public TvExtender setDeleteIntent(PendingIntent param1PendingIntent) {
      this.mDeleteIntent = param1PendingIntent;
      return this;
    }
    
    public TvExtender setSuppressShowOverApps(boolean param1Boolean) {
      this.mSuppressShowOverApps = param1Boolean;
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Visibility {}
  
  public static final class WearableExtender implements Extender {
    private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
    
    private static final int DEFAULT_FLAGS = 1;
    
    private static final int DEFAULT_GRAVITY = 80;
    
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    
    private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
    
    private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
    
    private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
    
    private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
    
    private static final int FLAG_HINT_HIDE_ICON = 2;
    
    private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
    
    private static final int FLAG_START_SCROLL_BOTTOM = 8;
    
    private static final String KEY_ACTIONS = "actions";
    
    private static final String KEY_BACKGROUND = "background";
    
    private static final String KEY_BRIDGE_TAG = "bridgeTag";
    
    private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
    
    private static final String KEY_CONTENT_ICON = "contentIcon";
    
    private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
    
    private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
    
    private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
    
    private static final String KEY_DISMISSAL_ID = "dismissalId";
    
    private static final String KEY_DISPLAY_INTENT = "displayIntent";
    
    private static final String KEY_FLAGS = "flags";
    
    private static final String KEY_GRAVITY = "gravity";
    
    private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
    
    private static final String KEY_PAGES = "pages";
    
    @Deprecated
    public static final int SCREEN_TIMEOUT_LONG = -1;
    
    @Deprecated
    public static final int SCREEN_TIMEOUT_SHORT = 0;
    
    @Deprecated
    public static final int SIZE_DEFAULT = 0;
    
    @Deprecated
    public static final int SIZE_FULL_SCREEN = 5;
    
    @Deprecated
    public static final int SIZE_LARGE = 4;
    
    @Deprecated
    public static final int SIZE_MEDIUM = 3;
    
    @Deprecated
    public static final int SIZE_SMALL = 2;
    
    @Deprecated
    public static final int SIZE_XSMALL = 1;
    
    public static final int UNSET_ACTION_INDEX = -1;
    
    private ArrayList<Notification.Action> mActions = new ArrayList<>();
    
    private Bitmap mBackground;
    
    private String mBridgeTag;
    
    private int mContentActionIndex = -1;
    
    private int mContentIcon;
    
    private int mContentIconGravity = 8388613;
    
    private int mCustomContentHeight;
    
    private int mCustomSizePreset = 0;
    
    private String mDismissalId;
    
    private PendingIntent mDisplayIntent;
    
    private int mFlags = 1;
    
    private int mGravity = 80;
    
    private int mHintScreenTimeout;
    
    private ArrayList<Notification> mPages = new ArrayList<>();
    
    public WearableExtender() {}
    
    public WearableExtender(Notification param1Notification) {
      Bundle bundle = param1Notification.extras.getBundle("android.wearable.EXTENSIONS");
      if (bundle != null) {
        ArrayList<? extends Notification.Action> arrayList = bundle.getParcelableArrayList("actions");
        if (arrayList != null)
          this.mActions.addAll(arrayList); 
        this.mFlags = bundle.getInt("flags", 1);
        this.mDisplayIntent = (PendingIntent)bundle.getParcelable("displayIntent");
        Notification[] arrayOfNotification = (Notification[])Notification.getParcelableArrayFromBundle(bundle, "pages", (Class)Notification.class);
        if (arrayOfNotification != null)
          Collections.addAll(this.mPages, arrayOfNotification); 
        this.mBackground = (Bitmap)bundle.getParcelable("background");
        this.mContentIcon = bundle.getInt("contentIcon");
        this.mContentIconGravity = bundle.getInt("contentIconGravity", 8388613);
        this.mContentActionIndex = bundle.getInt("contentActionIndex", -1);
        this.mCustomSizePreset = bundle.getInt("customSizePreset", 0);
        this.mCustomContentHeight = bundle.getInt("customContentHeight");
        this.mGravity = bundle.getInt("gravity", 80);
        this.mHintScreenTimeout = bundle.getInt("hintScreenTimeout");
        this.mDismissalId = bundle.getString("dismissalId");
        this.mBridgeTag = bundle.getString("bridgeTag");
      } 
    }
    
    private void setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= param1Int;
      } else {
        this.mFlags &= param1Int;
      } 
    }
    
    public WearableExtender addAction(Notification.Action param1Action) {
      this.mActions.add(param1Action);
      return this;
    }
    
    public WearableExtender addActions(List<Notification.Action> param1List) {
      this.mActions.addAll(param1List);
      return this;
    }
    
    @Deprecated
    public WearableExtender addPage(Notification param1Notification) {
      this.mPages.add(param1Notification);
      return this;
    }
    
    @Deprecated
    public WearableExtender addPages(List<Notification> param1List) {
      this.mPages.addAll(param1List);
      return this;
    }
    
    public WearableExtender clearActions() {
      this.mActions.clear();
      return this;
    }
    
    @Deprecated
    public WearableExtender clearPages() {
      this.mPages.clear();
      return this;
    }
    
    public WearableExtender clone() {
      WearableExtender wearableExtender = new WearableExtender();
      wearableExtender.mActions = new ArrayList<>(this.mActions);
      wearableExtender.mFlags = this.mFlags;
      wearableExtender.mDisplayIntent = this.mDisplayIntent;
      wearableExtender.mPages = new ArrayList<>(this.mPages);
      wearableExtender.mBackground = this.mBackground;
      wearableExtender.mContentIcon = this.mContentIcon;
      wearableExtender.mContentIconGravity = this.mContentIconGravity;
      wearableExtender.mContentActionIndex = this.mContentActionIndex;
      wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
      wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
      wearableExtender.mGravity = this.mGravity;
      wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
      wearableExtender.mDismissalId = this.mDismissalId;
      wearableExtender.mBridgeTag = this.mBridgeTag;
      return wearableExtender;
    }
    
    public Notification.Builder extend(Notification.Builder param1Builder) {
      Bundle bundle = new Bundle();
      if (!this.mActions.isEmpty())
        bundle.putParcelableArrayList("actions", this.mActions); 
      int i = this.mFlags;
      if (i != 1)
        bundle.putInt("flags", i); 
      PendingIntent pendingIntent = this.mDisplayIntent;
      if (pendingIntent != null)
        bundle.putParcelable("displayIntent", pendingIntent); 
      if (!this.mPages.isEmpty()) {
        ArrayList<Notification> arrayList = this.mPages;
        bundle.putParcelableArray("pages", arrayList.<Parcelable>toArray((Parcelable[])new Notification[arrayList.size()]));
      } 
      Bitmap bitmap = this.mBackground;
      if (bitmap != null)
        bundle.putParcelable("background", (Parcelable)bitmap); 
      i = this.mContentIcon;
      if (i != 0)
        bundle.putInt("contentIcon", i); 
      i = this.mContentIconGravity;
      if (i != 8388613)
        bundle.putInt("contentIconGravity", i); 
      i = this.mContentActionIndex;
      if (i != -1)
        bundle.putInt("contentActionIndex", i); 
      i = this.mCustomSizePreset;
      if (i != 0)
        bundle.putInt("customSizePreset", i); 
      i = this.mCustomContentHeight;
      if (i != 0)
        bundle.putInt("customContentHeight", i); 
      i = this.mGravity;
      if (i != 80)
        bundle.putInt("gravity", i); 
      i = this.mHintScreenTimeout;
      if (i != 0)
        bundle.putInt("hintScreenTimeout", i); 
      String str = this.mDismissalId;
      if (str != null)
        bundle.putString("dismissalId", str); 
      str = this.mBridgeTag;
      if (str != null)
        bundle.putString("bridgeTag", str); 
      param1Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
      return param1Builder;
    }
    
    public List<Notification.Action> getActions() {
      return this.mActions;
    }
    
    @Deprecated
    public Bitmap getBackground() {
      return this.mBackground;
    }
    
    public String getBridgeTag() {
      return this.mBridgeTag;
    }
    
    public int getContentAction() {
      return this.mContentActionIndex;
    }
    
    @Deprecated
    public int getContentIcon() {
      return this.mContentIcon;
    }
    
    @Deprecated
    public int getContentIconGravity() {
      return this.mContentIconGravity;
    }
    
    public boolean getContentIntentAvailableOffline() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    @Deprecated
    public int getCustomContentHeight() {
      return this.mCustomContentHeight;
    }
    
    @Deprecated
    public int getCustomSizePreset() {
      return this.mCustomSizePreset;
    }
    
    public String getDismissalId() {
      return this.mDismissalId;
    }
    
    @Deprecated
    public PendingIntent getDisplayIntent() {
      return this.mDisplayIntent;
    }
    
    @Deprecated
    public int getGravity() {
      return this.mGravity;
    }
    
    @Deprecated
    public boolean getHintAmbientBigPicture() {
      boolean bool;
      if ((this.mFlags & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Deprecated
    public boolean getHintAvoidBackgroundClipping() {
      boolean bool;
      if ((this.mFlags & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean getHintContentIntentLaunchesActivity() {
      boolean bool;
      if ((this.mFlags & 0x40) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Deprecated
    public boolean getHintHideIcon() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Deprecated
    public int getHintScreenTimeout() {
      return this.mHintScreenTimeout;
    }
    
    @Deprecated
    public boolean getHintShowBackgroundOnly() {
      boolean bool;
      if ((this.mFlags & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Deprecated
    public List<Notification> getPages() {
      return this.mPages;
    }
    
    public boolean getStartScrollBottom() {
      boolean bool;
      if ((this.mFlags & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Deprecated
    public WearableExtender setBackground(Bitmap param1Bitmap) {
      this.mBackground = param1Bitmap;
      return this;
    }
    
    public WearableExtender setBridgeTag(String param1String) {
      this.mBridgeTag = param1String;
      return this;
    }
    
    public WearableExtender setContentAction(int param1Int) {
      this.mContentActionIndex = param1Int;
      return this;
    }
    
    @Deprecated
    public WearableExtender setContentIcon(int param1Int) {
      this.mContentIcon = param1Int;
      return this;
    }
    
    @Deprecated
    public WearableExtender setContentIconGravity(int param1Int) {
      this.mContentIconGravity = param1Int;
      return this;
    }
    
    public WearableExtender setContentIntentAvailableOffline(boolean param1Boolean) {
      setFlag(1, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setCustomContentHeight(int param1Int) {
      this.mCustomContentHeight = param1Int;
      return this;
    }
    
    @Deprecated
    public WearableExtender setCustomSizePreset(int param1Int) {
      this.mCustomSizePreset = param1Int;
      return this;
    }
    
    public WearableExtender setDismissalId(String param1String) {
      this.mDismissalId = param1String;
      return this;
    }
    
    @Deprecated
    public WearableExtender setDisplayIntent(PendingIntent param1PendingIntent) {
      this.mDisplayIntent = param1PendingIntent;
      return this;
    }
    
    @Deprecated
    public WearableExtender setGravity(int param1Int) {
      this.mGravity = param1Int;
      return this;
    }
    
    @Deprecated
    public WearableExtender setHintAmbientBigPicture(boolean param1Boolean) {
      setFlag(32, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setHintAvoidBackgroundClipping(boolean param1Boolean) {
      setFlag(16, param1Boolean);
      return this;
    }
    
    public WearableExtender setHintContentIntentLaunchesActivity(boolean param1Boolean) {
      setFlag(64, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setHintHideIcon(boolean param1Boolean) {
      setFlag(2, param1Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setHintScreenTimeout(int param1Int) {
      this.mHintScreenTimeout = param1Int;
      return this;
    }
    
    @Deprecated
    public WearableExtender setHintShowBackgroundOnly(boolean param1Boolean) {
      setFlag(4, param1Boolean);
      return this;
    }
    
    public WearableExtender setStartScrollBottom(boolean param1Boolean) {
      setFlag(8, param1Boolean);
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
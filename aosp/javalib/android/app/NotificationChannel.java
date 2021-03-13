package android.app;

import android.annotation.SystemApi;
import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.text.TextUtils;
import android.util.proto.ProtoOutputStream;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public final class NotificationChannel implements Parcelable {
  public static final int ALLOW_BUBBLE_OFF = 0;
  
  public static final int ALLOW_BUBBLE_ON = 1;
  
  private static final String ATT_ALLOW_BUBBLE = "allow_bubbles";
  
  private static final String ATT_BLOCKABLE_SYSTEM = "blockable_system";
  
  private static final String ATT_CONTENT_TYPE = "content_type";
  
  private static final String ATT_CONVERSATION_ID = "conv_id";
  
  private static final String ATT_DELETED = "deleted";
  
  private static final String ATT_DEMOTE = "dem";
  
  private static final String ATT_DESC = "desc";
  
  private static final String ATT_FG_SERVICE_SHOWN = "fgservice";
  
  private static final String ATT_FLAGS = "flags";
  
  private static final String ATT_GROUP = "group";
  
  private static final String ATT_ID = "id";
  
  private static final String ATT_IMPORTANCE = "importance";
  
  private static final String ATT_IMP_CONVERSATION = "imp_conv";
  
  private static final String ATT_LIGHTS = "lights";
  
  private static final String ATT_LIGHT_COLOR = "light_color";
  
  private static final String ATT_NAME = "name";
  
  private static final String ATT_ORIG_IMP = "orig_imp";
  
  private static final String ATT_PARENT_CHANNEL = "parent";
  
  private static final String ATT_PRIORITY = "priority";
  
  private static final String ATT_SHOW_BADGE = "show_badge";
  
  private static final String ATT_SOUND = "sound";
  
  private static final String ATT_USAGE = "usage";
  
  private static final String ATT_USER_LOCKED = "locked";
  
  private static final String ATT_VIBRATION = "vibration";
  
  private static final String ATT_VIBRATION_ENABLED = "vibration_enabled";
  
  private static final String ATT_VISIBILITY = "visibility";
  
  public static final String CONVERSATION_CHANNEL_ID_FORMAT = "%1$s : %2$s";
  
  public static final Parcelable.Creator<NotificationChannel> CREATOR;
  
  public static final int DEFAULT_ALLOW_BUBBLE = -1;
  
  public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
  
  private static final boolean DEFAULT_DELETED = false;
  
  private static final int DEFAULT_IMPORTANCE = -1000;
  
  private static final int DEFAULT_LIGHT_COLOR = 0;
  
  private static final boolean DEFAULT_SHOW_BADGE = true;
  
  private static final int DEFAULT_VISIBILITY = -1000;
  
  private static final String DELIMITER = ",";
  
  public static final int[] LOCKABLE_FIELDS = new int[] { 1, 2, 4, 8, 16, 32, 128, 256 };
  
  private static final int MAX_TEXT_LENGTH = 1000;
  
  public static final String PLACEHOLDER_CONVERSATION_ID = ":placeholder_id";
  
  private static final String TAG_CHANNEL = "channel";
  
  public static final int USER_LOCKED_ALLOW_BUBBLE = 256;
  
  public static final int USER_LOCKED_IMPORTANCE = 4;
  
  public static final int USER_LOCKED_LIGHTS = 8;
  
  public static final int USER_LOCKED_PRIORITY = 1;
  
  public static final int USER_LOCKED_SHOW_BADGE = 128;
  
  @SystemApi
  public static final int USER_LOCKED_SOUND = 32;
  
  public static final int USER_LOCKED_VIBRATION = 16;
  
  public static final int USER_LOCKED_VISIBILITY = 2;
  
  private int mAllowBubbles;
  
  private AudioAttributes mAudioAttributes;
  
  private boolean mBlockableSystem;
  
  private boolean mBypassDnd;
  
  private String mConversationId;
  
  private boolean mDeleted;
  
  private boolean mDemoted;
  
  private String mDesc;
  
  private boolean mFgServiceShown;
  
  private String mGroup;
  
  private String mId;
  
  private int mImportance = -1000;
  
  private boolean mImportanceLockedByOEM;
  
  private boolean mImportanceLockedDefaultApp;
  
  private boolean mImportantConvo;
  
  private int mLightColor;
  
  private boolean mLights;
  
  private int mLockscreenVisibility = -1000;
  
  private String mName;
  
  private int mOriginalImportance = -1000;
  
  private String mParentId;
  
  private boolean mShowBadge;
  
  private Uri mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
  
  private int mUserLockedFields;
  
  private long[] mVibration;
  
  private boolean mVibrationEnabled;
  
  static {
    CREATOR = new Parcelable.Creator<NotificationChannel>() {
        public NotificationChannel createFromParcel(Parcel param1Parcel) {
          return new NotificationChannel(param1Parcel);
        }
        
        public NotificationChannel[] newArray(int param1Int) {
          return new NotificationChannel[param1Int];
        }
      };
  }
  
  protected NotificationChannel(Parcel paramParcel) {
    boolean bool1 = false;
    this.mLightColor = 0;
    this.mShowBadge = true;
    this.mDeleted = false;
    this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
    this.mBlockableSystem = false;
    this.mAllowBubbles = -1;
    AudioAttributes audioAttributes = null;
    this.mParentId = null;
    this.mConversationId = null;
    this.mDemoted = false;
    this.mImportantConvo = false;
    if (paramParcel.readByte() != 0) {
      this.mId = paramParcel.readString();
    } else {
      this.mId = null;
    } 
    if (paramParcel.readByte() != 0) {
      this.mName = paramParcel.readString();
    } else {
      this.mName = null;
    } 
    if (paramParcel.readByte() != 0) {
      this.mDesc = paramParcel.readString();
    } else {
      this.mDesc = null;
    } 
    this.mImportance = paramParcel.readInt();
    if (paramParcel.readByte() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mBypassDnd = bool2;
    this.mLockscreenVisibility = paramParcel.readInt();
    if (paramParcel.readByte() != 0) {
      this.mSound = (Uri)Uri.CREATOR.createFromParcel(paramParcel);
    } else {
      this.mSound = null;
    } 
    if (paramParcel.readByte() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mLights = bool2;
    this.mVibration = paramParcel.createLongArray();
    this.mUserLockedFields = paramParcel.readInt();
    if (paramParcel.readByte() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mFgServiceShown = bool2;
    if (paramParcel.readByte() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mVibrationEnabled = bool2;
    if (paramParcel.readByte() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mShowBadge = bool2;
    boolean bool2 = bool1;
    if (paramParcel.readByte() != 0)
      bool2 = true; 
    this.mDeleted = bool2;
    if (paramParcel.readByte() != 0) {
      this.mGroup = paramParcel.readString();
    } else {
      this.mGroup = null;
    } 
    if (paramParcel.readInt() > 0)
      audioAttributes = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(paramParcel); 
    this.mAudioAttributes = audioAttributes;
    this.mLightColor = paramParcel.readInt();
    this.mBlockableSystem = paramParcel.readBoolean();
    this.mAllowBubbles = paramParcel.readInt();
    this.mImportanceLockedByOEM = paramParcel.readBoolean();
    this.mOriginalImportance = paramParcel.readInt();
    this.mParentId = paramParcel.readString();
    this.mConversationId = paramParcel.readString();
    this.mDemoted = paramParcel.readBoolean();
    this.mImportantConvo = paramParcel.readBoolean();
  }
  
  public NotificationChannel(String paramString, CharSequence paramCharSequence, int paramInt) {
    this.mLightColor = 0;
    this.mShowBadge = true;
    this.mDeleted = false;
    this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
    this.mBlockableSystem = false;
    this.mAllowBubbles = -1;
    String str = null;
    this.mParentId = null;
    this.mConversationId = null;
    this.mDemoted = false;
    this.mImportantConvo = false;
    this.mId = getTrimmedString(paramString);
    paramString = str;
    if (paramCharSequence != null)
      paramString = getTrimmedString(paramCharSequence.toString()); 
    this.mName = paramString;
    this.mImportance = paramInt;
  }
  
  private String getFieldsString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(", mDescription=");
    if (!TextUtils.isEmpty(this.mDesc)) {
      str = "hasDescription ";
    } else {
      str = "";
    } 
    stringBuilder.append(str);
    stringBuilder.append(", mImportance=");
    stringBuilder.append(this.mImportance);
    stringBuilder.append(", mBypassDnd=");
    stringBuilder.append(this.mBypassDnd);
    stringBuilder.append(", mLockscreenVisibility=");
    stringBuilder.append(this.mLockscreenVisibility);
    stringBuilder.append(", mSound=");
    stringBuilder.append(this.mSound);
    stringBuilder.append(", mLights=");
    stringBuilder.append(this.mLights);
    stringBuilder.append(", mLightColor=");
    stringBuilder.append(this.mLightColor);
    stringBuilder.append(", mVibration=");
    stringBuilder.append(Arrays.toString(this.mVibration));
    stringBuilder.append(", mUserLockedFields=");
    stringBuilder.append(Integer.toHexString(this.mUserLockedFields));
    stringBuilder.append(", mFgServiceShown=");
    stringBuilder.append(this.mFgServiceShown);
    stringBuilder.append(", mVibrationEnabled=");
    stringBuilder.append(this.mVibrationEnabled);
    stringBuilder.append(", mShowBadge=");
    stringBuilder.append(this.mShowBadge);
    stringBuilder.append(", mDeleted=");
    stringBuilder.append(this.mDeleted);
    stringBuilder.append(", mGroup='");
    stringBuilder.append(this.mGroup);
    stringBuilder.append('\'');
    stringBuilder.append(", mAudioAttributes=");
    stringBuilder.append(this.mAudioAttributes);
    stringBuilder.append(", mBlockableSystem=");
    stringBuilder.append(this.mBlockableSystem);
    stringBuilder.append(", mAllowBubbles=");
    stringBuilder.append(this.mAllowBubbles);
    stringBuilder.append(", mImportanceLockedByOEM=");
    stringBuilder.append(this.mImportanceLockedByOEM);
    stringBuilder.append(", mImportanceLockedDefaultApp=");
    stringBuilder.append(this.mImportanceLockedDefaultApp);
    stringBuilder.append(", mOriginalImp=");
    stringBuilder.append(this.mOriginalImportance);
    stringBuilder.append(", mParent=");
    stringBuilder.append(this.mParentId);
    stringBuilder.append(", mConversationId=");
    stringBuilder.append(this.mConversationId);
    stringBuilder.append(", mDemoted=");
    stringBuilder.append(this.mDemoted);
    stringBuilder.append(", mImportantConvo=");
    stringBuilder.append(this.mImportantConvo);
    return stringBuilder.toString();
  }
  
  private Uri getSoundForBackup(Context paramContext) {
    Uri uri2 = getSound();
    if (uri2 == null || Uri.EMPTY.equals(uri2))
      return null; 
    Uri uri1 = paramContext.getContentResolver().canonicalize(uri2);
    return (uri1 == null) ? Settings.System.DEFAULT_NOTIFICATION_URI : uri1;
  }
  
  private String getTrimmedString(String paramString) {
    return (paramString != null && paramString.length() > 1000) ? paramString.substring(0, 1000) : paramString;
  }
  
  private static String longArrayToString(long[] paramArrayOflong) {
    StringBuffer stringBuffer = new StringBuffer();
    if (paramArrayOflong != null && paramArrayOflong.length > 0) {
      for (byte b = 0; b < paramArrayOflong.length - 1; b++) {
        stringBuffer.append(paramArrayOflong[b]);
        stringBuffer.append(",");
      } 
      stringBuffer.append(paramArrayOflong[paramArrayOflong.length - 1]);
    } 
    return stringBuffer.toString();
  }
  
  private void populateFromXml(XmlPullParser paramXmlPullParser, boolean paramBoolean, Context paramContext) {
    Uri uri1;
    boolean bool2;
    boolean bool1 = true;
    if (!paramBoolean || paramContext != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2, "forRestore is true but got null context");
    setDescription(paramXmlPullParser.getAttributeValue(null, "desc"));
    if (safeInt(paramXmlPullParser, "priority", 0) != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    setBypassDnd(bool2);
    setLockscreenVisibility(safeInt(paramXmlPullParser, "visibility", -1000));
    Uri uri2 = safeUri(paramXmlPullParser, "sound");
    if (paramBoolean) {
      uri1 = restoreSoundUri(paramContext, uri2);
    } else {
      uri1 = uri2;
    } 
    setSound(uri1, safeAudioAttributes(paramXmlPullParser));
    enableLights(safeBool(paramXmlPullParser, "lights", false));
    setLightColor(safeInt(paramXmlPullParser, "light_color", 0));
    setVibrationPattern(safeLongArray(paramXmlPullParser, "vibration", null));
    enableVibration(safeBool(paramXmlPullParser, "vibration_enabled", false));
    setShowBadge(safeBool(paramXmlPullParser, "show_badge", false));
    setDeleted(safeBool(paramXmlPullParser, "deleted", false));
    setGroup(paramXmlPullParser.getAttributeValue(null, "group"));
    lockFields(safeInt(paramXmlPullParser, "locked", 0));
    setFgServiceShown(safeBool(paramXmlPullParser, "fgservice", false));
    setBlockable(safeBool(paramXmlPullParser, "blockable_system", false));
    setAllowBubbles(safeInt(paramXmlPullParser, "allow_bubbles", -1));
    setOriginalImportance(safeInt(paramXmlPullParser, "orig_imp", -1000));
    setConversationId(paramXmlPullParser.getAttributeValue(null, "parent"), paramXmlPullParser.getAttributeValue(null, "conv_id"));
    setDemoted(safeBool(paramXmlPullParser, "dem", false));
    setImportantConversation(safeBool(paramXmlPullParser, "imp_conv", false));
  }
  
  private Uri restoreSoundUri(Context paramContext, Uri paramUri) {
    if (paramUri == null || Uri.EMPTY.equals(paramUri))
      return null; 
    ContentResolver contentResolver = paramContext.getContentResolver();
    paramUri = contentResolver.canonicalize(paramUri);
    return (paramUri == null) ? Settings.System.DEFAULT_NOTIFICATION_URI : contentResolver.uncanonicalize(paramUri);
  }
  
  private static AudioAttributes safeAudioAttributes(XmlPullParser paramXmlPullParser) {
    int i = safeInt(paramXmlPullParser, "usage", 5);
    int j = safeInt(paramXmlPullParser, "content_type", 4);
    int k = safeInt(paramXmlPullParser, "flags", 0);
    return (new AudioAttributes.Builder()).setUsage(i).setContentType(j).setFlags(k).build();
  }
  
  private static boolean safeBool(XmlPullParser paramXmlPullParser, String paramString, boolean paramBoolean) {
    String str = paramXmlPullParser.getAttributeValue(null, paramString);
    return TextUtils.isEmpty(str) ? paramBoolean : Boolean.parseBoolean(str);
  }
  
  private static int safeInt(XmlPullParser paramXmlPullParser, String paramString, int paramInt) {
    return tryParseInt(paramXmlPullParser.getAttributeValue(null, paramString), paramInt);
  }
  
  private static long[] safeLongArray(XmlPullParser paramXmlPullParser, String paramString, long[] paramArrayOflong) {
    String str = paramXmlPullParser.getAttributeValue(null, paramString);
    if (TextUtils.isEmpty(str))
      return paramArrayOflong; 
    String[] arrayOfString = str.split(",");
    long[] arrayOfLong = new long[arrayOfString.length];
    for (byte b = 0; b < arrayOfString.length; b++) {
      try {
        arrayOfLong[b] = Long.parseLong(arrayOfString[b]);
      } catch (NumberFormatException numberFormatException) {
        arrayOfLong[b] = 0L;
      } 
    } 
    return arrayOfLong;
  }
  
  private static Uri safeUri(XmlPullParser paramXmlPullParser, String paramString) {
    Uri uri;
    String str2 = null;
    String str1 = paramXmlPullParser.getAttributeValue(null, paramString);
    if (str1 == null) {
      str1 = str2;
    } else {
      uri = Uri.parse(str1);
    } 
    return uri;
  }
  
  private static int tryParseInt(String paramString, int paramInt) {
    if (TextUtils.isEmpty(paramString))
      return paramInt; 
    try {
      return Integer.parseInt(paramString);
    } catch (NumberFormatException numberFormatException) {
      return paramInt;
    } 
  }
  
  private void writeXml(XmlSerializer paramXmlSerializer, boolean paramBoolean, Context paramContext) throws IOException {
    Uri uri;
    boolean bool;
    if (!paramBoolean || paramContext != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "forBackup is true but got null context");
    paramXmlSerializer.startTag(null, "channel");
    paramXmlSerializer.attribute(null, "id", getId());
    if (getName() != null)
      paramXmlSerializer.attribute(null, "name", getName().toString()); 
    if (getDescription() != null)
      paramXmlSerializer.attribute(null, "desc", getDescription()); 
    if (getImportance() != -1000)
      paramXmlSerializer.attribute(null, "importance", Integer.toString(getImportance())); 
    if (canBypassDnd())
      paramXmlSerializer.attribute(null, "priority", Integer.toString(2)); 
    if (getLockscreenVisibility() != -1000)
      paramXmlSerializer.attribute(null, "visibility", Integer.toString(getLockscreenVisibility())); 
    if (paramBoolean) {
      uri = getSoundForBackup(paramContext);
    } else {
      uri = getSound();
    } 
    if (uri != null)
      paramXmlSerializer.attribute(null, "sound", uri.toString()); 
    if (getAudioAttributes() != null) {
      paramXmlSerializer.attribute(null, "usage", Integer.toString(getAudioAttributes().getUsage()));
      paramXmlSerializer.attribute(null, "content_type", Integer.toString(getAudioAttributes().getContentType()));
      paramXmlSerializer.attribute(null, "flags", Integer.toString(getAudioAttributes().getFlags()));
    } 
    if (shouldShowLights())
      paramXmlSerializer.attribute(null, "lights", Boolean.toString(shouldShowLights())); 
    if (getLightColor() != 0)
      paramXmlSerializer.attribute(null, "light_color", Integer.toString(getLightColor())); 
    if (shouldVibrate())
      paramXmlSerializer.attribute(null, "vibration_enabled", Boolean.toString(shouldVibrate())); 
    if (getVibrationPattern() != null)
      paramXmlSerializer.attribute(null, "vibration", longArrayToString(getVibrationPattern())); 
    if (getUserLockedFields() != 0)
      paramXmlSerializer.attribute(null, "locked", Integer.toString(getUserLockedFields())); 
    if (isFgServiceShown())
      paramXmlSerializer.attribute(null, "fgservice", Boolean.toString(isFgServiceShown())); 
    if (canShowBadge())
      paramXmlSerializer.attribute(null, "show_badge", Boolean.toString(canShowBadge())); 
    if (isDeleted())
      paramXmlSerializer.attribute(null, "deleted", Boolean.toString(isDeleted())); 
    if (getGroup() != null)
      paramXmlSerializer.attribute(null, "group", getGroup()); 
    if (isBlockable())
      paramXmlSerializer.attribute(null, "blockable_system", Boolean.toString(isBlockable())); 
    if (getAllowBubbles() != -1)
      paramXmlSerializer.attribute(null, "allow_bubbles", Integer.toString(getAllowBubbles())); 
    if (getOriginalImportance() != -1000)
      paramXmlSerializer.attribute(null, "orig_imp", Integer.toString(getOriginalImportance())); 
    if (getParentChannelId() != null)
      paramXmlSerializer.attribute(null, "parent", getParentChannelId()); 
    if (getConversationId() != null)
      paramXmlSerializer.attribute(null, "conv_id", getConversationId()); 
    if (isDemoted())
      paramXmlSerializer.attribute(null, "dem", Boolean.toString(isDemoted())); 
    if (isImportantConversation())
      paramXmlSerializer.attribute(null, "imp_conv", Boolean.toString(isImportantConversation())); 
    paramXmlSerializer.endTag(null, "channel");
  }
  
  public boolean canBubble() {
    int i = this.mAllowBubbles;
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public boolean canBypassDnd() {
    return this.mBypassDnd;
  }
  
  public boolean canShowBadge() {
    return this.mShowBadge;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(PrintWriter paramPrintWriter, String paramString, boolean paramBoolean) {
    String str1 = this.mName;
    String str2 = str1;
    if (paramBoolean)
      str2 = (String)TextUtils.trimToLengthWithEllipsis(str1, 3); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NotificationChannel{mId='");
    stringBuilder.append(this.mId);
    stringBuilder.append('\'');
    stringBuilder.append(", mName=");
    stringBuilder.append(str2);
    stringBuilder.append(getFieldsString());
    stringBuilder.append('}');
    str2 = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(str2);
    paramPrintWriter.println(stringBuilder.toString());
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1138166333441L, this.mId);
    paramProtoOutputStream.write(1138166333442L, this.mName);
    paramProtoOutputStream.write(1138166333443L, this.mDesc);
    paramProtoOutputStream.write(1120986464260L, this.mImportance);
    paramProtoOutputStream.write(1133871366149L, this.mBypassDnd);
    paramProtoOutputStream.write(1120986464262L, this.mLockscreenVisibility);
    Uri uri = this.mSound;
    if (uri != null)
      paramProtoOutputStream.write(1138166333447L, uri.toString()); 
    paramProtoOutputStream.write(1133871366152L, this.mLights);
    paramProtoOutputStream.write(1120986464265L, this.mLightColor);
    long[] arrayOfLong = this.mVibration;
    if (arrayOfLong != null) {
      int i = arrayOfLong.length;
      for (byte b = 0; b < i; b++)
        paramProtoOutputStream.write(2211908157450L, arrayOfLong[b]); 
    } 
    paramProtoOutputStream.write(1120986464267L, this.mUserLockedFields);
    paramProtoOutputStream.write(1133871366162L, this.mFgServiceShown);
    paramProtoOutputStream.write(1133871366156L, this.mVibrationEnabled);
    paramProtoOutputStream.write(1133871366157L, this.mShowBadge);
    paramProtoOutputStream.write(1133871366158L, this.mDeleted);
    paramProtoOutputStream.write(1138166333455L, this.mGroup);
    AudioAttributes audioAttributes = this.mAudioAttributes;
    if (audioAttributes != null)
      audioAttributes.dumpDebug(paramProtoOutputStream, 1146756268048L); 
    paramProtoOutputStream.write(1133871366161L, this.mBlockableSystem);
    paramProtoOutputStream.write(1133871366163L, this.mAllowBubbles);
    paramProtoOutputStream.end(paramLong);
  }
  
  public void enableLights(boolean paramBoolean) {
    this.mLights = paramBoolean;
  }
  
  public void enableVibration(boolean paramBoolean) {
    this.mVibrationEnabled = paramBoolean;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (getImportance() != paramObject.getImportance() || this.mBypassDnd != ((NotificationChannel)paramObject).mBypassDnd || getLockscreenVisibility() != paramObject.getLockscreenVisibility() || this.mLights != ((NotificationChannel)paramObject).mLights || getLightColor() != paramObject.getLightColor() || getUserLockedFields() != paramObject.getUserLockedFields() || isFgServiceShown() != paramObject.isFgServiceShown() || this.mVibrationEnabled != ((NotificationChannel)paramObject).mVibrationEnabled || this.mShowBadge != ((NotificationChannel)paramObject).mShowBadge || isDeleted() != paramObject.isDeleted() || isBlockable() != paramObject.isBlockable() || this.mAllowBubbles != ((NotificationChannel)paramObject).mAllowBubbles || !Objects.equals(getId(), paramObject.getId()) || !Objects.equals(getName(), paramObject.getName()) || !Objects.equals(this.mDesc, ((NotificationChannel)paramObject).mDesc) || !Objects.equals(getSound(), paramObject.getSound()) || !Arrays.equals(this.mVibration, ((NotificationChannel)paramObject).mVibration) || !Objects.equals(getGroup(), paramObject.getGroup()) || !Objects.equals(getAudioAttributes(), paramObject.getAudioAttributes()) || this.mImportanceLockedByOEM != ((NotificationChannel)paramObject).mImportanceLockedByOEM || this.mImportanceLockedDefaultApp != ((NotificationChannel)paramObject).mImportanceLockedDefaultApp || this.mOriginalImportance != ((NotificationChannel)paramObject).mOriginalImportance || !Objects.equals(getParentChannelId(), paramObject.getParentChannelId()) || !Objects.equals(getConversationId(), paramObject.getConversationId()) || isDemoted() != paramObject.isDemoted() || isImportantConversation() != paramObject.isImportantConversation())
      bool = false; 
    return bool;
  }
  
  public int getAllowBubbles() {
    return this.mAllowBubbles;
  }
  
  public AudioAttributes getAudioAttributes() {
    return this.mAudioAttributes;
  }
  
  public String getConversationId() {
    return this.mConversationId;
  }
  
  public String getDescription() {
    return this.mDesc;
  }
  
  public String getGroup() {
    return this.mGroup;
  }
  
  public String getId() {
    return this.mId;
  }
  
  public int getImportance() {
    return this.mImportance;
  }
  
  public int getLightColor() {
    return this.mLightColor;
  }
  
  public int getLockscreenVisibility() {
    return this.mLockscreenVisibility;
  }
  
  public CharSequence getName() {
    return this.mName;
  }
  
  public int getOriginalImportance() {
    return this.mOriginalImportance;
  }
  
  public String getParentChannelId() {
    return this.mParentId;
  }
  
  public Uri getSound() {
    return this.mSound;
  }
  
  @SystemApi
  public int getUserLockedFields() {
    return this.mUserLockedFields;
  }
  
  public long[] getVibrationPattern() {
    return this.mVibration;
  }
  
  public boolean hasUserSetImportance() {
    boolean bool;
    if ((this.mUserLockedFields & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasUserSetSound() {
    boolean bool;
    if ((this.mUserLockedFields & 0x20) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { 
          getId(), getName(), this.mDesc, Integer.valueOf(getImportance()), Boolean.valueOf(this.mBypassDnd), Integer.valueOf(getLockscreenVisibility()), getSound(), Boolean.valueOf(this.mLights), Integer.valueOf(getLightColor()), Integer.valueOf(getUserLockedFields()), 
          Boolean.valueOf(isFgServiceShown()), Boolean.valueOf(this.mVibrationEnabled), Boolean.valueOf(this.mShowBadge), Boolean.valueOf(isDeleted()), getGroup(), getAudioAttributes(), Boolean.valueOf(isBlockable()), Integer.valueOf(this.mAllowBubbles), Boolean.valueOf(this.mImportanceLockedByOEM), Boolean.valueOf(this.mImportanceLockedDefaultApp), 
          Integer.valueOf(this.mOriginalImportance), this.mParentId, this.mConversationId, Boolean.valueOf(this.mDemoted), Boolean.valueOf(this.mImportantConvo) }) * 31 + Arrays.hashCode(this.mVibration);
  }
  
  public boolean isBlockable() {
    return this.mBlockableSystem;
  }
  
  @SystemApi
  public boolean isDeleted() {
    return this.mDeleted;
  }
  
  public boolean isDemoted() {
    return this.mDemoted;
  }
  
  public boolean isFgServiceShown() {
    return this.mFgServiceShown;
  }
  
  public boolean isImportanceLockedByCriticalDeviceFunction() {
    return this.mImportanceLockedDefaultApp;
  }
  
  public boolean isImportanceLockedByOEM() {
    return this.mImportanceLockedByOEM;
  }
  
  public boolean isImportantConversation() {
    return this.mImportantConvo;
  }
  
  public void lockFields(int paramInt) {
    this.mUserLockedFields |= paramInt;
  }
  
  @SystemApi
  public void populateFromXml(XmlPullParser paramXmlPullParser) {
    populateFromXml(paramXmlPullParser, false, null);
  }
  
  public void populateFromXmlForRestore(XmlPullParser paramXmlPullParser, Context paramContext) {
    populateFromXml(paramXmlPullParser, true, paramContext);
  }
  
  public void setAllowBubbles(int paramInt) {
    this.mAllowBubbles = paramInt;
  }
  
  public void setAllowBubbles(boolean paramBoolean) {
    this.mAllowBubbles = paramBoolean;
  }
  
  @SystemApi
  public void setBlockable(boolean paramBoolean) {
    this.mBlockableSystem = paramBoolean;
  }
  
  public void setBypassDnd(boolean paramBoolean) {
    this.mBypassDnd = paramBoolean;
  }
  
  public void setConversationId(String paramString1, String paramString2) {
    this.mParentId = paramString1;
    this.mConversationId = paramString2;
  }
  
  public void setDeleted(boolean paramBoolean) {
    this.mDeleted = paramBoolean;
  }
  
  public void setDemoted(boolean paramBoolean) {
    this.mDemoted = paramBoolean;
  }
  
  public void setDescription(String paramString) {
    this.mDesc = getTrimmedString(paramString);
  }
  
  public void setFgServiceShown(boolean paramBoolean) {
    this.mFgServiceShown = paramBoolean;
  }
  
  public void setGroup(String paramString) {
    this.mGroup = paramString;
  }
  
  public void setId(String paramString) {
    this.mId = paramString;
  }
  
  public void setImportance(int paramInt) {
    this.mImportance = paramInt;
  }
  
  public void setImportanceLockedByCriticalDeviceFunction(boolean paramBoolean) {
    this.mImportanceLockedDefaultApp = paramBoolean;
  }
  
  public void setImportanceLockedByOEM(boolean paramBoolean) {
    this.mImportanceLockedByOEM = paramBoolean;
  }
  
  public void setImportantConversation(boolean paramBoolean) {
    this.mImportantConvo = paramBoolean;
  }
  
  public void setLightColor(int paramInt) {
    this.mLightColor = paramInt;
  }
  
  public void setLockscreenVisibility(int paramInt) {
    this.mLockscreenVisibility = paramInt;
  }
  
  public void setName(CharSequence paramCharSequence) {
    if (paramCharSequence != null) {
      paramCharSequence = getTrimmedString(paramCharSequence.toString());
    } else {
      paramCharSequence = null;
    } 
    this.mName = (String)paramCharSequence;
  }
  
  public void setOriginalImportance(int paramInt) {
    this.mOriginalImportance = paramInt;
  }
  
  public void setShowBadge(boolean paramBoolean) {
    this.mShowBadge = paramBoolean;
  }
  
  public void setSound(Uri paramUri, AudioAttributes paramAudioAttributes) {
    this.mSound = paramUri;
    this.mAudioAttributes = paramAudioAttributes;
  }
  
  public void setVibrationPattern(long[] paramArrayOflong) {
    boolean bool;
    if (paramArrayOflong != null && paramArrayOflong.length > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mVibrationEnabled = bool;
    this.mVibration = paramArrayOflong;
  }
  
  public boolean shouldShowLights() {
    return this.mLights;
  }
  
  public boolean shouldVibrate() {
    return this.mVibrationEnabled;
  }
  
  @SystemApi
  public JSONObject toJson() throws JSONException {
    JSONObject jSONObject = new JSONObject();
    jSONObject.put("id", getId());
    jSONObject.put("name", getName());
    jSONObject.put("desc", getDescription());
    if (getImportance() != -1000)
      jSONObject.put("importance", NotificationListenerService.Ranking.importanceToString(getImportance())); 
    if (canBypassDnd())
      jSONObject.put("priority", 2); 
    if (getLockscreenVisibility() != -1000)
      jSONObject.put("visibility", Notification.visibilityToString(getLockscreenVisibility())); 
    if (getSound() != null)
      jSONObject.put("sound", getSound().toString()); 
    if (getAudioAttributes() != null) {
      jSONObject.put("usage", Integer.toString(getAudioAttributes().getUsage()));
      jSONObject.put("content_type", Integer.toString(getAudioAttributes().getContentType()));
      jSONObject.put("flags", Integer.toString(getAudioAttributes().getFlags()));
    } 
    jSONObject.put("lights", Boolean.toString(shouldShowLights()));
    jSONObject.put("light_color", Integer.toString(getLightColor()));
    jSONObject.put("vibration_enabled", Boolean.toString(shouldVibrate()));
    jSONObject.put("locked", Integer.toString(getUserLockedFields()));
    jSONObject.put("fgservice", Boolean.toString(isFgServiceShown()));
    jSONObject.put("vibration", longArrayToString(getVibrationPattern()));
    jSONObject.put("show_badge", Boolean.toString(canShowBadge()));
    jSONObject.put("deleted", Boolean.toString(isDeleted()));
    jSONObject.put("group", getGroup());
    jSONObject.put("blockable_system", isBlockable());
    jSONObject.put("allow_bubbles", getAllowBubbles());
    return jSONObject;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NotificationChannel{mId='");
    stringBuilder.append(this.mId);
    stringBuilder.append('\'');
    stringBuilder.append(", mName=");
    stringBuilder.append(this.mName);
    stringBuilder.append(getFieldsString());
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void unlockFields(int paramInt) {
    this.mUserLockedFields &= paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mId != null) {
      paramParcel.writeByte((byte)1);
      paramParcel.writeString(this.mId);
    } else {
      paramParcel.writeByte((byte)0);
    } 
    if (this.mName != null) {
      paramParcel.writeByte((byte)1);
      paramParcel.writeString(this.mName);
    } else {
      paramParcel.writeByte((byte)0);
    } 
    if (this.mDesc != null) {
      paramParcel.writeByte((byte)1);
      paramParcel.writeString(this.mDesc);
    } else {
      paramParcel.writeByte((byte)0);
    } 
    paramParcel.writeInt(this.mImportance);
    paramParcel.writeByte(this.mBypassDnd);
    paramParcel.writeInt(this.mLockscreenVisibility);
    if (this.mSound != null) {
      paramParcel.writeByte((byte)1);
      this.mSound.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeByte((byte)0);
    } 
    paramParcel.writeByte(this.mLights);
    paramParcel.writeLongArray(this.mVibration);
    paramParcel.writeInt(this.mUserLockedFields);
    paramParcel.writeByte(this.mFgServiceShown);
    paramParcel.writeByte(this.mVibrationEnabled);
    paramParcel.writeByte(this.mShowBadge);
    paramParcel.writeByte(this.mDeleted);
    if (this.mGroup != null) {
      paramParcel.writeByte((byte)1);
      paramParcel.writeString(this.mGroup);
    } else {
      paramParcel.writeByte((byte)0);
    } 
    if (this.mAudioAttributes != null) {
      paramParcel.writeInt(1);
      this.mAudioAttributes.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.mLightColor);
    paramParcel.writeBoolean(this.mBlockableSystem);
    paramParcel.writeInt(this.mAllowBubbles);
    paramParcel.writeBoolean(this.mImportanceLockedByOEM);
    paramParcel.writeInt(this.mOriginalImportance);
    paramParcel.writeString(this.mParentId);
    paramParcel.writeString(this.mConversationId);
    paramParcel.writeBoolean(this.mDemoted);
    paramParcel.writeBoolean(this.mImportantConvo);
  }
  
  @SystemApi
  public void writeXml(XmlSerializer paramXmlSerializer) throws IOException {
    writeXml(paramXmlSerializer, false, null);
  }
  
  public void writeXmlForBackup(XmlSerializer paramXmlSerializer, Context paramContext) throws IOException {
    writeXml(paramXmlSerializer, true, paramContext);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
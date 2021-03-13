package android.content.pm;

import android.annotation.SystemApi;
import android.app.Person;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.LocusId;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import java.util.Set;

public final class ShortcutInfo implements Parcelable {
  private static final String ANDROID_PACKAGE_NAME = "android";
  
  public static final int CLONE_REMOVE_FOR_APP_PREDICTION = 9;
  
  public static final int CLONE_REMOVE_FOR_CREATOR = 9;
  
  public static final int CLONE_REMOVE_FOR_LAUNCHER = 27;
  
  public static final int CLONE_REMOVE_FOR_LAUNCHER_APPROVAL = 26;
  
  private static final int CLONE_REMOVE_ICON = 1;
  
  private static final int CLONE_REMOVE_INTENT = 2;
  
  public static final int CLONE_REMOVE_NON_KEY_INFO = 4;
  
  public static final int CLONE_REMOVE_PERSON = 16;
  
  public static final int CLONE_REMOVE_RES_NAMES = 8;
  
  public static final Parcelable.Creator<ShortcutInfo> CREATOR = new Parcelable.Creator<ShortcutInfo>() {
      public ShortcutInfo createFromParcel(Parcel param1Parcel) {
        return new ShortcutInfo(param1Parcel);
      }
      
      public ShortcutInfo[] newArray(int param1Int) {
        return new ShortcutInfo[param1Int];
      }
    };
  
  public static final int DISABLED_REASON_APP_CHANGED = 2;
  
  public static final int DISABLED_REASON_BACKUP_NOT_SUPPORTED = 101;
  
  public static final int DISABLED_REASON_BY_APP = 1;
  
  public static final int DISABLED_REASON_NOT_DISABLED = 0;
  
  public static final int DISABLED_REASON_OTHER_RESTORE_ISSUE = 103;
  
  private static final int DISABLED_REASON_RESTORE_ISSUE_START = 100;
  
  public static final int DISABLED_REASON_SIGNATURE_MISMATCH = 102;
  
  public static final int DISABLED_REASON_UNKNOWN = 3;
  
  public static final int DISABLED_REASON_VERSION_LOWER = 100;
  
  public static final int FLAG_ADAPTIVE_BITMAP = 512;
  
  public static final int FLAG_CACHED_ALL = 1073758208;
  
  public static final int FLAG_CACHED_BUBBLES = 1073741824;
  
  public static final int FLAG_CACHED_NOTIFICATIONS = 16384;
  
  public static final int FLAG_DISABLED = 64;
  
  public static final int FLAG_DYNAMIC = 1;
  
  public static final int FLAG_HAS_ICON_FILE = 8;
  
  public static final int FLAG_HAS_ICON_RES = 4;
  
  public static final int FLAG_HAS_ICON_URI = 32768;
  
  public static final int FLAG_ICON_FILE_PENDING_SAVE = 2048;
  
  public static final int FLAG_IMMUTABLE = 256;
  
  public static final int FLAG_KEY_FIELDS_ONLY = 16;
  
  public static final int FLAG_LONG_LIVED = 8192;
  
  public static final int FLAG_MANIFEST = 32;
  
  public static final int FLAG_PINNED = 2;
  
  public static final int FLAG_RETURNED_BY_SERVICE = 1024;
  
  public static final int FLAG_SHADOW = 4096;
  
  public static final int FLAG_STRINGS_RESOLVED = 128;
  
  private static final int IMPLICIT_RANK_MASK = 2147483647;
  
  private static final int RANK_CHANGED_BIT = -2147483648;
  
  public static final int RANK_NOT_SET = 2147483647;
  
  private static final String RES_TYPE_STRING = "string";
  
  public static final String SHORTCUT_CATEGORY_CONVERSATION = "android.shortcut.conversation";
  
  static final String TAG = "Shortcut";
  
  public static final int VERSION_CODE_UNKNOWN = -1;
  
  private ComponentName mActivity;
  
  private String mBitmapPath;
  
  private ArraySet<String> mCategories;
  
  private CharSequence mDisabledMessage;
  
  private int mDisabledMessageResId;
  
  private String mDisabledMessageResName;
  
  private int mDisabledReason;
  
  private PersistableBundle mExtras;
  
  private int mFlags;
  
  private Icon mIcon;
  
  private int mIconResId;
  
  private String mIconResName;
  
  private String mIconUri;
  
  private final String mId;
  
  private int mImplicitRank;
  
  private PersistableBundle[] mIntentPersistableExtrases;
  
  private Intent[] mIntents;
  
  private long mLastChangedTimestamp;
  
  private LocusId mLocusId;
  
  private final String mPackageName;
  
  private Person[] mPersons;
  
  private int mRank;
  
  private CharSequence mText;
  
  private int mTextResId;
  
  private String mTextResName;
  
  private CharSequence mTitle;
  
  private int mTitleResId;
  
  private String mTitleResName;
  
  private final int mUserId;
  
  public ShortcutInfo(int paramInt1, String paramString1, String paramString2, ComponentName paramComponentName, Icon paramIcon, CharSequence paramCharSequence1, int paramInt2, String paramString3, CharSequence paramCharSequence2, int paramInt3, String paramString4, CharSequence paramCharSequence3, int paramInt4, String paramString5, Set<String> paramSet, Intent[] paramArrayOfIntent, int paramInt5, PersistableBundle paramPersistableBundle, long paramLong, int paramInt6, int paramInt7, String paramString6, String paramString7, String paramString8, int paramInt8, Person[] paramArrayOfPerson, LocusId paramLocusId) {
    this.mUserId = paramInt1;
    this.mId = paramString1;
    this.mPackageName = paramString2;
    this.mActivity = paramComponentName;
    this.mIcon = paramIcon;
    this.mTitle = paramCharSequence1;
    this.mTitleResId = paramInt2;
    this.mTitleResName = paramString3;
    this.mText = paramCharSequence2;
    this.mTextResId = paramInt3;
    this.mTextResName = paramString4;
    this.mDisabledMessage = paramCharSequence3;
    this.mDisabledMessageResId = paramInt4;
    this.mDisabledMessageResName = paramString5;
    this.mCategories = cloneCategories(paramSet);
    this.mIntents = cloneIntents(paramArrayOfIntent);
    fixUpIntentExtras();
    this.mRank = paramInt5;
    this.mExtras = paramPersistableBundle;
    this.mLastChangedTimestamp = paramLong;
    this.mFlags = paramInt6;
    this.mIconResId = paramInt7;
    this.mIconResName = paramString6;
    this.mBitmapPath = paramString7;
    this.mIconUri = paramString8;
    this.mDisabledReason = paramInt8;
    this.mPersons = paramArrayOfPerson;
    this.mLocusId = paramLocusId;
  }
  
  private ShortcutInfo(Builder paramBuilder) {
    this.mUserId = paramBuilder.mContext.getUserId();
    this.mId = (String)Preconditions.checkStringNotEmpty(paramBuilder.mId, "Shortcut ID must be provided");
    this.mPackageName = paramBuilder.mContext.getPackageName();
    this.mActivity = paramBuilder.mActivity;
    this.mIcon = paramBuilder.mIcon;
    this.mTitle = paramBuilder.mTitle;
    this.mTitleResId = paramBuilder.mTitleResId;
    this.mText = paramBuilder.mText;
    this.mTextResId = paramBuilder.mTextResId;
    this.mDisabledMessage = paramBuilder.mDisabledMessage;
    this.mDisabledMessageResId = paramBuilder.mDisabledMessageResId;
    this.mCategories = cloneCategories(paramBuilder.mCategories);
    this.mIntents = cloneIntents(paramBuilder.mIntents);
    fixUpIntentExtras();
    this.mPersons = clonePersons(paramBuilder.mPersons);
    if (paramBuilder.mIsLongLived)
      setLongLived(); 
    this.mRank = paramBuilder.mRank;
    this.mExtras = paramBuilder.mExtras;
    this.mLocusId = paramBuilder.mLocusId;
    updateTimestamp();
  }
  
  private ShortcutInfo(ShortcutInfo paramShortcutInfo, int paramInt) {
    this.mUserId = paramShortcutInfo.mUserId;
    this.mId = paramShortcutInfo.mId;
    this.mPackageName = paramShortcutInfo.mPackageName;
    this.mActivity = paramShortcutInfo.mActivity;
    int i = paramShortcutInfo.mFlags;
    this.mFlags = i;
    this.mLastChangedTimestamp = paramShortcutInfo.mLastChangedTimestamp;
    this.mDisabledReason = paramShortcutInfo.mDisabledReason;
    this.mLocusId = paramShortcutInfo.mLocusId;
    this.mIconResId = paramShortcutInfo.mIconResId;
    if ((paramInt & 0x4) == 0) {
      if ((paramInt & 0x1) == 0) {
        this.mIcon = paramShortcutInfo.mIcon;
        this.mBitmapPath = paramShortcutInfo.mBitmapPath;
        this.mIconUri = paramShortcutInfo.mIconUri;
      } 
      this.mTitle = paramShortcutInfo.mTitle;
      this.mTitleResId = paramShortcutInfo.mTitleResId;
      this.mText = paramShortcutInfo.mText;
      this.mTextResId = paramShortcutInfo.mTextResId;
      this.mDisabledMessage = paramShortcutInfo.mDisabledMessage;
      this.mDisabledMessageResId = paramShortcutInfo.mDisabledMessageResId;
      this.mCategories = cloneCategories((Set<String>)paramShortcutInfo.mCategories);
      if ((paramInt & 0x10) == 0)
        this.mPersons = clonePersons(paramShortcutInfo.mPersons); 
      if ((paramInt & 0x2) == 0) {
        this.mIntents = cloneIntents(paramShortcutInfo.mIntents);
        this.mIntentPersistableExtrases = clonePersistableBundle(paramShortcutInfo.mIntentPersistableExtrases);
      } 
      this.mRank = paramShortcutInfo.mRank;
      this.mExtras = paramShortcutInfo.mExtras;
      if ((paramInt & 0x8) == 0) {
        this.mTitleResName = paramShortcutInfo.mTitleResName;
        this.mTextResName = paramShortcutInfo.mTextResName;
        this.mDisabledMessageResName = paramShortcutInfo.mDisabledMessageResName;
        this.mIconResName = paramShortcutInfo.mIconResName;
      } 
    } else {
      this.mFlags = i | 0x10;
    } 
  }
  
  private ShortcutInfo(Parcel paramParcel) {
    ClassLoader classLoader = getClass().getClassLoader();
    this.mUserId = paramParcel.readInt();
    this.mId = paramParcel.readString8();
    this.mPackageName = paramParcel.readString8();
    this.mActivity = (ComponentName)paramParcel.readParcelable(classLoader);
    this.mFlags = paramParcel.readInt();
    this.mIconResId = paramParcel.readInt();
    this.mLastChangedTimestamp = paramParcel.readLong();
    this.mDisabledReason = paramParcel.readInt();
    if (paramParcel.readInt() == 0)
      return; 
    this.mIcon = (Icon)paramParcel.readParcelable(classLoader);
    this.mTitle = paramParcel.readCharSequence();
    this.mTitleResId = paramParcel.readInt();
    this.mText = paramParcel.readCharSequence();
    this.mTextResId = paramParcel.readInt();
    this.mDisabledMessage = paramParcel.readCharSequence();
    this.mDisabledMessageResId = paramParcel.readInt();
    this.mIntents = (Intent[])paramParcel.readParcelableArray(classLoader, Intent.class);
    this.mIntentPersistableExtrases = (PersistableBundle[])paramParcel.readParcelableArray(classLoader, PersistableBundle.class);
    this.mRank = paramParcel.readInt();
    this.mExtras = (PersistableBundle)paramParcel.readParcelable(classLoader);
    this.mBitmapPath = paramParcel.readString8();
    this.mIconResName = paramParcel.readString8();
    this.mTitleResName = paramParcel.readString8();
    this.mTextResName = paramParcel.readString8();
    this.mDisabledMessageResName = paramParcel.readString8();
    int i = paramParcel.readInt();
    if (i == 0) {
      this.mCategories = null;
    } else {
      this.mCategories = new ArraySet(i);
      for (byte b = 0; b < i; b++)
        this.mCategories.add(paramParcel.readString8().intern()); 
    } 
    this.mPersons = (Person[])paramParcel.readParcelableArray(classLoader, Person.class);
    this.mLocusId = (LocusId)paramParcel.readParcelable(classLoader);
    this.mIconUri = paramParcel.readString8();
  }
  
  private void addIndentOrComma(StringBuilder paramStringBuilder, String paramString) {
    if (paramString != null) {
      paramStringBuilder.append("\n  ");
      paramStringBuilder.append(paramString);
    } else {
      paramStringBuilder.append(", ");
    } 
  }
  
  private static ArraySet<String> cloneCategories(Set<String> paramSet) {
    if (paramSet == null)
      return null; 
    ArraySet<String> arraySet = new ArraySet(paramSet.size());
    for (CharSequence charSequence : paramSet) {
      if (!TextUtils.isEmpty(charSequence))
        arraySet.add(charSequence.toString().intern()); 
    } 
    return arraySet;
  }
  
  private static Intent[] cloneIntents(Intent[] paramArrayOfIntent) {
    if (paramArrayOfIntent == null)
      return null; 
    Intent[] arrayOfIntent = new Intent[paramArrayOfIntent.length];
    for (byte b = 0; b < arrayOfIntent.length; b++) {
      if (paramArrayOfIntent[b] != null)
        arrayOfIntent[b] = new Intent(paramArrayOfIntent[b]); 
    } 
    return arrayOfIntent;
  }
  
  private static PersistableBundle[] clonePersistableBundle(PersistableBundle[] paramArrayOfPersistableBundle) {
    if (paramArrayOfPersistableBundle == null)
      return null; 
    PersistableBundle[] arrayOfPersistableBundle = new PersistableBundle[paramArrayOfPersistableBundle.length];
    for (byte b = 0; b < arrayOfPersistableBundle.length; b++) {
      if (paramArrayOfPersistableBundle[b] != null)
        arrayOfPersistableBundle[b] = new PersistableBundle(paramArrayOfPersistableBundle[b]); 
    } 
    return arrayOfPersistableBundle;
  }
  
  private static Person[] clonePersons(Person[] paramArrayOfPerson) {
    if (paramArrayOfPerson == null)
      return null; 
    Person[] arrayOfPerson = new Person[paramArrayOfPerson.length];
    for (byte b = 0; b < arrayOfPerson.length; b++) {
      if (paramArrayOfPerson[b] != null)
        arrayOfPerson[b] = paramArrayOfPerson[b].toBuilder().setIcon(null).build(); 
    } 
    return arrayOfPerson;
  }
  
  private void fixUpIntentExtras() {
    Intent[] arrayOfIntent = this.mIntents;
    if (arrayOfIntent == null) {
      this.mIntentPersistableExtrases = null;
      return;
    } 
    this.mIntentPersistableExtrases = new PersistableBundle[arrayOfIntent.length];
    byte b = 0;
    while (true) {
      arrayOfIntent = this.mIntents;
      if (b < arrayOfIntent.length) {
        Intent intent = arrayOfIntent[b];
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
          this.mIntentPersistableExtrases[b] = null;
        } else {
          this.mIntentPersistableExtrases[b] = new PersistableBundle(bundle);
          intent.replaceExtras((Bundle)null);
        } 
        b++;
        continue;
      } 
      break;
    } 
  }
  
  public static String getDisabledReasonDebugString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          StringBuilder stringBuilder;
          switch (paramInt) {
            default:
              stringBuilder = new StringBuilder();
              stringBuilder.append("[Disabled: unknown reason:");
              stringBuilder.append(paramInt);
              stringBuilder.append("]");
              return stringBuilder.toString();
            case 103:
              return "[Disabled: unknown restore issue]";
            case 102:
              return "[Disabled: signature mismatch]";
            case 101:
              return "[Disabled: backup not supported]";
            case 100:
              break;
          } 
          return "[Disabled: lower version]";
        } 
        return "[Disabled: app changed]";
      } 
      return "[Disabled: by app]";
    } 
    return "[Not disabled]";
  }
  
  public static String getDisabledReasonForRestoreIssue(Context paramContext, int paramInt) {
    Resources resources = paramContext.getResources();
    if (paramInt != 3) {
      switch (paramInt) {
        default:
          return null;
        case 103:
          return resources.getString(17041240);
        case 102:
          return resources.getString(17041239);
        case 101:
          return resources.getString(17041238);
        case 100:
          break;
      } 
      return resources.getString(17041241);
    } 
    return resources.getString(17041237);
  }
  
  public static IllegalArgumentException getInvalidIconException() {
    return new IllegalArgumentException("Unsupported icon type: only the bitmap and resource types are supported");
  }
  
  public static String getResourceEntryName(String paramString) {
    int i = paramString.indexOf('/');
    return (i < 0) ? null : paramString.substring(i + 1);
  }
  
  public static String getResourcePackageName(String paramString) {
    int i = paramString.indexOf(':');
    return (i < 0) ? null : paramString.substring(0, i);
  }
  
  private CharSequence getResourceString(Resources paramResources, int paramInt, CharSequence paramCharSequence) {
    try {
      return paramResources.getString(paramInt);
    } catch (android.content.res.Resources.NotFoundException notFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Resource for ID=");
      stringBuilder.append(paramInt);
      stringBuilder.append(" not found in package ");
      stringBuilder.append(this.mPackageName);
      Log.e("Shortcut", stringBuilder.toString());
      return paramCharSequence;
    } 
  }
  
  public static String getResourceTypeAndEntryName(String paramString) {
    int i = paramString.indexOf(':');
    return (i < 0) ? null : paramString.substring(i + 1);
  }
  
  public static String getResourceTypeName(String paramString) {
    int i = paramString.indexOf(':');
    if (i < 0)
      return null; 
    int j = paramString.indexOf('/', i + 1);
    return (j < 0) ? null : paramString.substring(i + 1, j);
  }
  
  public static boolean isDisabledForRestoreIssue(int paramInt) {
    boolean bool;
    if (paramInt >= 100) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static int lookUpResourceId(Resources paramResources, String paramString1, String paramString2, String paramString3) {
    if (paramString1 == null)
      return 0; 
    try {
      return Integer.parseInt(paramString1);
    } catch (NumberFormatException numberFormatException) {
      return paramResources.getIdentifier(paramString1, paramString2, paramString3);
    } catch (android.content.res.Resources.NotFoundException notFoundException) {}
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Resource ID for name=");
    stringBuilder.append(paramString1);
    stringBuilder.append(" not found in package ");
    stringBuilder.append(paramString3);
    Log.e("Shortcut", stringBuilder.toString());
    return 0;
  }
  
  public static String lookUpResourceName(Resources paramResources, int paramInt, boolean paramBoolean, String paramString) {
    if (paramInt == 0)
      return null; 
    try {
      String str = paramResources.getResourceName(paramInt);
      if ("android".equals(getResourcePackageName(str)))
        return String.valueOf(paramInt); 
      if (paramBoolean) {
        str = getResourceTypeAndEntryName(str);
      } else {
        str = getResourceEntryName(str);
      } 
      return str;
    } catch (android.content.res.Resources.NotFoundException notFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Resource name for ID=");
      stringBuilder.append(paramInt);
      stringBuilder.append(" not found in package ");
      stringBuilder.append(paramString);
      stringBuilder.append(". Resource IDs may change when the application is upgraded, and the system may not be able to find the correct resource.");
      Log.e("Shortcut", stringBuilder.toString());
      return null;
    } 
  }
  
  public static Intent setIntentExtras(Intent paramIntent, PersistableBundle paramPersistableBundle) {
    if (paramPersistableBundle == null) {
      paramIntent.replaceExtras((Bundle)null);
    } else {
      paramIntent.replaceExtras(new Bundle(paramPersistableBundle));
    } 
    return paramIntent;
  }
  
  private String toStringInner(boolean paramBoolean1, boolean paramBoolean2, String paramString) {
    CharSequence charSequence;
    StringBuilder stringBuilder = new StringBuilder();
    if (paramString != null)
      stringBuilder.append(paramString); 
    stringBuilder.append("ShortcutInfo {");
    stringBuilder.append("id=");
    String str = "***";
    if (paramBoolean1) {
      charSequence = "***";
    } else {
      charSequence = this.mId;
    } 
    stringBuilder.append((String)charSequence);
    stringBuilder.append(", flags=0x");
    stringBuilder.append(Integer.toHexString(this.mFlags));
    stringBuilder.append(" [");
    if ((this.mFlags & 0x1000) != 0)
      stringBuilder.append("Sdw"); 
    if (!isEnabled())
      stringBuilder.append("Dis"); 
    if (isImmutable())
      stringBuilder.append("Im"); 
    if (isManifestShortcut())
      stringBuilder.append("Man"); 
    if (isDynamic())
      stringBuilder.append("Dyn"); 
    if (isPinned())
      stringBuilder.append("Pin"); 
    if (hasIconFile())
      stringBuilder.append("Ic-f"); 
    if (isIconPendingSave())
      stringBuilder.append("Pens"); 
    if (hasIconResource())
      stringBuilder.append("Ic-r"); 
    if (hasIconUri())
      stringBuilder.append("Ic-u"); 
    if (hasAdaptiveBitmap())
      stringBuilder.append("Ic-a"); 
    if (hasKeyFieldsOnly())
      stringBuilder.append("Key"); 
    if (hasStringResourcesResolved())
      stringBuilder.append("Str"); 
    if (isReturnedByServer())
      stringBuilder.append("Rets"); 
    if (isLongLived())
      stringBuilder.append("Liv"); 
    stringBuilder.append("]");
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("packageName=");
    stringBuilder.append(this.mPackageName);
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("activity=");
    stringBuilder.append(this.mActivity);
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("shortLabel=");
    if (paramBoolean1) {
      charSequence = "***";
    } else {
      charSequence = this.mTitle;
    } 
    stringBuilder.append(charSequence);
    stringBuilder.append(", resId=");
    stringBuilder.append(this.mTitleResId);
    stringBuilder.append("[");
    stringBuilder.append(this.mTitleResName);
    stringBuilder.append("]");
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("longLabel=");
    if (paramBoolean1) {
      charSequence = "***";
    } else {
      charSequence = this.mText;
    } 
    stringBuilder.append(charSequence);
    stringBuilder.append(", resId=");
    stringBuilder.append(this.mTextResId);
    stringBuilder.append("[");
    stringBuilder.append(this.mTextResName);
    stringBuilder.append("]");
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("disabledMessage=");
    if (paramBoolean1) {
      charSequence = str;
    } else {
      charSequence = this.mDisabledMessage;
    } 
    stringBuilder.append(charSequence);
    stringBuilder.append(", resId=");
    stringBuilder.append(this.mDisabledMessageResId);
    stringBuilder.append("[");
    stringBuilder.append(this.mDisabledMessageResName);
    stringBuilder.append("]");
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("disabledReason=");
    stringBuilder.append(getDisabledReasonDebugString(this.mDisabledReason));
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("categories=");
    stringBuilder.append(this.mCategories);
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("persons=");
    stringBuilder.append(this.mPersons);
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("icon=");
    stringBuilder.append(this.mIcon);
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("rank=");
    stringBuilder.append(this.mRank);
    stringBuilder.append(", timestamp=");
    stringBuilder.append(this.mLastChangedTimestamp);
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("intents=");
    Intent[] arrayOfIntent = this.mIntents;
    if (arrayOfIntent == null) {
      stringBuilder.append("null");
    } else if (paramBoolean1) {
      stringBuilder.append("size:");
      stringBuilder.append(this.mIntents.length);
    } else {
      int i = arrayOfIntent.length;
      stringBuilder.append("[");
      String str1 = "";
      for (byte b = 0; b < i; b++) {
        stringBuilder.append(str1);
        str1 = ", ";
        stringBuilder.append(this.mIntents[b]);
        stringBuilder.append("/");
        stringBuilder.append(this.mIntentPersistableExtrases[b]);
      } 
      stringBuilder.append("]");
    } 
    addIndentOrComma(stringBuilder, paramString);
    stringBuilder.append("extras=");
    stringBuilder.append(this.mExtras);
    if (paramBoolean2) {
      addIndentOrComma(stringBuilder, paramString);
      stringBuilder.append("iconRes=");
      stringBuilder.append(this.mIconResId);
      stringBuilder.append("[");
      stringBuilder.append(this.mIconResName);
      stringBuilder.append("]");
      stringBuilder.append(", bitmapPath=");
      stringBuilder.append(this.mBitmapPath);
      stringBuilder.append(", iconUri=");
      stringBuilder.append(this.mIconUri);
    } 
    if (this.mLocusId != null) {
      stringBuilder.append("locusId=");
      stringBuilder.append(this.mLocusId);
    } 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public static Icon validateIcon(Icon paramIcon) {
    int i = paramIcon.getType();
    if (i == 1 || i == 2 || i == 4 || i == 5 || i == 6) {
      if (!paramIcon.hasTint())
        return paramIcon; 
      throw new IllegalArgumentException("Icons with tints are not supported");
    } 
    throw getInvalidIconException();
  }
  
  public void addFlags(int paramInt) {
    this.mFlags |= paramInt;
  }
  
  public void clearFlags(int paramInt) {
    this.mFlags &= paramInt;
  }
  
  public void clearIcon() {
    this.mIcon = null;
  }
  
  public void clearIconPendingSave() {
    clearFlags(2048);
  }
  
  public void clearImplicitRankAndRankChangedFlag() {
    this.mImplicitRank = 0;
  }
  
  public ShortcutInfo clone(int paramInt) {
    return new ShortcutInfo(this, paramInt);
  }
  
  public void copyNonNullFieldsFrom(ShortcutInfo paramShortcutInfo) {
    ensureUpdatableWith(paramShortcutInfo, true);
    ComponentName componentName = paramShortcutInfo.mActivity;
    if (componentName != null)
      this.mActivity = componentName; 
    Icon icon = paramShortcutInfo.mIcon;
    if (icon != null) {
      this.mIcon = icon;
      this.mIconResId = 0;
      this.mIconResName = null;
      this.mBitmapPath = null;
      this.mIconUri = null;
    } 
    CharSequence charSequence = paramShortcutInfo.mTitle;
    if (charSequence != null) {
      this.mTitle = charSequence;
      this.mTitleResId = 0;
      this.mTitleResName = null;
    } else {
      int j = paramShortcutInfo.mTitleResId;
      if (j != 0) {
        this.mTitle = null;
        this.mTitleResId = j;
        this.mTitleResName = null;
      } 
    } 
    charSequence = paramShortcutInfo.mText;
    if (charSequence != null) {
      this.mText = charSequence;
      this.mTextResId = 0;
      this.mTextResName = null;
    } else {
      int j = paramShortcutInfo.mTextResId;
      if (j != 0) {
        this.mText = null;
        this.mTextResId = j;
        this.mTextResName = null;
      } 
    } 
    charSequence = paramShortcutInfo.mDisabledMessage;
    if (charSequence != null) {
      this.mDisabledMessage = charSequence;
      this.mDisabledMessageResId = 0;
      this.mDisabledMessageResName = null;
    } else {
      int j = paramShortcutInfo.mDisabledMessageResId;
      if (j != 0) {
        this.mDisabledMessage = null;
        this.mDisabledMessageResId = j;
        this.mDisabledMessageResName = null;
      } 
    } 
    ArraySet<String> arraySet = paramShortcutInfo.mCategories;
    if (arraySet != null)
      this.mCategories = cloneCategories((Set<String>)arraySet); 
    Person[] arrayOfPerson = paramShortcutInfo.mPersons;
    if (arrayOfPerson != null)
      this.mPersons = clonePersons(arrayOfPerson); 
    Intent[] arrayOfIntent = paramShortcutInfo.mIntents;
    if (arrayOfIntent != null) {
      this.mIntents = cloneIntents(arrayOfIntent);
      this.mIntentPersistableExtrases = clonePersistableBundle(paramShortcutInfo.mIntentPersistableExtrases);
    } 
    int i = paramShortcutInfo.mRank;
    if (i != Integer.MAX_VALUE)
      this.mRank = i; 
    PersistableBundle persistableBundle = paramShortcutInfo.mExtras;
    if (persistableBundle != null)
      this.mExtras = persistableBundle; 
    LocusId locusId = paramShortcutInfo.mLocusId;
    if (locusId != null)
      this.mLocusId = locusId; 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void enforceMandatoryFields(boolean paramBoolean) {
    Preconditions.checkStringNotEmpty(this.mId, "Shortcut ID must be provided");
    if (!paramBoolean)
      Objects.requireNonNull(this.mActivity, "Activity must be provided"); 
    if (this.mTitle != null || this.mTitleResId != 0) {
      Objects.requireNonNull(this.mIntents, "Shortcut Intent must be provided");
      if (this.mIntents.length > 0) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      } 
      Preconditions.checkArgument(paramBoolean, "Shortcut Intent must be provided");
      return;
    } 
    throw new IllegalArgumentException("Short label must be provided");
  }
  
  public void ensureUpdatableWith(ShortcutInfo paramShortcutInfo, boolean paramBoolean) {
    if (paramBoolean)
      Preconditions.checkState(isVisibleToPublisher(), "[Framework BUG] Invisible shortcuts can't be updated"); 
    if (this.mUserId == paramShortcutInfo.mUserId) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    Preconditions.checkState(paramBoolean, "Owner User ID must match");
    Preconditions.checkState(this.mId.equals(paramShortcutInfo.mId), "ID must match");
    Preconditions.checkState(this.mPackageName.equals(paramShortcutInfo.mPackageName), "Package name must match");
    if (isVisibleToPublisher())
      Preconditions.checkState(isImmutable() ^ true, "Target ShortcutInfo is immutable"); 
  }
  
  public ComponentName getActivity() {
    return this.mActivity;
  }
  
  public String getBitmapPath() {
    return this.mBitmapPath;
  }
  
  public Set<String> getCategories() {
    return (Set<String>)this.mCategories;
  }
  
  public CharSequence getDisabledMessage() {
    return this.mDisabledMessage;
  }
  
  public String getDisabledMessageResName() {
    return this.mDisabledMessageResName;
  }
  
  public int getDisabledMessageResourceId() {
    return this.mDisabledMessageResId;
  }
  
  public int getDisabledReason() {
    return this.mDisabledReason;
  }
  
  public PersistableBundle getExtras() {
    return this.mExtras;
  }
  
  public int getFlags() {
    return this.mFlags;
  }
  
  public Icon getIcon() {
    return this.mIcon;
  }
  
  public String getIconResName() {
    return this.mIconResName;
  }
  
  public int getIconResourceId() {
    return this.mIconResId;
  }
  
  public String getIconUri() {
    return this.mIconUri;
  }
  
  public String getId() {
    return this.mId;
  }
  
  public int getImplicitRank() {
    return this.mImplicitRank & Integer.MAX_VALUE;
  }
  
  public Intent getIntent() {
    Intent[] arrayOfIntent = this.mIntents;
    if (arrayOfIntent == null || arrayOfIntent.length == 0)
      return null; 
    int i = arrayOfIntent.length - 1;
    return setIntentExtras(new Intent(this.mIntents[i]), this.mIntentPersistableExtrases[i]);
  }
  
  public PersistableBundle[] getIntentPersistableExtrases() {
    return this.mIntentPersistableExtrases;
  }
  
  public Intent[] getIntents() {
    Intent[] arrayOfIntent = new Intent[this.mIntents.length];
    for (byte b = 0; b < arrayOfIntent.length; b++) {
      arrayOfIntent[b] = new Intent(this.mIntents[b]);
      setIntentExtras(arrayOfIntent[b], this.mIntentPersistableExtrases[b]);
    } 
    return arrayOfIntent;
  }
  
  public Intent[] getIntentsNoExtras() {
    return this.mIntents;
  }
  
  public CharSequence getLabel() {
    CharSequence charSequence1 = getLongLabel();
    CharSequence charSequence2 = charSequence1;
    if (TextUtils.isEmpty(charSequence1))
      charSequence2 = getShortLabel(); 
    return charSequence2;
  }
  
  public long getLastChangedTimestamp() {
    return this.mLastChangedTimestamp;
  }
  
  public LocusId getLocusId() {
    return this.mLocusId;
  }
  
  public CharSequence getLongLabel() {
    return this.mText;
  }
  
  public int getLongLabelResourceId() {
    return this.mTextResId;
  }
  
  public String getPackage() {
    return this.mPackageName;
  }
  
  @SystemApi
  public Person[] getPersons() {
    return clonePersons(this.mPersons);
  }
  
  public int getRank() {
    return this.mRank;
  }
  
  public CharSequence getShortLabel() {
    return this.mTitle;
  }
  
  public int getShortLabelResourceId() {
    return this.mTitleResId;
  }
  
  @Deprecated
  public CharSequence getText() {
    return this.mText;
  }
  
  @Deprecated
  public int getTextResId() {
    return this.mTextResId;
  }
  
  public String getTextResName() {
    return this.mTextResName;
  }
  
  @Deprecated
  public CharSequence getTitle() {
    return this.mTitle;
  }
  
  @Deprecated
  public int getTitleResId() {
    return this.mTitleResId;
  }
  
  public String getTitleResName() {
    return this.mTitleResName;
  }
  
  public UserHandle getUserHandle() {
    return UserHandle.of(this.mUserId);
  }
  
  public int getUserId() {
    return this.mUserId;
  }
  
  public boolean hasAdaptiveBitmap() {
    return hasFlags(512);
  }
  
  public boolean hasAnyResources() {
    return (hasIconResource() || hasStringResources());
  }
  
  public boolean hasFlags(int paramInt) {
    boolean bool;
    if ((this.mFlags & paramInt) == paramInt) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasIconFile() {
    return hasFlags(8);
  }
  
  public boolean hasIconResource() {
    return hasFlags(4);
  }
  
  public boolean hasIconUri() {
    return hasFlags(32768);
  }
  
  public boolean hasKeyFieldsOnly() {
    return hasFlags(16);
  }
  
  public boolean hasRank() {
    boolean bool;
    if (this.mRank != Integer.MAX_VALUE) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasStringResources() {
    return (this.mTitleResId != 0 || this.mTextResId != 0 || this.mDisabledMessageResId != 0);
  }
  
  public boolean hasStringResourcesResolved() {
    return hasFlags(128);
  }
  
  public boolean isAlive() {
    boolean bool = hasFlags(2);
    boolean bool1 = true;
    if (!bool && !hasFlags(1) && !hasFlags(32) && !isCached())
      bool1 = false; 
    return bool1;
  }
  
  public boolean isCached() {
    boolean bool;
    if ((getFlags() & 0x40004000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDeclaredInManifest() {
    return hasFlags(32);
  }
  
  public boolean isDynamic() {
    return hasFlags(1);
  }
  
  public boolean isDynamicVisible() {
    boolean bool;
    if (isDynamic() && isVisibleToPublisher()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isEnabled() {
    return hasFlags(64) ^ true;
  }
  
  public boolean isFloating() {
    boolean bool;
    if ((isPinned() || isCached()) && !isDynamic() && !isManifestShortcut()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isIconPendingSave() {
    return hasFlags(2048);
  }
  
  public boolean isImmutable() {
    return hasFlags(256);
  }
  
  public boolean isLongLived() {
    return hasFlags(8192);
  }
  
  @Deprecated
  public boolean isManifestShortcut() {
    return isDeclaredInManifest();
  }
  
  public boolean isManifestVisible() {
    boolean bool;
    if (isDeclaredInManifest() && isVisibleToPublisher()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isNonManifestVisible() {
    boolean bool;
    if (!isDeclaredInManifest() && isVisibleToPublisher() && (isPinned() || isCached() || isDynamic())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOriginallyFromManifest() {
    return hasFlags(256);
  }
  
  public boolean isPinned() {
    return hasFlags(2);
  }
  
  public boolean isPinnedVisible() {
    boolean bool;
    if (isPinned() && isVisibleToPublisher()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRankChanged() {
    boolean bool;
    if ((this.mImplicitRank & Integer.MIN_VALUE) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isReturnedByServer() {
    return hasFlags(1024);
  }
  
  public boolean isVisibleToPublisher() {
    return isDisabledForRestoreIssue(this.mDisabledReason) ^ true;
  }
  
  public void lookupAndFillInResourceIds(Resources paramResources) {
    if (this.mTitleResName == null && this.mTextResName == null && this.mDisabledMessageResName == null && this.mIconResName == null)
      return; 
    this.mTitleResId = lookUpResourceId(paramResources, this.mTitleResName, "string", this.mPackageName);
    this.mTextResId = lookUpResourceId(paramResources, this.mTextResName, "string", this.mPackageName);
    this.mDisabledMessageResId = lookUpResourceId(paramResources, this.mDisabledMessageResName, "string", this.mPackageName);
    this.mIconResId = lookUpResourceId(paramResources, this.mIconResName, null, this.mPackageName);
  }
  
  public void lookupAndFillInResourceNames(Resources paramResources) {
    if (this.mTitleResId == 0 && this.mTextResId == 0 && this.mDisabledMessageResId == 0 && this.mIconResId == 0)
      return; 
    this.mTitleResName = lookUpResourceName(paramResources, this.mTitleResId, false, this.mPackageName);
    this.mTextResName = lookUpResourceName(paramResources, this.mTextResId, false, this.mPackageName);
    this.mDisabledMessageResName = lookUpResourceName(paramResources, this.mDisabledMessageResId, false, this.mPackageName);
    this.mIconResName = lookUpResourceName(paramResources, this.mIconResId, true, this.mPackageName);
  }
  
  public void replaceFlags(int paramInt) {
    this.mFlags = paramInt;
  }
  
  public void resolveResourceStrings(Resources paramResources) {
    this.mFlags |= 0x80;
    if (this.mTitleResId == 0 && this.mTextResId == 0 && this.mDisabledMessageResId == 0)
      return; 
    int i = this.mTitleResId;
    if (i != 0)
      this.mTitle = getResourceString(paramResources, i, this.mTitle); 
    i = this.mTextResId;
    if (i != 0)
      this.mText = getResourceString(paramResources, i, this.mText); 
    i = this.mDisabledMessageResId;
    if (i != 0)
      this.mDisabledMessage = getResourceString(paramResources, i, this.mDisabledMessage); 
  }
  
  public void setActivity(ComponentName paramComponentName) {
    this.mActivity = paramComponentName;
  }
  
  public void setBitmapPath(String paramString) {
    this.mBitmapPath = paramString;
  }
  
  public void setCached(int paramInt) {
    addFlags(paramInt);
  }
  
  public void setCategories(Set<String> paramSet) {
    this.mCategories = cloneCategories(paramSet);
  }
  
  public void setDisabledMessage(String paramString) {
    this.mDisabledMessage = paramString;
    this.mDisabledMessageResId = 0;
    this.mDisabledMessageResName = null;
  }
  
  public void setDisabledMessageResId(int paramInt) {
    if (this.mDisabledMessageResId != paramInt)
      this.mDisabledMessageResName = null; 
    this.mDisabledMessageResId = paramInt;
    this.mDisabledMessage = null;
  }
  
  public void setDisabledMessageResName(String paramString) {
    this.mDisabledMessageResName = paramString;
  }
  
  public void setDisabledReason(int paramInt) {
    this.mDisabledReason = paramInt;
  }
  
  public void setIconPendingSave() {
    addFlags(2048);
  }
  
  public void setIconResName(String paramString) {
    this.mIconResName = paramString;
  }
  
  public void setIconResourceId(int paramInt) {
    if (this.mIconResId != paramInt)
      this.mIconResName = null; 
    this.mIconResId = paramInt;
  }
  
  public void setIconUri(String paramString) {
    this.mIconUri = paramString;
  }
  
  public void setImplicitRank(int paramInt) {
    this.mImplicitRank = this.mImplicitRank & Integer.MIN_VALUE | Integer.MAX_VALUE & paramInt;
  }
  
  public void setIntents(Intent[] paramArrayOfIntent) throws IllegalArgumentException {
    boolean bool;
    Objects.requireNonNull(paramArrayOfIntent);
    if (paramArrayOfIntent.length > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    this.mIntents = cloneIntents(paramArrayOfIntent);
    fixUpIntentExtras();
  }
  
  public void setLongLived() {
    addFlags(8192);
  }
  
  public void setRank(int paramInt) {
    this.mRank = paramInt;
  }
  
  public void setRankChanged() {
    this.mImplicitRank |= Integer.MIN_VALUE;
  }
  
  public void setReturnedByServer() {
    addFlags(1024);
  }
  
  public void setTextResName(String paramString) {
    this.mTextResName = paramString;
  }
  
  public void setTimestamp(long paramLong) {
    this.mLastChangedTimestamp = paramLong;
  }
  
  public void setTitleResName(String paramString) {
    this.mTitleResName = paramString;
  }
  
  public String toDumpString(String paramString) {
    return toStringInner(false, true, paramString);
  }
  
  public String toInsecureString() {
    return toStringInner(false, true, null);
  }
  
  public String toString() {
    return toStringInner(true, false, null);
  }
  
  public void updateTimestamp() {
    this.mLastChangedTimestamp = System.currentTimeMillis();
  }
  
  public boolean usesQuota() {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (!hasFlags(1))
      if (hasFlags(32)) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    return bool2;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mUserId);
    paramParcel.writeString8(this.mId);
    paramParcel.writeString8(this.mPackageName);
    paramParcel.writeParcelable((Parcelable)this.mActivity, paramInt);
    paramParcel.writeInt(this.mFlags);
    paramParcel.writeInt(this.mIconResId);
    paramParcel.writeLong(this.mLastChangedTimestamp);
    paramParcel.writeInt(this.mDisabledReason);
    if (hasKeyFieldsOnly()) {
      paramParcel.writeInt(0);
      return;
    } 
    paramParcel.writeInt(1);
    paramParcel.writeParcelable((Parcelable)this.mIcon, paramInt);
    paramParcel.writeCharSequence(this.mTitle);
    paramParcel.writeInt(this.mTitleResId);
    paramParcel.writeCharSequence(this.mText);
    paramParcel.writeInt(this.mTextResId);
    paramParcel.writeCharSequence(this.mDisabledMessage);
    paramParcel.writeInt(this.mDisabledMessageResId);
    paramParcel.writeParcelableArray((Parcelable[])this.mIntents, paramInt);
    paramParcel.writeParcelableArray((Parcelable[])this.mIntentPersistableExtrases, paramInt);
    paramParcel.writeInt(this.mRank);
    paramParcel.writeParcelable((Parcelable)this.mExtras, paramInt);
    paramParcel.writeString8(this.mBitmapPath);
    paramParcel.writeString8(this.mIconResName);
    paramParcel.writeString8(this.mTitleResName);
    paramParcel.writeString8(this.mTextResName);
    paramParcel.writeString8(this.mDisabledMessageResName);
    ArraySet<String> arraySet = this.mCategories;
    if (arraySet != null) {
      int i = arraySet.size();
      paramParcel.writeInt(i);
      for (byte b = 0; b < i; b++)
        paramParcel.writeString8((String)this.mCategories.valueAt(b)); 
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeParcelableArray((Parcelable[])this.mPersons, paramInt);
    paramParcel.writeParcelable((Parcelable)this.mLocusId, paramInt);
    paramParcel.writeString8(this.mIconUri);
  }
  
  public static class Builder {
    private ComponentName mActivity;
    
    private Set<String> mCategories;
    
    private final Context mContext;
    
    private CharSequence mDisabledMessage;
    
    private int mDisabledMessageResId;
    
    private PersistableBundle mExtras;
    
    private Icon mIcon;
    
    private String mId;
    
    private Intent[] mIntents;
    
    private boolean mIsLongLived;
    
    private LocusId mLocusId;
    
    private Person[] mPersons;
    
    private int mRank = Integer.MAX_VALUE;
    
    private CharSequence mText;
    
    private int mTextResId;
    
    private CharSequence mTitle;
    
    private int mTitleResId;
    
    @Deprecated
    public Builder(Context param1Context) {
      this.mContext = param1Context;
    }
    
    public Builder(Context param1Context, String param1String) {
      this.mContext = param1Context;
      this.mId = (String)Preconditions.checkStringNotEmpty(param1String, "id cannot be empty");
    }
    
    public ShortcutInfo build() {
      return new ShortcutInfo(this);
    }
    
    public Builder setActivity(ComponentName param1ComponentName) {
      Objects.requireNonNull(param1ComponentName, "activity cannot be null");
      this.mActivity = param1ComponentName;
      return this;
    }
    
    public Builder setCategories(Set<String> param1Set) {
      this.mCategories = param1Set;
      return this;
    }
    
    public Builder setDisabledMessage(CharSequence param1CharSequence) {
      boolean bool;
      if (this.mDisabledMessageResId == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "disabledMessageResId already set");
      this.mDisabledMessage = Preconditions.checkStringNotEmpty(param1CharSequence, "disabledMessage cannot be empty");
      return this;
    }
    
    @Deprecated
    public Builder setDisabledMessageResId(int param1Int) {
      boolean bool;
      if (this.mDisabledMessage == null) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "disabledMessage already set");
      this.mDisabledMessageResId = param1Int;
      return this;
    }
    
    public Builder setExtras(PersistableBundle param1PersistableBundle) {
      this.mExtras = param1PersistableBundle;
      return this;
    }
    
    public Builder setIcon(Icon param1Icon) {
      this.mIcon = ShortcutInfo.validateIcon(param1Icon);
      return this;
    }
    
    @Deprecated
    public Builder setId(String param1String) {
      this.mId = (String)Preconditions.checkStringNotEmpty(param1String, "id cannot be empty");
      return this;
    }
    
    public Builder setIntent(Intent param1Intent) {
      return setIntents(new Intent[] { param1Intent });
    }
    
    public Builder setIntents(Intent[] param1ArrayOfIntent) {
      Objects.requireNonNull(param1ArrayOfIntent, "intents cannot be null");
      Objects.requireNonNull(Integer.valueOf(param1ArrayOfIntent.length), "intents cannot be empty");
      int i = param1ArrayOfIntent.length;
      for (byte b = 0; b < i; b++) {
        Intent intent = param1ArrayOfIntent[b];
        Objects.requireNonNull(intent, "intents cannot contain null");
        Objects.requireNonNull(intent.getAction(), "intent's action must be set");
      } 
      this.mIntents = ShortcutInfo.cloneIntents(param1ArrayOfIntent);
      return this;
    }
    
    public Builder setLocusId(LocusId param1LocusId) {
      Objects.requireNonNull(param1LocusId, "locusId cannot be null");
      this.mLocusId = param1LocusId;
      return this;
    }
    
    public Builder setLongLabel(CharSequence param1CharSequence) {
      boolean bool;
      if (this.mTextResId == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "longLabelResId already set");
      this.mText = Preconditions.checkStringNotEmpty(param1CharSequence, "longLabel cannot be empty");
      return this;
    }
    
    @Deprecated
    public Builder setLongLabelResId(int param1Int) {
      boolean bool;
      if (this.mText == null) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "longLabel already set");
      this.mTextResId = param1Int;
      return this;
    }
    
    public Builder setLongLived(boolean param1Boolean) {
      this.mIsLongLived = param1Boolean;
      return this;
    }
    
    public Builder setPerson(Person param1Person) {
      return setPersons(new Person[] { param1Person });
    }
    
    public Builder setPersons(Person[] param1ArrayOfPerson) {
      Objects.requireNonNull(param1ArrayOfPerson, "persons cannot be null");
      Objects.requireNonNull(Integer.valueOf(param1ArrayOfPerson.length), "persons cannot be empty");
      int i = param1ArrayOfPerson.length;
      for (byte b = 0; b < i; b++)
        Objects.requireNonNull(param1ArrayOfPerson[b], "persons cannot contain null"); 
      this.mPersons = ShortcutInfo.clonePersons(param1ArrayOfPerson);
      return this;
    }
    
    public Builder setRank(int param1Int) {
      boolean bool;
      if (param1Int >= 0) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "Rank cannot be negative or bigger than MAX_RANK");
      this.mRank = param1Int;
      return this;
    }
    
    public Builder setShortLabel(CharSequence param1CharSequence) {
      boolean bool;
      if (this.mTitleResId == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "shortLabelResId already set");
      this.mTitle = Preconditions.checkStringNotEmpty(param1CharSequence, "shortLabel cannot be empty");
      return this;
    }
    
    @Deprecated
    public Builder setShortLabelResId(int param1Int) {
      boolean bool;
      if (this.mTitle == null) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "shortLabel already set");
      this.mTitleResId = param1Int;
      return this;
    }
    
    @Deprecated
    public Builder setText(CharSequence param1CharSequence) {
      return setLongLabel(param1CharSequence);
    }
    
    @Deprecated
    public Builder setTextResId(int param1Int) {
      return setLongLabelResId(param1Int);
    }
    
    @Deprecated
    public Builder setTitle(CharSequence param1CharSequence) {
      return setShortLabel(param1CharSequence);
    }
    
    @Deprecated
    public Builder setTitleResId(int param1Int) {
      return setShortLabelResId(param1Int);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CloneFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DisabledReason {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ShortcutFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ShortcutInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
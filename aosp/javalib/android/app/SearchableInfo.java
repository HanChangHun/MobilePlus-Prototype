package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class SearchableInfo implements Parcelable {
  public static final Parcelable.Creator<SearchableInfo> CREATOR = new Parcelable.Creator<SearchableInfo>() {
      public SearchableInfo createFromParcel(Parcel param1Parcel) {
        return new SearchableInfo(param1Parcel);
      }
      
      public SearchableInfo[] newArray(int param1Int) {
        return new SearchableInfo[param1Int];
      }
    };
  
  private static final boolean DBG = false;
  
  private static final String LOG_TAG = "SearchableInfo";
  
  private static final String MD_LABEL_SEARCHABLE = "android.app.searchable";
  
  private static final String MD_XML_ELEMENT_SEARCHABLE = "searchable";
  
  private static final String MD_XML_ELEMENT_SEARCHABLE_ACTION_KEY = "actionkey";
  
  private static final int SEARCH_MODE_BADGE_ICON = 8;
  
  private static final int SEARCH_MODE_BADGE_LABEL = 4;
  
  private static final int SEARCH_MODE_QUERY_REWRITE_FROM_DATA = 16;
  
  private static final int SEARCH_MODE_QUERY_REWRITE_FROM_TEXT = 32;
  
  private static final int VOICE_SEARCH_LAUNCH_RECOGNIZER = 4;
  
  private static final int VOICE_SEARCH_LAUNCH_WEB_SEARCH = 2;
  
  private static final int VOICE_SEARCH_SHOW_BUTTON = 1;
  
  private HashMap<Integer, ActionKeyInfo> mActionKeys;
  
  private final boolean mAutoUrlDetect;
  
  private final int mHintId;
  
  private final int mIconId;
  
  private final boolean mIncludeInGlobalSearch;
  
  private final int mLabelId;
  
  private final boolean mQueryAfterZeroResults;
  
  private final ComponentName mSearchActivity;
  
  private final int mSearchButtonText;
  
  private final int mSearchImeOptions;
  
  private final int mSearchInputType;
  
  private final int mSearchMode;
  
  private final int mSettingsDescriptionId;
  
  private final String mSuggestAuthority;
  
  private final String mSuggestIntentAction;
  
  private final String mSuggestIntentData;
  
  private final String mSuggestPath;
  
  private final String mSuggestProviderPackage;
  
  private final String mSuggestSelection;
  
  private final int mSuggestThreshold;
  
  private final int mVoiceLanguageId;
  
  private final int mVoiceLanguageModeId;
  
  private final int mVoiceMaxResults;
  
  private final int mVoicePromptTextId;
  
  private final int mVoiceSearchMode;
  
  private SearchableInfo(Context paramContext, AttributeSet paramAttributeSet, ComponentName paramComponentName) {
    String str;
    this.mActionKeys = null;
    this.mSearchActivity = paramComponentName;
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Searchable);
    this.mSearchMode = typedArray.getInt(3, 0);
    this.mLabelId = typedArray.getResourceId(0, 0);
    this.mHintId = typedArray.getResourceId(2, 0);
    this.mIconId = typedArray.getResourceId(1, 0);
    this.mSearchButtonText = typedArray.getResourceId(9, 0);
    this.mSearchInputType = typedArray.getInt(10, 1);
    this.mSearchImeOptions = typedArray.getInt(16, 2);
    this.mIncludeInGlobalSearch = typedArray.getBoolean(18, false);
    this.mQueryAfterZeroResults = typedArray.getBoolean(19, false);
    this.mAutoUrlDetect = typedArray.getBoolean(21, false);
    this.mSettingsDescriptionId = typedArray.getResourceId(20, 0);
    this.mSuggestAuthority = typedArray.getString(4);
    this.mSuggestPath = typedArray.getString(5);
    this.mSuggestSelection = typedArray.getString(6);
    this.mSuggestIntentAction = typedArray.getString(7);
    this.mSuggestIntentData = typedArray.getString(8);
    this.mSuggestThreshold = typedArray.getInt(17, 0);
    this.mVoiceSearchMode = typedArray.getInt(11, 0);
    this.mVoiceLanguageModeId = typedArray.getResourceId(12, 0);
    this.mVoicePromptTextId = typedArray.getResourceId(13, 0);
    this.mVoiceLanguageId = typedArray.getResourceId(14, 0);
    this.mVoiceMaxResults = typedArray.getInt(15, 0);
    typedArray.recycle();
    paramComponentName = null;
    ComponentName componentName = paramComponentName;
    if (this.mSuggestAuthority != null) {
      ProviderInfo providerInfo = paramContext.getPackageManager().resolveContentProvider(this.mSuggestAuthority, 268435456);
      componentName = paramComponentName;
      if (providerInfo != null)
        str = providerInfo.packageName; 
    } 
    this.mSuggestProviderPackage = str;
    if (this.mLabelId != 0)
      return; 
    throw new IllegalArgumentException("Search label must be a resource reference.");
  }
  
  SearchableInfo(Parcel paramParcel) {
    boolean bool2;
    this.mActionKeys = null;
    this.mLabelId = paramParcel.readInt();
    this.mSearchActivity = ComponentName.readFromParcel(paramParcel);
    this.mHintId = paramParcel.readInt();
    this.mSearchMode = paramParcel.readInt();
    this.mIconId = paramParcel.readInt();
    this.mSearchButtonText = paramParcel.readInt();
    this.mSearchInputType = paramParcel.readInt();
    this.mSearchImeOptions = paramParcel.readInt();
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIncludeInGlobalSearch = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mQueryAfterZeroResults = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mAutoUrlDetect = bool2;
    this.mSettingsDescriptionId = paramParcel.readInt();
    this.mSuggestAuthority = paramParcel.readString();
    this.mSuggestPath = paramParcel.readString();
    this.mSuggestSelection = paramParcel.readString();
    this.mSuggestIntentAction = paramParcel.readString();
    this.mSuggestIntentData = paramParcel.readString();
    this.mSuggestThreshold = paramParcel.readInt();
    for (i = paramParcel.readInt(); i > 0; i--)
      addActionKey(new ActionKeyInfo(paramParcel)); 
    this.mSuggestProviderPackage = paramParcel.readString();
    this.mVoiceSearchMode = paramParcel.readInt();
    this.mVoiceLanguageModeId = paramParcel.readInt();
    this.mVoicePromptTextId = paramParcel.readInt();
    this.mVoiceLanguageId = paramParcel.readInt();
    this.mVoiceMaxResults = paramParcel.readInt();
  }
  
  private void addActionKey(ActionKeyInfo paramActionKeyInfo) {
    if (this.mActionKeys == null)
      this.mActionKeys = new HashMap<>(); 
    this.mActionKeys.put(Integer.valueOf(paramActionKeyInfo.getKeyCode()), paramActionKeyInfo);
  }
  
  private static Context createActivityContext(Context paramContext, ComponentName paramComponentName) {
    StringBuilder stringBuilder1;
    StringBuilder stringBuilder2 = null;
    StringBuilder stringBuilder3 = null;
    try {
      paramContext = paramContext.createPackageContext(paramComponentName.getPackageName(), 0);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Package not found ");
      stringBuilder1.append(paramComponentName.getPackageName());
      Log.e("SearchableInfo", stringBuilder1.toString());
      stringBuilder1 = stringBuilder3;
    } catch (SecurityException securityException) {
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Can't make context for ");
      stringBuilder1.append(paramComponentName.getPackageName());
      Log.e("SearchableInfo", stringBuilder1.toString(), securityException);
      stringBuilder1 = stringBuilder2;
    } 
    return (Context)stringBuilder1;
  }
  
  public static SearchableInfo getActivityMetaData(Context paramContext, ActivityInfo paramActivityInfo, int paramInt) {
    try {
      UserHandle userHandle = new UserHandle();
      this(paramInt);
      Context context = paramContext.createPackageContextAsUser("system", 0, userHandle);
      XmlResourceParser xmlResourceParser = paramActivityInfo.loadXmlMetaData(context.getPackageManager(), "android.app.searchable");
      if (xmlResourceParser == null)
        return null; 
      SearchableInfo searchableInfo = getActivityMetaData(context, (XmlPullParser)xmlResourceParser, new ComponentName(paramActivityInfo.packageName, paramActivityInfo.name));
      xmlResourceParser.close();
      return searchableInfo;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Couldn't create package context for user ");
      stringBuilder.append(paramInt);
      Log.e("SearchableInfo", stringBuilder.toString());
      return null;
    } 
  }
  
  private static SearchableInfo getActivityMetaData(Context paramContext, XmlPullParser paramXmlPullParser, ComponentName paramComponentName) {
    Context context1 = null;
    Context context2 = createActivityContext(paramContext, paramComponentName);
    if (context2 == null)
      return null; 
    try {
      StringBuilder stringBuilder;
      int i = paramXmlPullParser.next();
      paramContext = context1;
      while (i != 1) {
        StringBuilder stringBuilder1;
        StringBuilder stringBuilder2;
        context1 = paramContext;
        if (i == 2) {
          StringBuilder stringBuilder3;
          boolean bool = paramXmlPullParser.getName().equals("searchable");
          if (bool) {
            AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
            context1 = paramContext;
            if (attributeSet != null)
              try {
                SearchableInfo searchableInfo = new SearchableInfo();
                this(context2, attributeSet, paramComponentName);
              } catch (IllegalArgumentException illegalArgumentException) {
                stringBuilder3 = new StringBuilder();
                this();
                stringBuilder3.append("Invalid searchable metadata for ");
                stringBuilder3.append(paramComponentName.flattenToShortString());
                stringBuilder3.append(": ");
                stringBuilder3.append(illegalArgumentException.getMessage());
                Log.w("SearchableInfo", stringBuilder3.toString());
                return null;
              }  
          } else {
            stringBuilder2 = stringBuilder3;
            if (illegalArgumentException.getName().equals("actionkey")) {
              if (stringBuilder3 == null)
                return null; 
              AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)illegalArgumentException);
              stringBuilder2 = stringBuilder3;
              if (attributeSet != null)
                try {
                  ActionKeyInfo actionKeyInfo = new ActionKeyInfo();
                  this(context2, attributeSet);
                  stringBuilder3.addActionKey(actionKeyInfo);
                  stringBuilder2 = stringBuilder3;
                } catch (IllegalArgumentException illegalArgumentException1) {
                  stringBuilder1 = new StringBuilder();
                  this();
                  stringBuilder1.append("Invalid action key for ");
                  stringBuilder1.append(paramComponentName.flattenToShortString());
                  stringBuilder1.append(": ");
                  stringBuilder1.append(illegalArgumentException1.getMessage());
                  Log.w("SearchableInfo", stringBuilder1.toString());
                  return null;
                }  
            } 
          } 
        } 
        i = stringBuilder1.next();
        stringBuilder = stringBuilder2;
      } 
      return (SearchableInfo)stringBuilder;
    } catch (XmlPullParserException xmlPullParserException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Reading searchable metadata for ");
      stringBuilder.append(paramComponentName.flattenToShortString());
      Log.w("SearchableInfo", stringBuilder.toString(), (Throwable)xmlPullParserException);
      return null;
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Reading searchable metadata for ");
      stringBuilder.append(paramComponentName.flattenToShortString());
      Log.w("SearchableInfo", stringBuilder.toString(), iOException);
      return null;
    } 
  }
  
  public boolean autoUrlDetect() {
    return this.mAutoUrlDetect;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public ActionKeyInfo findActionKey(int paramInt) {
    HashMap<Integer, ActionKeyInfo> hashMap = this.mActionKeys;
    return (hashMap == null) ? null : hashMap.get(Integer.valueOf(paramInt));
  }
  
  public Context getActivityContext(Context paramContext) {
    return createActivityContext(paramContext, this.mSearchActivity);
  }
  
  public int getHintId() {
    return this.mHintId;
  }
  
  public int getIconId() {
    return this.mIconId;
  }
  
  public int getImeOptions() {
    return this.mSearchImeOptions;
  }
  
  public int getInputType() {
    return this.mSearchInputType;
  }
  
  public int getLabelId() {
    return this.mLabelId;
  }
  
  public Context getProviderContext(Context paramContext1, Context paramContext2) {
    Context context = null;
    PackageManager.NameNotFoundException nameNotFoundException = null;
    if (this.mSearchActivity.getPackageName().equals(this.mSuggestProviderPackage))
      return paramContext2; 
    String str = this.mSuggestProviderPackage;
    paramContext2 = context;
    if (str != null)
      try {
        paramContext1 = paramContext1.createPackageContext(str, 0);
        paramContext2 = paramContext1;
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException1) {
        nameNotFoundException1 = nameNotFoundException;
        PackageManager.NameNotFoundException nameNotFoundException2 = nameNotFoundException1;
      } catch (SecurityException securityException) {
        paramContext2 = context;
      }  
    return paramContext2;
  }
  
  public ComponentName getSearchActivity() {
    return this.mSearchActivity;
  }
  
  public int getSearchButtonText() {
    return this.mSearchButtonText;
  }
  
  public int getSettingsDescriptionId() {
    return this.mSettingsDescriptionId;
  }
  
  public String getSuggestAuthority() {
    return this.mSuggestAuthority;
  }
  
  public String getSuggestIntentAction() {
    return this.mSuggestIntentAction;
  }
  
  public String getSuggestIntentData() {
    return this.mSuggestIntentData;
  }
  
  public String getSuggestPackage() {
    return this.mSuggestProviderPackage;
  }
  
  public String getSuggestPath() {
    return this.mSuggestPath;
  }
  
  public String getSuggestSelection() {
    return this.mSuggestSelection;
  }
  
  public int getSuggestThreshold() {
    return this.mSuggestThreshold;
  }
  
  public int getVoiceLanguageId() {
    return this.mVoiceLanguageId;
  }
  
  public int getVoiceLanguageModeId() {
    return this.mVoiceLanguageModeId;
  }
  
  public int getVoiceMaxResults() {
    return this.mVoiceMaxResults;
  }
  
  public int getVoicePromptTextId() {
    return this.mVoicePromptTextId;
  }
  
  public boolean getVoiceSearchEnabled() {
    int i = this.mVoiceSearchMode;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean getVoiceSearchLaunchRecognizer() {
    boolean bool;
    if ((this.mVoiceSearchMode & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean getVoiceSearchLaunchWebSearch() {
    boolean bool;
    if ((this.mVoiceSearchMode & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean queryAfterZeroResults() {
    return this.mQueryAfterZeroResults;
  }
  
  public boolean shouldIncludeInGlobalSearch() {
    return this.mIncludeInGlobalSearch;
  }
  
  public boolean shouldRewriteQueryFromData() {
    boolean bool;
    if ((this.mSearchMode & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean shouldRewriteQueryFromText() {
    boolean bool;
    if ((this.mSearchMode & 0x20) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean useBadgeIcon() {
    boolean bool;
    if ((this.mSearchMode & 0x8) != 0 && this.mIconId != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean useBadgeLabel() {
    boolean bool;
    if ((this.mSearchMode & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mLabelId);
    this.mSearchActivity.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.mHintId);
    paramParcel.writeInt(this.mSearchMode);
    paramParcel.writeInt(this.mIconId);
    paramParcel.writeInt(this.mSearchButtonText);
    paramParcel.writeInt(this.mSearchInputType);
    paramParcel.writeInt(this.mSearchImeOptions);
    paramParcel.writeInt(this.mIncludeInGlobalSearch);
    paramParcel.writeInt(this.mQueryAfterZeroResults);
    paramParcel.writeInt(this.mAutoUrlDetect);
    paramParcel.writeInt(this.mSettingsDescriptionId);
    paramParcel.writeString(this.mSuggestAuthority);
    paramParcel.writeString(this.mSuggestPath);
    paramParcel.writeString(this.mSuggestSelection);
    paramParcel.writeString(this.mSuggestIntentAction);
    paramParcel.writeString(this.mSuggestIntentData);
    paramParcel.writeInt(this.mSuggestThreshold);
    HashMap<Integer, ActionKeyInfo> hashMap = this.mActionKeys;
    if (hashMap == null) {
      paramParcel.writeInt(0);
    } else {
      paramParcel.writeInt(hashMap.size());
      Iterator<ActionKeyInfo> iterator = this.mActionKeys.values().iterator();
      while (iterator.hasNext())
        ((ActionKeyInfo)iterator.next()).writeToParcel(paramParcel, paramInt); 
    } 
    paramParcel.writeString(this.mSuggestProviderPackage);
    paramParcel.writeInt(this.mVoiceSearchMode);
    paramParcel.writeInt(this.mVoiceLanguageModeId);
    paramParcel.writeInt(this.mVoicePromptTextId);
    paramParcel.writeInt(this.mVoiceLanguageId);
    paramParcel.writeInt(this.mVoiceMaxResults);
  }
  
  public static class ActionKeyInfo implements Parcelable {
    private final int mKeyCode;
    
    private final String mQueryActionMsg;
    
    private final String mSuggestActionMsg;
    
    private final String mSuggestActionMsgColumn;
    
    ActionKeyInfo(Context param1Context, AttributeSet param1AttributeSet) {
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.SearchableActionKey);
      this.mKeyCode = typedArray.getInt(0, 0);
      this.mQueryActionMsg = typedArray.getString(1);
      this.mSuggestActionMsg = typedArray.getString(2);
      this.mSuggestActionMsgColumn = typedArray.getString(3);
      typedArray.recycle();
      if (this.mKeyCode != 0) {
        if (this.mQueryActionMsg != null || this.mSuggestActionMsg != null || this.mSuggestActionMsgColumn != null)
          return; 
        throw new IllegalArgumentException("No message information.");
      } 
      throw new IllegalArgumentException("No keycode.");
    }
    
    private ActionKeyInfo(Parcel param1Parcel) {
      this.mKeyCode = param1Parcel.readInt();
      this.mQueryActionMsg = param1Parcel.readString();
      this.mSuggestActionMsg = param1Parcel.readString();
      this.mSuggestActionMsgColumn = param1Parcel.readString();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public int getKeyCode() {
      return this.mKeyCode;
    }
    
    public String getQueryActionMsg() {
      return this.mQueryActionMsg;
    }
    
    public String getSuggestActionMsg() {
      return this.mSuggestActionMsg;
    }
    
    public String getSuggestActionMsgColumn() {
      return this.mSuggestActionMsgColumn;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mKeyCode);
      param1Parcel.writeString(this.mQueryActionMsg);
      param1Parcel.writeString(this.mSuggestActionMsg);
      param1Parcel.writeString(this.mSuggestActionMsgColumn);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SearchableInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
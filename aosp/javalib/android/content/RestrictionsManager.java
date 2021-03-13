package android.content;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.android.internal.R;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RestrictionsManager {
  public static final String ACTION_PERMISSION_RESPONSE_RECEIVED = "android.content.action.PERMISSION_RESPONSE_RECEIVED";
  
  public static final String ACTION_REQUEST_LOCAL_APPROVAL = "android.content.action.REQUEST_LOCAL_APPROVAL";
  
  public static final String ACTION_REQUEST_PERMISSION = "android.content.action.REQUEST_PERMISSION";
  
  public static final String EXTRA_PACKAGE_NAME = "android.content.extra.PACKAGE_NAME";
  
  public static final String EXTRA_REQUEST_BUNDLE = "android.content.extra.REQUEST_BUNDLE";
  
  public static final String EXTRA_REQUEST_ID = "android.content.extra.REQUEST_ID";
  
  public static final String EXTRA_REQUEST_TYPE = "android.content.extra.REQUEST_TYPE";
  
  public static final String EXTRA_RESPONSE_BUNDLE = "android.content.extra.RESPONSE_BUNDLE";
  
  public static final String META_DATA_APP_RESTRICTIONS = "android.content.APP_RESTRICTIONS";
  
  public static final String REQUEST_KEY_APPROVE_LABEL = "android.request.approve_label";
  
  public static final String REQUEST_KEY_DATA = "android.request.data";
  
  public static final String REQUEST_KEY_DENY_LABEL = "android.request.deny_label";
  
  public static final String REQUEST_KEY_ICON = "android.request.icon";
  
  public static final String REQUEST_KEY_ID = "android.request.id";
  
  public static final String REQUEST_KEY_MESSAGE = "android.request.mesg";
  
  public static final String REQUEST_KEY_NEW_REQUEST = "android.request.new_request";
  
  public static final String REQUEST_KEY_TITLE = "android.request.title";
  
  public static final String REQUEST_TYPE_APPROVAL = "android.request.type.approval";
  
  public static final String RESPONSE_KEY_ERROR_CODE = "android.response.errorcode";
  
  public static final String RESPONSE_KEY_MESSAGE = "android.response.msg";
  
  public static final String RESPONSE_KEY_RESPONSE_TIMESTAMP = "android.response.timestamp";
  
  public static final String RESPONSE_KEY_RESULT = "android.response.result";
  
  public static final int RESULT_APPROVED = 1;
  
  public static final int RESULT_DENIED = 2;
  
  public static final int RESULT_ERROR = 5;
  
  public static final int RESULT_ERROR_BAD_REQUEST = 1;
  
  public static final int RESULT_ERROR_INTERNAL = 3;
  
  public static final int RESULT_ERROR_NETWORK = 2;
  
  public static final int RESULT_NO_RESPONSE = 3;
  
  public static final int RESULT_UNKNOWN_REQUEST = 4;
  
  private static final String TAG = "RestrictionsManager";
  
  private static final String TAG_RESTRICTION = "restriction";
  
  private final Context mContext;
  
  private final IRestrictionsManager mService;
  
  public RestrictionsManager(Context paramContext, IRestrictionsManager paramIRestrictionsManager) {
    this.mContext = paramContext;
    this.mService = paramIRestrictionsManager;
  }
  
  private static Bundle addRestrictionToBundle(Bundle paramBundle, RestrictionEntry paramRestrictionEntry) {
    StringBuilder stringBuilder;
    RestrictionEntry[] arrayOfRestrictionEntry;
    Bundle bundle;
    Bundle[] arrayOfBundle;
    byte b;
    switch (paramRestrictionEntry.getType()) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported restrictionEntry type: ");
        stringBuilder.append(paramRestrictionEntry.getType());
        throw new IllegalArgumentException(stringBuilder.toString());
      case 8:
        arrayOfRestrictionEntry = paramRestrictionEntry.getRestrictions();
        arrayOfBundle = new Bundle[arrayOfRestrictionEntry.length];
        for (b = 0; b < arrayOfRestrictionEntry.length; b++) {
          RestrictionEntry[] arrayOfRestrictionEntry1 = arrayOfRestrictionEntry[b].getRestrictions();
          if (arrayOfRestrictionEntry1 == null) {
            Log.w("RestrictionsManager", "addRestrictionToBundle: Non-bundle entry found in bundle array");
            arrayOfBundle[b] = new Bundle();
          } else {
            arrayOfBundle[b] = convertRestrictionsToBundle(Arrays.asList(arrayOfRestrictionEntry1));
          } 
        } 
        stringBuilder.putParcelableArray(paramRestrictionEntry.getKey(), (Parcelable[])arrayOfBundle);
        return (Bundle)stringBuilder;
      case 7:
        bundle = convertRestrictionsToBundle(Arrays.asList(paramRestrictionEntry.getRestrictions()));
        stringBuilder.putBundle(paramRestrictionEntry.getKey(), bundle);
        return (Bundle)stringBuilder;
      case 5:
        stringBuilder.putInt(paramRestrictionEntry.getKey(), paramRestrictionEntry.getIntValue());
        return (Bundle)stringBuilder;
      case 2:
      case 3:
      case 4:
        stringBuilder.putStringArray(paramRestrictionEntry.getKey(), paramRestrictionEntry.getAllSelectedStrings());
        return (Bundle)stringBuilder;
      case 1:
        stringBuilder.putBoolean(paramRestrictionEntry.getKey(), paramRestrictionEntry.getSelectedState());
        return (Bundle)stringBuilder;
      case 0:
      case 6:
        break;
    } 
    stringBuilder.putString(paramRestrictionEntry.getKey(), paramRestrictionEntry.getSelectedString());
    return (Bundle)stringBuilder;
  }
  
  public static Bundle convertRestrictionsToBundle(List<RestrictionEntry> paramList) {
    Bundle bundle = new Bundle();
    Iterator<RestrictionEntry> iterator = paramList.iterator();
    while (iterator.hasNext())
      addRestrictionToBundle(bundle, iterator.next()); 
    return bundle;
  }
  
  private List<RestrictionEntry> loadManifestRestrictions(String paramString, XmlResourceParser paramXmlResourceParser) {
    try {
      Context context = this.mContext.createPackageContext(paramString, 0);
      ArrayList<RestrictionEntry> arrayList = new ArrayList();
      try {
        int i;
        for (i = paramXmlResourceParser.next(); i != 1; i = paramXmlResourceParser.next()) {
          if (i == 2) {
            RestrictionEntry restrictionEntry = loadRestrictionElement(context, paramXmlResourceParser);
            if (restrictionEntry != null)
              arrayList.add(restrictionEntry); 
          } 
        } 
        return arrayList;
      } catch (XmlPullParserException xmlPullParserException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Reading restriction metadata for ");
        stringBuilder.append(paramString);
        Log.w("RestrictionsManager", stringBuilder.toString(), (Throwable)xmlPullParserException);
        return null;
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Reading restriction metadata for ");
        stringBuilder.append(paramString);
        Log.w("RestrictionsManager", stringBuilder.toString(), iOException);
        return null;
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  private RestrictionEntry loadRestriction(Context paramContext, TypedArray paramTypedArray, XmlResourceParser paramXmlResourceParser) throws IOException, XmlPullParserException {
    StringBuilder stringBuilder;
    ArrayList<StringBuilder> arrayList;
    Context context = paramContext;
    String str1 = paramTypedArray.getString(3);
    int i = paramTypedArray.getInt(6, -1);
    String str2 = paramTypedArray.getString(2);
    String str3 = paramTypedArray.getString(0);
    int j = paramTypedArray.getResourceId(1, 0);
    int k = paramTypedArray.getResourceId(5, 0);
    if (i == -1) {
      Log.w("RestrictionsManager", "restrictionType cannot be omitted");
      return null;
    } 
    if (str1 == null) {
      Log.w("RestrictionsManager", "key cannot be omitted");
      return null;
    } 
    RestrictionEntry restrictionEntry = new RestrictionEntry(i, str1);
    restrictionEntry.setTitle(str2);
    restrictionEntry.setDescription(str3);
    if (j != 0)
      restrictionEntry.setChoiceEntries(context, j); 
    if (k != 0)
      restrictionEntry.setChoiceValues(context, k); 
    switch (i) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown restriction type ");
        stringBuilder.append(i);
        Log.w("RestrictionsManager", stringBuilder.toString());
        return restrictionEntry;
      case 7:
      case 8:
        j = paramXmlResourceParser.getDepth();
        arrayList = new ArrayList();
        while (XmlUtils.nextElementWithin((XmlPullParser)paramXmlResourceParser, j)) {
          StringBuilder stringBuilder1;
          RestrictionEntry restrictionEntry1 = loadRestrictionElement((Context)stringBuilder, paramXmlResourceParser);
          if (restrictionEntry1 == null) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Child entry cannot be loaded for bundle restriction ");
            stringBuilder1.append(str1);
            Log.w("RestrictionsManager", stringBuilder1.toString());
            continue;
          } 
          arrayList.add(stringBuilder1);
          if (i == 8 && stringBuilder1.getType() != 7) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("bundle_array ");
            stringBuilder1.append(str1);
            stringBuilder1.append(" can only contain entries of type bundle");
            Log.w("RestrictionsManager", stringBuilder1.toString());
          } 
        } 
        restrictionEntry.setRestrictions(arrayList.<RestrictionEntry>toArray(new RestrictionEntry[arrayList.size()]));
        return restrictionEntry;
      case 5:
        restrictionEntry.setIntValue(arrayList.getInt(4, 0));
        return restrictionEntry;
      case 4:
        i = arrayList.getResourceId(4, 0);
        if (i != 0)
          restrictionEntry.setAllSelectedStrings(stringBuilder.getResources().getStringArray(i)); 
        return restrictionEntry;
      case 1:
        restrictionEntry.setSelectedState(arrayList.getBoolean(4, false));
        return restrictionEntry;
      case 0:
      case 2:
      case 6:
        break;
    } 
    restrictionEntry.setSelectedString(arrayList.getString(4));
    return restrictionEntry;
  }
  
  private RestrictionEntry loadRestrictionElement(Context paramContext, XmlResourceParser paramXmlResourceParser) throws IOException, XmlPullParserException {
    if (paramXmlResourceParser.getName().equals("restriction")) {
      AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)paramXmlResourceParser);
      if (attributeSet != null)
        return loadRestriction(paramContext, paramContext.obtainStyledAttributes(attributeSet, R.styleable.RestrictionEntry), paramXmlResourceParser); 
    } 
    return null;
  }
  
  public Intent createLocalApprovalIntent() {
    try {
      return (this.mService != null) ? this.mService.createLocalApprovalIntent() : null;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Bundle getApplicationRestrictions() {
    try {
      return (this.mService != null) ? this.mService.getApplicationRestrictions(this.mContext.getPackageName()) : null;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<RestrictionEntry> getManifestRestrictions(String paramString) {
    try {
      ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo(paramString, 128);
      return (applicationInfo == null || !applicationInfo.metaData.containsKey("android.content.APP_RESTRICTIONS")) ? null : loadManifestRestrictions(paramString, applicationInfo.loadXmlMetaData(this.mContext.getPackageManager(), "android.content.APP_RESTRICTIONS"));
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("No such package ");
      stringBuilder.append(paramString);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
  }
  
  public boolean hasRestrictionsProvider() {
    try {
      return (this.mService != null) ? this.mService.hasRestrictionsProvider() : false;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void notifyPermissionResponse(String paramString, PersistableBundle paramPersistableBundle) {
    if (paramString != null) {
      if (paramPersistableBundle != null) {
        if (paramPersistableBundle.containsKey("android.request.id")) {
          if (paramPersistableBundle.containsKey("android.response.result"))
            try {
              if (this.mService != null)
                this.mService.notifyPermissionResponse(paramString, paramPersistableBundle); 
              return;
            } catch (RemoteException remoteException) {
              throw remoteException.rethrowFromSystemServer();
            }  
          throw new IllegalArgumentException("RESPONSE_KEY_RESULT must be specified");
        } 
        throw new IllegalArgumentException("REQUEST_KEY_ID must be specified");
      } 
      throw new NullPointerException("request cannot be null");
    } 
    throw new NullPointerException("packageName cannot be null");
  }
  
  public void requestPermission(String paramString1, String paramString2, PersistableBundle paramPersistableBundle) {
    if (paramString1 != null) {
      if (paramString2 != null) {
        if (paramPersistableBundle != null)
          try {
            if (this.mService != null)
              this.mService.requestPermission(this.mContext.getPackageName(), paramString1, paramString2, paramPersistableBundle); 
            return;
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          }  
        throw new NullPointerException("request cannot be null");
      } 
      throw new NullPointerException("requestId cannot be null");
    } 
    throw new NullPointerException("requestType cannot be null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/RestrictionsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
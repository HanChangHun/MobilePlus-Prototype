package android.content;

import android.annotation.SystemApi;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.util.AndroidException;
import android.util.Log;
import android.util.Printer;
import android.util.proto.ProtoOutputStream;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class IntentFilter implements Parcelable {
  private static final String ACTION_STR = "action";
  
  private static final String AGLOB_STR = "aglob";
  
  private static final String AUTH_STR = "auth";
  
  private static final String AUTO_VERIFY_STR = "autoVerify";
  
  private static final String CAT_STR = "cat";
  
  public static final Parcelable.Creator<IntentFilter> CREATOR = new Parcelable.Creator<IntentFilter>() {
      public IntentFilter createFromParcel(Parcel param1Parcel) {
        return new IntentFilter(param1Parcel);
      }
      
      public IntentFilter[] newArray(int param1Int) {
        return new IntentFilter[param1Int];
      }
    };
  
  private static final String GROUP_STR = "group";
  
  private static final String HOST_STR = "host";
  
  private static final String LITERAL_STR = "literal";
  
  public static final int MATCH_ADJUSTMENT_MASK = 65535;
  
  public static final int MATCH_ADJUSTMENT_NORMAL = 32768;
  
  public static final int MATCH_CATEGORY_EMPTY = 1048576;
  
  public static final int MATCH_CATEGORY_HOST = 3145728;
  
  public static final int MATCH_CATEGORY_MASK = 268369920;
  
  public static final int MATCH_CATEGORY_PATH = 5242880;
  
  public static final int MATCH_CATEGORY_PORT = 4194304;
  
  public static final int MATCH_CATEGORY_SCHEME = 2097152;
  
  public static final int MATCH_CATEGORY_SCHEME_SPECIFIC_PART = 5767168;
  
  public static final int MATCH_CATEGORY_TYPE = 6291456;
  
  private static final String NAME_STR = "name";
  
  public static final int NO_MATCH_ACTION = -3;
  
  public static final int NO_MATCH_CATEGORY = -4;
  
  public static final int NO_MATCH_DATA = -2;
  
  public static final int NO_MATCH_TYPE = -1;
  
  private static final String PATH_STR = "path";
  
  private static final String PORT_STR = "port";
  
  private static final String PREFIX_STR = "prefix";
  
  public static final String SCHEME_HTTP = "http";
  
  public static final String SCHEME_HTTPS = "https";
  
  private static final String SCHEME_STR = "scheme";
  
  private static final String SGLOB_STR = "sglob";
  
  private static final String SSP_STR = "ssp";
  
  private static final int STATE_NEED_VERIFY = 16;
  
  private static final int STATE_NEED_VERIFY_CHECKED = 256;
  
  private static final int STATE_VERIFIED = 4096;
  
  private static final int STATE_VERIFY_AUTO = 1;
  
  private static final String STATIC_TYPE_STR = "staticType";
  
  public static final int SYSTEM_HIGH_PRIORITY = 1000;
  
  public static final int SYSTEM_LOW_PRIORITY = -1000;
  
  private static final String TYPE_STR = "type";
  
  public static final int VISIBILITY_EXPLICIT = 1;
  
  public static final int VISIBILITY_IMPLICIT = 2;
  
  public static final int VISIBILITY_NONE = 0;
  
  public static final String WILDCARD = "*";
  
  public static final String WILDCARD_PATH = "/*";
  
  private final ArrayList<String> mActions;
  
  private ArrayList<String> mCategories = null;
  
  private ArrayList<AuthorityEntry> mDataAuthorities = null;
  
  private ArrayList<PatternMatcher> mDataPaths = null;
  
  private ArrayList<PatternMatcher> mDataSchemeSpecificParts = null;
  
  private ArrayList<String> mDataSchemes = null;
  
  private ArrayList<String> mDataTypes = null;
  
  private boolean mHasDynamicPartialTypes;
  
  private boolean mHasStaticPartialTypes;
  
  private int mInstantAppVisibility;
  
  private ArrayList<String> mMimeGroups = null;
  
  private int mOrder;
  
  private int mPriority;
  
  private ArrayList<String> mStaticDataTypes = null;
  
  private int mVerifyState;
  
  public IntentFilter() {
    this.mHasStaticPartialTypes = false;
    this.mHasDynamicPartialTypes = false;
    this.mPriority = 0;
    this.mActions = new ArrayList<>();
  }
  
  public IntentFilter(IntentFilter paramIntentFilter) {
    this.mHasStaticPartialTypes = false;
    this.mHasDynamicPartialTypes = false;
    this.mPriority = paramIntentFilter.mPriority;
    this.mOrder = paramIntentFilter.mOrder;
    this.mActions = new ArrayList<>(paramIntentFilter.mActions);
    if (paramIntentFilter.mCategories != null)
      this.mCategories = new ArrayList<>(paramIntentFilter.mCategories); 
    if (paramIntentFilter.mStaticDataTypes != null)
      this.mStaticDataTypes = new ArrayList<>(paramIntentFilter.mStaticDataTypes); 
    if (paramIntentFilter.mDataTypes != null)
      this.mDataTypes = new ArrayList<>(paramIntentFilter.mDataTypes); 
    if (paramIntentFilter.mDataSchemes != null)
      this.mDataSchemes = new ArrayList<>(paramIntentFilter.mDataSchemes); 
    if (paramIntentFilter.mDataSchemeSpecificParts != null)
      this.mDataSchemeSpecificParts = new ArrayList<>(paramIntentFilter.mDataSchemeSpecificParts); 
    if (paramIntentFilter.mDataAuthorities != null)
      this.mDataAuthorities = new ArrayList<>(paramIntentFilter.mDataAuthorities); 
    if (paramIntentFilter.mDataPaths != null)
      this.mDataPaths = new ArrayList<>(paramIntentFilter.mDataPaths); 
    if (paramIntentFilter.mMimeGroups != null)
      this.mMimeGroups = new ArrayList<>(paramIntentFilter.mMimeGroups); 
    this.mHasStaticPartialTypes = paramIntentFilter.mHasStaticPartialTypes;
    this.mHasDynamicPartialTypes = paramIntentFilter.mHasDynamicPartialTypes;
    this.mVerifyState = paramIntentFilter.mVerifyState;
    this.mInstantAppVisibility = paramIntentFilter.mInstantAppVisibility;
  }
  
  public IntentFilter(Parcel paramParcel) {
    boolean bool1 = false;
    this.mHasStaticPartialTypes = false;
    this.mHasDynamicPartialTypes = false;
    ArrayList<String> arrayList = new ArrayList();
    this.mActions = arrayList;
    paramParcel.readStringList(arrayList);
    if (paramParcel.readInt() != 0) {
      arrayList = new ArrayList<>();
      this.mCategories = arrayList;
      paramParcel.readStringList(arrayList);
    } 
    if (paramParcel.readInt() != 0) {
      arrayList = new ArrayList<>();
      this.mDataSchemes = arrayList;
      paramParcel.readStringList(arrayList);
    } 
    if (paramParcel.readInt() != 0) {
      arrayList = new ArrayList<>();
      this.mStaticDataTypes = arrayList;
      paramParcel.readStringList(arrayList);
    } 
    if (paramParcel.readInt() != 0) {
      arrayList = new ArrayList<>();
      this.mDataTypes = arrayList;
      paramParcel.readStringList(arrayList);
    } 
    if (paramParcel.readInt() != 0) {
      arrayList = new ArrayList<>();
      this.mMimeGroups = arrayList;
      paramParcel.readStringList(arrayList);
    } 
    int i = paramParcel.readInt();
    if (i > 0) {
      this.mDataSchemeSpecificParts = new ArrayList<>(i);
      for (byte b = 0; b < i; b++)
        this.mDataSchemeSpecificParts.add(new PatternMatcher(paramParcel)); 
    } 
    i = paramParcel.readInt();
    if (i > 0) {
      this.mDataAuthorities = new ArrayList<>(i);
      for (byte b = 0; b < i; b++)
        this.mDataAuthorities.add(new AuthorityEntry(paramParcel)); 
    } 
    i = paramParcel.readInt();
    if (i > 0) {
      this.mDataPaths = new ArrayList<>(i);
      for (byte b = 0; b < i; b++)
        this.mDataPaths.add(new PatternMatcher(paramParcel)); 
    } 
    this.mPriority = paramParcel.readInt();
    if (paramParcel.readInt() > 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mHasStaticPartialTypes = bool2;
    if (paramParcel.readInt() > 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mHasDynamicPartialTypes = bool2;
    boolean bool2 = bool1;
    if (paramParcel.readInt() > 0)
      bool2 = true; 
    setAutoVerify(bool2);
    setVisibilityToInstantApp(paramParcel.readInt());
    this.mOrder = paramParcel.readInt();
  }
  
  public IntentFilter(String paramString) {
    this.mHasStaticPartialTypes = false;
    this.mHasDynamicPartialTypes = false;
    this.mPriority = 0;
    this.mActions = new ArrayList<>();
    addAction(paramString);
  }
  
  public IntentFilter(String paramString1, String paramString2) throws MalformedMimeTypeException {
    this.mHasStaticPartialTypes = false;
    this.mHasDynamicPartialTypes = false;
    this.mPriority = 0;
    this.mActions = new ArrayList<>();
    addAction(paramString1);
    addDataType(paramString2);
  }
  
  private static String[] addStringToSet(String[] paramArrayOfString, String paramString, int[] paramArrayOfint, int paramInt) {
    if (findStringInSet(paramArrayOfString, paramString, paramArrayOfint, paramInt) >= 0)
      return paramArrayOfString; 
    if (paramArrayOfString == null) {
      paramArrayOfString = new String[2];
      paramArrayOfString[0] = paramString;
      paramArrayOfint[paramInt] = 1;
      return paramArrayOfString;
    } 
    int i = paramArrayOfint[paramInt];
    if (i < paramArrayOfString.length) {
      paramArrayOfString[i] = paramString;
      paramArrayOfint[paramInt] = i + 1;
      return paramArrayOfString;
    } 
    String[] arrayOfString = new String[i * 3 / 2 + 2];
    System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, i);
    arrayOfString[i] = paramString;
    paramArrayOfint[paramInt] = i + 1;
    return arrayOfString;
  }
  
  public static IntentFilter create(String paramString1, String paramString2) {
    try {
      return new IntentFilter(paramString1, paramString2);
    } catch (MalformedMimeTypeException malformedMimeTypeException) {
      throw new RuntimeException("Bad MIME type", malformedMimeTypeException);
    } 
  }
  
  private final boolean findMimeType(String paramString) {
    ArrayList<String> arrayList = this.mDataTypes;
    if (paramString == null)
      return false; 
    if (arrayList.contains(paramString))
      return true; 
    int i = paramString.length();
    if (i == 3 && paramString.equals("*/*"))
      return arrayList.isEmpty() ^ true; 
    if (hasPartialTypes() && arrayList.contains("*"))
      return true; 
    int j = paramString.indexOf('/');
    if (j > 0) {
      if (hasPartialTypes() && arrayList.contains(paramString.substring(0, j)))
        return true; 
      if (i == j + 2 && paramString.charAt(j + 1) == '*') {
        int k = arrayList.size();
        for (i = 0; i < k; i++) {
          if (paramString.regionMatches(0, arrayList.get(i), 0, j + 1))
            return true; 
        } 
      } 
    } 
    return false;
  }
  
  private static int findStringInSet(String[] paramArrayOfString, String paramString, int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfString == null)
      return -1; 
    int i = paramArrayOfint[paramInt];
    for (paramInt = 0; paramInt < i; paramInt++) {
      if (paramArrayOfString[paramInt].equals(paramString))
        return paramInt; 
    } 
    return -1;
  }
  
  private boolean hasDataPath(String paramString, boolean paramBoolean) {
    if (this.mDataPaths == null)
      return false; 
    if (paramBoolean && "/*".equals(paramString))
      return true; 
    int i = this.mDataPaths.size();
    for (byte b = 0; b < i; b++) {
      if (((PatternMatcher)this.mDataPaths.get(b)).match(paramString))
        return true; 
    } 
    return false;
  }
  
  private boolean hasDataSchemeSpecificPart(String paramString, boolean paramBoolean) {
    if (this.mDataSchemeSpecificParts == null)
      return false; 
    if (paramBoolean && "*".equals(paramString) && this.mDataSchemeSpecificParts.size() > 0)
      return true; 
    int i = this.mDataSchemeSpecificParts.size();
    for (byte b = 0; b < i; b++) {
      if (((PatternMatcher)this.mDataSchemeSpecificParts.get(b)).match(paramString))
        return true; 
    } 
    return false;
  }
  
  private boolean hasPartialTypes() {
    return (this.mHasStaticPartialTypes || this.mHasDynamicPartialTypes);
  }
  
  private boolean matchAction(String paramString, boolean paramBoolean, Collection<String> paramCollection) {
    if (paramBoolean && "*".equals(paramString)) {
      if (paramCollection == null)
        return this.mActions.isEmpty() ^ true; 
      for (int i = this.mActions.size() - 1; i >= 0; i--) {
        if (!paramCollection.contains(this.mActions.get(i)))
          return true; 
      } 
      return false;
    } 
    return (paramCollection != null && paramCollection.contains(paramString)) ? false : hasAction(paramString);
  }
  
  private int matchData(String paramString1, String paramString2, Uri paramUri, boolean paramBoolean) {
    boolean bool;
    if (paramBoolean && countMimeGroups() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    ArrayList<String> arrayList1 = this.mDataTypes;
    ArrayList<String> arrayList2 = this.mDataSchemes;
    int i = 1048576;
    int j = -2;
    if (!bool && arrayList1 == null && arrayList2 == null) {
      if (paramString1 == null && paramUri == null) {
        i = 1081344;
      } else {
        i = j;
      } 
      return i;
    } 
    String str = "";
    if (arrayList2 != null) {
      if (paramString2 != null)
        str = paramString2; 
      if (arrayList2.contains(str) || (paramBoolean && "*".equals(paramString2))) {
        j = 2097152;
        i = j;
        if (this.mDataSchemeSpecificParts != null) {
          i = j;
          if (paramUri != null)
            if (hasDataSchemeSpecificPart(paramUri.getSchemeSpecificPart(), paramBoolean)) {
              i = 5767168;
            } else {
              i = -2;
            }  
        } 
        j = i;
        if (i != 5767168) {
          j = i;
          if (this.mDataAuthorities != null) {
            j = matchDataAuthority(paramUri, paramBoolean);
            if (j >= 0) {
              if (this.mDataPaths != null)
                if (hasDataPath(paramUri.getPath(), paramBoolean)) {
                  j = 5242880;
                } else {
                  return -2;
                }  
            } else {
              return -2;
            } 
          } 
        } 
        if (j == -2)
          return -2; 
      } else {
        return -2;
      } 
    } else {
      j = i;
      if (paramString2 != null) {
        j = i;
        if (!"".equals(paramString2)) {
          j = i;
          if (!"content".equals(paramString2)) {
            j = i;
            if (!"file".equals(paramString2))
              if (paramBoolean) {
                j = i;
                if (!"*".equals(paramString2))
                  return -2; 
              } else {
                return -2;
              }  
          } 
        } 
      } 
    } 
    if (bool)
      return 6291456; 
    if (arrayList1 != null) {
      if (findMimeType(paramString1)) {
        j = 6291456;
      } else {
        return -1;
      } 
    } else if (paramString1 != null) {
      return -1;
    } 
    return 32768 + j;
  }
  
  private void processMimeType(String paramString, BiConsumer<String, Boolean> paramBiConsumer) throws MalformedMimeTypeException {
    int i = paramString.indexOf('/');
    int j = paramString.length();
    if (i > 0 && j >= i + 2) {
      String str1 = paramString;
      boolean bool1 = false;
      String str2 = str1;
      boolean bool2 = bool1;
      if (j == i + 2) {
        str2 = str1;
        bool2 = bool1;
        if (paramString.charAt(i + 1) == '*') {
          str2 = paramString.substring(0, i);
          bool2 = true;
        } 
      } 
      paramBiConsumer.accept(str2, Boolean.valueOf(bool2));
      return;
    } 
    throw new MalformedMimeTypeException(paramString);
  }
  
  private static String[] removeStringFromSet(String[] paramArrayOfString, String paramString, int[] paramArrayOfint, int paramInt) {
    int i = findStringInSet(paramArrayOfString, paramString, paramArrayOfint, paramInt);
    if (i < 0)
      return paramArrayOfString; 
    int j = paramArrayOfint[paramInt];
    if (j > paramArrayOfString.length / 4) {
      int k = j - i + 1;
      if (k > 0)
        System.arraycopy(paramArrayOfString, i + 1, paramArrayOfString, i, k); 
      paramArrayOfString[j - 1] = null;
      paramArrayOfint[paramInt] = j - 1;
      return paramArrayOfString;
    } 
    String[] arrayOfString = new String[paramArrayOfString.length / 3];
    if (i > 0)
      System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, i); 
    if (i + 1 < j)
      System.arraycopy(paramArrayOfString, i + 1, arrayOfString, i, j - i + 1); 
    return arrayOfString;
  }
  
  private void writeDataTypeToXml(XmlSerializer paramXmlSerializer, String paramString1, String paramString2) throws IOException {
    paramXmlSerializer.startTag(null, paramString2);
    String str = paramString1;
    if (paramString1.indexOf('/') < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString1);
      stringBuilder.append("/*");
      str = stringBuilder.toString();
    } 
    paramXmlSerializer.attribute(null, "name", str);
    paramXmlSerializer.endTag(null, paramString2);
  }
  
  private void writeDataTypesToXml(XmlSerializer paramXmlSerializer) throws IOException {
    byte b2;
    ArrayList<String> arrayList = this.mStaticDataTypes;
    if (arrayList == null)
      return; 
    byte b1 = 0;
    Iterator<String> iterator = arrayList.iterator();
    while (true) {
      b2 = b1;
      if (iterator.hasNext()) {
        String str = iterator.next();
        while (!((String)this.mDataTypes.get(b1)).equals(str)) {
          writeDataTypeToXml(paramXmlSerializer, this.mDataTypes.get(b1), "type");
          b1++;
        } 
        writeDataTypeToXml(paramXmlSerializer, str, "staticType");
        b1++;
        continue;
      } 
      break;
    } 
    while (b2 < this.mDataTypes.size()) {
      writeDataTypeToXml(paramXmlSerializer, this.mDataTypes.get(b2), "type");
      b2++;
    } 
  }
  
  public final Iterator<String> actionsIterator() {
    ArrayList<String> arrayList = this.mActions;
    if (arrayList != null) {
      Iterator<String> iterator = arrayList.iterator();
    } else {
      arrayList = null;
    } 
    return (Iterator<String>)arrayList;
  }
  
  public final void addAction(String paramString) {
    if (!this.mActions.contains(paramString))
      this.mActions.add(paramString.intern()); 
  }
  
  public final void addCategory(String paramString) {
    if (this.mCategories == null)
      this.mCategories = new ArrayList<>(); 
    if (!this.mCategories.contains(paramString))
      this.mCategories.add(paramString.intern()); 
  }
  
  public final void addDataAuthority(AuthorityEntry paramAuthorityEntry) {
    if (this.mDataAuthorities == null)
      this.mDataAuthorities = new ArrayList<>(); 
    this.mDataAuthorities.add(paramAuthorityEntry);
  }
  
  public final void addDataAuthority(String paramString1, String paramString2) {
    String str = paramString2;
    if (paramString2 != null)
      str = paramString2.intern(); 
    addDataAuthority(new AuthorityEntry(paramString1.intern(), str));
  }
  
  public final void addDataPath(PatternMatcher paramPatternMatcher) {
    if (this.mDataPaths == null)
      this.mDataPaths = new ArrayList<>(); 
    this.mDataPaths.add(paramPatternMatcher);
  }
  
  public final void addDataPath(String paramString, int paramInt) {
    addDataPath(new PatternMatcher(paramString.intern(), paramInt));
  }
  
  public final void addDataScheme(String paramString) {
    if (this.mDataSchemes == null)
      this.mDataSchemes = new ArrayList<>(); 
    if (!this.mDataSchemes.contains(paramString))
      this.mDataSchemes.add(paramString.intern()); 
  }
  
  public final void addDataSchemeSpecificPart(PatternMatcher paramPatternMatcher) {
    if (this.mDataSchemeSpecificParts == null)
      this.mDataSchemeSpecificParts = new ArrayList<>(); 
    this.mDataSchemeSpecificParts.add(paramPatternMatcher);
  }
  
  public final void addDataSchemeSpecificPart(String paramString, int paramInt) {
    addDataSchemeSpecificPart(new PatternMatcher(paramString, paramInt));
  }
  
  public final void addDataType(String paramString) throws MalformedMimeTypeException {
    processMimeType(paramString, new _$$Lambda$IntentFilter$fvZpjl2C1djVISORSFvcX__NkJo(this));
  }
  
  public final void addDynamicDataType(String paramString) throws MalformedMimeTypeException {
    processMimeType(paramString, new _$$Lambda$IntentFilter$WX75RVXUnG63zh_f133zF3i8Szs(this));
  }
  
  public final void addMimeGroup(String paramString) {
    if (this.mMimeGroups == null)
      this.mMimeGroups = new ArrayList<>(); 
    if (!this.mMimeGroups.contains(paramString))
      this.mMimeGroups.add(paramString); 
  }
  
  public final Iterator<AuthorityEntry> authoritiesIterator() {
    ArrayList<AuthorityEntry> arrayList = this.mDataAuthorities;
    if (arrayList != null) {
      Iterator<AuthorityEntry> iterator = arrayList.iterator();
    } else {
      arrayList = null;
    } 
    return (Iterator<AuthorityEntry>)arrayList;
  }
  
  public final Iterator<String> categoriesIterator() {
    ArrayList<String> arrayList = this.mCategories;
    if (arrayList != null) {
      Iterator<String> iterator = arrayList.iterator();
    } else {
      arrayList = null;
    } 
    return (Iterator<String>)arrayList;
  }
  
  public final void clearDynamicDataTypes() {
    ArrayList<String> arrayList = this.mDataTypes;
    if (arrayList == null)
      return; 
    if (this.mStaticDataTypes != null) {
      arrayList.clear();
      this.mDataTypes.addAll(this.mStaticDataTypes);
    } else {
      this.mDataTypes = null;
    } 
    this.mHasDynamicPartialTypes = false;
  }
  
  public final int countActions() {
    return this.mActions.size();
  }
  
  public final int countCategories() {
    boolean bool;
    ArrayList<String> arrayList = this.mCategories;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final int countDataAuthorities() {
    boolean bool;
    ArrayList<AuthorityEntry> arrayList = this.mDataAuthorities;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final int countDataPaths() {
    boolean bool;
    ArrayList<PatternMatcher> arrayList = this.mDataPaths;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final int countDataSchemeSpecificParts() {
    boolean bool;
    ArrayList<PatternMatcher> arrayList = this.mDataSchemeSpecificParts;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final int countDataSchemes() {
    boolean bool;
    ArrayList<String> arrayList = this.mDataSchemes;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final int countDataTypes() {
    boolean bool;
    ArrayList<String> arrayList = this.mDataTypes;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final int countMimeGroups() {
    boolean bool;
    ArrayList<String> arrayList = this.mMimeGroups;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int countStaticDataTypes() {
    boolean bool;
    ArrayList<String> arrayList = this.mStaticDataTypes;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final List<String> dataTypes() {
    List list;
    if (this.mDataTypes != null) {
      list = new ArrayList<>(this.mDataTypes);
    } else {
      list = null;
    } 
    return list;
  }
  
  public boolean debugCheck() {
    return true;
  }
  
  public final int describeContents() {
    return 0;
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    StringBuilder stringBuilder = new StringBuilder(256);
    if (this.mActions.size() > 0) {
      Iterator<String> iterator = this.mActions.iterator();
      while (iterator.hasNext()) {
        stringBuilder.setLength(0);
        stringBuilder.append(paramString);
        stringBuilder.append("Action: \"");
        stringBuilder.append(iterator.next());
        stringBuilder.append("\"");
        paramPrinter.println(stringBuilder.toString());
      } 
    } 
    ArrayList<String> arrayList4 = this.mCategories;
    if (arrayList4 != null) {
      Iterator<String> iterator = arrayList4.iterator();
      while (iterator.hasNext()) {
        stringBuilder.setLength(0);
        stringBuilder.append(paramString);
        stringBuilder.append("Category: \"");
        stringBuilder.append(iterator.next());
        stringBuilder.append("\"");
        paramPrinter.println(stringBuilder.toString());
      } 
    } 
    arrayList4 = this.mDataSchemes;
    if (arrayList4 != null) {
      Iterator<String> iterator = arrayList4.iterator();
      while (iterator.hasNext()) {
        stringBuilder.setLength(0);
        stringBuilder.append(paramString);
        stringBuilder.append("Scheme: \"");
        stringBuilder.append(iterator.next());
        stringBuilder.append("\"");
        paramPrinter.println(stringBuilder.toString());
      } 
    } 
    ArrayList<PatternMatcher> arrayList3 = this.mDataSchemeSpecificParts;
    if (arrayList3 != null)
      for (PatternMatcher patternMatcher : arrayList3) {
        stringBuilder.setLength(0);
        stringBuilder.append(paramString);
        stringBuilder.append("Ssp: \"");
        stringBuilder.append(patternMatcher);
        stringBuilder.append("\"");
        paramPrinter.println(stringBuilder.toString());
      }  
    ArrayList<AuthorityEntry> arrayList = this.mDataAuthorities;
    if (arrayList != null)
      for (AuthorityEntry authorityEntry : arrayList) {
        stringBuilder.setLength(0);
        stringBuilder.append(paramString);
        stringBuilder.append("Authority: \"");
        stringBuilder.append(authorityEntry.mHost);
        stringBuilder.append("\": ");
        stringBuilder.append(authorityEntry.mPort);
        if (authorityEntry.mWild)
          stringBuilder.append(" WILD"); 
        paramPrinter.println(stringBuilder.toString());
      }  
    ArrayList<PatternMatcher> arrayList2 = this.mDataPaths;
    if (arrayList2 != null)
      for (PatternMatcher patternMatcher : arrayList2) {
        stringBuilder.setLength(0);
        stringBuilder.append(paramString);
        stringBuilder.append("Path: \"");
        stringBuilder.append(patternMatcher);
        stringBuilder.append("\"");
        paramPrinter.println(stringBuilder.toString());
      }  
    ArrayList<String> arrayList1 = this.mStaticDataTypes;
    if (arrayList1 != null) {
      Iterator<String> iterator = arrayList1.iterator();
      while (iterator.hasNext()) {
        stringBuilder.setLength(0);
        stringBuilder.append(paramString);
        stringBuilder.append("StaticType: \"");
        stringBuilder.append(iterator.next());
        stringBuilder.append("\"");
        paramPrinter.println(stringBuilder.toString());
      } 
    } 
    arrayList1 = this.mDataTypes;
    if (arrayList1 != null)
      for (String str : arrayList1) {
        if (hasExactStaticDataType(str))
          continue; 
        stringBuilder.setLength(0);
        stringBuilder.append(paramString);
        stringBuilder.append("Type: \"");
        stringBuilder.append(str);
        stringBuilder.append("\"");
        paramPrinter.println(stringBuilder.toString());
      }  
    arrayList1 = this.mMimeGroups;
    if (arrayList1 != null) {
      Iterator<String> iterator = arrayList1.iterator();
      while (iterator.hasNext()) {
        stringBuilder.setLength(0);
        stringBuilder.append(paramString);
        stringBuilder.append("MimeGroup: \"");
        stringBuilder.append(iterator.next());
        stringBuilder.append("\"");
        paramPrinter.println(stringBuilder.toString());
      } 
    } 
    if (this.mPriority != 0 || this.mOrder != 0 || hasPartialTypes()) {
      stringBuilder.setLength(0);
      stringBuilder.append(paramString);
      stringBuilder.append("mPriority=");
      stringBuilder.append(this.mPriority);
      stringBuilder.append(", mOrder=");
      stringBuilder.append(this.mOrder);
      stringBuilder.append(", mHasStaticPartialTypes=");
      stringBuilder.append(this.mHasStaticPartialTypes);
      stringBuilder.append(", mHasDynamicPartialTypes=");
      stringBuilder.append(this.mHasDynamicPartialTypes);
      paramPrinter.println(stringBuilder.toString());
    } 
    if (getAutoVerify()) {
      stringBuilder.setLength(0);
      stringBuilder.append(paramString);
      stringBuilder.append("AutoVerify=");
      stringBuilder.append(getAutoVerify());
      paramPrinter.println(stringBuilder.toString());
    } 
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    if (this.mActions.size() > 0) {
      Iterator<String> iterator = this.mActions.iterator();
      while (iterator.hasNext())
        paramProtoOutputStream.write(2237677961217L, iterator.next()); 
    } 
    ArrayList<String> arrayList4 = this.mCategories;
    if (arrayList4 != null) {
      Iterator<String> iterator = arrayList4.iterator();
      while (iterator.hasNext())
        paramProtoOutputStream.write(2237677961218L, iterator.next()); 
    } 
    arrayList4 = this.mDataSchemes;
    if (arrayList4 != null) {
      Iterator<String> iterator = arrayList4.iterator();
      while (iterator.hasNext())
        paramProtoOutputStream.write(2237677961219L, iterator.next()); 
    } 
    ArrayList<PatternMatcher> arrayList3 = this.mDataSchemeSpecificParts;
    if (arrayList3 != null) {
      Iterator<PatternMatcher> iterator = arrayList3.iterator();
      while (iterator.hasNext())
        ((PatternMatcher)iterator.next()).dumpDebug(paramProtoOutputStream, 2246267895812L); 
    } 
    ArrayList<AuthorityEntry> arrayList = this.mDataAuthorities;
    if (arrayList != null) {
      Iterator<AuthorityEntry> iterator = arrayList.iterator();
      while (iterator.hasNext())
        ((AuthorityEntry)iterator.next()).dumpDebug(paramProtoOutputStream, 2246267895813L); 
    } 
    ArrayList<PatternMatcher> arrayList2 = this.mDataPaths;
    if (arrayList2 != null) {
      Iterator<PatternMatcher> iterator = arrayList2.iterator();
      while (iterator.hasNext())
        ((PatternMatcher)iterator.next()).dumpDebug(paramProtoOutputStream, 2246267895814L); 
    } 
    ArrayList<String> arrayList1 = this.mDataTypes;
    if (arrayList1 != null) {
      Iterator<String> iterator = arrayList1.iterator();
      while (iterator.hasNext())
        paramProtoOutputStream.write(2237677961223L, iterator.next()); 
    } 
    arrayList1 = this.mMimeGroups;
    if (arrayList1 != null) {
      Iterator<String> iterator = arrayList1.iterator();
      while (iterator.hasNext())
        paramProtoOutputStream.write(2237677961227L, iterator.next()); 
    } 
    if (this.mPriority != 0 || hasPartialTypes()) {
      paramProtoOutputStream.write(1120986464264L, this.mPriority);
      paramProtoOutputStream.write(1133871366153L, hasPartialTypes());
    } 
    paramProtoOutputStream.write(1133871366154L, getAutoVerify());
    paramProtoOutputStream.end(paramLong);
  }
  
  public final String getAction(int paramInt) {
    return this.mActions.get(paramInt);
  }
  
  public final boolean getAutoVerify() {
    int i = this.mVerifyState;
    boolean bool = true;
    if ((i & 0x1) != 1)
      bool = false; 
    return bool;
  }
  
  public final String getCategory(int paramInt) {
    return this.mCategories.get(paramInt);
  }
  
  public final AuthorityEntry getDataAuthority(int paramInt) {
    return this.mDataAuthorities.get(paramInt);
  }
  
  public final PatternMatcher getDataPath(int paramInt) {
    return this.mDataPaths.get(paramInt);
  }
  
  public final String getDataScheme(int paramInt) {
    return this.mDataSchemes.get(paramInt);
  }
  
  public final PatternMatcher getDataSchemeSpecificPart(int paramInt) {
    return this.mDataSchemeSpecificParts.get(paramInt);
  }
  
  public final String getDataType(int paramInt) {
    return this.mDataTypes.get(paramInt);
  }
  
  public String[] getHosts() {
    ArrayList<String> arrayList = getHostsList();
    return arrayList.<String>toArray(new String[arrayList.size()]);
  }
  
  public ArrayList<String> getHostsList() {
    ArrayList<String> arrayList = new ArrayList();
    Iterator<AuthorityEntry> iterator = authoritiesIterator();
    if (iterator != null)
      while (iterator.hasNext())
        arrayList.add(((AuthorityEntry)iterator.next()).getHost());  
    return arrayList;
  }
  
  public final String getMimeGroup(int paramInt) {
    return this.mMimeGroups.get(paramInt);
  }
  
  @SystemApi
  public final int getOrder() {
    return this.mOrder;
  }
  
  public final int getPriority() {
    return this.mPriority;
  }
  
  public int getVisibilityToInstantApp() {
    return this.mInstantAppVisibility;
  }
  
  public final boolean handleAllWebDataURI() {
    null = hasCategory("android.intent.category.APP_BROWSER");
    boolean bool = false;
    if (!null) {
      null = bool;
      if (handlesWebUris(false)) {
        null = bool;
        if (countDataAuthorities() == 0)
          return true; 
      } 
      return null;
    } 
    return true;
  }
  
  public final boolean handlesWebUris(boolean paramBoolean) {
    if (hasAction("android.intent.action.VIEW") && hasCategory("android.intent.category.BROWSABLE")) {
      ArrayList<String> arrayList = this.mDataSchemes;
      if (arrayList != null && arrayList.size() != 0) {
        int i = this.mDataSchemes.size();
        for (byte b = 0; b < i; b++) {
          boolean bool;
          String str = this.mDataSchemes.get(b);
          if ("http".equals(str) || "https".equals(str)) {
            bool = true;
          } else {
            bool = false;
          } 
          if (paramBoolean) {
            if (!bool)
              return false; 
          } else if (bool) {
            return true;
          } 
        } 
        return paramBoolean;
      } 
    } 
    return false;
  }
  
  public final boolean hasAction(String paramString) {
    boolean bool;
    if (paramString != null && this.mActions.contains(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean hasCategory(String paramString) {
    boolean bool;
    ArrayList<String> arrayList = this.mCategories;
    if (arrayList != null && arrayList.contains(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean hasDataAuthority(AuthorityEntry paramAuthorityEntry) {
    ArrayList<AuthorityEntry> arrayList = this.mDataAuthorities;
    if (arrayList == null)
      return false; 
    int i = arrayList.size();
    for (byte b = 0; b < i; b++) {
      if (((AuthorityEntry)this.mDataAuthorities.get(b)).match(paramAuthorityEntry))
        return true; 
    } 
    return false;
  }
  
  public final boolean hasDataAuthority(Uri paramUri) {
    boolean bool;
    if (matchDataAuthority(paramUri) >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean hasDataPath(PatternMatcher paramPatternMatcher) {
    ArrayList<PatternMatcher> arrayList = this.mDataPaths;
    if (arrayList == null)
      return false; 
    int i = arrayList.size();
    for (byte b = 0; b < i; b++) {
      PatternMatcher patternMatcher = this.mDataPaths.get(b);
      if (patternMatcher.getType() == paramPatternMatcher.getType() && patternMatcher.getPath().equals(paramPatternMatcher.getPath()))
        return true; 
    } 
    return false;
  }
  
  public final boolean hasDataPath(String paramString) {
    return hasDataPath(paramString, false);
  }
  
  public final boolean hasDataScheme(String paramString) {
    boolean bool;
    ArrayList<String> arrayList = this.mDataSchemes;
    if (arrayList != null && arrayList.contains(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean hasDataSchemeSpecificPart(PatternMatcher paramPatternMatcher) {
    ArrayList<PatternMatcher> arrayList = this.mDataSchemeSpecificParts;
    if (arrayList == null)
      return false; 
    int i = arrayList.size();
    for (byte b = 0; b < i; b++) {
      PatternMatcher patternMatcher = this.mDataSchemeSpecificParts.get(b);
      if (patternMatcher.getType() == paramPatternMatcher.getType() && patternMatcher.getPath().equals(paramPatternMatcher.getPath()))
        return true; 
    } 
    return false;
  }
  
  public final boolean hasDataSchemeSpecificPart(String paramString) {
    return hasDataSchemeSpecificPart(paramString, false);
  }
  
  public final boolean hasDataType(String paramString) {
    boolean bool;
    if (this.mDataTypes != null && findMimeType(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean hasExactDataType(String paramString) {
    boolean bool;
    ArrayList<String> arrayList = this.mDataTypes;
    if (arrayList != null && arrayList.contains(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean hasExactDynamicDataType(String paramString) {
    boolean bool;
    if (hasExactDataType(paramString) && !hasExactStaticDataType(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean hasExactStaticDataType(String paramString) {
    boolean bool;
    ArrayList<String> arrayList = this.mStaticDataTypes;
    if (arrayList != null && arrayList.contains(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean hasMimeGroup(String paramString) {
    boolean bool;
    ArrayList<String> arrayList = this.mMimeGroups;
    if (arrayList != null && arrayList.contains(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isExplicitlyVisibleToInstantApp() {
    int i = this.mInstantAppVisibility;
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isImplicitlyVisibleToInstantApp() {
    boolean bool;
    if (this.mInstantAppVisibility == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isVerified() {
    int i = this.mVerifyState;
    boolean bool = false;
    if ((i & 0x100) == 256) {
      if ((i & 0x10) == 16)
        bool = true; 
      return bool;
    } 
    return false;
  }
  
  public boolean isVisibleToInstantApp() {
    boolean bool;
    if (this.mInstantAppVisibility != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final int match(ContentResolver paramContentResolver, Intent paramIntent, boolean paramBoolean, String paramString) {
    String str;
    if (paramBoolean) {
      str = paramIntent.resolveType(paramContentResolver);
    } else {
      str = paramIntent.getType();
    } 
    return match(paramIntent.getAction(), str, paramIntent.getScheme(), paramIntent.getData(), paramIntent.getCategories(), paramString);
  }
  
  public final int match(String paramString1, String paramString2, String paramString3, Uri paramUri, Set<String> paramSet, String paramString4) {
    return match(paramString1, paramString2, paramString3, paramUri, paramSet, paramString4, false, null);
  }
  
  public final int match(String paramString1, String paramString2, String paramString3, Uri paramUri, Set<String> paramSet, String paramString4, boolean paramBoolean, Collection<String> paramCollection) {
    if (paramString1 != null && !matchAction(paramString1, paramBoolean, paramCollection))
      return -3; 
    int i = matchData(paramString2, paramString3, paramUri, paramBoolean);
    return (i < 0) ? i : ((matchCategories(paramSet) != null) ? -4 : i);
  }
  
  public final boolean matchAction(String paramString) {
    return matchAction(paramString, false, null);
  }
  
  public final String matchCategories(Set<String> paramSet) {
    Set set = null;
    if (paramSet == null)
      return null; 
    Iterator<String> iterator = paramSet.iterator();
    if (this.mCategories == null) {
      String str;
      paramSet = set;
      if (iterator.hasNext())
        str = iterator.next(); 
      return str;
    } 
    while (iterator.hasNext()) {
      String str = iterator.next();
      if (!this.mCategories.contains(str))
        return str; 
    } 
    return null;
  }
  
  public final int matchData(String paramString1, String paramString2, Uri paramUri) {
    return matchData(paramString1, paramString2, paramUri, false);
  }
  
  public final int matchDataAuthority(Uri paramUri) {
    return matchDataAuthority(paramUri, false);
  }
  
  public final int matchDataAuthority(Uri paramUri, boolean paramBoolean) {
    if (paramUri != null) {
      ArrayList<AuthorityEntry> arrayList = this.mDataAuthorities;
      if (arrayList != null) {
        int i = arrayList.size();
        for (byte b = 0; b < i; b++) {
          int j = ((AuthorityEntry)this.mDataAuthorities.get(b)).match(paramUri, paramBoolean);
          if (j >= 0)
            return j; 
        } 
        return -2;
      } 
    } 
    return -2;
  }
  
  public final Iterator<String> mimeGroupsIterator() {
    ArrayList<String> arrayList = this.mMimeGroups;
    if (arrayList != null) {
      Iterator<String> iterator = arrayList.iterator();
    } else {
      arrayList = null;
    } 
    return (Iterator<String>)arrayList;
  }
  
  public final boolean needsVerification() {
    boolean bool = getAutoVerify();
    boolean bool1 = true;
    if (!bool || !handlesWebUris(true))
      bool1 = false; 
    return bool1;
  }
  
  public final Iterator<PatternMatcher> pathsIterator() {
    ArrayList<PatternMatcher> arrayList = this.mDataPaths;
    if (arrayList != null) {
      Iterator<PatternMatcher> iterator = arrayList.iterator();
    } else {
      arrayList = null;
    } 
    return (Iterator<PatternMatcher>)arrayList;
  }
  
  public void readFromXml(XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    boolean bool;
    String str = paramXmlPullParser.getAttributeValue(null, "autoVerify");
    if (TextUtils.isEmpty(str)) {
      bool = false;
    } else {
      bool = Boolean.getBoolean(str);
    } 
    setAutoVerify(bool);
    int i = paramXmlPullParser.getDepth();
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1 && (j != 3 || paramXmlPullParser.getDepth() > i)) {
        if (j == 3 || j == 4)
          continue; 
        str = paramXmlPullParser.getName();
        if (str.equals("action")) {
          str = paramXmlPullParser.getAttributeValue(null, "name");
          if (str != null)
            addAction(str); 
        } else if (str.equals("cat")) {
          str = paramXmlPullParser.getAttributeValue(null, "name");
          if (str != null)
            addCategory(str); 
        } else if (str.equals("staticType")) {
          str = paramXmlPullParser.getAttributeValue(null, "name");
          if (str != null)
            try {
              addDataType(str);
            } catch (MalformedMimeTypeException malformedMimeTypeException) {} 
        } else if (malformedMimeTypeException.equals("type")) {
          String str1 = paramXmlPullParser.getAttributeValue(null, "name");
          if (str1 != null)
            try {
              addDynamicDataType(str1);
            } catch (MalformedMimeTypeException malformedMimeTypeException1) {} 
        } else {
          String str1;
          if (malformedMimeTypeException1.equals("group")) {
            str1 = paramXmlPullParser.getAttributeValue(null, "name");
            if (str1 != null)
              addMimeGroup(str1); 
          } else if (str1.equals("scheme")) {
            str1 = paramXmlPullParser.getAttributeValue(null, "name");
            if (str1 != null)
              addDataScheme(str1); 
          } else if (str1.equals("ssp")) {
            str1 = paramXmlPullParser.getAttributeValue(null, "literal");
            if (str1 != null) {
              addDataSchemeSpecificPart(str1, 0);
            } else {
              str1 = paramXmlPullParser.getAttributeValue(null, "prefix");
              if (str1 != null) {
                addDataSchemeSpecificPart(str1, 1);
              } else {
                str1 = paramXmlPullParser.getAttributeValue(null, "sglob");
                if (str1 != null) {
                  addDataSchemeSpecificPart(str1, 2);
                } else {
                  str1 = paramXmlPullParser.getAttributeValue(null, "aglob");
                  if (str1 != null)
                    addDataSchemeSpecificPart(str1, 3); 
                } 
              } 
            } 
          } else if (str1.equals("auth")) {
            str1 = paramXmlPullParser.getAttributeValue(null, "host");
            String str2 = paramXmlPullParser.getAttributeValue(null, "port");
            if (str1 != null)
              addDataAuthority(str1, str2); 
          } else if (str1.equals("path")) {
            str1 = paramXmlPullParser.getAttributeValue(null, "literal");
            if (str1 != null) {
              addDataPath(str1, 0);
            } else {
              str1 = paramXmlPullParser.getAttributeValue(null, "prefix");
              if (str1 != null) {
                addDataPath(str1, 1);
              } else {
                str1 = paramXmlPullParser.getAttributeValue(null, "sglob");
                if (str1 != null) {
                  addDataPath(str1, 2);
                } else {
                  str1 = paramXmlPullParser.getAttributeValue(null, "aglob");
                  if (str1 != null)
                    addDataPath(str1, 3); 
                } 
              } 
            } 
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown tag parsing IntentFilter: ");
            stringBuilder.append(str1);
            Log.w("IntentFilter", stringBuilder.toString());
          } 
        } 
        XmlUtils.skipCurrentTag(paramXmlPullParser);
        continue;
      } 
      break;
    } 
  }
  
  public final Iterator<PatternMatcher> schemeSpecificPartsIterator() {
    ArrayList<PatternMatcher> arrayList = this.mDataSchemeSpecificParts;
    if (arrayList != null) {
      Iterator<PatternMatcher> iterator = arrayList.iterator();
    } else {
      arrayList = null;
    } 
    return (Iterator<PatternMatcher>)arrayList;
  }
  
  public final Iterator<String> schemesIterator() {
    ArrayList<String> arrayList = this.mDataSchemes;
    if (arrayList != null) {
      Iterator<String> iterator = arrayList.iterator();
    } else {
      arrayList = null;
    } 
    return (Iterator<String>)arrayList;
  }
  
  public final void setAutoVerify(boolean paramBoolean) {
    int i = this.mVerifyState & 0xFFFFFFFE;
    this.mVerifyState = i;
    if (paramBoolean)
      this.mVerifyState = i | 0x1; 
  }
  
  @SystemApi
  public final void setOrder(int paramInt) {
    this.mOrder = paramInt;
  }
  
  public final void setPriority(int paramInt) {
    this.mPriority = paramInt;
  }
  
  public void setVerified(boolean paramBoolean) {
    int i = this.mVerifyState | 0x100;
    this.mVerifyState = i;
    i &= 0xFFFFEFFF;
    this.mVerifyState = i;
    if (paramBoolean)
      this.mVerifyState = i | 0x1000; 
  }
  
  public void setVisibilityToInstantApp(int paramInt) {
    this.mInstantAppVisibility = paramInt;
  }
  
  public final Iterator<String> typesIterator() {
    ArrayList<String> arrayList = this.mDataTypes;
    if (arrayList != null) {
      Iterator<String> iterator = arrayList.iterator();
    } else {
      arrayList = null;
    } 
    return (Iterator<String>)arrayList;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStringList(this.mActions);
    if (this.mCategories != null) {
      paramParcel.writeInt(1);
      paramParcel.writeStringList(this.mCategories);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mDataSchemes != null) {
      paramParcel.writeInt(1);
      paramParcel.writeStringList(this.mDataSchemes);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mStaticDataTypes != null) {
      paramParcel.writeInt(1);
      paramParcel.writeStringList(this.mStaticDataTypes);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mDataTypes != null) {
      paramParcel.writeInt(1);
      paramParcel.writeStringList(this.mDataTypes);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mMimeGroups != null) {
      paramParcel.writeInt(1);
      paramParcel.writeStringList(this.mMimeGroups);
    } else {
      paramParcel.writeInt(0);
    } 
    ArrayList<PatternMatcher> arrayList2 = this.mDataSchemeSpecificParts;
    if (arrayList2 != null) {
      int i = arrayList2.size();
      paramParcel.writeInt(i);
      for (byte b = 0; b < i; b++)
        ((PatternMatcher)this.mDataSchemeSpecificParts.get(b)).writeToParcel(paramParcel, paramInt); 
    } else {
      paramParcel.writeInt(0);
    } 
    ArrayList<AuthorityEntry> arrayList = this.mDataAuthorities;
    if (arrayList != null) {
      int i = arrayList.size();
      paramParcel.writeInt(i);
      for (byte b = 0; b < i; b++)
        ((AuthorityEntry)this.mDataAuthorities.get(b)).writeToParcel(paramParcel); 
    } else {
      paramParcel.writeInt(0);
    } 
    ArrayList<PatternMatcher> arrayList1 = this.mDataPaths;
    if (arrayList1 != null) {
      int i = arrayList1.size();
      paramParcel.writeInt(i);
      for (byte b = 0; b < i; b++)
        ((PatternMatcher)this.mDataPaths.get(b)).writeToParcel(paramParcel, paramInt); 
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.mPriority);
    paramParcel.writeInt(this.mHasStaticPartialTypes);
    paramParcel.writeInt(this.mHasDynamicPartialTypes);
    paramParcel.writeInt(getAutoVerify());
    paramParcel.writeInt(this.mInstantAppVisibility);
    paramParcel.writeInt(this.mOrder);
  }
  
  public void writeToXml(XmlSerializer paramXmlSerializer) throws IOException {
    if (getAutoVerify())
      paramXmlSerializer.attribute(null, "autoVerify", Boolean.toString(true)); 
    int i = countActions();
    byte b;
    for (b = 0; b < i; b++) {
      paramXmlSerializer.startTag(null, "action");
      paramXmlSerializer.attribute(null, "name", this.mActions.get(b));
      paramXmlSerializer.endTag(null, "action");
    } 
    i = countCategories();
    for (b = 0; b < i; b++) {
      paramXmlSerializer.startTag(null, "cat");
      paramXmlSerializer.attribute(null, "name", this.mCategories.get(b));
      paramXmlSerializer.endTag(null, "cat");
    } 
    writeDataTypesToXml(paramXmlSerializer);
    i = countMimeGroups();
    for (b = 0; b < i; b++) {
      paramXmlSerializer.startTag(null, "group");
      paramXmlSerializer.attribute(null, "name", this.mMimeGroups.get(b));
      paramXmlSerializer.endTag(null, "group");
    } 
    i = countDataSchemes();
    for (b = 0; b < i; b++) {
      paramXmlSerializer.startTag(null, "scheme");
      paramXmlSerializer.attribute(null, "name", this.mDataSchemes.get(b));
      paramXmlSerializer.endTag(null, "scheme");
    } 
    i = countDataSchemeSpecificParts();
    for (b = 0; b < i; b++) {
      paramXmlSerializer.startTag(null, "ssp");
      PatternMatcher patternMatcher = this.mDataSchemeSpecificParts.get(b);
      int j = patternMatcher.getType();
      if (j != 0) {
        if (j != 1) {
          if (j != 2) {
            if (j == 3)
              paramXmlSerializer.attribute(null, "aglob", patternMatcher.getPath()); 
          } else {
            paramXmlSerializer.attribute(null, "sglob", patternMatcher.getPath());
          } 
        } else {
          paramXmlSerializer.attribute(null, "prefix", patternMatcher.getPath());
        } 
      } else {
        paramXmlSerializer.attribute(null, "literal", patternMatcher.getPath());
      } 
      paramXmlSerializer.endTag(null, "ssp");
    } 
    i = countDataAuthorities();
    for (b = 0; b < i; b++) {
      paramXmlSerializer.startTag(null, "auth");
      AuthorityEntry authorityEntry = this.mDataAuthorities.get(b);
      paramXmlSerializer.attribute(null, "host", authorityEntry.getHost());
      if (authorityEntry.getPort() >= 0)
        paramXmlSerializer.attribute(null, "port", Integer.toString(authorityEntry.getPort())); 
      paramXmlSerializer.endTag(null, "auth");
    } 
    i = countDataPaths();
    for (b = 0; b < i; b++) {
      paramXmlSerializer.startTag(null, "path");
      PatternMatcher patternMatcher = this.mDataPaths.get(b);
      int j = patternMatcher.getType();
      if (j != 0) {
        if (j != 1) {
          if (j != 2) {
            if (j == 3)
              paramXmlSerializer.attribute(null, "aglob", patternMatcher.getPath()); 
          } else {
            paramXmlSerializer.attribute(null, "sglob", patternMatcher.getPath());
          } 
        } else {
          paramXmlSerializer.attribute(null, "prefix", patternMatcher.getPath());
        } 
      } else {
        paramXmlSerializer.attribute(null, "literal", patternMatcher.getPath());
      } 
      paramXmlSerializer.endTag(null, "path");
    } 
  }
  
  public static final class AuthorityEntry {
    private final String mHost;
    
    private final String mOrigHost;
    
    private final int mPort;
    
    private final boolean mWild;
    
    AuthorityEntry(Parcel param1Parcel) {
      boolean bool;
      this.mOrigHost = param1Parcel.readString();
      this.mHost = param1Parcel.readString();
      if (param1Parcel.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mWild = bool;
      this.mPort = param1Parcel.readInt();
    }
    
    public AuthorityEntry(String param1String1, String param1String2) {
      this.mOrigHost = param1String1;
      int i = param1String1.length();
      boolean bool1 = false;
      boolean bool2 = bool1;
      if (i > 0) {
        bool2 = bool1;
        if (param1String1.charAt(0) == '*')
          bool2 = true; 
      } 
      this.mWild = bool2;
      if (bool2)
        param1String1 = param1String1.substring(1).intern(); 
      this.mHost = param1String1;
      if (param1String2 != null) {
        i = Integer.parseInt(param1String2);
      } else {
        i = -1;
      } 
      this.mPort = i;
    }
    
    void dumpDebug(ProtoOutputStream param1ProtoOutputStream, long param1Long) {
      param1Long = param1ProtoOutputStream.start(param1Long);
      param1ProtoOutputStream.write(1138166333441L, this.mHost);
      param1ProtoOutputStream.write(1133871366146L, this.mWild);
      param1ProtoOutputStream.write(1120986464259L, this.mPort);
      param1ProtoOutputStream.end(param1Long);
    }
    
    public boolean equals(Object param1Object) {
      return (param1Object instanceof AuthorityEntry) ? match((AuthorityEntry)param1Object) : false;
    }
    
    public String getHost() {
      return this.mOrigHost;
    }
    
    public int getPort() {
      return this.mPort;
    }
    
    public int match(Uri param1Uri) {
      return match(param1Uri, false);
    }
    
    public int match(Uri param1Uri, boolean param1Boolean) {
      String str = param1Uri.getHost();
      if (str == null)
        return (param1Boolean && this.mWild) ? 3145728 : -2; 
      if (!param1Boolean || !"*".equals(str)) {
        String str1 = str;
        if (this.mWild) {
          if (str.length() < this.mHost.length())
            return -2; 
          str1 = str.substring(str.length() - this.mHost.length());
        } 
        if (str1.compareToIgnoreCase(this.mHost) != 0)
          return -2; 
      } 
      if (!param1Boolean) {
        int i = this.mPort;
        if (i >= 0)
          return (i != param1Uri.getPort()) ? -2 : 4194304; 
      } 
      return 3145728;
    }
    
    public boolean match(AuthorityEntry param1AuthorityEntry) {
      return (this.mWild != param1AuthorityEntry.mWild) ? false : (!this.mHost.equals(param1AuthorityEntry.mHost) ? false : (!(this.mPort != param1AuthorityEntry.mPort)));
    }
    
    void writeToParcel(Parcel param1Parcel) {
      param1Parcel.writeString(this.mOrigHost);
      param1Parcel.writeString(this.mHost);
      param1Parcel.writeInt(this.mWild);
      param1Parcel.writeInt(this.mPort);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface InstantAppVisibility {}
  
  public static class MalformedMimeTypeException extends AndroidException {
    public MalformedMimeTypeException() {}
    
    public MalformedMimeTypeException(String param1String) {
      super(param1String);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IntentFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
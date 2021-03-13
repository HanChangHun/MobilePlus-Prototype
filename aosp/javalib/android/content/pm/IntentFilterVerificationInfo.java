package android.content.pm;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

@SystemApi
public final class IntentFilterVerificationInfo implements Parcelable {
  private static final String ATTR_DOMAIN_NAME = "name";
  
  private static final String ATTR_PACKAGE_NAME = "packageName";
  
  private static final String ATTR_STATUS = "status";
  
  public static final Parcelable.Creator<IntentFilterVerificationInfo> CREATOR;
  
  private static final String TAG = IntentFilterVerificationInfo.class.getName();
  
  private static final String TAG_DOMAIN = "domain";
  
  private ArraySet<String> mDomains = new ArraySet();
  
  private int mMainStatus;
  
  private String mPackageName;
  
  static {
    CREATOR = new Parcelable.Creator<IntentFilterVerificationInfo>() {
        public IntentFilterVerificationInfo createFromParcel(Parcel param1Parcel) {
          return new IntentFilterVerificationInfo(param1Parcel);
        }
        
        public IntentFilterVerificationInfo[] newArray(int param1Int) {
          return new IntentFilterVerificationInfo[param1Int];
        }
      };
  }
  
  public IntentFilterVerificationInfo() {
    this.mPackageName = null;
    this.mMainStatus = 0;
  }
  
  public IntentFilterVerificationInfo(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public IntentFilterVerificationInfo(String paramString, ArraySet<String> paramArraySet) {
    this.mPackageName = paramString;
    this.mDomains = paramArraySet;
    this.mMainStatus = 0;
  }
  
  public IntentFilterVerificationInfo(XmlPullParser paramXmlPullParser) throws IOException, XmlPullParserException {
    readFromXml(paramXmlPullParser);
  }
  
  public static String getStatusStringFromValue(long paramLong) {
    StringBuilder stringBuilder = new StringBuilder();
    int i = (int)(paramLong >> 32L);
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i != 4) {
            stringBuilder.append("undefined");
          } else {
            stringBuilder.append("always-ask");
          } 
        } else {
          stringBuilder.append("never");
        } 
      } else {
        stringBuilder.append("always : ");
        stringBuilder.append(Long.toHexString(0xFFFFFFFFFFFFFFFFL & paramLong));
      } 
    } else {
      stringBuilder.append("ask");
    } 
    return stringBuilder.toString();
  }
  
  private void readFromParcel(Parcel paramParcel) {
    this.mPackageName = paramParcel.readString();
    this.mMainStatus = paramParcel.readInt();
    ArrayList arrayList = new ArrayList();
    paramParcel.readStringList(arrayList);
    this.mDomains.addAll(arrayList);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Set<String> getDomains() {
    return (Set<String>)this.mDomains;
  }
  
  public String getDomainsString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (String str : this.mDomains) {
      if (stringBuilder.length() > 0)
        stringBuilder.append(" "); 
      stringBuilder.append(str);
    } 
    return stringBuilder.toString();
  }
  
  int getIntFromXml(XmlPullParser paramXmlPullParser, String paramString, int paramInt) {
    StringBuilder stringBuilder;
    String str = paramXmlPullParser.getAttributeValue(null, paramString);
    if (TextUtils.isEmpty(str)) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Missing element under ");
      stringBuilder.append(TAG);
      stringBuilder.append(": ");
      stringBuilder.append(paramString);
      stringBuilder.append(" at ");
      stringBuilder.append(paramXmlPullParser.getPositionDescription());
      String str1 = stringBuilder.toString();
      Log.w(TAG, str1);
      return paramInt;
    } 
    return Integer.parseInt((String)stringBuilder);
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int getStatus() {
    return this.mMainStatus;
  }
  
  public String getStatusString() {
    return getStatusStringFromValue(this.mMainStatus << 32L);
  }
  
  String getStringFromXml(XmlPullParser paramXmlPullParser, String paramString1, String paramString2) {
    StringBuilder stringBuilder;
    String str = paramXmlPullParser.getAttributeValue(null, paramString1);
    if (str == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Missing element under ");
      stringBuilder.append(TAG);
      stringBuilder.append(": ");
      stringBuilder.append(paramString1);
      stringBuilder.append(" at ");
      stringBuilder.append(paramXmlPullParser.getPositionDescription());
      String str1 = stringBuilder.toString();
      Log.w(TAG, str1);
      return paramString2;
    } 
    return (String)stringBuilder;
  }
  
  public void readFromXml(XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    String str = getStringFromXml(paramXmlPullParser, "packageName", null);
    this.mPackageName = str;
    if (str == null)
      Log.e(TAG, "Package name cannot be null!"); 
    int i = getIntFromXml(paramXmlPullParser, "status", -1);
    if (i == -1) {
      String str1 = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown status value: ");
      stringBuilder.append(i);
      Log.e(str1, stringBuilder.toString());
    } 
    this.mMainStatus = i;
    int j = paramXmlPullParser.getDepth();
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 1 && (i != 3 || paramXmlPullParser.getDepth() > j)) {
        if (i == 3 || i == 4)
          continue; 
        String str1 = paramXmlPullParser.getName();
        if (str1.equals("domain")) {
          str = getStringFromXml(paramXmlPullParser, "name", null);
          if (!TextUtils.isEmpty(str))
            this.mDomains.add(str); 
        } else {
          str = TAG;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown tag parsing IntentFilter: ");
          stringBuilder.append(str1);
          Log.w(str, stringBuilder.toString());
        } 
        XmlUtils.skipCurrentTag(paramXmlPullParser);
        continue;
      } 
      break;
    } 
  }
  
  public void setDomains(ArraySet<String> paramArraySet) {
    this.mDomains = paramArraySet;
  }
  
  public void setStatus(int paramInt) {
    if (paramInt >= 0 && paramInt <= 3) {
      this.mMainStatus = paramInt;
    } else {
      String str = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Trying to set a non supported status: ");
      stringBuilder.append(paramInt);
      Log.w(str, stringBuilder.toString());
    } 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeInt(this.mMainStatus);
    paramParcel.writeStringList(new ArrayList<>((Collection<? extends String>)this.mDomains));
  }
  
  public void writeToXml(XmlSerializer paramXmlSerializer) throws IOException {
    paramXmlSerializer.attribute(null, "packageName", this.mPackageName);
    paramXmlSerializer.attribute(null, "status", String.valueOf(this.mMainStatus));
    for (String str : this.mDomains) {
      paramXmlSerializer.startTag(null, "domain");
      paramXmlSerializer.attribute(null, "name", str);
      paramXmlSerializer.endTag(null, "domain");
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IntentFilterVerificationInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
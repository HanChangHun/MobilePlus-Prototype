package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public final class FactoryResetProtectionPolicy implements Parcelable {
  private static final String ATTR_VALUE = "value";
  
  public static final Parcelable.Creator<FactoryResetProtectionPolicy> CREATOR = new Parcelable.Creator<FactoryResetProtectionPolicy>() {
      public FactoryResetProtectionPolicy createFromParcel(Parcel param1Parcel) {
        ArrayList<String> arrayList = new ArrayList();
        int i = param1Parcel.readInt();
        for (byte b = 0; b < i; b++)
          arrayList.add(param1Parcel.readString()); 
        return new FactoryResetProtectionPolicy(arrayList, param1Parcel.readBoolean());
      }
      
      public FactoryResetProtectionPolicy[] newArray(int param1Int) {
        return new FactoryResetProtectionPolicy[param1Int];
      }
    };
  
  private static final String KEY_FACTORY_RESET_PROTECTION_ACCOUNT = "factory_reset_protection_account";
  
  private static final String KEY_FACTORY_RESET_PROTECTION_ENABLED = "factory_reset_protection_enabled";
  
  private static final String LOG_TAG = "FactoryResetProtectionPolicy";
  
  private final List<String> mFactoryResetProtectionAccounts;
  
  private final boolean mFactoryResetProtectionEnabled;
  
  private FactoryResetProtectionPolicy(List<String> paramList, boolean paramBoolean) {
    this.mFactoryResetProtectionAccounts = paramList;
    this.mFactoryResetProtectionEnabled = paramBoolean;
  }
  
  public static FactoryResetProtectionPolicy readFromXml(XmlPullParser paramXmlPullParser) {
    try {
      boolean bool = Boolean.parseBoolean(paramXmlPullParser.getAttributeValue(null, "factory_reset_protection_enabled"));
      ArrayList<String> arrayList = new ArrayList();
      this();
      int i = paramXmlPullParser.getDepth();
      while (true) {
        int j = paramXmlPullParser.next();
        if (j != 1 && (j != 3 || paramXmlPullParser.getDepth() > i)) {
          if (j == 3 || j == 4 || !paramXmlPullParser.getName().equals("factory_reset_protection_account"))
            continue; 
          arrayList.add(paramXmlPullParser.getAttributeValue(null, "value"));
          continue;
        } 
        break;
      } 
      return new FactoryResetProtectionPolicy(arrayList, bool);
    } catch (XmlPullParserException|IOException xmlPullParserException) {
      Log.w("FactoryResetProtectionPolicy", "Reading from xml failed", (Throwable)xmlPullParserException);
      return null;
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<String> getFactoryResetProtectionAccounts() {
    return this.mFactoryResetProtectionAccounts;
  }
  
  public boolean isFactoryResetProtectionEnabled() {
    return this.mFactoryResetProtectionEnabled;
  }
  
  public boolean isNotEmpty() {
    boolean bool;
    if (!this.mFactoryResetProtectionAccounts.isEmpty() && this.mFactoryResetProtectionEnabled) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FactoryResetProtectionPolicy{mFactoryResetProtectionAccounts=");
    stringBuilder.append(this.mFactoryResetProtectionAccounts);
    stringBuilder.append(", mFactoryResetProtectionEnabled=");
    stringBuilder.append(this.mFactoryResetProtectionEnabled);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mFactoryResetProtectionAccounts.size());
    Iterator<String> iterator = this.mFactoryResetProtectionAccounts.iterator();
    while (iterator.hasNext())
      paramParcel.writeString(iterator.next()); 
    paramParcel.writeBoolean(this.mFactoryResetProtectionEnabled);
  }
  
  public void writeToXml(XmlSerializer paramXmlSerializer) throws IOException {
    paramXmlSerializer.attribute(null, "factory_reset_protection_enabled", Boolean.toString(this.mFactoryResetProtectionEnabled));
    for (String str : this.mFactoryResetProtectionAccounts) {
      paramXmlSerializer.startTag(null, "factory_reset_protection_account");
      paramXmlSerializer.attribute(null, "value", str);
      paramXmlSerializer.endTag(null, "factory_reset_protection_account");
    } 
  }
  
  public static class Builder {
    private List<String> mFactoryResetProtectionAccounts;
    
    private boolean mFactoryResetProtectionEnabled = true;
    
    public FactoryResetProtectionPolicy build() {
      return new FactoryResetProtectionPolicy(this.mFactoryResetProtectionAccounts, this.mFactoryResetProtectionEnabled);
    }
    
    public Builder setFactoryResetProtectionAccounts(List<String> param1List) {
      this.mFactoryResetProtectionAccounts = new ArrayList<>(param1List);
      return this;
    }
    
    public Builder setFactoryResetProtectionEnabled(boolean param1Boolean) {
      this.mFactoryResetProtectionEnabled = param1Boolean;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/FactoryResetProtectionPolicy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
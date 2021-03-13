package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.Parcelling;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ParsedComponent implements Parcelable {
  private static ParsedIntentInfo.ListParceler sForIntentInfos = (ParsedIntentInfo.ListParceler)Parcelling.Cache.getOrCreate(ParsedIntentInfo.ListParceler.class);
  
  int banner;
  
  private ComponentName componentName;
  
  int descriptionRes;
  
  int flags;
  
  int icon;
  
  private List<ParsedIntentInfo> intents;
  
  int labelRes;
  
  int logo;
  
  protected Bundle metaData;
  
  private String name;
  
  CharSequence nonLocalizedLabel;
  
  private String packageName;
  
  ParsedComponent() {}
  
  public ParsedComponent(ParsedComponent paramParsedComponent) {
    this.metaData = paramParsedComponent.metaData;
    this.name = paramParsedComponent.name;
    this.icon = paramParsedComponent.getIcon();
    this.labelRes = paramParsedComponent.getLabelRes();
    this.nonLocalizedLabel = paramParsedComponent.getNonLocalizedLabel();
    this.logo = paramParsedComponent.getLogo();
    this.banner = paramParsedComponent.getBanner();
    this.descriptionRes = paramParsedComponent.getDescriptionRes();
    this.flags = paramParsedComponent.getFlags();
    setPackageName(paramParsedComponent.packageName);
    this.intents = new ArrayList<>(paramParsedComponent.getIntents());
  }
  
  protected ParsedComponent(Parcel paramParcel) {
    ClassLoader classLoader = Object.class.getClassLoader();
    this.name = paramParcel.readString();
    this.icon = paramParcel.readInt();
    this.labelRes = paramParcel.readInt();
    this.nonLocalizedLabel = paramParcel.readCharSequence();
    this.logo = paramParcel.readInt();
    this.banner = paramParcel.readInt();
    this.descriptionRes = paramParcel.readInt();
    this.flags = paramParcel.readInt();
    this.packageName = ParsingPackageImpl.sForInternedString.unparcel(paramParcel);
    this.intents = sForIntentInfos.unparcel(paramParcel);
    this.metaData = paramParcel.readBundle(classLoader);
  }
  
  public void addIntent(ParsedIntentInfo paramParsedIntentInfo) {
    this.intents = CollectionUtils.add(this.intents, paramParsedIntentInfo);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getBanner() {
    return this.banner;
  }
  
  public ComponentName getComponentName() {
    if (this.componentName == null)
      this.componentName = new ComponentName(getPackageName(), getName()); 
    return this.componentName;
  }
  
  public int getDescriptionRes() {
    return this.descriptionRes;
  }
  
  public int getFlags() {
    return this.flags;
  }
  
  public int getIcon() {
    return this.icon;
  }
  
  public List<ParsedIntentInfo> getIntents() {
    List<ParsedIntentInfo> list = this.intents;
    if (list == null)
      list = Collections.emptyList(); 
    return list;
  }
  
  public int getLabelRes() {
    return this.labelRes;
  }
  
  public int getLogo() {
    return this.logo;
  }
  
  public Bundle getMetaData() {
    return this.metaData;
  }
  
  public String getName() {
    return this.name;
  }
  
  public CharSequence getNonLocalizedLabel() {
    return this.nonLocalizedLabel;
  }
  
  public String getPackageName() {
    return this.packageName;
  }
  
  public ParsedComponent setName(String paramString) {
    this.name = TextUtils.safeIntern(paramString);
    return this;
  }
  
  public void setPackageName(String paramString) {
    this.packageName = TextUtils.safeIntern(paramString);
    this.componentName = null;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.name);
    paramParcel.writeInt(getIcon());
    paramParcel.writeInt(getLabelRes());
    paramParcel.writeCharSequence(getNonLocalizedLabel());
    paramParcel.writeInt(getLogo());
    paramParcel.writeInt(getBanner());
    paramParcel.writeInt(getDescriptionRes());
    paramParcel.writeInt(getFlags());
    ParsingPackageImpl.sForInternedString.parcel(this.packageName, paramParcel, paramInt);
    sForIntentInfos.parcel(getIntents(), paramParcel, paramInt);
    paramParcel.writeBundle(this.metaData);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
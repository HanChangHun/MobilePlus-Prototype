package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class ParsedMainComponent extends ParsedComponent {
  public static final Parcelable.Creator<ParsedMainComponent> CREATOR = new Parcelable.Creator<ParsedMainComponent>() {
      public ParsedMainComponent createFromParcel(Parcel param1Parcel) {
        return new ParsedMainComponent(param1Parcel);
      }
      
      public ParsedMainComponent[] newArray(int param1Int) {
        return new ParsedMainComponent[param1Int];
      }
    };
  
  boolean directBootAware;
  
  boolean enabled = true;
  
  boolean exported;
  
  int order;
  
  private String processName;
  
  String splitName;
  
  public ParsedMainComponent() {}
  
  public ParsedMainComponent(ParsedMainComponent paramParsedMainComponent) {
    super(paramParsedMainComponent);
    this.processName = paramParsedMainComponent.processName;
    this.directBootAware = paramParsedMainComponent.directBootAware;
    this.enabled = paramParsedMainComponent.enabled;
    this.exported = paramParsedMainComponent.exported;
    this.order = paramParsedMainComponent.order;
    this.splitName = paramParsedMainComponent.splitName;
  }
  
  protected ParsedMainComponent(Parcel paramParcel) {
    super(paramParcel);
    this.processName = ParsingPackageImpl.sForInternedString.unparcel(paramParcel);
    this.directBootAware = paramParcel.readBoolean();
    this.enabled = paramParcel.readBoolean();
    this.exported = paramParcel.readBoolean();
    this.order = paramParcel.readInt();
    this.splitName = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getClassName() {
    return getName();
  }
  
  public int getOrder() {
    return this.order;
  }
  
  public String getProcessName() {
    return this.processName;
  }
  
  public String getSplitName() {
    return this.splitName;
  }
  
  public boolean isDirectBootAware() {
    return this.directBootAware;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public boolean isExported() {
    return this.exported;
  }
  
  public ParsedMainComponent setDirectBootAware(boolean paramBoolean) {
    this.directBootAware = paramBoolean;
    return this;
  }
  
  public ParsedMainComponent setEnabled(boolean paramBoolean) {
    this.enabled = paramBoolean;
    return this;
  }
  
  public ParsedMainComponent setExported(boolean paramBoolean) {
    this.exported = paramBoolean;
    return this;
  }
  
  public ParsedMainComponent setProcessName(String paramString) {
    this.processName = TextUtils.safeIntern(paramString);
    return this;
  }
  
  public ParsedMainComponent setSplitName(String paramString) {
    this.splitName = paramString;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    ParsingPackageImpl.sForInternedString.parcel(this.processName, paramParcel, paramInt);
    paramParcel.writeBoolean(this.directBootAware);
    paramParcel.writeBoolean(this.enabled);
    paramParcel.writeBoolean(this.exported);
    paramParcel.writeInt(this.order);
    paramParcel.writeString(this.splitName);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedMainComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
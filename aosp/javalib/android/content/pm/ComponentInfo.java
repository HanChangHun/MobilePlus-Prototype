package android.content.pm;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.util.Printer;

public class ComponentInfo extends PackageItemInfo {
  public ApplicationInfo applicationInfo;
  
  public int descriptionRes;
  
  public boolean directBootAware;
  
  public boolean enabled;
  
  public boolean exported;
  
  public String processName;
  
  public String splitName;
  
  public ComponentInfo() {
    this.enabled = true;
    this.exported = false;
    this.directBootAware = false;
  }
  
  public ComponentInfo(ComponentInfo paramComponentInfo) {
    super(paramComponentInfo);
    this.enabled = true;
    this.exported = false;
    this.directBootAware = false;
    this.applicationInfo = paramComponentInfo.applicationInfo;
    this.processName = paramComponentInfo.processName;
    this.splitName = paramComponentInfo.splitName;
    this.descriptionRes = paramComponentInfo.descriptionRes;
    this.enabled = paramComponentInfo.enabled;
    this.exported = paramComponentInfo.exported;
    this.directBootAware = paramComponentInfo.directBootAware;
  }
  
  protected ComponentInfo(Parcel paramParcel) {
    super(paramParcel);
    boolean bool2;
    boolean bool1 = true;
    this.enabled = true;
    this.exported = false;
    this.directBootAware = false;
    this.applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(paramParcel);
    this.processName = paramParcel.readString8();
    this.splitName = paramParcel.readString8();
    this.descriptionRes = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.enabled = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.exported = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.directBootAware = bool2;
  }
  
  protected void dumpBack(Printer paramPrinter, String paramString) {
    dumpBack(paramPrinter, paramString, 3);
  }
  
  void dumpBack(Printer paramPrinter, String paramString, int paramInt) {
    if ((paramInt & 0x2) != 0)
      if (this.applicationInfo != null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append("ApplicationInfo:");
        paramPrinter.println(stringBuilder.toString());
        ApplicationInfo applicationInfo = this.applicationInfo;
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append("  ");
        applicationInfo.dump(paramPrinter, stringBuilder.toString(), paramInt);
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append("ApplicationInfo: null");
        paramPrinter.println(stringBuilder.toString());
      }  
    super.dumpBack(paramPrinter, paramString);
  }
  
  protected void dumpFront(Printer paramPrinter, String paramString) {
    super.dumpFront(paramPrinter, paramString);
    if (this.processName != null && !this.packageName.equals(this.processName)) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("processName=");
      stringBuilder1.append(this.processName);
      paramPrinter.println(stringBuilder1.toString());
    } 
    if (this.splitName != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("splitName=");
      stringBuilder1.append(this.splitName);
      paramPrinter.println(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("enabled=");
    stringBuilder.append(this.enabled);
    stringBuilder.append(" exported=");
    stringBuilder.append(this.exported);
    stringBuilder.append(" directBootAware=");
    stringBuilder.append(this.directBootAware);
    paramPrinter.println(stringBuilder.toString());
    if (this.descriptionRes != 0) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("description=");
      stringBuilder.append(this.descriptionRes);
      paramPrinter.println(stringBuilder.toString());
    } 
  }
  
  protected ApplicationInfo getApplicationInfo() {
    return this.applicationInfo;
  }
  
  public final int getBannerResource() {
    int i;
    if (this.banner != 0) {
      i = this.banner;
    } else {
      i = this.applicationInfo.banner;
    } 
    return i;
  }
  
  public ComponentName getComponentName() {
    return new ComponentName(this.packageName, this.name);
  }
  
  public final int getIconResource() {
    int i;
    if (this.icon != 0) {
      i = this.icon;
    } else {
      i = this.applicationInfo.icon;
    } 
    return i;
  }
  
  public final int getLogoResource() {
    int i;
    if (this.logo != 0) {
      i = this.logo;
    } else {
      i = this.applicationInfo.logo;
    } 
    return i;
  }
  
  public boolean isEnabled() {
    boolean bool;
    if (this.enabled && this.applicationInfo.enabled) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected Drawable loadDefaultBanner(PackageManager paramPackageManager) {
    return this.applicationInfo.loadBanner(paramPackageManager);
  }
  
  public Drawable loadDefaultIcon(PackageManager paramPackageManager) {
    return this.applicationInfo.loadIcon(paramPackageManager);
  }
  
  protected Drawable loadDefaultLogo(PackageManager paramPackageManager) {
    return this.applicationInfo.loadLogo(paramPackageManager);
  }
  
  public CharSequence loadUnsafeLabel(PackageManager paramPackageManager) {
    if (this.nonLocalizedLabel != null)
      return this.nonLocalizedLabel; 
    ApplicationInfo applicationInfo = this.applicationInfo;
    if (this.labelRes != 0) {
      CharSequence charSequence = paramPackageManager.getText(this.packageName, this.labelRes, applicationInfo);
      if (charSequence != null)
        return charSequence; 
    } 
    if (applicationInfo.nonLocalizedLabel != null)
      return applicationInfo.nonLocalizedLabel; 
    if (applicationInfo.labelRes != 0) {
      CharSequence charSequence = paramPackageManager.getText(this.packageName, applicationInfo.labelRes, applicationInfo);
      if (charSequence != null)
        return charSequence; 
    } 
    return this.name;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    this.applicationInfo.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString8(this.processName);
    paramParcel.writeString8(this.splitName);
    paramParcel.writeInt(this.descriptionRes);
    paramParcel.writeInt(this.enabled);
    paramParcel.writeInt(this.exported);
    paramParcel.writeInt(this.directBootAware);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ComponentInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
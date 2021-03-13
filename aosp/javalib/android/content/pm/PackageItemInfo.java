package android.content.pm;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Printer;
import android.util.proto.ProtoOutputStream;
import java.text.Collator;
import java.util.Comparator;
import java.util.Objects;

public class PackageItemInfo {
  public static final float DEFAULT_MAX_LABEL_SIZE_PX = 500.0F;
  
  public static final int DUMP_FLAG_ALL = 3;
  
  public static final int DUMP_FLAG_APPLICATION = 2;
  
  public static final int DUMP_FLAG_DETAILS = 1;
  
  public static final int MAX_SAFE_LABEL_LENGTH = 1000;
  
  @SystemApi
  @Deprecated
  public static final int SAFE_LABEL_FLAG_FIRST_LINE = 4;
  
  @SystemApi
  @Deprecated
  public static final int SAFE_LABEL_FLAG_SINGLE_LINE = 2;
  
  @SystemApi
  @Deprecated
  public static final int SAFE_LABEL_FLAG_TRIM = 1;
  
  private static volatile boolean sForceSafeLabels = false;
  
  public int banner;
  
  public int icon;
  
  public int labelRes;
  
  public int logo;
  
  public Bundle metaData;
  
  public String name;
  
  public CharSequence nonLocalizedLabel;
  
  public String packageName;
  
  public int showUserIcon;
  
  public PackageItemInfo() {
    this.showUserIcon = -10000;
  }
  
  public PackageItemInfo(PackageItemInfo paramPackageItemInfo) {
    String str = paramPackageItemInfo.name;
    this.name = str;
    if (str != null)
      this.name = str.trim(); 
    this.packageName = paramPackageItemInfo.packageName;
    this.labelRes = paramPackageItemInfo.labelRes;
    CharSequence charSequence = paramPackageItemInfo.nonLocalizedLabel;
    this.nonLocalizedLabel = charSequence;
    if (charSequence != null)
      this.nonLocalizedLabel = charSequence.toString().trim(); 
    this.icon = paramPackageItemInfo.icon;
    this.banner = paramPackageItemInfo.banner;
    this.logo = paramPackageItemInfo.logo;
    this.metaData = paramPackageItemInfo.metaData;
    this.showUserIcon = paramPackageItemInfo.showUserIcon;
  }
  
  protected PackageItemInfo(Parcel paramParcel) {
    this.name = paramParcel.readString8();
    this.packageName = paramParcel.readString8();
    this.labelRes = paramParcel.readInt();
    this.nonLocalizedLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.icon = paramParcel.readInt();
    this.logo = paramParcel.readInt();
    this.metaData = paramParcel.readBundle();
    this.banner = paramParcel.readInt();
    this.showUserIcon = paramParcel.readInt();
  }
  
  @SystemApi
  public static void forceSafeLabels() {
    sForceSafeLabels = true;
  }
  
  protected void dumpBack(Printer paramPrinter, String paramString) {}
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong, int paramInt) {
    paramLong = paramProtoOutputStream.start(paramLong);
    String str = this.name;
    if (str != null)
      paramProtoOutputStream.write(1138166333441L, str); 
    paramProtoOutputStream.write(1138166333442L, this.packageName);
    paramProtoOutputStream.write(1120986464259L, this.labelRes);
    CharSequence charSequence = this.nonLocalizedLabel;
    if (charSequence != null)
      paramProtoOutputStream.write(1138166333444L, charSequence.toString()); 
    paramProtoOutputStream.write(1120986464261L, this.icon);
    paramProtoOutputStream.write(1120986464262L, this.banner);
    paramProtoOutputStream.end(paramLong);
  }
  
  protected void dumpFront(Printer paramPrinter, String paramString) {
    if (this.name != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("name=");
      stringBuilder1.append(this.name);
      paramPrinter.println(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("packageName=");
    stringBuilder.append(this.packageName);
    paramPrinter.println(stringBuilder.toString());
    if (this.labelRes != 0 || this.nonLocalizedLabel != null || this.icon != 0 || this.banner != 0) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("labelRes=0x");
      stringBuilder.append(Integer.toHexString(this.labelRes));
      stringBuilder.append(" nonLocalizedLabel=");
      stringBuilder.append(this.nonLocalizedLabel);
      stringBuilder.append(" icon=0x");
      stringBuilder.append(Integer.toHexString(this.icon));
      stringBuilder.append(" banner=0x");
      stringBuilder.append(Integer.toHexString(this.banner));
      paramPrinter.println(stringBuilder.toString());
    } 
  }
  
  protected ApplicationInfo getApplicationInfo() {
    return null;
  }
  
  public Drawable loadBanner(PackageManager paramPackageManager) {
    int i = this.banner;
    if (i != 0) {
      Drawable drawable = paramPackageManager.getDrawable(this.packageName, i, getApplicationInfo());
      if (drawable != null)
        return drawable; 
    } 
    return loadDefaultBanner(paramPackageManager);
  }
  
  protected Drawable loadDefaultBanner(PackageManager paramPackageManager) {
    return null;
  }
  
  public Drawable loadDefaultIcon(PackageManager paramPackageManager) {
    return paramPackageManager.getDefaultActivityIcon();
  }
  
  protected Drawable loadDefaultLogo(PackageManager paramPackageManager) {
    return null;
  }
  
  public Drawable loadIcon(PackageManager paramPackageManager) {
    return paramPackageManager.loadItemIcon(this, getApplicationInfo());
  }
  
  public CharSequence loadLabel(PackageManager paramPackageManager) {
    return (sForceSafeLabels && !Objects.equals(this.packageName, ActivityThread.currentPackageName())) ? loadSafeLabel(paramPackageManager, 500.0F, 5) : loadUnsafeLabel(paramPackageManager);
  }
  
  public Drawable loadLogo(PackageManager paramPackageManager) {
    int i = this.logo;
    if (i != 0) {
      Drawable drawable = paramPackageManager.getDrawable(this.packageName, i, getApplicationInfo());
      if (drawable != null)
        return drawable; 
    } 
    return loadDefaultLogo(paramPackageManager);
  }
  
  @SystemApi
  @Deprecated
  public CharSequence loadSafeLabel(PackageManager paramPackageManager) {
    return loadSafeLabel(paramPackageManager, 500.0F, 5);
  }
  
  @SystemApi
  public CharSequence loadSafeLabel(PackageManager paramPackageManager, float paramFloat, int paramInt) {
    Objects.requireNonNull(paramPackageManager);
    return TextUtils.makeSafeForPresentation(loadUnsafeLabel(paramPackageManager).toString(), 1000, paramFloat, paramInt);
  }
  
  public Drawable loadUnbadgedIcon(PackageManager paramPackageManager) {
    return paramPackageManager.loadUnbadgedItemIcon(this, getApplicationInfo());
  }
  
  public CharSequence loadUnsafeLabel(PackageManager paramPackageManager) {
    CharSequence charSequence = this.nonLocalizedLabel;
    if (charSequence != null)
      return charSequence; 
    int i = this.labelRes;
    if (i != 0) {
      CharSequence charSequence1 = paramPackageManager.getText(this.packageName, i, getApplicationInfo());
      if (charSequence1 != null)
        return charSequence1.toString().trim(); 
    } 
    String str = this.name;
    return (str != null) ? str : this.packageName;
  }
  
  public XmlResourceParser loadXmlMetaData(PackageManager paramPackageManager, String paramString) {
    Bundle bundle = this.metaData;
    if (bundle != null) {
      int i = bundle.getInt(paramString);
      if (i != 0)
        return paramPackageManager.getXml(this.packageName, i, getApplicationInfo()); 
    } 
    return null;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString8(this.name);
    paramParcel.writeString8(this.packageName);
    paramParcel.writeInt(this.labelRes);
    TextUtils.writeToParcel(this.nonLocalizedLabel, paramParcel, paramInt);
    paramParcel.writeInt(this.icon);
    paramParcel.writeInt(this.logo);
    paramParcel.writeBundle(this.metaData);
    paramParcel.writeInt(this.banner);
    paramParcel.writeInt(this.showUserIcon);
  }
  
  public static class DisplayNameComparator implements Comparator<PackageItemInfo> {
    private PackageManager mPM;
    
    private final Collator sCollator = Collator.getInstance();
    
    public DisplayNameComparator(PackageManager param1PackageManager) {
      this.mPM = param1PackageManager;
    }
    
    public final int compare(PackageItemInfo param1PackageItemInfo1, PackageItemInfo param1PackageItemInfo2) {
      CharSequence charSequence2 = param1PackageItemInfo1.loadLabel(this.mPM);
      CharSequence charSequence3 = charSequence2;
      if (charSequence2 == null)
        charSequence3 = param1PackageItemInfo1.name; 
      charSequence2 = param1PackageItemInfo2.loadLabel(this.mPM);
      CharSequence charSequence1 = charSequence2;
      if (charSequence2 == null)
        charSequence1 = param1PackageItemInfo2.name; 
      return this.sCollator.compare(charSequence3.toString(), charSequence1.toString());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageItemInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
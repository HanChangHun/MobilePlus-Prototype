package android.content.pm;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Printer;
import android.util.Slog;
import java.text.Collator;
import java.util.Comparator;

public class ResolveInfo implements Parcelable {
  public static final Parcelable.Creator<ResolveInfo> CREATOR = new Parcelable.Creator<ResolveInfo>() {
      public ResolveInfo createFromParcel(Parcel param1Parcel) {
        return new ResolveInfo(param1Parcel);
      }
      
      public ResolveInfo[] newArray(int param1Int) {
        return new ResolveInfo[param1Int];
      }
    };
  
  private static final String INTENT_FORWARDER_ACTIVITY = "com.android.internal.app.IntentForwarderActivity";
  
  private static final String TAG = "ResolveInfo";
  
  public ActivityInfo activityInfo;
  
  public AuxiliaryResolveInfo auxiliaryInfo;
  
  public IntentFilter filter;
  
  @SystemApi
  public boolean handleAllWebDataURI;
  
  public int icon;
  
  public int iconResourceId;
  
  public boolean isDefault;
  
  public boolean isInstantAppAvailable;
  
  public int labelRes;
  
  public int match;
  
  public boolean noResourceId;
  
  public CharSequence nonLocalizedLabel;
  
  public int preferredOrder;
  
  public int priority;
  
  public ProviderInfo providerInfo;
  
  public String resolvePackageName;
  
  public ServiceInfo serviceInfo;
  
  public int specificIndex;
  
  public boolean system;
  
  public int targetUserId;
  
  public ResolveInfo() {
    this.specificIndex = -1;
    this.targetUserId = -2;
  }
  
  public ResolveInfo(ResolveInfo paramResolveInfo) {
    this.specificIndex = -1;
    this.activityInfo = paramResolveInfo.activityInfo;
    this.serviceInfo = paramResolveInfo.serviceInfo;
    this.providerInfo = paramResolveInfo.providerInfo;
    this.filter = paramResolveInfo.filter;
    this.priority = paramResolveInfo.priority;
    this.preferredOrder = paramResolveInfo.preferredOrder;
    this.match = paramResolveInfo.match;
    this.specificIndex = paramResolveInfo.specificIndex;
    this.labelRes = paramResolveInfo.labelRes;
    this.nonLocalizedLabel = paramResolveInfo.nonLocalizedLabel;
    this.icon = paramResolveInfo.icon;
    this.resolvePackageName = paramResolveInfo.resolvePackageName;
    this.noResourceId = paramResolveInfo.noResourceId;
    this.iconResourceId = paramResolveInfo.iconResourceId;
    this.system = paramResolveInfo.system;
    this.targetUserId = paramResolveInfo.targetUserId;
    this.handleAllWebDataURI = paramResolveInfo.handleAllWebDataURI;
    this.isInstantAppAvailable = paramResolveInfo.isInstantAppAvailable;
  }
  
  private ResolveInfo(Parcel paramParcel) {
    boolean bool2;
    this.specificIndex = -1;
    this.activityInfo = null;
    this.serviceInfo = null;
    this.providerInfo = null;
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          Slog.w("ResolveInfo", "Missing ComponentInfo!");
        } else {
          this.providerInfo = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(paramParcel);
        } 
      } else {
        this.serviceInfo = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(paramParcel);
      } 
    } else {
      this.activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(paramParcel);
    } 
    if (paramParcel.readInt() != 0)
      this.filter = (IntentFilter)IntentFilter.CREATOR.createFromParcel(paramParcel); 
    this.priority = paramParcel.readInt();
    this.preferredOrder = paramParcel.readInt();
    this.match = paramParcel.readInt();
    this.specificIndex = paramParcel.readInt();
    this.labelRes = paramParcel.readInt();
    this.nonLocalizedLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.icon = paramParcel.readInt();
    this.resolvePackageName = paramParcel.readString8();
    this.targetUserId = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.system = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.noResourceId = bool2;
    this.iconResourceId = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.handleAllWebDataURI = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.isInstantAppAvailable = bool2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    dump(paramPrinter, paramString, 3);
  }
  
  public void dump(Printer paramPrinter, String paramString, int paramInt) {
    if (this.filter != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("Filter:");
      paramPrinter.println(stringBuilder1.toString());
      IntentFilter intentFilter = this.filter;
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(paramString);
      stringBuilder2.append("  ");
      intentFilter.dump(paramPrinter, stringBuilder2.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("priority=");
    stringBuilder.append(this.priority);
    stringBuilder.append(" preferredOrder=");
    stringBuilder.append(this.preferredOrder);
    stringBuilder.append(" match=0x");
    stringBuilder.append(Integer.toHexString(this.match));
    stringBuilder.append(" specificIndex=");
    stringBuilder.append(this.specificIndex);
    stringBuilder.append(" isDefault=");
    stringBuilder.append(this.isDefault);
    paramPrinter.println(stringBuilder.toString());
    if (this.resolvePackageName != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("resolvePackageName=");
      stringBuilder.append(this.resolvePackageName);
      paramPrinter.println(stringBuilder.toString());
    } 
    if (this.labelRes != 0 || this.nonLocalizedLabel != null || this.icon != 0) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("labelRes=0x");
      stringBuilder.append(Integer.toHexString(this.labelRes));
      stringBuilder.append(" nonLocalizedLabel=");
      stringBuilder.append(this.nonLocalizedLabel);
      stringBuilder.append(" icon=0x");
      stringBuilder.append(Integer.toHexString(this.icon));
      paramPrinter.println(stringBuilder.toString());
    } 
    if (this.activityInfo != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("ActivityInfo:");
      paramPrinter.println(stringBuilder.toString());
      ActivityInfo activityInfo = this.activityInfo;
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  ");
      activityInfo.dump(paramPrinter, stringBuilder.toString(), paramInt);
    } else if (this.serviceInfo != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("ServiceInfo:");
      paramPrinter.println(stringBuilder.toString());
      ServiceInfo serviceInfo = this.serviceInfo;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("  ");
      serviceInfo.dump(paramPrinter, stringBuilder1.toString(), paramInt);
    } else if (this.providerInfo != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("ProviderInfo:");
      paramPrinter.println(stringBuilder.toString());
      ProviderInfo providerInfo = this.providerInfo;
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  ");
      providerInfo.dump(paramPrinter, stringBuilder.toString(), paramInt);
    } 
  }
  
  public ComponentInfo getComponentInfo() {
    ActivityInfo activityInfo = this.activityInfo;
    if (activityInfo != null)
      return activityInfo; 
    ServiceInfo serviceInfo = this.serviceInfo;
    if (serviceInfo != null)
      return serviceInfo; 
    ProviderInfo providerInfo = this.providerInfo;
    if (providerInfo != null)
      return providerInfo; 
    throw new IllegalStateException("Missing ComponentInfo!");
  }
  
  public final int getIconResource() {
    return this.noResourceId ? 0 : getIconResourceInternal();
  }
  
  final int getIconResourceInternal() {
    int i = this.iconResourceId;
    if (i != 0)
      return i; 
    ComponentInfo componentInfo = getComponentInfo();
    return (componentInfo != null) ? componentInfo.getIconResource() : 0;
  }
  
  public boolean isCrossProfileIntentForwarderActivity() {
    boolean bool;
    ActivityInfo activityInfo = this.activityInfo;
    if (activityInfo != null && "com.android.internal.app.IntentForwarderActivity".equals(activityInfo.targetActivity)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Drawable loadIcon(PackageManager paramPackageManager) {
    Drawable drawable1 = null;
    String str = this.resolvePackageName;
    Drawable drawable2 = drawable1;
    if (str != null) {
      int i = this.iconResourceId;
      drawable2 = drawable1;
      if (i != 0)
        drawable2 = paramPackageManager.getDrawable(str, i, null); 
    } 
    ComponentInfo componentInfo = getComponentInfo();
    drawable1 = drawable2;
    if (drawable2 == null) {
      drawable1 = drawable2;
      if (this.iconResourceId != 0) {
        ApplicationInfo applicationInfo = componentInfo.applicationInfo;
        drawable1 = paramPackageManager.getDrawable(componentInfo.packageName, this.iconResourceId, applicationInfo);
      } 
    } 
    return (drawable1 != null) ? paramPackageManager.getUserBadgedIcon(drawable1, new UserHandle(paramPackageManager.getUserId())) : componentInfo.loadIcon(paramPackageManager);
  }
  
  public CharSequence loadLabel(PackageManager paramPackageManager) {
    CharSequence charSequence3 = this.nonLocalizedLabel;
    if (charSequence3 != null)
      return charSequence3; 
    charSequence3 = this.resolvePackageName;
    if (charSequence3 != null) {
      int i = this.labelRes;
      if (i != 0) {
        charSequence3 = paramPackageManager.getText((String)charSequence3, i, null);
        if (charSequence3 != null)
          return charSequence3.toString().trim(); 
      } 
    } 
    ComponentInfo componentInfo = getComponentInfo();
    ApplicationInfo applicationInfo = componentInfo.applicationInfo;
    if (this.labelRes != 0) {
      CharSequence charSequence = paramPackageManager.getText(componentInfo.packageName, this.labelRes, applicationInfo);
      if (charSequence != null)
        return charSequence.toString().trim(); 
    } 
    CharSequence charSequence2 = componentInfo.loadLabel(paramPackageManager);
    CharSequence charSequence1 = charSequence2;
    if (charSequence2 != null)
      charSequence1 = charSequence2.toString().trim(); 
    return charSequence1;
  }
  
  public int resolveIconResId() {
    int i = this.icon;
    if (i != 0)
      return i; 
    ComponentInfo componentInfo = getComponentInfo();
    return (componentInfo.icon != 0) ? componentInfo.icon : componentInfo.applicationInfo.icon;
  }
  
  public int resolveLabelResId() {
    int i = this.labelRes;
    if (i != 0)
      return i; 
    ComponentInfo componentInfo = getComponentInfo();
    return (componentInfo.labelRes != 0) ? componentInfo.labelRes : componentInfo.applicationInfo.labelRes;
  }
  
  public String toString() {
    ComponentInfo componentInfo = getComponentInfo();
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("ResolveInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    ComponentName.appendShortString(stringBuilder, componentInfo.packageName, componentInfo.name);
    if (this.priority != 0) {
      stringBuilder.append(" p=");
      stringBuilder.append(this.priority);
    } 
    if (this.preferredOrder != 0) {
      stringBuilder.append(" o=");
      stringBuilder.append(this.preferredOrder);
    } 
    stringBuilder.append(" m=0x");
    stringBuilder.append(Integer.toHexString(this.match));
    if (this.targetUserId != -2) {
      stringBuilder.append(" targetUserId=");
      stringBuilder.append(this.targetUserId);
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.activityInfo != null) {
      paramParcel.writeInt(1);
      this.activityInfo.writeToParcel(paramParcel, paramInt);
    } else if (this.serviceInfo != null) {
      paramParcel.writeInt(2);
      this.serviceInfo.writeToParcel(paramParcel, paramInt);
    } else if (this.providerInfo != null) {
      paramParcel.writeInt(3);
      this.providerInfo.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.filter != null) {
      paramParcel.writeInt(1);
      this.filter.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.priority);
    paramParcel.writeInt(this.preferredOrder);
    paramParcel.writeInt(this.match);
    paramParcel.writeInt(this.specificIndex);
    paramParcel.writeInt(this.labelRes);
    TextUtils.writeToParcel(this.nonLocalizedLabel, paramParcel, paramInt);
    paramParcel.writeInt(this.icon);
    paramParcel.writeString8(this.resolvePackageName);
    paramParcel.writeInt(this.targetUserId);
    paramParcel.writeInt(this.system);
    paramParcel.writeInt(this.noResourceId);
    paramParcel.writeInt(this.iconResourceId);
    paramParcel.writeInt(this.handleAllWebDataURI);
    paramParcel.writeInt(this.isInstantAppAvailable);
  }
  
  public static class DisplayNameComparator implements Comparator<ResolveInfo> {
    private final Collator mCollator;
    
    private PackageManager mPM;
    
    public DisplayNameComparator(PackageManager param1PackageManager) {
      Collator collator = Collator.getInstance();
      this.mCollator = collator;
      this.mPM = param1PackageManager;
      collator.setStrength(0);
    }
    
    public final int compare(ResolveInfo param1ResolveInfo1, ResolveInfo param1ResolveInfo2) {
      if (param1ResolveInfo1.targetUserId != -2)
        return 1; 
      if (param1ResolveInfo2.targetUserId != -2)
        return -1; 
      CharSequence charSequence2 = param1ResolveInfo1.loadLabel(this.mPM);
      CharSequence charSequence3 = charSequence2;
      if (charSequence2 == null)
        charSequence3 = param1ResolveInfo1.activityInfo.name; 
      charSequence2 = param1ResolveInfo2.loadLabel(this.mPM);
      CharSequence charSequence1 = charSequence2;
      if (charSequence2 == null)
        charSequence1 = param1ResolveInfo2.activityInfo.name; 
      return this.mCollator.compare(charSequence3.toString(), charSequence1.toString());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ResolveInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
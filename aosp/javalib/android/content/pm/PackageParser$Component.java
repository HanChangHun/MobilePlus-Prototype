package android.content.pm;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.Parcel;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public abstract class Component<II extends PackageParser.IntentInfo> {
  public final String className;
  
  ComponentName componentName;
  
  String componentShortName;
  
  public final ArrayList<II> intents;
  
  public Bundle metaData;
  
  public int order;
  
  public PackageParser.Package owner;
  
  public Component(Component<II> paramComponent) {
    this.owner = paramComponent.owner;
    this.intents = paramComponent.intents;
    this.className = paramComponent.className;
    this.componentName = paramComponent.componentName;
    this.componentShortName = paramComponent.componentShortName;
  }
  
  public Component(PackageParser.Package paramPackage) {
    this.owner = paramPackage;
    this.intents = null;
    this.className = null;
  }
  
  public Component(PackageParser.Package paramPackage, ArrayList<II> paramArrayList, String paramString) {
    this.owner = paramPackage;
    this.intents = paramArrayList;
    this.className = paramString;
  }
  
  public Component(PackageParser.ParseComponentArgs paramParseComponentArgs, ComponentInfo paramComponentInfo) {
    this(paramParseComponentArgs, paramComponentInfo);
    if (paramParseComponentArgs.outError[0] != null)
      return; 
    if (paramParseComponentArgs.processRes != 0) {
      String str;
      if (this.owner.applicationInfo.targetSdkVersion >= 8) {
        str = paramParseComponentArgs.sa.getNonConfigurationString(paramParseComponentArgs.processRes, 1024);
      } else {
        str = paramParseComponentArgs.sa.getNonResourceString(paramParseComponentArgs.processRes);
      } 
      paramComponentInfo.processName = PackageParser.buildProcessName(this.owner.applicationInfo.packageName, this.owner.applicationInfo.processName, str, paramParseComponentArgs.flags, paramParseComponentArgs.sepProcesses, paramParseComponentArgs.outError);
    } 
    if (paramParseComponentArgs.descriptionRes != 0)
      paramComponentInfo.descriptionRes = paramParseComponentArgs.sa.getResourceId(paramParseComponentArgs.descriptionRes, 0); 
    paramComponentInfo.enabled = paramParseComponentArgs.sa.getBoolean(paramParseComponentArgs.enabledRes, true);
  }
  
  public Component(PackageParser.ParsePackageItemArgs paramParsePackageItemArgs, PackageItemInfo paramPackageItemInfo) {
    this.owner = paramParsePackageItemArgs.owner;
    this.intents = new ArrayList<>(0);
    if (PackageParser.access$600(paramParsePackageItemArgs.owner, paramPackageItemInfo, paramParsePackageItemArgs.outError, paramParsePackageItemArgs.tag, paramParsePackageItemArgs.sa, true, paramParsePackageItemArgs.nameRes, paramParsePackageItemArgs.labelRes, paramParsePackageItemArgs.iconRes, paramParsePackageItemArgs.roundIconRes, paramParsePackageItemArgs.logoRes, paramParsePackageItemArgs.bannerRes)) {
      this.className = paramPackageItemInfo.name;
    } else {
      this.className = null;
    } 
  }
  
  protected Component(Parcel paramParcel) {
    this.className = paramParcel.readString();
    this.metaData = paramParcel.readBundle();
    this.intents = createIntentsList(paramParcel);
    this.owner = null;
  }
  
  private static <T extends PackageParser.IntentInfo> ArrayList<T> createIntentsList(Parcel paramParcel) {
    int i = paramParcel.readInt();
    if (i == -1)
      return null; 
    if (i == 0)
      return new ArrayList<>(0); 
    String str = paramParcel.readString();
    try {
      Constructor<?> constructor = Class.forName(str).getConstructor(new Class[] { Parcel.class });
      ArrayList<PackageParser.IntentInfo> arrayList = new ArrayList();
      this(i);
      for (byte b = 0; b < i; b++) {
        arrayList.add((PackageParser.IntentInfo)constructor.newInstance(new Object[] { paramParcel }));
      } 
      return (ArrayList)arrayList;
    } catch (ReflectiveOperationException reflectiveOperationException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to construct intent list for: ");
      stringBuilder.append(str);
      throw new AssertionError(stringBuilder.toString());
    } 
  }
  
  private static void writeIntentsList(ArrayList<? extends PackageParser.IntentInfo> paramArrayList, Parcel paramParcel, int paramInt) {
    if (paramArrayList == null) {
      paramParcel.writeInt(-1);
      return;
    } 
    int i = paramArrayList.size();
    paramParcel.writeInt(i);
    if (i > 0) {
      paramParcel.writeString(((PackageParser.IntentInfo)paramArrayList.get(0)).getClass().getName());
      for (byte b = 0; b < i; b++)
        ((PackageParser.IntentInfo)paramArrayList.get(b)).writeIntentInfoToParcel(paramParcel, paramInt); 
    } 
  }
  
  public void appendComponentShortName(StringBuilder paramStringBuilder) {
    ComponentName.appendShortString(paramStringBuilder, this.owner.applicationInfo.packageName, this.className);
  }
  
  public ComponentName getComponentName() {
    ComponentName componentName = this.componentName;
    if (componentName != null)
      return componentName; 
    if (this.className != null)
      this.componentName = new ComponentName(this.owner.applicationInfo.packageName, this.className); 
    return this.componentName;
  }
  
  public void printComponentShortName(PrintWriter paramPrintWriter) {
    ComponentName.printShortString(paramPrintWriter, this.owner.applicationInfo.packageName, this.className);
  }
  
  public void setPackageName(String paramString) {
    this.componentName = null;
    this.componentShortName = null;
  }
  
  protected void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.className);
    paramParcel.writeBundle(this.metaData);
    writeIntentsList(this.intents, paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Component.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
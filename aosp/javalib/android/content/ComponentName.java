package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.proto.ProtoOutputStream;
import java.io.PrintWriter;

public final class ComponentName implements Parcelable, Cloneable, Comparable<ComponentName> {
  public static final Parcelable.Creator<ComponentName> CREATOR = new Parcelable.Creator<ComponentName>() {
      public ComponentName createFromParcel(Parcel param1Parcel) {
        return new ComponentName(param1Parcel);
      }
      
      public ComponentName[] newArray(int param1Int) {
        return new ComponentName[param1Int];
      }
    };
  
  private final String mClass;
  
  private final String mPackage;
  
  public ComponentName(Context paramContext, Class<?> paramClass) {
    this.mPackage = paramContext.getPackageName();
    this.mClass = paramClass.getName();
  }
  
  public ComponentName(Context paramContext, String paramString) {
    if (paramString != null) {
      this.mPackage = paramContext.getPackageName();
      this.mClass = paramString;
      return;
    } 
    throw new NullPointerException("class name is null");
  }
  
  public ComponentName(Parcel paramParcel) {
    String str = paramParcel.readString();
    this.mPackage = str;
    if (str != null) {
      String str1 = paramParcel.readString();
      this.mClass = str1;
      if (str1 != null)
        return; 
      throw new NullPointerException("class name is null");
    } 
    throw new NullPointerException("package name is null");
  }
  
  private ComponentName(String paramString, Parcel paramParcel) {
    this.mPackage = paramString;
    this.mClass = paramParcel.readString();
  }
  
  public ComponentName(String paramString1, String paramString2) {
    if (paramString1 != null) {
      if (paramString2 != null) {
        this.mPackage = paramString1;
        this.mClass = paramString2;
        return;
      } 
      throw new NullPointerException("class name is null");
    } 
    throw new NullPointerException("package name is null");
  }
  
  private static void appendShortClassName(StringBuilder paramStringBuilder, String paramString1, String paramString2) {
    if (paramString2.startsWith(paramString1)) {
      int i = paramString1.length();
      int j = paramString2.length();
      if (j > i && paramString2.charAt(i) == '.') {
        paramStringBuilder.append(paramString2, i, j);
        return;
      } 
    } 
    paramStringBuilder.append(paramString2);
  }
  
  public static void appendShortString(StringBuilder paramStringBuilder, String paramString1, String paramString2) {
    paramStringBuilder.append(paramString1);
    paramStringBuilder.append('/');
    appendShortClassName(paramStringBuilder, paramString1, paramString2);
  }
  
  public static ComponentName createRelative(Context paramContext, String paramString) {
    return createRelative(paramContext.getPackageName(), paramString);
  }
  
  public static ComponentName createRelative(String paramString1, String paramString2) {
    if (!TextUtils.isEmpty(paramString2)) {
      if (paramString2.charAt(0) == '.') {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString1);
        stringBuilder.append(paramString2);
        paramString2 = stringBuilder.toString();
      } 
      return new ComponentName(paramString1, paramString2);
    } 
    throw new IllegalArgumentException("class name cannot be empty");
  }
  
  public static String flattenToShortString(ComponentName paramComponentName) {
    String str;
    if (paramComponentName == null) {
      paramComponentName = null;
    } else {
      str = paramComponentName.flattenToShortString();
    } 
    return str;
  }
  
  private static void printShortClassName(PrintWriter paramPrintWriter, String paramString1, String paramString2) {
    if (paramString2.startsWith(paramString1)) {
      int i = paramString1.length();
      int j = paramString2.length();
      if (j > i && paramString2.charAt(i) == '.') {
        paramPrintWriter.write(paramString2, i, j - i);
        return;
      } 
    } 
    paramPrintWriter.print(paramString2);
  }
  
  public static void printShortString(PrintWriter paramPrintWriter, String paramString1, String paramString2) {
    paramPrintWriter.print(paramString1);
    paramPrintWriter.print('/');
    printShortClassName(paramPrintWriter, paramString1, paramString2);
  }
  
  public static ComponentName readFromParcel(Parcel paramParcel) {
    String str = paramParcel.readString();
    if (str != null) {
      ComponentName componentName = new ComponentName(str, paramParcel);
    } else {
      paramParcel = null;
    } 
    return (ComponentName)paramParcel;
  }
  
  public static ComponentName unflattenFromString(String paramString) {
    String str1;
    int i = paramString.indexOf('/');
    if (i < 0 || i + 1 >= paramString.length())
      return null; 
    String str2 = paramString.substring(0, i);
    String str3 = paramString.substring(i + 1);
    paramString = str3;
    if (str3.length() > 0) {
      paramString = str3;
      if (str3.charAt(0) == '.') {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(str3);
        str1 = stringBuilder.toString();
      } 
    } 
    return new ComponentName(str2, str1);
  }
  
  public static void writeToParcel(ComponentName paramComponentName, Parcel paramParcel) {
    if (paramComponentName != null) {
      paramComponentName.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeString(null);
    } 
  }
  
  public void appendShortString(StringBuilder paramStringBuilder) {
    appendShortString(paramStringBuilder, this.mPackage, this.mClass);
  }
  
  public ComponentName clone() {
    return new ComponentName(this.mPackage, this.mClass);
  }
  
  public int compareTo(ComponentName paramComponentName) {
    int i = this.mPackage.compareTo(paramComponentName.mPackage);
    return (i != 0) ? i : this.mClass.compareTo(paramComponentName.mClass);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1138166333441L, this.mPackage);
    paramProtoOutputStream.write(1138166333442L, this.mClass);
    paramProtoOutputStream.end(paramLong);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject != null)
      try {
        paramObject = paramObject;
        if (this.mPackage.equals(((ComponentName)paramObject).mPackage)) {
          boolean bool1 = this.mClass.equals(((ComponentName)paramObject).mClass);
          if (bool1)
            bool = true; 
        } 
        return bool;
      } catch (ClassCastException classCastException) {} 
    return false;
  }
  
  public String flattenToShortString() {
    StringBuilder stringBuilder = new StringBuilder(this.mPackage.length() + this.mClass.length());
    appendShortString(stringBuilder, this.mPackage, this.mClass);
    return stringBuilder.toString();
  }
  
  public String flattenToString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mPackage);
    stringBuilder.append("/");
    stringBuilder.append(this.mClass);
    return stringBuilder.toString();
  }
  
  public String getClassName() {
    return this.mClass;
  }
  
  public String getPackageName() {
    return this.mPackage;
  }
  
  public String getShortClassName() {
    if (this.mClass.startsWith(this.mPackage)) {
      int i = this.mPackage.length();
      int j = this.mClass.length();
      if (j > i && this.mClass.charAt(i) == '.')
        return this.mClass.substring(i, j); 
    } 
    return this.mClass;
  }
  
  public int hashCode() {
    return this.mPackage.hashCode() + this.mClass.hashCode();
  }
  
  public String toShortString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{");
    stringBuilder.append(this.mPackage);
    stringBuilder.append("/");
    stringBuilder.append(this.mClass);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ComponentInfo{");
    stringBuilder.append(this.mPackage);
    stringBuilder.append("/");
    stringBuilder.append(this.mClass);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPackage);
    paramParcel.writeString(this.mClass);
  }
  
  @FunctionalInterface
  public static interface WithComponentName {
    ComponentName getComponentName();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ComponentName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
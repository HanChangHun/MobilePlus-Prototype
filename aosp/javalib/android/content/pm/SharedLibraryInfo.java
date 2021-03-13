package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SharedLibraryInfo implements Parcelable {
  public static final Parcelable.Creator<SharedLibraryInfo> CREATOR = new Parcelable.Creator<SharedLibraryInfo>() {
      public SharedLibraryInfo createFromParcel(Parcel param1Parcel) {
        return new SharedLibraryInfo(param1Parcel);
      }
      
      public SharedLibraryInfo[] newArray(int param1Int) {
        return new SharedLibraryInfo[param1Int];
      }
    };
  
  public static final int TYPE_BUILTIN = 0;
  
  public static final int TYPE_DYNAMIC = 1;
  
  public static final int TYPE_STATIC = 2;
  
  public static final int VERSION_UNDEFINED = -1;
  
  private final List<String> mCodePaths;
  
  private final VersionedPackage mDeclaringPackage;
  
  private List<SharedLibraryInfo> mDependencies;
  
  private final List<VersionedPackage> mDependentPackages;
  
  private final String mName;
  
  private final String mPackageName;
  
  private final String mPath;
  
  private final int mType;
  
  private final long mVersion;
  
  private SharedLibraryInfo(Parcel paramParcel) {
    this.mPath = paramParcel.readString8();
    this.mPackageName = paramParcel.readString8();
    if (paramParcel.readInt() != 0) {
      this.mCodePaths = Arrays.asList(paramParcel.createString8Array());
    } else {
      this.mCodePaths = null;
    } 
    this.mName = paramParcel.readString8();
    this.mVersion = paramParcel.readLong();
    this.mType = paramParcel.readInt();
    this.mDeclaringPackage = (VersionedPackage)paramParcel.readParcelable(null);
    this.mDependentPackages = paramParcel.readArrayList(null);
    this.mDependencies = paramParcel.createTypedArrayList(CREATOR);
  }
  
  public SharedLibraryInfo(String paramString1, String paramString2, List<String> paramList, String paramString3, long paramLong, int paramInt, VersionedPackage paramVersionedPackage, List<VersionedPackage> paramList1, List<SharedLibraryInfo> paramList2) {
    this.mPath = paramString1;
    this.mPackageName = paramString2;
    this.mCodePaths = paramList;
    this.mName = paramString3;
    this.mVersion = paramLong;
    this.mType = paramInt;
    this.mDeclaringPackage = paramVersionedPackage;
    this.mDependentPackages = paramList1;
    this.mDependencies = paramList2;
  }
  
  private static String typeToString(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? "unknown" : "static") : "dynamic") : "builtin";
  }
  
  public void addDependency(SharedLibraryInfo paramSharedLibraryInfo) {
    if (paramSharedLibraryInfo == null)
      return; 
    if (this.mDependencies == null)
      this.mDependencies = new ArrayList<>(); 
    this.mDependencies.add(paramSharedLibraryInfo);
  }
  
  public void clearDependencies() {
    this.mDependencies = null;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public List<String> getAllCodePaths() {
    if (getPath() != null) {
      ArrayList<String> arrayList = new ArrayList();
      arrayList.add(getPath());
      return arrayList;
    } 
    return this.mCodePaths;
  }
  
  public VersionedPackage getDeclaringPackage() {
    return this.mDeclaringPackage;
  }
  
  public List<SharedLibraryInfo> getDependencies() {
    return this.mDependencies;
  }
  
  public List<VersionedPackage> getDependentPackages() {
    List<VersionedPackage> list = this.mDependentPackages;
    return (list == null) ? Collections.emptyList() : list;
  }
  
  public long getLongVersion() {
    return this.mVersion;
  }
  
  public String getName() {
    return this.mName;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public String getPath() {
    return this.mPath;
  }
  
  public int getType() {
    return this.mType;
  }
  
  @Deprecated
  public int getVersion() {
    long l = this.mVersion;
    if (l >= 0L)
      l &= 0x7FFFFFFFL; 
    return (int)l;
  }
  
  public boolean isBuiltin() {
    boolean bool;
    if (this.mType == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDynamic() {
    int i = this.mType;
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isStatic() {
    boolean bool;
    if (this.mType == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SharedLibraryInfo{name:");
    stringBuilder.append(this.mName);
    stringBuilder.append(", type:");
    stringBuilder.append(typeToString(this.mType));
    stringBuilder.append(", version:");
    stringBuilder.append(this.mVersion);
    if (!getDependentPackages().isEmpty()) {
      str = " has dependents";
    } else {
      str = "";
    } 
    stringBuilder.append(str);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString8(this.mPath);
    paramParcel.writeString8(this.mPackageName);
    if (this.mCodePaths != null) {
      paramParcel.writeInt(1);
      List<String> list = this.mCodePaths;
      paramParcel.writeString8Array(list.<String>toArray(new String[list.size()]));
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeString8(this.mName);
    paramParcel.writeLong(this.mVersion);
    paramParcel.writeInt(this.mType);
    paramParcel.writeParcelable(this.mDeclaringPackage, paramInt);
    paramParcel.writeList(this.mDependentPackages);
    paramParcel.writeTypedList(this.mDependencies);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface Type {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/SharedLibraryInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
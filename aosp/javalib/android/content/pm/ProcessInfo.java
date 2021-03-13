package android.content.pm;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Parcelling;

public class ProcessInfo implements Parcelable {
  public static final Parcelable.Creator<ProcessInfo> CREATOR;
  
  static Parcelling<ArraySet<String>> sParcellingForDeniedPermissions;
  
  public ArraySet<String> deniedPermissions;
  
  public int gwpAsanMode;
  
  public String name;
  
  static {
    Parcelling<ArraySet<String>> parcelling = Parcelling.Cache.get(Parcelling.BuiltIn.ForInternedStringArraySet.class);
    sParcellingForDeniedPermissions = parcelling;
    if (parcelling == null)
      sParcellingForDeniedPermissions = Parcelling.Cache.put((Parcelling)new Parcelling.BuiltIn.ForInternedStringArraySet()); 
    CREATOR = new Parcelable.Creator<ProcessInfo>() {
        public ProcessInfo createFromParcel(Parcel param1Parcel) {
          return new ProcessInfo(param1Parcel);
        }
        
        public ProcessInfo[] newArray(int param1Int) {
          return new ProcessInfo[param1Int];
        }
      };
  }
  
  @Deprecated
  public ProcessInfo(ProcessInfo paramProcessInfo) {
    this.name = paramProcessInfo.name;
    this.deniedPermissions = paramProcessInfo.deniedPermissions;
    this.gwpAsanMode = paramProcessInfo.gwpAsanMode;
  }
  
  protected ProcessInfo(Parcel paramParcel) {
    paramParcel.readByte();
    String str = paramParcel.readString();
    ArraySet<String> arraySet = (ArraySet)sParcellingForDeniedPermissions.unparcel(paramParcel);
    int i = paramParcel.readInt();
    this.name = str;
    AnnotationValidations.validate(NonNull.class, null, str);
    this.deniedPermissions = arraySet;
    this.gwpAsanMode = i;
    AnnotationValidations.validate(ApplicationInfo.GwpAsanMode.class, null, i);
  }
  
  public ProcessInfo(String paramString, ArraySet<String> paramArraySet, int paramInt) {
    this.name = paramString;
    AnnotationValidations.validate(NonNull.class, null, paramString);
    this.deniedPermissions = paramArraySet;
    this.gwpAsanMode = paramInt;
    AnnotationValidations.validate(ApplicationInfo.GwpAsanMode.class, null, paramInt);
  }
  
  @Deprecated
  private void __metadata() {}
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    byte b1 = 0;
    byte b2 = b1;
    if (this.deniedPermissions != null) {
      b1 = (byte)(0x0 | 0x2);
      b2 = b1;
    } 
    paramParcel.writeByte(b2);
    paramParcel.writeString(this.name);
    sParcellingForDeniedPermissions.parcel(this.deniedPermissions, paramParcel, paramInt);
    paramParcel.writeInt(this.gwpAsanMode);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ProcessInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
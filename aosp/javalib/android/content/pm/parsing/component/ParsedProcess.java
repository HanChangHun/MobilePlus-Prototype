package android.content.pm.parsing.component;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.Parcelling;
import java.util.Collections;
import java.util.Set;

public class ParsedProcess implements Parcelable {
  public static final Parcelable.Creator<ParsedProcess> CREATOR;
  
  static Parcelling<Set<String>> sParcellingForDeniedPermissions;
  
  protected Set<String> deniedPermissions = Collections.emptySet();
  
  protected int gwpAsanMode = -1;
  
  protected String name;
  
  static {
    Parcelling<Set<String>> parcelling = Parcelling.Cache.get(Parcelling.BuiltIn.ForInternedStringSet.class);
    sParcellingForDeniedPermissions = parcelling;
    if (parcelling == null)
      sParcellingForDeniedPermissions = Parcelling.Cache.put((Parcelling)new Parcelling.BuiltIn.ForInternedStringSet()); 
    CREATOR = new Parcelable.Creator<ParsedProcess>() {
        public ParsedProcess createFromParcel(Parcel param1Parcel) {
          return new ParsedProcess(param1Parcel);
        }
        
        public ParsedProcess[] newArray(int param1Int) {
          return new ParsedProcess[param1Int];
        }
      };
  }
  
  public ParsedProcess() {}
  
  public ParsedProcess(ParsedProcess paramParsedProcess) {
    this.name = paramParsedProcess.name;
    this.deniedPermissions = (Set<String>)new ArraySet(paramParsedProcess.deniedPermissions);
  }
  
  protected ParsedProcess(Parcel paramParcel) {
    String str = paramParcel.readString();
    Set<String> set = (Set)sParcellingForDeniedPermissions.unparcel(paramParcel);
    int i = paramParcel.readInt();
    this.name = str;
    AnnotationValidations.validate(NonNull.class, null, str);
    this.deniedPermissions = set;
    AnnotationValidations.validate(NonNull.class, null, set);
    this.gwpAsanMode = i;
  }
  
  public ParsedProcess(String paramString, Set<String> paramSet, int paramInt) {
    this.name = paramString;
    AnnotationValidations.validate(NonNull.class, null, paramString);
    this.deniedPermissions = paramSet;
    AnnotationValidations.validate(NonNull.class, null, paramSet);
    this.gwpAsanMode = paramInt;
  }
  
  @Deprecated
  private void __metadata() {}
  
  public void addStateFrom(ParsedProcess paramParsedProcess) {
    this.deniedPermissions = CollectionUtils.addAll(this.deniedPermissions, paramParsedProcess.deniedPermissions);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Set<String> getDeniedPermissions() {
    return this.deniedPermissions;
  }
  
  public int getGwpAsanMode() {
    return this.gwpAsanMode;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.name);
    sParcellingForDeniedPermissions.parcel(this.deniedPermissions, paramParcel, paramInt);
    paramParcel.writeInt(this.gwpAsanMode);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedProcess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class ParsedInstrumentation extends ParsedComponent {
  public static final Parcelable.Creator<ParsedInstrumentation> CREATOR = new Parcelable.Creator<ParsedInstrumentation>() {
      public ParsedInstrumentation createFromParcel(Parcel param1Parcel) {
        return new ParsedInstrumentation(param1Parcel);
      }
      
      public ParsedInstrumentation[] newArray(int param1Int) {
        return new ParsedInstrumentation[param1Int];
      }
    };
  
  boolean functionalTest;
  
  boolean handleProfiling;
  
  private String targetPackage;
  
  private String targetProcesses;
  
  public ParsedInstrumentation() {}
  
  protected ParsedInstrumentation(Parcel paramParcel) {
    super(paramParcel);
    boolean bool2;
    this.targetPackage = ParsingPackageImpl.sForInternedString.unparcel(paramParcel);
    this.targetProcesses = ParsingPackageImpl.sForInternedString.unparcel(paramParcel);
    byte b = paramParcel.readByte();
    boolean bool1 = true;
    if (b != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.handleProfiling = bool2;
    if (paramParcel.readByte() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.functionalTest = bool2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getTargetPackage() {
    return this.targetPackage;
  }
  
  public String getTargetProcesses() {
    return this.targetProcesses;
  }
  
  public boolean isFunctionalTest() {
    return this.functionalTest;
  }
  
  public boolean isHandleProfiling() {
    return this.handleProfiling;
  }
  
  public void setTargetPackage(String paramString) {
    this.targetPackage = TextUtils.safeIntern(paramString);
  }
  
  public void setTargetProcesses(String paramString) {
    this.targetProcesses = TextUtils.safeIntern(paramString);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("Instrumentation{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    ComponentName.appendShortString(stringBuilder, getPackageName(), getName());
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    ParsingPackageImpl.sForInternedString.parcel(this.targetPackage, paramParcel, paramInt);
    ParsingPackageImpl.sForInternedString.parcel(this.targetProcesses, paramParcel, paramInt);
    paramParcel.writeBoolean(this.handleProfiling);
    paramParcel.writeBoolean(this.functionalTest);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedInstrumentation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
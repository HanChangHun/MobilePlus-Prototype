package android.content.pm.parsing.component;

import android.os.Parcel;
import android.os.Parcelable;

public class ParsedPermissionGroup extends ParsedComponent {
  public static final Parcelable.Creator<ParsedPermissionGroup> CREATOR = new Parcelable.Creator<ParsedPermissionGroup>() {
      public ParsedPermissionGroup createFromParcel(Parcel param1Parcel) {
        return new ParsedPermissionGroup(param1Parcel);
      }
      
      public ParsedPermissionGroup[] newArray(int param1Int) {
        return new ParsedPermissionGroup[param1Int];
      }
    };
  
  int backgroundRequestDetailResourceId;
  
  int backgroundRequestResourceId;
  
  int priority;
  
  int requestDetailResourceId;
  
  int requestRes;
  
  public ParsedPermissionGroup() {}
  
  protected ParsedPermissionGroup(Parcel paramParcel) {
    super(paramParcel);
    this.requestDetailResourceId = paramParcel.readInt();
    this.backgroundRequestResourceId = paramParcel.readInt();
    this.backgroundRequestDetailResourceId = paramParcel.readInt();
    this.requestRes = paramParcel.readInt();
    this.priority = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getBackgroundRequestDetailResourceId() {
    return this.backgroundRequestDetailResourceId;
  }
  
  public int getBackgroundRequestResourceId() {
    return this.backgroundRequestResourceId;
  }
  
  public int getPriority() {
    return this.priority;
  }
  
  public int getRequestDetailResourceId() {
    return this.requestDetailResourceId;
  }
  
  public int getRequestRes() {
    return this.requestRes;
  }
  
  public void setPriority(int paramInt) {
    this.priority = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PermissionGroup{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(getName());
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.requestDetailResourceId);
    paramParcel.writeInt(this.backgroundRequestResourceId);
    paramParcel.writeInt(this.backgroundRequestDetailResourceId);
    paramParcel.writeInt(this.requestRes);
    paramParcel.writeInt(this.priority);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedPermissionGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
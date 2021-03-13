package android.content.pm;

import android.content.ComponentName;
import android.content.LocusId;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import java.util.ArrayList;
import java.util.List;

public final class ShortcutQueryWrapper extends LauncherApps.ShortcutQuery implements Parcelable {
  public static final Parcelable.Creator<ShortcutQueryWrapper> CREATOR = new Parcelable.Creator<ShortcutQueryWrapper>() {
      public ShortcutQueryWrapper createFromParcel(Parcel param1Parcel) {
        return new ShortcutQueryWrapper(param1Parcel);
      }
      
      public ShortcutQueryWrapper[] newArray(int param1Int) {
        return new ShortcutQueryWrapper[param1Int];
      }
    };
  
  public ShortcutQueryWrapper() {}
  
  public ShortcutQueryWrapper(LauncherApps.ShortcutQuery paramShortcutQuery) {
    this();
    this.mChangedSince = paramShortcutQuery.mChangedSince;
    this.mPackage = paramShortcutQuery.mPackage;
    this.mLocusIds = paramShortcutQuery.mLocusIds;
    this.mShortcutIds = paramShortcutQuery.mShortcutIds;
    this.mActivity = paramShortcutQuery.mActivity;
    this.mQueryFlags = paramShortcutQuery.mQueryFlags;
  }
  
  ShortcutQueryWrapper(Parcel paramParcel) {
    String str;
    ComponentName componentName;
    byte b = paramParcel.readByte();
    long l = paramParcel.readLong();
    if ((b & 0x2) == 0) {
      str = null;
    } else {
      str = paramParcel.readString();
    } 
    ArrayList<String> arrayList = null;
    if ((b & 0x4) != 0) {
      arrayList = new ArrayList();
      paramParcel.readStringList(arrayList);
    } 
    ArrayList<LocusId> arrayList1 = null;
    if ((b & 0x8) != 0) {
      arrayList1 = new ArrayList();
      paramParcel.readParcelableList(arrayList1, LocusId.class.getClassLoader());
    } 
    if ((b & 0x10) == 0) {
      componentName = null;
    } else {
      componentName = (ComponentName)paramParcel.readTypedObject(ComponentName.CREATOR);
    } 
    int i = paramParcel.readInt();
    this.mChangedSince = l;
    this.mPackage = str;
    this.mShortcutIds = arrayList;
    this.mLocusIds = arrayList1;
    this.mActivity = componentName;
    this.mQueryFlags = i;
    AnnotationValidations.validate(LauncherApps.ShortcutQuery.QueryFlags.class, null, this.mQueryFlags);
  }
  
  @Deprecated
  private void __metadata() {}
  
  public int describeContents() {
    return 0;
  }
  
  public ComponentName getActivity() {
    return this.mActivity;
  }
  
  public long getChangedSince() {
    return this.mChangedSince;
  }
  
  public List<LocusId> getLocusIds() {
    return this.mLocusIds;
  }
  
  public String getPackage() {
    return this.mPackage;
  }
  
  public int getQueryFlags() {
    return this.mQueryFlags;
  }
  
  public List<String> getShortcutIds() {
    return this.mShortcutIds;
  }
  
  public String toString() {
    return "ShortcutQueryWrapper {  }";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    byte b1 = 0;
    if (this.mPackage != null)
      b1 = (byte)(0x0 | 0x2); 
    byte b2 = b1;
    if (this.mShortcutIds != null)
      b2 = (byte)(b1 | 0x4); 
    b1 = b2;
    if (this.mLocusIds != null)
      b1 = (byte)(b2 | 0x8); 
    byte b3 = b1;
    if (this.mActivity != null) {
      b2 = (byte)(b1 | 0x10);
      b3 = b2;
    } 
    paramParcel.writeByte(b3);
    paramParcel.writeLong(this.mChangedSince);
    if (this.mPackage != null)
      paramParcel.writeString(this.mPackage); 
    if (this.mShortcutIds != null)
      paramParcel.writeStringList(this.mShortcutIds); 
    if (this.mLocusIds != null)
      paramParcel.writeParcelableList(this.mLocusIds, paramInt); 
    if (this.mActivity != null)
      paramParcel.writeTypedObject((Parcelable)this.mActivity, paramInt); 
    paramParcel.writeInt(this.mQueryFlags);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ShortcutQueryWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
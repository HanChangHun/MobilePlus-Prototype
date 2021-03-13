package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import java.time.MonthDay;

class null implements Parcelable.Creator<SystemUpdatePolicy> {
  public SystemUpdatePolicy createFromParcel(Parcel paramParcel) {
    SystemUpdatePolicy systemUpdatePolicy = new SystemUpdatePolicy(null);
    SystemUpdatePolicy.access$202(systemUpdatePolicy, paramParcel.readInt());
    SystemUpdatePolicy.access$302(systemUpdatePolicy, paramParcel.readInt());
    SystemUpdatePolicy.access$402(systemUpdatePolicy, paramParcel.readInt());
    int i = paramParcel.readInt();
    SystemUpdatePolicy.access$500(systemUpdatePolicy).ensureCapacity(i);
    for (byte b = 0; b < i; b++) {
      MonthDay monthDay1 = MonthDay.of(paramParcel.readInt(), paramParcel.readInt());
      MonthDay monthDay2 = MonthDay.of(paramParcel.readInt(), paramParcel.readInt());
      SystemUpdatePolicy.access$500(systemUpdatePolicy).add(new FreezePeriod(monthDay1, monthDay2));
    } 
    return systemUpdatePolicy;
  }
  
  public SystemUpdatePolicy[] newArray(int paramInt) {
    return new SystemUpdatePolicy[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SystemUpdatePolicy$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
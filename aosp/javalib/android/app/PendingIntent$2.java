package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PendingIntent> {
  public PendingIntent createFromParcel(Parcel paramParcel) {
    IBinder iBinder = paramParcel.readStrongBinder();
    if (iBinder != null) {
      PendingIntent pendingIntent = new PendingIntent(iBinder, paramParcel.getClassCookie(PendingIntent.class));
    } else {
      paramParcel = null;
    } 
    return (PendingIntent)paramParcel;
  }
  
  public PendingIntent[] newArray(int paramInt) {
    return new PendingIntent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PendingIntent$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
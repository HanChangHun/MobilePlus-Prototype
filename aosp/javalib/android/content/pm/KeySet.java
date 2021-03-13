package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public class KeySet implements Parcelable {
  public static final Parcelable.Creator<KeySet> CREATOR = new Parcelable.Creator<KeySet>() {
      public KeySet createFromParcel(Parcel param1Parcel) {
        return KeySet.readFromParcel(param1Parcel);
      }
      
      public KeySet[] newArray(int param1Int) {
        return new KeySet[param1Int];
      }
    };
  
  private IBinder token;
  
  public KeySet(IBinder paramIBinder) {
    if (paramIBinder != null) {
      this.token = paramIBinder;
      return;
    } 
    throw new NullPointerException("null value for KeySet IBinder token");
  }
  
  private static KeySet readFromParcel(Parcel paramParcel) {
    return new KeySet(paramParcel.readStrongBinder());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof KeySet;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      if (this.token == ((KeySet)paramObject).token)
        bool1 = true; 
      return bool1;
    } 
    return false;
  }
  
  public IBinder getToken() {
    return this.token;
  }
  
  public int hashCode() {
    return this.token.hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStrongBinder(this.token);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/KeySet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
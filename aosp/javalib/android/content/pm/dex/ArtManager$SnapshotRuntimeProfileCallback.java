package android.content.pm.dex;

import android.os.ParcelFileDescriptor;

public abstract class SnapshotRuntimeProfileCallback {
  public abstract void onError(int paramInt);
  
  public abstract void onSuccess(ParcelFileDescriptor paramParcelFileDescriptor);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/ArtManager$SnapshotRuntimeProfileCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
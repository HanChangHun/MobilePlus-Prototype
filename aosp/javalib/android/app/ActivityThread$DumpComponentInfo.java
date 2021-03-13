package android.app;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;

final class DumpComponentInfo {
  String[] args;
  
  ParcelFileDescriptor fd;
  
  String prefix;
  
  IBinder token;
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$DumpComponentInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
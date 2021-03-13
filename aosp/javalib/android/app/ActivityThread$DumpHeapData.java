package android.app;

import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;

final class DumpHeapData {
  ParcelFileDescriptor fd;
  
  RemoteCallback finishCallback;
  
  public boolean mallocInfo;
  
  public boolean managed;
  
  String path;
  
  public boolean runGc;
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$DumpHeapData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
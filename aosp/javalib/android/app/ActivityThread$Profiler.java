package android.app;

import android.os.Debug;
import android.os.ParcelFileDescriptor;
import android.os.SystemProperties;
import android.util.Slog;
import dalvik.system.VMDebug;
import java.io.FileDescriptor;
import java.io.IOException;

final class Profiler {
  boolean autoStopProfiler;
  
  boolean handlingProfiling;
  
  ParcelFileDescriptor profileFd;
  
  String profileFile;
  
  boolean profiling;
  
  int samplingInterval;
  
  boolean streamingOutput;
  
  public void setProfiler(ProfilerInfo paramProfilerInfo) {
    ParcelFileDescriptor parcelFileDescriptor1 = paramProfilerInfo.profileFd;
    if (this.profiling) {
      if (parcelFileDescriptor1 != null)
        try {
          parcelFileDescriptor1.close();
        } catch (IOException iOException) {} 
      return;
    } 
    ParcelFileDescriptor parcelFileDescriptor2 = this.profileFd;
    if (parcelFileDescriptor2 != null)
      try {
        parcelFileDescriptor2.close();
      } catch (IOException iOException1) {} 
    this.profileFile = ((ProfilerInfo)iOException).profileFile;
    this.profileFd = parcelFileDescriptor1;
    this.samplingInterval = ((ProfilerInfo)iOException).samplingInterval;
    this.autoStopProfiler = ((ProfilerInfo)iOException).autoStopProfiler;
    this.streamingOutput = ((ProfilerInfo)iOException).streamingOutput;
  }
  
  public void startProfiling() {
    if (this.profileFd == null || this.profiling)
      return; 
    try {
      boolean bool;
      int i = SystemProperties.getInt("debug.traceview-buffer-size-mb", 8);
      String str = this.profileFile;
      FileDescriptor fileDescriptor = this.profileFd.getFileDescriptor();
      if (this.samplingInterval != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      VMDebug.startMethodTracing(str, fileDescriptor, i * 1024 * 1024, 0, bool, this.samplingInterval, this.streamingOutput);
      this.profiling = true;
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Profiling failed on path ");
      stringBuilder.append(this.profileFile);
      Slog.w("ActivityThread", stringBuilder.toString(), runtimeException);
      try {
        this.profileFd.close();
        this.profileFd = null;
      } catch (IOException iOException) {
        Slog.w("ActivityThread", "Failure closing profile fd", iOException);
      } 
    } 
  }
  
  public void stopProfiling() {
    if (this.profiling) {
      this.profiling = false;
      Debug.stopMethodTracing();
      ParcelFileDescriptor parcelFileDescriptor = this.profileFd;
      if (parcelFileDescriptor != null)
        try {
          parcelFileDescriptor.close();
        } catch (IOException iOException) {} 
      this.profileFd = null;
      this.profileFile = null;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$Profiler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
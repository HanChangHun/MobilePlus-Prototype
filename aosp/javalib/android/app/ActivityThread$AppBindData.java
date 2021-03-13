package android.app;

import android.content.AutofillOptions;
import android.content.ComponentName;
import android.content.ContentCaptureOptions;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.List;

final class AppBindData {
  ApplicationInfo appInfo;
  
  AutofillOptions autofillOptions;
  
  String buildSerial;
  
  CompatibilityInfo compatInfo;
  
  Configuration config;
  
  ContentCaptureOptions contentCaptureOptions;
  
  int debugMode;
  
  long[] disabledCompatChanges;
  
  boolean enableBinderTracking;
  
  LoadedApk info;
  
  ProfilerInfo initProfilerInfo;
  
  Bundle instrumentationArgs;
  
  ComponentName instrumentationName;
  
  IUiAutomationConnection instrumentationUiAutomationConnection;
  
  IInstrumentationWatcher instrumentationWatcher;
  
  boolean persistent;
  
  String processName;
  
  List<ProviderInfo> providers;
  
  boolean restrictedBackupMode;
  
  boolean trackAllocation;
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AppBindData{appInfo=");
    stringBuilder.append(this.appInfo);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$AppBindData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
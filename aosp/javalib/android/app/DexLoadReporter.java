package android.app;

import android.os.FileUtils;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Slog;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.VMRuntime;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class DexLoadReporter implements BaseDexClassLoader.Reporter {
  private static final boolean DEBUG = false;
  
  private static final DexLoadReporter INSTANCE = new DexLoadReporter();
  
  private static final String TAG = "DexLoadReporter";
  
  private final Set<String> mDataDirs = new HashSet<>();
  
  static DexLoadReporter getInstance() {
    return INSTANCE;
  }
  
  private boolean isSecondaryDexFile(String paramString, String[] paramArrayOfString) {
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (FileUtils.contains(paramArrayOfString[b], paramString))
        return true; 
    } 
    return false;
  }
  
  private void notifyPackageManager(Map<String, String> paramMap) {
    String str = ActivityThread.currentPackageName();
    try {
      ActivityThread.getPackageManager().notifyDexLoad(str, paramMap, VMRuntime.getRuntime().vmInstructionSet());
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to notify PM about dex load for package ");
      stringBuilder.append(str);
      Slog.e("DexLoadReporter", stringBuilder.toString(), (Throwable)remoteException);
    } 
  }
  
  private void registerSecondaryDexForProfiling(String paramString, String[] paramArrayOfString) {
    StringBuilder stringBuilder1;
    if (!isSecondaryDexFile(paramString, paramArrayOfString))
      return; 
    File file2 = new File(paramString);
    File file1 = new File(file2.getParent(), "oat");
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(file2.getName());
    stringBuilder2.append(".cur.prof");
    File file3 = new File(file1, stringBuilder2.toString());
    if (!file1.exists() && !file1.mkdir()) {
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Could not create the profile directory: ");
      stringBuilder1.append(file3);
      Slog.e("DexLoadReporter", stringBuilder1.toString());
      return;
    } 
    try {
      file3.createNewFile();
      VMRuntime.registerAppInfo(file3.getPath(), new String[] { (String)stringBuilder1 });
      return;
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to create profile for secondary dex ");
      stringBuilder.append((String)stringBuilder1);
      stringBuilder.append(":");
      stringBuilder.append(iOException.getMessage());
      Slog.e("DexLoadReporter", stringBuilder.toString());
      return;
    } 
  }
  
  private void registerSecondaryDexForProfiling(Set<String> paramSet) {
    if (!SystemProperties.getBoolean("dalvik.vm.dexopt.secondary", false))
      return; 
    synchronized (this.mDataDirs) {
      String[] arrayOfString = this.mDataDirs.<String>toArray(new String[0]);
      Iterator<String> iterator = paramSet.iterator();
      while (iterator.hasNext())
        registerSecondaryDexForProfiling(iterator.next(), arrayOfString); 
      return;
    } 
  }
  
  void registerAppDataDir(String paramString1, String paramString2) {
    if (paramString2 != null)
      synchronized (this.mDataDirs) {
        this.mDataDirs.add(paramString2);
      }  
  }
  
  public void report(Map<String, String> paramMap) {
    if (paramMap.isEmpty()) {
      Slog.wtf("DexLoadReporter", "Bad call to DexLoadReporter: empty classLoaderContextMap");
      return;
    } 
    notifyPackageManager(paramMap);
    registerSecondaryDexForProfiling(paramMap.keySet());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DexLoadReporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
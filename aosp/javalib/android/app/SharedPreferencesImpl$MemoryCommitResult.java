package android.app;

import android.content.SharedPreferences;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

class MemoryCommitResult {
  final boolean keysCleared;
  
  final List<String> keysModified;
  
  final Set<SharedPreferences.OnSharedPreferenceChangeListener> listeners;
  
  final Map<String, Object> mapToWriteToDisk;
  
  final long memoryStateGeneration;
  
  boolean wasWritten = false;
  
  volatile boolean writeToDiskResult = false;
  
  final CountDownLatch writtenToDiskLatch = new CountDownLatch(1);
  
  private MemoryCommitResult(long paramLong, boolean paramBoolean, List<String> paramList, Set<SharedPreferences.OnSharedPreferenceChangeListener> paramSet, Map<String, Object> paramMap) {
    this.memoryStateGeneration = paramLong;
    this.keysCleared = paramBoolean;
    this.keysModified = paramList;
    this.listeners = paramSet;
    this.mapToWriteToDisk = paramMap;
  }
  
  void setDiskWriteResult(boolean paramBoolean1, boolean paramBoolean2) {
    this.wasWritten = paramBoolean1;
    this.writeToDiskResult = paramBoolean2;
    this.writtenToDiskLatch.countDown();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SharedPreferencesImpl$MemoryCommitResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
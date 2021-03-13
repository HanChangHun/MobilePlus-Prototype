package android.app;

import android.compat.Compatibility;
import android.content.SharedPreferences;
import android.os.Looper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class EditorImpl implements SharedPreferences.Editor {
  private boolean mClear = false;
  
  private final Object mEditorLock = new Object();
  
  private final Map<String, Object> mModified = new HashMap<>();
  
  private SharedPreferencesImpl.MemoryCommitResult commitToMemory() {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: aconst_null
    //   5: astore_3
    //   6: aload_0
    //   7: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   10: invokestatic access$200 : (Landroid/app/SharedPreferencesImpl;)Ljava/lang/Object;
    //   13: astore #4
    //   15: aload #4
    //   17: monitorenter
    //   18: aload_0
    //   19: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   22: invokestatic access$300 : (Landroid/app/SharedPreferencesImpl;)I
    //   25: ifle -> 59
    //   28: aload_0
    //   29: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   32: astore #5
    //   34: new java/util/HashMap
    //   37: astore #6
    //   39: aload #6
    //   41: aload_0
    //   42: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   45: invokestatic access$400 : (Landroid/app/SharedPreferencesImpl;)Ljava/util/Map;
    //   48: invokespecial <init> : (Ljava/util/Map;)V
    //   51: aload #5
    //   53: aload #6
    //   55: invokestatic access$402 : (Landroid/app/SharedPreferencesImpl;Ljava/util/Map;)Ljava/util/Map;
    //   58: pop
    //   59: aload_0
    //   60: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   63: invokestatic access$400 : (Landroid/app/SharedPreferencesImpl;)Ljava/util/Map;
    //   66: astore #5
    //   68: aload_0
    //   69: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   72: invokestatic access$308 : (Landroid/app/SharedPreferencesImpl;)I
    //   75: pop
    //   76: aload_0
    //   77: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   80: invokestatic access$500 : (Landroid/app/SharedPreferencesImpl;)Ljava/util/WeakHashMap;
    //   83: invokevirtual size : ()I
    //   86: ifle -> 95
    //   89: iconst_1
    //   90: istore #7
    //   92: goto -> 98
    //   95: iconst_0
    //   96: istore #7
    //   98: iload #7
    //   100: ifeq -> 129
    //   103: new java/util/ArrayList
    //   106: astore_2
    //   107: aload_2
    //   108: invokespecial <init> : ()V
    //   111: new java/util/HashSet
    //   114: astore_3
    //   115: aload_3
    //   116: aload_0
    //   117: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   120: invokestatic access$500 : (Landroid/app/SharedPreferencesImpl;)Ljava/util/WeakHashMap;
    //   123: invokevirtual keySet : ()Ljava/util/Set;
    //   126: invokespecial <init> : (Ljava/util/Collection;)V
    //   129: aload_0
    //   130: getfield mEditorLock : Ljava/lang/Object;
    //   133: astore #6
    //   135: aload #6
    //   137: monitorenter
    //   138: iconst_0
    //   139: istore #8
    //   141: iconst_0
    //   142: istore #9
    //   144: aload_0
    //   145: getfield mClear : Z
    //   148: ifeq -> 182
    //   151: iload #9
    //   153: istore #8
    //   155: aload #5
    //   157: invokeinterface isEmpty : ()Z
    //   162: ifne -> 175
    //   165: iconst_1
    //   166: istore #8
    //   168: aload #5
    //   170: invokeinterface clear : ()V
    //   175: iconst_1
    //   176: istore_1
    //   177: aload_0
    //   178: iconst_0
    //   179: putfield mClear : Z
    //   182: aload_0
    //   183: getfield mModified : Ljava/util/Map;
    //   186: invokeinterface entrySet : ()Ljava/util/Set;
    //   191: invokeinterface iterator : ()Ljava/util/Iterator;
    //   196: astore #10
    //   198: aload #10
    //   200: invokeinterface hasNext : ()Z
    //   205: ifeq -> 356
    //   208: aload #10
    //   210: invokeinterface next : ()Ljava/lang/Object;
    //   215: checkcast java/util/Map$Entry
    //   218: astore #11
    //   220: aload #11
    //   222: invokeinterface getKey : ()Ljava/lang/Object;
    //   227: checkcast java/lang/String
    //   230: astore #12
    //   232: aload #11
    //   234: invokeinterface getValue : ()Ljava/lang/Object;
    //   239: astore #13
    //   241: aload #13
    //   243: aload_0
    //   244: if_acmpeq -> 311
    //   247: aload #13
    //   249: ifnonnull -> 255
    //   252: goto -> 311
    //   255: aload #5
    //   257: aload #12
    //   259: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   264: ifeq -> 296
    //   267: aload #5
    //   269: aload #12
    //   271: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   276: astore #11
    //   278: aload #11
    //   280: ifnull -> 296
    //   283: aload #11
    //   285: aload #13
    //   287: invokevirtual equals : (Ljava/lang/Object;)Z
    //   290: ifeq -> 296
    //   293: goto -> 198
    //   296: aload #5
    //   298: aload #12
    //   300: aload #13
    //   302: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   307: pop
    //   308: goto -> 336
    //   311: aload #5
    //   313: aload #12
    //   315: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   320: ifne -> 326
    //   323: goto -> 198
    //   326: aload #5
    //   328: aload #12
    //   330: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   335: pop
    //   336: iconst_1
    //   337: istore #8
    //   339: iload #7
    //   341: ifeq -> 353
    //   344: aload_2
    //   345: aload #12
    //   347: invokeinterface add : (Ljava/lang/Object;)Z
    //   352: pop
    //   353: goto -> 198
    //   356: aload_0
    //   357: getfield mModified : Ljava/util/Map;
    //   360: invokeinterface clear : ()V
    //   365: iload #8
    //   367: ifeq -> 378
    //   370: aload_0
    //   371: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   374: invokestatic access$608 : (Landroid/app/SharedPreferencesImpl;)J
    //   377: pop2
    //   378: aload_0
    //   379: getfield this$0 : Landroid/app/SharedPreferencesImpl;
    //   382: invokestatic access$600 : (Landroid/app/SharedPreferencesImpl;)J
    //   385: lstore #14
    //   387: aload #6
    //   389: monitorexit
    //   390: aload #4
    //   392: monitorexit
    //   393: new android/app/SharedPreferencesImpl$MemoryCommitResult
    //   396: dup
    //   397: lload #14
    //   399: iload_1
    //   400: aload_2
    //   401: aload_3
    //   402: aload #5
    //   404: aconst_null
    //   405: invokespecial <init> : (JZLjava/util/List;Ljava/util/Set;Ljava/util/Map;Landroid/app/SharedPreferencesImpl$1;)V
    //   408: areturn
    //   409: astore_2
    //   410: aload #6
    //   412: monitorexit
    //   413: aload_2
    //   414: athrow
    //   415: astore_2
    //   416: aload #4
    //   418: monitorexit
    //   419: aload_2
    //   420: athrow
    // Exception table:
    //   from	to	target	type
    //   18	59	415	finally
    //   59	89	415	finally
    //   103	111	415	finally
    //   111	129	415	finally
    //   129	138	415	finally
    //   144	151	409	finally
    //   155	165	409	finally
    //   168	175	409	finally
    //   177	182	409	finally
    //   182	198	409	finally
    //   198	241	409	finally
    //   255	278	409	finally
    //   283	293	409	finally
    //   296	308	409	finally
    //   311	323	409	finally
    //   326	336	409	finally
    //   344	353	409	finally
    //   356	365	409	finally
    //   370	378	409	finally
    //   378	390	409	finally
    //   390	393	415	finally
    //   410	413	409	finally
    //   413	415	415	finally
    //   416	419	415	finally
  }
  
  private void notifyListeners(SharedPreferencesImpl.MemoryCommitResult paramMemoryCommitResult) {
    if (paramMemoryCommitResult.listeners == null || (paramMemoryCommitResult.keysModified == null && !paramMemoryCommitResult.keysCleared))
      return; 
    if (Looper.myLooper() == Looper.getMainLooper()) {
      if (paramMemoryCommitResult.keysCleared && Compatibility.isChangeEnabled(119147584L))
        for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : paramMemoryCommitResult.listeners) {
          if (onSharedPreferenceChangeListener != null)
            onSharedPreferenceChangeListener.onSharedPreferenceChanged(SharedPreferencesImpl.this, null); 
        }  
      for (int i = paramMemoryCommitResult.keysModified.size() - 1; i >= 0; i--) {
        String str = paramMemoryCommitResult.keysModified.get(i);
        for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : paramMemoryCommitResult.listeners) {
          if (onSharedPreferenceChangeListener != null)
            onSharedPreferenceChangeListener.onSharedPreferenceChanged(SharedPreferencesImpl.this, str); 
        } 
      } 
    } else {
      ActivityThread.sMainThreadHandler.post(new _$$Lambda$SharedPreferencesImpl$EditorImpl$3CAjkhzA131V3V_sLfP2uy0FWZ0(this, paramMemoryCommitResult));
    } 
  }
  
  public void apply() {
    final long startTime = System.currentTimeMillis();
    final SharedPreferencesImpl.MemoryCommitResult mcr = commitToMemory();
    final Runnable awaitCommit = new Runnable() {
        public void run() {
          try {
            mcr.writtenToDiskLatch.await();
          } catch (InterruptedException interruptedException) {}
        }
      };
    QueuedWork.addFinisher(runnable);
    runnable = new Runnable() {
        public void run() {
          awaitCommit.run();
          QueuedWork.removeFinisher(awaitCommit);
        }
      };
    SharedPreferencesImpl.access$100(SharedPreferencesImpl.this, memoryCommitResult, runnable);
    notifyListeners(memoryCommitResult);
  }
  
  public SharedPreferences.Editor clear() {
    synchronized (this.mEditorLock) {
      this.mClear = true;
      return this;
    } 
  }
  
  public boolean commit() {
    null = commitToMemory();
    SharedPreferencesImpl.access$100(SharedPreferencesImpl.this, null, null);
    try {
      null.writtenToDiskLatch.await();
      notifyListeners(null);
      return null.writeToDiskResult;
    } catch (InterruptedException interruptedException) {
      return false;
    } finally {}
  }
  
  public SharedPreferences.Editor putBoolean(String paramString, boolean paramBoolean) {
    synchronized (this.mEditorLock) {
      this.mModified.put(paramString, Boolean.valueOf(paramBoolean));
      return this;
    } 
  }
  
  public SharedPreferences.Editor putFloat(String paramString, float paramFloat) {
    synchronized (this.mEditorLock) {
      this.mModified.put(paramString, Float.valueOf(paramFloat));
      return this;
    } 
  }
  
  public SharedPreferences.Editor putInt(String paramString, int paramInt) {
    synchronized (this.mEditorLock) {
      this.mModified.put(paramString, Integer.valueOf(paramInt));
      return this;
    } 
  }
  
  public SharedPreferences.Editor putLong(String paramString, long paramLong) {
    synchronized (this.mEditorLock) {
      this.mModified.put(paramString, Long.valueOf(paramLong));
      return this;
    } 
  }
  
  public SharedPreferences.Editor putString(String paramString1, String paramString2) {
    synchronized (this.mEditorLock) {
      this.mModified.put(paramString1, paramString2);
      return this;
    } 
  }
  
  public SharedPreferences.Editor putStringSet(String paramString, Set<String> paramSet) {
    synchronized (this.mEditorLock) {
      Map<String, Object> map = this.mModified;
      if (paramSet == null) {
        paramSet = null;
      } else {
        paramSet = new HashSet<>(paramSet);
      } 
      map.put(paramString, paramSet);
      return this;
    } 
  }
  
  public SharedPreferences.Editor remove(String paramString) {
    synchronized (this.mEditorLock) {
      this.mModified.put(paramString, this);
      return this;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SharedPreferencesImpl$EditorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
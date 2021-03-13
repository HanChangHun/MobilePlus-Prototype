package android.app;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import com.android.internal.util.FastPrintWriter;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class PropertyInvalidatedCache<Query, Result> {
  private static final boolean DEBUG = false;
  
  private static final long NONCE_DISABLED = -1L;
  
  private static final long NONCE_UNSET = 0L;
  
  private static final String TAG = "PropertyInvalidatedCache";
  
  private static final boolean VERIFY = false;
  
  private static final WeakHashMap<PropertyInvalidatedCache, Void> sCaches;
  
  private static final Object sCorkLock;
  
  private static final HashMap<String, Integer> sCorks;
  
  private static boolean sEnabled;
  
  private static final HashMap<String, Long> sInvalidates = new HashMap<>();
  
  private final LinkedHashMap<Query, Result> mCache;
  
  private boolean mDisabled = false;
  
  private long mHits = 0L;
  
  private long mLastSeenNonce = 0L;
  
  private final Object mLock = new Object();
  
  private final int mMaxEntries;
  
  private long mMisses = 0L;
  
  private volatile SystemProperties.Handle mPropertyHandle;
  
  private final String mPropertyName;
  
  static {
    sEnabled = true;
    sCorkLock = new Object();
    sCorks = new HashMap<>();
    sCaches = new WeakHashMap<>();
  }
  
  public PropertyInvalidatedCache(final int maxEntries, String paramString) {
    this.mPropertyName = paramString;
    this.mMaxEntries = maxEntries;
    this.mCache = new LinkedHashMap<Query, Result>(2, 0.75F, true) {
        protected boolean removeEldestEntry(Map.Entry param1Entry) {
          boolean bool;
          if (size() > maxEntries) {
            bool = true;
          } else {
            bool = false;
          } 
          return bool;
        }
      };
    synchronized (sCorkLock) {
      sCaches.put(this, null);
      sInvalidates.put(paramString, Long.valueOf(0L));
      return;
    } 
  }
  
  public static void corkInvalidations(String paramString) {
    synchronized (sCorkLock) {
      int i = ((Integer)sCorks.getOrDefault(paramString, Integer.valueOf(0))).intValue();
      if (i == 0) {
        long l = SystemProperties.getLong(paramString, 0L);
        if (l != 0L && l != -1L)
          SystemProperties.set(paramString, Long.toString(0L)); 
      } 
      sCorks.put(paramString, Integer.valueOf(i + 1));
      return;
    } 
  }
  
  public static void disableForTestMode() {
    Log.d("PropertyInvalidatedCache", "disabling all caches in the process");
    sEnabled = false;
  }
  
  public static void disableSystemWide(String paramString) {
    if (!sEnabled)
      return; 
    SystemProperties.set(paramString, Long.toString(-1L));
  }
  
  public static void dumpCacheInfo(FileDescriptor paramFileDescriptor, String[] paramArrayOfString) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream();
      this(paramFileDescriptor);
      try {
        FastPrintWriter fastPrintWriter = new FastPrintWriter();
        this(fileOutputStream);
      } finally {
        try {
          fileOutputStream.close();
        } finally {
          paramArrayOfString = null;
        } 
      } 
    } catch (IOException iOException) {
      Log.e("PropertyInvalidatedCache", "Failed to dump PropertyInvalidatedCache instances");
    } 
  }
  
  private void dumpContents(PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    synchronized (sCorkLock) {
      long l = ((Long)sInvalidates.getOrDefault(this.mPropertyName, Long.valueOf(0L))).longValue();
      synchronized (this.mLock) {
        paramPrintWriter.println(String.format("  Cache Property Name: %s", new Object[] { cacheName() }));
        paramPrintWriter.println(String.format("    Hits: %d, Misses: %d, Invalidates: %d", new Object[] { Long.valueOf(this.mHits), Long.valueOf(this.mMisses), Long.valueOf(l) }));
        paramPrintWriter.println(String.format("    Last Observed Nonce: %d", new Object[] { Long.valueOf(this.mLastSeenNonce) }));
        paramPrintWriter.println(String.format("    Current Size: %d, Max Size: %d", new Object[] { Integer.valueOf(this.mCache.entrySet().size()), Integer.valueOf(this.mMaxEntries) }));
        if (this.mDisabled) {
          null = (Object<Map.Entry<Query, Result>>)"false";
        } else {
          null = (Object<Map.Entry<Query, Result>>)"true";
        } 
        paramPrintWriter.println(String.format("    Enabled: %s", new Object[] { null }));
        null = (Object<Map.Entry<Query, Result>>)this.mCache.entrySet();
        if (null.size() == 0) {
          paramPrintWriter.println("");
          return;
        } 
        paramPrintWriter.println("");
        paramPrintWriter.println("    Contents:");
        for (Map.Entry<Query, Result> entry : null) {
          paramPrintWriter.println(String.format("      Key: %s\n      Value: %s\n", new Object[] { Objects.toString(entry.getKey()), Objects.toString(entry.getValue()) }));
        } 
        return;
      } 
    } 
  }
  
  public static ArrayList<PropertyInvalidatedCache> getActiveCaches() {
    synchronized (sCorkLock) {
      ArrayList<PropertyInvalidatedCache> arrayList = new ArrayList();
      this((Collection)sCaches.keySet());
      return arrayList;
    } 
  }
  
  public static ArrayList<Map.Entry<String, Integer>> getActiveCorks() {
    synchronized (sCorkLock) {
      ArrayList<Map.Entry<String, Integer>> arrayList = new ArrayList();
      this((Collection)sCorks.entrySet());
      return arrayList;
    } 
  }
  
  private long getCurrentNonce() {
    SystemProperties.Handle handle1 = this.mPropertyHandle;
    SystemProperties.Handle handle2 = handle1;
    if (handle1 == null) {
      handle2 = SystemProperties.find(this.mPropertyName);
      if (handle2 == null)
        return 0L; 
      this.mPropertyHandle = handle2;
    } 
    return handle2.getLong(0L);
  }
  
  public static void invalidateCache(String paramString) {
    if (!sEnabled)
      return; 
    synchronized (sCorkLock) {
      Integer integer = sCorks.get(paramString);
      if (integer != null && integer.intValue() > 0)
        return; 
      invalidateCacheLocked(paramString);
      return;
    } 
  }
  
  private static void invalidateCacheLocked(String paramString) {
    if (SystemProperties.getLong(paramString, 0L) == -1L)
      return; 
    while (true) {
      long l = NoPreloadHolder.next();
      if (l != 0L && l != -1L) {
        SystemProperties.set(paramString, Long.toString(l));
        l = ((Long)sInvalidates.getOrDefault(paramString, Long.valueOf(0L))).longValue();
        sInvalidates.put(paramString, Long.valueOf(1L + l));
        return;
      } 
    } 
  }
  
  public static void uncorkInvalidations(String paramString) {
    synchronized (sCorkLock) {
      int i = ((Integer)sCorks.getOrDefault(paramString, Integer.valueOf(0))).intValue();
      if (i >= 1) {
        if (i == 1) {
          sCorks.remove(paramString);
          invalidateCacheLocked(paramString);
        } else {
          sCorks.put(paramString, Integer.valueOf(i - 1));
        } 
        return;
      } 
      AssertionError assertionError = new AssertionError();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("cork underflow: ");
      stringBuilder.append(paramString);
      this(stringBuilder.toString());
      throw assertionError;
    } 
  }
  
  public String cacheName() {
    return this.mPropertyName;
  }
  
  public final void clear() {
    synchronized (this.mLock) {
      this.mCache.clear();
      return;
    } 
  }
  
  protected boolean debugCompareQueryResults(Result paramResult1, Result paramResult2) {
    return (paramResult2 != null) ? Objects.equals(paramResult1, paramResult2) : true;
  }
  
  public final void disableLocal() {
    synchronized (this.mLock) {
      this.mDisabled = true;
      this.mCache.clear();
      return;
    } 
  }
  
  public final void disableSystemWide() {
    disableSystemWide(this.mPropertyName);
  }
  
  public final void invalidateCache() {
    invalidateCache(this.mPropertyName);
  }
  
  public final boolean isDisabledLocal() {
    return (this.mDisabled || !sEnabled);
  }
  
  protected Result maybeCheckConsistency(Query paramQuery, Result paramResult) {
    return paramResult;
  }
  
  public Result query(Query paramQuery) {
    long l;
    if (!isDisabledLocal()) {
      l = getCurrentNonce();
    } else {
      l = -1L;
    } 
    while (l != -1L && l != 0L) {
      synchronized (this.mLock) {
        if (l == this.mLastSeenNonce) {
          Result result1 = this.mCache.get(paramQuery);
          Result result2 = result1;
          if (result1 != null) {
            this.mHits++;
            Result result3 = result1;
          } 
        } else {
          this.mCache.clear();
          this.mLastSeenNonce = l;
          null = null;
        } 
        if (null != null) {
          Result result1 = refresh(null, paramQuery);
          if (result1 != null) {
            long l1 = getCurrentNonce();
            if (l != l1) {
              l = l1;
              continue;
            } 
            synchronized (this.mLock) {
              if (l == this.mLastSeenNonce)
                if (result1 == null) {
                  this.mCache.remove(paramQuery);
                } else {
                  this.mCache.put(paramQuery, result1);
                }  
              return maybeCheckConsistency(paramQuery, result1);
            } 
          } 
          return maybeCheckConsistency(paramQuery, null);
        } 
        Result result = recompute(paramQuery);
        synchronized (this.mLock) {
          if (this.mLastSeenNonce == l && result != null)
            this.mCache.put(paramQuery, result); 
          this.mMisses++;
          return maybeCheckConsistency(paramQuery, result);
        } 
      } 
    } 
    return recompute(paramQuery);
  }
  
  public String queryToString(Query paramQuery) {
    return Objects.toString(paramQuery);
  }
  
  protected abstract Result recompute(Query paramQuery);
  
  protected Result refresh(Result paramResult, Query paramQuery) {
    return paramResult;
  }
  
  public static final class AutoCorker {
    public static final int DEFAULT_AUTO_CORK_DELAY_MS = 2000;
    
    private final int mAutoCorkDelayMs;
    
    private Handler mHandler;
    
    private final Object mLock = new Object();
    
    private final String mPropertyName;
    
    private long mUncorkDeadlineMs = -1L;
    
    public AutoCorker(String param1String) {
      this(param1String, 2000);
    }
    
    public AutoCorker(String param1String, int param1Int) {
      this.mPropertyName = param1String;
      this.mAutoCorkDelayMs = param1Int;
    }
    
    private Handler getHandlerLocked() {
      if (this.mHandler == null)
        this.mHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message param2Message) {
              PropertyInvalidatedCache.AutoCorker.this.handleMessage(param2Message);
            }
          }; 
      return this.mHandler;
    }
    
    private void handleMessage(Message param1Message) {
      synchronized (this.mLock) {
        if (this.mUncorkDeadlineMs < 0L)
          return; 
        long l = SystemClock.uptimeMillis();
        if (this.mUncorkDeadlineMs > l) {
          this.mUncorkDeadlineMs = this.mAutoCorkDelayMs + l;
          getHandlerLocked().sendEmptyMessageAtTime(0, this.mUncorkDeadlineMs);
          return;
        } 
        this.mUncorkDeadlineMs = -1L;
        PropertyInvalidatedCache.uncorkInvalidations(this.mPropertyName);
        return;
      } 
    }
    
    public void autoCork() {
      if (Looper.getMainLooper() == null) {
        PropertyInvalidatedCache.invalidateCache(this.mPropertyName);
        return;
      } 
      synchronized (this.mLock) {
        boolean bool;
        if (this.mUncorkDeadlineMs >= 0L) {
          bool = true;
        } else {
          bool = false;
        } 
        this.mUncorkDeadlineMs = SystemClock.uptimeMillis() + this.mAutoCorkDelayMs;
        if (!bool) {
          getHandlerLocked().sendEmptyMessageAtTime(0, this.mUncorkDeadlineMs);
          PropertyInvalidatedCache.corkInvalidations(this.mPropertyName);
        } 
        return;
      } 
    }
  }
  
  class null extends Handler {
    null(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      this.this$0.handleMessage(param1Message);
    }
  }
  
  private static final class NoPreloadHolder {
    private static final AtomicLong sNextNonce = new AtomicLong((new Random()).nextLong());
    
    public static long next() {
      return sNextNonce.getAndIncrement();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PropertyInvalidatedCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
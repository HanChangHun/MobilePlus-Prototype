package android.content.res.loader;

import android.content.res.ApkAssets;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.internal.util.ArrayUtils;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResourcesLoader {
  private ApkAssets[] mApkAssets;
  
  private ArrayMap<WeakReference<Object>, UpdateCallbacks> mChangeCallbacks = new ArrayMap();
  
  private final Object mLock = new Object();
  
  private ResourcesProvider[] mPreviousProviders;
  
  private ResourcesProvider[] mProviders;
  
  private static boolean arrayEquals(ResourcesProvider[] paramArrayOfResourcesProvider1, ResourcesProvider[] paramArrayOfResourcesProvider2) {
    if (paramArrayOfResourcesProvider1 == paramArrayOfResourcesProvider2)
      return true; 
    if (paramArrayOfResourcesProvider1 == null || paramArrayOfResourcesProvider2 == null)
      return false; 
    if (paramArrayOfResourcesProvider1.length != paramArrayOfResourcesProvider2.length)
      return false; 
    byte b = 0;
    int i = paramArrayOfResourcesProvider1.length;
    while (b < i) {
      if (paramArrayOfResourcesProvider1[b] != paramArrayOfResourcesProvider2[b])
        return false; 
      b++;
    } 
    return true;
  }
  
  private void notifyProvidersChangedLocked() {
    ArraySet arraySet = new ArraySet();
    if (arrayEquals(this.mPreviousProviders, this.mProviders))
      return; 
    ResourcesProvider[] arrayOfResourcesProvider = this.mProviders;
    if (arrayOfResourcesProvider == null || arrayOfResourcesProvider.length == 0) {
      this.mApkAssets = null;
    } else {
      this.mApkAssets = new ApkAssets[arrayOfResourcesProvider.length];
      byte b = 0;
      int k = arrayOfResourcesProvider.length;
      while (b < k) {
        this.mProviders[b].incrementRefCount();
        this.mApkAssets[b] = this.mProviders[b].getApkAssets();
        b++;
      } 
    } 
    arrayOfResourcesProvider = this.mPreviousProviders;
    if (arrayOfResourcesProvider != null) {
      int k = arrayOfResourcesProvider.length;
      for (byte b = 0; b < k; b++)
        arrayOfResourcesProvider[b].decrementRefCount(); 
    } 
    this.mPreviousProviders = this.mProviders;
    int i;
    for (i = this.mChangeCallbacks.size() - 1; i >= 0; i--) {
      if (((WeakReference)this.mChangeCallbacks.keyAt(i)).get() == null) {
        this.mChangeCallbacks.removeAt(i);
      } else {
        arraySet.add(this.mChangeCallbacks.valueAt(i));
      } 
    } 
    i = 0;
    int j = arraySet.size();
    while (i < j) {
      ((UpdateCallbacks)arraySet.valueAt(i)).onLoaderUpdated(this);
      i++;
    } 
  }
  
  public void addProvider(ResourcesProvider paramResourcesProvider) {
    synchronized (this.mLock) {
      this.mProviders = (ResourcesProvider[])ArrayUtils.appendElement(ResourcesProvider.class, (Object[])this.mProviders, paramResourcesProvider);
      notifyProvidersChangedLocked();
      return;
    } 
  }
  
  public void clearProviders() {
    synchronized (this.mLock) {
      this.mProviders = null;
      notifyProvidersChangedLocked();
      return;
    } 
  }
  
  public List<ApkAssets> getApkAssets() {
    synchronized (this.mLock) {
      if (this.mApkAssets == null)
        return (List)Collections.emptyList(); 
      return Arrays.asList(this.mApkAssets);
    } 
  }
  
  public List<ResourcesProvider> getProviders() {
    synchronized (this.mLock) {
      List<ResourcesProvider> list;
      if (this.mProviders == null) {
        list = Collections.emptyList();
      } else {
        list = Arrays.asList(this.mProviders);
      } 
      return list;
    } 
  }
  
  public void registerOnProvidersChangedCallback(Object paramObject, UpdateCallbacks paramUpdateCallbacks) {
    synchronized (this.mLock) {
      ArrayMap<WeakReference<Object>, UpdateCallbacks> arrayMap = this.mChangeCallbacks;
      WeakReference weakReference = new WeakReference();
      this((T)paramObject);
      arrayMap.put(weakReference, paramUpdateCallbacks);
      return;
    } 
  }
  
  public void removeProvider(ResourcesProvider paramResourcesProvider) {
    synchronized (this.mLock) {
      this.mProviders = (ResourcesProvider[])ArrayUtils.removeElement(ResourcesProvider.class, (Object[])this.mProviders, paramResourcesProvider);
      notifyProvidersChangedLocked();
      return;
    } 
  }
  
  public void setProviders(List<ResourcesProvider> paramList) {
    synchronized (this.mLock) {
      this.mProviders = paramList.<ResourcesProvider>toArray(new ResourcesProvider[0]);
      notifyProvidersChangedLocked();
      return;
    } 
  }
  
  public void unregisterOnProvidersChangedCallback(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLock : Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: iconst_0
    //   8: istore_3
    //   9: aload_0
    //   10: getfield mChangeCallbacks : Landroid/util/ArrayMap;
    //   13: invokevirtual size : ()I
    //   16: istore #4
    //   18: iload_3
    //   19: iload #4
    //   21: if_icmpge -> 60
    //   24: aload_1
    //   25: aload_0
    //   26: getfield mChangeCallbacks : Landroid/util/ArrayMap;
    //   29: iload_3
    //   30: invokevirtual keyAt : (I)Ljava/lang/Object;
    //   33: checkcast java/lang/ref/WeakReference
    //   36: invokevirtual get : ()Ljava/lang/Object;
    //   39: if_acmpne -> 54
    //   42: aload_0
    //   43: getfield mChangeCallbacks : Landroid/util/ArrayMap;
    //   46: iload_3
    //   47: invokevirtual removeAt : (I)Ljava/lang/Object;
    //   50: pop
    //   51: aload_2
    //   52: monitorexit
    //   53: return
    //   54: iinc #3, 1
    //   57: goto -> 18
    //   60: aload_2
    //   61: monitorexit
    //   62: return
    //   63: astore_1
    //   64: aload_2
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   9	18	63	finally
    //   24	53	63	finally
    //   60	62	63	finally
    //   64	66	63	finally
  }
  
  public static interface UpdateCallbacks {
    void onLoaderUpdated(ResourcesLoader param1ResourcesLoader);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/loader/ResourcesLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
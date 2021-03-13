package android.content.res;

import android.util.ArrayMap;
import android.util.LongSparseArray;
import java.lang.ref.WeakReference;

abstract class ThemedResourceCache<T> {
  private LongSparseArray<WeakReference<T>> mNullThemedEntries;
  
  private ArrayMap<Resources.ThemeKey, LongSparseArray<WeakReference<T>>> mThemedEntries;
  
  private LongSparseArray<WeakReference<T>> mUnthemedEntries;
  
  private LongSparseArray<WeakReference<T>> getThemedLocked(Resources.Theme paramTheme, boolean paramBoolean) {
    if (paramTheme == null) {
      if (this.mNullThemedEntries == null && paramBoolean)
        this.mNullThemedEntries = new LongSparseArray(1); 
      return this.mNullThemedEntries;
    } 
    if (this.mThemedEntries == null)
      if (paramBoolean) {
        this.mThemedEntries = new ArrayMap(1);
      } else {
        return null;
      }  
    Resources.ThemeKey themeKey = paramTheme.getKey();
    LongSparseArray<WeakReference<T>> longSparseArray2 = (LongSparseArray)this.mThemedEntries.get(themeKey);
    LongSparseArray<WeakReference<T>> longSparseArray1 = longSparseArray2;
    if (longSparseArray2 == null) {
      longSparseArray1 = longSparseArray2;
      if (paramBoolean) {
        longSparseArray1 = new LongSparseArray(1);
        Resources.ThemeKey themeKey1 = themeKey.clone();
        this.mThemedEntries.put(themeKey1, longSparseArray1);
      } 
    } 
    return longSparseArray1;
  }
  
  private LongSparseArray<WeakReference<T>> getUnthemedLocked(boolean paramBoolean) {
    if (this.mUnthemedEntries == null && paramBoolean)
      this.mUnthemedEntries = new LongSparseArray(1); 
    return this.mUnthemedEntries;
  }
  
  private boolean prune(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mThemedEntries : Landroid/util/ArrayMap;
    //   6: astore_2
    //   7: iconst_1
    //   8: istore_3
    //   9: aload_2
    //   10: ifnull -> 65
    //   13: aload_0
    //   14: getfield mThemedEntries : Landroid/util/ArrayMap;
    //   17: invokevirtual size : ()I
    //   20: iconst_1
    //   21: isub
    //   22: istore #4
    //   24: iload #4
    //   26: iflt -> 65
    //   29: aload_0
    //   30: aload_0
    //   31: getfield mThemedEntries : Landroid/util/ArrayMap;
    //   34: iload #4
    //   36: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   39: checkcast android/util/LongSparseArray
    //   42: iload_1
    //   43: invokespecial pruneEntriesLocked : (Landroid/util/LongSparseArray;I)Z
    //   46: ifeq -> 59
    //   49: aload_0
    //   50: getfield mThemedEntries : Landroid/util/ArrayMap;
    //   53: iload #4
    //   55: invokevirtual removeAt : (I)Ljava/lang/Object;
    //   58: pop
    //   59: iinc #4, -1
    //   62: goto -> 24
    //   65: aload_0
    //   66: aload_0
    //   67: getfield mNullThemedEntries : Landroid/util/LongSparseArray;
    //   70: iload_1
    //   71: invokespecial pruneEntriesLocked : (Landroid/util/LongSparseArray;I)Z
    //   74: pop
    //   75: aload_0
    //   76: aload_0
    //   77: getfield mUnthemedEntries : Landroid/util/LongSparseArray;
    //   80: iload_1
    //   81: invokespecial pruneEntriesLocked : (Landroid/util/LongSparseArray;I)Z
    //   84: pop
    //   85: aload_0
    //   86: getfield mThemedEntries : Landroid/util/ArrayMap;
    //   89: ifnonnull -> 109
    //   92: aload_0
    //   93: getfield mNullThemedEntries : Landroid/util/LongSparseArray;
    //   96: ifnonnull -> 109
    //   99: aload_0
    //   100: getfield mUnthemedEntries : Landroid/util/LongSparseArray;
    //   103: ifnonnull -> 109
    //   106: goto -> 111
    //   109: iconst_0
    //   110: istore_3
    //   111: aload_0
    //   112: monitorexit
    //   113: iload_3
    //   114: ireturn
    //   115: astore_2
    //   116: aload_0
    //   117: monitorexit
    //   118: aload_2
    //   119: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	115	finally
    //   13	24	115	finally
    //   29	59	115	finally
    //   65	106	115	finally
    //   111	113	115	finally
    //   116	118	115	finally
  }
  
  private boolean pruneEntriesLocked(LongSparseArray<WeakReference<T>> paramLongSparseArray, int paramInt) {
    boolean bool = true;
    if (paramLongSparseArray == null)
      return true; 
    for (int i = paramLongSparseArray.size() - 1; i >= 0; i--) {
      WeakReference<T> weakReference = (WeakReference)paramLongSparseArray.valueAt(i);
      if (weakReference == null || pruneEntryLocked(weakReference.get(), paramInt))
        paramLongSparseArray.removeAt(i); 
    } 
    if (paramLongSparseArray.size() != 0)
      bool = false; 
    return bool;
  }
  
  private boolean pruneEntryLocked(T paramT, int paramInt) {
    return (paramT == null || (paramInt != 0 && shouldInvalidateEntry(paramT, paramInt)));
  }
  
  public void clear() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mThemedEntries : Landroid/util/ArrayMap;
    //   6: ifnull -> 16
    //   9: aload_0
    //   10: getfield mThemedEntries : Landroid/util/ArrayMap;
    //   13: invokevirtual clear : ()V
    //   16: aload_0
    //   17: getfield mUnthemedEntries : Landroid/util/LongSparseArray;
    //   20: ifnull -> 30
    //   23: aload_0
    //   24: getfield mUnthemedEntries : Landroid/util/LongSparseArray;
    //   27: invokevirtual clear : ()V
    //   30: aload_0
    //   31: getfield mNullThemedEntries : Landroid/util/LongSparseArray;
    //   34: ifnull -> 44
    //   37: aload_0
    //   38: getfield mNullThemedEntries : Landroid/util/LongSparseArray;
    //   41: invokevirtual clear : ()V
    //   44: aload_0
    //   45: monitorexit
    //   46: return
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	47	finally
    //   16	30	47	finally
    //   30	44	47	finally
  }
  
  public T get(long paramLong, Resources.Theme paramTheme) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_3
    //   4: iconst_0
    //   5: invokespecial getThemedLocked : (Landroid/content/res/Resources$Theme;Z)Landroid/util/LongSparseArray;
    //   8: astore_3
    //   9: aload_3
    //   10: ifnull -> 35
    //   13: aload_3
    //   14: lload_1
    //   15: invokevirtual get : (J)Ljava/lang/Object;
    //   18: checkcast java/lang/ref/WeakReference
    //   21: astore_3
    //   22: aload_3
    //   23: ifnull -> 35
    //   26: aload_3
    //   27: invokevirtual get : ()Ljava/lang/Object;
    //   30: astore_3
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_3
    //   34: areturn
    //   35: aload_0
    //   36: iconst_0
    //   37: invokespecial getUnthemedLocked : (Z)Landroid/util/LongSparseArray;
    //   40: astore_3
    //   41: aload_3
    //   42: ifnull -> 67
    //   45: aload_3
    //   46: lload_1
    //   47: invokevirtual get : (J)Ljava/lang/Object;
    //   50: checkcast java/lang/ref/WeakReference
    //   53: astore_3
    //   54: aload_3
    //   55: ifnull -> 67
    //   58: aload_3
    //   59: invokevirtual get : ()Ljava/lang/Object;
    //   62: astore_3
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_3
    //   66: areturn
    //   67: aload_0
    //   68: monitorexit
    //   69: aconst_null
    //   70: areturn
    //   71: astore_3
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_3
    //   75: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	71	finally
    //   13	22	71	finally
    //   26	33	71	finally
    //   35	41	71	finally
    //   45	54	71	finally
    //   58	65	71	finally
    //   67	69	71	finally
    //   72	74	71	finally
  }
  
  public void onConfigurationChange(int paramInt) {
    prune(paramInt);
  }
  
  public void put(long paramLong, Resources.Theme paramTheme, T paramT) {
    put(paramLong, paramTheme, paramT, true);
  }
  
  public void put(long paramLong, Resources.Theme paramTheme, T paramT, boolean paramBoolean) {
    // Byte code:
    //   0: aload #4
    //   2: ifnonnull -> 6
    //   5: return
    //   6: aload_0
    //   7: monitorenter
    //   8: iload #5
    //   10: ifne -> 22
    //   13: aload_0
    //   14: iconst_1
    //   15: invokespecial getUnthemedLocked : (Z)Landroid/util/LongSparseArray;
    //   18: astore_3
    //   19: goto -> 29
    //   22: aload_0
    //   23: aload_3
    //   24: iconst_1
    //   25: invokespecial getThemedLocked : (Landroid/content/res/Resources$Theme;Z)Landroid/util/LongSparseArray;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull -> 52
    //   33: new java/lang/ref/WeakReference
    //   36: astore #6
    //   38: aload #6
    //   40: aload #4
    //   42: invokespecial <init> : (Ljava/lang/Object;)V
    //   45: aload_3
    //   46: lload_1
    //   47: aload #6
    //   49: invokevirtual put : (JLjava/lang/Object;)V
    //   52: aload_0
    //   53: monitorexit
    //   54: return
    //   55: astore_3
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_3
    //   59: athrow
    // Exception table:
    //   from	to	target	type
    //   13	19	55	finally
    //   22	29	55	finally
    //   33	52	55	finally
    //   52	54	55	finally
    //   56	58	55	finally
  }
  
  protected abstract boolean shouldInvalidateEntry(T paramT, int paramInt);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ThemedResourceCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
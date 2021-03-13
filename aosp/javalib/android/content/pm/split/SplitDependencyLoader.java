package android.content.pm.split;

import android.content.pm.PackageParser;
import android.util.IntArray;
import android.util.SparseArray;
import java.util.Arrays;
import java.util.BitSet;
import libcore.util.EmptyArray;

public abstract class SplitDependencyLoader<E extends Exception> {
  private final SparseArray<int[]> mDependencies;
  
  protected SplitDependencyLoader(SparseArray<int[]> paramSparseArray) {
    this.mDependencies = paramSparseArray;
  }
  
  private static int[] append(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint == null)
      return new int[] { paramInt }; 
    int[] arrayOfInt = Arrays.copyOf(paramArrayOfint, paramArrayOfint.length + 1);
    arrayOfInt[paramArrayOfint.length] = paramInt;
    return arrayOfInt;
  }
  
  private int[] collectConfigSplitIndices(int paramInt) {
    int[] arrayOfInt = (int[])this.mDependencies.get(paramInt);
    return (arrayOfInt == null || arrayOfInt.length <= 1) ? EmptyArray.INT : Arrays.copyOfRange(arrayOfInt, 1, arrayOfInt.length);
  }
  
  public static SparseArray<int[]> createDependenciesFromPackage(PackageParser.PackageLite paramPackageLite) throws IllegalDependencyException {
    StringBuilder stringBuilder;
    SparseArray sparseArray = new SparseArray();
    sparseArray.put(0, new int[] { -1 });
    int i;
    for (i = 0; i < paramPackageLite.splitNames.length; i++) {
      if (paramPackageLite.isFeatureSplits[i]) {
        boolean bool;
        String str = paramPackageLite.usesSplitNames[i];
        if (str != null) {
          bool = Arrays.binarySearch((Object[])paramPackageLite.splitNames, str);
          if (bool >= 0) {
            bool++;
          } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Split '");
            stringBuilder.append(paramPackageLite.splitNames[i]);
            stringBuilder.append("' requires split '");
            stringBuilder.append(str);
            stringBuilder.append("', which is missing.");
            throw new IllegalDependencyException(stringBuilder.toString());
          } 
        } else {
          bool = false;
        } 
        stringBuilder.put(i + 1, new int[] { bool });
      } 
    } 
    for (i = 0; i < paramPackageLite.splitNames.length; i++) {
      if (!paramPackageLite.isFeatureSplits[i]) {
        boolean bool;
        String str = paramPackageLite.configForSplit[i];
        if (str != null) {
          bool = Arrays.binarySearch((Object[])paramPackageLite.splitNames, str);
          if (bool >= 0) {
            if (paramPackageLite.isFeatureSplits[bool]) {
              bool++;
            } else {
              stringBuilder = new StringBuilder();
              stringBuilder.append("Split '");
              stringBuilder.append(paramPackageLite.splitNames[i]);
              stringBuilder.append("' declares itself as configuration split for a non-feature split '");
              stringBuilder.append(paramPackageLite.splitNames[bool]);
              stringBuilder.append("'");
              throw new IllegalDependencyException(stringBuilder.toString());
            } 
          } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Split '");
            stringBuilder.append(paramPackageLite.splitNames[i]);
            stringBuilder.append("' targets split '");
            stringBuilder.append(str);
            stringBuilder.append("', which is missing.");
            throw new IllegalDependencyException(stringBuilder.toString());
          } 
        } else {
          bool = false;
        } 
        stringBuilder.put(bool, append((int[])stringBuilder.get(bool), i + 1));
      } 
    } 
    BitSet bitSet = new BitSet();
    byte b = 0;
    int j = stringBuilder.size();
    while (b < j) {
      i = stringBuilder.keyAt(b);
      bitSet.clear();
      while (i != -1) {
        if (!bitSet.get(i)) {
          bitSet.set(i);
          int[] arrayOfInt = (int[])stringBuilder.get(i);
          if (arrayOfInt != null) {
            i = arrayOfInt[0];
            continue;
          } 
          i = -1;
          continue;
        } 
        throw new IllegalDependencyException("Cycle detected in split dependencies.");
      } 
      b++;
    } 
    return (SparseArray<int[]>)stringBuilder;
  }
  
  protected abstract void constructSplit(int paramInt1, int[] paramArrayOfint, int paramInt2) throws E;
  
  protected abstract boolean isSplitCached(int paramInt);
  
  protected void loadDependenciesForSplit(int paramInt) throws E {
    if (isSplitCached(paramInt))
      return; 
    if (paramInt == 0) {
      constructSplit(0, collectConfigSplitIndices(0), -1);
      return;
    } 
    IntArray intArray = new IntArray();
    intArray.add(paramInt);
    while (true) {
      int[] arrayOfInt = (int[])this.mDependencies.get(paramInt);
      if (arrayOfInt != null && arrayOfInt.length > 0) {
        paramInt = arrayOfInt[0];
      } else {
        paramInt = -1;
      } 
      if (paramInt < 0 || isSplitCached(paramInt))
        break; 
      intArray.add(paramInt);
    } 
    int i = paramInt;
    for (paramInt = intArray.size() - 1; paramInt >= 0; paramInt--) {
      int j = intArray.get(paramInt);
      constructSplit(j, collectConfigSplitIndices(j), i);
      i = j;
    } 
  }
  
  public static class IllegalDependencyException extends Exception {
    private IllegalDependencyException(String param1String) {
      super(param1String);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/split/SplitDependencyLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
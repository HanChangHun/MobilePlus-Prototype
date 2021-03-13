package android.database;

import android.content.res.Resources;
import android.database.sqlite.SQLiteClosable;
import android.os.Binder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseIntArray;
import dalvik.annotation.optimization.FastNative;
import dalvik.system.CloseGuard;

public class CursorWindow extends SQLiteClosable implements Parcelable {
  public static final Parcelable.Creator<CursorWindow> CREATOR;
  
  private static final String STATS_TAG = "CursorWindowStats";
  
  private static int sCursorWindowSize = -1;
  
  private static final LongSparseArray<Integer> sWindowToPidMap;
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private final String mName;
  
  private int mStartPos;
  
  public long mWindowPtr;
  
  static {
    CREATOR = new Parcelable.Creator<CursorWindow>() {
        public CursorWindow createFromParcel(Parcel param1Parcel) {
          return new CursorWindow(param1Parcel);
        }
        
        public CursorWindow[] newArray(int param1Int) {
          return new CursorWindow[param1Int];
        }
      };
    sWindowToPidMap = new LongSparseArray();
  }
  
  private CursorWindow(Parcel paramParcel) {
    this.mStartPos = paramParcel.readInt();
    long l = nativeCreateFromParcel(paramParcel);
    this.mWindowPtr = l;
    if (l != 0L) {
      this.mName = nativeGetName(l);
      this.mCloseGuard.open("close");
      return;
    } 
    throw new AssertionError();
  }
  
  public CursorWindow(String paramString) {
    this(paramString, getCursorWindowSize());
  }
  
  public CursorWindow(String paramString, long paramLong) {
    this.mStartPos = 0;
    if (paramString == null || paramString.length() == 0)
      paramString = "<unnamed>"; 
    this.mName = paramString;
    paramLong = nativeCreate(paramString, (int)paramLong);
    this.mWindowPtr = paramLong;
    if (paramLong != 0L) {
      this.mCloseGuard.open("close");
      recordNewWindow(Binder.getCallingPid(), this.mWindowPtr);
      return;
    } 
    throw new AssertionError();
  }
  
  @Deprecated
  public CursorWindow(boolean paramBoolean) {
    this((String)null);
  }
  
  private void dispose() {
    CloseGuard closeGuard = this.mCloseGuard;
    if (closeGuard != null)
      closeGuard.close(); 
    long l = this.mWindowPtr;
    if (l != 0L) {
      recordClosingOfWindow(l);
      nativeDispose(this.mWindowPtr);
      this.mWindowPtr = 0L;
    } 
  }
  
  private static int getCursorWindowSize() {
    if (sCursorWindowSize < 0)
      sCursorWindowSize = Resources.getSystem().getInteger(17694766) * 1024; 
    return sCursorWindowSize;
  }
  
  @FastNative
  private static native boolean nativeAllocRow(long paramLong);
  
  @FastNative
  private static native void nativeClear(long paramLong);
  
  private static native void nativeCopyStringToBuffer(long paramLong, int paramInt1, int paramInt2, CharArrayBuffer paramCharArrayBuffer);
  
  private static native long nativeCreate(String paramString, int paramInt);
  
  private static native long nativeCreateFromParcel(Parcel paramParcel);
  
  private static native void nativeDispose(long paramLong);
  
  @FastNative
  private static native void nativeFreeLastRow(long paramLong);
  
  private static native byte[] nativeGetBlob(long paramLong, int paramInt1, int paramInt2);
  
  @FastNative
  private static native double nativeGetDouble(long paramLong, int paramInt1, int paramInt2);
  
  @FastNative
  private static native long nativeGetLong(long paramLong, int paramInt1, int paramInt2);
  
  private static native String nativeGetName(long paramLong);
  
  @FastNative
  private static native int nativeGetNumRows(long paramLong);
  
  private static native String nativeGetString(long paramLong, int paramInt1, int paramInt2);
  
  @FastNative
  private static native int nativeGetType(long paramLong, int paramInt1, int paramInt2);
  
  private static native boolean nativePutBlob(long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  @FastNative
  private static native boolean nativePutDouble(long paramLong, double paramDouble, int paramInt1, int paramInt2);
  
  @FastNative
  private static native boolean nativePutLong(long paramLong1, long paramLong2, int paramInt1, int paramInt2);
  
  @FastNative
  private static native boolean nativePutNull(long paramLong, int paramInt1, int paramInt2);
  
  private static native boolean nativePutString(long paramLong, String paramString, int paramInt1, int paramInt2);
  
  @FastNative
  private static native boolean nativeSetNumColumns(long paramLong, int paramInt);
  
  private static native void nativeWriteToParcel(long paramLong, Parcel paramParcel);
  
  public static CursorWindow newFromParcel(Parcel paramParcel) {
    return (CursorWindow)CREATOR.createFromParcel(paramParcel);
  }
  
  private String printStats() {
    LongSparseArray<Integer> longSparseArray;
    StringBuilder stringBuilder;
    null = new StringBuilder();
    int i = Process.myPid();
    int j = 0;
    SparseIntArray sparseIntArray = new SparseIntArray();
    synchronized (sWindowToPidMap) {
      String str;
      int k = sWindowToPidMap.size();
      if (k == 0)
        return ""; 
      byte b;
      for (b = 0; b < k; b++) {
        int m = ((Integer)sWindowToPidMap.valueAt(b)).intValue();
        sparseIntArray.put(m, sparseIntArray.get(m) + 1);
      } 
      k = sparseIntArray.size();
      for (b = 0; b < k; b++) {
        null.append(" (# cursors opened by ");
        int m = sparseIntArray.keyAt(b);
        if (m == i) {
          null.append("this proc=");
        } else {
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("pid ");
          stringBuilder2.append(m);
          stringBuilder2.append("=");
          null.append(stringBuilder2.toString());
        } 
        m = sparseIntArray.get(m);
        stringBuilder = new StringBuilder();
        stringBuilder.append(m);
        stringBuilder.append(")");
        null.append(stringBuilder.toString());
        j += m;
      } 
      if (null.length() > 980) {
        str = null.substring(0, 980);
      } else {
        str = str.toString();
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("# Open Cursors=");
      stringBuilder1.append(j);
      stringBuilder1.append(str);
      return stringBuilder1.toString();
    } 
  }
  
  private void recordClosingOfWindow(long paramLong) {
    synchronized (sWindowToPidMap) {
      if (sWindowToPidMap.size() == 0)
        return; 
      sWindowToPidMap.delete(paramLong);
      return;
    } 
  }
  
  private void recordNewWindow(int paramInt, long paramLong) {
    synchronized (sWindowToPidMap) {
      sWindowToPidMap.put(paramLong, Integer.valueOf(paramInt));
      if (Log.isLoggable("CursorWindowStats", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Created a new Cursor. ");
        stringBuilder.append(printStats());
        Log.i("CursorWindowStats", stringBuilder.toString());
      } 
      return;
    } 
  }
  
  public boolean allocRow() {
    acquireReference();
    try {
      return nativeAllocRow(this.mWindowPtr);
    } finally {
      releaseReference();
    } 
  }
  
  public void clear() {
    acquireReference();
    try {
      this.mStartPos = 0;
      nativeClear(this.mWindowPtr);
      return;
    } finally {
      releaseReference();
    } 
  }
  
  public void copyStringToBuffer(int paramInt1, int paramInt2, CharArrayBuffer paramCharArrayBuffer) {
    if (paramCharArrayBuffer != null) {
      acquireReference();
      try {
        nativeCopyStringToBuffer(this.mWindowPtr, paramInt1 - this.mStartPos, paramInt2, paramCharArrayBuffer);
        return;
      } finally {
        releaseReference();
      } 
    } 
    throw new IllegalArgumentException("CharArrayBuffer should not be null");
  }
  
  public int describeContents() {
    return 0;
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      dispose();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public void freeLastRow() {
    acquireReference();
    try {
      nativeFreeLastRow(this.mWindowPtr);
      return;
    } finally {
      releaseReference();
    } 
  }
  
  public byte[] getBlob(int paramInt1, int paramInt2) {
    acquireReference();
    try {
      return nativeGetBlob(this.mWindowPtr, paramInt1 - this.mStartPos, paramInt2);
    } finally {
      releaseReference();
    } 
  }
  
  public double getDouble(int paramInt1, int paramInt2) {
    acquireReference();
    try {
      return nativeGetDouble(this.mWindowPtr, paramInt1 - this.mStartPos, paramInt2);
    } finally {
      releaseReference();
    } 
  }
  
  public float getFloat(int paramInt1, int paramInt2) {
    return (float)getDouble(paramInt1, paramInt2);
  }
  
  public int getInt(int paramInt1, int paramInt2) {
    return (int)getLong(paramInt1, paramInt2);
  }
  
  public long getLong(int paramInt1, int paramInt2) {
    acquireReference();
    try {
      return nativeGetLong(this.mWindowPtr, paramInt1 - this.mStartPos, paramInt2);
    } finally {
      releaseReference();
    } 
  }
  
  public String getName() {
    return this.mName;
  }
  
  public int getNumRows() {
    acquireReference();
    try {
      return nativeGetNumRows(this.mWindowPtr);
    } finally {
      releaseReference();
    } 
  }
  
  public short getShort(int paramInt1, int paramInt2) {
    return (short)(int)getLong(paramInt1, paramInt2);
  }
  
  public int getStartPosition() {
    return this.mStartPos;
  }
  
  public String getString(int paramInt1, int paramInt2) {
    acquireReference();
    try {
      return nativeGetString(this.mWindowPtr, paramInt1 - this.mStartPos, paramInt2);
    } finally {
      releaseReference();
    } 
  }
  
  public int getType(int paramInt1, int paramInt2) {
    acquireReference();
    try {
      paramInt1 = nativeGetType(this.mWindowPtr, paramInt1 - this.mStartPos, paramInt2);
      return paramInt1;
    } finally {
      releaseReference();
    } 
  }
  
  @Deprecated
  public boolean isBlob(int paramInt1, int paramInt2) {
    paramInt1 = getType(paramInt1, paramInt2);
    return (paramInt1 == 4 || paramInt1 == 0);
  }
  
  @Deprecated
  public boolean isFloat(int paramInt1, int paramInt2) {
    boolean bool;
    if (getType(paramInt1, paramInt2) == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public boolean isLong(int paramInt1, int paramInt2) {
    paramInt1 = getType(paramInt1, paramInt2);
    boolean bool = true;
    if (paramInt1 != 1)
      bool = false; 
    return bool;
  }
  
  @Deprecated
  public boolean isNull(int paramInt1, int paramInt2) {
    boolean bool;
    if (getType(paramInt1, paramInt2) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @Deprecated
  public boolean isString(int paramInt1, int paramInt2) {
    paramInt1 = getType(paramInt1, paramInt2);
    return (paramInt1 == 3 || paramInt1 == 0);
  }
  
  protected void onAllReferencesReleased() {
    dispose();
  }
  
  public boolean putBlob(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    acquireReference();
    try {
      return nativePutBlob(this.mWindowPtr, paramArrayOfbyte, paramInt1 - this.mStartPos, paramInt2);
    } finally {
      releaseReference();
    } 
  }
  
  public boolean putDouble(double paramDouble, int paramInt1, int paramInt2) {
    acquireReference();
    try {
      return nativePutDouble(this.mWindowPtr, paramDouble, paramInt1 - this.mStartPos, paramInt2);
    } finally {
      releaseReference();
    } 
  }
  
  public boolean putLong(long paramLong, int paramInt1, int paramInt2) {
    acquireReference();
    try {
      return nativePutLong(this.mWindowPtr, paramLong, paramInt1 - this.mStartPos, paramInt2);
    } finally {
      releaseReference();
    } 
  }
  
  public boolean putNull(int paramInt1, int paramInt2) {
    acquireReference();
    try {
      return nativePutNull(this.mWindowPtr, paramInt1 - this.mStartPos, paramInt2);
    } finally {
      releaseReference();
    } 
  }
  
  public boolean putString(String paramString, int paramInt1, int paramInt2) {
    acquireReference();
    try {
      return nativePutString(this.mWindowPtr, paramString, paramInt1 - this.mStartPos, paramInt2);
    } finally {
      releaseReference();
    } 
  }
  
  public boolean setNumColumns(int paramInt) {
    acquireReference();
    try {
      return nativeSetNumColumns(this.mWindowPtr, paramInt);
    } finally {
      releaseReference();
    } 
  }
  
  public void setStartPosition(int paramInt) {
    this.mStartPos = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getName());
    stringBuilder.append(" {");
    stringBuilder.append(Long.toHexString(this.mWindowPtr));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    acquireReference();
    try {
      paramParcel.writeInt(this.mStartPos);
      nativeWriteToParcel(this.mWindowPtr, paramParcel);
      releaseReference();
      return;
    } finally {
      releaseReference();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/CursorWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
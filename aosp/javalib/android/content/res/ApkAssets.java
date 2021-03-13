package android.content.res;

import android.content.om.OverlayableInfo;
import android.content.res.loader.AssetsProvider;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public final class ApkAssets {
  private static final int FORMAT_APK = 0;
  
  private static final int FORMAT_ARSC = 2;
  
  private static final int FORMAT_DIR = 3;
  
  private static final int FORMAT_IDMAP = 1;
  
  public static final int PROPERTY_DYNAMIC = 2;
  
  public static final int PROPERTY_LOADER = 4;
  
  private static final int PROPERTY_OVERLAY = 8;
  
  public static final int PROPERTY_SYSTEM = 1;
  
  private final AssetsProvider mAssets;
  
  private final int mFlags;
  
  private final long mNativePtr;
  
  private boolean mOpen = true;
  
  private final StringBlock mStringBlock;
  
  private ApkAssets(int paramInt, AssetsProvider paramAssetsProvider) {
    this.mFlags = paramInt;
    this.mNativePtr = nativeLoadEmpty(paramInt, paramAssetsProvider);
    this.mStringBlock = null;
    this.mAssets = paramAssetsProvider;
  }
  
  private ApkAssets(int paramInt1, FileDescriptor paramFileDescriptor, String paramString, int paramInt2, AssetsProvider paramAssetsProvider) throws IOException {
    Objects.requireNonNull(paramFileDescriptor, "fd");
    Objects.requireNonNull(paramString, "friendlyName");
    this.mFlags = paramInt2;
    long l = nativeLoadFd(paramInt1, paramFileDescriptor, paramString, paramInt2, paramAssetsProvider);
    this.mNativePtr = l;
    this.mStringBlock = new StringBlock(nativeGetStringBlock(l), true);
    this.mAssets = paramAssetsProvider;
  }
  
  private ApkAssets(int paramInt1, FileDescriptor paramFileDescriptor, String paramString, long paramLong1, long paramLong2, int paramInt2, AssetsProvider paramAssetsProvider) throws IOException {
    Objects.requireNonNull(paramFileDescriptor, "fd");
    Objects.requireNonNull(paramString, "friendlyName");
    this.mFlags = paramInt2;
    paramLong1 = nativeLoadFdOffsets(paramInt1, paramFileDescriptor, paramString, paramLong1, paramLong2, paramInt2, paramAssetsProvider);
    this.mNativePtr = paramLong1;
    this.mStringBlock = new StringBlock(nativeGetStringBlock(paramLong1), true);
    this.mAssets = paramAssetsProvider;
  }
  
  private ApkAssets(int paramInt1, String paramString, int paramInt2, AssetsProvider paramAssetsProvider) throws IOException {
    Objects.requireNonNull(paramString, "path");
    this.mFlags = paramInt2;
    long l = nativeLoad(paramInt1, paramString, paramInt2, paramAssetsProvider);
    this.mNativePtr = l;
    this.mStringBlock = new StringBlock(nativeGetStringBlock(l), true);
    this.mAssets = paramAssetsProvider;
  }
  
  public static ApkAssets loadEmptyForLoader(int paramInt, AssetsProvider paramAssetsProvider) {
    return new ApkAssets(paramInt, paramAssetsProvider);
  }
  
  public static ApkAssets loadFromDir(String paramString, int paramInt, AssetsProvider paramAssetsProvider) throws IOException {
    return new ApkAssets(3, paramString, paramInt, paramAssetsProvider);
  }
  
  public static ApkAssets loadFromFd(FileDescriptor paramFileDescriptor, String paramString, int paramInt, AssetsProvider paramAssetsProvider) throws IOException {
    return new ApkAssets(0, paramFileDescriptor, paramString, paramInt, paramAssetsProvider);
  }
  
  public static ApkAssets loadFromFd(FileDescriptor paramFileDescriptor, String paramString, long paramLong1, long paramLong2, int paramInt, AssetsProvider paramAssetsProvider) throws IOException {
    return new ApkAssets(0, paramFileDescriptor, paramString, paramLong1, paramLong2, paramInt, paramAssetsProvider);
  }
  
  public static ApkAssets loadFromPath(String paramString) throws IOException {
    return loadFromPath(paramString, 0);
  }
  
  public static ApkAssets loadFromPath(String paramString, int paramInt) throws IOException {
    return new ApkAssets(0, paramString, paramInt, null);
  }
  
  public static ApkAssets loadFromPath(String paramString, int paramInt, AssetsProvider paramAssetsProvider) throws IOException {
    return new ApkAssets(0, paramString, paramInt, paramAssetsProvider);
  }
  
  public static ApkAssets loadOverlayFromPath(String paramString, int paramInt) throws IOException {
    return new ApkAssets(1, paramString, paramInt, null);
  }
  
  public static ApkAssets loadTableFromFd(FileDescriptor paramFileDescriptor, String paramString, int paramInt, AssetsProvider paramAssetsProvider) throws IOException {
    return new ApkAssets(2, paramFileDescriptor, paramString, paramInt, paramAssetsProvider);
  }
  
  public static ApkAssets loadTableFromFd(FileDescriptor paramFileDescriptor, String paramString, long paramLong1, long paramLong2, int paramInt, AssetsProvider paramAssetsProvider) throws IOException {
    return new ApkAssets(2, paramFileDescriptor, paramString, paramLong1, paramLong2, paramInt, paramAssetsProvider);
  }
  
  private static native boolean nativeDefinesOverlayable(long paramLong) throws IOException;
  
  private static native void nativeDestroy(long paramLong);
  
  private static native String nativeGetAssetPath(long paramLong);
  
  private static native OverlayableInfo nativeGetOverlayableInfo(long paramLong, String paramString) throws IOException;
  
  private static native long nativeGetStringBlock(long paramLong);
  
  private static native boolean nativeIsUpToDate(long paramLong);
  
  private static native long nativeLoad(int paramInt1, String paramString, int paramInt2, AssetsProvider paramAssetsProvider) throws IOException;
  
  private static native long nativeLoadEmpty(int paramInt, AssetsProvider paramAssetsProvider);
  
  private static native long nativeLoadFd(int paramInt1, FileDescriptor paramFileDescriptor, String paramString, int paramInt2, AssetsProvider paramAssetsProvider) throws IOException;
  
  private static native long nativeLoadFdOffsets(int paramInt1, FileDescriptor paramFileDescriptor, String paramString, long paramLong1, long paramLong2, int paramInt2, AssetsProvider paramAssetsProvider) throws IOException;
  
  private static native long nativeOpenXml(long paramLong, String paramString) throws IOException;
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mOpen : Z
    //   6: ifeq -> 35
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield mOpen : Z
    //   14: aload_0
    //   15: getfield mStringBlock : Landroid/content/res/StringBlock;
    //   18: ifnull -> 28
    //   21: aload_0
    //   22: getfield mStringBlock : Landroid/content/res/StringBlock;
    //   25: invokevirtual close : ()V
    //   28: aload_0
    //   29: getfield mNativePtr : J
    //   32: invokestatic nativeDestroy : (J)V
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Exception table:
    //   from	to	target	type
    //   2	28	38	finally
    //   28	35	38	finally
    //   35	37	38	finally
    //   39	41	38	finally
  }
  
  public boolean definesOverlayable() throws IOException {
    return nativeDefinesOverlayable(this.mNativePtr);
  }
  
  protected void finalize() throws Throwable {
    close();
  }
  
  public String getAssetPath() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mNativePtr : J
    //   6: invokestatic nativeGetAssetPath : (J)Ljava/lang/String;
    //   9: astore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: aload_1
    //   13: areturn
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	14	finally
    //   15	17	14	finally
  }
  
  public AssetsProvider getAssetsProvider() {
    return this.mAssets;
  }
  
  public OverlayableInfo getOverlayableInfo(String paramString) throws IOException {
    return nativeGetOverlayableInfo(this.mNativePtr, paramString);
  }
  
  CharSequence getStringFromPool(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mStringBlock : Landroid/content/res/StringBlock;
    //   4: ifnonnull -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: monitorenter
    //   11: aload_0
    //   12: getfield mStringBlock : Landroid/content/res/StringBlock;
    //   15: iload_1
    //   16: invokevirtual get : (I)Ljava/lang/CharSequence;
    //   19: astore_2
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: areturn
    //   24: astore_2
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_2
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   11	22	24	finally
    //   25	27	24	finally
  }
  
  public boolean isForLoader() {
    boolean bool;
    if ((this.mFlags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isUpToDate() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mNativePtr : J
    //   6: invokestatic nativeIsUpToDate : (J)Z
    //   9: istore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: iload_1
    //   13: ireturn
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	14	finally
    //   15	17	14	finally
  }
  
  public XmlResourceParser openXml(String paramString) throws IOException {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'fileName'
    //   3: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   6: pop
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield mNativePtr : J
    //   13: aload_1
    //   14: invokestatic nativeOpenXml : (JLjava/lang/String;)J
    //   17: lstore_2
    //   18: new android/content/res/XmlBlock
    //   21: astore_1
    //   22: aload_1
    //   23: aconst_null
    //   24: lload_2
    //   25: invokespecial <init> : (Landroid/content/res/AssetManager;J)V
    //   28: aload_1
    //   29: invokevirtual newParser : ()Landroid/content/res/XmlResourceParser;
    //   32: astore #4
    //   34: aload #4
    //   36: ifnull -> 48
    //   39: aload_1
    //   40: invokevirtual close : ()V
    //   43: aload_0
    //   44: monitorexit
    //   45: aload #4
    //   47: areturn
    //   48: new java/lang/AssertionError
    //   51: astore #4
    //   53: aload #4
    //   55: ldc 'block.newParser() returned a null parser'
    //   57: invokespecial <init> : (Ljava/lang/Object;)V
    //   60: aload #4
    //   62: athrow
    //   63: astore #4
    //   65: aload_1
    //   66: invokevirtual close : ()V
    //   69: goto -> 79
    //   72: astore_1
    //   73: aload #4
    //   75: aload_1
    //   76: invokevirtual addSuppressed : (Ljava/lang/Throwable;)V
    //   79: aload #4
    //   81: athrow
    //   82: astore_1
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_1
    //   86: athrow
    // Exception table:
    //   from	to	target	type
    //   9	28	82	finally
    //   28	34	63	finally
    //   39	45	82	finally
    //   48	63	63	finally
    //   65	69	72	finally
    //   73	79	82	finally
    //   79	82	82	finally
    //   83	85	82	finally
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ApkAssets{path=");
    stringBuilder.append(getAssetPath());
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FormatType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PropertyFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ApkAssets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
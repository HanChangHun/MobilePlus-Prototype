package android.graphics;

import android.content.res.AssetManager;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontVariationAxis;
import android.text.TextUtils;
import dalvik.annotation.optimization.CriticalNative;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import libcore.util.NativeAllocationRegistry;

@Deprecated
public class FontFamily {
  private static String TAG = "FontFamily";
  
  private static final NativeAllocationRegistry sBuilderRegistry = NativeAllocationRegistry.createMalloced(FontFamily.class.getClassLoader(), nGetBuilderReleaseFunc());
  
  private static final NativeAllocationRegistry sFamilyRegistry = NativeAllocationRegistry.createMalloced(FontFamily.class.getClassLoader(), nGetFamilyReleaseFunc());
  
  private long mBuilderPtr;
  
  private Runnable mNativeBuilderCleaner;
  
  public long mNativePtr;
  
  public FontFamily() {
    long l = nInitBuilder(null, 0);
    this.mBuilderPtr = l;
    this.mNativeBuilderCleaner = sBuilderRegistry.registerNativeAllocation(this, l);
  }
  
  public FontFamily(String[] paramArrayOfString, int paramInt) {
    String str;
    if (paramArrayOfString == null || paramArrayOfString.length == 0) {
      paramArrayOfString = null;
    } else if (paramArrayOfString.length == 1) {
      str = paramArrayOfString[0];
    } else {
      str = TextUtils.join(",", (Object[])str);
    } 
    long l = nInitBuilder(str, paramInt);
    this.mBuilderPtr = l;
    this.mNativeBuilderCleaner = sBuilderRegistry.registerNativeAllocation(this, l);
  }
  
  @CriticalNative
  private static native void nAddAxisValue(long paramLong, int paramInt, float paramFloat);
  
  private static native boolean nAddFont(long paramLong, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3);
  
  private static native boolean nAddFontWeightStyle(long paramLong, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3);
  
  @CriticalNative
  private static native long nCreateFamily(long paramLong);
  
  @CriticalNative
  private static native long nGetBuilderReleaseFunc();
  
  @CriticalNative
  private static native long nGetFamilyReleaseFunc();
  
  private static native long nInitBuilder(String paramString, int paramInt);
  
  public void abortCreation() {
    if (this.mBuilderPtr != 0L) {
      this.mNativeBuilderCleaner.run();
      this.mBuilderPtr = 0L;
      return;
    } 
    throw new IllegalStateException("This FontFamily is already frozen or abandoned");
  }
  
  public boolean addFont(String paramString, int paramInt1, FontVariationAxis[] paramArrayOfFontVariationAxis, int paramInt2, int paramInt3) {
    if (this.mBuilderPtr != 0L) {
      try {
        FileInputStream fileInputStream = new FileInputStream();
        try {
          this(paramString);
          try {
            FileChannel fileChannel = fileInputStream.getChannel();
            long l = fileChannel.size();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, l);
            if (paramArrayOfFontVariationAxis != null) {
              int i = paramArrayOfFontVariationAxis.length;
              for (byte b = 0; b < i; b++) {
                FontVariationAxis fontVariationAxis = paramArrayOfFontVariationAxis[b];
                nAddAxisValue(this.mBuilderPtr, fontVariationAxis.getOpenTypeTagValue(), fontVariationAxis.getStyleValue());
              } 
            } 
            return nAddFont(this.mBuilderPtr, mappedByteBuffer, paramInt1, paramInt2, paramInt3);
          } finally {
            try {
              fileInputStream.close();
            } finally {
              paramArrayOfFontVariationAxis = null;
            } 
          } 
        } catch (IOException iOException) {}
      } catch (IOException iOException) {}
      return false;
    } 
    throw new IllegalStateException("Unable to call addFont after freezing.");
  }
  
  public boolean addFontFromAssetManager(AssetManager paramAssetManager, String paramString, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, FontVariationAxis[] paramArrayOfFontVariationAxis) {
    if (this.mBuilderPtr != 0L)
      try {
        return addFontFromBuffer(Font.Builder.createBuffer(paramAssetManager, paramString, paramBoolean, paramInt1), paramInt2, paramArrayOfFontVariationAxis, paramInt3, paramInt4);
      } catch (IOException iOException) {
        return false;
      }  
    throw new IllegalStateException("Unable to call addFontFromAsset after freezing.");
  }
  
  public boolean addFontFromBuffer(ByteBuffer paramByteBuffer, int paramInt1, FontVariationAxis[] paramArrayOfFontVariationAxis, int paramInt2, int paramInt3) {
    if (this.mBuilderPtr != 0L) {
      if (paramArrayOfFontVariationAxis != null) {
        int i = paramArrayOfFontVariationAxis.length;
        for (byte b = 0; b < i; b++) {
          FontVariationAxis fontVariationAxis = paramArrayOfFontVariationAxis[b];
          nAddAxisValue(this.mBuilderPtr, fontVariationAxis.getOpenTypeTagValue(), fontVariationAxis.getStyleValue());
        } 
      } 
      return nAddFontWeightStyle(this.mBuilderPtr, paramByteBuffer, paramInt1, paramInt2, paramInt3);
    } 
    throw new IllegalStateException("Unable to call addFontWeightStyle after freezing.");
  }
  
  public boolean freeze() {
    long l = this.mBuilderPtr;
    if (l != 0L) {
      boolean bool;
      this.mNativePtr = nCreateFamily(l);
      this.mNativeBuilderCleaner.run();
      this.mBuilderPtr = 0L;
      l = this.mNativePtr;
      if (l != 0L)
        sFamilyRegistry.registerNativeAllocation(this, l); 
      if (this.mNativePtr != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    throw new IllegalStateException("This FontFamily is already frozen");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/FontFamily.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
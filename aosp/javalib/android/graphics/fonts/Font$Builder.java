package android.graphics.fonts;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.ParcelFileDescriptor;
import android.util.TypedValue;
import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import libcore.util.NativeAllocationRegistry;

public final class Builder {
  private static final NativeAllocationRegistry sFontRegistry = NativeAllocationRegistry.createMalloced(Font.class.getClassLoader(), nGetReleaseNativeFont());
  
  private FontVariationAxis[] mAxes;
  
  private ByteBuffer mBuffer;
  
  private IOException mException;
  
  private File mFile;
  
  private int mItalic;
  
  private String mLocaleList;
  
  private int mTtcIndex;
  
  private int mWeight;
  
  public Builder(AssetManager paramAssetManager, String paramString) {
    this.mLocaleList = "";
    this.mWeight = -1;
    this.mItalic = -1;
    this.mTtcIndex = 0;
    this.mAxes = null;
    try {
      this.mBuffer = createBuffer(paramAssetManager, paramString, true, 0);
    } catch (IOException iOException) {
      this.mException = iOException;
    } 
  }
  
  public Builder(AssetManager paramAssetManager, String paramString, boolean paramBoolean, int paramInt) {
    this.mLocaleList = "";
    this.mWeight = -1;
    this.mItalic = -1;
    this.mTtcIndex = 0;
    this.mAxes = null;
    try {
      this.mBuffer = createBuffer(paramAssetManager, paramString, paramBoolean, paramInt);
    } catch (IOException iOException) {
      this.mException = iOException;
    } 
  }
  
  public Builder(Resources paramResources, int paramInt) {
    StringBuilder stringBuilder;
    this.mLocaleList = "";
    this.mWeight = -1;
    this.mItalic = -1;
    this.mTtcIndex = 0;
    this.mAxes = null;
    TypedValue typedValue = new TypedValue();
    paramResources.getValue(paramInt, typedValue, true);
    if (typedValue.string == null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramInt);
      stringBuilder.append(" not found");
      this.mException = new FileNotFoundException(stringBuilder.toString());
      return;
    } 
    String str = typedValue.string.toString();
    if (str.toLowerCase().endsWith(".xml")) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramInt);
      stringBuilder.append(" must be font file.");
      this.mException = new FileNotFoundException(stringBuilder.toString());
      return;
    } 
    try {
      this.mBuffer = createBuffer(stringBuilder.getAssets(), str, false, typedValue.assetCookie);
    } catch (IOException iOException) {
      this.mException = iOException;
    } 
  }
  
  public Builder(ParcelFileDescriptor paramParcelFileDescriptor) {
    this(paramParcelFileDescriptor, 0L, -1L);
  }
  
  public Builder(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, long paramLong2) {
    this.mLocaleList = "";
    this.mWeight = -1;
    this.mItalic = -1;
    this.mTtcIndex = 0;
    this.mAxes = null;
    long l = paramLong2;
    try {
      FileInputStream fileInputStream = new FileInputStream();
      l = paramLong2;
      this(paramParcelFileDescriptor.getFileDescriptor());
      try {
        FileChannel fileChannel = fileInputStream.getChannel();
        if (paramLong2 == -1L) {
          l = fileChannel.size();
          paramLong2 = l - paramLong1;
        } 
        try {
          this.mBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, paramLong1, paramLong2);
          try {
            fileInputStream.close();
          } catch (IOException null) {}
        } finally {}
      } finally {}
      try {
        fileInputStream.close();
      } finally {
        fileInputStream = null;
        l = paramLong2;
      } 
    } catch (IOException iOException) {}
    this.mException = iOException;
  }
  
  public Builder(File paramFile) {
    this.mLocaleList = "";
    this.mWeight = -1;
    this.mItalic = -1;
    this.mTtcIndex = 0;
    this.mAxes = null;
    Preconditions.checkNotNull(paramFile, "path can not be null");
    try {
      FileInputStream fileInputStream = new FileInputStream();
      this(paramFile);
      try {
        FileChannel fileChannel = fileInputStream.getChannel();
        this.mBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, fileChannel.size());
      } finally {
        try {
          fileInputStream.close();
        } finally {
          fileInputStream = null;
        } 
      } 
    } catch (IOException iOException) {
      this.mException = iOException;
    } 
    this.mFile = paramFile;
  }
  
  public Builder(ByteBuffer paramByteBuffer) {
    this.mLocaleList = "";
    this.mWeight = -1;
    this.mItalic = -1;
    this.mTtcIndex = 0;
    this.mAxes = null;
    Preconditions.checkNotNull(paramByteBuffer, "buffer can not be null");
    if (paramByteBuffer.isDirect()) {
      this.mBuffer = paramByteBuffer;
      return;
    } 
    throw new IllegalArgumentException("Only direct buffer can be used as the source of font data.");
  }
  
  public Builder(ByteBuffer paramByteBuffer, File paramFile, String paramString) {
    this(paramByteBuffer);
    this.mFile = paramFile;
    this.mLocaleList = paramString;
  }
  
  public static ByteBuffer createBuffer(AssetManager paramAssetManager, String paramString, boolean paramBoolean, int paramInt) throws IOException {
    Preconditions.checkNotNull(paramAssetManager, "assetManager can not be null");
    Preconditions.checkNotNull(paramString, "path can not be null");
    if (paramBoolean) {
      try {
        null = paramAssetManager.openFd(paramString);
      } catch (IOException iOException) {}
    } else if (paramInt > 0) {
      null = paramAssetManager.openNonAssetFd(paramInt, paramString);
    } else {
      null = paramAssetManager.openNonAssetFd(paramString);
    } 
    FileInputStream fileInputStream = null.createInputStream();
    try {
      FileChannel fileChannel = fileInputStream.getChannel();
      long l1 = null.getStartOffset();
      long l2 = null.getDeclaredLength();
      return fileChannel.map(FileChannel.MapMode.READ_ONLY, l1, l2);
    } finally {
      if (fileInputStream != null)
        try {
          fileInputStream.close();
        } finally {
          fileInputStream = null;
        }  
    } 
  }
  
  @CriticalNative
  private static native void nAddAxis(long paramLong, int paramInt, float paramFloat);
  
  private static native long nBuild(long paramLong, ByteBuffer paramByteBuffer, String paramString, int paramInt1, boolean paramBoolean, int paramInt2);
  
  @CriticalNative
  private static native long nGetReleaseNativeFont();
  
  private static native long nInitBuilder();
  
  public Font build() throws IOException {
    if (this.mException == null) {
      boolean bool;
      String str;
      int i = this.mWeight;
      byte b = 0;
      if (i == -1 || this.mItalic == -1) {
        i = FontFileUtil.analyzeStyle(this.mBuffer, this.mTtcIndex, this.mAxes);
        if (FontFileUtil.isSuccess(i)) {
          if (this.mWeight == -1)
            this.mWeight = FontFileUtil.unpackWeight(i); 
          if (this.mItalic == -1)
            this.mItalic = FontFileUtil.unpackItalic(i); 
        } else {
          this.mWeight = 400;
          this.mItalic = 0;
        } 
      } 
      int j = Math.min(1000, this.mWeight);
      i = 1;
      this.mWeight = Math.max(1, j);
      if (this.mItalic == 1) {
        bool = true;
      } else {
        bool = false;
      } 
      if (this.mItalic != 1)
        i = 0; 
      long l = nInitBuilder();
      FontVariationAxis[] arrayOfFontVariationAxis = this.mAxes;
      if (arrayOfFontVariationAxis != null) {
        j = arrayOfFontVariationAxis.length;
        while (b < j) {
          FontVariationAxis fontVariationAxis = arrayOfFontVariationAxis[b];
          nAddAxis(l, fontVariationAxis.getOpenTypeTagValue(), fontVariationAxis.getStyleValue());
          b++;
        } 
      } 
      ByteBuffer byteBuffer = this.mBuffer.asReadOnlyBuffer();
      File file = this.mFile;
      if (file == null) {
        str = "";
      } else {
        str = str.getAbsolutePath();
      } 
      l = nBuild(l, byteBuffer, str, this.mWeight, bool, this.mTtcIndex);
      Font font = new Font(l, byteBuffer, this.mFile, new FontStyle(this.mWeight, i), this.mTtcIndex, this.mAxes, this.mLocaleList, null);
      sFontRegistry.registerNativeAllocation(font, l);
      return font;
    } 
    throw new IOException("Failed to read font contents", this.mException);
  }
  
  public Builder setFontVariationSettings(String paramString) {
    this.mAxes = FontVariationAxis.fromFontVariationSettings(paramString);
    return this;
  }
  
  public Builder setFontVariationSettings(FontVariationAxis[] paramArrayOfFontVariationAxis) {
    if (paramArrayOfFontVariationAxis == null) {
      paramArrayOfFontVariationAxis = null;
    } else {
      paramArrayOfFontVariationAxis = (FontVariationAxis[])paramArrayOfFontVariationAxis.clone();
    } 
    this.mAxes = paramArrayOfFontVariationAxis;
    return this;
  }
  
  public Builder setSlant(int paramInt) {
    if (paramInt == 0) {
      paramInt = 0;
    } else {
      paramInt = 1;
    } 
    this.mItalic = paramInt;
    return this;
  }
  
  public Builder setTtcIndex(int paramInt) {
    this.mTtcIndex = paramInt;
    return this;
  }
  
  public Builder setWeight(int paramInt) {
    boolean bool = true;
    if (1 > paramInt || paramInt > 1000)
      bool = false; 
    Preconditions.checkArgument(bool);
    this.mWeight = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/fonts/Font$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
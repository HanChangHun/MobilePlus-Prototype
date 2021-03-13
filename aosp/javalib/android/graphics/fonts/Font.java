package android.graphics.fonts;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.LocaleList;
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
import java.util.Arrays;
import java.util.Objects;
import libcore.util.NativeAllocationRegistry;

public final class Font {
  private static final int NOT_SPECIFIED = -1;
  
  private static final int STYLE_ITALIC = 1;
  
  private static final int STYLE_NORMAL = 0;
  
  private static final String TAG = "Font";
  
  private final FontVariationAxis[] mAxes;
  
  private final ByteBuffer mBuffer;
  
  private final File mFile;
  
  private final FontStyle mFontStyle;
  
  private final String mLocaleList;
  
  private final long mNativePtr;
  
  private final int mTtcIndex;
  
  private Font(long paramLong, ByteBuffer paramByteBuffer, File paramFile, FontStyle paramFontStyle, int paramInt, FontVariationAxis[] paramArrayOfFontVariationAxis, String paramString) {
    this.mBuffer = paramByteBuffer;
    this.mFile = paramFile;
    this.mFontStyle = paramFontStyle;
    this.mNativePtr = paramLong;
    this.mTtcIndex = paramInt;
    this.mAxes = paramArrayOfFontVariationAxis;
    this.mLocaleList = paramString;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (paramObject == null || !(paramObject instanceof Font))
      return false; 
    paramObject = paramObject;
    if (!this.mFontStyle.equals(((Font)paramObject).mFontStyle) || ((Font)paramObject).mTtcIndex != this.mTtcIndex || !Arrays.equals((Object[])((Font)paramObject).mAxes, (Object[])this.mAxes) || !((Font)paramObject).mBuffer.equals(this.mBuffer) || !Objects.equals(((Font)paramObject).mLocaleList, this.mLocaleList))
      bool = false; 
    return bool;
  }
  
  public FontVariationAxis[] getAxes() {
    FontVariationAxis[] arrayOfFontVariationAxis = this.mAxes;
    if (arrayOfFontVariationAxis == null) {
      arrayOfFontVariationAxis = null;
    } else {
      arrayOfFontVariationAxis = (FontVariationAxis[])arrayOfFontVariationAxis.clone();
    } 
    return arrayOfFontVariationAxis;
  }
  
  public ByteBuffer getBuffer() {
    return this.mBuffer;
  }
  
  public File getFile() {
    return this.mFile;
  }
  
  public LocaleList getLocaleList() {
    return LocaleList.forLanguageTags(this.mLocaleList);
  }
  
  public long getNativePtr() {
    return this.mNativePtr;
  }
  
  public FontStyle getStyle() {
    return this.mFontStyle;
  }
  
  public int getTtcIndex() {
    return this.mTtcIndex;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mFontStyle, Integer.valueOf(this.mTtcIndex), Integer.valueOf(Arrays.hashCode((Object[])this.mAxes)), this.mBuffer, this.mLocaleList });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Font {path=");
    stringBuilder.append(this.mFile);
    stringBuilder.append(", style=");
    stringBuilder.append(this.mFontStyle);
    stringBuilder.append(", ttcIndex=");
    stringBuilder.append(this.mTtcIndex);
    stringBuilder.append(", axes=");
    stringBuilder.append(FontVariationAxis.toFontVariationSettings(this.mAxes));
    stringBuilder.append(", localeList=");
    stringBuilder.append(this.mLocaleList);
    stringBuilder.append(", buffer=");
    stringBuilder.append(this.mBuffer);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public static final class Builder {
    private static final NativeAllocationRegistry sFontRegistry = NativeAllocationRegistry.createMalloced(Font.class.getClassLoader(), nGetReleaseNativeFont());
    
    private FontVariationAxis[] mAxes;
    
    private ByteBuffer mBuffer;
    
    private IOException mException;
    
    private File mFile;
    
    private int mItalic;
    
    private String mLocaleList;
    
    private int mTtcIndex;
    
    private int mWeight;
    
    public Builder(AssetManager param1AssetManager, String param1String) {
      this.mLocaleList = "";
      this.mWeight = -1;
      this.mItalic = -1;
      this.mTtcIndex = 0;
      this.mAxes = null;
      try {
        this.mBuffer = createBuffer(param1AssetManager, param1String, true, 0);
      } catch (IOException iOException) {
        this.mException = iOException;
      } 
    }
    
    public Builder(AssetManager param1AssetManager, String param1String, boolean param1Boolean, int param1Int) {
      this.mLocaleList = "";
      this.mWeight = -1;
      this.mItalic = -1;
      this.mTtcIndex = 0;
      this.mAxes = null;
      try {
        this.mBuffer = createBuffer(param1AssetManager, param1String, param1Boolean, param1Int);
      } catch (IOException iOException) {
        this.mException = iOException;
      } 
    }
    
    public Builder(Resources param1Resources, int param1Int) {
      StringBuilder stringBuilder;
      this.mLocaleList = "";
      this.mWeight = -1;
      this.mItalic = -1;
      this.mTtcIndex = 0;
      this.mAxes = null;
      TypedValue typedValue = new TypedValue();
      param1Resources.getValue(param1Int, typedValue, true);
      if (typedValue.string == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(param1Int);
        stringBuilder.append(" not found");
        this.mException = new FileNotFoundException(stringBuilder.toString());
        return;
      } 
      String str = typedValue.string.toString();
      if (str.toLowerCase().endsWith(".xml")) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(param1Int);
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
    
    public Builder(ParcelFileDescriptor param1ParcelFileDescriptor) {
      this(param1ParcelFileDescriptor, 0L, -1L);
    }
    
    public Builder(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long1, long param1Long2) {
      this.mLocaleList = "";
      this.mWeight = -1;
      this.mItalic = -1;
      this.mTtcIndex = 0;
      this.mAxes = null;
      long l = param1Long2;
      try {
        FileInputStream fileInputStream = new FileInputStream();
        l = param1Long2;
        this(param1ParcelFileDescriptor.getFileDescriptor());
        try {
          FileChannel fileChannel = fileInputStream.getChannel();
          if (param1Long2 == -1L) {
            l = fileChannel.size();
            param1Long2 = l - param1Long1;
          } 
          try {
            this.mBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, param1Long1, param1Long2);
            try {
              fileInputStream.close();
            } catch (IOException null) {}
          } finally {}
        } finally {}
        try {
          fileInputStream.close();
        } finally {
          fileInputStream = null;
          l = param1Long2;
        } 
      } catch (IOException iOException) {}
      this.mException = iOException;
    }
    
    public Builder(File param1File) {
      this.mLocaleList = "";
      this.mWeight = -1;
      this.mItalic = -1;
      this.mTtcIndex = 0;
      this.mAxes = null;
      Preconditions.checkNotNull(param1File, "path can not be null");
      try {
        FileInputStream fileInputStream = new FileInputStream();
        this(param1File);
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
      this.mFile = param1File;
    }
    
    public Builder(ByteBuffer param1ByteBuffer) {
      this.mLocaleList = "";
      this.mWeight = -1;
      this.mItalic = -1;
      this.mTtcIndex = 0;
      this.mAxes = null;
      Preconditions.checkNotNull(param1ByteBuffer, "buffer can not be null");
      if (param1ByteBuffer.isDirect()) {
        this.mBuffer = param1ByteBuffer;
        return;
      } 
      throw new IllegalArgumentException("Only direct buffer can be used as the source of font data.");
    }
    
    public Builder(ByteBuffer param1ByteBuffer, File param1File, String param1String) {
      this(param1ByteBuffer);
      this.mFile = param1File;
      this.mLocaleList = param1String;
    }
    
    public static ByteBuffer createBuffer(AssetManager param1AssetManager, String param1String, boolean param1Boolean, int param1Int) throws IOException {
      Preconditions.checkNotNull(param1AssetManager, "assetManager can not be null");
      Preconditions.checkNotNull(param1String, "path can not be null");
      if (param1Boolean) {
        try {
          null = param1AssetManager.openFd(param1String);
        } catch (IOException iOException) {}
      } else if (param1Int > 0) {
        null = param1AssetManager.openNonAssetFd(param1Int, param1String);
      } else {
        null = param1AssetManager.openNonAssetFd(param1String);
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
    private static native void nAddAxis(long param1Long, int param1Int, float param1Float);
    
    private static native long nBuild(long param1Long, ByteBuffer param1ByteBuffer, String param1String, int param1Int1, boolean param1Boolean, int param1Int2);
    
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
        Font font = new Font(l, byteBuffer, this.mFile, new FontStyle(this.mWeight, i), this.mTtcIndex, this.mAxes, this.mLocaleList);
        sFontRegistry.registerNativeAllocation(font, l);
        return font;
      } 
      throw new IOException("Failed to read font contents", this.mException);
    }
    
    public Builder setFontVariationSettings(String param1String) {
      this.mAxes = FontVariationAxis.fromFontVariationSettings(param1String);
      return this;
    }
    
    public Builder setFontVariationSettings(FontVariationAxis[] param1ArrayOfFontVariationAxis) {
      if (param1ArrayOfFontVariationAxis == null) {
        param1ArrayOfFontVariationAxis = null;
      } else {
        param1ArrayOfFontVariationAxis = (FontVariationAxis[])param1ArrayOfFontVariationAxis.clone();
      } 
      this.mAxes = param1ArrayOfFontVariationAxis;
      return this;
    }
    
    public Builder setSlant(int param1Int) {
      if (param1Int == 0) {
        param1Int = 0;
      } else {
        param1Int = 1;
      } 
      this.mItalic = param1Int;
      return this;
    }
    
    public Builder setTtcIndex(int param1Int) {
      this.mTtcIndex = param1Int;
      return this;
    }
    
    public Builder setWeight(int param1Int) {
      boolean bool = true;
      if (1 > param1Int || param1Int > 1000)
        bool = false; 
      Preconditions.checkArgument(bool);
      this.mWeight = param1Int;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/fonts/Font.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
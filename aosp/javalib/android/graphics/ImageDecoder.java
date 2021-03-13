package android.graphics;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Size;
import android.util.TypedValue;
import dalvik.system.CloseGuard;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import libcore.io.IoUtils;

public final class ImageDecoder implements AutoCloseable {
  public static final int ALLOCATOR_DEFAULT = 0;
  
  public static final int ALLOCATOR_HARDWARE = 3;
  
  public static final int ALLOCATOR_SHARED_MEMORY = 2;
  
  public static final int ALLOCATOR_SOFTWARE = 1;
  
  @Deprecated
  public static final int ERROR_SOURCE_ERROR = 3;
  
  @Deprecated
  public static final int ERROR_SOURCE_EXCEPTION = 1;
  
  @Deprecated
  public static final int ERROR_SOURCE_INCOMPLETE = 2;
  
  public static final int MEMORY_POLICY_DEFAULT = 1;
  
  public static final int MEMORY_POLICY_LOW_RAM = 0;
  
  public static int sApiLevel;
  
  private int mAllocator = 0;
  
  private final boolean mAnimated;
  
  private AssetFileDescriptor mAssetFd;
  
  private final CloseGuard mCloseGuard;
  
  private final AtomicBoolean mClosed = new AtomicBoolean();
  
  private boolean mConserveMemory = false;
  
  private Rect mCropRect;
  
  private boolean mDecodeAsAlphaMask = false;
  
  private ColorSpace mDesiredColorSpace = null;
  
  private int mDesiredHeight;
  
  private int mDesiredWidth;
  
  private final int mHeight;
  
  private InputStream mInputStream;
  
  private final boolean mIsNinePatch;
  
  private boolean mMutable = false;
  
  private long mNativePtr;
  
  private OnPartialImageListener mOnPartialImageListener;
  
  private Rect mOutPaddingRect;
  
  private boolean mOwnsInputStream;
  
  private PostProcessor mPostProcessor;
  
  private Source mSource;
  
  private byte[] mTempStorage;
  
  private boolean mUnpremultipliedRequired = false;
  
  private final int mWidth;
  
  private ImageDecoder(long paramLong, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
    CloseGuard closeGuard = CloseGuard.get();
    this.mCloseGuard = closeGuard;
    this.mNativePtr = paramLong;
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    this.mDesiredWidth = paramInt1;
    this.mDesiredHeight = paramInt2;
    this.mAnimated = paramBoolean1;
    this.mIsNinePatch = paramBoolean2;
    closeGuard.open("close");
  }
  
  private void callHeaderDecoded(OnHeaderDecodedListener paramOnHeaderDecodedListener, Source paramSource) {
    if (paramOnHeaderDecodedListener != null) {
      ImageInfo imageInfo = new ImageInfo(this);
      try {
        paramOnHeaderDecodedListener.onHeaderDecoded(this, imageInfo, paramSource);
      } finally {
        ImageInfo.access$1302(imageInfo, null);
      } 
    } 
  }
  
  private boolean checkForExtended() {
    ColorSpace colorSpace = this.mDesiredColorSpace;
    boolean bool = false;
    if (colorSpace == null)
      return false; 
    if (colorSpace == ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB) || this.mDesiredColorSpace == ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB))
      bool = true; 
    return bool;
  }
  
  private void checkState(boolean paramBoolean) {
    if (this.mNativePtr != 0L) {
      checkSubset(this.mDesiredWidth, this.mDesiredHeight, this.mCropRect);
      if (!paramBoolean && this.mAllocator == 3)
        if (!this.mMutable) {
          if (this.mDecodeAsAlphaMask)
            throw new IllegalStateException("Cannot make HARDWARE Alpha mask Bitmap!"); 
        } else {
          throw new IllegalStateException("Cannot make mutable HARDWARE Bitmap!");
        }  
      if (this.mPostProcessor == null || !this.mUnpremultipliedRequired)
        return; 
      throw new IllegalStateException("Cannot draw to unpremultiplied pixels!");
    } 
    throw new IllegalStateException("Cannot use closed ImageDecoder!");
  }
  
  private static void checkSubset(int paramInt1, int paramInt2, Rect paramRect) {
    if (paramRect == null)
      return; 
    if (paramRect.width() > 0 && paramRect.height() > 0) {
      if (paramRect.left >= 0 && paramRect.top >= 0 && paramRect.right <= paramInt1 && paramRect.bottom <= paramInt2)
        return; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Subset ");
      stringBuilder1.append(paramRect);
      stringBuilder1.append(" not contained by scaled image bounds: (");
      stringBuilder1.append(paramInt1);
      stringBuilder1.append(" x ");
      stringBuilder1.append(paramInt2);
      stringBuilder1.append(")");
      throw new IllegalStateException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Subset ");
    stringBuilder.append(paramRect);
    stringBuilder.append(" is empty/unsorted");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private int computeDensity(Source paramSource) {
    if (requestedResize())
      return 0; 
    int i = paramSource.getDensity();
    if (i == 0)
      return i; 
    if (this.mIsNinePatch && this.mPostProcessor == null)
      return i; 
    Resources resources = paramSource.getResources();
    if (resources != null && (resources.getDisplayMetrics()).noncompatDensityDpi == i)
      return i; 
    int j = paramSource.computeDstDensity();
    if (i == j)
      return i; 
    if (i < j && sApiLevel >= 28)
      return i; 
    float f = j / i;
    setTargetSize(Math.max((int)(this.mWidth * f + 0.5F), 1), Math.max((int)(this.mHeight * f + 0.5F), 1));
    return j;
  }
  
  private static ImageDecoder createFromAsset(AssetManager.AssetInputStream paramAssetInputStream, boolean paramBoolean, Source paramSource) throws IOException {
    try {
      ImageDecoder imageDecoder = nCreate(paramAssetInputStream.getNativeAsset(), paramBoolean, paramSource);
      return imageDecoder;
    } finally {
      if (!false)
        IoUtils.closeQuietly((AutoCloseable)paramAssetInputStream); 
    } 
  }
  
  private static ImageDecoder createFromAssetFileDescriptor(AssetFileDescriptor paramAssetFileDescriptor, boolean paramBoolean, Source paramSource) throws IOException {
    if (paramAssetFileDescriptor != null) {
      ImageDecoder imageDecoder;
      FileDescriptor fileDescriptor = paramAssetFileDescriptor.getFileDescriptor();
      long l = paramAssetFileDescriptor.getStartOffset();
      try {
        Os.lseek(fileDescriptor, l, OsConstants.SEEK_SET);
        ImageDecoder imageDecoder1 = nCreate(fileDescriptor, paramBoolean, paramSource);
        imageDecoder = imageDecoder1;
      } catch (ErrnoException errnoException) {
        FileInputStream fileInputStream = new FileInputStream();
        this(fileDescriptor);
        imageDecoder = createFromStream(fileInputStream, true, paramBoolean, (Source)imageDecoder);
      } finally {}
      if (imageDecoder == null) {
        IoUtils.closeQuietly((AutoCloseable)paramAssetFileDescriptor);
      } else {
        imageDecoder.mAssetFd = paramAssetFileDescriptor;
      } 
      return imageDecoder;
    } 
    throw new FileNotFoundException();
  }
  
  private static ImageDecoder createFromFile(File paramFile, boolean paramBoolean, Source paramSource) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(paramFile);
    FileDescriptor fileDescriptor = fileInputStream.getFD();
    try {
      Os.lseek(fileDescriptor, 0L, OsConstants.SEEK_CUR);
      try {
        ImageDecoder imageDecoder = nCreate(fileDescriptor, paramBoolean, paramSource);
        return imageDecoder;
      } finally {
        if (!false)
          IoUtils.closeQuietly(fileInputStream); 
      } 
    } catch (ErrnoException errnoException) {
      return createFromStream(fileInputStream, true, paramBoolean, paramSource);
    } 
  }
  
  private static ImageDecoder createFromStream(InputStream paramInputStream, boolean paramBoolean1, boolean paramBoolean2, Source paramSource) throws IOException {
    byte[] arrayOfByte = new byte[16384];
    try {
      ImageDecoder imageDecoder = nCreate(paramInputStream, arrayOfByte, paramBoolean2, paramSource);
      return imageDecoder;
    } finally {
      if (!false && paramBoolean1)
        IoUtils.closeQuietly(paramInputStream); 
    } 
  }
  
  public static Source createSource(ContentResolver paramContentResolver, Uri paramUri) {
    return new ContentResolverSource(paramContentResolver, paramUri, null);
  }
  
  public static Source createSource(ContentResolver paramContentResolver, Uri paramUri, Resources paramResources) {
    return new ContentResolverSource(paramContentResolver, paramUri, paramResources);
  }
  
  public static Source createSource(AssetManager paramAssetManager, String paramString) {
    return new AssetSource(paramAssetManager, paramString);
  }
  
  public static Source createSource(Resources paramResources, int paramInt) {
    return new ResourceSource(paramResources, paramInt);
  }
  
  public static Source createSource(Resources paramResources, InputStream paramInputStream) {
    return new InputStreamSource(paramResources, paramInputStream, Bitmap.getDefaultDensity());
  }
  
  public static Source createSource(Resources paramResources, InputStream paramInputStream, int paramInt) {
    return new InputStreamSource(paramResources, paramInputStream, paramInt);
  }
  
  public static Source createSource(File paramFile) {
    return new FileSource(paramFile);
  }
  
  public static Source createSource(ByteBuffer paramByteBuffer) {
    return new ByteBufferSource(paramByteBuffer);
  }
  
  public static Source createSource(Callable<AssetFileDescriptor> paramCallable) {
    return new CallableSource(paramCallable);
  }
  
  public static Source createSource(byte[] paramArrayOfbyte) {
    return createSource(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static Source createSource(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws ArrayIndexOutOfBoundsException {
    if (paramArrayOfbyte != null) {
      if (paramInt1 >= 0 && paramInt2 >= 0 && paramInt1 < paramArrayOfbyte.length && paramInt1 + paramInt2 <= paramArrayOfbyte.length)
        return new ByteArraySource(paramArrayOfbyte, paramInt1, paramInt2); 
      throw new ArrayIndexOutOfBoundsException("invalid offset/length!");
    } 
    throw new NullPointerException("null byte[] in createSource!");
  }
  
  public static Bitmap decodeBitmap(Source paramSource) throws IOException {
    return decodeBitmapImpl(paramSource, null);
  }
  
  public static Bitmap decodeBitmap(Source paramSource, OnHeaderDecodedListener paramOnHeaderDecodedListener) throws IOException {
    if (paramOnHeaderDecodedListener != null)
      return decodeBitmapImpl(paramSource, paramOnHeaderDecodedListener); 
    throw new IllegalArgumentException("listener cannot be null! Use decodeBitmap(Source) to not have a listener");
  }
  
  private static Bitmap decodeBitmapImpl(Source paramSource, OnHeaderDecodedListener paramOnHeaderDecodedListener) throws IOException {
    ImageDecoder imageDecoder = paramSource.createImageDecoder(false);
    try {
      imageDecoder.mSource = paramSource;
      imageDecoder.callHeaderDecoded(paramOnHeaderDecodedListener, paramSource);
      int i = imageDecoder.computeDensity(paramSource);
      Bitmap bitmap = imageDecoder.decodeBitmapInternal();
      bitmap.setDensity(i);
      Rect rect = imageDecoder.mOutPaddingRect;
      if (rect != null) {
        byte[] arrayOfByte = bitmap.getNinePatchChunk();
        if (arrayOfByte != null && NinePatch.isNinePatchChunk(arrayOfByte))
          nGetPadding(imageDecoder.mNativePtr, rect); 
      } 
      return bitmap;
    } finally {
      if (imageDecoder != null)
        try {
          imageDecoder.close();
        } finally {
          paramOnHeaderDecodedListener = null;
        }  
    } 
  }
  
  private Bitmap decodeBitmapInternal() throws IOException {
    boolean bool = false;
    checkState(false);
    long l = this.mNativePtr;
    if (this.mPostProcessor != null)
      bool = true; 
    return nDecodeBitmap(l, this, bool, this.mDesiredWidth, this.mDesiredHeight, this.mCropRect, this.mMutable, this.mAllocator, this.mUnpremultipliedRequired, this.mConserveMemory, this.mDecodeAsAlphaMask, getColorSpacePtr(), checkForExtended());
  }
  
  public static Drawable decodeDrawable(Source paramSource) throws IOException {
    return decodeDrawableImpl(paramSource, null);
  }
  
  public static Drawable decodeDrawable(Source paramSource, OnHeaderDecodedListener paramOnHeaderDecodedListener) throws IOException {
    if (paramOnHeaderDecodedListener != null)
      return decodeDrawableImpl(paramSource, paramOnHeaderDecodedListener); 
    throw new IllegalArgumentException("listener cannot be null! Use decodeDrawable(Source) to not have a listener");
  }
  
  private static Drawable decodeDrawableImpl(Source paramSource, OnHeaderDecodedListener paramOnHeaderDecodedListener) throws IOException {
    ImageDecoder imageDecoder = paramSource.createImageDecoder(true);
    try {
      imageDecoder.mSource = paramSource;
      imageDecoder.callHeaderDecoded(paramOnHeaderDecodedListener, paramSource);
      if (!imageDecoder.mUnpremultipliedRequired) {
        if (!imageDecoder.mMutable) {
          int i = imageDecoder.computeDensity(paramSource);
          if (imageDecoder.mAnimated) {
            ImageDecoder imageDecoder1;
            if (imageDecoder.mPostProcessor == null) {
              paramOnHeaderDecodedListener = null;
            } else {
              imageDecoder1 = imageDecoder;
            } 
            imageDecoder.checkState(true);
            AnimatedImageDrawable animatedImageDrawable = new AnimatedImageDrawable();
            this(imageDecoder.mNativePtr, imageDecoder1, imageDecoder.mDesiredWidth, imageDecoder.mDesiredHeight, imageDecoder.getColorSpacePtr(), imageDecoder.checkForExtended(), i, paramSource.computeDstDensity(), imageDecoder.mCropRect, imageDecoder.mInputStream, imageDecoder.mAssetFd);
            imageDecoder.mInputStream = null;
            imageDecoder.mAssetFd = null;
            return (Drawable)animatedImageDrawable;
          } 
          Bitmap bitmap = imageDecoder.decodeBitmapInternal();
          bitmap.setDensity(i);
          Resources resources = paramSource.getResources();
          byte[] arrayOfByte = bitmap.getNinePatchChunk();
          if (arrayOfByte != null && NinePatch.isNinePatchChunk(arrayOfByte)) {
            Rect rect2 = new Rect();
            this();
            bitmap.getOpticalInsets(rect2);
            Rect rect1 = imageDecoder.mOutPaddingRect;
            if (rect1 == null) {
              rect1 = new Rect();
              this();
            } 
            nGetPadding(imageDecoder.mNativePtr, rect1);
            return (Drawable)new NinePatchDrawable(resources, bitmap, arrayOfByte, rect1, rect2, null);
          } 
          return (Drawable)new BitmapDrawable(resources, bitmap);
        } 
        IllegalStateException illegalStateException1 = new IllegalStateException();
        this("Cannot decode a mutable Drawable!");
        throw illegalStateException1;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Cannot decode a Drawable with unpremultiplied pixels!");
      throw illegalStateException;
    } finally {
      if (imageDecoder != null)
        try {
          imageDecoder.close();
        } finally {
          paramOnHeaderDecodedListener = null;
        }  
    } 
  }
  
  private ColorSpace getColorSpace() {
    return nGetColorSpace(this.mNativePtr);
  }
  
  private long getColorSpacePtr() {
    ColorSpace colorSpace = this.mDesiredColorSpace;
    return (colorSpace == null) ? 0L : colorSpace.getNativeInstance();
  }
  
  private String getMimeType() {
    return nGetMimeType(this.mNativePtr);
  }
  
  private int getTargetDimension(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt2 >= paramInt1)
      return 1; 
    int i = paramInt1 / paramInt2;
    return (paramInt3 == i) ? paramInt3 : ((Math.abs(paramInt3 * paramInt2 - paramInt1) < paramInt2) ? paramInt3 : i);
  }
  
  public static boolean isMimeTypeSupported(String paramString) {
    byte b;
    Objects.requireNonNull(paramString);
    paramString = paramString.toLowerCase(Locale.US);
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 2111234748:
        if (paramString.equals("image/x-canon-cr2")) {
          b = 10;
          break;
        } 
      case 2099152524:
        if (paramString.equals("image/x-nikon-nrw")) {
          b = 13;
          break;
        } 
      case 2099152104:
        if (paramString.equals("image/x-nikon-nef")) {
          b = 12;
          break;
        } 
      case 1378106698:
        if (paramString.equals("image/x-olympus-orf")) {
          b = 14;
          break;
        } 
      case 1146342924:
        if (paramString.equals("image/x-ico")) {
          b = 7;
          break;
        } 
      case 741270252:
        if (paramString.equals("image/vnd.wap.wbmp")) {
          b = 8;
          break;
        } 
      case -332763809:
        if (paramString.equals("image/x-pentax-pef")) {
          b = 17;
          break;
        } 
      case -879258763:
        if (paramString.equals("image/png")) {
          b = 0;
          break;
        } 
      case -879267568:
        if (paramString.equals("image/gif")) {
          b = 3;
          break;
        } 
      case -879272239:
        if (paramString.equals("image/bmp")) {
          b = 6;
          break;
        } 
      case -985160897:
        if (paramString.equals("image/x-panasonic-rw2")) {
          b = 16;
          break;
        } 
      case -1423313290:
        if (paramString.equals("image/x-adobe-dng")) {
          b = 11;
          break;
        } 
      case -1487018032:
        if (paramString.equals("image/webp")) {
          b = 2;
          break;
        } 
      case -1487394660:
        if (paramString.equals("image/jpeg")) {
          b = 1;
          break;
        } 
      case -1487464690:
        if (paramString.equals("image/heif")) {
          b = 4;
          break;
        } 
      case -1487464693:
        if (paramString.equals("image/heic")) {
          b = 5;
          break;
        } 
      case -1594371159:
        if (paramString.equals("image/x-sony-arw")) {
          b = 9;
          break;
        } 
      case -1635437028:
        if (paramString.equals("image/x-samsung-srw")) {
          b = 18;
          break;
        } 
      case -1875291391:
        if (paramString.equals("image/x-fuji-raf")) {
          b = 15;
          break;
        } 
    } 
    switch (b) {
      default:
        return false;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
        break;
    } 
    return true;
  }
  
  private static native void nClose(long paramLong);
  
  private static native ImageDecoder nCreate(long paramLong, boolean paramBoolean, Source paramSource) throws IOException;
  
  private static native ImageDecoder nCreate(FileDescriptor paramFileDescriptor, boolean paramBoolean, Source paramSource) throws IOException;
  
  private static native ImageDecoder nCreate(InputStream paramInputStream, byte[] paramArrayOfbyte, boolean paramBoolean, Source paramSource) throws IOException;
  
  private static native ImageDecoder nCreate(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, boolean paramBoolean, Source paramSource) throws IOException;
  
  private static native ImageDecoder nCreate(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean, Source paramSource) throws IOException;
  
  private static native Bitmap nDecodeBitmap(long paramLong1, ImageDecoder paramImageDecoder, boolean paramBoolean1, int paramInt1, int paramInt2, Rect paramRect, boolean paramBoolean2, int paramInt3, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, long paramLong2, boolean paramBoolean6) throws IOException;
  
  private static native ColorSpace nGetColorSpace(long paramLong);
  
  private static native String nGetMimeType(long paramLong);
  
  private static native void nGetPadding(long paramLong, Rect paramRect);
  
  private static native Size nGetSampledSize(long paramLong, int paramInt);
  
  private void onPartialImage(int paramInt, Throwable paramThrowable) throws DecodeException {
    DecodeException decodeException = new DecodeException(paramInt, paramThrowable, this.mSource);
    OnPartialImageListener onPartialImageListener = this.mOnPartialImageListener;
    if (onPartialImageListener != null && onPartialImageListener.onPartialImage(decodeException))
      return; 
    throw decodeException;
  }
  
  private int postProcessAndRelease(Canvas paramCanvas) {
    try {
      return this.mPostProcessor.onPostProcess(paramCanvas);
    } finally {
      paramCanvas.release();
    } 
  }
  
  private boolean requestedResize() {
    return (this.mWidth != this.mDesiredWidth || this.mHeight != this.mDesiredHeight);
  }
  
  public void close() {
    this.mCloseGuard.close();
    if (!this.mClosed.compareAndSet(false, true))
      return; 
    nClose(this.mNativePtr);
    this.mNativePtr = 0L;
    if (this.mOwnsInputStream)
      IoUtils.closeQuietly(this.mInputStream); 
    IoUtils.closeQuietly((AutoCloseable)this.mAssetFd);
    this.mInputStream = null;
    this.mAssetFd = null;
    this.mTempStorage = null;
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      this.mInputStream = null;
      this.mAssetFd = null;
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getAllocator() {
    return this.mAllocator;
  }
  
  @Deprecated
  public boolean getAsAlphaMask() {
    return getDecodeAsAlphaMask();
  }
  
  @Deprecated
  public boolean getConserveMemory() {
    return this.mConserveMemory;
  }
  
  public Rect getCrop() {
    return this.mCropRect;
  }
  
  @Deprecated
  public boolean getDecodeAsAlphaMask() {
    return this.mDecodeAsAlphaMask;
  }
  
  public int getMemorySizePolicy() {
    return this.mConserveMemory ^ true;
  }
  
  @Deprecated
  public boolean getMutable() {
    return isMutableRequired();
  }
  
  public OnPartialImageListener getOnPartialImageListener() {
    return this.mOnPartialImageListener;
  }
  
  public PostProcessor getPostProcessor() {
    return this.mPostProcessor;
  }
  
  @Deprecated
  public boolean getRequireUnpremultiplied() {
    return isUnpremultipliedRequired();
  }
  
  public Size getSampledSize(int paramInt) {
    if (paramInt > 0) {
      long l = this.mNativePtr;
      if (l != 0L)
        return nGetSampledSize(l, paramInt); 
      throw new IllegalStateException("ImageDecoder is closed!");
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("sampleSize must be positive! provided ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean isDecodeAsAlphaMaskEnabled() {
    return this.mDecodeAsAlphaMask;
  }
  
  public boolean isMutableRequired() {
    return this.mMutable;
  }
  
  public boolean isUnpremultipliedRequired() {
    return this.mUnpremultipliedRequired;
  }
  
  public void setAllocator(int paramInt) {
    if (paramInt >= 0 && paramInt <= 3) {
      this.mAllocator = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("invalid allocator ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  @Deprecated
  public ImageDecoder setAsAlphaMask(boolean paramBoolean) {
    setDecodeAsAlphaMask(paramBoolean);
    return this;
  }
  
  @Deprecated
  public void setConserveMemory(boolean paramBoolean) {
    this.mConserveMemory = paramBoolean;
  }
  
  public void setCrop(Rect paramRect) {
    this.mCropRect = paramRect;
  }
  
  @Deprecated
  public ImageDecoder setDecodeAsAlphaMask(boolean paramBoolean) {
    setDecodeAsAlphaMaskEnabled(paramBoolean);
    return this;
  }
  
  public void setDecodeAsAlphaMaskEnabled(boolean paramBoolean) {
    this.mDecodeAsAlphaMask = paramBoolean;
  }
  
  public void setMemorySizePolicy(int paramInt) {
    boolean bool;
    if (paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mConserveMemory = bool;
  }
  
  @Deprecated
  public ImageDecoder setMutable(boolean paramBoolean) {
    setMutableRequired(paramBoolean);
    return this;
  }
  
  public void setMutableRequired(boolean paramBoolean) {
    this.mMutable = paramBoolean;
  }
  
  public void setOnPartialImageListener(OnPartialImageListener paramOnPartialImageListener) {
    this.mOnPartialImageListener = paramOnPartialImageListener;
  }
  
  public void setOutPaddingRect(Rect paramRect) {
    this.mOutPaddingRect = paramRect;
  }
  
  public void setPostProcessor(PostProcessor paramPostProcessor) {
    this.mPostProcessor = paramPostProcessor;
  }
  
  @Deprecated
  public ImageDecoder setRequireUnpremultiplied(boolean paramBoolean) {
    setUnpremultipliedRequired(paramBoolean);
    return this;
  }
  
  @Deprecated
  public ImageDecoder setResize(int paramInt) {
    setTargetSampleSize(paramInt);
    return this;
  }
  
  @Deprecated
  public ImageDecoder setResize(int paramInt1, int paramInt2) {
    setTargetSize(paramInt1, paramInt2);
    return this;
  }
  
  public void setTargetColorSpace(ColorSpace paramColorSpace) {
    this.mDesiredColorSpace = paramColorSpace;
  }
  
  public void setTargetSampleSize(int paramInt) {
    Size size = getSampledSize(paramInt);
    setTargetSize(getTargetDimension(this.mWidth, paramInt, size.getWidth()), getTargetDimension(this.mHeight, paramInt, size.getHeight()));
  }
  
  public void setTargetSize(int paramInt1, int paramInt2) {
    if (paramInt1 > 0 && paramInt2 > 0) {
      this.mDesiredWidth = paramInt1;
      this.mDesiredHeight = paramInt2;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Dimensions must be positive! provided (");
    stringBuilder.append(paramInt1);
    stringBuilder.append(", ");
    stringBuilder.append(paramInt2);
    stringBuilder.append(")");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setUnpremultipliedRequired(boolean paramBoolean) {
    this.mUnpremultipliedRequired = paramBoolean;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Allocator {}
  
  public static class AssetInputStreamSource extends Source {
    private AssetManager.AssetInputStream mAssetInputStream;
    
    private final int mDensity;
    
    private final Resources mResources;
    
    public AssetInputStreamSource(AssetManager.AssetInputStream param1AssetInputStream, Resources param1Resources, TypedValue param1TypedValue) {
      this.mAssetInputStream = param1AssetInputStream;
      this.mResources = param1Resources;
      if (param1TypedValue.density == 0) {
        this.mDensity = 160;
      } else if (param1TypedValue.density != 65535) {
        this.mDensity = param1TypedValue.density;
      } else {
        this.mDensity = 0;
      } 
    }
    
    public ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mAssetInputStream : Landroid/content/res/AssetManager$AssetInputStream;
      //   6: ifnull -> 30
      //   9: aload_0
      //   10: getfield mAssetInputStream : Landroid/content/res/AssetManager$AssetInputStream;
      //   13: astore_2
      //   14: aload_0
      //   15: aconst_null
      //   16: putfield mAssetInputStream : Landroid/content/res/AssetManager$AssetInputStream;
      //   19: aload_2
      //   20: iload_1
      //   21: aload_0
      //   22: invokestatic access$500 : (Landroid/content/res/AssetManager$AssetInputStream;ZLandroid/graphics/ImageDecoder$Source;)Landroid/graphics/ImageDecoder;
      //   25: astore_2
      //   26: aload_0
      //   27: monitorexit
      //   28: aload_2
      //   29: areturn
      //   30: new java/io/IOException
      //   33: astore_2
      //   34: aload_2
      //   35: ldc 'Cannot reuse AssetInputStreamSource'
      //   37: invokespecial <init> : (Ljava/lang/String;)V
      //   40: aload_2
      //   41: athrow
      //   42: astore_2
      //   43: aload_0
      //   44: monitorexit
      //   45: aload_2
      //   46: athrow
      // Exception table:
      //   from	to	target	type
      //   2	28	42	finally
      //   30	42	42	finally
      //   43	45	42	finally
    }
    
    public int getDensity() {
      return this.mDensity;
    }
    
    public Resources getResources() {
      return this.mResources;
    }
  }
  
  private static class AssetSource extends Source {
    private final AssetManager mAssets;
    
    private final String mFileName;
    
    AssetSource(AssetManager param1AssetManager, String param1String) {
      this.mAssets = param1AssetManager;
      this.mFileName = param1String;
    }
    
    public ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException {
      return ImageDecoder.createFromAsset((AssetManager.AssetInputStream)this.mAssets.open(this.mFileName), param1Boolean, this);
    }
  }
  
  private static class ByteArraySource extends Source {
    private final byte[] mData;
    
    private final int mLength;
    
    private final int mOffset;
    
    ByteArraySource(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      this.mData = param1ArrayOfbyte;
      this.mOffset = param1Int1;
      this.mLength = param1Int2;
    }
    
    public ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException {
      return ImageDecoder.nCreate(this.mData, this.mOffset, this.mLength, param1Boolean, this);
    }
  }
  
  private static class ByteBufferSource extends Source {
    private final ByteBuffer mBuffer;
    
    ByteBufferSource(ByteBuffer param1ByteBuffer) {
      this.mBuffer = param1ByteBuffer;
    }
    
    public ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException {
      if (!this.mBuffer.isDirect() && this.mBuffer.hasArray()) {
        int i = this.mBuffer.arrayOffset();
        int j = this.mBuffer.position();
        int k = this.mBuffer.limit();
        int m = this.mBuffer.position();
        return ImageDecoder.nCreate(this.mBuffer.array(), i + j, k - m, param1Boolean, this);
      } 
      ByteBuffer byteBuffer = this.mBuffer.slice();
      return ImageDecoder.nCreate(byteBuffer, byteBuffer.position(), byteBuffer.limit(), param1Boolean, this);
    }
  }
  
  private static class CallableSource extends Source {
    private final Callable<AssetFileDescriptor> mCallable;
    
    CallableSource(Callable<AssetFileDescriptor> param1Callable) {
      this.mCallable = param1Callable;
    }
    
    public ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException {
      try {
        AssetFileDescriptor assetFileDescriptor = this.mCallable.call();
        return ImageDecoder.createFromAssetFileDescriptor(assetFileDescriptor, param1Boolean, this);
      } catch (Exception exception) {
        if (exception instanceof IOException)
          throw (IOException)exception; 
        throw new IOException(exception);
      } 
    }
  }
  
  private static class ContentResolverSource extends Source {
    private final ContentResolver mResolver;
    
    private final Resources mResources;
    
    private final Uri mUri;
    
    ContentResolverSource(ContentResolver param1ContentResolver, Uri param1Uri, Resources param1Resources) {
      this.mResolver = param1ContentResolver;
      this.mUri = param1Uri;
      this.mResources = param1Resources;
    }
    
    public ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException {
      InputStream inputStream;
      AssetFileDescriptor assetFileDescriptor = null;
      try {
        if ("content".equals(this.mUri.getScheme())) {
          AssetFileDescriptor assetFileDescriptor1 = this.mResolver.openTypedAssetFileDescriptor(this.mUri, "image/*", null);
          assetFileDescriptor = assetFileDescriptor1;
        } else {
          AssetFileDescriptor assetFileDescriptor1 = this.mResolver.openAssetFileDescriptor(this.mUri, "r");
          assetFileDescriptor = assetFileDescriptor1;
        } 
      } catch (FileNotFoundException fileNotFoundException) {}
      if (assetFileDescriptor == null) {
        inputStream = this.mResolver.openInputStream(this.mUri);
        if (inputStream != null)
          return ImageDecoder.createFromStream(inputStream, true, param1Boolean, this); 
        throw new FileNotFoundException(this.mUri.toString());
      } 
      return ImageDecoder.createFromAssetFileDescriptor((AssetFileDescriptor)inputStream, param1Boolean, this);
    }
    
    Resources getResources() {
      return this.mResources;
    }
  }
  
  public static final class DecodeException extends IOException {
    public static final int SOURCE_EXCEPTION = 1;
    
    public static final int SOURCE_INCOMPLETE = 2;
    
    public static final int SOURCE_MALFORMED_DATA = 3;
    
    final int mError;
    
    final ImageDecoder.Source mSource;
    
    DecodeException(int param1Int, String param1String, Throwable param1Throwable, ImageDecoder.Source param1Source) {
      super(stringBuilder.toString(), param1Throwable);
      this.mError = param1Int;
      this.mSource = param1Source;
    }
    
    DecodeException(int param1Int, Throwable param1Throwable, ImageDecoder.Source param1Source) {
      super(errorMessage(param1Int, param1Throwable), param1Throwable);
      this.mError = param1Int;
      this.mSource = param1Source;
    }
    
    private static String errorMessage(int param1Int, Throwable param1Throwable) {
      if (param1Int != 1)
        return (param1Int != 2) ? ((param1Int != 3) ? "" : "Input contained an error.") : "Input was incomplete."; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Exception in input: ");
      stringBuilder.append(param1Throwable);
      return stringBuilder.toString();
    }
    
    public int getError() {
      return this.mError;
    }
    
    public ImageDecoder.Source getSource() {
      return this.mSource;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface Error {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Error {}
  
  private static class FileSource extends Source {
    private final File mFile;
    
    FileSource(File param1File) {
      this.mFile = param1File;
    }
    
    public ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException {
      return ImageDecoder.createFromFile(this.mFile, param1Boolean, this);
    }
  }
  
  public static class ImageInfo {
    private ImageDecoder mDecoder;
    
    private final Size mSize;
    
    private ImageInfo(ImageDecoder param1ImageDecoder) {
      this.mSize = new Size(param1ImageDecoder.mWidth, param1ImageDecoder.mHeight);
      this.mDecoder = param1ImageDecoder;
    }
    
    public ColorSpace getColorSpace() {
      return this.mDecoder.getColorSpace();
    }
    
    public String getMimeType() {
      return this.mDecoder.getMimeType();
    }
    
    public Size getSize() {
      return this.mSize;
    }
    
    public boolean isAnimated() {
      return this.mDecoder.mAnimated;
    }
  }
  
  @Deprecated
  public static class IncompleteException extends IOException {}
  
  private static class InputStreamSource extends Source {
    final int mInputDensity;
    
    InputStream mInputStream;
    
    final Resources mResources;
    
    InputStreamSource(Resources param1Resources, InputStream param1InputStream, int param1Int) {
      if (param1InputStream != null) {
        this.mResources = param1Resources;
        this.mInputStream = param1InputStream;
        this.mInputDensity = param1Int;
        return;
      } 
      throw new IllegalArgumentException("The InputStream cannot be null");
    }
    
    public ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mInputStream : Ljava/io/InputStream;
      //   6: ifnull -> 31
      //   9: aload_0
      //   10: getfield mInputStream : Ljava/io/InputStream;
      //   13: astore_2
      //   14: aload_0
      //   15: aconst_null
      //   16: putfield mInputStream : Ljava/io/InputStream;
      //   19: aload_2
      //   20: iconst_0
      //   21: iload_1
      //   22: aload_0
      //   23: invokestatic access$300 : (Ljava/io/InputStream;ZZLandroid/graphics/ImageDecoder$Source;)Landroid/graphics/ImageDecoder;
      //   26: astore_2
      //   27: aload_0
      //   28: monitorexit
      //   29: aload_2
      //   30: areturn
      //   31: new java/io/IOException
      //   34: astore_2
      //   35: aload_2
      //   36: ldc 'Cannot reuse InputStreamSource'
      //   38: invokespecial <init> : (Ljava/lang/String;)V
      //   41: aload_2
      //   42: athrow
      //   43: astore_2
      //   44: aload_0
      //   45: monitorexit
      //   46: aload_2
      //   47: athrow
      // Exception table:
      //   from	to	target	type
      //   2	29	43	finally
      //   31	43	43	finally
      //   44	46	43	finally
    }
    
    public int getDensity() {
      return this.mInputDensity;
    }
    
    public Resources getResources() {
      return this.mResources;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface MemoryPolicy {}
  
  public static interface OnHeaderDecodedListener {
    void onHeaderDecoded(ImageDecoder param1ImageDecoder, ImageDecoder.ImageInfo param1ImageInfo, ImageDecoder.Source param1Source);
  }
  
  public static interface OnPartialImageListener {
    boolean onPartialImage(ImageDecoder.DecodeException param1DecodeException);
  }
  
  private static class ResourceSource extends Source {
    private Object mLock = new Object();
    
    int mResDensity;
    
    final int mResId;
    
    final Resources mResources;
    
    ResourceSource(Resources param1Resources, int param1Int) {
      this.mResources = param1Resources;
      this.mResId = param1Int;
      this.mResDensity = 0;
    }
    
    public ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException {
      null = new TypedValue();
      InputStream inputStream = this.mResources.openRawResource(this.mResId, null);
      synchronized (this.mLock) {
        if (null.density == 0) {
          this.mResDensity = 160;
        } else if (null.density != 65535) {
          this.mResDensity = null.density;
        } 
        return ImageDecoder.createFromAsset((AssetManager.AssetInputStream)inputStream, param1Boolean, this);
      } 
    }
    
    public int getDensity() {
      synchronized (this.mLock) {
        return this.mResDensity;
      } 
    }
    
    public Resources getResources() {
      return this.mResources;
    }
  }
  
  public static abstract class Source {
    private Source() {}
    
    final int computeDstDensity() {
      Resources resources = getResources();
      return (resources == null) ? Bitmap.getDefaultDensity() : (resources.getDisplayMetrics()).densityDpi;
    }
    
    abstract ImageDecoder createImageDecoder(boolean param1Boolean) throws IOException;
    
    int getDensity() {
      return 0;
    }
    
    Resources getResources() {
      return null;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.graphics;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class ContentResolverSource extends ImageDecoder.Source {
  private final ContentResolver mResolver;
  
  private final Resources mResources;
  
  private final Uri mUri;
  
  ContentResolverSource(ContentResolver paramContentResolver, Uri paramUri, Resources paramResources) {
    this.mResolver = paramContentResolver;
    this.mUri = paramUri;
    this.mResources = paramResources;
  }
  
  public ImageDecoder createImageDecoder(boolean paramBoolean) throws IOException {
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
        return ImageDecoder.access$300(inputStream, true, paramBoolean, this); 
      throw new FileNotFoundException(this.mUri.toString());
    } 
    return ImageDecoder.access$400((AssetFileDescriptor)inputStream, paramBoolean, this);
  }
  
  Resources getResources() {
    return this.mResources;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$ContentResolverSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
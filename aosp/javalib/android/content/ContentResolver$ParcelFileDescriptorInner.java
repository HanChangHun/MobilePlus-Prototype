package android.content;

import android.os.ParcelFileDescriptor;
import java.util.concurrent.atomic.AtomicBoolean;

final class ParcelFileDescriptorInner extends ParcelFileDescriptor {
  private final IContentProvider mContentProvider;
  
  private final AtomicBoolean mProviderReleased = new AtomicBoolean();
  
  ParcelFileDescriptorInner(ParcelFileDescriptor paramParcelFileDescriptor, IContentProvider paramIContentProvider) {
    super(paramParcelFileDescriptor);
    this.mContentProvider = paramIContentProvider;
  }
  
  public void releaseResources() {
    if (this.mProviderReleased.compareAndSet(false, true))
      ContentResolver.this.releaseProvider(this.mContentProvider); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentResolver$ParcelFileDescriptorInner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
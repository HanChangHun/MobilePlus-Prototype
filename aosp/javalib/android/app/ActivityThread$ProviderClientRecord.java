package android.app;

import android.content.ContentProvider;
import android.content.IContentProvider;

final class ProviderClientRecord {
  final ContentProviderHolder mHolder;
  
  final ContentProvider mLocalProvider;
  
  final String[] mNames;
  
  final IContentProvider mProvider;
  
  ProviderClientRecord(String[] paramArrayOfString, IContentProvider paramIContentProvider, ContentProvider paramContentProvider, ContentProviderHolder paramContentProviderHolder) {
    this.mNames = paramArrayOfString;
    this.mProvider = paramIContentProvider;
    this.mLocalProvider = paramContentProvider;
    this.mHolder = paramContentProviderHolder;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$ProviderClientRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
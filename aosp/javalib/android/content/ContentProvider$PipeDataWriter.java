package android.content;

import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;

public interface PipeDataWriter<T> {
  void writeDataToPipe(ParcelFileDescriptor paramParcelFileDescriptor, Uri paramUri, String paramString, Bundle paramBundle, T paramT);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProvider$PipeDataWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
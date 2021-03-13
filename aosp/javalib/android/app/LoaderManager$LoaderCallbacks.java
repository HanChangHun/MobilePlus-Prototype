package android.app;

import android.content.Loader;
import android.os.Bundle;

@Deprecated
public interface LoaderCallbacks<D> {
  Loader<D> onCreateLoader(int paramInt, Bundle paramBundle);
  
  void onLoadFinished(Loader<D> paramLoader, D paramD);
  
  void onLoaderReset(Loader<D> paramLoader);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoaderManager$LoaderCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
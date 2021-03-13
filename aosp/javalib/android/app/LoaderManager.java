package android.app;

import android.content.Loader;
import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@Deprecated
public abstract class LoaderManager {
  public static void enableDebugLogging(boolean paramBoolean) {
    LoaderManagerImpl.DEBUG = paramBoolean;
  }
  
  public abstract void destroyLoader(int paramInt);
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  public FragmentHostCallback getFragmentHostCallback() {
    return null;
  }
  
  public abstract <D> Loader<D> getLoader(int paramInt);
  
  public abstract <D> Loader<D> initLoader(int paramInt, Bundle paramBundle, LoaderCallbacks<D> paramLoaderCallbacks);
  
  public abstract <D> Loader<D> restartLoader(int paramInt, Bundle paramBundle, LoaderCallbacks<D> paramLoaderCallbacks);
  
  @Deprecated
  public static interface LoaderCallbacks<D> {
    Loader<D> onCreateLoader(int param1Int, Bundle param1Bundle);
    
    void onLoadFinished(Loader<D> param1Loader, D param1D);
    
    void onLoaderReset(Loader<D> param1Loader);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoaderManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
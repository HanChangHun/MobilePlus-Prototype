package android.content.pm.split;

import android.content.pm.PackageParser;
import android.content.res.AssetManager;

public interface SplitAssetLoader extends AutoCloseable {
  AssetManager getBaseAssetManager() throws PackageParser.PackageParserException;
  
  AssetManager getSplitAssetManager(int paramInt) throws PackageParser.PackageParserException;
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/split/SplitAssetLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.database;

import android.net.Uri;

public class Config {
  public final String auxiliaryColumn;
  
  public final Uri baseUri;
  
  public final String[] translateColumns;
  
  public Config(Uri paramUri, String paramString, String... paramVarArgs) {
    this.baseUri = paramUri;
    this.auxiliaryColumn = paramString;
    this.translateColumns = paramVarArgs;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/TranslatingCursor$Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
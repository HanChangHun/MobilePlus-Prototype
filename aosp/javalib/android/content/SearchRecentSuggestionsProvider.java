package android.content;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class SearchRecentSuggestionsProvider extends ContentProvider {
  public static final int DATABASE_MODE_2LINES = 2;
  
  public static final int DATABASE_MODE_QUERIES = 1;
  
  private static final int DATABASE_VERSION = 512;
  
  private static final String NULL_COLUMN = "query";
  
  private static final String ORDER_BY = "date DESC";
  
  private static final String TAG = "SuggestionsProvider";
  
  private static final int URI_MATCH_SUGGEST = 1;
  
  private static final String sDatabaseName = "suggestions.db";
  
  private static final String sSuggestions = "suggestions";
  
  private String mAuthority;
  
  private int mMode;
  
  private SQLiteOpenHelper mOpenHelper;
  
  private String mSuggestSuggestionClause;
  
  private String[] mSuggestionProjection;
  
  private Uri mSuggestionsUri;
  
  private boolean mTwoLineDisplay;
  
  private UriMatcher mUriMatcher;
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString) {
    SQLiteDatabase sQLiteDatabase = this.mOpenHelper.getWritableDatabase();
    if (paramUri.getPathSegments().size() == 1) {
      if (((String)paramUri.getPathSegments().get(0)).equals("suggestions")) {
        int i = sQLiteDatabase.delete("suggestions", paramString, paramArrayOfString);
        getContext().getContentResolver().notifyChange(paramUri, null);
        return i;
      } 
      throw new IllegalArgumentException("Unknown Uri");
    } 
    throw new IllegalArgumentException("Unknown Uri");
  }
  
  public String getType(Uri paramUri) {
    if (this.mUriMatcher.match(paramUri) == 1)
      return "vnd.android.cursor.dir/vnd.android.search.suggest"; 
    int i = paramUri.getPathSegments().size();
    if (i >= 1 && ((String)paramUri.getPathSegments().get(0)).equals("suggestions")) {
      if (i == 1)
        return "vnd.android.cursor.dir/suggestion"; 
      if (i == 2)
        return "vnd.android.cursor.item/suggestion"; 
    } 
    throw new IllegalArgumentException("Unknown Uri");
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues) {
    SQLiteDatabase sQLiteDatabase = this.mOpenHelper.getWritableDatabase();
    int i = paramUri.getPathSegments().size();
    if (i >= 1) {
      long l1 = -1L;
      String str = paramUri.getPathSegments().get(0);
      Uri uri = null;
      long l2 = l1;
      paramUri = uri;
      if (str.equals("suggestions")) {
        l2 = l1;
        paramUri = uri;
        if (i == 1) {
          l1 = sQLiteDatabase.insert("suggestions", "query", paramContentValues);
          l2 = l1;
          paramUri = uri;
          if (l1 > 0L) {
            paramUri = Uri.withAppendedPath(this.mSuggestionsUri, String.valueOf(l1));
            l2 = l1;
          } 
        } 
      } 
      if (l2 >= 0L) {
        getContext().getContentResolver().notifyChange(paramUri, null);
        return paramUri;
      } 
      throw new IllegalArgumentException("Unknown Uri");
    } 
    throw new IllegalArgumentException("Unknown Uri");
  }
  
  public boolean onCreate() {
    if (this.mAuthority != null) {
      int i = this.mMode;
      if (i != 0) {
        this.mOpenHelper = new DatabaseHelper(getContext(), i + 512);
        return true;
      } 
    } 
    throw new IllegalArgumentException("Provider not configured");
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) {
    Cursor cursor;
    String[] arrayOfString;
    String str;
    SQLiteDatabase sQLiteDatabase = this.mOpenHelper.getReadableDatabase();
    if (this.mUriMatcher.match(paramUri) == 1) {
      String str1;
      if (TextUtils.isEmpty(paramArrayOfString2[0])) {
        paramArrayOfString1 = null;
        paramString1 = null;
      } else {
        String[] arrayOfString1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%");
        stringBuilder.append(paramArrayOfString2[0]);
        stringBuilder.append("%");
        paramString1 = stringBuilder.toString();
        if (this.mTwoLineDisplay) {
          arrayOfString1 = new String[2];
          arrayOfString1[0] = paramString1;
          arrayOfString1[1] = paramString1;
        } else {
          arrayOfString1 = new String[1];
          arrayOfString1[0] = paramString1;
        } 
        str = this.mSuggestSuggestionClause;
        arrayOfString = arrayOfString1;
        str1 = str;
      } 
      cursor = sQLiteDatabase.query("suggestions", this.mSuggestionProjection, str1, arrayOfString, null, null, "date DESC", null);
      cursor.setNotificationUri(getContext().getContentResolver(), paramUri);
      return cursor;
    } 
    int i = paramUri.getPathSegments().size();
    if (i == 1 || i == 2) {
      String str1 = paramUri.getPathSegments().get(0);
      if (str1.equals("suggestions")) {
        if (cursor != null && cursor.length > 0) {
          String[] arrayOfString2 = new String[cursor.length + 1];
          System.arraycopy(cursor, 0, arrayOfString2, 0, cursor.length);
          arrayOfString2[cursor.length] = "_id AS _id";
          String[] arrayOfString1 = arrayOfString2;
        } else {
          cursor = null;
        } 
        StringBuilder stringBuilder = new StringBuilder(256);
        if (i == 2) {
          stringBuilder.append("(_id = ");
          stringBuilder.append(paramUri.getPathSegments().get(1));
          stringBuilder.append(")");
        } 
        if (arrayOfString != null && arrayOfString.length() > 0) {
          if (stringBuilder.length() > 0)
            stringBuilder.append(" AND "); 
          stringBuilder.append('(');
          stringBuilder.append((String)arrayOfString);
          stringBuilder.append(')');
        } 
        cursor = sQLiteDatabase.query(str1, (String[])cursor, stringBuilder.toString(), (String[])str, null, null, paramString2, null);
        cursor.setNotificationUri(getContext().getContentResolver(), paramUri);
        return cursor;
      } 
      throw new IllegalArgumentException("Unknown Uri");
    } 
    throw new IllegalArgumentException("Unknown Uri");
  }
  
  protected void setupSuggestions(String paramString, int paramInt) {
    if (!TextUtils.isEmpty(paramString) && (paramInt & 0x1) != 0) {
      boolean bool;
      if ((paramInt & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mTwoLineDisplay = bool;
      this.mAuthority = new String(paramString);
      this.mMode = paramInt;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("content://");
      stringBuilder.append(this.mAuthority);
      stringBuilder.append("/suggestions");
      this.mSuggestionsUri = Uri.parse(stringBuilder.toString());
      UriMatcher uriMatcher = new UriMatcher(-1);
      this.mUriMatcher = uriMatcher;
      uriMatcher.addURI(this.mAuthority, "search_suggest_query", 1);
      if (this.mTwoLineDisplay) {
        this.mSuggestSuggestionClause = "display1 LIKE ? OR display2 LIKE ?";
        this.mSuggestionProjection = new String[] { "0 AS suggest_format", "'android.resource://system/17301578' AS suggest_icon_1", "display1 AS suggest_text_1", "display2 AS suggest_text_2", "query AS suggest_intent_query", "_id" };
      } else {
        this.mSuggestSuggestionClause = "display1 LIKE ?";
        this.mSuggestionProjection = new String[] { "0 AS suggest_format", "'android.resource://system/17301578' AS suggest_icon_1", "display1 AS suggest_text_1", "query AS suggest_intent_query", "_id" };
      } 
      return;
    } 
    throw new IllegalArgumentException();
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  private static class DatabaseHelper extends SQLiteOpenHelper {
    private int mNewVersion;
    
    public DatabaseHelper(Context param1Context, int param1Int) {
      super(param1Context, "suggestions.db", null, param1Int);
      this.mNewVersion = param1Int;
    }
    
    public void onCreate(SQLiteDatabase param1SQLiteDatabase) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("CREATE TABLE suggestions (_id INTEGER PRIMARY KEY,display1 TEXT UNIQUE ON CONFLICT REPLACE");
      if ((this.mNewVersion & 0x2) != 0)
        stringBuilder.append(",display2 TEXT"); 
      stringBuilder.append(",query TEXT,date LONG);");
      param1SQLiteDatabase.execSQL(stringBuilder.toString());
    }
    
    public void onUpgrade(SQLiteDatabase param1SQLiteDatabase, int param1Int1, int param1Int2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Upgrading database from version ");
      stringBuilder.append(param1Int1);
      stringBuilder.append(" to ");
      stringBuilder.append(param1Int2);
      stringBuilder.append(", which will destroy all old data");
      Log.w("SuggestionsProvider", stringBuilder.toString());
      param1SQLiteDatabase.execSQL("DROP TABLE IF EXISTS suggestions");
      onCreate(param1SQLiteDatabase);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SearchRecentSuggestionsProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
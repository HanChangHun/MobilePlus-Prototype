package android.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import java.io.Closeable;
import java.util.Arrays;
import java.util.List;

public interface Cursor extends Closeable {
  public static final int FIELD_TYPE_BLOB = 4;
  
  public static final int FIELD_TYPE_FLOAT = 2;
  
  public static final int FIELD_TYPE_INTEGER = 1;
  
  public static final int FIELD_TYPE_NULL = 0;
  
  public static final int FIELD_TYPE_STRING = 3;
  
  void close();
  
  void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer);
  
  @Deprecated
  void deactivate();
  
  byte[] getBlob(int paramInt);
  
  int getColumnCount();
  
  int getColumnIndex(String paramString);
  
  int getColumnIndexOrThrow(String paramString) throws IllegalArgumentException;
  
  String getColumnName(int paramInt);
  
  String[] getColumnNames();
  
  int getCount();
  
  double getDouble(int paramInt);
  
  Bundle getExtras();
  
  float getFloat(int paramInt);
  
  int getInt(int paramInt);
  
  long getLong(int paramInt);
  
  Uri getNotificationUri();
  
  default List<Uri> getNotificationUris() {
    List<Uri> list;
    Uri uri = getNotificationUri();
    if (uri == null) {
      uri = null;
    } else {
      list = Arrays.asList(new Uri[] { uri });
    } 
    return list;
  }
  
  int getPosition();
  
  short getShort(int paramInt);
  
  String getString(int paramInt);
  
  int getType(int paramInt);
  
  boolean getWantsAllOnMoveCalls();
  
  boolean isAfterLast();
  
  boolean isBeforeFirst();
  
  boolean isClosed();
  
  boolean isFirst();
  
  boolean isLast();
  
  boolean isNull(int paramInt);
  
  boolean move(int paramInt);
  
  boolean moveToFirst();
  
  boolean moveToLast();
  
  boolean moveToNext();
  
  boolean moveToPosition(int paramInt);
  
  boolean moveToPrevious();
  
  void registerContentObserver(ContentObserver paramContentObserver);
  
  void registerDataSetObserver(DataSetObserver paramDataSetObserver);
  
  @Deprecated
  boolean requery();
  
  Bundle respond(Bundle paramBundle);
  
  void setExtras(Bundle paramBundle);
  
  void setNotificationUri(ContentResolver paramContentResolver, Uri paramUri);
  
  default void setNotificationUris(ContentResolver paramContentResolver, List<Uri> paramList) {
    setNotificationUri(paramContentResolver, paramList.get(0));
  }
  
  void unregisterContentObserver(ContentObserver paramContentObserver);
  
  void unregisterDataSetObserver(DataSetObserver paramDataSetObserver);
}


/* Location:              /home/chun/Desktop/temp/!/android/database/Cursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.ArraySet;
import com.android.internal.util.ArrayUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class TranslatingCursor extends CrossProcessCursorWrapper {
  private final int mAuxiliaryColumnIndex;
  
  private final Config mConfig;
  
  private final boolean mDropLast;
  
  private final ArraySet<Integer> mTranslateColumnIndices;
  
  private final Translator mTranslator;
  
  public TranslatingCursor(Cursor paramCursor, Config paramConfig, Translator paramTranslator, boolean paramBoolean) {
    super(paramCursor);
    Objects.requireNonNull(paramConfig);
    this.mConfig = paramConfig;
    Objects.requireNonNull(paramTranslator);
    this.mTranslator = paramTranslator;
    this.mDropLast = paramBoolean;
    this.mAuxiliaryColumnIndex = paramCursor.getColumnIndexOrThrow(paramConfig.auxiliaryColumn);
    this.mTranslateColumnIndices = new ArraySet();
    for (byte b = 0; b < paramCursor.getColumnCount(); b++) {
      String str = paramCursor.getColumnName(b);
      if (ArrayUtils.contains((Object[])paramConfig.translateColumns, str))
        this.mTranslateColumnIndices.add(Integer.valueOf(b)); 
    } 
  }
  
  public static Cursor query(Config paramConfig, Translator paramTranslator, SQLiteQueryBuilder paramSQLiteQueryBuilder, SQLiteDatabase paramSQLiteDatabase, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, String paramString3, String paramString4, String paramString5, CancellationSignal paramCancellationSignal) {
    boolean bool2;
    boolean bool3;
    String[] arrayOfString = paramArrayOfString1;
    boolean bool = ArrayUtils.isEmpty((Object[])paramArrayOfString1);
    boolean bool1 = false;
    if (bool || ArrayUtils.contains((Object[])arrayOfString, paramConfig.auxiliaryColumn)) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (ArrayUtils.isEmpty((Object[])paramArrayOfString1) || ArrayUtils.containsAny((Object[])arrayOfString, (Object[])paramConfig.translateColumns)) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    if (!bool3)
      return paramSQLiteQueryBuilder.query(paramSQLiteDatabase, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, paramString3, paramString4, paramString5, paramCancellationSignal); 
    paramArrayOfString1 = arrayOfString;
    if (!bool2)
      paramArrayOfString1 = (String[])ArrayUtils.appendElement(String.class, (Object[])arrayOfString, paramConfig.auxiliaryColumn); 
    Cursor cursor = paramSQLiteQueryBuilder.query(paramSQLiteDatabase, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, paramString3, paramString4);
    if (!bool2)
      bool1 = true; 
    return new TranslatingCursor(cursor, paramConfig, paramTranslator, bool1);
  }
  
  public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer) {
    if (!ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt))) {
      super.copyStringToBuffer(paramInt, paramCharArrayBuffer);
      return;
    } 
    throw new IllegalArgumentException();
  }
  
  public void fillWindow(int paramInt, CursorWindow paramCursorWindow) {
    DatabaseUtils.cursorFillWindow(this, paramInt, paramCursorWindow);
  }
  
  public byte[] getBlob(int paramInt) {
    if (!ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt)))
      return super.getBlob(paramInt); 
    throw new IllegalArgumentException();
  }
  
  public int getColumnCount() {
    return this.mDropLast ? (super.getColumnCount() - 1) : super.getColumnCount();
  }
  
  public String[] getColumnNames() {
    return this.mDropLast ? Arrays.<String>copyOfRange(super.getColumnNames(), 0, super.getColumnCount() - 1) : super.getColumnNames();
  }
  
  public double getDouble(int paramInt) {
    if (!ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt)))
      return super.getDouble(paramInt); 
    throw new IllegalArgumentException();
  }
  
  public float getFloat(int paramInt) {
    if (!ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt)))
      return super.getFloat(paramInt); 
    throw new IllegalArgumentException();
  }
  
  public int getInt(int paramInt) {
    if (!ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt)))
      return super.getInt(paramInt); 
    throw new IllegalArgumentException();
  }
  
  public long getLong(int paramInt) {
    if (!ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt)))
      return super.getLong(paramInt); 
    throw new IllegalArgumentException();
  }
  
  public short getShort(int paramInt) {
    if (!ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt)))
      return super.getShort(paramInt); 
    throw new IllegalArgumentException();
  }
  
  public String getString(int paramInt) {
    return ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt)) ? this.mTranslator.translate(super.getString(paramInt), this.mAuxiliaryColumnIndex, getColumnName(paramInt), this) : super.getString(paramInt);
  }
  
  public int getType(int paramInt) {
    return ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt)) ? 3 : super.getType(paramInt);
  }
  
  public CursorWindow getWindow() {
    return null;
  }
  
  public Cursor getWrappedCursor() {
    throw new UnsupportedOperationException("Returning underlying cursor risks leaking data");
  }
  
  public boolean isNull(int paramInt) {
    if (ArrayUtils.contains((Collection)this.mTranslateColumnIndices, Integer.valueOf(paramInt))) {
      boolean bool;
      if (getString(paramInt) == null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    return super.isNull(paramInt);
  }
  
  public static class Config {
    public final String auxiliaryColumn;
    
    public final Uri baseUri;
    
    public final String[] translateColumns;
    
    public Config(Uri param1Uri, String param1String, String... param1VarArgs) {
      this.baseUri = param1Uri;
      this.auxiliaryColumn = param1String;
      this.translateColumns = param1VarArgs;
    }
  }
  
  public static interface Translator {
    String translate(String param1String1, int param1Int, String param1String2, Cursor param1Cursor);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/TranslatingCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
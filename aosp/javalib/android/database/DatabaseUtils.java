package android.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteStatement;
import android.os.OperationCanceledException;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.Collator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DatabaseUtils {
  private static final boolean DEBUG = false;
  
  private static final char[] DIGITS = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'a', 'b', 'c', 'd', 'e', 'f' };
  
  public static final int STATEMENT_ABORT = 6;
  
  public static final int STATEMENT_ATTACH = 3;
  
  public static final int STATEMENT_BEGIN = 4;
  
  public static final int STATEMENT_COMMIT = 5;
  
  public static final int STATEMENT_DDL = 8;
  
  public static final int STATEMENT_OTHER = 99;
  
  public static final int STATEMENT_PRAGMA = 7;
  
  public static final int STATEMENT_SELECT = 1;
  
  public static final int STATEMENT_UNPREPARED = 9;
  
  public static final int STATEMENT_UPDATE = 2;
  
  private static final String TAG = "DatabaseUtils";
  
  private static Collator mColl = null;
  
  public static void appendEscapedSQLString(StringBuilder paramStringBuilder, String paramString) {
    paramStringBuilder.append('\'');
    if (paramString.indexOf('\'') != -1) {
      int i = paramString.length();
      for (byte b = 0; b < i; b++) {
        char c = paramString.charAt(b);
        if (c == '\'')
          paramStringBuilder.append('\''); 
        paramStringBuilder.append(c);
      } 
    } else {
      paramStringBuilder.append(paramString);
    } 
    paramStringBuilder.append('\'');
  }
  
  public static String[] appendSelectionArgs(String[] paramArrayOfString1, String[] paramArrayOfString2) {
    if (paramArrayOfString1 == null || paramArrayOfString1.length == 0)
      return paramArrayOfString2; 
    String[] arrayOfString = new String[paramArrayOfString1.length + paramArrayOfString2.length];
    System.arraycopy(paramArrayOfString1, 0, arrayOfString, 0, paramArrayOfString1.length);
    System.arraycopy(paramArrayOfString2, 0, arrayOfString, paramArrayOfString1.length, paramArrayOfString2.length);
    return arrayOfString;
  }
  
  public static final void appendValueToSql(StringBuilder paramStringBuilder, Object paramObject) {
    if (paramObject == null) {
      paramStringBuilder.append("NULL");
    } else if (paramObject instanceof Boolean) {
      if (((Boolean)paramObject).booleanValue()) {
        paramStringBuilder.append('1');
      } else {
        paramStringBuilder.append('0');
      } 
    } else {
      appendEscapedSQLString(paramStringBuilder, paramObject.toString());
    } 
  }
  
  private static void bindArgs(SQLiteStatement paramSQLiteStatement, Object[] paramArrayOfObject) {
    if (paramArrayOfObject == null)
      return; 
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      Object object = paramArrayOfObject[b];
      int i = getTypeOfObject(object);
      if (i != 0) {
        if (i != 1) {
          if (i != 2) {
            if (i != 4) {
              if (object instanceof Boolean) {
                long l;
                if (((Boolean)object).booleanValue()) {
                  l = 1L;
                } else {
                  l = 0L;
                } 
                paramSQLiteStatement.bindLong(b + 1, l);
              } else {
                paramSQLiteStatement.bindString(b + 1, object.toString());
              } 
            } else {
              paramSQLiteStatement.bindBlob(b + 1, (byte[])object);
            } 
          } else {
            paramSQLiteStatement.bindDouble(b + 1, ((Number)object).doubleValue());
          } 
        } else {
          paramSQLiteStatement.bindLong(b + 1, ((Number)object).longValue());
        } 
      } else {
        paramSQLiteStatement.bindNull(b + 1);
      } 
    } 
  }
  
  public static void bindObjectToProgram(SQLiteProgram paramSQLiteProgram, int paramInt, Object paramObject) {
    if (paramObject == null) {
      paramSQLiteProgram.bindNull(paramInt);
    } else {
      if (paramObject instanceof Double || paramObject instanceof Float) {
        paramSQLiteProgram.bindDouble(paramInt, ((Number)paramObject).doubleValue());
        return;
      } 
      if (paramObject instanceof Number) {
        paramSQLiteProgram.bindLong(paramInt, ((Number)paramObject).longValue());
      } else if (paramObject instanceof Boolean) {
        if (((Boolean)paramObject).booleanValue()) {
          paramSQLiteProgram.bindLong(paramInt, 1L);
        } else {
          paramSQLiteProgram.bindLong(paramInt, 0L);
        } 
      } else if (paramObject instanceof byte[]) {
        paramSQLiteProgram.bindBlob(paramInt, (byte[])paramObject);
      } else {
        paramSQLiteProgram.bindString(paramInt, paramObject.toString());
      } 
    } 
  }
  
  public static String bindSelection(String paramString, Object... paramVarArgs) {
    if (paramString == null)
      return null; 
    if (ArrayUtils.isEmpty(paramVarArgs))
      return paramString; 
    if (paramString.indexOf('?') == -1)
      return paramString; 
    char c = ' ';
    int i = 0;
    int j = paramString.length();
    StringBuilder stringBuilder = new StringBuilder(j);
    int k;
    for (k = 0; k < j; k = m) {
      int m = k + 1;
      char c1 = paramString.charAt(k);
      if (c1 == '?') {
        char c3;
        char c2 = ' ';
        k = m;
        while (true) {
          c3 = c2;
          if (k < j) {
            c3 = paramString.charAt(k);
            if (c3 < '0' || c3 > '9')
              break; 
            k++;
            continue;
          } 
          break;
        } 
        if (m != k)
          i = Integer.parseInt(paramString.substring(m, k)) - 1; 
        Object object = paramVarArgs[i];
        if (c != ' ' && c != '=')
          stringBuilder.append(' '); 
        m = getTypeOfObject(object);
        if (m != 0) {
          if (m != 1) {
            if (m != 2) {
              if (m != 4) {
                if (object instanceof Boolean) {
                  stringBuilder.append(((Boolean)object).booleanValue());
                } else {
                  stringBuilder.append('\'');
                  stringBuilder.append(object.toString());
                  stringBuilder.append('\'');
                } 
              } else {
                throw new IllegalArgumentException("Blobs not supported");
              } 
            } else {
              stringBuilder.append(((Number)object).doubleValue());
            } 
          } else {
            stringBuilder.append(((Number)object).longValue());
          } 
        } else {
          stringBuilder.append("NULL");
        } 
        if (c3 != ' ')
          stringBuilder.append(' '); 
        i++;
        continue;
      } 
      stringBuilder.append(c1);
      c = c1;
    } 
    return stringBuilder.toString();
  }
  
  public static ParcelFileDescriptor blobFileDescriptorForQuery(SQLiteDatabase paramSQLiteDatabase, String paramString, String[] paramArrayOfString) {
    SQLiteStatement sQLiteStatement = paramSQLiteDatabase.compileStatement(paramString);
    try {
      return blobFileDescriptorForQuery(sQLiteStatement, paramArrayOfString);
    } finally {
      sQLiteStatement.close();
    } 
  }
  
  public static ParcelFileDescriptor blobFileDescriptorForQuery(SQLiteStatement paramSQLiteStatement, String[] paramArrayOfString) {
    paramSQLiteStatement.bindAllArgsAsStrings(paramArrayOfString);
    return paramSQLiteStatement.simpleQueryForBlobFileDescriptor();
  }
  
  public static String concatenateWhere(String paramString1, String paramString2) {
    if (TextUtils.isEmpty(paramString1))
      return paramString2; 
    if (TextUtils.isEmpty(paramString2))
      return paramString1; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("(");
    stringBuilder.append(paramString1);
    stringBuilder.append(") AND (");
    stringBuilder.append(paramString2);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public static void createDbFromSqlStatements(Context paramContext, String paramString1, int paramInt, String paramString2) {
    byte b = 0;
    SQLiteDatabase sQLiteDatabase = paramContext.openOrCreateDatabase(paramString1, 0, null);
    String[] arrayOfString = TextUtils.split(paramString2, ";\n");
    int i = arrayOfString.length;
    while (b < i) {
      paramString2 = arrayOfString[b];
      if (!TextUtils.isEmpty(paramString2))
        sQLiteDatabase.execSQL(paramString2); 
      b++;
    } 
    sQLiteDatabase.setVersion(paramInt);
    sQLiteDatabase.close();
  }
  
  public static void cursorDoubleToContentValues(Cursor paramCursor, String paramString1, ContentValues paramContentValues, String paramString2) {
    int i = paramCursor.getColumnIndex(paramString1);
    if (!paramCursor.isNull(i)) {
      paramContentValues.put(paramString2, Double.valueOf(paramCursor.getDouble(i)));
    } else {
      paramContentValues.put(paramString2, (Double)null);
    } 
  }
  
  public static void cursorDoubleToContentValuesIfPresent(Cursor paramCursor, ContentValues paramContentValues, String paramString) {
    int i = paramCursor.getColumnIndex(paramString);
    if (i != -1 && !paramCursor.isNull(i))
      paramContentValues.put(paramString, Double.valueOf(paramCursor.getDouble(i))); 
  }
  
  public static void cursorDoubleToCursorValues(Cursor paramCursor, String paramString, ContentValues paramContentValues) {
    cursorDoubleToContentValues(paramCursor, paramString, paramContentValues, paramString);
  }
  
  public static void cursorFillWindow(Cursor paramCursor, int paramInt, CursorWindow paramCursorWindow) {
    if (paramInt < 0 || paramInt >= paramCursor.getCount())
      return; 
    int i = paramCursor.getPosition();
    int j = paramCursor.getColumnCount();
    paramCursorWindow.clear();
    paramCursorWindow.setStartPosition(paramInt);
    paramCursorWindow.setNumColumns(j);
    if (paramCursor.moveToPosition(paramInt))
      label44: while (paramCursorWindow.allocRow()) {
        for (byte b = 0; b < j; b++) {
          boolean bool;
          int k = paramCursor.getType(b);
          if (k != 0) {
            if (k != 1) {
              if (k != 2) {
                if (k != 4) {
                  String str = paramCursor.getString(b);
                  if (str != null) {
                    bool = paramCursorWindow.putString(str, paramInt, b);
                  } else {
                    bool = paramCursorWindow.putNull(paramInt, b);
                  } 
                } else {
                  byte[] arrayOfByte = paramCursor.getBlob(b);
                  if (arrayOfByte != null) {
                    bool = paramCursorWindow.putBlob(arrayOfByte, paramInt, b);
                  } else {
                    bool = paramCursorWindow.putNull(paramInt, b);
                  } 
                } 
              } else {
                bool = paramCursorWindow.putDouble(paramCursor.getDouble(b), paramInt, b);
              } 
            } else {
              bool = paramCursorWindow.putLong(paramCursor.getLong(b), paramInt, b);
            } 
          } else {
            bool = paramCursorWindow.putNull(paramInt, b);
          } 
          if (!bool) {
            paramCursorWindow.freeLastRow();
            break label44;
          } 
        } 
        paramInt++;
        if (!paramCursor.moveToNext())
          break; 
      }  
    paramCursor.moveToPosition(i);
  }
  
  public static void cursorFloatToContentValuesIfPresent(Cursor paramCursor, ContentValues paramContentValues, String paramString) {
    int i = paramCursor.getColumnIndex(paramString);
    if (i != -1 && !paramCursor.isNull(i))
      paramContentValues.put(paramString, Float.valueOf(paramCursor.getFloat(i))); 
  }
  
  public static void cursorIntToContentValues(Cursor paramCursor, String paramString, ContentValues paramContentValues) {
    cursorIntToContentValues(paramCursor, paramString, paramContentValues, paramString);
  }
  
  public static void cursorIntToContentValues(Cursor paramCursor, String paramString1, ContentValues paramContentValues, String paramString2) {
    int i = paramCursor.getColumnIndex(paramString1);
    if (!paramCursor.isNull(i)) {
      paramContentValues.put(paramString2, Integer.valueOf(paramCursor.getInt(i)));
    } else {
      paramContentValues.put(paramString2, (Integer)null);
    } 
  }
  
  public static void cursorIntToContentValuesIfPresent(Cursor paramCursor, ContentValues paramContentValues, String paramString) {
    int i = paramCursor.getColumnIndex(paramString);
    if (i != -1 && !paramCursor.isNull(i))
      paramContentValues.put(paramString, Integer.valueOf(paramCursor.getInt(i))); 
  }
  
  public static void cursorLongToContentValues(Cursor paramCursor, String paramString, ContentValues paramContentValues) {
    cursorLongToContentValues(paramCursor, paramString, paramContentValues, paramString);
  }
  
  public static void cursorLongToContentValues(Cursor paramCursor, String paramString1, ContentValues paramContentValues, String paramString2) {
    int i = paramCursor.getColumnIndex(paramString1);
    if (!paramCursor.isNull(i)) {
      paramContentValues.put(paramString2, Long.valueOf(paramCursor.getLong(i)));
    } else {
      paramContentValues.put(paramString2, (Long)null);
    } 
  }
  
  public static void cursorLongToContentValuesIfPresent(Cursor paramCursor, ContentValues paramContentValues, String paramString) {
    int i = paramCursor.getColumnIndex(paramString);
    if (i != -1 && !paramCursor.isNull(i))
      paramContentValues.put(paramString, Long.valueOf(paramCursor.getLong(i))); 
  }
  
  public static int cursorPickFillWindowStartPosition(int paramInt1, int paramInt2) {
    return Math.max(paramInt1 - paramInt2 / 3, 0);
  }
  
  public static void cursorRowToContentValues(Cursor paramCursor, ContentValues paramContentValues) {
    String[] arrayOfString = paramCursor.getColumnNames();
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (paramCursor.getType(b) == 4) {
        paramContentValues.put(arrayOfString[b], paramCursor.getBlob(b));
      } else {
        paramContentValues.put(arrayOfString[b], paramCursor.getString(b));
      } 
    } 
  }
  
  public static void cursorShortToContentValuesIfPresent(Cursor paramCursor, ContentValues paramContentValues, String paramString) {
    int i = paramCursor.getColumnIndex(paramString);
    if (i != -1 && !paramCursor.isNull(i))
      paramContentValues.put(paramString, Short.valueOf(paramCursor.getShort(i))); 
  }
  
  public static void cursorStringToContentValues(Cursor paramCursor, String paramString, ContentValues paramContentValues) {
    cursorStringToContentValues(paramCursor, paramString, paramContentValues, paramString);
  }
  
  public static void cursorStringToContentValues(Cursor paramCursor, String paramString1, ContentValues paramContentValues, String paramString2) {
    paramContentValues.put(paramString2, paramCursor.getString(paramCursor.getColumnIndexOrThrow(paramString1)));
  }
  
  public static void cursorStringToContentValuesIfPresent(Cursor paramCursor, ContentValues paramContentValues, String paramString) {
    int i = paramCursor.getColumnIndex(paramString);
    if (i != -1 && !paramCursor.isNull(i))
      paramContentValues.put(paramString, paramCursor.getString(i)); 
  }
  
  public static void cursorStringToInsertHelper(Cursor paramCursor, String paramString, InsertHelper paramInsertHelper, int paramInt) {
    paramInsertHelper.bind(paramInt, paramCursor.getString(paramCursor.getColumnIndexOrThrow(paramString)));
  }
  
  public static Object[] deepCopyOf(Object[] paramArrayOfObject) {
    if (paramArrayOfObject == null)
      return null; 
    Object[] arrayOfObject = new Object[paramArrayOfObject.length];
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      Object object = paramArrayOfObject[b];
      if (object == null || object instanceof Number || object instanceof String) {
        arrayOfObject[b] = object;
      } else if (object instanceof byte[]) {
        object = object;
        arrayOfObject[b] = Arrays.copyOf((byte[])object, object.length);
      } else {
        arrayOfObject[b] = String.valueOf(object);
      } 
    } 
    return arrayOfObject;
  }
  
  public static void dumpCurrentRow(Cursor paramCursor) {
    dumpCurrentRow(paramCursor, System.out);
  }
  
  public static void dumpCurrentRow(Cursor paramCursor, PrintStream paramPrintStream) {
    String[] arrayOfString = paramCursor.getColumnNames();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramCursor.getPosition());
    stringBuilder.append(" {");
    paramPrintStream.println(stringBuilder.toString());
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str;
      try {
        str = paramCursor.getString(b);
      } catch (SQLiteException sQLiteException) {
        str = "<unprintable>";
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("   ");
      stringBuilder1.append(arrayOfString[b]);
      stringBuilder1.append('=');
      stringBuilder1.append(str);
      paramPrintStream.println(stringBuilder1.toString());
    } 
    paramPrintStream.println("}");
  }
  
  public static void dumpCurrentRow(Cursor paramCursor, StringBuilder paramStringBuilder) {
    String[] arrayOfString = paramCursor.getColumnNames();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramCursor.getPosition());
    stringBuilder.append(" {\n");
    paramStringBuilder.append(stringBuilder.toString());
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str;
      try {
        str = paramCursor.getString(b);
      } catch (SQLiteException sQLiteException) {
        str = "<unprintable>";
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("   ");
      stringBuilder1.append(arrayOfString[b]);
      stringBuilder1.append('=');
      stringBuilder1.append(str);
      stringBuilder1.append("\n");
      paramStringBuilder.append(stringBuilder1.toString());
    } 
    paramStringBuilder.append("}\n");
  }
  
  public static String dumpCurrentRowToString(Cursor paramCursor) {
    StringBuilder stringBuilder = new StringBuilder();
    dumpCurrentRow(paramCursor, stringBuilder);
    return stringBuilder.toString();
  }
  
  public static void dumpCursor(Cursor paramCursor) {
    dumpCursor(paramCursor, System.out);
  }
  
  public static void dumpCursor(Cursor paramCursor, PrintStream paramPrintStream) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(">>>>> Dumping cursor ");
    stringBuilder.append(paramCursor);
    paramPrintStream.println(stringBuilder.toString());
    if (paramCursor != null) {
      int i = paramCursor.getPosition();
      paramCursor.moveToPosition(-1);
      while (paramCursor.moveToNext())
        dumpCurrentRow(paramCursor, paramPrintStream); 
      paramCursor.moveToPosition(i);
    } 
    paramPrintStream.println("<<<<<");
  }
  
  public static void dumpCursor(Cursor paramCursor, StringBuilder paramStringBuilder) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(">>>>> Dumping cursor ");
    stringBuilder.append(paramCursor);
    stringBuilder.append("\n");
    paramStringBuilder.append(stringBuilder.toString());
    if (paramCursor != null) {
      int i = paramCursor.getPosition();
      paramCursor.moveToPosition(-1);
      while (paramCursor.moveToNext())
        dumpCurrentRow(paramCursor, paramStringBuilder); 
      paramCursor.moveToPosition(i);
    } 
    paramStringBuilder.append("<<<<<\n");
  }
  
  public static String dumpCursorToString(Cursor paramCursor) {
    StringBuilder stringBuilder = new StringBuilder();
    dumpCursor(paramCursor, stringBuilder);
    return stringBuilder.toString();
  }
  
  private static char[] encodeHex(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    char[] arrayOfChar = new char[i << 1];
    byte b = 0;
    int j = 0;
    while (b < i) {
      int k = j + 1;
      char[] arrayOfChar1 = DIGITS;
      arrayOfChar[j] = (char)arrayOfChar1[(paramArrayOfbyte[b] & 0xF0) >>> 4];
      j = k + 1;
      arrayOfChar[k] = (char)arrayOfChar1[paramArrayOfbyte[b] & 0xF];
      b++;
    } 
    return arrayOfChar;
  }
  
  public static String escapeForLike(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      if (c != '%') {
        if (c == '_')
          stringBuilder.append('\\'); 
      } else {
        stringBuilder.append('\\');
      } 
      stringBuilder.append(c);
    } 
    return stringBuilder.toString();
  }
  
  public static long executeInsert(SQLiteDatabase paramSQLiteDatabase, String paramString, Object[] paramArrayOfObject) throws SQLException {
    SQLiteStatement sQLiteStatement = paramSQLiteDatabase.compileStatement(paramString);
    try {
      bindArgs(sQLiteStatement, paramArrayOfObject);
      return sQLiteStatement.executeInsert();
    } finally {
      if (sQLiteStatement != null)
        try {
          sQLiteStatement.close();
        } finally {
          sQLiteStatement = null;
        }  
    } 
  }
  
  public static int executeUpdateDelete(SQLiteDatabase paramSQLiteDatabase, String paramString, Object[] paramArrayOfObject) throws SQLException {
    SQLiteStatement sQLiteStatement = paramSQLiteDatabase.compileStatement(paramString);
    try {
      bindArgs(sQLiteStatement, paramArrayOfObject);
      return sQLiteStatement.executeUpdateDelete();
    } finally {
      if (sQLiteStatement != null)
        try {
          sQLiteStatement.close();
        } finally {
          sQLiteStatement = null;
        }  
    } 
  }
  
  public static int findRowIdColumnIndex(String[] paramArrayOfString) {
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfString[b].equals("_id"))
        return b; 
    } 
    return -1;
  }
  
  public static String getCollationKey(String paramString) {
    byte[] arrayOfByte = getCollationKeyInBytes(paramString);
    try {
      return new String(arrayOfByte, 0, getKeyLen(arrayOfByte), "ISO8859_1");
    } catch (Exception exception) {
      return "";
    } 
  }
  
  private static byte[] getCollationKeyInBytes(String paramString) {
    if (mColl == null) {
      Collator collator = Collator.getInstance();
      mColl = collator;
      collator.setStrength(0);
    } 
    return mColl.getCollationKey(paramString).toByteArray();
  }
  
  public static String getHexCollationKey(String paramString) {
    byte[] arrayOfByte = getCollationKeyInBytes(paramString);
    return new String(encodeHex(arrayOfByte), 0, getKeyLen(arrayOfByte) * 2);
  }
  
  private static int getKeyLen(byte[] paramArrayOfbyte) {
    return (paramArrayOfbyte[paramArrayOfbyte.length - 1] != 0) ? paramArrayOfbyte.length : (paramArrayOfbyte.length - 1);
  }
  
  public static int getSqlStatementType(String paramString) {
    StringBuilder stringBuilder;
    paramString = paramString.trim();
    if (paramString.length() < 3)
      return 99; 
    String str = paramString.substring(0, 3).toUpperCase(Locale.ROOT);
    if (str.equals("SEL"))
      return 1; 
    if (str.equals("INS") || str.equals("UPD") || str.equals("REP") || str.equals("DEL"))
      return 2; 
    if (str.equals("ATT"))
      return 3; 
    if (str.equals("COM"))
      return 5; 
    if (str.equals("END"))
      return 5; 
    if (str.equals("ROL")) {
      if (paramString.toUpperCase(Locale.ROOT).contains(" TO ")) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Statement '");
        stringBuilder.append(paramString);
        stringBuilder.append("' may not work on API levels 16-27, use ';");
        stringBuilder.append(paramString);
        stringBuilder.append("' instead");
        Log.w("DatabaseUtils", stringBuilder.toString());
        return 99;
      } 
      return 6;
    } 
    return stringBuilder.equals("BEG") ? 4 : (stringBuilder.equals("PRA") ? 7 : ((stringBuilder.equals("CRE") || stringBuilder.equals("DRO") || stringBuilder.equals("ALT")) ? 8 : ((stringBuilder.equals("ANA") || stringBuilder.equals("DET")) ? 9 : 99)));
  }
  
  public static int getTypeOfObject(Object paramObject) {
    return (paramObject == null) ? 0 : ((paramObject instanceof byte[]) ? 4 : ((paramObject instanceof Float || paramObject instanceof Double) ? 2 : ((paramObject instanceof Long || paramObject instanceof Integer || paramObject instanceof Short || paramObject instanceof Byte) ? 1 : 3)));
  }
  
  public static long longForQuery(SQLiteDatabase paramSQLiteDatabase, String paramString, String[] paramArrayOfString) {
    SQLiteStatement sQLiteStatement = paramSQLiteDatabase.compileStatement(paramString);
    try {
      return longForQuery(sQLiteStatement, paramArrayOfString);
    } finally {
      sQLiteStatement.close();
    } 
  }
  
  public static long longForQuery(SQLiteStatement paramSQLiteStatement, String[] paramArrayOfString) {
    paramSQLiteStatement.bindAllArgsAsStrings(paramArrayOfString);
    return paramSQLiteStatement.simpleQueryForLong();
  }
  
  public static boolean queryIsEmpty(SQLiteDatabase paramSQLiteDatabase, String paramString) {
    boolean bool;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("select exists(select 1 from ");
    stringBuilder.append(paramString);
    stringBuilder.append(")");
    if (longForQuery(paramSQLiteDatabase, stringBuilder.toString(), null) == 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static long queryNumEntries(SQLiteDatabase paramSQLiteDatabase, String paramString) {
    return queryNumEntries(paramSQLiteDatabase, paramString, null, null);
  }
  
  public static long queryNumEntries(SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2) {
    return queryNumEntries(paramSQLiteDatabase, paramString1, paramString2, null);
  }
  
  public static long queryNumEntries(SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, String[] paramArrayOfString) {
    if (!TextUtils.isEmpty(paramString2)) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(" where ");
      stringBuilder1.append(paramString2);
      paramString2 = stringBuilder1.toString();
    } else {
      paramString2 = "";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("select count(*) from ");
    stringBuilder.append(paramString1);
    stringBuilder.append(paramString2);
    return longForQuery(paramSQLiteDatabase, stringBuilder.toString(), paramArrayOfString);
  }
  
  public static final void readExceptionFromParcel(Parcel paramParcel) {
    int i = paramParcel.readExceptionCode();
    if (i == 0)
      return; 
    readExceptionFromParcel(paramParcel, paramParcel.readString(), i);
  }
  
  private static final void readExceptionFromParcel(Parcel paramParcel, String paramString, int paramInt) {
    switch (paramInt) {
      default:
        paramParcel.readException(paramInt, paramString);
        return;
      case 11:
        throw new OperationCanceledException(paramString);
      case 9:
        throw new SQLiteException(paramString);
      case 8:
        throw new SQLiteDiskIOException(paramString);
      case 7:
        throw new SQLiteFullException(paramString);
      case 6:
        throw new SQLiteDatabaseCorruptException(paramString);
      case 5:
        throw new SQLiteConstraintException(paramString);
      case 4:
        throw new SQLiteAbortException(paramString);
      case 3:
        throw new UnsupportedOperationException(paramString);
      case 2:
        break;
    } 
    throw new IllegalArgumentException(paramString);
  }
  
  public static void readExceptionWithFileNotFoundExceptionFromParcel(Parcel paramParcel) throws FileNotFoundException {
    int i = paramParcel.readExceptionCode();
    if (i == 0)
      return; 
    String str = paramParcel.readString();
    if (i != 1) {
      readExceptionFromParcel(paramParcel, str, i);
      return;
    } 
    throw new FileNotFoundException(str);
  }
  
  public static void readExceptionWithOperationApplicationExceptionFromParcel(Parcel paramParcel) throws OperationApplicationException {
    int i = paramParcel.readExceptionCode();
    if (i == 0)
      return; 
    String str = paramParcel.readString();
    if (i != 10) {
      readExceptionFromParcel(paramParcel, str, i);
      return;
    } 
    throw new OperationApplicationException(str);
  }
  
  public static String sqlEscapeString(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    appendEscapedSQLString(stringBuilder, paramString);
    return stringBuilder.toString();
  }
  
  public static String stringForQuery(SQLiteDatabase paramSQLiteDatabase, String paramString, String[] paramArrayOfString) {
    SQLiteStatement sQLiteStatement = paramSQLiteDatabase.compileStatement(paramString);
    try {
      paramString = stringForQuery(sQLiteStatement, paramArrayOfString);
      return paramString;
    } finally {
      sQLiteStatement.close();
    } 
  }
  
  public static String stringForQuery(SQLiteStatement paramSQLiteStatement, String[] paramArrayOfString) {
    paramSQLiteStatement.bindAllArgsAsStrings(paramArrayOfString);
    return paramSQLiteStatement.simpleQueryForString();
  }
  
  public static final void writeExceptionToParcel(Parcel paramParcel, Exception paramException) {
    byte b;
    boolean bool = true;
    if (paramException instanceof FileNotFoundException) {
      b = 1;
      bool = false;
    } else if (paramException instanceof IllegalArgumentException) {
      b = 2;
    } else if (paramException instanceof UnsupportedOperationException) {
      b = 3;
    } else if (paramException instanceof SQLiteAbortException) {
      b = 4;
    } else if (paramException instanceof SQLiteConstraintException) {
      b = 5;
    } else if (paramException instanceof SQLiteDatabaseCorruptException) {
      b = 6;
    } else if (paramException instanceof SQLiteFullException) {
      b = 7;
    } else if (paramException instanceof SQLiteDiskIOException) {
      b = 8;
    } else if (paramException instanceof SQLiteException) {
      b = 9;
    } else if (paramException instanceof OperationApplicationException) {
      b = 10;
    } else if (paramException instanceof OperationCanceledException) {
      b = 11;
      bool = false;
    } else {
      paramParcel.writeException(paramException);
      Log.e("DatabaseUtils", "Writing exception to parcel", paramException);
      return;
    } 
    paramParcel.writeInt(b);
    paramParcel.writeString(paramException.getMessage());
    if (bool)
      Log.e("DatabaseUtils", "Writing exception to parcel", paramException); 
  }
  
  @Deprecated
  public static class InsertHelper {
    public static final int TABLE_INFO_PRAGMA_COLUMNNAME_INDEX = 1;
    
    public static final int TABLE_INFO_PRAGMA_DEFAULT_INDEX = 4;
    
    private HashMap<String, Integer> mColumns;
    
    private final SQLiteDatabase mDb;
    
    private String mInsertSQL = null;
    
    private SQLiteStatement mInsertStatement = null;
    
    private SQLiteStatement mPreparedStatement = null;
    
    private SQLiteStatement mReplaceStatement = null;
    
    private final String mTableName;
    
    public InsertHelper(SQLiteDatabase param1SQLiteDatabase, String param1String) {
      this.mDb = param1SQLiteDatabase;
      this.mTableName = param1String;
    }
    
    private void buildSQL() throws SQLException {
      StringBuilder stringBuilder1 = new StringBuilder(128);
      stringBuilder1.append("INSERT INTO ");
      stringBuilder1.append(this.mTableName);
      stringBuilder1.append(" (");
      StringBuilder stringBuilder2 = new StringBuilder(128);
      stringBuilder2.append("VALUES (");
      int i = 1;
      null = null;
      Cursor cursor = null;
      try {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        cursor = null;
        StringBuilder stringBuilder = new StringBuilder();
        cursor = null;
        this();
        cursor = null;
        stringBuilder.append("PRAGMA table_info(");
        cursor = null;
        stringBuilder.append(this.mTableName);
        cursor = null;
        stringBuilder.append(")");
        cursor = null;
        Cursor cursor1 = sQLiteDatabase.rawQuery(stringBuilder.toString(), null);
        cursor = cursor1;
        HashMap<Object, Object> hashMap = new HashMap<>();
        cursor = cursor1;
        this(cursor1.getCount());
        cursor = cursor1;
        this.mColumns = (HashMap)hashMap;
        while (true) {
          cursor = cursor1;
          if (cursor1.moveToNext()) {
            cursor = cursor1;
            String str2 = cursor1.getString(1);
            cursor = cursor1;
            String str1 = cursor1.getString(4);
            cursor = cursor1;
            this.mColumns.put(str2, Integer.valueOf(i));
            cursor = cursor1;
            stringBuilder1.append("'");
            cursor = cursor1;
            stringBuilder1.append(str2);
            cursor = cursor1;
            stringBuilder1.append("'");
            if (str1 == null) {
              cursor = cursor1;
              stringBuilder2.append("?");
            } else {
              cursor = cursor1;
              stringBuilder2.append("COALESCE(?, ");
              cursor = cursor1;
              stringBuilder2.append(str1);
              cursor = cursor1;
              stringBuilder2.append(")");
            } 
            cursor = cursor1;
            int j = cursor1.getCount();
            str2 = ", ";
            if (i == j) {
              str1 = ") ";
            } else {
              str1 = ", ";
            } 
            cursor = cursor1;
            stringBuilder1.append(str1);
            str1 = str2;
            cursor = cursor1;
            if (i == cursor1.getCount())
              str1 = ");"; 
            cursor = cursor1;
            stringBuilder2.append(str1);
            i++;
            continue;
          } 
          if (cursor1 != null)
            cursor1.close(); 
          stringBuilder1.append(stringBuilder2);
          return;
        } 
      } finally {
        if (cursor != null)
          cursor.close(); 
      } 
    }
    
    private SQLiteStatement getStatement(boolean param1Boolean) throws SQLException {
      if (param1Boolean) {
        if (this.mReplaceStatement == null) {
          if (this.mInsertSQL == null)
            buildSQL(); 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("INSERT OR REPLACE");
          stringBuilder.append(this.mInsertSQL.substring(6));
          String str = stringBuilder.toString();
          this.mReplaceStatement = this.mDb.compileStatement(str);
        } 
        return this.mReplaceStatement;
      } 
      if (this.mInsertStatement == null) {
        if (this.mInsertSQL == null)
          buildSQL(); 
        this.mInsertStatement = this.mDb.compileStatement(this.mInsertSQL);
      } 
      return this.mInsertStatement;
    }
    
    private long insertInternal(ContentValues param1ContentValues, boolean param1Boolean) {
      this.mDb.beginTransactionNonExclusive();
      try {
        SQLiteStatement sQLiteStatement = getStatement(param1Boolean);
        sQLiteStatement.clearBindings();
        for (Map.Entry entry : param1ContentValues.valueSet())
          DatabaseUtils.bindObjectToProgram((SQLiteProgram)sQLiteStatement, getColumnIndex((String)entry.getKey()), entry.getValue()); 
        long l = sQLiteStatement.executeInsert();
        this.mDb.setTransactionSuccessful();
        this.mDb.endTransaction();
        return l;
      } catch (SQLException sQLException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Error inserting ");
        stringBuilder.append(param1ContentValues);
        stringBuilder.append(" into table  ");
        stringBuilder.append(this.mTableName);
        Log.e("DatabaseUtils", stringBuilder.toString(), sQLException);
        this.mDb.endTransaction();
        return -1L;
      } finally {}
      this.mDb.endTransaction();
      throw param1ContentValues;
    }
    
    public void bind(int param1Int, double param1Double) {
      this.mPreparedStatement.bindDouble(param1Int, param1Double);
    }
    
    public void bind(int param1Int, float param1Float) {
      this.mPreparedStatement.bindDouble(param1Int, param1Float);
    }
    
    public void bind(int param1Int1, int param1Int2) {
      this.mPreparedStatement.bindLong(param1Int1, param1Int2);
    }
    
    public void bind(int param1Int, long param1Long) {
      this.mPreparedStatement.bindLong(param1Int, param1Long);
    }
    
    public void bind(int param1Int, String param1String) {
      if (param1String == null) {
        this.mPreparedStatement.bindNull(param1Int);
      } else {
        this.mPreparedStatement.bindString(param1Int, param1String);
      } 
    }
    
    public void bind(int param1Int, boolean param1Boolean) {
      long l;
      SQLiteStatement sQLiteStatement = this.mPreparedStatement;
      if (param1Boolean) {
        l = 1L;
      } else {
        l = 0L;
      } 
      sQLiteStatement.bindLong(param1Int, l);
    }
    
    public void bind(int param1Int, byte[] param1ArrayOfbyte) {
      if (param1ArrayOfbyte == null) {
        this.mPreparedStatement.bindNull(param1Int);
      } else {
        this.mPreparedStatement.bindBlob(param1Int, param1ArrayOfbyte);
      } 
    }
    
    public void bindNull(int param1Int) {
      this.mPreparedStatement.bindNull(param1Int);
    }
    
    public void close() {
      SQLiteStatement sQLiteStatement = this.mInsertStatement;
      if (sQLiteStatement != null) {
        sQLiteStatement.close();
        this.mInsertStatement = null;
      } 
      sQLiteStatement = this.mReplaceStatement;
      if (sQLiteStatement != null) {
        sQLiteStatement.close();
        this.mReplaceStatement = null;
      } 
      this.mInsertSQL = null;
      this.mColumns = null;
    }
    
    public long execute() {
      SQLiteStatement sQLiteStatement = this.mPreparedStatement;
      if (sQLiteStatement != null) {
        try {
          long l = sQLiteStatement.executeInsert();
          this.mPreparedStatement = null;
          return l;
        } catch (SQLException sQLException) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Error executing InsertHelper with table ");
          stringBuilder.append(this.mTableName);
          Log.e("DatabaseUtils", stringBuilder.toString(), sQLException);
          this.mPreparedStatement = null;
          return -1L;
        } finally {}
        this.mPreparedStatement = null;
        throw sQLiteStatement;
      } 
      throw new IllegalStateException("you must prepare this inserter before calling execute");
    }
    
    public int getColumnIndex(String param1String) {
      getStatement(false);
      Integer integer = this.mColumns.get(param1String);
      if (integer != null)
        return integer.intValue(); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("column '");
      stringBuilder.append(param1String);
      stringBuilder.append("' is invalid");
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public long insert(ContentValues param1ContentValues) {
      return insertInternal(param1ContentValues, false);
    }
    
    public void prepareForInsert() {
      SQLiteStatement sQLiteStatement = getStatement(false);
      this.mPreparedStatement = sQLiteStatement;
      sQLiteStatement.clearBindings();
    }
    
    public void prepareForReplace() {
      SQLiteStatement sQLiteStatement = getStatement(true);
      this.mPreparedStatement = sQLiteStatement;
      sQLiteStatement.clearBindings();
    }
    
    public long replace(ContentValues param1ContentValues) {
      return insertInternal(param1ContentValues, true);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/DatabaseUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
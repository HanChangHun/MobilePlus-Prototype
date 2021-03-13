package android.database.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Build;
import android.os.CancellationSignal;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import libcore.util.EmptyArray;

public class SQLiteQueryBuilder {
  private static final int STRICT_COLUMNS = 2;
  
  private static final int STRICT_GRAMMAR = 4;
  
  private static final int STRICT_PARENTHESES = 1;
  
  private static final String TAG = "SQLiteQueryBuilder";
  
  private static final Pattern sAggregationPattern = Pattern.compile("(?i)(AVG|COUNT|MAX|MIN|SUM|TOTAL|GROUP_CONCAT)\\((.+)\\)");
  
  private boolean mDistinct = false;
  
  private SQLiteDatabase.CursorFactory mFactory = null;
  
  private Collection<Pattern> mProjectionGreylist = null;
  
  private Map<String, String> mProjectionMap = null;
  
  private int mStrictFlags;
  
  private String mTables = "";
  
  private StringBuilder mWhereClause = null;
  
  private static void appendClause(StringBuilder paramStringBuilder, String paramString1, String paramString2) {
    if (!TextUtils.isEmpty(paramString2)) {
      paramStringBuilder.append(paramString1);
      paramStringBuilder.append(paramString2);
    } 
  }
  
  public static void appendColumns(StringBuilder paramStringBuilder, String[] paramArrayOfString) {
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = paramArrayOfString[b];
      if (str != null) {
        if (b > 0)
          paramStringBuilder.append(", "); 
        paramStringBuilder.append(str);
      } 
    } 
    paramStringBuilder.append(' ');
  }
  
  public static String buildQueryString(boolean paramBoolean, String paramString1, String[] paramArrayOfString, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6) {
    if (!TextUtils.isEmpty(paramString3) || TextUtils.isEmpty(paramString4)) {
      StringBuilder stringBuilder = new StringBuilder(120);
      stringBuilder.append("SELECT ");
      if (paramBoolean)
        stringBuilder.append("DISTINCT "); 
      if (paramArrayOfString != null && paramArrayOfString.length != 0) {
        appendColumns(stringBuilder, paramArrayOfString);
      } else {
        stringBuilder.append("* ");
      } 
      stringBuilder.append("FROM ");
      stringBuilder.append(paramString1);
      appendClause(stringBuilder, " WHERE ", paramString2);
      appendClause(stringBuilder, " GROUP BY ", paramString3);
      appendClause(stringBuilder, " HAVING ", paramString4);
      appendClause(stringBuilder, " ORDER BY ", paramString5);
      appendClause(stringBuilder, " LIMIT ", paramString6);
      return stringBuilder.toString();
    } 
    throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
  }
  
  private String computeSingleProjection(String paramString) {
    Map<String, String> map = this.mProjectionMap;
    if (map == null)
      return paramString; 
    String str2 = null;
    String str3 = map.get(paramString);
    String str4 = str3;
    String str5 = str2;
    String str1 = paramString;
    if (str3 == null) {
      Matcher matcher = sAggregationPattern.matcher(paramString);
      str4 = str3;
      str5 = str2;
      str1 = paramString;
      if (matcher.matches()) {
        str5 = matcher.group(1);
        str1 = matcher.group(2);
        str4 = this.mProjectionMap.get(str1);
      } 
    } 
    if (str4 != null)
      return maybeWithOperator(str5, str4); 
    if (this.mStrictFlags == 0 && (str1.contains(" AS ") || str1.contains(" as ")))
      return maybeWithOperator(str5, str1); 
    Collection<Pattern> collection = this.mProjectionGreylist;
    if (collection != null) {
      boolean bool2;
      boolean bool1 = false;
      Iterator<Pattern> iterator = collection.iterator();
      while (true) {
        bool2 = bool1;
        if (iterator.hasNext()) {
          if (((Pattern)iterator.next()).matcher(str1).matches()) {
            bool2 = true;
            break;
          } 
          continue;
        } 
        break;
      } 
      if (bool2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Allowing abusive custom column: ");
        stringBuilder.append(str1);
        Log.w("SQLiteQueryBuilder", stringBuilder.toString());
        return maybeWithOperator(str5, str1);
      } 
    } 
    return null;
  }
  
  private String computeSingleProjectionOrThrow(String paramString) {
    String str = computeSingleProjection(paramString);
    if (str != null)
      return str; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid column ");
    stringBuilder.append(paramString);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private void enforceStrictColumns(ContentValues paramContentValues) {
    Objects.requireNonNull(this.mProjectionMap, "No projection map defined");
    ArrayMap arrayMap = paramContentValues.getValues();
    byte b = 0;
    while (b < arrayMap.size()) {
      String str = (String)arrayMap.keyAt(b);
      if (this.mProjectionMap.containsKey(str)) {
        b++;
        continue;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid column ");
      stringBuilder.append(str);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
  }
  
  private void enforceStrictColumns(String[] paramArrayOfString) {
    Objects.requireNonNull(this.mProjectionMap, "No projection map defined");
    computeProjection(paramArrayOfString);
  }
  
  private void enforceStrictGrammar(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    SQLiteTokenizer.tokenize(paramString1, 0, new _$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI(this));
    SQLiteTokenizer.tokenize(paramString2, 0, new _$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI(this));
    SQLiteTokenizer.tokenize(paramString3, 0, new _$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI(this));
    SQLiteTokenizer.tokenize(paramString4, 0, new _$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI(this));
    SQLiteTokenizer.tokenize(paramString5, 0, new _$$Lambda$SQLiteQueryBuilder$W2yQ6UjYGqGIu6HEomKgdgvGNKI(this));
  }
  
  private void enforceStrictToken(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return; 
    if (isTableOrColumn(paramString))
      return; 
    if (SQLiteTokenizer.isFunction(paramString))
      return; 
    if (SQLiteTokenizer.isType(paramString))
      return; 
    boolean bool = SQLiteTokenizer.isKeyword(paramString);
    switch (paramString.toUpperCase(Locale.US)) {
      case "SELECT":
      case "FROM":
      case "WHERE":
      case "GROUP":
      case "HAVING":
      case "WINDOW":
      case "VALUES":
      case "ORDER":
      case "LIMIT":
        bool = false;
        break;
    } 
    if (bool)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid token ");
    stringBuilder.append(paramString);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private boolean isTableOrColumn(String paramString) {
    boolean bool = this.mTables.equals(paramString);
    boolean bool1 = true;
    if (bool)
      return true; 
    if (computeSingleProjection(paramString) == null)
      bool1 = false; 
    return bool1;
  }
  
  private static String maybeWithOperator(String paramString1, String paramString2) {
    if (paramString1 != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString1);
      stringBuilder.append("(");
      stringBuilder.append(paramString2);
      stringBuilder.append(")");
      return stringBuilder.toString();
    } 
    return paramString2;
  }
  
  private String wrap(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return paramString; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("(");
    stringBuilder.append(paramString);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void appendWhere(CharSequence paramCharSequence) {
    if (this.mWhereClause == null)
      this.mWhereClause = new StringBuilder(paramCharSequence.length() + 16); 
    this.mWhereClause.append(paramCharSequence);
  }
  
  public void appendWhereEscapeString(String paramString) {
    if (this.mWhereClause == null)
      this.mWhereClause = new StringBuilder(paramString.length() + 16); 
    DatabaseUtils.appendEscapedSQLString(this.mWhereClause, paramString);
  }
  
  public void appendWhereStandalone(CharSequence paramCharSequence) {
    if (this.mWhereClause == null)
      this.mWhereClause = new StringBuilder(paramCharSequence.length() + 16); 
    if (this.mWhereClause.length() > 0)
      this.mWhereClause.append(" AND "); 
    StringBuilder stringBuilder = this.mWhereClause;
    stringBuilder.append('(');
    stringBuilder.append(paramCharSequence);
    stringBuilder.append(')');
  }
  
  public String buildDelete(String paramString) {
    StringBuilder stringBuilder = new StringBuilder(120);
    stringBuilder.append("DELETE FROM ");
    stringBuilder.append(SQLiteDatabase.findEditTable(this.mTables));
    appendClause(stringBuilder, " WHERE ", computeWhere(paramString));
    return stringBuilder.toString();
  }
  
  public String buildInsert(ContentValues paramContentValues) {
    if (paramContentValues != null && !paramContentValues.isEmpty()) {
      StringBuilder stringBuilder = new StringBuilder(120);
      stringBuilder.append("INSERT INTO ");
      stringBuilder.append(SQLiteDatabase.findEditTable(this.mTables));
      stringBuilder.append(" (");
      ArrayMap arrayMap = paramContentValues.getValues();
      byte b;
      for (b = 0; b < arrayMap.size(); b++) {
        if (b > 0)
          stringBuilder.append(','); 
        stringBuilder.append((String)arrayMap.keyAt(b));
      } 
      stringBuilder.append(") VALUES (");
      for (b = 0; b < arrayMap.size(); b++) {
        if (b > 0)
          stringBuilder.append(','); 
        stringBuilder.append('?');
      } 
      stringBuilder.append(")");
      return stringBuilder.toString();
    } 
    throw new IllegalArgumentException("Empty values");
  }
  
  public String buildQuery(String[] paramArrayOfString, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    paramArrayOfString = computeProjection(paramArrayOfString);
    paramString1 = computeWhere(paramString1);
    return buildQueryString(this.mDistinct, this.mTables, paramArrayOfString, paramString1, paramString2, paramString3, paramString4, paramString5);
  }
  
  @Deprecated
  public String buildQuery(String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, String paramString3, String paramString4, String paramString5) {
    return buildQuery(paramArrayOfString1, paramString1, paramString2, paramString3, paramString4, paramString5);
  }
  
  public String buildUnionQuery(String[] paramArrayOfString, String paramString1, String paramString2) {
    String str;
    StringBuilder stringBuilder = new StringBuilder(128);
    int i = paramArrayOfString.length;
    if (this.mDistinct) {
      str = " UNION ";
    } else {
      str = " UNION ALL ";
    } 
    for (byte b = 0; b < i; b++) {
      if (b > 0)
        stringBuilder.append(str); 
      stringBuilder.append(paramArrayOfString[b]);
    } 
    appendClause(stringBuilder, " ORDER BY ", paramString1);
    appendClause(stringBuilder, " LIMIT ", paramString2);
    return stringBuilder.toString();
  }
  
  public String buildUnionSubQuery(String paramString1, String[] paramArrayOfString, Set<String> paramSet, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5) {
    int i = paramArrayOfString.length;
    String[] arrayOfString = new String[i];
    for (byte b = 0; b < i; b++) {
      String str = paramArrayOfString[b];
      if (str.equals(paramString1)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'");
        stringBuilder.append(paramString2);
        stringBuilder.append("' AS ");
        stringBuilder.append(paramString1);
        arrayOfString[b] = stringBuilder.toString();
      } else if (b <= paramInt || paramSet.contains(str)) {
        arrayOfString[b] = str;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("NULL AS ");
        stringBuilder.append(str);
        arrayOfString[b] = stringBuilder.toString();
      } 
    } 
    return buildQuery(arrayOfString, paramString3, paramString4, paramString5, null, null);
  }
  
  @Deprecated
  public String buildUnionSubQuery(String paramString1, String[] paramArrayOfString1, Set<String> paramSet, int paramInt, String paramString2, String paramString3, String[] paramArrayOfString2, String paramString4, String paramString5) {
    return buildUnionSubQuery(paramString1, paramArrayOfString1, paramSet, paramInt, paramString2, paramString3, paramString4, paramString5);
  }
  
  public String buildUpdate(ContentValues paramContentValues, String paramString) {
    if (paramContentValues != null && !paramContentValues.isEmpty()) {
      StringBuilder stringBuilder = new StringBuilder(120);
      stringBuilder.append("UPDATE ");
      stringBuilder.append(SQLiteDatabase.findEditTable(this.mTables));
      stringBuilder.append(" SET ");
      ArrayMap arrayMap = paramContentValues.getValues();
      for (byte b = 0; b < arrayMap.size(); b++) {
        if (b > 0)
          stringBuilder.append(','); 
        stringBuilder.append((String)arrayMap.keyAt(b));
        stringBuilder.append("=?");
      } 
      appendClause(stringBuilder, " WHERE ", computeWhere(paramString));
      return stringBuilder.toString();
    } 
    throw new IllegalArgumentException("Empty values");
  }
  
  public String[] computeProjection(String[] paramArrayOfString) {
    if (!ArrayUtils.isEmpty((Object[])paramArrayOfString)) {
      String[] arrayOfString = new String[paramArrayOfString.length];
      for (byte b = 0; b < paramArrayOfString.length; b++)
        arrayOfString[b] = computeSingleProjectionOrThrow(paramArrayOfString[b]); 
      return arrayOfString;
    } 
    Map<String, String> map = this.mProjectionMap;
    if (map != null) {
      Set<Map.Entry<String, String>> set = map.entrySet();
      String[] arrayOfString = new String[set.size()];
      Iterator<Map.Entry<String, String>> iterator = set.iterator();
      for (byte b = 0; iterator.hasNext(); b++) {
        Map.Entry entry = iterator.next();
        if (((String)entry.getKey()).equals("_count"))
          continue; 
        arrayOfString[b] = (String)entry.getValue();
      } 
      return arrayOfString;
    } 
    return null;
  }
  
  public String computeWhere(String paramString) {
    int i = TextUtils.isEmpty(this.mWhereClause) ^ true;
    int j = TextUtils.isEmpty(paramString) ^ true;
    if (i != 0 || j != 0) {
      StringBuilder stringBuilder = new StringBuilder();
      if (i != 0) {
        stringBuilder.append('(');
        stringBuilder.append(this.mWhereClause);
        stringBuilder.append(')');
      } 
      if (i != 0 && j != 0)
        stringBuilder.append(" AND "); 
      if (j != 0) {
        stringBuilder.append('(');
        stringBuilder.append(paramString);
        stringBuilder.append(')');
      } 
      return stringBuilder.toString();
    } 
    return null;
  }
  
  public int delete(SQLiteDatabase paramSQLiteDatabase, String paramString, String[] paramArrayOfString) {
    Objects.requireNonNull(this.mTables, "No tables defined");
    Objects.requireNonNull(paramSQLiteDatabase, "No database defined");
    String str = buildDelete(paramString);
    if (isStrictGrammar())
      enforceStrictGrammar(paramString, null, null, null, null); 
    if (isStrict()) {
      paramSQLiteDatabase.validateSql(str, null);
      paramString = buildDelete(wrap(paramString));
    } else {
      paramString = str;
    } 
    if (Log.isLoggable("SQLiteQueryBuilder", 3))
      if (Build.IS_DEBUGGABLE) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(" with args ");
        stringBuilder.append(Arrays.toString((Object[])paramArrayOfString));
        Log.d("SQLiteQueryBuilder", stringBuilder.toString());
      } else {
        Log.d("SQLiteQueryBuilder", paramString);
      }  
    return DatabaseUtils.executeUpdateDelete(paramSQLiteDatabase, paramString, (Object[])paramArrayOfString);
  }
  
  public SQLiteDatabase.CursorFactory getCursorFactory() {
    return this.mFactory;
  }
  
  public Collection<Pattern> getProjectionGreylist() {
    return this.mProjectionGreylist;
  }
  
  public Map<String, String> getProjectionMap() {
    return this.mProjectionMap;
  }
  
  public String getTables() {
    return this.mTables;
  }
  
  public long insert(SQLiteDatabase paramSQLiteDatabase, ContentValues paramContentValues) {
    Objects.requireNonNull(this.mTables, "No tables defined");
    Objects.requireNonNull(paramSQLiteDatabase, "No database defined");
    Objects.requireNonNull(paramContentValues, "No values defined");
    if (isStrictColumns())
      enforceStrictColumns(paramContentValues); 
    String str = buildInsert(paramContentValues);
    ArrayMap arrayMap = paramContentValues.getValues();
    Object[] arrayOfObject = new Object[arrayMap.size()];
    for (byte b = 0; b < arrayOfObject.length; b++)
      arrayOfObject[b] = arrayMap.valueAt(b); 
    if (Log.isLoggable("SQLiteQueryBuilder", 3))
      if (Build.IS_DEBUGGABLE) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" with args ");
        stringBuilder.append(Arrays.toString(arrayOfObject));
        Log.d("SQLiteQueryBuilder", stringBuilder.toString());
      } else {
        Log.d("SQLiteQueryBuilder", str);
      }  
    return DatabaseUtils.executeInsert(paramSQLiteDatabase, str, arrayOfObject);
  }
  
  public boolean isDistinct() {
    return this.mDistinct;
  }
  
  @Deprecated
  public boolean isProjectionAggregationAllowed() {
    return true;
  }
  
  public boolean isStrict() {
    int i = this.mStrictFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean isStrictColumns() {
    boolean bool;
    if ((this.mStrictFlags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStrictGrammar() {
    boolean bool;
    if ((this.mStrictFlags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Cursor query(SQLiteDatabase paramSQLiteDatabase, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, String paramString3, String paramString4) {
    return query(paramSQLiteDatabase, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, paramString3, paramString4, null, null);
  }
  
  public Cursor query(SQLiteDatabase paramSQLiteDatabase, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, String paramString3, String paramString4, String paramString5) {
    return query(paramSQLiteDatabase, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, paramString3, paramString4, paramString5, null);
  }
  
  public Cursor query(SQLiteDatabase paramSQLiteDatabase, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, String paramString3, String paramString4, String paramString5, CancellationSignal paramCancellationSignal) {
    String str1;
    if (this.mTables == null)
      return null; 
    String str2 = buildQuery(paramArrayOfString1, paramString1, paramString2, paramString3, paramString4, paramString5);
    if (isStrictColumns())
      enforceStrictColumns(paramArrayOfString1); 
    if (isStrictGrammar())
      enforceStrictGrammar(paramString1, paramString2, paramString3, paramString4, paramString5); 
    if (isStrict()) {
      paramSQLiteDatabase.validateSql(str2, paramCancellationSignal);
      str1 = buildQuery(paramArrayOfString1, wrap(paramString1), paramString2, wrap(paramString3), paramString4, paramString5);
    } else {
      str1 = str2;
    } 
    if (Log.isLoggable("SQLiteQueryBuilder", 3))
      if (Build.IS_DEBUGGABLE) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append(" with args ");
        stringBuilder.append(Arrays.toString((Object[])paramArrayOfString2));
        Log.d("SQLiteQueryBuilder", stringBuilder.toString());
      } else {
        Log.d("SQLiteQueryBuilder", str1);
      }  
    return paramSQLiteDatabase.rawQueryWithFactory(this.mFactory, str1, paramArrayOfString2, SQLiteDatabase.findEditTable(this.mTables), paramCancellationSignal);
  }
  
  public void setCursorFactory(SQLiteDatabase.CursorFactory paramCursorFactory) {
    this.mFactory = paramCursorFactory;
  }
  
  public void setDistinct(boolean paramBoolean) {
    this.mDistinct = paramBoolean;
  }
  
  @Deprecated
  public void setProjectionAggregationAllowed(boolean paramBoolean) {}
  
  public void setProjectionGreylist(Collection<Pattern> paramCollection) {
    this.mProjectionGreylist = paramCollection;
  }
  
  public void setProjectionMap(Map<String, String> paramMap) {
    this.mProjectionMap = paramMap;
  }
  
  public void setStrict(boolean paramBoolean) {
    if (paramBoolean) {
      this.mStrictFlags |= 0x1;
    } else {
      this.mStrictFlags &= 0xFFFFFFFE;
    } 
  }
  
  public void setStrictColumns(boolean paramBoolean) {
    if (paramBoolean) {
      this.mStrictFlags |= 0x2;
    } else {
      this.mStrictFlags &= 0xFFFFFFFD;
    } 
  }
  
  public void setStrictGrammar(boolean paramBoolean) {
    if (paramBoolean) {
      this.mStrictFlags |= 0x4;
    } else {
      this.mStrictFlags &= 0xFFFFFFFB;
    } 
  }
  
  public void setTables(String paramString) {
    this.mTables = paramString;
  }
  
  public int update(SQLiteDatabase paramSQLiteDatabase, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) {
    Objects.requireNonNull(this.mTables, "No tables defined");
    Objects.requireNonNull(paramSQLiteDatabase, "No database defined");
    Objects.requireNonNull(paramContentValues, "No values defined");
    String str = buildUpdate(paramContentValues, paramString);
    if (isStrictColumns())
      enforceStrictColumns(paramContentValues); 
    if (isStrictGrammar())
      enforceStrictGrammar(paramString, null, null, null, null); 
    if (isStrict()) {
      paramSQLiteDatabase.validateSql(str, null);
      paramString = buildUpdate(paramContentValues, wrap(paramString));
    } else {
      paramString = str;
    } 
    String[] arrayOfString = paramArrayOfString;
    if (paramArrayOfString == null)
      arrayOfString = EmptyArray.STRING; 
    ArrayMap arrayMap = paramContentValues.getValues();
    int i = arrayMap.size();
    Object[] arrayOfObject = new Object[arrayOfString.length + i];
    for (byte b = 0; b < arrayOfObject.length; b++) {
      if (b < i) {
        arrayOfObject[b] = arrayMap.valueAt(b);
      } else {
        arrayOfObject[b] = arrayOfString[b - i];
      } 
    } 
    if (Log.isLoggable("SQLiteQueryBuilder", 3))
      if (Build.IS_DEBUGGABLE) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString);
        stringBuilder.append(" with args ");
        stringBuilder.append(Arrays.toString(arrayOfObject));
        Log.d("SQLiteQueryBuilder", stringBuilder.toString());
      } else {
        Log.d("SQLiteQueryBuilder", paramString);
      }  
    return DatabaseUtils.executeUpdateDelete(paramSQLiteDatabase, paramString, arrayOfObject);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteQueryBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;

public class Query {
  public static final int ORDER_ASCENDING = 1;
  
  public static final int ORDER_DESCENDING = 2;
  
  private String mFilterString = null;
  
  private long[] mIds = null;
  
  private boolean mOnlyIncludeVisibleInDownloadsUi = false;
  
  private String mOrderByColumn = "lastmod";
  
  private int mOrderDirection = 2;
  
  private Integer mStatusFlags = null;
  
  private String joinStrings(String paramString, Iterable<String> paramIterable) {
    StringBuilder stringBuilder = new StringBuilder();
    boolean bool = true;
    for (String str : paramIterable) {
      if (!bool)
        stringBuilder.append(paramString); 
      stringBuilder.append(str);
      bool = false;
    } 
    return stringBuilder.toString();
  }
  
  private String statusClause(String paramString, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("status");
    stringBuilder.append(paramString);
    stringBuilder.append("'");
    stringBuilder.append(paramInt);
    stringBuilder.append("'");
    return stringBuilder.toString();
  }
  
  public Query orderBy(String paramString, int paramInt) {
    if (paramInt == 1 || paramInt == 2) {
      if (paramString.equals("last_modified_timestamp")) {
        this.mOrderByColumn = "lastmod";
      } else {
        if (paramString.equals("total_size")) {
          this.mOrderByColumn = "total_bytes";
          this.mOrderDirection = paramInt;
          return this;
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Cannot order by ");
        stringBuilder1.append(paramString);
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      this.mOrderDirection = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid direction: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  Cursor runQuery(ContentResolver paramContentResolver, String[] paramArrayOfString, Uri paramUri) {
    String str1;
    int i;
    ArrayList<String> arrayList = new ArrayList();
    long[] arrayOfLong = this.mIds;
    if (arrayOfLong == null) {
      i = 0;
    } else {
      i = arrayOfLong.length;
    } 
    if (this.mFilterString != null)
      i++; 
    String[] arrayOfString = new String[i];
    if (i > 0) {
      long[] arrayOfLong1 = this.mIds;
      if (arrayOfLong1 != null) {
        arrayList.add(DownloadManager.getWhereClauseForIds(arrayOfLong1));
        DownloadManager.getWhereArgsForIds(this.mIds, arrayOfString);
      } 
      if (this.mFilterString != null) {
        arrayList.add("title LIKE ?");
        i = arrayOfString.length;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("%");
        stringBuilder1.append(this.mFilterString);
        stringBuilder1.append("%");
        arrayOfString[i - 1] = stringBuilder1.toString();
      } 
    } 
    if (this.mStatusFlags != null) {
      ArrayList<String> arrayList1 = new ArrayList();
      if ((this.mStatusFlags.intValue() & 0x1) != 0)
        arrayList1.add(statusClause("=", 190)); 
      if ((this.mStatusFlags.intValue() & 0x2) != 0)
        arrayList1.add(statusClause("=", 192)); 
      if ((this.mStatusFlags.intValue() & 0x4) != 0) {
        arrayList1.add(statusClause("=", 193));
        arrayList1.add(statusClause("=", 194));
        arrayList1.add(statusClause("=", 195));
        arrayList1.add(statusClause("=", 196));
      } 
      if ((this.mStatusFlags.intValue() & 0x8) != 0)
        arrayList1.add(statusClause("=", 200)); 
      if ((this.mStatusFlags.intValue() & 0x10) != 0) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("(");
        stringBuilder1.append(statusClause(">=", 400));
        stringBuilder1.append(" AND ");
        stringBuilder1.append(statusClause("<", 600));
        stringBuilder1.append(")");
        arrayList1.add(stringBuilder1.toString());
      } 
      arrayList.add(joinStrings(" OR ", arrayList1));
    } 
    if (this.mOnlyIncludeVisibleInDownloadsUi)
      arrayList.add("is_visible_in_downloads_ui != '0'"); 
    arrayList.add("deleted != '1'");
    String str2 = joinStrings(" AND ", arrayList);
    if (this.mOrderDirection == 1) {
      str1 = "ASC";
    } else {
      str1 = "DESC";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mOrderByColumn);
    stringBuilder.append(" ");
    stringBuilder.append(str1);
    return paramContentResolver.query(paramUri, paramArrayOfString, str2, arrayOfString, stringBuilder.toString());
  }
  
  public Query setFilterById(long... paramVarArgs) {
    this.mIds = paramVarArgs;
    return this;
  }
  
  public Query setFilterByStatus(int paramInt) {
    this.mStatusFlags = Integer.valueOf(paramInt);
    return this;
  }
  
  public Query setFilterByString(String paramString) {
    this.mFilterString = paramString;
    return this;
  }
  
  public Query setOnlyIncludeVisibleInDownloadsUi(boolean paramBoolean) {
    this.mOnlyIncludeVisibleInDownloadsUi = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DownloadManager$Query.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
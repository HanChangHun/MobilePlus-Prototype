package android.content;

public final class FilterComparison {
  private final int mHashCode;
  
  private final Intent mIntent;
  
  public FilterComparison(Intent paramIntent) {
    this.mIntent = paramIntent;
    this.mHashCode = paramIntent.filterHashCode();
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof FilterComparison) {
      paramObject = ((FilterComparison)paramObject).mIntent;
      return this.mIntent.filterEquals((Intent)paramObject);
    } 
    return false;
  }
  
  public Intent getIntent() {
    return this.mIntent;
  }
  
  public int hashCode() {
    return this.mHashCode;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/Intent$FilterComparison.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
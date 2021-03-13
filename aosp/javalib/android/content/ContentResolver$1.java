package android.content;

class null extends ContentResolver {
  null(Context paramContext, ContentInterface paramContentInterface) {
    super(paramContext, paramContentInterface);
  }
  
  protected IContentProvider acquireProvider(Context paramContext, String paramString) {
    throw new UnsupportedOperationException();
  }
  
  protected IContentProvider acquireUnstableProvider(Context paramContext, String paramString) {
    throw new UnsupportedOperationException();
  }
  
  public boolean releaseProvider(IContentProvider paramIContentProvider) {
    throw new UnsupportedOperationException();
  }
  
  public boolean releaseUnstableProvider(IContentProvider paramIContentProvider) {
    throw new UnsupportedOperationException();
  }
  
  public void unstableProviderDied(IContentProvider paramIContentProvider) {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentResolver$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
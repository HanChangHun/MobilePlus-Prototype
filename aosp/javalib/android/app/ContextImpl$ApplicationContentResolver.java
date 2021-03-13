package android.app;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IContentProvider;
import java.util.Objects;

final class ApplicationContentResolver extends ContentResolver {
  private final ActivityThread mMainThread;
  
  public ApplicationContentResolver(Context paramContext, ActivityThread paramActivityThread) {
    super(paramContext);
    Objects.requireNonNull(paramActivityThread);
    this.mMainThread = paramActivityThread;
  }
  
  protected IContentProvider acquireExistingProvider(Context paramContext, String paramString) {
    return this.mMainThread.acquireExistingProvider(paramContext, ContentProvider.getAuthorityWithoutUserId(paramString), resolveUserIdFromAuthority(paramString), true);
  }
  
  protected IContentProvider acquireProvider(Context paramContext, String paramString) {
    return this.mMainThread.acquireProvider(paramContext, ContentProvider.getAuthorityWithoutUserId(paramString), resolveUserIdFromAuthority(paramString), true);
  }
  
  protected IContentProvider acquireUnstableProvider(Context paramContext, String paramString) {
    return this.mMainThread.acquireProvider(paramContext, ContentProvider.getAuthorityWithoutUserId(paramString), resolveUserIdFromAuthority(paramString), false);
  }
  
  public void appNotRespondingViaProvider(IContentProvider paramIContentProvider) {
    this.mMainThread.appNotRespondingViaProvider(paramIContentProvider.asBinder());
  }
  
  public boolean releaseProvider(IContentProvider paramIContentProvider) {
    return this.mMainThread.releaseProvider(paramIContentProvider, true);
  }
  
  public boolean releaseUnstableProvider(IContentProvider paramIContentProvider) {
    return this.mMainThread.releaseProvider(paramIContentProvider, false);
  }
  
  protected int resolveUserIdFromAuthority(String paramString) {
    return ContentProvider.getUserIdFromAuthority(paramString, getUserId());
  }
  
  public void unstableProviderDied(IContentProvider paramIContentProvider) {
    this.mMainThread.handleUnstableProviderDied(paramIContentProvider.asBinder(), true);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ContextImpl$ApplicationContentResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
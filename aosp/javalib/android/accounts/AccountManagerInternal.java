package android.accounts;

import android.os.RemoteCallback;

public abstract class AccountManagerInternal {
  public abstract void addOnAppPermissionChangeListener(OnAppPermissionChangeListener paramOnAppPermissionChangeListener);
  
  public abstract byte[] backupAccountAccessPermissions(int paramInt);
  
  public abstract boolean hasAccountAccess(Account paramAccount, int paramInt);
  
  public abstract void requestAccountAccess(Account paramAccount, String paramString, int paramInt, RemoteCallback paramRemoteCallback);
  
  public abstract void restoreAccountAccessPermissions(byte[] paramArrayOfbyte, int paramInt);
  
  public static interface OnAppPermissionChangeListener {
    void onAppPermissionChanged(Account param1Account, int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountManagerInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.accounts;

import android.graphics.drawable.Drawable;

class AuthInfo {
  final AuthenticatorDescription desc;
  
  final Drawable drawable;
  
  final String name;
  
  AuthInfo(AuthenticatorDescription paramAuthenticatorDescription, String paramString, Drawable paramDrawable) {
    this.desc = paramAuthenticatorDescription;
    this.name = paramString;
    this.drawable = paramDrawable;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/ChooseAccountTypeActivity$AuthInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
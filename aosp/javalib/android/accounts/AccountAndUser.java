package android.accounts;

public class AccountAndUser {
  public Account account;
  
  public int userId;
  
  public AccountAndUser(Account paramAccount, int paramInt) {
    this.account = paramAccount;
    this.userId = paramInt;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof AccountAndUser))
      return false; 
    paramObject = paramObject;
    if (!this.account.equals(((AccountAndUser)paramObject).account) || this.userId != ((AccountAndUser)paramObject).userId)
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    return this.account.hashCode() + this.userId;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.account.toString());
    stringBuilder.append(" u");
    stringBuilder.append(this.userId);
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AccountAndUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
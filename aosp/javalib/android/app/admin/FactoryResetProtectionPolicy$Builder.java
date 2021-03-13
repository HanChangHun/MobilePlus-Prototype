package android.app.admin;

import java.util.ArrayList;
import java.util.List;

public class Builder {
  private List<String> mFactoryResetProtectionAccounts;
  
  private boolean mFactoryResetProtectionEnabled = true;
  
  public FactoryResetProtectionPolicy build() {
    return new FactoryResetProtectionPolicy(this.mFactoryResetProtectionAccounts, this.mFactoryResetProtectionEnabled, null);
  }
  
  public Builder setFactoryResetProtectionAccounts(List<String> paramList) {
    this.mFactoryResetProtectionAccounts = new ArrayList<>(paramList);
    return this;
  }
  
  public Builder setFactoryResetProtectionEnabled(boolean paramBoolean) {
    this.mFactoryResetProtectionEnabled = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/FactoryResetProtectionPolicy$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
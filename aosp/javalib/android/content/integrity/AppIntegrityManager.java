package android.content.integrity;

import android.annotation.SystemApi;
import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.RemoteException;
import java.util.List;

@SystemApi
public class AppIntegrityManager {
  public static final String EXTRA_STATUS = "android.content.integrity.extra.STATUS";
  
  public static final int STATUS_FAILURE = 1;
  
  public static final int STATUS_SUCCESS = 0;
  
  IAppIntegrityManager mManager;
  
  public AppIntegrityManager(IAppIntegrityManager paramIAppIntegrityManager) {
    this.mManager = paramIAppIntegrityManager;
  }
  
  public RuleSet getCurrentRuleSet() {
    try {
      ParceledListSlice<Rule> parceledListSlice = this.mManager.getCurrentRules();
      String str = this.mManager.getCurrentRuleSetVersion();
      RuleSet.Builder builder = new RuleSet.Builder();
      this();
      return builder.setVersion(str).addRules(parceledListSlice.getList()).build();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public String getCurrentRuleSetProvider() {
    try {
      return this.mManager.getCurrentRuleSetProvider();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public String getCurrentRuleSetVersion() {
    try {
      return this.mManager.getCurrentRuleSetVersion();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public List<String> getWhitelistedRuleProviders() {
    try {
      return this.mManager.getWhitelistedRuleProviders();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public void updateRuleSet(RuleSet paramRuleSet, IntentSender paramIntentSender) {
    try {
      IAppIntegrityManager iAppIntegrityManager = this.mManager;
      String str = paramRuleSet.getVersion();
      ParceledListSlice<Rule> parceledListSlice = new ParceledListSlice();
      this(paramRuleSet.getRules());
      iAppIntegrityManager.updateRuleSet(str, parceledListSlice, paramIntentSender);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AppIntegrityManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
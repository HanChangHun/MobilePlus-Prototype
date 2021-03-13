package android.content.integrity;

import android.annotation.SystemApi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SystemApi
public class RuleSet {
  private final List<Rule> mRules;
  
  private final String mVersion;
  
  private RuleSet(String paramString, List<Rule> paramList) {
    this.mVersion = paramString;
    this.mRules = Collections.unmodifiableList(paramList);
  }
  
  public List<Rule> getRules() {
    return this.mRules;
  }
  
  public String getVersion() {
    return this.mVersion;
  }
  
  public static class Builder {
    private List<Rule> mRules = new ArrayList<>();
    
    private String mVersion;
    
    public Builder addRules(List<Rule> param1List) {
      this.mRules.addAll(param1List);
      return this;
    }
    
    public RuleSet build() {
      Objects.requireNonNull(this.mVersion);
      return new RuleSet(this.mVersion, this.mRules);
    }
    
    public Builder setVersion(String param1String) {
      this.mVersion = param1String;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/RuleSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
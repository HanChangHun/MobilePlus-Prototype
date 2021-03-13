package android.content.integrity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Builder {
  private List<Rule> mRules = new ArrayList<>();
  
  private String mVersion;
  
  public Builder addRules(List<Rule> paramList) {
    this.mRules.addAll(paramList);
    return this;
  }
  
  public RuleSet build() {
    Objects.requireNonNull(this.mVersion);
    return new RuleSet(this.mVersion, this.mRules, null);
  }
  
  public Builder setVersion(String paramString) {
    this.mVersion = paramString;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/RuleSet$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
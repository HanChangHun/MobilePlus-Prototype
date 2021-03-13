package android.app;

import android.content.LocusId;
import android.os.Bundle;
import java.util.Objects;

public final class Builder {
  private Bundle mExtras;
  
  private String mId;
  
  private LocusId mLocusId;
  
  public Builder(String paramString) {
    Objects.requireNonNull(paramString);
    this.mId = paramString;
  }
  
  public DirectAction build() {
    return new DirectAction(this.mId, this.mExtras, this.mLocusId);
  }
  
  public Builder setExtras(Bundle paramBundle) {
    this.mExtras = paramBundle;
    return this;
  }
  
  public Builder setLocusId(LocusId paramLocusId) {
    this.mLocusId = paramLocusId;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DirectAction$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
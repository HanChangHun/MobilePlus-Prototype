package android.app.contentsuggestions;

import android.annotation.SystemApi;
import android.os.Bundle;
import java.util.List;

@SystemApi
public final class Builder {
  private Bundle mExtras;
  
  private final List<ContentSelection> mSelections;
  
  public Builder(List<ContentSelection> paramList) {
    this.mSelections = paramList;
  }
  
  public ClassificationsRequest build() {
    return new ClassificationsRequest(this.mSelections, this.mExtras, null);
  }
  
  public Builder setExtras(Bundle paramBundle) {
    this.mExtras = paramBundle;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ClassificationsRequest$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
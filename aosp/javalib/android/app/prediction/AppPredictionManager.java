package android.app.prediction;

import android.annotation.SystemApi;
import android.content.Context;
import java.util.Objects;

@SystemApi
public final class AppPredictionManager {
  private final Context mContext;
  
  public AppPredictionManager(Context paramContext) {
    Objects.requireNonNull(paramContext);
    this.mContext = paramContext;
  }
  
  public AppPredictor createAppPredictionSession(AppPredictionContext paramAppPredictionContext) {
    return new AppPredictor(this.mContext, paramAppPredictionContext);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppPredictionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
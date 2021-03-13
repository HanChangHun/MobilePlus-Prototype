package android.content.res;

public class ConfigurationBoundResourceCache<T> extends ThemedResourceCache<ConstantState<T>> {
  public T getInstance(long paramLong, Resources paramResources, Resources.Theme paramTheme) {
    ConstantState<T> constantState = (ConstantState)get(paramLong, paramTheme);
    return (constantState != null) ? constantState.newInstance(paramResources, paramTheme) : null;
  }
  
  public boolean shouldInvalidateEntry(ConstantState<T> paramConstantState, int paramInt) {
    return Configuration.needNewResources(paramInt, paramConstantState.getChangingConfigurations());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ConfigurationBoundResourceCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
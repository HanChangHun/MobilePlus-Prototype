package android.content.res;

public abstract class ConstantState<T> {
  public abstract int getChangingConfigurations();
  
  public abstract T newInstance();
  
  public T newInstance(Resources paramResources) {
    return newInstance();
  }
  
  public T newInstance(Resources paramResources, Resources.Theme paramTheme) {
    return newInstance(paramResources);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ConstantState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
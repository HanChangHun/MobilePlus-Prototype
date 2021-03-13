package android.animation;

public abstract class TypeConverter<T, V> {
  private Class<T> mFromClass;
  
  private Class<V> mToClass;
  
  public TypeConverter(Class<T> paramClass, Class<V> paramClass1) {
    this.mFromClass = paramClass;
    this.mToClass = paramClass1;
  }
  
  public abstract V convert(T paramT);
  
  Class<T> getSourceType() {
    return this.mFromClass;
  }
  
  Class<V> getTargetType() {
    return this.mToClass;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/TypeConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.animation;

public abstract class BidirectionalTypeConverter<T, V> extends TypeConverter<T, V> {
  private BidirectionalTypeConverter mInvertedConverter;
  
  public BidirectionalTypeConverter(Class<T> paramClass, Class<V> paramClass1) {
    super(paramClass, paramClass1);
  }
  
  public abstract T convertBack(V paramV);
  
  public BidirectionalTypeConverter<V, T> invert() {
    if (this.mInvertedConverter == null)
      this.mInvertedConverter = new InvertedConverter<>(this); 
    return this.mInvertedConverter;
  }
  
  private static class InvertedConverter<From, To> extends BidirectionalTypeConverter<From, To> {
    private BidirectionalTypeConverter<To, From> mConverter;
    
    public InvertedConverter(BidirectionalTypeConverter<To, From> param1BidirectionalTypeConverter) {
      super(param1BidirectionalTypeConverter.getTargetType(), param1BidirectionalTypeConverter.getSourceType());
      this.mConverter = param1BidirectionalTypeConverter;
    }
    
    public To convert(From param1From) {
      return this.mConverter.convertBack(param1From);
    }
    
    public From convertBack(To param1To) {
      return this.mConverter.convert(param1To);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/BidirectionalTypeConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
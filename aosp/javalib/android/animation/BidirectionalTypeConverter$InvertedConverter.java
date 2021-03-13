package android.animation;

class InvertedConverter<From, To> extends BidirectionalTypeConverter<From, To> {
  private BidirectionalTypeConverter<To, From> mConverter;
  
  public InvertedConverter(BidirectionalTypeConverter<To, From> paramBidirectionalTypeConverter) {
    super(paramBidirectionalTypeConverter.getTargetType(), paramBidirectionalTypeConverter.getSourceType());
    this.mConverter = paramBidirectionalTypeConverter;
  }
  
  public To convert(From paramFrom) {
    return this.mConverter.convertBack(paramFrom);
  }
  
  public From convertBack(To paramTo) {
    return this.mConverter.convert(paramTo);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/BidirectionalTypeConverter$InvertedConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.graphics;

public class TransferParameters {
  public final double a;
  
  public final double b;
  
  public final double c;
  
  public final double d;
  
  public final double e;
  
  public final double f;
  
  public final double g;
  
  public TransferParameters(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5) {
    this(paramDouble1, paramDouble2, paramDouble3, paramDouble4, 0.0D, 0.0D, paramDouble5);
  }
  
  public TransferParameters(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7) {
    if (!Double.isNaN(paramDouble1) && !Double.isNaN(paramDouble2) && !Double.isNaN(paramDouble3) && !Double.isNaN(paramDouble4) && !Double.isNaN(paramDouble5) && !Double.isNaN(paramDouble6) && !Double.isNaN(paramDouble7)) {
      if (paramDouble4 >= 0.0D && paramDouble4 <= (Math.ulp(1.0F) + 1.0F)) {
        if (paramDouble4 != 0.0D || (paramDouble1 != 0.0D && paramDouble7 != 0.0D)) {
          if (paramDouble4 < 1.0D || paramDouble3 != 0.0D) {
            if ((paramDouble1 != 0.0D && paramDouble7 != 0.0D) || paramDouble3 != 0.0D) {
              if (paramDouble3 >= 0.0D) {
                if (paramDouble1 >= 0.0D && paramDouble7 >= 0.0D) {
                  this.a = paramDouble1;
                  this.b = paramDouble2;
                  this.c = paramDouble3;
                  this.d = paramDouble4;
                  this.e = paramDouble5;
                  this.f = paramDouble6;
                  this.g = paramDouble7;
                  return;
                } 
                throw new IllegalArgumentException("The transfer function must be positive or increasing");
              } 
              throw new IllegalArgumentException("The transfer function must be increasing");
            } 
            throw new IllegalArgumentException("Parameter a or g is zero, and c is zero, the transfer function is constant");
          } 
          throw new IllegalArgumentException("Parameter c is zero, the transfer function is constant");
        } 
        throw new IllegalArgumentException("Parameter a or g is zero, the transfer function is constant");
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Parameter d must be in the range [0..1], was ");
      stringBuilder.append(paramDouble4);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("Parameters cannot be NaN");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (Double.compare(((TransferParameters)paramObject).a, this.a) != 0)
      return false; 
    if (Double.compare(((TransferParameters)paramObject).b, this.b) != 0)
      return false; 
    if (Double.compare(((TransferParameters)paramObject).c, this.c) != 0)
      return false; 
    if (Double.compare(((TransferParameters)paramObject).d, this.d) != 0)
      return false; 
    if (Double.compare(((TransferParameters)paramObject).e, this.e) != 0)
      return false; 
    if (Double.compare(((TransferParameters)paramObject).f, this.f) != 0)
      return false; 
    if (Double.compare(((TransferParameters)paramObject).g, this.g) != 0)
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    long l = Double.doubleToLongBits(this.a);
    int i = (int)(l >>> 32L ^ l);
    l = Double.doubleToLongBits(this.b);
    int j = (int)(l >>> 32L ^ l);
    l = Double.doubleToLongBits(this.c);
    int k = (int)(l >>> 32L ^ l);
    l = Double.doubleToLongBits(this.d);
    int m = (int)(l >>> 32L ^ l);
    l = Double.doubleToLongBits(this.e);
    int n = (int)(l >>> 32L ^ l);
    l = Double.doubleToLongBits(this.f);
    int i1 = (int)(l >>> 32L ^ l);
    l = Double.doubleToLongBits(this.g);
    return (((((i * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + (int)(l >>> 32L ^ l);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Rgb$TransferParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
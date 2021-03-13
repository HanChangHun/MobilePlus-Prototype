package android.animation;

public class PropertyValues {
  public DataSource dataSource = null;
  
  public Object endValue;
  
  public String propertyName;
  
  public Object startValue;
  
  public Class type;
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("property name: ");
    stringBuilder.append(this.propertyName);
    stringBuilder.append(", type: ");
    stringBuilder.append(this.type);
    stringBuilder.append(", startValue: ");
    stringBuilder.append(this.startValue.toString());
    stringBuilder.append(", endValue: ");
    stringBuilder.append(this.endValue.toString());
    return stringBuilder.toString();
  }
  
  public static interface DataSource {
    Object getValueAtFraction(float param2Float);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PropertyValuesHolder$PropertyValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
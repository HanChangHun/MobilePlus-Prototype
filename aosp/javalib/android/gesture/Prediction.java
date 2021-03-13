package android.gesture;

public class Prediction {
  public final String name;
  
  public double score;
  
  Prediction(String paramString, double paramDouble) {
    this.name = paramString;
    this.score = paramDouble;
  }
  
  public String toString() {
    return this.name;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/Prediction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
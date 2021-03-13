package android.gesture;

import java.util.Comparator;

class null implements Comparator<Prediction> {
  public int compare(Prediction paramPrediction1, Prediction paramPrediction2) {
    double d1 = paramPrediction1.score;
    double d2 = paramPrediction2.score;
    return (d1 > d2) ? -1 : ((d1 < d2) ? 1 : 0);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/InstanceLearner$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
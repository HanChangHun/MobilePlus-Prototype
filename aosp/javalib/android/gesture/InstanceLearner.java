package android.gesture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

class InstanceLearner extends Learner {
  private static final Comparator<Prediction> sComparator = new Comparator<Prediction>() {
      public int compare(Prediction param1Prediction1, Prediction param1Prediction2) {
        double d1 = param1Prediction1.score;
        double d2 = param1Prediction2.score;
        return (d1 > d2) ? -1 : ((d1 < d2) ? 1 : 0);
      }
    };
  
  ArrayList<Prediction> classify(int paramInt1, int paramInt2, float[] paramArrayOffloat) {
    ArrayList<Prediction> arrayList = new ArrayList();
    ArrayList<Instance> arrayList1 = getInstances();
    int i = arrayList1.size();
    TreeMap<Object, Object> treeMap = new TreeMap<>();
    for (byte b = 0; b < i; b++) {
      Instance instance = arrayList1.get(b);
      if (instance.vector.length == paramArrayOffloat.length) {
        double d;
        if (paramInt1 == 2) {
          d = GestureUtils.minimumCosineDistance(instance.vector, paramArrayOffloat, paramInt2);
        } else {
          d = GestureUtils.squaredEuclideanDistance(instance.vector, paramArrayOffloat);
        } 
        if (d == 0.0D) {
          d = Double.MAX_VALUE;
        } else {
          d = 1.0D / d;
        } 
        Double double_ = (Double)treeMap.get(instance.label);
        if (double_ == null || d > double_.doubleValue())
          treeMap.put(instance.label, Double.valueOf(d)); 
      } 
    } 
    for (String str : treeMap.keySet())
      arrayList.add(new Prediction(str, ((Double)treeMap.get(str)).doubleValue())); 
    Collections.sort(arrayList, sComparator);
    return arrayList;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/InstanceLearner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
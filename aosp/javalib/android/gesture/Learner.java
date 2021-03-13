package android.gesture;

import java.util.ArrayList;

abstract class Learner {
  private final ArrayList<Instance> mInstances = new ArrayList<>();
  
  void addInstance(Instance paramInstance) {
    this.mInstances.add(paramInstance);
  }
  
  abstract ArrayList<Prediction> classify(int paramInt1, int paramInt2, float[] paramArrayOffloat);
  
  ArrayList<Instance> getInstances() {
    return this.mInstances;
  }
  
  void removeInstance(long paramLong) {
    ArrayList<Instance> arrayList = this.mInstances;
    int i = arrayList.size();
    for (byte b = 0; b < i; b++) {
      Instance instance = arrayList.get(b);
      if (paramLong == instance.id) {
        arrayList.remove(instance);
        return;
      } 
    } 
  }
  
  void removeInstances(String paramString) {
    ArrayList<Instance> arrayList1 = new ArrayList();
    ArrayList<Instance> arrayList2 = this.mInstances;
    int i = arrayList2.size();
    for (byte b = 0; b < i; b++) {
      Instance instance = arrayList2.get(b);
      if ((instance.label == null && paramString == null) || (instance.label != null && instance.label.equals(paramString)))
        arrayList1.add(instance); 
    } 
    arrayList2.removeAll(arrayList1);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/Learner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
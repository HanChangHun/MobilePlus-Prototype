package android.gesture;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GestureStore {
  private static final short FILE_FORMAT_VERSION = 1;
  
  public static final int ORIENTATION_INVARIANT = 1;
  
  public static final int ORIENTATION_SENSITIVE = 2;
  
  static final int ORIENTATION_SENSITIVE_4 = 4;
  
  static final int ORIENTATION_SENSITIVE_8 = 8;
  
  private static final boolean PROFILE_LOADING_SAVING = false;
  
  public static final int SEQUENCE_INVARIANT = 1;
  
  public static final int SEQUENCE_SENSITIVE = 2;
  
  private boolean mChanged = false;
  
  private Learner mClassifier = new InstanceLearner();
  
  private final HashMap<String, ArrayList<Gesture>> mNamedGestures = new HashMap<>();
  
  private int mOrientationStyle = 2;
  
  private int mSequenceType = 2;
  
  private void readFormatV1(DataInputStream paramDataInputStream) throws IOException {
    Learner learner = this.mClassifier;
    HashMap<String, ArrayList<Gesture>> hashMap = this.mNamedGestures;
    hashMap.clear();
    int i = paramDataInputStream.readInt();
    for (byte b = 0; b < i; b++) {
      String str = paramDataInputStream.readUTF();
      int j = paramDataInputStream.readInt();
      ArrayList<Gesture> arrayList = new ArrayList(j);
      for (byte b1 = 0; b1 < j; b1++) {
        Gesture gesture = Gesture.deserialize(paramDataInputStream);
        arrayList.add(gesture);
        learner.addInstance(Instance.createInstance(this.mSequenceType, this.mOrientationStyle, gesture, str));
      } 
      hashMap.put(str, arrayList);
    } 
  }
  
  public void addGesture(String paramString, Gesture paramGesture) {
    if (paramString == null || paramString.length() == 0)
      return; 
    ArrayList<Gesture> arrayList1 = this.mNamedGestures.get(paramString);
    ArrayList<Gesture> arrayList2 = arrayList1;
    if (arrayList1 == null) {
      arrayList2 = new ArrayList();
      this.mNamedGestures.put(paramString, arrayList2);
    } 
    arrayList2.add(paramGesture);
    this.mClassifier.addInstance(Instance.createInstance(this.mSequenceType, this.mOrientationStyle, paramGesture, paramString));
    this.mChanged = true;
  }
  
  public Set<String> getGestureEntries() {
    return this.mNamedGestures.keySet();
  }
  
  public ArrayList<Gesture> getGestures(String paramString) {
    ArrayList<? extends Gesture> arrayList = this.mNamedGestures.get(paramString);
    return (arrayList != null) ? new ArrayList<>(arrayList) : null;
  }
  
  Learner getLearner() {
    return this.mClassifier;
  }
  
  public int getOrientationStyle() {
    return this.mOrientationStyle;
  }
  
  public int getSequenceType() {
    return this.mSequenceType;
  }
  
  public boolean hasChanged() {
    return this.mChanged;
  }
  
  public void load(InputStream paramInputStream) throws IOException {
    load(paramInputStream, false);
  }
  
  public void load(InputStream paramInputStream, boolean paramBoolean) throws IOException {
    InputStream inputStream1 = null;
    InputStream inputStream2 = inputStream1;
    try {
      DataInputStream dataInputStream = new DataInputStream();
      inputStream2 = inputStream1;
      if (!(paramInputStream instanceof BufferedInputStream)) {
        inputStream2 = inputStream1;
        paramInputStream = new BufferedInputStream(paramInputStream, 32768);
      } 
      inputStream2 = inputStream1;
      this(paramInputStream);
      paramInputStream = dataInputStream;
      inputStream2 = paramInputStream;
      if (paramInputStream.readShort() == 1) {
        inputStream2 = paramInputStream;
        readFormatV1((DataInputStream)paramInputStream);
      } 
      return;
    } finally {
      if (paramBoolean)
        GestureUtils.closeStream(inputStream2); 
    } 
  }
  
  public ArrayList<Prediction> recognize(Gesture paramGesture) {
    Instance instance = Instance.createInstance(this.mSequenceType, this.mOrientationStyle, paramGesture, null);
    return this.mClassifier.classify(this.mSequenceType, this.mOrientationStyle, instance.vector);
  }
  
  public void removeEntry(String paramString) {
    this.mNamedGestures.remove(paramString);
    this.mClassifier.removeInstances(paramString);
    this.mChanged = true;
  }
  
  public void removeGesture(String paramString, Gesture paramGesture) {
    ArrayList arrayList = this.mNamedGestures.get(paramString);
    if (arrayList == null)
      return; 
    arrayList.remove(paramGesture);
    if (arrayList.isEmpty())
      this.mNamedGestures.remove(paramString); 
    this.mClassifier.removeInstance(paramGesture.getID());
    this.mChanged = true;
  }
  
  public void save(OutputStream paramOutputStream) throws IOException {
    save(paramOutputStream, false);
  }
  
  public void save(OutputStream paramOutputStream, boolean paramBoolean) throws IOException {
    OutputStream outputStream1 = null;
    OutputStream outputStream2 = outputStream1;
    try {
      HashMap<String, ArrayList<Gesture>> hashMap = this.mNamedGestures;
      outputStream2 = outputStream1;
      DataOutputStream dataOutputStream = new DataOutputStream();
      outputStream2 = outputStream1;
      if (!(paramOutputStream instanceof BufferedOutputStream)) {
        outputStream2 = outputStream1;
        paramOutputStream = new BufferedOutputStream(paramOutputStream, 32768);
      } 
      outputStream2 = outputStream1;
      this(paramOutputStream);
      paramOutputStream = dataOutputStream;
      outputStream2 = paramOutputStream;
      paramOutputStream.writeShort(1);
      outputStream2 = paramOutputStream;
      paramOutputStream.writeInt(hashMap.size());
      outputStream2 = paramOutputStream;
      Iterator<Map.Entry> iterator = hashMap.entrySet().iterator();
      while (true) {
        outputStream2 = paramOutputStream;
        if (iterator.hasNext()) {
          outputStream2 = paramOutputStream;
          Map.Entry entry = iterator.next();
          outputStream2 = paramOutputStream;
          String str = (String)entry.getKey();
          outputStream2 = paramOutputStream;
          ArrayList<Gesture> arrayList = (ArrayList)entry.getValue();
          outputStream2 = paramOutputStream;
          int i = arrayList.size();
          outputStream2 = paramOutputStream;
          paramOutputStream.writeUTF(str);
          outputStream2 = paramOutputStream;
          paramOutputStream.writeInt(i);
          for (byte b = 0; b < i; b++) {
            outputStream2 = paramOutputStream;
            ((Gesture)arrayList.get(b)).serialize((DataOutputStream)paramOutputStream);
          } 
          continue;
        } 
        outputStream2 = paramOutputStream;
        paramOutputStream.flush();
        outputStream2 = paramOutputStream;
        this.mChanged = false;
        return;
      } 
    } finally {
      if (paramBoolean)
        GestureUtils.closeStream(outputStream2); 
    } 
  }
  
  public void setOrientationStyle(int paramInt) {
    this.mOrientationStyle = paramInt;
  }
  
  public void setSequenceType(int paramInt) {
    this.mSequenceType = paramInt;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/GestureStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
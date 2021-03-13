package android.hardware.camera2.impl;

import android.hardware.camera2.CaptureResult;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FrameNumberTracker {
  private static final String TAG = "FrameNumberTracker";
  
  private long[] mCompletedFrameNumber = new long[3];
  
  private final TreeMap<Long, Integer> mFutureErrorMap = new TreeMap<>();
  
  private final HashMap<Long, List<CaptureResult>> mPartialResults = new HashMap<>();
  
  private final LinkedList<Long>[] mSkippedFrameNumbers = (LinkedList<Long>[])new LinkedList[3];
  
  private final LinkedList<Long>[] mSkippedOtherFrameNumbers = (LinkedList<Long>[])new LinkedList[3];
  
  public FrameNumberTracker() {
    for (byte b = 0; b < 3; b++) {
      this.mCompletedFrameNumber[b] = -1L;
      this.mSkippedOtherFrameNumbers[b] = new LinkedList<>();
      this.mSkippedFrameNumbers[b] = new LinkedList<>();
    } 
  }
  
  private void update() {
    Iterator<Map.Entry> iterator = this.mFutureErrorMap.entrySet().iterator();
    while (iterator.hasNext()) {
      Boolean bool1;
      Map.Entry entry = iterator.next();
      Long long_ = (Long)entry.getKey();
      int i = ((Integer)entry.getValue()).intValue();
      Boolean bool2 = Boolean.valueOf(false);
      long l = long_.longValue();
      long[] arrayOfLong = this.mCompletedFrameNumber;
      if (l == arrayOfLong[i] + 1L) {
        arrayOfLong[i] = long_.longValue();
        bool1 = Boolean.valueOf(true);
      } else if (!this.mSkippedFrameNumbers[i].isEmpty()) {
        bool1 = bool2;
        if (long_ == this.mSkippedFrameNumbers[i].element()) {
          this.mCompletedFrameNumber[i] = long_.longValue();
          this.mSkippedFrameNumbers[i].remove();
          bool1 = Boolean.valueOf(true);
        } 
      } else {
        byte b = 1;
        while (true) {
          bool1 = bool2;
          if (b < 3) {
            int j = (i + b) % 3;
            if (!this.mSkippedOtherFrameNumbers[j].isEmpty() && long_ == this.mSkippedOtherFrameNumbers[j].element()) {
              this.mCompletedFrameNumber[i] = long_.longValue();
              this.mSkippedOtherFrameNumbers[j].remove();
              bool1 = Boolean.valueOf(true);
              break;
            } 
            b++;
            continue;
          } 
          break;
        } 
      } 
      if (bool1.booleanValue())
        iterator.remove(); 
    } 
  }
  
  private void updateCompletedFrameNumber(long paramLong, int paramInt) throws IllegalArgumentException {
    long[] arrayOfLong = this.mCompletedFrameNumber;
    if (paramLong > arrayOfLong[paramInt]) {
      int i = (paramInt + 1) % 3;
      int j = (paramInt + 2) % 3;
      long l = Math.max(arrayOfLong[i], arrayOfLong[j]);
      if (paramLong < l) {
        if (!this.mSkippedFrameNumbers[paramInt].isEmpty()) {
          if (paramLong >= ((Long)this.mSkippedFrameNumbers[paramInt].element()).longValue()) {
            if (paramLong <= ((Long)this.mSkippedFrameNumbers[paramInt].element()).longValue()) {
              this.mSkippedFrameNumbers[paramInt].remove();
            } else {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("frame number ");
              stringBuilder1.append(paramLong);
              stringBuilder1.append(" comes out of order. Expecting ");
              stringBuilder1.append(this.mSkippedFrameNumbers[paramInt].element());
              throw new IllegalArgumentException(stringBuilder1.toString());
            } 
          } else {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("frame number ");
            stringBuilder1.append(paramLong);
            stringBuilder1.append(" is a repeat");
            throw new IllegalArgumentException(stringBuilder1.toString());
          } 
        } else {
          int n;
          int k = this.mSkippedOtherFrameNumbers[i].indexOf(Long.valueOf(paramLong));
          int m = this.mSkippedOtherFrameNumbers[j].indexOf(Long.valueOf(paramLong));
          byte b = 0;
          if (k != -1) {
            n = 1;
          } else {
            n = 0;
          } 
          if (m != -1)
            b = 1; 
          if ((n ^ b) != 0) {
            LinkedList<Long> linkedList1;
            LinkedList<Long> linkedList2;
            if (n) {
              linkedList2 = this.mSkippedOtherFrameNumbers[i];
              linkedList1 = this.mSkippedFrameNumbers[j];
              n = k;
            } else {
              linkedList2 = this.mSkippedOtherFrameNumbers[j];
              linkedList1 = this.mSkippedFrameNumbers[i];
              n = m;
            } 
            for (b = 0; b < n; b++)
              linkedList1.add(linkedList2.removeFirst()); 
            linkedList2.remove();
          } else {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("frame number ");
            stringBuilder1.append(paramLong);
            stringBuilder1.append(" is a repeat or invalid");
            throw new IllegalArgumentException(stringBuilder1.toString());
          } 
        } 
      } else {
        for (l = Math.max(l, this.mCompletedFrameNumber[paramInt]) + 1L; l < paramLong; l++)
          this.mSkippedOtherFrameNumbers[paramInt].add(Long.valueOf(l)); 
      } 
      this.mCompletedFrameNumber[paramInt] = paramLong;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("frame number ");
    stringBuilder.append(paramLong);
    stringBuilder.append(" is a repeat");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public long getCompletedFrameNumber() {
    return this.mCompletedFrameNumber[0];
  }
  
  public long getCompletedReprocessFrameNumber() {
    return this.mCompletedFrameNumber[1];
  }
  
  public long getCompletedZslStillFrameNumber() {
    return this.mCompletedFrameNumber[2];
  }
  
  public List<CaptureResult> popPartialResults(long paramLong) {
    return this.mPartialResults.remove(Long.valueOf(paramLong));
  }
  
  public void updateTracker(long paramLong, CaptureResult paramCaptureResult, boolean paramBoolean, int paramInt) {
    if (!paramBoolean) {
      updateTracker(paramLong, false, paramInt);
      return;
    } 
    if (paramCaptureResult == null)
      return; 
    List<CaptureResult> list1 = this.mPartialResults.get(Long.valueOf(paramLong));
    List<CaptureResult> list2 = list1;
    if (list1 == null) {
      list2 = new ArrayList();
      this.mPartialResults.put(Long.valueOf(paramLong), list2);
    } 
    list2.add(paramCaptureResult);
  }
  
  public void updateTracker(long paramLong, boolean paramBoolean, int paramInt) {
    if (paramBoolean) {
      this.mFutureErrorMap.put(Long.valueOf(paramLong), Integer.valueOf(paramInt));
    } else {
      try {
        updateCompletedFrameNumber(paramLong, paramInt);
      } catch (IllegalArgumentException illegalArgumentException) {
        Log.e("FrameNumberTracker", illegalArgumentException.getMessage());
      } 
    } 
    update();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/FrameNumberTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
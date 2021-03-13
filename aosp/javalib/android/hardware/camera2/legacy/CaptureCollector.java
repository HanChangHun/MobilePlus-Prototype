package android.hardware.camera2.legacy;

import android.util.Log;
import android.util.MutableLong;
import android.util.Pair;
import android.view.Surface;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CaptureCollector {
  private static final boolean DEBUG = false;
  
  private static final int FLAG_RECEIVED_ALL_JPEG = 3;
  
  private static final int FLAG_RECEIVED_ALL_PREVIEW = 12;
  
  private static final int FLAG_RECEIVED_JPEG = 1;
  
  private static final int FLAG_RECEIVED_JPEG_TS = 2;
  
  private static final int FLAG_RECEIVED_PREVIEW = 4;
  
  private static final int FLAG_RECEIVED_PREVIEW_TS = 8;
  
  private static final int MAX_JPEGS_IN_FLIGHT = 1;
  
  private static final String TAG = "CaptureCollector";
  
  private final TreeSet<CaptureHolder> mActiveRequests;
  
  private final ArrayList<CaptureHolder> mCompletedRequests = new ArrayList<>();
  
  private final CameraDeviceState mDeviceState;
  
  private int mInFlight = 0;
  
  private int mInFlightPreviews = 0;
  
  private final Condition mIsEmpty;
  
  private final ArrayDeque<CaptureHolder> mJpegCaptureQueue;
  
  private final ArrayDeque<CaptureHolder> mJpegProduceQueue;
  
  private final ReentrantLock mLock = new ReentrantLock();
  
  private final int mMaxInFlight;
  
  private final Condition mNotFull;
  
  private final ArrayDeque<CaptureHolder> mPreviewCaptureQueue;
  
  private final ArrayDeque<CaptureHolder> mPreviewProduceQueue;
  
  private final Condition mPreviewsEmpty;
  
  public CaptureCollector(int paramInt, CameraDeviceState paramCameraDeviceState) {
    this.mMaxInFlight = paramInt;
    this.mJpegCaptureQueue = new ArrayDeque<>(1);
    this.mJpegProduceQueue = new ArrayDeque<>(1);
    this.mPreviewCaptureQueue = new ArrayDeque<>(this.mMaxInFlight);
    this.mPreviewProduceQueue = new ArrayDeque<>(this.mMaxInFlight);
    this.mActiveRequests = new TreeSet<>();
    this.mIsEmpty = this.mLock.newCondition();
    this.mNotFull = this.mLock.newCondition();
    this.mPreviewsEmpty = this.mLock.newCondition();
    this.mDeviceState = paramCameraDeviceState;
  }
  
  private void onPreviewCompleted() {
    int i = this.mInFlightPreviews - 1;
    this.mInFlightPreviews = i;
    if (i >= 0) {
      if (i == 0)
        this.mPreviewsEmpty.signalAll(); 
      return;
    } 
    throw new IllegalStateException("More preview captures completed than requests queued.");
  }
  
  private void onRequestCompleted(CaptureHolder paramCaptureHolder) {
    paramCaptureHolder.mRequest;
    int i = this.mInFlight - 1;
    this.mInFlight = i;
    if (i >= 0) {
      this.mCompletedRequests.add(paramCaptureHolder);
      this.mActiveRequests.remove(paramCaptureHolder);
      this.mNotFull.signalAll();
      if (this.mInFlight == 0)
        this.mIsEmpty.signalAll(); 
      return;
    } 
    throw new IllegalStateException("More captures completed than requests queued.");
  }
  
  private boolean removeRequestIfCompleted(RequestHolder paramRequestHolder, MutableLong paramMutableLong) {
    byte b = 0;
    for (CaptureHolder captureHolder : this.mCompletedRequests) {
      if (captureHolder.mRequest.equals(paramRequestHolder)) {
        paramMutableLong.value = captureHolder.mTimestamp;
        this.mCompletedRequests.remove(b);
        return true;
      } 
      b++;
    } 
    return false;
  }
  
  public void failAll() {
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      while (true) {
        CaptureHolder captureHolder = this.mActiveRequests.pollFirst();
        if (captureHolder != null) {
          captureHolder.setPreviewFailed();
          captureHolder.setJpegFailed();
          continue;
        } 
        this.mPreviewCaptureQueue.clear();
        this.mPreviewProduceQueue.clear();
        this.mJpegCaptureQueue.clear();
        this.mJpegProduceQueue.clear();
        return;
      } 
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public void failNextJpeg() {
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      CaptureHolder captureHolder1 = this.mJpegCaptureQueue.peek();
      CaptureHolder captureHolder2 = this.mJpegProduceQueue.peek();
      if (captureHolder1 == null || (captureHolder2 != null && captureHolder1.compareTo(captureHolder2) > 0))
        captureHolder1 = captureHolder2; 
      if (captureHolder1 != null) {
        this.mJpegCaptureQueue.remove(captureHolder1);
        this.mJpegProduceQueue.remove(captureHolder1);
        this.mActiveRequests.remove(captureHolder1);
        captureHolder1.setJpegFailed();
      } 
      return;
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public void failNextPreview() {
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      CaptureHolder captureHolder1 = this.mPreviewCaptureQueue.peek();
      CaptureHolder captureHolder2 = this.mPreviewProduceQueue.peek();
      if (captureHolder1 != null && (captureHolder2 == null || captureHolder1.compareTo(captureHolder2) <= 0))
        captureHolder2 = captureHolder1; 
      if (captureHolder2 != null) {
        this.mPreviewCaptureQueue.remove(captureHolder2);
        this.mPreviewProduceQueue.remove(captureHolder2);
        this.mActiveRequests.remove(captureHolder2);
        captureHolder2.setPreviewFailed();
      } 
      return;
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public boolean hasPendingPreviewCaptures() {
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      boolean bool = this.mPreviewCaptureQueue.isEmpty();
      return bool ^ true;
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public RequestHolder jpegCaptured(long paramLong) {
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      CaptureHolder captureHolder = this.mJpegCaptureQueue.poll();
      if (captureHolder == null) {
        Log.w("CaptureCollector", "jpegCaptured called with no jpeg request on queue!");
        return null;
      } 
      captureHolder.setJpegTimestamp(paramLong);
      return captureHolder.mRequest;
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public Pair<RequestHolder, Long> jpegProduced() {
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      CaptureHolder captureHolder = this.mJpegProduceQueue.poll();
      if (captureHolder == null) {
        Log.w("CaptureCollector", "jpegProduced called with no jpeg request on queue!");
        return null;
      } 
      captureHolder.setJpegProduced();
      return new Pair(captureHolder.mRequest, Long.valueOf(captureHolder.mTimestamp));
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public Pair<RequestHolder, Long> previewCaptured(long paramLong) {
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      CaptureHolder captureHolder = this.mPreviewCaptureQueue.poll();
      if (captureHolder == null)
        return null; 
      captureHolder.setPreviewTimestamp(paramLong);
      return new Pair(captureHolder.mRequest, Long.valueOf(captureHolder.mTimestamp));
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public RequestHolder previewProduced() {
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      CaptureHolder captureHolder = this.mPreviewProduceQueue.poll();
      if (captureHolder == null) {
        Log.w("CaptureCollector", "previewProduced called with no preview request on queue!");
        return null;
      } 
      captureHolder.setPreviewProduced();
      return captureHolder.mRequest;
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public boolean queueRequest(RequestHolder paramRequestHolder, LegacyRequest paramLegacyRequest, long paramLong, TimeUnit paramTimeUnit) throws InterruptedException {
    null = new CaptureHolder(paramRequestHolder, paramLegacyRequest);
    long l = paramTimeUnit.toNanos(paramLong);
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      if (null.needsJpeg || null.needsPreview) {
        paramLong = l;
        if (null.needsJpeg) {
          paramLong = l;
          while (true) {
            int i = this.mInFlight;
            if (i > 0) {
              if (paramLong <= 0L)
                return false; 
              paramLong = this.mIsEmpty.awaitNanos(paramLong);
              continue;
            } 
            this.mJpegCaptureQueue.add(null);
            this.mJpegProduceQueue.add(null);
            break;
          } 
        } 
        if (null.needsPreview)
          while (true) {
            int j = this.mInFlight;
            int i = this.mMaxInFlight;
            if (j >= i) {
              if (paramLong <= 0L)
                return false; 
              paramLong = this.mNotFull.awaitNanos(paramLong);
              continue;
            } 
            this.mPreviewCaptureQueue.add(null);
            this.mPreviewProduceQueue.add(null);
            this.mInFlightPreviews++;
            break;
          }  
        this.mActiveRequests.add(null);
        this.mInFlight++;
        return true;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Request must target at least one output surface!");
      throw illegalStateException;
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public boolean waitForEmpty(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException {
    paramLong = paramTimeUnit.toNanos(paramLong);
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      while (true) {
        int i = this.mInFlight;
        if (i > 0) {
          if (paramLong <= 0L)
            return false; 
          paramLong = this.mIsEmpty.awaitNanos(paramLong);
          continue;
        } 
        return true;
      } 
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public boolean waitForPreviewsEmpty(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException {
    paramLong = paramTimeUnit.toNanos(paramLong);
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      while (true) {
        int i = this.mInFlightPreviews;
        if (i > 0) {
          if (paramLong <= 0L)
            return false; 
          paramLong = this.mPreviewsEmpty.awaitNanos(paramLong);
          continue;
        } 
        return true;
      } 
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  public boolean waitForRequestCompleted(RequestHolder paramRequestHolder, long paramLong, TimeUnit paramTimeUnit, MutableLong paramMutableLong) throws InterruptedException {
    paramLong = paramTimeUnit.toNanos(paramLong);
    ReentrantLock reentrantLock = this.mLock;
    reentrantLock.lock();
    try {
      while (true) {
        boolean bool = removeRequestIfCompleted(paramRequestHolder, paramMutableLong);
        if (!bool) {
          if (paramLong <= 0L)
            return false; 
          paramLong = this.mNotFull.awaitNanos(paramLong);
          continue;
        } 
        return true;
      } 
    } finally {
      reentrantLock.unlock();
    } 
  }
  
  private class CaptureHolder implements Comparable<CaptureHolder> {
    private boolean mCompleted = false;
    
    private boolean mFailedJpeg = false;
    
    private boolean mFailedPreview = false;
    
    private boolean mHasStarted = false;
    
    private final LegacyRequest mLegacy;
    
    private boolean mPreviewCompleted = false;
    
    private int mReceivedFlags = 0;
    
    private final RequestHolder mRequest;
    
    private long mTimestamp = 0L;
    
    public final boolean needsJpeg;
    
    public final boolean needsPreview;
    
    public CaptureHolder(RequestHolder param1RequestHolder, LegacyRequest param1LegacyRequest) {
      this.mRequest = param1RequestHolder;
      this.mLegacy = param1LegacyRequest;
      this.needsJpeg = param1RequestHolder.hasJpegTargets();
      this.needsPreview = param1RequestHolder.hasPreviewTargets();
    }
    
    public int compareTo(CaptureHolder param1CaptureHolder) {
      byte b;
      if (this.mRequest.getFrameNumber() > param1CaptureHolder.mRequest.getFrameNumber()) {
        b = 1;
      } else if (this.mRequest.getFrameNumber() == param1CaptureHolder.mRequest.getFrameNumber()) {
        b = 0;
      } else {
        b = -1;
      } 
      return b;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool;
      if (param1Object instanceof CaptureHolder && compareTo((CaptureHolder)param1Object) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isCompleted() {
      boolean bool;
      if (this.needsJpeg == isJpegCompleted() && this.needsPreview == isPreviewCompleted()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isJpegCompleted() {
      boolean bool;
      if ((this.mReceivedFlags & 0x3) == 3) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isPreviewCompleted() {
      boolean bool;
      if ((this.mReceivedFlags & 0xC) == 12) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void setJpegFailed() {
      if (!this.needsJpeg || isJpegCompleted())
        return; 
      this.mFailedJpeg = true;
      int i = 0x1 | this.mReceivedFlags;
      this.mReceivedFlags = i;
      this.mReceivedFlags = i | 0x2;
      tryComplete();
    }
    
    public void setJpegProduced() {
      if (this.needsJpeg) {
        if (!isCompleted()) {
          this.mReceivedFlags |= 0x1;
          tryComplete();
          return;
        } 
        throw new IllegalStateException("setJpegProduced called on already completed request.");
      } 
      throw new IllegalStateException("setJpegProduced called for capture with no jpeg targets.");
    }
    
    public void setJpegTimestamp(long param1Long) {
      if (this.needsJpeg) {
        if (!isCompleted()) {
          this.mReceivedFlags |= 0x2;
          if (this.mTimestamp == 0L)
            this.mTimestamp = param1Long; 
          if (!this.mHasStarted) {
            this.mHasStarted = true;
            CaptureCollector.this.mDeviceState.setCaptureStart(this.mRequest, this.mTimestamp, -1);
          } 
          tryComplete();
          return;
        } 
        throw new IllegalStateException("setJpegTimestamp called on already completed request.");
      } 
      throw new IllegalStateException("setJpegTimestamp called for capture with no jpeg targets.");
    }
    
    public void setPreviewFailed() {
      if (!this.needsPreview || isPreviewCompleted())
        return; 
      this.mFailedPreview = true;
      int i = this.mReceivedFlags | 0x4;
      this.mReceivedFlags = i;
      this.mReceivedFlags = i | 0x8;
      tryComplete();
    }
    
    public void setPreviewProduced() {
      if (this.needsPreview) {
        if (!isCompleted()) {
          this.mReceivedFlags |= 0x4;
          tryComplete();
          return;
        } 
        throw new IllegalStateException("setPreviewProduced called on already completed request.");
      } 
      throw new IllegalStateException("setPreviewProduced called for capture with no preview targets.");
    }
    
    public void setPreviewTimestamp(long param1Long) {
      if (this.needsPreview) {
        if (!isCompleted()) {
          this.mReceivedFlags |= 0x8;
          if (this.mTimestamp == 0L)
            this.mTimestamp = param1Long; 
          if (!this.needsJpeg && !this.mHasStarted) {
            this.mHasStarted = true;
            CaptureCollector.this.mDeviceState.setCaptureStart(this.mRequest, this.mTimestamp, -1);
          } 
          tryComplete();
          return;
        } 
        throw new IllegalStateException("setPreviewTimestamp called on already completed request.");
      } 
      throw new IllegalStateException("setPreviewTimestamp called for capture with no preview targets.");
    }
    
    public void tryComplete() {
      if (!this.mPreviewCompleted && this.needsPreview && isPreviewCompleted()) {
        CaptureCollector.this.onPreviewCompleted();
        this.mPreviewCompleted = true;
      } 
      if (isCompleted() && !this.mCompleted) {
        if (this.mFailedPreview || this.mFailedJpeg)
          if (!this.mHasStarted) {
            this.mRequest.failRequest();
            CaptureCollector.this.mDeviceState.setCaptureStart(this.mRequest, this.mTimestamp, 3);
          } else {
            for (Surface surface : this.mRequest.getRequest().getTargets()) {
              try {
                if (this.mRequest.jpegType(surface)) {
                  if (this.mFailedJpeg)
                    CaptureCollector.this.mDeviceState.setCaptureResult(this.mRequest, null, 5, surface); 
                  continue;
                } 
                if (this.mFailedPreview)
                  CaptureCollector.this.mDeviceState.setCaptureResult(this.mRequest, null, 5, surface); 
              } catch (BufferQueueAbandonedException bufferQueueAbandonedException) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected exception when querying Surface: ");
                stringBuilder.append(bufferQueueAbandonedException);
                Log.e("CaptureCollector", stringBuilder.toString());
              } 
            } 
          }  
        CaptureCollector.this.onRequestCompleted(this);
        this.mCompleted = true;
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/CaptureCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
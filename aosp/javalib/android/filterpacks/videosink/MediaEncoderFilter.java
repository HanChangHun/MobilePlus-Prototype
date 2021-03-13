package android.filterpacks.videosink;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GLEnvironment;
import android.filterfw.core.GLFrame;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;

public class MediaEncoderFilter extends Filter {
  private static final int NO_AUDIO_SOURCE = -1;
  
  private static final String TAG = "MediaEncoderFilter";
  
  @GenerateFieldPort(hasDefault = true, name = "audioSource")
  private int mAudioSource = -1;
  
  private boolean mCaptureTimeLapse = false;
  
  @GenerateFieldPort(hasDefault = true, name = "errorListener")
  private MediaRecorder.OnErrorListener mErrorListener = null;
  
  @GenerateFieldPort(hasDefault = true, name = "outputFileDescriptor")
  private FileDescriptor mFd = null;
  
  @GenerateFieldPort(hasDefault = true, name = "framerate")
  private int mFps = 30;
  
  @GenerateFieldPort(hasDefault = true, name = "height")
  private int mHeight = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "infoListener")
  private MediaRecorder.OnInfoListener mInfoListener = null;
  
  private long mLastTimeLapseFrameRealTimestampNs = 0L;
  
  private boolean mLogVerbose = Log.isLoggable("MediaEncoderFilter", 2);
  
  @GenerateFieldPort(hasDefault = true, name = "maxDurationMs")
  private int mMaxDurationMs = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "maxFileSize")
  private long mMaxFileSize = 0L;
  
  private MediaRecorder mMediaRecorder;
  
  private int mNumFramesEncoded = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "orientationHint")
  private int mOrientationHint = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "outputFile")
  private String mOutputFile = new String("/sdcard/MediaEncoderOut.mp4");
  
  @GenerateFieldPort(hasDefault = true, name = "outputFormat")
  private int mOutputFormat = 2;
  
  @GenerateFieldPort(hasDefault = true, name = "recordingProfile")
  private CamcorderProfile mProfile = null;
  
  private ShaderProgram mProgram;
  
  @GenerateFieldPort(hasDefault = true, name = "recording")
  private boolean mRecording = true;
  
  private boolean mRecordingActive = false;
  
  @GenerateFieldPort(hasDefault = true, name = "recordingDoneListener")
  private OnRecordingDoneListener mRecordingDoneListener = null;
  
  private GLFrame mScreen;
  
  @GenerateFieldPort(hasDefault = true, name = "inputRegion")
  private Quad mSourceRegion = new Quad(new Point(0.0F, 0.0F), new Point(1.0F, 0.0F), new Point(0.0F, 1.0F), new Point(1.0F, 1.0F));
  
  private int mSurfaceId;
  
  @GenerateFieldPort(hasDefault = true, name = "timelapseRecordingIntervalUs")
  private long mTimeBetweenTimeLapseFrameCaptureUs = 0L;
  
  private long mTimestampNs = 0L;
  
  @GenerateFieldPort(hasDefault = true, name = "videoEncoder")
  private int mVideoEncoder = 2;
  
  @GenerateFieldPort(hasDefault = true, name = "width")
  private int mWidth = 0;
  
  public MediaEncoderFilter(String paramString) {
    super(paramString);
  }
  
  private void startRecording(FilterContext paramFilterContext) {
    int i;
    int j;
    if (this.mLogVerbose)
      Log.v("MediaEncoderFilter", "Starting recording"); 
    MutableFrameFormat mutableFrameFormat = new MutableFrameFormat(2, 3);
    mutableFrameFormat.setBytesPerSample(4);
    if (this.mWidth > 0 && this.mHeight > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    CamcorderProfile camcorderProfile = this.mProfile;
    if (camcorderProfile != null && !i) {
      j = camcorderProfile.videoFrameWidth;
      i = this.mProfile.videoFrameHeight;
    } else {
      j = this.mWidth;
      i = this.mHeight;
    } 
    mutableFrameFormat.setDimensions(j, i);
    this.mScreen = (GLFrame)paramFilterContext.getFrameManager().newBoundFrame((FrameFormat)mutableFrameFormat, 101, 0L);
    this.mMediaRecorder = new MediaRecorder();
    updateMediaRecorderParams();
    try {
      this.mMediaRecorder.prepare();
      this.mMediaRecorder.start();
      if (this.mLogVerbose)
        Log.v("MediaEncoderFilter", "Open: registering surface from Mediarecorder"); 
      this.mSurfaceId = paramFilterContext.getGLEnvironment().registerSurfaceFromMediaRecorder(this.mMediaRecorder);
      this.mNumFramesEncoded = 0;
      this.mRecordingActive = true;
      return;
    } catch (IllegalStateException illegalStateException) {
      throw illegalStateException;
    } catch (IOException iOException) {
      throw new RuntimeException("IOException inMediaRecorder.prepare()!", iOException);
    } catch (Exception exception) {
      throw new RuntimeException("Unknown Exception inMediaRecorder.prepare()!", exception);
    } 
  }
  
  private void stopRecording(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("MediaEncoderFilter", "Stopping recording"); 
    this.mRecordingActive = false;
    this.mNumFramesEncoded = 0;
    GLEnvironment gLEnvironment = paramFilterContext.getGLEnvironment();
    if (this.mLogVerbose)
      Log.v("MediaEncoderFilter", String.format("Unregistering surface %d", new Object[] { Integer.valueOf(this.mSurfaceId) })); 
    gLEnvironment.unregisterSurfaceId(this.mSurfaceId);
    try {
      this.mMediaRecorder.stop();
      this.mMediaRecorder.release();
      this.mMediaRecorder = null;
      this.mScreen.release();
      this.mScreen = null;
      OnRecordingDoneListener onRecordingDoneListener = this.mRecordingDoneListener;
      if (onRecordingDoneListener != null)
        onRecordingDoneListener.onRecordingDone(); 
      return;
    } catch (RuntimeException runtimeException) {
      throw new MediaRecorderStopException("MediaRecorder.stop() failed!", runtimeException);
    } 
  }
  
  private void updateMediaRecorderParams() {
    boolean bool;
    if (this.mTimeBetweenTimeLapseFrameCaptureUs > 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mCaptureTimeLapse = bool;
    this.mMediaRecorder.setVideoSource(2);
    if (!this.mCaptureTimeLapse) {
      int i = this.mAudioSource;
      if (i != -1)
        this.mMediaRecorder.setAudioSource(i); 
    } 
    CamcorderProfile camcorderProfile = this.mProfile;
    if (camcorderProfile != null) {
      this.mMediaRecorder.setProfile(camcorderProfile);
      this.mFps = this.mProfile.videoFrameRate;
      int i = this.mWidth;
      if (i > 0) {
        int j = this.mHeight;
        if (j > 0)
          this.mMediaRecorder.setVideoSize(i, j); 
      } 
    } else {
      this.mMediaRecorder.setOutputFormat(this.mOutputFormat);
      this.mMediaRecorder.setVideoEncoder(this.mVideoEncoder);
      this.mMediaRecorder.setVideoSize(this.mWidth, this.mHeight);
      this.mMediaRecorder.setVideoFrameRate(this.mFps);
    } 
    this.mMediaRecorder.setOrientationHint(this.mOrientationHint);
    this.mMediaRecorder.setOnInfoListener(this.mInfoListener);
    this.mMediaRecorder.setOnErrorListener(this.mErrorListener);
    FileDescriptor fileDescriptor = this.mFd;
    if (fileDescriptor != null) {
      this.mMediaRecorder.setOutputFile(fileDescriptor);
    } else {
      this.mMediaRecorder.setOutputFile(this.mOutputFile);
    } 
    try {
      this.mMediaRecorder.setMaxFileSize(this.mMaxFileSize);
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Setting maxFileSize on MediaRecorder unsuccessful! ");
      stringBuilder.append(exception.getMessage());
      Log.w("MediaEncoderFilter", stringBuilder.toString());
    } 
    this.mMediaRecorder.setMaxDuration(this.mMaxDurationMs);
  }
  
  private void updateSourceRegion() {
    Quad quad = new Quad();
    quad.p0 = this.mSourceRegion.p2;
    quad.p1 = this.mSourceRegion.p3;
    quad.p2 = this.mSourceRegion.p0;
    quad.p3 = this.mSourceRegion.p1;
    this.mProgram.setSourceRegion(quad);
  }
  
  public void close(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("MediaEncoderFilter", "Closing"); 
    if (this.mRecordingActive)
      stopRecording(paramFilterContext); 
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Port ");
      stringBuilder.append(paramString);
      stringBuilder.append(" has been updated");
      Log.v("MediaEncoderFilter", stringBuilder.toString());
    } 
    if (paramString.equals("recording"))
      return; 
    if (paramString.equals("inputRegion")) {
      if (isOpen())
        updateSourceRegion(); 
      return;
    } 
    if (!isOpen() || !this.mRecordingActive)
      return; 
    throw new RuntimeException("Cannot change recording parameters when the filter is recording!");
  }
  
  public void open(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("MediaEncoderFilter", "Opening"); 
    updateSourceRegion();
    if (this.mRecording)
      startRecording(paramFilterContext); 
  }
  
  public void prepare(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("MediaEncoderFilter", "Preparing"); 
    this.mProgram = ShaderProgram.createIdentity(paramFilterContext);
    this.mRecordingActive = false;
  }
  
  public void process(FilterContext paramFilterContext) {
    GLEnvironment gLEnvironment = paramFilterContext.getGLEnvironment();
    Frame frame = pullInput("videoframe");
    if (!this.mRecordingActive && this.mRecording)
      startRecording(paramFilterContext); 
    if (this.mRecordingActive && !this.mRecording)
      stopRecording(paramFilterContext); 
    if (!this.mRecordingActive)
      return; 
    if (this.mCaptureTimeLapse) {
      if (skipFrameAndModifyTimestamp(frame.getTimestamp()))
        return; 
    } else {
      this.mTimestampNs = frame.getTimestamp();
    } 
    gLEnvironment.activateSurfaceWithId(this.mSurfaceId);
    this.mProgram.process(frame, (Frame)this.mScreen);
    gLEnvironment.setSurfaceTimestamp(this.mTimestampNs);
    gLEnvironment.swapBuffers();
    this.mNumFramesEncoded++;
  }
  
  public void setupPorts() {
    addMaskedInputPort("videoframe", (FrameFormat)ImageFormat.create(3, 3));
  }
  
  public boolean skipFrameAndModifyTimestamp(long paramLong) {
    int i = this.mNumFramesEncoded;
    if (i == 0) {
      this.mLastTimeLapseFrameRealTimestampNs = paramLong;
      this.mTimestampNs = paramLong;
      if (this.mLogVerbose) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("timelapse: FIRST frame, last real t= ");
        stringBuilder.append(this.mLastTimeLapseFrameRealTimestampNs);
        stringBuilder.append(", setting t = ");
        stringBuilder.append(this.mTimestampNs);
        Log.v("MediaEncoderFilter", stringBuilder.toString());
      } 
      return false;
    } 
    if (i >= 2 && paramLong < this.mLastTimeLapseFrameRealTimestampNs + this.mTimeBetweenTimeLapseFrameCaptureUs * 1000L) {
      if (this.mLogVerbose)
        Log.v("MediaEncoderFilter", "timelapse: skipping intermediate frame"); 
      return true;
    } 
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("timelapse: encoding frame, Timestamp t = ");
      stringBuilder.append(paramLong);
      stringBuilder.append(", last real t= ");
      stringBuilder.append(this.mLastTimeLapseFrameRealTimestampNs);
      stringBuilder.append(", interval = ");
      stringBuilder.append(this.mTimeBetweenTimeLapseFrameCaptureUs);
      Log.v("MediaEncoderFilter", stringBuilder.toString());
    } 
    this.mLastTimeLapseFrameRealTimestampNs = paramLong;
    this.mTimestampNs += 1000000000L / this.mFps;
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("timelapse: encoding frame, setting t = ");
      stringBuilder.append(this.mTimestampNs);
      stringBuilder.append(", delta t = ");
      stringBuilder.append(1000000000L / this.mFps);
      stringBuilder.append(", fps = ");
      stringBuilder.append(this.mFps);
      Log.v("MediaEncoderFilter", stringBuilder.toString());
    } 
    return false;
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    MediaRecorder mediaRecorder = this.mMediaRecorder;
    if (mediaRecorder != null)
      mediaRecorder.release(); 
    GLFrame gLFrame = this.mScreen;
    if (gLFrame != null)
      gLFrame.release(); 
  }
  
  public static interface OnRecordingDoneListener {
    void onRecordingDone();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosink/MediaEncoderFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
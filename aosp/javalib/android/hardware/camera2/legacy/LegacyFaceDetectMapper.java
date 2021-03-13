package android.hardware.camera2.legacy;

import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.utils.ParamsUtils;
import android.util.Log;
import android.util.Size;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;

public class LegacyFaceDetectMapper {
  private static final boolean DEBUG = false;
  
  private static String TAG = "LegacyFaceDetectMapper";
  
  private final Camera mCamera;
  
  private boolean mFaceDetectEnabled = false;
  
  private boolean mFaceDetectReporting = false;
  
  private boolean mFaceDetectScenePriority = false;
  
  private final boolean mFaceDetectSupported;
  
  private Camera.Face[] mFaces;
  
  private Camera.Face[] mFacesPrev;
  
  private final Object mLock = new Object();
  
  public LegacyFaceDetectMapper(Camera paramCamera, CameraCharacteristics paramCameraCharacteristics) {
    this.mCamera = (Camera)Preconditions.checkNotNull(paramCamera, "camera must not be null");
    Preconditions.checkNotNull(paramCameraCharacteristics, "characteristics must not be null");
    boolean bool = ArrayUtils.contains((int[])paramCameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES), 1);
    this.mFaceDetectSupported = bool;
    if (!bool)
      return; 
    this.mCamera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
          public void onFaceDetection(Camera.Face[] param1ArrayOfFace, Camera param1Camera) {
            int i;
            if (param1ArrayOfFace == null) {
              i = 0;
            } else {
              i = param1ArrayOfFace.length;
            } 
            synchronized (LegacyFaceDetectMapper.this.mLock) {
              if (LegacyFaceDetectMapper.this.mFaceDetectEnabled) {
                LegacyFaceDetectMapper.access$202(LegacyFaceDetectMapper.this, param1ArrayOfFace);
              } else if (i > 0) {
                Log.d(LegacyFaceDetectMapper.TAG, "onFaceDetection - Ignored some incoming faces sinceface detection was disabled");
              } 
              return;
            } 
          }
        });
  }
  
  public void mapResultFaces(CameraMetadataNative paramCameraMetadataNative, LegacyRequest paramLegacyRequest) {
    Preconditions.checkNotNull(paramCameraMetadataNative, "result must not be null");
    Preconditions.checkNotNull(paramLegacyRequest, "legacyRequest must not be null");
    synchronized (this.mLock) {
      boolean bool;
      Camera.Face[] arrayOfFace1;
      if (this.mFaceDetectReporting) {
        bool = true;
      } else {
        bool = false;
      } 
      if (this.mFaceDetectReporting) {
        arrayOfFace1 = this.mFaces;
      } else {
        arrayOfFace1 = null;
      } 
      boolean bool1 = this.mFaceDetectScenePriority;
      Camera.Face[] arrayOfFace2 = this.mFacesPrev;
      this.mFacesPrev = arrayOfFace1;
      CameraCharacteristics cameraCharacteristics = paramLegacyRequest.characteristics;
      null = paramLegacyRequest.captureRequest;
      Size size = paramLegacyRequest.previewSize;
      Camera.Parameters parameters = paramLegacyRequest.parameters;
      Rect rect = (Rect)cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
      ParameterUtils.ZoomData zoomData = ParameterUtils.convertToLegacyZoom(rect, (Rect)null.get(CaptureRequest.SCALER_CROP_REGION), (Float)null.get(CaptureRequest.CONTROL_ZOOM_RATIO), size, parameters);
      ArrayList<Face> arrayList = new ArrayList();
      if (arrayOfFace1 != null) {
        int i = arrayOfFace1.length;
        for (byte b = 0; b < i; b++) {
          null = arrayOfFace1[b];
          if (null != null) {
            arrayList.add(ParameterUtils.convertFaceFromLegacy((Camera.Face)null, rect, zoomData));
          } else {
            Log.w(TAG, "mapResultFaces - read NULL face from camera1 device");
          } 
        } 
      } 
      paramCameraMetadataNative.set(CaptureResult.STATISTICS_FACES, arrayList.<Face>toArray(new Face[0]));
      paramCameraMetadataNative.set(CaptureResult.STATISTICS_FACE_DETECT_MODE, Integer.valueOf(bool));
      if (bool1)
        paramCameraMetadataNative.set(CaptureResult.CONTROL_SCENE_MODE, Integer.valueOf(1)); 
      return;
    } 
  }
  
  public void processFaceDetectMode(CaptureRequest paramCaptureRequest, Camera.Parameters paramParameters) {
    boolean bool1;
    Preconditions.checkNotNull(paramCaptureRequest, "captureRequest must not be null");
    CaptureRequest.Key key = CaptureRequest.STATISTICS_FACE_DETECT_MODE;
    boolean bool = false;
    Integer integer = Integer.valueOf(0);
    int i = ((Integer)ParamsUtils.getOrDefault(paramCaptureRequest, key, integer)).intValue();
    if (i != 0 && !this.mFaceDetectSupported) {
      Log.w(TAG, "processFaceDetectMode - Ignoring statistics.faceDetectMode; face detection is not available");
      return;
    } 
    key = CaptureRequest.CONTROL_SCENE_MODE;
    int j = ((Integer)ParamsUtils.getOrDefault(paramCaptureRequest, key, integer)).intValue();
    if (j == 1 && !this.mFaceDetectSupported) {
      Log.w(TAG, "processFaceDetectMode - ignoring control.sceneMode == FACE_PRIORITY; face detection is not available");
      return;
    } 
    if (i != 0 && i != 1) {
      if (i != 2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processFaceDetectMode - ignoring unknown statistics.faceDetectMode = ");
        stringBuilder.append(i);
        Log.w(str, stringBuilder.toString());
        return;
      } 
      Log.w(TAG, "processFaceDetectMode - statistics.faceDetectMode == FULL unsupported, downgrading to SIMPLE");
    } 
    if (i != 0 || j == 1) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    synchronized (this.mLock) {
      if (bool1 != this.mFaceDetectEnabled) {
        if (bool1) {
          this.mCamera.startFaceDetection();
        } else {
          this.mCamera.stopFaceDetection();
          this.mFaces = null;
        } 
        this.mFaceDetectEnabled = bool1;
        if (j == 1) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        this.mFaceDetectScenePriority = bool1;
        bool1 = bool;
        if (i != 0)
          bool1 = true; 
        this.mFaceDetectReporting = bool1;
      } 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyFaceDetectMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
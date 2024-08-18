package demo.stpl.com.tpsmergedbuild.baseClass;


import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import java.io.FileOutputStream;
import java.io.IOException;

import demo.stpl.com.tpsmergedbuild.activity.ClaimWinningActivity;

//import tpsgames.activity.ClaimWinningActivity;


public class Preview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "Preview";

    ClaimWinningActivity activity;
    SurfaceHolder mHolder;
    public Camera camera;
    //    private Button scanButton;
    private ImageScanner scanner;

    public Preview(Activity context) {
        super(context);

        if (context instanceof ClaimWinningActivity)
            this.activity = (ClaimWinningActivity) context;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        autoFocusHandler = new Handler();
        mHolder = getHolder();
        // Instance barcode scanner
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mHolder.setFormat(PixelFormat.TRANSLUCENT | WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

//        mHolder.setFixedSize(context.getWindow().getWindowManager().getDefaultDisplay().getWidth()/10, context.getWindow().getWindowManager().getDefaultDisplay().getHeight()/10);

    }


    private Camera openFrontFacingCameraGingerbread() {
        int cameraCount = 0;
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    cam = Camera.open(camIdx);
                } catch (RuntimeException e) {
                    Log.e("", "Camera failed to open: " + e.getLocalizedMessage());
                }
            }
        }

        return cam;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, acquire the camera and tell it where
        // to draw.
        if (Build.MODEL.equalsIgnoreCase("SM-T231")) {
            camera = openFrontFacingCameraGingerbread();
        } else {
            camera = Camera.open();
        }

        try {
            if (camera == null) {
                this.activity.removeView();
                return;
            }

            camera.setPreviewDisplay(mHolder);


            camera.setPreviewCallback(new PreviewCallback() {

                public void onPreviewFrame(byte[] data, Camera arg1) {
                    FileOutputStream outStream = null;
                    try {
                        Camera.Parameters parameters = camera.getParameters();
                        Camera.Size size = parameters.getPreviewSize();
                        Image barcode = new Image(size.width, size.height, "Y800");
                        barcode.setData(data);

                        int result = scanner.scanImage(barcode);
                        if (result != 0) {
                            previewing = false;
//                            camera.setPreviewCallback(null);
//                            camera.stopPreview();
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SymbolSet syms = scanner.getResults();
                                    for (Symbol sym : syms) {
                                        ((ClaimWinningActivity) activity).setTextValue(sym.getData().trim());
                                    }

                                }
                            });


                        }
                        Log.e("", "");
//                        outStream = new FileOutputStream(String.format("/sdcard/%d.jpg", System.currentTimeMillis()));
//                        outStream.write(data);
//                        outStream.close();
//                        Camera.Parameters parameters = camera.getParameters();
//                        int width1 = parameters.getPreviewSize().width;
//                        int height1 = parameters.getPreviewSize().height;
//
//                        YuvImage yuv = new YuvImage(data, parameters.getPreviewFormat(), width1, height1, null);
//
//                        ByteArrayOutputStream out = new ByteArrayOutputStream();
//                        int width = yuv.getWidth();
//                        int height = yuv.getHeight();
//                        int left;
//                        if (width > height) {
//                            left = (width - height) / 2;
//                        } else {
//                            left = (height - width) / 2;
//                        }
//                        yuv.compressToJpeg(new Rect(left, 151, height, (height/5)-40), 100, out);
//
//                        byte[] bytes = out.toByteArray();
//                        BitmapFactory.Options options = new BitmapFactory.Options();
//                        options.inSampleSize = 6;
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,options);
                        Log.e("", "");
//                        int width = bitmap.getWidth();
//                        int height = bitmap.getHeight();
//                        int left;
//                        if (width > height) {
//                            left = (width - height) / 2;
//                        } else {
//                            left = (height - width) / 2;
//                        }
//                        bitmap = Bitmap.createBitmap(bitmap, left, 151, height, (height / 4));
//                        if (bitmap != null && previewing) {
//                            ocrScan(bitmap);
//                        }
//                        Camera.Parameters parameters = camera.getParameters();
//                        int width = parameters.getPreviewSize().width;
//                        int height = parameters.getPreviewSize().height;
//
//                        YuvImage yuv = new YuvImage(data, parameters.getPreviewFormat(), width, height, null);
//
//                        ByteArrayOutputStream out = new ByteArrayOutputStream();
//                        yuv.compressToJpeg(new Rect(0, 0, width, height), 50, out);
//
//                        byte[] bytes = out.toByteArray();
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                        if (bitmap != null && previewing) {
//                            ocrScan(bitmap);
//                        }
                        Log.d(TAG, "onPreviewFrame - wrote bytes: " + data.length);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                    Preview.this.invalidate();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlertDialog(final String message) {

        new AlertDialog.Builder(activity)
                .setTitle("QR code detected!")
                .setCancelable(false)
                .setMessage("Do you want to continue?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        })

                .show();
    }

    public void releaseCamera() {
        if (camera != null) {
            previewing = false;
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface will be destroyed when we return, so stop the preview.
        // Because the CameraDevice object is not a shared resource, it's very
        // important to release it when the activity is paused.
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    public boolean previewing = true;

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        Camera.Parameters parameters = camera.getParameters();
//        parameters.setPreviewSize(w/2, h/2);
        camera.setParameters(parameters);
        camera.startPreview();
        camera.autoFocus(autoFocusCB);
    }

    private Handler autoFocusHandler;

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing && camera != null)
                camera.autoFocus(autoFocusCB);
        }
    };


    // Mimic continuous auto-focusing
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint p = new Paint(Color.RED);
        Log.d(TAG, "draw");
        canvas.drawText("PREVIEW", canvas.getWidth() / 2, canvas.getHeight() / 2, p);
    }

    private synchronized void ocrScan(Bitmap bitmap) {

        new AsyncTask<Bitmap, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                previewing = false;
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                String recognizedText = "something went wrong";
                try {
//                    String LANG = "eng";
//                    TessBaseAPI baseApi = new TessBaseAPI();
//                    baseApi.setDebug(true);
//                    String DATAPATH = Environment.getExternalStorageDirectory().toString();
//                    baseApi.init(DATAPATH, LANG);
//                    baseApi.setImage(params[0]);
//                    recognizedText = baseApi.getUTF8Text();
//                    baseApi.end();

//        Log.v(LOG_TAG, "OCR Result: " + recognizedText);

                    // clean up and show
//                    if (LANG.equalsIgnoreCase("eng")) {
//                        recognizedText = recognizedText.replaceAll("[^a-zA-Z0-9]+", " ");
//                    }
//                    if (recognizedText.length() != 0) {
//
//                        recognizedText = recognizedText.trim();
//                    }
                } catch (Exception e) {
                    recognizedText = e.getLocalizedMessage();
                }

                return recognizedText;
            }

            @Override
            protected void onPostExecute(String recognizedText) {
                super.onPostExecute(recognizedText);
                previewing = true;
                if (recognizedText.length() != 0)
                    Toast.makeText(getContext(), recognizedText, Toast.LENGTH_SHORT).show();

            }
        }.execute(bitmap);


    }
}
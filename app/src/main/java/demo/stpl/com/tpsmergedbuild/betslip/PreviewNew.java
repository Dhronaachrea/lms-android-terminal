package demo.stpl.com.tpsmergedbuild.betslip;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;

//import tpsgames.HttpRequest;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.GameBean;
//import tpsgames.interfaces.ResponseLis;


class PreviewNew extends SurfaceView implements SurfaceHolder.Callback, ResponseLis {
    private static final String TAG = "Preview";

    Boolean isbetslipready = false;
    private Activity context;
    Mat newimage = new Mat();
    ActivityBetSlipCamera activity;
    SurfaceHolder mHolder;
    public Camera camera;
    float dd = 0;
    //    private Button scanButton;
    private Point topleft, topright, bottomleft, bottomright;
    private ImageScanner scanner;

    private String response = "";

    PreviewNew(Activity context) {
        super(context);

        this.context = context;

        if (context instanceof ActivityBetSlipCamera)
            this.activity = (ActivityBetSlipCamera) context;

        autoFocusHandler = new Handler();
        mHolder = getHolder();
        // Instance barcode scanner
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mHolder.setFormat(PixelFormat.TRANSLUCENT | WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

    }

    public void setisisImageCaptured(boolean b) {
        isImageCaptured = b;
        isbetslipready = b;
        previewing = !b;

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


    private boolean isImageCaptured = false;
    Camera.Parameters parameters;
    byte[] data;

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, acquire the camera and tell it where
        // to draw.
        if (Build.MODEL.equalsIgnoreCase("SM-T231")) {
            camera = openFrontFacingCameraGingerbread();
        } else {
            camera = Camera.open();
        }

        try {
            camera.setPreviewDisplay(mHolder);
            camera.setPreviewCallback(new PreviewCallback() {

                public void onPreviewFrame(byte[] data, Camera arg1) {
                    FileOutputStream outStream = null;
                    try {
                        Camera.Parameters parameters = camera.getParameters();

                        int zoom = -5;
                        int maxZoom = parameters.getMaxZoom();
                        if (parameters.isZoomSupported()) {
//                            if (zoom >= 0 && zoom < maxZoom) {
                            parameters.setZoom(-5);
//                            } else {
//                                // zoom parameter is incorrect
//                            }
                        }
                        //camera.setParameters(parameters);

                        Camera.Size size = parameters.getPreviewSize();
                        Image barcode = new Image(size.width, size.height, "Y800");
                        barcode.setData(data);


                        PreviewNew.this.parameters = parameters;
                        PreviewNew.this.data = data;
                        int result = scanner.scanImage(barcode);
                        if (!isImageCaptured) {
                            if (isbetslipready) {
                                isImageCaptured = true;

                                TpsGamesClass.getInstance().startLoader(context);

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        int width = PreviewNew.this.parameters.getPreviewSize().width;
                                        int height = PreviewNew.this.parameters.getPreviewSize().height;

                                        YuvImage yuv = new YuvImage(PreviewNew.this.data, PreviewNew.this.parameters.getPreviewFormat(), width, height, null);

                                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                                        yuv.compressToJpeg(new Rect(0, 0, width, height), 50, out);

                                        byte[] bytes = out.toByteArray();
                                        final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                        Bitmap bitmap1 = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                                        org.opencv.android.Utils.bitmapToMat(bitmap1, newimage);
                                        findLargestRectangle(newimage);
                                    }
                                }).start();
                            }
                        }
                        if (result != 0 && previewing) {
                            previewing = false;
//                            camera.setPreviewCallback(null);
//                            camera.stopPreview();

                            SymbolSet syms = scanner.getResults();
                            for (Symbol sym : syms) {

                                TpsGamesClass.getInstance().setBarcodeValueForBetSlip(sym.getData().toString());
                                System.out.print(sym.getData().toString());
                            }


                        }
                        Log.e("", "");
                        Log.e("", "");
                        Log.d(TAG, "onPreviewFrame - wrote bytes: " + data.length);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                    PreviewNew.this.invalidate();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
        public void onShutter() {
            Log.d(TAG, "onShutter'd");
        }
    };

    private synchronized void findLargestRectangle(Mat original_image) {

        Mat imgSource = new Mat(original_image.size(), CvType.CV_8U);
        Imgproc.cvtColor(original_image, imgSource, Imgproc.COLOR_BGR2GRAY);
        Imgproc.adaptiveThreshold(imgSource, imgSource, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 15, 40);
        Imgproc.GaussianBlur(imgSource, imgSource, new Size(9, 9), 2, 2);

        final List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        //find the contours
        Imgproc.findContours(imgSource, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

        double maxArea = -1;
        MatOfPoint temp_contour;
        MatOfPoint2f approxCurve = new MatOfPoint2f();
        MatOfPoint2f maxCurve = new MatOfPoint2f();
        Log.d("FFFFFFFFFFF", "size--" + contours.size());

        boolean isRectangleFound = false;

        for (int idx = 0; idx < contours.size(); idx++) {
            temp_contour = contours.get(idx);
            double contourarea = Imgproc.contourArea(temp_contour);

            //compare this contour to the previous largest contour found
            if (contourarea > maxArea) {
                //check if this contour is a square
                MatOfPoint2f new_mat = new MatOfPoint2f(temp_contour.toArray());
                int contourSize = (int) temp_contour.total();
                Imgproc.approxPolyDP(new_mat, approxCurve, contourSize * 0.05, true);
                Log.d("FFFFFFFFFFF", approxCurve.total() + "curve  " + contourarea);
                if (approxCurve.total() == 4 && contourarea > 10000) {
                    maxCurve = approxCurve;
                    maxArea = contourarea;
                    Log.d("FFFFFFFFFFF Area", contourarea + "Curve4 ");
                    isRectangleFound = true;
                    calculatedeviation(maxCurve, original_image);
//                    context.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {  TpsGamesClass.getInstance().startLoader(context);  }
//                    });
                    break;
                }
            }
        }

        if (!isRectangleFound) {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TpsGamesClass.getInstance().showAToast("Please put the betslip completely inside the camera", context, Toast.LENGTH_SHORT);
                    TpsGamesClass.getInstance().stopLoader();
                }
            });
            isImageCaptured = false;
            isbetslipready = false;
        }
    }


    void calculatedeviation(MatOfPoint2f maxcurve, final Mat newimage) {
        MatOfPoint2f maxCurve = maxcurve;

        double[] AB = new double[2];
        double temp_double[] = maxCurve.get(0, 0);

        Point p1 = new Point(temp_double[0], temp_double[1]);
        temp_double = maxCurve.get(1, 0);
        Point p2 = new Point(temp_double[0], temp_double[1]);
        temp_double = maxCurve.get(2, 0);
        Point p3 = new Point(temp_double[0], temp_double[1]);
        temp_double = maxCurve.get(3, 0);
        Point p4 = new Point(temp_double[0], temp_double[1]);
        List<Point> newpoints = new ArrayList<Point>();
        newpoints.add(p1);
        newpoints.add(p2);
        newpoints.add(p3);
        newpoints.add(p4);

        Collections.sort(newpoints, new Comparator<Point>() {

            public int compare(Point o1, Point o2) {
                return Double.compare(o1.x, o2.x);
            }
        });
        if (newpoints.get(0).y > newpoints.get(1).y) {

            bottomleft = newpoints.get(0);
            topleft = newpoints.get(1);
        } else {
            bottomleft = newpoints.get(1);
            topleft = newpoints.get(0);
        }
        if (newpoints.get(2).y > newpoints.get(3).y) {
            bottomright = newpoints.get(2);
            topright = newpoints.get(3);
        } else {
            bottomright = newpoints.get(3);
            topright = newpoints.get(2);

        }

        if (TpsGamesClass.getInstance().getBarcodeValueForBetSlip().equals("S1") || TpsGamesClass.getInstance().getBarcodeValueForBetSlip().equals("S2")) {
            response = TpsGamesClass.getInstance().getSportsResponse();

            if (response == null || response.trim().length() == 0) {
                String url = Utility.baseUrl + Utility.portNumber + Utility.sportProjectName;
                HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, this, "fetching game data", context, "sports", Utility.eBetSlipHeader);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("merchantCode", "PMS");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                httpRequest.setIsParams(true, jsonObject.toString());
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TpsGamesClass.getInstance().startLoader(context);
                    }
                });


                httpRequest.executeTask();
            } else {
                callclass();
            }
        } else {
            callclass();
        }


    }

    public void callclass() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                TpsGamesClass.getInstance().stopLoader();
                Mat lol = new Mat(newimage, new org.opencv.core.Rect(topleft, bottomright));
                dd = getAngle(bottomleft, bottomright);
                if ((dd > -5 && dd < -0.2) || (dd > 0.2 && dd < 5)) {

                    Bitmap abc = RotateBitmap(createBitmapfromMat(newimage), (-dd));
                    Bitmap bmp32 = abc.copy(Bitmap.Config.ARGB_8888, true);
                    Utils.bitmapToMat(bmp32, newimage);
                    findLargestRectangle(newimage);
                } else if (TpsGamesClass.getInstance().getBarcodeValueForBetSlip().equals("A1")) {
                    // miniKino
                    Mini_Keno mk = new Mini_Keno(PreviewNew.this);
                    mk.Mini_Keno_CalculateBets(context, newimage, topleft, topright, bottomleft, bottomright);

                } else if (TpsGamesClass.getInstance().getBarcodeValueForBetSlip().equals("A3")) {
                    // Lucky Number
                    LuckyNumber ln = new LuckyNumber(PreviewNew.this);
                    ln.Lucky_Number_CalculateBets(context, newimage, topleft, topright, bottomleft, bottomright);

                } else if (TpsGamesClass.getInstance().getBarcodeValueForBetSlip().equals("A2")) {
                    // Lucky Number
                    Super_Keno sk = new Super_Keno(PreviewNew.this);
                    sk.Super_Keno_CalculateBets(context, newimage, topleft, topright, bottomleft, bottomright);

                } else if (TpsGamesClass.getInstance().getBarcodeValueForBetSlip().equals("A4")) {
                    GameBean.Games gamesRoulette = null;
                    for (GameBean.Games games : TpsGamesClass.getInstance().getGameBean().getGames()) {
                        if (games.getGameCode().equals("MiniRoulette")) {
                            gamesRoulette = games;
                            break;
                        }
                    }
                    MiniRoulette mr = new MiniRoulette(gamesRoulette, PreviewNew.this);
                    mr.MiniRoulette_CalculateBets(context, newimage, topleft, topright, bottomleft, bottomright);
                } else if (TpsGamesClass.getInstance().getBarcodeValueForBetSlip().equals("S1")) {
                    Soccer6 sc6 = new Soccer6(PreviewNew.this, response);
                    sc6.Soccer_6_CalculateBets(context, newimage, topleft, topright, bottomleft, bottomright);
                } else if (TpsGamesClass.getInstance().getBarcodeValueForBetSlip().equals("S2")) {
                    Soccer13 sc13 = new Soccer13(PreviewNew.this, response);
                    sc13.Soccer_13_CalculateBets(context, newimage, topleft, topright, bottomleft, bottomright);
                }
            }
        });
    }


    protected void callSportsData() {
        String url = Utility.baseUrl + Utility.portNumber + Utility.sportProjectName;
        HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, this, "fetching game data", context, "sports", Utility.eBetSlipHeader);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("merchantCode", "PMS");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequest.setIsParams(true, jsonObject.toString());
        TpsGamesClass.getInstance().startLoader(context);
        httpRequest.executeTask();
    }


    /**
     * Handles data for raw picture
     */
    Camera.PictureCallback rawCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            Log.d(TAG, "onPictureTaken - raw");
        }
    };

    /**
     * Handles data for jpeg picture
     */
    Camera.PictureCallback jpegCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            FileOutputStream outStream = null;
            long time = 0;
            try {
                time = System.currentTimeMillis();
                PreviewNew.this.releaseCamera();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 6;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                Bitmap bitmap1 = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                org.opencv.android.Utils.bitmapToMat(bitmap1, newimage);
                findLargestRectangle(newimage);
                Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                PreviewNew.this.releaseCamera();

            }
            Log.d(TAG, "onPictureTaken - jpeg");
        }
    };

    private void showAlertDialog(final String message) {

//        new AlertDialog.Builder(activity)
//                .setTitle("QR code detected!")
//                .setCancelable(false)
//                .setMessage("Do you want to continue?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        activity.closeCamera(message);
//                    }
//                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                dialogInterface.dismiss();
//            }
//        })

//                .show();
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

    public Bitmap createBitmapfromMat(Mat snap) {
        Bitmap bp = Bitmap.createBitmap(snap.cols(), snap.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(snap, bp);
        return bp;
    }

    public float getAngle(Point start, Point target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - start.y, target.x - start.x));

       /* if(angle < 0){
            angle += 360;
        }*/

        return angle;
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


    @Override
    public void onResponse(String response, String requestedMethod) {
        TpsGamesClass.getInstance().stopLoader();
        if (response != null && requestedMethod.equals("sports")) {
            TpsGamesClass.getInstance().setSportsResponse(response);
            this.response = response;
            callclass();
        }

    }
}
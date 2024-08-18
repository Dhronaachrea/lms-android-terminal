package demo.stpl.com.tpsmergedbuild.baseClass;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import demo.stpl.com.tpsmergedbuild.activity.Ola;

//import tpsgames.activity.Ola;

/**
 * Created by stpl on 10/22/2016.
 */

public class TPS390Camera extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    boolean previewing = true;

    Ola activity;

    @SuppressWarnings("deprecation")

    public TPS390Camera(Activity context) {
        super(context);
        this.activity = (Ola) context;

        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        camera = Camera.open();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            if (camera == null) {
                camera = Camera.open();
            }
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            Log.d("Camera", "created");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try {
            camera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        camera.startPreview();


    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            previewing = false;

            camera.setPreviewCallback(null);

            camera.stopPreview();
            camera.release();
            camera = null;
            Log.d("Camera", "destroyed");

        }

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

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyCameraApp");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }

    public void takePicture() {
        try {
            camera.takePicture(null, null, new Camera.PictureCallback() {


                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    File pictureFile = getOutputMediaFile();
                    previewing = false;

                    if (pictureFile == null) {
                        return;
                    }
                    try {
                        if (activity != null) {
                            FileOutputStream fos = new FileOutputStream(pictureFile);

                            fos.write(data);
                            fos.close();
                            Log.d("file", "closed");
                        }
                        TpsGamesClass.getInstance().stopLoader();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, "Picture saved in memory", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Thread.sleep(3000);

                        previewing = true;
                        camera.startPreview();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        } catch (Exception e) {

        }


    }


}

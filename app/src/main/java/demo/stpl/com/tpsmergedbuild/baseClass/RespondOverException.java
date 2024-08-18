package demo.stpl.com.tpsmergedbuild.baseClass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.io.PrintWriter;
import java.io.StringWriter;

import demo.stpl.com.tpsmergedbuild.activity.MainAppCrashActivity;

//import tpsgames.activity.MainAppCrashActivity;

/**
 * Created by stpl on 05-Nov-16.
 */
public class RespondOverException implements Thread.UncaughtExceptionHandler {
    private Context context;

    public RespondOverException(Context context) {
        this.context = context;
    }


    protected void launch(Class<? extends Activity> activity, String exception) {

        Intent intent = new Intent(context, activity);


        intent.putExtra("error", exception);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @SuppressWarnings("unchecked")
    protected static Class<? extends Activity> getLauncherActivity(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (intent != null) {
            try {
                return (Class<? extends Activity>) Class.forName(intent.getComponent().getClassName());
            } catch (ClassNotFoundException e) {
//                Log.e(TAG, "Error", e);
            }
        }

        return null;
    }

    private static Class<? extends Activity> createAppCrashActivity() {
        return MainAppCrashActivity.class;
    }


    Thread thread;

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        this.thread = thread;
//        ((Activity)context).runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Communcations.winningInside(context, "Server Error!", onClickListener);
//            }
//        });
//        this.thread.setUncaughtExceptionHandler(null);
//        new Thread() {
//            @Override
//            public void run() {
//                RespondOverException.this.thread.interrupt();
//                Looper.prepare();
//                Toast.makeText(context.getApplicationContext(), "Application crashed", Toast.LENGTH_LONG).show();
//                moveToLogin();
//                Looper.loop();
//            }
//        }.start();
        StringWriter stackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(stackTrace));
        launch(createAppCrashActivity(), stackTrace.toString());
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
//        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.skilrock.tpsgames.games");
////        launchIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(launchIntent);
//        //for restarting the Activity
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
    }
}

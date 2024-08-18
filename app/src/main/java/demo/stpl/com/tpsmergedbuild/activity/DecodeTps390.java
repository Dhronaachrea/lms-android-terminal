package demo.stpl.com.tpsmergedbuild.activity;

import android.util.Log;

import com.telpo.tps550.api.TelpoException;

/**
 * Created by stpl on 10/27/2016.
 */

public class DecodeTps390 extends Thread implements Runnable {
    private ClaimWinningActivityTps390 claimWinningActivityTps390;
    public boolean isCancelCall = false;
    private String code;

    public DecodeTps390(ClaimWinningActivityTps390 activity) {
        this.claimWinningActivityTps390 = activity;
    }

    @Override
    public void run() {
        super.run();

        while (!Thread.currentThread().isInterrupted()) {

            try {
                com.telpo.tps550.api.decode.Decode.open();

                this.code = com.telpo.tps550.api.decode.Decode.read(3000);

                if (code != null) {
                    Log.d("code",code);
                    claimWinningActivityTps390.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            claimWinningActivityTps390.setTextValue(code);
                        }
                    });
                    Thread.currentThread().interrupt();



                }

            } catch (TelpoException e) {

                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {

                com.telpo.tps550.api.decode.Decode.disconnect();
            }
        }






    }

}

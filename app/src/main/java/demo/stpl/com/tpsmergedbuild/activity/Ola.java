package demo.stpl.com.tpsmergedbuild.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

public class Ola extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    TabLayout tabLayout;
    ImageView ola_btn;
    TextView user_name, user_balance;
    int currentPosition = 0;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private int currentPos;
    private OlaRegistrationFragment olaRegistrationFragment;
    private OlaDepositFragment olaDepositFragment;
    private OlaWithdrawalFragment olaWithDrawlFragment;
    OlaRegistrationFragment olaRegistrationFragmentForCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.activity_ola);
        TpsGamesClass.getInstance().openCard();
        TpsGamesClass.getInstance().setOlaActivity(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TpsGamesClass.getInstance().setIsOlaFirstTime(true);

        if (TpsGamesClass.getInstance().getDeviceName().contains("TPS390") || TpsGamesClass.getInstance().getDeviceName().contains("TPS580") || TpsGamesClass.getInstance().getDeviceName().contains("TPS550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx")) {
            TpsGamesClass.getInstance().openCard();
        }

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        ola_btn = (ImageView) findViewById(R.id.ola_btn);
        tabLayout.setupWithViewPager(mViewPager);

        user_name = (TextView) findViewById(R.id.user_name);

        user_balance = (TextView) findViewById(R.id.user_balance);

        ola_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        user_name.setText(TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
        String balance = TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "";

        if (balance.contains(".")) {
            String[] decimalAmount = balance.split("[.]");
            balance = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        user_balance.setText("$ " + balance);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TpsGamesClass.getInstance().stopLoader();
                if (!TpsGamesClass.getInstance().getDeviceName().contains("TPS515") && !TpsGamesClass.getInstance().getDeviceName().contains("rdx")) {
                    TpsGamesClass.getInstance().hideKeyBoard(Ola.this, Ola.this);
                }

                if (TpsGamesClass.getInstance().getDeviceName().contains("TPS390") || TpsGamesClass.getInstance().getDeviceName().contains("TPS580") || TpsGamesClass.getInstance().getDeviceName().contains("TPS550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx")) {
                    TpsGamesClass.getInstance().openCard();
                }
                currentPosition = position;
                if (position == 0) {
                    OlaRegistrationFragment olaRegistrationFragment = ((OlaRegistrationFragment) mSectionsPagerAdapter.getItem(position));
                    olaRegistrationFragment.refreshContaints();

                    olaRegistrationFragment.addCamera();
                    if (TpsGamesClass.getInstance().getDeviceName().contains("TPS515") || TpsGamesClass.getInstance().getDeviceName().contains("rdx")) {

                        olaRegistrationFragment.weaverId.requestFocus();
                    }
                } else if (position == 1) {
                    if (!TpsGamesClass.getInstance().getDeviceName().contains("7003")) {
                        if (TpsGamesClass.getInstance().getIfHasCamera()) {
                            olaRegistrationFragmentForCamera = ((OlaRegistrationFragment) mSectionsPagerAdapter.getItem(0));
                            olaRegistrationFragmentForCamera.tps390Camera.releaseCamera();
                        }
                    }
                    OlaDepositFragment olaDepositFragment = ((OlaDepositFragment) mSectionsPagerAdapter.getItem(position));
                    olaDepositFragment.refreshView();
                    if (TpsGamesClass.getInstance().getDeviceName().contains("TPS515") || TpsGamesClass.getInstance().getDeviceName().contains("rdx")) {
//                        TpsGamesClass.getInstance().openCard();
                        olaDepositFragment.weaverId.requestFocus();
                    }
                } else if (position == 2) {
                    OlaWithdrawalFragment olaWithdrawalFragment = ((OlaWithdrawalFragment) mSectionsPagerAdapter.getItem(position));
                    olaWithdrawalFragment.updateView();
                    if (TpsGamesClass.getInstance().getDeviceName().contains("TPS515") || TpsGamesClass.getInstance().getDeviceName().contains("rdx")) {
//                        TpsGamesClass.getInstance().openCard();
                        olaWithdrawalFragment.weaverId.requestFocus();
                    }
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
//        olaRegistrationFragmentForCamera = ((OlaRegistrationFragment) mSectionsPagerAdapter.getItem(0));
//        olaRegistrationFragment.addCamera();
    }

    public void updateWeaverCard(String weaverNumber) {
        weaverNumber = weaverNumber.replaceAll("[^0-9]", "");
        if (TpsGamesClass.getInstance().getDeviceName().contains("TPS390") || TpsGamesClass.getInstance().getDeviceName().contains("TPS580") || TpsGamesClass.getInstance().getDeviceName().contains("TPS550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx")) {
            TpsGamesClass.getInstance().stopCard();
        }

        if (currentPosition == 0) {
            ((OlaRegistrationFragment) mSectionsPagerAdapter.getItem(currentPosition)).setWeaverCard(weaverNumber);
        } else if (currentPosition == 1) {
            ((OlaDepositFragment) mSectionsPagerAdapter.getItem(currentPosition)).setWeaverCard(weaverNumber);
        } else if (currentPosition == 2) {
            ((OlaWithdrawalFragment) mSectionsPagerAdapter.getItem(currentPosition)).setWeaverCard(weaverNumber);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        TpsGamesClass.getInstance().setCurrentActivity("ola");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ola, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    if (olaRegistrationFragment == null) {
                        olaRegistrationFragment = OlaRegistrationFragment.newInstance();
                    }

                    return olaRegistrationFragment;
                case 1:
                    if (olaDepositFragment == null) {
                        olaDepositFragment = OlaDepositFragment.newInstance();
                    }

//                    olaDepositFragment = OlaDepositFragment.newInstance();
                    return olaDepositFragment;
                case 2:
                    if (olaWithDrawlFragment == null) {
                        olaWithDrawlFragment = OlaWithdrawalFragment.newInstance();
                    }

//                    olaWithDrawlFragment = OlaWithdrawalFragment.newInstance();
                    return olaWithDrawlFragment;


            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Register";
                case 1:
                    return "Deposit";
                case 2:
                    return "Withdrawal";
            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TpsGamesClass.getInstance().stopCard();

        TpsGamesClass.getInstance().setOlaActivity(null);
    }

    public synchronized void setPlayerId(String playerId) {
        playerId = playerId.replaceAll("[^0-9]", "");
        currentPos = tabLayout.getSelectedTabPosition();

        switch (currentPos) {
            case 0:
                olaRegistrationFragment.setWeaverId(playerId);
                break;
            case 1:
                olaDepositFragment.setWeaverId(playerId);
                break;
            case 2:
                olaWithDrawlFragment.setWeaverId(playerId);
                break;
        }
    }


}

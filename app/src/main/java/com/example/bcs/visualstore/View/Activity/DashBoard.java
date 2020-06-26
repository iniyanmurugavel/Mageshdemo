package com.example.bcs.visualstore.View.Activity;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.ContextCompat;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bcs.visualstore.BottomNavigationViewHelper;
import com.example.bcs.visualstore.JivoChatSDK.JivoActivity;
import com.example.bcs.visualstore.PojoDatas.Sharedpreference;
import com.example.bcs.visualstore.View.Adapter.CustomExpandableListAdapter;
import com.example.bcs.visualstore.Utils.APIService;
import com.example.bcs.visualstore.Utils.ApiUtils;
import com.example.bcs.visualstore.View.Fragment.CartResultsFragment;
import com.example.bcs.visualstore.View.Fragment.ContactInformation;

import com.example.bcs.visualstore.View.Fragment.DashBoardFragment;
import com.example.bcs.visualstore.View.Fragment.EmployeeManagementFragment;
import com.example.bcs.visualstore.View.Fragment.LensOrderingFragment;
import com.example.bcs.visualstore.View.Fragment.MyAccountFragment;
import com.example.bcs.visualstore.PojoDatas.LogoutData;


import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.View.Fragment.SearchOrdersFragment;
import com.example.bcs.visualstore.View.Fragment.ShapeAndBevelFragment;
import com.google.gson.JsonElement;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton closebtn;
    DrawerLayout drawer;
    APIService mAPIService,LogoutService;
    ProgressDialog progressBar;
    CircleImageView profileImage;
    TextView toolText;
    TextView name,mobile,gmail;
    ImageView backLogo;
    LogoutData logoutData;
    RelativeLayout rel_inCompleteOrder,rel_completeOrder,rel_sentOrder;
    FragmentManager fm;
    ExpandableListView expandableListView;
    Button newButton,completebtn,incompletebtn,sentbtn;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    CustomExpandableListAdapter adapter;
    String id,auth_id,username;

    String fragmentName;
    Fragment fragment;
    SharedPreferences preferences;
    FloatingActionButton fab;

    String PREFS_NAME = "MyPrefName";
    String PREFS_KEY = "MyPrefKey";




    private BottomNavigationView mBottomNavigationView;


    //DrawerLayout drawer;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.darkgrey)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkgrey)); //status bar or the time bar at the top
        }
        if (savedInstanceState==null){
            Fragment fragment;
            fragment=new DashBoardFragment();
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        boolean network=isNetworkAvailable();
        if(network){
            System.out.println("Internet Available");
        }else {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DashBoard.this);
            alertDialogBuilder.setMessage("No Internet");
            alertDialogBuilder.setPositiveButton("Exit",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                            System.exit(0);
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
        }


        preferences=getApplicationContext().getSharedPreferences("MyPref",0);
        id= preferences.getString("id","1");
        auth_id=preferences.getString("auth_id","1");
        username=preferences.getString("userName","1");

        String share=id;
        SharedPreferences shared =getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("Login",share);
        editor.putString("auther_id",auth_id);
        editor.apply();


        mAPIService= ApiUtils.getMyprofile(auth_id);
        LogoutService=ApiUtils.getLogoutService(auth_id);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolText= (TextView) toolbar.findViewById(R.id.tooltxt);
        backLogo=(ImageView) toolbar.findViewById(R.id.menuLogo);
        toolText.setText("Visustore");
        profileImage=(CircleImageView)findViewById(R.id.myImageontoolbar);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);






                       /*this is back button in toolbar*/

        backLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FrameLayout fl = (FrameLayout) findViewById(R.id.fragmentContainer);
                androidx.fragment.app.FragmentManager manager=getSupportFragmentManager();
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                    getFragmentManager().popBackStackImmediate();

                }else if (manager.getBackStackEntryCount()>=1){

                    Fragment fragName=getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
                    String aa=fragName.getClass().getSimpleName();
                    if (aa.equals("LensOrderingFragment")){
                        Fragment fragment=new DashBoardFragment();
                        fragmentMethod(fragment);
                       // manager.popBackStack();
                        toolText.setText("VISUSTORE");
                        backLogo.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));

                    }else if (aa.equals("AdvanceOptionFragment")){
                        toolText.setText("LENS ORDERING");
                        manager.popBackStack();

                    }else if (aa.equals("ShapeAndBevelFragment")){
                        toolText.setText("LENS ORDERING");
                        manager.popBackStack();
                    }else if(aa.equals("OrderReviewFragment")){

                       int number= ShapeAndBevelFragment.shape;
                       Log.e("number from shape ", String.valueOf(number));

                        if (number==0){
                            toolText.setText("LENS ORDERING");
                            manager.popBackStack();
                        }else {
                            toolText.setText("SHAPE & BEVEL");
                            manager.popBackStack();
                        }

                    }else {
                      //  Log.e("tt",aa);
                        drawer.openDrawer(GravityCompat.START);
                        //  toolText.setText("VisuStore");
                      //  Log.e("getCount", String.valueOf(fl.getChildCount()));
                        backLogo.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));
                    }

                }else {

                    drawer.openDrawer(GravityCompat.START);
                }
                hideKeyboard(v);
            }
        });
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        name=(TextView)headerView.findViewById(R.id.name);
        mobile=(TextView)headerView.findViewById(R.id.mobile);
        gmail=(TextView)headerView.findViewById(R.id.gmail);

        Fragment fr=new Fragment();
        FrameLayout fl = (FrameLayout) findViewById(R.id.fragmentContainer);

        setupBottomNavigation();

        init();
        listener();

        Log.e("fragm",fl.getClass().getSimpleName());


//
//        String aa=fl.getClass().getSimpleName();
//        if (aa.equals(AdvanceOptionFragment.class.getName())){
//            Log.e("tt",aa);
//        }

    }


    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void setupBottomNavigation() {

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(mBottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                Fragment fragment;
                Fragment fragName=getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
                String aa=fragName.getClass().getSimpleName();

                switch (item.getItemId()) {
                    case R.id.action_lens:
                      //  Log.e("tt",aa);
                        fragment=new DashBoardFragment();
                      //  fragmentMethod(fragment);
                        if (aa.equals("LensOrderingFragment") || aa.equals("AdvanceOptionFragment") || aa.equals("ShapeAndBevelFragment") || aa.equals("OrderReviewFragment")){
                            alertDialog(fragment,"VISUSTORE");
                        }else {
                            fragmentMethod(fragment);
                            toolText.setText("VISUSTORE");
                        }

                        return true;
                    case R.id.action_cart:
                     //   Log.e("tt",aa);
                        fragment=new LensOrderingFragment();
                        if (aa.equals("LensOrderingFragment") || aa.equals("AdvanceOptionFragment") || aa.equals("ShapeAndBevelFragment") || aa.equals("OrderReviewFragment")){
                            alertDialog(fragment,"CART");
                        }else {
                            fragmentMethod(fragment);
                            toolText.setText("CART");
                        }


                        return true;
                    case R.id.action_profile:
                        fragment=new SearchOrdersFragment();
                        if (aa.equals("LensOrderingFragment") || aa.equals("AdvanceOptionFragment") || aa.equals("ShapeAndBevelFragment") || aa.equals("OrderReviewFragment")){
                            alertDialog(fragment,"SENT ORDERS");
                          //  toolText.setText("SENT ORDERS");
                        }else {
                            fragmentMethod(fragment);
                            toolText.setText("SENT ORDERS");
                        }
                        return true;
                    case R.id.action_chat:
                        final long TIME = 1 * 1000;
                        if (aa.equals("LensOrderingFragment") || aa.equals("AdvanceOptionFragment") || aa.equals("ShapeAndBevelFragment") || aa.equals("OrderReviewFragment")){
                            AlertDialog.Builder alertDialog=new AlertDialog.Builder(DashBoard.this,R.style.AlertDialogDanger);

                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("Do you like to cancel the order");
                            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                            alertDialog.setPositiveButton("YES",null);
                            alertDialog.setNegativeButton("NO",null);
                            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    item.setEnabled(false);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent =new Intent(DashBoard.this, JivoActivity.class);
                                            startActivity(intent);
                                            item.setEnabled(true);
                                        }
                                    },TIME);
                                }
                            });
                            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                        }else {

                            item.setEnabled(false);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent =new Intent(DashBoard.this, JivoActivity.class);
                                    startActivity(intent);
                                    item.setEnabled(true);
                                }
                            },TIME);


                        }

                        //break;
                       return true;
                }

                return false;
            }
        });
    }



    @Override
    public void onBackPressed() {
        FrameLayout fl = (FrameLayout) findViewById(R.id.fragmentContainer);
        androidx.fragment.app.FragmentManager manager=getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else  if(manager.getBackStackEntryCount()>=1){
            Fragment fragName=getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            String aa=fragName.getClass().getSimpleName();
            if (aa.equals("LensOrderingFragment")){
               // Fragment fragment=new DashBoardFragment();
               // fragmentMethod(fragment);
               // super.onBackPressed();
//                Log.e("aa",aa);
                manager.popBackStack();
                toolText.setText("VISUSTORE");
                backLogo.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));

            }
            else if (aa.equals("ShapeAndBevelFragment")){
                toolText.setText("LENS ORDERING");
                manager.popBackStack();
            }else if (aa.equals("AdvanceOptionFragment")){
                toolText.setText("LENS ORDERING");
                manager.popBackStack();
//                Log.e("aa",aa);
            }else if (aa.equals("OrderReviewFragment")){

                int number= ShapeAndBevelFragment.shape;
                Log.e("number from shape ", String.valueOf(number));
                if (number==0){
                    toolText.setText("LENS ORDERING");
                    manager.popBackStack();
                }else {
                    toolText.setText("SHAPE & BEVEL");
                    manager.popBackStack();
                }

//                toolText.setText("LENS ORDERING");
//                manager.popBackStack();
//                Log.e("aa",aa);
            }
            else if (aa.equals("DashBoardFragment")){
              //  Log.e("aa",aa);
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                finish();
                super.onBackPressed();
            }else {
                toolText.setText("VISUSTORE");
                super.onBackPressed();

            }

        }

        //   super.onBackPressed();

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment;
        if (id == R.id.home) {
            // Handle the camera action
            fragment=new DashBoardFragment();
            fragmentMethod(fragment);
        } else if (id == R.id.lens) {
            fragment=new LensOrderingFragment();
            fragmentMethod(fragment);
            toolText.setText("LENS ORDERING");

        } else if (id == R.id.cart) {
            fragment=new CartResultsFragment();
            fragmentMethod(fragment);
            Sharedpreference.onStorePreference(DashBoard.this,Sharedpreference.Order,"3");


        } else if (id == R.id.sent) {
            fragment=new SearchOrdersFragment();
            fragmentMethod(fragment);
            //toolText.setText("");

        } else if (id == R.id.employee) {
            Bundle bundle=new Bundle();
            bundle.putString("auth_id",auth_id);
            fragment=new EmployeeManagementFragment();
            fragment.setArguments(bundle);
            fragmentMethod(fragment);

        } else if (id == R.id.contact) {
            fragment=new ContactInformation();
            fragmentMethod(fragment);
        }else if (id==R.id.account){
            fragment=new MyAccountFragment();
            fragmentMethod(fragment);
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private void listener() {
        final DrawerLayout drawerone = (DrawerLayout) findViewById(R.id.drawer_layout);

        closebtn=(FloatingActionButton)findViewById(R.id.clearButton);
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });


//        newButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment fragment=new LensOrderingFragment();
//                fragmentMethod(fragment);
//
//                SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//                settings.edit().clear().apply();
//
//                SharedPreferences setting = getApplicationContext().getSharedPreferences("hi", Context.MODE_PRIVATE);
//                setting.edit().clear().apply();
//
//                SharedPreferences removeShape=getApplicationContext().getSharedPreferences("hello",Context.MODE_PRIVATE);
//                removeShape.edit().clear().apply();
//
//
//            }
//        });
//
//
//        incompletebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("status","1");
//                Fragment fragment=new CartResultsFragment();
//                fragment.setArguments(bundle);
//                fragmentMethod(fragment);
//                Sharedpreference.onStorePreference(getApplicationContext(),Sharedpreference.Order,"1");
//            }
//        });
//
//        completebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("status","2");
//
//                Fragment fragment=new CartResultsFragment();
//                fragment.setArguments(bundle);
//                fragmentMethod(fragment);
//                Sharedpreference.onStorePreference(getApplicationContext(),Sharedpreference.Order,"2");
//            }
//        });
//
//        sentbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle=new Bundle();
//                bundle.putString("sentOrders","sent orders");
//                Fragment fragment=new SearchOrdersFragment();
//                fragment.setArguments(bundle);
//                fragmentMethod(fragment);
//            }
//        });
//





        profileImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Fragment fragName=getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
                String aa=fragName.getClass().getSimpleName();
                final Fragment fragment=new MyAccountFragment();
                if (aa.equals("LensOrderingFragment") || aa.equals("AdvanceOptionFragment") || aa.equals("ShapeAndBevelFragment") || aa.equals("OrderReviewFragment")){

                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(DashBoard.this,R.style.AlertDialogDanger);

                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Do you like to cancel the order");
                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    alertDialog.setPositiveButton("YES",null);
                    alertDialog.setNegativeButton("NO",null);
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            fragmentMethod(fragment);
                            backLogo.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));
                        }
                    });
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });
                    alertDialog.show();

                }else {
                    fragmentMethod(fragment);
                }




            }
        });

        fab = (FloatingActionButton) findViewById(R.id.logoutButton);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                logoutData = new LogoutData(id,username);
                progressBar = new ProgressDialog(DashBoard.this,R.style.MyAlertDialogStyle);
                progressBar.setMessage("Loading...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.setCancelable(true);
                progressBar.setCanceledOnTouchOutside(true);
                progressBar.show();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                }).start();

                System.out.println("id"+id);

                System.out.println("auth_id"+auth_id);

                LogoutService.logout(logoutData).enqueue(new Callback<LogoutData>() {

                    @Override
                    public void onResponse(@NonNull Call<LogoutData> call, @NonNull Response<LogoutData> response) {

                        if (response.code() == 200) {

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.remove("Login");
                            editor.apply();

                            System.out.println(response.body());
                            progressBar.dismiss();
                            Intent intent=new Intent(DashBoard.this,LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();

                        } else {
                            progressBar.dismiss();
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LogoutData> call, @NonNull Throwable t) {
                        System.out.println(t.getMessage());
                        progressBar.dismiss();

                    }
                });
            }

        });


        /*add new DashBoard fragment in fragment */

//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                if (groupPosition==0){
//                    fragment=new DashBoardFragment();
//                    fragmentMethod(fragment);
//                  //  backLogo.setImageDrawable(getResources().getDrawable(R.drawable.backbtn));
//                    drawerone.closeDrawer(GravityCompat.START);
//                }else if (groupPosition==1){
//               //     backLogo.setImageDrawable(getResources().getDrawable(R.drawable.backbtn));
//                    drawerone.closeDrawer(GravityCompat.START);
//                    fragment= new LensOrderingFragment();
//                    fragmentMethod(fragment);
//                    fragmentName=fragment.getClass().getSimpleName();
//                }else if (groupPosition==2){
//         //           backLogo.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));
//                }else if (groupPosition==3){
//        //            backLogo.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));
//                }else if (groupPosition==4){
//                  //  backLogo.setImageDrawable(getResources().getDrawable(R.drawable.backbtn));
//                    fragment= new MyAccountFragment();
//                    fragmentMethod(fragment);
//                    drawerone.closeDrawer(GravityCompat.START);
//                    fragmentName=fragment.getClass().getSimpleName();
//                }
//
//            }
//        });
//
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//
//            }
//        });
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//
//                String child= String.valueOf(listDataChild.get(
//                        listDataHeader.get(groupPosition)).get(
//                        childPosition));
//
//                if (child=="CART"){
//                     fragment= new CartResultsFragment();
//                     fragmentMethod(fragment);
//                    drawerone.closeDrawer(GravityCompat.START);
//                    fragmentName=fragment.getClass().getSimpleName();
//                } else if (child=="SENT ORDER"){
//                     fragment= new SearchOrdersFragment();
//                    fragmentMethod(fragment);
//                    drawerone.closeDrawer(GravityCompat.START);
//                    fragmentName=fragment.getClass().getSimpleName();
//                } else if (child=="EMPLOYEE MANAGEMENT"){
//                    fragment= new EmployeeManagementFragment();
//                    Bundle bundle=new Bundle();
//                    bundle.putString("auth_id",auth_id);
//                    fragment.setArguments(bundle);
//                   fragmentMethod(fragment);
//                    drawerone.closeDrawer(GravityCompat.START);
//                    fm = getFragmentManager();
//                    fragmentName=fragment.getClass().getSimpleName();
//                    Log.e("fragmentName",fragmentName);
//                }else if (child=="CONTACT INFORMATION"){
//                    fragment= new ContactInformation();
//                    fragmentMethod(fragment);
//                    drawerone.closeDrawer(GravityCompat.START);
//                    fragmentName=fragment.getClass().getSimpleName();
//                }
//
//                return false;
//            }
//        });
    }
    public void init() {
        progressBar = new ProgressDialog(DashBoard.this,R.style.MyAlertDialogStyle);
        progressBar.setMessage("Loading...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.setCancelable(true);
        progressBar.setCanceledOnTouchOutside(true);
        progressBar.show();
        mAPIService.myprofile().enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {

                //JsonElement=response.body();

                if (response.code() == 200) {
                    System.out.println("response of profile"+response.body());
                    String dd = response.body().toString();
                    try {
                        JSONObject object=new JSONObject(dd);
                        for (int i=0;i<object.length();i++){
                            JSONObject c=object.getJSONObject("data");
                            System.out.println(c);

                            String id=c.getString("id");
                            String name1=c.getString("name");
                            String phone=c.getString("phone");
                            String email=c.getString("email");
                            String username=c.getString("username");
                            name.setText(name1);
                            mobile.setText(phone);
                            gmail.setText(email);
                        }
                    }catch (Exception e){

                    }
                    progressBar.dismiss();

                } else {
                    progressBar.dismiss();
                    System.out.println(response.code());
                    if (response.code() == 406) {
                        Toast.makeText(DashBoard.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
                progressBar.dismiss();

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void fragmentMethod(Fragment fragment){


        /*replace with addd ith transaction.*/
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
        transaction.addToBackStack(null);
        // this will manage backstack
        transaction.commit();

    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void unLockNavigationDrawer() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public void lockNavigationDrawer() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
    public void setToolbar(Toolbar toolbar) {

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.setDrawerIndicatorEnabled(false);
            toggle.syncState();

        } else {
///drawer.setDrawerListener(null);
        }
    }


    public void alertDialog(final Fragment fragment, final String title){

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this,R.style.AlertDialogDanger);

        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Do you like to cancel the order");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setPositiveButton("YES",null);
        alertDialog.setNegativeButton("NO",null);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fragmentMethod(fragment);
                toolText.setText(title);
                backLogo.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });
        alertDialog.show();

    }




//    textView.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Log.e("clicked","done");
//
//            int width = LinearLayout.LayoutParams.MATCH_PARENT;
//            int height = LinearLayout.LayoutParams.MATCH_PARENT;
//            boolean focusable = true;
//
//            popupSpinnerwindow = new PopupWindow(popup, width, height, focusable);
//            popupSpinnerwindow.showAtLocation(v, Gravity.CENTER, 0, 0);
//            popupSpinnerwindow.setOutsideTouchable(true);
//
//            ArrayAdapter<String> adapt = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, categories1);
//            listView.setAdapter(adapt);
//
//
//        }
//    });

//    LayoutInflater inflaterConsumer = getLayoutInflater();
//    popup=inflaterConsumer.inflate(R.layout.framespiner,null);
//    final ListView listView=(ListView)popup.findViewById(R.id.fragmeSpinner);




}

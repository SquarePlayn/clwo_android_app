package eu.clwo.clwo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import eu.clwo.clwo.networkquery.NetworkQueryApi;
import eu.clwo.clwo.networkquery.NetworkQueryResult;
import eu.clwo.clwo.recyclerview.RecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NetworkQueryApi networkQueryApi;
    public static String csgo = String.valueOf(R.drawable.csgo);
    public static String success = String.valueOf(R.drawable.succes);
    public static String fail = String.valueOf(R.drawable.fail);

    public static ArrayList<String> servernames = new ArrayList<>();
    public static ArrayList<String> player_count_labels = new ArrayList<>();
    public static ArrayList<String> status_images = new ArrayList<>();
    public static ArrayList<String> game_images = new ArrayList<>();
    public static ArrayList<String> map_names = new ArrayList<>();
    public static ArrayList<String> game_dirs = new ArrayList<>();
    public static ArrayList<String> pings = new ArrayList<>();
    public static ArrayList<String> alert_icons = new ArrayList<>();
    private long lastClickTimer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        WebView myWebView = findViewById(R.id.webview);
        ImageView wallpaper_view = findViewById(R.id.wallpaper_view);
        Button button = findViewById(R.id.button);
        final FloatingActionButton refresh_button = findViewById(R.id.refresh_button);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://clwo.eu/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        networkQueryApi = retrofit.create(NetworkQueryApi.class);

        wallpaper_view.setVisibility(INVISIBLE);
        myWebView.setVisibility(INVISIBLE);
        button.setVisibility(INVISIBLE);

        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTimer >= 1000) {
                    lastClickTimer = SystemClock.elapsedRealtime();
                    servernames.clear();
                    player_count_labels.clear();
                    status_images.clear();
                    game_images.clear();
                    map_names.clear();
                    game_dirs.clear();
                    pings.clear();
                    alert_icons.clear();
                    Toast.makeText(getApplicationContext(),
                            R.string.refreshing, Toast.LENGTH_SHORT).show();
                    getComments();
                }
            }
        });
        getComments();
    }

    @Override
    public void onBackPressed() {

        WebView myWebView = findViewById(R.id.webview);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        // If sidebar/drawer is open, close it
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        // If in webview able to go back, do so
        } else if(myWebView.canGoBack()) {
            Toast.makeText(getApplicationContext(), R.string.opening_prev_page,
                    Toast.LENGTH_SHORT).show();
            myWebView.goBack();

        // Otherwise, prompt to close the app
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_alert)
                    .setTitle(R.string.closing_activity)
                    .setMessage(R.string.sure_leave_app)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        }
    }

    @SuppressLint("RestrictedApi")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Runs whenever a new page is opened

        int id = item.getItemId();

        RecyclerView recyler = findViewById(R.id.recycler);
        ImageView wallpaper_view = findViewById(R.id.wallpaper_view);
        Button button = findViewById(R.id.button);
        FloatingActionButton refresh_button = findViewById(R.id.refresh_button);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final WebView siteview = findViewById(R.id.webview);

        // Hide everything to start with
        wallpaper_view.setVisibility(INVISIBLE);
        siteview.setVisibility(INVISIBLE);
        button.setVisibility(INVISIBLE);
        recyler.setVisibility(INVISIBLE);
        refresh_button.setVisibility(INVISIBLE);

        // Set the listener for the Set Wallpaper button
        // TODO Check whether this can just be done at startup only
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setIcon(R.drawable.ic_alert)
                        .setTitle(R.string.set_wallpaper)
                        .setMessage(R.string.confirm_set_wallpaper)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                wallpaper_alert_interface();
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });

        // Add JavaScript needed for WebView pages
        // TODO Only create webview for webview pages
        WebSettings webSettings = siteview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true); // Allow zooming
        webSettings.setDisplayZoomControls(false); // Do not display buttons for zooming
        siteview.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onLoadResource(WebView view, String url) {
                siteview.loadUrl("javascript:("+
                        "function() {" +
                        "    var page = document.getElementsByTagName('*');" +
                        "    for (x in page) {" +
                        "        var element = page[x];" +

                                 //General Rules
                        "        if (element.id == 'pid_6') {" +
                        "            document.body.innerHTML = element.outerHTML;" +
                        "            document.body.style.background='white';" +
                        "            document.body.style.textAlign='left';" +
                        "            document.body.style.padding = '10px 10px 10px 10px';" +

                                 //Jailbreak Rules and TTT rules
                        "        } else if (element.id == 'pid_4385' || element.id == 'pid_15525') {" +
                        "            document.body.innerHTML = element.outerHTML;" +
                        "            document.body.style.background='white';" +
                        "            document.body.style.textAlign='left';" +
                        "            document.body.style.padding = '2px 10px 10px 10px';" +

                                 //Jailbreak Rules (Comments Link)
                        "        } else if (element.href=='https://clwo.eu/showthread.php?tid=566') {" +
                        "            element.remove();" +

                                 //Warden Teleporter Rules
                        "        } else if (element.id == 'pid_25689') {" +
                        "            document.body.innerHTML = element.outerHTML;" +
                        "            document.body.style.background='white';" +
                        "            document.body.style.textAlign='left';" +
                        "            document.body.style.padding = '0px 10px 10px 10px';" +

                                 //Warden Teleporter Rules (Tag Hyperlinks)
                        "        } else if (element.tagName=='A') {" +
                        "            if (element.href.includes('clwo.eu/user')) {" +
                        "                element.removeAttribute('href');" +
                        "            }" +

                                 //Live Chat & Player Profiles
                        "        } else if (element.tagName=='NAV') {" +
                        "            element.remove();" +

                                 //Staff
                        "        } else if (element.className=='notifications') {" +
                        "            element.remove();" +
                        "        }" +
                        "    }" +
                        "}"
                        + ")()");
            }
        });

        if (id == R.id.nav_status) {
            // Server statuses
            Toast.makeText(getApplicationContext(), "Loading: Server Status",
                    Toast.LENGTH_SHORT).show();
            refresh_button.setVisibility(VISIBLE);
            getComments();

        } else if (id == R.id.nav_general_rules) {
            // General rules
            Toast.makeText(getApplicationContext(), "Loading: General Rules",
                    Toast.LENGTH_SHORT).show();
            siteview.loadUrl("https://clwo.eu/showthread.php?tid=6");

        } else if (id == R.id.nav_staff) {
            // Staff
            Toast.makeText(getApplicationContext(), "Loading: Staff",
                    Toast.LENGTH_SHORT).show();
            siteview.loadUrl("https://staff.aimless.eu/");

        } else if (id == R.id.nav_forums) {
            // Forums
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://clwo.eu/index.php"));
            startActivity(browserIntent);

        } else if (id == R.id.nav_jailbreak_rules) {
            // Jailbreak rules
            Toast.makeText(getApplicationContext(), "Loading: Jailbreak Rules",
                    Toast.LENGTH_SHORT).show();
            siteview.loadUrl("https://clwo.eu/showthread.php?tid=562&pid=4385#pid4385");

        } else if (id == R.id.nav_livechat) {
            // Live chat
            Toast.makeText(getApplicationContext(), "Loading: Live Chat",
                    Toast.LENGTH_SHORT).show();
            siteview.loadUrl("https://clwo.eu/jailbreak/livechat.php");

        } else if (id == R.id.nav_profiles) {
            // Player Profiles
            Toast.makeText(getApplicationContext(), "Loading: Player Profiles",
                    Toast.LENGTH_SHORT).show();
            siteview.loadUrl("https://clwo.eu/jailbreak/profile.php");

            // Disabled because Sourcebans does not work well with chrome-like browsers currently
//        } else if (id == R.id.nav_jb_sourcebans) {
//            // TODO JB SourceBans
//            // Jailbreak SourceBans
//            Toast.makeText(getApplicationContext(), "Loading: Jailbreak Sourcebans",
//                    Toast.LENGTH_SHORT).show();
//            siteview.loadUrl("https://clwo.eu/sourcebans/");

        } else if (id == R.id.nav_ttt_rules) {
            // TODO TTT Rules
            // Player Profiles
            Toast.makeText(getApplicationContext(), "Loading: TTT Rules",
                    Toast.LENGTH_SHORT).show();
            siteview.loadUrl("https://clwo.eu/thread-1614.html");

            // Disabled because Sourcebans does not work well with chrome-like browsers currently
//        } else if (id == R.id.nav_ttt_sourcebans) {
//            // TODO TTT SourceBans
//            // Player Profiles
//            Toast.makeText(getApplicationContext(), "Loading: Player Profiles",
//                    Toast.LENGTH_SHORT).show();
//            siteview.loadUrl("https://clwo.eu/ttt/sourcebans/");

        } else if (id == R.id.nav_wallpaper) {
            // Wallpaper
            wallpaper_view.setVisibility(VISIBLE);
            button.setVisibility(VISIBLE);
            wallpaper_view.setAlpha(1f);
        }

        // Enable the webview if needed
        if(!(id == R.id.nav_wallpaper || id == R.id.nav_status || id == R.id.nav_forums)){
            siteview.setVisibility(VISIBLE);
        }

        // Close the sidebar/drawer
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    // Loads the info for the server status page
    private void getComments() {

        servernames.clear();
        player_count_labels.clear();
        status_images.clear();
        game_images.clear();
        map_names.clear();
        game_dirs.clear();
        pings.clear();
        alert_icons.clear();

        ImageView wallpaper_view = findViewById(R.id.wallpaper_view);
        RecyclerView recycler = findViewById(R.id.recycler);
        wallpaper_view.setVisibility(VISIBLE);
        recycler.setVisibility(VISIBLE);
        wallpaper_view.setAlpha(0.5f);

        Call<NetworkQueryResult> call = networkQueryApi
                .getComments();
        call.enqueue(new Callback<NetworkQueryResult>() {
            @Override
            public void onResponse(Call<NetworkQueryResult> call,
                                   Response<NetworkQueryResult> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                NetworkQueryResult comment = response.body();
                for (int i = 0; i < comment.getData().size(); i++) {
                    if (!(comment.getData().get(i).getLastSeen() == null)) {

                        int currentplayercount = Integer.parseInt(comment.getData().get(i)
                                .getQrd().getInfo().getNumberOfPlayers());
                        int maximumplayercount = Integer.parseInt(comment.getData().get(i)
                                .getMaxPlayers());
                        String server_ping = String.valueOf(comment.getData().get(i)
                                .getQrd().getPing());
                        String map = comment.getData().get(i).getMapDisplayName();
                        String servername = comment.getData().get(i).getShortName();

                        if(Integer.parseInt(comment.getData().get(i).getLastSeen()) < 100){
                            servernames.add(servername);
                            player_count_labels.add("Players: " + currentplayercount + " / "
                                    + maximumplayercount);
                            status_images.add(success);
                            game_images.add(csgo);
                            game_dirs.add("Counter-Strike: Global Offensive");
                            map_names.add(map);
                            pings.add(server_ping + " ms");
                            alert_icons.add(csgo);
                        } else {
                            servernames.add(servername);
                            player_count_labels.add("Players" + 0 + " / " + maximumplayercount);
                            status_images.add(fail);
                            game_images.add(fail);
                            game_dirs.add("Counter-Strike: Global Offensive");
                            map_names.add(getString(R.string.unknown));
                            pings.add(getString(R.string.unknown));
                            alert_icons.add(fail);
                        }
                    }
                }
                initRecyclerView();
            }
            @Override
            public void onFailure(Call<NetworkQueryResult> call, Throwable t) {

                servernames.add(getString(R.string.apiError));
                player_count_labels.add(getString(R.string.unableToFetch));
                status_images.add(fail);
                game_images.add(fail);
                map_names.add(getString(R.string.unableToFetch));
                game_dirs.add(getString(R.string.unableToFetch));
                pings.add(getString(R.string.unableToFetch));
                alert_icons.add(fail);
                initRecyclerView();
            }
        });
    }

    private void initRecyclerView(){

        RecyclerView recycler = findViewById(R.id.recycler);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(
                servernames, player_count_labels, status_images, game_images, this );
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void wallpaper_alert_interface(){
        Toast.makeText(getApplicationContext(), R.string.setting_wallpapers,
                Toast.LENGTH_SHORT).show();
        WallpaperManager wallmgr = WallpaperManager.getInstance(getApplicationContext());
        Bitmap wallpaper = BitmapFactory.decodeResource(getResources(),R.raw.clwo_smartphone);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels + 20;
        Bitmap resized_wallpaper = Bitmap.createScaledBitmap(wallpaper, width, height,true);
        try {
            wallmgr.setBitmap(resized_wallpaper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.navisense;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private String currentScreen = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    private void open(int layoutId, String screenName) {
        currentScreen = screenName;
        setContentView(layoutId);
        bindCommonBack();
    }

    private void bindCommonBack() {
        View back = findViewById(R.id.btnBack);
        if (back != null) back.setOnClickListener(v -> showHome());
    }

    private void showHome() {
        currentScreen = "home";
        setContentView(R.layout.activity_main);

        Button search = findViewById(R.id.btnSearch);
        Button qr = findViewById(R.id.btnScanQr);
        Button facilities = findViewById(R.id.btnFacilities);
        Button nav = findViewById(R.id.btnNavigate);
        Button friend = findViewById(R.id.btnFriend);
        Button offers = findViewById(R.id.btnOffers);
        EditText shopSearch = findViewById(R.id.etShopSearch);

        search.setOnClickListener(v -> {
            String query = shopSearch.getText().toString().trim();
            if (query.isEmpty()) {
                Toast.makeText(this, "Enter a shop or brand name", Toast.LENGTH_SHORT).show();
            } else {
                open(R.layout.activity_search, "search");
                TextView result = findViewById(R.id.tvSearchResult);
                result.setText("Sample result for: " + query + "\n\nActual mall shop data will be connected later.");
            }
        });
        qr.setOnClickListener(v -> open(R.layout.activity_scan_qr, "qr"));
        facilities.setOnClickListener(v -> open(R.layout.activity_facilities, "facilities"));
        nav.setOnClickListener(v -> open(R.layout.activity_navigation, "navigation"));
        friend.setOnClickListener(v -> open(R.layout.activity_friend, "friend"));
        offers.setOnClickListener(v -> {
            Toast.makeText(this, "Nearby offers will be shown here.", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onBackPressed() {
        if (!"home".equals(currentScreen)) showHome();
        else super.onBackPressed();
    }
}

package com.example.zivdrag.contactapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private static final int PERMISSION_REQ = 100;

    private RecyclerView mRecyclerView;
    private List<ContactPeople> mList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProviderClass mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        loadContacts();
    }
    private void initComponent() {
        mRecyclerView = findViewById(R.id.recyclerviewContacts);
        mProvider = new ProviderClass(this);
    }

    private void loadContacts() {
        // Checking the Android SDK version and if permision is granted (minimum sdk version 23)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQ);

        } else {
            mList.addAll(mProvider.getContactsDisplay());
            mAdapter = new ContactsRecyclerAdapter(mList,this, getSupportFragmentManager());
            mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSION_REQ) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is OK
                loadContacts();
            } else {
                Snackbar.make(findViewById(android.R.id.content), "You need to grant permission",Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
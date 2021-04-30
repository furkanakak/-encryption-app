package com.example.encryptionapp.viewactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.encryptionapp.viewfragment.FragmentShowCreditcards;
import com.example.encryptionapp.viewfragment.FragmentShowNotes;
import com.example.encryptionapp.viewfragment.FragmentShowPhones;
import com.example.encryptionapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class EnterActivity extends NavigationDrawer {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    public ArrayList<Fragment> fragmentList = new ArrayList<>();
    public ArrayList<String> fragmentHeaderList = new ArrayList<>();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(this);
        View myview = inflater.inflate(R.layout.activity_enter,null,false);
        drawerLayout.addView(myview,0);

        toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("MAIN Activity");
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewpager2);

        fragmentList.add(new FragmentShowNotes());
        fragmentList.add(new FragmentShowPhones());
        fragmentList.add(new FragmentShowCreditcards());

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        fragmentHeaderList.add("Notes");
        fragmentHeaderList.add("Phones");
        fragmentHeaderList.add("Creditcards");


        new TabLayoutMediator(tabLayout,viewPager2,
                ((tab, position) -> tab.setText(fragmentHeaderList.get(position)))).attach();
        tabLayout.getTabAt(0).setIcon(R.drawable.noteadd);
        tabLayout.getTabAt(1).setIcon(R.drawable.phoneadd);
        tabLayout.getTabAt(2).setIcon(R.drawable.creditcardadd);


    }

private class MyViewPagerAdapter extends FragmentStateAdapter{

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) { return fragmentList.get(position); }

    @Override
    public int getItemCount() { return fragmentList.size(); }
}





}

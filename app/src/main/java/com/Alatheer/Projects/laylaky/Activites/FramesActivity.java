package com.Alatheer.Projects.laylaky.Activites;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.Alatheer.Projects.laylaky.Adapter.ViewPagerAdapter;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Classic;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Custom;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Pinboard;
import com.Alatheer.Projects.laylaky.Fragments.Fragment_Poster;
import com.Alatheer.Projects.laylaky.R;
import java.util.ArrayList;
import java.util.List;

public class FramesActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager pager;
    private List<Integer> icons;
    private List<Integer> icons_color;

    private List<String> titles;
    private ViewPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private String user_id="",id_offer="";
    private int album_size=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frames);
        initView();

    }

    private void initView() {

        getDataFromIntent();
        fragmentList = new ArrayList<>();
        icons = new ArrayList<>();
        icons_color = new ArrayList<>();
        titles = new ArrayList<>();
        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),this);
        AddFragments();
        adapter.AddFragment(fragmentList);
        adapter.AddTilte(titles);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);
        setUpTab();

        for(int i=0; i < tab.getTabCount(); i++) {
            View tab = ((ViewGroup) FramesActivity.this.tab.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            if (i==0)
            {
                p.setMargins(10, 0, 10, 0);

            }else
                {
                    p.setMargins(0, 0, 10, 0);

                }
            tab.requestLayout();
        }

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0)
                {
                    TextView textView = FramesActivity.this.tab.getTabAt(0).getCustomView().findViewById(R.id.title);
                    ImageView icon =  FramesActivity.this.tab.getTabAt(0).getCustomView().findViewById(R.id.icon);
                    icon.setImageResource(icons.get(0));
                    textView.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.white));

                    TextView textView2 = FramesActivity.this.tab.getTabAt(1).getCustomView().findViewById(R.id.title);
                    ImageView icon2 =  FramesActivity.this.tab.getTabAt(1).getCustomView().findViewById(R.id.icon);
                    icon2.setImageResource(icons_color.get(1));
                    textView2.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));

                    TextView textView3 = FramesActivity.this.tab.getTabAt(2).getCustomView().findViewById(R.id.title);
                    ImageView icon3 =  FramesActivity.this.tab.getTabAt(2).getCustomView().findViewById(R.id.icon);
                    icon3.setImageResource(icons_color.get(2));
                    textView3.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));


                    TextView textView4 = FramesActivity.this.tab.getTabAt(3).getCustomView().findViewById(R.id.title);
                    ImageView icon4 =  FramesActivity.this.tab.getTabAt(3).getCustomView().findViewById(R.id.icon);
                    icon4.setImageResource(icons_color.get(3));
                    textView4.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));


                }else if (position==1)
                {
                    TextView textView = FramesActivity.this.tab.getTabAt(1).getCustomView().findViewById(R.id.title);
                    ImageView icon =  FramesActivity.this.tab.getTabAt(1).getCustomView().findViewById(R.id.icon);
                    icon.setImageResource(icons.get(1));
                    textView.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.white));

                    TextView textView2 = FramesActivity.this.tab.getTabAt(0).getCustomView().findViewById(R.id.title);
                    ImageView icon2 =  FramesActivity.this.tab.getTabAt(0).getCustomView().findViewById(R.id.icon);
                    icon2.setImageResource(icons_color.get(0));
                    textView2.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));

                    TextView textView3 = FramesActivity.this.tab.getTabAt(2).getCustomView().findViewById(R.id.title);
                    ImageView icon3 =  FramesActivity.this.tab.getTabAt(2).getCustomView().findViewById(R.id.icon);
                    icon3.setImageResource(icons_color.get(2));
                    textView3.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));

                    TextView textView4 = FramesActivity.this.tab.getTabAt(3).getCustomView().findViewById(R.id.title);
                    ImageView icon4 =  FramesActivity.this.tab.getTabAt(3).getCustomView().findViewById(R.id.icon);
                    icon4.setImageResource(icons_color.get(3));
                    textView4.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));


                }
                else if (position==2)
                {
                    TextView textView = FramesActivity.this.tab.getTabAt(2).getCustomView().findViewById(R.id.title);
                    ImageView icon =  FramesActivity.this.tab.getTabAt(2).getCustomView().findViewById(R.id.icon);

                    icon.setImageResource(icons.get(2));
                    textView.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.white));

                    TextView textView2 = FramesActivity.this.tab.getTabAt(0).getCustomView().findViewById(R.id.title);
                    ImageView icon2 =  FramesActivity.this.tab.getTabAt(0).getCustomView().findViewById(R.id.icon);
                    icon2.setImageResource(icons_color.get(0));
                    textView2.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));

                    TextView textView3 = FramesActivity.this.tab.getTabAt(1).getCustomView().findViewById(R.id.title);
                    ImageView icon3 =  FramesActivity.this.tab.getTabAt(1).getCustomView().findViewById(R.id.icon);
                    icon3.setImageResource(icons_color.get(1));
                    textView3.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));


                    TextView textView4 = FramesActivity.this.tab.getTabAt(3).getCustomView().findViewById(R.id.title);
                    ImageView icon4 =  FramesActivity.this.tab.getTabAt(3).getCustomView().findViewById(R.id.icon);
                    icon4.setImageResource(icons_color.get(3));
                    textView4.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));

                }else if (position==3)
                {
                    TextView textView = FramesActivity.this.tab.getTabAt(3).getCustomView().findViewById(R.id.title);
                    ImageView icon =  FramesActivity.this.tab.getTabAt(3).getCustomView().findViewById(R.id.icon);

                    icon.setImageResource(icons.get(3));
                    textView.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.white));

                    TextView textView2 = FramesActivity.this.tab.getTabAt(0).getCustomView().findViewById(R.id.title);
                    ImageView icon2 =  FramesActivity.this.tab.getTabAt(0).getCustomView().findViewById(R.id.icon);
                    icon2.setImageResource(icons_color.get(0));
                    textView2.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));

                    TextView textView3 = FramesActivity.this.tab.getTabAt(1).getCustomView().findViewById(R.id.title);
                    ImageView icon3 =  FramesActivity.this.tab.getTabAt(1).getCustomView().findViewById(R.id.icon);
                    icon3.setImageResource(icons_color.get(1));
                    textView3.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));


                    TextView textView4 = FramesActivity.this.tab.getTabAt(2).getCustomView().findViewById(R.id.title);
                    ImageView icon4 =  FramesActivity.this.tab.getTabAt(2).getCustomView().findViewById(R.id.icon);
                    icon4.setImageResource(icons_color.get(2));
                    textView4.setTextColor(ContextCompat.getColor(FramesActivity.this,R.color.colorPrimary));

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            album_size = Integer.parseInt(intent.getStringExtra("album_size"));
            user_id = intent.getStringExtra("user_id");
            id_offer = intent.getStringExtra("id_offer");

            Log.e("Frames albumsize",album_size+"");
            Log.e("Frames id",user_id+"");
            Log.e("Frames offer",id_offer+"");

        }
    }

    private void AddFragments() {
        fragmentList.add(Fragment_Classic.getInstance(user_id,id_offer,album_size));
        fragmentList.add(Fragment_Poster.getInstance(user_id,id_offer,album_size));
        fragmentList.add(Fragment_Pinboard.getInstance(user_id,id_offer,album_size));
        fragmentList.add(Fragment_Custom.getInstance(user_id,id_offer,album_size));

        icons.add(R.drawable.classic);
        icons.add(R.drawable.poster);
        icons.add(R.drawable.pinboard);
        icons.add(R.drawable.icon_white);

        icons_color.add(R.drawable.classic1);
        icons_color.add(R.drawable.poster3);
        icons_color.add(R.drawable.pinboard2);
        icons_color.add(R.drawable.icon_color);

        titles.add("Classic");
        titles.add("Poster");
        titles.add("Pinboard");
        titles.add("Free style");


    }

    private void setUpTab() {


        View view1 = LayoutInflater.from(this).inflate(R.layout.classic_tab_view,null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.classic_tab_view,null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.classic_tab_view,null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.classic_tab_view,null);

        TextView title1 = view1.findViewById(R.id.title);
        ImageView icon = view1.findViewById(R.id.icon);
        title1.setText(titles.get(0));
        title1.setTextColor(ContextCompat.getColor(this,R.color.white));

        icon.setImageResource(icons.get(0));
        tab.getTabAt(0).setCustomView(view1);

        TextView title2 = view2.findViewById(R.id.title);
        ImageView icon2 = view2.findViewById(R.id.icon);
        icon2.setImageResource(icons_color.get(1));
        title2.setText(titles.get(1));
        title2.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
        tab.getTabAt(1).setCustomView(view2);

        TextView title3 = view3.findViewById(R.id.title);
        ImageView icon3 = view3.findViewById(R.id.icon);
        icon3.setImageResource(icons_color.get(2));

        title3.setText(titles.get(2));
        title3.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));

        tab.getTabAt(2).setCustomView(view3);


        TextView title4 = view4.findViewById(R.id.title);
        ImageView icon4 = view4.findViewById(R.id.icon);
        icon4.setImageResource(icons_color.get(3));

        title4.setText(titles.get(3));
        title4.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));

        tab.getTabAt(3).setCustomView(view4);






    }
}

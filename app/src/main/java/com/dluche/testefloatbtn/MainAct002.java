package com.dluche.testefloatbtn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import ctrls.FloatMenuItem;
import ctrls.MyFabMenu;

/**
 * Created by d.luche on 17/10/2017.
 */

public class MainAct002 extends AppCompatActivity {

    private LinearLayout ll_menu_fab;
    private FloatingActionButton fab;
    private MyFabMenu fabMenu;
    private FloatingActionButton fabFinalize;
    private FloatingActionButton fabSave;
    private FloatingActionButton fabRemove;
    private Animation animationFabOpen;
    private Animation animationFabClose;
    private boolean isFabMenuOpen = false;
    private ArrayList<FloatingActionButton> fabList = new ArrayList<>();
    private FloatMenuItem floatMenuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_main_2);

        iniVar();

        iniAction();


    }

    private void iniAction() {
        //fabMenu = new MyFabMenu(getApplicationContext());
        //
        //fabMenu.setIconClosed(R.drawable.ic_add_black_24dp);
        fabMenu.setIconClosed(R.drawable.ic_all_inclusive_black_24dp);
        //
        //fabMenu.setIconOpened(R.drawable.ic_keyboard_arrow_down_black_24dp);
        fabMenu.setIconOpened(R.drawable.ic_close_black_24dp);

        //
        fabList.add(fabFinalize);
        //
        fabList.add(fabSave);
        //
        fabList.add(fabRemove);
        //
        fabMenu.setFabItemList(fabList);
        //
        floatMenuItem.setmItem_icon(R.drawable.ic_battery_charging_full_black_24dp);
        floatMenuItem.setmItem_icon_color(R.color.color_seila3);
        floatMenuItem.setmItem_bg_icon_color(R.color.color_dark_avg);
        floatMenuItem.setmItem_bg_label_color(R.color.color_grayalpha);

    }

    private void iniVar() {

        ll_menu_fab = (LinearLayout) findViewById(R.id.main_002_ll_menu);
        //
        fab = (FloatingActionButton) findViewById(R.id.main002_fab);
        //
        fabFinalize = (FloatingActionButton) findViewById(R.id.main002_menu_finaliza);
        //
        fabSave = (FloatingActionButton) findViewById(R.id.main002_menu_save);
        //
        fabRemove = (FloatingActionButton) findViewById(R.id.main002_menu_remove);
        //
        fabMenu = (MyFabMenu) findViewById(R.id.main002_myfab);
        //
        floatMenuItem = (FloatMenuItem) findViewById(R.id.main002_fab_item);



    }

    private void iniActionOld() {

        animationFabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btn_open);
        animationFabOpen.setAnimationListener(animationListener);
        animationFabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btn_close);
        animationFabClose.setAnimationListener(animationListener);
        //
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainAct002.this, "FabClicado", Toast.LENGTH_SHORT).show();
//                if(ll_menu_fab.getVisibility() == View.GONE){
//                    ll_menu_fab.startAnimation(animationFabOpen);
//                    fab.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
//                }else{
//                    ll_menu_fab.setVisibility(View.GONE);
//                    fab.setImageDrawable(getDrawable(R.drawable.ic_add_black_24dp));
//                }

                if(isFabMenuOpen){
                    isFabMenuOpen = false;

                    //
                    fabRemove.startAnimation(animationFabClose);
                    fabSave.startAnimation(animationFabClose);
                    fabFinalize.startAnimation(animationFabClose);
                    /*
                    * O setVisibility(View.GONE) deve ser feito no "evento"
                    * onAnimationEnd do Animation.AnimationListener ,pois
                    * os itens só deve ser removidos após completar a animação.
                    * */
                    fab.setImageDrawable(getDrawable(R.drawable.ic_add_black_24dp));
                }else{
                    isFabMenuOpen = true;

                    fabRemove.setVisibility(View.VISIBLE);
                    fabSave.setVisibility(View.VISIBLE);
                    fabFinalize.setVisibility(View.VISIBLE);
                    //
                    fabRemove.startAnimation(animationFabOpen);
                    fabSave.startAnimation(animationFabOpen);
                    fabFinalize.startAnimation(animationFabOpen);
                    //
                    fab.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
                    //
                    //fab.setBackgroundTintList(getColorStateList(R.color.dark_blue_states));
                }

            }
        });
        //

        fabFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Finalize",Toast.LENGTH_SHORT).show();
            }
        });
        //
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_SHORT).show();
            }
        });
        //
        fabRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Remove",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(!isFabMenuOpen){
                //ll_menu_fab.setVisibility(View.GONE);
                fabRemove.setVisibility(View.GONE);
                fabSave.setVisibility(View.GONE);
                fabFinalize.setVisibility(View.GONE);
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

}

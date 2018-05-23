package ctrls;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.dluche.testefloatbtn.R;

import java.util.ArrayList;

/**
 * Created by d.luche on 17/10/2017.
 */

public class MyFabMenu extends FloatingActionButton implements View.OnClickListener {

    private Context context;
    private ArrayList<FloatingActionButton> fabItemList;
    private boolean isMenuOpen;
    private Animation animationShow;
    private Animation animationHide;
    private Animation.AnimationListener animationListener;
    private int iconClosed;
    private int iconOpened;

    public MyFabMenu(Context context) {
        super(context);
        //
        initialize(context, null);
    }

    public MyFabMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        initialize(context, attrs);
    }

    public MyFabMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //
        initialize(context, attrs);
    }

    private void initialize(Context context,@Nullable AttributeSet attrs) {
        this.context = context;
        //
        setDefaultValues();
        //
        configAnimation();
        //
        this.setOnClickListener(this);
    }

    private void setDefaultValues() {
        //
        fabItemList = new ArrayList<>();
        //
        isMenuOpen = false;
        //
        iconClosed = R.drawable.ic_add_black_24dp;
        //
        iconOpened = R.drawable.ic_keyboard_arrow_down_black_24dp;
    }

    private void configAnimation() {
        //Animação de exibição
        animationShow = AnimationUtils.loadAnimation(context, R.anim.btn_open);
        animationShow.setAnimationListener(animationListener);
        //Animação de esconder
        animationHide = AnimationUtils.loadAnimation(context,R.anim.btn_close);
        animationHide.setAnimationListener(animationListener);
        //
        //Listner da Animação
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!isMenuOpen){
                    for (int i = 0; i < fabItemList.size() ; i++) {
                        fabItemList.get(i).setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

    }

    @Override
    public void onClick(View view) {

        if(isMenuOpen){
            isMenuOpen = false;
            //Seta icone fechado
            this.setImageDrawable(context.getDrawable(iconClosed));
            //
            //Seta animação de esconder.
            //A visibilidade só é alterado no evento onAnimationEnd
            //no listner da animação
            for (int i = 0; i < fabItemList.size(); i++) {
                fabItemList.get(i).startAnimation(animationHide);
            }

        }else{
            isMenuOpen = true;
            //Set icone de aberto.
            this.setImageDrawable(context.getDrawable(iconOpened));
            //Seta exibição e animação de exibição.
            for (int i = 0; i < fabItemList.size(); i++) {
                fabItemList.get(i).setVisibility(View.VISIBLE);
                fabItemList.get(i).startAnimation(animationShow);
            }
        }
    }

    public int getIconClosed() {
        return iconClosed;
    }

    public void setIconClosed(int iconClosed) {
        this.setImageDrawable(context.getDrawable(iconClosed));
        this.iconClosed = iconClosed;
    }

    public int getIconOpened() {
        return iconOpened;
    }

    public void setIconOpened(int iconOpened) {
        this.iconOpened = iconOpened;
    }

    public ArrayList<FloatingActionButton> getFabItemList() {
        return fabItemList;
    }

    public void setFabItemList(ArrayList<FloatingActionButton> fabItemList) {
        this.fabItemList = fabItemList;
    }
}

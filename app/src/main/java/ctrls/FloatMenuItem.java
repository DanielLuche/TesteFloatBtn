package ctrls;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dluche.testefloatbtn.R;

public class FloatMenuItem extends LinearLayout {

    private Context context;
    private TextView tv_item_label;
    private FloatingActionButton fab_item_icon;
    private String mItem_lbl;
    private int mItem_icon;
    private int mItem_icon_color;
    private int mItem_bg_label_color;
    private int mItem_bg_icon_color;
    private int mItem_icon_size;

    public FloatMenuItem(Context context) {
        super(context);
        initialize(context, null);
    }

    public FloatMenuItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    private void initialize(Context context, @Nullable AttributeSet attrs) {
        this.context = context;
        //
        setDefaultValues();
        //
        if (attrs != null) {
            TypedArray a = this.context.obtainStyledAttributes(attrs,
                    R.styleable.FloatMenuItem, 0, 0);
            final int N = a.getIndexCount();

            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);

                switch (attr) {
                    case R.styleable.FloatMenuItem_mItemLabel:
                        mItem_lbl = a.getString(attr);
                        break;
                    /*case R.styleable.FloatMenuItem_mIcon:
                        mItem_icon =  a.getDrawable(attr);
                        break;*/
                    /*case R.styleable.FloatMenuItem_mIconColor:
                        mItem_icon_color = a.getInt(attr, R.color.colorAccent);
                        break;
                    case R.styleable.FloatMenuItem_mBackgroundLabelColor:
                        mItem_bg_label_color = a.getInt(attr, Color.parseColor("#22000000"));
                        break;
                    case R.styleable.FloatMenuItem_mBackgroundIconColor:
                        mItem_bg_icon_color = a.getInt(attr, R.color.colorPrimary);
                        break;*/
                    case R.styleable.FloatMenuItem_mIconSize:
                        mItem_icon_size = a.getInt(attr,FloatingActionButton.SIZE_MINI);
                        break;
                }
            }
            a.recycle();
        }
    }

    private void setDefaultValues() {
        mItem_lbl = "Label - Trad";
        //
        mItem_icon = R.drawable.ic_keyboard_arrow_down_black_24dp;
        //
        mItem_icon_color = R.color.colorAccent;
        mItem_bg_icon_color = R.color.color_seila;//R.color.colorPrimary;
        mItem_icon_size = FloatingActionButton.SIZE_MINI;
        //
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.float_menu_item_layout, this);

        tv_item_label = (TextView) findViewById(R.id.float_menu_item_tv_label);
        fab_item_icon = (FloatingActionButton) findViewById(R.id.float_menu_item_fab_btn);
    }

    @Override
    protected void onAttachedToWindow() {
        setControlValues();

        super.onAttachedToWindow();
    }

    private void setControlValues() {
        tv_item_label.setText(mItem_lbl);
        tv_item_label.setBackgroundColor(mItem_bg_label_color);
        //
        Drawable icDrawable = getResources().getDrawable(mItem_icon);
        icDrawable.setColorFilter(getResources().getColor(mItem_icon_color), PorterDuff.Mode.SRC_ATOP);
        fab_item_icon.setImageDrawable(icDrawable);
        fab_item_icon.setBackgroundTintList(ColorStateList.valueOf(mItem_bg_icon_color));
        fab_item_icon.setSize(FloatingActionButton.SIZE_MINI);
        fab_item_icon.setUseCompatPadding(true);

    }

    public String getmItem_lbl() {
        return mItem_lbl;
    }

    public void setmItem_lbl(String mItem_lbl) {
        this.mItem_lbl = mItem_lbl;
    }

    public int getmItem_icon() {
        return mItem_icon;
    }

    public void setmItem_icon(int mItem_icon) {
        this.mItem_icon = mItem_icon;
    }

    public int getmItem_bg_label_color() {
        return mItem_bg_label_color;
    }

    public void setmItem_bg_label_color(int mItem_bg_label_color) {
        this.mItem_bg_label_color = mItem_bg_label_color;
    }

    public int getmItem_icon_color() {
        return mItem_icon_color;
    }

    public void setmItem_icon_color(int mItem_icon_color) {
        this.mItem_icon_color = mItem_icon_color;
    }

    public int getmItem_bg_icon_color() {
        return mItem_bg_icon_color;
    }

    public void setmItem_bg_icon_color(int mItem_bg_icon_color) {
        this.mItem_bg_icon_color = mItem_bg_icon_color;
    }

    public int getmItem_icon_size() {
        return mItem_icon_size;
    }

    public void setmItem_icon_size(int mItem_icon_size) {
        this.mItem_icon_size = mItem_icon_size;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss._Item_lbl = mItem_lbl;
        ss._Item_icon = mItem_icon;
        ss._Item_icon_color = mItem_icon_color;
        ss._Item_bg_label_color = mItem_bg_label_color;
        ss._Item_bg_icon_color = mItem_bg_icon_color;
        ss._Item_icon_size = mItem_icon_size;
        //
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        //
        setmItem_lbl(ss._Item_lbl);
        setmItem_icon(ss._Item_icon);
        setmItem_icon_color(ss._Item_icon_color);
        setmItem_bg_label_color(ss._Item_bg_label_color);
        setmItem_bg_icon_color(ss._Item_bg_icon_color);
        setmItem_icon_size(ss._Item_icon_size);
    }

    private static class SavedState extends BaseSavedState {

        private String _Item_lbl;
        private int _Item_icon;
        private int _Item_icon_color;
        private int _Item_bg_label_color;
        private int _Item_bg_icon_color;
        private int _Item_icon_size;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel in) {
            super(in);
            _Item_lbl = in.readString();
            _Item_icon = in.readInt();
            _Item_icon_color = in.readInt();
            _Item_bg_label_color = in.readInt();
            _Item_bg_icon_color = in.readInt();
            _Item_icon_size = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(_Item_lbl);
            out.writeInt(_Item_icon);
            out.writeInt(_Item_icon_color);
            out.writeInt(_Item_bg_label_color);
            out.writeInt(_Item_bg_icon_color);
            out.writeInt(_Item_icon_size);
        }

        public static final Creator<SavedState> CREATOR
                = new Creator<FloatMenuItem.SavedState>() {
            public FloatMenuItem.SavedState createFromParcel(Parcel in) {
                return new FloatMenuItem.SavedState(in);
            }

            public FloatMenuItem.SavedState[] newArray(int size) {
                return new FloatMenuItem.SavedState[size];
            }
        };
    }

}

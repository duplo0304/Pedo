package com.projektarbeit.duplo.pedo;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WearableListItemLayout extends LinearLayout
        implements WearableListView.OnCenterProximityListener {

    private ImageView mCircle;
    private TextView mName;

    private final float mFadedTextAlpha;
    private final int mFadedCircleColor;
    private final int mChosenCircleColor;

    public WearableListItemLayout(Context context) {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);

        mFadedTextAlpha = 40 / 100f;
        mFadedCircleColor = getResources().getColor(R.color.grey);
        mChosenCircleColor = getResources().getColor(R.color.blue);
    }


    /*******************************************************************************************
     *  Herstellen der Referenz für Icon und Text; gesetzt in Item-Layout Definition
     *******************************************************************************************/
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // Finden entsprechender Views in Layout-File über ID
        mCircle = (ImageView) findViewById(R.id.circle);
        mName = (TextView) findViewById(R.id.name);
    }


    /******************************************************************************************
     *  Methoden für Animation der WearableListViews:
     *  onCenterPosition() --> fokussiertes Element wird farblich hervorgehoben
     *  onNonCenterPosition() --> nicht-fokussierte Elemente wird grau gesetzt
     *  @param animate
     *****************************************************************************************/
    @Override
    public void onCenterPosition(boolean animate) {
        mName.setAlpha(1f);
        ((GradientDrawable) mCircle.getDrawable()).setColor(mChosenCircleColor);
    }

    @Override
    public void onNonCenterPosition(boolean animate) {
        ((GradientDrawable) mCircle.getDrawable()).setColor(mFadedCircleColor);
        mName.setAlpha(mFadedTextAlpha);
    }
}

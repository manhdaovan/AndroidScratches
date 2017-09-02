package com.manhdaovan.block01;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonBlue, buttonPink;
    ConstraintLayout mainLayout;

    /**
     * This lesson show the methods how to add an action to view
     */

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = fetchMainLayout();

        buttonBlue = fetchBlueButton();
        addActionToButton(buttonBlue);

        buttonPink = fetchPinkButton();
        addActionToButton(buttonPink);

        addRedButton();
    }

    /**
     * Firs† method:
     * Define android:onClick="methodName"
     * then define methodName content in activity
     */

    /**
     * Source code for first method
     *
     private void addActionToButton(){}
     private void addRedButton(){}
     */


    /**
     * Second method:
     * Add action to view directly in activity instead of defining it in layout
     * by implements View.OnClickListener and override onClick method.
     */

    /**
     * Source code for second method
     *
     private void addRedButton(){}
     private void addActionToButton(Button btn){
        btn.setOnClickListener(this);
     }

     @Override public void onClick(View v) {
         toDo(v);
     }
     */


    /**
     * Third method:
     * Create button directly in activity (JAVA code) and add it to layout
     */

    /**
     * Source code for third method
     */
    private void addActionToButton(Button btn) {
        btn.setOnClickListener(this);
    }

    private void addRedButton() {
        Button redButton = createRedButton();
        addActionToButton(redButton);
        mainLayout.addView(redButton);

        // Set Red button position
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mainLayout);
        constraintSet.connect(redButton.getId(), ConstraintSet.TOP, buttonPink.getId(), ConstraintSet.BOTTOM, 72);
        constraintSet.constrainDefaultHeight(redButton.getId(), 200);
        constraintSet.applyTo(mainLayout);
    }

    private Button createRedButton() {
        Button buttonRed = new Button(getApplicationContext());
        // TODO: use i18n here
        buttonRed.setText("Red Button");
        buttonRed.setTextColor(Color.WHITE);
        buttonRed.setBackgroundColor(Color.RED);

        return buttonRed;
    }

    /**
     * End for third method
     */

    @Override
    public void onClick(View v) {
        toDo(v);
    }

    public void toDo(View v) {
        if (isBlueBtn(v)) {
            v.setVisibility(View.INVISIBLE);
        }
        if (isPinkBtn(v)) {
            makeToast("Text displaying from Toast").show();
            buttonBlue.setVisibility(View.VISIBLE);
        }
        if (isRedButton((Button) v)) {
            makeToast("Tex† display when press on Red button").show();
        }
    }

    private Button fetchBlueButton() {
        return (Button) findViewById(R.id.button_blueInvisible);
    }

    private Button fetchPinkButton() {
        return (Button) findViewById(R.id.button_pinkPanther);
    }

    private ConstraintLayout fetchMainLayout() {
        return (ConstraintLayout) findViewById(R.id.constraintLayout_mainLayout);
    }

    private boolean isBlueBtn(View v) {
        return v.equals(buttonBlue);
    }

    private boolean isPinkBtn(View v) {
        return v.equals(buttonPink);
    }

    private boolean isRedButton(Button btn) {
        // This is a bad practice,
        // but i don't know other way at this time :(
        return btn.getText().toString().equals("Red Button");
    }

    private Toast makeToast(String message) {
        return Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    }
}

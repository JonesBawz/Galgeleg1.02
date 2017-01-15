package com.example.derp.galgeleg102;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class GalgelegSpilActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private TextView textView, textView2;
    Galgelogik logik;
    private EditText editText;
    String str = "";
    private int[] img;
    private String letters = "";
    private Fragment fragmentGameEnd = new fragmentGameEnd();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_spil);

                logik = new Galgelogik();
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String s = (String) bundle.get("word");
            logik.setOrdet(s);
        logik.opdaterSynligtOrd();
                this.imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.galge);

                this.textView2 = (TextView) findViewById(R.id.textView2);


                this.textView = (TextView) findViewById(R.id.textView);
                textView.setText(logik.getSynligtOrd());

                this.editText = (EditText) findViewById(R.id.editText2);
                this.editText.setOnClickListener(this);


                img = new int[]{
                        R.drawable.forkert1, R.drawable.forkert2, R.drawable.forkert3, R.drawable.forkert4, R.drawable.forkert5, R.drawable.forkert6,
                };





                //editText.setOnClickListener(new View.OnClickListener());

        }
        @Override
        public void onClick(View v) {
            str = "";
            String s1 = editText.getText().toString();
            logik.gætBogstav(s1);
            if (editText.getText().toString().length() != 1 || letters.contains(s1)) {
            }
            else {

                str = editText.getText().toString();
                textView.setText(logik.getSynligtOrd());
                String s = textView2.getText().toString();
                letters = letters + str;
                s = s + " " + str;
                textView2.setText(s);
                System.out.println(str);

                for (int i = 0; i < img.length; i++) {
                    if (logik.getAntalForkerteBogstaver() == i + 1)
                        imageView.setImageResource(img[i]);
                }
                if(logik.erSpilletTabt()){
                    startFragment("Du har tabt spillet\nDet rigtige ord var " + logik.getOrdet());
                }
                if(logik.erSpilletVundet()){
                    startFragment("Du har vundet spillet\nDet rigtige ord var " + logik.getOrdet());
                }


            }

        }
    public void startFragment(String s){
        Bundle bundle = new Bundle();
        String text = s;
        bundle.putString("message", text);
        fragmentGameEnd.setArguments(bundle);
        FragmentManager fm = getFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.activity_spil, fragmentGameEnd);

        transaction.commit();
    }
    }

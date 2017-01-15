package com.example.derp.galgeleg102;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class fragmentGameEnd extends Fragment implements View.OnClickListener {
    private Button button;
    private TextView textView3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_endgame, container, false);

            button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);

        textView3 = (TextView) view.findViewById(R.id.textView3);

        String text = this.getArguments().getString("message");
        textView3.setText(text);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
    }
}

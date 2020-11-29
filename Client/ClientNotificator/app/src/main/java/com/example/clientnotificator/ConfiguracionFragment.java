package com.example.clientnotificator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ConfiguracionFragment extends DialogFragment {

    private TextView txt;
    private Button btn;

    public ConfiguracionFragment() {
    }

    public static ConfiguracionFragment newInstance(String title) {
        ConfiguracionFragment frag = new ConfiguracionFragment();
        Bundle args = new Bundle();
        args.putString("Configuracion", title);
        frag.setArguments(args);
        return frag;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.configuracion, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = view.findViewById(R.id.btnGuardarIP);
        txt = view.findViewById(R.id.txtIP);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(v.getContext().openFileOutput("config.txt", Context.MODE_PRIVATE));
                    outputStreamWriter.write(txt.getText().toString());
                    outputStreamWriter.close();
                    MainActivity mainQuiz;
                    mainQuiz = (MainActivity) getActivity();
                    mainQuiz.url = txt.getText().toString();
                    mainQuiz.stop();
                    mainQuiz.loadData();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getDialog().dismiss();
            }
        });
    }
}

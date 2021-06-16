package de.fh_kiel.iue.mob2021_hw_938055_933041;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private TextView tvText1;
    private TextView tvText2;
    public static String data;
    public static String data1;
    public DetailsFragment() {

        super(R.layout.fragment_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvText1 = view.findViewById(R.id.tvText1);
        tvText2 = view.findViewById(R.id.tvText2);
        tvText1.setText(data);
        tvText2.setText(data1+"€");
    }
    public void SetHeaderText (String headtext)
    {
    tvText1.setText(headtext);
    }
}
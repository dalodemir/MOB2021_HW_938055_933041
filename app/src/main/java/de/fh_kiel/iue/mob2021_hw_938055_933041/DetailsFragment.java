package de.fh_kiel.iue.mob2021_hw_938055_933041;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private TextView detailsTextLeft;
    private TextView detailsTextRight;
    public static String data;
    public static String data1;

    public DetailsFragment() {
        super(R.layout.fragment_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailsTextLeft = view.findViewById(R.id.detailsTextLeft);
        detailsTextRight = view.findViewById(R.id.detailsTextRight);
        detailsTextLeft.setText(data);
        detailsTextRight.setText(data1 + getString(R.string.symbolEuro));
    }

    public void SetHeaderText(String headtext) {
        detailsTextLeft.setText(headtext);
    }
}
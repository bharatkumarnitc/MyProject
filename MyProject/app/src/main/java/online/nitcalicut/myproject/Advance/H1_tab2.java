package online.nitcalicut.myproject.Advance;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import online.nitcalicut.myproject.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link H1_tab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link H1_tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class H1_tab2 extends Fragment {
    public H1_tab2()

    {


        // Required empty public constructor
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_h1_tab2, container, false);
    }


}

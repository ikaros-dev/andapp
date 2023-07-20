package run.ikaros.app.and.fragment.subject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import run.ikaros.app.and.R;
import run.ikaros.app.and.api.subject.model.Subject;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SubjectEpisodesFragment extends Fragment {

    private final Subject subject;


    public SubjectEpisodesFragment(Subject subject) {
        // Required empty public constructor
        this.subject = subject;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject_episodes, container, false);
    }
}
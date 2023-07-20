package run.ikaros.app.and.fragment.subject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import run.ikaros.app.and.R;
import run.ikaros.app.and.api.subject.model.Episode;
import run.ikaros.app.and.api.subject.model.Subject;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SubjectEpisodesFragment extends Fragment {

    private final Subject subject;
    private RecyclerView episodesRecyclerView;
    private EpisodeRecyclerViewAdapter episodeRecyclerViewAdapter;


    public SubjectEpisodesFragment(Subject subject) {
        // Required empty public constructor
        this.subject = subject;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_subject_episodes, container, false);
        episodesRecyclerView = rootView.findViewById(R.id.episodesRecyclerView);
        episodeRecyclerViewAdapter = new EpisodeRecyclerViewAdapter(subject.getEpisodes(), SubjectEpisodesFragment.this);
        episodesRecyclerView.setAdapter(episodeRecyclerViewAdapter);
        episodesRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        return rootView;
    }



    public interface OnEpisodeSelectBtnClickListener {
        void onBtnClick(Episode episode);
    }
    private OnEpisodeSelectBtnClickListener episodeSelectBtnClickListener;

    public void episodeSelectBtnClick(Episode episode) {
        if(episodeSelectBtnClickListener != null) {
            episodeSelectBtnClickListener.onBtnClick(episode);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // 确保父 Activity 实现了接口
        if (context instanceof OnEpisodeSelectBtnClickListener) {
            episodeSelectBtnClickListener = (OnEpisodeSelectBtnClickListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnEpisodeSelectBtnClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        episodeSelectBtnClickListener = null;
    }
}
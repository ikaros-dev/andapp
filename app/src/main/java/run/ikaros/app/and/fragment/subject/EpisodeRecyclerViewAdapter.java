package run.ikaros.app.and.fragment.subject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import run.ikaros.app.and.R;
import run.ikaros.app.and.api.subject.model.Episode;
import run.ikaros.app.and.infra.utils.StringUtils;

public class EpisodeRecyclerViewAdapter extends RecyclerView.Adapter<EpisodeRecyclerViewAdapter.EpisodeViewHolder> {

    private final List<Episode> episodeList;
    private final SubjectEpisodesFragment fragment;

    public EpisodeRecyclerViewAdapter(List<Episode> episodeList, SubjectEpisodesFragment fragment) {
        this.episodeList = episodeList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_episode, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode episode = episodeList.get(position);
        holder.bind(episode, fragment);
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }


    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        private ToggleButton toggleButton;

        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);
            toggleButton = itemView.findViewById(R.id.episodeToggleButton);
        }

        public void bind(Episode episode, SubjectEpisodesFragment subjectEpisodesFragment) {
            String nameCn = episode.getNameCn();
            if (StringUtils.isBlank(nameCn)) {
                nameCn = episode.getName();
            }
            nameCn = episode.getSequence().intValue() + ". " + nameCn;
            toggleButton.setText(nameCn);
            toggleButton.setTextOn(nameCn);
            toggleButton.setTextOff(nameCn);
            toggleButton.setOnClickListener(v -> subjectEpisodesFragment.episodeSelectBtnClick(episode));
        }
    }
}

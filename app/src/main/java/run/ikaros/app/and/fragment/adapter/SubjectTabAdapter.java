package run.ikaros.app.and.fragment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import run.ikaros.app.and.api.subject.model.Subject;
import run.ikaros.app.and.fragment.subject.SubjectEpisodesFragment;
import run.ikaros.app.and.fragment.subject.SubjectMetaFragment;

public class SubjectTabAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 2; // 两个页面，详情和选集
    private static final String[] PAGE_TITLES = {"详情", "选集"};
    private Subject subject;

    public SubjectTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SubjectMetaFragment(subject); // 返回详情页的 Fragment
            case 1:
                return new SubjectEpisodesFragment(subject); // 返回选集页的 Fragment
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}

package run.ikaros.app.and.ui.subject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import run.ikaros.app.and.R;

public class SubjectFragment extends Fragment {
    private RecyclerView subjectRecyclerView;
    private SubjectAdapter subjectAdapter;

    private static final String testTmpUrl
            = "https://lain.bgm.tv/r/400/pic/cover/l/68/ea/333979_lG5Gt.jpg";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject, container, false);

        subjectRecyclerView = view.findViewById(R.id.subjectRecyclerView);
        subjectAdapter = new SubjectAdapter(getSubjectList(), this);
        subjectRecyclerView.setAdapter(subjectAdapter);
        subjectRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        return view;
    }

    private List<Subject> getSubjectList() {
        // 获取番剧数据集合的示例方法
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(new Subject("Bangumi-1", testTmpUrl));
        subjectList.add(new Subject("Bangumi-2", testTmpUrl));
        subjectList.add(new Subject("Bangumi-3", testTmpUrl));
        subjectList.add(new Subject("Bangumi-4", testTmpUrl));
        subjectList.add(new Subject("Bangumi-5", testTmpUrl));
        subjectList.add(new Subject("Bangumi-6", testTmpUrl));
        subjectList.add(new Subject("Bangumi-7", testTmpUrl));
        // ...
        return subjectList;
    }
}

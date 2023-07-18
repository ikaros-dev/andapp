package run.ikaros.app.and.ui.subject;

import static run.ikaros.app.and.constants.TmpConst.SUBJECT_COVER_URL;

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
import run.ikaros.app.and.constants.TmpConst;

public class SubjectFragment extends Fragment {
    private RecyclerView subjectRecyclerView;
    private SubjectAdapter subjectAdapter;

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
        subjectList.add(new Subject("Bangumi-1", SUBJECT_COVER_URL));
        subjectList.add(new Subject("Bangumi-2", SUBJECT_COVER_URL));
        subjectList.add(new Subject("Bangumi-3", SUBJECT_COVER_URL));
        subjectList.add(new Subject("Bangumi-4", SUBJECT_COVER_URL));
        subjectList.add(new Subject("Bangumi-5", SUBJECT_COVER_URL));
        subjectList.add(new Subject("Bangumi-6", SUBJECT_COVER_URL));
        subjectList.add(new Subject("Bangumi-7", SUBJECT_COVER_URL));
        // ...
        return subjectList;
    }
}

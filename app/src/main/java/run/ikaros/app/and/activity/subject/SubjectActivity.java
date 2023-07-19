package run.ikaros.app.and.activity.subject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import run.ikaros.app.and.R;
import run.ikaros.app.and.constants.AppConst;
import run.ikaros.app.and.databinding.ActivitySubjectBinding;
import run.ikaros.app.and.activity.login.LoginActivity;
import run.ikaros.app.and.activity.subject.Subject;
import run.ikaros.app.and.activity.subject.SubjectAdapter;

public class SubjectActivity extends AppCompatActivity {

    private ActivitySubjectBinding binding;
    private RecyclerView subjectRecyclerView;
    private SubjectAdapter subjectAdapter;

    private static final String testTmpUrl
            = "https://lain.bgm.tv/r/400/pic/cover/l/68/ea/333979_lG5Gt.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySubjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        subjectRecyclerView = findViewById(R.id.subjectRecyclerView);
        subjectAdapter = new SubjectAdapter(getSubjectList(), this);
        subjectRecyclerView.setAdapter(subjectAdapter);
        subjectRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        SharedPreferences sharedPreferences = getSharedPreferences(AppConst.SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        String username = sharedPreferences.getString(AppConst.USERNAME_KEY, "");
        if(Objects.isNull(username) || "".equals(username)) {
            Intent in = new Intent(SubjectActivity.this, LoginActivity.class);
            startActivity(in);
        }
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

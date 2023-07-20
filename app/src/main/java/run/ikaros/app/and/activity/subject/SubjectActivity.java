package run.ikaros.app.and.activity.subject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import run.ikaros.app.and.R;
import run.ikaros.app.and.activity.login.LoginActivity;
import run.ikaros.app.and.api.auth.AuthParams;
import run.ikaros.app.and.api.common.PagingWrap;
import run.ikaros.app.and.api.subject.SubjectClient;
import run.ikaros.app.and.api.subject.enums.SubjectType;
import run.ikaros.app.and.api.subject.model.Subject;
import run.ikaros.app.and.constants.UserKeyConst;
import run.ikaros.app.and.databinding.ActivitySubjectBinding;
import run.ikaros.app.and.infra.utils.StringUtils;

public class SubjectActivity extends AppCompatActivity {

    private ActivitySubjectBinding binding;
    private RecyclerView subjectRecyclerView;
    private TextView searchEditTextView;
    private Switch searchNsfwSwitch;
    private Button searchButton;
    private SubjectAdapter subjectAdapter;
    private SubjectClient subjectClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySubjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // init subject client
        SharedPreferences sharedPreferences = getSharedPreferences(UserKeyConst.SHARED_PREFERENCES, MODE_PRIVATE);
        String username = sharedPreferences.getString(UserKeyConst.USERNAME, "");
        if (Objects.isNull(username) || "".equals(username)) {
            Intent in = new Intent(SubjectActivity.this, LoginActivity.class);
            startActivity(in);
        } else {
            AuthParams authParams = new AuthParams();
            authParams.setBaseUrl(sharedPreferences.getString(UserKeyConst.BASE_URL, ""));
            authParams.setUsername(sharedPreferences.getString(UserKeyConst.USERNAME, ""));
            authParams.setPassword(sharedPreferences.getString(UserKeyConst.PASSWORD, ""));
            subjectClient = new SubjectClient(authParams);
        }

        subjectRecyclerView = findViewById(R.id.subjectRecyclerView);
        subjectAdapter = new SubjectAdapter(getSubjectList(), this);
        subjectRecyclerView.setAdapter(subjectAdapter);
        subjectRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        searchEditTextView = findViewById(R.id.searchEditText);
        searchNsfwSwitch = findViewById(R.id.searchNsfwSwitch);
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> {
            String nameCn = searchEditTextView.getText().toString();
            boolean nsfw = searchNsfwSwitch.isChecked();
            List<Subject> subjectList = getSubjectList(1, 12, null, nameCn, nsfw, null);
            subjectAdapter.setSubjectList(subjectList);
            subjectAdapter.notifyDataSetChanged();
        });

    }

    private List<Subject> getSubjectList() {
        return getSubjectList(1, 12, null, null, false, null);
    }

    private List<Subject> getSubjectList(@Nullable Integer page,
                                         @Nullable Integer size,
                                         @Nullable String name,
                                         @Nullable String nameCn,
                                         @Nullable Boolean nsfw,
                                         @Nullable SubjectType type) {
        if(Objects.isNull(subjectClient)) {
            return Collections.emptyList();
        }
        // 获取番剧数据集合的示例方法
        List<Subject> subjectList = new ArrayList<>();
        Thread thread = new Thread(() -> {
            try {
                Base64.Encoder encoder = Base64.getEncoder();
                PagingWrap<Subject> subjectPagingWrap
                        = subjectClient.listSubjectsByCondition(page, size,
                        StringUtils.isBlank(name) ? null : encoder.encodeToString(name.getBytes()),
                        StringUtils.isBlank(nameCn) ? null : encoder.encodeToString(nameCn.getBytes()),
                        nsfw, type);
                List<Subject> subjects = subjectPagingWrap.getItems();
                subjectList.addAll(subjects);
            } catch (Exception e) {
                Log.e(SubjectActivity.class.getSimpleName(), "请求条目API异常", e);
                SubjectActivity.this.runOnUiThread(() ->
                        Toast.makeText(getApplicationContext(), "请求条目API异常", Toast.LENGTH_LONG).show());
            }
        });
        thread.start();
        while (thread.isAlive()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return subjectList;
    }
}

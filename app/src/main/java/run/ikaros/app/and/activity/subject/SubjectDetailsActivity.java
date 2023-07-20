package run.ikaros.app.and.activity.subject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import run.ikaros.app.and.R;
import run.ikaros.app.and.activity.login.LoginActivity;
import run.ikaros.app.and.api.auth.AuthParams;
import run.ikaros.app.and.api.subject.SubjectClient;
import run.ikaros.app.and.api.subject.model.Episode;
import run.ikaros.app.and.api.subject.model.EpisodeResource;
import run.ikaros.app.and.api.subject.model.Subject;
import run.ikaros.app.and.constants.UserKeyConst;
import run.ikaros.app.and.fragment.adapter.SubjectTabAdapter;
import run.ikaros.app.and.fragment.subject.SubjectEpisodesFragment;
import run.ikaros.app.and.infra.utils.Assert;
import run.ikaros.app.and.infra.utils.StringUtils;

public class SubjectDetailsActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer>
        implements SubjectEpisodesFragment.OnEpisodeSelectBtnClickListener {

    StandardGSYVideoPlayer detailPlayer;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private SubjectClient subjectClient;
    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        // init subject client
        SharedPreferences sharedPreferences = getSharedPreferences(UserKeyConst.SHARED_PREFERENCES, MODE_PRIVATE);
        String username = sharedPreferences.getString(UserKeyConst.USERNAME, "");
        if (Objects.isNull(username) || "".equals(username)) {
            Intent in = new Intent(SubjectDetailsActivity.this, LoginActivity.class);
            startActivity(in);
        } else {
            AuthParams authParams = new AuthParams();
            baseUrl = sharedPreferences.getString(UserKeyConst.BASE_URL, "");
            authParams.setBaseUrl(baseUrl);
            authParams.setUsername(sharedPreferences.getString(UserKeyConst.USERNAME, ""));
            authParams.setPassword(sharedPreferences.getString(UserKeyConst.PASSWORD, ""));
            subjectClient = new SubjectClient(authParams);
        }

        // get subjectId
        Intent intent = getIntent();
        Long subjectId = intent.getLongExtra("id", 0L);
        Subject subject = getSubjectById(subjectId);

        // bind tab fragments
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.subjectViewPager);
        SubjectTabAdapter tabAdapter = new SubjectTabAdapter(getSupportFragmentManager(), getLifecycle());
        tabAdapter.setSubject(subject);
        viewPager.setAdapter(tabAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("详情");
                    break;
                case 1:
                    tab.setText("选集");
                    break;
            }
        }).attach();

        // init video player
        detailPlayer = findViewById(R.id.detail_player);
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
        detailPlayer.getBackButton().setOnClickListener(v -> onBackPressed());

        initVideoBuilderMode();

        //允许window 的内容可以上移到刘海屏状态栏
        if (getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }

    }

    private Subject getSubjectById(Long subjectId) {
        Assert.isTrue(subjectId > 0, "subject id must > 0.");
        AtomicReference<Subject> subject = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            try {
                subject.set(subjectClient.findById(subjectId));
            } catch (Exception e) {
                Log.e(SubjectDetailsActivity.class.getSimpleName(), "请求条目API异常", e);
                SubjectDetailsActivity.this.runOnUiThread(() ->
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
        return subject.get();
    }


    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.videocover);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setCacheWithPlay(true)
                .setIsTouchWiget(true)
                //.setAutoFullWithSize(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)//打开动画
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }


    /**
     * 是否启动旋转横屏，true表示启动
     */
    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

    @Override
    public void onBtnClick(Episode episode) {
        List<EpisodeResource> resources = episode.getResources();
        EpisodeResource episodeResource = resources.get(0);
        String episodeUrl = baseUrl + episodeResource.getUrl();
        String nameCn = episode.getNameCn();
        if (StringUtils.isBlank(nameCn)) {
            nameCn = episode.getName();
        }
        nameCn = episode.getSequence() + ". " + nameCn + " (" + episodeResource.getName() + ')';
        getGSYVideoOptionBuilder()
                .setVideoTitle(nameCn)
                .setUrl(episodeUrl)
                .build(detailPlayer);

        detailPlayer.startPlayLogic();
        Log.i(SubjectDetailsActivity.class.getSimpleName(), "switch video title and url: " + episodeUrl);
    }

    @Override
    public void onStartPrepared(String url, Object... objects) {
        super.onStartPrepared(url, objects);
        /// 硬解码
        GSYVideoType.enableMediaCodec();
        GSYVideoType.enableMediaCodecTexture();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        /// 硬解码
        GSYVideoType.disableMediaCodec();
        GSYVideoType.disableMediaCodecTexture();
        GSYVideoManager.releaseAllVideos();
    }

}

package run.ikaros.app.and.activity.subject;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import run.ikaros.app.and.R;
import run.ikaros.app.and.constants.TmpConst;

public class SubjectDetailsActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {

    StandardGSYVideoPlayer detailPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        final TextView titleTextView = findViewById(R.id.subjectTitle);
        titleTextView.setText(TmpConst.Nas.Subject.TITLE);

        final TextView titleCnTextView = findViewById(R.id.subjectTitleCn);
        titleCnTextView.setText(TmpConst.Nas.Subject.TITLE_CN);

        final TextView descTextView = findViewById(R.id.subjectDesc);
        descTextView.setText(TmpConst.Nas.Subject.DESC);

        final ImageView imageView = findViewById(R.id.subjectCover);
        imageView.setContentDescription("Subject cover.");
        Uri imageUri = Uri.parse(TmpConst.Nas.Subject.COVER);
        Glide.with(this)
                .load(imageUri)
                .into(imageView);


        detailPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);

        initVideoBuilderMode();

        //允许window 的内容可以上移到刘海屏状态栏
        if (getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }

    }


    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        //loadCover(imageView, url);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(TmpConst.Nas.Subject.VIDEO)
                .setCacheWithPlay(true)
                .setVideoTitle("这里是剧集标题")
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

//    private void loadCover(ImageView imageView, String url) {
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.xxx1);
//        Glide.with(this.getApplicationContext())
//                .setDefaultRequestOptions(
//                        new RequestOptions()
//                                .frame(3000000)
//                                .centerCrop()
//                                .error(R.mipmap.xxx2)
//                                .placeholder(R.mipmap.xxx1))
//                .load(url)
//                .into(imageView);
//    }

}

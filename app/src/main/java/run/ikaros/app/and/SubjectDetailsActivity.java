package run.ikaros.app.and;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import run.ikaros.app.and.constants.TmpConst;
import run.ikaros.app.and.databinding.ActivitySubjectDetailsBinding;

public class SubjectDetailsActivity extends AppCompatActivity {

    private ActivitySubjectDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySubjectDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final ImageView imageView = binding.imageView;
        Uri imageUri = Uri.parse(TmpConst.Nas.Subject.COVER);
        Glide.with(this)
                .load(imageUri)
                .into(imageView);

        final VideoView videoView = binding.videoView;
        videoView.setMediaController(new MediaController(this));
        Uri videoUri = Uri.parse(TmpConst.Nas.Subject.VIDEO);
        videoView.setVideoURI(videoUri);
        videoView.start();
        videoView.requestFocus();

        final TextView titleTextView = binding.subjectTitle;
        titleTextView.setText(TmpConst.Nas.Subject.TITLE);

        final TextView titleCnTextView = binding.subjectTitleCn;
        titleCnTextView.setText(TmpConst.Nas.Subject.TITLE_CN);

        final TextView descTextView = binding.subjectDesc;
        String desc = TmpConst.Nas.Subject.DESC;
        descTextView.setText(desc);

    }
}

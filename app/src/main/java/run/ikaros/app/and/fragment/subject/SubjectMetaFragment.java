package run.ikaros.app.and.fragment.subject;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import run.ikaros.app.and.R;
import run.ikaros.app.and.api.subject.model.Subject;
import run.ikaros.app.and.constants.TmpConst;
import run.ikaros.app.and.constants.UserKeyConst;

public class SubjectMetaFragment extends Fragment {

    private final Subject subject;

    public SubjectMetaFragment(Subject subject) {
        this.subject = subject;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_subject_meta, container, false);

        final TextView titleTextView = rootView.findViewById(R.id.subjectTitle);
        titleTextView.setText(subject.getName());

        final TextView titleCnTextView = rootView.findViewById(R.id.subjectTitleCn);
        titleCnTextView.setText(subject.getNameCn());

        final TextView descTextView = rootView.findViewById(R.id.subjectDesc);
        descTextView.setText(subject.getSummary());

        final ImageView imageView = rootView.findViewById(R.id.subjectCover);
        imageView.setContentDescription("Subject cover.");
        Uri imageUri = Uri.parse(getBaseUrl() + subject.getCover());
        Glide.with(this)
                .load(imageUri)
                .into(imageView);

        return rootView;
    }

    private String getBaseUrl() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserKeyConst.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(UserKeyConst.BASE_URL, "");
    }
}
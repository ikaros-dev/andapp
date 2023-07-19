package run.ikaros.app.and.activity.subject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import run.ikaros.app.and.R;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private final List<Subject> subjectList;
    private Fragment fragment;
    private Activity activity;

    public SubjectAdapter(List<Subject> subjectList, Fragment fragment) {
        this.subjectList = subjectList;
        this.fragment = fragment;
    }

    public SubjectAdapter(List<Subject> subjectList, Activity activity) {
        this.subjectList = subjectList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subject, parent, false);
        if (fragment != null) {
            return new SubjectViewHolder(view, fragment);
        }
        if (activity != null) {
            return new SubjectViewHolder(view, activity);
        }
        throw new IllegalArgumentException("context is null.");
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        // 绑定数据到视图
        if (fragment != null) {
            holder.bind(subject, fragment);
        }
        if (activity != null) {
            holder.bind(subject, activity);
        }
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        private ImageView subjectImageView;
        private TextView subjectTextView;

        public SubjectViewHolder(@NonNull View itemView, Activity activity) {
            super(itemView);
            subjectImageView = itemView.findViewById(R.id.subjectImageView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            subjectImageView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, SubjectDetailsActivity.class);
                activity.startActivity(intent);
            });
        }

        public SubjectViewHolder(@NonNull View itemView, Fragment fragment) {
            super(itemView);
            subjectImageView = itemView.findViewById(R.id.subjectImageView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            subjectImageView.setOnClickListener(v -> {
                Intent intent = new Intent(fragment.getContext(), SubjectDetailsActivity.class);
                fragment.requireActivity().startActivity(intent);
            });
        }

        public void bind(Subject subject, Fragment fragment) {
            if (fragment == null) {
                throw new IllegalArgumentException("fragment must not null");
            }
            // 设置图片和文本
            Glide.with(fragment)
                    .asBitmap()
                    .load(subject.getCoverUrl())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();

                            int targetWidth = subjectImageView.getWidth(); // 获取 ImageView 的宽度
                            int targetHeight = (int) ((float) targetWidth / width * height); // 根据宽高比计算目标高度

                            ViewGroup.LayoutParams layoutParams = subjectImageView.getLayoutParams();
                            layoutParams.height = targetHeight;
                            subjectImageView.setLayoutParams(layoutParams);

                            Glide.with(fragment)
                                    .load(resource)
                                    .into(subjectImageView);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            // 图像加载被清除时的处理
                        }
                    });

            subjectTextView.setText(subject.getTitle());
        }

        public void bind(Subject subject, Activity activity) {
            if (activity == null) {
                throw new IllegalArgumentException("activity must not null");
            }
            // 设置图片和文本
            Glide.with(activity)
                    .asBitmap()
                    .load(subject.getCoverUrl())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();

                            int targetWidth = subjectImageView.getWidth(); // 获取 ImageView 的宽度
                            int targetHeight = (int) ((float) targetWidth / width * height); // 根据宽高比计算目标高度

                            ViewGroup.LayoutParams layoutParams = subjectImageView.getLayoutParams();
                            layoutParams.height = targetHeight;
                            subjectImageView.setLayoutParams(layoutParams);

                            Glide.with(activity)
                                    .load(resource)
                                    .into(subjectImageView);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            // 图像加载被清除时的处理
                        }
                    });

            subjectTextView.setText(subject.getTitle());
        }
    }
}

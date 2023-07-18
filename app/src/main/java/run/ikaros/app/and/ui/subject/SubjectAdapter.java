package run.ikaros.app.and.ui.subject;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;


import run.ikaros.app.and.R;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private final List<Subject> subjectList;
    private final SubjectFragment fragment;

    public SubjectAdapter(List<Subject> subjectList, SubjectFragment fragment) {
        this.subjectList = subjectList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        // 绑定数据到视图
        holder.bind(subject, fragment);
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        private ImageView subjectImageView;
        private TextView subjectTextView;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectImageView = itemView.findViewById(R.id.subjectImageView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
        }

        public void bind(Subject subject, SubjectFragment fragment) {
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
    }
}

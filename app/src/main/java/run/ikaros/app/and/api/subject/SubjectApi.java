package run.ikaros.app.and.api.subject;

import androidx.annotation.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import run.ikaros.app.and.api.common.PagingWrap;
import run.ikaros.app.and.api.subject.enums.SubjectType;
import run.ikaros.app.and.api.subject.model.Subject;

public interface SubjectApi {

    // List subjects by condition.
    @GET("/api/v1alpha1/subjects/condition")
    Call<PagingWrap<Subject>> listSubjectsByCondition(@Header("Authorization") String authorization,
                                                      @Nullable @Query("page") Integer page,
                                                      @Nullable @Query("size") Integer size,
                                                      @Nullable @Query("name") String name,
                                                      @Nullable @Query("nameCn") String nameCn,
                                                      @Nullable @Query("nsfw") Boolean nsfw,
                                                      @Nullable @Query("type") SubjectType type);

    @GET("/api/v1alpha1/subject/{id}")
    Call<Subject> findById(@Header("Authorization") String authorization, @Path("id") Long id);
}

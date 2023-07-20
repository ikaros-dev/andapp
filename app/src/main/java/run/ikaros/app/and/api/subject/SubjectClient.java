package run.ikaros.app.and.api.subject;

import androidx.annotation.Nullable;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import run.ikaros.app.and.infra.utils.Assert;
import run.ikaros.app.and.infra.utils.AuthorizationUtils;
import run.ikaros.app.and.api.auth.AuthParams;
import run.ikaros.app.and.api.common.PagingWrap;
import run.ikaros.app.and.api.subject.enums.SubjectType;
import run.ikaros.app.and.api.subject.model.Subject;

public class SubjectClient {
    private final AuthParams authParams;
    private Retrofit retrofit;
    private SubjectApi subjectApi;

    public SubjectClient(AuthParams authParams) {
        Assert.notNull(authParams, "authParams must not null.");
        this.authParams = authParams;
        Assert.notBlank(authParams.getBaseUrl(), "baseUrl must not blank.");
        Assert.notBlank(authParams.getUsername(), "username must not blank.");
        Assert.notBlank(authParams.getPassword(), "password must not blank.");
        retrofit = new Retrofit.Builder()
                .baseUrl(authParams.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        subjectApi = retrofit.create(SubjectApi.class);
    }

    public PagingWrap<Subject> listSubjectsByCondition(@Nullable Integer page,
                                                       @Nullable Integer size,
                                                       @Nullable String name,
                                                       @Nullable String nameCn,
                                                       @Nullable Boolean nsfw,
                                                       @Nullable SubjectType type) {
        try {
            String authStr = AuthorizationUtils.encodeBasicHeader(authParams.getUsername(), authParams.getPassword());
            Response<PagingWrap<Subject>> response
                    = subjectApi.listSubjectsByCondition(authStr, page, size, name, nameCn, nsfw, type).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PagingWrap<Subject> listSubjectsByCondition() {
        return listSubjectsByCondition(null, null, null, null, null, null);
    }
}

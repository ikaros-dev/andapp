package run.ikaros.app.and.api.subject;

import androidx.media3.common.util.Assertions;

import junit.framework.TestCase;

import org.junit.Test;

import run.ikaros.app.and.api.auth.AuthParams;
import run.ikaros.app.and.api.common.PagingWrap;
import run.ikaros.app.and.api.subject.model.Subject;

public class SubjectClientTest extends TestCase {

    // @Test
    public void testListSubjectsByCondition() {
        AuthParams authParams = new AuthParams();
        authParams.setBaseUrl("http://nas:9999");
        authParams.setUsername("tomoki");
        authParams.setPassword("tomoki");
        SubjectClient subjectClient = new SubjectClient(authParams);
        PagingWrap<Subject> listPagingWrap = subjectClient.listSubjectsByCondition();
        Assertions.checkNotNull(listPagingWrap);
    }
}
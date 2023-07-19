package run.ikaros.app.and.api.subject.model;

import java.time.LocalDateTime;

import run.ikaros.app.and.api.subject.enums.SubjectSyncPlatform;


public class SubjectSync {
    private Long subjectId;
    private SubjectSyncPlatform platform;
    private String platformId;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public SubjectSyncPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(SubjectSyncPlatform platform) {
        this.platform = platform;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
}

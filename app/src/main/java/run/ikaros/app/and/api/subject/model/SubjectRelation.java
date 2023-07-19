package run.ikaros.app.and.api.subject.model;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

import run.ikaros.app.and.api.subject.enums.SubjectRelationType;

public class SubjectRelation {
    private Long subject;

    /**
     * Subject relation type.
     *
     * @see SubjectRelationType#getCode()
     */
    @SerializedName("relation_type")
    private SubjectRelationType relationType;

    @SerializedName("relation_subjects")
    private Set<Long> relationSubjects;

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public SubjectRelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(SubjectRelationType relationType) {
        this.relationType = relationType;
    }

    public Set<Long> getRelationSubjects() {
        return relationSubjects;
    }

    public void setRelationSubjects(Set<Long> relationSubjects) {
        this.relationSubjects = relationSubjects;
    }
}

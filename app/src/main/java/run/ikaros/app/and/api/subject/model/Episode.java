package run.ikaros.app.and.api.subject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Episode {
    private Long id;
    @SerializedName("subject_id")
    private Long subjectId;
    private String name;
    @SerializedName("name_cn")
    private String nameCn;
    private String description;
    private Double sequence;

    private List<EpisodeResource> resources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSequence() {
        return sequence;
    }

    public void setSequence(Double sequence) {
        this.sequence = sequence;
    }

    public List<EpisodeResource> getResources() {
        return resources;
    }

    public void setResources(List<EpisodeResource> resources) {
        this.resources = resources;
    }
}

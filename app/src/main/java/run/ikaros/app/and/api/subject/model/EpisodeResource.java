package run.ikaros.app.and.api.subject.model;

import com.google.gson.annotations.SerializedName;

import java.util.Set;


public class EpisodeResource {
    @SerializedName("file_id")
    private Long fileId;
    @SerializedName("episode_id")
    private Long episodeId;
    private String url;
    private boolean canRead;
    private String name;
    /**
     * Such as 1080p 720p.
     */
    private Set<String> tags;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}

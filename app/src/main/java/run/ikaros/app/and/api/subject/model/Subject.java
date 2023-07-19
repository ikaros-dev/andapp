package run.ikaros.app.and.api.subject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import run.ikaros.app.and.api.subject.enums.CollectionStatus;
import run.ikaros.app.and.api.subject.enums.SubjectType;

public class Subject {
    private Long id;
    private SubjectType type;
    private String name;
    @SerializedName("name_cn")
    private String nameCn;
    private String infobox;
    private String summary;
    /**
     * Can search by anonymous access.
     */
    private Boolean nsfw;
    private String cover;
    private List<Episode> episodes;
    @SerializedName("total_episodes")
    private Long totalEpisodes;
    @SerializedName("collection_status")
    private CollectionStatus collectionStatus;
    private List<SubjectSync> syncs;
    private boolean canRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
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

    public String getInfobox() {
        return infobox;
    }

    public void setInfobox(String infobox) {
        this.infobox = infobox;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public Long getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(Long totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public CollectionStatus getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(CollectionStatus collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public List<SubjectSync> getSyncs() {
        return syncs;
    }

    public void setSyncs(List<SubjectSync> syncs) {
        this.syncs = syncs;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }
}

package run.ikaros.app.and.ui.subject;

public class Subject {
    private String title;
    private String coverUrl;

    public Subject(String title, String coverUrl) {
        this.title = title;
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

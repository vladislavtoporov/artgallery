package clientapp.models;

import net.htmlparser.jericho.Source;

import java.util.List;

public class Exhibit {
    private int id;
    private String name;
    private String content;
    private long ts;
    private String pictureFile;

    private Exposition exposition;

    private List<File> images;

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Exhibit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", ts=" + ts +
                ", pictureFile='" + pictureFile + '\'' +
                ", exposition=" + exposition +
                ", images=" + images +
                '}';
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        Source source=new Source(content);
        this.content = source.getRenderer().toString();
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
    }
}

package vulan.com.trackingstore.data.model;

/**
 * Created by Thanh on 2/27/2017.
 */

public class TagSearch {
    private String tagContent;

    public TagSearch(String tagContent){
        this.tagContent = tagContent;
    }
    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }
}

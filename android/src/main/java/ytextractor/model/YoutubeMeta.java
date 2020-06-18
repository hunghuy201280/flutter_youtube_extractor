package ytextractor.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class YoutubeMeta {
    @SerializedName("channelId")
    public String channelId;
    @SerializedName("averageRating")
    public Float averageRating;
    @SerializedName("allowRatings")
    public boolean allowRatings;
    @SerializedName("author")
    public String author;
    @SerializedName("isCrawlable")
    public boolean isCrawlable;
    @SerializedName("isLiveContent")
    public boolean isLiveContent;
    @SerializedName("thumbnail")
    public Thumbnail thumbnail;

    //	public boolean isLive;
    public boolean isLowLatencyLiveStream;
    public boolean isOwnerViewing;
    public boolean isPrivate;
    public boolean useCipher;
    public boolean isUnpluggedCorpus;
    public String latencyClass;
    public String lengthSeconds;
    public String shortDescription;

    public String title;
    public String videoId;
    public String viewCount;


    public class Thumbnail {

        private List<Thumbnail_> thumbnails = new ArrayList<Thumbnail_>();

        public List<Thumbnail_> getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(List<Thumbnail_> thumbnails) {
            this.thumbnails = thumbnails;
        }

    }

    public class Thumbnail_ {

        private Integer height;
        private String url;
        private Integer width;

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

    }
}
	
	

	


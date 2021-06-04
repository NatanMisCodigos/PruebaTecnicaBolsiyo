package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model;

import java.io.Serializable;
import java.util.List;

public class ImageApi implements Serializable {

    private List<Hits> hits;

    public List<Hits> getHits() {
        return hits;
    }

    public void setHits(List<Hits> hits) {
        this.hits = hits;
    }

    public class Hits implements Serializable{

        private String userImageURL;
        private String tags;
        private String largeImageURL;
        private long views;

        public String getUserImageURL() {
            return userImageURL;
        }

        public void setUserImageURL(String userImageURL) {
            this.userImageURL = userImageURL;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getLargeImageURL() {
            return largeImageURL;
        }

        public void setLargeImageURL(String largeImageURL) {
            this.largeImageURL = largeImageURL;
        }

        public long getViews() {
            return views;
        }

        public void setViews(long views) {
            this.views = views;
        }
    }

}

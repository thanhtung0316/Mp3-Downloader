package com.thanhtung.mp3downloader.model.youtubemodel;

import com.google.gson.annotations.SerializedName;

public class QualityImageThumbnail {

        @SerializedName("url")
        public String url;

        @SerializedName("width")
        public Integer wVideoImage;

        @SerializedName("height")
        public Integer hVideoImage;


        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public Integer getwVideoImage() {
                return wVideoImage;
        }

        public void setwVideoImage(Integer wVideoImage) {
                this.wVideoImage = wVideoImage;
        }

        public Integer gethVideoImage() {
                return hVideoImage;
        }

        public void sethVideoImage(Integer hVideoImage) {
                this.hVideoImage = hVideoImage;
        }
}

package ytextractor.model;

import com.google.gson.annotations.SerializedName;

public class YTMedia {
    @SerializedName("mimeType")
    public String mimeType;
    @SerializedName(value = "cipher", alternate = {"signatureCipher"})
    public String cipher;
    int itag;
    int bitrate;
    int width;
    int height;
    double lastModified;
    double contentLength;
    String quality;
    String qualityLabel;
    String projectionType;
    double averageBitrate;
    double approxDurationMs;
    int audioChannels;
    int audioSampleRate;
    String audioQuality;
    String url;
    int fps;

    public boolean isVideo() {
        return mimeType.contains("video");
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getItag() {
        return itag;
    }

    public void setItag(int itag) {
        this.itag = itag;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getLastModified() {
        return lastModified;
    }

    public void setLastModified(double lastModified) {
        this.lastModified = lastModified;
    }

    public double getContentLength() {
        return contentLength;
    }

    public void setContentLength(double contentLength) {
        this.contentLength = contentLength;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQualityLabel() {
        return qualityLabel;
    }

    public void setQualityLabel(String qualityLabel) {
        this.qualityLabel = qualityLabel;
    }

    public String getProjectionType() {
        return projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }

    public double getAverageBitrate() {
        return averageBitrate;
    }

    public void setAverageBitrate(double averageBitrate) {
        this.averageBitrate = averageBitrate;
    }

    public double getApproxDurationMs() {
        return approxDurationMs;
    }

    public void setApproxDurationMs(double approxDurationMs) {
        this.approxDurationMs = approxDurationMs;
    }

    public int getAudioChannels() {
        return audioChannels;
    }

    public void setAudioChannels(int audioChannels) {
        this.audioChannels = audioChannels;
    }

    public int getAudioSampleRate() {
        return audioSampleRate;
    }

    public void setAudioSampleRate(int audioSampleRate) {
        this.audioSampleRate = audioSampleRate;
    }

//    public void setCipher(String cipher) {
//        this.cipher = cipher;
//    }

//    public String getCipher() {
//        return cipher;
//    }

    public String getAudioQuality() {
        return audioQuality;
    }

    public void setAudioQuality(String audioQuality) {
        this.audioQuality = audioQuality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean useCipher() {
        return (cipher != null && cipher.contains("s="));
    }

}

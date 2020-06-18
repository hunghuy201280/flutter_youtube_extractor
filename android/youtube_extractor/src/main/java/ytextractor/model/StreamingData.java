package ytextractor.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class StreamingData {
    private String hlsManifestUrl;
    @SerializedName("expiresInSeconds")
    private long expiresInSeconds;
    @SerializedName("formats")
    private YTMedia[] formats;
    @SerializedName("adaptiveFormats")
    private YTMedia[] adaptiveFormats;

    public YTMedia[] getFormats() {
        return formats;
    }

    public void setFormats(YTMedia[] formats) {
        this.formats = formats;
    }

    public YTMedia[] getAdaptiveFormats() {
        return adaptiveFormats;
    }

    public void setAdaptiveFormats(YTMedia[] adaptiveFormats) {
        this.adaptiveFormats = adaptiveFormats;
    }

    public long getExpiresInSeconds() {
        return expiresInSeconds;
    }

    public void setExpiresInSeconds(long expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
    }

    public String getHlsManifestUrl() {
        return hlsManifestUrl;
    }

    public void setHlsManifestUrl(String hlsManifestUrl) {
        this.hlsManifestUrl = hlsManifestUrl;
    }

    @Override
    public String toString() {
        return "StreamingData{" +
                "hlsManifestUrl='" + hlsManifestUrl + '\'' +
                ", expiresInSeconds=" + expiresInSeconds +
                ", formats=" + Arrays.toString(formats) +
                ", adaptiveFormats=" + Arrays.toString(adaptiveFormats) +
                '}';
    }
}

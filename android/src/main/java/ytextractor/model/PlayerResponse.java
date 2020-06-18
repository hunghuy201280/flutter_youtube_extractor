package ytextractor.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerResponse {
    @SerializedName("streamingData")
    public StreamingData streamingData;
    @SerializedName("videoDetails")
    public YoutubeMeta videoDetails;
    public Captions captions;


    public class Captions {
        private PlayerCaptionsTracklistRenderer playerCaptionsTracklistRenderer;

        public PlayerCaptionsTracklistRenderer getPlayerCaptionsTracklistRenderer() {
            return playerCaptionsTracklistRenderer;
        }

        public void setPlayerCaptionsTracklistRenderer(PlayerCaptionsTracklistRenderer playerCaptionsTracklistRenderer) {
            this.playerCaptionsTracklistRenderer = playerCaptionsTracklistRenderer;
        }

        public class PlayerCaptionsTracklistRenderer {
            private List<YTSubtitles> captionTracks;

            public List<YTSubtitles> getCaptionTracks() {
                return captionTracks;
            }

            public void setCaptionTracks(List<YTSubtitles> captionTracks) {
                this.captionTracks = captionTracks;
            }
        }
    }


    public class PlayabilityStatus {
        private String status;
        private boolean playableInEmbed;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isPlayableInEmbed() {
            return playableInEmbed;
        }

        public void setPlayableInEmbed(boolean playableInEmbed) {
            this.playableInEmbed = playableInEmbed;
        }
    }
}



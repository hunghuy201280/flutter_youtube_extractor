package io.fluttervn.flutteryoutubeextractor;

import android.net.Uri;
import androidx.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import ytextractor.ExtractorException;
import ytextractor.YoutubeStreamExtractor;
import ytextractor.model.YTMedia;
import ytextractor.model.YTSubtitles;
import ytextractor.model.YoutubeMeta;

/**
 * FlutterYoutubeExtractorPlugin
 */
public class FlutterYoutubeExtractorPlugin implements FlutterPlugin, MethodCallHandler {
    private MethodChannel nativeChannel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        this.nativeChannel = new MethodChannel(binding.getBinaryMessenger(), "flutter.youtube.extractor/native");
        this.nativeChannel.setMethodCallHandler(this);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        this.nativeChannel.setMethodCallHandler(null);
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("getYoutubeMediaLink")) {
            String youtubeLink = (String) call.arguments;
            /*new YouTubeExtractor(this.context) {
                @Override
                public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                    if (ytFiles != null) {
                        String mediaLink = "";
                        String link720 = "", link480 = "", link360 = "";

                        if (ytFiles.get(22) != null || ytFiles.get(18) != null || ytFiles.get(36) != null) {
                            mediaLink = ytFiles.get(22) != null ? ytFiles.get(22).getUrl() : (ytFiles.get(18) != null) ? ytFiles.get(18).getUrl() : (ytFiles.get(36) != null) ? ytFiles.get(36).getUrl() : "";

                        } else {
                            YtFile ytFile;
                            for (int i = 0; i < ytFiles.size(); i++) {
                                if (!TextUtils.isEmpty(link720) && !TextUtils.isEmpty(link480) && !TextUtils.isEmpty(link360))
                                    break;

                                ytFile = ytFiles.get(i);
                                if(ytFile != null && ytFile.getFormat()!= null) {
                                    if (TextUtils.isEmpty(link720) && ytFile.getFormat().getHeight() == 720 && ytFile.getFormat().getAudioBitrate() != -1) {
                                        link720 = ytFile.getUrl();

                                    } else if (TextUtils.isEmpty(link480) && ytFile.getFormat().getHeight() == 480 && ytFile.getFormat().getAudioBitrate() != -1) {
                                        link480 = ytFile.getUrl();

                                    } else if (TextUtils.isEmpty(link360) && ytFile.getFormat().getHeight() == 360 && ytFile.getFormat().getAudioBitrate() != -1) {
                                        link360 = ytFile.getUrl();

                                    } else if (ytFile.getFormat().getAudioBitrate() != -1)
                                        mediaLink = ytFile.getUrl();
                                }
                            }

                            mediaLink = !TextUtils.isEmpty(link720) ? link720 : !TextUtils.isEmpty(link480) ? link480 : !TextUtils.isEmpty(link360)? link360 : mediaLink;
                        }
                        nativeChannel.invokeMethod("receiveYoutubeMediaLink", mediaLink);
                    }
                }
            }.extract(youtubeLink, true, true);*/
            try {
                final Uri uri = Uri.parse(youtubeLink);
                final String id = uri.getQueryParameter("v");
                Log.d("YoutubeExtractor", "uri: " + uri + ", id: " + id);
                YoutubeStreamExtractor youtubeStreamExtractor = new YoutubeStreamExtractor(new YoutubeStreamExtractor.ExtractorListner() {
                    @Override
                    public void onExtractionDone(List<YTMedia> adativeStream, final List<YTMedia> muxedStream, List<YTSubtitles> subtitles, YoutubeMeta meta) {
                        //url to get subtitle
                        if (subtitles != null && !subtitles.isEmpty()) {
                            String subUrl = subtitles.get(0).getBaseUrl();
                        }
                        if (adativeStream != null && !adativeStream.isEmpty()) {
                            String videoUrl = "";
                            int height = 0;
                            for (YTMedia media : adativeStream) {
                                if (media.isVideo()) {
                                    //is video
                                    //find the largest
                                    if (media.getHeight() > height) {
                                        height = media.getHeight();
                                        videoUrl = media.getUrl();
                                    }
                                } else {
                                    //is audio
                                }
                            }
                            Log.d("YoutubeExtractor", height + "p" + ", videoUrl: " + videoUrl);
                            if (videoUrl != null && !videoUrl.isEmpty()) {
                                nativeChannel.invokeMethod("receiveYoutubeMediaLink", videoUrl);
                            }
                        }
                    }

                    @Override
                    public void onExtractionGoesWrong(final ExtractorException e) {
                        Log.d("YoutubeExtractor", e.getMessage());
                    }
                });
                youtubeStreamExtractor.useDefaultLogin();
                youtubeStreamExtractor.Extract(id);//url to get subtitle
//is video
//find the largest
//is audio
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result.notImplemented();
        }
    }
}

package io.fluttervn.flutteryoutubeextractor;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.NonNull;

//import com.commit451.youtubeextractor.Stream;
//import com.commit451.youtubeextractor.YouTubeExtraction;
//import com.commit451.youtubeextractor.YouTubeExtractor;
//import com.github.kotvertolet.youtubejextractor.JExtractorCallback;
//import com.github.kotvertolet.youtubejextractor.YoutubeJExtractor;
//import com.github.kotvertolet.youtubejextractor.exception.YoutubeRequestException;
//import com.github.kotvertolet.youtubejextractor.models.youtube.videoData.YoutubeVideoData;

import java.util.List;

//import at.huber.youtubeExtractor.VideoMeta;
//import at.huber.youtubeExtractor.YouTubeExtractor;
//import at.huber.youtubeExtractor.YtFile;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
import ytextractor.ExtractorException;
import ytextractor.YoutubeStreamExtractor;
import ytextractor.model.YTMedia;
import ytextractor.model.YTSubtitles;
import ytextractor.model.YoutubeMeta;
import ytextractor.utils.LogUtils;

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

    @SuppressLint("StaticFieldLeak")
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

//                new YouTubeExtractor(null) {
//                    @Override
//                    public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
//                        if (ytFiles != null) {
////                            int itag = 22;
//                            String videoUrl = ytFiles.get(0).getUrl();
//
//                            nativeChannel.invokeMethod("receiveYoutubeMediaLink", videoUrl);
//                        }
//                    }
//                }.extract(youtubeLink, true, true);

//                YoutubeJExtractor jExtractor = new YoutubeJExtractor();
//                jExtractor.extract(id, new JExtractorCallback() {
//                    @Override
//                    public void onSuccess(YoutubeVideoData videoData) {
//                        // use extracted data
//
//                        String videoUrl = "";
//                        try {
//                            videoUrl = videoData.getStreamingData().getAdaptiveVideoStreams().get(0).getUrl();
//                        } catch (Exception e) {
//                            LogUtils.log("ERROR: "+ e.getMessage());
//                        }
//                        nativeChannel.invokeMethod("receiveYoutubeMediaLink", videoUrl);
//                    }
//
//                    @Override
//                    public void onNetworkException(YoutubeRequestException e) {
//                        // may be a connection problem, ask user to check his internet connection
//                    }
//
//                    @Override
//                    public void onError(Exception exception) {
//                        // some serious problem occured, just show some error message
//                    }
//                });

//                new AsyncTask<String, Void, YouTubeExtraction>() {
//                    YouTubeExtractor extractor = new YouTubeExtractor.Builder().build();
//
//                    @Override
//                    protected YouTubeExtraction doInBackground(String... strings) {
//                        YouTubeExtraction extraction = extractor.extract(strings[0]).blockingGet();
//                        return extraction;
//                    }
//
//                    @Override
//                    protected void onPostExecute(YouTubeExtraction result) {
////                        super.onPostExecute(youTubeExtraction);
//
//                        String videoUrl = "";
////                        List<Stream> arr= result.getStreams();
////                        for (Stream stream : arr) {
////                            if (stream instanceof Stream.VideoStream) {
////                                videoUrl = ((Stream.VideoStream) stream).getUrl();
////                            }
////                        }
//                        videoUrl = extractor.getVideoUrl(result);
//
//                        nativeChannel.invokeMethod("receiveYoutubeMediaLink", videoUrl);
//
//
//                    }
//                }.execute(id);

//                YouTubeExtractor extractor = new YouTubeExtractor.Builder().build();
//                Disposable disp = extractor.extract(id)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(extraction -> {
//                            String videoUrl = extractor.getVideoUrl(extraction);
//                            Log.d("YoutubeExtractor", "videoUrl: " + videoUrl);
//                            nativeChannel.invokeMethod("receiveYoutubeMediaLink", videoUrl);
//                        }, t -> Log.d("YoutubeExtractor", t.getMessage()
//                        ));

                YoutubeStreamExtractor youtubeStreamExtractor = new YoutubeStreamExtractor(new YoutubeStreamExtractor.ExtractorListner() {
                    @Override
                    public void onExtractionDone(
                            List<YTMedia> adativeStream,
                            final List<YTMedia> muxedStream,
                            List<YTSubtitles> subtitles,
                            YoutubeMeta meta) {
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
                                        videoUrl = media.url;
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

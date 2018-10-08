package io.fluttervn.flutteryoutubeextractor;

import android.content.Context;
import android.util.SparseArray;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterYoutubeExtractorPlugin
 */
public class FlutterYoutubeExtractorPlugin implements MethodCallHandler {
    private Context context;
    private final MethodChannel nativeChannel;

    public FlutterYoutubeExtractorPlugin(Context context, MethodChannel nativeChannel) {
        this.context = context;
        this.nativeChannel = nativeChannel;
        this.nativeChannel.setMethodCallHandler(this);
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter.youtube.extractor/native");
        channel.setMethodCallHandler(new FlutterYoutubeExtractorPlugin(registrar.activeContext(), channel));
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("getYoutubeMediaLink")) {
            String youtubeLink = (String) call.arguments;
            new YouTubeExtractor(this.context) {
                @Override
                public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                    if (ytFiles != null) {
                        int itag = 22;
                        String downloadUrl = "";
                        if (ytFiles.get(itag) != null)
                            downloadUrl = ytFiles.get(itag).getUrl();
                        nativeChannel.invokeMethod("receiveYoutubeMediaLink", downloadUrl);
                    }
                }
            }.extract(youtubeLink, true, true);

        } else {
            result.notImplemented();
        }
    }
}

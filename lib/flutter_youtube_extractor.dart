import 'package:flutter/services.dart';

class FlutterYoutubeExtractor {
  static const MethodChannel _nativeChannel =
      const MethodChannel('flutter.youtube.extractor/native');

  static void getYoutubeMediaLink(
      {String youtubeLink, Function(String mediaLink) onReceive}) {
    _nativeChannel.invokeMethod('getYoutubeMediaLink', youtubeLink);

    _nativeChannel.setMethodCallHandler((MethodCall call) {
      switch (call.method) {
        case 'receiveYoutubeMediaLink':
          onReceive(call.arguments);
          break;
      }
    });
  }
}

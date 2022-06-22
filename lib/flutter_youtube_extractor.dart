import 'dart:async';

import 'package:flutter/services.dart';

class FlutterYoutubeExtractor {
  static const MethodChannel _nativeChannel =
      MethodChannel('flutter.youtube.extractor/native');

  static Future<String> getYoutubeMediaLink({
    required String youtubeLink,
  }) async {
    var _link = '';
    final Completer<String> resultCompleter = Completer();
    if (youtubeLink.startsWith('https://www.youtube.com')) {
      if (youtubeLink.contains('watch?v=')) {
        _link = youtubeLink;
      } else if (youtubeLink.contains('embed')) {
        _link =
            'https://www.youtube.com/watch?v=${youtubeLink.substring(youtubeLink.indexOf('embed/') + 6)}';
      }
      _nativeChannel.invokeMethod('getYoutubeMediaLink', _link);
    } else {
      resultCompleter.complete('Unknown');
    }

    _nativeChannel.setMethodCallHandler((MethodCall call) async {
      switch (call.method) {
        case 'receiveYoutubeMediaLink':
          resultCompleter.complete(call.arguments);
          break;
      }
    });
    return resultCompleter.future;
  }

  static void requestRotateScreen({required bool isLandscape}) {
    _nativeChannel.invokeMethod('requestRotateScreen', isLandscape);
  }
}

import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_youtube_extractor/flutter_youtube_extractor.dart';

void main() => runApp(new MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => new _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _youtubeMediaLink = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  Future<void> initPlatformState() async {
    try {
      FlutterYoutubeExtractor.getYoutubeMediaLink(
          youtubeLink: 'https://www.youtube.com/watch?v=TZRpCGQsBCw',
          onReceive: (link) {
            if (!mounted) return;

            setState(() {
              _youtubeMediaLink = link;
            });
          });
    } on PlatformException {
      _youtubeMediaLink = 'Failed to get Youtube Media link.';
    }
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
        appBar: new AppBar(
          title: const Text('Plugin example app'),
        ),
        body: new Center(
          child: new Text('Youtube Media link: $_youtubeMediaLink\n'),
        ),
      ),
    );
  }
}

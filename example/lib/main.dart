import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_youtube_extractor/flutter_youtube_extractor.dart';

void main() => runApp(const MyApp());

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _youtubeMediaLink = 'Unknown';
  bool isLandScape = false;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  Future<void> initPlatformState() async {
    try {
      _youtubeMediaLink = await FlutterYoutubeExtractor.getYoutubeMediaLink(
          youtubeLink: 'https://www.youtube.com/watch?v=HaYwvSrT-Ig?rel=0');
    } on PlatformException {
      _youtubeMediaLink = 'Failed to get Youtube Media link.';
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
      appBar: AppBar(
        title: const Text('Plugin example app'),
      ),
      body: Center(
        child: Text('Youtube Media link: $_youtubeMediaLink\n'),
      ),
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.rotate_left),
        onPressed: () {
          isLandScape = !isLandScape;
          FlutterYoutubeExtractor.requestRotateScreen(isLandscape: isLandScape);
        },
      ),
    ));
  }
}

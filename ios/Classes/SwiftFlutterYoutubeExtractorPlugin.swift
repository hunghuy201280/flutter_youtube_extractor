import Flutter
import UIKit

public class SwiftFlutterYoutubeExtractorPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_youtube_extractor", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterYoutubeExtractorPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}

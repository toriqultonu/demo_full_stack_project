import 'dart:convert';

import 'package:web_socket_channel/web_socket_channel.dart';
import 'package:web_socket_channel/status.dart' as status;

class WebSocketService {
  late WebSocketChannel _channel;
  final String wsUrl = 'ws://localhost:8080/ws'; // Backend WebSocket URL

  Stream<dynamic> connect() {
    _channel = WebSocketChannel.connect(Uri.parse(wsUrl));
    return _channel.stream;
  }

  void disconnect() {
    _channel.sink.close(status.goingAway);
  }

  void subscribeToTopic(String topic) {
    _channel.sink.add(jsonEncode({'action': 'subscribe', 'topic': topic}));
  }
}
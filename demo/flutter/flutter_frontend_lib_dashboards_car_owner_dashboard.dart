import 'package:flutter/material.dart';
import 'package:web_socket_channel/web_socket_channel.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class CarOwnerDashboard extends StatefulWidget {
  @override
  _CarOwnerDashboardState createState() => _CarOwnerDashboardState();
}

class _CarOwnerDashboardState extends State<CarOwnerDashboard> {
  final channel = WebSocketChannel.connect(Uri.parse('ws://localhost:8080/ws'));

  void submitRequest(String description, String brand, String model) async {
    var response = await http.post(
      Uri.parse('http://localhost:8080/owner/request'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({'description': description, 'carBrand': brand, 'carModel': model}),
    );
    // Handle
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          // Form for request
          StreamBuilder(
            stream: channel.stream,
            builder: (context, snapshot) {
              return Text(snapshot.hasData ? '${snapshot.data}' : '');
            },
          ),
          // Inventory list with real-time
        ],
      ),
    );
  }

  @override
  void dispose() {
    channel.sink.close();
    super.dispose();
  }
}
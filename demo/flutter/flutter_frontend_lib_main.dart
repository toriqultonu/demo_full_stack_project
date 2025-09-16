import 'package:flutter/material.dart';
import 'package:marketplace_frontend/dashboards/car_owner_dashboard.dart';
// Import other dashboards

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @Override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Marketplace',
      home: CarOwnerDashboard(), // Route based on role
    );
  }
}
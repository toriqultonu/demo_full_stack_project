import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:go_router/go_router.dart';
import 'package:auto_industry/provider/auth_provider.dart';
import 'package:auto_industry/screens/login_screen.dart';
import 'package:auto_industry/screens/car_owner_dashboard.dart';
import 'package:auto_industry/screens/repair_shop_dashboard.dart';
import 'package:auto_industry/screens/vendor_dashboard.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  MyApp({super.key});

  final GoRouter _router = GoRouter(
    initialLocation: '/login',
    routes: [
      GoRoute(
        path: '/login',
        builder: (context, state) => const LoginScreen(),
      ),
      GoRoute(
        path: '/owner',
        builder: (context, state) => const CarOwnerDashboard(),
      ),
      GoRoute(
        path: '/shop',
        builder: (context, state) => const RepairShopDashboard(),
      ),
      GoRoute(
        path: '/vendor',
        builder: (context, state) => const VendorDashboard(),
      ),
    ],
  );

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => AuthProvider(),
      child: MaterialApp.router(
        title: 'Auto Industry',
        theme: ThemeData(primarySwatch: Colors.blue),
        routerConfig: _router,
      ),
    );
  }
}
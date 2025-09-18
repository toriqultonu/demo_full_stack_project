import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:go_router/go_router.dart';
import 'package:auto_industry/models/user.dart';
import 'package:auto_industry/services/api_service.dart';

class AuthProvider extends ChangeNotifier {
  User? _user;
  final _storage = const FlutterSecureStorage();
  final ApiService _apiService = ApiService();

  User? get user => _user;

  Future<void> login(String username, String password, BuildContext context) async {
    try {
      final response = await _apiService.login(username, password);
      _user = User.fromJson(response);
      await _storage.write(key: 'token', value: _user!.token);
      await _storage.write(key: 'refreshToken', value: _user!.refreshToken);
      _redirectBasedOnRole(context);
      notifyListeners();
    } catch (e) {
      // Handle error, e.g., show snackbar
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Login failed: $e')));
    }
  }

  Future<void> refreshToken(BuildContext context) async {
    final refreshToken = await _storage.read(key: 'refreshToken');
    if (refreshToken != null) {
      try {
        final response = await _apiService.refreshToken(refreshToken);
        _user = User.fromJson(response);
        await _storage.write(key: 'token', value: _user!.token);
        notifyListeners();
      } catch (e) {
        logout(context);
      }
    }
  }

  Future<void> logout(BuildContext context) async {
    _user = null;
    await _storage.deleteAll();
    GoRouter.of(context).go('/login');
    notifyListeners();
  }

  void _redirectBasedOnRole(BuildContext context) {
    switch (_user?.role) {
      case UserRole.carOwner:
        GoRouter.of(context).go('/owner');
        break;
      case UserRole.repairShop:
        GoRouter.of(context).go('/shop');
        break;
      case UserRole.vendor:
        GoRouter.of(context).go('/vendor');
        break;
      default:
        logout(context);
    }
  }
}
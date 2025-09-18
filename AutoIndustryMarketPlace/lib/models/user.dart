enum UserRole {
  carOwner,
  repairShop,
  vendor,
}

class User {
  final String id;
  final String username;
  final UserRole role;
  final String token;
  final String refreshToken;

  User({
    required this.id,
    required this.username,
    required this.role,
    required this.token,
    required this.refreshToken,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'],
      username: json['username'],
      role: _roleFromString(json['role']),
      token: json['token'],
      refreshToken: json['refreshToken'],
    );
  }

  static UserRole _roleFromString(String roleStr) {
    switch (roleStr.toLowerCase()) {
      case 'car_owner':
        return UserRole.carOwner;
      case 'repair_shop':
        return UserRole.repairShop;
      case 'vendor':
        return UserRole.vendor;
      default:
        throw Exception('Unknown role');
    }
  }
}
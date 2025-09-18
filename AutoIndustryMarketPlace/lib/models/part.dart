class Part {
  final String id;
  final String name;
  final String category;
  final double price;
  final int stock;

  Part({
    required this.id,
    required this.name,
    required this.category,
    required this.price,
    required this.stock,
  });

  factory Part.fromJson(Map<String, dynamic> json) {
    return Part(
      id: json['id'],
      name: json['name'],
      category: json['category'],
      price: json['price'],
      stock: json['stock'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'name': name,
      'category': category,
      'price': price,
      'stock': stock,
    };
  }
}
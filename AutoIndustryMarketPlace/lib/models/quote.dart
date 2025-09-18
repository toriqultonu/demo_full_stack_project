class Quote {
  final String id;
  final String repairShopId;
  final String requestId;
  final Map<String, double> priceBreakdown;
  final double estimatedHours;
  final double totalPrice;

  Quote({
    required this.id,
    required this.repairShopId,
    required this.requestId,
    required this.priceBreakdown,
    required this.estimatedHours,
    required this.totalPrice,
  });

  factory Quote.fromJson(Map<String, dynamic> json) {
    return Quote(
      id: json['id'],
      repairShopId: json['repairShopId'],
      requestId: json['requestId'],
      priceBreakdown: Map<String, double>.from(json['priceBreakdown'] ?? {}),
      estimatedHours: json['estimatedHours'],
      totalPrice: json['totalPrice'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'requestId': requestId,
      'priceBreakdown': priceBreakdown,
      'estimatedHours': estimatedHours,
      'totalPrice': totalPrice,
    };
  }
}
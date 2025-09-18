class RepairRequest {
  final String id;
  final String ownerId;
  final String description;
  final String carBrand;
  final String carModel;
  final int year;
  final List<String> partsNeeded;

  RepairRequest({
    required this.id,
    required this.ownerId,
    required this.description,
    required this.carBrand,
    required this.carModel,
    required this.year,
    required this.partsNeeded,
  });

  factory RepairRequest.fromJson(Map<String, dynamic> json) {
    return RepairRequest(
      id: json['id'],
      ownerId: json['ownerId'],
      description: json['description'],
      carBrand: json['carBrand'],
      carModel: json['carModel'],
      year: json['year'],
      partsNeeded: List<String>.from(json['partsNeeded'] ?? []),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'description': description,
      'carBrand': carBrand,
      'carModel': carModel,
      'year': year,
      'partsNeeded': partsNeeded,
    };
  }
}
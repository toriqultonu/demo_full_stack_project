import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:graphql_flutter/graphql_flutter.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:auto_industry/models/request.dart';
import 'package:auto_industry/models/quote.dart';
import 'package:auto_industry/models/part.dart';

class ApiService {
  static const String baseUrl = 'http://localhost:8080'; // Backend URL
  final _storage = const FlutterSecureStorage();
  late GraphQLClient _client;

  ApiService() {
    final httpLink = HttpLink('$baseUrl/graphql');
    _client = GraphQLClient(link: httpLink, cache: GraphQLCache());
  }

  Future<Map<String, dynamic>> login(String username, String password) async {
    final response = await http.post(
      Uri.parse('$baseUrl/auth/login'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({'username': username, 'password': password}),
    );
    if (response.statusCode == 200) {
      return jsonDecode(response.body);
    } else {
      throw Exception('Failed to login');
    }
  }

  Future<Map<String, dynamic>> refreshToken(String refreshToken) async {
    final response = await http.post(
      Uri.parse('$baseUrl/auth/refresh'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({'refreshToken': refreshToken}),
    );
    if (response.statusCode == 200) {
      return jsonDecode(response.body);
    } else {
      throw Exception('Failed to refresh token');
    }
  }

  Future<String?> _getToken() async {
    return await _storage.read(key: 'token');
  }

  Future<void> submitRequest(RepairRequest request) async {
    final token = await _getToken();
    final response = await http.post(
      Uri.parse('$baseUrl/api/requests'),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token',
      },
      body: jsonEncode(request.toJson()),
    );
    if (response.statusCode != 201) {
      throw Exception('Failed to submit request');
    }
  }

  Future<void> submitQuote(Quote quote) async {
    final token = await _getToken();
    final response = await http.post(
      Uri.parse('$baseUrl/api/quotes'),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token',
      },
      body: jsonEncode(quote.toJson()),
    );
    if (response.statusCode != 201) {
      throw Exception('Failed to submit quote');
    }
  }

  Future<void> addPart(Part part) async {
    final token = await _getToken();
    final response = await http.post(
      Uri.parse('$baseUrl/api/parts'), // Assuming REST wrapper for gRPC
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token',
      },
      body: jsonEncode(part.toJson()),
    );
    if (response.statusCode != 201) {
      throw Exception('Failed to add part');
    }
  }

  Future<List<RepairRequest>> getRequests() async {
    final token = await _getToken();
    final result = await _client.query(
      QueryOptions(
        document: gql(r'''
        query GetRequests {
          requests {
            id
            ownerId
            description
            carBrand
            carModel
            year
            partsNeeded
          }
        }
      '''),
        fetchPolicy: FetchPolicy.networkOnly,
        context: Context.fromList([
          HttpLinkHeaders(
            headers: {
              'Authorization': 'Bearer $token',
            },
          ),
        ]),
      ),
    );

    if (result.hasException) {
      throw result.exception!;
    }

    return (result.data?['requests'] as List)
        .map((json) => RepairRequest.fromJson(json))
        .toList();
  }

  Future<List<Quote>> getQuotes(String requestId) async {
    final token = await _getToken();
    final result = await _client.query(
      QueryOptions(
        document: gql(r'''
        query GetQuotes($requestId: String!) {
          quotes(requestId: $requestId) {
            id
            repairShopId
            requestId
            priceBreakdown
            estimatedHours
            totalPrice
          }
        }
      '''),
        variables: {'requestId': requestId},
        fetchPolicy: FetchPolicy.networkOnly,
        context: Context.fromList([
          HttpLinkHeaders(
            headers: {
              'Authorization': 'Bearer $token',
            },
          ),
        ]),
      ),
    );

    if (result.hasException) {
      throw result.exception!;
    }

    return (result.data?['quotes'] as List)
        .map((json) => Quote.fromJson(json))
        .toList();
  }


  Future<List<Part>> getParts() async {
    final token = await _getToken();
    final response = await http.get(
      Uri.parse('$baseUrl/api/parts'),
      headers: {'Authorization': 'Bearer $token'},
    );
    if (response.statusCode == 200) {
      return (jsonDecode(response.body) as List)
          .map((json) => Part.fromJson(json))
          .toList();
    } else {
      throw Exception('Failed to get parts');
    }
  }

// Add GraphQL subscription for real-time if needed, but handled in WebSocketService
}
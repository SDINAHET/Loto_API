// package com.fdjloto.api;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.http.*;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import java.util.HashMap;
// import java.util.Map;

// @Service
// public class LotoTrackerApiTest implements CommandLineRunner {

//     private static final Logger logger = LoggerFactory.getLogger(LotoTrackerApiTest.class);
//     private static final String BASE_URL = "http://localhost:8082";
//     private static final String TICKETS_ENDPOINT = "/api/tickets/";
//     private final RestTemplate restTemplate = new RestTemplate();
//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @Override
//     public void run(String... args) {
//         logger.info("🚀 Lancement des tests de l'API Loto Tracker...");

//         String adminToken = authenticate("admin@example.com", "adminpass");
//         String userToken = authenticate("user1@example.com", "password1");

//         String ticketId = createTicket(userToken);
//         getTicket(userToken, ticketId);
//         updateTicket(userToken, ticketId);
//         deleteTicket(userToken, ticketId);
//         getAllTickets(userToken);
//         getUserInfo(adminToken, "1");
//         calculateGains(userToken);
//         scrapeLotoResults(adminToken);
//         generatePrediction(adminToken);
//         getLatestPrediction();
//     }

//     private String authenticate(String email, String password) {
//         String endpoint = BASE_URL + "/api/auth/login3";
//         Map<String, String> payload = Map.of("email", email, "password", password);

//         ResponseEntity<String> response = restTemplate.postForEntity(endpoint, payload, String.class);
//         if (response.getStatusCode() == HttpStatus.OK) {
//             try {
//                 JsonNode json = objectMapper.readTree(response.getBody());
//                 logger.info("✅ Connexion réussie pour {}", email);
//                 return json.get("token").asText();
//             } catch (Exception e) {
//                 throw new ApiTestException("Erreur JSON lors de l'authentification", e);
//             }
//         }
//         throw new ApiTestException("❌ Connexion échouée pour " + email);
//     }

//     private String createTicket(String token) {
//         String endpoint = BASE_URL + "/api/tickets";
//         Map<String, Object> payload = Map.of("numbers", new int[]{1, 2, 3, 4, 5, 6});

//         HttpHeaders headers = getAuthHeaders(token);
//         ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.POST, new HttpEntity<>(payload, headers), String.class);

//         if (response.getStatusCode() == HttpStatus.CREATED) {
//             try {
//                 JsonNode json = objectMapper.readTree(response.getBody());
//                 return json.get("ticketId").asText();
//             } catch (Exception e) {
//                 throw new ApiTestException("Erreur JSON lors de la création du ticket", e);
//             }
//         }
//         throw new ApiTestException("❌ Création du ticket échouée");
//     }

//     private void getTicket(String token, String ticketId) {
//         String endpoint = BASE_URL + TICKETS_ENDPOINT + ticketId;
//         ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, new HttpEntity<>(getAuthHeaders(token)), String.class);
//         logger.info("✅ Ticket récupéré : {}", response.getBody());
//     }

//     private void updateTicket(String token, String ticketId) {
//         String endpoint = BASE_URL + TICKETS_ENDPOINT + ticketId;
//         Map<String, Object> payload = Map.of("numbers", new int[]{7, 8, 9, 10, 11, 12});
//         HttpHeaders headers = getAuthHeaders(token);

//         restTemplate.exchange(endpoint, HttpMethod.PUT, new HttpEntity<>(payload, headers), String.class);
//         logger.info("✅ Ticket mis à jour");
//     }

//     private void deleteTicket(String token, String ticketId) {
//         String endpoint = BASE_URL + TICKETS_ENDPOINT + ticketId;
//         restTemplate.exchange(endpoint, HttpMethod.DELETE, new HttpEntity<>(getAuthHeaders(token)), String.class);
//         logger.info("✅ Ticket supprimé");
//     }

//     private void getAllTickets(String token) {
//         String endpoint = BASE_URL + "/api/tickets";
//         ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, new HttpEntity<>(getAuthHeaders(token)), String.class);
//         logger.info("✅ Liste des tickets : {}", response.getBody());
//     }

//     private void getUserInfo(String token, String userId) {
//         String endpoint = BASE_URL + "/api/users/" + userId;
//         ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, new HttpEntity<>(getAuthHeaders(token)), String.class);
//         logger.info("✅ Infos utilisateur récupérées : {}", response.getBody());
//     }

//     private void calculateGains(String token) {
//         String endpoint = BASE_URL + "/api/gains/calculate";
//         ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, new HttpEntity<>(getAuthHeaders(token)), String.class);
//         logger.info("✅ Calcul des gains effectué : {}", response.getBody());
//     }

//     private void scrapeLotoResults(String token) {
//         String endpoint = BASE_URL + "/api/loto/scrape";
//         restTemplate.exchange(endpoint, HttpMethod.GET, new HttpEntity<>(getAuthHeaders(token)), String.class);
//         logger.info("✅ Scraping des résultats du loto terminé");
//     }

//     private void generatePrediction(String token) {
//         String endpoint = BASE_URL + "/api/predictions/generate";
//         restTemplate.exchange(endpoint, HttpMethod.POST, new HttpEntity<>(getAuthHeaders(token)), String.class);
//         logger.info("✅ Prédiction de tirage générée");
//     }

//     private void getLatestPrediction() {
//         String endpoint = BASE_URL + "/api/predictions/latest";
//         ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
//         logger.info("✅ Dernière prédiction récupérée : {}", response.getBody());
//     }

//     private HttpHeaders getAuthHeaders(String token) {
//         HttpHeaders headers = new HttpHeaders();
//         headers.set("Authorization", "Bearer " + token);
//         headers.setContentType(MediaType.APPLICATION_JSON);
//         return headers;
//     }

//     public static class ApiTestException extends RuntimeException {
//         public ApiTestException(String message) { super(message); }
//         public ApiTestException(String message, Throwable cause) { super(message, cause); }
//     }
// }

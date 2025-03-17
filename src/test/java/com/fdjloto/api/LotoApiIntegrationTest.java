// // package com.fdjloto.api;


// // import com.fasterxml.jackson.databind.ObjectMapper;
// // import org.junit.jupiter.api.BeforeEach;
// // import org.junit.jupiter.api.Test;
// // import org.junit.jupiter.api.extension.ExtendWith;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// // import org.springframework.boot.test.context.SpringBootTest;
// // import org.springframework.http.MediaType;
// // import org.springframework.test.context.junit.jupiter.SpringExtension;
// // import org.springframework.test.web.servlet.MockMvc;

// // import java.util.HashMap;
// // import java.util.Map;

// // import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// // import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// // @ExtendWith(SpringExtension.class)
// // @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// // @AutoConfigureMockMvc
// // public class LotoApiIntegrationTest {

// //     @Autowired
// //     private MockMvc mockMvc;

// //     @Autowired
// //     private ObjectMapper objectMapper;

// //     private String jwtToken;

// //     @BeforeEach
// //     void setUp() throws Exception {
// //         // Inscription d'un utilisateur
// //         Map<String, String> newUser = new HashMap<>();
// //         newUser.put("email", "testuser@example.com");
// //         newUser.put("password", "password123");

// //         mockMvc.perform(post("/api/auth/register")
// //                 .contentType(MediaType.APPLICATION_JSON)
// //                 .content(objectMapper.writeValueAsString(newUser)))
// //                 .andExpect(status().isOk());

// //         // Connexion et récupération du token JWT
// //         var loginResponse = mockMvc.perform(post("/api/auth/login4")
// //                 .contentType(MediaType.APPLICATION_JSON)
// //                 .content(objectMapper.writeValueAsString(newUser)))
// //                 .andExpect(status().isOk())
// //                 .andReturn()
// //                 .getResponse()
// //                 .getContentAsString();

// //         jwtToken = objectMapper.readTree(loginResponse).get("token").asText();
// //     }

// //     @Test
// //     void testTicketCrudOperations() throws Exception {
// //         // Création d’un ticket
// //         Map<String, Object> ticket = new HashMap<>();
// //         ticket.put("numbers", new int[]{1, 2, 3, 4, 5});
// //         ticket.put("luckyNumber", 6);
// //         ticket.put("drawDate", "2025-03-16");

// //         var createResponse = mockMvc.perform(post("/api/tickets")
// //                 .header("Authorization", "Bearer " + jwtToken)
// //                 .contentType(MediaType.APPLICATION_JSON)
// //                 .content(objectMapper.writeValueAsString(ticket)))
// //                 .andExpect(status().isCreated())
// //                 .andReturn()
// //                 .getResponse()
// //                 .getContentAsString();

// //         String ticketId = objectMapper.readTree(createResponse).get("id").asText();

// //         // Récupération du ticket
// //         mockMvc.perform(get("/api/tickets/" + ticketId)
// //                 .header("Authorization", "Bearer " + jwtToken))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.numbers[0]").value(1));

// //         // Mise à jour du ticket
// //         ticket.put("luckyNumber", 7);
// //         mockMvc.perform(put("/api/tickets/" + ticketId)
// //                 .header("Authorization", "Bearer " + jwtToken)
// //                 .contentType(MediaType.APPLICATION_JSON)
// //                 .content(objectMapper.writeValueAsString(ticket)))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.luckyNumber").value(7));

// //         // Suppression du ticket
// //         mockMvc.perform(delete("/api/tickets/" + ticketId)
// //                 .header("Authorization", "Bearer " + jwtToken))
// //                 .andExpect(status().isNoContent());

// //         // Vérification que le ticket n'existe plus
// //         mockMvc.perform(get("/api/tickets/" + ticketId)
// //                 .header("Authorization", "Bearer " + jwtToken))
// //                 .andExpect(status().isNotFound());
// //     }

// //     @Test
// //     void testCalculateWinnings() throws Exception {
// //         mockMvc.perform(get("/api/gains/calculate")
// //                 .header("Authorization", "Bearer " + jwtToken))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.message").value("Winnings calculated successfully"));
// //     }

// //     @Test
// //     void testGetLast20Draws() throws Exception {
// //         mockMvc.perform(get("/api/historique/last20"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$[0].dateDeTirage").exists());
// //     }
// // }

// package com.fdjloto.api;

// import io.restassured.RestAssured;
// import io.restassured.response.Response;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.HttpStatus;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.Matchers.equalTo;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
// public class LotoApiIntegrationTest {

//     private static String JWT_TOKEN;

//     @BeforeAll
//     static void setUp() {
//         RestAssured.baseURI = "http://localhost:8082";

//         // Authentification et récupération du token JWT
//         Response response = given()
//                 .contentType("application/json")
//                 .body("{\"email\":\"test@example.com\", \"password\":\"password123\"}")
//                 .post("/api/auth/login4");

//         JWT_TOKEN = response.jsonPath().getString("token");
//         assertTrue(JWT_TOKEN != null && !JWT_TOKEN.isEmpty());
//     }

//     @Test
//     void testGetAllTickets() {
//         given()
//                 .header("Authorization", "Bearer " + JWT_TOKEN)
//                 .when()
//                 .get("/api/tickets")
//                 .then()
//                 .statusCode(HttpStatus.OK.value());
//     }

//     @Test
//     void testCreateTicket() {
//         given()
//                 .header("Authorization", "Bearer " + JWT_TOKEN)
//                 .contentType("application/json")
//                 .body("{\"numbers\":[1,2,3,4,5], \"luckyNumber\":6, \"drawDate\":\"2025-03-12\"}")
//                 .when()
//                 .post("/api/tickets")
//                 .then()
//                 .statusCode(HttpStatus.CREATED.value());
//     }

//     @Test
//     void testCalculateWinnings() {
//         given()
//                 .header("Authorization", "Bearer " + JWT_TOKEN)
//                 .when()
//                 .get("/api/gains/calculate")
//                 .then()
//                 .statusCode(HttpStatus.OK.value());
//     }

//     @Test
//     void testGetLast20Results() {
//         given()
//                 .when()
//                 .get("/api/historique/last20")
//                 .then()
//                 .statusCode(HttpStatus.OK.value());
//     }
// }

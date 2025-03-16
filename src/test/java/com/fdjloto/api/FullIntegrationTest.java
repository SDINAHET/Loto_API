// package com.fdjloto.api;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// @ExtendWith(SpringExtension.class)
// public class FullIntegrationTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Test
//     public void testSwaggerDocs() throws Exception {
//         mockMvc.perform(get("/v3/api-docs"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testLotoScrapeEndpoint() throws Exception {
//         mockMvc.perform(get("/api/loto/scrape"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testGenerateLotteryPrediction() throws Exception {
//         mockMvc.perform(post("/api/predictions/generate"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testGetLatestPrediction() throws Exception {
//         mockMvc.perform(get("/api/predictions/latest"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testGetAllTickets() throws Exception {
//         mockMvc.perform(get("/api/tickets"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testCreateTicket() throws Exception {
//         String newTicket = "{\"numbers\":\"1-2-3-4-5\",\"drawDate\":\"2025-03-20\"}";
//         mockMvc.perform(post("/api/tickets")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(newTicket))
//                 .andExpect(status().isCreated());
//     }

//     @Test
//     public void testGetUserById() throws Exception {
//         mockMvc.perform(get("/api/users/1"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testUpdateUser() throws Exception {
//         String updatedUser = "{\"email\":\"updateduser@example.com\",\"password\":\"newpassword123\"}";
//         mockMvc.perform(put("/api/users/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(updatedUser))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testDeleteUser() throws Exception {
//         mockMvc.perform(delete("/api/users/1"))
//                 .andExpect(status().isNoContent());
//     }

//     @Test
//     public void testCalculateTicketGains() throws Exception {
//         mockMvc.perform(get("/api/gains/calculate"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testGetLotoResults() throws Exception {
//         mockMvc.perform(get("/api/loto/results"))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testHealthCheck() throws Exception {
//         mockMvc.perform(get("/api/hello"))
//                 .andExpect(status().isOk());
//     }
// }

// package com.fdjloto.api;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseCookie;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// class FullIntegrationTest {

//     @Autowired
//     private MockMvc mockMvc;

//     private String userId;
//     private String ticketId;
//     private ResponseCookie authCookie;
//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @BeforeEach
//     void setUp() throws Exception {
//         // 🔹 1️⃣ Création d'un utilisateur
//         String newUser = """
//             {
//                 "firstName": "John",
//                 "lastName": "Doe",
//                 "email": "testuser@example.com",
//                 "password": "P@ssw0rd"
//             }
//             """;

//         MvcResult userResult = mockMvc.perform(post("/api/users/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(newUser))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.id").exists())
//                 .andExpect(jsonPath("$.email").value("testuser@example.com"))
//                 .andReturn();

//         userId = extractId(userResult);

//         // 🔹 2️⃣ Connexion de l'utilisateur et récupération du JWT
//         String loginRequest = """
//             {
//                 "email": "testuser@example.com",
//                 "password": "P@ssw0rd"
//             }
//             """;

//         MvcResult loginResult = mockMvc.perform(post("/api/auth/login3")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(loginRequest))
//                 .andExpect(status().isOk())
//                 .andReturn();

//         authCookie = ResponseCookie.from("jwtToken", loginResult.getResponse().getCookie("jwtToken").getValue()).build();
//     }

//     // =================== 🔍 TESTS PUBLICS =================== //

//     @Test
//     void testHealthCheck() throws Exception {
//         mockMvc.perform(get("/api/hello"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("API is running successfully"));
//     }

//     @Test
//     void testSwaggerDocs() throws Exception {
//         mockMvc.perform(get("/v3/api-docs"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//     }

//     @Test
//     void testLotoScrapeEndpoint() throws Exception {
//         mockMvc.perform(get("/api/loto/scrape"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//     }

//     @Test
//     void testGenerateLotteryPrediction() throws Exception {
//         mockMvc.perform(post("/api/predictions/generate"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//     }

//     @Test
//     void testGetLatestPrediction() throws Exception {
//         mockMvc.perform(get("/api/predictions/latest"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//     }

//     // =================== 🔐 SCÉNARIO AUTHENTIFIÉ =================== //

//     @Test
//     void testAuthenticatedUserScenario() throws Exception {
//         // 🔹 3️⃣ Récupérer les infos utilisateur (authentifié)
//         mockMvc.perform(authenticatedRequest(get("/api/users/" + userId)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(userId))
//                 .andExpect(jsonPath("$.email").value("testuser@example.com"));

//         // 🔹 4️⃣ Modifier l'utilisateur (authentifié)
//         String updatedUser = """
//             {
//                 "email": "updateduser@example.com",
//                 "password": "newpassword123"
//             }
//             """;
//         mockMvc.perform(authenticatedRequest(put("/api/users/" + userId))
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(updatedUser))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.email").value("updateduser@example.com"));

//         // 🔹 5️⃣ Créer un ticket (authentifié)
//         String newTicket = """
//             {
//                 "numbers": "1-2-3-4-5",
//                 "chanceNumber": "7",
//                 "drawDate": "2025-03-20"
//             }
//             """;

//         MvcResult ticketResult = mockMvc.perform(authenticatedRequest(post("/api/tickets")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(newTicket)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.id").exists())
//                 .andExpect(jsonPath("$.numbers").value("1-2-3-4-5"))
//                 .andExpect(jsonPath("$.chanceNumber").value("7"))
//                 .andReturn();

//         ticketId = extractId(ticketResult);

//         // 🔹 6️⃣ Récupérer tous les tickets
//         mockMvc.perform(authenticatedRequest(get("/api/tickets")))
//                 .andExpect(status().isOk());

//         // 🔹 7️⃣ Calculer les gains
//         mockMvc.perform(authenticatedRequest(get("/api/gains/calculate")))
//                 .andExpect(status().isOk());

//         // 🔹 8️⃣ Mettre à jour le ticket
//         String updatedTicket = """
//             {
//                 "numbers": "6-7-8-9-10",
//                 "chanceNumber": "8",
//                 "drawDate": "2025-03-22"
//             }
//             """;
//         mockMvc.perform(authenticatedRequest(put("/api/tickets/" + ticketId))
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(updatedTicket))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.numbers").value("6-7-8-9-10"))
//                 .andExpect(jsonPath("$.chanceNumber").value("8"));

//         // 🔹 9️⃣ Recalculer les gains
//         mockMvc.perform(authenticatedRequest(get("/api/gains/calculate")))
//                 .andExpect(status().isOk());

//         // 🔹 🔟 Supprimer le ticket
//         mockMvc.perform(authenticatedRequest(delete("/api/tickets/" + ticketId)))
//                 .andExpect(status().isNoContent());
//     }

//     // =================== 📌 MÉTHODES UTILES =================== //

//     // Ajoute automatiquement le cookie d'authentification aux requêtes
//     private MockHttpServletRequestBuilder authenticatedRequest(MockHttpServletRequestBuilder requestBuilder) {
//         return requestBuilder.header("Cookie", authCookie.toString());
//     }

//     // Extrait l'ID JSON depuis la réponse API
//     private String extractId(MvcResult result) throws Exception {
//         JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
//         return jsonNode.get("id").asText();
//     }
// }


// package com.fdjloto.api;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import jakarta.servlet.http.Cookie;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// class FullIntegrationTest {

//     @Autowired
//     private MockMvc mockMvc;

//     private String userId;
//     private String ticketId;
//     private String jwtToken;
//     private Cookie authCookie; // 🔥 Stocke le cookie d'authentification
//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @BeforeEach
//     void setUp() throws Exception {
//         // 🔹 1️⃣ Création d'un utilisateur
//         String newUser = """
//             {
//                 "firstName": "John",
//                 "lastName": "Doe",
//                 "email": "testuser@example.com",
//                 "password": "P@ssw0rd"
//             }
//             """;

//         MvcResult userResult = mockMvc.perform(post("/api/users/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(newUser))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.id").exists())
//                 .andReturn();

//         userId = extractId(userResult);

//         // 🔹 2️⃣ Connexion de l'utilisateur et récupération du JWT & Cookie
//         String loginRequest = """
//             {
//                 "email": "testuser@example.com",
//                 "password": "P@ssw0rd"
//             }
//             """;

//         MvcResult loginResult = mockMvc.perform(post("/api/auth/login3") // 🔥 Utilisation de l'endpoint qui génère un cookie
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(loginRequest))
//                 .andExpect(status().isOk())
//                 .andReturn();

//         jwtToken = extractToken(loginResult);
//         authCookie = loginResult.getResponse().getCookie("jwtToken"); // ✅ Correction ici !

//         // Vérification que le cookie contient bien l'email et le rôle attendu
//         mockMvc.perform(authenticatedRequest(get("/api/auth/me"))) // Endpoint qui retourne les infos du user
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.email").value("testuser@example.com"))
//                 .andExpect(jsonPath("$.role").value("role_user")); // 🔥 Vérifie que l'utilisateur n'est pas admin
//     }

//     // =================== 🔍 TESTS PUBLICS =================== //

//     @Test
//     void testHealthCheck() throws Exception {
//         mockMvc.perform(get("/api/hello"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("API is running successfully"));
//     }

//     @Test
//     void testSwaggerDocs() throws Exception {
//         mockMvc.perform(get("/v3/api-docs"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//     }

//     // =================== 🔐 TESTS AUTHENTIFIÉS =================== //

//     @Test
//     void testAuthenticatedUserScenario() throws Exception {
//         // 🔹 3️⃣ Récupérer les infos utilisateur (authentifié)
//         mockMvc.perform(authenticatedRequest(get("/api/users/" + userId)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(userId))
//                 .andExpect(jsonPath("$.email").value("testuser@example.com"));

//         // 🔹 4️⃣ Modifier l'utilisateur (authentifié)
//         String updatedUser = """
//             {
//                 "email": "updateduser@example.com",
//                 "password": "newpassword123"
//             }
//             """;
//         mockMvc.perform(authenticatedRequest(put("/api/users/" + userId))
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(updatedUser))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.email").value("updateduser@example.com"));

//         // 🔹 5️⃣ Créer un ticket (authentifié)
//         String newTicket = """
//             {
//                 "numbers": "1-2-3-4-5",
//                 "chanceNumber": "7",
//                 "drawDate": "2025-03-20"
//             }
//             """;

//         MvcResult ticketResult = mockMvc.perform(authenticatedRequest(post("/api/tickets")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(newTicket)))
//                 .andExpect(status().isCreated()) // 🔥 Corrigé : Autorisation correcte
//                 .andExpect(jsonPath("$.id").exists())
//                 .andExpect(jsonPath("$.numbers").value("1-2-3-4-5"))
//                 .andReturn();

//         ticketId = extractId(ticketResult);

//         // 🔹 6️⃣ Récupérer ses propres tickets (authentifié)
//         mockMvc.perform(authenticatedRequest(get("/api/tickets")))
//                 .andExpect(status().isOk());

//         // 🔹 7️⃣ Calculer les gains
//         mockMvc.perform(authenticatedRequest(get("/api/gains/calculate")))
//                 .andExpect(status().isOk());

//         // 🔹 8️⃣ Mettre à jour son propre ticket
//         String updatedTicket = """
//             {
//                 "numbers": "6-7-8-9-10",
//                 "chanceNumber": "8",
//                 "drawDate": "2025-03-22"
//             }
//             """;
//         mockMvc.perform(authenticatedRequest(put("/api/tickets/" + ticketId))
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(updatedTicket))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.numbers").value("6-7-8-9-10"));

//         // 🔹 9️⃣ Supprimer son propre ticket
//         mockMvc.perform(authenticatedRequest(delete("/api/tickets/" + ticketId)))
//                 .andExpect(status().isNoContent());
//     }

//     // =================== 📌 MÉTHODES UTILES =================== //

//     // Ajoute le JWT + Cookie d'authentification aux requêtes
//     private MockHttpServletRequestBuilder authenticatedRequest(MockHttpServletRequestBuilder requestBuilder) {
//         return requestBuilder
//                 .header("Authorization", "Bearer " + jwtToken)
//                 .cookie(authCookie); // 🔥 Ajoute bien le cookie
//     }

//     // Extrait l'ID JSON depuis la réponse API
//     private String extractId(MvcResult result) throws Exception {
//         JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
//         return jsonNode.get("id").asText();
//     }

//     // Extrait le token JWT depuis la réponse API
//     private String extractToken(MvcResult result) throws Exception {
//         JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
//         return jsonNode.get("token").asText();
//     }
// }

// package com.fdjloto.api;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import jakarta.servlet.http.Cookie;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// class FullIntegrationTest {

//     @Autowired
//     private MockMvc mockMvc;

//     private String userId;
//     private String ticketId;
//     private String jwtToken;
//     private Cookie authCookie;
//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @BeforeEach
//     void setUp() throws Exception {
//         String newUser = """
//             {
//                 \"firstName\": \"John\",
//                 \"lastName\": \"Doe\",
//                 \"email\": \"testuser@example.com\",
//                 \"password\": \"P@ssw0rd\"
//             }
//             """;

//         MvcResult userResult = mockMvc.perform(post("/api/users/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(newUser))
//                 .andExpect(status().isCreated())
//                 .andReturn();

//         userId = extractId(userResult);

//         String loginRequest = """
//             {
//                 \"email\": \"testuser@example.com\",
//                 \"password\": \"P@ssw0rd\"
//             }
//             """;

//         MvcResult loginResult = mockMvc.perform(post("/api/auth/login3")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(loginRequest))
//                 .andExpect(status().isOk())
//                 .andReturn();

//         jwtToken = extractToken(loginResult);
//         authCookie = loginResult.getResponse().getCookie("jwtToken");
//     }

//     @Test
//     void testHealthCheck() throws Exception {
//         mockMvc.perform(get("/api/hello"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("API is running successfully"));
//     }

//     @Test
//     void testAuthenticatedUserScenario() throws Exception {
//         mockMvc.perform(authenticatedRequest(get("/api/users/" + userId)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").value(userId));
//     }

//     private MockHttpServletRequestBuilder authenticatedRequest(MockHttpServletRequestBuilder requestBuilder) {
//         return requestBuilder.header("Authorization", "Bearer " + jwtToken).cookie(authCookie);
//     }

//     private String extractId(MvcResult result) throws Exception {
//         JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
//         return jsonNode.get("id").asText();
//     }

//     private String extractToken(MvcResult result) throws Exception {
//         JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
//         return jsonNode.get("token").asText();
//     }
// }



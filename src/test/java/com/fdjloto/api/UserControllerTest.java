// package com.fdjloto.api;

// import com.fdjloto.api.model.User;
// import com.fdjloto.api.controller.UserController;
// import com.fdjloto.api.service.UserService;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;
// import java.util.Optional;
// import java.util.UUID;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.hamcrest.Matchers.is;

// @WebMvcTest(UserController.class)
// class UserControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private UserService userService;

//     @Autowired
//     private ObjectMapper objectMapper;

//     private final UUID userId = UUID.randomUUID();

//     @Test
//     @WithMockUser(username = "admin@example.com", roles = {"ADMIN"})
//     void updateUser_AsAdmin_ShouldReturn200() throws Exception {
//         User existingUser = new User();
//         existingUser.setId(userId.toString());
//         existingUser.setFirstName("John");
//         existingUser.setLastName("Doe");
//         existingUser.setEmail("john.doe@example.com");
//         existingUser.setPassword("oldPassword");
//         existingUser.setAdmin(false);

//         User updatedUser = new User();
//         updatedUser.setId(userId.toString());
//         updatedUser.setFirstName("John");
//         updatedUser.setLastName("Doe");
//         updatedUser.setEmail("john.doe@example.com");
//         updatedUser.setPassword("newPassword");
//         updatedUser.setAdmin(false);

//         Mockito.when(userService.getUserById(userId)).thenReturn(Optional.of(existingUser));
//         Mockito.when(userService.updateUser(userId, updatedUser)).thenReturn(updatedUser);

//         mockMvc.perform(put("/api/users/{id}", userId)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(updatedUser)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.firstName", is("John")))
//                 .andExpect(jsonPath("$.lastName", is("Doe")))
//                 .andExpect(jsonPath("$.email", is("john.doe@example.com")));
//     }

//     @Test
//     @WithMockUser(username = "user@example.com", roles = {"USER"})
//     void updateUser_AsUser_ShouldReturn403() throws Exception {
//         User updatedUser = new User();
//         updatedUser.setId(userId.toString());
//         updatedUser.setFirstName("John");
//         updatedUser.setLastName("Doe");
//         updatedUser.setEmail("john.doe@example.com");
//         updatedUser.setPassword("newPassword");
//         updatedUser.setAdmin(false);

//         mockMvc.perform(put("/api/users/{id}", userId)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(updatedUser)))
//                 .andExpect(status().isForbidden());
//     }

//     @Test
//     @WithMockUser(username = "admin@example.com", roles = {"ADMIN"})
//     void updateUser_NotFound_ShouldReturn404() throws Exception {
//         User updatedUser = new User();
//         updatedUser.setId(userId.toString());
//         updatedUser.setFirstName("John");
//         updatedUser.setLastName("Doe");
//         updatedUser.setEmail("john.doe@example.com");
//         updatedUser.setPassword("newPassword");
//         updatedUser.setAdmin(false);

//         Mockito.when(userService.getUserById(userId)).thenReturn(Optional.empty());

//         mockMvc.perform(put("/api/users/{id}", userId)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(updatedUser)))
//                 .andExpect(status().isNotFound());
//     }

// }

// // import org.junit.jupiter.api.Test;
// // import static org.junit.jupiter.api.Assertions.assertTrue;

// // public class ExampleTest {

// //     @Test
// //     void testExample() {
// //         assertTrue(true);
// //     }
// // }

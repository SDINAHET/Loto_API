// package com.fdjloto.api.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.HashMap;
// import java.util.Map;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

// @RestController
// @RequestMapping("/api")
// public class HealthCheckController {

//     /**
//      * ✅ Endpoint de vérification de l'état du serveur
//      * URL : http://localhost:8082/api/health
//      */
// 	@GetMapping("/health")
// 	public ResponseEntity<Map<String, String>> healthCheck() {
// 		Map<String, String> response = new HashMap<>();
// 		response.put("status", "UP");
// 		response.put("serverTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
// 		return ResponseEntity.ok(response);
// 	}
// }


// <script>
// setTimeout(() => {
// 	window.location.href = "/"; // Redirige vers ton site
// }, 45000); // Attente de 5 secondes avant la redirection

// async function checkServer() {
// 	try {
// 		let response = await fetch("http://localhost:8082/api/health");
// 		if (response.ok) {
// 			document.getElementById("loading-screen").style.display = "none";
// 			document.getElementById("main-content").style.display = "block";
// 		} else {
// 			setTimeout(checkServer, 2000); // Vérifie toutes les 2 secondes
// 		}
// 	} catch (error) {
// 		setTimeout(checkServer, 2000); // Réessaie si erreur
// 	}
// }
// checkServer();
// </script>

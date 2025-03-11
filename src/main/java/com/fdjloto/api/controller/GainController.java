// // // package com.fdjloto.api.controller;

// // // import com.fdjloto.api.dto.GainResultDTO;
// // // import com.fdjloto.api.model.TicketGain;
// // // import com.fdjloto.api.repository.TicketGainRepository;
// // // import com.fdjloto.api.service.GainCalculationService;

// // // import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// // // import io.swagger.v3.oas.annotations.tags.Tag;

// // // import org.springframework.beans.factory.annotation.Autowired;
// // // import org.springframework.security.access.prepost.PreAuthorize;
// // // import org.springframework.security.core.context.SecurityContextHolder;
// // // import org.springframework.web.bind.annotation.*;
// // // import org.springframework.security.core.Authentication;


// // // import java.util.List;
// // // import java.util.Optional;

// // // @RestController
// // // @RequestMapping("/api/gains")
// // // @Tag(name = "Ticket Gains Management", description = "Endpoints for managing Tickets for searching and calculating gains.")
// // // @SecurityRequirement(name = "bearerAuth")
// // // @SecurityRequirement(name = "jwtCookieAuth")
// // // @CrossOrigin(
// // //     origins = "http://127.0.0.1:5500",
// // //     allowCredentials = "true",
// // //     allowedHeaders = "*",
// // //     methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
// // // )
// // // public class GainController {

// // //     @Autowired
// // //     private GainCalculationService gainCalculationService;

// // //     @Autowired
// // //     private TicketGainRepository ticketGainRepository;

// // //     /**
// // //      * 🚀 Calcule et enregistre les gains des tickets.
// // //      */
// // //     @GetMapping("/calculate")
// // // 	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// // //     public List<GainResultDTO> calculateGains() {
// // // 		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// // //     	System.out.println("✅ Requête API Gains par : " + auth.getName() + " - Rôles : " + auth.getAuthorities());

// // //         return gainCalculationService.calculerGains();
// // //     }

// // //     /**
// // //      * 🔍 Récupère tous les gains enregistrés en base.
// // //      */
// // //     @GetMapping
// // // 	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// // //     public List<TicketGain> getAllGains() {
// // // 		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// // //     	System.out.println("✅ Requête API Gains par : " + auth.getName() + " - Rôles : " + auth.getAuthorities());

// // //         return ticketGainRepository.findAll();
// // //     }

// // //     /**
// // //      * 🔍 Récupère les gains d'un ticket spécifique par ID.
// // //      */
// // //     @GetMapping("/{ticketId}")
// // // 	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// // //     public Optional<TicketGain> getGainByTicketId(@PathVariable String ticketId) {
// // // 		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// // //     	System.out.println("✅ Requête API Gains par : " + auth.getName() + " - Rôles : " + auth.getAuthorities());

// // //         return Optional.ofNullable(ticketGainRepository.findByTicketId(ticketId));
// // //     }
// // // }

// // package com.fdjloto.api.controller;

// // import com.fdjloto.api.dto.GainResultDTO;
// // import com.fdjloto.api.model.TicketGain;
// // import com.fdjloto.api.repository.TicketGainRepository;
// // import com.fdjloto.api.service.GainCalculationService;
// // import com.fdjloto.api.service.TicketGainService;
// // import com.fdjloto.api.dto.TicketGainDTO;

// // import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// // import io.swagger.v3.oas.annotations.tags.Tag;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.access.prepost.PreAuthorize;
// // import org.springframework.security.core.Authentication;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.web.bind.annotation.*;

// // import org.slf4j.Logger;
// // import org.slf4j.LoggerFactory;

// // import java.util.List;
// // import java.util.Optional;

// // @RestController
// // @RequestMapping("/api/gains")
// // @Tag(name = "Ticket Gains Management", description = "Endpoints for managing Tickets for searching and calculating gains.")
// // @SecurityRequirement(name = "bearerAuth")
// // @SecurityRequirement(name = "jwtCookieAuth")
// // @CrossOrigin(
// //     origins = "http://127.0.0.1:5500",
// //     allowCredentials = "true",
// //     allowedHeaders = "*",
// //     methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
// // )
// // public class GainController {

// //     private static final Logger logger = LoggerFactory.getLogger(GainController.class);
// //     private static final String LOGGER_MESSAGE = "✅ Requête API Gains par : {} - Rôles : {}";

// //     private final GainCalculationService gainCalculationService;
// //     private final TicketGainRepository ticketGainRepository;

// //     @Autowired
// //     public GainController(GainCalculationService gainCalculationService, TicketGainRepository ticketGainRepository) {
// //         this.gainCalculationService = gainCalculationService;
// //         this.ticketGainRepository = ticketGainRepository;
// //     }

// //     /**
// //      * 🚀 Calcule et enregistre les gains des tickets.
// //      */
// //     @GetMapping("/calculate")
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     public List<GainResultDTO> calculateGains() {
// //         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// //         logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());

// //         return gainCalculationService.calculerGains();
// //     }

// //     /**
// //      * 🔍 Récupère tous les gains enregistrés en base.
// //      */
// //     @GetMapping
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     public List<TicketGain> getAllGains() {
// //         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// //         logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());

// //         return ticketGainRepository.findAll();
// //     }

// //     /**
// //      * 🔍 Récupère les gains d'un ticket spécifique par ID.
// //      */
// //     // @GetMapping("/ticket/{ticketId}")
// //     // @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     // public TicketGain getGainByTicketId(@PathVariable String ticketId) {
// //     //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// //     //     logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());

// //     //     return ticketGainRepository.findByTicketId(ticketId)
// //     //         .orElseThrow(() -> new RuntimeException("Aucun gain trouvé pour le ticket " + ticketId));
// //     // }

// //     @GetMapping("/ticket/{ticketId}")
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     public GainResultDTO getGainByTicketId(@PathVariable String ticketId) {
// //         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
// //         logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());
// //         return ticketGainService.getGainByTicketId(ticketId);
// //     }

// // }


// package com.fdjloto.api.controller;

// import com.fdjloto.api.dto.TicketGainDTO;
// import com.fdjloto.api.service.GainCalculationService;
// import com.fdjloto.api.service.TicketGainService;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import java.util.List;

// @RestController
// @RequestMapping("/api/gains")
// @Tag(name = "Ticket Gains Management", description = "Endpoints for managing Tickets for searching and calculating gains.")
// @SecurityRequirement(name = "bearerAuth")
// @SecurityRequirement(name = "jwtCookieAuth")
// @CrossOrigin(
//     origins = "http://127.0.0.1:5500",
//     allowCredentials = "true",
//     allowedHeaders = "*",
//     methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
// )
// public class GainController {

//     private static final Logger logger = LoggerFactory.getLogger(GainController.class);
//     private static final String LOGGER_MESSAGE = "✅ Requête API Gains par : {} - Rôles : {}";

//     private final GainCalculationService gainCalculationService;
//     private final TicketGainService ticketGainService; // 🔥 Ajout du service manquant

//     public GainController(GainCalculationService gainCalculationService, TicketGainService ticketGainService) {
//         this.gainCalculationService = gainCalculationService;
//         this.ticketGainService = ticketGainService;
//     }

//     /**
//      * 🚀 Calcule et enregistre les gains des tickets.
//      */
//     @GetMapping("/calculate")
//     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     public List<TicketGainDTO> calculateGains() {
//         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//         logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());

//         return gainCalculationService.calculerGains();
//     }

//     /**
//      * 🔍 Récupère les gains d'un ticket spécifique par ID.
//      */
//     @GetMapping("/ticket/{ticketId}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     public TicketGainDTO getGainByTicketId(@PathVariable String ticketId) {
//         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//         logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());

//         return ticketGainService.getGainByTicketId(ticketId);
//     }

//     /**
//      * 🔍 Récupère tous les gains enregistrés en base.
//      */
//     @GetMapping
//     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     public List<TicketGain> getAllGains() {
//         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//         logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());

//         return ticketGainRepository.findAll();
//     }
// }

package com.fdjloto.api.controller;

import com.fdjloto.api.dto.TicketGainDTO;
import com.fdjloto.api.service.GainCalculationService;
import com.fdjloto.api.service.TicketGainService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/gains")
@Tag(name = "Ticket Gains Management", description = "Endpoints for managing and calculating ticket gains.")
@SecurityRequirement(name = "bearerAuth")
@SecurityRequirement(name = "jwtCookieAuth")
@CrossOrigin(
    origins = "http://127.0.0.1:5500",
    allowCredentials = "true",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class GainController {

    private static final Logger logger = LoggerFactory.getLogger(GainController.class);
    private static final String LOGGER_MESSAGE = "✅ Requête API Gains par : {} - Rôles : {}";

    private final GainCalculationService gainCalculationService;
    private final TicketGainService ticketGainService;

    public GainController(GainCalculationService gainCalculationService, TicketGainService ticketGainService) {
        this.gainCalculationService = gainCalculationService;
        this.ticketGainService = ticketGainService;
    }

    /**
     * 🚀 Calcule et enregistre les gains des tickets.
     */
    @PostMapping("/calculate")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<TicketGainDTO> calculateGains() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());

        return gainCalculationService.calculerGains();
    }

    /**
     * 🔍 Récupère tous les gains enregistrés en base (format DTO).
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<TicketGainDTO> getAllGains() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());

        return ticketGainService.getAllGains();
    }

    /**
     * 🎟️ Récupère les gains d'un ticket spécifique par ID.
     */
    @GetMapping("/ticket/{ticketId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public TicketGainDTO getGainByTicketId(@PathVariable String ticketId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info(LOGGER_MESSAGE, auth.getName(), auth.getAuthorities());

        return ticketGainService.getGainByTicketId(ticketId);
    }
}

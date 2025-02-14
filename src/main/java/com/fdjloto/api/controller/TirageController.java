package com.fdjloto.api.controller;

import com.fdjloto.api.model.LotoResult;
import com.fdjloto.api.repository.LotoRepository;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Locale;
import java.util.Optional;


@RestController
@RequestMapping("/api/tirages")
// @CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Autorise CORS pour Live Server
public class TirageController {

    private final LotoRepository lotoRepository;

	// ✅ Définition correcte de DATE_FORMAT
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE dd/MM/yyyy", Locale.FRANCE);

    public TirageController(LotoRepository lotoRepository) {
        this.lotoRepository = lotoRepository;
    }

    @GetMapping("/dates")
    public List<String> getAvailableDates() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy", Locale.FRANCE);

        return lotoRepository.findAll().stream()
                .map(LotoResult::getDateDeTirage) // Récupérer la date
                .filter(date -> date != null) // Éviter les valeurs nulles
                .distinct() // Éliminer les doublons
                .sorted((d1, d2) -> d2.compareTo(d1)) // Trier en ordre décroissant (du plus récent au plus ancien)
                .map(dateFormat::format) // Convertir la date en "jj/MM/aaaa"
                .collect(Collectors.toList());
    }

    // @GetMapping
    // public List<LotoResult> getTiragesParPeriode(
    //         @RequestParam("startDate") Date startDate,
    //         @RequestParam("endDate") Date endDate) {
    //     return lotoRepository.findByDateDeTirageBetween(startDate, endDate); // ✅ Correction
    // }

	    /**
     * ✅ Permet de récupérer les tirages entre deux dates
     * ✅ Fixe startDate par défaut à la date du dernier tirage si non fourni
     * ✅ endDate ne peut pas être antérieur à startDate
     */
    @GetMapping
    public List<LotoResult> getTiragesParPeriode(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {

        // Récupérer la date du dernier tirage disponible
        Optional<Date> lastTirageDate = lotoRepository.findAll().stream()
                .map(LotoResult::getDateDeTirage)
                .max(Date::compareTo);

        // Si startDate n'est pas fourni, utiliser la date du dernier tirage
        if (startDate == null && lastTirageDate.isPresent()) {
            startDate = lastTirageDate.get();
        }

        // Vérifier si endDate est avant startDate et l'ajuster
        if (endDate != null && startDate != null && endDate.before(startDate)) {
            endDate = startDate;
        }

        return lotoRepository.findByDateDeTirageBetween(startDate, endDate);
    }

    // /**
    //  * ✅ Retourne la liste des dates pour endDate en cachant celles avant startDate
    //  */
    // @GetMapping("/dates-end")
	// public List<String> getAvailableEndDates(@RequestParam("startDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate) {
	// 	return lotoRepository.findAll().stream()
	// 			.map(LotoResult::getDateDeTirage)
	// 			.filter(date -> date != null && !date.before(startDate)) // Masquer les dates avant startDate
	// 			.distinct()
	// 			.sorted((d1, d2) -> d2.compareTo(d1)) // Trier du plus récent au plus ancien
	// 			.map(DATE_FORMAT::format) // ✅ Utilisation correcte
	// 			.collect(Collectors.toList()); // ✅ Utilisation correcte pour Java 8-15, sinon `.toList()`
	// }


}

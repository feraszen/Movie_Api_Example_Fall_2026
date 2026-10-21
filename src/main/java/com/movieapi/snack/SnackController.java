
package com.movieapi.snack;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/snacks")
public class SnackController {

    private final SnackService snackService;

    public SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping
    public ResponseEntity<List<Snack>> getAllSnacks(
            @RequestParam(required = false) Double maxPrice) {
        return ResponseEntity.ok(
                snackService.getAllSnacks(maxPrice));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Snack> getSnackById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                snackService.getSnackById(id));
    }

    @PostMapping
    public ResponseEntity<Snack> createSnack(
            @RequestBody Snack snack) {
        Snack saved = snackService.createSnack(snack);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Snack> updateSnack(
            @PathVariable Long id,
            @RequestBody Snack snack) {
        return ResponseEntity.ok(
                snackService.updateSnack(id, snack));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Snack> patchSnack(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(
                snackService.patchSnack(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSnack(
            @PathVariable Long id) {
        snackService.deleteSnack(id);
        return ResponseEntity.noContent().build();
    }
}

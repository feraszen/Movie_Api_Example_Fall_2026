
package com.movieapi.snack;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class SnackService {

    private final SnackRepository snackRepository;

    public SnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    public List<Snack> getAllSnacks(Double maxPrice) {
        if (maxPrice != null) {
            return snackRepository.findByPriceLessThanEqual(maxPrice);
        }
        return snackRepository.findAll();
    }

    public Snack getSnackById(Long id) {
        return snackRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Snack not found: " + id));
    }

    public Snack createSnack(Snack snack) {
        validateSnack(snack);
        snack.setSnackId(null);
        return snackRepository.save(snack);
    }

    public Snack updateSnack(Long id, Snack updatedSnack) {
        Snack existing = getSnackById(id);

        existing.setName(updatedSnack.getName());
        existing.setType(updatedSnack.getType());
        existing.setPrice(updatedSnack.getPrice());
        existing.setShareable(updatedSnack.isShareable());

        validateSnack(existing);
        return snackRepository.save(existing);
    }

    public Snack patchSnack(Long id, Map<String, Object> updates) {
        Snack existing = getSnackById(id);

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            Object value = entry.getValue();

            switch (entry.getKey()) {
                case "name" -> {
                    if (!(value instanceof String)) {
                        throw badRequest("name must be a string");
                    }
                    existing.setName((String) value);
                }
                case "type" -> {
                    if (!(value instanceof String)) {
                        throw badRequest("type must be a string");
                    }
                    existing.setType((String) value);
                }
                case "price" -> {
                    if (!(value instanceof Number)) {
                        throw badRequest("price must be a number");
                    }
                    existing.setPrice(((Number) value).doubleValue());
                }
                case "shareable" -> {
                    if (!(value instanceof Boolean)) {
                        throw badRequest("shareable must be boolean");
                    }
                    existing.setShareable((Boolean) value);
                }
                default -> throw badRequest(
                        "Unknown field: " + entry.getKey());
            }
        }

        validateSnack(existing);
        return snackRepository.save(existing);
    }

    public void deleteSnack(Long id) {
        Snack existing = getSnackById(id);
        snackRepository.delete(existing);
    }

    private void validateSnack(Snack snack) {
        if (snack.getName() == null || snack.getName().isBlank()) {
            throw badRequest("Name cannot be blank");
        }

        if (snack.getType() == null || snack.getType().isBlank()) {
            throw badRequest("Type cannot be blank");
        }

        if (!Double.isFinite(snack.getPrice())
                || snack.getPrice() < 0) {
            throw badRequest("Price must be non-negative");
        }
    }

    private ResponseStatusException badRequest(String message) {
        return new ResponseStatusException(
                HttpStatus.BAD_REQUEST, message);
    }
}

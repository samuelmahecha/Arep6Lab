package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return propertyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyRepository.save(property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property updatedProperty) {
        return propertyRepository.findById(id)
                .map(property -> {
                    property.setAddress(updatedProperty.getAddress());
                    property.setPrice(updatedProperty.getPrice());
                    property.setSize(updatedProperty.getSize());
                    property.setDescription(updatedProperty.getDescription());
                    return ResponseEntity.ok(propertyRepository.save(property));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProperty(@PathVariable Long id) {
        return propertyRepository.findById(id)
                .map(property -> {
                    propertyRepository.delete(property);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

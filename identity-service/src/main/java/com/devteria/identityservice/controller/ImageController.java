package com.devteria.identityservice.controller;


import com.devteria.identityservice.entity.Image;
import com.devteria.identityservice.service.ImageService;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@RestController
@RequestMapping("api/img")
public class ImageController {
    private final String UPLOAD_DIR = "uploads/";
    private ImageService imageService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            Image savedImage = imageService.saveImage(file);
            return ResponseEntity.ok("Image uploaded successfully with ID: " + savedImage.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
//        try {
//            Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//            return ResponseEntity.ok("Image uploaded successfully: " + filePath.toString());
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
//        }
//    }

//    @GetMapping("/view/{filename}")
//    public ResponseEntity<UrlResource> getImage(@PathVariable String filename) {
//        try {
//            Path filePath = Paths.get(UPLOAD_DIR + filename);
//            UrlResource resource = new UrlResource(filePath.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return ResponseEntity.ok()
//                        .contentType(MediaType.IMAGE_JPEG)
//                        .body(resource);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//        } catch (MalformedURLException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
@GetMapping("/{id}")
public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
    Optional<Image> imageOptional = imageService.getImage(id);
    if (imageOptional.isPresent()) {
        Image image = imageOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image.getImgData());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
}

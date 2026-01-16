package uvt.tw.conferencemanagementsystem.app.file.service;

import io.minio.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uvt.tw.conferencemanagementsystem.api.exception.UserNotFoundException;
import uvt.tw.conferencemanagementsystem.app.conference.model.ConferenceEntity;
import uvt.tw.conferencemanagementsystem.app.conference.repository.ConferenceRepository;
import uvt.tw.conferencemanagementsystem.app.user.model.UserEntity;
import uvt.tw.conferencemanagementsystem.app.user.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

  private final MinioClient minioClient;
  private final UserRepository userRepository;
  private final ConferenceRepository conferenceRepository;

  @Value("${minio.bucket-name}")
  private String bucketName;

  @Value("${minio.image-size}")
  private long maxImageSize;

  @PostConstruct
  public void init() {
    try {
      boolean found =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

      if (!found) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        log.info("Bucket '{}' created successfully", bucketName);
      } else {
        log.info("Bucket '{}' already exists", bucketName);
      }
    } catch (Exception e) {
      log.error("Error initializing MinIO bucket", e);
      throw new RuntimeException("Could not initialize MinIO bucket", e);
    }
  }

  public InputStream getImage(String imageName) throws Exception {
    return minioClient.getObject(
        GetObjectArgs.builder().bucket(bucketName).object(imageName).build());
  }

  @Transactional
  public String uploadProfilePicture(MultipartFile file, Long userId) {

    UserEntity user =
        userRepository
            .findById(userId)
            .orElseThrow(
                () ->
                    new UserNotFoundException(String.format("User with id: %d not found", userId)));

    if (user.getProfilePictureUrl() != null) {
      try {
        deleteFile(user.getProfilePictureUrl());
      } catch (Exception e) {
        throw new RuntimeException(
            String.format("Could not find file with name %s", user.getProfilePictureUrl()));
      }
    }

    try {
      String fileName = generateProfilePictureName(file.getOriginalFilename(), user.getEmail());

      minioClient.putObject(
          PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                  file.getInputStream(), file.getSize(), -1)
              .contentType(file.getContentType())
              .build());
      user.setProfilePictureUrl(fileName);

      log.info("Image uploaded successfully: {}", fileName);
      return fileName;

    } catch (Exception e) {
      log.error("Error uploading image", e);
      throw new RuntimeException("Could not upload image", e);
    }
  }

  @Transactional
  public String uploadConferencePicture(MultipartFile file, Long conferenceId) {

    ConferenceEntity conference =
        conferenceRepository
            .findById(conferenceId)
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        String.format("Conference with id: %d not found", conferenceId)));

    if (conference.getCoverImageUrl() != null) {
      try {
        deleteFile(conference.getCoverImageUrl());
      } catch (Exception e) {
        throw new RuntimeException(
            String.format("Could not find file with name %s", conference.getCoverImageUrl()));
      }
    }

    try {
      String fileName =
          generateConferencePictureName(
              file.getOriginalFilename(),
              conference.getOrganizer().getEmail(),
              conference.getTitle());

      minioClient.putObject(
          PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                  file.getInputStream(), file.getSize(), -1)
              .contentType(file.getContentType())
              .build());
      conference.setCoverImageUrl(fileName);

      log.info("Image uploaded successfully: {}", fileName);
      return fileName;
    } catch (Exception e) {
      log.error("Error uploading image", e);
      throw new RuntimeException("Could not upload image", e);
    }
  }

  public String determineContentType(String filename) {
    if (filename.endsWith(".png")) return "image/png";
    if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) return "image/jpeg";
    if (filename.endsWith(".gif")) return "image/gif";
    if (filename.endsWith(".webp")) return "image/webp";
    return "application/octet-stream";
  }

  private void deleteFile(String fileName) throws Exception {
    minioClient.removeObject(
        RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
  }

  private String generateProfilePictureName(String originalFileName, String userEmail) {
    String extension = "";
    if (originalFileName != null && originalFileName.contains(".")) {
      extension = originalFileName.substring(originalFileName.lastIndexOf("."));
    }
    return userEmail + "_profile_picture" + extension;
  }

  private String generateConferencePictureName(
      String originalFileName, String userEmail, String conferenceName) {
    String extension = "";
    if (originalFileName != null && originalFileName.contains(".")) {
      extension = originalFileName.substring(originalFileName.lastIndexOf("."));
    }
    return userEmail + "_" + conferenceName + "_conference_picture" + extension;
  }

  public void validateImage(MultipartFile file) {
    if (file.isEmpty()) {
      throw new IllegalArgumentException("File is empty");
    }

    if (file.getSize() > maxImageSize) {
      throw new IllegalArgumentException("File size exceeds maximum limit");
    }

    String contentType = file.getContentType();
    if (contentType == null || !contentType.startsWith("image/")) {
      throw new IllegalArgumentException("File must be an image");
    }
  }
}

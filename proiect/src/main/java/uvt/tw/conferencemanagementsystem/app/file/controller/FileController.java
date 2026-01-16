package uvt.tw.conferencemanagementsystem.app.file.controller;

import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uvt.tw.conferencemanagementsystem.api.FileApi;
import uvt.tw.conferencemanagementsystem.app.file.service.ImageService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FileController implements FileApi {

  private final ImageService imageService;

  @Override
  public ResponseEntity<byte[]> getFile(String fileName) {
    log.info("Received request for getFile with fileName: {}", fileName);
    try {
      InputStream imageStream = imageService.getImage(fileName);
      byte[] imageBytes = imageStream.readAllBytes();
      imageStream.close();

      String contentType = imageService.determineContentType(fileName);

      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(contentType))
          .body(imageBytes);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @Override
  public ResponseEntity<String> uploadUserProfilePicture(MultipartFile file, Long userId) {
    log.info("Received request for uploadUserProfilePicture with userId {}", userId);
    imageService.validateImage(file);
    return ResponseEntity.ok().body(imageService.uploadProfilePicture(file, userId));
  }

  @Override
  public ResponseEntity<String> uploadConferencePicture(MultipartFile file, Long conferenceId) {
    log.info("Received request for uploadConferencePicture with conferenceId {}", conferenceId);
    imageService.validateImage(file);
    return ResponseEntity.ok().body(imageService.uploadConferencePicture(file, conferenceId));
  }
}

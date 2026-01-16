package uvt.tw.conferencemanagementsystem.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Tag(name = "File")
public interface FileApi {

  @GetMapping("/download/{fileName}")
  ResponseEntity<byte[]> getFile(@PathVariable String fileName);

  @PostMapping("/upload/user/{userId}")
  ResponseEntity<String> uploadUserProfilePicture(
      @RequestBody MultipartFile file, @PathVariable Long userId);

  @PostMapping("/upload/conference/{conferenceId}")
  ResponseEntity<String> uploadConferencePicture(
      @RequestBody MultipartFile file, @PathVariable Long conferenceId);
}

package com.example.TravelApp.Controllers;

import com.example.TravelApp.DTOs.FileInfoDTO;
import com.example.TravelApp.Service.Interfaces.IFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/files" , produces = MediaType.APPLICATION_JSON_VALUE)
public class FilesController {
    @Autowired
    IFilesService filesService;

    @PostMapping(value = "/{travelReqId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<FileInfoDTO>> uploadFile(@PathVariable String travelReqId, @RequestParam("files") MultipartFile[] files) {

        List<FileInfoDTO> fileInfo = filesService.saveUploadedFiles(travelReqId,files);
        return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
    }

    @GetMapping("/all/{travelReqId}")
    public ResponseEntity<List<FileInfoDTO>> getListFiles(@PathVariable String travelReqId) throws IOException {
        List<FileInfoDTO> fileInfo = filesService.loadAll(travelReqId);
        return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<List<FileInfoDTO>> removeFile(@PathVariable String fileName) throws IOException {
        List<FileInfoDTO> fileInfo = filesService.removeFile(fileName);
        return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity getFile(@PathVariable String fileName) {
        byte[] resource = filesService.getFile(fileName);
        String mediaType = filesService.getMediaType(fileName);

        try {
            return ResponseEntity.ok()
                    .contentLength(resource.length)
                    .contentType(MediaType.parseMediaType(mediaType))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }
}

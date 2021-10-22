package com.example.TravelApp.Service.Interfaces;

import com.example.TravelApp.DTOs.FileInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface IFilesService {
    List<FileInfoDTO> saveUploadedFiles(String travelReqId, MultipartFile[] files);
    List<FileInfoDTO> loadAll(String travelReqId) throws IOException;
    List<FileInfoDTO> removeFile(String fileName) throws IOException;
    byte[] getFile(String fileName);
    String getMediaType(String fileName);

    List<String> loadAllFilePaths(String travelReqId);
}

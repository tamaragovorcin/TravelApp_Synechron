package com.example.TravelApp.Service.Implementations;

import com.example.TravelApp.DTOs.FileInfoDTO;
import com.example.TravelApp.Service.Interfaces.IFilesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FilesServiceImpl implements IFilesService {

    private final Path root = Paths.get("src/main/java/com/example/TravelApp/UploadedFiles");

    @Override
    public List<FileInfoDTO> saveUploadedFiles(String travelReqId, MultipartFile[] files) {
        for (MultipartFile file: files) {
            if(!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                Path uploadsDir = Paths.get(root+"/"+travelReqId+"_"+filename);

                if (filename.contains("..")) {
                    throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, uploadsDir, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to store file " + filename, e);
                }
            }
        }
        return loadAll(travelReqId);
    }



    @Override
    public List<FileInfoDTO> loadAll(String travelReqId){
        List<FileInfoDTO> fileInfos = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/java/com/example/TravelApp/UploadedFiles/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().startsWith(travelReqId+"_"))
                    .forEach(path-> {
                        FileInfoDTO fileInfoDTO = new FileInfoDTO();
                        fileInfoDTO.setName(path.getFileName().toString());
                        try {
                            fileInfoDTO.setBytes(Files.readAllBytes(path));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        fileInfos.add(fileInfoDTO);
                    });
        }
        catch(Exception e) {
            return fileInfos;

        }
        return fileInfos;
    }

    @Override
    public List<FileInfoDTO> removeFile(String fileName) throws IOException {
        File f= new File("src/main/java/com/example/TravelApp/UploadedFiles/"+fileName);
        if(f.delete()) {
            String travelReqID = fileName.split("_")[0];
            return loadAll(travelReqID);
        }
        return new ArrayList<>();
    }

    @Override
    public byte[] getFile(String fileName) {
        try {
            File file= new File("src/main/java/com/example/TravelApp/UploadedFiles/"+fileName);
            return Files.readAllBytes(file.toPath());
        } catch(Exception e) {
            return new byte[0];
        }
    }

    @Override
    public String getMediaType(String fileName) {
        Path path = Paths.get("src/main/java/com/example/TravelApp/UploadedFiles/"+fileName);
        String[] parts = fileName.split("\\.");
        if(parts[parts.length-1].equals("xls")) {
            return "application/vnd.ms-excel";
        }
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public List<String> loadAllFilePaths(String travelReqId){
        List<String> filePaths = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/java/com/example/TravelApp/UploadedFiles/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().startsWith(travelReqId+"_"))
                    .forEach(path-> {
                        filePaths.add(path.toString());
                    });
        }
        catch(Exception e) {
            return filePaths;
        }
        return filePaths;
    }
}

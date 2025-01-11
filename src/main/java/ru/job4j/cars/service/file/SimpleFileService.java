package ru.job4j.cars.service.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.model.File;
import ru.job4j.cars.repository.file.HQLFileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimpleFileService implements FileService {

    private final HQLFileRepository hqlFileRepository;

    private final String storageDirectory;

    public SimpleFileService(HQLFileRepository hqlFileRepository, @Value("${file.directory}") String storageDirectory) {
        this.hqlFileRepository = hqlFileRepository;
        this.storageDirectory = storageDirectory;
        createStorageDirectory(storageDirectory);
    }

    private void createStorageDirectory(String path) {
        try {
            Files.createDirectories(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFileBytes(String path, byte[] content) {
        try {
            Files.write(Path.of(path), content);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private String getNewFilePath(String sourceName) {
        return storageDirectory + java.io.File.separator + UUID.randomUUID() + sourceName;
    }

    private void deleteFile(String path) {
        try {
            Files.deleteIfExists(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] readFileAsByte(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File save(FileDto fileDto) {
        var path = getNewFilePath(fileDto.getName());
        writeFileBytes(path, fileDto.getContent());
        return hqlFileRepository.save(new File(fileDto.getName(), path));
    }

    @Override
    public Optional<FileDto> findById(Long id) {
        var hqlFileOptional = hqlFileRepository.findById(id);
        if (hqlFileOptional.isEmpty()) {
            return Optional.empty();
        }
        var content = readFileAsByte(hqlFileOptional.get().getPath());
        return Optional.of(new FileDto(hqlFileOptional.get().getName(), content));
    }

    @Override
    public List<File> findAll() {
        return hqlFileRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        var hqlFileOptional = hqlFileRepository.findById(id);
        if (hqlFileOptional.isEmpty()) {
            return false;
        }
        deleteFile(hqlFileOptional.get().getPath());
        return hqlFileRepository.deleteById(id);
    }
}
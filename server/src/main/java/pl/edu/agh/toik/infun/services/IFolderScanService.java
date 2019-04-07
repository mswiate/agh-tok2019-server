package pl.edu.agh.toik.infun.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFolderScanService {
    List<String> scanFolder();
}

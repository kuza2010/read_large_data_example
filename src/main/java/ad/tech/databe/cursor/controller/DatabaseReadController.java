package ad.tech.databe.cursor.controller;

import ad.tech.databe.cursor.repository.RandomDataRepository;
import ad.tech.databe.cursor.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseReadController {

    private final DataService batchDataService;
    private final DataService simpleDataService;
    private final RandomDataRepository repository;

    @Autowired
    public DatabaseReadController(RandomDataRepository repository,
                                  DataService batchDataService,
                                  DataService simpleDataService) {
        this.repository = repository;
        this.batchDataService = batchDataService;
        this.simpleDataService = simpleDataService;
    }

    @GetMapping("/count")
    public Long countRows() {
        return repository.count();
    }

    @GetMapping("/data/all")
    public String all() {
        simpleDataService.doSomeJob();
        return "simple job was posted";
    }

    @GetMapping("/data/all/batch")
    public String allBatch() {
        batchDataService.doSomeJob();
        return "batch job was posted";
    }
}

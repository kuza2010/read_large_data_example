package ad.tech.databe.cursor.service.impl;

import ad.tech.databe.cursor.entity.RandomData;
import ad.tech.databe.cursor.repository.RandomDataRepository;
import ad.tech.databe.cursor.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleDataService implements DataService {

    private final RandomDataRepository repository;

    @Async
    public void doSomeJob() {
        log.info("Start reading data at once. Do not expect it will work...");
        List<RandomData> all = repository.findAll();
        // by idea it should crash here
        log.info("Simple read found {} rows", all.size());
    }

}

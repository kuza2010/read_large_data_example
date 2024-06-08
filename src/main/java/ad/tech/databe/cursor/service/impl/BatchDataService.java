package ad.tech.databe.cursor.service.impl;

import ad.tech.databe.cursor.entity.RandomData;
import ad.tech.databe.cursor.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchDataService implements DataService {

    private static final Integer BATCH_SIZE = 100_000;
    private static final String QUERY = "select * from random_data LIMIT 100000";

    private final DataSource dataSource;

    @Async
    @SneakyThrows
    public void doSomeJob() {
        log.info("Start reading data with batch. Do not expect it will work...");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     QUERY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
        ) {
            connection.setAutoCommit(false);
            statement.setFetchSize(BATCH_SIZE);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    traceRowIfNecessary(resultSet.getRow() % 100_000 == 0, resultSet);
                }
                resultSet.last();
                log.info("Batch read found {} rows", resultSet.getRow());
            }
        }
    }

    @SneakyThrows
    private void traceRowIfNecessary(Boolean print, ResultSet resultSet) {
        if (print) {
            DataClassRowMapper<RandomData> mapper = new DataClassRowMapper<>(RandomData.class);
            log.info("Already process {} rows, last row id is: {} ...",
                    resultSet.getRow(), mapper.mapRow(resultSet, resultSet.getRow()).getId());
        }
    }

}

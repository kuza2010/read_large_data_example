package ad.tech.databe.cursor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "random_data")
public class RandomData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "column1")
    private Integer column1;

    @Column(name = "column2")
    private String column2;

    @Column(name = "column3")
    private LocalDate column3;

    @Column(name = "column4")
    private Boolean column4;

    @Column(name = "column5")
    private Double column5;

    @Column(name = "column6")
    private String column6;

    @Column(name = "column7")
    private java.sql.Timestamp column7;

    @Column(name = "column8")
    private Integer column8;

}

package ru.job4j.cars.model;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "files")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String path;

    public File(String name, String path) {
        this.name = name;
        this.path = path;
    }
}

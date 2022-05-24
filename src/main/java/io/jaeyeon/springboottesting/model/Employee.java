package io.jaeyeon.springboottesting.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @Table(name = "employees")
@AllArgsConstructor @NoArgsConstructor @Builder
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;
}

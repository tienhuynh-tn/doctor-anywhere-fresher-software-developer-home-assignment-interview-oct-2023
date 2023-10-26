package com.tienhuynhtn.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Task", schema = "dbo", catalog = "DoctorAnywhere")
public class TaskEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private long id;
    @Basic
    @Column(name = "Title", nullable = false, length = 100)
    private String title;
    @Basic
    @Column(name = "Description", nullable = false, length = 2147483647)
    private String description;
    @Basic
    @Column(name = "Completed", nullable = false)
    private boolean completed;

}

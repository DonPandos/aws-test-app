package com.awstest.springboot.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class File {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "original_name")
    @NonNull
    private String originalName;

    @Column(name = "storage_name")
    @NonNull
    private String storageName;
}

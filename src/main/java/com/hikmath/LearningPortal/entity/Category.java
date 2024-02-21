package com.hikmath.LearningPortal.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor

public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long categoryId;

@Column(name="title")
private String categoryTitle;

@Column(name = "description")
private String categoryDescription;

@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Course> courses=new ArrayList<>();
}

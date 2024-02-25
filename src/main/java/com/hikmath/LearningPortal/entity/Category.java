package com.hikmath.LearningPortal.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor

public class Category {
    @Id
    @GenericGenerator(name = "cat_id", strategy = "com.hikmath.LearningPortal.entity.customCategoryIdGenerator")
    @GeneratedValue(generator = "cat_id")

    @Column(name = "cat_id")
    private String categoryId;

    @Column(name = "title")
    private String categoryTitle;

    @Column(name = "description")
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();
}

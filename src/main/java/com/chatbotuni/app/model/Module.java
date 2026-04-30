package com.chatbotuni.app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false)
    private String name;

    private String lecturer;

    private Integer credits;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String assessment;

    private String timetable;

    private LocalDate deadline;

    private String book;

    @Column(name = "moodle_url")
    private String moodleUrl;

    @Column(name = "resource_url")
    private String resourceUrl;

    public Module() {
    }

    // --- getters and setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLecturer() { return lecturer; }
    public void setLecturer(String lecturer) { this.lecturer = lecturer; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAssessment() { return assessment; }
    public void setAssessment(String assessment) { this.assessment = assessment; }

    public String getTimetable() { return timetable; }
    public void setTimetable(String timetable) { this.timetable = timetable; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }

    public String getMoodleUrl() { return moodleUrl; }
    public void setMoodleUrl(String moodleUrl) { this.moodleUrl = moodleUrl; }

    public String getResourceUrl() { return resourceUrl; }
    public void setResourceUrl(String resourceUrl) { this.resourceUrl = resourceUrl; }
}
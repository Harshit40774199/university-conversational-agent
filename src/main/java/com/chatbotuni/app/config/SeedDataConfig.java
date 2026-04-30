package com.chatbotuni.app.config;

import com.chatbotuni.app.model.KnowledgeSource;
import com.chatbotuni.app.model.Module;
import com.chatbotuni.app.repository.KnowledgeSourceRepository;
import com.chatbotuni.app.repository.ModuleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class SeedDataConfig {

    @Bean
    CommandLineRunner seedModules(ModuleRepository moduleRepository, KnowledgeSourceRepository knowledgeSourceRepository) {
        return args -> {
            if (moduleRepository.count() == 0) {
                Module m1 = new Module();
                m1.setCode("SET11106");
                m1.setSlug("database-systems");
                m1.setName("Database Systems");
                m1.setLecturer("Dr Smith");
                m1.setCredits(20);
                m1.setDescription("This module covers relational databases, SQL, normalization, ER diagrams, and database design principles.");
                m1.setAssessment("Coursework 50%, Exam 50%");
                m1.setTimetable("Wednesday 2:00 PM - 4:00 PM");
                m1.setDeadline(LocalDate.of(2026, 3, 25));
                m1.setBook("Database System Concepts");
                m1.setMoodleUrl("https://moodle.napier.ac.uk/my/");
                m1.setResourceUrl("https://moodle.napier.ac.uk/my/");

                Module m2 = new Module();
                m2.setCode("SET08101");
                m2.setSlug("web-development");
                m2.setName("Web Development");
                m2.setLecturer("Prof John");
                m2.setCredits(20);
                m2.setDescription("This module introduces HTML, CSS, JavaScript, responsive design, and practical web application development.");
                m2.setAssessment("Coursework 60%, Project 40%");
                m2.setTimetable("Monday 10:00 AM - 12:00 PM");
                m2.setDeadline(LocalDate.of(2026, 3, 30));
                m2.setBook("Learning Web Design");
                m2.setMoodleUrl("https://moodle.napier.ac.uk/my/");
                m2.setResourceUrl("https://moodle.napier.ac.uk/my/");

                Module m3 = new Module();
                m3.setCode("SET11007");
                m3.setSlug("artificial-intelligence");
                m3.setName("Artificial Intelligence");
                m3.setLecturer("Dr Lee");
                m3.setCredits(20);
                m3.setDescription("This module introduces intelligent systems, machine learning fundamentals, search techniques, and AI applications.");
                m3.setAssessment("Coursework 40%, Final Exam 60%");
                m3.setTimetable("Friday 11:00 AM - 1:00 PM");
                m3.setDeadline(LocalDate.of(2026, 4, 5));
                m3.setBook("Artificial Intelligence: A Modern Approach");
                m3.setMoodleUrl("https://moodle.napier.ac.uk/my/");
                m3.setResourceUrl("https://moodle.napier.ac.uk/my/");

                moduleRepository.save(m1);
                moduleRepository.save(m2);
                moduleRepository.save(m3);
            }

            if (knowledgeSourceRepository.count() == 0) {
                knowledgeSourceRepository.save(new KnowledgeSource(
                        "modules",
                        "Moodle Modules",
                        "https://moodle.napier.ac.uk/my/",
                        "module,modules,moodle,course,courses,subject,subjects,handbook",
                        "Use this source for module access, course spaces, Moodle-based module guidance, and module-related student questions."
                ));
                knowledgeSourceRepository.save(new KnowledgeSource(
                        "fees and funding",
                        "Fees and Funding",
                        "https://my.napier.ac.uk/money",
                        "fees,funding,tuition,scholarship,loan,bursary,payment,payments,cost,costs,money",
                        "Use this source for fees, funding, tuition payments, bursaries, and money-related student questions."
                ));
                knowledgeSourceRepository.save(new KnowledgeSource(
                        "timetables",
                        "Student Timetable",
                        "https://my.napier.ac.uk/my/",
                        "timetable,timetables,class,classes,schedule,schedules,lecture,lectures,room,rooms",
                        "Use this source for timetable access and schedule-related student questions. Replace this with the exact timetable URL when available."
                ));
            }
        };
    }
}
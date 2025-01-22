package com.alura.forohub.domain.topic;


import com.alura.forohub.domain.answer.Answer;
import com.alura.forohub.domain.courses.Course;
import com.alura.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Topic")
@Table(name = "topics")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String message;

    @Column(name = "creation_date") // Aquí ajusta el nombre de la columna según la base de data
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public Topic(TopicRegisterDTO data , User dataUser, Course course){
        this.id=null;
        this.title=data.title();
        this.message=data.message();
        this.creationDate=LocalDate.now();
        this.status=Status.ACTIVO;
        this.author= dataUser;
        this.course = course;
    }

    public void UpdateData(UpdateTopicDTO data) {
        if (data.titulo() != null) {
            this.title = data.titulo();
        }
        if (data.mensaje() != null) {
            this.message = data.mensaje();
        }
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }


}
//}

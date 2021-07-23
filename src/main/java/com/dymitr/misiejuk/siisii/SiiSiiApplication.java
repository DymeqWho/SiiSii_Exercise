package com.dymitr.misiejuk.siisii;

import com.dymitr.misiejuk.siisii.repository.LectureRepository;
import com.dymitr.misiejuk.siisii.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiiSiiApplication {


    public static void main(String[] args) {
        SpringApplication.run(SiiSiiApplication.class, args);
    }

}

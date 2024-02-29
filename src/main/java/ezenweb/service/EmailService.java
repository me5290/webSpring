package ezenweb.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    /*
        SMTP : 간이 우편 전송 프로토콜 (메일 전송)
            - 자바에서 메일 보내기
            1. SMTP 라이브러리 필요 https://start.spring.io/
            2. Dependencies : Java Mail Sender
                implementation 'org.springframework.boot:spring-boot-starter-mail'
            3. 이메일 전송
                1. 이메일 내용을 구성 => 구성 포맷 : MIME(SMTP 사용할때 사용되는 포맷)
            4. 보내는 사람 이메일 인증
                application.properties
    */
    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String toEmail , String subject , String content){
        try {
            // 메일 내용물들을 포맷하기 위한 MIME 형식 객체
            MimeMessage message = javaMailSender.createMimeMessage();
            // 1. 메일 구성
            // MimeMessageHelper(mime객체 , 첨부파일여부 , 인코딩타입utf-8);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true,"utf-8");
            // 2. 메일 보내는 사람
            mimeMessageHelper.setFrom("me5290@naver.com");
            // 3. 메일 받는 사람
            mimeMessageHelper.setTo(toEmail);
            // 4. 메일 제목
            mimeMessageHelper.setSubject(subject);
            // 5. 메일
            mimeMessageHelper.setText(content);
            // 6. 메일 전송
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println("이메일 전송 실패 "+e);
        }
    }
}

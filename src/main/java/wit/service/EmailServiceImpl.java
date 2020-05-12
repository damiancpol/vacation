package wit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
@Autowired
public JavaMailSender eMailSender;



@Override
public void sendSimpleMessage( String to, String subject, String text) {
    
    SimpleMailMessage message = new SimpleMailMessage(); 
    message.setTo(to); 
    message.setSubject(subject); 
    message.setText(text);
    eMailSender.send(message);
}





}




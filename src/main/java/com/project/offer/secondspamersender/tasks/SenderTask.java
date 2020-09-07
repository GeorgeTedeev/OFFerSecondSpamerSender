package com.project.offer.secondspamersender.tasks;


import com.project.offer.secondspamersender.entities.SpamTaskWithEmailAndNumberOfSpamerSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SenderTask implements Runnable{

    private SpamTaskWithEmailAndNumberOfSpamerSender spamTaskWithEmailAndNumberOfSpamerSender;

    public SenderTask(SpamTaskWithEmailAndNumberOfSpamerSender spamTaskWithEmailAndNumberOfSpamerSender) {
        this.spamTaskWithEmailAndNumberOfSpamerSender = spamTaskWithEmailAndNumberOfSpamerSender;
    }

    @Override
    public void run() {
        JavaMailSender emailSender = new JavaMailSenderImpl();
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        StringBuilder textBuilder =
                new StringBuilder("<html> <body><div style=\"text-align: center\"><h1>").append(spamTaskWithEmailAndNumberOfSpamerSender.getSpamTask().getSpamTaskText()).append("</h1></div></body> </html>");
        try {
            helper.setTo(spamTaskWithEmailAndNumberOfSpamerSender.getEmail());
            helper.setSubject(spamTaskWithEmailAndNumberOfSpamerSender.getSpamTask().getSpamTaskTheme());
            helper.setText(textBuilder.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        emailSender.send(message);
    }
}

package com.whl.test.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whl.MySpringBootDemoApplication;

@SpringBootTest(classes = MySpringBootDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MailTest {
	@Autowired
	private JavaMailSender mailSender;
/**
 * final 
 * 
 * MimeMessage mimeMessage = this.mailSender.createMimeMessage();  
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
            message.setFrom("****@126.com");  
            message.setTo("****@example.com");  
            message.setSubject("测试邮件主题");  
            message.setText("测试邮件内容");  
            this.mailSender.send(mimeMessage);  
              
            ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(),  
                    ResultStatusCode.OK.getErrmsg(), null);  
            return resultMsg;  
 * @throws Exception 
 */
	@Test
	public void sendMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		

		message.setFrom("13133668976@163.com");// 发送者.
		message.setTo("1769778747@qq.com");// 接收者.
		message.setSubject("测试邮件（邮件主题）");// 邮件主题.
		message.setText("这是邮件内容........");// 邮件内容.

		mailSender.send(mimeMessage);// 发送邮件
	}

}

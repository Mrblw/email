package com.example.email;

import com.example.email.bean.MailBean;
import com.example.email.util.DateUtils;
import com.example.email.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailApplicationTests {

    @Autowired
    MailUtil mailUtil;

    @Autowired
    TemplateEngine templateEngine;

    //接收人
    private static final String RECIPINET = "gg370523573@163.com";

    @Test
    public void sendSampleEmail() {

        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMail之这是一封文本的邮件");
        mailBean.setContent("SpringBootMail发送一个简单格式的邮件，时间为：" + DateUtils.format(new Date()));

        mailUtil.sendSimpleMail(mailBean);

    }

    @Test
    public void sendHTMLMail() {
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMailHTML之这是一封HTML格式的邮件");
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>SpirngBoot测试邮件HTML</h2>")
                .append("<p style='text-align:left'>这是一封HTML邮件...</p>")
                .append("<p> 时间为："+ DateUtils.format(new Date()) +"</p>");
        mailBean.setContent(sb.toString());

        mailUtil.sendHTMLMail(mailBean);
    }


    @Test
    public void sendAttachmentMail(){
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMail之这是一封有附件格式的邮件");
        mailBean.setContent("SpringBootMail发送一封有附件格式的邮件，时间为："+ DateUtils.format(new Date()));

        mailUtil.sendAttachmentMail(mailBean);

    }

    @Test
    public void sendInlineMail() {
        MailBean mailBean = new MailBean();
        //id,目前写死了，可根据需要封装
        String rscId = "picture";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMail之这是一封有静态资源格式的邮件");
        mailBean.setContent(content);

        mailUtil.sendInlineMail(mailBean);
    }


    /**
     * 对于发送HTML页面的封装---另外一种优雅的写法
     */
    @Test
    public void sendTemplate2Mail() {
        //注意：Context 类是在org.thymeleaf.context.Context包下的。
        Context context = new Context();
        //html中填充动态属性值
        context.setVariable("username", "码农用户");
        context.setVariable("url", "https://www.aliyun.com/?utm_content=se_1000301881");
        //注意：process第一个参数名称要和templates下的模板名称一致。要不然会报错
        //org.thymeleaf.exceptions.TemplateInputException: Error resolving template [email]
        String emailContent = templateEngine.process("email", context);

        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("主题：这是模板邮件");
        mailBean.setContent(emailContent);

        mailUtil.sendHTMLMail(mailBean);
    }

}

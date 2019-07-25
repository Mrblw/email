package com.example.email.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author <bu.liwen@chinaott.net>
 * @Description
 * @Date 2019-07-25 14:56
 */
@Data
public class MailBean implements Serializable {

    private static final long serialVersionUID = 7630799224509302715L;
    /**
     * 邮件接收人
     */
    private String recipient;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

}

package com.heli.oa.sunshine.util;
import com.heli.oa.common.dao.UserDao;
import com.heli.oa.common.entity.User;
import com.heli.oa.sunshine.entity.Sunshine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 白驹
 */
@Slf4j
@Service
public class MailUtil {
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private int port;
    @Value("${mail.userName}")
    private String userName;
    @Value("${mail.password}")
    private String password;
    private final UserDao userDao;

    @Autowired
    public MailUtil(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 阳光值扣除通知
     * @param s
     * @throws Exception
     */
    public void sendMinusSunshineMail(Sunshine s) throws Exception {

        HtmlEmail mail = new HtmlEmail();
        mail.setSSLOnConnect(true);
        // 设置邮箱服务器信息
        mail.setSmtpPort(port);
        mail.setHostName(host);
        // 设置密码验证器
        mail.setAuthentication(userName, password);
        // 设置邮件发送者
        mail.setFrom(userName);
        // 设置邮件接收者
        String userNcikName = s.getUserNickname();
        User user = userDao.getUserByNickname(userNcikName);
        mail.addTo(user.getUserMail());
        mail.addCc(userName);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("阳光值扣除通知");
        //设置邮件内容
        String minusSunshineHtml = "<html><body><div>"
                + s.getUserNickname() + " 由于您  "
                + s.getSunshineComment() + "  被扣除一颗阳光值，请在24小时内扫码支付扣款:"
                + s.getSunshineMoney() + "元，否则将产生每24小时5%的滞纳金（支付时请备注扣款ID:"
                + s.getSunshineRecordId() + "及花名）。"
                + "</div><img src='http://oa.helidianshang.com/image/money.jpg'/></body></html>" ;
        mail.setHtmlMsg(minusSunshineHtml);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
        log.info("扣除阳光值通知邮件发送成功：" +user.getUserNickname() +"，\n邮箱:" + user.getUserMail() + ",\n阳光值记录ID:"+  s.getUserId() + ",\n扣除原因：" + s.getSunshineComment());
    }

    /**
     * 扣款未缴纳，超24小时通知
     * @param s
     * @throws EmailException
     */
    public void sendReminderMail(Sunshine s) throws Exception {

        HtmlEmail mail = new HtmlEmail();
        mail.setSSLOnConnect(true);
        // 设置邮箱服务器信息
        mail.setSmtpPort(port);
        mail.setHostName(host);
        // 设置密码验证器
        mail.setAuthentication(userName, password);
        // 设置邮件发送者
        mail.setFrom(userName);
        // 设置邮件接收者
        String userNcikName = s.getUserNickname();
        User user = userDao.getUserByNickname(userNcikName);
        mail.addTo(user.getUserMail());
        mail.addCc(userName);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("阳光值系统--催款通知");
        //设置邮件路径
        mail.setHtmlMsg(
                "<html><body><div>"+ s.getUserNickname()
                        + "  由于您  "+s.getSunshineComment()+"  被扣除一颗阳光值，扣款记录ID： "+ s.getSunshineRecordId()
                        +" ；目前已超过24小时您尚未缴纳扣款，请尽快扫码支付:"+ s.getSunshineMoney()+"元，否则将产生滞纳金（支付时请备注扣款ID及花名）。 "
                        + "</div><img src='http://oa.helidianshang.com/image/money.jpg'/></body></html>");
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
        log.info("扣款未缴纳，超24小时通知邮件发送成功：" +user.getUserNickname() +"--" + user.getUserMail() + ",阳光值记录ID:"+  s.getSunshineRecordId() + ",扣除原因：" + s.getSunshineComment());


    }

    /**
     * 滞纳金产生通知
     * @param s
     * @throws EmailException
     */
    public void sendOverdueFineMail(Sunshine s) throws Exception {

        HtmlEmail mail = new HtmlEmail();
        mail.setSSLOnConnect(true);
        // 设置邮箱服务器信息
        mail.setSmtpPort(port);
        mail.setHostName(host);
        // 设置密码验证器
        mail.setAuthentication(userName, password);
        // 设置邮件发送者
        mail.setFrom(userName);
        // 设置邮件接收者
        String userNcikName = s.getUserNickname();
        User user = userDao.getUserByNickname(userNcikName);
        mail.addTo(user.getUserMail());
        mail.addCc(userName);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("阳光值系统--滞纳金产生通知");
        //设置邮件路径
        mail.setHtmlMsg(
                "<html><body><div>"+ s.getUserNickname()
                        + "  由于您  "+s.getSunshineComment()+"  被扣除一颗阳光值，扣款记录ID： "+ s.getSunshineRecordId()
                        +" ；目前已超过24小时您尚未缴纳扣款，已产生滞纳金，请尽快扫码支付（支付时请备注扣款ID及花名）。 "
                        + "</div><img src='http://oa.helidianshang.com/image/money.jpg'/></body></html>");
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();

        log.info("滞纳金产生通知邮件发送成功：" +user.getUserNickname() +"--" + user.getUserMail() + ",阳光值记录ID:"+  s.getSunshineRecordId() + ",扣除原因：" + s.getSunshineComment());


    }




    /**
     * 发送内嵌图片和附件邮件
     *
     * @throws Exception
     *//*
    public void sendImageAndAttachmentMail() throws Exception
    {
        HtmlEmail mail = new HtmlEmail();
        // 设置邮箱服务器信息
        mail.setSmtpPort(port);
        mail.setHostName(host);
        // 设置密码验证器
        mail.setAuthentication(userName, password);
        // 设置邮件发送者
        mail.setFrom(userName);
        // 设置邮件接收者
        mail.addTo(to);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件主题
        mail.setSubject("Test Email");
        mail.embed(new File("1_jianggujin.jpg"), "image");
        // 设置邮件内容
        String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML email.</div></body></html>";
        mail.setHtmlMsg(htmlText);
        // 创建附件
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("1_jianggujin.jpg");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName("1_jianggujin.jpg");
        mail.attach(attachment);
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }*/
}
package com.bskplu.service_sms.utils;

import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: MailUtil
 * @Description: 邮件工具类
 * @Author BsKPLu
 * @Date 2022/3/7
 * @Version 1.1
 */
public class MailUtil {
    public static String sendEmail(String to, String subject, String userName, boolean isHtml, String... r) {
        try {
            cn.hutool.extra.mail.MailUtil.send(to, subject, contentHtml(userName, r).toString(), isHtml);
            return "发送邮件成功!";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送邮件失败!";
        }
    }

    private static StringBuffer contentHtml(String userName, String... desc) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date now = new Date();
        boolean am = DateUtil.isAM(now);
        return new StringBuffer("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>邮件提醒</title> 　　\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"margin: 0; padding: 0;\">\n" +
                "  <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\"> 　\n" +
                "    <tr style=\"position: relative;\">\n" +
                "      <td>\n" +
                "        <div style=\"margin: 20px;text-align: center;margin-top: 50px\"> " +
                "         <img src='http://129.211.209.210/index.html' style=\"position: absolute;left: 0;z-index: -1;opacity: 0.4;\">" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <div style=\"border: #36649d 1px dashed;margin: 30px;padding: 20px\"> <label\n" +
                "            style=\"font-size: 22px;color: #36649d;font-weight: bold\">恭喜您，登陆成功！</label>\n" +
                "          <p style=\"font-size: 16px\">亲爱的&nbsp;<label style=\"font-weight: bold\"> " + userName + " 先生/女士</label>&nbsp; 您好！欢迎登陆" + desc[0] + "。 </p>\n" +
                "          <p style=\"font-size: 16px\">" + desc[1] + "</p>\n" +
                "          <p style=\"font-size: 16px\">您已于" + sdf.format(now) + "登陆成功，如不是本人进行操作请立即更改密码!，希望更好的为您服务！</p>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr> 　 <tr>\n" +
                "      <td>\n" +
                "        <div style=\"margin: 40px\">\n" +
                "          <p style=\"font-size: 16px\">BsKPLu管理团队</p>\n" +
                "          <p style=\"text-align:right;color:red;font-size: 14px \">（这是一封自动发送的邮件，请勿回复。）</p>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <div style=\"font-size:14px;margin-left: 30px;margin-right: 30px;padding: 20px\">\n" +
                "         <img src='http://129.211.209.210/index.html' style=\"position: absolute;left: 0;z-index: -1;opacity: 0.4;\">" +
                "          <label style=\"font-size: 12px;display:block;\">官网: http://bskplu.buzz</label>\n" +
                "          <label style=\"font-size: 12px;display:block;\">了解更多</label>\n" +
                "          <p style=\"font-size: 12px\">\n" +
                "            如有任何问题，可以与我们联系，我们将尽快为你解答。<br />\n" +
                "            QQ: 3263405221\n" +
                "             微信：这个就没有了\n" +
                "          </p>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <div align=\"right\" style=\"margin: 40px;border-top: solid 1px gray\" id=\"bottomTime\">\n" +
                "          <p style=\"margin-right: 20px\">" + (am ? "上午" : "下午") + "</p> <label style=\"margin-right: 20px\">" + sdf.format(now) + "</label>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
    }

}

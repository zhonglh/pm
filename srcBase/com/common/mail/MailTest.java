package com.common.mail;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

public class MailTest {

    public static void main(String[] args) {
        MailAccount account = new MailAccount();
        account.setHost("smtp.qiye.163.com");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("hr2@souvi.com");
        account.setUser("hr2@souvi.com");
        account.setPass("souvi2018@");

        MailUtil.send(account, CollUtil.newArrayList("493843721@qq.com"),
                "测试", "邮件来自Hutool测试", true);
    }
}

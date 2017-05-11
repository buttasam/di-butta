package cvut.fit.di.commonEntity.managed.withInterface;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */

@Singleton
public class SmsMailer implements Mailer {

    @Override
    public void send() {
        System.out.println("sms mailer");
    }

}

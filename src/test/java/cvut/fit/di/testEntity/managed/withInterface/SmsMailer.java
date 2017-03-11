package cvut.fit.di.testEntity.managed.withInterface;

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

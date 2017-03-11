package cvut.fit.di.testEntity.managed.withInterface;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */

@Singleton
public class MailMailer implements Mailer {

    @Override
    public void send() {
        System.out.println("mail mailer");
    }

}

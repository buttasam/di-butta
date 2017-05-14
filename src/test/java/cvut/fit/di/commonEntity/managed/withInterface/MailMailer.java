package cvut.fit.di.commonEntity.managed.withInterface;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */

@Singleton
public class MailMailer implements Mailer {

    @Override
    public String send() {
        return MailMailer.class.toString();
    }

}

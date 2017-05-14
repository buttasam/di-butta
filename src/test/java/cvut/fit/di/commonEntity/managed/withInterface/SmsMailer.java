package cvut.fit.di.commonEntity.managed.withInterface;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */

@Singleton
public class SmsMailer implements Mailer {

    @Override
    public String send() {
        return SmsMailer.class.toString();
    }

}

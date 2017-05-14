package cvut.fit.di.proxy;


class ServiceAImpl implements ServiceA {

    @Override
    public String test() {
        return ServiceAImpl.class.toString();
    }

}

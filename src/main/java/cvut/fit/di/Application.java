package cvut.fit.di;

import cvut.fit.di.builder.Finder;


public class Application {

    public static void main(String[] args) {

        Finder finder = new Finder();

        finder.findManagedBeans("cvut.fit.di.testEntity");
    }

}

package training.stub;

import org.apache.log4j.Logger;

import training.api.IPlugin;

/**
 * Created by Andrei_Pimenau on 29.9.14.
 */
public class StubPlugin implements IPlugin {

    private Logger logger = Logger.getRootLogger();

    @Override
    public String getName() {
        return "stub";
    }

    @Override
    public void exec() {
        logger.info("I am stub");
    }
}

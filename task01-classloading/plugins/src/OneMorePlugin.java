import org.apache.log4j.Logger;

import training.api.IPlugin;

/**
 * Created by Andrei_Pimenau on 29.9.14.
 */
public class OneMorePlugin implements IPlugin {

    private Logger logger = Logger.getRootLogger();

    @Override
    public String getName() {
        return "One more plugin";
    }

    @Override
    public void exec() {
        logger.info("Another plugin is called.");
    }

}

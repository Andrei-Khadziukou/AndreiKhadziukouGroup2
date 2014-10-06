package training.api;

/**
 * Created by Andrei_Pimenau on 29.9.14.
 */
public interface IPlugin {
    /**
     * Get name of plugin.
     *
     * @return name.
     */
    String getName();

    /**
     * Do something useful.
     */
    void exec();

}

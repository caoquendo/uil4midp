
package ec.edu.epn.fis.uil4midp.actions;

/**
 * Wraps the execution of an ActionListener
 * @author Carlos Andrés Oquendo
 */
public final class ListenerExecutor {

    private ActionListener actionListener;
    private boolean executed;

    /**
     * Sets the ActionListener to be managed by the ListenerExecutor.
     * @param actionListener ActionListener instance.
     */
    public final void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    /**
     * Executes the ActionListener
     */
    public final void execute() {
        executed = true;
        actionListener.execute();
    }

    /**
     * Determines if the ActionListener was executed.
     * @return True if the ActionListener has been invoked, otherwise, False.
     */
    public final boolean isExecuted() {
        return executed;
    }

    /**
     * Determines if there is an ActionListener loaded.
     * @return True if there is an ActionListener loaded, otherwise, False.
     */
    public final boolean isActionListenerSet() {
        return actionListener != null;
    }
}

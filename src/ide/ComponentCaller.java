package ide;

import java.util.Stack;

/**
 *  Component Caller have running Component, Component Stack.
 *  Component Caller can handle Stack and running Component.
 */

public class ComponentCaller {

    /**
     * Component Caller make Component Stack.
     */
    public ComponentCaller() {
        componentStack = new Stack<>();
    }

    /**
     * call Component and add Component Stack to execute Component.
     * when Component is called, it runs itself by this function.
     * @param component can be called by other Component or MainFunction
     *                  all params must be called by "new Component();"
     *                  don't make variable!!
     */
    public void callComponent(IDEComponent component) {
        if(!componentStack.add(component)){
            System.out.println("Component already exists");
        }
        else {
            //these for checking Stack.
            for(int i = 0; i<componentStack.size(); i++){
                System.out.print(componentStack.elementAt(i).toString() + " ");
            }
            System.out.println();
            //
        }
        runningComponent = componentStack.peek();
    }

    /**
     * run Component, show Component UI first.
     */
    void runComponent() {
        runningComponent.executeComponent();
        runningComponent.showComponent();
    }

    /**
     * if user quit Component, call this method.
     * delete peek of Component and change running Component to peek of Stack
     * running component is changed peek of stack.
     */
    public void returnComponent() {
        if(!componentStack.isEmpty()) {
            componentStack.removeLast();
            if(componentStack.isEmpty()){ //quit Index
                runningComponent = null;
            }else {
                runningComponent = componentStack.peek();
            }
        }
        else {
            System.out.println("Error_001 : No component found");
        }
        //these for checking Stack.
        for(int i = 0; i<componentStack.size(); i++){
            System.out.print(componentStack.elementAt(i).toString() + " ");
        }
        System.out.println();
        //
    }

    public void returnComponent(Mode mode) {
        if(!componentStack.isEmpty()) {
            componentStack.removeLast();
            if(componentStack.isEmpty()){ //quit Index
                runningComponent = null;
            }else {
                runningComponent = componentStack.peek();
                runningComponent.setMode(mode);
            }
        }
        else {
            System.out.println("Error_001 : No component found");
        }
        //these for checking Stack.
        for(int i = 0; i<componentStack.size(); i++){
            System.out.print(componentStack.elementAt(i).toString() + " ");
        }
        System.out.println();
        //
    }

    public IDEComponent getRunningComponent() {
        return runningComponent;
    }

    private static IDEComponent runningComponent = null;
    private static Stack<IDEComponent> componentStack;
}
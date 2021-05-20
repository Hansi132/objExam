package no.MCH.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class ComponentModel {
    private final List<Component> componentList = new ArrayList<>();

    public List<Component> getComponentList() {
        return this.componentList;
    }

    public void addComponent(Component component) {
        this.componentList.add(component);
    }
    
    public Component getComponent(Component component) {
    	return this.componentList.get(this.componentList.indexOf(component));
    }
    
    public void removeComponent(Component component) {
    	this.componentList.remove(component);
    }

}

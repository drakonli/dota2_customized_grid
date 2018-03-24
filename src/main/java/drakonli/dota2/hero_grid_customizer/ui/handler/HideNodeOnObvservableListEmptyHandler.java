package drakonli.dota2.hero_grid_customizer.ui.handler;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class HideNodeOnObvservableListEmptyHandler
{
    private final ObservableList list;
    private final Node node;

    public HideNodeOnObvservableListEmptyHandler(ObservableList list, Node node)
    {
        this.list = list;
        this.node = node;
    }

    public void handle()
    {
        this.node.visibleProperty().bind(
                Bindings
                        .size(this.list)
                        .isNotEqualTo(0)
        );

        this.node.managedProperty().bind(this.node.visibleProperty());
    }
}

package base.visibility.scroll;


import base.visibility.items.ListItem;

/**
 * This interface is used by {@link com.waynell.videolist.visibility.calculator.SingleListViewItemActiveCalculator}.
 * Using this class to get {@link com.waynell.videolist.visibility.items.ListItem}
 *
 * @author Charles
 */
public interface ItemsProvider {

    ListItem getListItem(int position);

    int listItemSize();

}

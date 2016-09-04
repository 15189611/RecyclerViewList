package base.visibility.calculator;

import android.widget.AbsListView;

import base.visibility.scroll.ItemsPositionGetter;
import base.visibility.scroll.ScrollDirectionDetector;


public abstract class BaseItemsVisibilityCalculator implements ListItemsVisibilityCalculator{

    /** Initial scroll direction should be UP in order to set as active most top item if no active item yet*/
    protected ScrollDirectionDetector.ScrollDirection mScrollDirection = ScrollDirectionDetector.ScrollDirection.UP;

    protected final ItemsPositionGetter mPositionGetter;

    public BaseItemsVisibilityCalculator(ItemsPositionGetter positionGetter) {
        mPositionGetter = positionGetter;
    }

    private final ScrollDirectionDetector mScrollDirectionDetector = new ScrollDirectionDetector(
            new ScrollDirectionDetector.OnDetectScrollListener() {
        @Override
        public void onScrollDirectionChanged(ScrollDirectionDetector.ScrollDirection scrollDirection) {
            mScrollDirection = scrollDirection;
        }
    });

    @Override
    public void onScrolled(int scrollState) {
        int firstVisiblePosition = mPositionGetter.getFirstVisiblePosition();

        mScrollDirectionDetector.onDetectedListScroll(mPositionGetter, firstVisiblePosition);

        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:  //滑动状态
                onStateTouchScroll(mPositionGetter);
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:         //快速滑动
                onStateTouchScroll(mPositionGetter);
                break;

            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:           //滑动结束
                onScrollStateIdle();
                break;
        }
    }

    public abstract void onStateLost();

    protected abstract void onStateTouchScroll(ItemsPositionGetter itemsPositionGetter);

}

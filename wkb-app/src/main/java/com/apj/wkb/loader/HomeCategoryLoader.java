package com.apj.wkb.loader;

import android.content.Context;
import android.content.Loader;
import android.support.v4.content.AsyncTaskLoader;

import com.apj.wkb.entity.CourserItem;
import com.apj.wkb.entity.HomeCategory;
import com.apj.wkb.provider.contentprovider.ProviderUtils;
import com.apj.wkb.utils.DataUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by student on 2014/11/22.
 */
public class HomeCategoryLoader extends AsyncTaskLoader<List<HomeCategory>> {

    private Context context;
    private List<HomeCategory> mData;

    public HomeCategoryLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public List<HomeCategory> loadInBackground() {
        ProviderUtils p = new ProviderUtils(context);
        if(p.getCount() > 0) {

          return loadDataFromLocal();
        }
        else
        {
            return DataUtils.loadDate(context);
        }
    }
    private  List<HomeCategory> loadDataFromLocal()
    {
        ProviderUtils utils = new ProviderUtils(context);
        List<HomeCategory> listCategory = new ArrayList<HomeCategory>();

        HomeCategory category = new HomeCategory();
        category.setType("0");

        List<CourserItem> items = utils.showCourseItems("0");
        category.setVos(items);
        category.setName(items.size()>0 ? items.get(0).getTypeName():"");
        listCategory.add(category);

        HomeCategory category2 = new HomeCategory();
        category2.setType("2");
        List<CourserItem> items2 = utils.showCourseItems("2");
        category.setVos(items2);
        category.setName(items2.size()>0 ? items2.get(0).getTypeName():"");
        listCategory.add(category2);

        HomeCategory category3 = new HomeCategory();
        category2.setType("3");
        List<CourserItem> items3 = utils.showCourseItems("3");
        category.setVos(items3);
        category.setName(items3.size()>0 ? items3.get(0).getTypeName():"");
        listCategory.add(category3);

        HomeCategory category4 = new HomeCategory();
        category2.setType("4");
        List<CourserItem> items4 = utils.showCourseItems("4");
        category.setVos(items4);
        category.setName(items4.size()>0 ? items4.get(0).getTypeName():"");
        listCategory.add(category4);

        return listCategory;

    }

    /**
     * Called when there is new data to deliver to the client.  The
     * super class will take care of delivering it; the implementation
     * here just adds a little more logic.
     */
    @Override
    public void deliverResult(List<HomeCategory> data) {
        if (isReset()) {
            // An async query came in while the loader is stopped.  We
            // don't need the result.
            if (data != null) {
                onReleaseResources(data);
            }
        }
        List<HomeCategory> oldData = mData;
        mData = data;
        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(data);
        }


        // At this point we can release the resources associated with
        // 'oldApps' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if (oldData != null) {
            onReleaseResources(oldData);
        }
    }


    /**
     * Handles a request to start the Loader.
     */
    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(mData);
        }
        if (takeContentChanged() || mData == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }


    /**
     * Handles a request to cancel a load.
     */
    @Override
    public void onCanceled(List<HomeCategory> apps) {
        super.onCanceled(apps);

        // At this point we can release the resources associated with 'apps'
        // if needed.
        onReleaseResources(apps);
    }


    /**
     * Handles a request to completely reset the Loader.
     */
    @Override
    protected void onReset() {
        super.onReset();


        // Ensure the loader is stopped
        onStopLoading();


        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (mData != null) {
            onReleaseResources(mData);
            mData = null;
        }
    }

    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    protected void onReleaseResources(List<HomeCategory> apps) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }

}

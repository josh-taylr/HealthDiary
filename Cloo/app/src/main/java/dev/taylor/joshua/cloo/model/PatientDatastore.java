package dev.taylor.joshua.cloo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Josh on 27/06/15.
 */
public class PatientDatastore {

    public static List<FeedItem> ITEMS = new ArrayList<FeedItem>();

    public static Map<String, FeedItem> ITEM_MAP = new HashMap<String, FeedItem>();

    static
    {
        // Add 3 sample items.
        addItem(new FeedItem("1", "Item 1"));
        addItem(new FeedItem("2", "Item 2"));
        addItem(new FeedItem("3", "Item 3"));
    }

    private static void addItem(FeedItem item)
    {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class FeedItem {
        public String id;
        public String content;

        public FeedItem(String id, String content)
        {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString()
        {
            return content;
        }
    }
}

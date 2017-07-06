package com.code.demo.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;

public class MongoCollectionDemoTest {
    
    private MongoCollectionDemo collectionDemo;
    private static final String DB_NAME = "mongo_test";

    @Before
    public void init() {
        collectionDemo = new MongoCollectionDemo();
    }

    @Test
    public void testAddCollection() {
        String collName = "collection1";
        collectionDemo.createCollection(DB_NAME, collName);
    }

    @Test
    public void testAddCollectionByOptions() {
        String collName = "collection2";
        CreateCollectionOptions options = new CreateCollectionOptions();
        options.autoIndex(false);
        collectionDemo.createCollectionByOptions(DB_NAME, collName, options);
    }

    @Test
    public void testDropCollection() {
        String collName = "collection2";
        collectionDemo.dropCollection(DB_NAME, collName);
    }

    @After
    public void print() {
        MongoIterable<String> iterable = collectionDemo.getCollectionNames(DB_NAME);
        MongoCursor<String> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            System.out.println("======" + cursor.next() + "======");
        }
    }

}

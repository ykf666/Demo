package com.code.demo.Mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public final class MongoUtil {
    private static final String MONGO_ADDR = "10.60.1.25"; // mongo服务地址
    private static final int MONGO_PORT = 27017; // mongo端口号
    private static MongoClient client;
    
    static {
        client = new MongoClient(MONGO_ADDR, MONGO_PORT);
    }

    public static MongoDatabase getDB(String dbName) {
        return client.getDatabase(dbName);
    }

    public static MongoCollection<Document> getCollection(String dbName, String collName) {
        MongoDatabase database = getDB(dbName);
        return database.getCollection(collName);
    }

}

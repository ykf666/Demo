package com.code.demo.Mongo;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;

public class MongoCollectionDemo {

    /**
     * db中创建一个集合
     * 
     * @param dbName
     * @param collName
     */
    public void createCollection(String dbName, String collName) {
        MongoDatabase database = MongoUtil.getDB(dbName);
        database.createCollection(collName);
    }

    /**
     * db中创建一个集合
     * 
     * @param dbName
     *            数据库名称
     * @param collName
     *            集合名称
     * @param options
     *            可选项 具体参照mongo-java-driver doc文档
     */
    public void createCollectionByOptions(String dbName, String collName, CreateCollectionOptions options) {
        MongoDatabase database = MongoUtil.getDB(dbName);
        database.createCollection(collName, options);
    }

    /**
     * 获取该数据库下所有集合的名称
     * 
     * @param dbName
     * @return
     */
    public MongoIterable<String> getCollectionNames(String dbName) {
        MongoDatabase database = MongoUtil.getDB(dbName);
        MongoIterable<String> collNames = database.listCollectionNames();
        return collNames;
    }

    /**
     * 删除某一db下的一个集合
     * 
     * @param dbName
     *            数据库名称
     * @param collName
     *            要删除的集合的名称
     */
    public void dropCollection(String dbName, String collName) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        collection.drop();
    }

}

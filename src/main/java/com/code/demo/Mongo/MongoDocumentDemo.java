package com.code.demo.Mongo;

import static com.mongodb.client.model.Projections.include;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class MongoDocumentDemo {

    /**
     * 向集合中插入一个文档
     * 
     * @param dbName
     * @param collName
     * @param document
     */
    public void insertOne(String dbName, String collName, Document document) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        collection.insertOne(document);
    }

    /**
     * 向集合中插入多个文档
     * 
     * @param dbName
     * @param collName
     * @param documents
     */
    public void insertMany(String dbName, String collName, List<Document> documents) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        collection.insertMany(documents);
    }

    /**
     * 向集合中插入多个文档
     * 
     * @param dbName
     * @param collName
     * @param documents
     * @param options
     *            可选参数， 如设置bypassDocumentValidation为false来避开验证等等
     */
    public void insertMany(String dbName, String collName, List<Document> documents, InsertManyOptions options) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        collection.insertMany(documents, options);
    }

    /**
     * 查询该集合下的所有文档
     * 
     * @param dbName
     * @param collName
     * @return
     */
    public FindIterable<Document> getDocuments(String dbName, String collName) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        FindIterable<Document> iterable = collection.find();
        return iterable;
    }

    /**
     * 通过条件来查询该集合下的文档
     * 
     * @param dbName
     * @param collName
     * @param filter
     *            查询的条件 参照com.mongodb.client.model.Filters.* 中的static方法
     * @return
     */
    public FindIterable<Document> getDocumentsByCondition(String dbName, String collName, Bson filter) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        return collection.find(filter);
    }

    /**
     * 通过条件查询该集合下的文档，并且对结果进行排序
     * 
     * @param dbName
     * @param collName
     * @param filter
     *            查询的条件 参照com.mongodb.client.model.Filters.* 中的static方法
     * @param sort
     *            排序的标准
     * @return
     */
    public FindIterable<Document> getDocumentsBySort(String dbName, String collName, Bson filter, Bson sort) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        return collection.find(filter).sort(sort);
    }

    /**
     * 投影，只查询该集合下符合条件的文档的 那些inclusionFields
     * 
     * @param dbName
     * @param collName
     * @param filter
     *            查询的条件 参照com.mongodb.client.model.Filters.* 中的static方法
     * @param inclusionFields
     *            想要获取的字段 以，分隔开 eg:name,age
     * @return
     */
    public FindIterable<Document> getDocumentsByProjections(String dbName, String collName, Bson filter,
            String inclusionFields) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        return collection.find(filter).projection(include(inclusionFields));
    }

    /**
     * 获取该集合下，满足查询条件的num个文档
     * 
     * @param dbName
     * @param collName
     * @param filter
     *            查询的条件 参照com.mongodb.client.model.Filters.* 中的static方法
     * @param num
     * @return
     */
    public FindIterable<Document> getLimitDocuments(String dbName, String collName, Bson filter, int num) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        return collection.find(filter).limit(num);
    }

    /**
     * 聚合
     * 
     * @param dbName
     * @param collName
     * @param groupBson
     * @return
     */
    public AggregateIterable<Document> getGroupData(String dbName, String collName, List<? extends Bson> groupBson) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        return collection.aggregate(groupBson);
    }

    /**
     * 更新一个文档
     * 
     * @param dbName
     * @param collName
     * @param filter
     *            查询的条件 参照com.mongodb.client.model.Filters.* 中的static方法
     * @param update
     * @return
     */
    public UpdateResult updateDocument(String dbName, String collName, Bson filter, Bson update) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        return collection.updateOne(filter, update);
    }

    /**
     * 更新多个文档
     * 
     * @param dbName
     * @param collName
     * @param filter
     *            查询的条件 参照com.mongodb.client.model.Filters.* 中的static方法
     * @param update
     * @return
     */
    public UpdateResult updateDocuments(String dbName, String collName, Bson filter, Bson update) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        return collection.updateMany(filter, update);
    }

    /**
     * 删除一个文档
     * 
     * @param dbName
     * @param collName
     * @param filter
     *            查询的条件 参照com.mongodb.client.model.Filters.* 中的static方法
     * @return
     */
    public DeleteResult deleteDocument(String dbName, String collName, Bson filter) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        return collection.deleteOne(filter);
    }

    /**
     * 删除多个文档
     * 
     * @param dbName
     * @param collName
     * @param filter
     *            查询的条件 参照com.mongodb.client.model.Filters.* 中的static方法
     * @return
     */
    public DeleteResult deleteDocuments(String dbName, String collName, Bson filter) {
        MongoCollection<Document> collection = MongoUtil.getCollection(dbName, collName);
        return collection.deleteMany(filter);
    }
}

package com.code.demo.mongo;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.result.UpdateResult;

public class MongoDocumentDemoTest {
    private MongoDocumentDemo documentDemo;
    private static final String COLL_NAME = "collection1";
    private static final String DB_NAME = "mongo_test";

    @Before
    public void init() {
        documentDemo = new MongoDocumentDemo();
    }

    @Test
    public void testInsertDocument() {
        Document document = new Document();
        document.append("name", "yankefei").append("age", 29).append("addr", "北京市").append("tel", "15222116185");
        documentDemo.insertOne(DB_NAME, COLL_NAME, document);
    }

    @Test
    public void testInsertDocuments() {
        Document document = new Document();
        document.append("name", "zhangsan").append("age", 24).append("addr", "天津市").append("tel", "15222112403");
        Document document1 = new Document();
        document1.append("name", "lisi").append("age", 28).append("addr", "武汉市").append("tel", "15222101234");
        Document document2 = new Document();
        document2.append("name", "wangwu").append("age", 27).append("addr", "深圳市").append("tel", "15222169875");
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        documents.add(document1);
        documents.add(document2);
        documentDemo.insertMany(DB_NAME, COLL_NAME, documents);
    }

    @Test
    public void testInsertDocumentsByOptions() {
        Document document = new Document();
        document.append("name", "zhangsan").append("age", 24).append("addr", "天津市").append("tel", "15222112403");
        Document document1 = new Document();
        document1.append("name", "lisi").append("age", 28).append("addr", "武汉市").append("tel", "15222101234");
        Document document2 = new Document();
        document2.append("name", "wangwu").append("age", 27).append("addr", "深圳市").append("tel", "15222169875");
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        documents.add(document1);
        documents.add(document2);
        InsertManyOptions options = new InsertManyOptions();
        options.ordered(false);
        documentDemo.insertMany(DB_NAME, COLL_NAME, documents, options);
    }

    @Test
    public void testFindDocuments() {
        FindIterable<Document> documents = documentDemo.getDocumentsByCondition(DB_NAME, COLL_NAME,
                eq("name", "huangyao"));
        for (Document document : documents) {
            System.out.println(document.toJson());
        }
    }

    @Test
    public void testFindDocumentsBySort() {
        FindIterable<Document> documents = documentDemo.getDocumentsBySort(DB_NAME, COLL_NAME, new Document(),
                new Document("age", -1));
        for (Document document : documents) {
            System.out.println(document.toJson());
        }
    }

    @Test
    public void testGroup() {
        // db.hyCollection.aggregate([{$group:{_id:"$name", total:{$sum:
        // "$age"}}}])
        List<Document> list = new ArrayList<>();
        Document document = new Document().append("$group",
                new Document().append("_id", "$name").append("total", new Document().append("$sum", "$age")));
        list.add(document);
        AggregateIterable<Document> documents = documentDemo.getGroupData(DB_NAME, COLL_NAME, list);
        for (Document document2 : documents) {
            System.out.println(document2.toJson());
        }
    }

    @Test
    public void testUpdateDocument() {
        UpdateResult result = documentDemo.updateDocument(DB_NAME, COLL_NAME, eq("name", "huangyao"), new Document(
                "$set", new Document("age", 18).append("addr", "天门市")));
        System.out.println("被修改的个数有:" + result.getModifiedCount());
    }

    @After
    public void printDocuments() {
        FindIterable<Document> documents = documentDemo.getDocuments(DB_NAME, COLL_NAME);
        MongoCursor<Document> cursor = documents.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }
    }

}

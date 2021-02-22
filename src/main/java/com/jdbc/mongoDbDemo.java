package com.jdbc;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/12
 * @Description:
 */
public class mongoDbDemo {
    public static void main(String[] args) {
        MongoClientURI con = new MongoClientURI("mongodb://root:123456@url:25555");
        MongoClient mongoClient = new MongoClient(con);
        MongoDatabase test = mongoClient.getDatabase("test");
        MongoCollection<Document> documentMongoCollection = test.getCollection("ryantest2");
        MongoCursor<Document> iterator = documentMongoCollection.find().iterator();
        while (iterator.hasNext()){
            Document next = iterator.next();
            System.out.println(next);
        }
        System.out.println(documentMongoCollection);
    }
}

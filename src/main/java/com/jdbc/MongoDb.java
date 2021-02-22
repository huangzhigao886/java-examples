package com.jdbc;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/3
 * @Description:
 */
public class MongoDb {
    public static void main(String[] args) {

        MongoClientURI connStr = new MongoClientURI("mongodb://root:123456@url:25555");
        MongoClient mongoClient = new MongoClient(connStr);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        MongoCollection<Document> documentMongoCollection = mongoDatabase.getCollection("test");
        MongoCursor<Document> iterator = documentMongoCollection.find().iterator();
        System.out.println("aaa");


    }
}

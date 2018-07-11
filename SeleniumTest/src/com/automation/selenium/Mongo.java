import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Mongo {

private MongoDatabase mongoDB = null;

public Mongo(String host, String db) {
    MongoClient mongoClient = new MongoClient(host);
    mongoDB = mongoClient.getDatabase(db);
}

public FindIterable<Document> getDocuments (String key, int value) {
    MongoCollection collection = mongoDB.getCollection("MyCollection");
    BasicDBObject query = new BasicDBObject();
    query.put(key, value);
    FindIterable<Document> docs = collection.find(query);
    return docs;
}

}

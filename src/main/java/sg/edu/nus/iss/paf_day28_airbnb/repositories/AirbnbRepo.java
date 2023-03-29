package sg.edu.nus.iss.paf_day28_airbnb.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;

@Repository
public class AirbnbRepo {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String C_AIRBNB= "airbnb";


    // Creating Text Index for Text Search
    /*
     * db.airbnb.createIndex({ description: "text" })
     */
    public void addTextIndex(){

        //Retrieving the collection on which you want to create the index
        MongoCollection<Document> collection = mongoTemplate.getDb().getCollection(C_AIRBNB);
        //Creating an index
        collection.createIndex(Indexes.text("description"));

        System.out.println(">>>>> added text index on description");
    }

    /*
     * db.airbnb.find(
        {
            $text:{ $search: "windy" }
        },
        {
            score:{ $meta: "textScore" }
            
        })
        .sort({score:1})
     */
    public List<Document> findByTextSearch(String textSearch){

        TextCriteria textCriteria= TextCriteria.forDefaultLanguage()
                .matchingPhrase(textSearch);

        Query query= TextQuery.queryText(textCriteria).includeScore("score").sortByScore().limit(20);

        // Add projection
        query.fields().include("name","description","score").exclude("_id");

        // perform query
        List<Document> results = mongoTemplate.find(query,Document.class, C_AIRBNB);

        System.out.println(">>>> result is " + results);

        return results;
    }

}

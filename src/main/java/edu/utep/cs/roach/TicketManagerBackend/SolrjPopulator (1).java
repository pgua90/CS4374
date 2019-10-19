package edu.utep.cs.roach.TicketManagerBackend;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

class SolrjPopulator{
    public static void main(String[] args) throws IOException,
            SolrServerException {

//        CommonsHttpSolrServer server = new CommonsHttpSolrServer(
//                "http://localhost:8983/solr/person");
//
//        //Create solr document
//        SolrInputDocument doc = new SolrInputDocument();
//        doc.addField("name", "tester11");
//        doc.addField("age", 38);
//        server.add(doc);
//        server.commit();

        SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/memes").build();
        //SolrDocument document = solrClient.getById("1344dad0-7ec4-48df-9632-d60c78f92aac");
        //System.out.println(document.get("name"));
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("cats", "loops");
        doc.addField("robots", "useless");
        solrClient.add(doc);
        solrClient.commit();



    }
}
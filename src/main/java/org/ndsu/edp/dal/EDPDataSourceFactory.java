package org.ndsu.edp.dal;

import java.net.URI;
import java.util.ArrayList;

import com.couchbase.client.CouchbaseClient;


public class EDPDataSourceFactory {
	
	CouchbaseClient client;
	ArrayList<URI> nodes = new ArrayList<URI>();
	
	public EDPDataSourceFactory() {
		nodes.add(URI.create("http://127.0.0.1:8091/pools"));
		try {
	      client = new CouchbaseClient(nodes, "edp","");
	    } catch (Exception e) {
	      System.err.println("Error connecting to Couchbase: " + e.getMessage());
	      System.exit(1);
	    }
	}

	public CouchbaseClient getCouchbaseClient() {
		return client;
	}

	public void setCouchbaseClient(CouchbaseClient cl) {
		this.client = cl;
	}

	
}

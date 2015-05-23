package org.ndsu.edp.dal;

import com.couchbase.client.CouchbaseClient;

public class EDPDal {

	EDPDataSourceFactory dsFactory;
	CouchbaseClient cbClient;

	public EDPDal(EDPDataSourceFactory dsFactory) {
		super();
		this.dsFactory = dsFactory;
		cbClient = dsFactory.getCouchbaseClient();
	}
	
	public void insert(String key, byte[] data){
		cbClient.set(key, data);
	}
	
	public void find(String key){
		cbClient.get(key);
	}
	
	
}

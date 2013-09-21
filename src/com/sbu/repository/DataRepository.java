package com.sbu.repository;

import com.mongodb.DBCollection;

public class DataRepository {

	protected MongoInterface mongoInterface;
	
	public DataRepository(MongoInterface Interface)
	{
		this.mongoInterface = Interface;
	}
	
	public DBCollection getCollection(String collectionName)
	{
		return mongoInterface.getCollection(collectionName);
	}
	
}

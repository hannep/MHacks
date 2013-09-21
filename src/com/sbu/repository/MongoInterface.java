package com.sbu.repository;

import java.net.UnknownHostException;
import java.util.HashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

public class MongoInterface {

	protected Mongo mongo;
	protected DB db;
	
	protected String databaseName;
	protected String hostName;
	
	protected DBCollection userCollection;
	protected DBCollection interestCollection;
	protected DBCollection socialNetworksCollection;
	protected DBCollection eventsCollection;
	protected DBCollection organizationCollection;
	
	protected HashMap<String, DBCollection> collections;
	
	public MongoInterface(String hostName, String databaseName)
	{
		this.databaseName = databaseName;
		this.hostName = hostName;
		this.collections = new HashMap<String, DBCollection>();
		initializeDB();
	}
	
	public void initializeDB()
	{
		if(mongo == null)
		{
			MongoOptions mongoOption = new MongoOptions();
			mongoOption.connectionsPerHost = 30;
			try 
			{
				mongo = new Mongo("localhost",mongoOption);
			} catch (UnknownHostException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(db == null)
		{
			db = mongo.getDB(databaseName);
			
			if(!db.collectionExists(TableNames.EVENTS_TABLE))
			{
				db.createCollection(TableNames.EVENTS_TABLE, new BasicDBObject());
			}
			
			if(!db.collectionExists(TableNames.INTERESTS_TABLE))
			{
				db.createCollection(TableNames.INTERESTS_TABLE, new BasicDBObject());
			}
			
			if(!db.collectionExists(TableNames.ORGANIZATIONS_TABLE))
			{
				db.createCollection(TableNames.ORGANIZATIONS_TABLE, new BasicDBObject());
			}
			
			if(!db.collectionExists(TableNames.SOCIAL_NETWORKS_TABLE))
			{
				db.createCollection(TableNames.SOCIAL_NETWORKS_TABLE, new BasicDBObject());
			}
			
			if(!db.collectionExists(TableNames.USERS_TABLE))
			{
				db.createCollection(TableNames.USERS_TABLE, new BasicDBObject());
			}
		}
	}

	public Mongo getMongo()
	{
		return mongo;
	}
	
	public DB getDB()
	{
		return db;
	}
	
	public DBCollection getCollection(String collectionName)
	{
		return collections.get(collectionName);
	}
}
